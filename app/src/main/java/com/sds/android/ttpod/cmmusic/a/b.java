package com.sds.android.ttpod.cmmusic.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* ImageAdapter */
public class b extends PagerAdapter {
    private ArrayList<HashMap<String, String>> a;
    private List<View> b;
    private Context c;

    public b(ArrayList<HashMap<String, String>> arrayList, List<View> list, Context context) {
        this.b = list;
        this.a = arrayList;
        this.c = context;
    }

    public int getCount() {
        return this.b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        View view = (View) this.b.get(i);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        g.a(imageView, (String) ((HashMap) this.a.get(i)).get("img"), a.d(), (int) this.c.getResources().getDimension(R.dimen.recommend_pager_image_height), R.drawable.cmmusic_adseat_background);
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "AdSeatContent");
                String str = (String) ((HashMap) this.b.a.get(i)).get("href");
                if (m.a(str, "ttlink:a1")) {
                    str = "tag_6";
                } else if (m.a(str, "ttlink:a2")) {
                    str = "tag_7";
                } else if (m.a(str, "ttlink:a3")) {
                    str = "tag_8";
                } else if (m.a(str, "ttlink:a4")) {
                    str = "tag_9";
                } else if (m.a(str, "ttlink:a5")) {
                    str = "tag_10";
                } else {
                    if (!(str == null || str.equals(""))) {
                        intent.setAction("com.sds.android.ttpod.cmmusic.webview");
                        bundle.putString("href", str);
                    }
                    str = null;
                }
                if (str != null) {
                    new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_POSTER.getValue(), s.PAGE_CMMUSIC_RECOMMEND_CODE.getValue(), s.PAGE_CMMUSIC_AD_SEAT_CODE.getValue()).append("position", str).post();
                    intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                    bundle.putString("href", str);
                }
                intent.putExtras(bundle);
                this.b.c.startActivity(intent);
            }
        });
        viewGroup.removeView(view);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ImageView imageView = (ImageView) ((View) this.b.get(i)).findViewById(R.id.image);
        if (imageView != null) {
            imageView.setOnClickListener(null);
        }
        viewGroup.removeView((View) this.b.get(i));
    }
}
