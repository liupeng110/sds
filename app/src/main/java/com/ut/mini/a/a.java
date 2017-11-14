package com.ut.mini.a;

/* UTMCRC4 */
public class a {

    /* UTMCRC4 */
    private static class a {
        public int[] a;
        public int b;
        public int c;

        private a() {
            this.a = new int[256];
        }
    }

    public static byte[] a(byte[] bArr, String str) {
        if (!(bArr == null || str == null)) {
            a a = a(str);
            if (a != null) {
                return a(bArr, a);
            }
        }
        return null;
    }

    private static a a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        int i2;
        a aVar = new a();
        for (i2 = 0; i2 < 256; i2++) {
            aVar.a[i2] = i2;
        }
        aVar.b = 0;
        aVar.c = 0;
        i2 = 0;
        int i3 = 0;
        while (i < 256) {
            try {
                i2 = (i2 + (str.charAt(i3) + aVar.a[i])) % 256;
                int i4 = aVar.a[i];
                aVar.a[i] = aVar.a[i2];
                aVar.a[i2] = i4;
                i3 = (i3 + 1) % str.length();
                i++;
            } catch (Exception e) {
                return null;
            }
        }
        return aVar;
    }

    private static byte[] a(byte[] bArr, a aVar) {
        if (bArr == null || aVar == null) {
            return null;
        }
        int i = aVar.b;
        int i2 = aVar.c;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) % 256;
            i2 = (i2 + aVar.a[i]) % 256;
            int i4 = aVar.a[i];
            aVar.a[i] = aVar.a[i2];
            aVar.a[i2] = i4;
            i4 = (aVar.a[i] + aVar.a[i2]) % 256;
            bArr[i3] = (byte) (aVar.a[i4] ^ bArr[i3]);
        }
        aVar.b = i;
        aVar.c = i2;
        return bArr;
    }
}
