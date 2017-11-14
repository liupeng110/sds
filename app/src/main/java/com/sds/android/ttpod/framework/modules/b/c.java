package com.sds.android.ttpod.framework.modules.b;

import com.sds.android.cloudapi.ttpod.a.i;
import com.sds.android.cloudapi.ttpod.result.OnlineSongsResult;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.a.p;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.a.f;

/* QuickPlayRecommendSongListRunnable */
public class c implements Runnable {
    private long a;

    public c(long j) {
        this.a = j;
    }

    public void run() {
        OnlineSongsResult onlineSongsResult = (OnlineSongsResult) i.b(this.a, 1).g();
        if (onlineSongsResult.isSuccess() && !j.a(onlineSongsResult.getOnlineSongItems())) {
            t.a().a("channel", String.valueOf(this.a), true);
            o.a(p.a(onlineSongsResult.getOnlineSongItems()), "flag_recommend_song_list" + this.a, 0);
            b.a(f.REPEAT);
        }
    }
}
