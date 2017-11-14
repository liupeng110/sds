package com.sds.android.ttpod.widget.mediamenu;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class MediaMenuLayout<T> extends LinearLayout {
    protected List<a> a;
    protected T b;
    private SparseArray<View> c = new SparseArray(5);
    private SparseArray<View> d = new SparseArray();

    public abstract List<a> b();

    public MediaMenuLayout(Context context) {
        super(context);
        a(context);
    }

    public MediaMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        setOrientation(0);
        setGravity(17);
        a(b());
    }

    private void a(List<a> list) {
        int size = list == null ? 0 : list.size();
        this.a = new ArrayList();
        Collections.sort(list, new Comparator<a>(this) {
            final /* synthetic */ MediaMenuLayout a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((a) obj, (a) obj2);
            }

            public int a(a aVar, a aVar2) {
                return aVar.a(aVar2);
            }
        });
        if (size > 0) {
            this.a.addAll(list);
            c();
        }
    }

    private void c() {
        for (a aVar : this.a) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_media_menu_item, this, false);
            IconTextView iconTextView = (IconTextView) inflate.findViewById(R.id.itv_media_menu_icon);
            TextView textView = (TextView) inflate.findViewById(R.id.tv_media_menu_text);
            if (aVar.c() > 0) {
                iconTextView.setCheckable(true);
                iconTextView.a(aVar.b(), aVar.c());
            } else {
                iconTextView.setText(aVar.b());
            }
            iconTextView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setText(aVar.d());
            textView.setId(aVar.a());
            textView.setTag(aVar);
            if (aVar.a() == R.id.media_menu_favor) {
                iconTextView.setId(R.id.media_menu_favor_icon);
                this.c.put(R.id.media_menu_favor_icon, iconTextView);
            }
            addView(inflate);
            if (getChildCount() > 5) {
                inflate.setVisibility(8);
            }
            this.c.put(aVar.a(), inflate);
            this.d.put(aVar.a(), textView);
        }
    }

    protected void a() {
    }

    public void setData(T t) {
        this.b = t;
        a();
    }

    public void a(int i, boolean z) {
        int i2 = z ? 0 : 8;
        View view = (View) this.c.get(i);
        if (view != null && view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
    }

    public View a(int i) {
        return (View) this.c.get(i);
    }

    public void setMenuItemClickListener(OnClickListener onClickListener) {
        for (int i = 0; i < this.d.size(); i++) {
            a(this.d.keyAt(i), onClickListener);
        }
    }

    public void a(int i, OnClickListener onClickListener) {
        View view = (View) this.d.get(i);
        if (view != null) {
            OnClickListener e = ((a) view.getTag()).e();
            if (e != null) {
                onClickListener = e;
            }
            view.setOnClickListener(onClickListener);
        }
    }
}
