package com.sds.android.cloudapi.ttpod.result;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult.OnlineThemeListExtra;

public class OnlineBackgroundListResult extends OnlineSkinListResult {
    public String getMainUrl() {
        OnlineThemeListExtra extra = getExtra();
        String picUrl = extra == null ? "" : extra.getPicUrl();
        return TextUtils.isEmpty(picUrl) ? "http://api.skin.ttpod.com/skin" : picUrl;
    }
}
