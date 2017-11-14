package com.sds.android.ttpod.fragment.skinmanager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BackgroundListFragment extends BackgroundBaseFragment {
    private static final int DEFAULT_PAGE_SIZE = 12;
    private static final int ITEM_COUNT = 100;
    private String mCategoryName;
    private q mDataPager = new q();
    private int mId;
    private boolean mIsErrorNotFirstPage = false;
    private DataListFooterView mListFooterView;
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ BackgroundListFragment a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (m.b(i, i2, i3) && !this.a.mIsLoading) {
                if (this.a.mDataPager.a() >= this.a.mDataPager.g()) {
                    this.a.mBackgroundListView.removeFooterView(this.a.mListFooterView);
                } else if (this.a.mIsErrorNotFirstPage) {
                    this.a.showNotFirstPageError();
                } else {
                    this.a.requestData(this.a.mDataPager.d());
                }
            }
        }
    };

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.REQUEST_PAGED_BKG_LIST_FINISHED, i.a(cls, "updateDataListForAdapter", OnlinePagedSkinListResult.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getActivity().getIntent().getExtras();
        this.mId = extras.getInt(StarCategory.KEY_STAR_CATEGORY_ID);
        this.mCategoryName = extras.getString("name");
        setStatisticPage(s.PAGE_BACKGROUND_CATEGORY_DETAIL);
    }

    protected void initListViewHeader() {
    }

    protected void initListViewFooter() {
        this.mListFooterView = new DataListFooterView(getActivity());
        this.mBackgroundListView.addFooterView(this.mListFooterView);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBackgroundListView.setOnScrollListener(this.mOnScrollListener);
        getActionBarController().a(this.mCategoryName);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        requestData(1);
    }

    private void requestData(int i) {
        this.mIsLoading = true;
        if (i > 1) {
            this.mListFooterView.a();
        }
        loadDataList(i, 12);
        this.mDataPager.c(i);
    }

    private void loadDataList(int i, int i2) {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_PAGED_BKG_LIST, Integer.valueOf(this.mId), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mListFooterView, ThemeElement.BACKGROUND_MASK);
    }

    public void updateDataListForAdapter(OnlinePagedSkinListResult onlinePagedSkinListResult) {
        if (isAdded()) {
            if (onlinePagedSkinListResult.getCode() == 1) {
                ArrayList skins = onlinePagedSkinListResult.getData().getSkins();
                ArrayList arrayList = new ArrayList();
                String mainUrl = onlinePagedSkinListResult.getMainUrl();
                mainUrl = mainUrl.substring(0, mainUrl.length() - 1);
                Iterator it = skins.iterator();
                while (it.hasNext()) {
                    OnlineSkinItem onlineSkinItem = (OnlineSkinItem) it.next();
                    if (!onlineSkinItem.getSkinUrl().startsWith(mainUrl)) {
                        onlineSkinItem.setPictureUrl(mainUrl + onlineSkinItem.getRecommendPicUrl());
                        onlineSkinItem.setSkinUrl(mainUrl + onlineSkinItem.getSkinUrl());
                        com.sds.android.ttpod.framework.modules.theme.a aVar = new com.sds.android.ttpod.framework.modules.theme.a(onlineSkinItem);
                        aVar.a(onlineSkinItem.getId() + "_" + onlineSkinItem.getName());
                        if (e.a(aVar.h())) {
                            aVar.a(com.sds.android.ttpod.framework.modules.theme.a.a.ADD_BY_USER);
                        }
                        arrayList.add(aVar);
                    } else {
                        return;
                    }
                }
                if (arrayList.size() == 0) {
                    this.mStateView.setState(StateView.b.NO_DATA);
                } else {
                    this.mDataPager.b((int) Math.ceil(8.333333333333334d));
                    if (this.mDataPager.a() > 1) {
                        this.mListFooterView.c();
                    }
                    this.mBackgroundAdapter.a(arrayList);
                    this.mStateView.setState(StateView.b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else if (this.mDataPager.a() > 1) {
                this.mIsErrorNotFirstPage = true;
                this.mDataPager.c(this.mDataPager.a() - 1);
                showNotFirstPageError();
            } else {
                this.mIsErrorNotFirstPage = false;
                this.mStateView.setState(StateView.b.FAILED);
            }
            this.mIsLoading = false;
        }
    }

    protected void showNotFirstPageError() {
        if (this.mListFooterView != null) {
            this.mListFooterView.a(getResources().getString(R.string.retry_next_page));
            this.mListFooterView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ BackgroundListFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(this.a.mDataPager.d());
                }
            });
        }
    }
}
