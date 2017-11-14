package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class AudioEffectUserExp implements Serializable {
    @c(a = "add")
    private int mAdd = 0;
    @c(a = "bind")
    private int mBind = 0;
    @c(a = "bind_by")
    private int mBindBy = 0;
    @c(a = "pick")
    private int mPick = 0;
    @c(a = "pick_by")
    private int mPickBy = 0;
    @c(a = "total")
    private int mTotal = 0;

    public int getAdd() {
        return this.mAdd;
    }

    public int getBind() {
        return this.mBind;
    }

    public int getBindBy() {
        return this.mBindBy;
    }

    public int getPick() {
        return this.mPick;
    }

    public int getPickBy() {
        return this.mPickBy;
    }

    public int getTotal() {
        return this.mTotal;
    }
}
