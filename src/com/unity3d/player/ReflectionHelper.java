package com.unity3d.player;

import com.google.unity.billingaidl.BuildConfig;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;
    private static a[] a = new a[4096];

    private static class a {
        public volatile Member a;
        private final Class b;
        private final String c;
        private final String d;
        private final int e = (((((this.b.hashCode() + 527) * 31) + this.c.hashCode()) * 31) + this.d.hashCode());

        a(Class cls, String str, String str2) {
            this.b = cls;
            this.c = str;
            this.d = str2;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.e == aVar.e && this.d.equals(aVar.d) && this.c.equals(aVar.c) && this.b.equals(aVar.b);
        }

        public final int hashCode() {
            return this.e;
        }
    }

    ReflectionHelper() {
    }

    private static float a(Class cls, Class cls2) {
        if (cls.equals(cls2)) {
            return 1.0f;
        }
        if (!(cls.isPrimitive() || cls2.isPrimitive())) {
            try {
                if (cls.asSubclass(cls2) != null) {
                    return 0.5f;
                }
            } catch (ClassCastException e) {
            }
            try {
                if (cls2.asSubclass(cls) != null) {
                    return 0.1f;
                }
            } catch (ClassCastException e2) {
            }
        }
        return 0.0f;
    }

    private static float a(Class cls, Class[] clsArr, Class[] clsArr2) {
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        if ((clsArr == null ? 0 : clsArr.length) + 1 != clsArr2.length) {
            return 0.0f;
        }
        float f = 1.0f;
        if (clsArr != null) {
            int i = 0;
            int i2 = 0;
            while (i < clsArr.length) {
                f *= a(clsArr[i], clsArr2[i2]);
                i++;
                i2++;
            }
        }
        return f * a(cls, clsArr2[clsArr2.length - 1]);
    }

    private static Class a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '(' && charAt != ')') {
                if (charAt == 'L') {
                    i = str.indexOf(59, iArr[0]);
                    if (i != -1) {
                        String substring = str.substring(iArr[0], i);
                        iArr[0] = i + 1;
                        try {
                            return Class.forName(substring.replace('/', '.'));
                        } catch (ClassNotFoundException e) {
                        }
                    }
                } else if (charAt == 'Z') {
                    return Boolean.TYPE;
                } else {
                    if (charAt == 'I') {
                        return Integer.TYPE;
                    }
                    if (charAt == 'F') {
                        return Float.TYPE;
                    }
                    if (charAt == 'V') {
                        return Void.TYPE;
                    }
                    if (charAt == 'B') {
                        return Byte.TYPE;
                    }
                    if (charAt == 'S') {
                        return Short.TYPE;
                    }
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'D') {
                        return Double.TYPE;
                    }
                    if (charAt == '[') {
                        return Array.newInstance(a(str, iArr), 0).getClass();
                    }
                    j.Log(5, "! parseType; " + charAt + " is not known!");
                }
                return null;
            }
        }
        return null;
    }

    private static void a(a aVar, Member member) {
        aVar.a = member;
        a[aVar.hashCode() & (a.length - 1)] = aVar;
    }

    private static boolean a(a aVar) {
        a aVar2 = a[aVar.hashCode() & (a.length - 1)];
        if (!aVar.equals(aVar2)) {
            return false;
        }
        aVar.a = aVar2.a;
        return true;
    }

    private static Class[] a(String str) {
        int[] iArr = new int[]{0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length()) {
            Class a = a(str, iArr);
            if (a == null) {
                break;
            }
            arrayList.add(a);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            clsArr[i] = (Class) it.next();
            i = i2;
        }
        return clsArr;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Member member = null;
        a aVar = new a(cls, BuildConfig.FLAVOR, str);
        if (a(aVar)) {
            Constructor constructor = (Constructor) aVar.a;
        } else {
            Member member2;
            Class[] a = a(str);
            float f = 0.0f;
            Constructor[] constructors = cls.getConstructors();
            int length = constructors.length;
            int i = 0;
            while (i < length) {
                member2 = constructors[i];
                float a2 = a(Void.TYPE, member2.getParameterTypes(), a);
                if (a2 > f) {
                    if (a2 == 1.0f) {
                        break;
                    }
                } else {
                    a2 = f;
                    member2 = member;
                }
                i++;
                f = a2;
                Object obj = r2;
            }
            member2 = member;
            a(aVar, member2);
            Member member3 = member2;
        }
        if (constructor != null) {
            return constructor;
        }
        throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field = null;
        a aVar = new a(cls, str, str2);
        if (a(aVar)) {
            field = (Field) aVar.a;
        } else {
            Class[] a = a(str2);
            float f = 0.0f;
            while (cls != null) {
                Field field2;
                Field[] declaredFields = cls.getDeclaredFields();
                int length = declaredFields.length;
                int i = 0;
                Field field3 = field;
                while (i < length) {
                    float a2;
                    field2 = declaredFields[i];
                    if (z == Modifier.isStatic(field2.getModifiers()) && field2.getName().compareTo(str) == 0) {
                        a2 = a(field2.getType(), null, a);
                        if (a2 > f) {
                            if (a2 == 1.0f) {
                                f = a2;
                                break;
                            }
                            i++;
                            f = a2;
                            field3 = field2;
                        }
                    }
                    a2 = f;
                    field2 = field3;
                    i++;
                    f = a2;
                    field3 = field2;
                }
                field2 = field3;
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    field = field2;
                    break;
                }
                cls = cls.getSuperclass();
                field = field2;
            }
            a(aVar, r0);
        }
        if (field != null) {
            return field;
        }
        String str3 = "no %s field with name='%s' signature='%s' in class L%s;";
        Object[] objArr = new Object[4];
        objArr[0] = z ? "non-static" : "static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchFieldError(String.format(str3, objArr));
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method = null;
        a aVar = new a(cls, str, str2);
        if (a(aVar)) {
            method = (Method) aVar.a;
        } else {
            Class[] a = a(str2);
            float f = 0.0f;
            while (cls != null) {
                Method method2;
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                int i = 0;
                Method method3 = method;
                while (i < length) {
                    float a2;
                    method2 = declaredMethods[i];
                    if (z == Modifier.isStatic(method2.getModifiers()) && method2.getName().compareTo(str) == 0) {
                        a2 = a(method2.getReturnType(), method2.getParameterTypes(), a);
                        if (a2 > f) {
                            if (a2 == 1.0f) {
                                f = a2;
                                break;
                            }
                            i++;
                            f = a2;
                            method3 = method2;
                        }
                    }
                    a2 = f;
                    method2 = method3;
                    i++;
                    f = a2;
                    method3 = method2;
                }
                method2 = method3;
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    method = method2;
                    break;
                }
                cls = cls.getSuperclass();
                method = method2;
            }
            a(aVar, r0);
        }
        if (method != null) {
            return method;
        }
        String str3 = "no %s method with name='%s' signature='%s' in class L%s;";
        Object[] objArr = new Object[4];
        objArr[0] = z ? "non-static" : "static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchMethodError(String.format(str3, objArr));
    }

    private static native void nativeProxyFinalize(int i);

    private static native Object nativeProxyInvoke(int i, String str, Object[] objArr);

    protected static Object newProxyInstance(int i, Class cls) {
        return newProxyInstance(i, new Class[]{cls});
    }

    protected static Object newProxyInstance(final int i, final Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new InvocationHandler() {
            protected final void finalize() {
                try {
                    ReflectionHelper.nativeProxyFinalize(i);
                } finally {
                    super.finalize();
                }
            }

            public final Object invoke(Object obj, Method method, Object[] objArr) {
                return ReflectionHelper.nativeProxyInvoke(i, method.getName(), objArr);
            }
        });
    }
}
