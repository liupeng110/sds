package com.sds.android.ttpod.framework.modules.f;

/* MusiccircleContentType */
public enum d {
    SINGLE_SONG(1),
    DJ(2),
    SONG_LIST(3);
    
    private int mValue;

    private d(int i) {
        this.mValue = i;
    }

    public int value() {
        return this.mValue;
    }
}
