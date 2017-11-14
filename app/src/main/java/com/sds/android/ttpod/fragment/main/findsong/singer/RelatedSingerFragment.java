package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.ISinger;
import com.sds.android.cloudapi.ttpod.result.OnlineRelatedSingersResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e.c;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.a;
import com.sds.android.ttpod.widget.g;
import java.lang.reflect.Method;
import java.util.Map;

public class RelatedSingerFragment extends BaseFragment implements a {
    private c mAdapter;
    private View mHeaderView;
    private View mListLessItemFooterView;
    private ListView mListView;
    private OnlineRelatedSingersResult mResult;
    private int mSingerId;
    private StateView mStateView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setStatisticPage(s.PAGE_SINGER_RELATED);
        View inflate = layoutInflater.inflate(R.layout.fragment_related_singer, viewGroup, false);
        initView(inflate);
        getSingerId();
        requestRelatedSingerList();
        return inflate;
    }

    private void initView(View view) {
        this.mListView = (ListView) view.findViewById(R.id.listview_related_singer);
        this.mHeaderView = new g(getActivity()).a();
        this.mListView.addHeaderView(this.mHeaderView);
        this.mStateView = new g(getActivity()).b();
        this.mListView.addFooterView(this.mStateView);
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ RelatedSingerFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - this.a.mListView.getHeaderViewsCount();
                if (headerViewsCount >= 0) {
                    ISinger a = this.a.mAdapter.a(headerViewsCount);
                    if (a != null && a.getSingerId() > 0 && !m.a(a.getSingerName())) {
                        SingerDetailFragment.launch((BaseActivity) this.a.getActivity(), a.getSingerName(), (int) a.getSingerId());
                        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_RELATED_SINGER_ITEM.getValue(), s.PAGE_SINGER_RELATED.getValue(), s.PAGE_SINGER_MESSAGE.getValue());
                        sUserEvent.append("position", Integer.valueOf(headerViewsCount + 1));
                        sUserEvent.append("singer_id", Long.valueOf(a.getSingerId()));
                        sUserEvent.post();
                        new b().a("singer_id", String.valueOf((int) a.getSingerId())).a("singer_name", String.valueOf(a.getSingerName())).a();
                    }
                }
            }
        });
        this.mAdapter = new c(getActivity());
        this.mListView.setAdapter(this.mAdapter);
        this.mStateView.setOnRetryRequestListener(new a(this) {
            final /* synthetic */ RelatedSingerFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestRelatedSingerList();
            }
        });
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
        this.mStateView.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_RELATED_LIST, i.a(getClass(), "updateRelatedSingerResult", OnlineRelatedSingersResult.class));
    }

    public void updateRelatedSingerResult(OnlineRelatedSingersResult onlineRelatedSingersResult) {
        this.mResult = onlineRelatedSingersResult;
        e.a(this, onlineRelatedSingersResult, new e.a<OnlineRelatedSingersResult>(this) {
            final /* synthetic */ RelatedSingerFragment a;

            {
                this.a = r1;
            }

            public void a(OnlineRelatedSingersResult onlineRelatedSingersResult) {
                this.a.updateRelatedSingerList(onlineRelatedSingersResult);
            }
        });
    }

    public void requestNextPage(AbsListView absListView, int i, int i2, int i3) {
    }

    private void getSingerId() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalArgumentException("related singer argument--singer id must not be null");
        }
        this.mSingerId = arguments.getInt(SingerDetailFragment.KEY_SINGER_ID);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateRelatedSingerList(this.mResult);
    }

    private void requestRelatedSingerList() {
        this.mStateView.setState(StateView.b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SINGER_RELATED_LIST_BY_ID, Integer.valueOf(this.mSingerId)));
    }

    private void updateRelatedSingerList(OnlineRelatedSingersResult onlineRelatedSingersResult) {
        if (checkSuccess(onlineRelatedSingersResult)) {
            this.mStateView.setState(StateView.b.SUCCESS);
            this.mListView.removeFooterView(this.mStateView);
            this.mAdapter.a(onlineRelatedSingersResult.getOnlineRelatedSingerItems());
            int[] iArr = new int[2];
            this.mListView.getLocationInWindow(iArr);
            this.mListLessItemFooterView = new g(getActivity()).a(onlineRelatedSingersResult.getOnlineRelatedSingerItems().size() * getActivity().getResources().getDimensionPixelSize(R.dimen.related_singer_list_item_height), iArr[1]);
            this.mListView.addFooterView(this.mListLessItemFooterView);
        }
    }

    private boolean checkSuccess(OnlineRelatedSingersResult onlineRelatedSingersResult) {
        if (!isViewAccessAble() || onlineRelatedSingersResult == null) {
            return false;
        }
        if (!onlineRelatedSingersResult.isSuccess()) {
            this.mStateView.setState(StateView.b.FAILED);
            return false;
        } else if (!j.a(onlineRelatedSingersResult.getOnlineRelatedSingerItems())) {
            return true;
        } else {
            this.mStateView.setState(StateView.b.NO_DATA);
            return false;
        }
    }

    public ListView getSingerListView() {
        return this.mListView;
    }

    public void requestRefreshView(Bundle bundle) {
        this.mListView.removeFooterView(this.mListLessItemFooterView);
        this.mListView.removeFooterView(this.mStateView);
        this.mListView.addFooterView(this.mStateView);
        if (bundle != null) {
            this.mSingerId = bundle.getInt(SingerDetailFragment.KEY_SINGER_ID);
            requestRelatedSingerList();
            return;
        }
        throw new IllegalArgumentException("getArguments() singerId must not be null");
    }

    public View getSingerListHeaderView() {
        return this.mHeaderView;
    }
}
