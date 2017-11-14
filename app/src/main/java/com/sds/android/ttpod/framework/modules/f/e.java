package com.sds.android.ttpod.framework.modules.f;

/* NoticeType */
public enum e {
    COMMENT(1),
    REPOST(2);
    
    private int mValue;

    private e(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}
