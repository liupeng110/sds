package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class RadioCategory implements Serializable {
    @c(a = "data")
    private ArrayList<RadioCategoryChannel> mRadioCategoryChannels;
    @c(a = "tag_type_name")
    private String mRadioCategoryName;

    public ArrayList<RadioCategoryChannel> getRadioCategoryChannels() {
        return this.mRadioCategoryChannels;
    }

    public String getRadioCategoryName() {
        return this.mRadioCategoryName;
    }

    public void setRadioCategoryName(String str) {
        this.mRadioCategoryName = str;
    }

    public void setRadioCategoryChannels(ArrayList<RadioCategoryChannel> arrayList) {
        this.mRadioCategoryChannels = arrayList;
    }
}
