package com.sds.android.ttpod.fragment.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.PictureManagerActivity;
import com.sds.android.ttpod.activities.PlayingListActivity;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.activities.musiccircle.SlidingAlbumDetailFragment;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.a.n;
import com.sds.android.ttpod.component.d.a.n.a;
import com.sds.android.ttpod.component.d.a.n.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.g.a.e;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.base.d;
import com.sds.android.ttpod.fragment.main.findsong.SubRelatedRecommendFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.PlayerMediaListFragment;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.a.b.d.p;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.skin.b.u;
import com.sds.android.ttpod.framework.modules.skin.j;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class PortraitPlayerFragment extends BasePlayerFragment implements a, b, d {
    private static final int REQUEST_CODE_PLAY_LIST = 1002;
    private static final int SEARCH_LYRIC = 1000;
    private static final int SEARCH_PICTURE = 1001;
    private static final String TAG = "PortraitPlayerFragment";
    private com.sds.android.ttpod.ThirdParty.a mApp360Callback = new com.sds.android.ttpod.ThirdParty.a(this) {
        final /* synthetic */ PortraitPlayerFragment a;

        {
            this.a = r1;
        }
    };
    private boolean mDownloadDialogClick = false;
    private com.sds.android.ttpod.widget.b mDropDownMenu;
    private com.sds.android.ttpod.fragment.base.a mImmersiveObserver;
    private PlayerMediaListFragment mMediaListFragment;
    private e.b mScreenChangeListener = new e.b(this) {
        final /* synthetic */ PortraitPlayerFragment a;

        {
            this.a = r1;
        }

        public void a(int i, int i2) {
            if (this.a.mVisible) {
                l.a().b(this.a.getCurrentPage(i2));
                l.a().a(this.a.getCurrentPage(i));
            }
        }
    };
    private j mSkinCache = null;
    private a mSkinEventHandler;
    private e mViewController;
    private boolean mVisible = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setVolumeControlStream(3);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.SKIN_CHANGED, i.a(cls, "skinChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN_FINISHED, i.a(cls, "loadSkinFinished", j.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_MODE, i.a(cls, "updatePlayMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SLEEP_MODE, i.a(cls, "updateSleepMode", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_LYRIC_DELETED, i.a(cls, "lyricDeleted", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PICTURE_DELETED, i.a(cls, "pictureDeleted", MediaItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_REPORT, i.a(cls, "updateReport", com.sds.android.ttpod.framework.support.search.task.d.b.class, MediaItem.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND, i.a(cls, "updateBackground", Drawable.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_FAVORITE_CHANGED, i.a(cls, "updateFavoriteChanged", new Class[0]));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.framelayout_container, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (com.sds.android.sdk.lib.util.j.i()) {
            this.mImmersiveObserver = new com.sds.android.ttpod.fragment.base.a(getActivity().findViewById(R.id.view_immersive_observer));
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002 && this.mViewController != null) {
            this.mViewController.b("OnPlaylistDisappear");
        }
    }

    public void resumeRefresh() {
        if (this.mViewController != null) {
            this.mViewController.B();
            this.mViewController.u();
        }
        startUpdatePlayPosition();
    }

    public void pauseRefresh() {
        if (this.mViewController != null) {
            this.mViewController.A();
            this.mViewController.t();
        }
        stopUpdatePlayPosition();
    }

    public void onResume() {
        super.onResume();
        if (this.mViewController != null && getUserVisibleHint()) {
            this.mViewController.u();
        }
        startUpdatePlayPosition();
        if (this.mVisible) {
            l.a().a(getCurrentPage(getCurrentPanelIndex()));
            trackPlaySong();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mViewController != null && getUserVisibleHint()) {
            this.mViewController.t();
            com.sds.android.ttpod.framework.storage.environment.b.f(this.mViewController.j());
        }
        stopUpdatePlayPosition();
        if (this.mVisible) {
            l.a().b(getCurrentPage(getCurrentPanelIndex()));
        }
    }

    public void setVisible(boolean z) {
        this.mVisible = z;
    }

    public void onStatsResume() {
        l.a().a(getCurrentPage(getCurrentPanelIndex()));
        trackPlaySong();
    }

    private void trackPlaySong() {
        String a = p.a().a("songlist_type");
        String a2 = p.a().a("songlist_id");
        String a3 = p.a().a("online");
        String a4 = p.a().a("module_id");
        String a5 = p.a().a("search_type");
        String a6 = p.a().a("keyword");
        t.a().a(a, a2, a3);
        t.a().a(a5, a6);
        t.a().a(a4);
    }

    public void onStatsPause() {
        l.a().b(getCurrentPage(getCurrentPanelIndex()));
    }

    private String getCurrentPage(int i) {
        String str = "";
        if (this.mViewController == null || i < 0 || i >= this.mViewController.g()) {
            return str;
        }
        String a = this.mViewController.a(i);
        if ("Main".equals(a)) {
            return "tt_player_pic";
        }
        if ("Visual".equals(a)) {
            return "tt_player_spectrum";
        }
        if ("Lyric".equals(a)) {
            return "tt_player_lyric";
        }
        if ("Playing".equals(a)) {
            return "tt_player_media_list";
        }
        return str;
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        j l = com.sds.android.ttpod.framework.storage.a.a.a().l();
        if (l == null || l.b() == null) {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN, new Object[0]));
        } else {
            loadSkinFinished(l);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mViewController != null) {
            this.mViewController.h();
        }
        f.a(this.mDropDownMenu);
    }

    public void onDetach() {
        super.onDetach();
        if (this.mViewController != null) {
            this.mViewController.b();
        }
    }

    private void addPlayingList(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.playing));
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, com.sds.android.ttpod.framework.storage.environment.b.m());
        this.mMediaListFragment = (PlayerMediaListFragment) Fragment.instantiate(getActivity(), PlayerMediaListFragment.class.getName(), bundle);
        getChildFragmentManager().beginTransaction().replace(view.getId(), this.mMediaListFragment).commitAllowingStateLoss();
    }

    public void onPreVisible() {
        if (this.mMediaListFragment != null) {
            this.mMediaListFragment.scrollToPlayingRow();
        }
    }

    public void loadSkinFinished(j jVar) {
        View view = null;
        final Context activity = getActivity();
        View view2 = getView();
        if (jVar != null && jVar.d() && view2 != null && activity != null) {
            View findViewWithTag;
            RelativeLayout relativeLayout = (RelativeLayout) view2.findViewById(R.id.container);
            relativeLayout.removeAllViews();
            if (this.mViewController != null) {
                this.mViewController.b();
            }
            this.mViewController = new e(getActivity(), jVar);
            this.mViewController.a(getSkinEventHandler());
            this.mViewController.a(this.mScreenChangeListener);
            this.mSkinEventHandler.a(this.mViewController);
            View a = this.mViewController.a();
            if (a != null) {
                relativeLayout.addView(a);
                findViewWithTag = a.findViewWithTag("PlayingList");
                if (findViewWithTag instanceof ViewGroup) {
                    findViewWithTag.setId(R.id.playing_list);
                    addPlayingList(findViewWithTag);
                }
            }
            if (this.mSkinCache != null) {
                this.mSkinCache.i();
            }
            this.mSkinCache = jVar;
            if (!com.sds.android.ttpod.framework.storage.environment.b.ab()) {
                updateBackground(v.a());
            }
            if (VERSION.SDK_INT > 10) {
                addTestParticleScene(jVar);
            }
            if (!(a == null || this.mImmersiveObserver == null)) {
                Drawable c = this.mViewController.c();
                if (c != null) {
                    findViewWithTag = new View(activity);
                    findViewWithTag.setBackgroundDrawable(c);
                    relativeLayout.addView(findViewWithTag, new LayoutParams(-1, 0));
                } else {
                    findViewWithTag = null;
                }
                c = this.mViewController.d();
                if (c != null) {
                    view = new View(activity);
                    view.setBackgroundDrawable(c);
                    ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, 0);
                    layoutParams.addRule(12, -1);
                    relativeLayout.addView(view, layoutParams);
                }
                this.mImmersiveObserver.a(a, a, findViewWithTag, view);
                this.mImmersiveObserver.a();
            }
            relativeLayout.post(new Runnable(this) {
                final /* synthetic */ PortraitPlayerFragment b;

                public void run() {
                    if (this.b.mViewController != null && activity != null) {
                        AudioManager audioManager = (AudioManager) activity.getSystemService("audio");
                        this.b.mViewController.b(audioManager.getStreamVolume(3), audioManager.getStreamMaxVolume(3));
                        this.b.initViewController();
                        if (this.b.getUserVisibleHint()) {
                            this.b.mViewController.u();
                        }
                    }
                }
            });
        }
    }

    private void addTestParticleScene(j jVar) {
        Context activity = getActivity();
        View findViewById = activity.findViewById(R.id.surface_view_scene);
        if (findViewById != null) {
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        }
        Renderer a = com.sds.android.ttpod.component.f.a.a(activity, jVar);
        if (a != null) {
            View gLSurfaceView = new GLSurfaceView(activity);
            gLSurfaceView.setId(R.id.surface_view_scene);
            gLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
            gLSurfaceView.setRenderer(a);
            gLSurfaceView.getHolder().setFormat(-3);
            gLSurfaceView.setZOrderOnTop(true);
            activity.addContentView(gLSurfaceView, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            g.d(TAG, "PortraitPlayerFragment.updateBackground background is null");
        } else if (this.mViewController != null) {
            if (com.sds.android.ttpod.framework.storage.environment.b.ab()) {
                j l = com.sds.android.ttpod.framework.storage.a.a.a().l();
                if (l != null) {
                    drawable = l.b(BaseApplication.e());
                    if (drawable == null) {
                        drawable = l.a(BaseApplication.e());
                    }
                } else {
                    g.a(TAG, "PortraitPlayerFragment.updateBackground skin cache is null!");
                }
            }
            this.mViewController.a().setBackgroundDrawable(drawable);
        }
    }

    public void updateFavoriteChanged() {
        if (this.mViewController != null) {
            MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
            if (!M.isNull()) {
                if (k.a(M)) {
                    M.setFav(true);
                    this.mViewController.a(true);
                    return;
                }
                M.setFav(false);
                this.mViewController.a(false);
            }
        }
    }

    public void updateDownloadTaskState(DownloadTaskInfo downloadTaskInfo) {
    }

    public void skinChanged() {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN, new Object[0]));
    }

    protected void artistBitmapLoadFinished() {
        super.artistBitmapLoadFinished();
        if (this.mViewController != null) {
            this.mViewController.a(getCurrentArtistBitmap());
        }
    }

    protected void switchArtistPicture(Bitmap bitmap, String str) {
        super.switchArtistPicture(bitmap, str);
        if (this.mViewController != null) {
            this.mViewController.b(bitmap);
        }
    }

    protected void artistBitmapSearchStarted() {
        super.artistBitmapSearchStarted();
        if (this.mViewController != null) {
            this.mViewController.s();
        }
    }

    protected void artistBitmapDownloadStarted() {
        super.artistBitmapDownloadStarted();
        if (this.mViewController != null) {
            this.mViewController.q();
        }
    }

    protected void artistBitmapDownloadError() {
        super.artistBitmapDownloadError();
        if (this.mViewController != null) {
            this.mViewController.r();
        }
    }

    public void pictureDeleted(MediaItem mediaItem) {
        if (this.mViewController != null) {
            this.mViewController.a(null);
        }
    }

    protected void lyricLoadFinished() {
        super.lyricLoadFinished();
        if (this.mViewController != null) {
            this.mViewController.a(getCurrentLyric());
        }
    }

    protected void lyricSearchStarted() {
        super.lyricSearchStarted();
        if (this.mViewController != null) {
            this.mViewController.p();
        }
    }

    protected void lyricDownloadStarted() {
        super.lyricDownloadStarted();
        if (this.mViewController != null) {
            this.mViewController.k();
        }
    }

    protected void lyricSearchFailed() {
        super.lyricSearchFailed();
        if (this.mViewController != null) {
            this.mViewController.l();
        }
    }

    protected void lyricDownloadError() {
        super.lyricDownloadError();
        if (this.mViewController != null) {
            this.mViewController.m();
        }
    }

    protected void lyricNetError() {
        super.lyricNetError();
        if (this.mViewController != null) {
            this.mViewController.n();
        }
    }

    protected void lyricSearchStop() {
        super.lyricSearchStop();
        if (this.mViewController != null) {
            this.mViewController.o();
        }
    }

    public void lyricDeleted(MediaItem mediaItem) {
        if (this.mViewController != null) {
            this.mViewController.a(null);
        }
    }

    public void playMediaChanged() {
        super.playMediaChanged();
        if (this.mViewController != null) {
            loadLyric();
            loadArtistBitmap();
            this.mViewController.a((long) com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).l().intValue(), 0.0f);
        }
    }

    public void updatePlayMediaInfo() {
        if (this.mViewController != null) {
            this.mViewController.a(com.sds.android.ttpod.framework.storage.a.a.a().M());
            this.mViewController.a((long) com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).l().intValue(), com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).m());
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (this.mViewController != null) {
            this.mViewController.a(playStatus);
        }
    }

    public void updatePlayMode() {
        if (this.mViewController != null) {
            this.mViewController.a(com.sds.android.ttpod.framework.storage.environment.b.l());
        }
    }

    public void updateSleepMode() {
        if (this.mViewController != null) {
            this.mViewController.d(((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue());
        }
    }

    public void updateReport(com.sds.android.ttpod.framework.support.search.task.d.b bVar, MediaItem mediaItem, Boolean bool) {
        f.a(bool.booleanValue() ? R.string.report_send_successful : R.string.report_send_failed);
    }

    private void initViewController() {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        g.a(TAG, "initViewController looklyricloading %s", M.getTitle());
        if (!M.isNull()) {
            this.mViewController.a(M, null, null);
            updatePlayMediaInfo();
            loadLyric();
            loadArtistBitmap();
        } else if (!this.mViewController.M()) {
            this.mViewController.a(M, null, null);
        }
        updatePlayPosition();
        updatePlayMode();
        updateSleepMode();
        updatePlayStatus(com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).n());
        startupSearchLyricPic();
    }

    private void startupSearchLyricPic() {
        BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", "search_lyric_pic_command"));
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            if (this.mViewController != null) {
                this.mViewController.u();
                getView().requestLayout();
            }
        } else if (this.mViewController != null) {
            this.mViewController.t();
        }
    }

    private void loadArtistBitmap() {
        if (this.mViewController != null) {
            this.mViewController.a(getCurrentArtistBitmap());
        }
    }

    private void loadLyric() {
        if (this.mViewController != null) {
            this.mViewController.a(getCurrentLyric());
        }
    }

    private a getSkinEventHandler() {
        if (this.mSkinEventHandler == null) {
            this.mSkinEventHandler = new a(this, getActivity(), this.mViewController) {
                final /* synthetic */ PortraitPlayerFragment a;

                public boolean a(int i, Object obj) {
                    if (super.a(i, obj)) {
                        return true;
                    }
                    Object access$400 = this.a.getParent();
                    switch (i) {
                        case 1:
                            if (access$400 instanceof com.sds.android.ttpod.fragment.a) {
                                ((com.sds.android.ttpod.fragment.a) access$400).onClosePlayerPanelRequested();
                                break;
                            }
                            break;
                        case 3:
                            if (this.a.mViewController != null) {
                                this.a.mViewController.b("OnPlaylistShow");
                            }
                            Context activity = this.a.getActivity();
                            this.a.startActivityForResult(new Intent(activity, PlayingListActivity.class), 1002);
                            if (this.a.mSkinCache != null && this.a.mSkinCache.d()) {
                                u b = this.a.mSkinCache.b().b(0);
                                if (b != null) {
                                    PlayingListActivity.overrideActivityInAnimation(activity, b.f());
                                }
                            }
                            new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_PLAYING_LIST.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue(), s.PAGE_PLAYING_LIST.getValue()).post();
                            break;
                        case 7:
                            this.a.tryShowLyricMenu();
                            break;
                        case 8:
                            this.a.checkOfflineModeToShowDialog(1000);
                            break;
                        case 9:
                            this.a.tryShowPictureMenu();
                            break;
                        case 30:
                            n nVar = new n(this.a.getActivity());
                            nVar.a(this.a);
                            nVar.a(this.a);
                            nVar.show();
                            new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_MENU.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue(), s.PAGE_NONE.getValue()).post();
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_show_menu");
                            break;
                        case 31:
                            this.a.startActivity(new Intent(this.a.getActivity(), ThemeManagementActivity.class));
                            x.t();
                            x.g("play");
                            break;
                        case 32:
                            this.a.showAdjustMoreDialog();
                            break;
                        case 33:
                            this.a.showDeleteLyricDialog();
                            break;
                        case 34:
                            com.sds.android.ttpod.ThirdParty.d.a(this.a.getActivity(), this.a.mApp360Callback);
                            break;
                        default:
                            return false;
                    }
                    return true;
                }
            };
        }
        return this.mSkinEventHandler;
    }

    public void onPictureOptionSelected() {
        tryShowPictureMenu();
    }

    public void onLyricOptionSelected() {
        checkOfflineModeToShowDialog(1000);
    }

    public void onMediaOptionSelected() {
        getSkinEventHandler().a(null);
    }

    private com.sds.android.ttpod.component.b.a.b createItemClickListener() {
        return new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ PortraitPlayerFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
                Object access$1200 = this.a.getParent();
                switch (aVar.g()) {
                    case R.id.media_menu_album:
                        if (access$1200 instanceof com.sds.android.ttpod.fragment.a) {
                            ((com.sds.android.ttpod.fragment.a) access$1200).onClosePlayerPanelRequested();
                        }
                        BaseFragment instantiate = SlidingAlbumDetailFragment.instantiate(M.getAlbumId(), M.getAlbum());
                        if (instantiate != null) {
                            this.a.launchFragment(instantiate);
                            return;
                        }
                        return;
                    case R.id.media_menu_related:
                        if (access$1200 instanceof com.sds.android.ttpod.fragment.a) {
                            ((com.sds.android.ttpod.fragment.a) access$1200).onClosePlayerPanelRequested();
                        }
                        SubRelatedRecommendFragment.launch((BaseActivity) this.a.getActivity(), M, true);
                        return;
                    case R.id.media_menu_singer:
                        if (access$1200 instanceof com.sds.android.ttpod.fragment.a) {
                            ((com.sds.android.ttpod.fragment.a) access$1200).onClosePlayerPanelRequested();
                        }
                        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(M.getExtra(), OnlineMediaItem.class);
                        if (onlineMediaItem != null) {
                            SingerDetailFragment.launch((BaseActivity) this.a.getActivity(), M.getArtist(), (int) M.getArtistID(), true, onlineMediaItem.getSingerSFlag());
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void onSetRingtoneSelected() {
        com.sds.android.ttpod.framework.a.b.l.aM();
        f.a(getActivity(), com.sds.android.ttpod.framework.storage.a.a.a().M());
    }

    public void onDownloadSelected() {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (M == null || M.isNull() || !M.isTtfmRadioSingleSong()) {
            new com.sds.android.ttpod.component.c.b(getActivity()).a(M, com.sds.android.ttpod.framework.a.b.p.b());
        } else if (!this.mDownloadDialogClick) {
            com.sds.android.cloudapi.ttpod.a.r.a(M.getSongID()).a(new com.sds.android.sdk.lib.request.p<OnlineMediaItemsResult>(this) {
                final /* synthetic */ PortraitPlayerFragment a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((OnlineMediaItemsResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((OnlineMediaItemsResult) baseResult);
                }

                public void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                    if (onlineMediaItemsResult.getDataList().size() > 0) {
                        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) onlineMediaItemsResult.getDataList().get(0);
                        MediaItem a = k.a(onlineMediaItem);
                        if (com.sds.android.ttpod.framework.a.n.a(onlineMediaItem, c.d()) != null) {
                            new com.sds.android.ttpod.component.c.b(this.a.getActivity()).a(a, com.sds.android.ttpod.framework.a.b.p.b());
                        }
                    }
                    this.a.mDownloadDialogClick = false;
                }

                public void b(OnlineMediaItemsResult onlineMediaItemsResult) {
                    this.a.mDownloadDialogClick = false;
                }
            });
            this.mDownloadDialogClick = true;
        }
    }

    public void onShareOptionSelected() {
        getSkinEventHandler().a();
    }

    public void onAdjustOptionSelected() {
        this.mViewController.z();
    }

    public void onMoreOptionSelected() {
        getSkinEventHandler().a(createItemClickListener());
    }

    public void onVolumeChanged(int i, int i2) {
        if (this.mViewController != null) {
            this.mViewController.b(i, i2);
        }
    }

    protected void updatePlayPosition() {
        getSkinEventHandler().b();
    }

    private void checkOfflineModeToShowDialog(final int i) {
        com.sds.android.ttpod.b.p.a(getActivity(), new OnClickListener(this) {
            final /* synthetic */ PortraitPlayerFragment b;

            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case -1:
                        if (1001 == i) {
                            PortraitPlayerFragment.startActivityFromBottom(this.b.getActivity(), PictureManagerActivity.class);
                            return;
                        } else if (1000 == i) {
                            this.b.showSearchLyricDialog();
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        });
    }

    private void tryShowLyricMenu() {
        final MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            f.a(getActivity(), "Lyric".equals(this.mViewController.d(this.mViewController.j()).H()) ? new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, (int) R.drawable.img_contextmenu_search, (int) R.string.search_lyric), new com.sds.android.ttpod.component.b.a(1, (int) R.drawable.img_contextmenu_remove, (int) R.string.delete_lyric), new com.sds.android.ttpod.component.b.a(2, (int) R.drawable.img_contextmenu_adjust_lyric, (int) R.string.adjust_lyric), new com.sds.android.ttpod.component.b.a(3, (int) R.drawable.img_contextmenu_error_report, (int) R.string.report_lyric_error)} : new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, (int) R.drawable.img_contextmenu_search, (int) R.string.search_lyric), new com.sds.android.ttpod.component.b.a(1, (int) R.drawable.img_contextmenu_remove, (int) R.string.delete_lyric), new com.sds.android.ttpod.component.b.a(3, (int) R.drawable.img_contextmenu_error_report, (int) R.string.report_lyric_error)}, M.getTitle(), new com.sds.android.ttpod.component.b.a.b(this) {
                final /* synthetic */ PortraitPlayerFragment b;

                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    int g = aVar.g();
                    if (g == 0) {
                        this.b.checkOfflineModeToShowDialog(1000);
                    } else if (g == 1) {
                        this.b.showDeleteLyricDialog();
                    } else if (g == 2) {
                        this.b.mViewController.i();
                    } else {
                        this.b.showLyricErrorReportDialog(M);
                    }
                }
            });
        }
    }

    private void tryShowPictureMenu() {
        checkOfflineModeToShowDialog(1001);
    }

    private static Activity getTopActivity(Activity activity) {
        while (activity.getParent() != null) {
            activity = activity.getParent();
        }
        return activity;
    }

    private void showAdjustMoreDialog() {
        com.sds.android.ttpod.component.b.a aVar = new com.sds.android.ttpod.component.b.a(3, (int) R.drawable.img_contextmenu_download_song, (int) R.string.download_song);
        com.sds.android.ttpod.component.b.a aVar2 = new com.sds.android.ttpod.component.b.a(3, (int) R.drawable.img_contextmenu_manager_song, (int) R.string.manager_song);
        com.sds.android.ttpod.component.b.a[] aVarArr = new com.sds.android.ttpod.component.b.a[5];
        aVarArr[0] = new com.sds.android.ttpod.component.b.a(0, (int) R.drawable.img_contextmenu_search, (int) R.string.search_lyric);
        aVarArr[1] = new com.sds.android.ttpod.component.b.a(1, (int) R.drawable.img_contextmenu_remove_lyric, (int) R.string.delete_lyric);
        aVarArr[2] = new com.sds.android.ttpod.component.b.a(2, (int) R.drawable.img_contextmenu_manager_pic, (int) R.string.manager_picture);
        if (!com.sds.android.ttpod.framework.storage.a.a.a().M().isOnline()) {
            aVar = aVar2;
        }
        aVarArr[3] = aVar;
        aVarArr[4] = new com.sds.android.ttpod.component.b.a(4, (int) R.drawable.img_contextmenu_share_song, (int) R.string.share_song);
        Context activity = getActivity();
        f.a(activity, aVarArr, activity.getString(R.string.more), new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ PortraitPlayerFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                int g = aVar.g();
                if (g == 0) {
                    this.a.checkOfflineModeToShowDialog(1000);
                } else if (g == 1) {
                    this.a.showDeleteLyricDialog();
                } else if (g == 2) {
                    this.a.tryShowPictureMenu();
                } else if (g == 3) {
                    this.a.getSkinEventHandler().a(null);
                } else if (g == 4) {
                    this.a.getSkinEventHandler().a();
                }
            }
        });
    }

    private static void startActivityFromBottom(Context context, Class<?> cls) {
        Intent flags = new Intent(context, cls).setFlags(603979776);
        Activity activity = null;
        if (context instanceof Activity) {
            Context topActivity = getTopActivity((Activity) context);
            if (topActivity != null) {
                context = topActivity;
            }
        }
        context.startActivity(flags.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START));
        if (activity != null) {
            activity.overridePendingTransition(R.anim.push_bottom_in, 0);
        }
    }

    private void showSearchLyricDialog() {
        final MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            final String title = M.getTitle();
            final String artist = M.getArtist();
            com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(getActivity(), new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, getString(R.string.title), M.getTitle(), getString(R.string.please_input_title)), new com.sds.android.ttpod.component.d.a.b.a(2, getString(R.string.artist), M.getArtist(), getString(R.string.please_input_artist))}, R.string.search, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                final /* synthetic */ PortraitPlayerFragment d;

                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    try {
                        com.sds.android.ttpod.component.d.a.b.a c = bVar.c(1);
                        com.sds.android.ttpod.component.d.a.b.a c2 = bVar.c(2);
                        a(c.e().toString(), c2.e().toString());
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_LYRIC, M, r1, r0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                private void a(String str, String str2) {
                    int i = 1;
                    if (m.a(title, str) && m.a(artist, str2)) {
                        i = 0;
                    }
                    new com.sds.android.ttpod.framework.a.b.b().b("portrait_menu_search_lyric").a(Downloads.COLUMN_STATUS, String.valueOf(i)).a();
                }
            }, null);
            bVar.setTitle((int) R.string.search_lyric);
            bVar.show();
        }
    }

    private void showSearchPictureDialog() {
        final MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(getActivity(), new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, getString(R.string.artist), M.getArtist(), getString(R.string.please_input_artist))}, R.string.search, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                final /* synthetic */ PortraitPlayerFragment b;

                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    com.sds.android.ttpod.component.d.a.b.a c = bVar.c(1);
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_PICTURE, M, c.e().toString(), null));
                }
            }, null);
            bVar.setTitle((int) R.string.search_picture);
            bVar.show();
        }
    }

    private void showDeleteLyricDialog() {
        h hVar = new h(getActivity(), (int) R.string.confirm_delete_lyric, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ PortraitPlayerFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                if (!m.a(com.sds.android.ttpod.framework.storage.a.a.a().i())) {
                    new ArrayList().add(com.sds.android.ttpod.framework.storage.a.a.a().M());
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_LYRIC, r0));
                }
            }
        }, null);
        hVar.setTitle((int) R.string.delete_lyric);
        hVar.show();
    }

    private void showLyricErrorReportDialog(final MediaItem mediaItem) {
        final com.sds.android.ttpod.component.d.a.e eVar = new com.sds.android.ttpod.component.d.a.e(getActivity(), Arrays.asList(new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, 0, (int) R.string.download_error), new com.sds.android.ttpod.component.b.a(1, 0, (int) R.string.not_matched), new com.sds.android.ttpod.component.b.a(2, 0, (int) R.string.not_synced_or_misprint)}), null, null);
        eVar.b(R.string.cancel, null);
        eVar.setTitle((int) R.string.report_lyric_error);
        eVar.a(new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ PortraitPlayerFragment c;

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                com.sds.android.ttpod.framework.support.search.task.d.a aVar2 = com.sds.android.ttpod.framework.support.search.task.d.a.REPORT_NO_CONTENT_STATE;
                if (i == 2) {
                    aVar2 = com.sds.android.ttpod.framework.support.search.task.d.a.REPORT_LOW_QUALITY_STATE;
                } else if (i == 1) {
                    aVar2 = com.sds.android.ttpod.framework.support.search.task.d.a.REPORT_NO_MATCH_STATE;
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REPORT_LYRIC_PICTURE, com.sds.android.ttpod.framework.support.search.task.d.b.REPORT_TYPE_LYRIC, aVar2, mediaItem));
                f.a((int) R.string.thank_you_for_join);
                eVar.dismiss();
            }
        });
        eVar.show();
    }

    private void showPictureErrorReportDialog(final MediaItem mediaItem) {
        final com.sds.android.ttpod.component.d.a.e eVar = new com.sds.android.ttpod.component.d.a.e(getActivity(), Arrays.asList(new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, 0, (int) R.string.download_error), new com.sds.android.ttpod.component.b.a(1, 0, (int) R.string.not_matched), new com.sds.android.ttpod.component.b.a(2, 0, (int) R.string.low_quality)}), null, null);
        eVar.b(R.string.cancel, null);
        eVar.setTitle((int) R.string.report_picture_error);
        eVar.a(new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ PortraitPlayerFragment c;

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                com.sds.android.ttpod.framework.support.search.task.d.a aVar2 = com.sds.android.ttpod.framework.support.search.task.d.a.REPORT_NO_CONTENT_STATE;
                if (i == 1) {
                    aVar2 = com.sds.android.ttpod.framework.support.search.task.d.a.REPORT_NO_MATCH_STATE;
                } else if (i == 2) {
                    aVar2 = com.sds.android.ttpod.framework.support.search.task.d.a.REPORT_LOW_QUALITY_STATE;
                }
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REPORT_LYRIC_PICTURE, com.sds.android.ttpod.framework.support.search.task.d.b.REPORT_TYPE_LYRIC, aVar2, mediaItem));
                f.a((int) R.string.thank_you_for_join);
                eVar.dismiss();
            }
        });
        eVar.show();
    }

    public void onToggleMenuView(boolean z) {
        if (this.mDropDownMenu == null || !this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu = ActionBarFragment.popupMenu(getActivity(), null, z, this);
            return;
        }
        this.mDropDownMenu.dismiss();
        this.mDropDownMenu = null;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        this.mDropDownMenu = null;
        switch (i) {
            case 1:
                com.sds.android.ttpod.b.f.h(getActivity());
                return;
            case 2:
                com.sds.android.ttpod.b.f.c(getActivity());
                return;
            case 3:
                com.sds.android.ttpod.b.f.b();
                return;
            default:
                return;
        }
    }

    public int getCurrentPanelIndex() {
        if (this.mViewController != null) {
            return this.mViewController.j();
        }
        return -1;
    }
}
