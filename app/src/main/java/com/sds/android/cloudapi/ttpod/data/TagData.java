package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagData implements Serializable {
    private static final String KEY_CLASSIFIER = "classifier";
    private static final String KEY_ID = "id";
    private static final String KEY_PARENTID = "parentId";
    private static final String KEY_STANDARDID = "standardId";
    private static final String KEY_TAG = "tag";
    private static final String KEY_TYPE = "type";
    @c(a = "classifier")
    private int mClassifier;
    @c(a = "id")
    private long mId;
    @c(a = "parentId")
    private List<Long> mParentId = new ArrayList();
    @c(a = "standardId")
    private long mStandardId;
    @c(a = "tag")
    private String mTag = "";
    @c(a = "type")
    private int mType;

    public long getId() {
        return this.mId;
    }

    public String getTag() {
        return this.mTag;
    }

    public int getType() {
        return this.mType;
    }

    public int getClassifier() {
        return this.mClassifier;
    }

    public List<Long> getParentId() {
        if (this.mParentId == null) {
            this.mParentId = new ArrayList();
        }
        return this.mParentId;
    }

    public long getStandardId() {
        return this.mStandardId;
    }
}
