package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IntroductionData implements Serializable {
    @c(a = "actions")
    private Actions mActions = new Actions();
    @c(a = "desc")
    private String mDesc = "";
    @c(a = "detail")
    private String mDetail = "";
    @c(a = "_id")
    private long mId;
    @c(a = "name")
    private String mName = "";
    @c(a = "pic_url")
    private String mPicUrl = "";

    public static class Actions implements Serializable {
        @c(a = "value")
        private ArrayList<SongListTag> mTags = new ArrayList();
        @c(a = "type")
        private int mType;

        public int getType() {
            return this.mType;
        }

        public List<SongListTag> getSongListTags() {
            return this.mTags;
        }
    }

    public static class SongListTag implements Serializable {
        @c(a = "id")
        private String mId = "";
        @c(a = "tagName")
        private String mTagName = "";

        public String getId() {
            return this.mId;
        }

        public String getTagName() {
            return this.mTagName;
        }
    }

    public long getId() {
        return this.mId;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public String getName() {
        return this.mName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public Actions getActions() {
        return this.mActions;
    }
}
