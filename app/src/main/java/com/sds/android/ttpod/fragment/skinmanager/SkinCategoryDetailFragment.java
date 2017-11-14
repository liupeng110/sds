package com.sds.android.ttpod.fragment.skinmanager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult.OnlinePagedSkinListData;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.a.v;
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

public class SkinCategoryDetailFragment extends ActionBarThemeListFragment {
    private static final int DEFAULT_PAGE_SIZE = 12;
    private String mCategoryName;
    private DataListFooterView mFooterView;
    private int mId;
    private boolean mIsErrorNotFirstPage = false;
    private boolean mIsLoading = false;
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ SkinCategoryDetailFragment a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            switch (i) {
                case 0:
                    this.a.mEnableRefreshProgressbar = true;
                    return;
                case 1:
                    this.a.mEnableRefreshProgressbar = false;
                    return;
                default:
                    return;
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (m.b(i, i2, i3) && !this.a.mIsLoading) {
                if (this.a.mPager.a() >= this.a.mPager.g()) {
                    this.a.mThemeListView.removeFooterView(this.a.mFooterView);
                } else if (this.a.mIsErrorNotFirstPage) {
                    this.a.showNotFirstPageError();
                } else {
                    this.a.requestData(this.a.mPager.d());
                }
            }
        }
    };
    private q mPager = new q();

    protected void initListViewFooter() {
        this.mFooterView = new DataListFooterView(getActivity());
        this.mThemeListView.addFooterView(this.mFooterView);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.REQUEST_PAGED_SKIN_LIST_FINISHED, i.a(cls, "updateDataListForAdapter", OnlinePagedSkinListResult.class));
    }

    protected void loadData() {
        requestData(1);
    }

    protected OnScrollListener getOnScrollListener() {
        return this.mOnScrollListener;
    }

    private void requestData(int i) {
        this.mIsLoading = true;
        if (i > 1) {
            this.mFooterView.a();
        }
        loadDataList(i, 12);
        this.mPager.c(i);
    }

    private void loadDataList(int i, int i2) {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_PAGED_SKIN_LIST, Integer.valueOf(this.mId), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public void updateDataListForAdapter(OnlinePagedSkinListResult onlinePagedSkinListResult) {
        if (isAdded()) {
            if (onlinePagedSkinListResult.getCode() == 1) {
                OnlinePagedSkinListData data = onlinePagedSkinListResult.getData();
                ArrayList skins = data.getSkins();
                ArrayList arrayList = new ArrayList();
                String mainUrl = onlinePagedSkinListResult.getMainUrl();
                Iterator it = skins.iterator();
                while (it.hasNext()) {
                    OnlineSkinItem onlineSkinItem = (OnlineSkinItem) it.next();
                    if (v.a(onlineSkinItem)) {
                        onlineSkinItem.setPictureUrl(mainUrl + onlineSkinItem.getRecommendPicUrl());
                        onlineSkinItem.setSkinUrl("http://api.skin.ttpod.com/skin/apiSkin/download?id=" + onlineSkinItem.getId());
                        com.sds.android.ttpod.framework.modules.skin.m mVar = new com.sds.android.ttpod.framework.modules.skin.m(onlineSkinItem);
                        if (e.a(mVar.b())) {
                            mVar.a(0);
                        }
                        arrayList.add(mVar);
                    }
                }
                if (arrayList.size() == 0) {
                    this.mStateView.setState(StateView.b.NO_DATA);
                } else {
                    this.mPager.b((int) Math.ceil(((double) data.getSkinsCount()) / 12.0d));
                    if (this.mPager.a() > 1) {
                        this.mThemeAdapter.b(arrayList);
                        setOnlineSkinInfoMap(arrayList);
                        this.mFooterView.c();
                    } else {
                        setAdapterDataSource(arrayList);
                    }
                    this.mThemeAdapter.notifyDataSetChanged();
                    this.mStateView.setState(StateView.b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else if (this.mPager.a() > 1) {
                this.mIsErrorNotFirstPage = true;
                this.mPager.c(this.mPager.a() - 1);
                showNotFirstPageError();
            } else {
                this.mIsErrorNotFirstPage = false;
                this.mStateView.setState(StateView.b.FAILED);
            }
            this.mIsLoading = false;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getActivity().getIntent().getExtras();
        this.mId = extras.getInt(StarCategory.KEY_STAR_CATEGORY_ID);
        this.mCategoryName = extras.getString("name");
        this.mSubClassTag = SkinCategoryDetailFragment.class.getSimpleName() + this.mCategoryName;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mFooterView, ThemeElement.BACKGROUND_MASK);
    }

    protected ArrayList<com.sds.android.ttpod.framework.modules.skin.m> loadDataFromCache() {
        return null;
    }

    protected a getLoadDataCommandID() {
        return null;
    }

    protected String getTitle() {
        return this.mCategoryName;
    }

    protected void showNotFirstPageError() {
        if (this.mFooterView != null) {
            this.mFooterView.a(getResources().getString(R.string.retry_next_page));
            this.mFooterView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SkinCategoryDetailFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(this.a.mPager.d());
                }
            });
        }
    }
}
