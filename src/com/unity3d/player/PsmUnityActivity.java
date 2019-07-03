package com.unity3d.player;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

public class PsmUnityActivity extends UnityPlayerNativeActivity {
    private String a = null;
    private String b = null;

    public ApplicationInfo getApplicationInfo() {
        ApplicationInfo applicationInfo = super.getApplicationInfo();
        applicationInfo.nativeLibraryDir = this.b;
        return applicationInfo;
    }

    public String getPackageCodePath() {
        return this.a;
    }

    protected void onCreate(Bundle bundle) {
        Log.d(getClass().getSimpleName(), "pid = " + Process.myPid());
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("path");
        Log.d(getClass().getSimpleName(), "path = " + extras.getString("path"));
        this.a = string;
        this.b = string + "/lib/armeabi-v7a";
        super.onCreate(bundle);
    }
}
