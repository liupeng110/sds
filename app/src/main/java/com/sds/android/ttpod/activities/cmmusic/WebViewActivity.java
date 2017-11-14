package com.sds.android.ttpod.activities.cmmusic;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;

public class WebViewActivity extends SlidingClosableActivity {
    private String mHref;
    private WebView mWebView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cmmusic_webview_activity);
        setTitle((int) R.string.cailing);
        initActionBar();
        getIntentData();
        viewInit();
    }

    private void initActionBar() {
        a actionBarController = getActionBarController();
        actionBarController.d();
        actionBarController.c(true);
        actionBarController.d((int) R.drawable.cmmusic_img_mine).a(new b(this) {
            final /* synthetic */ WebViewActivity a;

            {
                this.a = r1;
            }

            public void a(a.a aVar) {
                new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_PERSIONAL.getValue(), s.PAGE_NONE.getValue(), s.PAGE_CMMUSIC_PERSONAL_CODE.getValue()).post();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "PersionalListenControl");
                intent.putExtras(bundle);
                intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                this.a.startActivity(intent);
                this.a.finish();
            }
        });
        actionBarController.d((int) R.drawable.cmmusic_search).a(new b(this) {
            final /* synthetic */ WebViewActivity a;

            {
                this.a = r1;
            }

            public void a(a.a aVar) {
                new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_SEARCH.getValue(), s.PAGE_NONE.getValue(), s.PAGE_CMMUSIC_SEARCH_CODE.getValue()).post();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("pagename", "SearchPage");
                intent.putExtras(bundle);
                intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                this.a.startActivity(intent);
                this.a.finish();
            }
        });
    }

    private void viewInit() {
        this.mWebView = (WebView) findViewById(R.id.webview_webviewpage);
        this.mWebView.setWebChromeClient(new WebChromeClient());
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.getSettings().setLoadsImagesAutomatically(true);
        if (this.mWebView != null) {
            this.mWebView.setWebViewClient(new WebViewClient(this) {
                final /* synthetic */ WebViewActivity a;

                {
                    this.a = r1;
                }

                public void onPageFinished(WebView webView, String str) {
                }

                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    webView.loadUrl(str);
                    return true;
                }

                public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                    sslErrorHandler.proceed();
                }
            });
            if (this.mHref.length() > 0) {
                this.mWebView.loadUrl(this.mHref);
            } else {
                finish();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        finish();
        return false;
    }

    private void getIntentData() {
        try {
            this.mHref = getIntent().getExtras().getString("href");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
