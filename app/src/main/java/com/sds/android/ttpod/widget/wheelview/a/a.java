package com.sds.android.ttpod.widget.wheelview.a;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.List;

/* AbstractWheelAdapter */
public abstract class a implements d {
    private List<DataSetObserver> a;

    public View a(View view, ViewGroup viewGroup) {
        return null;
    }

    public void a(DataSetObserver dataSetObserver) {
        if (this.a == null) {
            this.a = new LinkedList();
        }
        this.a.add(dataSetObserver);
    }

    public void b(DataSetObserver dataSetObserver) {
        if (this.a != null) {
            this.a.remove(dataSetObserver);
        }
    }
}
