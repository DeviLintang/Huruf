package com.unity3d.player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.File;

public class PsmMainActivity extends Activity {
    private static void a(File file) {
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                a(a);
            }
        }
        file.delete();
        Log.d(PsmMainActivity.class.getSimpleName(), "deleted : " + file.getAbsolutePath());
    }

    protected void DecompressAPK(File file) {
        new b(this).execute(new File[]{file, getFilesDir(), getFilesDir()});
    }

    protected void LaunchApp() {
        Intent intent = new Intent(this, PsmUnityActivity.class);
        intent.addFlags(65536);
        Bundle bundle = new Bundle();
        bundle.putString("path", getFilesDir().getPath());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        for (File a : getFilesDir().listFiles()) {
            a(a);
        }
        DecompressAPK(new File(super.getPackageCodePath()));
    }
}
