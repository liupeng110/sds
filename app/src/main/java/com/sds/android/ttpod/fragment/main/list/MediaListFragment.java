package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.local.MediaItemSearchActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.common.c.c;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.b;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList.OnLoadFinishedListener;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MediaListFragment extends AbsMediaListFragment implements a, b, c {
    private static final int COLOR_DOWNLOAD = -14304298;
    private static final int COLOR_DOWNLOADED = -9707185;
    private static final int COLOR_DOWNLOADING = -2327585;
    private static final int COLOR_DOWNLOAD_ERROR = -383198;
    private static final ArrayList<a> GROUP_PAGE_LIST = new ArrayList();
    private static final String TAG = "MediaListFragment";
    private OnLoadFinishedListener mAttachFavOnLoadFinishedListener = new OnLoadFinishedListener(this) {
        final /* synthetic */ MediaListFragment a;

        {
            this.a = r1;
        }

        public void onLoadFinished() {
            this.a.attachFavoriteState();
        }
    };
    private boolean mAutoSelectPlayingMedia = true;
    private boolean mDisplayMenu = true;
    private com.sds.android.ttpod.fragment.main.list.a.a mEditRequestListener;
    private boolean mIsEditing = false;
    private boolean mNeedCountStastic;
    private int mNoDataIconResId = R.string.icon_no_song;
    private int mNoDataMessageResId = R.string.no_song_local_music;
    private OnClickListener mNoDataViewOnClickListener;
    private OnLoadFinishedListener mOnBuildAZKeysLoadFinishedListener = new OnLoadFinishedListener(this) {
        final /* synthetic */ MediaListFragment a;

        {
            this.a = r1;
        }

        public void onLoadFinished() {
            if (this.a.isAZSideBarEnable()) {
                this.a.updateAZKeys(this.a.buildAZKeys(this.a.getMediaItemList()));
            }
        }
    };
    private String mOrderBy;
    private String mPlayingGroupID;
    private String mPlayingMediaID;
    private Map<Integer, MediaItem> mSelectMediaItemHashMap = new LinkedHashMap();
    private b mTaskCountChangeListener;

    private static class a {
        private String a;
        private s b;

        public a(String str, s sVar) {
            this.a = str;
            this.b = sVar;
        }
    }

    static {
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_ALL_LOCAL, s.PAGE_LOCAL_SONG));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_ARTIST_PREFIX, s.PAGE_LOCAL_ARTIST_DETAIL));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_ALBUM_PREFIX, s.PAGE_LOCAL_ALBUM_DETAIL));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_FOLDER_PREFIX, s.PAGE_LOCAL_FOLDER_DETAIL));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_RECENTLY_ADD, s.PAGE_RECENT_ADDED));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_RECENTLY_PLAY, s.PAGE_RECENT_PLAY));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_FAV, s.PAGE_MY_FAVORITE));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_DOWNLOAD, s.PAGE_MY_DOWNLOAD_DOWNLOADED));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX, s.PAGE_MY_SONGLIST_ONLINE_DETAIL));
        GROUP_PAGE_LIST.add(new a(MediaStorage.GROUP_ID_CUSTOM_PREFIX, s.PAGE_MY_SONGLIST_LOCAL_DETAIL));
    }

    public void setOnTaskCountChangeListener(b bVar) {
        this.mTaskCountChangeListener = bVar;
    }

    private final void notifyTaskListChanged() {
        if (this.mTaskCountChangeListener != null) {
            this.mTaskCountChangeListener.a(this.mMediaItemList == null ? 0 : this.mMediaItemList.size());
        }
    }

    protected void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        notifyTaskListChanged();
    }

    public void setEditRequestListener(com.sds.android.ttpod.fragment.main.list.a.a aVar) {
        this.mEditRequestListener = aVar;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    protected void setAutoSelectPlayingMedia(boolean z) {
        this.mAutoSelectPlayingMedia = z;
    }

    public void setNeedCountStastic() {
        this.mNeedCountStastic = true;
    }

    public static s pageFromGroupId(String str) {
        s sVar = s.PAGE_NONE;
        Iterator it = GROUP_PAGE_LIST.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (str.startsWith(aVar.a)) {
                return aVar.b;
            }
        }
        return sVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        assertGroupID();
        loadCacheInfo();
        setStatisticPage(pageFromGroupId(this.mGroupID));
    }

    public void onResume() {
        super.onResume();
        if (this.mAutoSelectPlayingMedia && m.a(com.sds.android.ttpod.framework.storage.environment.b.m(), this.mGroupID)) {
            int s = e.a(getActivity()).s();
            selectRow(s);
            if (this.mAZSideBar != null) {
                this.mAZSideBar.a(s);
            }
        }
        if (this.mGroupID.equals(MediaStorage.GROUP_ID_DOWNLOAD)) {
            l.aA();
        }
    }

    private void assertGroupID() {
        d.a(this.mGroupID, "mGroupID");
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ASYNCLOAD_MEDIA_ITEM_LIST, i.a(cls, "updateAsyncloadMediaItemList", String.class, AsyncLoadMediaItemList.class));
        map.put(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEMS_FINISHED, i.a(cls, "onDeleteMediaItemsFinished", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, i.a(cls, "onScanFinished", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, i.a(cls, "updateMediaLibraryChanged", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM_FINISHED, i.a(cls, "updateMediaItemFinished", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, i.a(cls, "updateSaveEffectToLocal", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DELETE_PRIVATE_EFFECT_LIST, i.a(cls, "updateDeletePrivateEffectList", new Class[0]));
    }

    public void updateSaveEffectToLocal(Boolean bool) {
        if (bool.booleanValue()) {
            notifyDataSetChanged();
        }
    }

    public void updateDeletePrivateEffectList() {
        notifyDataSetChanged();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        loadAZKeys(getMediaItemList());
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, null);
    }

    protected void configFailedView(View view) {
        configNoDataView((IconTextView) view.findViewById(R.id.no_media_icon), (TextView) view.findViewById(R.id.textview_load_failed), (TextView) view.findViewById(R.id.no_data_action_view));
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setOnClickListener(this.mNoDataViewOnClickListener);
        textView.setOnClickListener(this.mNoDataViewOnClickListener);
        iconTextView.setText(this.mNoDataIconResId);
        textView.setText(c.a(getString(this.mNoDataMessageResId), getResources().getColor(R.color.not_data_hint_text)));
        textView2.setVisibility(this.mDisplayMenu ? 0 : 4);
        if (m.a(this.mGroupID, MediaStorage.GROUP_ID_ALL_LOCAL)) {
            textView2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MediaListFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    t.a(r.ACTION_LOCAL_SCAN_MUSIC_WHEN_NO_SONG, s.PAGE_SCAN_MUSIC);
                    this.a.startActivity(new Intent(this.a.getActivity(), MediaScanAnimationActivity.class));
                }
            });
        }
    }

    public void setNoDataViewOnClickListener(OnClickListener onClickListener) {
        this.mNoDataViewOnClickListener = onClickListener;
    }

    public void setNoDataViewMessage(int i, int i2) {
        this.mNoDataIconResId = i;
        this.mNoDataMessageResId = i2;
        this.mDisplayMenu = false;
    }

    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.listview_footer_local_media, null);
    }

    protected void configListFooterView(View view) {
        if (view instanceof TextView) {
            int size;
            List mediaItemList = getMediaItemList();
            TextView textView = (TextView) view;
            Object[] objArr = new Object[1];
            if (mediaItemList != null) {
                size = mediaItemList.size();
            } else {
                size = 0;
            }
            objArr[0] = Integer.valueOf(size);
            textView.setText(getString(R.string.count_of_media, objArr));
        }
    }

    protected void onDataSetChanged() {
        super.onDataSetChanged();
        loadCacheInfo();
    }

    private void loadCacheInfo() {
        if (this.mGroupID != null) {
            this.mPlayingGroupID = com.sds.android.ttpod.framework.storage.environment.b.m();
            this.mPlayingMediaID = com.sds.android.ttpod.framework.storage.environment.b.n();
            this.mOrderBy = com.sds.android.ttpod.framework.storage.environment.b.l(this.mGroupID);
        }
    }

    protected void onReloadData() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_ASYNCLOAD_MEDIA_ITEM_LIST, this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.l(this.mGroupID)));
    }

    public void putSelectedMediaItem(Collection<MediaItem> collection) {
        if (collection != null && collection.size() > 0) {
            for (MediaItem mediaItem : collection) {
                this.mSelectMediaItemHashMap.put(Integer.valueOf(mediaItem.hashCode()), mediaItem);
            }
            tryNotifySelectedCountChanged();
            notifyDataSetChanged();
        }
    }

    protected void onMediaItemClicked(MediaItem mediaItem, int i) {
        p.a();
        com.sds.android.ttpod.b.m.a(this.mListView);
        if (this.mIsEditing) {
            int hashCode = mediaItem.hashCode();
            if (this.mSelectMediaItemHashMap.containsKey(Integer.valueOf(hashCode))) {
                this.mSelectMediaItemHashMap.remove(Integer.valueOf(hashCode));
            } else {
                this.mSelectMediaItemHashMap.put(Integer.valueOf(hashCode), mediaItem);
            }
            tryNotifySelectedCountChanged();
            notifyDataSetChanged();
        } else if (m.a(this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.m()) && m.a(mediaItem.getID(), com.sds.android.ttpod.framework.storage.environment.b.n())) {
            PlayStatus n = e.a(getActivity()).n();
            if (n == PlayStatus.STATUS_PAUSED) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
            } else if (n == PlayStatus.STATUS_PLAYING) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
            } else {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
            }
            com.sds.android.ttpod.framework.a.b.b.a("local_songlist_item_play");
        } else {
            Object title;
            if (mediaItem.isOnline()) {
                title = mediaItem.getTitle();
            } else {
                title = com.sds.android.sdk.lib.util.e.j(mediaItem.getLocalDataSource());
            }
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_LOCAL_CLICK_SONG_LISTEN.getValue(), 0, 0);
            sUserEvent.setPageParameter(true);
            sUserEvent.append("position", Integer.valueOf(i + 1)).append("song_name", title).append(MediasColumns.SONG_ID, mediaItem.getSongID()).post();
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, this.mGroupID, mediaItem));
            com.sds.android.ttpod.framework.a.b.b.a("local_songlist_item_play");
        }
    }

    protected boolean isAZSideBarEnable() {
        if (this.mIsEditing || getMediaItemList() == null || getMediaItemList().size() <= 20) {
            return false;
        }
        if (!this.mGroupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) && !this.mGroupID.equals(MediaStorage.GROUP_ID_FAV) && !this.mGroupID.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) && !this.mGroupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            return false;
        }
        String l = com.sds.android.ttpod.framework.storage.environment.b.l(this.mGroupID);
        if (l.equals("title_key") || l.equals("artist_key")) {
            return true;
        }
        return false;
    }

    protected boolean needFailedState() {
        return this.mGroupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_PLAY) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_ADD) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_FAV) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_DOWNLOAD) || this.mGroupID.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX);
    }

    public void updateAsyncloadMediaItemList(String str, AsyncLoadMediaItemList asyncLoadMediaItemList) {
        if (m.a(str, this.mGroupID) && this.mMediaItemList != asyncLoadMediaItemList) {
            asyncLoadMediaItemList.addRef();
            updateMediaList(asyncLoadMediaItemList);
            loadAZKeys(asyncLoadMediaItemList);
            if (this.mNeedCountStastic) {
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", countAction().getValue(), 0, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.append("song_count", Integer.valueOf(asyncLoadMediaItemList.size()));
                sUserEvent.post();
            }
            if (!asyncLoadMediaItemList.isLoadFinished()) {
                asyncLoadMediaItemList.addLoadFinishedListener(this.mAttachFavOnLoadFinishedListener);
            }
        }
    }

    private r countAction() {
        r rVar = r.ACTION_NONE;
        String statisitcPage = getStatisitcPage();
        if (String.valueOf(s.PAGE_LOCAL_SONG.getValue()).equals(statisitcPage)) {
            return r.ACTION_AMOUNT_LOCAL_SONG;
        }
        if (String.valueOf(s.PAGE_MY_FAVORITE.getValue()).equals(statisitcPage)) {
            return r.ACTION_AMOUNT_FAVORITE;
        }
        return rVar;
    }

    public void updateMediaItemFinished(MediaItem mediaItem) {
        if (getMediaItemList() != null && getMediaItemList().contains(mediaItem)) {
            notifyDataSetChanged();
        }
    }

    private void loadAZKeys(List<MediaItem> list) {
        if (list != null && isViewAccessAble() && (list instanceof AsyncLoadMediaItemList)) {
            AsyncLoadMediaItemList asyncLoadMediaItemList = (AsyncLoadMediaItemList) list;
            if (!isAZSideBarEnable()) {
                return;
            }
            if (asyncLoadMediaItemList.isLoadFinished()) {
                updateAZKeys(buildAZKeys(asyncLoadMediaItemList));
            } else {
                updateAZKeys(buildAZKeys(asyncLoadMediaItemList));
            }
        }
    }

    private List<String> buildAZKeys(List<MediaItem> list) {
        List arrayList = new ArrayList(list.size());
        if (this.mGroupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) && "artist_key".equals(com.sds.android.ttpod.framework.storage.environment.b.l(this.mGroupID))) {
            for (MediaItem artist : list) {
                arrayList.add(artist.getArtist());
            }
        } else {
            for (MediaItem artist2 : list) {
                arrayList.add(artist2.getTitle());
            }
        }
        return arrayList;
    }

    public void onDeleteMediaItemsFinished(String str) {
    }

    public void updateMediaLibraryChanged(String str) {
        if (m.a(str, this.mGroupID) || str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
            onReloadData();
        }
    }

    public void playMediaChanged() {
        if (MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(this.mGroupID)) {
            onReloadData();
        }
        if (m.a(this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.m())) {
            notifyDataSetChanged();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (m.a(this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.m())) {
            notifyDataSetChanged();
        }
    }

    public void onScanFinished(Integer num) {
        onReloadData();
    }

    public Collection<MediaItem> getSelectedMediaItems() {
        return this.mSelectMediaItemHashMap.values();
    }

    protected View getMediaItemView(final MediaItem mediaItem, View view, ViewGroup viewGroup, final int i) {
        View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
        if (getActivity() == null) {
            return mediaItemView;
        }
        g gVar = (g) mediaItemView.getTag();
        CharSequence j = (m.a(this.mOrderBy, MediaStorage.MEDIA_ORDER_BY_FILE_NAME) || m.a(this.mOrderBy, MediaStorage.MEDIA_ORDER_BY_FILE_NAME_DESC)) ? com.sds.android.sdk.lib.util.e.j(mediaItem.getLocalDataSource()) : TTTextUtils.validateString(mediaItemView.getContext(), mediaItem.getArtist());
        gVar.a(j, 0, false);
        bindView(gVar, mediaItem, this.mIsEditing);
        gVar.l().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MediaListFragment c;

            public void onClick(View view) {
                this.c.onMediaItemClicked(mediaItem, i);
            }
        });
        gVar.l().setContentDescription(getString(R.string.media_item_check_view) + i);
        if (MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(this.mGroupID)) {
            bindRecentlyPlayDownloadState(gVar.i(), (MediaItem) getMediaItemList().get(i));
        }
        return mediaItemView;
    }

    private void bindRecentlyPlayDownloadState(IconTextView iconTextView, MediaItem mediaItem) {
        int i;
        int i2 = 0;
        if (m.a(mediaItem.getLocalDataSource())) {
            i = 0;
        } else {
            i = 1;
        }
        if (i == 0) {
            i2 = 8;
        }
        iconTextView.setVisibility(i2);
        flushDownloadStateView(iconTextView, R.string.icon_download_downloaded);
    }

    protected void flushDownloadStateView(IconTextView iconTextView, int i) {
        iconTextView.setText(i);
        int i2 = COLOR_DOWNLOAD;
        switch (i) {
            case R.string.icon_download_downloaded:
                i2 = COLOR_DOWNLOADED;
                break;
            case R.string.icon_download_downloading:
                i2 = COLOR_DOWNLOADING;
                break;
            case R.string.icon_download_error:
                i2 = COLOR_DOWNLOAD_ERROR;
                break;
        }
        iconTextView.setTextColor(i2);
    }

    protected void bindView(g gVar, MediaItem mediaItem, boolean z) {
        boolean z2 = true;
        gVar.a(mediaItem.getErrorStatus());
        if (z) {
            gVar.b().setSelected(false);
            gVar.g().setVisibility(8);
            gVar.d().setVisibility(8);
            gVar.c().setVisibility(4);
            gVar.l().setVisibility(0);
            if (this.mSelectMediaItemHashMap.get(Integer.valueOf(mediaItem.hashCode())) == null) {
                z2 = false;
            }
            gVar.l().setChecked(z2);
            gVar.b().setSelected(z2);
            return;
        }
        gVar.l().setVisibility(8);
        gVar.g().setVisibility(0);
        gVar.d().setVisibility(0);
        gVar.c().setVisibility(0);
        gVar.a(mediaItem);
        if (!(m.a(this.mGroupID, this.mPlayingGroupID) && m.a(this.mPlayingMediaID, mediaItem.getID()))) {
            z2 = false;
        }
        gVar.j().setSelected(z2);
        gVar.k().setSelected(z2);
        gVar.b().setSelected(z2);
        gVar.a(z2, this.mPlayStatus);
    }

    public void selectAll() {
        List<MediaItem> mediaItemList = getMediaItemList();
        this.mSelectMediaItemHashMap.clear();
        for (MediaItem mediaItem : mediaItemList) {
            this.mSelectMediaItemHashMap.put(Integer.valueOf(mediaItem.hashCode()), mediaItem);
        }
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
        t.a(r.ACTION_BATCH_OPERATE_CHOOSE_ALL, s.PAGE_NONE);
    }

    public void selectNone() {
        this.mSelectMediaItemHashMap.clear();
        tryNotifySelectedCountChanged();
        notifyDataSetChanged();
    }

    public void startEdit() {
        this.mIsEditing = true;
        notifyDataSetChanged();
        updateAZSideBar();
    }

    public void stopEdit() {
        this.mIsEditing = false;
        this.mSelectMediaItemHashMap.clear();
        notifyDataSetChanged();
        updateAZSideBar();
    }

    public boolean isEditing() {
        return this.mIsEditing;
    }

    public int selectedCount() {
        return this.mSelectMediaItemHashMap.size();
    }

    public int totalCount() {
        return isEmpty() ? 0 : getMediaItemList().size();
    }

    public void addTo() {
        f.a(getActivity(), com.sds.android.ttpod.framework.storage.a.a.a().j(), this.mSelectMediaItemHashMap.values(), new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ MediaListFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                this.a.tryNotifyStopEditRequested();
            }
        }, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
            final /* synthetic */ MediaListFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                this.a.tryNotifyStopEditRequested();
            }
        });
    }

    public void sendTo() {
        int size = this.mSelectMediaItemHashMap.size();
        if (size > 0) {
            File[] fileArr = new File[size];
            int i = 0;
            for (MediaItem mediaItem : this.mSelectMediaItemHashMap.values()) {
                if (mediaItem.getLocalDataSource() != null) {
                    int i2 = i + 1;
                    fileArr[i] = new File(mediaItem.getLocalDataSource());
                    size = i2;
                } else {
                    size = i;
                }
                i = size;
            }
            com.sds.android.ttpod.b.c.a(getActivity(), fileArr);
            tryNotifyStopEditRequested();
        }
    }

    public void remove() {
        f.a(getActivity(), this.mSelectMediaItemHashMap.values(), this.mGroupID, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.i>(this) {
            final /* synthetic */ MediaListFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.i iVar) {
                t.a(r.ACTION_BATCH_OPERATE_REMOVE_SURE, s.PAGE_NONE);
                this.a.getMediaItemList().removeAll(this.a.mSelectMediaItemHashMap.values());
                this.a.updateMediaList(this.a.getMediaItemList());
                this.a.tryNotifyStopEditRequested();
            }
        });
    }

    public void removeAll() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM_LIST, this.mGroupID, getMediaItemList(), Boolean.valueOf(false)));
    }

    public void search() {
        com.sds.android.ttpod.framework.a.b.b.a("my_search_button");
        t.a(r.ACTION_LOCAL_SEARCH, s.PAGE_NONE);
        startActivity(new Intent(getActivity(), MediaItemSearchActivity.class).putExtra(AbsMediaListFragment.KEY_GROUP_ID, this.mGroupID).putExtra("origin", getString(R.string.music)));
    }

    public void order() {
        f.a(getActivity(), this.mGroupID, new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ MediaListFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                this.a.onReloadData();
            }
        });
    }

    public void order(int i) {
        f.a(this.mGroupID, i);
        r orderAction = GroupListFragment.orderAction(i);
        if (orderAction != null) {
            t.a(orderAction, s.PAGE_NONE);
        }
        onReloadData();
    }

    private void tryNotifySelectedCountChanged() {
        if (this.mEditRequestListener != null) {
            this.mEditRequestListener.onSelectedCountChanged();
        }
    }

    private void tryNotifyStopEditRequested() {
        if (this.mEditRequestListener != null) {
            this.mEditRequestListener.onStopEditRequested();
        }
    }

    public void onKeyPressed(int i, KeyEvent keyEvent) {
        super.onKeyPressed(i, keyEvent);
        if (getUserVisibleHint() && i == 84) {
            search();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        List mediaItemList = getMediaItemList();
        if (mediaItemList instanceof AsyncLoadMediaItemList) {
            ((AsyncLoadMediaItemList) mediaItemList).removeLoadFinishedListener(this.mAttachFavOnLoadFinishedListener);
            ((AsyncLoadMediaItemList) mediaItemList).removeLoadFinishedListener(this.mOnBuildAZKeysLoadFinishedListener);
        }
    }
}
