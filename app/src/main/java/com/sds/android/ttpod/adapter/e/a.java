package com.sds.android.ttpod.adapter.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.util.ArrayList;
import java.util.List;

/* AlbumListAdapter */
public class a extends BaseAdapter {
    private List<AlbumItem> a;
    private Context b;
    private b c;

    /* AlbumListAdapter */
    private class a {
        final /* synthetic */ a a;
        private View b;
        private TextView c;
        private TextView d;
        private ImageView e;

        public a(a aVar, View view) {
            this.a = aVar;
            this.b = view;
            this.c = (TextView) view.findViewById(R.id.item_name);
            this.d = (TextView) view.findViewById(R.id.item_artist);
            this.e = (ImageView) view.findViewById(R.id.item_picture);
        }

        public void a() {
            c.a(this.c, ThemeElement.COMMON_TITLE_TEXT);
            c.a(this.d, ThemeElement.COMMON_CONTENT_TEXT);
        }
    }

    /* AlbumListAdapter */
    public interface b {
        void a(int i);
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context) {
        this.b = context;
    }

    public List<AlbumItem> a() {
        return this.a;
    }

    public void a(List<AlbumItem> list) {
        this.a = new ArrayList(list);
    }

    public int getCount() {
        return j.a(this.a) ? 0 : (int) Math.ceil(((double) this.a.size()) / 2.0d);
    }

    public AlbumItem a(int i) {
        if (!j.b(this.a) || i >= this.a.size()) {
            return null;
        }
        return (AlbumItem) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a[] aVarArr;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.new_disc_list_item, viewGroup, false);
            Object obj = new a[]{new a(this, view.findViewById(R.id.song_item1)), new a(this, view.findViewById(R.id.song_item2))};
            view.setTag(obj);
            aVarArr = obj;
        } else {
            aVarArr = (a[]) view.getTag();
        }
        int i2 = i * 2;
        int i3 = i2 + 1;
        AlbumItem albumItem = (AlbumItem) a().get(i2);
        AlbumItem albumItem2 = null;
        if (i3 < a().size()) {
            albumItem2 = (AlbumItem) a().get(i3);
        }
        a(aVarArr[0], albumItem, i2);
        a(aVarArr[1], albumItem2, i3);
        return view;
    }

    private void a(a aVar, AlbumItem albumItem, final int i) {
        if (albumItem == null) {
            a(aVar, false);
            return;
        }
        a(aVar, true);
        int d = com.sds.android.ttpod.common.c.a.d() / 2;
        g.a(aVar.e, albumItem.getPicUrl(), d, d, (int) R.drawable.img_album_list_item_cover_default);
        aVar.c.setText(albumItem.getName());
        aVar.d.setText(albumItem.getPublishDate());
        aVar.b.setTag(albumItem.getName());
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                if (this.b.c != null) {
                    this.b.c.a(i);
                }
            }
        });
        aVar.a();
    }

    private void a(a aVar, boolean z) {
        if (aVar != null) {
            int i = z ? 0 : 4;
            aVar.b.setVisibility(i);
            aVar.c.setVisibility(i);
            aVar.d.setVisibility(i);
            aVar.e.setVisibility(i);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
