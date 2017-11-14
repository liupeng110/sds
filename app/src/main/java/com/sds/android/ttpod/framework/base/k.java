package com.sds.android.ttpod.framework.base;

import com.sds.android.cloudapi.ttpod.a.ad;
import com.sds.android.sdk.lib.request.b;
import java.io.Serializable;

/* ValidityResult */
public class k implements Serializable {
    private b a;
    private long b = System.currentTimeMillis();
    private long c;

    /* ValidityResult */
    private enum a {
        TYPE_DEFAULT(0),
        TYPE_SHORT(1800),
        TYPE_MEDIUM(3600),
        TYPE_LONG(86400);
        
        private long mExpiredTime;

        private a(int i) {
            this.mExpiredTime = ((long) i) * 1000;
        }

        public long getExpiredTime() {
            return this.mExpiredTime;
        }

        public static a from(String str) {
            a aVar = TYPE_DEFAULT;
            if (str.contains("http://api.dongting.com/channel/channel/mvs")) {
                return TYPE_SHORT;
            }
            if (str.contains("http://api.dongting.com/song/video/type")) {
                return TYPE_MEDIUM;
            }
            if (str.contains(ad.f)) {
                return TYPE_DEFAULT;
            }
            if (str.contains(ad.a + "/recomm_modules")) {
                return TYPE_DEFAULT;
            }
            if (str.contains("http://api.dongting.com/channel/channel/0/songs")) {
                return TYPE_DEFAULT;
            }
            if (str.contains(ad.b)) {
                return TYPE_LONG;
            }
            if (str.contains(ad.c)) {
                return TYPE_LONG;
            }
            if (str.contains("http://api.dongting.com/channel/channel/")) {
                return TYPE_LONG;
            }
            if (str.contains("http://fm.api.ttpod.com")) {
                return TYPE_LONG;
            }
            if (str.contains(ad.a() + "/recomm/song_list")) {
                return TYPE_DEFAULT;
            }
            if (str.contains(ad.a)) {
                return TYPE_SHORT;
            }
            if (str.contains("http://v1.ard.q.itlily.com/share/get_celebrities")) {
                return TYPE_DEFAULT;
            }
            if (str.contains("http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod")) {
                if (str.contains("size:1000")) {
                    return TYPE_LONG;
                }
                return TYPE_SHORT;
            } else if (str.contains("http://so.ard.iyyin.com/v2/songs/singersearch") || str.contains("http://so.ard.iyyin.com/albums")) {
                return TYPE_MEDIUM;
            } else {
                if (str.contains("http://v1.ard.q.itlily.com/share") && str.contains("user_timeline")) {
                    return TYPE_MEDIUM;
                }
                if (str.contains("http://ting.hotchanson.com/v2/songs/download") || str.contains("http://ting.hotchanson.com/songs/downwhite")) {
                    return TYPE_MEDIUM;
                }
                if (str.contains("http://so.ard.iyyin.com/meta/query_tab")) {
                    return TYPE_LONG;
                }
                return aVar;
            }
        }
    }

    public k(b bVar, String str) {
        this.a = bVar;
        this.c = a.from(str).getExpiredTime();
    }

    public b a() {
        return this.a;
    }

    public boolean b() {
        return System.currentTimeMillis() - this.b > this.c;
    }
}
