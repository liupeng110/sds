package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.b;

public class HeaderFooterGridView extends LinearLayout implements b {
    private ImageView a;
    private ImageView b;
    private GridView c;
    private boolean d = false;
    private OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ HeaderFooterGridView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view != null) {
                this.a.c.smoothScrollToPosition(this.a.c.getTop());
            }
        }
    };
    private OnClickListener f = new OnClickListener(this) {
        final /* synthetic */ HeaderFooterGridView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view != null) {
                this.a.c.smoothScrollToPosition(this.a.c.getBottom());
            }
        }
    };

    public HeaderFooterGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.header_footer_gridview, this);
        this.a = (ImageView) findViewById(R.id.img_right_menu_arrow_up);
        this.b = (ImageView) findViewById(R.id.img_right_menu_arrow_down);
        this.c = (GridView) findViewById(R.id.gridview);
        if (j.a()) {
            this.a.setOnClickListener(this.e);
            this.b.setOnClickListener(this.f);
            this.c.setOnScrollListener(new OnScrollListener(this) {
                final /* synthetic */ HeaderFooterGridView a;

                {
                    this.a = r1;
                }

                public void onScrollStateChanged(AbsListView absListView, int i) {
                    int firstVisiblePosition = absListView.getFirstVisiblePosition();
                    int childCount = absListView.getChildCount();
                    int count = this.a.c.getCount();
                    if (childCount != 0) {
                        if (this.a.c.getBottom() - this.a.c.getTop() >= absListView.getChildAt(childCount - 1).getBottom() - absListView.getChildAt(0).getTop()) {
                            this.a.a.setVisibility(4);
                            this.a.b.setVisibility(4);
                            return;
                        }
                        if (firstVisiblePosition == 0 && this.a.d && absListView.getChildAt(0).getTop() >= 0) {
                            this.a.d = false;
                        }
                        if (firstVisiblePosition + childCount == count && !this.a.d && absListView.getChildAt(childCount - 1).getBottom() <= this.a.c.getBottom() - this.a.c.getTop()) {
                            this.a.d = true;
                        }
                        if (this.a.d) {
                            this.a.a.setVisibility(0);
                            this.a.b.setVisibility(4);
                            return;
                        }
                        this.a.a.setVisibility(4);
                        this.a.b.setVisibility(0);
                    }
                }

                public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                    onScrollStateChanged(absListView, 2);
                }
            });
            return;
        }
        this.a.setVisibility(8);
        this.b.setVisibility(8);
    }

    public GridView getGridView() {
        return this.c;
    }

    public void onThemeLoaded() {
        c.a(this.a, ThemeElement.SETTING_ARROW_UP_IMAGE);
        c.a(this.b, ThemeElement.SETTING_ARROW_DOWN_IMAGE);
    }
}
