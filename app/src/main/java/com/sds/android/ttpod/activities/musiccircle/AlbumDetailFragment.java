package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.AlbumData;
import com.sds.android.cloudapi.ttpod.data.OnlineSongItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.AlbumResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.c.c;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.p;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.StateView;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AlbumDetailFragment extends ImageHeaderMusicListFragment implements b {
    public static final int TYPE_ORIGIN_OTHER = 0;
    public static final int TYPE_ORIGIN_SINGER = 1;
    private AlbumData mAlbumData;
    private a mAlbumDetailHeader;
    private long mAlbumId;
    private Handler mHandler = new Handler();
    private OnClickListener mHeaderButtonClickListener = new OnClickListener(this) {
        final /* synthetic */ AlbumDetailFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (!y.a()) {
                switch (view.getId()) {
                    case R.id.post_header_back:
                    case R.id.post_header_title:
                        this.a.onBackPressed();
                        return;
                    case R.id.post_header_play:
                        if (!this.a.isSongListNotLoaded()) {
                            this.a.playAlbum();
                            return;
                        }
                        return;
                    case R.id.post_header_user_click_bounds:
                        this.a.gotoSingerDetailFragment();
                        return;
                    case R.id.post_header_tweet_bounds:
                        this.a.createSUserEvent(r.ACTION_CLICK_ENTRY_ALBUM_DESCRIPTION, s.PAGE_ALBUM_DESCRIPTION.getValue()).post();
                        BaseFragment instantiate = AlbumDescriptionFragment.instantiate(this.a.mAlbumData);
                        instantiate.setArguments(this.a.getArguments());
                        this.a.launchFragment(instantiate);
                        return;
                    case R.id.post_header_download:
                        if (!this.a.isSongListNotLoaded()) {
                            this.a.downloadMediaList();
                            return;
                        }
                        return;
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        }
    };
    private int mOriginType = 0;
    private b mSecondLoadView;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateContentView(layoutInflater, viewGroup, bundle);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlbumId = getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID, 0);
        this.mOriginType = getArguments().getInt(SocialConstants.PARAM_TYPE, 0);
        if (this.mAlbumId <= 0 && a.j()) {
            throw new IllegalArgumentException("album id is null");
        }
    }

    protected void setupListHeader() {
        this.mAlbumDetailHeader = new a(getActivity(), getLayoutInflater(null), this.mOnlineMediaListFragment.getListView());
        this.mOnlineMediaListFragment.getListView().addHeaderView(this.mAlbumDetailHeader.b());
        this.mAlbumDetailHeader.a(this.mHeaderButtonClickListener);
        this.mSecondLoadView = new b(getLayoutInflater(null), null);
        this.mOnlineMediaListFragment.getListView().addFooterView(this.mSecondLoadView.a());
    }

    protected String onLoadTitleText() {
        return "";
    }

    protected String onLoadStatisticModule() {
        return null;
    }

    protected void onNewBundle(Bundle bundle) {
        this.mAlbumId = bundle.getLong(StarCategory.KEY_STAR_CATEGORY_ID, 0);
        doGetAlbumDetail();
    }

    protected void requestDataList(int i) {
        super.requestDataList(i);
        doGetAlbumDetail();
    }

    protected void doGetAlbumDetail() {
        this.mOnlineMediaListFragment.setTotal(1);
        if (this.mAlbumId > 0) {
            requestAlbumDetail(this.mAlbumId);
        }
    }

    protected void beforeOnlineFragmentOnMediaItemClicked(MediaItem mediaItem) {
    }

    private void bindHeadView(AlbumData albumData) {
        if (getArguments() == null || m.a(getArguments().getString("title"))) {
            setTitle(albumData.getName());
        }
        BaseFragment topFragment = ((BaseActivity) getActivity()).getTopFragment();
        topFragment.updateAlibabaProperty("songlist_name", albumData.getName());
        topFragment.updateAlibabaProperty("singer_id", String.valueOf(albumData.getSingerId()));
        this.mAlbumDetailHeader.a(albumData);
        setPlayingGroupName(c.c(String.valueOf(albumData.getId())));
        updatePlayStatus(e.a(getActivity()).n());
        StateView stateView = this.mOnlineMediaListFragment.getStateView();
        if (stateView != null) {
            stateView.setState(StateView.b.SUCCESS);
        }
        this.mSecondLoadView.a(false, 0, getString(R.string.loading));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ALBUM_DETAIL_BY_ID, i.a(cls, "updateAlbumDetail", AlbumResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mAlbumDetailHeader != null) {
            this.mAlbumDetailHeader.a();
        }
    }

    private String getRequestId() {
        return toString() + this.mAlbumId;
    }

    public void doStatistic(MediaItem mediaItem, int i) {
        createSUserEvent(r.ACTION_CLICK_PLAY_ITEM_ALBUM_DETAIL, 0).append("position", Integer.valueOf(i + 1)).append(MediasColumns.SONG_ID, mediaItem.getSongID()).post();
    }

    protected void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
    }

    private void updateAlbumSong(final List<OnlineSongItem> list) {
        if (list == null || list.isEmpty()) {
            showAlbumNetworkError();
        } else {
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ AlbumDetailFragment b;

                public void run() {
                    final ArrayList a = p.a(list);
                    this.b.mHandler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            if (this.b.b.isViewAccessAble() && !j.a(a)) {
                                this.b.b.updateData(a, Integer.valueOf(1));
                                this.b.b.mSecondLoadView.a(false, 8, "");
                                this.b.b.mOnlineMediaListFragment.getListView().removeFooterView(this.b.b.mSecondLoadView.a());
                            }
                        }
                    });
                }
            });
        }
    }

    private void requestAlbumDetail(long j) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_ALBUM_DETAIL_BY_ID, Long.valueOf(j), getRequestId()));
    }

    public void updateAlbumDetail(AlbumResult albumResult, String str) {
        if (!str.equals(getRequestId())) {
            return;
        }
        if (albumResult.isSuccess()) {
            this.mAlbumData = (AlbumData) albumResult.getData();
            if (this.mAlbumData != null) {
                bindHeadView(this.mAlbumData);
                updateAlbumSong(this.mAlbumData.getSongList());
                return;
            }
            return;
        }
        showAlbumNetworkError();
    }

    private void gotoSingerDetailFragment() {
        int singerId = (int) this.mAlbumData.getSingerId();
        SingerDetailFragment.launch((BaseActivity) getActivity(), m.a(this.mAlbumData.getSingerName()) ? "" : this.mAlbumData.getSingerName(), singerId, true, this.mAlbumData.getSingerSFlag());
        createSUserEvent(r.ACTION_CLICK_SINGER_ALBUM_DETAIL, s.PAGE_SINGER_MESSAGE.getValue()).append("song_album_id", Long.valueOf(this.mAlbumId)).append("singer_id", Integer.valueOf(singerId)).post();
    }

    private void showAlbumNetworkError() {
        updateData(null, Integer.valueOf(1));
    }

    private void playAlbum() {
        createSUserEvent(r.ACTION_CLICK_PLAY_ALL_ALBUM_DETAIL, 0).post();
        replayPlayMediaRepeat(0);
    }

    private void downloadMediaList() {
        createSUserEvent(r.ACTION_CLICK_DOWNLOAD_ALBUM_DETAIL, 0).post();
        new com.sds.android.ttpod.component.c.b(getActivity()).a(this.mediaItemList);
    }

    private SUserEvent createSUserEvent(r rVar, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), 0, i);
        sUserEvent.setPageParameter(true);
        return sUserEvent;
    }
}
