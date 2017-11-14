package com.sds.android.ttpod.framework.a.b;

import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.a.b.d.r;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;

/* AliClickStats */
public class b {
    private UTCustomHitBuilder a = new UTCustomHitBuilder("click");

    public b() {
        this.a.setEventPage(l.a().b());
    }

    public static void a(String str, boolean z) {
        new b().b(str).a(Downloads.COLUMN_STATUS, String.valueOf(z ? 1 : 0)).a();
    }

    public static void a(String str, int i) {
        new b().b(str).a(Downloads.COLUMN_STATUS, String.valueOf(i)).a();
    }

    public static void a(String str) {
        new b().b(str).a();
    }

    public static void a(int i, MusicRank musicRank) {
        new b().a("location", String.valueOf(i)).c(musicRank.getTitle()).d("rank_" + musicRank.getTitle()).a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(musicRank.getId())).a("name", musicRank.getTitle()).a();
    }

    public b a(String str, String str2) {
        if (!m.a(str2)) {
            this.a.setProperty(str, str2);
        }
        return this;
    }

    public b b(String str) {
        if (!m.a(str)) {
            this.a.setProperty("ctrl_name", str);
        }
        return this;
    }

    public b c(String str) {
        if (!m.a(str)) {
            this.a.setProperty("module_id", str);
        }
        return this;
    }

    public b d(String str) {
        if (!m.a(str)) {
            this.a.setProperty("module_name", str);
        }
        return this;
    }

    public static void a(BaseActivity baseActivity, MediaItem mediaItem, String str) {
        t a = t.a();
        BaseFragment topFragment = baseActivity.getTopFragment();
        String b = a.b("songlist_type");
        b a2 = new b().c(a.b()).d(a.b()).b(str).a(MediasColumns.SONG_ID, String.valueOf(mediaItem.getSongID())).a(SocialConstants.PARAM_TYPE, b);
        a(a2, baseActivity);
        if (topFragment != null) {
            if (a.b().equals("online_search")) {
                a2.a("search_type", r.a()).a("keyword", topFragment.getAlibabaProperty("keyword"));
            }
            if ("album".equals(b)) {
                a2.a("singer_id", topFragment.getAlibabaProperty("singer_id"));
            }
            if ("post".equals(b) || "rank".equals(b) || "album".equals(b)) {
                a2.a("songlist_name", topFragment.getAlibabaProperty("name"));
            }
        }
        if ("menu_singer".equals(str)) {
            a2.a("singer_type", String.valueOf(mediaItem.getSingerSFlag()));
        }
        a2.a();
    }

    public static void a(int i, String str, String str2) {
        new b().a("adid", String.valueOf(i)).a("app_name", str).a("ctrl_name", str2).a();
    }

    private static void a(b bVar, BaseActivity baseActivity) {
        BaseFragment topFragment = baseActivity.getTopFragment();
        if (topFragment != null) {
            bVar.a("songlist_id", topFragment.getAlibabaProperty("songlist_id")).a("songlist_name", topFragment.getAlibabaProperty("songlist_name"));
        } else {
            bVar.a("songlist_id", baseActivity.getAlibabaProperty("songlist_id")).a("songlist_name", baseActivity.getAlibabaProperty("songlist_name"));
        }
    }

    public void a() {
        UTAnalytics.getInstance().getDefaultTracker().send(this.a.build());
    }
}
