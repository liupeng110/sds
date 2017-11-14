package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.f;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.a;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.g;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class SingerTopSongFragment extends OnlineMediaListFragment implements a {
    private static final int LOADING_FOOTVIEW_HEIGHT = 32;
    private static final int PAGE_1 = 1;
    private static final int PAGE_SIZE = 30;
    private View mHeaderView;
    private boolean mIsFirstAddFooter = true;
    private View mListLessItemFooterView;
    private StateView mOldStateView;
    private int mSingerId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSingerId = arguments.getInt(SingerDetailFragment.KEY_SINGER_ID);
        }
        this.mGroupID = MediaStorage.GROUP_ID_ONLINE_TEMPORARY;
        setStatisticPage(s.PAGE_SINGER_SONG_LIST);
    }

    protected View onCreateListHeaderView(LayoutInflater layoutInflater) {
        this.mHeaderView = new g(getActivity()).a();
        return this.mHeaderView;
    }

    public void requestNextPage(AbsListView absListView, int i, int i2, int i3) {
        if (!this.mIsFirstAddFooter) {
            performOnScroll(absListView, i, i2, i3);
        }
    }

    protected void onMediaItemClicked(MediaItem mediaItem, int i) {
        super.onMediaItemClicked(mediaItem, i);
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ONLINE_SONG_LIST_ITEM.getValue(), s.PAGE_SINGER_SONG_LIST.getValue(), 0).append("singer_id", Integer.valueOf(this.mSingerId)).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("position", Integer.valueOf(i + 1)).post();
    }

    protected void onReloadData() {
        super.onReloadData();
        this.mStateView.setState(b.SUCCESS);
        this.mOldStateView = this.mStateView;
        this.mStateView = new g(getActivity()).b();
        this.mStateView.setOnRetryRequestListener(new a(this) {
            final /* synthetic */ SingerTopSongFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.onRequestPage(1);
            }
        });
        this.mListView.removeFooterView(this.mListFooterView);
        this.mListView.removeFooterView(this.mStateView);
        this.mListView.addFooterView(this.mStateView);
        onRequestPage(1);
        onThemeLoaded();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mOldStateView != null) {
            this.mOldStateView.onThemeLoaded();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_SONG_LIST, i.a(cls, "updateSingerSongList", com.sds.android.ttpod.framework.modules.b.class));
    }

    public void updateSingerSongList(com.sds.android.ttpod.framework.modules.b bVar) {
        if (checkSuccess(bVar)) {
            if (bVar.isSuccess()) {
                updateSuccessStateView();
            }
            List a = bVar.a();
            f b = bVar.b();
            if (this.mIsFirstAddFooter && a.size() != 30) {
                int[] iArr = new int[2];
                getListView().getLocationInWindow(iArr);
                int height = this.mListFooterView.getHeight();
                if (height == 0) {
                    height = com.sds.android.ttpod.common.c.a.a(32);
                }
                this.mListLessItemFooterView = new g(getActivity()).a(height + (getActivity().getResources().getDimensionPixelSize(R.dimen.singer_song_list_item_height) * a.size()), iArr[1]);
                getListView().addFooterView(this.mListLessItemFooterView);
            }
            this.mIsFirstAddFooter = false;
            super.updateMediaList(Integer.valueOf(bVar.getCode()), Integer.valueOf(b.b()), a);
        }
    }

    private boolean checkSuccess(com.sds.android.ttpod.framework.modules.b bVar) {
        if (!isViewAccessAble() || bVar == null) {
            return false;
        }
        boolean z;
        if (bVar.isSuccess()) {
            z = false;
        } else {
            z = true;
        }
        boolean a = j.a(bVar.a());
        if (getPager().f()) {
            if (z) {
                this.mStateView.setState(b.FAILED);
                return false;
            } else if (a) {
                this.mStateView.setState(b.NO_DATA);
                return false;
            }
        }
        return true;
    }

    private void updateSuccessStateView() {
        this.mStateView.setState(b.SUCCESS);
        this.mListView.removeFooterView(this.mStateView);
        if (this.mIsFirstAddFooter) {
            this.mListView.addFooterView(this.mListFooterView);
        }
    }

    protected void onRequestPage(int i) {
        this.mStateView.setState(b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SINGER_SONG_LIST_BY_ID, Integer.valueOf(this.mSingerId), Integer.valueOf(i), Integer.valueOf(30)));
    }

    public ListView getSingerListView() {
        return getListView();
    }

    public void requestRefreshView(Bundle bundle) {
        this.mListView.removeFooterView(this.mListLessItemFooterView);
        this.mListView.removeFooterView(this.mListFooterView);
        this.mListView.removeFooterView(this.mStateView);
        this.mListView.addFooterView(this.mStateView);
        if (getMediaItemList() != null) {
            getMediaItemList().clear();
            notifyDataSetChanged();
        }
        if (bundle != null) {
            this.mSingerId = bundle.getInt(SingerDetailFragment.KEY_SINGER_ID);
            this.mIsFirstAddFooter = true;
            getPager().a(true);
            getPager().c(1);
            onRequestPage(1);
            return;
        }
        throw new IllegalArgumentException("getArguments() singerId must not be null");
    }

    public View getSingerListHeaderView() {
        return this.mHeaderView;
    }
}
