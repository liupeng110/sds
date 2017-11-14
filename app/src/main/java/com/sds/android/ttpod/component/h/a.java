package com.sds.android.ttpod.component.h;

import android.content.Context;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.a.b;
import com.sds.android.ttpod.component.d.f;
import java.io.File;

/* Register */
public final class a {
    private static final int[] a = new int[]{-1857714090, -47692346, -1232996205, 796674487};

    public static final String a(String str) {
        int i = 0;
        if (str == null || str.equals("")) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(str.trim());
        if (stringBuffer.equals(FeedbackItem.STATUS_WAITING)) {
            return "";
        }
        int i2;
        long longValue;
        long longValue2;
        for (i2 = 0; i2 < stringBuffer.length(); i2++) {
            char charAt = stringBuffer.charAt(i2);
            if (charAt < '0' || charAt > ';') {
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        if (stringBuffer.length() < 16) {
            while (stringBuffer.length() < 16) {
                stringBuffer.append(FeedbackItem.STATUS_WAITING);
            }
        }
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        if (i2 != 0) {
            try {
                Long valueOf = Long.valueOf(Long.parseLong(stringBuffer.substring(0, 8), 16));
                Long valueOf2 = Long.valueOf(Long.parseLong(stringBuffer.substring(8, 16), 16));
                longValue = valueOf.longValue();
                longValue2 = valueOf2.longValue();
            } catch (Exception e) {
                return "";
            }
        }
        longValue = Long.valueOf(stringBuffer.substring(0, 8), 16).longValue();
        longValue2 = Long.valueOf(stringBuffer.substring(8, 16), 16).longValue();
        iArr[0] = (int) longValue;
        iArr[1] = (int) longValue2;
        int[] a = a(iArr, a, 16);
        stringBuffer = new StringBuffer(String.format("%08x%08x", new Object[]{Integer.valueOf(a[0]), Integer.valueOf(a[1])}).toLowerCase());
        StringBuffer stringBuffer2 = new StringBuffer();
        while (i < stringBuffer.length()) {
            int intValue = Integer.valueOf(stringBuffer.substring(i, i + 1), 16).intValue();
            if (intValue >= 10) {
                stringBuffer2.append(intValue - 10);
            } else {
                stringBuffer2.append(intValue);
            }
            i++;
        }
        return stringBuffer2.toString();
    }

    public static int[] a(int[] iArr, int[] iArr2, int i) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr2[0];
        int i5 = iArr2[1];
        int i6 = iArr2[2];
        int i7 = iArr2[3];
        int i8 = i3;
        int i9 = i2;
        i2 = 0;
        for (i3 = 0; i3 < i; i3++) {
            i2 -= 1640531527;
            i9 += (((i8 << 4) + i4) ^ (i8 + i2)) ^ ((i8 >>> 5) + i5);
            i8 += (((i9 << 4) + i6) ^ (i9 + i2)) ^ ((i9 >>> 5) + i7);
        }
        return new int[]{i9, i8};
    }

    public static void a(Context context) {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.h() && com.sds.android.sdk.lib.util.EnvironmentUtils.a.j()) {
            final String str = com.sds.android.ttpod.framework.a.C() + File.separator + "user_data.xml";
            b a = b.a(str);
            final String a2 = a(m.a(c.a()) ? c.c() : c.a());
            if (!a.a("serialnumber", "").equals(a2)) {
                b bVar = new b(context, new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(0, "设备串号:", r3, "", 1, 17).a(), new com.sds.android.ttpod.component.d.a.b.a(1, "注册码:", "", "请输入注册码", 1, 17)}, R.string.verify, new com.sds.android.ttpod.common.a.a.a<b>() {
                    public void a(b bVar) {
                        com.sds.android.ttpod.component.d.a.b.a c = bVar.c(1);
                        if (c != null && c.e() != null) {
                            String charSequence = c.e().toString();
                            if (m.a(a2, charSequence)) {
                                bVar.e(true);
                                b a = b.a(str);
                                a.b("serialnumber", charSequence);
                                a.a();
                                return;
                            }
                            bVar.e(false);
                            f.a("注册码输入错误");
                        }
                    }
                }, new com.sds.android.ttpod.common.a.a.a<b>() {
                    public void a(b bVar) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.EXIT, new Object[0]));
                    }
                });
                bVar.setTitle((CharSequence) "天天动听内测验证");
                bVar.setCancelable(false);
                bVar.show();
            }
        }
    }
}
