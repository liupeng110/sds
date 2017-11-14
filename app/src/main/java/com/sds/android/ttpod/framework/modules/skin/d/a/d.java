package com.sds.android.ttpod.framework.modules.skin.d.a;

import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* TskPackHandle */
public class d extends a {
    private String a = null;
    private String b;
    private long c;
    private HashMap<String, int[]> d = null;
    private InputStream e;
    private ArrayList f = new ArrayList(10);

    public d(String str) throws IOException {
        a(str);
    }

    public d(InputStream inputStream) throws IOException {
        a(inputStream, true);
    }

    public void a(String str) throws IOException {
        InputStream cVar;
        IOException e;
        File file = new File(str);
        long lastModified = file.lastModified();
        boolean z = (TextUtils.equals(this.b, str) && lastModified == this.c) ? false : true;
        try {
            cVar = new c(file, "r");
            try {
                a(cVar, z);
                this.b = str;
                this.c = lastModified;
            } catch (IOException e2) {
                e = e2;
                if (cVar != null) {
                    cVar.close();
                }
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            cVar = null;
            if (cVar != null) {
                cVar.close();
            }
            throw e;
        }
    }

    public boolean a() {
        return this.e != null;
    }

    public void close() throws IOException {
        if (this.e != null) {
            this.e.close();
            this.e = null;
        }
    }

    public byte[] b(String str) throws IOException {
        if (str == null) {
            throw new IOException("file name is null");
        }
        int[] iArr = (int[]) this.d.get(str.toLowerCase(Locale.US));
        if (iArr == null) {
            return null;
        }
        this.e.reset();
        this.e.skip((long) iArr[0]);
        byte[] bArr = new byte[iArr[1]];
        this.e.read(bArr);
        return bArr;
    }

    public Iterator<String> iterator() {
        return this.d.keySet().iterator();
    }

    public void a(InputStream inputStream, boolean z) throws IOException {
        if (inputStream == null || !inputStream.markSupported()) {
            throw new IOException("INPUT STREAM NOT SUPPORTED");
        }
        inputStream.mark(inputStream.available());
        if (z || this.d.size() <= 0) {
            byte[] bArr = new byte[16784];
            if (inputStream.read(bArr) > 4 && bArr[0] == (byte) 84 && bArr[1] == (byte) 80 && bArr[2] == (byte) 65 && bArr[3] == (byte) 75) {
                a(bArr, 4);
                a(bArr, 8);
                a(bArr, 12);
                int a = a(bArr, 16);
                this.a = com.sds.android.ttpod.framework.a.d.a(bArr, 24, a(bArr, 20));
                a += 20;
                int[] iArr = new int[(a(bArr, a) >> 2)];
                int i = a + 4;
                for (a = 0; a < iArr.length; a++) {
                    iArr[a] = a(bArr, i);
                    i += 4;
                }
                this.d = new HashMap();
                for (a = 0; a < iArr.length - 2; a += 2) {
                    int i2 = iArr[a + 2] - iArr[a];
                    Object obj = new int[]{iArr[a + 1], iArr[a + 3] - iArr[a + 1]};
                    int a2 = a(bArr, i);
                    if (a2 <= 256 && a2 >= 0) {
                        String a3 = com.sds.android.ttpod.framework.a.d.a(bArr, i + 4, a2);
                        i += i2;
                        if (a3 != null) {
                            this.d.put(a3.toLowerCase(Locale.US), obj);
                        }
                    }
                }
                this.e = inputStream;
                return;
            }
            throw new IOException("NOT TSK FORMATION");
        }
        this.e = inputStream;
    }

    private int a(byte[] bArr, int i) {
        return (((bArr[i] & MotionEventCompat.ACTION_MASK) | ((bArr[i + 1] & MotionEventCompat.ACTION_MASK) << 8)) | ((bArr[i + 2] & MotionEventCompat.ACTION_MASK) << 16)) | ((bArr[i + 3] & MotionEventCompat.ACTION_MASK) << 24);
    }
}
