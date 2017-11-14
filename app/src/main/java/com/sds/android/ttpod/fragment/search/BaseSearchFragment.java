package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.base.BaseSlidingTabFragment.a;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;

public abstract class BaseSearchFragment extends BaseFragment implements a, b {
    public static final int DEFAULT_PAGE_SIZE = 50;
    private static final String TAG = "BaseSearchFragment";
    protected BaseAdapter mAdapter;
    protected DataListFooterView mFooterView;
    protected boolean mIsErrorNotFirstPage = false;
    protected boolean mIsLoading = true;
    protected ListView mListView;
    protected a mOnDataCountChangeListener;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener(this) {
        final /* synthetic */ BaseSearchFragment a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int a = m.a(this.a.mListView.getHeaderViewsCount(), i, this.a.mAdapter.getCount());
            if (a > -1) {
                this.a.performOnItemClick(a);
            }
        }
    };
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ BaseSearchFragment a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (m.b(i, i2, i3) && !this.a.mIsLoading) {
                if (this.a.mPager.a() >= this.a.mPager.g()) {
                    this.a.showLastPageFooterText();
                } else if (this.a.mIsErrorNotFirstPage) {
                    this.a.showNotFirstPageError();
                } else {
                    this.a.requestData(this.a.mPager.d());
                }
            }
        }
    };
    protected String mOrigin;
    protected q mPager = new q();
    protected View mRootView;
    protected StateView mStateView;
    protected String mUserInput;
    protected String mWord;

    protected abstract BaseAdapter getAdapter();

    protected abstract int getSize();

    protected abstract void performOnItemClick(int i);

    protected abstract void search(String str, int i, int i2);

    protected abstract void showLastPageFooterText();

    private void requestData(int i) {
        g.a(TAG, "requestData page = " + i);
        if (i >= 1) {
            this.mIsLoading = true;
            this.mFooterView.a();
            search(this.mWord, i, 50);
            this.mPager.c(i);
        }
    }

    protected void showNotFirstPageError() {
        if (this.mFooterView != null) {
            this.mFooterView.a("加载下一页出错, 请重新尝试");
            this.mFooterView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BaseSearchFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(this.a.mPager.d());
                }
            });
        }
    }

    protected void onLoadNextPageError() {
        if (this.mPager.a() > 1) {
            this.mIsErrorNotFirstPage = true;
            this.mPager.c(this.mPager.a() - 1);
            showNotFirstPageError();
            return;
        }
        this.mIsErrorNotFirstPage = false;
        this.mStateView.setState(b.FAILED);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_online_search_result, viewGroup, false);
            this.mStateView = (StateView) this.mRootView.findViewById(R.id.loading_view);
            this.mStateView.setOnRetryRequestListener(new StateView.a(this) {
                final /* synthetic */ BaseSearchFragment a;

                {
                    this.a = r1;
                }

                public void onRetryRequested() {
                    this.a.search(this.a.mWord, this.a.mUserInput);
                }
            });
            this.mListView = (ListView) this.mStateView.findViewById(R.id.media_listview);
            this.mFooterView = new DataListFooterView(getActivity());
            this.mListView.addFooterView(this.mFooterView);
            this.mAdapter = getAdapter();
            this.mListView.setAdapter(this.mAdapter);
            this.mListView.setOnItemClickListener(this.mOnItemClickListener);
            this.mListView.setOnScrollListener(this.mOnScrollListener);
            this.mPager.b(Entry.MAX_LIMIT);
            this.mStateView.setNodataView(createEmptyView(layoutInflater));
            ((DragUpdateListView) this.mListView).setEnableDragUpdate(false);
        }
        this.mListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ BaseSearchFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    ((OnlineSearchDetailFragment) this.a.getParentFragment()).hideSoftInputFromWindow();
                }
                return false;
            }
        });
        return this.mRootView;
    }

    protected View createEmptyView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.search_result_nodata, null);
    }

    protected String getRequestId() {
        return toString() + this.mWord;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        c.a(this.mFooterView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
        this.mAdapter.notifyDataSetChanged();
    }

    public void search(String str, String str2) {
        this.mUserInput = str2;
        if (!com.sds.android.sdk.lib.util.m.a(str)) {
            this.mPager = new q();
            this.mPager.b(Entry.MAX_LIMIT);
            this.mStateView.setState(b.LOADING);
            search(str, 1, 50);
        }
    }

    public void onFragmentSelected(int i, String str, String str2) {
        if (!com.sds.android.sdk.lib.util.m.a(str, this.mWord)) {
            search(str, this.mUserInput);
        }
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public void setOnDataCountChangeListener(a aVar) {
        this.mOnDataCountChangeListener = aVar;
    }
}
