package com.taobao.wireless.security.adapter.f;

import com.taobao.wireless.security.adapter.JNICLibrary;
import java.nio.ByteBuffer;

public final class b implements a {
    private JNICLibrary a = JNICLibrary.getInstance();

    public final Long a(String str, String str2, String str3, byte[] bArr, String str4) {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0 || str3 == null || str2.length() == 0 || bArr == null || bArr.length == 0) {
            return null;
        }
        byte[] analyzeOpenIdNative = this.a.analyzeOpenIdNative(str, str2, str3, bArr, str4);
        if (analyzeOpenIdNative == null || analyzeOpenIdNative.length > 8 || analyzeOpenIdNative.length <= 0) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.put(analyzeOpenIdNative, 0, analyzeOpenIdNative.length);
        allocate.flip();
        return Long.valueOf(allocate.getLong());
    }
}
