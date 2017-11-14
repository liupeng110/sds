package com.sds.android.ttpod.a.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.tencent.connect.common.Constants;

/* AccessTokenUtil */
public class a {
    public static void a(Context context, String str, Bundle bundle) {
        try {
            String string = bundle.getString("access_token");
            String string2 = bundle.getString(Constants.PARAM_EXPIRES_IN);
            String string3 = bundle.getString("openid");
            Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putString("access_token", string);
            edit.putString(Constants.PARAM_EXPIRES_IN, string2);
            edit.putString("openid", string3);
            edit.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void b(Context context, String str, Bundle bundle) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            String string = sharedPreferences.getString("access_token", "");
            String string2 = sharedPreferences.getString(Constants.PARAM_EXPIRES_IN, FeedbackItem.STATUS_WAITING);
            String string3 = sharedPreferences.getString("openid", "");
            bundle.putString("access_token", string);
            bundle.putString(Constants.PARAM_EXPIRES_IN, string2);
            bundle.putString("openid", string3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, String str) {
        context.getSharedPreferences(str, 0).edit().clear().commit();
    }
}
