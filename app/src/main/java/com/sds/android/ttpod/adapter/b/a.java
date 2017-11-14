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

/* AudioEffectAdapter */
public class a extends BaseAdapter {
    private List<String> a;
    private Context b;
    private String c;

    /* AudioEffectAdapter */
    private class a {
        final /* synthetic */ a a;
        private TextView b;
        private ImageView c;
        private View d;

        public a(a aVar, View view) {
            this.a = aVar;
            this.b = (TextView) view.findViewById(R.id.textview_title);
            this.c = (ImageView) view.findViewById(R.id.imageview_state);
            this.d = view.findViewById(R.id.view_state_effect);
        }
    }

    public a(Context context, List<String> list, String str) {
        this.a = list;
        this.b = context;
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
            view = LayoutInflater.from(this.b).inflate(R.layout.audio_effect_listview_item, null, false);
            view.setTag(new a(this, view));
        }
        a(view, i);
        return view;
    }

    private void a(View view, int i) {
        int i2 = 0;
        a aVar = (a) view.getTag();
        String str = (String) this.a.get(i);
        boolean equals = this.c.equals(str);
        aVar.b.setText(str);
        aVar.c.setVisibility(equals ? 0 : 8);
        view.setEnabled(equals);
        aVar.b.setEnabled(equals);
        View c = aVar.d;
        if (!equals) {
            i2 = 8;
        }
        c.setVisibility(i2);
    }
}
