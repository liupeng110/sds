package com.sds.android.ttpod.fragment.musiccircle;

import android.view.View;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.lang.reflect.Method;
import java.util.Map;

public class StarListOfCategoryFragment extends StarListFragment {
    public void onRequestData(int i, int i2, int i3) {
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REQUEST_STAR_USERS_BY_CATEGORY, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), "star_user_by_category"));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_STAR_USER_lIST_BY_CATEGORY, i.a(StarListOfCategoryFragment.class, "onUpdateStarUserListByCategory", LabeledTTPodUserListResult.class, String.class));
    }

    public void onUpdateStarUserListByCategory(LabeledTTPodUserListResult labeledTTPodUserListResult, String str) {
        if ("star_user_by_category".equals(str)) {
            onRequestDataFinished(labeledTTPodUserListResult);
        }
    }

    public void onFollow(long j) {
        super.onFollow(j);
        c.b(1, getTitle());
    }

    public void onUnFollow(long j) {
        super.onUnFollow(j);
        c.b(2, getTitle());
    }

    protected void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        super.onItemClickEvent(adapterView, view, i, j);
        c.e(getTitle());
    }

    protected String onCreateOrigin() {
        return WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY;
    }

    protected String onCreateTitle() {
        return getTitle();
    }
}
