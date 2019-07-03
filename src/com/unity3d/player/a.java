package com.unity3d.player;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import java.util.ArrayList;
import java.util.List;

final class a {
    Camera a;
    Parameters b;
    Size c;
    int d;
    int[] e;
    c f;
    private final Object[] g = new Object[0];
    private final int h;
    private final int i;
    private final int j;
    private final int k;

    interface a {
        void onCameraFrame(a aVar, byte[] bArr);
    }

    public a(int i, int i2, int i3, int i4) {
        this.h = i;
        this.i = a(i2, 640);
        this.j = a(i3, 480);
        this.k = a(i4, 24);
    }

    private static final int a(int i, int i2) {
        return i != 0 ? i : i2;
    }

    private static void a(Parameters parameters) {
        if (parameters.getSupportedColorEffects() != null) {
            parameters.setColorEffect("none");
        }
        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
            parameters.setFocusMode("continuous-video");
        }
    }

    private void b(final a aVar) {
        synchronized (this.g) {
            this.a = Camera.open(this.h);
            this.b = this.a.getParameters();
            this.c = f();
            this.e = e();
            this.d = d();
            a(this.b);
            this.b.setPreviewSize(this.c.width, this.c.height);
            this.b.setPreviewFpsRange(this.e[0], this.e[1]);
            this.a.setParameters(this.b);
            PreviewCallback anonymousClass1 = new PreviewCallback(this) {
                long a = 0;
                final /* synthetic */ a c;

                public final void onPreviewFrame(byte[] bArr, Camera camera) {
                    if (this.c.a == camera) {
                        aVar.onCameraFrame(this.c, bArr);
                    }
                }
            };
            int i = (((this.c.width * this.c.height) * this.d) / 8) + 4096;
            this.a.addCallbackBuffer(new byte[i]);
            this.a.addCallbackBuffer(new byte[i]);
            this.a.setPreviewCallbackWithBuffer(anonymousClass1);
        }
    }

    private final int d() {
        this.b.setPreviewFormat(17);
        return ImageFormat.getBitsPerPixel(17);
    }

    private final int[] e() {
        double d = (double) (this.k * 1000);
        List supportedPreviewFpsRange = this.b.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null) {
            supportedPreviewFpsRange = new ArrayList();
        }
        int[] iArr = new int[]{this.k * 1000, this.k * 1000};
        double d2 = Double.MAX_VALUE;
        for (int[] iArr2 : r0) {
            int[] iArr22;
            double abs = Math.abs(Math.log(d / ((double) iArr22[0]))) + Math.abs(Math.log(d / ((double) iArr22[1])));
            if (abs < d2) {
                d2 = abs;
            } else {
                iArr22 = iArr;
            }
            iArr = iArr22;
        }
        return iArr;
    }

    private final Size f() {
        double d = (double) this.i;
        double d2 = (double) this.j;
        Size size = null;
        double d3 = Double.MAX_VALUE;
        for (Size size2 : this.b.getSupportedPreviewSizes()) {
            Size size22;
            double abs = Math.abs(Math.log(d / ((double) size22.width))) + Math.abs(Math.log(d2 / ((double) size22.height)));
            if (abs >= d3) {
                abs = d3;
                size22 = size;
            }
            d3 = abs;
            size = size22;
        }
        return size;
    }

    public final int a() {
        return this.h;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.unity3d.player.a.a r4) {
        /*
        r3 = this;
        r1 = r3.g;
        monitor-enter(r1);
        r0 = r3.a;	 Catch:{ all -> 0x0031 }
        if (r0 != 0) goto L_0x000a;
    L_0x0007:
        r3.b(r4);	 Catch:{ all -> 0x0031 }
    L_0x000a:
        r0 = com.unity3d.player.n.a;	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x001f;
    L_0x000e:
        r0 = com.unity3d.player.n.e;	 Catch:{ all -> 0x0031 }
        r2 = r3.a;	 Catch:{ all -> 0x0031 }
        r0 = r0.a(r2);	 Catch:{ all -> 0x0031 }
        if (r0 == 0) goto L_0x001f;
    L_0x0018:
        r0 = r3.a;	 Catch:{ all -> 0x0031 }
        r0.startPreview();	 Catch:{ all -> 0x0031 }
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
    L_0x001e:
        return;
    L_0x001f:
        r0 = r3.f;	 Catch:{ all -> 0x0031 }
        if (r0 != 0) goto L_0x002f;
    L_0x0023:
        r0 = new com.unity3d.player.a$2;	 Catch:{ all -> 0x0031 }
        r0.<init>(r3);	 Catch:{ all -> 0x0031 }
        r3.f = r0;	 Catch:{ all -> 0x0031 }
        r0 = r3.f;	 Catch:{ all -> 0x0031 }
        r0.a();	 Catch:{ all -> 0x0031 }
    L_0x002f:
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
        goto L_0x001e;
    L_0x0031:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0031 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.a.a(com.unity3d.player.a$a):void");
    }

    public final void a(byte[] bArr) {
        synchronized (this.g) {
            if (this.a != null) {
                this.a.addCallbackBuffer(bArr);
            }
        }
    }

    public final Size b() {
        return this.c;
    }

    public final void c() {
        synchronized (this.g) {
            if (this.a != null) {
                this.a.setPreviewCallbackWithBuffer(null);
                this.a.stopPreview();
                this.a.release();
                this.a = null;
            }
            if (this.f != null) {
                this.f.b();
                this.f = null;
            }
        }
    }
}
