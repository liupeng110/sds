package com.sds.android.ttpod.fragment.musiccircle;

import android.view.View;
import android.widget.AdapterView;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.framework.a.b.p;

public class StarListOfRecommendFragment extends StarListOfRankFragment {
    private static final int RECOMMEND_ID = 0;

    public void onRequestData(int i, int i2, int i3) {
        super.onRequestData(0, i2, i3);
    }

    public void onFollow(long j) {
        c.b(1);
    }

    public void onUnFollow(long j) {
        c.b(2);
    }

    protected void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        c.q();
        p.a("music-circle");
    }

    protected String onCreateOrigin() {
        return "recommend";
    }

    protected String onCreateTitle() {
        return super.onCreateTitle();
    }
}
