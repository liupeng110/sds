package com.sds.android.ttpod.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.util.ArrayList;

/* FindSongGridSectionListAdapter */
public abstract class b<D> extends d {
    private ArrayList<a<D>> b = new ArrayList();
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            Object tag = view.getTag(R.id.view_bind_data);
            if (tag != null) {
                this.a.c(tag);
            }
        }
    };

    /* FindSongGridSectionListAdapter */
    public static class a<D> {
        private String a;
        private int b;
        private long c;
        private ArrayList<D> d;

        public a(String str, int i, long j, ArrayList<D> arrayList) {
            this.a = str;
            this.d = arrayList;
            this.c = j;
            this.b = i;
        }

        public int a() {
            return this.b;
        }

        public long b() {
            return this.c;
        }

        public String c() {
            return this.a;
        }

        public ArrayList<D> d() {
            return this.d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.a != null) {
                if (this.a.equals(aVar.a)) {
                    return true;
                }
            } else if (aVar.a == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.a != null ? this.a.hashCode() : 0;
        }
    }

    protected abstract String a(D d);

    protected abstract String b(D d);

    protected abstract void c(D d);

    protected /* synthetic */ Object c(int i) {
        return b(i);
    }

    public void a(ArrayList<a<D>> arrayList) {
        this.b = arrayList;
        notifyDataSetChanged();
    }

    protected int a() {
        return this.b.size();
    }

    public void notifyDataSetChanged() {
        b();
        super.notifyDataSetChanged();
    }

    protected int a(int i) {
        if (i < a()) {
            a aVar = (a) this.b.get(i);
            if (aVar != null) {
                ArrayList d = aVar.d();
                if (d != null) {
                    return (int) Math.ceil(((double) d.size()) / 2.0d);
                }
            }
        }
        return 0;
    }

    protected D a(int i, int i2) {
        if (i < a()) {
            a aVar = (a) this.b.get(i);
            if (aVar != null) {
                ArrayList d = aVar.d();
                if (d != null && i2 < d.size()) {
                    return d.get(i2);
                }
            }
        }
        return null;
    }

    protected a b(int i) {
        return (a) this.b.get(i);
    }

    protected View a(ViewGroup viewGroup) {
        return this.a.inflate(R.layout.findsong_grid_section_view, viewGroup, false);
    }

    protected View b(ViewGroup viewGroup) {
        View inflate = this.a.inflate(R.layout.findsong_grid_section_subitem_view, viewGroup, false);
        v.a(new com.sds.android.ttpod.adapter.c.a(inflate.findViewById(R.id.song_item1)).g(), ThemeElement.TILE_BACKGROUND, ThemeElement.HOME_BACKGROUND);
        inflate.setTag(new com.sds.android.ttpod.adapter.c.a[]{r1, new com.sds.android.ttpod.adapter.c.a(inflate.findViewById(R.id.song_item2))});
        v.a(r2.g(), ThemeElement.TILE_BACKGROUND, ThemeElement.HOME_BACKGROUND);
        return inflate;
    }

    private void a(com.sds.android.ttpod.adapter.c.a aVar) {
        v.a(aVar.b(), ThemeElement.TILE_TEXT, ThemeElement.HOME_TEXT);
        v.a(aVar.c(), ThemeElement.TILE_SUB_TEXT, ThemeElement.HOME_SUB_TEXT);
    }

    protected void a(int i, View view) {
        View view2 = (TextView) view.findViewById(R.id.find_song_section_title);
        view2.setText(b(i).c());
        c.a(view2, ThemeElement.SUB_BAR_TEXT);
        c.a(view2, ThemeElement.TILE_MASK);
    }

    protected void a(int i, int i2, View view) {
        Object a = a(i, i2 * 2);
        Object a2 = a(i, (i2 * 2) + 1);
        Object tag = view.getTag();
        if (tag != null) {
            com.sds.android.ttpod.adapter.c.a[] aVarArr = (com.sds.android.ttpod.adapter.c.a[]) tag;
            a(aVarArr[0], a);
            a(aVarArr[1], a2);
            a(aVarArr[0]);
            a(aVarArr[1]);
        }
    }

    protected void a(com.sds.android.ttpod.adapter.c.a aVar, D d) {
        if (d != null) {
            aVar.a().setVisibility(0);
            aVar.b().setText(a((Object) d));
            aVar.c().setText(b((Object) d));
            b(aVar, d);
            aVar.a().setTag(R.id.view_bind_data, d);
            aVar.a().setOnClickListener(this.c);
            return;
        }
        aVar.a().setVisibility(4);
    }

    protected void b(com.sds.android.ttpod.adapter.c.a aVar, D d) {
    }
}
