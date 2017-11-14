package com.alibaba.wireless.security.open.initialize;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.os.Build;
import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.IInitializeComponent.IInitFinishListener;
import com.taobao.wireless.security.adapter.a.d;
import com.taobao.wireless.security.adapter.dynamicupdatelib.DynamicUpdateLibAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class a implements IInitializeComponent {
    private static final Object a = new Object();
    private static final Object b = new Object();
    private static volatile boolean c = false;
    private static volatile int d = 0;
    private static AtomicBoolean e = new AtomicBoolean(false);
    private static volatile boolean f = false;
    private ArrayList g = new ArrayList();
    private String h = null;

    private static class a {
        File a;
        boolean b;

        private a() {
        }
    }

    private class b implements Runnable {
        private ContextWrapper a;
        private boolean b = true;
        private boolean c = true;
        private /* synthetic */ a d;

        public b(a aVar, ContextWrapper contextWrapper) {
            this.d = aVar;
            this.a = contextWrapper;
            this.b = true;
            this.c = true;
        }

        public final void run() {
            boolean z = this.b;
            if (this.c) {
                try {
                    a aVar = this.d;
                    if (a.a(this.a, true)) {
                        a.a(this.d);
                    } else {
                        this.d.c();
                    }
                } catch (SecException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int a(int[] iArr, int[] iArr2) {
        if (iArr == null && iArr2 != null) {
            return -1;
        }
        if (iArr != null && iArr2 == null) {
            return 1;
        }
        if (iArr == null && iArr2 == null) {
            return 0;
        }
        int length = iArr.length < iArr2.length ? iArr.length : iArr2.length;
        for (int i = 0; i < length; i++) {
            if (iArr[i] > iArr2[i]) {
                return 1;
            }
            if (iArr[i] < iArr2[i]) {
                return -1;
            }
        }
        return iArr.length == iArr2.length ? 0 : length == iArr.length ? -1 : 1;
    }

    private static a a(ContextWrapper contextWrapper) {
        File filesDir;
        Throwable th;
        Throwable th2;
        String str = null;
        a aVar = new a();
        aVar.b = false;
        try {
            filesDir = contextWrapper.getFilesDir();
            if (filesDir != null) {
                try {
                    if (!filesDir.exists()) {
                        filesDir.mkdir();
                    }
                    aVar.b = filesDir.exists();
                } catch (Throwable th3) {
                    th = th3;
                    th.printStackTrace();
                    aVar.a = filesDir;
                    return aVar;
                }
                aVar.a = filesDir;
                return aVar;
            }
            StringBuilder stringBuilder;
            if (contextWrapper != null) {
                stringBuilder = new StringBuilder("/data/data/");
                if (contextWrapper != null) {
                    str = contextWrapper.getPackageName();
                }
                str = stringBuilder.append(str).toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str).append("/files/");
            File file = new File(stringBuilder.toString());
            try {
                file.mkdir();
                aVar.b = file.exists();
                filesDir = file;
            } catch (Throwable th4) {
                th2 = th4;
                filesDir = file;
                th = th2;
                th.printStackTrace();
                aVar.a = filesDir;
                return aVar;
            }
            aVar.a = filesDir;
            return aVar;
        } catch (Throwable th42) {
            th2 = th42;
            filesDir = null;
            th = th2;
            th.printStackTrace();
            aVar.a = filesDir;
            return aVar;
        }
    }

    private synchronized void a(ContextWrapper contextWrapper, String str) {
        if (str != null) {
            File file = new File(str, "libsecuritysdk-2.3.39.so");
            if (e.get()) {
                new StringBuilder().append(str);
                new StringBuilder().append(d);
                new StringBuilder().append(file.exists());
            }
            if (file.exists() && b.a(file.getAbsolutePath())) {
                if (d == 0 || (d & 1) == 1) {
                    if (a(file)) {
                        f = true;
                    } else {
                        d |= 1;
                    }
                }
                if (!str.endsWith("/")) {
                    str = str.concat("/");
                }
                b.a = str;
            }
            if (e.get()) {
                new StringBuilder().append(d);
            }
        } else if (!(((d & 1) != 1 && d != 0) || (d & 536870912) == 536870912 || (d & 1073741824) == 1073741824)) {
            e.get();
            boolean b = b(contextWrapper);
            if (!b) {
                d |= 268435456;
                b = c(contextWrapper);
            }
            if (b) {
                d |= 536870912;
                f = true;
            } else {
                d |= 1073741824;
            }
        }
    }

    static /* synthetic */ void a(a aVar) {
        Iterator it = aVar.g.iterator();
        while (it.hasNext()) {
            ((IInitFinishListener) it.next()).onSuccess();
        }
    }

    private static void a(InputStream inputStream, File file) throws Throwable {
        Throwable th;
        byte[] bArr = new byte[4096];
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                int read = inputStream.read(bArr, 0, 4096);
                while (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                    read = inputStream.read(bArr, 0, 4096);
                }
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                }
            } catch (Throwable th3) {
                th = th3;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static boolean a() {
        return e.get();
    }

    private boolean a(Context context, String str) throws SecException {
        boolean z = true;
        if (!f) {
            synchronized (a) {
                if (!f) {
                    File file;
                    com.alibaba.wireless.security.open.a.a(context);
                    if (str == null || str.length() == 0) {
                        try {
                            file = new File(d.a().b());
                            if (!file.exists()) {
                                throw new SecException(SecExceptionCode.SEC_ERROR_INIT_SO_NOT_EXIST);
                            } else if (b.a(file.getAbsolutePath())) {
                                System.loadLibrary("securitysdk-2.3.39");
                                f = true;
                            } else {
                                throw new SecException(102);
                            }
                        } catch (Throwable th) {
                            d |= 1;
                            SecException secException = new SecException(SecExceptionCode.SEC_ERROR_INIT_UNKNOWN_ERROR);
                        }
                    }
                    if (!f) {
                        a(new ContextWrapper(context), str);
                    }
                    if (f) {
                        ContextWrapper contextWrapper = new ContextWrapper(context);
                        if (a(contextWrapper).b) {
                            String b = this.h == null ? d.a().b() : this.h;
                            file = new File(b);
                            File b2 = b();
                            int[] a = a(file.getName());
                            String miniVersion = DynamicUpdateLibAdapter.getMiniVersion();
                            int[] b3 = miniVersion != null ? b(miniVersion) : null;
                            if (b2 != null) {
                                int[] a2 = a(b2.getName());
                                if (a(a2, b3) >= 0 && a(a2, a) > 0) {
                                    if (!(d.a().a(contextWrapper, b2.getAbsolutePath()) == 1)) {
                                        b2.delete();
                                    }
                                }
                            }
                            if (d.a().a(contextWrapper, b) != 1) {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        f = z;
                    }
                }
            }
        }
        return f;
    }

    private static boolean a(Context context, boolean z) throws SecException {
        if (!c) {
            synchronized (b) {
                if (!c && f) {
                    int a = new com.taobao.wireless.security.adapter.e.a(context).a(z);
                    boolean z2 = a == 0;
                    c = z2;
                    if (!z2) {
                        throw new SecException(a);
                    } else if (z) {
                        com.alibaba.wireless.security.open.a.a(context).a().a();
                    }
                }
            }
        }
        return c;
    }

    private boolean a(File file) {
        try {
            System.load(file.getAbsolutePath());
            this.h = file.getAbsolutePath();
            f = true;
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static int[] a(String str) {
        if (str == null || str.length() == 0 || !str.startsWith("libsecuritysdk-")) {
            return null;
        }
        int indexOf = str.indexOf(45) + 1;
        int lastIndexOf = str.lastIndexOf(46);
        return (indexOf == -1 || indexOf >= str.length() || lastIndexOf == -1 || lastIndexOf >= str.length()) ? null : b(str.substring(indexOf, lastIndexOf));
    }

    private static File b() {
        File file = null;
        String downloadSoFolderDir = DynamicUpdateLibAdapter.getDownloadSoFolderDir();
        if (!(downloadSoFolderDir == null || downloadSoFolderDir.length() == 0)) {
            File file2 = new File(downloadSoFolderDir);
            ArrayList arrayList = new ArrayList();
            if (file2.exists()) {
                File[] listFiles = file2.listFiles();
                int length = listFiles.length;
                int i = 0;
                int[] iArr = null;
                while (i < length) {
                    int[] iArr2;
                    File file3 = listFiles[i];
                    if (file3.isFile()) {
                        int[] a = a(file3.getName());
                        if (a != null) {
                            if (iArr == null || (iArr != null && a(a, iArr) > 0)) {
                                if (file != null) {
                                    arrayList.add(file);
                                }
                                iArr2 = a;
                                i++;
                                iArr = iArr2;
                                file = file3;
                            } else {
                                arrayList.add(file3);
                            }
                        }
                    }
                    file3 = file;
                    iArr2 = iArr;
                    i++;
                    iArr = iArr2;
                    file = file3;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((File) it.next()).delete();
                }
            }
        }
        return file;
    }

    private boolean b(ContextWrapper contextWrapper) {
        Throwable th;
        InputStream inputStream = null;
        File file = new File(contextWrapper != null ? contextWrapper.getPackageCodePath() : null);
        if (!file.exists()) {
            return false;
        }
        ZipFile zipFile;
        try {
            a a = a(contextWrapper);
            if (a.b) {
                File file2 = new File(a.a, "libsecuritysdk-2.3.39.so");
                if (file2.exists() && b.a(file2.getAbsolutePath())) {
                    zipFile = null;
                } else {
                    if (file2.exists()) {
                        file2.delete();
                    }
                    zipFile = new ZipFile(file, 1);
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("lib/").append(Build.CPU_ABI).append('/').append("libsecuritysdk-2.3.39.so");
                        ZipEntry entry = zipFile.getEntry(stringBuilder.toString());
                        if ((entry == null || entry.isDirectory()) && Build.CPU_ABI.equals("armeabi-v7a")) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("lib/armeabi/libsecuritysdk-2.3.39.so");
                            entry = zipFile.getEntry(stringBuilder.toString());
                        }
                        if (entry == null || entry.isDirectory()) {
                            try {
                                zipFile.close();
                            } catch (Throwable th2) {
                            }
                            return false;
                        }
                        inputStream = zipFile.getInputStream(entry);
                        a(inputStream, file2);
                        if (!b.a(file2.getAbsolutePath())) {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th3) {
                                }
                            }
                            zipFile.close();
                            return false;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        try {
                            th.printStackTrace();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th5) {
                                    return false;
                                }
                            }
                            zipFile.close();
                            return false;
                        } catch (Throwable th6) {
                            th = th6;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th7) {
                                    throw th;
                                }
                            }
                            zipFile.close();
                            throw th;
                        }
                    }
                }
                boolean a2 = a(file2);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th8) {
                        return a2;
                    }
                }
                zipFile.close();
                return a2;
            }
            ZipFile zipFile2 = null;
            try {
                zipFile2.close();
            } catch (Throwable th9) {
            }
            return false;
        } catch (Throwable th10) {
            th = th10;
            zipFile = null;
            if (inputStream != null) {
                inputStream.close();
            }
            zipFile.close();
            throw th;
        }
    }

    private static int[] b(String str) {
        if (str != null) {
            String[] split = str.split("\\.");
            if (split != null && split.length > 1) {
                int[] iArr = new int[split.length];
                int i = 0;
                while (i < split.length) {
                    try {
                        iArr[i] = Integer.valueOf(split[i]).intValue();
                        i++;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
                return iArr;
            }
        }
        return null;
    }

    private void c() {
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            ((IInitFinishListener) it.next()).onError();
        }
    }

    private boolean c(ContextWrapper contextWrapper) {
        boolean z = false;
        AssetManager assets = contextWrapper.getAssets();
        a a = a(contextWrapper);
        if (a.b) {
            File file = a.a;
            StringBuilder stringBuilder = new StringBuilder();
            String str = Build.CPU_ABI;
            if (str.equals("armeabi-v7a") || str.equals("armeabi")) {
                stringBuilder.append("armeabi/libsecuritysdk-2.3.39.so");
                String stringBuilder2 = stringBuilder.toString();
                File file2 = new File(file, "libsecuritysdk-2.3.39.so");
                InputStream inputStream = null;
                try {
                    if (!(file2.exists() && b.a(file2.getAbsolutePath()))) {
                        if (file2.exists()) {
                            file2.delete();
                        }
                        inputStream = assets.open(stringBuilder2);
                        a(inputStream, file2);
                        if (!b.a(file2.getAbsolutePath())) {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th) {
                                }
                            }
                        }
                    }
                    z = a(file2);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                        }
                    }
                } catch (Throwable th3) {
                }
            }
        }
        return z;
    }

    public final synchronized int a(Context context, String str, boolean z) throws SecException {
        int i;
        if (context == null) {
            throw new SecException(101);
        }
        i = (a(context, str) && a(context, z)) ? 0 : 1;
        return i;
    }

    public final int initialize(Context context) throws SecException {
        if (context == null) {
            throw new SecException(101);
        }
        if (d.a == null && context != null) {
            d.a = context.getApplicationContext();
        }
        return loadLibrarySync(context);
    }

    public final boolean isSoValid(Context context) {
        return context == null ? false : b.a(new ContextWrapper(context));
    }

    public final void loadLibraryAsync(Context context) throws SecException {
        loadLibraryAsync(context, null);
    }

    public final synchronized void loadLibraryAsync(Context context, String str) throws SecException {
        if (context == null) {
            c();
        } else if (a(context, str)) {
            new Thread(new b(this, new ContextWrapper(context))).start();
        } else {
            c();
        }
    }

    public final int loadLibrarySync(Context context) throws SecException {
        if (context != null) {
            return loadLibrarySync(context, null);
        }
        throw new SecException(101);
    }

    public final int loadLibrarySync(Context context, String str) throws SecException {
        return a(context, str, true);
    }

    public final void registerInitFinishListener(IInitFinishListener iInitFinishListener) throws SecException {
        if (iInitFinishListener != null) {
            this.g.add(iInitFinishListener);
        }
    }

    public final void unregisterInitFinishListener(IInitFinishListener iInitFinishListener) throws SecException {
        if (iInitFinishListener != null) {
            this.g.remove(iInitFinishListener);
        }
    }
}
