package com.unity3d.player;

import android.os.Build.VERSION;

public final class n {
    static final boolean a = (VERSION.SDK_INT >= 11);
    static final boolean b = (VERSION.SDK_INT >= 12);
    static final boolean c = (VERSION.SDK_INT >= 21);
    static final boolean d;
    static final g e = (a ? new e() : null);
    static final f f = (b ? new d() : null);
    static final h g;

    static {
        h hVar = null;
        boolean z = true;
        if (VERSION.SDK_INT < 23) {
            z = false;
        }
        d = z;
        if (d) {
            hVar = new k();
        }
        g = hVar;
    }
}
