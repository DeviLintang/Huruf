package com.unity3d.player;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

final class b extends AsyncTask {
    private ProgressDialog a;
    private PsmMainActivity b;

    b(PsmMainActivity psmMainActivity) {
        this.b = psmMainActivity;
        this.a = new ProgressDialog(psmMainActivity);
        this.a.setMessage("Please wait while decompressing...");
        this.a.setIndeterminate(true);
        this.a.setCancelable(false);
        this.a.show();
    }

    private void a(String str, String str2) {
        byte[] bArr = new byte[1024];
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                File file2 = new File(str2 + File.separator + nextEntry.getName());
                Log.d(getClass().getSimpleName(), "file unzip : " + file2.getAbsoluteFile());
                new File(file2.getParent()).mkdirs();
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.close();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected final Long doInBackground(File... fileArr) {
        File file = fileArr[0];
        File file2 = fileArr[1];
        File file3 = fileArr[2];
        String file4 = file.toString();
        file2.toString();
        a(file4, file3.toString());
        return Long.valueOf(0);
    }

    protected final void onPostExecute(Long l) {
        this.a.dismiss();
        this.b.LaunchApp();
        Toast.makeText(this.a.getContext(), "DONE", 0).show();
    }

    protected final void onProgressUpdate(Integer... numArr) {
    }
}
