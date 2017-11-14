package com.sds.android.ttpod.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.framework.a.b.d.n;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;

/* ListViewPager */
public class c extends q {
    private ListView a;
    private NetworkLoadView b;
    private DataListFooterView c;
    private c d;
    private b e = b.IDLE;
    private int f;
    private boolean g = true;
    private a h;
    private String i = "";
    private long j;
    private com.sds.android.ttpod.widget.NetworkLoadView.b k = new com.sds.android.ttpod.widget.NetworkLoadView.b(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.j = System.currentTimeMillis();
            this.a.c.c();
            this.a.c(1);
            this.a.e = b.FIRST_LOAD;
            this.a.d.requestPageData(this.a.a());
        }
    };
    private OnScrollListener l = new OnScrollListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (m.b(i, i2, i3) && this.a.e == b.IDLE && this.a.m() > 0) {
                this.a.k();
            }
        }
    };

    /* ListViewPager */
    public interface c {
        void requestPageData(int i);
    }

    /* ListViewPager */
    public interface a {
        void a(int i);
    }

    /* ListViewPager */
    public enum b {
        IDLE,
        FIRST_LOAD,
        NEXT_PAGE
    }

    public c(ListView listView, NetworkLoadView networkLoadView, c cVar) {
        this.a = listView;
        this.b = networkLoadView;
        this.d = cVar;
        this.b.setOnStartLoadingListener(this.k);
        this.c = new DataListFooterView(this.a.getContext());
        this.a.addFooterView(this.c);
        ViewCompat.setOverScrollMode(this.a, 2);
        this.a.setOnScrollListener(this.l);
        this.f = R.string.count_of_media;
        this.h = new a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.f(i);
            }
        };
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public void e(int i) {
        this.f = i;
    }

    public void a(BaseAdapter baseAdapter) {
        this.a.setAdapter(baseAdapter);
    }

    public boolean i() {
        return this.g;
    }

    public void j() {
        this.b.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
    }

    public void k() {
        this.j = System.currentTimeMillis();
        if (a() >= g()) {
            this.h.a(m());
            return;
        }
        if (!d(d())) {
            e();
            this.c.a();
        }
        this.e = b.NEXT_PAGE;
        this.d.requestPageData(a());
    }

    public void a(boolean z, boolean z2) {
        n.a(System.currentTimeMillis() - this.j);
        this.g = b.FIRST_LOAD == this.e;
        this.e = b.IDLE;
        if (this.g) {
            if (z2) {
                this.b.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                if (z) {
                    this.b.setLoadingEmptyText(this.i);
                }
            } else if (z) {
                this.b.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
            }
        } else if (!z) {
            this.c.b();
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.c.a();
                    this.a.d.requestPageData(this.a.a());
                }
            });
        } else if (z2) {
            this.h.a(m());
        }
    }

    public void a(String str) {
        this.i = str;
    }

    public void f(int i) {
        if (this.c != null && this.a != null) {
            this.c.a(this.a.getContext().getString(this.f, new Object[]{Integer.valueOf(i)}));
        }
    }

    private int m() {
        if (this.a.getAdapter() != null) {
            return (this.a.getAdapter().getCount() - this.a.getHeaderViewsCount()) - this.a.getFooterViewsCount();
        }
        return 0;
    }

    public void l() {
        this.b.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.c, ThemeElement.SUB_BAR_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.c, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
    }
}
