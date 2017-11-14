package com.sds.android.ttpod.activities.musiccircle.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.d.h;
import com.sds.android.ttpod.adapter.d.i;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.UserListFragment;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class SearchFriendsResultFragment extends SlidingClosableFragment {

    public static class SearchResultFragment extends UserListFragment<TTPodUser> {
        private String mSearchContent;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.mSearchContent = getArguments().getString("search_content");
        }

        protected i<TTPodUser> onCreateAdapter(List<TTPodUser> list) {
            return new i<TTPodUser>(this, getActivity(), list) {
                final /* synthetic */ SearchResultFragment a;

                protected void a(h hVar, TTPodUser tTPodUser) {
                    super.a(hVar, tTPodUser);
                    hVar.d().setVisibility(8);
                }
            };
        }

        protected void onRequestData(int i, int i2) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MUSICCIRCLE_SEARCH, this.mSearchContent, "search"));
        }

        protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
            super.onLoadCommandMap(map);
            map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_RESULT, com.sds.android.sdk.lib.util.i.a(getClass(), "onUpdateSearchResult", UserListResult.class, String.class));
        }

        public void onUpdateSearchResult(UserListResult userListResult, String str) {
            if ("search".equals(str)) {
                addData(userListResult.getDataList(), false);
            }
        }

        public void onLoadFinished() {
            super.onLoadFinished();
            reload();
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        initView();
        return layoutInflater.inflate(R.layout.musiccircle_search_result_layout, viewGroup, false);
    }

    private void initView() {
        getActionBarController().b((int) R.string.musiccircle_search_result);
        if (getArguments() == null || !TextUtils.isEmpty(getArguments().getString("search_content"))) {
            getChildFragmentManager().beginTransaction().replace(R.id.search_result, Fragment.instantiate(getActivity(), SearchResultFragment.class.getName(), getArguments())).commitAllowingStateLoss();
            return;
        }
        throw new IllegalArgumentException("search content must not be null and length >= 0");
    }

    protected boolean needSearchAction() {
        return false;
    }
}
