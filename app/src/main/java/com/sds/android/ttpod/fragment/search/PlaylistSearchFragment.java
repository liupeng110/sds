package com.sds.android.ttpod.fragment.search;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.PlaylistResult;
import com.sds.android.cloudapi.ttpod.data.PlaylistResult.PlaylistItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaylistSearchFragment extends BaseSearchFragment {
    private static final String TAG = "PlaylistSearchFragment";
    protected List<PlaylistItem> mPlaylist = new ArrayList();

    private class a extends BaseAdapter {
        final /* synthetic */ PlaylistSearchFragment a;

        private a(PlaylistSearchFragment playlistSearchFragment) {
            this.a = playlistSearchFragment;
        }

        public int getCount() {
            return this.a.mPlaylist == null ? 0 : this.a.mPlaylist.size();
        }

        public Object getItem(int i) {
            return this.a.mPlaylist.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = View.inflate(this.a.getActivity(), R.layout.album_list_item, null);
                bVar = new b(this.a, view);
                c.a(bVar.c, ThemeElement.SONG_LIST_ITEM_TEXT);
                c.a(bVar.d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                c.a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
                view.setTag(bVar);
            }
            bVar = (b) view.getTag();
            PlaylistItem playlistItem = (PlaylistItem) this.a.mPlaylist.get(i);
            g.a(bVar.b, playlistItem.getPicUrl(), com.sds.android.ttpod.common.c.a.a(50), com.sds.android.ttpod.common.c.a.a(50), (int) R.drawable.img_album_list_item_cover_default);
            bVar.c.setText(Html.fromHtml(playlistItem.getTitle()));
            bVar.d.setText(this.a.getString(R.string.search_song_unit, Integer.valueOf(playlistItem.getSongListSize())));
            bVar.e.setVisibility(0);
            return view;
        }
    }

    private class b {
        final /* synthetic */ PlaylistSearchFragment a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private ImageView e;

        public b(PlaylistSearchFragment playlistSearchFragment, View view) {
            this.a = playlistSearchFragment;
            this.b = (ImageView) view.findViewById(R.id.image_album_cover);
            this.c = (TextView) view.findViewById(R.id.title_view);
            this.d = (TextView) view.findViewById(R.id.subtitle_view);
            this.e = (ImageView) view.findViewById(R.id.iv_right_arrow);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PLAY_LIST_RESULT, i.a(cls, "updatePlaylistResult", PlaylistResult.class, String.class));
    }

    protected void showLastPageFooterText() {
        this.mFooterView.a(getString(R.string.count_of_song_list, Integer.valueOf(this.mPlaylist.size())));
        this.mFooterView.setOnClickListener(null);
    }

    protected View createEmptyView(LayoutInflater layoutInflater) {
        View createEmptyView = super.createEmptyView(layoutInflater);
        ((TextView) createEmptyView.findViewById(R.id.textview_load_failed)).setText(R.string.song_list_search_nodata);
        ((IconTextView) createEmptyView.findViewById(R.id.icon_no_data)).setText((int) R.string.icon_search_result_no_song_list);
        return createEmptyView;
    }

    protected int getSize() {
        return this.mPlaylist.size();
    }

    protected BaseAdapter getAdapter() {
        return new a();
    }

    protected void search(String str, int i, int i2) {
        this.mWord = str;
        com.sds.android.sdk.lib.util.g.a(TAG, "search playlist, word: " + str + ",page: " + i + ",pageSize: " + i2 + ",mUserInput: " + this.mUserInput);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_PLAY_LIST, str, Integer.valueOf(i), Integer.valueOf(i2), this.mUserInput, getRequestId()));
    }

    public void updatePlaylistResult(PlaylistResult playlistResult, String str) {
        if (isAdded() && str.equals(getRequestId())) {
            int code = playlistResult.getCode();
            if (code != 1) {
                onLoadNextPageError();
                if (this.mAdapter.isEmpty()) {
                    c.a(r.ACTION_STATE_SEARCH_SONG_LIST, code);
                    d.r.a(this.mWord, false);
                }
            } else {
                List dataList = playlistResult.getDataList();
                if (this.mAdapter.isEmpty()) {
                    boolean z;
                    c.a(r.ACTION_STATE_SEARCH_SONG_LIST, dataList.size() == 0 ? 0 : 1);
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
                    int pages = playlistResult.getPages();
                    this.mPager.b(pages);
                    if (this.mPager.a() > 1) {
                        this.mPlaylist.addAll(playlistResult.getDataList());
                        this.mFooterView.c();
                    } else {
                        this.mPlaylist = playlistResult.getDataList();
                        if (pages == 1) {
                            showLastPageFooterText();
                        }
                    }
                    this.mAdapter.notifyDataSetChanged();
                    this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            }
            this.mIsLoading = false;
            statisticPlaylist(code);
            if (this.mOnDataCountChangeListener != null && !this.mIsErrorNotFirstPage) {
                this.mOnDataCountChangeListener.a(playlistResult.getCount());
            }
        }
    }

    protected void statisticPlaylist(int i) {
        u.a(Integer.valueOf(i));
    }

    protected void performOnItemClick(int i) {
        PlaylistItem playlistItem = (PlaylistItem) this.mPlaylist.get(i);
        if (playlistItem.getQuanId() != 0) {
            d.r.a(this.mWord, playlistItem.getQuanId(), playlistItem.getTitle(), i);
            launchFragment(SubPostDetailFragment.createById(playlistItem.getQuanId(), "search-playlist"));
            new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SEARCH_SONG_LIST_ITEM.getValue(), s.PAGE_SEARCH_SONG_LIST.getValue(), s.PAGE_SEARCH_SONG_LIST_DETAIL.getValue()).append("title", playlistItem.getTitle()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(playlistItem.getQuanId())).append("keyword", this.mWord).append("position", Integer.valueOf(i + 1)).post();
        }
    }
}
