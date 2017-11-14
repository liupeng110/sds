package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.ttpod.R;

/* TTWebViewClient */
public class i extends WebViewClient {
    private String a;
    private Context b;
    private a c;
    private View d;
    private Button e;
    private long f;
    private long g;
    private boolean h;
    private Handler i;

    /* TTWebViewClient */
    public interface a {
        void onLoadResource(WebView webView, String str);

        boolean onOverrideUrlLoadingEvent(WebView webView, String str);

        void onPageFinishedEvent(WebView webView, String str);

        void onPageStartedEvent(WebView webView, String str, Bitmap bitmap);

        boolean onReceivedErrorEvent(WebView webView, int i, String str, String str2);

        void onReloadEvent();
    }

    public i(Context context) {
        this.f = 0;
        this.g = 0;
        this.h = true;
        this.i = new Handler();
        this.b = context;
    }

    public i(Context context, a aVar, View view) {
        this(context);
        this.c = aVar;
        this.d = view;
        this.d.setVisibility(4);
        this.e = (Button) this.d.findViewById(R.id.reload_button);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.c != null) {
                    this.a.c.onReloadEvent();
                }
            }
        });
    }

    public void a() {
        if (this.e != null) {
            this.e.setOnClickListener(null);
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!c.e()) {
            return true;
        }
        if (this.a != null && this.a.equals(str)) {
            return true;
        }
        this.a = str;
        webView.getSettings().setBlockNetworkImage(true);
        if (this.c != null) {
            return this.c.onOverrideUrlLoadingEvent(webView, str);
        }
        return false;
    }

    public void onLoadResource(WebView webView, String str) {
        if (this.c != null) {
            this.c.onLoadResource(webView, str);
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.c != null) {
            this.c.onPageStartedEvent(webView, str, bitmap);
        }
        ((TTWebView) webView).setState(com.sds.android.ttpod.widget.TTWebView.a.WEBVIEW_PAGE_START);
    }

    public void onPageFinished(WebView webView, String str) {
        webView.getSettings().setBlockNetworkImage(false);
        this.a = "";
        ((TTWebView) webView).a();
        if (this.c != null) {
            this.c.onPageFinishedEvent(webView, str);
        }
        ((TTWebView) webView).setState(com.sds.android.ttpod.widget.TTWebView.a.WEBVIEW_PAGE_FINISHED);
        this.g = System.currentTimeMillis() - this.f;
        if (this.g > 1000) {
            if (this.d.getVisibility() == 0) {
                webView.goBack();
            }
            this.i.postDelayed(new Runnable(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.d != null) {
                        this.a.d.setVisibility(8);
                    }
                }
            }, 1000);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.c != null) {
            this.h = this.c.onReceivedErrorEvent(webView, i, str, str2);
        }
        if (this.h) {
            this.d.setVisibility(0);
            ((TTWebView) webView).setState(com.sds.android.ttpod.widget.TTWebView.a.WEBVIEW_PAGE_ERROR);
            this.f = System.currentTimeMillis();
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
    }

    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        super.onReceivedLoginRequest(webView, str, str2, str3);
    }
}
