package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.StarCategoryResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.adapter.d.g;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.widget.StateView.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StarCategoryFragment extends BaseStarCategoryFragment {
    private List<StarCategory> mDatas = new ArrayList();
    private g mStarCategoryAdapter;

    protected g onCreateAdapter() {
        this.mStarCategoryAdapter = new g(getActivity(), this.mDatas);
        return this.mStarCategoryAdapter;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setLoadingState(this.mDatas.isEmpty() ? b.LOADING : b.SUCCESS);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_STAR_CATEGORIES_LIST, i.a(StarCategoryFragment.class, "onUpdateStarCategoryList", StarCategoryResult.class, String.class));
    }

    public void onUpdateStarCategoryList(StarCategoryResult starCategoryResult, String str) {
        if ("star_category".equals(str)) {
            List dataList = starCategoryResult.getDataList();
            if (dataList.isEmpty()) {
                setLoadingState(b.FAILED);
                return;
            }
            setLoadingState(b.SUCCESS);
            this.mDatas.clear();
            this.mDatas = dataList;
            this.mStarCategoryAdapter.a(this.mDatas);
        }
    }

    protected void onRequestData() {
        if (this.mDatas.isEmpty()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_STAR_CATEGORIES, "star_category"));
        }
    }

    protected int loadCategoryType() {
        return 1;
    }

    protected void onItemClickEvent(StarCategory starCategory) {
        super.onItemClickEvent(starCategory);
        c.d(starCategory.getName());
        p.a("music-circle");
    }
}
