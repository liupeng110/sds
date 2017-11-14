package com.sds.android.ttpod.component.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.framework.a.b.n;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.b.z;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.lang.ref.WeakReference;
import java.util.List;

public class VideoPlayManager {
    private static boolean a = false;
    private static int b = 0;
    private static e c = null;
    private static WeakReference<Context> d;
    private static MediaPlayer e;
    private static a f;
    private static boolean g = true;
    private static Context h;
    private static MvData i;
    private static com.sds.android.ttpod.fragment.main.findsong.b j = new com.sds.android.ttpod.fragment.main.findsong.b() {
        public void a() {
            VideoPlayManager.b(VideoPlayManager.i);
        }
    };
    private static BroadcastReceiver k = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                VideoPlayManager.i();
                if (!m.a(schemeSpecificPart)) {
                    if ("com.storm.smart".equals(schemeSpecificPart)) {
                        n.c(true);
                    }
                    if (m.a(com.sds.android.ttpod.framework.storage.a.a.a().q(), "waiting_intall")) {
                        com.sds.android.ttpod.framework.storage.a.a.a().c(schemeSpecificPart);
                    }
                    if ("com.storm.smart".equals(schemeSpecificPart) || "com.pplive.androidphone".equals(schemeSpecificPart) || "tv.pps.mobile".equals(schemeSpecificPart)) {
                        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MV_DOWNLOAD_PLUGIN_SUCCESS.getValue(), 0, 0);
                        sUserEvent.setPageParameter(true);
                        sUserEvent.append(SocialConstants.PARAM_TYPE, schemeSpecificPart);
                        sUserEvent.post();
                    }
                }
            }
        }
    };
    private static OnErrorListener l = new OnErrorListener() {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            com.sds.android.ttpod.framework.storage.environment.b.x(2);
            if (VideoPlayManager.f != null) {
                VideoPlayManager.f.a(false);
            }
            if (VideoPlayManager.e != null) {
                VideoPlayManager.e.stop();
                VideoPlayManager.e.release();
            }
            return true;
        }
    };
    private static OnCompletionListener m = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            com.sds.android.ttpod.framework.storage.environment.b.x(1);
            if (VideoPlayManager.f != null) {
                VideoPlayManager.f.a(true);
            }
            if (VideoPlayManager.e != null) {
                try {
                    VideoPlayManager.e.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    VideoPlayManager.e.release();
                }
            }
        }
    };

    public static class VideoBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            g.d("VideoPlayManager", action);
            if (action.equals("com.storm.smart.action.TTPOD_VIDEO_FINISH") || action.equals("com.sds.android.ttpod.video_finished")) {
                VideoPlayManager.h();
            }
        }
    }

    private interface a {
        void a(boolean z);
    }

    private interface b {
        void a(Context context, MvData mvData);
    }

    public static class c implements b {
        public void a(Context context, MvData mvData) {
            if (mvData.getMvList() != null && !mvData.getMvList().isEmpty()) {
                f.a(VideoPlayManager.h, mvData, a(a(), mvData.getMvList()));
            }
        }

        private static MvListItem a(int i, List<MvListItem> list) {
            while (i >= 0) {
                for (MvListItem mvListItem : list) {
                    if (mvListItem.getType() == i) {
                        return mvListItem;
                    }
                }
                i--;
            }
            MvListItem mvListItem2 = a((List) list);
            if (mvListItem2 != null || list.isEmpty()) {
                return mvListItem2;
            }
            return (MvListItem) list.get(0);
        }

        private static MvListItem a(List<MvListItem> list) {
            return VideoPlayManager.a((List) list, 0);
        }

        private static int a() {
            return com.sds.android.ttpod.framework.storage.environment.b.N();
        }

        public static MvListItem a(MvData mvData) {
            return a(a(), mvData.getMvList());
        }
    }

    public static h a(final Context context, int i, String str, String str2, boolean z, final OnClickListener onClickListener) {
        n.a();
        if (c != null) {
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MV_SHOW_DOWNLOAD_PLUGIN.getValue(), 0, 0);
            sUserEvent.setPageParameter(true);
            sUserEvent.append(SocialConstants.PARAM_TYPE, c.b());
            sUserEvent.post();
        }
        if (c instanceof b) {
            z.a(context, "storm", "recommend_count");
            w.a(217, (int) StatisticHelper.DELAY_SEND, 1);
        } else {
            w.a(213, (int) StatisticHelper.DELAY_SEND, 1);
        }
        h hVar = new h(context, i, null, null);
        final OnClickListener onClickListener2 = onClickListener;
        final Context context2 = context;
        final boolean z2 = z;
        final String str3 = str;
        final String str4 = str2;
        hVar.a((int) R.string.download_experience, new com.sds.android.ttpod.common.a.a.a<h>() {
            public void a(h hVar) {
                hVar.dismiss();
                if (onClickListener != null) {
                    onClickListener.onClick(hVar, -1);
                }
                if (VideoPlayManager.c != null) {
                    com.sds.android.ttpod.framework.storage.a.a.a().c("waiting_intall");
                    VideoPlayManager.d = new WeakReference(context);
                    VideoPlayManager.b(context);
                    VideoPlayManager.c.a();
                    if (VideoPlayManager.c instanceof b) {
                        z.a(context, "storm", "install_count");
                        w.a(218, (int) StatisticHelper.DELAY_SEND, 1);
                        n.a(true);
                    } else {
                        w.a(214, (int) StatisticHelper.DELAY_SEND, 1);
                    }
                    SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MV_DOWNLOAD_PLUGIN.getValue(), 0, 0);
                    sUserEvent.setPageParameter(true);
                    sUserEvent.append(SocialConstants.PARAM_TYPE, VideoPlayManager.c.b());
                    sUserEvent.post();
                }
            }
        }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a<h>() {
            public void a(h hVar) {
                hVar.dismiss();
                if (onClickListener2 != null) {
                    onClickListener2.onClick(hVar, -2);
                }
                if (VideoPlayManager.c instanceof b) {
                    z.a(context2, "storm", "uninstall_count");
                    w.a(219, (int) StatisticHelper.DELAY_SEND, 1);
                }
                if (z2) {
                    n.a(false);
                    VideoPlayManager.c = new c();
                    VideoPlayManager.c.a(context2, str3, str4);
                    t.a(r.ACTION_MV_PLAY_SUCCESS, s.PAGE_NONE);
                    w.a(215, (int) StatisticHelper.DELAY_SEND, 1);
                }
            }
        });
        hVar.setTitle((int) R.string.prompt_title);
        hVar.dismiss();
        return hVar;
    }

    public static void a(Context context, MediaItem mediaItem) {
        if (context != null && mediaItem != null && !m.a(mediaItem.getExtra())) {
            h = context;
            OnlineMediaItem onlineMediaItem = (OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(mediaItem.getExtra(), OnlineMediaItem.class);
            i = a(onlineMediaItem);
            List mVUrls = onlineMediaItem.getMVUrls();
            if (mVUrls != null && mVUrls.size() > 0) {
                com.sds.android.ttpod.fragment.main.findsong.a.b(context, j);
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_MV.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
                sUserEvent.append(MediasColumns.SONG_ID, Long.valueOf(onlineMediaItem.getSongId()));
                sUserEvent.append("mv_id", Integer.valueOf(((MvListItem) mVUrls.get(0)).getId()));
                sUserEvent.post();
            }
        }
    }

    public static void a(Context context, MvData mvData) {
        if (context != null && mvData != null) {
            h = context;
            i = mvData;
            List mvList = mvData.getMvList();
            if (mvList != null && mvList.size() > 0) {
                com.sds.android.ttpod.fragment.main.findsong.a.b(context, j);
            }
        }
    }

    public static void a(Context context, Integer num) {
        a(context, new a(num));
    }

    private static MvData a(OnlineMediaItem onlineMediaItem) {
        MvData mvData = new MvData();
        List mVUrls = onlineMediaItem.getMVUrls();
        if (mVUrls.size() > 0) {
            MvListItem mvListItem = (MvListItem) mVUrls.get(0);
            mvData.setId(mvListItem.getId());
            mvData.setPicUrl(mvListItem.getPicUrl());
            mvData.setMvList(mVUrls);
        }
        mvData.setName(onlineMediaItem.getTitle());
        mvData.setSingerName(onlineMediaItem.getArtist());
        mvData.setSongId(onlineMediaItem.getSongId());
        mvData.setSingerId(onlineMediaItem.getArtistId());
        return mvData;
    }

    private static void b(MvData mvData) {
        new c().a(h, mvData);
    }

    private static void h() {
        if (a) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
        }
    }

    private static void b(Context context) {
        if (context != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            context.registerReceiver(k, intentFilter);
        }
    }

    private static void i() {
        try {
            if (d != null && d.get() != null) {
                ((Context) d.get()).unregisterReceiver(k);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MvListItem a(List<MvListItem> list, int i) {
        for (MvListItem mvListItem : list) {
            if (mvListItem.getType() == i) {
                return mvListItem;
            }
        }
        return null;
    }
}
