package com.sds.android.ttpod.fragment.musiccircle;

import android.view.View;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.activities.musiccircle.c;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import java.lang.reflect.Method;
import java.util.Map;

public class StarListOfRankFragment extends StarListFragment {
    public void onRequestData(int i, int i2, int i3) {
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REQUEST_STAR_USERS_BY_RANK, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), "star_user_by_rank"));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_STAR_USER_LIST_BY_RANK, i.a(StarListOfRankFragment.class, "onUpdateStarUserListByRank", LabeledTTPodUserListResult.class, String.class));
    }

    public void onUpdateStarUserListByRank(LabeledTTPodUserListResult labeledTTPodUserListResult, String str) {
        if ("star_user_by_rank".equals(str)) {
            onRequestDataFinished(labeledTTPodUserListResult);
        }
    }

    public void onFollow(long j) {
        super.onFollow(j);
        c.a(1, getTitle());
    }

    public void onUnFollow(long j) {
        super.onUnFollow(j);
        c.a(2, getTitle());
    }

    protected void onItemClickEvent(AdapterView<?> adapterView, View view, int i, long j) {
        super.onItemClickEvent(adapterView, view, i, j);
        c.b(getTitle());
    }

    protected String onCreateOrigin() {
        return "rank";
    }

    protected String onCreateTitle() {
        return getTitle();
    }
}
