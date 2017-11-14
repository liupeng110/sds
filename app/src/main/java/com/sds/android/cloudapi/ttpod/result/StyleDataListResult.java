package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.ForwardAction;
import com.sds.android.sdk.lib.request.h;

public class StyleDataListResult<D> extends h<D> {
    @c(a = "action")
    private ForwardAction mForwardAction = new ForwardAction();
    @c(a = "_id")
    private long mId;
    @c(a = "name")
    private String mName = "";
    @c(a = "order")
    private int mOrder;
    @c(a = "style")
    private int mStyle = 2;

    public long getId() {
        return this.mId;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public int getStyle() {
        return this.mStyle;
    }

    public String getName() {
        return this.mName;
    }

    public ForwardAction getForwardAction() {
        return this.mForwardAction;
    }
}
