package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CarouselSpinner extends CarouselViewGroup<SpinnerAdapter> {
    private DataSetObserver H;
    SpinnerAdapter a;
    int b;
    int c;
    boolean d;
    int e;
    int f;
    int g;
    int h;
    final Rect i;
    final a j;
    protected int k;
    protected int l;

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
        long a;
        int b;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readLong();
            this.b = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.a);
            parcel.writeInt(this.b);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.a + " position=" + this.b + "}";
        }
    }

    class a {
        final /* synthetic */ CarouselSpinner a;
        private final SparseArray<View> b = new SparseArray();

        a(CarouselSpinner carouselSpinner) {
            this.a = carouselSpinner;
        }

        public void a(int i, View view) {
            this.b.put(i, view);
        }

        View a(int i) {
            View view = (View) this.b.get(i);
            if (view != null) {
                this.b.delete(i);
            }
            return view;
        }

        void a() {
            SparseArray sparseArray = this.b;
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                View view = (View) sparseArray.valueAt(i);
                if (view != null) {
                    this.a.removeDetachedView(view, true);
                }
            }
            sparseArray.clear();
        }
    }

    abstract void a(int i, boolean z);

    public CarouselSpinner(Context context) {
        super(context);
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = new Rect();
        this.j = new a(this);
        b();
    }

    public CarouselSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarouselSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = new Rect();
        this.j = new a(this);
        b();
    }

    private void b() {
        setFocusable(true);
        setWillNotDraw(false);
    }

    public SpinnerAdapter getAdapter() {
        return this.a;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        int i = -1;
        if (this.a != null) {
            this.a.unregisterDataSetObserver(this.H);
            d();
        }
        this.a = spinnerAdapter;
        this.E = -1;
        this.F = Long.MIN_VALUE;
        if (this.a != null) {
            this.D = this.C;
            this.C = this.a.getCount();
            g();
            this.H = new b(this);
            this.a.registerDataSetObserver(this.H);
            if (this.C > 0) {
                i = this.A;
            }
            setSelectedPositionInt(i);
            setNextSelectedPositionInt(i);
            if (this.C == 0) {
                i();
            }
        } else {
            g();
            d();
            i();
        }
        requestLayout();
    }

    public View getSelectedView() {
        if (this.C <= 0 || this.A < 0) {
            return null;
        }
        return getChildAt(this.A - this.m);
    }

    void b(int i, boolean z) {
        if (i != this.E) {
            this.d = true;
            int i2 = i - this.A;
            setNextSelectedPositionInt(i);
            a(i2, z);
            this.d = false;
        }
    }

    public void setSelection(int i) {
        b(i, false);
    }

    void d() {
        this.x = false;
        this.r = false;
        removeAllViewsInLayout();
        this.E = -1;
        this.F = Long.MIN_VALUE;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        int b;
        boolean z = true;
        int i3 = false;
        int mode = MeasureSpec.getMode(i);
        this.i.left = getPaddingLeft() > this.e ? getPaddingLeft() : this.e;
        this.i.top = getPaddingTop() > this.f ? getPaddingTop() : this.f;
        this.i.right = getPaddingRight() > this.g ? getPaddingRight() : this.g;
        this.i.bottom = getPaddingBottom() > this.h ? getPaddingBottom() : this.h;
        if (this.x) {
            h();
        }
        int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition >= 0 && this.a != null && selectedItemPosition < this.a.getCount()) {
            View view;
            View a = this.j.a(selectedItemPosition);
            if (a == null) {
                view = this.a.getView(selectedItemPosition, null, this);
            } else {
                view = a;
            }
            if (view != null) {
                this.j.a(selectedItemPosition, view);
            }
            if (view != null) {
                if (view.getLayoutParams() == null) {
                    this.d = true;
                    view.setLayoutParams(generateDefaultLayoutParams());
                    this.d = false;
                }
                measureChild(view, i, i2);
                z = this.i.bottom + (a(view) + this.i.top);
                b = (b(view) + this.i.left) + this.i.right;
                this.k = view.getMeasuredWidth();
                this.l = view.getMeasuredHeight();
                i3 = z;
                z = false;
                if (z) {
                    i3 = this.i.top + this.i.bottom;
                    if (mode == 0) {
                        b = this.i.left + this.i.right;
                    }
                }
                setMeasuredDimension(resolveSize(Math.max(b, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
                this.b = i2;
                this.c = i;
            }
        }
        b = 0;
        if (z) {
            i3 = this.i.top + this.i.bottom;
            if (mode == 0) {
                b = this.i.left + this.i.right;
            }
        }
        setMeasuredDimension(resolveSize(Math.max(b, getSuggestedMinimumWidth()), i), resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
        this.b = i2;
        this.c = i;
    }

    int a(View view) {
        return view.getMeasuredHeight();
    }

    int b(View view) {
        return view.getMeasuredWidth();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    void e() {
        int childCount = getChildCount();
        a aVar = this.j;
        int i = this.m;
        for (int i2 = 0; i2 < childCount; i2++) {
            aVar.a(i + i2, getChildAt(i2));
        }
    }

    public void requestLayout() {
        if (!this.d) {
            super.requestLayout();
        }
    }

    public int getCount() {
        return this.C;
    }

    public boolean a(int i, int i2) {
        for (int i3 = 0; i3 < this.a.getCount(); i3++) {
            a aVar = (a) getChildAt(i3);
            Matrix carouseItemMatrix = aVar.getCarouseItemMatrix();
            float[] fArr = new float[]{(float) aVar.getLeft(), (float) aVar.getTop(), 0.0f};
            carouseItemMatrix.mapPoints(fArr);
            int i4 = (int) fArr[0];
            int i5 = (int) fArr[1];
            fArr[0] = (float) aVar.getRight();
            fArr[1] = (float) aVar.getBottom();
            fArr[2] = 0.0f;
            carouseItemMatrix.mapPoints(fArr);
            int i6 = (int) fArr[0];
            int i7 = (int) fArr[1];
            if (i4 < i) {
                int i8;
                if (i6 > i) {
                    i8 = 1;
                } else {
                    i8 = 0;
                }
                if (i5 < i2) {
                    i6 = 1;
                } else {
                    i6 = 0;
                }
                if ((i6 & i8) != 0 && i7 > i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public int b(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.a.getCount(); i3++) {
            a aVar = (a) getChildAt(i3);
            Matrix carouseItemMatrix = aVar.getCarouseItemMatrix();
            float[] fArr = new float[]{(float) aVar.getLeft(), (float) aVar.getTop(), 0.0f};
            carouseItemMatrix.mapPoints(fArr);
            int i4 = (int) fArr[0];
            int i5 = (int) fArr[1];
            fArr[0] = (float) aVar.getRight();
            fArr[1] = (float) aVar.getBottom();
            fArr[2] = 0.0f;
            carouseItemMatrix.mapPoints(fArr);
            int i6 = (int) fArr[0];
            int i7 = (int) fArr[1];
            if (i4 < i) {
                int i8;
                if (i6 > i) {
                    i8 = 1;
                } else {
                    i8 = 0;
                }
                if (i5 < i2) {
                    i6 = 1;
                } else {
                    i6 = 0;
                }
                if ((i6 & i8) != 0 && i7 > i2) {
                    arrayList.add(aVar);
                }
            }
        }
        Collections.sort(arrayList);
        if (arrayList.size() != 0) {
            return ((a) arrayList.get(arrayList.size() - 1)).getIndex();
        }
        return this.A;
    }

    public Parcelable onSaveInstanceState() {
        Object savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getSelectedItemId();
        if (savedState.a >= 0) {
            savedState.b = getSelectedItemPosition();
        } else {
            savedState.b = -1;
        }
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.a >= 0) {
            this.x = true;
            this.r = true;
            this.p = savedState.a;
            this.o = savedState.b;
            this.s = 0;
            requestLayout();
        }
    }
}
