package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class NewFollowers implements Serializable {
    private static final String KEY_USER = "user";
    @c(a = "user")
    private TTPodUser mUser;

    public TTPodUser getUser() {
        return this.mUser;
    }
}
