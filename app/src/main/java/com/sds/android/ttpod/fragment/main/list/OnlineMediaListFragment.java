package com.sds.android.ttpod.fragment.main.list;

import android.app.Activity;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.component.b.f;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.fragment.MVGuideFragment;
import com.sds.android.ttpod.fragment.search.SongSearchFragment;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.AsyncLoadMediaItemList;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import java.util.List;
import java.util.UUID;

public class OnlineMediaListFragment extends AbsMediaListFragment {
    private static final int CODE_LOADING = -1;
    private static final int DELAY_MILLS = 200;
    private static final int SONG_MV_GUIDE_MARK_PADDING = 5;
    private static final String TAG = "OnlineMediaListFragment";
    private int[] mFirstMvFlagLocation;
    protected DataListFooterView mFooterView;
    protected boolean mIsErrorNotFirstPage = false;
    private boolean mIsLoading = true;
    private boolean mIsScrolled = false;
    private Boolean mIsShowMediaListMVGuideEnable = null;
    private String mModule;
    private boolean mNeedShowFootAnimation = true;
    protected View mNodataView;
    private a mOnDataRequestListener;
    private b mOnMediaItemClickListener;
    private c mOnNextPageListener;
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ OnlineMediaListFragment a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.a.mIsScrolled = true;
            if (this.a.mAZSideBar != null) {
                this.a.mAZSideBar.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.a.performOnScroll(absListView, i, i2, i3);
            if (this.a.mAZSideBar != null) {
                this.a.mAZSideBar.onScroll(absListView, i, i2, i3);
            }
        }
    };
    private String mOrigin;
    private q mPager = new q();
    private String mPlayingGroupID;
    private String mPlayingMediaID;

    public interface b {
        void doStatistic(MediaItem mediaItem, int i);
    }

    public interface c {
        void a(int i);
    }

    public interface a {
        void a();
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public String getModule() {
        return this.mModule;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    protected String getGroupID() {
        return this.mGroupID;
    }

    public q getPager() {
        return this.mPager;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        getStateView().onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mNodataView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_failed, null);
    }

    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        this.mFooterView = new DataListFooterView(getActivity());
        return this.mFooterView;
    }

    protected View onCreateNodataView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_nodata, null);
    }

    protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        boolean z = true;
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.online_media_list_item, null);
            setViewTagHolder(view);
        }
        g gVar = (g) view.getTag();
        boolean z2 = (this.mMediaItemList instanceof AsyncLoadMediaItemList) && ((AsyncLoadMediaItemList) this.mMediaItemList).isLoadFinished();
        gVar.a(this.mListView, mediaItem, i, z2, getActivity());
        if (mediaItem.containMV() && (this instanceof SongSearchFragment)) {
            getFirstMvFlagLocation(gVar);
        }
        if (m.a(this.mPlayingGroupID)) {
            this.mPlayingGroupID = com.sds.android.ttpod.framework.storage.environment.b.m();
        }
        if (m.a(this.mPlayingMediaID)) {
            this.mPlayingMediaID = com.sds.android.ttpod.framework.storage.environment.b.n();
        }
        if (!(m.a(this.mGroupID, this.mPlayingGroupID) && m.a(this.mPlayingMediaID, mediaItem.getID()))) {
            z = false;
        }
        gVar.a(mediaItem, this.mPlayStatus, z);
        gVar.c(mediaItem);
        view.setSelected(z);
        return view;
    }

    private void getFirstMvFlagLocation(g gVar) {
        if (gVar != null && this.mFirstMvFlagLocation == null) {
            this.mFirstMvFlagLocation = new int[2];
            final View f = gVar.f();
            f.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ OnlineMediaListFragment b;

                public void onGlobalLayout() {
                    f.getLocationInWindow(this.b.mFirstMvFlagLocation);
                    com.sds.android.sdk.lib.util.g.c(OnlineMediaListFragment.TAG, "flagMvView.x = " + this.b.mFirstMvFlagLocation[0] + " flagMvView.y = " + this.b.mFirstMvFlagLocation[1]);
                    this.b.tryShowListItemMvFlagGuideView(f);
                    if (j.g()) {
                        f.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        f.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
            });
        }
    }

    private void tryShowListItemMvFlagGuideView(View view) {
        if (this.mIsShowMediaListMVGuideEnable == null && !this.mIsScrolled) {
            this.mIsShowMediaListMVGuideEnable = Boolean.valueOf(com.sds.android.ttpod.framework.storage.environment.b.aU());
            if (this.mIsShowMediaListMVGuideEnable.booleanValue()) {
                new MVGuideFragment(getListItemMvFlagGuideRect(view), R.drawable.mv_guide_search_song_description).show(getChildFragmentManager(), "search_song");
                com.sds.android.ttpod.framework.storage.environment.b.V(false);
            }
        }
    }

    private RectF getListItemMvFlagGuideRect(View view) {
        float f = (float) this.mFirstMvFlagLocation[0];
        float f2 = (float) this.mFirstMvFlagLocation[1];
        return new RectF(f - ((float) com.sds.android.ttpod.common.c.a.a(5)), (f2 - ((float) com.sds.android.ttpod.common.c.a.a(1))) - com.sds.android.ttpod.common.c.a.a(), (((float) view.getWidth()) + f) + ((float) com.sds.android.ttpod.common.c.a.a(5)), ((f2 + ((float) view.getHeight())) + ((float) com.sds.android.ttpod.common.c.a.a(1))) - com.sds.android.ttpod.common.c.a.a());
    }

    public void onExpand(View view, int i) {
        ((g) ((View) view.getParent()).getTag()).d().setText((int) R.string.icon_arrow_top);
        if (i < getMediaItemList().size() && i >= 0) {
            f fVar = (f) view.getTag();
            MediaItem mediaItem = (MediaItem) getMediaItemList().get(i);
            initMediaItemMenuClickEvent(fVar, mediaItem, i);
            fVar.a(mediaItem);
            if (!(this.mOrigin == null || this.mModule == null)) {
                p.a(i + 1);
                onExpandStatistic(mediaItem);
            }
            com.sds.android.ttpod.framework.a.b.b.a((BaseActivity) getActivity(), mediaItem, "drop_down_menu");
        }
    }

    protected void onExpandStatistic(MediaItem mediaItem) {
        w.a(this.mModule, "menu", this.mOrigin, 0, (long) p.f(), mediaItem.getTitle(), UUID.randomUUID().toString());
    }

    protected boolean isShowFavoriteCount() {
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mNodataView = onCreateNodataView(layoutInflater);
        this.mStateView.setNodataView(this.mNodataView);
        return this.mStateView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mPager.b(Entry.MAX_LIMIT);
        getListView().setOnScrollListener(this.mOnScrollListener);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mOnScrollListener = null;
        this.mOnMediaItemClickListener = null;
        this.mOnDataRequestListener = null;
        this.mOnNextPageListener = null;
        if (getListView() != null) {
            getListView().setOnScrollListener(null);
        }
    }

    protected void configFailedView(View view) {
        if (view != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OnlineMediaListFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.onReloadData();
                }
            });
        }
    }

    protected void configListFooterView(View view) {
    }

    protected void configNodataView(View view) {
        if (view != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OnlineMediaListFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.onReloadData();
                }
            });
        }
    }

    protected void onReloadData() {
        if (this.mOnDataRequestListener != null) {
            this.mOnDataRequestListener.a();
        }
    }

    protected boolean onListStatistic() {
        return false;
    }

    protected void beforeOnMediaItemClicked(MediaItem mediaItem) {
    }

    protected void onMediaItemClicked(MediaItem mediaItem, int i) {
        com.sds.android.ttpod.b.m.a(this.mListView);
        beforeOnMediaItemClicked(mediaItem);
        com.sds.android.sdk.lib.util.g.a("ListStatistic", "onMediaItemClicked=" + mediaItem.getSongID() + SelectCountryActivity.SPLITTER + mediaItem.getTitle());
        if (onListStatistic()) {
            p.a(Integer.valueOf(com.sds.android.ttpod.framework.a.b.j.a()));
            p.b(com.sds.android.ttpod.framework.a.b.j.b());
            p.a(mediaItem.getSongID());
            p.c(com.sds.android.ttpod.framework.a.b.j.c());
        } else {
            p.a(Integer.valueOf(-1));
            p.b(null);
            p.a(Long.valueOf(-1));
            p.c(null);
        }
        p.a(getListenOrigin());
        p.a();
        if (m.a(this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.m()) && m.a(mediaItem.getID(), com.sds.android.ttpod.framework.storage.environment.b.n())) {
            performPlayCurrentMedia(true);
            return;
        }
        com.sds.android.sdk.lib.util.g.a(TAG, "onMediaItemClicked SYNC_NET_TEMPORARY_GROUP " + getMediaItemList());
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, o.a(getMediaItemList())));
        if (mediaItem.hasCopyright() || mediaItem.getCensorLevel() == 4) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, this.mGroupID, mediaItem));
            this.mPlayingGroupID = this.mGroupID;
            this.mPlayingMediaID = mediaItem.getID();
        } else {
            checkMediaCensor(getActivity(), mediaItem, i);
        }
        if (this.mOnMediaItemClickListener != null) {
            this.mOnMediaItemClickListener.doStatistic(mediaItem, i);
        }
    }

    public static void performPlayCurrentMedia(boolean z) {
        PlayStatus n = e.a(BaseApplication.e()).n();
        if (n == PlayStatus.STATUS_PAUSED) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
        } else if (n != PlayStatus.STATUS_PLAYING) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
        } else if (z) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
        }
    }

    public static void checkMediaCensor(Activity activity, MediaItem mediaItem, int i) {
        new com.sds.android.ttpod.component.c.a(activity, mediaItem, u.c(), i).a(13);
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        this.mPlayingGroupID = com.sds.android.ttpod.framework.storage.environment.b.m();
        if (m.a(this.mGroupID, this.mPlayingGroupID)) {
            notifyDataSetChanged();
        }
    }

    public void playMediaChanged() {
        this.mPlayingMediaID = com.sds.android.ttpod.framework.storage.a.a.a().M().getID();
        if (m.a(this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.m())) {
            notifyDataSetChanged();
        }
    }

    public void updatePlayingMediaInfo() {
    }

    public void updateMediaList(List<MediaItem> list) {
        updateMediaList(Integer.valueOf(0), Integer.valueOf(Entry.MAX_LIMIT), list);
    }

    public void updateMediaList(Integer num, List<MediaItem> list) {
        updateMediaList(Integer.valueOf(0), num, list);
    }

    public void clearMediaList() {
        List mediaItemList = getMediaItemList();
        if (com.sds.android.ttpod.framework.a.j.b(mediaItemList)) {
            mediaItemList.clear();
            notifyDataSetChanged();
        }
    }

    public void updateMediaList(Integer num, Integer num2, List<MediaItem> list) {
        this.mIsLoading = false;
        if (isViewAccessAble() && list != null && list.size() > 0) {
            com.sds.android.sdk.lib.util.g.a(TAG, getClass().getSimpleName() + ".updateMediaList ");
            this.mPager.b(num2.intValue());
            if (this.mPager.a() > 1) {
                getMediaItemList().addAll(list);
                if (this.mFooterView != null) {
                    this.mFooterView.c();
                }
            } else {
                setMediaList(list);
            }
            this.mIsErrorNotFirstPage = false;
        } else if (this.mPager.a() > 1) {
            this.mIsErrorNotFirstPage = true;
            this.mPager.c(this.mPager.a() - 1);
            showNotFirstPageError();
            return;
        } else {
            this.mIsErrorNotFirstPage = false;
            setMediaList(list);
        }
        notifyDataSetChanged();
        if (isViewAccessAble()) {
            updateStateViews(num, getMediaItemList());
        }
    }

    public void updateStateViews(List<MediaItem> list) {
        if (isViewAccessAble()) {
            updateStateViews(Integer.valueOf(-1), list);
        }
    }

    private void updateStateViews(Integer num, List<MediaItem> list) {
        final StateView stateView = getStateView();
        if (list == null) {
            if (num.intValue() == -1) {
                stateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
                return;
            }
            stateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
            configFailedView(getFailedView());
        } else if (list.size() == 0) {
            if (num.intValue() == 1) {
                stateView.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
                configNodataView(this.mNodataView);
                return;
            }
            stateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
            stateView.postDelayed(new Runnable(this) {
                final /* synthetic */ OnlineMediaListFragment b;

                public void run() {
                    if (stateView != null) {
                        stateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
                        this.b.configFailedView(this.b.getFailedView());
                    }
                }
            }, 200);
        } else if (list.size() > 0) {
            stateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            configListFooterView(getListFooterView());
        }
    }

    protected void performOnScroll(AbsListView absListView, int i, int i2, int i3) {
        com.sds.android.sdk.lib.util.g.a(TAG, "performOnScroll firstVisibleItem=" + i + ",visibleItemCount=" + i2 + ",totalItemCount=" + i3);
        if (com.sds.android.ttpod.b.m.b(i, i2, i3) && !this.mIsLoading) {
            com.sds.android.sdk.lib.util.g.a(TAG, "mPager.getCurrent()=" + this.mPager.a() + ",mPager.end()=" + this.mPager.g());
            if (this.mPager.a() >= this.mPager.g()) {
                showLastPageFooterText();
            } else {
                requestData(this.mPager.d());
            }
        }
    }

    public boolean isHomePage() {
        return this.mPager.f();
    }

    public void repeatPageRequestData() {
        int d = this.mPager.d();
        if (d > this.mPager.c()) {
            d = this.mPager.b();
        }
        requestData(d);
    }

    public void requestHomePage() {
        requestData(this.mPager.b());
    }

    private void requestData(int i) {
        if (!this.mIsLoading) {
            com.sds.android.sdk.lib.util.g.a(TAG, "requestData page = " + i);
            if (i >= 1 && i >= this.mPager.b() && i <= this.mPager.g()) {
                this.mIsLoading = true;
                if (this.mFooterView != null && this.mNeedShowFootAnimation) {
                    this.mFooterView.a();
                    this.mFooterView.setOnClickListener(null);
                }
                onRequestPage(i);
                this.mPager.c(i);
                if (this.mOnNextPageListener != null) {
                    this.mOnNextPageListener.a(i);
                }
            }
        }
    }

    public void setNeedShowFootAnimation(boolean z) {
        this.mNeedShowFootAnimation = z;
    }

    public void showLastPageFooterText() {
        if (this.mFooterView != null && getMediaItemList() != null && getActivity() != null) {
            this.mFooterView.a(getString(R.string.count_of_media, Integer.valueOf(getMediaItemList().size())));
            this.mFooterView.setOnClickListener(null);
        }
    }

    private void showNotFirstPageError() {
        if (this.mFooterView != null && getActivity() != null) {
            this.mFooterView.b();
            this.mFooterView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OnlineMediaListFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(this.a.mPager.d());
                }
            });
        }
    }

    protected void onRequestPage(int i) {
    }

    public void setTotal(int i) {
        this.mPager.b(i);
    }

    public void clearPage() {
        this.mPager = new q();
        this.mPager.b(Entry.MAX_LIMIT);
    }

    public void setOnNextPageListener(c cVar) {
        this.mOnNextPageListener = cVar;
    }

    public void hide() {
        if (isViewAccessAble()) {
            getStateView().setVisibility(8);
        }
    }

    public void show() {
        if (isViewAccessAble()) {
            getStateView().setVisibility(0);
        }
    }

    public void setOnDataRequestListener(a aVar) {
        this.mOnDataRequestListener = aVar;
    }

    public void setOnMediaItemClickListener(b bVar) {
        this.mOnMediaItemClickListener = bVar;
    }

    protected String getListenOrigin() {
        return this.mOrigin;
    }

    protected String getDownloadOrigin() {
        return getListenOrigin();
    }
}
