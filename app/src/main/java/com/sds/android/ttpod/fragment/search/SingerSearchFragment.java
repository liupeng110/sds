package com.sds.android.ttpod.fragment.search;

import android.widget.BaseAdapter;
import com.sds.android.cloudapi.ttpod.data.ISinger;
import com.sds.android.cloudapi.ttpod.result.SearchSingerResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e.c;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.widget.StateView.b;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class SingerSearchFragment extends BaseSearchFragment {
    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UPDATE_SEARCH_SINGER_FINISHED, i.a(cls, "updateSearchSinger", SearchSingerResult.class, String.class));
    }

    public void updateSearchSinger(SearchSingerResult searchSingerResult, String str) {
        if (isAdded() && str.equals(getRequestId())) {
            int code = searchSingerResult.getCode();
            if (code == 1) {
                List dataList = searchSingerResult.getDataList();
                if (this.mAdapter.isEmpty()) {
                    boolean z;
                    c.a(r.ACTION_STATE_SEARCH_SINGER, dataList.size() == 0 ? 0 : 1);
                    String str2 = this.mWord;
                    if (dataList.isEmpty()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    d.r.a(str2, z);
                }
                if (dataList.size() == 0) {
                    this.mStateView.setState(b.NO_DATA);
                } else {
                    int pages = searchSingerResult.getPages();
                    this.mPager.b(pages);
                    if (this.mPager.a() > 1) {
                        ((c) this.mAdapter).b(searchSingerResult.getDataList());
                        this.mFooterView.c();
                    } else {
                        ((c) this.mAdapter).a(searchSingerResult.getDataList());
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
                    c.a(r.ACTION_STATE_SEARCH_SINGER, code);
                    d.r.a(this.mWord, false);
                }
            }
            this.mIsLoading = false;
            if (this.mOnDataCountChangeListener != null && !this.mIsErrorNotFirstPage) {
                this.mOnDataCountChangeListener.a(searchSingerResult.getRows());
            }
        }
    }

    protected void showLastPageFooterText() {
        this.mFooterView.a(getString(R.string.count_of_singers, Integer.valueOf(getSize())));
        this.mFooterView.setOnClickListener(null);
    }

    protected int getSize() {
        return this.mAdapter != null ? this.mAdapter.getCount() : 0;
    }

    protected BaseAdapter getAdapter() {
        return new c(getActivity());
    }

    protected void performOnItemClick(int i) {
        ISinger iSinger = (ISinger) this.mAdapter.getItem(i);
        d.r.a(this.mWord, iSinger.getSingerId(), iSinger.getSingerName(), i);
        SingerDetailFragment.launch((BaseActivity) getActivity(), iSinger.getSingerName(), (int) iSinger.getSingerId());
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SEARCH_SINGER_ITEM.getValue(), s.PAGE_SEARCH_SINGER.getValue(), s.PAGE_SINGER_MESSAGE.getValue()).append("singer_id", Long.valueOf(iSinger.getSingerId())).append("position", Integer.valueOf(i + 1)).post();
    }

    protected void search(String str, int i, int i2) {
        this.mWord = str;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.START_SEARCH_SINGER, str, Integer.valueOf(i), Integer.valueOf(i2), this.mUserInput, getRequestId()));
    }
}
