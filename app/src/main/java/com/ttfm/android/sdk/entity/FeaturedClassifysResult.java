package com.ttfm.android.sdk.entity;

import java.util.ArrayList;

public class FeaturedClassifysResult extends TTFMBaseResult {
    private FeaturedClassifysArray data = null;

    class FeaturedClassifysArray {
        private ArrayList<FeatureClassifysEntity> classifys = new ArrayList();

        FeaturedClassifysArray() {
        }

        public ArrayList<FeatureClassifysEntity> getClassifys() {
            return this.classifys;
        }

        public void setClassifys(ArrayList<FeatureClassifysEntity> arrayList) {
            this.classifys = arrayList;
        }
    }

    public FeaturedClassifysArray getData() {
        return this.data;
    }

    public void setData(FeaturedClassifysArray featuredClassifysArray) {
        this.data = featuredClassifysArray;
    }

    public ArrayList<FeatureClassifysEntity> getClassifys() {
        if (this.data != null) {
            return this.data.classifys;
        }
        return null;
    }
}
