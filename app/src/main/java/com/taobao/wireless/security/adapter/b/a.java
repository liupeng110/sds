package com.taobao.wireless.security.adapter.b;

import android.content.Context;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.a.e;

public final class a implements b {
    private Context a;
    private JNICLibrary b = JNICLibrary.getInstance();

    public a(Context context) {
        this.a = context;
    }

    private static void a(Exception exception) throws SecException {
        if (exception instanceof SecException) {
            int errorCode = ((SecException) exception).getErrorCode();
            if (errorCode > 0 && errorCode <= 99) {
                throw new SecException(exception.getMessage(), exception.getCause(), errorCode + 400);
            }
        }
    }

    public final String a(String str) throws SecException {
        if (str == null || str.length() <= 0) {
            return null;
        }
        String a = e.a(this.a);
        String b = e.b(this.a);
        try {
            byte[] dynamicValueNative = this.b.getDynamicValueNative(str, new String[]{a, b});
            return dynamicValueNative != null ? new String(dynamicValueNative, "UTF-8") : null;
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    public final String b(String str) throws SecException {
        if (str == null || str.length() <= 0) {
            return null;
        }
        String a = e.a(this.a);
        String b = e.b(this.a);
        try {
            byte[] orgValueNative = this.b.getOrgValueNative(str, new String[]{a, b});
            return orgValueNative != null ? new String(orgValueNative, "UTF-8") : null;
        } catch (Exception e) {
            a(e);
            return null;
        }
    }
}
