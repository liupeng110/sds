package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mediascan.MediaScanActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.list.a.a;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Collection;

public class SubMediaListFragment extends SlidingClosableFragment implements a {
    public static final String KEY_GROUP_NAME = "group_name";
    private String mGroupID;
    private MediaListFragment mMediaListFragment;
    private b mOnActionClickListener = new b(this) {
        final /* synthetic */ SubMediaListFragment a;

        {
            this.a = r1;
        }

        public void a(com.sds.android.ttpod.component.a.a aVar) {
            if (aVar != this.a.mSelectAction) {
                return;
            }
            if (aVar.g() == null) {
                this.a.mMediaListFragment.selectAll();
            } else {
                this.a.mMediaListFragment.selectNone();
            }
        }
    };
    private com.sds.android.ttpod.component.a.a mSelectAction;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        d.a(getArguments(), "Arguments");
        this.mSelectAction = getActionBarController().a(null);
        v.a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
        this.mSelectAction.a();
        this.mSelectAction.a(this.mOnActionClickListener);
        updateActionBar();
        return layoutInflater.inflate(R.layout.fragment_local_sub_media_list, null);
    }

    protected boolean needMenuAction() {
        return true;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(getView().findViewById(R.id.content_local_media_list), ThemeElement.BACKGROUND_MASK);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mGroupID = arguments.getString(AbsMediaListFragment.KEY_GROUP_ID);
            if (arguments.getBoolean(AbsMediaListFragment.KEY_FROM_GROUP)) {
                setTBSPage(l.a().c() + "_sub");
            }
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mMediaListFragment = initMediaListFragment();
        this.mMediaListFragment.setEditRequestListener(this);
    }

    protected MediaListFragment initMediaListFragment() {
        if (this.mGroupID != null) {
            setStatisticPage(MediaListFragment.pageFromGroupId(this.mGroupID));
            trackPlaySong(NewUser.LOCAL_LOGIN, this.mGroupID, false);
        }
        MediaListFragment mediaListFragment = (MediaListFragment) Fragment.instantiate(getActivity(), selectMediaListFragmentClassName(), getArguments());
        mediaListFragment.setNoDataViewMessage(R.string.icon_male, R.string.no_song_go_recommend);
        mediaListFragment.setNoDataViewOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SubMediaListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.TOGGLE_FIND_SONG_FRAGMENT, new Object[0]), (int) SecExceptionCode.SEC_ERROR_STA_ENC);
            }
        });
        getChildFragmentManager().beginTransaction().replace(R.id.content_local_media_list, mediaListFragment).commitAllowingStateLoss();
        return mediaListFragment;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMediaListFragment = null;
    }

    protected void onSearchActionClicked() {
        if (this.mMediaListFragment != null) {
            this.mMediaListFragment.search();
            com.sds.android.ttpod.framework.a.b.l.af();
        }
    }

    public void onRemoveRequested() {
        if (isViewAccessAble() && this.mMediaListFragment.selectedCount() > 0) {
            this.mMediaListFragment.remove();
        }
    }

    public void onAddToRequested() {
        if (isViewAccessAble() && this.mMediaListFragment.selectedCount() > 0) {
            this.mMediaListFragment.addTo();
        }
    }

    public void onSendToRequested() {
        if (isViewAccessAble() && this.mMediaListFragment.selectedCount() > 0) {
            this.mMediaListFragment.sendTo();
        }
    }

    public void onStopEditRequested() {
        stopEdit();
    }

    public void onSelectedCountChanged() {
        if (isViewAccessAble()) {
            updateActionBar();
        }
    }

    protected void onBackPressed() {
        if (f.b()) {
            stopEdit();
        } else {
            super.onBackPressed();
        }
    }

    private void startEdit() {
        if (isViewAccessAble()) {
            View findViewById = getActivity().findViewById(R.id.view_immersive_observer);
            f.a(getView(), findViewById == null ? 0 : findViewById.getPaddingBottom(), (a) this);
            updateActionBar();
            this.mMediaListFragment.startEdit();
        }
    }

    private void stopEdit() {
        if (isViewAccessAble()) {
            f.c();
            updateActionBar();
            this.mMediaListFragment.stopEdit();
        }
    }

    private void updateActionBar() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        if (!f.b() || this.mMediaListFragment == null) {
            showFixedAction();
            this.mSelectAction.a();
            actionBarController.a(getArguments().getString(KEY_GROUP_NAME));
            return;
        }
        hideFixedAction();
        if (this.mMediaListFragment.totalCount() == 0 || this.mMediaListFragment.totalCount() != this.mMediaListFragment.selectedCount()) {
            this.mSelectAction.a(null);
            v.a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
        } else {
            this.mSelectAction.a(this.mSelectAction);
            v.a(this.mSelectAction, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
        }
        this.mSelectAction.b();
        actionBarController.a(getResources().getString(R.string.select_media_with_count, new Object[]{Integer.valueOf(this.mMediaListFragment.selectedCount())}));
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        int i = 1;
        com.sds.android.ttpod.framework.a.b.b.a("my_menu");
        t.a(r.ACTION_OPEN_LOCAL_DROP_MENU, s.PAGE_NONE);
        String string = getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID);
        Collection arrayList = new ArrayList();
        int i2 = (!f.b() || this.mMediaListFragment == null) ? 0 : 1;
        boolean isEmpty;
        if (this.mMediaListFragment != null) {
            isEmpty = this.mMediaListFragment.isEmpty();
        } else {
            isEmpty = true;
        }
        if (!(string.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX))) {
            i = 0;
        }
        if (i2 == 0 && r1 != 0) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(4, 0, (int) R.string.menu_scan_media));
        }
        if (i2 == 0 && string.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) && !string.equals(MediaStorage.GROUP_ID_RECENTLY_ADD)) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(17, 0, (int) R.string.menu_add_meida));
        }
        if (i2 == 0 && !r3) {
            if (string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY)) {
                arrayList.add(new com.sds.android.ttpod.component.b.a(22, 0, (int) R.string.menu_clear_recent_play_list));
            } else {
                arrayList.add(new com.sds.android.ttpod.component.b.a(15, 0, (int) R.string.menu_batch_operate));
            }
        }
        if (!(string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) || string.equals(MediaStorage.GROUP_ID_RECENTLY_ADD) || string.equals(MediaStorage.GROUP_ID_KTV) || r3)) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_title).a(Integer.valueOf(7)));
            if (!string.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
                arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_artist).a(Integer.valueOf(8)));
            }
            arrayList.add(new com.sds.android.ttpod.component.b.a(6, 0, (int) R.string.menu_sort_by_add_time).a(Integer.valueOf(10)));
        }
        if (i2 == 0 && !r3 && (string.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX) || string.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX))) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(21, 0, (int) R.string.menu_add_to_songlist));
        }
        if (!(i2 != 0 || !string.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) || string.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY) || string.equals(MediaStorage.GROUP_ID_RECENTLY_ADD) || string.equals(MediaStorage.GROUP_ID_KTV))) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(23, 0, (int) R.string.menu_rename_songlist));
            arrayList.add(new com.sds.android.ttpod.component.b.a(24, 0, (int) R.string.menu_delete_songlist));
        }
        return arrayList;
    }

    private void menuSortStats(int i) {
        String str = "";
        switch (i) {
            case 7:
                str = "my_menu_sorting_song";
                break;
            case 8:
                str = "my_menu_sorting_singer";
                break;
            case 9:
                str = "my_menu_sorting_album";
                break;
            case 10:
                str = "my_menu_sorting_time";
                break;
            case 11:
                str = "my_menu_sorting_file_name";
                break;
            case 12:
                str = "my_menu_sorting_amount";
                break;
        }
        com.sds.android.ttpod.framework.a.b.b.a(str);
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        String string = getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID);
        String string2 = getArguments().getString(KEY_GROUP_NAME);
        switch (i) {
            case 4:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_scan");
                t.a(r.ACTION_MENU_SCAN_MUSIC, s.PAGE_SCAN_MUSIC);
                startActivity(new Intent(getActivity(), MediaScanActivity.class));
                return;
            case 5:
                com.sds.android.ttpod.framework.a.b.b.a("my_search_button");
                this.mMediaListFragment.search();
                return;
            case 6:
                if (this.mMediaListFragment != null) {
                    int intValue = ((Number) aVar.h()).intValue();
                    menuSortStats(intValue);
                    this.mMediaListFragment.order(intValue);
                    return;
                }
                return;
            case 15:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_manage");
                if (MediaStorage.GROUP_ID_RECENTLY_ADD.equals(string)) {
                    com.sds.android.ttpod.framework.a.b.l.ar();
                }
                t.a(r.ACTION_MENU_BATCH_OPERATE, s.PAGE_NONE);
                startEdit();
                return;
            case 17:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_add_media");
                t.a(r.ACTION_MENU_ADD_MEDIA, s.PAGE_NONE);
                if (this.mMediaListFragment instanceof DraggableMediaListFragment) {
                    ((DraggableMediaListFragment) this.mMediaListFragment).addMedia(false);
                }
                if (com.sds.android.ttpod.b.l.b(string)) {
                    com.sds.android.ttpod.framework.a.b.l.am();
                    return;
                }
                return;
            case 21:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_add_to_songlist");
                t.a(r.ACTION_LOCAL_SONG_DETAIL_ADD_TO_SONGLIST, s.PAGE_NONE);
                f.b(getActivity(), e.k(string2), new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                    final /* synthetic */ SubMediaListFragment a;

                    {
                        this.a = r1;
                    }

                    public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                        if (this.a.mMediaListFragment != null && !this.a.mMediaListFragment.isEmpty()) {
                            t.a(r.ACTION_LOCAL_SONG_DETAIL_ADD_TO_SONGLIST_SURE, s.PAGE_NONE);
                            String charSequence = bVar.c(0).e().toString();
                            charSequence = (String) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_GROUP, charSequence), String.class);
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM_LIST, charSequence, this.a.mMediaListFragment.getMediaItemList()));
                            f.a((int) R.string.add_to_main_success);
                        }
                    }
                });
                return;
            case 22:
                if (this.mMediaListFragment != null) {
                    com.sds.android.ttpod.framework.a.b.b.a("my_menu_clear_recent_play");
                    this.mMediaListFragment.removeAll();
                    com.sds.android.ttpod.framework.a.b.l.aq();
                    t.a(r.ACTION_MENU_CLEAR_PLAYLIST, s.PAGE_NONE);
                    return;
                }
                return;
            case 23:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_rename_songlist");
                rename(string, string2);
                if (com.sds.android.ttpod.b.l.b(string)) {
                    com.sds.android.ttpod.framework.a.b.l.an();
                    t.a(r.ACTION_MENU_RENAME_SONGLIST, s.PAGE_NONE);
                    return;
                }
                return;
            case 24:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_delete_songlist");
                remove(string);
                if (com.sds.android.ttpod.b.l.b(string)) {
                    t.a(r.ACTION_MENU_DELETE_SONGLIST, s.PAGE_NONE);
                    com.sds.android.ttpod.framework.a.b.l.ao();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void rename(final String str, String str2) {
        com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(getActivity(), new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, "", str2, getActivity().getString(R.string.rename_hint))}, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
            final /* synthetic */ SubMediaListFragment b;

            public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                com.sds.android.ttpod.component.d.a.b.a c = bVar.c(1);
                if (c == null) {
                    return;
                }
                if (com.sds.android.ttpod.b.l.a(c.e().toString())) {
                    bVar.e(false);
                    f.a((int) R.string.playlist_name_existed);
                    return;
                }
                int totalCount;
                bVar.e(true);
                String charSequence = c.e().toString();
                String str = str;
                if (this.b.mMediaListFragment != null) {
                    totalCount = this.b.mMediaListFragment.totalCount();
                } else {
                    totalCount = 0;
                }
                GroupItem groupItem = new GroupItem(charSequence, str, Integer.valueOf(totalCount));
                this.b.getArguments().putString(SubMediaListFragment.KEY_GROUP_NAME, groupItem.getName());
                this.b.getActionBarController().a(groupItem.getName());
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_ITEM, groupItem));
            }
        }, null);
        bVar.setTitle((int) R.string.rename);
        bVar.show();
    }

    private void remove(final String str) {
        h hVar = new h(getActivity(), getActivity().getString(R.string.delete_message), new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ SubMediaListFragment b;

            public void a(h hVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_GROUP, str));
                this.b.finish();
            }
        }, null);
        hVar.setTitle((int) R.string.menu_delete_songlist);
        hVar.show();
    }

    protected String selectMediaListFragmentClassName() {
        return MediaListFragment.class.getName();
    }

    protected MediaListFragment getMediaListFragment() {
        return this.mMediaListFragment;
    }

    protected void onSlidingClosed() {
        if (f.b()) {
            new Handler().post(new Runnable(this) {
                final /* synthetic */ SubMediaListFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mMediaListFragment != null) {
                        this.a.stopEdit();
                    }
                }
            });
        }
        super.onSlidingClosed();
    }

    public static String selectSubMediaListFragmentClassName(String str) {
        if (com.sds.android.ttpod.b.l.b(str)) {
            return DraggableSubMediaListFragment.class.getName();
        }
        if (m.a(str, MediaStorage.GROUP_ID_FAV)) {
            return FavoriteSubMediaListFragment.class.getName();
        }
        return SubMediaListFragment.class.getName();
    }
}
