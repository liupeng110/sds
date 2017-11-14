package com.sds.android.ttpod.component.landscape;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.landscape.b.i;
import com.sds.android.ttpod.fragment.main.BasePlayerFragment;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView.c;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.player.PlayStatus;

public class LandscapeFragment extends BasePlayerFragment {
    private static final int ANIM_DURATION = 300;
    private static final int MENU_SHOW_DURATION = 5000;
    private static final int MSG_ID_HIDE_MENU = 1;
    private static final String TAG = "LandscapeFragment";
    private AudioManager mAudioManager;
    private View mBottomMenu;
    private OnClickListener mClickListener = new OnClickListener(this) {
        final /* synthetic */ LandscapeFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.getActivity() != null) {
                switch (view.getId()) {
                    case R.id.button_share:
                        if (!com.sds.android.ttpod.framework.storage.a.a.a().M().isNull()) {
                            if (this.a.mScreenCapture != null) {
                                this.a.mScreenCapture.a();
                                return;
                            }
                            Bitmap createBitmap = Bitmap.createBitmap(this.a.mLandscapeView.getWidth(), this.a.mLandscapeView.getHeight(), Config.RGB_565);
                            this.a.mLandscapeView.draw(new Canvas(createBitmap));
                            f.a(this.a.getActivity(), com.sds.android.ttpod.framework.storage.a.a.a().M(), createBitmap);
                            return;
                        }
                        return;
                    case R.id.imagebutton_previous_landscape:
                        ((BaseActivity) this.a.getActivity()).acquireFastClickSupport();
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PREVIOUS, new Object[0]));
                        return;
                    case R.id.imagebutton_play_landscape:
                        ((BaseActivity) this.a.getActivity()).acquireFastClickSupport();
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(e.a(this.a.getActivity()).n() == PlayStatus.STATUS_PAUSED ? com.sds.android.ttpod.framework.modules.a.RESUME : com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                        return;
                    case R.id.imagebutton_pause_landscape:
                        ((BaseActivity) this.a.getActivity()).acquireFastClickSupport();
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                        return;
                    case R.id.imagebutton_next_landscape:
                        ((BaseActivity) this.a.getActivity()).acquireFastClickSupport();
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
                        return;
                    case R.id.gesture:
                    case R.id.animtransview_landscape_old:
                    case R.id.lyricview_landscape_old:
                        this.a.toggleMenu();
                        return;
                    case R.id.switch_effect:
                        final i b = h.a().b();
                        if (b != null && (b instanceof com.sds.android.ttpod.component.landscape.b.e)) {
                            this.a.mGLSurfaceView.queueEvent(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 b;

                                public void run() {
                                    ((com.sds.android.ttpod.component.landscape.b.e) b).f_();
                                }
                            });
                            return;
                        }
                        return;
                    case R.id.select_landscape:
                        boolean z;
                        if (com.sds.android.ttpod.framework.storage.environment.b.aC()) {
                            z = false;
                        } else {
                            z = true;
                        }
                        com.sds.android.ttpod.framework.storage.environment.b.I(z);
                        this.a.switchLandscape();
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private Dialog mDialog;
    private GLSurfaceView mGLSurfaceView;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ LandscapeFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.getActivity() != null && message.what == 1) {
                this.a.hideMenu();
            }
        }
    };
    private AnimationSet mHideBottomMenuAnimal;
    private AnimationSet mHideTopMenuAnimal;
    private LandscapeAnimTransView mLandscapeAnimTransView;
    private ViewGroup mLandscapeContent;
    private ViewGroup mLandscapeView;
    private c mLyricTouchListener = new c(this) {
        final /* synthetic */ LandscapeFragment a;

        {
            this.a = r1;
        }

        public void a(long j) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_POSITION, Integer.valueOf((int) j)));
        }

        public void a(int i) {
        }
    };
    private LyricView mLyricView;
    private d mMediaHelper;
    private View mNewLandScapeView;
    private View mNextView;
    private View mOldLandScapeView;
    private View mPauseView;
    private View mPlayView;
    private View mPreviousView;
    private f mScreenCapture;
    private View mShareView;
    private AnimationSet mShowBottomMenuAnimal;
    private AnimationSet mShowTopMenuAnimal;
    private ImageView mSwitchEffectIcon;
    private ImageView mSwitchLandscapeIcon;
    private View mTopMenu;
    private SeekBar mVolumeSeekBar;

    private class a extends Dialog {
        final /* synthetic */ LandscapeFragment a;
        private boolean b = false;

        public a(LandscapeFragment landscapeFragment, Context context, int i) {
            this.a = landscapeFragment;
            super(context, i);
        }

        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            boolean z = false;
            if (keyEvent.getRepeatCount() > 1) {
                if (i == 24) {
                    this.a.increaseVolume();
                } else if (i != 25) {
                    return super.onKeyDown(i, keyEvent);
                } else {
                    this.a.decreaseVolume();
                }
                this.b = false;
                return true;
            }
            boolean z2;
            if (i == 25 || i == 24) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.b = z2;
            if (this.b || super.onKeyDown(i, keyEvent)) {
                z = true;
            }
            return z;
        }

        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            boolean z = this.b;
            this.b = false;
            switch (i) {
                case 24:
                    if (z) {
                        this.a.increaseVolume();
                        break;
                    }
                    break;
                case 25:
                    if (z) {
                        this.a.decreaseVolume();
                        break;
                    }
                    break;
                case 82:
                    this.a.toggleMenu();
                    break;
                default:
                    return super.onKeyUp(i, keyEvent);
            }
            return true;
        }
    }

    public void playMediaChanged() {
        super.playMediaChanged();
        Bitmap currentArtistBitmap = getCurrentArtistBitmap();
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a(currentArtistBitmap);
        }
        if (this.mLandscapeAnimTransView != null) {
            this.mLandscapeAnimTransView.setImageBitmap(currentArtistBitmap);
        }
        invalidLyric();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        g.a(TAG, "onCreate looklyric");
        b.a(getActivity());
    }

    public void onStatsResume() {
        l.a().a("tt_landscape_player");
    }

    public void onStatsPause() {
        l.a().b("tt_landscape_player");
    }

    private AudioManager getAudioManager() {
        if (this.mAudioManager == null) {
            this.mAudioManager = (AudioManager) getActivity().getSystemService("audio");
        }
        return this.mAudioManager;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLandscapeView = (ViewGroup) layoutInflater.inflate(R.layout.landscape_player, null);
        this.mLandscapeContent = (ViewGroup) this.mLandscapeView.findViewById(R.id.layout_landscape_content);
        this.mTopMenu = this.mLandscapeView.findViewById(R.id.volume_panel);
        SeekBar seekBar = (SeekBar) this.mTopMenu.findViewById(R.id.seekbar_volume_landscape);
        seekBar.setMax(getAudioManager().getStreamMaxVolume(3));
        seekBar.setProgress(getAudioManager().getStreamVolume(3));
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ LandscapeFragment a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.hideMenuDelay();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.mHandler.removeMessages(1);
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.a.getAudioManager().setStreamVolume(3, i, 0);
            }
        });
        LandscapeLockerIcon landscapeLockerIcon = (LandscapeLockerIcon) this.mLandscapeView.findViewById(R.id.icon_locker);
        landscapeLockerIcon.a(getResources().getDrawable(R.drawable.xml_button_lock_landscape), getResources().getDrawable(R.drawable.xml_button_unlock_landscape));
        landscapeLockerIcon.a();
        landscapeLockerIcon.setOnLockerStateChangeListener(new com.sds.android.ttpod.component.landscape.LandscapeLockerIcon.a(this) {
            final /* synthetic */ LandscapeFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().setRequestedOrientation(i == 0 ? 0 : 4);
                    this.a.hideMenuDelay();
                }
            }
        });
        this.mVolumeSeekBar = seekBar;
        this.mBottomMenu = this.mLandscapeView.findViewById(R.id.control_panel);
        this.mPreviousView = this.mBottomMenu.findViewById(R.id.imagebutton_previous_landscape);
        this.mPreviousView.setOnClickListener(this.mClickListener);
        this.mPauseView = this.mBottomMenu.findViewById(R.id.imagebutton_pause_landscape);
        this.mPauseView.setOnClickListener(this.mClickListener);
        this.mPlayView = this.mBottomMenu.findViewById(R.id.imagebutton_play_landscape);
        this.mPlayView.setOnClickListener(this.mClickListener);
        this.mNextView = this.mBottomMenu.findViewById(R.id.imagebutton_next_landscape);
        this.mNextView.setOnClickListener(this.mClickListener);
        this.mSwitchLandscapeIcon = (ImageView) this.mLandscapeView.findViewById(R.id.select_landscape);
        this.mSwitchLandscapeIcon.setOnClickListener(this.mClickListener);
        this.mSwitchEffectIcon = (ImageView) this.mLandscapeView.findViewById(R.id.switch_effect);
        this.mSwitchEffectIcon.setOnClickListener(this.mClickListener);
        this.mShareView = this.mLandscapeView.findViewById(R.id.button_share);
        this.mShareView.setOnClickListener(this.mClickListener);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        this.mShowTopMenuAnimal = new AnimationSet(false);
        this.mShowTopMenuAnimal.addAnimation(alphaAnimation);
        this.mShowBottomMenuAnimal = new AnimationSet(false);
        this.mShowBottomMenuAnimal.addAnimation(alphaAnimation);
        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        this.mHideTopMenuAnimal = new AnimationSet(false);
        this.mHideTopMenuAnimal.addAnimation(alphaAnimation);
        this.mHideBottomMenuAnimal = new AnimationSet(false);
        this.mHideBottomMenuAnimal.addAnimation(alphaAnimation);
        this.mDialog = new a(this, getActivity(), R.style.Dialog_FullScreen);
        this.mDialog.requestWindowFeature(1);
        this.mLandscapeView.setKeepScreenOn(com.sds.android.ttpod.framework.storage.environment.b.C());
        return this.mLandscapeView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            this.mDialog.setContentView(view);
        }
        this.mDialog.setOwnerActivity(getActivity());
        this.mDialog.setCancelable(false);
    }

    public void onStart() {
        super.onStart();
        this.mDialog.show();
    }

    public void show(FragmentManager fragmentManager) {
        fragmentManager.beginTransaction().add((Fragment) this, "").commitAllowingStateLoss();
    }

    public void dismiss() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
        getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.mDialog.dismiss();
        } else {
            this.mDialog.show();
        }
    }

    public void onResume() {
        super.onResume();
        switchLandscape();
        startUpdatePlayPosition();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeMessages(1);
        stopUpdatePlayPosition();
    }

    protected void updatePlayPosition() {
        if (this.mLyricView != null) {
            this.mLyricView.setPlayingTime((long) e.a(getActivity()).l().intValue());
        }
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a((long) e.a(getActivity()).l().intValue());
        }
    }

    protected void setArtistBitmap(Bitmap bitmap, String str) {
        super.setArtistBitmap(bitmap, str);
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a(bitmap);
        }
        if (this.mLandscapeAnimTransView != null) {
            this.mLandscapeAnimTransView.setImageBitmap(bitmap);
        }
    }

    protected void artistBitmapSearchStarted() {
        super.artistBitmapSearchStarted();
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a(null);
        }
        if (this.mLandscapeAnimTransView != null) {
            this.mLandscapeAnimTransView.setImageBitmap(null);
        }
    }

    protected void lyricLoadFinished() {
        super.lyricLoadFinished();
        g.a(TAG, "lyricLoadFinished looklyric");
        if (this.mLyricView != null) {
            this.mLyricView.setLyric(getCurrentLyric());
        }
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a(getCurrentLyric());
        }
    }

    protected void lyricSearchStarted() {
        super.lyricSearchStarted();
        g.a(TAG, "lyricSearchStarted looklyric");
        if (this.mLyricView != null) {
            this.mLyricView.setState(2);
        }
    }

    protected void lyricDownloadStarted() {
        super.lyricDownloadStarted();
        g.a(TAG, "lyricDownloadStarted looklyric");
        if (this.mLyricView != null) {
            this.mLyricView.setState(4);
        }
    }

    protected void lyricDownloadError() {
        super.lyricDownloadError();
        g.a(TAG, "lyricDownloadError looklyric");
        invalidLyric();
    }

    protected void lyricSearchStop() {
        super.lyricSearchStop();
        g.a(TAG, "lyricSearchStop looklyric");
        invalidLyric();
    }

    public void updatePlayMediaInfo() {
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a(com.sds.android.ttpod.framework.storage.a.a.a().M(), 0);
        }
    }

    protected void lyricSearchFailed() {
        super.lyricSearchFailed();
        g.a(TAG, "lyricSearchFailed looklyric");
        invalidLyric();
    }

    private void invalidLyric() {
        if (this.mLyricView != null) {
            this.mLyricView.setState(1);
        }
        if (this.mMediaHelper != null) {
            this.mMediaHelper.a(null);
        }
    }

    protected void lyricNetError() {
        super.lyricNetError();
        g.a(TAG, "lyricNetError looklyric");
        invalidLyric();
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        int i = 0;
        super.updatePlayStatus(playStatus);
        if (isViewAccessAble()) {
            int i2;
            int i3;
            if (PlayStatus.STATUS_PLAYING == e.a(getActivity()).n()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            View findViewById = this.mBottomMenu.findViewById(R.id.imagebutton_play_landscape);
            if (i2 != 0) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            findViewById.setVisibility(i3);
            View findViewById2 = this.mBottomMenu.findViewById(R.id.imagebutton_pause_landscape);
            if (i2 == 0) {
                i = 8;
            }
            findViewById2.setVisibility(i);
        }
    }

    private void toggleMenu() {
        if (this.mTopMenu.getVisibility() == 8) {
            showMenu();
        } else {
            hideMenu();
        }
    }

    private void showMenu() {
        if (this.mTopMenu.getVisibility() != 0) {
            this.mTopMenu.setVisibility(0);
            this.mBottomMenu.setVisibility(0);
            this.mTopMenu.clearAnimation();
            this.mTopMenu.startAnimation(this.mShowTopMenuAnimal);
            this.mBottomMenu.clearAnimation();
            this.mBottomMenu.startAnimation(this.mShowBottomMenuAnimal);
        }
        hideMenuDelay();
    }

    private void hideMenu() {
        this.mTopMenu.setVisibility(8);
        this.mBottomMenu.setVisibility(8);
        this.mTopMenu.clearAnimation();
        this.mTopMenu.startAnimation(this.mHideTopMenuAnimal);
        this.mBottomMenu.clearAnimation();
        this.mBottomMenu.startAnimation(this.mHideBottomMenuAnimal);
        this.mHandler.removeMessages(1);
    }

    private void hideMenuDelay() {
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, 5000);
    }

    private void switchLandscape() {
        if (!com.sds.android.ttpod.framework.storage.environment.b.aC()) {
            loadOldLandscape();
        } else if (getActivity() != null) {
            loadNewLandscape();
        }
    }

    private void removeNewLandscape() {
        this.mSwitchEffectIcon.setVisibility(8);
        if (this.mNewLandScapeView != null) {
            this.mGLSurfaceView.onPause();
            this.mGLSurfaceView = null;
            this.mNewLandScapeView = null;
            this.mLandscapeContent.removeAllViews();
            this.mMediaHelper = null;
            this.mScreenCapture = null;
            com.sds.android.ttpod.component.landscape.temporary.a.a().c();
            h.a().f();
        }
    }

    private void loadNewLandscape() {
        if (this.mNewLandScapeView == null) {
            g.a(TAG, "loadNewLandscape looklyric");
            removeOldLandscape();
            this.mSwitchEffectIcon.setVisibility(0);
            this.mSwitchLandscapeIcon.setImageResource(R.drawable.xml_landscape_switch_old);
            this.mSwitchLandscapeIcon.setVisibility(0);
            this.mNewLandScapeView = View.inflate(getActivity(), R.layout.landscape_new_layout, this.mLandscapeContent);
            this.mGLSurfaceView = (GLSurfaceView) this.mNewLandScapeView.findViewById(R.id.main_scene);
            this.mGLSurfaceView.setRenderer(new e(getActivity()));
            GestureView.c cVar = new c();
            GestureView gestureView = (GestureView) this.mNewLandScapeView.findViewById(R.id.gesture);
            gestureView.setTTPodClickListener(this.mClickListener);
            gestureView.setTTPodLongClickListener(cVar);
            gestureView.setGestureTranslation(cVar);
            gestureView.setGestureRotate(cVar);
            gestureView.setGestureScale(cVar);
            gestureView.setGestureState(cVar);
            this.mMediaHelper = new d(getActivity());
            this.mMediaHelper.a((long) e.a(getActivity()).l().intValue());
            this.mMediaHelper.a(com.sds.android.ttpod.framework.storage.a.a.a().M(), 0);
            updatePlayStatus(e.a(getActivity()).n());
            this.mMediaHelper.a(getCurrentArtistBitmap());
            this.mMediaHelper.a(getCurrentLyric());
            this.mScreenCapture = new f(new com.sds.android.ttpod.component.landscape.f.a(this) {
                final /* synthetic */ LandscapeFragment a;

                {
                    this.a = r1;
                }

                public void a(Bitmap bitmap) {
                    f.a(this.a.getActivity(), com.sds.android.ttpod.framework.storage.a.a.a().M(), bitmap);
                }
            });
            h.a().e();
            this.mGLSurfaceView.onResume();
        }
    }

    private void removeOldLandscape() {
        if (this.mOldLandScapeView != null) {
            this.mLyricView = null;
            this.mLandscapeAnimTransView = null;
            this.mOldLandScapeView = null;
            this.mLandscapeContent.removeAllViews();
        }
    }

    private void loadOldLandscape() {
        if (this.mOldLandScapeView == null) {
            g.a(TAG, "loadOldLandscape looklyric");
            removeNewLandscape();
            this.mSwitchLandscapeIcon.setImageResource(R.drawable.xml_landscape_switch_new);
            this.mSwitchLandscapeIcon.setVisibility(0);
            this.mOldLandScapeView = View.inflate(getActivity(), R.layout.landscape_old_layout, this.mLandscapeContent);
            this.mOldLandScapeView.setOnClickListener(this.mClickListener);
            updatePlayStatus(e.a(getActivity()).n());
            this.mLyricView = (LyricView) this.mOldLandScapeView.findViewById(R.id.lyricview_landscape_old);
            this.mLyricView.setTouchListener(this.mLyricTouchListener);
            this.mLyricView.setKalaOK(com.sds.android.ttpod.framework.storage.environment.b.U());
            this.mLyricView.setColorHighlight(com.sds.android.ttpod.framework.storage.environment.b.S());
            this.mLyricView.setLyric(getCurrentLyric());
            this.mLyricView.setPlayingTime((long) e.a(getActivity()).l().intValue());
            this.mLyricView.setOnClickListener(this.mClickListener);
            this.mLandscapeAnimTransView = (LandscapeAnimTransView) this.mOldLandScapeView.findViewById(R.id.animtransview_landscape_old);
            this.mLandscapeAnimTransView.setOnClickListener(this.mClickListener);
            this.mLandscapeAnimTransView.setImageBitmap(getCurrentArtistBitmap());
        }
    }

    private void increaseVolume() {
        if (this.mVolumeSeekBar != null) {
            showMenu();
            this.mVolumeSeekBar.setProgress(this.mVolumeSeekBar.getProgress() + 1);
        }
    }

    private void decreaseVolume() {
        if (this.mVolumeSeekBar != null) {
            showMenu();
            this.mVolumeSeekBar.setProgress(this.mVolumeSeekBar.getProgress() - 1);
        }
    }

    public void initLyricArtistBitmap(com.sds.android.ttpod.framework.modules.skin.e.g gVar, Bitmap bitmap, String str) {
        setLyric(gVar);
        setArtistBitmap(bitmap, str);
    }
}
