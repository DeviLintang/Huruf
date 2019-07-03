package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

public final class t extends FrameLayout implements SensorEventListener, OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {
    private static final boolean A;
    private static boolean a = false;
    private final UnityPlayer b;
    private final Context c;
    private final SurfaceView d;
    private final SurfaceHolder e;
    private final String f;
    private final int g;
    private final int h;
    private final boolean i;
    private final long j;
    private final long k;
    private final FrameLayout l;
    private final SensorManager m;
    private final Display n;
    private int o;
    private int p;
    private int q;
    private int r;
    private MediaPlayer s;
    private MediaController t;
    private boolean u = false;
    private boolean v = false;
    private int w = 0;
    private boolean x = false;
    private int y = 0;
    private boolean z;

    static {
        boolean z = false;
        if (Build.MANUFACTURER.equalsIgnoreCase("Amazon") && (Build.MODEL.equalsIgnoreCase("KFTT") || Build.MODEL.equalsIgnoreCase("KFJWI") || Build.MODEL.equalsIgnoreCase("KFJWA") || Build.MODEL.equalsIgnoreCase("KFSOWI") || Build.MODEL.equalsIgnoreCase("KFTHWA") || Build.MODEL.equalsIgnoreCase("KFTHWI") || Build.MODEL.equalsIgnoreCase("KFAPWA") || Build.MODEL.equalsIgnoreCase("KFAPWI"))) {
            z = true;
        }
        A = z;
    }

    protected t(UnityPlayer unityPlayer, Context context, String str, int i, int i2, int i3, boolean z, long j, long j2) {
        super(context);
        this.b = unityPlayer;
        this.c = context;
        this.l = this;
        this.d = new SurfaceView(context);
        this.e = this.d.getHolder();
        this.e.addCallback(this);
        this.e.setType(3);
        this.l.setBackgroundColor(i);
        this.l.addView(this.d);
        this.m = (SensorManager) this.c.getSystemService("sensor");
        this.n = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay();
        this.f = str;
        this.g = i2;
        this.h = i3;
        this.i = z;
        this.j = j;
        this.k = j2;
        if (a) {
            a("fileName: " + this.f);
        }
        if (a) {
            a("backgroundColor: " + i);
        }
        if (a) {
            a("controlMode: " + this.g);
        }
        if (a) {
            a("scalingMode: " + this.h);
        }
        if (a) {
            a("isURL: " + this.i);
        }
        if (a) {
            a("videoOffset: " + this.j);
        }
        if (a) {
            a("videoLength: " + this.k);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.m.registerListener(this, this.m.getDefaultSensor(1), 1);
        this.z = true;
    }

    private void a() {
        doCleanUp();
        try {
            this.s = new MediaPlayer();
            if (this.i) {
                this.s.setDataSource(this.c, Uri.parse(this.f));
            } else if (this.k != 0) {
                FileInputStream fileInputStream = new FileInputStream(this.f);
                this.s.setDataSource(fileInputStream.getFD(), this.j, this.k);
                fileInputStream.close();
            } else {
                try {
                    AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f);
                    this.s.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                } catch (IOException e) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.f);
                    this.s.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                }
            }
            this.s.setDisplay(this.e);
            this.s.setOnBufferingUpdateListener(this);
            this.s.setOnCompletionListener(this);
            this.s.setOnPreparedListener(this);
            this.s.setOnVideoSizeChangedListener(this);
            this.s.setAudioStreamType(3);
            this.s.prepare();
            if (this.g == 0 || this.g == 1) {
                this.t = new MediaController(this.c);
                this.t.setMediaPlayer(this);
                this.t.setAnchorView(this.d);
                this.t.setEnabled(true);
                this.t.show();
            }
        } catch (Exception e2) {
            if (a) {
                a("error: " + e2.getMessage() + e2);
            }
            onDestroy();
        }
    }

    private static void a(String str) {
        Log.v("Video", str);
    }

    private void b() {
        if (!isPlaying()) {
            if (a) {
                a("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.x) {
                start();
            }
        }
    }

    public static int calculateDeviceOrientation(SensorEvent sensorEvent, Display display) {
        int i;
        int i2 = 1;
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        float sqrt = 1.0f / ((float) Math.sqrt((double) (((f * f) + (f2 * f2)) + (f3 * f3))));
        float f4 = f * sqrt;
        f2 *= sqrt;
        f3 *= sqrt;
        if ((((display.getOrientation() & 1) == 0 ? 1 : 0) ^ (display.getHeight() > display.getWidth() ? 1 : 0)) != 0) {
            f = -f4;
            f4 = f2;
        } else {
            f = f2;
        }
        if (A) {
            f4 = -f4;
        }
        if (-1.0f < f) {
            f2 = f;
        } else {
            i2 = 0;
            f2 = -1.0f;
        }
        if (f2 < (-f)) {
            f2 = -f;
            i = 2;
        } else {
            i = i2;
        }
        if (f2 < f4) {
            i = 3;
            f2 = f4;
        }
        if (f2 < (-f4)) {
            f2 = -f4;
            i = 4;
        }
        if (f2 < f3) {
            i = 5;
            f2 = f3;
        }
        if (f2 < (-f3)) {
            f2 = -f3;
            i = 6;
        }
        return ((double) f2) < 0.5d * Math.sqrt(3.0d) ? 0 : i;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    protected final void doCleanUp() {
        if (this.s != null) {
            this.s.release();
            this.s = null;
        }
        this.q = 0;
        this.r = 0;
        this.v = false;
        this.u = false;
    }

    public final int getBufferPercentage() {
        return this.i ? this.w : 100;
    }

    public final int getCurrentPosition() {
        return this.s == null ? 0 : this.s.getCurrentPosition();
    }

    public final int getDuration() {
        return this.s == null ? 0 : this.s.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.v && this.u;
        return this.s == null ? !z : this.s.isPlaying() || !z;
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (a) {
            a("onBufferingUpdate percent:" + i);
        }
        this.w = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (a) {
            a("onCompletion called");
        }
        onDestroy();
    }

    public final void onControllerHide() {
    }

    protected final void onDestroy() {
        onPause();
        doCleanUp();
        UnityPlayer.a(new Runnable(this) {
            final /* synthetic */ t a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.b.hideVideoPlayer();
            }
        });
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.g != 2 || i == 0 || keyEvent.isSystem())) {
            return this.t != null ? this.t.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        } else {
            onDestroy();
            return true;
        }
    }

    protected final void onPause() {
        if (a) {
            a("onPause called");
        }
        this.m.unregisterListener(this);
        if (!this.x) {
            pause();
            this.x = false;
        }
        if (this.s != null) {
            this.y = this.s.getCurrentPosition();
        }
        this.z = false;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (a) {
            a("onPrepared called");
        }
        this.v = true;
        if (this.v && this.u) {
            b();
        }
    }

    protected final void onResume() {
        if (a) {
            a("onResume called");
        }
        if (!this.z) {
            this.m.registerListener(this, this.m.getDefaultSensor(1), 1);
            if (!this.x) {
                start();
            }
        }
        this.z = true;
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            this.b.nativeDeviceOrientation(calculateDeviceOrientation(sensorEvent, this.n));
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.g != 2 || action != 0) {
            return this.t != null ? this.t.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        } else {
            onDestroy();
            return true;
        }
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (a) {
            a("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.u = true;
            this.q = i;
            this.r = i2;
            if (this.v && this.u) {
                b();
            }
        } else if (a) {
            a("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        if (this.s != null) {
            this.s.pause();
            this.x = true;
        }
    }

    public final void seekTo(int i) {
        if (this.s != null) {
            this.s.seekTo(i);
        }
    }

    public final void start() {
        if (this.s != null) {
            this.s.start();
            this.x = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (a) {
            a("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        this.o = i2;
        this.p = i3;
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (a) {
            a("surfaceCreated called");
        }
        a();
        seekTo(this.y);
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (a) {
            a("surfaceDestroyed called");
        }
        doCleanUp();
    }

    protected final void updateVideoLayout() {
        if (a) {
            a("updateVideoLayout");
        }
        WindowManager windowManager = (WindowManager) this.c.getSystemService("window");
        this.o = windowManager.getDefaultDisplay().getWidth();
        this.p = windowManager.getDefaultDisplay().getHeight();
        int i = this.o;
        int i2 = this.p;
        if (this.h == 1 || this.h == 2) {
            float f = ((float) this.q) / ((float) this.r);
            if (((float) this.o) / ((float) this.p) <= f) {
                i2 = (int) (((float) this.o) / f);
            } else {
                i = (int) (((float) this.p) * f);
            }
        } else if (this.h == 0) {
            i = this.q;
            i2 = this.r;
        }
        if (a) {
            a("frameWidth = " + i + "; frameHeight = " + i2);
        }
        this.l.updateViewLayout(this.d, new LayoutParams(i, i2, 17));
    }
}
