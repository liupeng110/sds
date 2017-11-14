package com.sds.android.ttpod.fragment.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumSearchItem;
import com.sds.android.cloudapi.ttpod.result.AlbumSearchItemsResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SlidingAlbumDetailFragment;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumSearchFragment extends BaseSearchFragment {
    private static final String TAG = "AlbumSearchFragment";
    private List<AlbumSearchItem> mAlbumItemList = new ArrayList();

    private class a extends BaseAdapter {
        final /* synthetic */ AlbumSearchFragment a;

        private a(AlbumSearchFragment albumSearchFragment) {
            this.a = albumSearchFragment;
        }

        public int getCount() {
            return this.a.mAlbumItemList == null ? 0 : this.a.mAlbumItemList.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.a.getActivity(), R.layout.album_list_item, null);
                view.setTag(new b(this.a, view));
            }
            b bVar = (b) view.getTag();
            AlbumSearchItem albumSearchItem = (AlbumSearchItem) this.a.mAlbumItemList.get(i);
            g.a(bVar.b, albumSearchItem.getPic200(), com.sds.android.ttpod.common.c.a.a(50), com.sds.android.ttpod.common.c.a.a(50), (int) R.drawable.img_album_list_item_cover_default);
            bVar.c.setText(albumSearchItem.getName());
            bVar.d.setText(albumSearchItem.getSingerName() + " " + albumSearchItem.getPublishTime());
            c.a(bVar.c, ThemeElement.SONG_LIST_ITEM_TEXT);
            c.a(bVar.d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            c.a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            return view;
        }
    }

    private class b {
        final /* synthetic */ AlbumSearchFragment a;
        private ImageView b;
        private TextView c;
        private TextView d;

        public b(AlbumSearchFragment albumSearchFragment, View view) {
            this.a = albumSearchFragment;
            this.b = (ImageView) view.findViewById(R.id.image_album_cover);
            this.c = (TextView) view.findViewById(R.id.title_view);
            this.d = (TextView) view.findViewById(R.id.subtitle_view);
        }
    }

    protected void search(String str, int i, int i2) {
        this.mWord = str;
        com.sds.android.sdk.lib.util.g.a(TAG, "search album, word: " + str + ",page: " + i + ",pageSize: " + i2 + ",mUserInput: " + this.mUserInput);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_ALBUM_WORDKEY, str, Integer.valueOf(i), Integer.valueOf(i2), this.mUserInput, getRequestId()));
    }

    protected View createEmptyView(LayoutInflater layoutInflater) {
        View createEmptyView = super.createEmptyView(layoutInflater);
        ((TextView) createEmptyView.findViewById(R.id.textview_load_failed)).setText(R.string.album_search_nodata);
        ((IconTextView) createEmptyView.findViewById(R.id.icon_no_data)).setText((int) R.string.icon_search_result_no_album);
        return createEmptyView;
    }

    protected BaseAdapter getAdapter() {
        return new a();
    }

    protected int getSize() {
        return this.mAlbumItemList.size();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_ALBUM_WORDKEY_FINISHED, i.a(cls, "updateSearchAlbum", AlbumSearchItemsResult.class, String.class));
    }

    protected void showLastPageFooterText() {
        this.mFooterView.a(getString(R.string.count_of_album, Integer.valueOf(this.mAlbumItemList.size())));
        this.mFooterView.setOnClickListener(null);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mStateView = null;
    }

    public void updateSearchAlbum(AlbumSearchItemsResult albumSearchItemsResult, String str) {
        if (isAdded() && str.equals(getRequestId())) {
            int code = albumSearchItemsResult.getCode();
            if (code == 1) {
                List dataList = albumSearchItemsResult.getDataList();
                if (this.mAdapter.isEmpty()) {
                    boolean z;
                    c.a(r.ACTION_STATE_SEARCH_ALBUM, dataList.size() == 0 ? 0 : 1);
                    String str2 = this.mWord;
                    if (dataList.isEmpty()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    d.r.a(str2, z);
                }
                if (dataList.size() == 0) {
                    this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
                } else {
                    int allPage = albumSearchItemsResult.getAllPage();
                    this.mPager.b(allPage);
                    if (this.mPager.a() > 1) {
                        this.mAlbumItemList.addAll(albumSearchItemsResult.getDataList());
                        this.mFooterView.c();
                    } else {
                        this.mAlbumItemList = albumSearchItemsResult.getDataList();
                        if (allPage == 1) {
                            showLastPageFooterText();
                        }
                    }
                    this.mAdapter.notifyDataSetChanged();
                    this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else {
                onLoadNextPageError();
                if (this.mAdapter.isEmpty()) {
                    c.a(r.ACTION_STATE_SEARCH_ALBUM, code);
                    d.r.a(this.mWord, false);
                }
            }
            this.mIsLoading = false;
            statisticAlbum(code);
            if (this.mOnDataCountChangeListener != null && !this.mIsErrorNotFirstPage) {
                this.mOnDataCountChangeListener.a(albumSearchItemsResult.getCount());
            }
        }
    }

    protected void statisticAlbum(int i) {
        u.a(Integer.valueOf(i));
    }

    protected void performOnItemClick(int i) {
        AlbumSearchItem albumSearchItem = (AlbumSearchItem) this.mAlbumItemList.get(i);
        d.r.a(this.mWord, albumSearchItem.getId(), albumSearchItem.getName(), i);
        launchFragment(SlidingAlbumDetailFragment.instantiate(albumSearchItem.getId(), albumSearchItem.getName()));
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SEARCH_ALBUM_ITEM.getValue(), s.PAGE_SEARCH_ALBUM.getValue(), s.PAGE_ALBUM_DETAIL.getValue()).append("title", albumSearchItem.getName()).append("song_album_id", Long.valueOf(albumSearchItem.getId())).append("keyword", this.mWord).append("position", Integer.valueOf(i + 1)).post();
    }
}
