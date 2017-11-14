package com.alibaba.wireless.security.open.dynamicdatastore;

import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;

public interface IDynamicDataStoreComponent extends IComponent {
    boolean getBoolean(String str) throws SecException;

    byte[] getByteArray(String str) throws SecException;

    float getFloat(String str) throws SecException;

    int getInt(String str) throws SecException;

    long getLong(String str) throws SecException;

    String getString(String str) throws SecException;

    int putBoolean(String str, boolean z) throws SecException;

    int putByteArray(String str, byte[] bArr) throws SecException;

    int putFloat(String str, float f) throws SecException;

    int putInt(String str, int i) throws SecException;

    int putLong(String str, long j) throws SecException;

    int putString(String str, String str2) throws SecException;

    void removeBoolean(String str) throws SecException;

    void removeByteArray(String str) throws SecException;

    void removeFloat(String str) throws SecException;

    void removeInt(String str) throws SecException;

    void removeLong(String str) throws SecException;

    void removeString(String str) throws SecException;
}
