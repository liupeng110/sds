package com.sds.android.ttpod.framework.a.b;

import android.os.SystemClock;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTHitBuilders.UTCustomHitBuilder;
import com.ut.mini.UTHitBuilders.UTPageHitBuilder;
import com.ut.mini.UTPageHitHelper;
import com.ut.mini.UTTracker;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* AlibabaStats */
public class d {

    /* AlibabaStats */
    public static final class a {
        public static void a(String str, String str2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("deliver");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty(SocialConstants.PARAM_TYPE, str);
            uTCustomHitBuilder.setProperty(Downloads.COLUMN_STATUS, str2);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class b {
        public static void a(String str, boolean z) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("audio_effect");
            uTCustomHitBuilder.setProperty(str, String.valueOf(z ? 0 : 1));
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void a(String str, String str2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("audio_effect");
            uTCustomHitBuilder.setProperty(str, str2);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class c {
        public static void a(String str, String str2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("basic_scan");
            uTCustomHitBuilder.setProperty(str, str2);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void a(int i, int i2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("basic_scan");
            uTCustomHitBuilder.setProperty("my_songlist", String.valueOf(i));
            uTCustomHitBuilder.setProperty("my_favorite_songlist", String.valueOf(i2));
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void a(boolean z, int i, int i2, String str, String str2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("basic_scan");
            uTCustomHitBuilder.setProperty("unicom", String.valueOf(z ? 1 : 0));
            uTCustomHitBuilder.setProperty("local_song", String.valueOf(i));
            uTCustomHitBuilder.setProperty("my_favorite", String.valueOf(i2));
            uTCustomHitBuilder.setProperty("cpu", str);
            uTCustomHitBuilder.setProperty("memory", str2);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void b(String str, String str2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("basic_scan");
            uTCustomHitBuilder.setProperty("recommendation_age", str);
            uTCustomHitBuilder.setProperty("recommendation_school", str2);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void a(String str, String str2, String str3, String str4) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("basic_scan");
            uTCustomHitBuilder.setProperty("online_cache", str);
            uTCustomHitBuilder.setProperty("media_cache", str2);
            uTCustomHitBuilder.setProperty("pic_cache", str3);
            uTCustomHitBuilder.setProperty("lrc_cache", str4);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void a(String str) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("basic_scan");
            uTCustomHitBuilder.setProperty("scan_song_list", str);
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class d {
        public static void a(long j, boolean z) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder(MediasColumns.COMMENT);
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty("module_id", t.a().b());
            uTCustomHitBuilder.setProperty("post_id", String.valueOf(j));
            uTCustomHitBuilder.setProperty("emotion", z ? "1" : FeedbackItem.STATUS_WAITING);
            UTAnalytics.getInstance().getTracker(MediasColumns.COMMENT).send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class e {
        public static void a(String str, String str2) {
            UTAnalytics.getInstance().getTracker("download").setGlobalProperty(str, str2);
        }

        public static void a(String str) {
            UTAnalytics.getInstance().getTracker("download").removeGlobalProperty(str);
        }

        public static String b(String str) {
            return UTAnalytics.getInstance().getTracker("download").getGlobalProperty(str);
        }

        public static void a(HashMap<String, String> hashMap) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("download");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperties(hashMap);
            UTAnalytics.getInstance().getTracker("download").send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class f {
        public static void a(String str, boolean z, String str2, String str3, String str4) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("favorite");
            uTCustomHitBuilder.setProperty(Downloads.COLUMN_STATUS, z ? "1" : FeedbackItem.STATUS_WAITING);
            uTCustomHitBuilder.setProperty(MediasColumns.SONG_ID, str2);
            uTCustomHitBuilder.setProperty("code", str4);
            uTCustomHitBuilder.setProperty("favorite_type", str3);
            uTCustomHitBuilder.setProperties(k.b(str).b());
            UTAnalytics.getInstance().getTracker("favorite").send(uTCustomHitBuilder.build());
        }

        public static void a(String str, String str2, String str3, boolean z, String str4) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("favorite");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty(MediasColumns.SONG_ID, str);
            uTCustomHitBuilder.setProperty("name", str2);
            uTCustomHitBuilder.setProperty("code", str4);
            uTCustomHitBuilder.setProperty(Downloads.COLUMN_STATUS, z ? "1" : FeedbackItem.STATUS_WAITING);
            uTCustomHitBuilder.setProperty("module_id", t.a().b());
            uTCustomHitBuilder.setProperty("module_name", t.a().b());
            uTCustomHitBuilder.setProperty("songlist_id", t.a().b("songlist_id"));
            uTCustomHitBuilder.setProperty("songlist_type", t.a().b("songlist_type"));
            uTCustomHitBuilder.setProperty("online", t.a().b("online"));
            uTCustomHitBuilder.setProperty("singer_id", str3);
            uTCustomHitBuilder.setProperty("keyword", t.a().d());
            uTCustomHitBuilder.setProperty("search_type", t.a().c());
            uTCustomHitBuilder.setProperty("favorite_type", "song");
            UTAnalytics.getInstance().getTracker("favorite").send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class g {
        public static void a() {
            UTAnalytics.getInstance().getDefaultTracker().send(new UTCustomHitBuilder("global").build());
        }
    }

    /* AlibabaStats */
    public static final class h {
        public static void a(boolean z, String str, boolean z2, int i, long j) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("lrcpic");
            uTCustomHitBuilder.setProperty(SocialConstants.PARAM_TYPE, str);
            uTCustomHitBuilder.setProperty("search_lrcpic", z ? "lrc" : User.KEY_AVATAR);
            uTCustomHitBuilder.setProperty("search_result", z2 ? "success" : "fail");
            if (!z2) {
                uTCustomHitBuilder.setProperty("error_code", String.valueOf(i));
            }
            if (j > 0) {
                uTCustomHitBuilder.setProperty(MediasColumns.SONG_ID, String.valueOf(j));
            }
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }

        public static void a(boolean z, String str, boolean z2, int i, long j, int i2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("lrcpic");
            uTCustomHitBuilder.setProperty(SocialConstants.PARAM_TYPE, str);
            uTCustomHitBuilder.setProperty("download_lrcpic", z ? "lrc" : User.KEY_AVATAR);
            uTCustomHitBuilder.setProperty("download_result", z2 ? "success" : "fail");
            if (!z2) {
                uTCustomHitBuilder.setProperty("error_code", String.valueOf(i));
            }
            if (j > 0) {
                uTCustomHitBuilder.setProperty(MediasColumns.SONG_ID, String.valueOf(j));
            }
            uTCustomHitBuilder.setProperty(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(i2));
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class i {
        public static void a(String str, String str2) {
            UTAnalytics.getInstance().getTracker("mv").setGlobalProperty(str, str2);
        }

        public static String a(String str) {
            return UTAnalytics.getInstance().getTracker("mv").getGlobalProperty(str);
        }

        public static void b(String str) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("click");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty("ctrl_name", str);
            UTAnalytics.getInstance().getTracker("mv").send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class j {
        public static void a(String str, String str2) {
            UTAnalytics.getInstance().getTracker("mvplay").setGlobalProperty(str, str2);
        }

        public static String a(String str) {
            return UTAnalytics.getInstance().getTracker("mvplay").getGlobalProperty(str);
        }

        public static void a(HashMap<String, String> hashMap) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("play");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperties(hashMap);
            UTAnalytics.getInstance().getTracker("mvplay").send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class k {
        private String a;
        private HashMap<String, String> b;

        public String a() {
            return this.a;
        }

        public void a(String str) {
            this.a = str;
        }

        public HashMap<String, String> b() {
            return this.b;
        }

        public void a(HashMap<String, String> hashMap) {
            this.b = hashMap;
        }

        public static k a(String str, boolean z) {
            String b = z ? t.a().b("songlist_type") : NewUser.LOCAL_LOGIN;
            if (z) {
                str = t.a().b("songlist_id");
            }
            String str2 = z ? "1" : FeedbackItem.STATUS_WAITING;
            String b2 = t.a().b();
            String c = z ? t.a().c() : "";
            String d = z ? t.a().d() : "";
            HashMap hashMap = new HashMap();
            a(hashMap, "songlist_type", b);
            a(hashMap, "songlist_id", str);
            a(hashMap, "online", str2);
            a(hashMap, "module_id", b2);
            a(hashMap, "keyword", d);
            a(hashMap, "search_type", c);
            a(hashMap, "trigger_id", t.a().b("trigger_id"));
            a(hashMap, "scm", t.a().b("scm"));
            JSONObject jSONObject = new JSONObject(hashMap);
            k kVar = new k();
            kVar.a(jSONObject.toString());
            kVar.a(hashMap);
            return kVar;
        }

        public static k a(String str, MvData mvData) {
            String b = t.a().b();
            HashMap hashMap = new HashMap();
            a(hashMap, "mv_origin", e.b("mv_origin"));
            e.a("mv_origin");
            a(hashMap, "file_format", str);
            a(hashMap, "module_id", b);
            a(hashMap, "module_name", b);
            a(hashMap, "singer_id", String.valueOf(mvData.getSingerId()));
            a(hashMap, "singer_name", String.valueOf(mvData.getSingerName()));
            a(hashMap, MediasColumns.SONG_ID, String.valueOf(mvData.getSongId()));
            a(hashMap, "mv_id", String.valueOf(mvData.getId()));
            a(hashMap, "similar_type", String.valueOf(mvData.getRecommendType()));
            a(hashMap, "similar_songid", String.valueOf(mvData.getSongId()));
            a(hashMap, "keyword", t.a().d());
            a(hashMap, "search_type", t.a().c());
            JSONObject jSONObject = new JSONObject(hashMap);
            k kVar = new k();
            kVar.a(jSONObject.toString());
            kVar.a(hashMap);
            return kVar;
        }

        public static k a(boolean z, String str, String str2, String str3) {
            String b = t.a().b("songlist_type");
            String b2 = t.a().b("songlist_id");
            String str4 = "1";
            str4 = t.a().b();
            String c = t.a().c();
            String d = t.a().d();
            HashMap hashMap = new HashMap();
            a(hashMap, "songlist_type", b);
            a(hashMap, "songlist_id", b2);
            a(hashMap, "online", "1");
            a(hashMap, "module_id", str4);
            a(hashMap, "file_format", str);
            a(hashMap, "download_batch", z ? "1" : FeedbackItem.STATUS_WAITING);
            a(hashMap, "singer_id", str2);
            a(hashMap, "keyword", d);
            a(hashMap, "trigger_id", t.a().b("trigger_id"));
            if (str3 == null || "".equals(str3)) {
                a(hashMap, "scm", t.a().b("scm"));
            } else {
                a(hashMap, "scm", str3);
            }
            a(hashMap, "search_type", c);
            JSONObject jSONObject = new JSONObject(hashMap);
            k kVar = new k();
            kVar.a(jSONObject.toString());
            kVar.a(hashMap);
            return kVar;
        }

        private static void a(HashMap<String, String> hashMap, String str, String str2) {
            if (str2 != null && str2.length() > 0) {
                hashMap.put(str, str2);
            }
        }

        public static k a(String str, String str2, String str3) {
            HashMap hashMap = new HashMap();
            a(hashMap, "name", str);
            a(hashMap, "songlist_type", t.a().b("songlist_type"));
            a(hashMap, "songlist_id", t.a().b("songlist_id"));
            a(hashMap, "online", t.a().b("online"));
            a(hashMap, "module_id", t.a().b());
            a(hashMap, "module_name", t.a().b());
            a(hashMap, "keyword", t.a().d());
            a(hashMap, "search_type", t.a().c());
            a(hashMap, "singer_id", str2);
            a(hashMap, "trigger_id", t.a().b("trigger_id"));
            if (str3 == null || "".equals(str3)) {
                a(hashMap, "scm", t.a().b("scm"));
            } else {
                a(hashMap, "scm", str3);
            }
            JSONObject jSONObject = new JSONObject(hashMap);
            k kVar = new k();
            kVar.a(jSONObject.toString());
            kVar.a(hashMap);
            return kVar;
        }

        public static k b(String str) {
            k kVar = new k();
            if (str != null) {
                kVar.a(c(str));
            }
            return kVar;
        }

        private static HashMap<String, String> c(String str) {
            HashMap<String, String> hashMap = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    hashMap.put(str2, jSONObject.optString(str2));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return hashMap;
        }
    }

    /* AlibabaStats */
    public static final class l {
        private static l f = new l();
        private String a;
        private String b;
        private long c;
        private Map<String, String> d = new HashMap();
        private boolean e = false;

        private l() {
        }

        public static l a() {
            return f;
        }

        public synchronized void a(String str) {
            if (str != null) {
                if (this.a == null || !this.a.equals(str)) {
                    this.a = str;
                    this.c = SystemClock.elapsedRealtime();
                    this.e = false;
                }
            }
        }

        public synchronized void b(String str) {
            if (!(this.a == null || str == null)) {
                if (!this.e && this.a.equals(str)) {
                    String str2 = "-";
                    if (this.b == null || this.b.length() <= 0) {
                        str2 = UTPageHitHelper.getInstance().getCurrentPage();
                    } else {
                        str2 = this.b;
                    }
                    c(str2);
                }
            }
        }

        private void c(String str) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.c;
            UTPageHitBuilder uTPageHitBuilder = new UTPageHitBuilder(this.a);
            uTPageHitBuilder.setReferPage(str).setDurationOnPage(elapsedRealtime).setProperties(this.d);
            this.b = this.a;
            this.e = true;
            this.a = null;
            this.d = new HashMap();
            UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send(uTPageHitBuilder.build());
            }
        }

        public synchronized void a(Map<String, String> map) {
            if (map != null) {
                this.d.putAll(map);
            }
        }

        public synchronized void a(String str, String str2) {
            if (!(str == null || str2 == null)) {
                if (str2.length() > 0) {
                    this.d.put(str, str2);
                }
            }
        }

        public synchronized String b() {
            return this.a;
        }

        public synchronized String c() {
            return this.b;
        }
    }

    /* AlibabaStats */
    public static final class m {
        private String a;
        private HashMap<String, String> b;
        private boolean c;
        private boolean d = true;
        private boolean e;

        public m(boolean z) {
            this.e = z;
        }

        public void a(boolean z) {
            this.c = z;
        }

        private boolean e() {
            return this.c;
        }

        private boolean f() {
            return this.e;
        }

        public void a() {
            c();
        }

        public void b() {
            d();
        }

        public void c() {
            if ((f() || e()) && this.a != null && this.d) {
                this.d = false;
                l.a().a(this.a);
                h();
                g();
            }
        }

        private void g() {
            if (this.b != null) {
                l.a().a(this.b);
                t.a().a(this.b);
            }
        }

        private void h() {
            String b = t.a().b();
            if ((this.b == null || this.b.get("module_id") == null) && b != null) {
                a("module_id", b);
            }
            if ("online_search".equals(b("module_id"))) {
                a("search_type", t.a().c());
                a("keyword", t.a().d());
                return;
            }
            t.a().f();
        }

        public void d() {
            if ((f() || e()) && this.a != null && !this.d) {
                this.d = true;
                l.a().b(this.a);
                t.a().g();
            }
        }

        public void a(String str) {
            this.a = str;
        }

        public void a(String str, String str2) {
            if (f() || e()) {
                if (this.b == null) {
                    this.b = new HashMap();
                }
                this.b.put(str, str2);
                l.a().a(str, str2);
            }
        }

        public void a(String str, String str2, boolean z) {
            if (f() || e()) {
                String str3 = z ? "1" : FeedbackItem.STATUS_WAITING;
                a("songlist_id", str2);
                a("songlist_type", str);
                a("online", str3);
                t.a().a(str, str2, z);
            }
        }

        public void b(String str, String str2) {
            if (f() || e()) {
                a(str, str2);
                t.a().b(str, str2);
            }
        }

        public String b(String str) {
            if (this.b == null || str == null) {
                return "";
            }
            return (String) this.b.get(str);
        }

        public void c(String str) {
            if (str == null) {
                return;
            }
            if (f() || e()) {
                a("module_id", str);
                t.a().a(str);
            }
        }

        public void c(String str, String str2) {
            if (f() || e()) {
                a("search_type", str);
                a("keyword", str2);
                t.a().a(str, str2);
            }
        }
    }

    /* AlibabaStats */
    public static final class n {
        public static void a(long j) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("loading_time");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty("loading_time", String.valueOf(j));
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class o {
        public static void a(HashMap<String, String> hashMap) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("play");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperties(hashMap);
            UTAnalytics.getInstance().getTracker("play").send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class p {
        private static p b = new p();
        private HashMap<String, String> a = new HashMap();

        private p() {
        }

        public static p a() {
            return b;
        }

        public void a(HashMap<String, String> hashMap) {
            this.a.clear();
            this.a.putAll(hashMap);
        }

        public String a(String str) {
            return (String) this.a.get(str);
        }

        public String a(String str, boolean z) {
            k a = k.a(str, z);
            a(a.b());
            String a2 = a.a();
            com.sds.android.ttpod.framework.storage.environment.b.x(a2);
            return a2;
        }

        public void b(String str) {
            a(k.b(str).b());
        }
    }

    /* AlibabaStats */
    public static final class q {
        private static long a = 0;

        /* AlibabaStats */
        public enum a {
            FOREGROUND("foreground"),
            BACKGROUND("background");
            
            private String mState;

            private a(String str) {
                this.mState = str;
            }

            public String toString() {
                return this.mState;
            }
        }

        public static long a() {
            return a;
        }

        public static void a(long j) {
            a = j;
        }

        public static void a(a aVar, long j, long j2) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("time");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty(SocialConstants.PARAM_TYPE, aVar.toString());
            uTCustomHitBuilder.setProperty("start_time", String.valueOf(j));
            uTCustomHitBuilder.setProperty("end_time", String.valueOf(j2));
            UTAnalytics.getInstance().getDefaultTracker().send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class r {
        public static void a(String str) {
            if (str != null) {
                UTAnalytics.getInstance().getTracker("search").setGlobalProperty("search_type", str);
            }
        }

        public static String a() {
            return UTAnalytics.getInstance().getTracker("search").getGlobalProperty("search_type");
        }

        public static void b(String str) {
            a(str, null, null);
        }

        public static void a(String str, String str2, String str3) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("search");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty("keyword", str);
            if (str2 != null) {
                uTCustomHitBuilder.setProperty("word", str2);
            }
            if (str3 != null) {
                uTCustomHitBuilder.setProperty("keyword_type", str3);
            }
            UTAnalytics.getInstance().getTracker("search").send(uTCustomHitBuilder.build());
        }

        public static void a(String str, long j, String str2, int i) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("click");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty("keyword", str);
            uTCustomHitBuilder.setProperty(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(j));
            uTCustomHitBuilder.setProperty("name", str2);
            uTCustomHitBuilder.setProperty("location", String.valueOf(i));
            UTAnalytics.getInstance().getTracker("search").send(uTCustomHitBuilder.build());
        }

        public static void a(String str, boolean z) {
            UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("search_result");
            uTCustomHitBuilder.setEventPage(l.a().b());
            uTCustomHitBuilder.setProperty("keyword", str);
            uTCustomHitBuilder.setProperty("search_result", z ? "success" : "fail");
            UTAnalytics.getInstance().getTracker("search").send(uTCustomHitBuilder.build());
        }
    }

    /* AlibabaStats */
    public static final class s {
        public static void a(String str) {
            if (str != null) {
                UTAnalytics.getInstance().getTracker("share").setGlobalProperty(SocialConstants.PARAM_TYPE, str);
            }
        }

        public static void a(String str, String str2) {
            if (str2 == null || str2.length() <= 0) {
                b(str);
            } else {
                UTAnalytics.getInstance().getTracker("share").setGlobalProperty(str, str2);
            }
        }

        public static void b(String str) {
            UTAnalytics.getInstance().getTracker("share").removeGlobalProperty(str);
        }

        public static void a(String str, boolean z, com.sds.android.ttpod.common.b.a.a aVar) {
            if (!aVar.q()) {
                UTCustomHitBuilder uTCustomHitBuilder = new UTCustomHitBuilder("share");
                uTCustomHitBuilder.setEventPage(l.a().b());
                if (aVar.k() == com.sds.android.ttpod.common.b.a.a.a.POST) {
                    uTCustomHitBuilder.setProperty("songlist_name", aVar.g());
                } else {
                    uTCustomHitBuilder.setProperty(MediasColumns.SONG_ID, aVar.i().toString());
                    uTCustomHitBuilder.setProperty("song_name", aVar.g());
                }
                uTCustomHitBuilder.setProperty(Constants.PARAM_PLATFORM, str);
                uTCustomHitBuilder.setProperty("scm", aVar.a());
                uTCustomHitBuilder.setProperty("singer_id", aVar.j().toString());
                uTCustomHitBuilder.setProperty("module_id", t.a().b());
                uTCustomHitBuilder.setProperty("online", aVar.c() ? FeedbackItem.STATUS_WAITING : "1");
                uTCustomHitBuilder.setProperty("share_result", z ? "1" : FeedbackItem.STATUS_WAITING);
                if (!com.sds.android.sdk.lib.util.m.a(t.a().d())) {
                    uTCustomHitBuilder.setProperty("keyword", t.a().d());
                    uTCustomHitBuilder.setProperty("search_type", t.a().c());
                }
                if (!FeedbackItem.STATUS_WAITING.equals(aVar.r())) {
                    uTCustomHitBuilder.setProperty("similar_type", aVar.r());
                }
                if (!FeedbackItem.STATUS_WAITING.equals(aVar.s())) {
                    uTCustomHitBuilder.setProperty("similar_songid", aVar.s());
                }
                if (aVar.k() == com.sds.android.ttpod.common.b.a.a.a.MV) {
                    uTCustomHitBuilder.setProperty("online", (!com.sds.android.sdk.lib.util.e.a(aVar.l()) ? 1 : null) != null ? "1" : FeedbackItem.STATUS_WAITING);
                    if (aVar.t().intValue() > 0) {
                        uTCustomHitBuilder.setProperty("mv_id", aVar.t().toString());
                    } else {
                        uTCustomHitBuilder.setProperty("mv_name", aVar.g());
                    }
                }
                UTAnalytics.getInstance().getTracker("share").send(uTCustomHitBuilder.build());
                b("songlist_id");
                b("songlist_type");
            }
        }
    }

    /* AlibabaStats */
    public static final class t {
        private static HashMap<String, String> a = new HashMap();
        private static HashMap<String, String> b = new HashMap();
        private static boolean c = true;
        private static t d = new t();

        private t() {
        }

        public static t a() {
            return d;
        }

        public synchronized void a(String str) {
            if (str != null) {
                if (str.length() > 0) {
                    a.put("module_id", str);
                }
            }
        }

        public synchronized String b() {
            return (String) a.get("module_id");
        }

        public synchronized void a(String str, String str2) {
            a.put("search_type", str);
            a.put("keyword", str2);
        }

        public synchronized String c() {
            return (String) a.get("search_type");
        }

        public synchronized String d() {
            return (String) a.get("keyword");
        }

        public synchronized void a(String str, String str2, boolean z) {
            a(str, str2, z ? "1" : FeedbackItem.STATUS_WAITING);
        }

        public synchronized void b(String str, String str2) {
            if (str2 != null) {
                b.put(str, str2);
            }
        }

        public synchronized void a(String str, String str2, String str3) {
            b("songlist_id", str2);
            b("songlist_type", str);
            b("online", str3);
        }

        private void e() {
            b.clear();
        }

        private void f() {
            a.remove("search_type");
            a.remove("keyword");
        }

        private synchronized void a(HashMap<String, String> hashMap) {
            c = false;
            b("scm", (String) hashMap.get("scm"));
            b("songlist_type", (String) hashMap.get("songlist_type"));
            b("songlist_id", (String) hashMap.get("songlist_id"));
            b("online", (String) hashMap.get("online"));
            b("trigger_id", (String) hashMap.get("trigger_id"));
            a((String) hashMap.get("module_id"));
        }

        private synchronized void g() {
            if (!c) {
                c = true;
                e();
            }
        }

        public synchronized String b(String str) {
            return (String) b.get(str);
        }
    }
}
