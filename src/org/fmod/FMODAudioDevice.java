package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {
    private static int k = 0;
    private static int l = 1;
    private static int m = 2;
    private static int n = 3;
    private volatile Thread a = null;
    private volatile boolean b = false;
    private volatile a c = null;
    private AudioTrack d = null;
    private boolean e = false;
    private ByteBuffer f = null;
    private byte[] g = null;
    private volatile a h;
    private Object i = new Object[0];
    private boolean j = false;

    private class a extends Thread {
        final /* synthetic */ FMODAudioDevice a;
        private Object b = new Object();

        a(FMODAudioDevice fMODAudioDevice) {
            this.a = fMODAudioDevice;
            super("FMODStreamBlocker");
        }

        private void a() {
            synchronized (this.b) {
                this.b.notifyAll();
            }
        }

        public final void run() {
            if (this.a.fmodBlockStreaming() != 0) {
                throw new RuntimeException("Unable to block fmod streaming thread");
            }
            synchronized (this.b) {
                try {
                    this.b.wait();
                } catch (InterruptedException e) {
                }
            }
            if (this.a.fmodUnblockStreaming() != 0) {
                throw new RuntimeException("Unable to unblock fmod streaming thread");
            }
        }
    }

    private synchronized void blockStreaming() {
        if (isInitialized() && this.c == null) {
            this.c = new a(this);
            this.c.start();
        }
    }

    private native int fmodBlockStreaming();

    private native int fmodGetInfo(int i);

    private native int fmodInitJni();

    private native int fmodProcess(ByteBuffer byteBuffer);

    private native int fmodUnblockStreaming();

    private void releaseAudioTrack() {
        if (this.d != null) {
            if (this.d.getState() == 1) {
                this.d.stop();
            }
            this.d.release();
            this.d = null;
        }
        this.f = null;
        this.g = null;
        this.e = false;
    }

    private synchronized void unblockStreaming() {
        if (this.c != null) {
            do {
                try {
                    this.c.a();
                    this.c.join(10);
                } catch (InterruptedException e) {
                }
            } while (this.c.isAlive());
            this.c = null;
        }
    }

    public void audioTrackInitialized() {
        synchronized (this.i) {
            this.j = true;
            this.i.notifyAll();
        }
    }

    public synchronized void close() {
        stop();
        unblockStreaming();
    }

    native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isInitialized() {
        return fmodGetInfo(k) > 0;
    }

    public boolean isRunning() {
        return this.a != null && this.a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.b) {
            if (isInitialized()) {
                int i2;
                if (this.e || i <= 0) {
                    i2 = i;
                } else {
                    releaseAudioTrack();
                    int fmodGetInfo = fmodGetInfo(k);
                    int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                    int fmodGetInfo2 = fmodGetInfo(l);
                    i2 = fmodGetInfo(m);
                    if ((fmodGetInfo2 * i2) * 4 > round) {
                        round = (i2 * fmodGetInfo2) * 4;
                    }
                    this.d = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                    this.e = this.d.getState() == 1;
                    if (this.e) {
                        this.f = ByteBuffer.allocateDirect((fmodGetInfo2 * 2) * 2);
                        this.g = new byte[this.f.capacity()];
                        this.d.play();
                        i2 = 3;
                    } else {
                        Log.e("FMOD", "AudioTrack failed to initialize (status " + this.d.getState() + ")");
                        releaseAudioTrack();
                        i2 = i - 1;
                    }
                }
                if (!this.e) {
                    i = i2;
                } else if (fmodGetInfo(n) == 1) {
                    fmodProcess(this.f);
                    this.f.get(this.g, 0, this.f.capacity());
                    this.d.write(this.g, 0, this.f.capacity());
                    this.f.position(0);
                    i = i2;
                } else {
                    releaseAudioTrack();
                    i = i2;
                }
            } else {
                synchronized (this.i) {
                    try {
                        if (!this.j) {
                            this.i.wait();
                        }
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.a != null) {
            stop();
        }
        this.a = new Thread(this, "FMODAudioDevice");
        this.a.setPriority(10);
        this.b = true;
        fmodInitJni();
        unblockStreaming();
        this.a.start();
        if (this.h != null) {
            this.h.b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.h == null) {
            this.h = new a(this, i, i2);
            this.h.b();
        }
        return this.h.a();
    }

    public synchronized void stop() {
        while (this.a != null) {
            this.b = false;
            try {
                synchronized (this.i) {
                    this.i.notifyAll();
                }
                this.a.join();
                this.a = null;
                blockStreaming();
            } catch (InterruptedException e) {
            }
        }
        if (this.h != null) {
            this.h.c();
        }
    }

    public synchronized void stopAudioRecord() {
        if (this.h != null) {
            this.h.c();
            this.h = null;
        }
    }
}
