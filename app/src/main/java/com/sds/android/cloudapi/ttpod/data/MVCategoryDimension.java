package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MVCategoryDimension implements Serializable {
    @c(a = "id")
    private long mId;
    @c(a = "name")
    private String mName;
    @c(a = "order")
    private String mOrder;
    @c(a = "parentId")
    private String mParentId;
    @c(a = "subType")
    private List<MVCategoryDimension> mSubType = new ArrayList();

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getParentId() {
        return this.mParentId;
    }

    public void setParentId(String str) {
        this.mParentId = str;
    }

    public String getOrder() {
        return this.mOrder;
    }

    public void setOrder(String str) {
        this.mOrder = str;
    }

    public List<MVCategoryDimension> getCategories() {
        return this.mSubType;
    }

    public void setCategories(List<MVCategoryDimension> list) {
        this.mSubType = list;
    }
}
