package com.sds.android.ttpod.cmmusic.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.ttpod.cmmusic.R;
import java.util.ArrayList;
import java.util.HashMap;

/* SearchRecommendAdapter */
public class d extends BaseAdapter {
    private static LayoutInflater b = null;
    private ArrayList<HashMap<String, String>> a;
    private TextView c;

    public d(ArrayList<HashMap<String, String>> arrayList, Context context) {
        this.a = arrayList;
        b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public Object getItem(int i) {
        return this.a == null ? null : (HashMap) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = b.inflate(R.layout.cmmusic_recommend_search_key_list_row, null);
        }
        this.c = (TextView) view.findViewById(R.id.text_recommend_searchkey);
        this.c.setText((String) ((HashMap) this.a.get(i)).get("recommendKey"));
        return view;
    }
}
