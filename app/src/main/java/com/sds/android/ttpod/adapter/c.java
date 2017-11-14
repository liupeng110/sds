package com.sds.android.ttpod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;

/* GridListAdapter */
public abstract class c<D> extends a<D> {

    /* GridListAdapter */
    public static class a {
        private TextView a;
        private TextView b;
        private ImageView c;
        private ImageView d;
        private Animation e;
        private View f;
        private View g;

        public a(View view) {
            this.f = view;
            this.a = (TextView) view.findViewById(R.id.grid_view_item_name);
            this.b = (TextView) view.findViewById(R.id.grid_view_item_num);
            this.c = (ImageView) view.findViewById(R.id.grid_view_item_image);
            this.d = (ImageView) view.findViewById(R.id.grid_view_item_play_icon_back);
            this.e = (Animation) view.findViewById(R.id.grid_view_item_play_icon);
            this.g = view.findViewById(R.id.grid_view_item_mask);
        }

        public View a() {
            return this.f;
        }

        public TextView b() {
            return this.a;
        }

        public TextView c() {
            return this.b;
        }

        public ImageView d() {
            return this.c;
        }

        public Animation e() {
            return this.e;
        }

        public ImageView f() {
            return this.d;
        }

        public View g() {
            return this.g;
        }
    }

    protected abstract String b(D d);

    protected abstract String c(D d);

    protected abstract String d(D d);

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.find_song_with_num_grid_list_view_item, viewGroup, false);
        inflate.setTag(new a(inflate));
        return inflate;
    }

    protected void a(View view, D d, int i) {
        Object obj = this.d.get(i);
        view.setTag(R.id.view_bind_data, obj);
        a aVar = (a) view.getTag();
        aVar.a.setText(b(obj));
        aVar.b.setText(c(obj));
        a(aVar.e, obj);
        g.a(aVar.c, d(obj), com.sds.android.ttpod.common.c.a.d() / 2, com.sds.android.ttpod.common.c.a.e() / 4, (int) R.drawable.img_music_default_icon);
    }

    protected void a(Animation animation, D d) {
    }

    protected void a(a aVar) {
        com.sds.android.ttpod.framework.modules.theme.c.a(aVar.g(), ThemeElement.TILE_BACKGROUND);
        v.a(aVar.b(), ThemeElement.TILE_TEXT, ThemeElement.HOME_TEXT);
        v.a(aVar.c(), ThemeElement.TILE_SUB_TEXT, ThemeElement.HOME_SUB_TEXT);
    }
}
