package com.sds.android.ttpod.b;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import com.igexin.download.Downloads;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SlidingAlbumDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.activities.version.VersionUpgradeProgressActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.MainFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubRankDetailFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubSongCategoryDetailFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.fragment.mv.MVFragment;
import com.sds.android.ttpod.fragment.search.OnlineSearchFragment;
import com.sds.android.ttpod.framework.a.b.d.j;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* StartAction */
public class u {
    private static final String a = u.class.getName();
    private static boolean d;
    private BaseFragment b;
    private BaseActivity c;

    public u(BaseActivity baseActivity, BaseFragment baseFragment) {
        this.c = baseActivity;
        this.b = baseFragment;
    }

    public u(BaseActivity baseActivity) {
        this.c = baseActivity;
    }

    public static boolean a() {
        return d;
    }

    public static void a(boolean z) {
        d = z;
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            return false;
        }
        if (MediaStore.AUTHORITY.equals(uri.getScheme()) && "open".equals(uri.getHost())) {
            return c(uri);
        }
        if (MediaStore.AUTHORITY.equals(uri.getScheme())) {
            return a(d(uri));
        }
        if (!FeedbackMessage.TYPE_FILE.equals(uri.getScheme())) {
            return c(uri);
        }
        Bundle bundle = new Bundle();
        bundle.putString(Downloads.COLUMN_URI, uri.getPath());
        return p(bundle);
    }

    public boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        boolean c;
        String string = bundle.getString(PushConsts.CMD_ACTION);
        int a = (int) a(bundle, "sid", 0);
        a(a, string, true);
        if ("play".equals(string)) {
            a(true);
            c = c(bundle);
        } else if ("play_next".equals(string)) {
            c = d(bundle);
        } else if ("play_previous".equals(string)) {
            c = e(bundle);
        } else if ("play_pause".equals(string)) {
            c = g(bundle);
        } else if ("play_stop".equals(string)) {
            c = f(bundle);
        } else if ("play_resume".equals(string)) {
            c = h(bundle);
        } else if ("download".equals(string)) {
            c = r(bundle);
        } else if ("play_list".equals(string)) {
            c = t(bundle);
        } else if ("user_post_list".equals(string)) {
            c = v(bundle);
        } else if ("post_detail".equals(string)) {
            c = w(bundle);
        } else if ("open_web".equals(string)) {
            c = y(bundle);
        } else if ("search".equals(string)) {
            c = z(bundle);
        } else if ("share".equals(string)) {
            c = n(bundle);
        } else if ("dialog".equals(string)) {
            c = m(bundle);
        } else if ("play_mv".equals(string)) {
            c = l(bundle);
        } else if ("open_mv".equals(string)) {
            c = i(bundle);
        } else if ("open_album".equals(string)) {
            c = k(bundle);
        } else if ("open_singer".equals(string)) {
            c = j(bundle);
        } else {
            c = b(bundle);
        }
        if (c) {
            a(a, string, false);
        }
        return c;
    }

    private boolean c(Uri uri) {
        String queryParameter = uri.getQueryParameter(StarCategory.KEY_STAR_CATEGORY_ID);
        String queryParameter2 = uri.getQueryParameter("target");
        if (!(m.a(queryParameter) || m.a(queryParameter2))) {
            long f = f(queryParameter);
            Bundle bundle = new Bundle();
            if ("post".equals(queryParameter2)) {
                bundle.putLong("post_id", f);
                return x(bundle);
            } else if ("user".equals(queryParameter2)) {
                bundle.putLong("user_id", f);
                return u(bundle);
            }
        }
        return false;
    }

    private boolean b(Bundle bundle) {
        long a = a(bundle, "postId", 0);
        if (0 != a) {
            bundle.putLong("post_id", a);
            bundle.putInt("category_type", 1);
            w.a("push", "show", "gexin", 1);
            new SUserEvent("PAGE_CLICK", r.ACTION_PUSH_OPEN.getValue(), s.PAGE_PUSH.getValue(), s.PAGE_ONLINE_POST_DETAIL.getValue()).append("cid", b.aG()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(a)).post();
            return x(bundle);
        }
        a = a(bundle, "rankId", 0);
        if (a > 0) {
            this.c.launchFragment(new SubRankDetailFragment((int) a));
            new SUserEvent("PAGE_CLICK", r.ACTION_PUSH_OPEN.getValue(), s.PAGE_PUSH.getValue(), s.PAGE_ONLINE_RANK_DETAIL.getValue()).append("cid", b.aG()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(a)).post();
            return true;
        }
        a = a(bundle, "categoryId", 0);
        if (a > 0) {
            this.c.launchFragment(new SubSongCategoryDetailFragment(String.valueOf(a)));
            new SUserEvent("PAGE_CLICK", r.ACTION_PUSH_OPEN.getValue(), s.PAGE_PUSH.getValue(), s.PAGE_ONLINE_SONG_CATEGORY.getValue()).append("cid", b.aG()).append("channel_id", Long.valueOf(a)).post();
            return true;
        }
        String string = bundle.getString("link");
        if (m.a(string)) {
            string = bundle.getString("search");
            if (m.a(string)) {
                string = bundle.getString(Downloads.COLUMN_URI);
                int a2 = (int) a(bundle, "app", 0);
                if (!m.a(string) && a2 > 0) {
                    bundle.putString(Downloads.COLUMN_URI, string);
                    return c(bundle);
                } else if (!bundle.getBoolean("push_redirect_id")) {
                    return false;
                } else {
                    b();
                    new SUserEvent("PAGE_CLICK", r.ACTION_PUSH_OPEN.getValue(), s.PAGE_PUSH.getValue(), s.PAGE_ONLINE_FIND_SONG.getValue()).append("cid", b.aG()).post();
                    return true;
                }
            }
            bundle.putString("search", string);
            return z(bundle);
        }
        bundle.putInt("category_type", 1);
        bundle.putString(WebFragment.EXTRA_TITLE, this.c.getString(R.string.detail_page));
        bundle.putString(WebFragment.EXTRA_URL, string);
        bundle.putBoolean(WebFragment.EXTRA_IS_SHOW_PLAY_CONTROL_BAR, true);
        w.a("push", "show", "gexin", 2);
        BaseFragment webSlidingClosableFragment = new WebSlidingClosableFragment();
        webSlidingClosableFragment.setArguments(bundle);
        this.c.launchFragment(webSlidingClosableFragment);
        new SUserEvent("PAGE_CLICK", r.ACTION_PUSH_OPEN.getValue(), s.PAGE_PUSH.getValue(), s.PAGE_ONLINE_POST_DETAIL_WEB.getValue()).append("cid", b.aG()).append("url", string).post();
        return true;
    }

    private boolean c(Bundle bundle) {
        String string = bundle.getString(Downloads.COLUMN_URI);
        String string2 = bundle.getString(MediasColumns.SONG_ID);
        if (h(string)) {
            return q(bundle);
        }
        if (i(string)) {
            return p(bundle);
        }
        if (m.a(string2)) {
            return false;
        }
        return o(bundle);
    }

    private boolean d(Bundle bundle) {
        b((int) a(bundle, "play_mode", -1));
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
        return true;
    }

    private boolean e(Bundle bundle) {
        b((int) a(bundle, "play_mode", -1));
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.PREVIOUS, new Object[0]));
        return true;
    }

    private boolean f(Bundle bundle) {
        b((int) a(bundle, "play_mode", -1));
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.STOP, new Object[0]));
        return true;
    }

    private boolean g(Bundle bundle) {
        b((int) a(bundle, "play_mode", -1));
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
        return true;
    }

    private boolean h(Bundle bundle) {
        b((int) a(bundle, "play_mode", -1));
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
        return true;
    }

    private void a(long j, boolean z, Bundle bundle) {
        if (!z && 1 == j && this.c != null) {
            if (!m.a(a(bundle, Downloads.COLUMN_URI, ""))) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.START_COMMON_UPGRADE, r0));
                this.c.startActivity(new Intent(this.c, VersionUpgradeProgressActivity.class));
            }
        }
    }

    private boolean i(Bundle bundle) {
        int a = (int) a(bundle, "position", 0);
        if (this.c == null || (this.c.getTopFragment() instanceof MVFragment)) {
            return false;
        }
        BaseFragment mVFragment = new MVFragment();
        mVFragment.setCurrentPosition(a);
        this.c.launchFragment(mVFragment);
        return true;
    }

    private boolean j(Bundle bundle) {
        int a = (int) a(bundle, StarCategory.KEY_STAR_CATEGORY_ID, 0);
        String a2 = a(bundle, "name", "");
        boolean z = bundle.getBoolean("key_show_tags", true);
        int a3 = (int) a(bundle, "tags", 1);
        if (this.c == null || a <= 0) {
            return false;
        }
        SingerDetailFragment.launch(this.c, a2, a, z, a3);
        return true;
    }

    private boolean k(Bundle bundle) {
        int a = (int) a(bundle, StarCategory.KEY_STAR_CATEGORY_ID, 0);
        BaseFragment instantiate = SlidingAlbumDetailFragment.instantiate((long) a, a(bundle, "name", ""));
        if (this.c == null || a <= 0) {
            return false;
        }
        this.c.launchFragment(instantiate);
        return true;
    }

    private boolean l(Bundle bundle) {
        int a = (int) a(bundle, StarCategory.KEY_STAR_CATEGORY_ID, 0);
        if (this.c == null || a == 0) {
            return false;
        }
        t.a().a("push");
        j.a("mv_origin", "push");
        VideoPlayManager.a(this.c, Integer.valueOf(a));
        return true;
    }

    private boolean m(final Bundle bundle) {
        if (this.c != null) {
            CharSequence a = a(bundle, "title", "提示");
            CharSequence a2 = a(bundle, "message", "内容");
            String a3 = a(bundle, "button_ok", "");
            String a4 = a(bundle, "button_cancel", "");
            final long a5 = a(bundle, SocialConstants.PARAM_TYPE, 0);
            h hVar = new h(this.c, a2, (int) R.string.ok, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ u c;

                public void a(h hVar) {
                    this.c.a(a5, false, bundle);
                }
            }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ u c;

                public void a(h hVar) {
                    this.c.a(a5, true, bundle);
                }
            });
            if (!m.a(a3)) {
                hVar.c(a3);
            }
            if (!m.a(a4)) {
                hVar.d(a4);
            }
            hVar.setTitle(a);
            hVar.show();
        }
        return false;
    }

    private boolean n(Bundle bundle) {
        String a = a(bundle, "url", "");
        if (m.a(a)) {
            return false;
        }
        String a2 = a(bundle, "title", "");
        String a3 = a(bundle, User.KEY_AVATAR, "");
        String a4 = a(bundle, SocialConstants.PARAM_APP_DESC, "");
        String a5 = a(bundle, SocialConstants.PARAM_TYPE, "");
        com.sds.android.ttpod.common.b.a.a aVar = new com.sds.android.ttpod.common.b.a.a();
        aVar.a(com.sds.android.ttpod.common.b.a.a.a.THIRDPARTY);
        aVar.f(a2);
        aVar.d(a3);
        aVar.i(a);
        aVar.j(a5);
        aVar.e(a4 + " " + a);
        f.a(this.c, aVar);
        return true;
    }

    private boolean o(Bundle bundle) {
        String string = bundle.getString(MediasColumns.SONG_ID);
        final int a = (int) a(bundle, "play_mode", -1);
        final int a2 = (int) a(bundle, "position", 0);
        if (m.a(string)) {
            return false;
        }
        List b = m.b(string, SelectCountryActivity.SPLITTER);
        if (b.size() > 0) {
            o.a(b, new o.a<List<MediaItem>>(this) {
                final /* synthetic */ u c;

                public void a(List<MediaItem> list) {
                    if (list != null && list.size() > 0) {
                        this.c.a((List) list, a, a2);
                    }
                }
            });
        }
        return true;
    }

    private boolean p(Bundle bundle) {
        String string = bundle.getString(Downloads.COLUMN_URI);
        int a = (int) a(bundle, "play_mode", -1);
        if (m.a(string) || !e.a(string)) {
            return false;
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PLAY, string));
        b(a);
        return true;
    }

    private boolean q(Bundle bundle) {
        List arrayList = new ArrayList();
        String string = bundle.getString(Downloads.COLUMN_URI);
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("artist");
        int a = (int) a(bundle, "position", 0);
        int a2 = (int) a(bundle, "duration", 0);
        int a3 = (int) a(bundle, "play_mode", -1);
        if (m.a(string)) {
            return false;
        }
        arrayList.add(k.a(string, string2, string3, a2));
        return a(arrayList, a3, a);
    }

    private void a(int i, String str, boolean z) {
        if (i > 0) {
            new SSystemEvent("SYS_THIRDPARTY", str).append("sid", Integer.valueOf(i)).append(PushConsts.CMD_ACTION, z ? FeedbackItem.STATUS_WAITING : "1").post();
        }
    }

    private boolean r(Bundle bundle) {
        String string = bundle.getString(MediasColumns.SONG_ID);
        if (!m.a(string)) {
            return b(string);
        }
        if (s(bundle) == null) {
            return false;
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, r2));
        return true;
    }

    private boolean b(String str) {
        if (m.a(str)) {
            return false;
        }
        List b = m.b(str, SelectCountryActivity.SPLITTER);
        if (b.size() <= 0) {
            return false;
        }
        o.a(b, new o.a<List<MediaItem>>(this) {
            final /* synthetic */ u a;

            {
                this.a = r1;
            }

            public void a(List<MediaItem> list) {
                if (list != null && list.size() > 0) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.ASYN_ADD_DOWNLOAD_TASK_LIST, com.sds.android.ttpod.framework.a.e.a((List) list, AudioQuality.HIGH), Boolean.FALSE), 10);
                }
            }
        });
        return true;
    }

    private DownloadTaskInfo s(Bundle bundle) {
        String string = bundle.getString(Downloads.COLUMN_URI);
        if (m.a(string)) {
            return null;
        }
        int intValue;
        int a = (int) a(bundle, SocialConstants.PARAM_TYPE, (long) c(string));
        if (a > DownloadTaskInfo.TYPE_APP.intValue() || a < DownloadTaskInfo.TYPE_AUDIO.intValue()) {
            intValue = DownloadTaskInfo.TYPE_AUDIO.intValue();
        } else {
            intValue = a;
        }
        String string2 = bundle.getString("path");
        if (m.a(string2) || !e.a(string2)) {
            string2 = a(intValue);
        }
        String string3 = bundle.getString("name");
        StringBuilder append = new StringBuilder(string2).append(File.separator);
        if (m.a(string3)) {
            string3 = e.k(string);
            append.append(e.j(string));
        } else {
            String m = e.m(string);
            String m2 = e.m(string3);
            append.append(string3);
            if (m.a(m2) && !m.a(m)) {
                append.append(".").append(m);
            }
        }
        return com.sds.android.ttpod.framework.a.e.a(string, append.toString(), Long.valueOf(0), string3, Integer.valueOf(intValue), Boolean.valueOf(true), "thirdparty_download");
    }

    private int c(String str) {
        String m = e.m(str);
        if (!m.a(m)) {
            if (m.equalsIgnoreCase("apk")) {
                return DownloadTaskInfo.TYPE_APP.intValue();
            }
            if (m.equalsIgnoreCase("mp4")) {
                return DownloadTaskInfo.TYPE_VIDEO.intValue();
            }
        }
        return DownloadTaskInfo.TYPE_AUDIO.intValue();
    }

    private String a(int i) {
        if (DownloadTaskInfo.TYPE_AUDIO.intValue() == i) {
            return com.sds.android.ttpod.framework.a.r();
        }
        if (DownloadTaskInfo.TYPE_APP.intValue() == i) {
            return com.sds.android.ttpod.framework.a.x();
        }
        if (DownloadTaskInfo.TYPE_VIDEO.intValue() == i) {
            return com.sds.android.ttpod.framework.a.z();
        }
        if (DownloadTaskInfo.TYPE_SKIN.intValue() == i) {
            return com.sds.android.ttpod.framework.a.o();
        }
        return com.sds.android.ttpod.framework.a.C();
    }

    private void b(int i) {
        if (i >= 0 && i < com.sds.android.ttpod.framework.support.a.f.values().length) {
            b.a(com.sds.android.ttpod.framework.support.a.f.values()[i]);
            com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_MODE, new Object[0]), c.SUPPORT);
        }
    }

    private boolean a(List<MediaItem> list, int i, int i2) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        if (i2 >= list.size()) {
            i2 = 0;
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, list));
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(i2)));
        b(i);
        return true;
    }

    private boolean t(Bundle bundle) {
        String string = bundle.getString("media_json");
        int a = (int) a(bundle, "play_mode", -1);
        int a2 = (int) a(bundle, "position", 0);
        if (m.a(string)) {
            return false;
        }
        return a(a(g(e(string))), a, a2);
    }

    private boolean u(Bundle bundle) {
        long a = a(bundle, "user_id", 0);
        if (a <= 0) {
            return false;
        }
        TTPodUser tTPodUser = new TTPodUser();
        tTPodUser.setUserId(a);
        BaseFragment createUserPostListFragmentByUser = WrapUserPostListFragment.createUserPostListFragmentByUser(com.sds.android.ttpod.framework.modules.core.f.b.a(tTPodUser), "start-action");
        createUserPostListFragmentByUser.setModuleId("push");
        this.c.launchFragment(createUserPostListFragmentByUser);
        return true;
    }

    private boolean v(Bundle bundle) {
        if (a(bundle, "user_id", 0) > 0) {
            return u(bundle);
        }
        return false;
    }

    private boolean w(Bundle bundle) {
        if (a(bundle, "post_id", 0) > 0) {
            return x(bundle);
        }
        return false;
    }

    private boolean x(Bundle bundle) {
        long a = a(bundle, "post_id", 0);
        if (a <= 0) {
            return false;
        }
        long a2 = a(bundle, "display_type", 0);
        BaseFragment createById = SubPostDetailFragment.createById(a, "start-action");
        createById.setModuleId("push");
        this.c.launchFragment(createById);
        if (1 == a2) {
            b();
        }
        return true;
    }

    private boolean y(Bundle bundle) {
        String string = bundle.getString(Downloads.COLUMN_URI);
        String string2 = bundle.getString("title");
        long a = a(bundle, "display_type", 0);
        if (m.a(string)) {
            return false;
        }
        Intent intent = new Intent(this.c, WebActivity.class);
        intent.setData(Uri.parse(string));
        intent.putExtra(WebFragment.EXTRA_TITLE, string2);
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
        this.c.startActivity(intent);
        if (1 == a) {
            b();
        }
        return true;
    }

    private boolean z(Bundle bundle) {
        String string = bundle.getString("search");
        if (m.a(string)) {
            return false;
        }
        a(string, (int) a(bundle, StarCategory.KEY_STAR_CATEGORY_ID, 0));
        return true;
    }

    private void b() {
        if (this.b instanceof MainFragment) {
            ((MainFragment) this.b).toggleFindSongFragment();
        }
    }

    private void a(String str, int i) {
        this.c.launchFragment(OnlineSearchFragment.instantiate(str, i));
    }

    private String d(String str) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private String e(String str) {
        try {
            return new String(Base64.decode(str.getBytes(), 0));
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    private long a(Bundle bundle, String str, long j) {
        Object obj = bundle.get(str);
        if (obj == null || m.a(obj.toString())) {
            return j;
        }
        return f(obj.toString());
    }

    private String a(Bundle bundle, String str, String str2) {
        String string = bundle.getString(str);
        return m.a(string) ? str2 : string;
    }

    private Bundle d(Uri uri) {
        Bundle bundle = new Bundle();
        bundle.putString("scheme", uri.getScheme());
        bundle.putString(PushConsts.CMD_ACTION, uri.getHost());
        for (String str : a(uri.getEncodedQuery())) {
            String queryParameter = uri.getQueryParameter(str);
            if (!m.a(queryParameter)) {
                bundle.putString(str, queryParameter);
            }
        }
        return bundle;
    }

    public Set<String> a(String str) {
        if (str == null) {
            return Collections.emptySet();
        }
        Set linkedHashSet = new LinkedHashSet();
        int i = 0;
        do {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            linkedHashSet.add(d(str.substring(i, indexOf2)));
            i = indexOf + 1;
        } while (i < str.length());
        return Collections.unmodifiableSet(linkedHashSet);
    }

    private long f(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0;
        }
    }

    private JSONArray g(String str) {
        if (!m.a(str)) {
            try {
                return new JSONArray(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new JSONArray();
    }

    private List<MediaItem> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        List<MediaItem> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString(Downloads.COLUMN_URI);
                if (!m.a(string)) {
                    arrayList.add(k.a(string, jSONObject.optString("title", ""), jSONObject.optString("artist", ""), (int) f(jSONObject.optString("duration", FeedbackItem.STATUS_WAITING))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    private boolean h(String str) {
        return !m.a(str) && (str.startsWith("http://") || str.startsWith("https://"));
    }

    private boolean i(String str) {
        return !m.a(str) && e.a(str);
    }

    public static boolean b(Uri uri) {
        return uri != null && MediaStore.AUTHORITY.equals(uri.getScheme());
    }
}
