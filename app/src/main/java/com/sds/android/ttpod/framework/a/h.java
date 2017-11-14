package com.sds.android.ttpod.framework.a;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.storage.environment.b.a;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.a.g;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;

/* ImageSwitcherEngine */
public class h {
    private static h b = new h();
    private ArrayList<String> a = new ArrayList();
    private String c;
    private int d;
    private boolean e = true;
    private Handler f = new Handler(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 2) {
                if (!BaseApplication.e().j() && this.a.e && g.e().h() == PlayStatus.STATUS_PLAYING) {
                    this.a.g();
                }
                this.a.h();
            }
        }
    };
    private a g = new a(this) {
        final /* synthetic */ h a;

        {
            this.a = r1;
        }

        public void a(c cVar) {
            com.sds.android.sdk.lib.util.g.a("ImageSwitcherEngine", "onPreferencesChanged artistPic artistPicPlay=%b", Boolean.valueOf(b.aK()));
            if (b.aK()) {
                this.a.h();
            } else {
                this.a.f.removeCallbacksAndMessages(null);
            }
        }
    };

    public void a() {
        this.e = true;
    }

    public void b() {
        this.e = false;
    }

    private void g() {
        this.d++;
        int size = this.a.size();
        if (this.d >= size) {
            this.d = 0;
        }
        String str = this.d < size ? (String) this.a.get(this.d) : null;
        Intent intent = new Intent(Action.SWITCH_ARTIST_BITMAP);
        intent.putExtra(Members.MEDIA_ID, this.c);
        intent.putExtra("path", str);
        BaseApplication.e().sendBroadcast(intent);
    }

    public synchronized void c() {
        this.a.clear();
        this.f.removeCallbacksAndMessages(null);
    }

    public static h d() {
        return b;
    }

    public synchronized void a(String str, ArrayList<String> arrayList) {
        Object obj = null;
        synchronized (this) {
            MediaItem queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.e(), b.m(), b.n());
            if (!(queryMediaItem == null || queryMediaItem.isNull() || !m.a(str, queryMediaItem.getID()))) {
                if (!(this.a.isEmpty() && (arrayList == null || arrayList.isEmpty()))) {
                    if (m.a(this.c, str) && arrayList != null && !this.a.isEmpty() && arrayList.containsAll(this.a)) {
                        obj = 1;
                    }
                    this.c = str;
                    this.a.clear();
                    if (arrayList != null) {
                        this.a.addAll(arrayList);
                    }
                    boolean aK = b.aK();
                    if (obj == null) {
                        this.d = 0;
                        if (aK) {
                            h();
                        }
                    } else if (!this.f.hasMessages(2) && aK) {
                        h();
                    }
                }
            }
        }
    }

    private void h() {
        this.f.removeCallbacksAndMessages(null);
        if (this.a.size() > 1 && r.a()) {
            this.f.sendMessageDelayed(this.f.obtainMessage(2), 15000);
        }
    }

    public void e() {
        b.a(c.ARTIST_PIC_PLAY, this.g);
    }

    public void f() {
        b.b(c.ARTIST_PIC_PLAY, this.g);
    }
}
