package com.sds.android.ttpod.fragment.skinmanager;

import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SkinCategoryDetailActivity;
import com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class ThemeCategoryFragment extends BaseCategoryFragment {
    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.ON_SKIN_CATEGORY_LIST_PARSED, i.a(cls, "onDataListParsed", ArrayList.class, Long.class));
        map.put(a.ON_SKIN_CATEGORY_LIST_DOWNLOADED, i.a(cls, "onDataListDownloaded", new Class[0]));
    }

    protected com.sds.android.ttpod.framework.base.a.a getLoadDataCommand() {
        return new com.sds.android.ttpod.framework.base.a.a(a.PARSE_CATEGORY_LIST, Integer.valueOf(0));
    }

    protected a getRequestDataCommandID() {
        return a.REQUEST_SKIN_CATEGORY_LIST;
    }

    protected void startActivity(com.sds.android.ttpod.framework.modules.skin.a.a aVar) {
        Intent intent = new Intent(getActivity(), SkinCategoryDetailActivity.class);
        intent.putExtra(StarCategory.KEY_STAR_CATEGORY_ID, aVar.a());
        intent.putExtra("name", aVar.b());
        getActivity().startActivity(intent);
        x.d(aVar.b());
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_CATEGORY_ITEM.getValue(), s.PAGE_THEME_CATEGORY.getValue(), s.PAGE_THEME_CATEGORY_DETAIL.getValue()).append("skin_category_name", aVar.b()).post();
    }

    protected int getItemLayoutId() {
        return R.layout.layout_category_item;
    }
}
