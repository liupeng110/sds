package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import java.io.File;

public class TTWebView extends WebView {
    private a a;
    private boolean b = false;

    public enum a {
        WEBVIEW_INIT(0),
        WEBVIEW_PAGE_START(1),
        WEBVIEW_PAGE_FINISHED(2),
        WEBVIEW_PAGE_ERROR(3);
        
        private final int mValue;

        private a(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public static a valueOf(int i) {
            if (i < WEBVIEW_INIT.value() || i > WEBVIEW_PAGE_ERROR.value()) {
                return WEBVIEW_INIT;
            }
            return values()[i];
        }
    }

    public TTWebView(Context context) {
        super(context);
        a(context);
    }

    public TTWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TTWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setScrollBarStyle(0);
        setVisibility(0);
        requestFocus(TransportMediator.KEYCODE_MEDIA_RECORD);
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setBuiltInZoomControls(false);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(context.getApplicationInfo().dataDir + File.separatorChar + "databases");
        a(settings);
    }

    private void a(WebSettings webSettings) {
        try {
            webSettings.setAppCacheEnabled(true);
            webSettings.setAppCacheMaxSize(1059576);
            webSettings.setDomStorageEnabled(true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onPause() {
        a();
        try {
            pauseTimers();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onPause();
    }

    public void onResume() {
        try {
            resumeTimers();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onResume();
    }

    public void a() {
        CookieSyncManager.getInstance().startSync();
        CookieSyncManager.getInstance().sync();
    }

    public void setState(a aVar) {
        this.a = aVar;
    }

    public a getState() {
        return this.a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b) {
            int action = motionEvent.getAction();
            if (action == 2 || action == 0) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setDisallowInterceptTouchEvents(boolean z) {
        this.b = z;
    }
}
