package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Billboards;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.b;
import com.sds.android.ttpod.framework.modules.theme.b.e;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HotwordView extends ViewGroup {
    private static final int a = com.sds.android.ttpod.common.c.a.a(4);
    private int b;
    private int c;
    private int d;
    private boolean e = false;
    private a f;
    private int g = -1;
    private List<String> h = new ArrayList();
    private OnClickListener i = new OnClickListener(this) {
        final /* synthetic */ HotwordView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            String charSequence = ((TextView) view).getText().toString();
            if (this.a.f != null) {
                this.a.f.a(charSequence);
            }
            if (this.a.g >= 0) {
                this.a.setHighLightIndex(this.a.h.indexOf(charSequence));
            }
        }
    };

    public interface a {
        void a(String str);
    }

    public HotwordView(Context context) {
        super(context);
        a(context);
    }

    public HotwordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public HotwordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.b = (int) TypedValue.applyDimension(1, 8.0f, getResources().getDisplayMetrics());
    }

    public void setHighLightIndex(int i) {
        this.g = i;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            a(getChildAt(i2), i2);
        }
    }

    public int getHighLightIndex() {
        return this.g;
    }

    public void setMargin(int i) {
        this.b = i;
        requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        if (childCount != 0) {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = i3 - i;
            int measuredHeight = getChildAt(0).getMeasuredHeight();
            int i9 = 0;
            while (i6 < childCount) {
                View childAt = getChildAt(i6);
                int measuredWidth = childAt.getMeasuredWidth();
                if (i5 > 0) {
                    i5 += this.b;
                }
                if (i5 == 0 || i5 + measuredWidth <= i8) {
                    childAt.layout(i5, i9, i5 + measuredWidth, i9 + measuredHeight);
                    i5 += measuredWidth;
                    i6++;
                } else {
                    i7++;
                    i9 += this.b + measuredHeight;
                    i5 = 0;
                }
                if (this.e && r5 >= this.d) {
                    break;
                }
            }
            while (i6 < childCount) {
                getChildAt(i6).layout(i3, i4, i3, i4);
                i6++;
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        int size = MeasureSpec.getSize(i);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, ExploreByTouchHelper.INVALID_ID);
        if (childCount > 0) {
            this.c = 1;
            int i3 = 0;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                childAt.measure(makeMeasureSpec, makeMeasureSpec);
                i3 += childAt.getMeasuredWidth();
                if (i3 < size) {
                    i3 += this.b;
                } else {
                    this.c++;
                    i3 = childAt.getMeasuredWidth();
                }
            }
            i3 = getChildAt(0).getMeasuredHeight();
            if (this.e) {
                this.c = this.d;
            }
            setMeasuredDimension(size, (i3 * this.c) + (this.b * (this.c - 1)));
            return;
        }
        super.onMeasure(i, i2);
    }

    public void setRows(int i) {
        this.d = i;
        this.e = true;
    }

    private void a(int i) {
        int childCount = getChildCount();
        if (childCount > i) {
            for (childCount--; childCount >= i; childCount--) {
                removeViewAt(childCount);
            }
        } else if (childCount < i) {
            while (childCount < i) {
                addView(b(childCount));
                childCount++;
            }
        }
    }

    private View b(int i) {
        View inflate = View.inflate(getContext(), R.layout.layout_hotword_item, null);
        inflate.setOnClickListener(this.i);
        a(inflate, i);
        return inflate;
    }

    public void setContent(List<Billboards> list) {
        int size;
        int size2 = list != null ? list.size() : 0;
        if (size2 > 10) {
            Collections.shuffle(list);
            list = list.subList(0, 10);
            size = list.size();
        } else {
            size = size2;
        }
        a(size);
        if (size > 0) {
            Collections.sort(list, new Comparator<Billboards>(this) {
                final /* synthetic */ HotwordView a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((Billboards) obj, (Billboards) obj2);
                }

                public int a(Billboards billboards, Billboards billboards2) {
                    return billboards.getWord().length() - billboards2.getWord().length();
                }
            });
            for (int i = 0; i < size; i++) {
                ((TextView) getChildAt(i)).setText(((Billboards) list.get(i)).getWord());
            }
        }
    }

    public void setStringList(List<String> list) {
        int i = 0;
        if (list != null && list.size() != 0) {
            List subList;
            List arrayList = new ArrayList();
            for (String str : list) {
                if (!m.a(str)) {
                    arrayList.add(str);
                }
            }
            if (arrayList.size() > 10) {
                subList = arrayList.subList(0, 10);
            } else {
                subList = arrayList;
            }
            this.h = subList;
            a(subList.size());
            while (i < subList.size()) {
                ((TextView) getChildAt(i)).setText((CharSequence) subList.get(i));
                i++;
            }
        }
    }

    public void setListener(a aVar) {
        this.f = aVar;
    }

    private void a(View view, int i) {
        if (i == this.g) {
            c.a(view, ThemeElement.COMMON_INDICATOR);
            return;
        }
        c.a(view, ThemeElement.SONG_LIST_ITEM_TEXT);
        Drawable backgroundDrawable = getBackgroundDrawable();
        if (backgroundDrawable != null) {
            view.setBackgroundDrawable(backgroundDrawable);
        } else {
            c.a(view, ThemeElement.TILE_MASK);
        }
    }

    public Drawable getBackgroundDrawable() {
        e b = c.b(ThemeElement.TILE_MASK);
        if (!(b instanceof b.c)) {
            return null;
        }
        Drawable drawable;
        Drawable drawable2;
        ColorDrawable colorDrawable = (ColorDrawable) b.c();
        ColorDrawable colorDrawable2 = (ColorDrawable) b.d();
        float[] fArr = new float[]{(float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a};
        if (colorDrawable != null) {
            Drawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
            shapeDrawable.getPaint().setColor(c.a(colorDrawable));
            drawable = shapeDrawable;
        } else {
            drawable = null;
        }
        if (colorDrawable2 != null) {
            shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
            shapeDrawable.getPaint().setColor(c.a(colorDrawable2));
            drawable2 = shapeDrawable;
        } else {
            drawable2 = null;
        }
        if (drawable != null && drawable2 != null) {
            shapeDrawable = new StateListDrawable();
            shapeDrawable.addState(new int[]{16842919}, drawable2);
            shapeDrawable.addState(new int[]{-16842910}, drawable2);
            shapeDrawable.addState(StateSet.WILD_CARD, drawable);
            drawable2.setPadding(null);
            drawable.setPadding(null);
            return shapeDrawable;
        } else if (drawable == null) {
            return drawable2;
        } else {
            return drawable;
        }
    }

    public void a() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            a(getChildAt(childCount), childCount);
        }
    }
}
