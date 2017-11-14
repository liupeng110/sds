package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.AuthMap.Auth;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.b;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class AuthDialog extends Dialog {
    private static WeakReference<Context> a;
    private static WeakReference<View> l;
    private String b;
    private OnTimeListener c;
    private IUiListener d;
    private Handler e;
    private FrameLayout f;
    private LinearLayout g;
    private FrameLayout h;
    private ProgressBar i;
    private String j;
    private WebView k;
    private boolean m = false;

    /* ProGuard */
    private class JsListener {
        final /* synthetic */ AuthDialog a;

        public void onCancelLogin() {
            onCancel(null);
        }

        public void onCancel(String str) {
            this.a.e.obtainMessage(2, str).sendToTarget();
            this.a.dismiss();
        }

        public void showMsg(String str) {
            this.a.e.obtainMessage(3, str).sendToTarget();
        }

        public void onLoad(String str) {
            this.a.e.obtainMessage(4, str).sendToTarget();
        }
    }

    /* ProGuard */
    private class LoginWebViewClient extends WebViewClient {
        final /* synthetic */ AuthDialog a;

        private LoginWebViewClient(AuthDialog authDialog) {
            this.a = authDialog;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Util.logd("AuthDialog", "Redirect URL: " + str);
            if (str.startsWith(AuthConstants.REDIRECT_BROWSER_URI)) {
                JSONObject parseUrlToJson = Util.parseUrlToJson(str);
                this.a.m = this.a.f();
                if (!this.a.m) {
                    if (parseUrlToJson.optString("fail_cb", null) != null) {
                        this.a.callJs(parseUrlToJson.optString("fail_cb"), "");
                    } else if (parseUrlToJson.optInt("fall_to_wv") == 1) {
                        AuthDialog.a(this.a, this.a.b.indexOf("?") > -1 ? "&" : "?");
                        AuthDialog.a(this.a, (Object) "browser_error=1");
                        this.a.k.loadUrl(this.a.b);
                    } else {
                        String optString = parseUrlToJson.optString("redir", null);
                        if (optString != null) {
                            this.a.k.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str.startsWith(ServerSetting.getInstance().getEnvUrl((Context) AuthDialog.a.get(), ServerSetting.DEFAULT_REDIRECT_URI))) {
                this.a.c.onComplete(Util.parseUrlToJson(str));
                this.a.dismiss();
                return true;
            } else if (str.startsWith("auth://cancel")) {
                this.a.c.onCancel();
                this.a.dismiss();
                return true;
            } else if (str.startsWith("auth://close")) {
                this.a.dismiss();
                return true;
            } else if (!str.startsWith("download://")) {
                return false;
            } else {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring("download://".length()))));
                intent.addFlags(268435456);
                if (!(AuthDialog.a == null || AuthDialog.a.get() == null)) {
                    ((Context) AuthDialog.a.get()).startActivity(intent);
                }
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            this.a.c.onError(new UiError(i, str, str2));
            if (!(AuthDialog.a == null || AuthDialog.a.get() == null)) {
                Toast.makeText((Context) AuthDialog.a.get(), "网络连接异常或系统错误", 0).show();
            }
            this.a.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Util.logd("AuthDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (AuthDialog.l != null && AuthDialog.l.get() != null) {
                ((View) AuthDialog.l.get()).setVisibility(0);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!(AuthDialog.l == null || AuthDialog.l.get() == null)) {
                ((View) AuthDialog.l.get()).setVisibility(8);
            }
            this.a.k.setVisibility(0);
        }
    }

    /* ProGuard */
    private class OnTimeListener implements IUiListener {
        String a;
        String b;
        final /* synthetic */ AuthDialog c;
        private String d;
        private IUiListener e;

        public OnTimeListener(AuthDialog authDialog, String str, String str2, String str3, IUiListener iUiListener) {
            this.c = authDialog;
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.e = iUiListener;
        }

        private void a(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            b.a().a((Context) AuthDialog.a.get(), this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.b, this.a, "1000067");
            if (this.e != null) {
                this.e.onComplete(jSONObject);
                this.e = null;
            }
        }

        public void onError(UiError uiError) {
            b.a().a((Context) AuthDialog.a.get(), this.d + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, this.b, uiError.errorMessage != null ? uiError.errorMessage + this.a : this.a, "1000067");
            if (this.e != null) {
                this.e.onError(uiError);
                this.e = null;
            }
        }

        public void onCancel() {
            if (this.e != null) {
                this.e.onCancel();
                this.e = null;
            }
        }
    }

    /* ProGuard */
    private static class THandler extends Handler {
        private OnTimeListener a;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.a = onTimeListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.a((String) message.obj);
                    return;
                case 2:
                    this.a.onCancel();
                    return;
                case 3:
                    if (AuthDialog.a != null && AuthDialog.a.get() != null) {
                        AuthDialog.b((Context) AuthDialog.a.get(), (String) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    static /* synthetic */ String a(AuthDialog authDialog, Object obj) {
        String str = authDialog.b + obj;
        authDialog.b = str;
        return str;
    }

    public AuthDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        a = new WeakReference(context.getApplicationContext());
        this.b = str2;
        this.c = new OnTimeListener(this, str, str2, qQToken.getAppId(), iUiListener);
        this.e = new THandler(this.c, context.getMainLooper());
        this.d = iUiListener;
        this.j = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        c();
        e();
    }

    public void onBackPressed() {
        if (!this.m) {
            this.c.onCancel();
        }
        super.onBackPressed();
    }

    private void c() {
        d();
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.k = new WebView((Context) a.get());
        this.k.setLayoutParams(layoutParams);
        try {
            Method method = this.k.getSettings().getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
            if (method != null) {
                method.invoke(this.k, new Object[]{"searchBoxJavaBridge_"});
            }
        } catch (Exception e) {
        }
        this.f = new FrameLayout((Context) a.get());
        layoutParams.gravity = 17;
        this.f.setLayoutParams(layoutParams);
        this.f.addView(this.k);
        this.f.addView(this.h);
        l = new WeakReference(this.h);
        setContentView(this.f);
    }

    private void d() {
        this.i = new ProgressBar((Context) a.get());
        this.i.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.g = new LinearLayout((Context) a.get());
        View view = null;
        if (this.j.equals("action_login")) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            View textView = new TextView((Context) a.get());
            if (Locale.getDefault().getLanguage().equals("zh")) {
                textView.setText("登录中...");
            } else {
                textView.setText("Logging in...");
            }
            textView.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK));
            textView.setTextSize(18.0f);
            textView.setLayoutParams(layoutParams);
            view = textView;
        }
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.g.setLayoutParams(layoutParams2);
        this.g.addView(this.i);
        if (view != null) {
            this.g.addView(view);
        }
        this.h = new FrameLayout((Context) a.get());
        LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        layoutParams3.leftMargin = 80;
        layoutParams3.rightMargin = 80;
        layoutParams3.topMargin = 40;
        layoutParams3.bottomMargin = 40;
        layoutParams3.gravity = 17;
        this.h.setLayoutParams(layoutParams3);
        this.h.setBackgroundResource(17301504);
        this.h.addView(this.g);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void e() {
        this.k.setVerticalScrollBarEnabled(false);
        this.k.setHorizontalScrollBarEnabled(false);
        this.k.setWebViewClient(new LoginWebViewClient());
        this.k.setWebChromeClient(new WebChromeClient());
        this.k.clearFormData();
        WebSettings settings = this.k.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        if (!(a == null || a.get() == null)) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(((Context) a.get()).getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.k.loadUrl(this.b);
        this.k.setVisibility(4);
        this.k.getSettings().setSavePassword(false);
    }

    private boolean f() {
        AuthMap instance = AuthMap.getInstance();
        String makeKey = instance.makeKey();
        Auth auth = new Auth();
        auth.listener = this.d;
        auth.dialog = this;
        auth.key = makeKey;
        String str = instance.set(auth);
        String substring = this.b.substring(0, this.b.indexOf("?"));
        Bundle parseUrl = Util.parseUrl(this.b);
        parseUrl.putString("token_key", makeKey);
        parseUrl.putString("serial", str);
        parseUrl.putString("browser", "1");
        this.b = substring + "?" + Util.encodeUrl(parseUrl);
        if (a == null || a.get() == null) {
            return false;
        }
        return Util.openBrowser((Context) a.get(), this.b);
    }

    private static void b(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(SocialConstants.PARAM_TYPE);
            Toast.makeText(context.getApplicationContext(), parseJson.getString(SocialConstants.PARAM_SEND_MSG), i).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void callJs(String str, String str2) {
        this.k.loadUrl("javascript:" + str + "(" + str2 + ");void(" + System.currentTimeMillis() + ");");
    }
}
