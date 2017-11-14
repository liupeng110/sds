package com.sds.android.cloudapi.ttpod.result;

import android.text.TextUtils;
import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

public class OnlineSkinListResult extends BaseResult implements Serializable {
    @c(a = "allPage")
    private int mAllPage;
    @c(a = "extra")
    private OnlineThemeListExtra mExtra;
    @c(a = "data")
    private ArrayList<OnlineSkinItem> mThemeItems;

    public static class OnlineThemeListExtra implements Serializable {
        @c(a = "picurl")
        private String mPicUrl;

        public String getPicUrl() {
            return this.mPicUrl;
        }
    }

    public int getAllPage() {
        return this.mAllPage;
    }

    public String getMainUrl() {
        String access$000 = this.mExtra == null ? "" : this.mExtra.mPicUrl;
        return TextUtils.isEmpty(access$000) ? "http://api.skin.ttpod.com/skin" : access$000;
    }

    public OnlineThemeListExtra getExtra() {
        return this.mExtra;
    }

    public ArrayList<OnlineSkinItem> getSkinItems() {
        return this.mThemeItems;
    }
}
