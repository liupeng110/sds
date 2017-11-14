package com.taobao.wireless.security.adapter.d;

import android.content.Context;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.d.d.a;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.util.Map;

public final class c implements b {
    private JNICLibrary a = JNICLibrary.getInstance();
    private Context b;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.a().length];

        static {
            try {
                a[0] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[2] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public c(Context context) {
        this.b = context;
    }

    public final int a(String str, String str2) {
        e.a(str2);
        return e.a(str, this.b);
    }

    public final String a(SecurityGuardParamContext securityGuardParamContext) {
        String[] strArr;
        int i;
        Map map = securityGuardParamContext.paramMap;
        int i2 = a.a[securityGuardParamContext.requestType];
        String str = securityGuardParamContext.appKey;
        String str2;
        String str3;
        switch (AnonymousClass1.a[i2 - 1]) {
            case 1:
                if (map == null || map.size() == 2) {
                    str2 = (String) map.get(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME);
                    str3 = (String) map.get(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP);
                    if (com.taobao.wireless.security.adapter.a.a.a(str2, str3)) {
                        String.format("Input map value invalid : some key not exits or the relative value is empty", new Object[0]);
                        str2 = null;
                    } else {
                        str2 = new String[]{str2, str3};
                    }
                } else {
                    String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", new Object[]{Integer.valueOf(2), Integer.valueOf(map.size())});
                    str2 = null;
                }
                strArr = str2;
                i = 2;
                break;
            case 2:
                if (map == null || map.size() == 1) {
                    str2 = (String) map.get(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP);
                    if (str2 == null || "".equals(str2)) {
                        String.format("Input map value invalid : key \"%1s\" not exits or the relative value is empty", new Object[]{IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP});
                        str2 = null;
                    } else {
                        str2 = new String[]{str2};
                    }
                } else {
                    String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", new Object[]{Integer.valueOf(1), Integer.valueOf(map.size())});
                    str2 = null;
                }
                strArr = str2;
                i = 1;
                break;
            case 3:
                if (map == null || map.size() == 2) {
                    str2 = (String) map.get(IndieKitDefine.SG_KEY_INDIE_KIT_FILESIGNATURE);
                    str3 = (String) map.get(IndieKitDefine.SG_KEY_INDIE_KIT_FILEHASH);
                    if (com.taobao.wireless.security.adapter.a.a.a(str2, str3)) {
                        String.format("Input map value invalid : some key not exits or the relative value is empty", new Object[0]);
                        str2 = null;
                    } else {
                        str2 = new String[]{str2, str3};
                    }
                } else {
                    String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", new Object[]{Integer.valueOf(2), Integer.valueOf(map.size())});
                    str2 = null;
                }
                strArr = str2;
                i = 2;
                break;
            default:
                i = 0;
                strArr = null;
                break;
        }
        if (strArr == null) {
            return null;
        }
        return i != 0 ? this.a.indieKitRequestNative(strArr, i, str, i2 - 1) : null;
    }
}
