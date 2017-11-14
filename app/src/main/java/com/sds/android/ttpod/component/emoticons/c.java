package com.sds.android.ttpod.component.emoticons;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.sds.android.ttpod.R;
import java.util.List;

/* EmoticonsAdapter */
public class c extends BaseAdapter {
    private List<a> a;
    private LayoutInflater b;
    private int c = 0;

    public c(Context context, List<a> list) {
        this.b = LayoutInflater.from(context);
        this.a = list;
        this.c = list.size();
    }

    public int getCount() {
        return this.c;
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        a aVar = (a) this.a.get(i);
        if (view == null) {
            view = this.b.inflate(R.layout.musiccircle_emoticons_item, null);
            imageView = (ImageView) view.findViewById(R.id.item_iv_face);
            view.setTag(imageView);
        } else {
            imageView = (ImageView) view.getTag();
        }
        if (aVar.b() == R.drawable.xml_musiccircle_emoticons_delete) {
            view.setBackgroundDrawable(null);
            imageView.setImageResource(aVar.b());
        } else if (TextUtils.isEmpty(aVar.a())) {
            view.setBackgroundDrawable(null);
            imageView.setImageDrawable(null);
        } else {
            imageView.setTag(aVar);
            imageView.setImageResource(aVar.b());
        }
        return view;
    }
}
