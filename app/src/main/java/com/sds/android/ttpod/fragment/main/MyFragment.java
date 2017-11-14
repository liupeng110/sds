package com.sds.android.ttpod.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.a.j;
import com.sds.android.ttpod.component.lockscreen.a.a.h;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.fragment.downloadmanager.MyDownloadFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.GroupListFragment;
import com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.a.b.aa;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.widget.SimpleGridView;
import com.sds.android.ttpod.widget.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MyFragment extends BaseFragment {
    private static final int GRIDDLE_ANIMATION_DURATION = 500;
    private static final float GRIDDLE_END_ANGLE = 30.0f;
    private static final float GRIDDLE_START_ANGLE = -30.0f;
    private static final String[] HOME_ELEMENT_ID = new String[]{ThemeElement.HOME_IMG_FAVORITE, ThemeElement.HOME_IMG_DOWNLOAD, ThemeElement.HOME_IMG_SONG_LIST, ThemeElement.HOME_IMG_RECENT_PLAY, ThemeElement.HOME_IMG_RECENT_ADD, ThemeElement.HOME_IMG_ARTIST, ThemeElement.HOME_IMG_ALBUM, ThemeElement.HOME_IMG_FOLDER};
    private static final int ID_CREATE_SONGLIST = 6;
    private static final int ID_CUSTOMIZED_HOMEPAGE = 8;
    private static final int ID_MY_DOWNLOAD = 1;
    private static final int ID_MY_FAVORITE = 0;
    private static final int ID_MY_SONGLIST = 2;
    private static final int ID_RECENT_ADDED = 4;
    private static final int ID_RECENT_PLAY = 3;
    private static final int ID_SCAN_MEDIA = 7;
    private static final int ID_USER_CREATED = 5;
    private static final String[] THEME_ID_RESOURCE_MAP = new String[]{ThemeElement.HOME_FAVORITE_IMAGE, ThemeElement.HOME_DOWNLOAD_IMAGE, ThemeElement.HOME_SONG_LIST_IMAGE, ThemeElement.HOME_RECENT_PLAY_IMAGE, ThemeElement.HOME_RECENT_ADD_IMAGE, ThemeElement.HOME_ARTIST_IMAGE, ThemeElement.HOME_ALBUM_IMAGE, ThemeElement.HOME_FOLDER_IMAGE};
    private b mDropdownMenu;
    private View mHeaderView;
    private d[] mItems;
    private IconTextView mItvLocalMusic;
    private IconTextView mItvMenu;
    private IconTextView mItvPlay;
    private View mLayoutLocalMusic;
    private com.sds.android.ttpod.fragment.base.d mMenuClickListener = new com.sds.android.ttpod.fragment.base.d(this) {
        final /* synthetic */ MyFragment a;

        {
            this.a = r1;
        }

        public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
            switch (i) {
                case 7:
                    f.f(this.a.getActivity());
                    l.ac();
                    t.a(r.ACTION_MY_DROP_MENU_SCAN_MUSIC, s.PAGE_SCAN_MUSIC);
                    return;
                case 8:
                    w.a(NewUser.LOCAL_LOGIN, "click", "local-customized_homepage");
                    this.a.displayCustomizedHomepage();
                    l.ad();
                    t.a(r.ACTION_MY_DROP_MENU_CUSTOM_MY, s.PAGE_NONE);
                    return;
                default:
                    this.a.doClickActionItem(aVar, true);
                    return;
            }
        }
    };
    private a mMyEntryManager;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ MyFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            Boolean valueOf = Boolean.valueOf(MyFragment.getMediaCount(MediaStorage.GROUP_ID_ALL_LOCAL) > 0);
            if (view == this.a.mItvPlay) {
                com.sds.android.ttpod.framework.a.b.b.a("my_shake");
                l.aa();
                this.a.tryRandomPlay(view, valueOf.booleanValue());
                t.a(r.ACTION_RANDOM_PLAY, s.PAGE_NONE);
            } else if (view == this.a.mLayoutLocalMusic) {
                t.a(r.ACTION_LOCAL_MUSIC, s.PAGE_LOCAL_SONG);
                com.sds.android.ttpod.framework.a.b.b.a("local_music");
                this.a.launchFragment((BaseFragment) Fragment.instantiate(this.a.getActivity(), LocalMediaEntryFragment.class.getName()));
                l.E();
                l.Q();
                p.a(NewUser.LOCAL_LOGIN);
            } else if (view == this.a.mItvMenu) {
                com.sds.android.ttpod.framework.a.b.b.a("my_menu");
                l.ab();
                this.a.popupDropdownMenu(view);
            } else if (view == this.a.mUnicomText) {
                aa.k();
                t.a(1122, s.PAGE_UNICOM_ORDER);
                f.b(this.a.getActivity());
                com.sds.android.ttpod.framework.a.b.b.a("unicom_start");
            } else if (view == this.a.mUnicomCloseButton) {
                this.a.mUnicomLayout.setVisibility(8);
                com.sds.android.ttpod.framework.a.b.b.a("unicom_close_bannel");
                aa.l();
                new SUserEvent("PAGE_CLICK", 1121, 0).post();
                com.sds.android.ttpod.framework.storage.a.a.a().g(false);
            } else {
                Object tag = view.getTag();
                if (tag instanceof com.sds.android.ttpod.component.b.a) {
                    this.a.doClickActionItem((com.sds.android.ttpod.component.b.a) tag, false);
                }
            }
        }
    };
    private com.sds.android.ttpod.framework.storage.environment.b.a mPreferencesChangeListener = new com.sds.android.ttpod.framework.storage.environment.b.a(this) {
        final /* synthetic */ MyFragment a;

        {
            this.a = r1;
        }

        public void a(c cVar) {
            if (cVar == c.IS_SHOW_DOWNLOAD_HIGHLIGHT) {
                this.a.mMyEntryManager.a(this.a.mItems);
            }
        }
    };
    private boolean mReloadTheme = true;
    private View mRootView;
    private SimpleGridView mSgvEntry;
    private TextView mTvLocalMusicCount;
    private TextView mTvLocalMusicTitle;
    private ImageButton mUnicomCloseButton;
    private View mUnicomLayout;
    private TextView mUnicomText;

    private final class a {
        final /* synthetic */ MyFragment a;
        private SimpleGridView b;

        private a(MyFragment myFragment, SimpleGridView simpleGridView) {
            this.a = myFragment;
            this.b = simpleGridView;
        }

        private void a(int i) {
            int childCount = this.b.getChildCount();
            if (childCount > i) {
                for (childCount--; childCount >= i; childCount--) {
                    this.b.removeViewAt(childCount);
                }
            } else if (childCount < i) {
                while (childCount < i) {
                    this.b.addView(b(childCount));
                    childCount++;
                }
            }
        }

        private View b(int i) {
            View inflate = View.inflate(this.a.getActivity(), R.layout.fragment_main_mine_item, null);
            inflate.setOnClickListener(this.a.mOnClickListener);
            return inflate;
        }

        private void a(d[] dVarArr) {
            int i = 0;
            int i2 = 0;
            for (d isChecked : dVarArr) {
                if (isChecked.isChecked()) {
                    i2++;
                }
            }
            a(i2);
            int i3 = 0;
            while (i < dVarArr.length) {
                com.sds.android.ttpod.component.b.a aVar = dVarArr[i];
                if (aVar.isChecked()) {
                    a(this.b.getChildAt(i3), aVar);
                    i3++;
                }
                i++;
            }
        }

        private void a(View view, com.sds.android.ttpod.component.b.a aVar) {
            view.setTag(aVar);
            View view2 = (TextView) view.findViewById(R.id.text_title);
            view2.setText(aVar.d());
            IconTextView iconTextView = (IconTextView) view.findViewById(R.id.itv_icon);
            IconTextView iconTextView2 = (IconTextView) view.findViewById(R.id.new_flag);
            MyFragment.displayHighlightFlag(iconTextView2, aVar);
            v.a(iconTextView, MyFragment.THEME_ID_RESOURCE_MAP[aVar.g()], aVar.l(), ThemeElement.HOME_TEXT);
            v.a(iconTextView2, ThemeElement.HOME_NEW_IMAGE, (int) R.string.icon_dot, ThemeElement.HOME_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(view, ThemeElement.HOME_BACKGROUND);
            com.sds.android.ttpod.framework.modules.theme.c.a(view2, ThemeElement.HOME_TEXT);
        }
    }

    private void popupDropdownMenu(View view) {
        t.a(r.ACTION_MY_DROP_MENU, s.PAGE_NONE);
        Collection arrayList = new ArrayList();
        arrayList.add(new com.sds.android.ttpod.component.b.a(7, 0, (int) R.string.menu_scan_media));
        for (d dVar : this.mItems) {
            if (!dVar.isChecked()) {
                arrayList.add(dVar);
            }
        }
        arrayList.add(new com.sds.android.ttpod.component.b.a(8, 0, (int) R.string.menu_customized_homepage));
        this.mDropdownMenu = ActionBarFragment.createDropDownMenu(getActivity(), arrayList, this.mMenuClickListener);
        this.mDropdownMenu.setWidth((int) getResources().getDimension(R.dimen.drop_down_top_right_menu_width));
        this.mDropdownMenu.showAsDropDown(view, 0, 0);
    }

    private void displayCustomizedHomepage() {
        j jVar = new j(getActivity(), new ArrayList(Arrays.asList(this.mItems)), new com.sds.android.ttpod.common.a.a.a<j>(this) {
            final /* synthetic */ MyFragment a;

            {
                this.a = r1;
            }

            public void a(j jVar) {
                this.a.saveCustomizedHomepage();
                this.a.statsCustomizedHomepageShow();
                this.a.mMyEntryManager.a(this.a.mItems);
            }
        }, new com.sds.android.ttpod.common.a.a.a<j>(this) {
            final /* synthetic */ MyFragment a;

            {
                this.a = r1;
            }

            public void a(j jVar) {
                this.a.loadCustomizedHomepage();
            }
        });
        jVar.setTitle((int) R.string.choose_homepage_content);
        jVar.show();
    }

    private void loadCustomizedHomepage() {
        String bp = com.sds.android.ttpod.framework.storage.environment.b.bp();
        if (m.a(bp)) {
            initHomepageSetting();
            statsCustomizedHomepageShow();
            return;
        }
        for (int i = 0; i < this.mItems.length; i++) {
            this.mItems[i].setChecked(bp.contains(HOME_ELEMENT_ID[i]));
        }
        statsCustomizedHomepageShow();
    }

    private void statsCustomizedHomepageShow() {
        int i = 1;
        for (int i2 = 0; i2 < this.mItems.length; i2++) {
            if (this.mItems[i2].isChecked()) {
                new com.sds.android.ttpod.framework.a.b.c("show").a("module_name", HOME_ELEMENT_ID[i2]).a("module_id", HOME_ELEMENT_ID[i2]).a("location", String.valueOf(i)).a();
                i++;
            }
        }
    }

    private void saveCustomizedHomepage() {
        int i = 0;
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MY_DROP_MENU_CUSTOM_MY_OK.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        StringBuilder stringBuilder = new StringBuilder(c.HOMEPAGE_ELEMENT_SETTING.name());
        StringBuilder stringBuilder2 = new StringBuilder();
        while (i < this.mItems.length) {
            if (this.mItems[i].isChecked()) {
                stringBuilder.append(HOME_ELEMENT_ID[i]);
                if (stringBuilder2.length() > 0) {
                    stringBuilder2.append(' ');
                }
                stringBuilder2.append(HOME_ELEMENT_ID[i]);
            }
            i++;
        }
        sUserEvent.append(c.HOMEPAGE_ELEMENT_SETTING.name(), stringBuilder2.toString());
        sUserEvent.post();
        com.sds.android.ttpod.framework.storage.environment.b.v(stringBuilder.toString());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_MY);
        initHomepageSetting();
        loadCustomizedHomepage();
        com.sds.android.ttpod.framework.a.b.d.c.a(e.c(), getMediaCount(MediaStorage.GROUP_ID_ALL_LOCAL), getMediaCount(MediaStorage.GROUP_ID_FAV), (String) EnvironmentUtils.b.e().get("cpu_model"), (String) EnvironmentUtils.b.e().get("ram"));
    }

    private void initHomepageSetting() {
        this.mItems = new d[]{new d(0, 0, R.string.mine_my_favorite, R.string.icon_my_favorite, true), new d(1, 0, R.string.mine_my_download, R.string.icon_my_download, true), new d(2, 0, R.string.my_playlist, R.string.icon_my_songlist, true), new d(3, 0, R.string.mine_recent_play, R.string.icon_my_recent_play, true), new d(4, 0, R.string.local_music_recent_add, R.string.icon_my_recent_add, false)};
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, i.a(cls, "updateMediaLibraryChanged", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PUSH_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, i.a(cls, "pushFavoriteOnlineMediaListComplete", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, i.a(cls, "updateAddDownloadTaskDatabase", DownloadTaskInfo.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE, i.a(cls, "updateAddDownloadTaskListDatabase", List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.USER_ADDED_FAVORITE_MEDIA, i.a(cls, "userAddedFavoriteMedia", MediaItem.class));
    }

    public void updateMediaLibraryChanged(String str) {
        loadCount();
        this.mMyEntryManager.a(this.mItems);
    }

    public void pushFavoriteOnlineMediaListComplete() {
        this.mMyEntryManager.a(this.mItems);
    }

    public void userAddedFavoriteMedia(MediaItem mediaItem) {
        com.sds.android.ttpod.framework.storage.environment.b.aa(true);
        this.mMyEntryManager.a(this.mItems);
    }

    public void updateAddDownloadTaskDatabase(DownloadTaskInfo downloadTaskInfo) {
        if (!com.sds.android.ttpod.framework.storage.environment.b.aW()) {
            if (downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_AUDIO || downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_VIDEO) {
                com.sds.android.ttpod.framework.storage.environment.b.X(true);
            }
        }
    }

    public void updateAddDownloadTaskListDatabase(List<DownloadTaskInfo> list) {
        if (!com.sds.android.ttpod.framework.storage.environment.b.aW() && !list.isEmpty() && ((DownloadTaskInfo) list.get(0)).getType() == DownloadTaskInfo.TYPE_AUDIO) {
            com.sds.android.ttpod.framework.storage.environment.b.X(true);
        }
    }

    private void loadCount() {
        int mediaCount = getMediaCount(MediaStorage.GROUP_ID_ALL_LOCAL);
        this.mTvLocalMusicCount.setText(getString(R.string.count_of_media, Integer.valueOf(mediaCount)));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_main_my_header_all, null, false);
            this.mHeaderView = this.mRootView;
            this.mLayoutLocalMusic = this.mHeaderView.findViewById(R.id.layout_local_music);
            this.mUnicomText = (TextView) this.mHeaderView.findViewById(R.id.text_unicom);
            this.mUnicomCloseButton = (ImageButton) this.mHeaderView.findViewById(R.id.image_button_unicom_close);
            this.mUnicomLayout = this.mHeaderView.findViewById(R.id.layout_unicom);
            initHomeUnicomLayout();
            this.mItvLocalMusic = (IconTextView) this.mHeaderView.findViewById(R.id.itv_local_music);
            this.mTvLocalMusicCount = (TextView) this.mHeaderView.findViewById(R.id.text_count);
            this.mItvPlay = (IconTextView) this.mHeaderView.findViewById(R.id.itv_play);
            this.mItvMenu = (IconTextView) this.mHeaderView.findViewById(R.id.itv_menu);
            this.mTvLocalMusicTitle = (TextView) this.mHeaderView.findViewById(R.id.text_title);
            this.mSgvEntry = (SimpleGridView) this.mHeaderView.findViewById(R.id.sgv_entry);
            this.mSgvEntry.setNumColumns(2);
            this.mMyEntryManager = new a(this.mSgvEntry);
            this.mMyEntryManager.a(this.mItems);
        }
        setClickListener(this.mOnClickListener);
        loadCount();
        return this.mRootView;
    }

    private void setClickListener(OnClickListener onClickListener) {
        this.mLayoutLocalMusic.setOnClickListener(onClickListener);
        this.mUnicomText.setOnClickListener(onClickListener);
        this.mUnicomCloseButton.setOnClickListener(onClickListener);
        this.mItvPlay.setOnClickListener(onClickListener);
        this.mItvMenu.setOnClickListener(onClickListener);
    }

    public void onStop() {
        super.onStop();
        com.sds.android.ttpod.component.d.f.a(this.mDropdownMenu);
    }

    public void onDestroyView() {
        super.onDestroyView();
        com.sds.android.ttpod.framework.storage.environment.b.b(c.IS_ONLY_USE_WIFI, this.mPreferencesChangeListener);
        com.sds.android.ttpod.framework.storage.environment.b.b(c.IS_SHOW_DOWNLOAD_HIGHLIGHT, this.mPreferencesChangeListener);
        setClickListener(null);
    }

    private boolean isShowUnicomLayout() {
        return e.b() && com.sds.android.ttpod.framework.storage.a.a.a().L();
    }

    private void initHomeUnicomLayout() {
        if (isShowUnicomLayout()) {
            this.mUnicomLayout.setVisibility(0);
            com.sds.android.ttpod.framework.a.b.b.a("unicom_show_bannel");
            aa.m();
            new SUserEvent("PAGE_CLICK", 1120, 0).post();
            return;
        }
        this.mUnicomLayout.setVisibility(8);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        com.sds.android.ttpod.framework.storage.environment.b.a(c.IS_ONLY_USE_WIFI, this.mPreferencesChangeListener);
        com.sds.android.ttpod.framework.storage.environment.b.a(c.IS_SHOW_DOWNLOAD_HIGHLIGHT, this.mPreferencesChangeListener);
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mHeaderView = null;
            this.mMyEntryManager = null;
            this.mRootView = null;
            this.mReloadTheme = true;
        }
    }

    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            this.mReloadTheme = false;
            setActionItemIcon(this.mItems[0], ThemeElement.HOME_FAVORITE_IMAGE);
            setActionItemIcon(this.mItems[1], ThemeElement.HOME_DOWNLOAD_IMAGE);
            setActionItemIcon(this.mItems[3], ThemeElement.HOME_RECENT_PLAY_IMAGE);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mLayoutLocalMusic, ThemeElement.HOME_BACKGROUND);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mTvLocalMusicTitle, ThemeElement.HOME_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.mTvLocalMusicCount, ThemeElement.HOME_TEXT);
            v.a(this.mItvMenu, ThemeElement.HOME_MENU_IMAGE, (int) R.string.icon_my_music_menu, ThemeElement.HOME_TEXT);
            v.a(this.mItvPlay, ThemeElement.HOME_RANDOM_PLAY_IMAGE, (int) R.string.icon_my_luck_play, ThemeElement.HOME_TEXT);
            v.a(this.mItvLocalMusic, ThemeElement.HOME_MUSIC_IMAGE, (int) R.string.icon_my_music, ThemeElement.HOME_TEXT);
        }
    }

    private void setActionItemIcon(com.sds.android.ttpod.component.b.a aVar, String str) {
    }

    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
        refreshListViewTheme();
    }

    private void refreshListViewTheme() {
        if (this.mMyEntryManager != null) {
            this.mMyEntryManager.a(this.mItems);
        }
    }

    private void tryRandomPlay(final View view, final boolean z) {
        view.clearAnimation();
        h a = h.a((Object) view, "rotation", 0.0f, GRIDDLE_START_ANGLE, 0.0f, GRIDDLE_END_ANGLE, 0.0f).a(500);
        a.a(new com.sds.android.ttpod.component.lockscreen.a.a.a.a(this) {
            final /* synthetic */ MyFragment c;

            public void a(com.sds.android.ttpod.component.lockscreen.a.a.a aVar) {
            }

            public void b(com.sds.android.ttpod.component.lockscreen.a.a.a aVar) {
                view.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.c.mRootView != null) {
                            boolean booleanValue;
                            if (z) {
                                booleanValue = ((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_LOCAL_RANDOM, new Object[0]), Boolean.class)).booleanValue();
                            } else {
                                booleanValue = false;
                            }
                            if (booleanValue) {
                                String string;
                                MediaItem mediaItem = (MediaItem) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_MEDIA_ITEM, com.sds.android.ttpod.framework.storage.environment.b.m(), com.sds.android.ttpod.framework.storage.environment.b.n()), MediaItem.class);
                                if (mediaItem == null || !TTTextUtils.isValidateMediaString(mediaItem.getArtist())) {
                                    string = this.a.c.getString(R.string.unknown_artist);
                                } else {
                                    string = this.a.c.getString(R.string.random_song_info, mediaItem.getArtist());
                                }
                                com.sds.android.ttpod.component.d.f.a(string);
                                return;
                            }
                            com.sds.android.ttpod.component.d.f.a("没有歌曲，臣妾做不到啊");
                        }
                    }
                });
            }

            public void c(com.sds.android.ttpod.component.lockscreen.a.a.a aVar) {
            }

            public void d(com.sds.android.ttpod.component.lockscreen.a.a.a aVar) {
            }
        });
        a.a();
    }

    private void doClickActionItem(com.sds.android.ttpod.component.b.a aVar, boolean z) {
        Context activity = getActivity();
        int g = aVar.g();
        Bundle bundle;
        if (g == 0) {
            com.sds.android.ttpod.framework.a.b.b.a("my_favorite");
            bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_FAV);
            bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, getString(R.string.mine_my_favorite));
            launchFragment((BaseFragment) Fragment.instantiate(getActivity(), MyFavoriteFragment.class.getName(), bundle));
            com.sds.android.ttpod.framework.storage.environment.b.aa(false);
            this.mMyEntryManager.a(this.mItems);
            l.F();
            t.a(r.ACTION_MY_FAVORITE, s.PAGE_MY_FAVORITE);
            p.a("favorite");
        } else if (1 == g) {
            com.sds.android.ttpod.framework.a.b.b.a("my_download");
            launchFragment((BaseFragment) Fragment.instantiate(activity, MyDownloadFragment.class.getName()));
            l.M();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_MY_DOWNLOAD.getValue(), 0, s.PAGE_MY_DOWNLOAD_DOWNLOADED.getValue());
            sUserEvent.append(DownloadManagerFragment.DOWNLOAD_TYPE, DownloadTaskInfo.TYPE_AUDIO);
            sUserEvent.setPageParameter(true);
            sUserEvent.post();
        } else if (2 == g) {
            com.sds.android.ttpod.framework.a.b.b.a("my_songlist");
            Bundle bundle2 = new Bundle();
            bundle2.putString(GroupListFragment.KEY_GROUP_TYPE, GroupType.CUSTOM_LOCAL.name());
            bundle2.putString("key_list_title", this.mItems[2].d().toString());
            bundle2.putBoolean("key_list_creatable", true);
            bundle2.putBoolean("key_list_draggable", true);
            BaseFragment baseFragment = (BaseFragment) Fragment.instantiate(activity, MySongListFragment.class.getName(), bundle2);
            baseFragment.setStatisticPage(s.PAGE_MY_SONGLIST);
            launchFragment(baseFragment);
            l.G();
            t.a(r.ACTION_MY_SONGLIST, s.PAGE_MY_SONGLIST);
        } else if (3 == g) {
            com.sds.android.ttpod.framework.a.b.b.a("recent_play");
            bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_RECENTLY_PLAY);
            bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, getString(R.string.mine_recent_play));
            launchFragment((BaseFragment) Fragment.instantiate(getActivity(), RecentPlayFragment.class.getName(), bundle));
            l.Y();
            t.a(r.ACTION_RECENT_PLAY, s.PAGE_RECENT_PLAY);
        } else if (4 == g) {
            com.sds.android.ttpod.framework.a.b.b.a("recent_add");
            bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_RECENTLY_ADD);
            bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, getString(R.string.local_music_recent_add));
            launchFragment((BaseFragment) Fragment.instantiate(getActivity(), RecentAddFragment.class.getName(), bundle));
            l.Z();
            t.a(r.ACTION_RECENT_ADDED, s.PAGE_RECENT_ADDED);
        } else if (6 == g) {
            com.sds.android.ttpod.component.d.f.b(getActivity(), null, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                final /* synthetic */ MyFragment a;

                {
                    this.a = r1;
                }

                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_GROUP, bVar.c(0).e().toString()), String.class);
                }
            });
        }
    }

    protected void launchSubMediaListFragment(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, str);
        bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, str2);
        launchFragment((BaseFragment) Fragment.instantiate(getActivity(), SubMediaListFragment.selectSubMediaListFragmentClassName(str), bundle));
    }

    public static void displayHighlightFlag(View view, com.sds.android.ttpod.component.b.a aVar) {
        boolean aY;
        int i = 0;
        if (aVar.g() == 0) {
            aY = com.sds.android.ttpod.framework.storage.environment.b.aY();
        } else if (aVar.g() == 1) {
            aY = com.sds.android.ttpod.framework.storage.environment.b.aW();
        } else {
            aY = false;
        }
        if (!aY) {
            i = 4;
        }
        view.setVisibility(i);
    }

    public static int getMediaCount(String str) {
        return ((Integer) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_MEDIA_COUNT, str), Integer.class)).intValue();
    }
}
