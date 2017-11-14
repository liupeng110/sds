package com.tencent.open;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build.VERSION;
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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.igexin.sdk.PushConsts;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.b;
import com.tencent.open.b.a.a;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class PKDialog extends h implements a {
    private static final int MSG_CANCEL = 2;
    private static final int MSG_COMPLETE = 1;
    private static final int MSG_ON_LOAD = 4;
    private static final int MSG_SHOW_PROCESS = 5;
    private static final int MSG_SHOW_TIPS = 3;
    private static final String TAG = PKDialog.class.getName();
    private static final int WEBVIEW_HEIGHT = 185;
    static Toast sToast = null;
    private static WeakReference<Context> sWeakContext;
    private IUiListener listener;
    private com.tencent.open.b.a mFlMain;
    private Handler mHandler;
    private OnTimeListener mListener;
    private String mUrl;
    private WebView mWebView;
    private int mWebviewHeight;

    /* ProGuard */
    private class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Util.logd(PKDialog.TAG, "Redirect URL: " + str);
            if (str.startsWith(ServerSetting.getInstance().getEnvUrl((Context) PKDialog.sWeakContext.get(), ServerSetting.DEFAULT_REDIRECT_URI))) {
                PKDialog.this.mListener.onComplete(Util.parseUrlToJson(str));
                PKDialog.this.dismiss();
                return true;
            } else if (str.startsWith("auth://cancel")) {
                PKDialog.this.mListener.onCancel();
                PKDialog.this.dismiss();
                return true;
            } else if (!str.startsWith("auth://close")) {
                return false;
            } else {
                PKDialog.this.dismiss();
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            PKDialog.this.mListener.onError(new UiError(i, str, str2));
            if (!(PKDialog.sWeakContext == null || PKDialog.sWeakContext.get() == null)) {
                Toast.makeText((Context) PKDialog.sWeakContext.get(), "网络连接异常或系统错误", 0).show();
            }
            PKDialog.this.dismiss();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Util.logd(PKDialog.TAG, "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            PKDialog.this.mWebView.setVisibility(0);
        }
    }

    /* ProGuard */
    private class JsListener extends a.a {
        private JsListener() {
        }

        public void onComplete(String str) {
            PKDialog.this.mHandler.obtainMessage(1, str).sendToTarget();
            Log.e("onComplete", str);
            PKDialog.this.dismiss();
        }

        public void onCancel(String str) {
            PKDialog.this.mHandler.obtainMessage(2, str).sendToTarget();
            PKDialog.this.dismiss();
        }

        public void showMsg(String str) {
            PKDialog.this.mHandler.obtainMessage(3, str).sendToTarget();
        }

        public void onLoad(String str) {
            PKDialog.this.mHandler.obtainMessage(4, str).sendToTarget();
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
            Log.d("PKDialog", "msg = " + message.what);
            switch (message.what) {
                case 1:
                    this.mL.onComplete((String) message.obj);
                    return;
                case 2:
                    this.mL.onCancel();
                    return;
                case 3:
                    if (PKDialog.sWeakContext != null && PKDialog.sWeakContext.get() != null) {
                        PKDialog.showTips((Context) PKDialog.sWeakContext.get(), (String) message.obj);
                        return;
                    }
                    return;
                case 5:
                    if (PKDialog.sWeakContext != null && PKDialog.sWeakContext.get() != null) {
                        PKDialog.showProcessDialog((Context) PKDialog.sWeakContext.get(), (String) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public PKDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        sWeakContext = new WeakReference(context);
        this.mUrl = str2;
        this.mListener = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.mHandler = new THandler(this.mListener, context.getMainLooper());
        this.listener = iUiListener;
        this.mWebviewHeight = Math.round(185.0f * context.getResources().getDisplayMetrics().density);
        Log.e(TAG, "density=" + context.getResources().getDisplayMetrics().density + "; webviewHeight=" + this.mWebviewHeight);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setSoftInputMode(16);
        getWindow().setSoftInputMode(1);
        createViews();
        initViews();
    }

    private void createViews() {
        this.mFlMain = new com.tencent.open.b.a((Context) sWeakContext.get());
        this.mFlMain.setBackgroundColor(1711276032);
        this.mFlMain.setLayoutParams(new LayoutParams(-1, -1));
        this.mWebView = new WebView((Context) sWeakContext.get());
        this.mWebView.setBackgroundColor(0);
        this.mWebView.setBackgroundDrawable(null);
        if (VERSION.SDK_INT >= 11) {
            try {
                View.class.getMethod("setLayerType", new Class[]{Integer.TYPE, Paint.class}).invoke(this.mWebView, new Object[]{Integer.valueOf(1), new Paint()});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Method method = this.mWebView.getSettings().getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
            if (method != null) {
                method.invoke(this.mWebView, new Object[]{"searchBoxJavaBridge_"});
            }
        } catch (Exception e2) {
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, this.mWebviewHeight);
        layoutParams.addRule(13, -1);
        this.mWebView.setLayoutParams(layoutParams);
        this.mFlMain.addView(this.mWebView);
        this.mFlMain.a(this);
        setContentView(this.mFlMain);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initViews() {
        this.mWebView.setVerticalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.setWebViewClient(new FbWebViewClient());
        this.mWebView.setWebChromeClient(this.mChromeClient);
        this.mWebView.clearFormData();
        WebSettings settings = this.mWebView.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setJavaScriptEnabled(true);
        if (!(sWeakContext == null || sWeakContext.get() == null)) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(((Context) sWeakContext.get()).getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.jsBridge.a(new JsListener(), "sdk_js_if");
        this.mWebView.clearView();
        this.mWebView.loadUrl(this.mUrl);
        this.mWebView.getSettings().setSavePassword(false);
    }

    public void callJs(String str, String str2) {
        this.mWebView.loadUrl("javascript:" + str + "(" + str2 + ")");
    }

    private static void showTips(Context context, String str) {
        try {
            JSONObject parseJson = Util.parseJson(str);
            int i = parseJson.getInt(SocialConstants.PARAM_TYPE);
            CharSequence string = parseJson.getString(SocialConstants.PARAM_SEND_MSG);
            if (i == 0) {
                if (sToast == null) {
                    sToast = Toast.makeText(context, string, 0);
                } else {
                    sToast.setView(sToast.getView());
                    sToast.setText(string);
                    sToast.setDuration(0);
                }
                sToast.show();
            } else if (i == 1) {
                if (sToast == null) {
                    sToast = Toast.makeText(context, string, 1);
                } else {
                    sToast.setView(sToast.getView());
                    sToast.setText(string);
                    sToast.setDuration(1);
                }
                sToast.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void showProcessDialog(Context context, String str) {
        if (context != null && str != null) {
            try {
                JSONObject parseJson = Util.parseJson(str);
                int i = parseJson.getInt(PushConsts.CMD_ACTION);
                parseJson.getString(SocialConstants.PARAM_SEND_MSG);
                if (i != 1) {
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadUrlWithBrowser(String str, String str2, String str3) throws Exception {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1073741824);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(str3));
        if (sWeakContext != null && sWeakContext.get() != null) {
            ((Context) sWeakContext.get()).startActivity(intent);
        }
    }

    public void onKeyboardShown(int i) {
        if (!(sWeakContext == null || sWeakContext.get() == null)) {
            if (i >= this.mWebviewHeight || 2 != ((Context) sWeakContext.get()).getResources().getConfiguration().orientation) {
                this.mWebView.getLayoutParams().height = this.mWebviewHeight;
            } else {
                this.mWebView.getLayoutParams().height = i;
            }
        }
        Log.e(TAG, "keyboard show");
    }

    public void onKeyboardHidden() {
        this.mWebView.getLayoutParams().height = this.mWebviewHeight;
        Log.e(TAG, "keyboard hide");
    }

    protected void onConsoleMessage(String str) {
        Log.d("PKDialog", "--onConsoleMessage--");
        try {
            this.jsBridge.a(this.mWebView, str);
        } catch (Exception e) {
        }
    }
}
