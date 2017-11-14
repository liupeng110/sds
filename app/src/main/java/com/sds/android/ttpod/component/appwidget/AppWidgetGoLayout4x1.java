package com.sds.android.ttpod.component.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.c;
import com.sds.android.ttpod.framework.support.d;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.tencent.open.SocialConstants;

public class AppWidgetGoLayout4x1 extends GoWidgetFrame implements OnLongClickListener {
    private static final int ARTIST_BITMAP_SIZE_IN_DP = 200;
    private static final int MILLS_PER_SECOND = 1000;
    private static final float ROUND_DIFFERENCE = 0.5f;
    private static final String TAG = "AppWidgetGoLayout4x1";
    private boolean mClickedPlay;
    private Handler mHandler;
    private ImageButton mImageButtonMiniLyric;
    private ImageButton mImageButtonPlayNext;
    private ImageButton mImageButtonPlayPause;
    private ImageButton mImageButtonPlayPrev;
    private ImageView mImageViewAlbumCover;
    private com.sds.android.ttpod.framework.storage.environment.b.a mOnPreferencesChangeListener;
    private ProgressBar mProgressBar;
    private a mRefreshWidgetMonitor;
    private Runnable mRunnable;
    private int mSongDuration;
    private c mSupport;
    private d mSupportCallback;
    private TextView mTextViewTitle;
    private boolean mUpdatedHandlerRun;

    private class a extends BroadcastReceiver {
        final /* synthetic */ AppWidgetGoLayout4x1 a;

        private a(AppWidgetGoLayout4x1 appWidgetGoLayout4x1) {
            this.a = appWidgetGoLayout4x1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                if (Action.LAUNCHER.equals(action)) {
                    if (!(this.a.mSupport == null || this.a.mSupport.y())) {
                        this.a.mSupport.b(this.a.mSupportCallback);
                        this.a.mSupport.a(this.a.mSupportCallback);
                    }
                    this.a.registerPreferenceListener();
                } else if (Action.EXIT.equals(action)) {
                    this.a.setPlayStateBackground(PlayStatus.STATUS_PAUSED);
                    this.a.unRegisterPreferenceListener();
                    if (this.a.mSupport != null && this.a.mSupport.y()) {
                        this.a.mSupport.b(this.a.mSupportCallback);
                        this.a.mSupport.d();
                    }
                } else if (Action.PLAYLIST_IS_EMPTY.equals(action) && this.a.mClickedPlay) {
                    this.a.mClickedPlay = false;
                    this.a.startActivity(Action.NOTIFICATION_START_MEDIA_SCAN);
                }
            }
        }
    }

    public AppWidgetGoLayout4x1(Context context) {
        this(context, null);
    }

    public AppWidgetGoLayout4x1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSupport = null;
        this.mOnPreferencesChangeListener = new com.sds.android.ttpod.framework.storage.environment.b.a(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.framework.storage.environment.c cVar) {
                if (com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_DESKTOP_LYRIC_ENABLED == cVar) {
                    if (this.a.mSupport != null) {
                        this.a.setMiniLyricButton(b.s());
                    }
                } else if (com.sds.android.ttpod.framework.storage.environment.c.CURRENT_ARTIST_BITMAP_PATH == cVar && this.a.mSupport != null) {
                    this.a.onAlbumCoverChanged(b.a(this.a.mSupport.w()));
                }
            }
        };
        this.mSupportCallback = new d(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void a() {
                super.a();
                MediaItem w = this.a.mSupport.w();
                if (!(w == null || w.isNull())) {
                    this.a.onMetaChanged(w.getArtist(), w.getTitle());
                    this.a.onAlbumCoverChanged(b.a(this.a.mSupport.w()));
                }
                this.a.setPlayStateBackground(this.a.mSupport.n());
            }

            public void a(MediaItem mediaItem) {
                super.a(mediaItem);
                g.a(AppWidgetGoLayout4x1.TAG, "onPlayMediaChanged");
                if (mediaItem != null && !mediaItem.isNull()) {
                    this.a.onMetaChanged(mediaItem.getArtist(), mediaItem.getTitle());
                    this.a.onAlbumCoverChanged(b.a(this.a.mSupport.w()));
                }
            }

            public void a(PlayStatus playStatus) {
                super.a(playStatus);
                if (!this.a.mUpdatedHandlerRun) {
                    this.a.mHandler.postDelayed(this.a.mRunnable, 1000);
                }
                this.a.setPlayStateBackground(this.a.mSupport.n());
            }
        };
        this.mHandler = new Handler();
        this.mRunnable = new Runnable(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.mSupport == null || !this.a.mSupport.y()) {
                    this.a.mUpdatedHandlerRun = false;
                    return;
                }
                try {
                    if (this.a.mSongDuration == 0) {
                        MediaItem w = this.a.mSupport.w();
                        if (!(w == null || w.isNull())) {
                            this.a.mSongDuration = w.getDuration().intValue();
                        }
                    }
                    this.a.updateTime(this.a.mSupport.l().intValue(), this.a.mSongDuration, this.a.mSupport.m());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.a.mUpdatedHandlerRun = true;
                this.a.mHandler.postDelayed(this, 1000);
            }
        };
    }

    public void onStart(Bundle bundle) {
        g.a(TAG, "onStart");
        this.mImageViewAlbumCover = (ImageView) findViewById(R.id.image_album_cover);
        this.mTextViewTitle = (TextView) findViewById(R.id.text_title);
        this.mProgressBar = (ProgressBar) findViewById(R.id.seekbar_progress);
        this.mImageButtonPlayPrev = (ImageButton) findViewById(R.id.button_play_prev);
        this.mImageButtonPlayPause = (ImageButton) findViewById(R.id.button_play_pause);
        this.mImageButtonPlayNext = (ImageButton) findViewById(R.id.button_play_next);
        this.mImageButtonMiniLyric = (ImageButton) findViewById(R.id.button_minilyric);
        this.mImageButtonPlayPause.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.mClickedPlay = true;
                this.a.startService("play_pause_command");
            }
        });
        this.mImageButtonPlayPrev.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("previous_command");
            }
        });
        this.mImageButtonPlayNext.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("next_command");
            }
        });
        this.mImageButtonMiniLyric.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("switch_desktop_lyric_hide_show_command");
            }
        });
        this.mImageViewAlbumCover.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidgetGoLayout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(Action.START_ENTRY);
            }
        });
        this.mImageButtonPlayPrev.setOnLongClickListener(this);
        this.mImageButtonPlayPause.setOnLongClickListener(this);
        this.mImageButtonPlayNext.setOnLongClickListener(this);
        this.mImageButtonMiniLyric.setOnLongClickListener(this);
        this.mImageViewAlbumCover.setOnLongClickListener(this);
        initSupport();
        loadMonitor();
        initAppWidget(this.mSupport);
        registerPreferenceListener();
        this.mHandler.postDelayed(this.mRunnable, 1000);
    }

    private void loadMonitor() {
        this.mRefreshWidgetMonitor = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.EXIT);
        intentFilter.addAction(Action.LAUNCHER);
        intentFilter.addAction(Action.PLAYLIST_IS_EMPTY);
        getContext().registerReceiver(this.mRefreshWidgetMonitor, intentFilter);
    }

    private void unLoadMonitor() {
        if (this.mRefreshWidgetMonitor != null) {
            getContext().unregisterReceiver(this.mRefreshWidgetMonitor);
            this.mRefreshWidgetMonitor = null;
        }
    }

    public void onPause(int i) {
        g.a(TAG, "onPause");
    }

    public void onResume(int i) {
        g.a(TAG, "onResume");
    }

    public void onDelete(int i) {
        g.a(TAG, "onDelete");
        destroy();
    }

    public void onRemove(int i) {
        g.a(TAG, "onRemove");
        destroy();
    }

    public boolean onApplyTheme(Bundle bundle) {
        g.a(TAG, "onApplyTheme");
        return false;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        g.a(TAG, "onFinishInflate");
    }

    public boolean onLongClick(View view) {
        return performLongClick();
    }

    private void registerPreferenceListener() {
        b.a(getContext(), com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        b.a(getContext(), com.sds.android.ttpod.framework.storage.environment.c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    private void unRegisterPreferenceListener() {
        b.b(getContext(), com.sds.android.ttpod.framework.storage.environment.c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        b.b(getContext(), com.sds.android.ttpod.framework.storage.environment.c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    private void onMetaChanged(String str, String str2) {
        if (TTTextUtils.isValidateMediaString(str)) {
            str2 = TTTextUtils.validateString(BaseApplication.e(), str) + " - " + TTTextUtils.validateString(BaseApplication.e(), str2);
        }
        this.mTextViewTitle.setText(str2);
        if (this.mSupport != null) {
            MediaItem w = this.mSupport.w();
            if (w != null && !w.isNull()) {
                this.mSongDuration = w.getDuration().intValue();
            }
        }
    }

    private void onAlbumCoverChanged(String str) {
        try {
            this.mImageViewAlbumCover.setImageBitmap(com.sds.android.sdk.lib.util.b.a(str, dp2px(200, getContext().getResources().getDisplayMetrics().density)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPlayStateBackground(PlayStatus playStatus) {
        this.mImageButtonPlayPause.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.img_appwidget_pause : R.drawable.img_appwidget_play);
    }

    private void setMiniLyricButton(boolean z) {
        this.mImageButtonMiniLyric.setImageResource(z ? R.drawable.img_appwidget_minilyric_on : R.drawable.img_appwidget_minilyric_off);
    }

    private void startActivity(String str) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        intent.putExtra(SocialConstants.PARAM_TYPE, "widget");
        getContext().startActivity(intent);
    }

    private void startService(String str) {
        getContext().startService(new Intent(getContext(), SupportService.class).putExtra("command", str));
    }

    private int dp2px(int i, float f) {
        return (int) ((((float) i) * f) + 0.5f);
    }

    private void destroy() {
        try {
            unRegisterPreferenceListener();
            unLoadMonitor();
            unInitSupport();
            this.mHandler.removeCallbacks(this.mRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTime(int i, int i2, float f) {
        int i3 = (int) (((float) i2) * f);
        if (i3 < 0) {
            i3 = 0;
        }
        this.mProgressBar.setProgress(i);
        this.mProgressBar.setMax(i2);
        this.mProgressBar.setSecondaryProgress(i3);
    }

    private void initSupport() {
        this.mSupport = e.a(getContext());
        this.mSupport.a(this.mSupportCallback);
    }

    private void unInitSupport() {
        if (this.mSupport != null) {
            this.mSupport.b(this.mSupportCallback);
        }
    }

    private void initAppWidget(c cVar) {
        if (cVar != null) {
            setPlayStateBackground(this.mSupport.n());
            onAlbumCoverChanged(b.a(this.mSupport.w()));
        }
    }
}
