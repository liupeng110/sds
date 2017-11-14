package com.sds.android.ttpod.fragment.main.list;

import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbsMediaListFragment extends BaseFragment implements com.sds.android.ttpod.fragment.main.list.d.a, com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.a {
    public static final String KEY_FROM_GROUP = "from_group";
    public static final String KEY_GROUP_ID = "group_id";
    private static final String TAG = "AbsMediaListFragment";
    protected AZSideBar mAZSideBar;
    private DataSetObserver mDataSetObserver = new DataSetObserver(this) {
        final /* synthetic */ AbsMediaListFragment a;

        {
            this.a = r1;
        }

        public void onChanged() {
            super.onChanged();
            this.a.onDataSetChanged();
        }

        public void onInvalidated() {
            super.onInvalidated();
            this.a.onDataSetChanged();
        }
    };
    protected View mFailedView;
    protected String mGroupID;
    protected View mListFooterView;
    protected View mListHeaderView;
    protected ActionExpandableListView mListView;
    protected List<MediaItem> mMediaItemList;
    protected a mMediaListAdapter;
    protected PlayStatus mPlayStatus;
    protected StateView mStateView;

    private class a extends BaseAdapter {
        final /* synthetic */ AbsMediaListFragment a;

        private a(AbsMediaListFragment absMediaListFragment) {
            this.a = absMediaListFragment;
        }

        public Object getItem(int i) {
            return null;
        }

        public int getCount() {
            return this.a.mMediaItemList == null ? 0 : this.a.mMediaItemList.size();
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return this.a.getMediaItemView((MediaItem) this.a.mMediaItemList.get(i), view, viewGroup, i);
        }
    }

    protected abstract void configFailedView(View view);

    protected abstract void configListFooterView(View view);

    protected abstract View onCreateFailedView(LayoutInflater layoutInflater);

    protected abstract View onCreateListFooterView(LayoutInflater layoutInflater);

    protected abstract void onMediaItemClicked(MediaItem mediaItem, int i);

    protected abstract void onReloadData();

    abstract void playMediaChanged();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mGroupID = arguments.getString(KEY_GROUP_ID);
            g.a(TAG, "onCreate lookStatisticId groupid=%s", this.mGroupID);
        }
        this.mMediaListAdapter = new a();
        this.mPlayStatus = e.a(getActivity()).n();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(cls, "updatePlayingMediaInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_FAVORITE_CHANGED, i.a(cls, "updateFavoriteChanged", new Class[0]));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_media_list, viewGroup, false);
        this.mListView = (ActionExpandableListView) this.mStateView.findViewById(R.id.media_listview);
        this.mListHeaderView = onCreateListHeaderView(layoutInflater);
        if (this.mListHeaderView != null) {
            this.mListView.addHeaderView(this.mListHeaderView, null, false);
        }
        this.mListFooterView = onCreateListFooterView(layoutInflater);
        if (this.mListFooterView != null) {
            this.mListView.addFooterView(this.mListFooterView);
        }
        this.mListView.setFooterDividersEnabled(true);
        this.mListView.setHeaderDividersEnabled(true);
        this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
        this.mFailedView = onCreateFailedView(layoutInflater);
        this.mStateView.setFailedView(this.mFailedView);
        return this.mStateView;
    }

    protected View onCreateListHeaderView(LayoutInflater layoutInflater) {
        return null;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mStateView = null;
        this.mListView = null;
        this.mListFooterView = null;
        this.mFailedView = null;
        this.mAZSideBar = null;
        this.mMediaListAdapter.unregisterDataSetObserver(this.mDataSetObserver);
    }

    protected boolean isAZSideBarEnable() {
        return false;
    }

    protected void onDataSetChanged() {
    }

    protected int getCount() {
        return this.mMediaListAdapter != null ? this.mMediaListAdapter.getCount() : 0;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mMediaListAdapter.registerDataSetObserver(this.mDataSetObserver);
        this.mListView.a(this.mMediaListAdapter, R.id.menu_view, R.id.expandable);
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ AbsMediaListFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.mMediaListAdapter != null && this.a.mListView != null) {
                    int a = m.a(this.a.mListView.getHeaderViewsCount(), i, this.a.mMediaListAdapter.getCount());
                    if (a > -1) {
                        t.a(a);
                        p.a(a + 1);
                        this.a.onMediaItemClicked((MediaItem) this.a.mMediaItemList.get(a), a);
                    }
                }
            }
        });
        this.mListView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ AbsMediaListFragment a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!(this.a.mMediaListAdapter == null || this.a.mListView == null)) {
                    int a = m.a(this.a.mListView.getHeaderViewsCount(), i, this.a.mMediaListAdapter.getCount());
                    if (a > -1) {
                        p.a(a + 1);
                        this.a.onMediaItemLongClicked((MediaItem) this.a.mMediaItemList.get(a), a);
                    }
                }
                return true;
            }
        });
        this.mListView.setOnScrollListener(this.mAZSideBar);
        this.mAZSideBar.setOnMatchedPositionChangeListener(new com.sds.android.ttpod.widget.AZSideBar.a(this) {
            final /* synthetic */ AbsMediaListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, String str) {
                this.a.selectRow(i);
            }
        });
        updateStateViews();
        updateAZSideBar();
        this.mListView.setItemExpandCollapseListener(this);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mMediaItemList == null) {
            onReloadData();
        }
    }

    protected void onMediaItemLongClicked(MediaItem mediaItem, int i) {
        sLongClick(mediaItem, i);
        f.a(getActivity(), mediaItem, (com.sds.android.ttpod.fragment.main.list.d.a) this, i);
        m.a(this.mListView);
    }

    private void sLongClick(MediaItem mediaItem, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SONG_LIST_LONG_PRESS.getValue(), 0, 0);
        Long songID = mediaItem.getSongID();
        if (songID == null || songID.longValue() <= 0) {
            sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getLocalDataSource());
        } else {
            sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID());
        }
        sUserEvent.append("position", Integer.valueOf(i + 1));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public void onThemeLoaded() {
        this.mStateView.onThemeLoaded();
        this.mAZSideBar.onThemeLoaded();
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        c.a(this.mListFooterView, ThemeElement.SUB_BAR_TEXT);
        c.a(this.mListFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        refreshListViewTheme();
    }

    private Drawable makeDrawable(Drawable drawable, int i, int i2) {
        if (drawable != null) {
            return new InsetDrawable(drawable, i, 0, i2, 0);
        }
        return null;
    }

    private void refreshListViewTheme() {
        if (this.mListView != null) {
            notifyDataSetChanged();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mMediaItemList != null) {
            this.mMediaItemList.clear();
            notifyDataSetChanged();
            this.mMediaListAdapter = null;
        }
    }

    protected void updateMediaList(List<MediaItem> list) {
        if (list != this.mMediaItemList) {
            if (list != null) {
                this.mMediaItemList = new ArrayList(list);
            } else {
                this.mMediaItemList = null;
            }
            attachFavoriteState();
            notifyDataSetChanged();
        }
        if (isViewAccessAble()) {
            updateStateViews();
            updateAZSideBar();
        }
    }

    protected void setMediaList(List<MediaItem> list) {
        if (list != null) {
            this.mMediaItemList = new ArrayList(list);
        }
        attachFavoriteState();
    }

    protected void notifyDataSetChanged() {
        if (this.mMediaListAdapter != null) {
            this.mMediaListAdapter.notifyDataSetChanged();
        }
    }

    public void updateFavoriteChanged() {
        attachFavoriteState();
        notifyDataSetChanged();
    }

    protected void attachFavoriteState() {
        if (this.mMediaItemList == null) {
            return;
        }
        if (!(this.mMediaItemList instanceof AsyncLoadMediaItemList) || ((AsyncLoadMediaItemList) this.mMediaItemList).isLoadFinished()) {
            for (MediaItem mediaItem : this.mMediaItemList) {
                mediaItem.setFav(k.a(mediaItem));
            }
        }
    }

    protected boolean needFailedState() {
        return true;
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mPlayStatus = playStatus;
    }

    public void updatePlayingMediaInfo() {
        notifyDataSetChanged();
    }

    protected void setViewTagHolder(View view) {
        view.setTag(new com.sds.android.ttpod.component.b.g(view));
    }

    public void onExpand(View view, int i) {
        updateMediaItemView((com.sds.android.ttpod.component.b.g) ((View) view.getParent()).getTag(), i, true);
        if (i < getMediaItemList().size() && i >= 0) {
            MediaItem mediaItem = (MediaItem) getMediaItemList().get(i);
            initMediaItemMenuClickEvent((com.sds.android.ttpod.component.b.f) view.getTag(), mediaItem, i);
            b.a((BaseActivity) getActivity(), mediaItem, "drop_down_menu");
        }
    }

    public void onCollapse(View view, int i) {
        if (view != null) {
            View view2 = (View) view.getParent();
            if (view2 != null) {
                updateMediaItemView((com.sds.android.ttpod.component.b.g) view2.getTag(), i, false);
            }
        }
    }

    private void updateMediaItemView(com.sds.android.ttpod.component.b.g gVar, int i, boolean z) {
        if (z && i < this.mMediaItemList.size()) {
            gVar.b((MediaItem) this.mMediaItemList.get(i));
        }
        gVar.d().setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
    }

    protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.media_list_item, null);
            setViewTagHolder(view);
        }
        com.sds.android.ttpod.component.b.g gVar = (com.sds.android.ttpod.component.b.g) view.getTag();
        boolean z = (this.mMediaItemList instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) this.mMediaItemList).isLoadFinished();
        gVar.a(this.mListView, mediaItem, i, z, getActivity());
        return view;
    }

    protected void initMediaItemMenuClickEvent(com.sds.android.ttpod.component.b.f fVar, MediaItem mediaItem, int i) {
        OnClickListener dVar = new d(getActivity(), this.mListView, mediaItem, i);
        dVar.a((com.sds.android.ttpod.fragment.main.list.d.a) this);
        fVar.a(dVar);
    }

    public void onDeleteMediaItem(final MediaItem mediaItem) {
        f.a(getActivity(), mediaItem, this.mGroupID, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.i>(this) {
            final /* synthetic */ AbsMediaListFragment b;

            public void a(com.sds.android.ttpod.component.d.a.i iVar) {
                List mediaItemList = this.b.getMediaItemList();
                if (!(mediaItemList instanceof AsyncLoadMediaItemList) || ((AsyncLoadMediaItemList) mediaItemList).isLoadFinished()) {
                    mediaItemList.remove(mediaItem);
                    this.b.updateMediaList(mediaItemList);
                }
            }
        });
        l.aI();
    }

    protected boolean isShowFavoriteCount() {
        return false;
    }

    protected void onFavoriteChanged(MediaItem mediaItem, boolean z) {
    }

    public final List<MediaItem> getMediaItemList() {
        return this.mMediaItemList;
    }

    public int getUnDownloadedMediaSize() {
        if (this.mMediaItemList == null || this.mMediaItemList.isEmpty()) {
            return 0;
        }
        int i = 0;
        for (MediaItem localDataSource : this.mMediaItemList) {
            int i2;
            if (com.sds.android.sdk.lib.util.m.a(localDataSource.getLocalDataSource())) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public boolean isEmpty() {
        return this.mMediaItemList == null || this.mMediaItemList.size() == 0;
    }

    public final ActionExpandableListView getListView() {
        return this.mListView;
    }

    public final StateView getStateView() {
        return this.mStateView;
    }

    protected final View getFailedView() {
        return this.mFailedView;
    }

    protected final View getListFooterView() {
        return this.mListFooterView;
    }

    private void updateStateViews() {
        if (!needFailedState()) {
            this.mStateView.setState(StateView.b.SUCCESS);
            configListFooterView(this.mListFooterView);
        } else if (this.mMediaItemList == null) {
            this.mStateView.setState(StateView.b.LOADING);
        } else if (this.mMediaItemList.size() == 0) {
            this.mStateView.setState(StateView.b.FAILED);
            configFailedView(this.mFailedView);
        } else if (this.mMediaItemList.size() > 0) {
            this.mStateView.setState(StateView.b.SUCCESS);
            configListFooterView(this.mListFooterView);
        }
    }

    protected final void updateAZSideBar() {
        boolean z = true;
        if (isViewAccessAble()) {
            boolean z2;
            boolean isAZSideBarEnable = isAZSideBarEnable();
            this.mAZSideBar.setVisibility(isAZSideBarEnable ? 0 : 8);
            ActionExpandableListView actionExpandableListView = this.mListView;
            if (isAZSideBarEnable) {
                z2 = false;
            } else {
                z2 = true;
            }
            actionExpandableListView.setVerticalScrollBarEnabled(z2);
            ActionExpandableListView actionExpandableListView2 = this.mListView;
            if (isAZSideBarEnable) {
                z = false;
            }
            actionExpandableListView2.setFastScrollEnabled(z);
        }
    }

    protected void updateAZKeys(List<String> list) {
        d.a((Object) list, "AZKeys");
        if (isViewAccessAble()) {
            g.a(TAG, "lookLoadData updateAZKeys groupId=%s", this.mGroupID);
            this.mAZSideBar.a((List) list);
        }
        updateAZSideBar();
    }

    public void selectRow(int i) {
        if (this.mListView != null) {
            this.mListView.requestFocus();
            this.mListView.setSelection(i);
        }
    }
}
