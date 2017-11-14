package com.sds.android.cloudapi.ttpod.result;

import android.database.AbstractCursor;
import java.util.ArrayList;
import java.util.Collection;

public abstract class DataCursor<D> extends AbstractCursor {
    private ArrayList<D> mValueList = new ArrayList();

    public abstract Object getDataColumnValue(D d, int i);

    public D getData() {
        return this.mValueList.get(this.mPos);
    }

    public void addData(Collection<D> collection) {
        this.mValueList.addAll(collection);
        onChange(true);
    }

    public void updateData(Collection<D> collection) {
        this.mValueList.clear();
        addData(collection);
    }

    public int getCount() {
        return this.mValueList.size();
    }

    public String getString(int i) {
        return (String) getDataColumnValue(i);
    }

    public short getShort(int i) {
        return ((Short) getDataColumnValue(i)).shortValue();
    }

    public int getInt(int i) {
        return ((Integer) getDataColumnValue(i)).intValue();
    }

    public long getLong(int i) {
        return ((Long) getDataColumnValue(i)).longValue();
    }

    public float getFloat(int i) {
        return ((Float) getDataColumnValue(i)).floatValue();
    }

    public double getDouble(int i) {
        return ((Double) getDataColumnValue(i)).doubleValue();
    }

    public boolean isNull(int i) {
        return getDataColumnValue(i) == null;
    }

    public Object getDataColumnValue(int i) {
        return getDataColumnValue(getData(), i);
    }
}
