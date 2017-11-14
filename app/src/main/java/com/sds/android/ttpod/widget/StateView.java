package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class StateView extends FrameLayout implements com.sds.android.ttpod.framework.modules.theme.c.b {
    private View a;
    private ViewGroup b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private Animation g;
    private a h;
    private OnClickListener i;

    public interface a {
        void onRetryRequested();
    }

    public enum b {
        LOADING,
        SUCCESS,
        FAILED,
        NO_DATA
    }

    public StateView(Context context) {
        this(context, null);
    }

    public StateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new OnClickListener(this) {
            final /* synthetic */ StateView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.h != null) {
                    this.a.h.onRetryRequested();
                }
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StateView);
        int resourceId = obtainStyledAttributes.getResourceId(2, -1);
        int resourceId2 = obtainStyledAttributes.getResourceId(1, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(0, R.layout.loadingview_loading);
        int resourceId4 = obtainStyledAttributes.getResourceId(3, -1);
        LayoutInflater from = LayoutInflater.from(context);
        if (resourceId != -1) {
            this.c = from.inflate(resourceId, null);
            addView(this.c, new LayoutParams(-1, -1));
            this.c.setOnClickListener(this.i);
        }
        if (resourceId2 != -1) {
            this.b = (ViewGroup) from.inflate(resourceId2, null);
            addView(this.b, new LayoutParams(-1, -1));
        }
        if (resourceId3 != -1) {
            this.a = from.inflate(resourceId3, null);
            addView(this.a, new LayoutParams(-1, -1));
        }
        if (resourceId4 != -1) {
            this.d = from.inflate(resourceId4, null);
            addView(this.d, new LayoutParams(-1, -1));
        }
        this.e = this.a.findViewById(R.id.online_refresh_animation);
        this.f = (TextView) this.a.findViewById(R.id.online_refresh_text);
        this.g = AnimationUtils.loadAnimation(context, R.anim.rotate);
        setState(b.SUCCESS);
    }

    public void setFailedView(View view) {
        if (this.c != null) {
            removeView(view);
        }
        this.c = view;
        if (this.c != null) {
            this.c.setOnClickListener(this.i);
        }
        addView(view);
    }

    public void setSuccessView(View view) {
        if (this.b != null) {
            removeView(view);
        }
        this.b = (ViewGroup) view;
        addView(view);
    }

    public void setLoadingView(View view) {
        if (this.a != null) {
            removeView(this.a);
        }
        this.a = view;
        addView(view);
    }

    public void setNodataView(View view) {
        if (this.d != null) {
            removeView(this.d);
        }
        this.d = view;
        addView(view);
    }

    public void setState(b bVar) {
        int i;
        int i2 = 0;
        if (this.a != null) {
            this.a.setVisibility(bVar == b.LOADING ? 0 : 8);
        }
        if (this.b != null) {
            ViewGroup viewGroup = this.b;
            if (bVar == b.SUCCESS) {
                i = 0;
            } else {
                i = 8;
            }
            viewGroup.setVisibility(i);
        }
        if (this.c != null) {
            View view = this.c;
            if (bVar == b.FAILED) {
                i = 0;
            } else {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (this.d != null) {
            View view2 = this.d;
            if (bVar != b.NO_DATA) {
                i2 = 8;
            }
            view2.setVisibility(i2);
        }
        if (bVar == b.LOADING) {
            this.e.startAnimation(this.g);
        } else {
            this.e.clearAnimation();
        }
    }

    public void onThemeLoaded() {
        c.a((View) this, ThemeElement.BACKGROUND_MASK);
        b();
        c.a(this.a, ThemeElement.BACKGROUND_MASK);
        a();
        ColorStateList c = c.c(ThemeElement.COMMON_CONTENT_TEXT);
        if (c != null) {
            this.f.setTextColor(c);
            if (this.c != null && (this.c instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) this.c;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if ((childAt instanceof TextView) && childAt.getId() != R.id.no_data_action_view) {
                        ((TextView) childAt).setTextColor(c);
                    }
                }
            }
        }
    }

    private void a() {
        c.a(this.d, ThemeElement.BACKGROUND_MASK);
        if (this.d != null) {
            IconTextView iconTextView = (IconTextView) this.d.findViewById(R.id.icon_no_data);
            if (iconTextView != null) {
                v.a(iconTextView, ThemeElement.TILE_SUB_TEXT);
            }
        }
    }

    private void b() {
        c.a(this.c, ThemeElement.BACKGROUND_MASK);
        if (this.c != null) {
            IconTextView iconTextView = (IconTextView) this.c.findViewById(R.id.no_media_icon);
            if (iconTextView != null) {
                v.a(iconTextView, ThemeElement.SONG_LIST_ITEM_TEXT);
            }
            View view = (TextView) this.c.findViewById(R.id.textview_load_failed);
            if (view != null) {
                c.a(view, ThemeElement.SONG_LIST_ITEM_TEXT);
            }
        }
    }

    public void setOnRetryRequestListener(a aVar) {
        this.h = aVar;
    }
}
