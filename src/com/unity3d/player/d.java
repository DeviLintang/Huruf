package com.unity3d.player;

import android.view.MotionEvent;
import android.view.View;

public final class d implements f {
    public final boolean a(View view, MotionEvent motionEvent) {
        return view.dispatchGenericMotionEvent(motionEvent);
    }
}
