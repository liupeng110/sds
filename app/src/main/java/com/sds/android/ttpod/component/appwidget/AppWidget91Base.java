package com.sds.android.ttpod.component.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.framework.support.d;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.io.File;
import java.io.FileInputStream;

public abstract class AppWidget91Base extends RelativeLayout implements OnLongClickListener {
    private static final int ARTIST_BITMAP_SIZE_IN_DP = 200;
    private static final int DENSITY_DEFAULT = 240;
    private static final int FLAG_91 = 32;
    private static final String KEY_PACKAGENAME = "packageName";
    private static final int MILLS_PER_SECOND = 1000;
    private static final float ROUND_DIFFERENCE = 0.5f;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final String TAG = "AppWidget91Base";
    private static final int VALUE_OF_EACH_DECIMAL = 10;
    protected boolean mClickedPlay;
    private Handler mHandler = new Handler();
    protected com.sds.android.ttpod.framework.storage.environment.b.a mOnPreferencesChangeListener = new com.sds.android.ttpod.framework.storage.environment.b.a(this) {
        final /* synthetic */ AppWidget91Base a;

        {
            this.a = r1;
        }

        public void a(c cVar) {
            g.a(AppWidget91Base.TAG, "onPreferencesChanged " + cVar + " mSupport=" + this.a.mSupport);
            if (c.CURRENT_ARTIST_BITMAP_PATH == cVar) {
                if (this.a.mSupport != null) {
                    this.a.onAlbumCoverChanged(b.a(this.a.mSupport.w()));
                }
            } else if (c.PLAY_MODE == cVar) {
                if (this.a.mSupport != null) {
                    this.a.setPlayModeBackground(b.l());
                }
            } else if (c.IS_SHOW_DESKTOP_LYRIC_ENABLED == cVar && this.a.mSupport != null) {
                this.a.setMiniLyricButton(b.s());
            }
        }
    };
    private a mRefreshWidgetMonitor;
    private Runnable mRunnable = new Runnable(this) {
        final /* synthetic */ AppWidget91Base a;

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
                    this.a.mSongDuration = this.a.mSupport.w().getDuration().intValue();
                }
                if (this.a.mSongDuration > 0 && this.a.mSupport.l() != null) {
                    this.a.updatePlayTime();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.a.mUpdatedHandlerRun = true;
            this.a.mHandler.postDelayed(this, 1000);
        }
    };
    protected int mSongDuration;
    protected com.sds.android.ttpod.framework.support.c mSupport = null;
    private d mSupportCallback = new d(this) {
        final /* synthetic */ AppWidget91Base a;

        {
            this.a = r1;
        }

        public void a() {
            super.a();
            g.a(AppWidget91Base.TAG, "onSupportServiceConnected");
            try {
                MediaItem w = this.a.mSupport.w();
                if (!(w == null || w.isNull())) {
                    this.a.onMetaChanged(w.getArtist(), w.getAlbum(), w.getTitle());
                    this.a.onAlbumCoverChanged(b.a(this.a.mSupport.w()));
                }
                this.a.setPlayModeBackground(b.l());
                this.a.setPlayStateBackground(this.a.mSupport.n());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void a(MediaItem mediaItem) {
            super.a(mediaItem);
            g.a(AppWidget91Base.TAG, "onPlayMediaChanged");
            if (mediaItem != null) {
                try {
                    if (!mediaItem.isNull()) {
                        this.a.onMetaChanged(mediaItem.getArtist(), mediaItem.getAlbum(), mediaItem.getTitle());
                        this.a.onAlbumCoverChanged(b.a(this.a.mSupport.w()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void a(PlayStatus playStatus) {
            super.a(playStatus);
            try {
                if (!this.a.mUpdatedHandlerRun) {
                    this.a.mHandler.postDelayed(this.a.mRunnable, 1000);
                }
                this.a.setPlayStateBackground(this.a.mSupport.n());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private boolean mUpdatedHandlerRun;

    private class a extends BroadcastReceiver {
        final /* synthetic */ AppWidget91Base a;

        private a(AppWidget91Base appWidget91Base) {
            this.a = appWidget91Base;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null) {
                String action = intent.getAction();
                g.a(AppWidget91Base.TAG, action);
                if ("com.nd.android.pandahome.THEME_INFO".equals(action)) {
                    action = intent.getStringExtra(AppWidget91Base.KEY_PACKAGENAME);
                    if (action == null || action.equals(this.a.getContext().getPackageName())) {
                        this.a.applyTheme(intent);
                        this.a.setPlayModeBackground(b.l());
                        this.a.setPlayStateBackground(this.a.mSupport.n());
                    }
                } else if (Action.LAUNCHER.equals(action)) {
                    if (!(this.a.mSupport == null || this.a.mSupport.y())) {
                        this.a.mSupport.b(this.a.mSupportCallback);
                        this.a.mSupport.a(this.a.mSupportCallback);
                    }
                    this.a.registerPreferenceListener();
                } else if (Action.EXIT.equals(action)) {
                    this.a.setPlayStateBackground(PlayStatus.STATUS_PAUSED);
                    g.a(AppWidget91Base.TAG, "EXIT");
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

    protected abstract void applyTheme(Intent intent);

    protected abstract ImageView getAlbumImageView();

    protected abstract void onMetaChanged(String str, String str2, String str3);

    protected abstract void registerPreferenceListener();

    protected abstract void setMiniLyricButton(boolean z);

    protected abstract void setPlayModeBackground(f fVar);

    protected abstract void setPlayStateBackground(PlayStatus playStatus);

    protected abstract void unRegisterPreferenceListener();

    protected abstract void updatePlayTime();

    public AppWidget91Base(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b.a(context);
    }

    public void onLoad(int i) {
        g.a(TAG, "onLoad");
        initSupport();
        loadMonitor();
        initAppWidget(this.mSupport);
        registerPreferenceListener();
        this.mHandler.postDelayed(this.mRunnable, 1000);
        Intent intent = new Intent("com.nd.android.pandahome.ASK_THEME");
        intent.putExtra(KEY_PACKAGENAME, getContext().getPackageName());
        intent.addFlags(32);
        getContext().sendBroadcast(intent);
    }

    private synchronized void initSupport() {
        g.a(TAG, "initSupport");
        this.mSupport = e.a(getContext());
        this.mSupport.a(this.mSupportCallback);
    }

    private synchronized void unInitSupport() {
        g.a(TAG, "unInitSupport");
        if (this.mSupport != null) {
            this.mSupport.b(this.mSupportCallback);
        }
    }

    private void loadMonitor() {
        g.a(TAG, "loadMonitor");
        this.mRefreshWidgetMonitor = new a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.nd.android.pandahome.THEME_INFO");
        intentFilter.addAction(Action.EXIT);
        intentFilter.addAction(Action.LAUNCHER);
        intentFilter.addAction(Action.PLAYLIST_IS_EMPTY);
        getContext().registerReceiver(this.mRefreshWidgetMonitor, intentFilter);
    }

    private void unLoadMonitor() {
        g.a(TAG, "unLoadMonitor");
        if (this.mRefreshWidgetMonitor != null) {
            getContext().unregisterReceiver(this.mRefreshWidgetMonitor);
            this.mRefreshWidgetMonitor = null;
        }
    }

    private void initAppWidget(com.sds.android.ttpod.framework.support.c cVar) {
        g.a(TAG, "initAppWidget");
        if (cVar != null && cVar.y()) {
            setPlayStateBackground(this.mSupport.n());
            setPlayModeBackground(b.l());
            onAlbumCoverChanged(b.a(this.mSupport.w()));
        }
    }

    public void onDestory(int i) {
        try {
            unRegisterPreferenceListener();
            unLoadMonitor();
            unInitSupport();
            this.mHandler.removeCallbacks(this.mRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startActivity(String str) {
        try {
            Intent intent = new Intent(str);
            intent.addFlags(268435456);
            getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startService(String str) {
        try {
            getContext().startService(new Intent(getContext(), SupportService.class).putExtra("command", str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onAlbumCoverChanged(String str) {
        try {
            Bitmap a = com.sds.android.sdk.lib.util.b.a(str, dp2px(200, getContext().getResources().getDisplayMetrics().density));
            ImageView albumImageView = getAlbumImageView();
            if (albumImageView != null) {
                albumImageView.setImageBitmap(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getFormatTime(int i) {
        int i2 = i / 1000;
        return unitFormat(i2 / 60) + ":" + unitFormat(i2 % 60);
    }

    private String unitFormat(int i) {
        if (i < 0) {
            return "00";
        }
        if (i < 0 || i >= 10) {
            return "" + i;
        }
        return FeedbackItem.STATUS_WAITING + i;
    }

    protected int dp2px(int i, float f) {
        return (int) ((((float) i) * f) + 0.5f);
    }

    /* JADX err: Inconsistent code. */
    protected static android.graphics.drawable.StateListDrawable newSelector(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
        /*
        r0 = 0;
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0074 }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0074 }
        r2.<init>(r9);	 Catch:{ Exception -> 0x0074 }
        r1.<init>(r2);	 Catch:{ Exception -> 0x0074 }
        r2 = android.graphics.BitmapFactory.decodeStream(r1);	 Catch:{ Exception -> 0x0074 }
        r1 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        r2.setDensity(r1);	 Catch:{ Exception -> 0x009e }
        r3 = r2;
    L_0x0015:
        r1 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x007b }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x007b }
        r2.<init>(r10);	 Catch:{ Exception -> 0x007b }
        r1.<init>(r2);	 Catch:{ Exception -> 0x007b }
        r2 = android.graphics.BitmapFactory.decodeStream(r1);	 Catch:{ Exception -> 0x007b }
        r1 = 240; // 0xf0 float:3.36E-43 double:1.186E-321;
        r2.setDensity(r1);	 Catch:{ Exception -> 0x009c }
        r4 = r2;
    L_0x0029:
        r1 = new android.graphics.drawable.StateListDrawable;	 Catch:{ Exception -> 0x0097 }
        r1.<init>();	 Catch:{ Exception -> 0x0097 }
        if (r3 != 0) goto L_0x0082;
    L_0x0030:
        r3 = r0;
    L_0x0031:
        if (r4 != 0) goto L_0x008d;
    L_0x0033:
        r2 = r0;
    L_0x0034:
        r4 = 0;
        r5 = 0;
        r6 = 2;
        r6 = new int[r6];	 Catch:{ Exception -> 0x0097 }
        r6 = {16842919, 16842910};	 Catch:{ Exception -> 0x0097 }
        r1.addState(r6, r2);	 Catch:{ Exception -> 0x0097 }
        r2 = 2;
        r2 = new int[r2];	 Catch:{ Exception -> 0x0097 }
        r2 = {16842910, 16842908};	 Catch:{ Exception -> 0x0097 }
        r1.addState(r2, r4);	 Catch:{ Exception -> 0x0097 }
        r2 = 1;
        r2 = new int[r2];	 Catch:{ Exception -> 0x0097 }
        r6 = 0;
        r7 = 16842910; // 0x101009e float:2.3694E-38 double:8.321503E-317;
        r2[r6] = r7;	 Catch:{ Exception -> 0x0097 }
        r1.addState(r2, r3);	 Catch:{ Exception -> 0x0097 }
        r2 = 1;
        r2 = new int[r2];	 Catch:{ Exception -> 0x0097 }
        r6 = 0;
        r7 = 16842908; // 0x101009c float:2.3693995E-38 double:8.321502E-317;
        r2[r6] = r7;	 Catch:{ Exception -> 0x0097 }
        r1.addState(r2, r4);	 Catch:{ Exception -> 0x0097 }
        r2 = 1;
        r2 = new int[r2];	 Catch:{ Exception -> 0x0097 }
        r4 = 0;
        r6 = 16842909; // 0x101009d float:2.3693998E-38 double:8.3215027E-317;
        r2[r4] = r6;	 Catch:{ Exception -> 0x0097 }
        r1.addState(r2, r5);	 Catch:{ Exception -> 0x0097 }
        r2 = 0;
        r2 = new int[r2];	 Catch:{ Exception -> 0x0097 }
        r1.addState(r2, r3);	 Catch:{ Exception -> 0x0097 }
        r0 = r1;
    L_0x0073:
        return r0;
    L_0x0074:
        r1 = move-exception;
        r2 = r0;
    L_0x0076:
        r1.printStackTrace();	 Catch:{ Exception -> 0x0097 }
        r3 = r2;
        goto L_0x0015;
    L_0x007b:
        r1 = move-exception;
        r2 = r0;
    L_0x007d:
        r1.printStackTrace();	 Catch:{ Exception -> 0x0097 }
        r4 = r2;
        goto L_0x0029;
    L_0x0082:
        r2 = new android.graphics.drawable.BitmapDrawable;	 Catch:{ Exception -> 0x0097 }
        r5 = r8.getResources();	 Catch:{ Exception -> 0x0097 }
        r2.<init>(r5, r3);	 Catch:{ Exception -> 0x0097 }
        r3 = r2;
        goto L_0x0031;
    L_0x008d:
        r2 = new android.graphics.drawable.BitmapDrawable;	 Catch:{ Exception -> 0x0097 }
        r5 = r8.getResources();	 Catch:{ Exception -> 0x0097 }
        r2.<init>(r5, r4);	 Catch:{ Exception -> 0x0097 }
        goto L_0x0034;
    L_0x0097:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0073;
    L_0x009c:
        r1 = move-exception;
        goto L_0x007d;
    L_0x009e:
        r1 = move-exception;
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.appwidget.AppWidget91Base.newSelector(android.content.Context, java.lang.String, java.lang.String):android.graphics.drawable.StateListDrawable");
    }

    protected Drawable getDrawableFromPath(String str) {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(new FileInputStream(new File(str)));
            decodeStream.setDensity(DENSITY_DEFAULT);
            return new BitmapDrawable(getContext().getResources(), decodeStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean onLongClick(View view) {
        try {
            View view2 = (View) getParent();
            if (view2 == null || !view2.performLongClick()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
