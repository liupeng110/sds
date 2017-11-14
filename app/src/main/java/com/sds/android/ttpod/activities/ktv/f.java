package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.l;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.component.d.a.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import com.tencent.weibo.sdk.android.component.sso.tools.MD5Tools;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* KtvSongControl */
public class f {
    private static f g;
    private s a;
    private String b;
    private String c;
    private String d;
    private long e;
    private c f;

    public static f a() {
        if (g == null) {
            g = new f();
        }
        return g;
    }

    public void a(c cVar) {
        this.f = cVar;
    }

    public void a(Context context, String str) {
        b(context, "正在连接ktv,请等待...");
        b.a(str).a(new p<e>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((e) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((e) baseResult);
            }

            public void a(e eVar) {
                this.a.c = eVar.c();
                this.a.b = eVar.b();
                g.a("KtvSongControl", "mRoomInfo: " + this.a.c + " mUrlDomain:" + this.a.b);
                b.c(this.a.c);
                b.a(this.a.b);
                this.a.d();
            }

            public void b(e eVar) {
                this.a.i();
                if (eVar.getCode() == 5) {
                    com.sds.android.ttpod.component.d.f.a("请到服务中心，开台吧！");
                } else {
                    com.sds.android.ttpod.component.d.f.a(eVar.getMessage());
                }
            }
        });
    }

    private void d() {
        int h = h();
        String str = this.b + "/room";
        this.e = e();
        Map hashMap = new HashMap();
        hashMap.put("appid", "D6A10423AD08459E8E384604C56E6836");
        hashMap.put("userid", Long.valueOf(this.e));
        hashMap.put(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, f());
        hashMap.put("userpic", g());
        hashMap.put("bindType", Integer.valueOf(2));
        hashMap.put("roominfo", this.c);
        hashMap.put("time", Integer.valueOf(h));
        hashMap.put("sign", a(this.c, null, h));
        b.a(str, hashMap).a(new p<e>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((e) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((e) baseResult);
            }

            public void a(e eVar) {
                this.a.d = eVar.a();
                g.a("KtvSongControl", "mCheckCode: " + this.a.d);
                b.b(this.a.d);
                b.a(this.a.e);
                if (this.a.f != null) {
                    this.a.f.success();
                }
                this.a.i();
                com.sds.android.ttpod.component.d.f.a("连接KTV成功，可以点歌啦！");
            }

            public void b(e eVar) {
                this.a.i();
                g.a("KtvSongControl", "bindKtvRoom fail: " + eVar.getCode());
                this.a.b();
                if (this.a.f != null) {
                    this.a.f.fail();
                }
                com.sds.android.ttpod.component.d.f.a(eVar.getMessage());
            }
        });
    }

    public void a(Context context, final List<g> list) {
        if (list != null) {
            if (c()) {
                w.a(169, (int) StatisticHelper.DELAY_SEND, 1);
                b(context, "正在点歌,请等待...");
                String str = this.b + "/song/vod";
                int h = h();
                this.e = this.e <= 0 ? b.e() : this.e;
                a(str, "D6A10423AD08459E8E384604C56E6836", this.c, com.sds.android.sdk.lib.util.f.a((Object) list), String.valueOf(this.e), h, a(this.c, this.d, h)).a(new p<h>(this) {
                    final /* synthetic */ f b;

                    public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                        b((h) baseResult);
                    }

                    public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                        a((h) baseResult);
                    }

                    public void a(h hVar) {
                        StringBuffer stringBuffer;
                        this.b.i();
                        w.a("ktv", "click", "play-song", 1);
                        w.a(170, (int) StatisticHelper.DELAY_SEND, 1);
                        if (list.size() == 1 && list.size() == hVar.a().size()) {
                            stringBuffer = new StringBuffer("歌曲不能匹配");
                        } else {
                            stringBuffer = new StringBuffer("点歌成功");
                            if (hVar.a().size() > 0) {
                                stringBuffer.append(",有" + hVar.a().size() + "首歌曲不能匹配!");
                            }
                        }
                        com.sds.android.ttpod.component.d.f.a(stringBuffer.toString());
                    }

                    public void b(h hVar) {
                        this.b.i();
                        w.a("ktv", "click", "play-song", -1);
                        if (hVar.getCode() == 6 || hVar.getCode() == 3) {
                            this.b.b();
                            if (this.b.f != null) {
                                this.b.f.fail();
                            }
                            com.sds.android.ttpod.component.d.f.a("请重新连接KTV!");
                            return;
                        }
                        com.sds.android.ttpod.component.d.f.a(hVar.getMessage());
                    }
                });
                return;
            }
            a(context);
        }
    }

    public void b() {
        b.b(null);
        b.c(null);
    }

    public boolean c() {
        this.b = b.b();
        this.c = b.d();
        this.d = b.c();
        return (m.a(this.b) || m.a(this.c) || m.a(this.d)) ? false : true;
    }

    private long e() {
        long userId = b.at().getUserId();
        if (userId <= 0) {
            return System.currentTimeMillis();
        }
        return userId;
    }

    private String f() {
        String userName = b.at().getUserName();
        if (m.a(userName)) {
            return "ttpod_ktv";
        }
        return userName;
    }

    private String g() {
        String avatarUrl = b.at().getAvatarUrl();
        return !m.a(avatarUrl) ? avatarUrl : "";
    }

    private int h() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    private String a(String str, String str2, int i) {
        if (i <= 0) {
            i = h();
        }
        StringBuffer stringBuffer = new StringBuffer("32433A3F98F34716A5D663B4D5AFF7D5");
        if (!m.a(str2)) {
            stringBuffer.append(str2);
        }
        stringBuffer.append(str);
        stringBuffer.append(i);
        g.a("KtvSongControl", "sing:" + stringBuffer.toString());
        return MD5Tools.toMD5(stringBuffer.toString()).toLowerCase();
    }

    private void b(Context context, String str) {
        try {
            if (this.a == null) {
                this.a = new s(context);
                if (!m.a(str)) {
                    this.a.a((CharSequence) str);
                }
            }
            if (!this.a.isShowing()) {
                this.a.show();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void i() {
        if (this.a != null && this.a.isShowing()) {
            this.a.dismiss();
            this.a = null;
        }
    }

    public static l<h> a(String str, String str2, String str3, String str4, String str5, int i, String str6) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appid", str2);
            jSONObject.put("roominfo", str3);
            jSONObject.put("musicinfo", new JSONArray(str4));
            jSONObject.put("userid", str5);
            jSONObject.put("sign", str6);
            jSONObject.put("time", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        l<h> lVar = new l(h.class, str, jSONObject.toString());
        lVar.d(HttpClientProxy.HEADER_CONTENT_TYPE, "application/json");
        lVar.d(HttpClientProxy.HEADER_ACCEPT_GZIP, HttpClientProxy.CONTENT_NOT_ENCODING_GZIP);
        return lVar;
    }

    public static void a(Context context) {
        if (b(context)) {
            w.a("ktv", "click", "camera");
            FragmentLoaderActivity.startFragmentLoaderActivity(context, 8);
        } else if (context instanceof KtvActivity) {
            ((KtvActivity) context).showDownloadDialog();
        }
    }

    public static boolean b(Context context) {
        return new File(context.getFilesDir(), "apk/Ktv.apk").exists();
    }
}
