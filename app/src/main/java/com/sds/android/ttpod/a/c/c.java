package com.sds.android.ttpod.a.c;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.ShortUrl;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.a.b;
import com.sds.android.ttpod.a.a.h;
import com.sds.android.ttpod.a.a.i;
import com.sds.android.ttpod.a.a.k;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.activities.apshare.ApShareEntryActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.s;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.wxapi.WXEntryActivity;
import com.sina.weibo.sdk.api.share.BaseResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* ShareSelectDialog */
public class c extends com.sds.android.ttpod.common.a.a {
    private static b f;
    private static Map<e, Integer> n = new ArrayMap();
    private static Map<e, Integer> o = new ArrayMap();
    private static Map<e, Integer> p = new ArrayMap();
    protected Map<e, Integer> a = new ArrayMap();
    protected Map<e, r> b = new ArrayMap();
    private GridView c;
    private com.sds.android.ttpod.adapter.e.b d;
    private List<com.sds.android.ttpod.adapter.e.b.a> e;
    private Activity g;
    private String h;
    private com.sds.android.ttpod.common.a.b i;
    private com.sds.android.ttpod.a.b.b j;
    private e k = e.NONE;
    private com.sds.android.ttpod.common.b.a.a l;
    private com.sds.android.ttpod.a.a m;
    private com.sds.android.ttpod.a.a.a q = new com.sds.android.ttpod.a.a.a(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(i iVar) {
            WXEntryActivity.a(null);
            if (iVar.a()) {
                g.a("ShareSelectDialog", "lookShare success: " + iVar.b());
                this.a.a((Integer) c.o.get(this.a.k));
            } else {
                g.a("ShareSelectDialog", "lookShare failed: " + iVar.b());
                if (!(this.a.n() || this.a.k == e.SINA_WEIBO)) {
                    Integer num = (Integer) c.p.get(this.a.k);
                    if (num != null) {
                        this.a.a(num);
                    } else {
                        Toast.makeText(this.a.getContext(), iVar.b(), 0).show();
                    }
                }
            }
            if (this.a.m != null) {
                this.a.m.a(this.a.k, this.a.l, iVar);
            }
        }
    };
    private com.sds.android.ttpod.a.b.a r = new com.sds.android.ttpod.a.b.a(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(Bundle bundle) {
            this.a.a(0, (Object) bundle);
        }

        public void a(String str) {
            this.a.a(1, (Object) str);
        }

        public void a() {
            this.a.dismiss();
        }
    };
    private Handler s = new Handler(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    g.a("ShareSelectDialog", "auth success");
                    this.a.a((Bundle) message.obj);
                    return;
                case 1:
                    g.a("ShareSelectDialog", "auth failed");
                    this.a.a((Integer) c.n.get(this.a.k));
                    return;
                case 2:
                    this.a.a(Integer.valueOf(R.string.share_no_network));
                    this.a.dismiss();
                    return;
                case 3:
                    this.a.a(Integer.valueOf(R.string.no_wechat));
                    this.a.dismiss();
                    return;
                case 4:
                    this.a.a(Integer.valueOf(R.string.wechat_not_support_friend));
                    return;
                case 5:
                case 6:
                    this.a.a(Integer.valueOf(R.string.local_song_no_share));
                    this.a.dismiss();
                    return;
                case 7:
                    this.a.a(Integer.valueOf(R.string.netword_error));
                    return;
                default:
                    return;
            }
        }
    };

    /* ShareSelectDialog */
    private static class a implements Runnable {
        private Activity a;
        private String b;

        public a(Activity activity, String str) {
            this.a = activity;
            this.b = str;
        }

        public void run() {
            if (this.a != null) {
                Intent intent = new Intent(this.a, ApShareEntryActivity.class);
                intent.putExtra("key_media_id", this.b);
                this.a.startActivity(intent);
            }
        }
    }

    protected /* synthetic */ Object a() {
        return g();
    }

    static {
        n.put(e.SINA_WEIBO, Integer.valueOf(R.string.sina_weibo_accredit_fail));
        n.put(e.QQ_WEIBO, Integer.valueOf(R.string.qq_weibo_accredit_fail));
        n.put(e.QZONE, Integer.valueOf(R.string.qzone_accredit_fail));
        o.put(e.SINA_WEIBO, Integer.valueOf(R.string.sina_weibo_share_success));
        o.put(e.QQ_WEIBO, Integer.valueOf(R.string.qq_weibo_share_success));
        o.put(e.QZONE, Integer.valueOf(R.string.qzone_share_success));
        p.put(e.SINA_WEIBO, Integer.valueOf(R.string.sina_weibo_share_fail));
        p.put(e.QQ_WEIBO, Integer.valueOf(R.string.qq_weibo_share_fail));
        p.put(e.QZONE, Integer.valueOf(R.string.qzone_share_fail));
    }

    private boolean n() {
        if (!f.e()) {
            return false;
        }
        com.sds.android.ttpod.a.d.a.a(getContext(), f.d());
        t();
        return true;
    }

    public void a(com.sds.android.ttpod.a.a aVar) {
        this.m = aVar;
    }

    private void a(Bundle bundle) {
        if (getContext() != null) {
            f.a(getContext(), bundle);
            r();
        }
    }

    private void a(Integer num) {
        if (num != null && num.intValue() > 0) {
            Toast.makeText(getContext(), num.intValue(), 0).show();
        }
    }

    public c(Activity activity, com.sds.android.ttpod.common.b.a.a aVar) {
        super(activity);
        if (activity != null && !activity.isFinishing() && aVar != null) {
            this.g = activity;
            this.l = aVar;
            this.h = d.b((Context) activity);
            A();
            this.d.a(this.e);
            this.d.notifyDataSetChanged();
            setTitle((int) R.string.share_to);
            a(2, 8, 0, null);
            a(0, 8, 0, null);
            d();
            c();
        }
    }

    protected View a(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_share_select, viewGroup, false);
        this.d = new com.sds.android.ttpod.adapter.e.b(getContext());
        this.c = (GridView) inflate.findViewById(R.id.list_view_share_select);
        this.c.setAdapter(this.d);
        this.c.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.c(i);
            }
        });
        return inflate;
    }

    protected void d() {
        this.a.put(e.WECHAT, Integer.valueOf(272));
        this.a.put(e.WECHAT_FRIENDS, Integer.valueOf(273));
        this.a.put(e.QQ, Integer.valueOf(270));
        this.a.put(e.QZONE, Integer.valueOf(271));
        this.a.put(e.SINA_WEIBO, Integer.valueOf(274));
        this.a.put(e.QQ_WEIBO, Integer.valueOf(275));
        this.a.put(e.OTHER, Integer.valueOf(276));
        this.a.put(e.FRIEND, Integer.valueOf(269));
    }

    protected void c() {
        this.b.put(e.WECHAT, r.ACTION_SONG_SHARE_TO_WECHAT);
        this.b.put(e.WECHAT_FRIENDS, r.ACTION_SONG_SHARE_TO_WECHAT_FRIEND_CIRCLE);
        this.b.put(e.QQ, r.ACTION_SONG_SHARE_TO_QQ_FRIEND);
        this.b.put(e.QZONE, r.ACTION_SONG_SHARE_TO_QQ_ZONE);
        this.b.put(e.SINA_WEIBO, r.ACTION_SONG_SHARE_TO_SINA_WEIBO);
        this.b.put(e.QQ_WEIBO, r.ACTION_SONG_SHARE_TO_TENCENT_WEIBO);
        this.b.put(e.OTHER, r.ACTION_SONG_SHARE_TO_OTHER);
        this.b.put(e.FRIEND, r.ACTION_SONG_SHARE_TO_FRIEND);
    }

    protected c g() {
        return this;
    }

    public void a(int i, int i2, Intent intent) {
        if (this.j != null) {
            this.j.a(i, i2, intent);
            this.j = null;
        }
    }

    private void a(int i, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        this.s.sendMessage(obtain);
    }

    public void a(Intent intent) {
        f.a(intent);
    }

    private void a(final String str) {
        com.sds.android.ttpod.a.d.b.a();
        f.a((int) R.string.getting_short_url);
        com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<String, ShortUrl>(this, str) {
            final /* synthetic */ c b;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((String) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((ShortUrl) obj);
            }

            protected ShortUrl a(String str) {
                return h.a(str);
            }

            protected void a(ShortUrl shortUrl) {
                if (!(shortUrl == null || !shortUrl.isSuccess() || m.a(shortUrl.getUrl()))) {
                    com.sds.android.ttpod.a.d.b.a(str, shortUrl.getUrl());
                }
                if (this.b.g != null && !this.b.g.isFinishing()) {
                    this.b.o();
                }
            }
        });
    }

    private void c(int i) {
        this.k = ((com.sds.android.ttpod.adapter.e.b.a) this.e.get(i)).a();
        f = com.sds.android.ttpod.a.c.a(this.k, this.g);
        if (f instanceof com.sds.android.ttpod.a.a.g) {
            if (!(((com.sds.android.ttpod.a.a.g) f).f() || com.sds.android.ttpod.a.a.e.b(this.g))) {
                f.a((int) R.string.share_failed_not_install_qq_qzone);
                dismiss();
                return;
            }
        } else if (f instanceof com.sds.android.ttpod.a.a.e) {
            if (!com.sds.android.ttpod.a.a.e.b(this.g)) {
                f.a((int) R.string.share_failed_not_install_qq);
                dismiss();
                return;
            }
        } else if ((this.k == e.WECHAT || this.k == e.WECHAT_FRIENDS) && !((k) f).g()) {
            this.s.sendEmptyMessage(3);
            return;
        }
        if (this.k == e.WECHAT || this.k == e.WECHAT_FRIENDS || this.k == e.QQ || this.k == e.QZONE) {
            if (!com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
                this.s.sendEmptyMessage(2);
                return;
            } else if (!com.sds.android.ttpod.a.d.b.a(this.l, this.k)) {
                a(com.sds.android.ttpod.a.d.b.b(this.l, this.k));
                hide();
                return;
            }
        }
        o();
    }

    private void o() {
        a(this.k);
        if (this.k == e.COPY) {
            com.sds.android.ttpod.b.d.a(getContext(), com.sds.android.ttpod.a.d.b.a(this.l, this.k, false) + " " + getContext().getString(R.string.share_text_tail_info));
            f.a((int) R.string.already_copy_to_clip_board);
            dismiss();
        } else if (this.k == e.OTHER) {
            s.a(this.k.name(), true, this.l);
            this.l.e(com.sds.android.ttpod.a.d.b.a(this.l, this.k, false) + " " + getContext().getString(R.string.share_text_tail_info));
            if (this.m != null) {
                this.m.a(this.k, this.l);
            }
            f.a(this, this.l, this.q);
            dismiss();
        } else if (this.k == e.FRIEND && !this.l.q()) {
            this.c.postDelayed(new a(this.g, this.l.b()), 500);
            dismiss();
        } else if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
            if (this.m != null) {
                this.m.a(this.k, this.l);
            }
            switch (this.k) {
                case WECHAT:
                case WECHAT_FRIENDS:
                    s.a(this.k.name(), true, this.l);
                    w();
                    break;
                case MUSIC_CYCLE:
                    p();
                    break;
                case QQ:
                case QZONE:
                    u();
                    break;
                case SINA_WEIBO:
                case QQ_WEIBO:
                    s();
                    break;
            }
            hide();
        } else {
            this.s.sendEmptyMessage(2);
        }
    }

    protected void a(e eVar) {
        if (this.l != null && eVar != null) {
            new SUserEvent("PAGE_CLICK", b(eVar), com.sds.android.ttpod.framework.a.b.s.PAGE_SHARE_DIALOG.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, this.l.i()).append("url", this.l.p()).post();
        }
    }

    protected int b(e eVar) {
        Integer num = (Integer) this.a.get(eVar);
        r rVar = (r) this.b.get(eVar);
        if (num != null && num.intValue() > 0) {
            w.a(num.intValue(), (int) StatisticHelper.DELAY_SEND, 1);
        }
        return rVar != null ? rVar.getValue() : r.ACTION_NONE.getValue();
    }

    private void p() {
        dismiss();
        b();
    }

    private void q() {
        String e = this.l.e();
        final String str = x() + e.hashCode() + ".jpg";
        if (m.a(e)) {
            e(this.l.g());
        } else if (com.sds.android.sdk.lib.util.e.a(str)) {
            this.l.c(str);
            e(this.l.g());
        } else {
            a(new com.sds.android.ttpod.a.d<String>(this) {
                final /* synthetic */ c b;

                public void a(String str) {
                    this.b.z();
                    if (!m.a(str)) {
                        this.b.l.c(str);
                    }
                    this.b.e(this.b.l.g());
                }
            }, str, this.l.e());
        }
    }

    private void r() {
        if (this.l.q()) {
            q();
            return;
        }
        final String string = getContext().getString(e.getShareContentDialogTitle(this.k));
        if (this.l.i().longValue() <= 0 || this.l.k() == com.sds.android.ttpod.common.b.a.a.a.MV || this.l.k() == com.sds.android.ttpod.common.b.a.a.a.POST) {
            e(string);
            return;
        }
        y();
        a(new com.sds.android.ttpod.a.d<com.sds.android.ttpod.common.b.a.a>(this) {
            final /* synthetic */ c b;

            public void a(com.sds.android.ttpod.common.b.a.a aVar) {
                this.b.z();
                this.b.e(string);
            }
        });
    }

    private void s() {
        f.a(getContext());
        if (f.c()) {
            t();
        } else {
            r();
        }
    }

    private void t() {
        this.j = com.sds.android.ttpod.a.c.b(this.k, this.g);
        this.j.a(this.r);
    }

    private void u() {
        if (this.l.q()) {
            v();
        } else if (this.l.i().longValue() > 0) {
            a(new com.sds.android.ttpod.a.d<com.sds.android.ttpod.common.b.a.a>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.common.b.a.a aVar) {
                    this.a.b(this.a.l.e());
                }
            });
        } else {
            v();
        }
    }

    private void v() {
        com.sds.android.ttpod.component.d.a.h hVar;
        if (f instanceof com.sds.android.ttpod.a.a.g) {
            if (!((com.sds.android.ttpod.a.a.g) f).f()) {
                if (com.sds.android.ttpod.a.a.e.b(this.g)) {
                    hide();
                    hVar = new com.sds.android.ttpod.component.d.a.h(this.g, (int) R.string.qzone_forbidden_share, (int) R.string.open_qq, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.h>(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void a(com.sds.android.ttpod.component.d.a.h hVar) {
                            hVar.dismiss();
                            s.a(this.a.k.name(), true, this.a.l);
                            com.sds.android.ttpod.b.d.a(this.a.g, com.sds.android.ttpod.a.d.b.a(this.a.l, e.QZONE, true) + " " + this.a.g.getString(R.string.share_text_tail_info));
                            c.f.a(this.a, this.a.l, this.a.q);
                            this.a.dismiss();
                        }
                    }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.h>(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void a(com.sds.android.ttpod.component.d.a.h hVar) {
                            hVar.dismiss();
                            this.a.dismiss();
                        }
                    });
                    hVar.setTitle((int) R.string.prompt_title);
                    hVar.show();
                    return;
                }
                f.a((int) R.string.share_failed_not_install_qq_qzone);
                dismiss();
                return;
            }
        } else if (f instanceof com.sds.android.ttpod.a.a.e) {
            s.a(this.k.name(), true, this.l);
        } else if (this.k == e.WECHAT) {
            g.a("ShareSelectDialog", "lookWeChat version = 0x%08X", Integer.valueOf(((k) f).h().getWXAppSupportAPI()));
            if (((k) f).h().getWXAppSupportAPI() >= 570556416) {
                hide();
                hVar = new com.sds.android.ttpod.component.d.a.h(this.g, (int) R.string.wechat_forbidden_share, (int) R.string.open_wechat, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.h>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void a(com.sds.android.ttpod.component.d.a.h hVar) {
                        hVar.dismiss();
                        s.a(this.a.k.name(), true, this.a.l);
                        com.sds.android.ttpod.b.d.a(this.a.g, com.sds.android.ttpod.a.d.b.a(this.a.l, e.WECHAT, true) + " " + this.a.g.getString(R.string.share_text_tail_info));
                        try {
                            this.a.g.startActivity(this.a.g.getPackageManager().getLaunchIntentForPackage(com.sds.android.sdk.lib.util.k.c.a("4bc7b85088e99c5c06a89298cf4a")));
                        } catch (Exception e) {
                            g.a("ShareSelectDialog", "share to wechat failed");
                        }
                        this.a.dismiss();
                    }
                }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.h>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void a(com.sds.android.ttpod.component.d.a.h hVar) {
                        this.a.dismiss();
                        hVar.dismiss();
                    }
                });
                hVar.setTitle((int) R.string.prompt_title);
                hVar.show();
                return;
            }
        }
        f.a(this, this.l, this.q);
        dismiss();
    }

    public void a(com.sds.android.ttpod.common.b.a.a aVar) {
        if (m.a(aVar.e())) {
            this.l.d(com.sds.android.ttpod.a.d.b.a(aVar.j().longValue()));
        }
    }

    private void a(final com.sds.android.ttpod.a.d<com.sds.android.ttpod.common.b.a.a> dVar) {
        y();
        com.sds.android.cloudapi.ttpod.a.r.a(this.l.i()).a(new p<OnlineMediaItemsResult>(this) {
            final /* synthetic */ c b;

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((OnlineMediaItemsResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((OnlineMediaItemsResult) baseResult);
            }

            public void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList dataList = onlineMediaItemsResult.getDataList();
                if (dataList == null || dataList.isEmpty()) {
                    this.b.z();
                    this.b.s.sendEmptyMessage(6);
                    return;
                }
                OnlineMediaItem onlineMediaItem = (OnlineMediaItem) dataList.get(0);
                String a = com.sds.android.ttpod.a.d.b.a(onlineMediaItem);
                if (onlineMediaItem != null) {
                    this.b.l.g(a);
                    this.b.l.a(onlineMediaItem.getArtistId());
                    this.b.a(this.b.l);
                    dVar.a(this.b.l);
                }
            }

            public void b(OnlineMediaItemsResult onlineMediaItemsResult) {
                this.b.z();
                this.b.s.sendEmptyMessage(7);
            }
        });
    }

    protected void b() {
        s.a(this.k.name(), true, this.l);
    }

    private void w() {
        if (f instanceof k) {
            k kVar = (k) f;
            if (!kVar.g()) {
                this.s.sendEmptyMessage(3);
            } else if (!kVar.f()) {
                this.s.sendEmptyMessage(4);
            } else if (this.l.q()) {
                b(this.l.e());
            } else if (this.l.i().longValue() > 0) {
                a(new com.sds.android.ttpod.a.d<com.sds.android.ttpod.common.b.a.a>(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void a(com.sds.android.ttpod.common.b.a.a aVar) {
                        this.a.b(this.a.l.e());
                    }
                });
            } else {
                v();
            }
        }
    }

    private String x() {
        String str = this.h + File.separator + "image" + File.separator;
        if (!new File(str).exists()) {
            com.sds.android.sdk.lib.util.e.f(str);
        }
        return str;
    }

    private void a(final com.sds.android.ttpod.a.d<String> dVar, final String str, String str2) {
        y();
        com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<String, String>(this, str2) {
            final /* synthetic */ c c;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((String) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                b((String) obj);
            }

            protected String a(String str) {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    Bitmap decodeStream = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                    OutputStream fileOutputStream = new FileOutputStream(str);
                    decodeStream.compress(CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    if (decodeStream != null) {
                        decodeStream.recycle();
                    }
                    return str;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    return null;
                }
            }

            protected void b(String str) {
                this.c.z();
                dVar.a(str);
            }
        });
    }

    private void b(String str) {
        if (m.a(str)) {
            v();
            return;
        }
        final String str2 = x() + str.hashCode() + ".jpg";
        if (new File(str2).exists()) {
            this.l.c(str2);
            v();
            return;
        }
        a(new com.sds.android.ttpod.a.d<String>(this) {
            final /* synthetic */ c b;

            public void a(String str) {
                this.b.z();
                if (!m.a(str)) {
                    this.b.l.c(str2);
                }
                this.b.v();
            }
        }, str2, str);
    }

    private void y() {
        if (this.i == null && this.g != null && !this.g.isFinishing()) {
            this.i = new com.sds.android.ttpod.common.a.b(this.g);
            this.i.a(this.g.getString(R.string.share_message));
            this.i.show();
        }
    }

    private void z() {
        if (this.i != null) {
            this.i.dismiss();
            this.i = null;
        }
    }

    private void e(final String str) {
        if (this.g != null && !this.g.isFinishing()) {
            if (m.a(this.l.d())) {
                String e = this.l.e();
                if (!m.a(e)) {
                    final String str2 = x() + e.hashCode() + ".jpg";
                    if (new File(str2).exists()) {
                        this.l.c(str2);
                    } else {
                        a(new com.sds.android.ttpod.a.d<String>(this) {
                            final /* synthetic */ c c;

                            public void a(String str) {
                                this.c.z();
                                if (!m.a(str)) {
                                    this.c.l.c(str2);
                                }
                                this.c.f(str);
                            }
                        }, str2, e);
                        return;
                    }
                }
            }
            f(str);
        }
    }

    private void f(String str) {
        if (this.g != null && !this.g.isFinishing()) {
            if (this.k == e.QQ_WEIBO) {
                b bVar = new b(this.g, f, this.q);
                bVar.setTitle((CharSequence) str);
                bVar.a(this.k, this.l);
                bVar.setOnDismissListener(new OnDismissListener(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void onDismiss(DialogInterface dialogInterface) {
                        this.a.dismiss();
                    }
                });
                bVar.show();
                return;
            }
            v();
        }
    }

    public void a(BaseResponse baseResponse) {
        g.a("ShareSelectDialog", "lookShare onResponse");
        i iVar = new i();
        if (baseResponse == null || baseResponse.errCode == 2) {
            iVar.a(0);
        } else if (baseResponse.errCode == 1) {
            iVar.a(2);
        } else {
            iVar.a(1);
        }
        iVar.a(baseResponse.errMsg);
        this.q.a(iVar);
    }

    private void A() {
        this.e = new ArrayList(10);
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.wechat_friend, R.drawable.icon_share_sns_weixinfriend, e.WECHAT_FRIENDS));
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.wechat, R.drawable.icon_share_sns_weixin, e.WECHAT));
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.qq, R.drawable.icon_share_sns_qq, e.QQ));
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.qq_zone, R.drawable.icon_share_sns_qzone, e.QZONE));
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.sina_weibo, R.drawable.icon_share_sns_sina, e.SINA_WEIBO));
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.qq_weibo, R.drawable.icon_share_sns_tencent, e.QQ_WEIBO));
        if (this.l.c()) {
            this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.share_friend, R.drawable.icon_share_sns_friend, e.FRIEND));
        }
        if (this.l != null && this.l.k() == com.sds.android.ttpod.common.b.a.a.a.POST) {
            this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.music_circle, R.drawable.icon_share_sns_music_circle, e.MUSIC_CYCLE));
        }
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.copy_to_clip_board, R.drawable.icon_share_copy_link, e.COPY));
        this.e.add(new com.sds.android.ttpod.adapter.e.b.a(R.string.other, R.drawable.icon_share_sns_other, e.OTHER));
    }

    protected boolean f() {
        return false;
    }

    public e h() {
        return this.k;
    }

    public b i() {
        return f;
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
