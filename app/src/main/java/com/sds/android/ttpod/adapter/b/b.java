package com.sds.android.ttpod.adapter.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import java.util.List;

/* AudioEffectGridAdapter */
public class b extends BaseAdapter {
    private List<com.sds.android.ttpod.component.b.b> a;
    private Context b;
    private String c;

    /* AudioEffectGridAdapter */
    private class a {
        final /* synthetic */ b a;
        private ImageView b;
        private TextView c;
        private ImageView d;

        public a(b bVar, View view) {
            this.a = bVar;
            this.b = (ImageView) view.findViewById(R.id.imageview_icon);
            this.c = (TextView) view.findViewById(R.id.textview_title);
            this.d = (ImageView) view.findViewById(R.id.audio_effect_gridview_selector_state);
        }
    }

    public b(Context context, List<com.sds.android.ttpod.component.b.b> list, String str) {
        this.b = context;
        this.a = list;
        this.c = str;
    }

    public void a(String str) {
        if (!this.c.equals(str)) {
            this.c = str;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.a.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.audio_effect_gridview_item, null, false);
            view.setTag(new a(this, view));
        }
        a(view, i);
        return view;
    }

    public void a(View view, int i) {
        a aVar = (a) view.getTag();
        com.sds.android.ttpod.component.b.b bVar = (com.sds.android.ttpod.component.b.b) this.a.get(i);
        aVar.b.setImageResource(bVar.a());
        if (this.c == null || !this.c.equals(bVar.c())) {
            aVar.d.setImageResource(R.drawable.img_audio_effect_reverb_grivd_view_unselector);
        } else {
            aVar.d.setImageResource(R.drawable.img_audio_effect_reverb_grivd_view_selector);
        }
        aVar.c.setText(bVar.b());
        view.setTag(R.id.view_bind_data, bVar.c());
    }
}
