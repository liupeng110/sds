package com.sds.android.ttpod.cmmusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.sds.android.ttpod.cmmusic.R;

public class LoadMoreListView extends ListView {
    private static View a;
    private OnScrollListener b;
    private boolean c;
    private int d;
    private OnScrollListener e = new OnScrollListener(this) {
        final /* synthetic */ LoadMoreListView a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.a.d = i;
            if (this.a.b != null) {
                this.a.b.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (this.a.b != null) {
                this.a.b.onScroll(absListView, i, i2, i3);
            }
            if (i2 == i3) {
                this.a.a();
            } else if (!this.a.c && i + i2 >= i3 && this.a.d != 0) {
                this.a.b();
                this.a.c = true;
            }
        }
    };

    public LoadMoreListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public LoadMoreListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LoadMoreListView(Context context) {
        super(context);
        a(context);
    }

    public static View getFooterView() {
        return a;
    }

    private void a(Context context) {
        a = View.inflate(context, R.layout.cmmusic_list_foodview, null);
        addFooterView(a);
        a();
        super.setOnScrollListener(this.e);
    }

    private void a() {
        a.setVisibility(8);
    }

    private void b() {
        a.setVisibility(0);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.b = onScrollListener;
    }
}
