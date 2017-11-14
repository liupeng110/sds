package com.sds.android.ttpod.adapter.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.ISinger;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import java.util.ArrayList;
import java.util.List;

/* SingerListAdapter */
public class c<SINGER extends ISinger> extends BaseAdapter {
    private List<SINGER> a;
    private Context b;

    /* SingerListAdapter */
    private final class a {
        final /* synthetic */ c a;
        private View b;
        private TextView c;
        private TextView d;
        private ImageView e;
        private IconTextView f;

        private a(c cVar, View view) {
            this.a = cVar;
            this.b = view;
            this.c = (TextView) view.findViewById(R.id.tv_title);
            this.d = (TextView) view.findViewById(R.id.tv_subtitle);
            this.e = (ImageView) view.findViewById(R.id.iv_singer);
            this.f = (IconTextView) view.findViewById(R.id.itv_arrow);
        }

        private void a() {
            com.sds.android.ttpod.framework.modules.theme.c.a(this.d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.c, ThemeElement.SONG_LIST_ITEM_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.b, ThemeElement.TILE_BACKGROUND);
            v.a(this.f, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        }

        private void a(SINGER singer) {
            g.a(this.e, singer.getPicUrl(), com.sds.android.ttpod.common.c.a.a(65), com.sds.android.ttpod.common.c.a.a(65), (int) R.drawable.img_background_song_publish);
            this.c.setText(singer.getSingerName());
            this.d.setText(this.a.b.getString(R.string.singer_song_album_num, new Object[]{Integer.valueOf(singer.getSongCount()), Integer.valueOf(singer.getAlbumCount())}));
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public c(Context context) {
        this.b = context;
    }

    public void a(List<SINGER> list) {
        this.a = new ArrayList(list);
        notifyDataSetChanged();
    }

    public void b(List<SINGER> list) {
        if (this.a == null) {
            a((List) list);
            return;
        }
        this.a.addAll(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        return j.a(this.a) ? 0 : this.a.size();
    }

    public SINGER a(int i) {
        if (!j.b(this.a) || i >= this.a.size()) {
            return null;
        }
        return (ISinger) this.a.get(i);
    }

    public long getItemId(int i) {
        ISinger a = a(i);
        return a != null ? a.getSingerId() : 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.singer_list_view_item, null, false);
            a aVar2 = new a(view);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a((ISinger) this.a.get(i));
        aVar.a();
        return view;
    }
}
