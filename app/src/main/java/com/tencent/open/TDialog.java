package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.igexin.sdk.PushConsts;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.a;
import com.tencent.open.a.b;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class TDialog extends h {
    static final LayoutParams a = new LayoutParams(-1, -1);
    static Toast b = null;
    private static WeakReference<Context> c;
    private static WeakReference<View> d;
    private static WeakReference<ProgressDialog> e;
    private String f;
    private OnTimeListener g;
    private IUiListener h;
    private FrameLayout i;
    private WebView j;
    private FrameLayout k;
    private ProgressBar l;
    private Handler m;
    private boolean n = false;
    private QQToken o = null;

    /* ProGuard */
    private class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Util.logd("TDialog", "Redirect URL: " + str);
            if (str.startsWith(ServerSetting.getInstance().getEnvUrl((Context) TDialog.c.get(), ServerSetting.DEFAULT_REDIRECT_URI))) {
                TDialog.this.g.onComplete(Util.parseUrlToJson(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith("auth://cancel")) {
                TDialog.this.g.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith("auth://close")) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (!str.startsWith("download://")) {
                return false;
            } else {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(str.substring("download://".length()))));
                if (!(TDialog.c == null || TDialog.c.get() == null)) {
                    ((Context) TDialog.c.get()).startActivity(intent);
                }
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            TDialog.this.g.onError(new UiError(i, str, str2));
            if (!(TDialog.c == null || TDialog.c.get() == null)) {
                Toast.makeText((Context) TDialog.c.get(), "网络连接异常或系统错误", 0).show();
            }
            TDialog.this.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Util.logd("TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (TDialog.d != null && TDialog.d.get() != null) {
                ((View) TDialog.d.get()).setVisibility(0);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (!(TDialog.d == null || TDialog.d.get() == null)) {
                ((View) TDialog.d.get()).setVisibility(8);
            }
            TDialog.this.j.setVisibility(0);
        }
    }

    /* ProGuard */
    private class JsListener extends a {
        private JsListener() {
        }

        public void onAddShare(String str) {
            Log.d("TDialog", "onAddShare");
            onComplete(str);
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onCancelAddShare(String str) {
            Log.d("TDialog", "onCancelAddShare");
            onCancel("cancel");
        }

        public void onCancelLogin() {
            onCancel("");
        }

        public void onCancelInvite() {
            Log.d("TDialog", "onCancelInvite");
            onCancel("");
        }

        public void onComplete(String str) {
            TDialog.this.m.obtainMessage(1, str).sendToTarget();
            Log.e("onComplete", str);
            TDialog.this.dismiss();
        }

        public void onCancel(String str) {
            Log.d("TDialog", "onCancel --msg = " + str);
            TDialog.this.m.obtainMessage(2, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void showMsg(String str) {
            TDialog.this.m.obtainMessage(3, str).sendToTarget();
        }

        public void onLoad(String str) {
            TDialog.this.m.obtainMessage(4, str).sendToTarget();
        }
    }

    /* ProGuard */
    private static class OnTimeListener implements IUiListener {
        private String mAction;
        String mAppid;
        String mUrl;
        private WeakReference<Context> mWeakCtx;
        private IUiListener mWeakL;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.mWeakCtx = new WeakReference(context);
            this.mAction = str;
            this.mUrl = str2;
            this.mAppid = str3;
            this.mWeakL = iUiListener;
        }

        private void onComplete(String str) {
            try {
                onComplete(Util.parseJson(str));
            } catch (JSONException e) {
                e.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }

        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            b.a().a((Context) this.mWeakCtx.get(), this.mAction + "_H5", SystemClock.elapsedRealtime(), 0, 0, jSONObject.optInt("ret", -6), this.mAppid, this.mUrl, "1000067");
            if (this.mWeakL != null) {
                this.mWeakL.onComplete(jSONObject);
                this.mWeakL = null;
            }
        }

        public void onError(UiError uiError) {
            b.a().a((Context) this.mWeakCtx.get(), this.mAction + "_H5", SystemClock.elapsedRealtime(), 0, 0, uiError.errorCode, this.mAppid, uiError.errorMessage != null ? uiError.errorMessage + this.mUrl : this.mUrl, "1000067");
            if (this.mWeakL != null) {
                this.mWeakL.onError(uiError);
                this.mWeakL = null;
            }
        }

        public void onCancel() {
            if (this.mWeakL != null) {
                this.mWeakL.onCancel();
                this.mWeakL = null;
            }
        }
    }

    /* ProGuard */
    private static class THandler extends Handler {
        private OnTimeListener mL;

        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            this.mL = onTimeListener;
        }

        public void handleMessage(Message message) {
            Log.d("TAG", "--handleMessage--msg.WHAT = " + message.what);
            switch (message.what) {
                case 1:
                    this.mL.onComplete((String) message.obj);
                    return;
                case 2:
                    this.mL.onCancel();
                    return;
                case 3:
                    if (TDialog.c != null && TDialog.c.get() != null) {
                        TDialog.c((Context) TDialog.c.get(), (String) message.obj);
                        return;
                    }
                    return;
                case 4:
                    if (TDialog.d != null && TDialog.d.get() != null) {
                        ((View) TDialog.d.get()).setVisibility(8);
                        return;
                    }
                    return;
                case 5:
                    if (TDialog.c != null && TDialog.c.get() != null) {
                        TDialog.d((Context) TDialog.c.get(), (String) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        c = new WeakReference(context);
        this.f = str2;
        this.g = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.m = new THandler(this.g, context.getMainLooper());
        this.h = iUiListener;
        this.o = qQToken;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        c();
        d();
    }

    public void onBackPressed() {
        if (this.g != null) {
            this.g.onCancel();
        }
        super.onBackPressed();
    }

    private void c() {
        this.l = new ProgressBar((Context) c.get());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.l.setLayoutParams(layoutParams);
        new TextView((Context) c.get()).setText("test");
        this.k = new FrameLayout((Context) c.get());
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.bottomMargin = 40;
        layoutParams.leftMargin = 80;
        layoutParams.rightMargin = 80;
        layoutParams.topMargin = 40;
        layoutParams.gravity = 17;
        this.k.setLayoutParams(layoutParams);
        this.k.setBackgroundResource(17301504);
        this.k.addView(this.l);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        this.j = new WebView((Context) c.get());
        this.j.setLayoutParams(layoutParams2);
        this.i = new FrameLayout((Context) c.get());
        layoutParams2.gravity = 17;
        this.i.setLayoutParams(layoutParams2);
        this.i.addView(this.j);
        this.i.addView(this.k);
        d = new WeakReference(this.k);
        setContentView(this.i);
    }

    protected void onConsoleMessage(String str) {
        Log.d("TDialog", "--onConsoleMessage--");
        try {
            this.jsBridge.a(this.j, str);
        } catch (Exception e) {
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.j.setVerticalScrollBarEnabled(false);
        this.j.setHorizontalScrollBarEnabled(false);
        this.j.setWebViewClient(new FbWebViewClient());
        this.j.setWebChromeClient(this.mChromeClient);
        this.j.clearFormData();
        WebSettings settings = this.j.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        try {
            Method method = settings.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
            if (method != null) {
                method.invoke(this.j, new Object[]{"searchBoxJavaBridge_"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!(c == null || c.get() == null)) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(((Context) c.get()).getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.jsBridge.a(new JsListener(), "sdk_js_if");
        this.j.loadUrl(this.f);
        this.j.setLayoutParams(a);
        this.j.setVisibility(4);
        this.j.getSettings().setSavePassword(false);
    }

    private static void c(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(SocialConstants.PARAM_TYPE);
            CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
            if (i == 0) {
                if (b == null) {
                    b = Toast.makeText(context, string, 0);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(0);
                }
                b.show();
            } else if (i == 1) {
                if (b == null) {
                    b = Toast.makeText(context, string, 1);
                } else {
                    b.setView(b.getView());
                    b.setText(string);
                    b.setDuration(1);
                }
                b.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void d(Context context, String str) {
        if (context != null && str != null) {
            try {
                JSONObject parseJson = Util.parseJson(str);
                int i = parseJson.getInt(PushConsts.CMD_ACTION);
                CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
                if (i == 1) {
                    if (e == null) {
                        ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage(string);
                        e = new WeakReference(progressDialog);
                        progressDialog.show();
                        return;
                    }
                    ((ProgressDialog) e.get()).setMessage(string);
                    if (!((ProgressDialog) e.get()).isShowing()) {
                        ((ProgressDialog) e.get()).show();
                    }
                } else if (i == 0 && e != null && e.get() != null && ((ProgressDialog) e.get()).isShowing()) {
                    ((ProgressDialog) e.get()).dismiss();
                    e = null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
