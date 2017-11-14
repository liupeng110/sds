package com.sds.android.ttpod.component.c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseArray;
import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.OutListItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.a.e;
import com.sds.android.ttpod.component.d.a.l;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.a.b.d.o;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* CensorHandler */
public class a {
    private BaseActivity a;
    private MediaItem b;
    private SparseArray<Integer> c;
    private String d;
    private int e;
    private int f;

    public a(Context context, MediaItem mediaItem) {
        this(context, mediaItem, "", -1);
    }

    public a(Context context, MediaItem mediaItem, String str, int i) {
        if (context == null || mediaItem == null) {
            throw new IllegalArgumentException("Context or MediaItem can NOT be null!!");
        }
        this.a = (BaseActivity) context;
        this.b = mediaItem;
        this.d = str;
        this.e = i;
        this.c = new SparseArray();
        c();
    }

    public boolean a(int i) {
        List a = a(this.b);
        Integer num = (Integer) this.c.get(i << Integer.valueOf(this.b.getCensorLevel()).intValue());
        if (num == null) {
            return false;
        }
        this.f = i;
        switch (num.intValue()) {
            case 1:
                b();
                break;
            case 3:
                a();
                break;
            default:
                a(a);
                break;
        }
        return true;
    }

    private void a() {
        List outList = this.b.getOutList();
        if (!j.a(outList)) {
            OutListItem outListItem = (OutListItem) outList.get(0);
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_COPYRIGHT_TO_THIRD.getValue(), String.valueOf(s.PAGE_DIALOG_COPYRIGHT.getValue()), String.valueOf(s.PAGE_NONE.getValue()));
            sUserEvent.append("url", a(outListItem.getUrl()));
            a(sUserEvent);
            a(outListItem.getName(), outListItem.getUrl());
            a(outListItem);
        }
    }

    private void a(List<com.sds.android.ttpod.component.b.a> list) {
        final e lVar = new l(this.a, list, this.b);
        lVar.a(new b(this) {
            final /* synthetic */ a b;

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                OutListItem outListItem = (OutListItem) aVar.h();
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_OUT_LIST.getValue(), String.valueOf(s.PAGE_DIALOG_COPYRIGHT.getValue()), outListItem.getName()).append("url", this.b.a(outListItem.getUrl())).post();
                this.b.a(outListItem.getName(), outListItem.getUrl());
                this.b.a(outListItem);
                lVar.dismiss();
            }
        });
        lVar.show();
        u.a(this.b);
        a(new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SHOW_COPYRIGHT_DIALOG.getValue(), s.PAGE_DIALOG_COPYRIGHT.getValue()));
    }

    private void b() {
        f.a((int) R.string.no_copyright_notification);
        a(new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SHOW_COPYRIGHT_TOAST.getValue(), String.valueOf(s.PAGE_DIALOG_COPYRIGHT.getValue()), String.valueOf(s.PAGE_NONE.getValue())));
        a(null, null);
    }

    private void a(String str, String str2) {
        BaseFragment topFragment = this.a.getTopFragment();
        HashMap hashMap = new HashMap();
        hashMap.put(MediasColumns.SONG_ID, String.valueOf(this.b.getSongID()));
        hashMap.put("name", this.b.getTitle());
        hashMap.put("module_id", t.a().b());
        hashMap.put("flag", String.valueOf(this.b.getCensorLevel()));
        if (!m.a(str)) {
            hashMap.put("out_name", str);
        }
        if (!m.a(str2)) {
            hashMap.put("out_url", str2);
        }
        if (topFragment != null) {
            String alibabaProperty = topFragment.getAlibabaProperty("search_type");
            String alibabaProperty2 = topFragment.getAlibabaProperty("keyword");
            if (!m.a(alibabaProperty)) {
                hashMap.put("search_type", alibabaProperty);
            }
            if (!m.a(alibabaProperty2)) {
                hashMap.put("keyword", alibabaProperty2);
            }
        }
        if (this.f == 13) {
            o.a(hashMap);
        }
    }

    private void a(SUserEvent sUserEvent) {
        sUserEvent.append("keyword", this.d);
        sUserEvent.append("position", Integer.valueOf(this.e + 1));
        sUserEvent.append(MediasColumns.SONG_ID, this.b.getSongID());
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void a(OutListItem outListItem) {
        Intent intent = new Intent(this.a, WebActivity.class);
        intent.setData(Uri.parse(a(outListItem.getUrl())));
        intent.putExtra(WebFragment.EXTRA_TITLE, outListItem.getName());
        intent.putExtra(WebFragment.EXTRA_ENABLE_SLIDING_CLOSABLE, false);
        this.a.startActivity(intent);
        u.b(this.b);
    }

    private String a(String str) {
        return str + "&from=" + this.a.getTopFragment().getStatisitcPage();
    }

    private List<com.sds.android.ttpod.component.b.a> a(MediaItem mediaItem) {
        List outList = mediaItem.getOutList();
        if (j.a(outList)) {
            return null;
        }
        List<com.sds.android.ttpod.component.b.a> arrayList = new ArrayList();
        for (int i = 0; i < outList.size(); i++) {
            Object obj = (OutListItem) outList.get(i);
            if (!m.a(obj.getUrl())) {
                com.sds.android.ttpod.component.b.a aVar = new com.sds.android.ttpod.component.b.a(i, 0, obj.getName());
                aVar.b(this.a.getResources().getDrawable(R.drawable.img_setting_right_arrow));
                aVar.a(obj);
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private void c() {
        this.c.put(20, Integer.valueOf(1));
        this.c.put(40, Integer.valueOf(1));
        this.c.put(80, Integer.valueOf(1));
        this.c.put(22, Integer.valueOf(1));
        this.c.put(44, Integer.valueOf(2));
        this.c.put(88, Integer.valueOf(3));
        this.c.put(24, Integer.valueOf(1));
        this.c.put(48, Integer.valueOf(1));
        this.c.put(96, Integer.valueOf(1));
        this.c.put(Downloads.STATUS_RUNNING, Integer.valueOf(1));
        this.c.put(26, Integer.valueOf(1));
        this.c.put(52, Integer.valueOf(2));
        this.c.put(SecExceptionCode.SEC_ERROR_INIT_NO_RSA_FILE_ERROR, Integer.valueOf(3));
    }
}
