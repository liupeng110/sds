package com.sds.android.ttpod.component.lockscreen;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.s;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.modules.skin.e.j;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.framework.support.search.b;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class LockScreenActivity extends SlidingClosableActivity implements OnClickListener {
    private static final int LYRIC_FADE_LENGTH_IN_DP = 22;
    private static final int LYRIC_NORMAL_TEXT_SIZE_CUT = 3;
    private static final int REFRESH_DELAY = 100;
    private static final int REFRESH_LYRIC_MSG = 0;
    private static final int SDK_VERSION_CODE_L = 20;
    private static final String TAG = "LockScreenActivity";
    private TextView mAMPMTextView = null;
    private SimpleDateFormat mDateTextDateFormat = new SimpleDateFormat("MM月dd日");
    private TextView mDateTextView = null;
    private boolean mIgnoreResume;
    private ImageView mImgViewNext;
    private ImageView mImgViewPause;
    private ImageView mImgViewPlay;
    private ImageView mImgViewPre;
    private ImageView mImgViewSlidingUnlock;
    private boolean mIsReceiverRegistered = false;
    private View mLockScreenLayout = null;
    private LyricView mLyricView;
    private MediaItem mPlayingMediaItem;
    private PowerManager mPowerManager;
    private Handler mRefreshHandler = new Handler(this) {
        final /* synthetic */ LockScreenActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    removeMessages(0);
                    if (this.a.mLyricView != null && this.a.mLyricView.getLyric() != null) {
                        if (this.a.mPowerManager == null || this.a.mPowerManager.isScreenOn()) {
                            this.a.mLyricView.setPlayingTime((long) e.a(this.a).l().intValue());
                            sendEmptyMessageDelayed(0, 100);
                            break;
                        }
                        return;
                    }
                    return;
                    break;
            }
            super.handleMessage(message);
        }
    };
    private AnimTransView mSongImageView = null;
    private a mTickReceiver;
    private SimpleDateFormat mTimeTextDateFormat = new SimpleDateFormat("HH:mm");
    private TextView mTimeTextView = null;
    private TextView mTvSongInfo = null;
    private SimpleDateFormat mWeekDayDateFormat = new SimpleDateFormat("EEEE");

    private static class a extends BroadcastReceiver {
        private final WeakReference<LockScreenActivity> a;

        public a(LockScreenActivity lockScreenActivity) {
            this.a = new WeakReference(lockScreenActivity);
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.TIME_TICK".equals(intent.getAction()) && this.a.get() != null) {
                ((LockScreenActivity) this.a.get()).updateTime();
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PICTURE_STATE, i.a(cls, "loadPictureAfterSearchFinished", b.class, List.class, String.class, Bitmap.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, i.a(cls, "updatePlayMeta", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_LYRIC_STATE, i.a(cls, "updateSearchLyricState", b.class, List.class, String.class, g.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPowerManager = (PowerManager) getSystemService("power");
        Window window = getWindow();
        if (VERSION.SDK_INT < 20) {
            window.addFlags(4194304);
        }
        window.addFlags(AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END);
        if (com.sds.android.ttpod.framework.storage.environment.b.G()) {
            window.setFlags(1024, 1024);
        } else {
            window.clearFlags(1024);
        }
        try {
            bindView();
            initView();
            setContentView(this.mLockScreenLayout);
            this.mTickReceiver = new a(this);
            setTBSPage("tt_lock_screen");
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            com.sds.android.ttpod.framework.a.g.b().c();
            com.sds.android.ttpod.framework.storage.a.a.a().b();
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        updateTime();
        if (!this.mIsReceiverRegistered) {
            this.mIsReceiverRegistered = true;
            registerReceiver(this.mTickReceiver, buildTimeTickFilter());
        }
        showCachedLyricAndPic();
        this.mImgViewSlidingUnlock.setBackgroundResource(R.drawable.xml_anim_sliding_to_unlock);
        ((AnimationDrawable) this.mImgViewSlidingUnlock.getBackground()).start();
        if (!this.mIgnoreResume && this.mPowerManager.isScreenOn()) {
            l.a();
        }
        this.mIgnoreResume = false;
    }

    protected void onPause() {
        super.onPause();
        ((AnimationDrawable) this.mImgViewSlidingUnlock.getBackground()).stop();
        if (this.mIsReceiverRegistered) {
            this.mIsReceiverRegistered = false;
            unregisterReceiver(this.mTickReceiver);
        }
        if (this.mRefreshHandler != null) {
            this.mRefreshHandler.removeMessages(0);
        }
        if (!this.mPowerManager.isScreenOn()) {
            e.a(this).r();
        }
    }

    public void onDetachedFromWindow() {
        if (this.mIsReceiverRegistered) {
            this.mIsReceiverRegistered = false;
            unregisterReceiver(this.mTickReceiver);
        }
        super.onDetachedFromWindow();
    }

    protected void onDestroy() {
        this.mPowerManager = null;
        super.onDestroy();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
        y.a(this.mLockScreenLayout);
    }

    protected boolean needFinishAnimation() {
        return false;
    }

    public void updatePlayMeta() {
        this.mPlayingMediaItem = com.sds.android.ttpod.framework.storage.a.a.a().M();
        setSongInfoTextView();
        setLyric(null);
        this.mSongImageView.setImageBitmapDelay(null);
    }

    public void loadPictureAfterSearchFinished(b bVar, List<ResultData> list, String str, Bitmap bitmap) {
        if (!m.a(this.mPlayingMediaItem.getID(), str)) {
            return;
        }
        if (b.SEARCH_LOCAL_FINISHED == bVar || b.SEARCH_DOWNLOAD_FINISHED == bVar) {
            this.mSongImageView.setImageBitmapDelay(bitmap);
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        switch (playStatus) {
            case STATUS_PLAYING:
                this.mImgViewPause.setVisibility(0);
                this.mImgViewPlay.setVisibility(4);
                return;
            default:
                this.mImgViewPlay.setVisibility(0);
                this.mImgViewPause.setVisibility(4);
                return;
        }
    }

    public void updateSearchLyricState(b bVar, List<ResultData> list, String str, g gVar) {
        if (m.a(this.mPlayingMediaItem.getID(), str)) {
            switch (bVar) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    setLyric(gVar);
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public void onClick(View view) {
        acquireFastClickSupport();
        switch (view.getId()) {
            case R.id.imageview_pre:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PREVIOUS, new Object[0]));
                return;
            case R.id.imageview_play:
                if (PlayStatus.STATUS_PAUSED == e.a(this).n()) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                    return;
                } else {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                    return;
                }
            case R.id.imageview_pause:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                return;
            case R.id.imageview_next:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
                return;
            default:
                return;
        }
    }

    private void bindView() {
        this.mLockScreenLayout = getLayoutInflater().inflate(R.layout.activity_lockscreen, null);
        this.mTimeTextView = (TextView) this.mLockScreenLayout.findViewById(R.id.timeText);
        this.mAMPMTextView = (TextView) this.mLockScreenLayout.findViewById(R.id.timeFormat);
        this.mDateTextView = (TextView) this.mLockScreenLayout.findViewById(R.id.dateText);
        this.mTvSongInfo = (TextView) this.mLockScreenLayout.findViewById(R.id.tv_songname);
        this.mSongImageView = (AnimTransView) this.mLockScreenLayout.findViewById(R.id.songImage);
        this.mImgViewPre = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_pre);
        this.mImgViewPlay = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_play);
        this.mImgViewPause = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_pause);
        this.mImgViewNext = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_next);
        this.mImgViewSlidingUnlock = (ImageView) this.mLockScreenLayout.findViewById(R.id.imageview_sliding_to_unlock);
        this.mLyricView = (LyricView) this.mLockScreenLayout.findViewById(R.id.lyric_view);
    }

    private void initView() {
        hideActionBar();
        this.mLockScreenLayout.setKeepScreenOn(com.sds.android.ttpod.framework.storage.environment.b.B());
        try {
            Drawable drawable;
            if (s.b()) {
                drawable = WallpaperManager.getInstance(this).getDrawable();
            } else {
                drawable = WallpaperManager.getInstance(this).getFastDrawable();
            }
            this.mLockScreenLayout.setBackgroundDrawable(drawable);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        Typeface c = com.sds.android.ttpod.common.c.a.c();
        if (c != null) {
            this.mTimeTextView.setTypeface(c);
            this.mAMPMTextView.setTypeface(c);
        }
        if (DateFormat.is24HourFormat(this)) {
            this.mAMPMTextView.setVisibility(8);
            this.mTimeTextDateFormat = new SimpleDateFormat("HH:mm");
        } else {
            this.mAMPMTextView.setText(getAMPMText());
            this.mTimeTextDateFormat = new SimpleDateFormat("hh:mm");
        }
        this.mPlayingMediaItem = com.sds.android.ttpod.framework.storage.a.a.a().M();
        setSongInfoTextView();
        this.mImgViewPre.setOnClickListener(this);
        this.mImgViewPause.setOnClickListener(this);
        this.mImgViewPlay.setOnClickListener(this);
        this.mImgViewNext.setOnClickListener(this);
        updatePlayStatus(e.a(this).n());
        initLyricView();
    }

    private void initLyricView() {
        this.mLyricView.setSlowScroll(false);
        this.mLyricView.setDisplayMode(com.sds.android.ttpod.framework.modules.skin.view.LyricView.a.Normal);
        this.mLyricView.setDefaultColorHighlight(-1);
        this.mLyricView.setColorHighlight(-1);
        this.mLyricView.setFadeEdgeLength(com.sds.android.ttpod.common.c.a.a(22));
        int T = com.sds.android.ttpod.framework.storage.environment.b.T();
        this.mLyricView.b(0, this.mLyricView.getDefaultFontSizeHighlight() + ((float) T));
        this.mLyricView.a(0, (((float) T) + this.mLyricView.getDefaultFontSizeNormal()) - 3.0f);
    }

    private void showCachedLyricAndPic() {
        Bitmap bitmap = null;
        if (this.mLyricView.getLyric() == null) {
            String i = com.sds.android.ttpod.framework.storage.a.a.a().i();
            setLyric(i == null ? null : j.b(i));
        } else {
            this.mRefreshHandler.sendEmptyMessage(0);
        }
        if (this.mSongImageView.getDrawable() == null) {
            i = com.sds.android.ttpod.framework.storage.a.a.a().g();
            AnimTransView animTransView = this.mSongImageView;
            if (i != null) {
                bitmap = com.sds.android.sdk.lib.util.b.b(i, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e());
            }
            animTransView.setImageBitmap(bitmap);
        }
    }

    private void updateTime() {
        Date date = new Date();
        if (!(this.mTimeTextView == null || this.mTimeTextDateFormat == null)) {
            this.mTimeTextView.setText(this.mTimeTextDateFormat.format(date));
        }
        if (!(this.mDateTextView == null || this.mDateTextDateFormat == null || this.mWeekDayDateFormat == null)) {
            this.mDateTextView.setText(this.mDateTextDateFormat.format(date) + "  " + this.mWeekDayDateFormat.format(date));
        }
        if (this.mAMPMTextView != null) {
            this.mAMPMTextView.setText(getAMPMText());
        }
    }

    private String getAMPMText() {
        return new GregorianCalendar().get(9) == 0 ? "AM" : "PM";
    }

    private void setSongInfoTextView() {
        if (this.mPlayingMediaItem != null) {
            CharSequence stringBuffer = new StringBuffer(TextUtils.isEmpty(this.mPlayingMediaItem.getTitle()) ? getString(R.string.unknown) : this.mPlayingMediaItem.getTitle());
            stringBuffer.append(" - ");
            stringBuffer.append(TTTextUtils.validateString(this, this.mPlayingMediaItem.getArtist()));
            this.mTvSongInfo.setText(stringBuffer);
        }
    }

    private void setLyric(g gVar) {
        this.mLyricView.setLyric(gVar);
        if (gVar == null) {
            this.mRefreshHandler.removeMessages(0);
            this.mLyricView.setState(8);
            return;
        }
        this.mRefreshHandler.sendEmptyMessage(0);
    }

    private IntentFilter buildTimeTickFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        return intentFilter;
    }
}
