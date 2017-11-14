package com.sds.android.ttpod.fragment.skinmanager;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* CategoryViewHolder */
public class a {
    private ImageView a;
    private TextView b;

    public a(View view) {
        this.a = (ImageView) view.findViewById(R.id.imageview_thumb);
        this.b = (TextView) view.findViewById(R.id.category_name);
    }

    public ImageView a() {
        return this.a;
    }

    public TextView b() {
        return this.b;
    }
}
