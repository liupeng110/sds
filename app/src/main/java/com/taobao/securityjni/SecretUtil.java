package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.securityjni.tools.DataContext;
import com.taobao.wireless.security.adapter.l.b;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;
import com.taobao.wireless.security.sdk.indiekit.IIndieKitComponent;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import com.taobao.wireless.security.sdk.securesignature.ISecureSignatureComponent;
import com.taobao.wireless.security.sdk.securesignature.SecureSignatureDefine;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SecretUtil implements IIndieKitComponent, ISecureSignatureComponent {
    public static final String M_API = "API";
    public static final String M_DATA = "DATA";
    public static final String M_DEV = "DEV";
    public static final String M_ECODE = "ECODE";
    public static final String M_IMEI = "IMEI";
    public static final String M_IMSI = "IMSI";
    public static final String M_SSO = "SSO";
    public static final String M_TIME = "TIME";
    public static final String M_V = "V";
    private ISecureSignatureComponent a;
    private IIndieKitComponent b;

    public SecretUtil(ContextWrapper contextWrapper) {
        SecurityGuardManager instance = SecurityGuardManager.getInstance(contextWrapper);
        if (instance != null) {
            this.a = instance.getSecureSignatureComp();
            this.b = instance.getIndieKitComp();
        }
    }

    /* JADX err: Inconsistent code. */
    public java.lang.String getExternalSign(java.util.LinkedHashMap r9, com.taobao.securityjni.tools.DataContext r10) {
        /*
        r8 = this;
        r2 = 0;
        r1 = -1;
        r3 = 0;
        r0 = r8.a;
        if (r0 == 0) goto L_0x0011;
    L_0x0007:
        if (r9 == 0) goto L_0x0011;
    L_0x0009:
        r0 = r9.isEmpty();
        if (r0 != 0) goto L_0x0011;
    L_0x000f:
        if (r10 != 0) goto L_0x0012;
    L_0x0011:
        return r3;
    L_0x0012:
        r0 = r10.category;
        switch(r0) {
            case 0: goto L_0x0052;
            case 1: goto L_0x006a;
            case 2: goto L_0x0062;
            case 3: goto L_0x005a;
            case 4: goto L_0x0072;
            default: goto L_0x0017;
        };
    L_0x0017:
        r4 = r1;
    L_0x0018:
        if (r4 == r1) goto L_0x00e0;
    L_0x001a:
        r5 = new java.lang.StringBuilder;
        r0 = 768; // 0x300 float:1.076E-42 double:3.794E-321;
        r5.<init>(r0);
        r0 = r9.keySet();
        r6 = r0.iterator();
    L_0x0029:
        r0 = r6.hasNext();
        if (r0 == 0) goto L_0x007e;
    L_0x002f:
        r0 = r6.next();
        r0 = (java.lang.String) r0;
        if (r0 == 0) goto L_0x0029;
    L_0x0037:
        r1 = r9.get(r0);
        r1 = (java.lang.String) r1;
        if (r1 == 0) goto L_0x007a;
    L_0x003f:
        r0 = r5.append(r0);
        r7 = 61;
        r0 = r0.append(r7);
        r0.append(r1);
    L_0x004c:
        r0 = 38;
        r5.append(r0);
        goto L_0x0029;
    L_0x0052:
        r0 = r10.type;
        if (r0 != 0) goto L_0x0017;
    L_0x0056:
        r0 = 10;
        r4 = r0;
        goto L_0x0018;
    L_0x005a:
        r0 = r10.type;
        if (r0 != 0) goto L_0x0017;
    L_0x005e:
        r0 = 8;
        r4 = r0;
        goto L_0x0018;
    L_0x0062:
        r0 = r10.type;
        if (r0 != 0) goto L_0x0017;
    L_0x0066:
        r0 = 12;
        r4 = r0;
        goto L_0x0018;
    L_0x006a:
        r0 = r10.type;
        if (r0 != 0) goto L_0x0017;
    L_0x006e:
        r0 = 11;
        r4 = r0;
        goto L_0x0018;
    L_0x0072:
        r0 = r10.type;
        if (r0 != 0) goto L_0x0017;
    L_0x0076:
        r0 = 14;
        r4 = r0;
        goto L_0x0018;
    L_0x007a:
        r5.append(r0);
        goto L_0x004c;
    L_0x007e:
        r0 = r5.length();
        if (r0 <= 0) goto L_0x0011;
    L_0x0084:
        r0 = new java.util.HashMap;
        r0.<init>();
        r1 = "INPUT";
        r6 = r5.length();
        r6 = r6 + -1;
        r5 = r5.substring(r2, r6);
        r0.put(r1, r5);
        r1 = new com.taobao.wireless.security.sdk.SecurityGuardParamContext;
        r1.<init>();
        r1.paramMap = r0;
        r1.requestType = r4;
        r0 = r10.extData;
        if (r0 == 0) goto L_0x00bc;
    L_0x00a5:
        r0 = r10.extData;
        r0 = r0.length;
        if (r0 == 0) goto L_0x0011;
    L_0x00aa:
        r0 = new java.lang.String;
        r2 = r10.extData;
        r0.<init>(r2);
        r1.appKey = r0;
    L_0x00b3:
        r0 = r8.a;
        r0 = r0.signRequest(r1);
    L_0x00b9:
        r3 = r0;
        goto L_0x0011;
    L_0x00bc:
        r0 = r10.index;
        if (r0 >= 0) goto L_0x00dd;
    L_0x00c0:
        r0 = r2;
    L_0x00c1:
        r10.index = r0;
        r0 = new com.taobao.wireless.security.adapter.l.b;
        r0.<init>();
        r2 = r10.index;
        r4 = "";
        r0 = r0.a(r2, r4);
        if (r0 == 0) goto L_0x0011;
    L_0x00d2:
        r2 = "";
        r2 = r2.equals(r0);
        if (r2 != 0) goto L_0x0011;
    L_0x00da:
        r1.appKey = r0;
        goto L_0x00b3;
    L_0x00dd:
        r0 = r10.index;
        goto L_0x00c1;
    L_0x00e0:
        r0 = r3;
        goto L_0x00b9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.securityjni.SecretUtil.getExternalSign(java.util.LinkedHashMap, com.taobao.securityjni.tools.DataContext):java.lang.String");
    }

    public String getLaiwangSign(String str, String str2, DataContext dataContext) {
        if (this.a == null || dataContext == null) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put("INPUT", str);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_KEY, str2);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 7;
        if (dataContext.extData == null) {
            dataContext.index = dataContext.index < 0 ? 0 : dataContext.index;
            String a = new b().a(dataContext.index, "");
            if (a == null || "".equals(a)) {
                return null;
            }
            securityGuardParamContext.appKey = a;
        } else if (dataContext.extData.length == 0) {
            return null;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.a.signRequest(securityGuardParamContext);
    }

    public String getLoginTopToken(String str, String str2) {
        return getLoginTopToken(str, str2, new DataContext(0, null));
    }

    public String getLoginTopToken(String str, String str2, DataContext dataContext) {
        int i = 0;
        if (this.b == null || str == null || str2 == null || dataContext == null) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, str);
        hashMap.put(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, str2);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 0;
        if (dataContext.extData == null) {
            if (dataContext.index >= 0) {
                i = dataContext.index;
            }
            dataContext.index = i;
            String a = new b().a(dataContext.index, "");
            if (a == null || "".equals(a)) {
                return null;
            }
            securityGuardParamContext.appKey = a;
        } else if (dataContext.extData.length == 0) {
            return null;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.b.indieKitRequest(securityGuardParamContext);
    }

    public String getMtopSign(HashMap hashMap, DataContext dataContext) {
        if (this.a == null || hashMap == null || dataContext == null) {
            return null;
        }
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 3;
        if (dataContext.extData == null) {
            dataContext.index = dataContext.index < 0 ? 0 : dataContext.index;
            String a = new b().a(dataContext.index, "");
            if (a == null || "".equals(a)) {
                return null;
            }
            securityGuardParamContext.appKey = a;
        } else if (dataContext.extData.length == 0) {
            return null;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.a.signRequest(securityGuardParamContext);
    }

    public String getMtopV4RespSign(String str, DataContext dataContext) {
        if (this.a == null || dataContext == null) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put("INPUT", str);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 5;
        if (dataContext.extData == null) {
            dataContext.index = dataContext.index < 0 ? 0 : dataContext.index;
            String a = new b().a(dataContext.index, "");
            if (a == null || "".equals(a)) {
                return null;
            }
            securityGuardParamContext.appKey = a;
        } else if (dataContext.extData.length == 0) {
            return null;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.a.signRequest(securityGuardParamContext);
    }

    public String getMtopV4Sign(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, DataContext dataContext) {
        if (this.a == null || dataContext == null) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put("ECODE", str);
        hashMap.put("DATA", str2);
        hashMap.put("TIME", str3);
        hashMap.put("API", str4);
        hashMap.put("V", str5);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_SID, str6);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_TTID, str7);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_DEVICDEID, str8);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_LAT, str9);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_LNG, str10);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 4;
        if (dataContext.extData == null) {
            dataContext.index = dataContext.index < 0 ? 0 : dataContext.index;
            String a = new b().a(dataContext.index, "");
            if (a == null || "".equals(a)) {
                return null;
            }
            securityGuardParamContext.appKey = a;
        } else if (dataContext.extData.length == 0) {
            return null;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.a.signRequest(securityGuardParamContext);
    }

    public String getQianNiuSign(byte[] bArr, byte[] bArr2) {
        if (this.a == null || bArr == null || bArr2 == null) {
            return null;
        }
        Map hashMap = new HashMap();
        String str = new String(bArr);
        String str2 = new String(bArr2);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_STR1, str);
        hashMap.put(SecureSignatureDefine.SG_KEY_SIGN_STR2, str2);
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 9;
        return this.a.signRequest(securityGuardParamContext);
    }

    public String getSign(String str, String str2, String str3, String str4, String str5, String str6) {
        return getSign(str, str2, str3, str4, str5, null, str6);
    }

    public String getSign(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (str == null || str2 == null || str3 == null || str4 == null || str7 == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("API", str);
        hashMap.put("V", str2);
        hashMap.put("IMEI", str3);
        hashMap.put("IMSI", str4);
        if (str5 != null) {
            hashMap.put("DATA", str5);
        }
        if (str6 != null) {
            hashMap.put("ECODE", str6);
        }
        hashMap.put("TIME", str7);
        return getSign(hashMap, new DataContext(0, null));
    }

    public String getSign(HashMap hashMap, DataContext dataContext) {
        return getMtopSign(hashMap, dataContext);
    }

    public String getTopSign(TreeMap treeMap) {
        return getTopSign(treeMap, new DataContext(0, null));
    }

    public String getTopSign(TreeMap treeMap, DataContext dataContext) {
        if (this.a == null || treeMap == null || treeMap.isEmpty()) {
            return null;
        }
        String a;
        StringBuilder stringBuilder = new StringBuilder(512);
        for (String a2 : treeMap.keySet()) {
            String str = (String) treeMap.get(a2);
            if (str != null) {
                stringBuilder.append(a2).append(str);
            } else {
                stringBuilder.append(a2);
            }
        }
        Map hashMap = new HashMap();
        hashMap.put("INPUT", stringBuilder.toString());
        SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
        securityGuardParamContext.paramMap = hashMap;
        securityGuardParamContext.requestType = 2;
        if (dataContext.extData == null) {
            dataContext.index = dataContext.index < 0 ? 0 : dataContext.index;
            a2 = new b().a(dataContext.index, "");
            if (a2 == null || "".equals(a2)) {
                return null;
            }
            securityGuardParamContext.appKey = a2;
        } else if (dataContext.extData.length == 0) {
            return null;
        } else {
            securityGuardParamContext.appKey = new String(dataContext.extData);
        }
        return this.a.signRequest(securityGuardParamContext);
    }

    public String indieKitRequest(SecurityGuardParamContext securityGuardParamContext) {
        return this.b == null ? null : this.b.indieKitRequest(securityGuardParamContext);
    }

    public int reportSusText(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    public String signRequest(SecurityGuardParamContext securityGuardParamContext) {
        return this.a == null ? null : this.a.signRequest(securityGuardParamContext);
    }

    public int validateFileSignature(String str, String str2, String str3) {
        return -1;
    }
}
