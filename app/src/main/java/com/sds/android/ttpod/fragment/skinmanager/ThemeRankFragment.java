package com.sds.android.ttpod.fragment.skinmanager;

import android.os.Bundle;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.skin.m;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class ThemeRankFragment extends ActionBarThemeListFragment {
    protected String getTitle() {
        return (String) getResources().getText(R.string.theme_rank);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.FINISH_UPDATE_SKIN_RANK_LIST, i.a(cls, "updateSkinRankResult", Boolean.class));
        map.put(a.UPDATE_SKIN_RANK_LIST, i.a(cls, "updateDataListForAdapter", ArrayList.class));
    }

    public void updateSkinRankResult(Boolean bool) {
        this.mCacheMode = false;
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_SKIN_RANK_LIST, Integer.valueOf(0)));
    }

    public void updateDataListForAdapter(ArrayList<m> arrayList) {
        this.mCacheMode = false;
        if (arrayList == null || arrayList.size() == 0) {
            this.mStateView.setState(StateView.b.FAILED);
        } else if (checkIfReloadData(arrayList)) {
            setAdapterDataSource(arrayList);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSubClassTag = ThemeRankFragment.class.getSimpleName();
    }

    protected String getStatisticOrigin() {
        return "rank";
    }

    public void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.storage.a.a.a().b(getThemeDataList());
    }

    protected ArrayList<m> loadDataFromCache() {
        return com.sds.android.ttpod.framework.storage.a.a.a().s();
    }

    protected a getLoadDataCommandID() {
        return a.REQUEST_UPDATE_SKIN_RANK_LIST;
    }
}
