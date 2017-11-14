package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.location.BDLocation;

public class TTPodButton extends MaskImageView {
    private long a;
    private int b;
    private a c;
    private long d = 100;
    private Runnable e = new Runnable(this) {
        final /* synthetic */ TTPodButton a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a(false);
            if (this.a.isPressed()) {
                this.a.postDelayed(this, this.a.d);
            }
        }
    };

    public interface a {
        void a(View view, long j, int i);
    }

    public TTPodButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TTPodButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TTPodButton(Context context) {
        super(context);
    }

    public void a(a aVar, long j) {
        this.c = aVar;
        this.d = j;
        if (this.c != null) {
            super.setLongClickable(true);
        }
    }

    public void setRepeatListener(a aVar) {
        a(aVar, 100);
    }

    public boolean performLongClick() {
        this.a = SystemClock.elapsedRealtime();
        this.b = 0;
        post(this.e);
        return true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3) {
            removeCallbacks(this.e);
            if (this.a != 0) {
                a(true);
                this.a = 0;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case BDLocation.TypeOffLineLocation /*66*/:
                super.onKeyDown(i, keyEvent);
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case BDLocation.TypeOffLineLocation /*66*/:
                removeCallbacks(this.e);
                if (this.a != 0) {
                    a(true);
                    this.a = 0;
                    break;
                }
                break;
        }
        return super.onKeyUp(i, keyEvent);
    }

    private void a(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.c != null) {
            int i;
            a aVar = this.c;
            long j = elapsedRealtime - this.a;
            if (z) {
                i = -1;
            } else {
                i = this.b;
                this.b = i + 1;
            }
            aVar.a(this, j, i);
        }
    }

    public long getRepeatInterval() {
        return this.d;
    }
}
