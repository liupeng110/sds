package com.sds.android.ttpod.a.a;

import android.app.Activity;
import com.tencent.mm.sdk.constants.Build;

/* WeChatFriendApi */
public class l extends k {
    public l(String str, Activity activity) {
        super(str, activity);
        this.a = true;
    }

    public boolean f() {
        return h().getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT;
    }
}
