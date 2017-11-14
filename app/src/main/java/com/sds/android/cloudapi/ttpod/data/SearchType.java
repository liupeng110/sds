package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;

public class SearchType implements Serializable {
    @c(a = "hidden")
    private int mHidden;
    @c(a = "_id")
    private String mId;
    @c(a = "words")
    private ArrayList<String> mWords = new ArrayList();

    public String getId() {
        return this.mId;
    }

    public int getHidden() {
        return this.mHidden;
    }

    public ArrayList<String> getWords() {
        return this.mWords;
    }
}
