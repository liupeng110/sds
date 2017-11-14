package com.sds.android.ttpod.framework.support.search;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.h;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.search.a.c;
import com.sds.android.ttpod.framework.support.minilyric.MiniLyricManager;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Medias;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

/* SearchManager */
public final class a {
    public static final b a = b.SEARCH_ONLINE_FINISHED;
    public static final b b = b.SEARCH_ONLINE_FAILURE;
    public static final b c = b.SEARCH_LOCAL_FINISHED;
    private static a d = null;
    private String e = "";
    private b f;
    private a g = new a();
    private List<String> h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private String n;
    private boolean o;
    private boolean p;
    private b q;
    private b r;
    private Handler s;

    /* SearchManager */
    public interface b {
        void b();
    }

    /* SearchManager */
    private class a extends BroadcastReceiver {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (Action.PLAY_MEDIA_CHANGED.equals(intent.getAction())) {
                    g.a("SearchManager", "SearchManager LyricPicSearchMonitor PLAY_MEDIA_CHANGED lookLyricPic");
                    a.a().a(null);
                } else if (Action.LYRIC_PIC_OPERATE_RESULT.equals(intent.getAction())) {
                    String stringExtra = intent.getStringExtra(SocialConstants.PARAM_TYPE);
                    b bVar = (b) intent.getSerializableExtra("state");
                    String stringExtra2 = intent.getStringExtra(Members.MEDIA_ID);
                    if (bVar == b.SEARCH_LOCAL_FINISHED || bVar == b.SEARCH_DOWNLOAD_FINISHED) {
                        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("download_result_list");
                        if (stringArrayListExtra != null && !stringArrayListExtra.isEmpty()) {
                            MediaItem g;
                            if ("picture_type".equals(stringExtra)) {
                                this.a.e = intent.getStringExtra(Members.MEDIA_ID) + ((String) stringArrayListExtra.get(0));
                                g = com.sds.android.ttpod.framework.support.a.g.e().g();
                                if (g != null && m.a(g.getID(), stringExtra2)) {
                                    com.sds.android.ttpod.framework.storage.environment.b.a((String) stringArrayListExtra.get(0), g);
                                }
                                this.a.f.b();
                            } else if ("lyric_type".equals(stringExtra)) {
                                String str = (String) stringArrayListExtra.get(0);
                                g = com.sds.android.ttpod.framework.support.a.g.e().g();
                                if (g != null && m.a(g.getID(), stringExtra2)) {
                                    com.sds.android.ttpod.framework.storage.environment.b.b(str, g);
                                    MiniLyricManager.a().f();
                                }
                            }
                        }
                    }
                }
            }
        }

        public IntentFilter a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.LYRIC_PIC_OPERATE_RESULT);
            intentFilter.addAction(Action.PLAY_MEDIA_CHANGED);
            return intentFilter;
        }
    }

    private void d() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.s = new Handler(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 1:
                            this.a.f();
                            return;
                        default:
                            return;
                    }
                }
            };
        }
    }

    public static a a() {
        synchronized (a.class) {
            if (d == null) {
                d = new a();
                com.sds.android.sdk.core.download.a.a().a("lyrics_picture_file_download", 3);
                h.d().e();
                d.d();
            }
        }
        return d;
    }

    public void b() {
        h.d().f();
        com.sds.android.sdk.core.download.a.a().b("lyrics_picture_file_download");
        BaseApplication.e().unregisterReceiver(this.g);
        this.g = null;
        d = null;
    }

    private a() {
        BaseApplication.e().registerReceiver(this.g, this.g.a());
    }

    public void a(b bVar) {
        this.f = bVar;
    }

    public String c() {
        return this.e;
    }

    public boolean a(Intent intent) {
        boolean z = false;
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        String stringExtra2 = intent.getStringExtra(SocialConstants.PARAM_TYPE);
        String stringExtra3;
        String stringExtra4;
        MediaItem mediaItem;
        if ("search_lyric_pic_command".equals(stringExtra)) {
            stringExtra3 = intent.getStringExtra("artist_parameter");
            stringExtra4 = intent.getStringExtra("title_parameter");
            mediaItem = (MediaItem) intent.getParcelableExtra(Medias.URI_PATH);
            boolean booleanExtra = intent.getBooleanExtra("only_local_search_parameter", false);
            String str = "SearchManager";
            String str2 = "handleIntent lookLyricPic command=%s type=%s artist=%s title=%s onlyLocal=%b mediaItemNotNull:%b";
            Object[] objArr = new Object[6];
            objArr[0] = stringExtra;
            objArr[1] = stringExtra2;
            objArr[2] = stringExtra3;
            objArr[3] = stringExtra4;
            objArr[4] = Boolean.valueOf(booleanExtra);
            objArr[5] = Boolean.valueOf(mediaItem != null);
            g.d(str, str2, objArr);
            if (stringExtra2 == null) {
                a(mediaItem);
                return true;
            } else if (booleanExtra && "picture_type".equals(stringExtra2)) {
                i();
                return true;
            } else if (mediaItem == null) {
                return true;
            } else {
                if ("lyric_type".equals(stringExtra2)) {
                    a(mediaItem, stringExtra3, stringExtra4);
                    com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.b(c.a(mediaItem, stringExtra4, stringExtra3)));
                    return true;
                } else if (!"picture_type".equals(stringExtra2)) {
                    return true;
                } else {
                    a(mediaItem, stringExtra3);
                    com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.c(c.b(mediaItem, stringExtra4, stringExtra3)));
                    return true;
                }
            }
        } else if ("download_lyric_pic_command".equals(stringExtra)) {
            Item item = (Item) intent.getParcelableExtra("item");
            MediaItem mediaItem2 = (MediaItem) intent.getParcelableExtra(Medias.URI_PATH);
            stringExtra3 = "SearchManager";
            stringExtra4 = "handleIntent lookLyricPic command=%s type=%s mediaItemNotNull:%b";
            Object[] objArr2 = new Object[3];
            objArr2[0] = stringExtra;
            objArr2[1] = stringExtra2;
            if (mediaItem2 != null) {
                z = true;
            }
            objArr2[2] = Boolean.valueOf(z);
            g.d(stringExtra3, stringExtra4, objArr2);
            if (mediaItem2 == null) {
                return true;
            }
            com.sds.android.ttpod.framework.support.search.task.a.a(stringExtra2, item, mediaItem2);
            return true;
        } else if ("batch_search_lyric_pic_command".equals(stringExtra)) {
            String stringExtra5 = intent.getStringExtra(SocialConstants.PARAM_TYPE);
            g.a("SearchManager", "handleIntent BATCH_SEARCH_LYRIC_PIC_COMMAND type=%s", stringExtra5);
            if ("search".equals(stringExtra5)) {
                e();
                return true;
            } else if ("stop".equals(stringExtra5)) {
                g();
                return true;
            } else if (!"query".equals(stringExtra5)) {
                return true;
            } else {
                h();
                return true;
            }
        } else if ("remove_lyric_pic_command".equals(stringExtra)) {
            mediaItem = (MediaItem) intent.getParcelableExtra(Medias.URI_PATH);
            if ("lyric_type".equals(stringExtra2)) {
                com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.b(c.c(mediaItem)));
                return true;
            } else if (!"picture_type".equals(stringExtra2)) {
                return true;
            } else {
                com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.c(c.d(mediaItem)));
                return true;
            }
        } else if ("resume_image_switcher".equals(stringExtra)) {
            h.d().a();
            return true;
        } else if (!"pause_image_switcher".equals(stringExtra)) {
            return false;
        } else {
            h.d().b();
            return true;
        }
    }

    private void e() {
        this.h = MediaStorage.queryMediaIDs(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, com.sds.android.ttpod.framework.storage.environment.b.l(MediaStorage.GROUP_ID_ALL_LOCAL));
        this.m = this.h.size();
        if (this.m == 0) {
            this.h = null;
            h();
            return;
        }
        this.i = -1;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        if (this.s != null) {
            this.s.sendEmptyMessage(1);
        }
    }

    private void f() {
        this.i++;
        MediaItem mediaItem = null;
        while (this.h != null && this.i < this.m) {
            mediaItem = MediaStorage.queryMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, (String) this.h.get(this.i));
            if (mediaItem != null) {
                break;
            }
            this.l++;
            this.i++;
        }
        if (mediaItem == null) {
            this.h = null;
            h();
            this.m = 0;
            this.j = 0;
            this.k = 0;
            this.l = 0;
            return;
        }
        this.o = false;
        this.p = false;
        this.n = mediaItem.getID();
        com.sds.android.ttpod.framework.support.search.a.b a = c.a(mediaItem);
        a.b(true);
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.b(a));
        com.sds.android.ttpod.framework.support.search.a.c b = c.b(mediaItem);
        b.b(true);
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.c(b));
    }

    private void g() {
        this.h = null;
        if (this.s != null) {
            this.s.removeMessages(1);
        }
        h();
    }

    private void h() {
        Intent intent = new Intent(Action.LYRIC_PIC_BATCH_OPERATE_RESULT);
        intent.putExtra("state", this.h != null);
        intent.putExtra("total_count", this.m);
        intent.putExtra("success_count", this.j);
        intent.putExtra("failed_count", this.k);
        intent.putExtra("skip_count", this.l);
        BaseApplication.e().sendBroadcast(intent);
    }

    private void i() {
        MediaItem queryMediaItem = MediaStorage.queryMediaItem(BaseApplication.e(), com.sds.android.ttpod.framework.storage.environment.b.m(), com.sds.android.ttpod.framework.storage.environment.b.n());
        if (queryMediaItem != null && !queryMediaItem.isNull()) {
            g.d("SearchManager", "searchLocalArtistPicture lookLyricPic create local pic search task, artist=%s title=%s", queryMediaItem.getArtist(), queryMediaItem.getTitle());
            com.sds.android.ttpod.framework.support.search.a.c b = c.b(queryMediaItem);
            b.c(true);
            com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.c(b));
        }
    }

    public void a(MediaItem mediaItem) {
        if (mediaItem == null) {
            mediaItem = MediaStorage.queryMediaItem(BaseApplication.e(), com.sds.android.ttpod.framework.storage.environment.b.m(), com.sds.android.ttpod.framework.storage.environment.b.n());
        }
        if (mediaItem != null && !mediaItem.isNull()) {
            h.d().c();
            g.d("SearchManager", "searchLyricPicture lookLyricPic create lyric pic search task, artist=%s title=%s", mediaItem.getArtist(), mediaItem.getTitle());
            a(mediaItem, null, null);
            com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.b(c.a(mediaItem)));
            a(mediaItem, null);
            com.sds.android.sdk.lib.e.a.a(new com.sds.android.ttpod.framework.support.search.task.c(c.b(mediaItem)));
        }
    }

    private static void a(MediaItem mediaItem, String str, String str2) {
        boolean z;
        String str3 = "SearchManager";
        String str4 = "logForSearchLyric lookLyricPic manual=%b artist=%s title=%s";
        Object[] objArr = new Object[3];
        if (str != null) {
            z = true;
        } else {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        if (str == null) {
            str = mediaItem.getArtist();
        }
        objArr[1] = str;
        if (str2 == null) {
            str2 = mediaItem.getTitle();
        }
        objArr[2] = str2;
        g.a(str3, str4, objArr);
    }

    private static void a(MediaItem mediaItem, String str) {
        boolean z;
        String str2 = "SearchManager";
        String str3 = "logForSearchPicture lookLyricPic manual=%b artist=%s title=%s";
        Object[] objArr = new Object[3];
        if (str != null) {
            z = true;
        } else {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        if (str == null) {
            str = mediaItem.getArtist();
        }
        objArr[1] = str;
        objArr[2] = mediaItem.getTitle();
        g.a(str2, str3, objArr);
    }

    public void a(String str, String str2, b bVar) {
        if (this.s != null && this.h != null && str != null && m.a(str, this.n)) {
            synchronized (d) {
                b(str, str2, bVar);
            }
        }
    }

    private void b(String str, String str2, b bVar) {
        if (this.h != null && str != null && m.a(str, this.n)) {
            if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.i() && MediaStorage.queryMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, str) != null) {
                g.a("SearchManager", "dealBatchItemSearchState type=%s status=%s mediaId=%s artist=%s title=%s", str2, bVar, str, MediaStorage.queryMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, str).getArtist(), MediaStorage.queryMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, str).getTitle());
            }
            if ("lyric_type".equals(str2)) {
                this.o = true;
                this.q = bVar;
            } else {
                this.p = true;
                this.r = bVar;
            }
            if (a(str)) {
                if (this.q == a || this.r == a) {
                    this.j++;
                } else if (this.q == b || this.r == b) {
                    this.k++;
                } else {
                    this.l++;
                }
                h();
                if (this.s != null) {
                    this.s.sendEmptyMessage(1);
                }
            }
        }
    }

    private boolean a(String str) {
        return this.p && this.o;
    }
}
