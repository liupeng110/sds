package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult.OnlineThemeListExtra;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;
import java.util.ArrayList;

public class OnlinePagedSkinListResult extends BaseResult implements Serializable {
    @c(a = "data")
    private OnlinePagedSkinListData mData;
    @c(a = "extra")
    private OnlineThemeListExtra mExtra;

    public static class OnlinePagedSkinListData implements Serializable {
        @c(a = "id")
        private int mId;
        @c(a = "name")
        private String mName;
        @c(a = "orderNum")
        private int mOrderNum;
        @c(a = "recommendPicUrl")
        private String mRecommendPicUrl;
        @c(a = "skins")
        private ArrayList<OnlineSkinItem> mSkins;
        @c(a = "skins_count")
        private int mSkinsCount;
        @c(a = "status")
        private int mStatus;

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getSkinsCount() {
            return this.mSkinsCount;
        }

        public String getRecommendPicUrl() {
            return this.mRecommendPicUrl;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public int getOrderNum() {
            return this.mOrderNum;
        }

        public ArrayList<OnlineSkinItem> getSkins() {
            return this.mSkins;
        }
    }

    public String getMainUrl() {
        return this.mExtra.getPicUrl();
    }

    public OnlineThemeListExtra getExtra() {
        return this.mExtra;
    }

    public OnlinePagedSkinListData getData() {
        return this.mData;
    }
}
