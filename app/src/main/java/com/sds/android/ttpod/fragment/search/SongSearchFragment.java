package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.b;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.j;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class SongSearchFragment extends OnlineMediaListFragment implements b, a, b {
    private static final int DEFAULT_SEARCH_PAGE = 1;
    private static final int DEFAULT_SEARCH_SIZE = 50;
    private static final String TAG = "SongSearchFragment";
    private boolean mIsNewSearch = false;
    private boolean mIsSearching = false;
    private String mUserInput;
    private String mWord;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onReloadData() {
        super.onReloadData();
        requestSongList(this.mWord);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setModule("search");
        j.a(0);
        this.mListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ SongSearchFragment a;

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
        setOnMediaItemClickListener(this);
        setOrigin(u.a());
    }

    protected View onCreateNodataView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.search_result_nodata, null);
        ((TextView) inflate.findViewById(R.id.textview_load_failed)).setText(R.string.song_search_nodata);
        return inflate;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UPDATE_SEARCH_SONG_FINISHED, i.a(cls, "updateSongSearchFinished", Integer.class, Integer.class, List.class, String.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (getArguments() != null) {
            this.mWord = getArguments().getString("search_word");
        }
    }

    public void requestSongList(String str, int i) {
        g.a(TAG, "requestSongList word: " + str + ",page: " + i + ",mUserInput:" + this.mUserInput);
        if (!this.mIsSearching && !m.a(str)) {
            String trim = str.trim();
            this.mIsSearching = true;
            this.mWord = trim;
            long currentTimeMillis = System.currentTimeMillis();
            if (i == 1) {
                show();
                updateStateViews(null);
            }
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.START_SEARCH_SONG, this.mWord, Integer.valueOf(i), Integer.valueOf(50), this.mUserInput));
            g.a(TAG, "requestSongList " + trim + "cost " + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public void requestSongList(String str) {
        clearPage();
        requestSongList(str, 1);
    }

    public void updateSongSearchFinished(Integer num, Integer num2, List list, String str) {
        boolean z = true;
        g.a(TAG, "loadPictureAfterSearchFinished " + list.size() + ", searchWord: " + str);
        if (isAdded() && m.a(this.mWord, str)) {
            int size = list.size();
            if (getCount() == 0) {
                int intValue = num.intValue() == 1 ? size > 0 ? 1 : 0 : num.intValue();
                c.a(r.ACTION_STATE_SEARCH_SONG, intValue);
                String str2 = this.mWord;
                if (size <= 0) {
                    z = false;
                }
                d.r.a(str2, z);
            }
            updateMediaList(num, num2, list);
            if (this.mIsNewSearch) {
                this.mListView.setSelection(0);
                this.mIsNewSearch = false;
            }
            this.mIsSearching = false;
            u.a(num, getOrigin(), this.mWord);
            updateProperties();
        }
    }

    private void updateProperties() {
        BaseFragment topFragment = ((BaseActivity) getActivity()).getTopFragment();
        topFragment.updateAlibabaProperty("keyword", this.mWord);
        topFragment.updateAlibabaProperty(SocialConstants.PARAM_TYPE, d.r.a());
    }

    public void search(String str, String str2) {
        this.mUserInput = str2;
        this.mIsNewSearch = true;
        requestSongList(str);
    }

    public void onFragmentSelected(int i, String str, String str2) {
        this.mUserInput = str2;
        if (!m.a(str) && !m.a(str, this.mWord)) {
            requestSongList(str);
        }
    }

    protected void onMediaItemClicked(MediaItem mediaItem, int i) {
        d.r.a(this.mWord, mediaItem.getSongID().longValue(), mediaItem.getTitle(), i);
        super.onMediaItemClicked(mediaItem, i);
        w.a("search", "listen", getOrigin(), 0, (long) p.f(), this.mWord, u.b());
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_SEARCH_SONG_ITEM.getValue(), s.PAGE_SEARCH_SINGLE_SONG.getValue(), 0).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("keyword", this.mWord).append("position", Integer.valueOf(p.f())).post();
    }

    protected void onFavoriteChanged(MediaItem mediaItem, boolean z) {
        super.onFavoriteChanged(mediaItem, z);
        u.a(z);
        j.a(mediaItem.getSongID().longValue(), p.f(), z);
    }

    protected boolean onListStatistic() {
        return true;
    }

    public String getListenOrigin() {
        String listenOrigin = super.getListenOrigin();
        return m.a(this.mWord) ? listenOrigin : listenOrigin + "_" + this.mWord + "_" + u.b();
    }

    public String getDownloadOrigin() {
        String origin = getOrigin();
        return m.a(this.mWord) ? origin : origin + "_" + this.mWord + "_" + u.b() + "_" + p.f();
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        if (!mediaItem.hasCopyright() && mediaItem.getCensorLevel() == 4) {
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_COPYRIGHT_PLAY.getValue(), String.valueOf(s.PAGE_NONE.getValue()), String.valueOf(s.PAGE_NONE.getValue()));
            sUserEvent.append("keyword", this.mWord);
            sUserEvent.append("position", Integer.valueOf(i + 1));
            sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID());
            sUserEvent.setPageParameter(true);
            sUserEvent.post();
        }
    }

    protected void onExpandStatistic(MediaItem mediaItem) {
        w.a(getModule(), "menu", getOrigin(), 0, (long) p.f(), this.mWord, u.b());
    }
}
