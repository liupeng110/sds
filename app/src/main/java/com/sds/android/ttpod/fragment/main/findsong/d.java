package com.sds.android.ttpod.fragment.main.findsong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* RecommendPostViewHolder */
public class d {
    private TextView a;
    private TextView b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private ImageView h;
    private View i;
    private View j;

    public d(View view) {
        this.a = (TextView) view.findViewById(R.id.tv_author);
        this.b = (TextView) view.findViewById(R.id.tv_count);
        this.c = view.findViewById(R.id.layout_count);
        this.d = (TextView) view.findViewById(R.id.tv_title);
        this.e = (TextView) view.findViewById(R.id.text_time);
        this.g = (ImageView) view.findViewById(R.id.iv_play_pause);
        this.f = (TextView) view.findViewById(R.id.text_tweet);
        this.h = (ImageView) view.findViewById(R.id.pic);
        this.i = view.findViewById(R.id.layout_discovery_list_item);
        this.j = view.findViewById(R.id.layout_container);
    }

    public TextView a() {
        return this.a;
    }

    public TextView b() {
        return this.b;
    }

    public View c() {
        return this.c;
    }

    public TextView d() {
        return this.d;
    }

    public TextView e() {
        return this.e;
    }

    public TextView f() {
        return this.f;
    }

    public ImageView g() {
        return this.g;
    }

    public ImageView h() {
        return this.h;
    }

    public View i() {
        return this.j;
    }

    public View j() {
        return this.i;
    }
}
