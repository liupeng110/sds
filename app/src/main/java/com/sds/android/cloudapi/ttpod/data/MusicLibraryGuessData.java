package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class MusicLibraryGuessData implements Serializable {
    public static final int TYPE_CLASSIFACATION = 1;
    public static final int TYPE_RADIO = 2;
    public static final int TYPE_SINGER = 3;
    @c(a = "pic_url")
    private String mPicUrl;
    @c(a = "type")
    private int mType;
    @c(a = "type_id")
    private int mTypeId;
    @c(a = "type_name")
    private String mTypeName;

    public int getType() {
        return this.mType;
    }

    public int getTypeId() {
        return this.mTypeId;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }
}
