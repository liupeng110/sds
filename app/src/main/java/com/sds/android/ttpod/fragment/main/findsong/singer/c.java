package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.b.x;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.f;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import java.util.ArrayList;
import java.util.List;

/* SingerMvListAdapter */
public class c extends BaseAdapter {
    private List<MvData> a;
    private LayoutInflater b;
    private Context c;
    private boolean d;

    /* SingerMvListAdapter */
    private static class a {
        private ImageView a;
        private TextView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private IconTextView h;
        private IconTextView i;
        private View j;

        public a(View view) {
            this.j = view;
            this.a = (ImageView) view.findViewById(R.id.image_mv);
            this.c = (TextView) view.findViewById(R.id.text_mv_name);
            this.d = (TextView) view.findViewById(R.id.mv_duration);
            this.e = (TextView) view.findViewById(R.id.text_mv_singer_name);
            this.f = (TextView) view.findViewById(R.id.mv_play_count);
            this.g = (TextView) view.findViewById(R.id.mv_comment_count);
            this.b = (TextView) view.findViewById(R.id.text_mv_type);
            this.h = (IconTextView) view.findViewById(R.id.icon_text_play_count);
            this.i = (IconTextView) view.findViewById(R.id.icon_text_bullet);
            this.j.setTag(this);
        }

        public void a(MvData mvData) {
            this.j.setTag(R.id.tag_view_key, mvData);
            if (!j.a(mvData.getMvList())) {
                MvListItem mvListItem = (MvListItem) mvData.getMvList().get(0);
                g.a(this.a, mvListItem.getPicUrl(), 0, 0, (int) R.drawable.img_mv_default_image);
                a(mvListItem);
                this.d.setText(String.valueOf(com.sds.android.sdk.lib.util.c.a(mvListItem.getDuration())));
            }
            this.c.setText(mvData.getName());
            this.e.setText(mvData.getSingerName());
            this.f.setText(f.a(mvData.getPlayCount()));
            this.g.setText(f.a(mvData.getDanmakuCount()));
        }

        private void a(MvListItem mvListItem) {
            if (mvListItem.getType() > 0) {
                this.b.setVisibility(0);
                this.b.setText(x.a(mvListItem.getType()).getMvQuality());
                return;
            }
            this.b.setVisibility(8);
        }

        public void a() {
            com.sds.android.ttpod.framework.modules.theme.c.a(this.c, ThemeElement.SONG_LIST_ITEM_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.e, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.f, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.g, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.j, ThemeElement.TILE_BACKGROUND);
            v.a(this.h, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            v.a(this.i, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        }
    }

    public c(Context context) {
        this.a = new ArrayList();
        this.a = new ArrayList();
        this.b = LayoutInflater.from(context);
        this.c = context;
        this.d = true;
    }

    public int getCount() {
        return this.a.size();
    }

    public void a(List<MvData> list) {
        if (list != null) {
            this.a = new ArrayList(list);
            notifyDataSetChanged();
        }
    }

    public void b(List<MvData> list) {
        if (!j.a(list)) {
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void a(boolean z) {
        this.d = z;
    }

    public Object getItem(int i) {
        return this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.b.inflate(R.layout.layout_mv_list_item, viewGroup, false);
            aVar = new a(view);
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a((MvData) this.a.get(i));
        if (this.d) {
            aVar.a();
        } else {
            view.setBackgroundResource(R.drawable.xml_background_mv_list_item);
        }
        return view;
    }
}
