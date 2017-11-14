package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.dynamicdatastore.IDynamicDataStoreComponent;

public class DynamicDataStore implements IDynamicDataStoreComponent {
    private IDynamicDataStoreComponent a;

    public DynamicDataStore(ContextWrapper contextWrapper) {
        SecurityGuardManager instance = SecurityGuardManager.getInstance(contextWrapper);
        if (instance != null) {
            this.a = instance.getDynamicDataStoreComp();
        }
    }

    public boolean getBoolean(String str) {
        return this.a == null ? false : this.a.getBoolean(str);
    }

    public byte[] getByteArray(String str) {
        return this.a == null ? null : this.a.getByteArray(str);
    }

    public float getFloat(String str) {
        return this.a == null ? -1.0f : this.a.getFloat(str);
    }

    public int getInt(String str) {
        return this.a == null ? -1 : this.a.getInt(str);
    }

    public long getLong(String str) {
        return this.a == null ? -1 : this.a.getLong(str);
    }

    public long getLongCompat(String str) {
        return getLong(str);
    }

    public String getString(String str) {
        return this.a == null ? null : this.a.getString(str);
    }

    public String getStringCompat(String str) {
        return getString(str);
    }

    public int putBoolean(String str, boolean z) {
        return this.a == null ? -1 : this.a.putBoolean(str, z);
    }

    public int putByteArray(String str, byte[] bArr) {
        return this.a == null ? -1 : this.a.putByteArray(str, bArr);
    }

    public int putFloat(String str, float f) {
        return this.a == null ? -1 : this.a.putFloat(str, f);
    }

    public int putInt(String str, int i) {
        return this.a == null ? -1 : this.a.putInt(str, i);
    }

    public int putLong(String str, long j) {
        return this.a == null ? -1 : this.a.putLong(str, j);
    }

    public int putString(String str, String str2) {
        return this.a == null ? -1 : this.a.putString(str, str2);
    }

    public void removeBoolean(String str) {
        if (this.a != null) {
            this.a.removeBoolean(str);
        }
    }

    public void removeByteArray(String str) {
        if (this.a != null) {
            this.a.removeByteArray(str);
        }
    }

    public void removeFloat(String str) {
        if (this.a != null) {
            this.a.removeFloat(str);
        }
    }

    public void removeInt(String str) {
        if (this.a != null) {
            this.a.removeInt(str);
        }
    }

    public void removeLong(String str) {
        if (this.a != null) {
            this.a.removeLong(str);
        }
    }

    public void removeString(String str) {
        if (this.a != null) {
            this.a.removeString(str);
        }
    }
}
