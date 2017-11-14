package com.a.a.a;

import android.content.Context;
import android.util.Log;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.open.SocialConstants;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.util.Hashtable;

/* InitCmmInterface */
public class h {
    public static Hashtable<String, String> a(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        long a = k.a(context);
        long b = k.b(context);
        if (b != 0 && currentTimeMillis - b >= HttpChannelSongListGetV2.CACHE_TIME) {
            k.b(context, 0);
            k.c(context, 0);
            a.b.put("initCount", Integer.valueOf(0));
        }
        if (a == 0 || currentTimeMillis - a >= 30000) {
            k.a(context, currentTimeMillis);
            try {
                Log.i("SDK_LW_CMM", "init 22");
                return g.b(context);
            } catch (Throwable th) {
                Log.i("SDK_LW_CMM", "init 11 exception");
                Hashtable<String, String> hashtable = new Hashtable();
                hashtable.put("code", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
                hashtable.put(SocialConstants.PARAM_APP_DESC, "未知错误");
                return hashtable;
            }
        }
        hashtable = new Hashtable();
        hashtable.put("code", "5");
        hashtable.put(SocialConstants.PARAM_APP_DESC, "初始化函数调用间隔不能小于30s");
        return hashtable;
    }

    public static boolean b(Context context) {
        try {
            Log.i("SDK_LW_CMM", "check 22");
            return g.a(context);
        } catch (Throwable th) {
            Log.i("SDK_LW_CMM", "check 11 exception");
            return false;
        }
    }
}
