package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class Billboards implements Serializable {
    @c(a = "hit")
    private int mHit;
    @c(a = "hot")
    private int mHot;
    @c(a = "val")
    private String mWord;

    public String getWord() {
        return this.mWord;
    }

    public int getHit() {
        return this.mHit;
    }

    public int getHot() {
        return this.mHot;
    }
}
