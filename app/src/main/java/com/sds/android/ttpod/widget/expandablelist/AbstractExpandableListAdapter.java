package com.sds.android.ttpod.widget.expandablelist;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.t;
import java.util.BitSet;

public abstract class AbstractExpandableListAdapter extends c {
    private View a = null;
    private int c = -1;
    private int d = 200;
    private BitSet e = new BitSet();
    private final SparseIntArray f = new SparseIntArray(10);
    private ViewGroup g;
    private a h;

    public interface a {
        void onCollapse(View view, int i);

        void onExpand(View view, int i);
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        };
        private BitSet a;
        private int b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.a = null;
            this.b = -1;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = null;
            this.b = -1;
            this.b = parcel.readInt();
            this.a = AbstractExpandableListAdapter.b(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            AbstractExpandableListAdapter.b(parcel, this.a);
        }
    }

    public abstract View a(View view);

    public abstract View b(View view);

    public AbstractExpandableListAdapter(ListAdapter listAdapter) {
        super(listAdapter);
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public int a() {
        return this.c;
    }

    private void a(int i, View view, int i2) {
        if (this.h == null) {
            return;
        }
        if (i == 0) {
            t.a(i2);
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_EXPAND.getValue(), 0, 0);
            sUserEvent.append("position", Integer.valueOf(i2 + 1));
            sUserEvent.setPageParameter(true);
            sUserEvent.post();
            this.h.onExpand(view, i2);
        } else if (i == 1) {
            this.h.onCollapse(view, i2);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        this.g = viewGroup;
        View view2 = this.b.getView(i, view, viewGroup);
        a(view2, i);
        return view2;
    }

    public int b() {
        return this.d;
    }

    public boolean c() {
        return this.c != -1;
    }

    private void a(View view, int i) {
        View a = a(view);
        View b = b(view);
        if (a != null && b != null) {
            b.measure(view.getWidth(), view.getHeight());
            b(a, b, i);
            b.requestLayout();
        }
    }

    private void a(final View view, View view2, int i) {
        Animation animation = view2.getAnimation();
        if (animation == null || !animation.hasStarted() || animation.hasEnded()) {
            view2.setAnimation(null);
            int i2 = view2.getVisibility() == 0 ? 1 : 0;
            if (i2 == 0) {
                this.e.set(i, true);
            } else {
                this.e.set(i, false);
            }
            if (i2 == 0) {
                if (!(this.c == -1 || this.c == i)) {
                    if (this.a != null) {
                        b(this.a, 1);
                        a(1, this.a, this.c);
                    }
                    this.e.set(this.c, false);
                }
                this.a = view2;
                this.c = i;
            } else if (this.c == i) {
                this.c = -1;
            }
            b(view2, i2);
            a(i2, view2, i);
            return;
        }
        animation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ AbstractExpandableListAdapter b;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.performClick();
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void b(final View view, final int i) {
        Animation aVar = new a(view, i);
        aVar.setDuration((long) b());
        aVar.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ AbstractExpandableListAdapter c;

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (i == 0 && (this.c.g instanceof ListView)) {
                    ListView listView = (ListView) this.c.g;
                    int bottom = view.getBottom();
                    Rect rect = new Rect();
                    boolean globalVisibleRect = view.getGlobalVisibleRect(rect);
                    Rect rect2 = new Rect();
                    listView.getGlobalVisibleRect(rect2);
                    if (!globalVisibleRect) {
                        listView.smoothScrollBy(bottom, this.c.b());
                    } else if (rect2.bottom == rect.bottom) {
                        listView.smoothScrollBy(bottom, this.c.b());
                    }
                }
            }
        });
        view.startAnimation(aVar);
    }

    private void c(View view, int i) {
        int i2 = view.getVisibility() == 0 ? 1 : 0;
        if (i2 == 0) {
            this.e.set(i, true);
        } else {
            this.e.set(i, false);
        }
        if (i2 == 0) {
            if (!(this.c == -1 || this.c == i)) {
                if (this.a != null) {
                    e(this.a, 1);
                    a(1, this.a, this.c);
                }
                this.e.set(this.c, false);
            }
            this.a = view;
            this.c = i;
        } else if (this.c == i) {
            this.c = -1;
        }
        e(view, i2);
        a(i2, view, i);
    }

    private void a(View view, View view2, int i, boolean z) {
        if (z) {
            a(view, view2, i);
        } else {
            c(view2, i);
        }
    }

    private void b(View view, final View view2, final int i) {
        if (view2 == this.a && i != this.c) {
            this.a = null;
        }
        if (i == this.c) {
            this.a = view2;
        }
        if (this.f.get(i, -1) == -1) {
            this.f.put(i, view2.getMeasuredHeight());
            d(view2, i);
        } else {
            d(view2, i);
        }
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AbstractExpandableListAdapter c;

            public void onClick(View view) {
                this.c.a(view, view2, i, false);
            }
        });
    }

    private void d(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (this.e.get(i)) {
            view.setVisibility(0);
            layoutParams.bottomMargin = 0;
            return;
        }
        view.setVisibility(8);
        layoutParams.bottomMargin = 0 - this.f.get(i);
    }

    private void e(View view, int i) {
        int measuredHeight = view.getMeasuredHeight();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (i == 0) {
            view.setVisibility(0);
            layoutParams.bottomMargin = 0;
        } else {
            view.setVisibility(8);
            layoutParams.bottomMargin = -measuredHeight;
        }
        if (i == 0 && (this.g instanceof ListView)) {
            ListView listView = (ListView) this.g;
            Rect rect = new Rect();
            ((View) view.getParent()).getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            listView.getGlobalVisibleRect(rect2);
            int measuredHeight2 = ((View) view.getParent()).getMeasuredHeight();
            if (((rect.top + measuredHeight) + measuredHeight2 < rect2.bottom ? 1 : 0) == 0 && j.a()) {
                listView.smoothScrollBy(((rect.top + measuredHeight) + measuredHeight2) - rect2.bottom, 0);
            }
        }
    }

    public boolean a(boolean z) {
        if (!c()) {
            return false;
        }
        if (this.a != null) {
            if (z) {
                b(this.a, 1);
            } else {
                e(this.a, 1);
            }
        }
        this.e.set(this.c, false);
        a(1, this.a, this.c);
        this.c = -1;
        return true;
    }

    public Parcelable a(Parcelable parcelable) {
        SavedState savedState = new SavedState(parcelable);
        savedState.b = this.c;
        savedState.a = this.e;
        return savedState;
    }

    public void a(SavedState savedState) {
        if (savedState != null) {
            this.c = savedState.b;
            this.e = savedState.a;
        }
    }

    private static BitSet b(Parcel parcel) {
        BitSet bitSet = new BitSet();
        if (parcel != null) {
            int readInt = parcel.readInt();
            for (int i = 0; i < readInt; i++) {
                bitSet.set(parcel.readInt());
            }
        }
        return bitSet;
    }

    private static void b(Parcel parcel, BitSet bitSet) {
        if (parcel != null && bitSet != null) {
            parcel.writeInt(bitSet.cardinality());
            int i = -1;
            while (true) {
                i = bitSet.nextSetBit(i + 1);
                if (i != -1) {
                    parcel.writeInt(i);
                } else {
                    return;
                }
            }
        }
    }
}
