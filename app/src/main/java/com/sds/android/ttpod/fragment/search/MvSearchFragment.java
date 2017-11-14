package com.sds.android.ttpod.fragment.search;

import android.widget.BaseAdapter;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.result.MvDataResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.main.findsong.singer.c;
import com.sds.android.ttpod.framework.a.b.d.j;
import com.sds.android.ttpod.framework.a.b.d.r;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.widget.StateView.b;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class MvSearchFragment extends BaseSearchFragment {
    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UPDATE_SEARCH_MV_FINISHED, i.a(cls, "updateSearchMv", MvDataResult.class, String.class));
    }

    public void updateSearchMv(MvDataResult mvDataResult, String str) {
        if (isAdded() && str.equals(getRequestId())) {
            if (mvDataResult.getCode() == 1) {
                List dataList = mvDataResult.getDataList();
                if (this.mAdapter.isEmpty()) {
                    r.a(this.mWord, !dataList.isEmpty());
                }
                if (dataList.size() == 0) {
                    this.mStateView.setState(b.NO_DATA);
                } else {
                    int pages = mvDataResult.getPages();
                    this.mPager.b(pages);
                    if (this.mPager.a() > 1) {
                        ((c) this.mAdapter).b(mvDataResult.getDataList());
                        this.mFooterView.c();
                    } else {
                        ((c) this.mAdapter).a(mvDataResult.getDataList());
                        if (pages == 1) {
                            showLastPageFooterText();
                        }
                    }
                    this.mStateView.setState(b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else {
                onLoadNextPageError();
                if (this.mAdapter.isEmpty()) {
                    r.a(this.mWord, false);
                }
            }
            this.mIsLoading = false;
            if (this.mOnDataCountChangeListener != null && !this.mIsErrorNotFirstPage) {
                this.mOnDataCountChangeListener.a(mvDataResult.getRows());
            }
        }
    }

    protected void showLastPageFooterText() {
        this.mFooterView.a(getString(R.string.count_of_mv, Integer.valueOf(getSize())));
        this.mFooterView.setOnClickListener(null);
    }

    protected int getSize() {
        return this.mAdapter != null ? this.mAdapter.getCount() : 0;
    }

    protected BaseAdapter getAdapter() {
        return new c(getActivity());
    }

    protected void performOnItemClick(int i) {
        MvData mvData = (MvData) this.mAdapter.getItem(i);
        r.a(this.mWord, (long) mvData.getId(), mvData.getName(), i);
        j.a("mv_origin", "mv_search");
        VideoPlayManager.a(getActivity(), mvData);
    }

    protected void search(String str, int i, int i2) {
        this.mWord = str;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.START_SEARCH_MV, str, Integer.valueOf(i), Integer.valueOf(i2), this.mUserInput, getRequestId()));
    }
}
