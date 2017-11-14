package com.sds.android.ttpod.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.widget.DefaultVideoView;
import com.sds.android.ttpod.widget.MediaController;
import com.sds.android.ttpod.widget.MediaController.a;
import com.sds.android.ttpod.widget.d;

public class DefaultVideoActivity extends BaseActivity implements a {
    private static final String LOG_TAG = "DefaultVideoActivity";
    private ImageView mBufferingAnimation;
    private int mBufferingPercent = 0;
    private TextView mBufferingPercentage;
    private OnBufferingUpdateListener mBufferingUpdateListener = new OnBufferingUpdateListener(this) {
        final /* synthetic */ DefaultVideoActivity a;

        {
            this.a = r1;
        }

        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            this.a.mBufferingPercent = i;
            this.a.mBufferingPercentage.setText(String.valueOf(i) + "%");
        }
    };
    private int mCurrentPosition = 0;
    private h mInstallStormDialog = null;
    private MediaController mMediaController;
    private OnClickListener mOnBackEventListener = new OnClickListener(this) {
        final /* synthetic */ DefaultVideoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            g.c(DefaultVideoActivity.LOG_TAG, "OnBackEventListener listener");
            this.a.hideBufferingAnimation();
            this.a.finish();
        }
    };
    private OnCompletionListener mOnCompletionListener = new OnCompletionListener(this) {
        final /* synthetic */ DefaultVideoActivity a;

        {
            this.a = r1;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.a.finish();
        }
    };
    private OnErrorListener mOnErrorListener = new OnErrorListener(this) {
        final /* synthetic */ DefaultVideoActivity a;

        {
            this.a = r1;
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            g.d(DefaultVideoActivity.LOG_TAG, "position: " + mediaPlayer.getCurrentPosition());
            g.d(DefaultVideoActivity.LOG_TAG, "what:" + i + " :extra:" + i2);
            int i3 = c.e() ? i2 : -1004;
            if (0 == i) {
                i3 = -1010;
            }
            switch (i3) {
                case -1010:
                case -1007:
                    this.a.mInstallStormDialog = VideoPlayManager.a(this.a, R.string.system_codec_not_support, null, null, false, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.finish();
                        }
                    });
                    i3 = R.string.play_error;
                    break;
                case -1004:
                case -110:
                    i3 = R.string.version_update_network_bad;
                    break;
                default:
                    i3 = R.string.play_error;
                    break;
            }
            if (this.a.mInstallStormDialog == null || this.a.mInstallStormDialog.isShowing()) {
                this.a.finish();
                f.a(i3);
                this.a.mInstallStormDialog = null;
            }
            return false;
        }
    };
    private OnInfoListener mOnInfoListener = new OnInfoListener(this) {
        final /* synthetic */ DefaultVideoActivity a;

        {
            this.a = r1;
        }

        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
            g.d(DefaultVideoActivity.LOG_TAG, "info:" + i + ":" + i2);
            return true;
        }
    };
    private OnPreparedListener mOnPreparedListener = new OnPreparedListener(this) {
        final /* synthetic */ DefaultVideoActivity a;

        {
            this.a = r1;
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            g.d(DefaultVideoActivity.LOG_TAG, "onPrepared");
        }
    };
    private long mPositionWhenPaused = 0;
    private String mVideoTitle;
    private String mVideoUrl;
    private DefaultVideoView mVideoView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(0);
        setContentView((int) R.layout.activity_default_video_layout);
        this.mVideoView = (DefaultVideoView) findViewById(R.id.video_view);
        this.mBufferingAnimation = (ImageView) findViewById(R.id.mv_load_animation);
        this.mBufferingPercentage = (TextView) findViewById(R.id.mv_percentage);
        this.mVideoView.setMediaTitleBanner(new d(this));
        Intent intent = getIntent();
        this.mVideoUrl = intent.getStringExtra("param_video_url");
        this.mVideoTitle = intent.getStringExtra("param_video_title");
        g.d(LOG_TAG, "url:" + this.mVideoUrl);
        this.mVideoView.setVideoURI(Uri.parse(this.mVideoUrl));
        this.mVideoView.setVideoTitle(this.mVideoTitle);
        this.mVideoView.b();
        this.mVideoView.setLapseChangedListener(this);
        this.mMediaController = new MediaController(this);
        this.mVideoView.setMediaController(this.mMediaController);
        this.mVideoView.setOnBackEventListener(this.mOnBackEventListener);
        this.mVideoView.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
        this.mVideoView.setOnErrorListener(this.mOnErrorListener);
        this.mVideoView.setOnCompletionListener(this.mOnCompletionListener);
        this.mVideoView.setOnPreparedListener(this.mOnPreparedListener);
        this.mVideoView.setOnInfoListener(this.mOnInfoListener);
        showBufferingAniamtion();
    }

    private void showBufferingAniamtion() {
        if (this.mBufferingAnimation.getVisibility() != 0) {
            this.mBufferingAnimation.setVisibility(0);
            this.mBufferingPercentage.setVisibility(0);
            this.mBufferingAnimation.startAnimation(AnimationUtils.loadAnimation(this, R.anim.unlimited_rotate));
        }
    }

    private void hideBufferingAnimation() {
        this.mBufferingAnimation.clearAnimation();
        this.mBufferingAnimation.setVisibility(8);
        this.mBufferingPercentage.setVisibility(8);
    }

    protected void onResume() {
        super.onResume();
        g.d(LOG_TAG, "onResume");
        getWindow().getDecorView().setKeepScreenOn(true);
        this.mBufferingAnimation.setVisibility(0);
        this.mBufferingAnimation.startAnimation(AnimationUtils.loadAnimation(this, R.anim.unlimited_rotate));
        this.mBufferingPercentage.setVisibility(0);
        this.mVideoView.a(this.mPositionWhenPaused);
        if (j.e()) {
            this.mVideoView.d();
        }
    }

    protected void onPause() {
        super.onPause();
        g.d(LOG_TAG, "onPause");
        getWindow().getDecorView().setKeepScreenOn(false);
        this.mPositionWhenPaused = this.mVideoView.getCurrentPosition();
        this.mVideoView.c();
    }

    protected void onStop() {
        super.onStop();
        g.d(LOG_TAG, "onStop");
        this.mVideoView.a();
        this.mVideoView.g();
    }

    protected void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent("com.sds.android.ttpod.video_finished"));
        g.d(LOG_TAG, "onDestroy");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mVideoView.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private boolean showBufferingAnimationEnable(MediaPlayer mediaPlayer, int i) {
        return this.mCurrentPosition == 0 || (mediaPlayer.isPlaying() && mediaPlayer.getCurrentPosition() == this.mCurrentPosition && mediaPlayer.getCurrentPosition() != mediaPlayer.getDuration() && i != 100);
    }

    public void onLapseChanged(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            if (showBufferingAnimationEnable(mediaPlayer, this.mBufferingPercent)) {
                showBufferingAniamtion();
            } else {
                hideBufferingAnimation();
            }
            if (mediaPlayer.isPlaying()) {
                this.mCurrentPosition = mediaPlayer.getCurrentPosition();
            }
        }
    }
}
