package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.b;
import com.sds.android.ttpod.framework.a.b.j;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class ImageHeaderMusicListFragment extends BaseFragment implements b {
    protected static final int HOME_PAGE = 1;
    private static final String TAG = "ImageHeaderMusicListFragment";
    private a mOnSetTitleListener;
    protected OnlineMediaListFragment mOnlineMediaListFragment;
    protected String mOrigin;
    private String mPlayingGroupName = "";
    private long mPlayingSongId;
    protected View mRootView;
    protected List mediaItemList;

    public interface a {
        void a(String str);
    }

    public class ImageHeaderMusicMediaListFragment extends OnlineMediaListFragment {
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            ImageHeaderMusicListFragment.this.setupListHeader();
            j.a(1);
            j.a(ImageHeaderMusicListFragment.this.onLoadTitleText());
            j.b(UUID.randomUUID().toString());
            return onCreateView;
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mListView.setFastScrollEnabled(true);
        }

        public void onThemeLoaded() {
            super.onThemeLoaded();
        }

        protected void onFavoriteChanged(MediaItem mediaItem, boolean z) {
            ImageHeaderMusicListFragment.this.doStatiticFavorite(mediaItem, z);
            j.a(mediaItem.getSongID().longValue(), p.f(), z);
        }

        protected void onMediaItemClicked(MediaItem mediaItem, int i) {
            super.onMediaItemClicked(mediaItem, i);
            ImageHeaderMusicListFragment.this.doStatisticMediaClick(mediaItem);
        }

        protected boolean onListStatistic() {
            return true;
        }

        protected void beforeOnMediaItemClicked(MediaItem mediaItem) {
            super.beforeOnMediaItemClicked(mediaItem);
            ImageHeaderMusicListFragment.this.beforeOnlineFragmentOnMediaItemClicked(mediaItem);
        }

        protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
            View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
            ImageHeaderMusicListFragment.this.onGetMediaItemView(mediaItemView, i);
            return mediaItemView;
        }
    }

    protected abstract String onLoadStatisticModule();

    protected abstract String onLoadTitleText();

    protected abstract void setupListHeader();

    public void setOnSetTitleListener(final com.sds.android.ttpod.component.a aVar) {
        if (aVar != null) {
            this.mOnSetTitleListener = new a(this) {
                final /* synthetic */ ImageHeaderMusicListFragment b;

                public void a(String str) {
                    aVar.a((CharSequence) str);
                }
            };
        }
    }

    protected void setTitle(String str) {
        if (this.mOnSetTitleListener != null) {
            this.mOnSetTitleListener.a(str);
        }
    }

    public void setPlayingGroupName(String str) {
        this.mPlayingGroupName = str;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (isViewAccessAble()) {
            MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
            if (M != null) {
                Long songID = M.getSongID();
                this.mPlayingSongId = songID == null ? 0 : songID.longValue();
            }
        }
    }

    protected boolean isPlayingItem() {
        return m.a(com.sds.android.ttpod.framework.storage.environment.b.bl(), this.mPlayingGroupName);
    }

    public void playMediaChanged() {
        updatePlayStatus(PlayStatus.STATUS_PLAYING);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
    }

    protected void requestDataList(int i) {
        if (this.mOnlineMediaListFragment.isHomePage()) {
            this.mOnlineMediaListFragment.updateStateViews(null);
        }
    }

    public void updateData(List list, Integer num) {
        if (isViewAccessAble()) {
            this.mediaItemList = list;
            this.mOnlineMediaListFragment.updateMediaList(num, list);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return onCreateContentView(layoutInflater, viewGroup, bundle);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_imageheader_musiclist, null);
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initOnlineMediaListFragment();
        setupTitleText();
    }

    public void onResume() {
        super.onResume();
        updatePlayStatus(e.a(BaseApplication.e()).n());
    }

    private void initOnlineMediaListFragment() {
        if (this.mOnlineMediaListFragment == null) {
            Bundle bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
            this.mOnlineMediaListFragment = new ImageHeaderMusicMediaListFragment();
            this.mOnlineMediaListFragment.setArguments(bundle);
            this.mOnlineMediaListFragment.setOnNextPageListener(new OnlineMediaListFragment.c(this) {
                final /* synthetic */ ImageHeaderMusicListFragment a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.requestDataList(i);
                }
            });
            this.mOnlineMediaListFragment.setOnDataRequestListener(new com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.a(this) {
                final /* synthetic */ ImageHeaderMusicListFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.requestDataList(1);
                }
            });
            this.mOnlineMediaListFragment.setOnMediaItemClickListener(this);
        }
        this.mOrigin = p.b();
        this.mOnlineMediaListFragment.setModule(onLoadStatisticModule());
        this.mOnlineMediaListFragment.setOrigin(this.mOrigin);
        getChildFragmentManager().beginTransaction().replace(R.id.content_online_media_list, this.mOnlineMediaListFragment).commitAllowingStateLoss();
    }

    protected void togglePlayMedia(long j) {
        if (!this.mOnlineMediaListFragment.isEmpty()) {
            o.a(this.mPlayingSongId, this.mOnlineMediaListFragment.getMediaItemList(), this.mPlayingGroupName, j);
        }
    }

    protected void replayPlayMediaRepeat(long j) {
        if (!this.mOnlineMediaListFragment.isEmpty()) {
            o.a(this.mOnlineMediaListFragment.getMediaItemList(), this.mPlayingGroupName, j);
            com.sds.android.ttpod.framework.storage.environment.b.a(f.REPEAT);
        }
    }

    protected void downloadAllMediaList() {
        new com.sds.android.ttpod.component.c.b(getActivity()).a(this.mOnlineMediaListFragment.getMediaItemList());
    }

    protected boolean isSongListNotLoaded() {
        return this.mOnlineMediaListFragment == null || com.sds.android.ttpod.framework.a.j.a(this.mOnlineMediaListFragment.getMediaItemList());
    }

    protected ActionExpandableListView getListView() {
        return this.mOnlineMediaListFragment.getListView();
    }

    protected void setupTitleText() {
    }

    protected void onGetMediaItemView(View view, int i) {
    }

    protected void beforeOnlineFragmentOnMediaItemClicked(MediaItem mediaItem) {
    }

    protected void doStatiticFavorite(MediaItem mediaItem, boolean z) {
        com.sds.android.ttpod.framework.a.b.o.a(z);
    }

    protected void doStatisticMediaClick(MediaItem mediaItem) {
        com.sds.android.ttpod.framework.a.b.o.c();
    }
}
