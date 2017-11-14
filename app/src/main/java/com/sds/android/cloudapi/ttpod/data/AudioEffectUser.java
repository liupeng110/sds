package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class AudioEffectUser implements Serializable {
    @c(a = "allow_add")
    private boolean mAllowAdd = false;
    @c(a = "exp")
    private AudioEffectUserExp mExp = new AudioEffectUserExp();
    @c(a = "_id")
    private long mId = 0;
    @c(a = "nick_name")
    private String mNickName = "";
    @c(a = "pic")
    private String mPic = "";

    public long getId() {
        return this.mId;
    }

    public AudioEffectUserExp getExp() {
        return this.mExp;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public String getPic() {
        return this.mPic;
    }

    public boolean getAllowAdd() {
        return this.mAllowAdd;
    }
}
