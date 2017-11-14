package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.adapter.d.g;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.widget.StateView.b;
import java.util.ArrayList;
import java.util.List;

public class StarRankFragment extends BaseStarCategoryFragment {
    private static final int STAR_ACTIVE = 4;
    private static final int STAR_CAPACITY = 3;
    private static final int STAR_POPULARITY = 1;
    private static final int STAR_RISE = 2;
    private List<StarCategory> mDatas = new ArrayList();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StarCategory starCategory = new StarCategory();
        starCategory.setId(1);
        starCategory.setName("人气榜");
        this.mDatas.add(starCategory);
        starCategory = new StarCategory();
        starCategory.setId(4);
        starCategory.setName("活跃榜");
        this.mDatas.add(starCategory);
        starCategory = new StarCategory();
        starCategory.setId(2);
        starCategory.setName("飙升榜");
        this.mDatas.add(starCategory);
        starCategory = new StarCategory();
        starCategory.setId(3);
        starCategory.setName("潜力榜");
        this.mDatas.add(starCategory);
    }

    protected g onCreateAdapter() {
        return new g(getActivity(), this.mDatas);
    }

    protected int loadCategoryType() {
        return 0;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setLoadingState(b.SUCCESS);
    }

    protected void onItemClickEvent(StarCategory starCategory) {
        super.onItemClickEvent(starCategory);
        c.a(starCategory.getName());
        p.a("music-circle");
    }
}
