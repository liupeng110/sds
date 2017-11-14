package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class CheckImageView extends ImageView {
    private boolean a;
    private a b;
    private Drawable c;
    private Drawable d;
    private OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ CheckImageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.a(!this.a.a, true);
        }
    };

    public interface a {
        void a(CheckImageView checkImageView, boolean z, boolean z2);
    }

    public CheckImageView(Context context) {
        super(context);
        setOnClickListener(this.e);
    }

    public CheckImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(this.e);
    }

    public CheckImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOnClickListener(this.e);
    }

    public void a(int i, int i2) {
        if (i2 != 0) {
            this.d = getContext().getResources().getDrawable(i2);
        } else {
            this.d = null;
        }
        if (i != 0) {
            this.c = getContext().getResources().getDrawable(i);
        } else {
            this.c = this.d;
        }
        setImageDrawable(this.a ? this.d : this.c);
    }

    public boolean a() {
        return this.a;
    }

    public void setChecked(boolean z) {
        a(z, false);
    }

    private void a(boolean z, boolean z2) {
        if (this.a != z) {
            this.a = z;
            if (this.b != null) {
                this.b.a(this, this.a, z2);
            }
            setImageDrawable(this.a ? this.d : this.c);
        }
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.b = aVar;
    }
}
