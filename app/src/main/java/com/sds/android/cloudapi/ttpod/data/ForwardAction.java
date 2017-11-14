package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class ForwardAction implements Serializable {
    @c(a = "type")
    private int mType = 0;
    @c(a = "value")
    private String mValue = "";

    public String getValue() {
        return this.mValue;
    }

    public int getType() {
        return this.mType;
    }
}
