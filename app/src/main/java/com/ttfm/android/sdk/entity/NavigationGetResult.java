package com.ttfm.android.sdk.entity;

import java.util.ArrayList;
import java.util.List;

public class NavigationGetResult extends TTFMBaseResult {
    private NavigationArray data = null;

    public class NavigationArray {
        private ArrayList<NavigationEntity> tabs = new ArrayList();

        public ArrayList<NavigationEntity> getTabs() {
            return this.tabs;
        }

        public void setTabs(ArrayList<NavigationEntity> arrayList) {
            this.tabs = arrayList;
        }
    }

    public NavigationArray getData() {
        return this.data;
    }

    public void setData(NavigationArray navigationArray) {
        this.data = navigationArray;
    }

    public List<NavigationEntity> getNavigations() {
        if (this.data != null) {
            return this.data.tabs;
        }
        return null;
    }
}
