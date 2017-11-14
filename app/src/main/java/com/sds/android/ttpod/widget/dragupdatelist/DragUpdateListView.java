package com.sds.android.ttpod.widget.dragupdatelist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.ttpod.widget.dragupdatelist.a.b;
import com.sds.android.ttpod.widget.dragupdatelist.a.c;

public class DragUpdateListView extends ListView {
    private a a;
    private boolean b;
    private b c;

    public DragUpdateListView(Context context) {
        this(context, null);
    }

    public DragUpdateListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DragUpdateListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = true;
        this.c = new b(this) {
            final /* synthetic */ DragUpdateListView a;

            {
                this.a = r1;
            }

            public boolean a() {
                return this.a.getFirstVisiblePosition() == 0;
            }

            public void b() {
                this.a.setSelection(0);
            }

            public void a(View view) {
                this.a.addHeaderView(view);
            }

            public void c() {
            }
        };
        this.a = new a();
        this.a.a(context, this.c);
        this.a.c();
    }

    public void setEnableDragUpdate(boolean z) {
        this.b = z;
    }

    public TextView getTitleTextView() {
        return this.a.e();
    }

    public TextView getContentTextView() {
        return this.a.f();
    }

    public void a() {
        this.a.a();
    }

    public void b() {
        this.a.g();
    }

    public void setOnStartRefreshListener(c cVar) {
        this.a.a(cVar);
    }

    public void setLoadingTitleColor(ColorStateList colorStateList) {
        this.a.a(colorStateList);
    }

    public void c() {
        this.a.b();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            this.a.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
}
