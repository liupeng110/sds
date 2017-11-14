package android.support.v4.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.RelativeLayout;

public class FragmentRelativeLayout extends RelativeLayout {
    public FragmentRelativeLayout(Context context) {
        super(context);
    }

    public FragmentRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FragmentRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }
}
