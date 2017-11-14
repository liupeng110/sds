package com.tencent.connect.a;

import android.content.Context;
import com.tencent.connect.auth.QQToken;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatReportStrategy;
import com.tencent.stat.StatService;
import com.tencent.stat.common.StatConstants;
import com.tencent.utils.OpenConfig;

/* ProGuard */
public class a {
    public static boolean a(Context context, QQToken qQToken) {
        return OpenConfig.getInstance(context, qQToken.getAppId()).getBoolean("Common_ta_enable");
    }

    public static void b(Context context, QQToken qQToken) {
        if (a(context, qQToken)) {
            StatConfig.setEnableStatService(true);
        } else {
            StatConfig.setEnableStatService(false);
        }
    }

    public static void c(Context context, QQToken qQToken) {
        b(context, qQToken);
        String str = "Aqc" + qQToken.getAppId();
        StatConfig.setAutoExceptionCaught(false);
        StatConfig.setEnableSmartReporting(true);
        StatConfig.setSendPeriodMinutes(1440);
        StatConfig.setStatSendStrategy(StatReportStrategy.PERIOD);
        StatConfig.setStatReportUrl("http://cgi.connect.qq.com/qqconnectutil/sdk");
        StatService.startStatService(context, str, StatConstants.VERSION);
    }

    public static void d(Context context, QQToken qQToken) {
        b(context, qQToken);
        if (qQToken.getOpenId() != null) {
            StatService.reportQQ(context, qQToken.getOpenId());
        }
    }

    public static void a(Context context, QQToken qQToken, String str, String... strArr) {
        b(context, qQToken);
        StatService.trackCustomEvent(context, str, strArr);
    }
}
