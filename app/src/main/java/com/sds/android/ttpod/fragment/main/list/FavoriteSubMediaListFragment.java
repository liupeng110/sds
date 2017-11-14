package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.c;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteSubMediaListFragment extends SubMediaListFragment {
    public static final String TAG = "FavoriteSubMediaListFragment";

    public static class FavoriteSongFragment extends MediaListFragment {
        private Animation mDownloadAnimation;
        private Map<MediaItem, TaskInfo> mDownloadMediaItemTaskInfoMap = new HashMap();
        private boolean mHasNotified = false;
        private View mHeaderView;
        private boolean mIsRefreshing = false;

        protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
            super.onLoadCommandMap(map);
            Class cls = getClass();
            map.put(a.UPDATE_DOWNLOAD_TASK_STATE, i.a(cls, "updateDownloadState", DownloadTaskInfo.class));
            map.put(a.PULL_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, i.a(cls, "pullFavoriteOnlineMediaListComplete", new Class[0]));
            map.put(a.PULL_FAVORITE_ONLINE_MEDIA_LIST_ERROR, i.a(cls, "refreshFavoriteOnlineFailed", new Class[0]));
            map.put(a.PUSH_FAVORITE_ONLINE_MEDIA_LIST_ERROR, i.a(cls, "refreshFavoriteOnlineFailed", new Class[0]));
            map.put(a.PUSH_FAVORITE_ONLINE_MEDIA_LIST_INVALID, i.a(cls, "refreshFavoriteOnlineFailed", new Class[0]));
            map.put(a.UPDATE_FAVORITE_CHANGED, i.a(cls, "updateFavoriteChanged", new Class[0]));
            map.put(a.UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE, i.a(cls, "updateDownloadTaskInfoList", List.class));
            map.put(a.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, i.a(cls, "updateAddDownloadTaskDatabase", DownloadTaskInfo.class));
        }

        public void pullFavoriteOnlineMediaListComplete() {
            f.a((int) R.string.sync_favorite_songs_complete);
            this.mIsRefreshing = false;
        }

        public void refreshFavoriteOnlineFailed() {
            if (!this.mHasNotified) {
                this.mHasNotified = true;
                f.a((int) R.string.sync_favorite_songs_failed);
            }
            this.mIsRefreshing = false;
        }

        public void updateDownloadState(DownloadTaskInfo downloadTaskInfo) {
            g.c(FavoriteSubMediaListFragment.TAG, "updateDownloadState = " + downloadTaskInfo.getState());
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) && (downloadTaskInfo.getTag() instanceof MediaItem) && b.av() && getMediaItemList() != null && (getMediaItemList() instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) getMediaItemList()).isLoadFinished()) {
                notifyDataSetChanged();
            }
        }

        public void updateFavoriteChanged() {
            g.c(FavoriteSubMediaListFragment.TAG, "updateFavoriteChanged");
            if (getGroupID().equals(MediaStorage.GROUP_ID_FAV)) {
                onReloadData();
            } else {
                notifyDataSetChanged();
            }
        }

        protected void updateMediaList(List<MediaItem> list) {
            this.mDownloadMediaItemTaskInfoMap = (Map) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.QUERY_DOWNLOADING_INFO_BY_GROUP, getGroupID()), Map.class);
            super.updateMediaList(list);
            sendFavCountStats(list);
        }

        private void sendFavCountStats(List<MediaItem> list) {
            if (list != null && list.size() != 0) {
                c.a("my_favorite", String.valueOf(list.size()));
            }
        }

        public void updateDownloadTaskInfoList(List<DownloadTaskInfo> list) {
            this.mDownloadMediaItemTaskInfoMap = (Map) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.QUERY_DOWNLOADING_INFO_BY_GROUP, getGroupID()), Map.class);
            notifyDataSetChanged();
        }

        public void updateFavoriteOnlineDownloadState(DownloadTaskInfo downloadTaskInfo) {
            g.c(FavoriteSubMediaListFragment.TAG, "updateFavoriteOnlineDownloadState");
            if (this.mListView != null && getMediaItemList() != null && downloadTaskInfo != null && downloadTaskInfo.getGroupId().equals(getGroupID())) {
                this.mDownloadMediaItemTaskInfoMap = (Map) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.QUERY_DOWNLOADING_INFO_BY_GROUP, downloadTaskInfo.getGroupId()), Map.class);
                if (downloadTaskInfo.getState() != null && 4 == downloadTaskInfo.getState().intValue() && (getMediaItemList() instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) getMediaItemList()).isLoadFinished()) {
                    int indexOf = getMediaItemList().indexOf(downloadTaskInfo.getTag());
                    if (indexOf >= 0) {
                        ((MediaItem) getMediaItemList().get(indexOf)).setLocalDataSource(downloadTaskInfo.getSavePath());
                    } else {
                        return;
                    }
                }
                MediaItem mediaItem = (MediaItem) downloadTaskInfo.getTag();
                int firstVisiblePosition = this.mListView.getFirstVisiblePosition();
                while (firstVisiblePosition <= this.mListView.getLastVisiblePosition()) {
                    int headerViewsCount = firstVisiblePosition - this.mListView.getHeaderViewsCount();
                    if (headerViewsCount < 0 || headerViewsCount >= getMediaItemList().size() || !((MediaItem) getMediaItemList().get(headerViewsCount)).equals(mediaItem)) {
                        firstVisiblePosition++;
                    } else {
                        notifyDataSetChanged();
                        return;
                    }
                }
            }
        }

        public void updateAddDownloadTaskDatabase(DownloadTaskInfo downloadTaskInfo) {
            if (DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType()) && (downloadTaskInfo.getTag() instanceof MediaItem) && b.av() && getMediaItemList() != null && (getMediaItemList() instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) getMediaItemList()).isLoadFinished()) {
                if (this.mDownloadMediaItemTaskInfoMap == null) {
                    this.mDownloadMediaItemTaskInfoMap = new ArrayMap();
                }
                this.mDownloadMediaItemTaskInfoMap.put((MediaItem) downloadTaskInfo.getTag(), downloadTaskInfo);
                notifyDataSetChanged();
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            getListView().addHeaderView(getHeaderView());
            if (String.valueOf(s.PAGE_MY_FAVORITE.getValue()).equals(getStatisitcPage())) {
                setNeedCountStastic();
            }
            return onCreateView;
        }

        public void onThemeLoaded() {
            super.onThemeLoaded();
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mHeaderView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mHeaderView.findViewById(R.id.textview_online_favorite_all_download), ThemeElement.SONG_LIST_ITEM_TEXT);
            v.a((IconTextView) this.mHeaderView.findViewById(R.id.itv_download), ThemeElement.SONG_LIST_ITEM_TEXT);
        }

        public void updateMediaLibraryChanged(String str) {
        }

        protected View getHeaderView() {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.favorite_online_header, null);
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FavoriteSongFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    com.sds.android.ttpod.component.c.b bVar = new com.sds.android.ttpod.component.c.b(this.a.getActivity());
                    String statisitcPage = this.a.getStatisitcPage();
                    if (String.valueOf(s.PAGE_MY_FAVORITE.getValue()).equals(statisitcPage)) {
                        t.a(r.ACTION_FAVORITE_CLICK_DOWNLOAD_ALL, s.PAGE_NONE);
                    } else if (String.valueOf(s.PAGE_MY_SONGLIST_ONLINE_DETAIL.getValue()).equals(statisitcPage)) {
                        t.a(r.ACTION_SONG_LIST_ONLINE_DETAIL_ALL_DOWNLOAD, s.PAGE_NONE);
                    }
                    com.sds.android.ttpod.framework.a.b.b.a("local_download_all");
                    bVar.a(this.a.getMediaItemList());
                }
            });
            this.mHeaderView = inflate;
            return inflate;
        }

        public void sync() {
            if (!getGroupID().equals(MediaStorage.GROUP_ID_FAV)) {
                onReloadData();
            } else if (!EnvironmentUtils.c.e()) {
                f.a((int) R.string.network_unavailable);
            } else if (!this.mIsRefreshing) {
                this.mIsRefreshing = true;
                this.mHasNotified = false;
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SYNC_FAVORITE_ONLINE_MEDIA_LIST, new Object[0]));
                f.a((int) R.string.sync_favorite_songs_start);
            }
        }

        protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
            View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
            bindDownloadState(((com.sds.android.ttpod.component.b.g) mediaItemView.getTag()).i(), mediaItem);
            return mediaItemView;
        }

        protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
            if (iconTextView != null) {
                iconTextView.setText((int) R.string.icon_no_favorite);
            }
            if (textView != null) {
                textView.setText(R.string.no_song_favorite);
            }
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
        }

        private void bindDownloadState(IconTextView iconTextView, MediaItem mediaItem) {
            iconTextView.clearAnimation();
            iconTextView.setVisibility(0);
            if (!m.a(mediaItem.getLocalDataSource())) {
                if (e.a(mediaItem.getLocalDataSource())) {
                    flushDownloadStateView(iconTextView, R.string.icon_download_downloaded);
                    return;
                }
                mediaItem.setLocalDataSource("");
            }
            TaskInfo taskInfo = (TaskInfo) this.mDownloadMediaItemTaskInfoMap.get(mediaItem);
            if (taskInfo == null) {
                iconTextView.setVisibility(8);
            } else if (taskInfo.getState() == null) {
                flushDownloadStateView(iconTextView, R.string.icon_download_download);
            } else {
                int i;
                switch (taskInfo.getState().intValue()) {
                    case 2:
                        this.mDownloadAnimation = this.mDownloadAnimation == null ? AnimationUtils.loadAnimation(getActivity(), R.anim.rotate) : this.mDownloadAnimation;
                        iconTextView.startAnimation(this.mDownloadAnimation);
                        i = R.string.icon_download_downloading;
                        break;
                    case 3:
                        i = R.string.icon_download_download;
                        break;
                    case 4:
                        mediaItem.setLocalDataSource(taskInfo.getSavePath());
                        this.mDownloadMediaItemTaskInfoMap.remove(mediaItem);
                        i = R.string.icon_download_downloaded;
                        break;
                    case 5:
                        i = R.string.icon_download_error;
                        break;
                    default:
                        i = R.string.icon_female;
                        break;
                }
                flushDownloadStateView(iconTextView, i);
            }
        }
    }

    protected String selectMediaListFragmentClassName() {
        return FavoriteSongFragment.class.getName();
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        com.sds.android.ttpod.framework.a.b.b.a("my_menu");
        t.a(r.ACTION_OPEN_LOCAL_DROP_MENU, s.PAGE_NONE);
        Collection arrayList = new ArrayList();
        arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_title).a(Integer.valueOf(7)));
        arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_artist).a(Integer.valueOf(8)));
        arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_add_time).a(Integer.valueOf(10)));
        return arrayList;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        switch (i) {
            case 6:
                l.ak();
                return;
            default:
                return;
        }
    }
}
