package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.result.SingerBriefResult.SingerBriefData;
import java.io.Serializable;

public class SingerDetailResult extends SingerBaseResult implements Serializable {
    @c(a = "data")
    private SingerDetailData mData;

    public static class SingerDetailData extends SingerBriefData {
        @c(a = "albumsCount")
        private int mAlbumsCount = 0;
        @c(a = "awards")
        private String mAwards = "";
        @c(a = "earlyExperience")
        private String mEarlyExperience = "";
        @c(a = "entertainmentExperience")
        private String mEntertainmentExperience = "";
        @c(a = "evaluation")
        private String mEvaluation = "";
        @c(a = "personalLife")
        private String mPersonalLife = "";
        @c(a = "socialActivity")
        private String mSocialActivity = "";
        @c(a = "songsCount")
        private int mSongsCount = 0;
        @c(a = "videoCount")
        private int mVideoCount = 0;

        public int getVideoCount() {
            return this.mVideoCount;
        }

        public void setVideoCount(int i) {
            this.mVideoCount = i;
        }

        public String getEarlyExperience() {
            return this.mEarlyExperience;
        }

        public String getEntertainmentExperience() {
            return this.mEntertainmentExperience;
        }

        public String getAwards() {
            return this.mAwards;
        }

        public String getPersonalLife() {
            return this.mPersonalLife;
        }

        public String getEvaluation() {
            return this.mEvaluation;
        }

        public String getSocialActivity() {
            return this.mSocialActivity;
        }

        public int getAlbumsCount() {
            return this.mAlbumsCount;
        }

        public int getSongsCount() {
            return this.mSongsCount;
        }
    }

    public SingerDetailData getData() {
        if (this.mData == null) {
            this.mData = new SingerDetailData();
        }
        return this.mData;
    }
}
