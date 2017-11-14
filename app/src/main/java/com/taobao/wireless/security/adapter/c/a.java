package com.taobao.wireless.security.adapter.c;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.a.e;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
                throw new SecException(exception.getMessage(), exception.getCause(), errorCode + SecExceptionCode.SEC_ERROR_DYN_STORE);
            }
        }
    }

    private static boolean a(Editor editor) {
        try {
            Method method = Editor.class.getMethod("apply", new Class[0]);
            if (method == null) {
                return false;
            }
            method.invoke(editor, new Object[0]);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalAccessException e2) {
            return false;
        } catch (IllegalArgumentException e3) {
            return false;
        } catch (InvocationTargetException e4) {
            return false;
        }
    }

    public final int a(String str, String str2, String str3) throws SecException {
        String a;
        String str4;
        Exception exception;
        if (com.taobao.wireless.security.adapter.a.a.a(str)) {
            throw new SecException("Parameter key is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        if (com.taobao.wireless.security.adapter.a.a.a(str2)) {
            throw new SecException("Parameter value is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        if (com.taobao.wireless.security.adapter.a.a.a(str3)) {
            throw new SecException("Parameter valueType is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        Editor edit;
        try {
            String dynamicKeyNative = this.b.getDynamicKeyNative(str, str3);
            try {
                if (!com.taobao.wireless.security.adapter.a.a.a(dynamicKeyNative)) {
                    a = e.a(this.a);
                    String b = e.b(this.a);
                    byte[] dynamicValueNative = this.b.getDynamicValueNative(str2, new String[]{a, b});
                    if (dynamicValueNative != null) {
                        a = new String(dynamicValueNative, "UTF-8");
                        str4 = dynamicKeyNative;
                        if (!com.taobao.wireless.security.adapter.a.a.a(a)) {
                            return 0;
                        }
                        String.format("key = %s value = %s", new Object[]{str4, a});
                        if (!com.taobao.wireless.security.adapter.a.a.a(str4)) {
                            if (!com.taobao.wireless.security.adapter.a.a.a(a)) {
                                edit = this.a.getSharedPreferences("DynamicData", 4).edit();
                                edit.putString(str4, a);
                                if (VERSION.SDK_INT < 9) {
                                    edit.commit();
                                } else if (!a(edit)) {
                                    edit.commit();
                                }
                                return 1;
                            }
                        }
                        return 0;
                    }
                }
                a = null;
                str4 = dynamicKeyNative;
            } catch (Exception e) {
                Exception exception2 = e;
                a = dynamicKeyNative;
                exception = exception2;
                a(exception);
                str4 = a;
                a = null;
                if (!com.taobao.wireless.security.adapter.a.a.a(a)) {
                    return 0;
                }
                String.format("key = %s value = %s", new Object[]{str4, a});
                if (com.taobao.wireless.security.adapter.a.a.a(str4)) {
                    if (com.taobao.wireless.security.adapter.a.a.a(a)) {
                        edit = this.a.getSharedPreferences("DynamicData", 4).edit();
                        edit.putString(str4, a);
                        if (VERSION.SDK_INT < 9) {
                            edit.commit();
                        } else if (a(edit)) {
                            edit.commit();
                        }
                        return 1;
                    }
                }
                return 0;
            }
        } catch (Exception e2) {
            exception = e2;
            a = null;
            a(exception);
            str4 = a;
            a = null;
            if (!com.taobao.wireless.security.adapter.a.a.a(a)) {
                return 0;
            }
            String.format("key = %s value = %s", new Object[]{str4, a});
            if (com.taobao.wireless.security.adapter.a.a.a(str4)) {
                if (com.taobao.wireless.security.adapter.a.a.a(a)) {
                    edit = this.a.getSharedPreferences("DynamicData", 4).edit();
                    edit.putString(str4, a);
                    if (VERSION.SDK_INT < 9) {
                        edit.commit();
                    } else if (a(edit)) {
                        edit.commit();
                    }
                    return 1;
                }
            }
            return 0;
        }
        if (!com.taobao.wireless.security.adapter.a.a.a(a)) {
            return 0;
        }
        String.format("key = %s value = %s", new Object[]{str4, a});
        if (com.taobao.wireless.security.adapter.a.a.a(str4)) {
            if (com.taobao.wireless.security.adapter.a.a.a(a)) {
                edit = this.a.getSharedPreferences("DynamicData", 4).edit();
                edit.putString(str4, a);
                if (VERSION.SDK_INT < 9) {
                    edit.commit();
                } else if (a(edit)) {
                    edit.commit();
                }
                return 1;
            }
        }
        return 0;
    }

    public final String a(String str, String str2) throws SecException {
        String str3 = null;
        if (com.taobao.wireless.security.adapter.a.a.a(str)) {
            throw new SecException("Parameter key is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        if (com.taobao.wireless.security.adapter.a.a.a(str2)) {
            throw new SecException("Parameter valueType is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        try {
            if (com.taobao.wireless.security.adapter.a.a.a(this.b.getDynamicKeyNative(str, str2))) {
                return null;
            }
            String string = com.taobao.wireless.security.adapter.a.a.a(this.b.getDynamicKeyNative(str, str2)) ? null : this.a.getSharedPreferences("DynamicData", 4).getString(this.b.getDynamicKeyNative(str, str2), null);
            if (com.taobao.wireless.security.adapter.a.a.a(string)) {
                return null;
            }
            String a = e.a(this.a);
            String b = e.b(this.a);
            byte[] orgValueNative = this.b.getOrgValueNative(string, new String[]{a, b});
            if (orgValueNative != null) {
                str3 = new String(orgValueNative, "UTF-8");
            }
            if (str3 != null && !"".equals(str3)) {
                return str3;
            }
            a = e.c(this.a);
            b = e.d(this.a);
            orgValueNative = this.b.getOrgValueNative(string, new String[]{a, b});
            if (orgValueNative != null) {
                str3 = new String(orgValueNative, "UTF-8");
            }
            if (str3 != null && !"".equals(str3)) {
                return str3;
            }
            Context context = this.a;
            Context context2 = this.a;
            byte[] orgValueNative2 = this.b.getOrgValueNative(string, new String[]{"111111111111111", "111111111111111"});
            return orgValueNative2 != null ? new String(orgValueNative2, "UTF-8") : str3;
        } catch (Exception e) {
            a(e);
            return null;
        }
    }

    public final void b(String str, String str2) throws SecException {
        if (com.taobao.wireless.security.adapter.a.a.a(str)) {
            throw new SecException("Parameter key is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        if (com.taobao.wireless.security.adapter.a.a.a(str2)) {
            throw new SecException("Parameter valueType is empty", (int) SecExceptionCode.SEC_ERROR_DYN_STORE_INVALID_PARAM);
        }
        String str3 = null;
        try {
            str3 = this.b.getDynamicKeyNative(str, str2);
        } catch (Exception e) {
            a(e);
        }
        String.format("key = %s", new Object[]{str3});
        if (!com.taobao.wireless.security.adapter.a.a.a(str3)) {
            Editor edit = this.a.getSharedPreferences("DynamicData", 4).edit();
            edit.remove(str3);
            if (VERSION.SDK_INT < 9) {
                edit.commit();
            } else if (!a(edit)) {
                edit.commit();
            }
        }
    }
}
