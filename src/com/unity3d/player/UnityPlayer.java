package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.provider.Settings.System;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import org.fmod.FMODAudioDevice;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UnityPlayer extends FrameLayout implements a {
    public static Activity currentActivity = null;
    private static boolean s = true;
    private String A = null;
    private NetworkInfo B = null;
    private Bundle C = new Bundle();
    private List D = new ArrayList();
    private t E;
    private ProgressBar F = null;
    private Runnable G = new Runnable(this) {
        final /* synthetic */ UnityPlayer a;

        {
            this.a = r1;
        }

        public final void run() {
            int o = this.a.nativeActivityIndicatorStyle();
            if (o >= 0) {
                if (this.a.F == null) {
                    this.a.F = new ProgressBar(this.a.m, null, new int[]{16842874, 16843401, 16842873, 16843400}[o]);
                    this.a.F.setIndeterminate(true);
                    this.a.F.setLayoutParams(new LayoutParams(-2, -2, 51));
                    this.a.addView(this.a.F);
                }
                this.a.F.setVisibility(0);
                this.a.bringChildToFront(this.a.F);
            }
        }
    };
    private Runnable H = new Runnable(this) {
        final /* synthetic */ UnityPlayer a;

        {
            this.a = r1;
        }

        public final void run() {
            if (this.a.F != null) {
                this.a.F.setVisibility(8);
                this.a.F = null;
            }
        }
    };
    b a = new b(this);
    p b = null;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private final i f;
    private final q g;
    private boolean h = false;
    private s i = new s();
    private final ConcurrentLinkedQueue j = new ConcurrentLinkedQueue();
    private BroadcastReceiver k = null;
    private boolean l = false;
    private ContextWrapper m;
    private SurfaceView n;
    private WakeLock o;
    private SensorManager p;
    private WindowManager q;
    private FMODAudioDevice r;
    private boolean t;
    private boolean u = true;
    private int v;
    private int w;
    private int x = 0;
    private int y = 0;
    private final o z;

    private abstract class c implements Runnable {
        final /* synthetic */ UnityPlayer f;

        private c(UnityPlayer unityPlayer) {
            this.f = unityPlayer;
        }

        public abstract void a();

        public final void run() {
            if (!this.f.isFinishing()) {
                a();
            }
        }
    }

    enum a {
        PAUSE,
        RESUME,
        QUIT,
        FOCUS_GAINED,
        FOCUS_LOST
    }

    private class b extends Thread {
        ArrayBlockingQueue a = new ArrayBlockingQueue(32);
        boolean b = false;
        final /* synthetic */ UnityPlayer c;

        b(UnityPlayer unityPlayer) {
            this.c = unityPlayer;
        }

        private void a(a aVar) {
            try {
                this.a.put(aVar);
            } catch (InterruptedException e) {
                interrupt();
            }
        }

        public final void a() {
            a(a.QUIT);
        }

        public final void a(boolean z) {
            a(z ? a.FOCUS_GAINED : a.FOCUS_LOST);
        }

        public final void b() {
            a(a.RESUME);
        }

        public final void c() {
            a(a.PAUSE);
        }

        public final void run() {
            setName("UnityMain");
            while (true) {
                try {
                    a aVar = (a) this.a.take();
                    if (aVar != a.QUIT) {
                        if (aVar == a.RESUME) {
                            this.b = true;
                        } else if (aVar == a.PAUSE) {
                            this.b = false;
                            this.c.executeGLThreadJobs();
                        } else if (aVar == a.FOCUS_LOST && !this.b) {
                            this.c.executeGLThreadJobs();
                        }
                        if (this.b) {
                            do {
                                this.c.executeGLThreadJobs();
                                if (!(this.c.isFinishing() || this.c.nativeRender())) {
                                    this.c.b();
                                }
                                if (this.a.peek() != null) {
                                    break;
                                }
                            } while (!interrupted());
                        }
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static {
        new r().a();
        System.loadLibrary("main");
    }

    public UnityPlayer(ContextWrapper contextWrapper) {
        super(contextWrapper);
        if (contextWrapper instanceof Activity) {
            currentActivity = (Activity) contextWrapper;
        }
        this.g = new q(this);
        this.m = contextWrapper;
        this.f = contextWrapper instanceof Activity ? new m(contextWrapper) : null;
        this.z = new o(contextWrapper, this);
        a();
        a(this.m.getApplicationInfo());
        if (s.c()) {
            nativeFile(this.m.getPackageCodePath());
            k();
            this.n = new SurfaceView(contextWrapper);
            this.n.getHolder().setFormat(2);
            this.n.getHolder().addCallback(new Callback(this) {
                final /* synthetic */ UnityPlayer a;

                {
                    this.a = r1;
                }

                public final void surfaceChanged(SurfaceHolder surfaceHolder, final int i, final int i2, final int i3) {
                    this.a.a(new c(this) {
                        final /* synthetic */ AnonymousClass12 d;

                        public final void a() {
                            int i = i2;
                            int i2 = i3;
                            if (!((this.d.a.x == 0 && this.d.a.y == 0) || (this.d.a.x == i && this.d.a.y == i2))) {
                                this.d.a.setScreenSize(this.d.a.x, this.d.a.y, n.a ? n.e.a() : false);
                                i = this.d.a.x;
                                i2 = this.d.a.y;
                            }
                            this.d.a.v = i;
                            this.d.a.w = i2;
                            this.d.a.nativeResize(this.d.a.v, this.d.a.w, this.d.a.n.getWidth(), this.d.a.n.getHeight());
                            this.d.a.h();
                        }
                    });
                }

                public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                    Surface surface = surfaceHolder.getSurface();
                    this.a.i;
                    if (s.c()) {
                        this.a.nativeRecreateGfxState(surface);
                    }
                }

                public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    this.a.nativeRecreateGfxState(null);
                }
            });
            this.n.setFocusable(true);
            this.n.setFocusableInTouchMode(true);
            this.g.c(this.n);
            this.t = false;
            this.o = ((PowerManager) this.m.getSystemService("power")).newWakeLock(26, "Unity-VideoPlayerWakeLock");
            c();
            initJni(contextWrapper);
            nativeInitWWW(WWW.class);
            if (n.d && currentActivity != null) {
                n.g.a(currentActivity, new Runnable(this) {
                    final /* synthetic */ UnityPlayer a;

                    {
                        this.a = r1;
                    }

                    public final void run() {
                        this.a.b(new Runnable(this) {
                            final /* synthetic */ AnonymousClass15 a;

                            {
                                this.a = r1;
                            }

                            public final void run() {
                                this.a.a.i.d();
                                this.a.a.g();
                            }
                        });
                    }
                });
            }
            if (n.a) {
                n.e.a((View) this);
            }
            this.p = (SensorManager) this.m.getSystemService("sensor");
            this.q = (WindowManager) this.m.getSystemService("window");
            nativeSetDefaultDisplay(this.q.getDefaultDisplay().getDisplayId());
            this.m.setTheme(l());
            c(!getStatusBarHidden());
            this.a.start();
            return;
        }
        AlertDialog create = new Builder(this.m).setTitle("Failure to initialize!").setPositiveButton("OK", new OnClickListener(this) {
            final /* synthetic */ UnityPlayer a;

            {
                this.a = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                this.a.b();
            }
        }).setMessage("Your hardware does not support this application, sorry!").create();
        create.setCancelable(false);
        create.show();
    }

    public static native void UnitySendMessage(String str, String str2, String str3);

    private static String a(String str) {
        byte[] digest;
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(str);
            long length = new File(str).length();
            fileInputStream.skip(length - Math.min(length, 65558));
            byte[] bArr = new byte[1024];
            for (int i2 = 0; i2 != -1; i2 = fileInputStream.read(bArr)) {
                instance.update(bArr, 0, i2);
            }
            digest = instance.digest();
        } catch (FileNotFoundException e) {
            digest = null;
        } catch (IOException e2) {
            digest = null;
        } catch (NoSuchAlgorithmException e3) {
            digest = null;
        }
        if (digest == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i < digest.length) {
            stringBuffer.append(Integer.toString((digest[i] & 255) + 256, 16).substring(1));
            i++;
        }
        return stringBuffer.toString();
    }

    private void a() {
        try {
            File file = new File(this.m.getPackageCodePath(), "assets/bin/Data/settings.xml");
            InputStream fileInputStream = file.exists() ? new FileInputStream(file) : this.m.getAssets().open("bin/Data/settings.xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(fileInputStream, null);
            String str = null;
            String str2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    str2 = newPullParser.getName();
                    String str3 = str;
                    for (int i = 0; i < newPullParser.getAttributeCount(); i++) {
                        if (newPullParser.getAttributeName(i).equalsIgnoreCase("name")) {
                            str3 = newPullParser.getAttributeValue(i);
                        }
                    }
                    str = str3;
                } else if (eventType == 3) {
                    str2 = null;
                } else if (eventType == 4 && str != null) {
                    if (str2.equalsIgnoreCase("integer")) {
                        this.C.putInt(str, Integer.parseInt(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("string")) {
                        this.C.putString(str, newPullParser.getText());
                    } else if (str2.equalsIgnoreCase("bool")) {
                        this.C.putBoolean(str, Boolean.parseBoolean(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("float")) {
                        this.C.putFloat(str, Float.parseFloat(newPullParser.getText()));
                    }
                    str = null;
                }
            }
        } catch (Exception e) {
            j.Log(6, "Unable to locate player settings. " + e.getLocalizedMessage());
            b();
        }
    }

    private static void a(ApplicationInfo applicationInfo) {
        if (NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            s.a();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to load libraries from libmain.so");
    }

    private void a(c cVar) {
        if (!isFinishing()) {
            c((Runnable) cVar);
        }
    }

    static void a(Runnable runnable) {
        new Thread(runnable).start();
    }

    private static void a(String str, WakeLock wakeLock, boolean z) {
        try {
            if (z != wakeLock.isHeld()) {
                if (z) {
                    wakeLock.acquire();
                    if (!wakeLock.isHeld()) {
                        j.Log(5, String.format("Unable to acquire %s wake-lock. Make sure 'android.permission.WAKE_LOCK' has been set in the manifest.", new Object[]{str}));
                    }
                } else if (!z) {
                    wakeLock.release();
                }
            }
        } catch (Exception e) {
            j.Log(5, String.format("Unable to acquire/release %s wake-lock. Make sure 'android.permission.WAKE_LOCK' has been set in the manifest.", new Object[]{str}));
        }
    }

    private void a(boolean z) {
        a("video", this.o, z);
    }

    private boolean a(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 25 || keyCode == 24) {
            return false;
        }
        final KeyEvent keyEvent2 = new KeyEvent(keyEvent);
        a(new c(this) {
            final /* synthetic */ UnityPlayer b;

            public final void a() {
                this.b.nativeInjectEvent(keyEvent2);
            }
        });
        return true;
    }

    private boolean a(MotionEvent motionEvent) {
        if (this.l) {
            return false;
        }
        final MotionEvent obtain = MotionEvent.obtain(motionEvent);
        a(new c(this) {
            final /* synthetic */ UnityPlayer b;

            public final void a() {
                this.b.nativeInjectEvent(obtain);
                obtain.recycle();
            }
        });
        return true;
    }

    private static String[] a(Context context) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        try {
            int i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + packageName);
                if (file.exists()) {
                    if (i > 0) {
                        String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                        if (new File(str).isFile()) {
                            vector.add(str);
                        }
                    }
                    if (i > 0) {
                        packageName = file + File.separator + "patch." + i + "." + packageName + ".obb";
                        if (new File(packageName).isFile()) {
                            vector.add(packageName);
                        }
                    }
                }
            }
            String[] strArr = new String[vector.size()];
            vector.toArray(strArr);
            return strArr;
        } catch (NameNotFoundException e) {
            return new String[0];
        }
    }

    private static int b(boolean z) {
        return z ? VERSION.SDK_INT >= 21 ? 16974383 : VERSION.SDK_INT >= 14 ? 16973933 : 16973831 : VERSION.SDK_INT >= 21 ? 16974382 : VERSION.SDK_INT >= 14 ? 16973932 : 16973830;
    }

    private void b() {
        if ((this.m instanceof Activity) && !((Activity) this.m).isFinishing()) {
            ((Activity) this.m).finish();
        }
    }

    private void b(Runnable runnable) {
        if (this.m instanceof Activity) {
            ((Activity) this.m).runOnUiThread(runnable);
        } else {
            j.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    private void c() {
        boolean a = new l((Activity) this.m).a();
        this.l = a;
        nativeForwardEventsToDalvik(a);
    }

    private void c(Runnable runnable) {
        if (!s.c()) {
            return;
        }
        if (Thread.currentThread() == this.a) {
            runnable.run();
        } else {
            this.j.add(runnable);
        }
    }

    private void c(boolean z) {
        Window window = ((Activity) this.m).getWindow();
        if (z) {
            window.clearFlags(1024);
        } else {
            window.setFlags(1024, 1024);
        }
    }

    private void d() {
        for (a c : this.D) {
            c.c();
        }
    }

    private void e() {
        for (a aVar : this.D) {
            try {
                aVar.a((a) this);
            } catch (Exception e) {
                j.Log(6, "Unable to initialize camera: " + e.getMessage());
                aVar.c();
            }
        }
    }

    private void f() {
        if (this.r != null) {
            this.r.close();
        }
        nativeDone();
    }

    private void g() {
        if (!this.i.f()) {
            return;
        }
        if (this.E != null) {
            a(true);
            this.E.onResume();
            return;
        }
        this.i.c(true);
        e();
        this.a.b();
        this.z.e();
        this.A = null;
        this.B = null;
        if (s.c()) {
            k();
        }
        c(new Runnable(this) {
            final /* synthetic */ UnityPlayer a;

            {
                this.a = r1;
            }

            public final void run() {
                this.a.nativeResume();
            }
        });
        if (s && this.r == null) {
            this.r = new FMODAudioDevice();
        }
        if (this.r != null && !this.r.isRunning()) {
            this.r.start();
        }
    }

    private void h() {
        if (this.m instanceof Activity) {
            float f = 0.0f;
            if (!getStatusBarHidden()) {
                Activity activity = (Activity) this.m;
                Rect rect = new Rect();
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                f = (float) rect.top;
            }
            nativeSetTouchDeltaY(f);
        }
    }

    private static void i() {
        if (!s.c()) {
            return;
        }
        if (NativeLoader.unload()) {
            s.b();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
    }

    private final native void initJni(Context context);

    private boolean j() {
        return this.m.getPackageManager().hasSystemFeature("android.hardware.camera") || this.m.getPackageManager().hasSystemFeature("android.hardware.camera.front");
    }

    private void k() {
        if (this.C.getBoolean("useObb")) {
            for (String str : a(this.m)) {
                String a = a(str);
                if (this.C.getBoolean(a)) {
                    nativeFile(str);
                }
                this.C.remove(a);
            }
        }
    }

    private int l() {
        int i;
        try {
            i = this.m.getApplicationInfo().theme;
        } catch (Exception e) {
            j.Log(5, "Failed to obtain current theme, applying best theme available on device");
            i = 16973831;
        }
        return i == 16973831 ? b(getStatusBarHidden()) : i;
    }

    private final native int nativeActivityIndicatorStyle();

    private final native void nativeDone();

    private final native void nativeFile(String str);

    private final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWWW(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(Surface surface);

    private final native boolean nativeRender();

    private final native boolean nativeRequested32bitDisplayBuffer();

    private final native int nativeRequestedAA();

    private final native void nativeResize(int i, int i2, int i3, int i4);

    private final native void nativeResume();

    private final native void nativeSetDefaultDisplay(int i);

    private final native void nativeSetExtras(Bundle bundle);

    private final native void nativeSetInputCanceled(boolean z);

    private final native void nativeSetInputString(String str);

    private final native void nativeSetTouchDeltaY(float f);

    private final native void nativeSoftInputClosed();

    private final native void nativeVideoFrameCallback(int i, byte[] bArr, int i2, int i3);

    protected boolean Location_IsServiceEnabledByUser() {
        return this.z.a();
    }

    protected void Location_SetDesiredAccuracy(float f) {
        this.z.b(f);
    }

    protected void Location_SetDistanceFilter(float f) {
        this.z.a(f);
    }

    protected void Location_StartUpdatingLocation() {
        this.z.b();
    }

    protected void Location_StopUpdatingLocation() {
        this.z.c();
    }

    protected void closeCamera(int i) {
        for (a aVar : this.D) {
            if (aVar.a() == i) {
                aVar.c();
                this.D.remove(aVar);
                return;
            }
        }
    }

    public void configurationChanged(Configuration configuration) {
        if (this.n instanceof SurfaceView) {
            this.n.getHolder().setSizeFromLayout();
        }
        if (this.e && configuration.hardKeyboardHidden == 2) {
            ((InputMethodManager) this.m.getSystemService("input_method")).toggleSoftInput(0, 1);
        }
        if (this.E != null) {
            this.E.updateVideoLayout();
        }
    }

    protected void disableLogger() {
        j.a = true;
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.j.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    protected void forwardMotionEventToDalvik(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        this.f.a(j, j2, i, i2, iArr, fArr, i3, f, f2, i4, i5, i6, i7, i8, jArr, fArr2);
    }

    protected int getCameraOrientation(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.orientation;
    }

    protected int getInternetReachability() {
        try {
            if (this.B == null) {
                this.B = ((ConnectivityManager) this.m.getSystemService("connectivity")).getActiveNetworkInfo();
            }
            NetworkInfo networkInfo = this.B;
            return networkInfo == null ? 0 : !networkInfo.isConnected() ? 0 : networkInfo.getType() + 1;
        } catch (Exception e) {
            j.Log(5, "android.permission.ACCESS_NETWORK_STATE not available?");
            return 0;
        }
    }

    protected int getNumCameras() {
        return !j() ? 0 : Camera.getNumberOfCameras();
    }

    protected int getScreenTimeout() {
        return System.getInt(this.m.getContentResolver(), "screen_off_timeout", 15000) / 1000;
    }

    public Bundle getSettings() {
        return this.C;
    }

    protected int getSplashMode() {
        return this.C.getInt("splash_mode");
    }

    protected boolean getStatusBarHidden() {
        return this.C.getBoolean("hide_status_bar", true);
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        final Runnable anonymousClass5 = new Runnable(this) {
            final /* synthetic */ UnityPlayer a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.e) {
                    ((InputMethodManager) this.a.m.getSystemService("input_method")).toggleSoftInput(1, 0);
                    this.a.e = false;
                } else if (this.a.b != null) {
                    this.a.b.dismiss();
                    this.a.b = null;
                }
            }
        };
        if (n.c) {
            a(new c(this) {
                final /* synthetic */ UnityPlayer b;

                public final void a() {
                    this.b.b(anonymousClass5);
                }
            });
        } else {
            b(anonymousClass5);
        }
    }

    protected void hideVideoPlayer() {
        b(new Runnable(this) {
            final /* synthetic */ UnityPlayer a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.E != null) {
                    this.a.g.c(this.a.n);
                    this.a.removeView(this.a.E);
                    this.a.E = null;
                    this.a.resume();
                    this.a.a(false);
                }
            }
        });
    }

    public void init(int i, boolean z) {
    }

    protected int[] initCamera(int i, int i2, int i3, int i4) {
        a aVar = new a(i, i2, i3, i4);
        try {
            aVar.a((a) this);
            this.D.add(aVar);
            Size b = aVar.b();
            return new int[]{b.width, b.height};
        } catch (Exception e) {
            j.Log(6, "Unable to initialize camera: " + e.getMessage());
            aVar.c();
            return null;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        if (inputEvent instanceof KeyEvent) {
            return a((KeyEvent) inputEvent);
        }
        if (inputEvent instanceof MotionEvent) {
            return a((MotionEvent) inputEvent);
        }
        j.Log(3, String.format("Dropping unknown event type '%s'", new Object[]{inputEvent}));
        return false;
    }

    protected boolean isCameraFrontFacing(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.facing == 1;
    }

    protected boolean isFinishing() {
        if (!this.t) {
            boolean z = (this.m instanceof Activity) && ((Activity) this.m).isFinishing();
            this.t = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            j.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            j.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    final native void nativeDeviceOrientation(int i);

    final native void nativeForwardEventsToDalvik(boolean z);

    protected native void nativeSetLocation(float f, float f2, float f3, float f4, double d, float f5);

    protected native void nativeSetLocationStatus(int i);

    public void onCameraFrame(a aVar, byte[] bArr) {
        final int a = aVar.a();
        final Size b = aVar.b();
        final byte[] bArr2 = bArr;
        final a aVar2 = aVar;
        a(new c(this) {
            final /* synthetic */ UnityPlayer e;

            public final void a() {
                this.e.nativeVideoFrameCallback(a, bArr2, b.width, b.height);
                aVar2.a(bArr2);
            }
        });
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return a(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return a(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return a(keyEvent);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        return (this.e && i == 4) ? a(keyEvent) : false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return a(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return a(motionEvent);
    }

    public void pause() {
        if (this.E != null) {
            this.E.onPause();
            a(false);
            return;
        }
        reportSoftInputStr(null, 1, true);
        if (this.i.g()) {
            if (s.c()) {
                final Semaphore semaphore = new Semaphore(0);
                if (isFinishing()) {
                    c(new Runnable(this) {
                        final /* synthetic */ UnityPlayer b;

                        public final void run() {
                            this.b.f();
                            semaphore.release();
                        }
                    });
                } else {
                    c(new Runnable(this) {
                        final /* synthetic */ UnityPlayer b;

                        public final void run() {
                            if (this.b.nativePause()) {
                                this.b.t = true;
                                this.b.f();
                                semaphore.release(2);
                                return;
                            }
                            semaphore.release();
                        }
                    });
                }
                try {
                    if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                        j.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e) {
                    j.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.i.c(false);
            this.i.b(true);
            d();
            if (this.r != null) {
                this.r.stop();
            }
            this.a.c();
            this.z.d();
        }
    }

    public void quit() {
        this.t = true;
        if (!this.i.e()) {
            pause();
        }
        this.a.a();
        try {
            this.a.join(4000);
        } catch (InterruptedException e) {
            this.a.interrupt();
        }
        if (this.k != null) {
            this.m.unregisterReceiver(this.k);
        }
        this.k = null;
        if (s.c()) {
            removeAllViews();
        }
        i();
        kill();
    }

    protected void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        a(new c(this) {
            final /* synthetic */ UnityPlayer d;

            public final void a() {
                if (z) {
                    this.d.nativeSetInputCanceled(true);
                } else if (str != null) {
                    this.d.nativeSetInputString(str);
                }
                if (i == 1) {
                    this.d.nativeSoftInputClosed();
                }
            }
        });
    }

    protected void restartFMODAudioDevice() {
        this.r.stop();
        this.r.start();
    }

    public void resume() {
        if (n.a) {
            n.e.b(this);
        }
        this.i.b(false);
        g();
    }

    protected void setHideInputField(boolean z) {
        this.d = z;
    }

    protected void setScreenSize(int i, int i2, boolean z) {
        boolean z2 = true;
        final SurfaceView surfaceView = this.n;
        surfaceView.getHolder().getSurfaceFrame();
        final boolean z3 = (surfaceView.getWidth() == i && surfaceView.getHeight() == i2) || (i == 0 && i2 == 0);
        if (!(i == -1 && i2 == -1)) {
            z2 = false;
        }
        if (!z2) {
            if (z3) {
                this.x = 0;
                this.y = 0;
            } else {
                this.x = i;
                this.y = i2;
            }
        }
        final int i3 = i;
        final int i4 = i2;
        final boolean z4 = z;
        b(new Runnable(this) {
            final /* synthetic */ UnityPlayer g;

            public final void run() {
                if (!z2) {
                    if (z3) {
                        surfaceView.getHolder().setSizeFromLayout();
                    } else {
                        surfaceView.getHolder().setFixedSize(i3, i4);
                    }
                    surfaceView.invalidate();
                }
                if (n.a) {
                    n.e.a(this.g, z4);
                }
            }
        });
    }

    protected void setSoftInputStr(final String str) {
        b(new Runnable(this) {
            final /* synthetic */ UnityPlayer b;

            public final void run() {
                if (this.b.b != null && str != null) {
                    this.b.b.a(str);
                }
            }
        });
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        final UnityPlayer unityPlayer = this;
        final String str3 = str;
        final int i2 = i;
        final boolean z5 = z;
        final boolean z6 = z2;
        final boolean z7 = z3;
        final boolean z8 = z4;
        final String str4 = str2;
        b(new Runnable(this) {
            final /* synthetic */ UnityPlayer i;

            public final void run() {
                if (this.i.d) {
                    ((InputMethodManager) unityPlayer.m.getSystemService("input_method")).toggleSoftInput(0, 1);
                    this.i.e = true;
                    return;
                }
                this.i.b = new p(this.i.m, unityPlayer, str3, i2, z5, z6, z7, str4);
                this.i.b.show();
            }
        });
    }

    protected void showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        final String str2 = str;
        final int i6 = i;
        final int i7 = i2;
        final int i8 = i3;
        final boolean z2 = z;
        final int i9 = i4;
        final int i10 = i5;
        b(new Runnable(this) {
            final /* synthetic */ UnityPlayer h;

            public final void run() {
                if (this.h.E == null) {
                    this.h.a(true);
                    this.h.pause();
                    this.h.E = new t(this.h, this.h.m, str2, i6, i7, i8, z2, (long) i9, (long) i10);
                    this.h.addView(this.h.E);
                    this.h.E.requestFocus();
                    this.h.g.d(this.h.n);
                }
            }
        });
    }

    protected void startActivityIndicator() {
        b(this.G);
    }

    protected void stopActivityIndicator() {
        b(this.H);
    }

    protected void triggerResizeCall() {
        nativeResize(this.v, this.w, this.v, this.w);
    }

    public void windowFocusChanged(final boolean z) {
        this.i.a(z);
        if (z && this.b != null) {
            reportSoftInputStr(null, 1, false);
        }
        c(new Runnable(this) {
            final /* synthetic */ UnityPlayer b;

            public final void run() {
                this.b.nativeFocusChanged(z);
            }
        });
        this.a.a(z);
        g();
    }
}
