package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.WrapStarListFragment;
import com.sds.android.ttpod.adapter.d.g;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;

public abstract class BaseStarCategoryFragment extends BaseFragment implements OnItemClickListener {
    private g mAdapter;
    private ListView mListView;
    private View mReloadView;
    private StateView mStateView;

    protected abstract int loadCategoryType();

    protected abstract g onCreateAdapter();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_user_list_layout, null, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.user_loadingview);
        this.mStateView.setState(b.SUCCESS);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BaseStarCategoryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (c.e()) {
                    this.a.mStateView.setState(b.LOADING);
                    this.a.onRequestData();
                    return;
                }
                f.a((int) R.string.network_error);
            }
        });
        this.mListView = (ListView) this.mStateView.findViewById(R.id.user_listview);
        this.mListView.setOnItemClickListener(this);
        this.mAdapter = onCreateAdapter();
        this.mListView.setAdapter(this.mAdapter);
        return inflate;
    }

    protected void setLoadingState(b bVar) {
        this.mStateView.setState(bVar);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        onRequestData();
    }

    protected void onRequestData() {
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (a > -1) {
            StarCategory starCategory = (StarCategory) this.mAdapter.getItem(a);
            Bundle bundle = new Bundle();
            bundle.putInt("category_type", loadCategoryType());
            bundle.putInt("category_id", starCategory.getId());
            bundle.putString("title", starCategory.getName());
            BaseFragment wrapStarListFragment = new WrapStarListFragment();
            wrapStarListFragment.setArguments(bundle);
            launchFragment(wrapStarListFragment);
            onItemClickEvent(starCategory);
        }
    }

    protected void onItemClickEvent(StarCategory starCategory) {
    }
}
