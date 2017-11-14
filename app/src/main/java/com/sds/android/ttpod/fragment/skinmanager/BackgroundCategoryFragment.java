package com.sds.android.ttpod.fragment.skinmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.BackgroundActivity;
import com.sds.android.ttpod.fragment.skinmanager.base.BaseCategoryFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class BackgroundCategoryFragment extends BaseCategoryFragment {
    private TextView mTextView;

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.ON_BACKGROUND_CATEGORY_LIST_PARSED, i.a(cls, "onDataListParsed", ArrayList.class, Long.class));
        map.put(a.ON_BKG_CATEGORY_LIST_DOWNLOADED, i.a(cls, "onDataListDownloaded", new Class[0]));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTextView = (TextView) view.findViewById(R.id.picture_copyright);
        this.mTextView.setVisibility(0);
        ((MarginLayoutParams) this.mGridView.getLayoutParams()).bottomMargin = getResources().getDimensionPixelSize(R.dimen.tab_label_height);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mTextView, ThemeElement.SUB_BAR_TEXT);
        c.a(this.mTextView, ThemeElement.SUB_BAR_BACKGROUND);
    }

    protected com.sds.android.ttpod.framework.base.a.a getLoadDataCommand() {
        return new com.sds.android.ttpod.framework.base.a.a(a.PARSE_CATEGORY_LIST, Integer.valueOf(1));
    }

    protected a getRequestDataCommandID() {
        return a.REQUEST_BKG_CATEGORY_LIST;
    }

    protected void startActivity(com.sds.android.ttpod.framework.modules.skin.a.a aVar) {
        Intent intent = new Intent(getActivity(), BackgroundActivity.class);
        intent.putExtra(StarCategory.KEY_STAR_CATEGORY_ID, aVar.a());
        intent.putExtra("name", aVar.b());
        getActivity().startActivity(intent);
        x.e(aVar.b());
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_BKG_CATEGORY_ITEM.getValue(), s.PAGE_BACKGROUND_CATEGORY.getValue(), s.PAGE_BACKGROUND_CATEGORY_DETAIL.getValue()).append("background_category_name", aVar.b()).post();
    }

    protected int getItemLayoutId() {
        return R.layout.layout_background_category_item;
    }
}
