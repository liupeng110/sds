package com.sds.android.ttpod;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.GuideActivity;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.b.u;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.i;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.Map;

public class EntryActivity extends BaseActivity {
    private static final String ACTION_BAOFENG = "com.sds.android.ttpod.player_entry";
    private static final String ACTION_DOWNLAOD = "com.sds.android.ttpod.framework.base.Action.start_downloadmanager";
    private static final int ACTION_MAIN = 0;
    private static final long MAIN_ACTIVITY_START_DELAY = 100;
    private static final String TAG = "EntryActivity";
    private OnClickListener mOnClickAudioEnableListener = new OnClickListener(this) {
        final /* synthetic */ EntryActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            boolean z;
            view.setBackgroundResource(!b.a() ? R.drawable.xml_background_button_splash_audio_disabled : R.drawable.xml_background_button_splash_audio_enabled);
            com.sds.android.ttpod.framework.base.a.b a = com.sds.android.ttpod.framework.base.a.b.a();
            a aVar = a.SET_AUDIO_ENABLED;
            Object[] objArr = new Object[1];
            if (b.a()) {
                z = false;
            } else {
                z = true;
            }
            objArr[0] = Boolean.valueOf(z);
            a.a(new com.sds.android.ttpod.framework.base.a.a(aVar, objArr));
        }
    };
    private boolean mSentLoadSplashCommand;
    private long mWaitInitBeginInMillis = 0;
    private Handler mWaitInitFinishedHandler = new Handler(this) {
        final /* synthetic */ EntryActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (i.a()) {
                this.a.startMainActivity();
                return;
            }
            if (this.a.getWaitInitTimeMillis() > 2000) {
                f.a((int) R.string.doing_init);
            }
            sendEmptyMessageDelayed(0, EntryActivity.MAIN_ACTIVITY_START_DELAY);
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!isActionTransactByMainActivity()) {
            getWindow().setFlags(1024, 1024);
        }
        setContentView((int) R.layout.activity_entry);
        setTBSPage("tt_splash");
    }

    protected void onResume() {
        super.onResume();
        if (com.sds.android.ttpod.framework.a.b() || isActionTransactByMainActivity()) {
            startMainActivityDelay();
        }
    }

    private boolean isActionTransactByMainActivity() {
        if (getIntent() != null) {
            String action = getIntent().getAction();
            if ("android.intent.action.VIEW".equals(action) || ACTION_BAOFENG.equals(action) || ACTION_DOWNLAOD.equals(action)) {
                return true;
            }
        }
        return false;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_SPLASH, com.sds.android.sdk.lib.util.i.a(EntryActivity.class, "updateSplash", Bitmap.class, Bitmap.class, String.class, Boolean.class));
        map.put(a.FINISH_SPLASH, com.sds.android.sdk.lib.util.i.a(EntryActivity.class, "finishSplash", new Class[0]));
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        g.d(TAG, "EntryActivity onWindowFocusChanged splash loaded test");
        if (z && !this.mSentLoadSplashCommand && !com.sds.android.ttpod.framework.a.b()) {
            this.mSentLoadSplashCommand = true;
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.LOAD_SPLASH, Integer.valueOf(R.drawable.img_splash), Integer.valueOf(R.string.readme)));
        }
    }

    public void onBackPressed() {
    }

    @TargetApi(11)
    public void updateSplash(Bitmap bitmap, Bitmap bitmap2, String str, Boolean bool) {
        String str2 = TAG;
        String str3 = "splash loaded splashBitmap != null ? %b";
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(bitmap2 != null);
        g.d(str2, str3, objArr);
        if (bitmap != null) {
            ImageView imageView = (ImageView) findViewById(R.id.imageview_channel_logo);
            imageView.setVisibility(0);
            imageView.setImageBitmap(bitmap);
        }
        if (bitmap2 != null) {
            imageView = (ImageView) findViewById(R.id.imageview_splash);
            imageView.setImageBitmap(bitmap2);
            com.sds.android.sdk.lib.util.b.a(imageView);
            if (bool.booleanValue()) {
                View findViewById = findViewById(R.id.button_voice);
                findViewById.setVisibility(0);
                findViewById.setBackgroundResource(b.a() ? R.drawable.xml_background_button_splash_audio_enabled : R.drawable.xml_background_button_splash_audio_disabled);
                findViewById.setOnClickListener(this.mOnClickAudioEnableListener);
            }
        }
        if (e.b(str)) {
            View webView = new WebView(this);
            webView.getSettings().setJavaScriptEnabled(true);
            if (j.c()) {
                webView.setLayerType(1, null);
            }
            webView.setBackgroundColor(0);
            Drawable background = webView.getBackground();
            if (background != null) {
                background.setAlpha(1);
            }
            addContentView(webView, new LayoutParams(-1, -1));
            webView.loadUrl("file://" + str);
        }
        getWindow().setFlags(1024, 1024);
    }

    public void finishSplash() {
        com.sds.android.ttpod.framework.a.c();
        g.d(TAG, "finishSplash");
        startMainActivityDelay();
    }

    private void handlerNewVersion() {
        if (b.aN()) {
            String Y = b.Y();
            if (!m.a(Y) && "assets://".equals(v.b(Y)) && e.j(Y).startsWith("1")) {
                b.h("");
            }
            com.sds.android.ttpod.framework.storage.a.a.a().u();
        }
    }

    private void handlerStartStatistic() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString(SocialConstants.PARAM_TYPE);
            if ("lyric".equals(string)) {
                com.sds.android.ttpod.framework.a.b.b.a("lyric_start");
            } else if ("notification".equals(string)) {
                com.sds.android.ttpod.framework.a.b.b.a("notification_start");
            } else if ("widget".equals(string)) {
                com.sds.android.ttpod.framework.a.b.b.a("widget_start");
            }
        }
    }

    private void startMainActivityDelay() {
        this.mWaitInitFinishedHandler.sendEmptyMessageDelayed(0, MAIN_ACTIVITY_START_DELAY);
    }

    private void startMainActivity() {
        handlerStartStatistic();
        handlerNewVersion();
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.LOAD_BACKGROUND, new Object[0]));
        if (!b.aN() || u.b(getIntent().getData())) {
            com.sds.android.ttpod.framework.a.a.b.a(TAG, "realStartMain, mWaitTimeInMillisIs " + getWaitInitTimeMillis() + "ms");
            Intent putExtras = new Intent(this, MainActivity.class).setData(getIntent().getData()).putExtras(getIntent());
            putExtras.fillIn(getIntent(), 35);
            startActivity(putExtras);
            finish();
            return;
        }
        b.q(EnvironmentUtils.a.f());
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }

    private long getWaitInitTimeMillis() {
        if (this.mWaitInitBeginInMillis <= 0) {
            this.mWaitInitBeginInMillis = System.currentTimeMillis();
        }
        return System.currentTimeMillis() - this.mWaitInitBeginInMillis;
    }
}
