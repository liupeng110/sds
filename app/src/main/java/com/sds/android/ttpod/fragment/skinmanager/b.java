package com.sds.android.ttpod.fragment.skinmanager;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* ThemeViewHolder */
public class b {
    private ImageView a;
    private ImageView b;
    private ImageView c;
    private ProgressBar d;
    private TextView e;
    private TextView f;
    private View g;
    private View h;
    private View i;
    private View j;

    public b(View view) {
        this.i = view;
        this.a = (ImageView) view.findViewById(R.id.imageview_check);
        this.b = (ImageView) view.findViewById(R.id.imageview_thumb);
        try {
            this.b.setImageResource(R.drawable.img_skin_default_thumbnail);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        this.c = (ImageView) view.findViewById(R.id.image_new_skin);
        this.j = view.findViewById(R.id.update_click_area);
        this.d = (ProgressBar) view.findViewById(R.id.progressbar_skin_download);
        this.e = (TextView) view.findViewById(R.id.skin_name);
        this.f = (TextView) view.findViewById(R.id.download_hint);
        this.g = view.findViewById(R.id.view_download_start_icon);
        this.h = view.findViewById(R.id.download_area);
    }

    public ImageView a() {
        return this.a;
    }

    public ImageView b() {
        return this.b;
    }

    public ProgressBar c() {
        return this.d;
    }

    public TextView d() {
        return this.e;
    }

    public TextView e() {
        return this.f;
    }

    public View f() {
        return this.g;
    }

    public View g() {
        return this.h;
    }

    public View h() {
        return this.i;
    }

    public ImageView i() {
        return this.c;
    }

    public View j() {
        return this.j;
    }
}
