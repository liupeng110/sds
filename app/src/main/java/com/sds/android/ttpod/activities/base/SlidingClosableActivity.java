package com.sds.android.ttpod.activities.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.a;

public class SlidingClosableActivity extends ActionBarActivity {
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSlidingClosableRelativeLayout = new SlidingClosableRelativeLayout(this);
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(3);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(new a(this) {
            final /* synthetic */ SlidingClosableActivity a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.mSlidingClosableRelativeLayout != null) {
                    this.a.mSlidingClosableRelativeLayout.setVisibility(8);
                    super.finish();
                    this.a.overridePendingTransition(0, 0);
                    return;
                }
                super.finish();
            }
        });
    }

    protected View buildContentView(View view) {
        this.mSlidingClosableRelativeLayout.addView(super.buildContentView(view), new LayoutParams(-1, -1));
        return this.mSlidingClosableRelativeLayout;
    }

    public SlidingClosableRelativeLayout getSlidingContainer() {
        return this.mSlidingClosableRelativeLayout;
    }

    public void setSlidingCloseMode(int i) {
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(i);
    }

    protected boolean needFinishAnimation() {
        return true;
    }

    public void finish() {
        if (this.mSlidingClosableRelativeLayout == null || !needFinishAnimation()) {
            super.finish();
            return;
        }
        setSlidingCloseMode(2);
        this.mSlidingClosableRelativeLayout.a(true);
    }
}
