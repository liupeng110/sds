package com.sds.android.ttpod.a.c;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.d.d;
import com.tencent.connect.common.Constants;

/* AuthDialog */
public class a extends Dialog {
    private WebView a;
    private ProgressDialog b;
    private String c;
    private com.sds.android.ttpod.a.b.a d;
    private Activity e;

    /* AuthDialog */
    private class a extends WebChromeClient {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (i == 100) {
                this.a.d();
                this.a.a.setVisibility(0);
            }
        }
    }

    /* AuthDialog */
    private class b extends WebViewClient {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        private boolean a(String str, String str2) {
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                try {
                    if (System.currentTimeMillis() < System.currentTimeMillis() + (Long.parseLong(str2) * 1000)) {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Bundle a = d.a(str);
            if (a == null || a.size() <= 0 || !a(a.getString("access_token"), a.getString(Constants.PARAM_EXPIRES_IN))) {
                super.onPageStarted(webView, str, bitmap);
                return;
            }
            if (this.a.d != null) {
                this.a.d.a(a);
            }
            webView.stopLoading();
            this.a.c();
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }
    }

    public a(Context context, String str, com.sds.android.ttpod.a.b.a aVar) {
        super(context, 16973840);
        this.e = (Activity) context;
        if (m.a(str)) {
            throw new IllegalArgumentException("authUrl can not be null.");
        }
        this.c = str;
        this.d = aVar;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        requestWindowFeature(1);
        this.a = new WebView(getContext());
        a();
    }

    private void a() {
        this.a.setVerticalScrollBarEnabled(false);
        this.a.setHorizontalScrollBarEnabled(false);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.setWebViewClient(new b());
        this.a.setWebChromeClient(new a());
        this.a.loadUrl(this.c);
        this.a.setVisibility(4);
        addContentView(this.a, new LayoutParams(-1, -1));
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.b.isShowing()) {
            d();
        } else {
            this.d.a();
        }
    }

    private void b() {
        if (this.b == null) {
            this.b = new ProgressDialog(getContext());
            this.b.requestWindowFeature(1);
            this.b.setTitle(getContext().getString(R.string.share_waiting_dialog_title));
            this.b.setMessage(getContext().getString(R.string.share_waiting_dialog_message));
            this.b.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4 && keyEvent.getRepeatCount() == 0) {
                        this.a.onBackPressed();
                    }
                    return true;
                }
            });
        }
        if (!this.b.isShowing()) {
            this.b.show();
        }
    }

    private void c() {
        if (this.e != null && !this.e.isFinishing() && isShowing()) {
            dismiss();
        }
    }

    private void d() {
        if (this.e != null && !this.e.isFinishing() && this.b.isShowing()) {
            this.b.dismiss();
        }
    }
}
