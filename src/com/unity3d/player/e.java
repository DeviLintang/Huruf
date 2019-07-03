package com.unity3d.player;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Handler;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;

public final class e implements g {
    private static final SurfaceTexture a = new SurfaceTexture(-1);
    private volatile boolean b;

    private void a(final View view, int i) {
        Handler handler = view.getHandler();
        if (handler == null) {
            a(view, this.b);
        } else {
            handler.postDelayed(new Runnable(this) {
                final /* synthetic */ e b;

                public final void run() {
                    this.b.a(view, this.b.b);
                }
            }, (long) i);
        }
    }

    public final void a(final View view) {
        view.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener(this) {
            final /* synthetic */ e b;

            public final void onSystemUiVisibilityChange(int i) {
                this.b.a(view, 1000);
            }
        });
    }

    public final void a(View view, boolean z) {
        this.b = z;
        view.setSystemUiVisibility(this.b ? view.getSystemUiVisibility() | 1 : view.getSystemUiVisibility() & -2);
    }

    public final boolean a() {
        return this.b;
    }

    public final boolean a(Camera camera) {
        try {
            camera.setPreviewTexture(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public final void b(View view) {
        if (this.b) {
            a(view, false);
            this.b = true;
            a(view, 500);
        }
    }
}
