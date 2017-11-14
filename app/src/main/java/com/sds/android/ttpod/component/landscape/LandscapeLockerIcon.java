package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.ttpod.framework.modules.skin.view.Icon;

public class LandscapeLockerIcon extends Icon {

    public interface a {
        void a(int i);
    }

    public LandscapeLockerIcon(Context context) {
        super(context);
    }

    public LandscapeLockerIcon(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LandscapeLockerIcon(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void a(Drawable drawable, Drawable drawable2) {
        if (drawable != null && drawable2 != null) {
            a(0, drawable);
            a(1, drawable2);
        }
    }

    public void a() {
        setState(1);
    }

    public void setOnLockerStateChangeListener(final a aVar) {
        if (aVar != null) {
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LandscapeLockerIcon b;

                public void onClick(View view) {
                    if (this.b.getState() == 0) {
                        aVar.a(1);
                        this.b.setState(1);
                        return;
                    }
                    aVar.a(0);
                    this.b.setState(0);
                }
            });
        } else {
            setOnClickListener(null);
        }
    }
}
