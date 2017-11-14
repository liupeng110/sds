package com.sds.android.ttpod.adapter.d;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.UserAvatarView;

/* PostViewItemHolder */
public class f {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private UserAvatarView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private View m;
    private View n;
    private View o;
    private View p;
    private View q;
    private View r;
    private View s;
    private View t;
    private boolean u = true;

    f(View view) {
        this.a = (TextView) view.findViewById(R.id.text_name);
        this.b = (TextView) view.findViewById(R.id.tv_post_title);
        this.c = (TextView) view.findViewById(R.id.text_time);
        this.d = (TextView) view.findViewById(R.id.tv_from);
        this.e = (TextView) view.findViewById(R.id.text_pick_amount);
        this.f = (TextView) view.findViewById(R.id.tv_reply_amount);
        this.g = (TextView) view.findViewById(R.id.tv_repost_amount);
        this.i = (UserAvatarView) view.findViewById(R.id.image_avatar);
        this.k = (ImageView) view.findViewById(R.id.iv_post_type);
        this.j = (ImageView) view.findViewById(R.id.iv_playpause);
        this.h = (TextView) view.findViewById(R.id.text_tweet);
        this.l = (ImageView) view.findViewById(R.id.pic);
        this.o = view.findViewById(R.id.layout_pick_amount);
        this.m = view.findViewById(R.id.layout_reply_amount);
        this.n = view.findViewById(R.id.layout_repost_amount);
        this.p = view.findViewById(R.id.layout_userinfo);
        this.q = view.findViewById(R.id.layout_amount);
        this.r = view.findViewById(R.id.view_amount_divider_reply);
        this.s = view.findViewById(R.id.view_amount_divider_repost);
        this.t = view.findViewById(R.id.layout_social_post_item_root);
    }

    public View a() {
        return this.p;
    }

    public TextView b() {
        return this.a;
    }

    public TextView c() {
        return this.b;
    }

    public TextView d() {
        return this.c;
    }

    public TextView e() {
        return this.d;
    }

    public TextView f() {
        return this.e;
    }

    public TextView g() {
        return this.f;
    }

    public TextView h() {
        return this.g;
    }

    public TextView i() {
        return this.h;
    }

    public UserAvatarView j() {
        return this.i;
    }

    public ImageView k() {
        return this.j;
    }

    public ImageView l() {
        return this.k;
    }

    public ImageView m() {
        return this.l;
    }

    public View n() {
        return this.m;
    }

    public View o() {
        return this.n;
    }

    public View p() {
        return this.o;
    }

    public View q() {
        return this.q;
    }

    public View r() {
        return this.r;
    }

    public View s() {
        return this.s;
    }

    public View t() {
        return this.t;
    }
}
