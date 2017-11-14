package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class AudioEffectItemData implements Serializable {
    @c(a = "balance")
    private float mBalance = 0.0f;
    @c(a = "bass")
    private int mBass = 0;
    @c(a = "eq")
    private short[] mEqualizer = new short[]{(short) 0};
    @c(a = "islimit")
    private boolean mIslimit = false;
    @c(a = "reverb")
    private int mReverb = 0;
    @c(a = "treble")
    private int mTreble = 0;
    @c(a = "virtualizer")
    private int mVirtualizer = 0;

    public short[] getEqualizer() {
        return this.mEqualizer;
    }

    public int getBass() {
        return this.mBass;
    }

    public int getTreble() {
        return this.mTreble;
    }

    public int getVirtualizer() {
        return this.mVirtualizer;
    }

    public int getReverb() {
        return this.mReverb;
    }

    public float getBalance() {
        return this.mBalance;
    }

    public boolean getIsLimit() {
        return this.mIslimit;
    }

    public void setEqualizer(short[] sArr) {
        this.mEqualizer = sArr;
    }

    public void setBass(int i) {
        this.mBass = i;
    }

    public void setTreble(int i) {
        this.mTreble = i;
    }

    public void setVirtualizer(int i) {
        this.mVirtualizer = i;
    }

    public void setReverb(int i) {
        this.mReverb = i;
    }

    public void setBalance(float f) {
        this.mBalance = f;
    }

    public void setIsLimit(boolean z) {
        this.mIslimit = z;
    }
}
