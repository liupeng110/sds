package android.support.v4.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TableLayout;

public class FragmentTableLayout extends TableLayout {
    public FragmentTableLayout(Context context) {
        super(context);
    }

    public FragmentTableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }
}
