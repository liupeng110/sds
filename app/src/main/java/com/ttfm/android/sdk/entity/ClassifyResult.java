package com.ttfm.android.sdk.entity;

public class ClassifyResult extends TTFMBaseResult {
    private ClassifyEntity data;

    public ClassifyResult() {
        this.data = null;
        this.data = new ClassifyEntity();
    }

    public ClassifyEntity getData() {
        return this.data;
    }

    public void setData(ClassifyEntity classifyEntity) {
        this.data = classifyEntity;
    }

    public void setAllLabelCount() {
        if (this.data != null) {
            this.data.setAlllabelcount();
        }
    }

    public void setAllLabelArrayList() {
        if (this.data != null) {
            this.data.setAllLaberArrayList();
        }
    }
}
