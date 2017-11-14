package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class FindSongHotListData extends RecommendData implements Serializable {
    @c(a = "author")
    private String mAuthor = "";
    @c(a = "listen_count")
    private long mListenCount;
    @c(a = "rec_alg")
    private int mRecommendAlgorithm;
    @c(a = "reason")
    private String mRecommendReason;
    @c(a = "rec_type")
    private int mRecommendType;

    public String getAuthor() {
        return this.mAuthor;
    }

    public long getListenCount() {
        return this.mListenCount;
    }

    public int getRecommentType() {
        return this.mRecommendType;
    }

    public int getRecommentAlgorithm() {
        return this.mRecommendAlgorithm;
    }

    public String getRecommendReason() {
        return this.mRecommendReason;
    }
}
