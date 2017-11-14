package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.result.SingerMvResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.g;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingerMvDetailFragment extends BaseFragment implements a {
    private static final int HOME_PAGE = 1;
    private View mHeaderView;
    private View mListLessItemFooterView;
    private b mLoadFooter;
    private boolean mLoading = false;
    private c mMVListAdapter;
    private List<MvData> mMvDataList = new ArrayList();
    private ListView mMvListView;
    private q mPager = new q();
    private SingerMvResult mResult;
    private View mRootView;
    private long mSingerId;
    private StateView mStateView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSingerId = (long) arguments.getInt(SingerDetailFragment.KEY_SINGER_ID);
            return;
        }
        throw new IllegalArgumentException("getArguments() singerId must not be null");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_related_singer, viewGroup, false);
        this.mMvListView = (ListView) this.mRootView.findViewById(R.id.listview_related_singer);
        this.mStateView = new g(getActivity()).b();
        configNoDataView();
        initViews(layoutInflater);
        return this.mRootView;
    }

    private void configNoDataView() {
        View findViewById = this.mStateView.findViewById(R.id.loadingview_data_empty);
        TextView textView = (TextView) findViewById.findViewById(R.id.textview_load_not_data);
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SingerMvDetailFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestSingerMvResult(1);
            }
        });
        if (textView != null) {
            textView.setText(R.string.not_mv);
        }
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_SINGER_MV_LIST, i.a(getClass(), "updateSingerMvResult", SingerMvResult.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mResult != null) {
            updateSingerMvList(this.mResult);
        }
    }

    private void initViews(LayoutInflater layoutInflater) {
        this.mStateView.setOnRetryRequestListener(new StateView.a(this) {
            final /* synthetic */ SingerMvDetailFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestSingerMvResult(1);
            }
        });
        this.mLoadFooter = new b(layoutInflater, new OnClickListener(this) {
            final /* synthetic */ SingerMvDetailFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestSingerMvResult(this.a.mPager.a());
            }
        });
        this.mHeaderView = new g(getActivity()).a();
        this.mMvListView.addHeaderView(this.mHeaderView);
        this.mMvListView.addFooterView(this.mStateView);
        this.mMVListAdapter = new c(getActivity());
        this.mMvListView.setAdapter(this.mMVListAdapter);
        this.mMvListView.setOnItemClickListener(new com.sds.android.ttpod.fragment.mv.b(getActivity(), "singer_mv"));
        requestSingerMvResult(1);
        this.mStateView.setState(StateView.b.LOADING);
    }

    private void requestSingerMvResult(int i) {
        this.mLoading = true;
        this.mPager.c(i);
        if (this.mPager.a() == 1) {
            this.mStateView.setState(StateView.b.LOADING);
        } else {
            this.mLoadFooter.a(false, 0, getString(R.string.page_loading));
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_SINGER_MV_LIST, Long.valueOf(this.mSingerId), Integer.valueOf(i)));
    }

    public void updateSingerMvResult(SingerMvResult singerMvResult) {
        this.mResult = singerMvResult;
        e.a(this, singerMvResult, new e.a<SingerMvResult>(this) {
            final /* synthetic */ SingerMvDetailFragment a;

            {
                this.a = r1;
            }

            public void a(SingerMvResult singerMvResult) {
                this.a.updateSingerMvList(singerMvResult);
            }
        });
    }

    public void updateSingerMvList(SingerMvResult singerMvResult) {
        this.mLoading = false;
        if (checkSuccess(singerMvResult)) {
            this.mPager.b(singerMvResult.getPageCount());
            updateSuccessStateView();
            if (this.mPager.a() > 1) {
                this.mMvDataList.addAll(singerMvResult.getMvDataList());
            } else {
                this.mMvDataList.clear();
                this.mMvDataList.addAll(singerMvResult.getMvDataList());
                if (!this.mPager.h()) {
                    int[] iArr = new int[2];
                    this.mMvListView.getLocationInWindow(iArr);
                    this.mListLessItemFooterView = new g(getActivity()).a(getListItemHeight() * singerMvResult.getMvDataList().size(), iArr[1]);
                    this.mMvListView.addFooterView(this.mListLessItemFooterView);
                }
            }
            this.mMVListAdapter.a(this.mMvDataList);
        }
    }

    private int getListItemHeight() {
        return getActivity().getResources().getDimensionPixelSize(R.dimen.singer_mv_image_height) + (getActivity().getResources().getDimensionPixelSize(R.dimen.singer_mv_list_item_padding) * 2);
    }

    private void updateSuccessStateView() {
        if (this.mPager.f()) {
            this.mStateView.setState(StateView.b.SUCCESS);
            this.mMvListView.removeFooterView(this.mStateView);
            this.mMvListView.addFooterView(this.mLoadFooter.a());
        } else {
            this.mLoadFooter.a(true, 8, "");
        }
        if (this.mPager.a() == this.mPager.g()) {
            this.mMvListView.removeFooterView(this.mLoadFooter.a());
        }
    }

    private boolean checkSuccess(SingerMvResult singerMvResult) {
        if (!isViewAccessAble() || singerMvResult == null) {
            return false;
        }
        boolean z;
        if (singerMvResult.isSuccess()) {
            z = false;
        } else {
            z = true;
        }
        boolean a = j.a(singerMvResult.getMvDataList());
        if (this.mPager.f()) {
            if (z) {
                this.mStateView.setState(StateView.b.FAILED);
                return false;
            } else if (a) {
                this.mStateView.setState(StateView.b.NO_DATA);
                return false;
            }
        } else if (z || a) {
            this.mLoadFooter.a(true, 8, getString(R.string.loading_failed));
            return false;
        }
        return true;
    }

    public ListView getSingerListView() {
        return this.mMvListView;
    }

    public void requestRefreshView(Bundle bundle) {
        this.mMvListView.removeFooterView(this.mListLessItemFooterView);
        this.mMvListView.removeFooterView(this.mLoadFooter.a());
        this.mMvListView.removeFooterView(this.mStateView);
        this.mMvListView.addFooterView(this.mStateView);
        if (this.mMvDataList != null) {
            this.mMVListAdapter = new c(getActivity());
            this.mMvListView.setAdapter(this.mMVListAdapter);
        }
        if (bundle != null) {
            this.mSingerId = (long) bundle.getInt(SingerDetailFragment.KEY_SINGER_ID);
            this.mPager.a(true);
            this.mPager.c(1);
            requestSingerMvResult(1);
            return;
        }
        throw new IllegalArgumentException("getArguments() singerId must not be null");
    }

    public View getSingerListHeaderView() {
        return this.mHeaderView;
    }

    public void requestNextPage(AbsListView absListView, int i, int i2, int i3) {
        if (m.b(i, i2, i3) && !this.mLoading && this.mPager.h()) {
            requestSingerMvResult(this.mPager.d());
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mMVListAdapter != null) {
            this.mMVListAdapter.notifyDataSetChanged();
        }
        this.mStateView.onThemeLoaded();
        this.mLoadFooter.onThemeLoaded();
        c.a(this.mMvListView, ThemeElement.COMMON_SEPARATOR);
        c.a(this.mMvListView, ThemeElement.BACKGROUND_MASK);
    }
}
