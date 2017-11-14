package android.support.v4.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;

public class FragmentFrameLayout extends FrameLayout {
    public FragmentFrameLayout(Context context) {
        super(context);
    }

    public FragmentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FragmentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }
}
