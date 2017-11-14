package com.alibaba.wireless.security.open.dynamicdatastore;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.taobao.wireless.security.adapter.c.b;

public final class a implements IDynamicDataStoreComponent {
    private b a;

    public a(ContextWrapper contextWrapper) {
        this.a = new com.taobao.wireless.security.adapter.c.a(contextWrapper);
    }

    public final boolean getBoolean(String str) throws SecException {
        boolean z = false;
        String a = this.a.a(str, "Z");
        if (a != null) {
            try {
                z = "1".equals(a);
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public final byte[] getByteArray(String str) throws SecException {
        byte[] bArr = null;
        String a = this.a.a(str, "[B");
        if (a != null) {
            try {
                bArr = com.alibaba.wireless.security.open.a.b.a(a);
            } catch (Throwable th) {
            }
        }
        return bArr;
    }

    public final float getFloat(String str) throws SecException {
        float f = -1.0f;
        String a = this.a.a(str, "F");
        if (a != null) {
            try {
                f = Float.parseFloat(a);
            } catch (Throwable th) {
            }
        }
        return f;
    }

    public final int getInt(String str) throws SecException {
        int i = -1;
        String a = this.a.a(str, "I");
        if (a != null) {
            try {
                i = Integer.parseInt(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    public final long getLong(String str) throws SecException {
        long j = -1;
        String a = this.a.a(str, "J");
        if (a != null) {
            try {
                j = Long.parseLong(a);
            } catch (Throwable th) {
            }
        }
        return j;
    }

    public final String getString(String str) throws SecException {
        return this.a.a(str, "LString");
    }

    public final int putBoolean(String str, boolean z) throws SecException {
        return this.a.a(str, z ? "1" : FeedbackItem.STATUS_WAITING, "Z");
    }

    public final int putByteArray(String str, byte[] bArr) throws SecException {
        return this.a.a(str, com.alibaba.wireless.security.open.a.b.a(bArr), "[B");
    }

    public final int putFloat(String str, float f) throws SecException {
        return this.a.a(str, Float.toString(f), "F");
    }

    public final int putInt(String str, int i) throws SecException {
        return this.a.a(str, Integer.toString(i), "I");
    }

    public final int putLong(String str, long j) throws SecException {
        return this.a.a(str, Long.toString(j), "J");
    }

    public final int putString(String str, String str2) throws SecException {
        return this.a.a(str, str2, "LString");
    }

    public final void removeBoolean(String str) throws SecException {
        this.a.b(str, "Z");
    }

    public final void removeByteArray(String str) throws SecException {
        this.a.b(str, "[B");
    }

    public final void removeFloat(String str) throws SecException {
        this.a.b(str, "F");
    }

    public final void removeInt(String str) throws SecException {
        this.a.b(str, "I");
    }

    public final void removeLong(String str) throws SecException {
        this.a.b(str, "J");
    }

    public final void removeString(String str) throws SecException {
        this.a.b(str, "LString");
    }
}
