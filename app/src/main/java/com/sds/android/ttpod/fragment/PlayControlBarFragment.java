package com.sds.android.ttpod.fragment;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.SeekBar;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.BasePlayerFragment;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.widget.ArtistFrameView;
import java.lang.reflect.Method;
import java.util.Map;

public class PlayControlBarFragment extends BasePlayerFragment {
    private static final String TAG = "PlayControlBarFragment";
    private ArtistFrameView mArtistFrameView;
    private TextView mArtistName;
    private OnClickListener mClickListener = new OnClickListener(this) {
        final /* synthetic */ PlayControlBarFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            ((BaseActivity) this.a.getActivity()).acquireFastClickSupport();
            MediaItem M = a.a().M();
            long longValue = M.getSongID() == null ? 0 : M.getSongID().longValue();
            switch (view.getId()) {
                case R.id.itv_playcontrolbar_menu:
                    FragmentActivity activity = this.a.getActivity();
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).toggleMenu();
                    }
                    b.a("global_menu");
                    return;
                case R.id.itv_playcontrolbar_next:
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
                    l.C();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PLAYBAR_NEXT.getValue(), s.PAGE_PLAY_BAR.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(longValue)).post();
                    b.a("sidebar_next");
                    return;
                case R.id.itv_playcontrolbar_play:
                    if (e.a(this.a.getActivity()).n() == PlayStatus.STATUS_PAUSED) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                    } else if (e.a(this.a.getActivity()).n() == PlayStatus.STATUS_STOPPED) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                    }
                    l.A();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PLAYBAR_PLAY.getValue(), s.PAGE_PLAY_BAR.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(longValue)).post();
                    b.a("sidebar_play");
                    return;
                case R.id.itv_playcontrolbar_pause:
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                    l.B();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PLAYBAR_PAUSE.getValue(), s.PAGE_PLAY_BAR.getValue(), s.PAGE_NONE.getValue()).append(MediasColumns.SONG_ID, Long.valueOf(longValue)).post();
                    b.a("sidebar_pause");
                    return;
                default:
                    return;
            }
        }
    };
    private int mDuration;
    private IconTextView mItvMenu;
    private IconTextView mItvNext;
    private IconTextView mItvPause;
    private IconTextView mItvPlay;
    private View mPlayPanel;
    private TextView mPlayPosition;
    private View mRootView;
    private SeekBar mSeekBar;
    private AnimTransView mSongImage;
    private TextView mSongName;
    private Drawable mThemeArtsDrawable = null;
    private Integer mTrySeekPosition;
    private int mUpdatePlayPositionCount;

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_POSITION, i.a(cls, "updatePlayPosition", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PICTURE_DELETED, i.a(cls, "pictureDeleted", MediaItem.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_playcontrolbar, viewGroup, false);
        this.mItvPlay = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_play);
        this.mItvPause = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_pause);
        this.mItvNext = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_next);
        this.mItvMenu = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_menu);
        this.mArtistName = (TextView) this.mRootView.findViewById(R.id.textview_playcontrolbar_artist);
        this.mSongName = (TextView) this.mRootView.findViewById(R.id.textview_playcontrolbar_title);
        this.mPlayPosition = (TextView) this.mRootView.findViewById(R.id.textview_playcontrolbar_playposition);
        this.mArtistFrameView = (ArtistFrameView) this.mRootView.findViewById(R.id.view_playcontrol_bar_artist);
        this.mSongImage = this.mArtistFrameView.getAnimTransView();
        this.mSeekBar = (SeekBar) this.mRootView.findViewById(R.id.seekbar_playcontrolbar_progress);
        this.mPlayPanel = this.mRootView.findViewById(R.id.view_playcontrolbar_attach_playerpanel);
        this.mItvPlay.setOnClickListener(this.mClickListener);
        this.mItvPause.setOnClickListener(this.mClickListener);
        this.mItvMenu.setOnClickListener(this.mClickListener);
        this.mItvNext.setOnClickListener(this.mClickListener);
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updatePlayMediaInfo();
        updatePlayStatus(e.a(getActivity()).n());
        updatePlayPosition(e.a(getActivity()).l());
        if (!m.a(a.a().g())) {
            artistBitmapLoadFinished();
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        g.a(TAG, "PlayControlBarFragment onThemeLoaded");
        v.a(this.mItvPlay, ThemeElement.PLAY_BAR_PLAY_IMAGE, (int) R.string.icon_playbar_play, ThemeElement.PLAY_BAR_TEXT);
        v.a(this.mItvPause, ThemeElement.PLAY_BAR_PAUSE_IMAGE, (int) R.string.icon_playbar_pause, ThemeElement.PLAY_BAR_TEXT);
        v.a(this.mItvNext, ThemeElement.PLAY_BAR_NEXT_IMAGE, (int) R.string.icon_playbar_next, ThemeElement.PLAY_BAR_TEXT);
        v.a(this.mItvMenu, ThemeElement.PLAY_BAR_SIDEBAR_IMAGE, (int) R.string.icon_menu_with_four_point, ThemeElement.PLAY_BAR_TEXT);
        setBackgroundStyle(this.mRootView);
        c.a(this.mSongImage, ThemeElement.PLAY_BAR_DEF_ARTIST_IMAGE);
        c.a(this.mSongName, ThemeElement.PLAY_BAR_TEXT);
        c.a(this.mArtistName, ThemeElement.PLAY_BAR_SUB_TEXT);
        v.a(this.mSeekBar);
        this.mThemeArtsDrawable = null;
        Bitmap currentArtistBitmap = getCurrentArtistBitmap();
        if (currentArtistBitmap == null) {
            loadArtsThemeImage();
        } else {
            this.mSongImage.setImageBitmap(currentArtistBitmap);
        }
        this.mArtistFrameView.a();
    }

    private void setBackgroundStyle(View view) {
        int f;
        View view2 = (View) view.getParent();
        c.a(view2, ThemeElement.PLAY_BAR_BACKGROUND);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height);
        com.sds.android.ttpod.framework.modules.theme.b.e b = c.b(ThemeElement.PLAY_BAR_BACKGROUND);
        if (b != null) {
            f = b.f();
            if (f <= dimensionPixelSize) {
                f = dimensionPixelSize;
            }
        } else {
            f = dimensionPixelSize;
        }
        setViewLayoutHeight(view2, setViewLayoutHeight(view, f));
    }

    private int setViewLayoutHeight(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (view.getPaddingTop() + i) + view.getPaddingBottom();
        view.setLayoutParams(layoutParams);
        return layoutParams.height;
    }

    public void updatePlayMediaInfo() {
        if (isViewAccessAble()) {
            MediaItem M = a.a().M();
            boolean isNull = M.isNull();
            this.mSongName.setText(isNull ? getString(R.string.lyric_ttpod) : M.getTitle());
            this.mArtistName.setText(isNull ? "" : TTTextUtils.validateString(this.mArtistName.getContext(), M.getArtist()));
            this.mDuration = isNull ? 0 : M.getDuration().intValue();
            this.mSeekBar.setMax(this.mDuration);
            updatePlayPosition(Integer.valueOf(0));
            if (isNull) {
                setArtistBitmap(null, null);
            }
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (!isViewAccessAble()) {
            return;
        }
        if (playStatus == PlayStatus.STATUS_PLAYING) {
            this.mItvPlay.setVisibility(4);
            this.mItvPause.setVisibility(0);
            return;
        }
        this.mItvPlay.setVisibility(0);
        this.mItvPause.setVisibility(4);
    }

    public void playMediaChanged() {
        super.playMediaChanged();
        if (isViewAccessAble()) {
            g.a(TAG, "PlayControlBarFragment playMediaChanged");
            if (getCurrentArtistBitmap() == null) {
                loadArtsThemeImage();
            }
        }
    }

    public void onResume() {
        super.onResume();
        startUpdatePlayPosition();
    }

    public void onPause() {
        super.onPause();
        stopUpdatePlayPosition();
    }

    protected void updatePlayPosition() {
        int i = this.mUpdatePlayPositionCount;
        this.mUpdatePlayPositionCount = i + 1;
        if (i >= 20) {
            this.mUpdatePlayPositionCount = 0;
            super.updatePlayPosition();
            updatePlayPosition(e.a(getActivity()).l());
        }
    }

    public void updatePlayPosition(Integer num) {
        int i = 0;
        if (isViewAccessAble()) {
            if (this.mTrySeekPosition != null) {
                num = this.mTrySeekPosition;
            }
            if (a.a().M().isNull()) {
                this.mPlayPosition.setText("");
                this.mSeekBar.setProgress(0);
                this.mSeekBar.setSecondaryProgress(0);
                return;
            }
            if (this.mPlayPosition.getVisibility() == 0) {
                this.mPlayPosition.setText(com.sds.android.sdk.lib.util.c.a((long) num.intValue()) + "-" + com.sds.android.sdk.lib.util.c.a((long) this.mDuration));
            }
            this.mSeekBar.setProgress(num.intValue());
            if (a.a().M().isOnline()) {
                i = (int) (e.a(getActivity()).m() * ((float) this.mSeekBar.getMax()));
            }
            this.mSeekBar.setSecondaryProgress(i);
        }
    }

    public Rect getPlayerPanelAttachRawRect() {
        int[] iArr = new int[2];
        this.mPlayPanel.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + this.mPlayPanel.getMeasuredWidth(), iArr[1] + this.mPlayPanel.getMeasuredHeight());
    }

    protected void artistBitmapDownloadStarted() {
        super.artistBitmapDownloadStarted();
    }

    protected void artistBitmapSearchStarted() {
        super.artistBitmapSearchStarted();
    }

    protected void setArtistBitmap(Bitmap bitmap, String str) {
        if (!m.a(getCurrentArtistBitmapPath(), str)) {
            if (bitmap != null) {
                bitmap = cropRoundedBitmap(bitmap);
            }
            super.setArtistBitmap(bitmap, str);
            if (!isViewAccessAble()) {
                return;
            }
            if (bitmap == null) {
                loadArtsThemeImage();
            } else {
                this.mSongImage.setImageBitmap(bitmap);
            }
        }
    }

    private Bitmap cropRoundedBitmap(Bitmap bitmap) {
        int cornerRadius = this.mArtistFrameView.getCornerRadius();
        if (cornerRadius == 0) {
            return bitmap;
        }
        View animTransView = this.mArtistFrameView.getAnimTransView();
        int dimension = ((int) getResources().getDimension(R.dimen.playcontrol_bar_background_height)) - (animTransView.getPaddingRight() + animTransView.getPaddingLeft());
        return com.sds.android.sdk.lib.util.b.a(com.sds.android.sdk.lib.util.b.a(com.sds.android.sdk.lib.util.b.a(bitmap, dimension, dimension, false), true), cornerRadius, true);
    }

    private void loadArtsThemeImage() {
        if (this.mThemeArtsDrawable == null) {
            this.mThemeArtsDrawable = c.a(ThemeElement.PLAY_BAR_DEF_ARTIST_IMAGE);
        }
        if (this.mThemeArtsDrawable == null) {
            this.mThemeArtsDrawable = getResources().getDrawable(R.drawable.img_artist_default);
        }
        int dimension = (int) getResources().getDimension(R.dimen.playcontrol_bar_height);
        this.mSongImage.setImageBitmap(cropRoundedBitmap(com.sds.android.sdk.lib.util.b.a(this.mThemeArtsDrawable, dimension, dimension)));
    }

    public void pictureDeleted(MediaItem mediaItem) {
        if (isViewAccessAble() && mediaItem.equals(a.a().M())) {
            setArtistBitmap(null, null);
        }
    }
}
