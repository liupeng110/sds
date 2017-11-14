package com.sds.android.ttpod.component;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;

/* OnlineMediaUrlWrapper */
public class b {
    private a a;
    private Url b;

    /* OnlineMediaUrlWrapper */
    public enum a {
        MEDIA,
        VIDEO
    }

    public b(a aVar, Url url) {
        if (aVar == null || url == null) {
            throw new IllegalArgumentException("type and url should not be null");
        }
        this.a = aVar;
        this.b = url;
    }

    public a a() {
        return this.a;
    }

    public Url b() {
        return this.b;
    }
}
