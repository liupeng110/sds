package com.taobao.wireless.security.sdk.dynamicdatastore;

import com.taobao.wireless.security.sdk.IComponent;

public interface IDynamicDataStoreComponent extends IComponent {
    boolean getBoolean(String str);

    byte[] getByteArray(String str);

    float getFloat(String str);

    int getInt(String str);

    long getLong(String str);

    String getString(String str);

    int putBoolean(String str, boolean z);

    int putByteArray(String str, byte[] bArr);

    int putFloat(String str, float f);

    int putInt(String str, int i);

    int putLong(String str, long j);

    int putString(String str, String str2);

    void removeBoolean(String str);

    void removeByteArray(String str);

    void removeFloat(String str);

    void removeInt(String str);

    void removeLong(String str);

    void removeString(String str);
}
