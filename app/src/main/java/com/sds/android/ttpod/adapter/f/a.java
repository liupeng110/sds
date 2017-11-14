package com.sds.android.ttpod.adapter.f;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

/* SearchHistoryAdapter */
public class a extends BaseAdapter {
    private com.sds.android.ttpod.component.e.a a;
    private a b;

    /* SearchHistoryAdapter */
    public interface a {
        void a(String str);
    }

    /* SearchHistoryAdapter */
    private final class b {
        final /* synthetic */ a a;
        private TextView b;
        private IconTextView c;
        private View d;

        b(a aVar, IconTextView iconTextView, View view, TextView textView) {
            this.a = aVar;
            this.c = iconTextView;
            this.b = textView;
            this.d = view;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(com.sds.android.ttpod.component.e.a aVar) {
        this.a = aVar;
    }

    public int getCount() {
        return this.a != null ? this.a.c() : 0;
    }

    public String a(int i) {
        return (String) this.a.b().get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            view = LayoutInflater.from(BaseApplication.e()).inflate(R.layout.online_search_history_item, viewGroup, false);
            b bVar2 = new b(this, (IconTextView) view.findViewById(R.id.search_history_clear_ic), view.findViewById(R.id.layout_search_history_clear), (TextView) view.findViewById(R.id.search_history_title));
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        bVar.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.b != null) {
                    this.a.b.a((String) view.getTag());
                }
            }
        });
        bVar.d.setTag(this.a.b().get(i));
        bVar.b.setText((CharSequence) this.a.b().get(i));
        c.a(bVar.b, ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        v.a(bVar.c, ThemeElement.SONG_LIST_ITEM_TEXT);
        return view;
    }

    public void a(a aVar) {
        this.b = aVar;
    }
}
