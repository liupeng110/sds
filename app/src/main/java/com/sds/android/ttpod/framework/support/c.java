package com.sds.android.ttpod.framework.support;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.StatisticEvent;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.h.f;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Medias;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/* Support */
public class c {
    protected static f a = null;
    protected static boolean b = false;
    protected Context c;
    protected boolean d = false;
    protected b e;
    protected ConcurrentLinkedQueue<d> f = new ConcurrentLinkedQueue();
    protected a g = new a();
    protected ArrayList<Object> h = new ArrayList();
    protected List<DownloadTaskInfo> i = new ArrayList();
    protected ServiceConnection j = new ServiceConnection(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.a.e = com.sds.android.ttpod.framework.support.b.a.a(iBinder);
            for (DownloadTaskInfo a : this.a.i) {
                this.a.a(a);
            }
            this.a.i.clear();
            Iterator it = new ArrayList(this.a.f).iterator();
            while (it.hasNext()) {
                ((d) it.next()).a();
            }
            this.a.D();
            this.a.a(c.a, c.b);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.a.e = null;
            Iterator it = this.a.f.iterator();
            while (it.hasNext()) {
                ((d) it.next()).d();
            }
            this.a.c.unbindService(this.a.j);
            g.d("Support", "音效:onServiceDisconnected");
        }
    };

    /* Support */
    private class a extends BroadcastReceiver {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            b.a("Support", "onReceive action=" + action);
            if (Action.PLAY_MEDIA_CHANGED.equals(action)) {
                this.a.a(intent);
            } else if (Action.UPDATE_MEDIA_DURATION.equals(action)) {
                r1 = this.a.f.iterator();
                while (r1.hasNext()) {
                    ((d) r1.next()).a(intent.getStringExtra(Members.MEDIA_ID), intent.getIntExtra("media_duration", 0));
                }
            } else if (Action.PLAY_BUFFERING_STARTED.equals(action)) {
                r1 = this.a.f.iterator();
                while (r1.hasNext()) {
                    ((d) r1.next()).b();
                }
            } else if (Action.PLAY_BUFFERING_DONE.equals(action)) {
                r1 = this.a.f.iterator();
                while (r1.hasNext()) {
                    ((d) r1.next()).c();
                }
            } else if (Action.PLAY_CACHE_SAVED.equals(action)) {
                r2 = this.a.f.iterator();
                while (r2.hasNext()) {
                    ((d) r2.next()).a((com.sds.android.ttpod.framework.a.a) intent.getSerializableExtra("cached_media"));
                }
            } else if (Action.PLAY_STATUS_CHANGED.equals(action)) {
                this.a.b(intent);
            } else if (Action.EXIT.equals(action)) {
                this.a.f.clear();
                if (BaseApplication.e() != null) {
                    BaseApplication.e().d();
                }
            } else if (Action.MEDIA_PLAY_GROUP_CHANGE.equals(action)) {
                r2 = this.a.f.iterator();
                while (r2.hasNext()) {
                    ((d) r2.next()).a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY, (MediaItem) intent.getExtras().get("mediaItem"));
                }
            }
        }

        public IntentFilter a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.PLAY_MEDIA_CHANGED);
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            intentFilter.addAction(Action.PLAY_BUFFERING_STARTED);
            intentFilter.addAction(Action.PLAY_BUFFERING_DONE);
            intentFilter.addAction(Action.PLAY_CACHE_SAVED);
            intentFilter.addAction(Action.UPDATE_MEDIA_DURATION);
            intentFilter.addAction(Action.EXIT);
            intentFilter.addAction(Action.PREFERENCES_CHANGED);
            intentFilter.addAction(Action.MEDIA_PLAY_GROUP_CHANGE);
            return intentFilter;
        }
    }

    public c(Context context) {
        d.a((Object) context, "context");
        this.c = context;
    }

    public void a(f fVar, boolean z) {
        a = fVar;
        b = z;
        g.a("Support", "support bindProxy is mSupportService:" + this.e);
        if (this.e != null && fVar != null) {
            try {
                this.e.a(fVar.a(), fVar.c(), fVar.b(), fVar.d(), fVar.e(), z);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(d dVar) {
        try {
            c(dVar);
            this.f.add(dVar);
            if (y()) {
                dVar.a();
            } else {
                A();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        throw new IllegalStateException("must be Override");
    }

    public void b(String str, String str2) {
        throw new IllegalStateException("must be Override");
    }

    public void d() {
        try {
            this.f.clear();
            B();
            this.c.stopService(new Intent(this.c, SupportService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(d dVar) {
        try {
            c(dVar);
            this.f.remove(dVar);
            if (this.f.size() > 0) {
                B();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void A() {
        this.c.bindService(new Intent(this.c, SupportService.class), this.j, 1);
        this.c.registerReceiver(this.g, this.g.a());
    }

    private void B() {
        this.c.unbindService(this.j);
        this.c.unregisterReceiver(this.g);
        this.e = null;
    }

    public void e() {
        c(a("exit_command"));
    }

    public void a(String str, String str2, String str3) {
        c(a("play_command").putExtra("group", str).putExtra("play_context", str3).putExtra("media_source", str2));
    }

    public void a(String str, String str2) {
        d.a((Object) str2, "mediaID");
        c(a("sync_command").putExtra("group", str).putExtra("media_source", str2));
    }

    public void f() {
        try {
            this.e.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void g() {
        c(a("pause_command"));
    }

    public void h() {
        c(a("resume_command"));
    }

    public void b() {
        new SSystemEvent("SYS_PLAY", "previous").append(SocialConstants.PARAM_TYPE, "user").post();
        c(a("previous_command"));
    }

    public void c() {
        new SSystemEvent("SYS_PLAY", "next").append(SocialConstants.PARAM_TYPE, "user").post();
        c(a("next_command"));
    }

    public void i() {
        c(a("network_state_changed"));
    }

    public void j() {
        c(a("resume_image_switcher"));
    }

    public void k() {
        c(a("pause_image_switcher"));
    }

    public Integer l() {
        try {
            if (this.e != null) {
                return Integer.valueOf(this.e.c());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return C();
    }

    private Integer C() {
        String o = com.sds.android.ttpod.framework.storage.environment.b.o();
        String str = com.sds.android.ttpod.framework.storage.environment.b.n() + File.pathSeparator;
        if (m.a(o) || !o.startsWith(str)) {
            return Integer.valueOf(0);
        }
        try {
            return Integer.valueOf(o.substring(str.length()));
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
    }

    public void a(Integer num) {
        try {
            this.e.a(num.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float m() {
        try {
            if (this.e != null) {
                return this.e.d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0f;
    }

    public PlayStatus n() {
        try {
            return PlayStatus.values()[this.e.b()];
        } catch (Exception e) {
            e.printStackTrace();
            return PlayStatus.STATUS_STOPPED;
        }
    }

    public void o() {
        c(a("reload_all_audioeffect_command"));
    }

    public void a(int i) {
        c(a("reload_audioeffect_command").putExtra("effect_type", i));
    }

    public void p() {
        c(a("reset_audioeffect_command"));
    }

    public void q() {
        c(a("start_mini_lyric_command"));
    }

    public void r() {
        c(a("stop_mini_lyric_command"));
    }

    public void a(MediaItem mediaItem, String str, String str2) {
        a(mediaItem, str, str2, "picture_type");
    }

    public void b(MediaItem mediaItem, String str, String str2) {
        a(mediaItem, str, str2, "lyric_type");
    }

    private void a(MediaItem mediaItem, String str, String str2, String str3) {
        c(a("search_lyric_pic_command").putExtra(SocialConstants.PARAM_TYPE, str3).putExtra("artist_parameter", str).putExtra("title_parameter", str2).putExtra(Medias.URI_PATH, mediaItem));
    }

    public void a(Item item, MediaItem mediaItem) {
        c(a("download_lyric_pic_command").putExtra(SocialConstants.PARAM_TYPE, "lyric_type").putExtra(Medias.URI_PATH, mediaItem).putExtra("item", item));
    }

    public int s() {
        try {
            return this.e.e();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean a(int[] iArr, int i) {
        try {
            return this.e.a(iArr, i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a(StatisticEvent statisticEvent) {
        try {
            if (this.e == null) {
                this.h.add(statisticEvent);
            } else {
                this.e.a(statisticEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Map map) {
        try {
            if (this.e == null) {
                this.h.add(map);
            } else {
                this.e.a(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void D() {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof StatisticEvent) {
                a((StatisticEvent) next);
            } else if (next instanceof Map) {
                a((Map) next);
            }
        }
        this.h.clear();
        this.h.trimToSize();
    }

    public AudioEffectParam t() {
        try {
            return (AudioEffectParam) com.sds.android.sdk.lib.util.f.a(this.e.j(), AudioEffectParam.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void u() {
        try {
            this.e.g();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void v() {
        try {
            this.e.h();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Boolean bool) {
        try {
            this.e.a(bool.booleanValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(AudioEffectItem audioEffectItem, boolean z) {
        try {
            this.e.a(com.sds.android.sdk.lib.util.f.a((Object) audioEffectItem), z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MediaItem w() {
        try {
            return this.e.f();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(DownloadTaskInfo downloadTaskInfo) {
        try {
            if (y()) {
                this.e.a(downloadTaskInfo);
            } else {
                this.i.add(downloadTaskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DownloadTaskInfo b(DownloadTaskInfo downloadTaskInfo) {
        try {
            if (y()) {
                return this.e.b(downloadTaskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DownloadTaskInfo c(DownloadTaskInfo downloadTaskInfo) {
        try {
            if (y()) {
                return this.e.c(downloadTaskInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int d(DownloadTaskInfo downloadTaskInfo) {
        if (this.e != null) {
            try {
                return this.e.d(downloadTaskInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    protected void a(Intent intent) {
        MediaItem mediaItem = (MediaItem) intent.getExtras().get("mediaItem");
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(mediaItem);
        }
    }

    protected void b(Intent intent) {
        Iterator it;
        PlayStatus playStatus = PlayStatus.values()[intent.getIntExtra("play_status", PlayStatus.STATUS_STOPPED.ordinal())];
        if (playStatus == PlayStatus.STATUS_ERROR) {
            it = this.f.iterator();
            while (it.hasNext()) {
                ((d) it.next()).a(intent.getIntExtra("play_error_code", 0), intent.getIntExtra("play_error_resource_id", 0));
            }
        }
        it = this.f.iterator();
        while (it.hasNext()) {
            ((d) it.next()).a(playStatus);
        }
    }

    public long x() {
        try {
            return this.e.k();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void a(long j) {
        try {
            this.e.a(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean y() {
        return this.e != null;
    }

    public List<DownloadTaskInfo> a(int[] iArr) {
        try {
            return this.e.a(iArr);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public void b(long j) {
        try {
            this.e.b(j);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void z() {
        try {
            this.e.l();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    protected Intent a(String str) {
        return new Intent(this.c, SupportService.class).putExtra("command", str);
    }

    protected void c(Intent intent) {
        b.a("Support", "trySendIntent, caller is -->" + com.sds.android.ttpod.framework.a.a.a.a());
        if (this.c != null && !this.d) {
            this.d = "exit_command".equals(intent.getStringExtra("command"));
            this.c.startService(intent);
        }
    }

    private void c(d dVar) {
        if (dVar == null) {
            throw new NullPointerException("SupportCallback must not be null.");
        } else if (this.f.contains(dVar)) {
            throw new IllegalArgumentException(dVar.getClass().getName() + " callback is added already.");
        }
    }
}
