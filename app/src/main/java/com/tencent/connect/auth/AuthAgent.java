package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sina.weibo.sdk.constant.WBConstants;
import com.taobao.dp.client.b;
import com.tencent.a.a.d;
import com.tencent.connect.a.a;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.ApiTask;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.yyb.TitleBar;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.Util;
import com.ttfm.android.sdk.http.RequestPackage;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class AuthAgent extends BaseApi {
    private IUiListener a;
    private String b;
    private Activity c;
    private IUiListener d = new IUiListener(this) {
        final /* synthetic */ AuthAgent a;

        {
            this.a = r1;
        }

        public void onError(UiError uiError) {
            d.b("openSDK_LOG", "AuthAgent, EncrytokenListener() onError relogin");
            this.a.a();
        }

        public void onComplete(Object obj) {
            if (obj == null) {
                this.a.a();
            } else {
                String str = null;
                try {
                    str = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
                } catch (Throwable e) {
                    e.printStackTrace();
                    d.a("openSDK_LOG", "OpenUi, EncrytokenListener() onComplete error", e);
                }
                if (TextUtils.isEmpty(str)) {
                    d.b("openSDK_LOG", "OpenUi, EncrytokenListener() onComplete relogin");
                    this.a.a();
                } else {
                    d.b("openSDK_LOG", "OpenUi, EncrytokenListener() onComplete validToken");
                    this.a.a(str);
                }
            }
            this.a.writeEncryToken(this.a.mContext);
        }

        public void onCancel() {
        }
    };
    private Handler e = new Handler(this) {
        final /* synthetic */ AuthAgent a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            d.b("openSDK_LOG", "OpenUi, handleMessage msg.what = " + message.what + "");
            if (message.what == 0) {
                int parseInt;
                try {
                    parseInt = Integer.parseInt(((JSONObject) message.obj).getString("ret"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    this.a.a();
                    parseInt = 0;
                }
                if (parseInt == 0) {
                    this.a.a.onComplete((JSONObject) message.obj);
                    return;
                } else {
                    this.a.a();
                    return;
                }
            }
            this.a.a.onError(new UiError(message.what, (String) message.obj, null));
        }
    };

    /* ProGuard */
    private class FeedConfirmListener implements IUiListener {
        IUiListener a;
        final /* synthetic */ AuthAgent b;
        private String c = "sendinstall";
        private String d = "installwording";
        private String e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";

        /* ProGuard */
        private abstract class ButtonListener implements OnClickListener {
            Dialog a;
            final /* synthetic */ FeedConfirmListener b;

            ButtonListener(FeedConfirmListener feedConfirmListener, Dialog dialog) {
                this.b = feedConfirmListener;
                this.a = dialog;
            }
        }

        public FeedConfirmListener(AuthAgent authAgent, IUiListener iUiListener) {
            this.b = authAgent;
            this.a = iUiListener;
        }

        public void onComplete(Object obj) {
            int i = 0;
            if (obj != null) {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject != null) {
                    String string;
                    int i2;
                    String str = "";
                    try {
                        if (jSONObject.getInt(this.c) == 1) {
                            i = 1;
                        }
                        string = jSONObject.getString(this.d);
                        i2 = i;
                    } catch (JSONException e) {
                        JSONException jSONException = e;
                        int i3 = 0;
                        JSONException jSONException2 = jSONException;
                        Toast.makeText(this.b.c, "json error", 1);
                        jSONException2.printStackTrace();
                        String str2 = str;
                        i2 = i3;
                        string = str2;
                    }
                    Object decode = URLDecoder.decode(string);
                    Log.d("TAG", " WORDING = " + decode + "xx");
                    if (i2 != 0 && !TextUtils.isEmpty(decode)) {
                        a(decode, this.a, obj);
                    } else if (this.a != null) {
                        this.a.onComplete(obj);
                    }
                }
            } else if (this.a != null) {
                this.a.onComplete(null);
            }
        }

        private void a(String str, final IUiListener iUiListener, final Object obj) {
            PackageInfo packageInfo;
            Drawable drawable = null;
            Dialog dialog = new Dialog(this.b.c);
            dialog.requestWindowFeature(1);
            PackageManager packageManager = this.b.c.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(this.b.c.getPackageName(), 0);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                packageInfo = null;
            }
            if (packageInfo != null) {
                drawable = packageInfo.applicationInfo.loadIcon(packageManager);
            }
            OnClickListener anonymousClass1 = new ButtonListener(this, dialog) {
                final /* synthetic */ FeedConfirmListener e;

                public void onClick(View view) {
                    this.e.a();
                    if (this.a != null && this.a.isShowing()) {
                        this.a.dismiss();
                    }
                    if (iUiListener != null) {
                        iUiListener.onComplete(obj);
                    }
                }
            };
            OnClickListener anonymousClass2 = new ButtonListener(this, dialog) {
                final /* synthetic */ FeedConfirmListener e;

                public void onClick(View view) {
                    if (this.a != null && this.a.isShowing()) {
                        this.a.dismiss();
                    }
                    if (iUiListener != null) {
                        iUiListener.onComplete(obj);
                    }
                }
            };
            Drawable colorDrawable = new ColorDrawable();
            colorDrawable.setAlpha(0);
            dialog.getWindow().setBackgroundDrawable(colorDrawable);
            dialog.setContentView(a(this.b.c, drawable, str, anonymousClass1, anonymousClass2));
            dialog.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ FeedConfirmListener c;

                public void onCancel(DialogInterface dialogInterface) {
                    if (iUiListener != null) {
                        iUiListener.onComplete(obj);
                    }
                }
            });
            if (this.b.c != null && !this.b.c.isFinishing()) {
                dialog.show();
            }
        }

        private Drawable a(String str, Context context) {
            Drawable createFromStream;
            IOException e;
            try {
                InputStream open = context.getApplicationContext().getAssets().open(str);
                if (open == null) {
                    return null;
                }
                if (str.endsWith(".9.png")) {
                    Bitmap decodeStream = BitmapFactory.decodeStream(open);
                    if (decodeStream == null) {
                        return null;
                    }
                    byte[] ninePatchChunk = decodeStream.getNinePatchChunk();
                    NinePatch.isNinePatchChunk(ninePatchChunk);
                    return new NinePatchDrawable(decodeStream, ninePatchChunk, new Rect(), null);
                }
                createFromStream = Drawable.createFromStream(open, str);
                try {
                    open.close();
                    return createFromStream;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    return createFromStream;
                }
            } catch (IOException e3) {
                IOException iOException = e3;
                createFromStream = null;
                e = iOException;
                e.printStackTrace();
                return createFromStream;
            }
        }

        private View a(Context context, Drawable drawable, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            View relativeLayout = new RelativeLayout(context);
            View imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setId(1);
            int i = (int) (14.0f * f);
            i = (int) (18.0f * f);
            int i2 = (int) (6.0f * f);
            int i3 = (int) (18.0f * f);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (60.0f * f), (int) (60.0f * f));
            layoutParams.addRule(9);
            layoutParams.setMargins(0, i, i2, i3);
            relativeLayout.addView(imageView, layoutParams);
            imageView = new TextView(context);
            imageView.setText(str);
            imageView.setTextSize(14.0f);
            imageView.setGravity(3);
            imageView.setIncludeFontPadding(false);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setLines(2);
            imageView.setId(5);
            imageView.setMinWidth((int) (185.0f * f));
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(6, 1);
            int i4 = (int) (TitleBar.SHAREBTN_RIGHT_MARGIN * f);
            layoutParams2.setMargins(0, 0, (int) (5.0f * f), 0);
            relativeLayout.addView(imageView, layoutParams2);
            imageView = new View(context);
            imageView.setBackgroundColor(Color.rgb(214, 214, 214));
            imageView.setId(3);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams2.addRule(3, 1);
            layoutParams2.addRule(5, 1);
            layoutParams2.addRule(7, 5);
            layoutParams2.setMargins(0, 0, 0, (int) (12.0f * f));
            relativeLayout.addView(imageView, layoutParams2);
            imageView = new LinearLayout(context);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(5, 1);
            layoutParams2.addRule(7, 5);
            layoutParams2.addRule(3, 3);
            View button = new Button(context);
            button.setText("跳过");
            button.setBackgroundDrawable(a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(36, 97, 131));
            button.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            button.setOnClickListener(onClickListener2);
            button.setId(4);
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams3.rightMargin = (int) (14.0f * f);
            layoutParams3.leftMargin = (int) (4.0f * f);
            layoutParams3.weight = 1.0f;
            imageView.addView(button, layoutParams3);
            button = new Button(context);
            button.setText("确定");
            button.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            button.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK));
            button.setBackgroundDrawable(a("buttonPost.png", context));
            button.setOnClickListener(onClickListener);
            layoutParams3 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams3.weight = 1.0f;
            layoutParams3.rightMargin = (int) (4.0f * f);
            imageView.addView(button, layoutParams3);
            relativeLayout.addView(imageView, layoutParams2);
            LayoutParams layoutParams4 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (163.0f * f));
            relativeLayout.setPadding((int) (14.0f * f), 0, (int) (12.0f * f), (int) (12.0f * f));
            relativeLayout.setLayoutParams(layoutParams4);
            relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
            Drawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
            paintDrawable.setCornerRadius(f * 5.0f);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        protected void a() {
            HttpUtils.requestAsync(this.b.mToken, this.b.c, this.e, this.b.composeActivityParams(), Constants.HTTP_POST, null);
        }

        public void onError(UiError uiError) {
            if (this.a != null) {
                this.a.onError(uiError);
            }
        }

        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }
    }

    /* ProGuard */
    private class RequestListener implements IRequestListener {
        final /* synthetic */ AuthAgent a;

        public RequestListener(AuthAgent authAgent) {
            this.a = authAgent;
            d.b("openSDK_LOG", "OpenUi, RequestListener()");
        }

        public void onUnknowException(Exception exception) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onUnknowException", exception);
            Message message = new Message();
            message.what = -6;
            message.obj = exception.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onSocketTimeoutException", socketTimeoutException);
            Message message = new Message();
            message.what = -8;
            message.obj = socketTimeoutException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onNetworkUnavailableException", networkUnavailableException);
            Message message = new Message();
            message.what = -2;
            message.obj = networkUnavailableException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            Message message = new Message();
            message.what = -3;
            message.obj = malformedURLException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onJSONException(JSONException jSONException) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onJSONException", jSONException);
            Message message = new Message();
            message.what = -4;
            message.obj = jSONException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onIOException(IOException iOException) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onIOException", iOException);
            Message message = new Message();
            message.what = -2;
            message.obj = iOException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onHttpStatusException", httpStatusException);
            Message message = new Message();
            message.what = -9;
            message.obj = httpStatusException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            d.a("openSDK_LOG", "OpenUi, RequestListener() onConnectTimeoutException", connectTimeoutException);
            Message message = new Message();
            message.what = -7;
            message.obj = connectTimeoutException.getMessage() + "";
            this.a.e.sendMessage(message);
        }

        public void onComplete(JSONObject jSONObject) {
            d.b("openSDK_LOG", "OpenUi, RequestListener() onComplete");
            Message message = new Message();
            message.what = 0;
            message.obj = jSONObject;
            this.a.e.sendMessage(message);
        }
    }

    /* ProGuard */
    private class TokenListener implements IUiListener {
        final /* synthetic */ AuthAgent a;
        private IUiListener b;
        private boolean c;
        private Context d;

        public TokenListener(AuthAgent authAgent, Context context, IUiListener iUiListener, boolean z, boolean z2) {
            this.a = authAgent;
            this.d = context;
            this.b = iUiListener;
            this.c = z;
            d.b("openSDK_LOG", "OpenUi, TokenListener()");
        }

        public void onComplete(Object obj) {
            d.b("openSDK_LOG", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString("access_token");
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString("openid");
                if (!(string == null || this.a.mToken == null || string3 == null)) {
                    this.a.mToken.setAccessToken(string, string2);
                    this.a.mToken.setOpenId(string3);
                    a.d(this.d, this.a.mToken);
                }
                string = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string != null) {
                    try {
                        this.d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string).commit();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        d.a("openSDK_LOG", "OpenUi, TokenListener() onComplete error", e);
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (Throwable e2) {
                e2.printStackTrace();
                d.a("openSDK_LOG", "OpenUi, TokenListener() onComplete error", e2);
            }
            this.b.onComplete(jSONObject);
            d.f().b();
        }

        public void onError(UiError uiError) {
            d.b("openSDK_LOG", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            d.f().b();
        }

        public void onCancel() {
            d.b("openSDK_LOG", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            d.f().b();
        }
    }

    public AuthAgent(Context context, QQToken qQToken) {
        super(context, qQToken);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener) {
        return doLogin(activity, str, iUiListener, false, false);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z) {
        return doLogin(activity, str, iUiListener, z, false);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, boolean z2) {
        this.b = str;
        this.c = activity;
        this.a = iUiListener;
        if (!z) {
            String accessToken = this.mToken.getAccessToken();
            String openId = this.mToken.getOpenId();
            String appId = this.mToken.getAppId();
            if (!(TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(appId))) {
                Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
                Intent targetActivityIntent2 = getTargetActivityIntent("com.tencent.open.agent.EncryTokenActivity");
                if (targetActivityIntent2 == null || targetActivityIntent == null || targetActivityIntent.getComponent() == null || targetActivityIntent2.getComponent() == null || !targetActivityIntent.getComponent().getPackageName().equals(targetActivityIntent2.getComponent().getPackageName())) {
                    accessToken = Util.encrypt("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(SocialConstants.PARAM_ENCRY_EOKEN, accessToken);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.d.onComplete(jSONObject);
                } else {
                    targetActivityIntent2.putExtra("oauth_consumer_key", appId);
                    targetActivityIntent2.putExtra("openid", openId);
                    targetActivityIntent2.putExtra("access_token", accessToken);
                    targetActivityIntent2.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
                    this.mActivityIntent = targetActivityIntent2;
                    if (hasActivityForIntent()) {
                        startAssitActivity(activity, this.d);
                    }
                }
                return 3;
            }
        }
        if (a(activity, z2)) {
            if (z) {
                Util.reportBernoulli(activity, "10785", 0, this.mToken.getAppId());
            }
            d.b("openSDK_LOG", "OpenUi, showUi, return Constants.UI_ACTIVITY");
            return 1;
        }
        this.a = new FeedConfirmListener(this, this.a);
        return a(z2, this.a);
    }

    private int a(boolean z, IUiListener iUiListener) {
        d.a("openSDK_LOG", "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(this.mContext);
        Bundle composeCGIParams = composeCGIParams();
        if (z) {
            composeCGIParams.putString("isadd", "1");
        }
        composeCGIParams.putString("scope", this.b);
        composeCGIParams.putString("client_id", this.mToken.getAppId());
        if (isOEM) {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + b.OS + "-" + registerChannel + "-" + businessId);
        } else {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000) + "";
        composeCGIParams.putString("sign", SystemUtils.getAppSignatureMD5(this.mContext, str));
        composeCGIParams.putString("time", str);
        composeCGIParams.putString(WBConstants.AUTH_PARAMS_DISPLAY, "mobile");
        composeCGIParams.putString(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "token");
        composeCGIParams.putString(WBConstants.AUTH_PARAMS_REDIRECT_URL, ServerSetting.DEFAULT_REDIRECT_URI);
        composeCGIParams.putString("cancel_display", "1");
        composeCGIParams.putString("switch", "1");
        composeCGIParams.putString("status_userip", Util.getUserIp());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ServerSetting.getInstance().getEnvUrl(this.mContext, ServerSetting.DEFAULT_CGI_AUTHORIZE));
        stringBuilder.append(Util.encodeUrl(composeCGIParams));
        String stringBuilder2 = stringBuilder.toString();
        TokenListener tokenListener = new TokenListener(this, this.mContext, iUiListener, true, false);
        d.b("openSDK_LOG", "OpenUi, showDialog TDialog");
        new AuthDialog(this.c, "action_login", stringBuilder2, tokenListener, this.mToken).show();
        return 2;
    }

    private boolean a(Activity activity, boolean z) {
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        if (targetActivityIntent != null) {
            Bundle composeCGIParams = composeCGIParams();
            if (z) {
                composeCGIParams.putString("isadd", "1");
            }
            composeCGIParams.putString("scope", this.b);
            composeCGIParams.putString("client_id", this.mToken.getAppId());
            if (isOEM) {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-" + b.OS + "-" + registerChannel + "-" + businessId);
            } else {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            composeCGIParams.putString("need_pay", "1");
            composeCGIParams.putString(Constants.KEY_APP_NAME, SystemUtils.getAppName(this.mContext));
            String str = (System.currentTimeMillis() / 1000) + "";
            composeCGIParams.putString("sign", SystemUtils.getAppSignatureMD5(this.mContext, str));
            composeCGIParams.putString("time", str);
            targetActivityIntent.putExtra(Constants.KEY_ACTION, "action_login");
            targetActivityIntent.putExtra(Constants.KEY_PARAMS, composeCGIParams);
            this.mActivityIntent = targetActivityIntent;
            if (hasActivityForIntent()) {
                this.a = new FeedConfirmListener(this, this.a);
                startAssitActivity(activity, this.a);
                return true;
            }
        }
        return false;
    }

    private void a() {
        this.mToken.setAccessToken("", FeedbackItem.STATUS_WAITING);
        this.mToken.setOpenId("");
        doLogin(this.c, this.b, this.a, true);
    }

    private void a(String str) {
        d.b("openSDK_LOG", "OpenUi, EncrytokenListener() validToken()");
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("encrytoken", str);
        HttpUtils.requestAsync(this.mToken, this.mContext, "https://openmobile.qq.com/user/user_login_statis", composeCGIParams, Constants.HTTP_POST, new RequestListener(this));
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void writeEncryToken(Context context) {
        String str = "tencent&sdk&qazxc***14969%%";
        String accessToken = this.mToken.getAccessToken();
        String appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        String str2 = "qzone3.4";
        if (accessToken == null || accessToken.length() <= 0 || appId == null || appId.length() <= 0 || openId == null || openId.length() <= 0) {
            str = null;
        } else {
            str = Util.encrypt(str + accessToken + appId + openId + str2);
        }
        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        try {
            Method method = settings.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
            if (method != null) {
                method.invoke(webView, new Object[]{"searchBoxJavaBridge_"});
            }
        } catch (Exception e) {
        }
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        accessToken = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.mToken.getOpenId() + "_" + this.mToken.getAppId() + "\"]=\"" + str + "\";</script></head><body></body></html>";
        str = ServerSetting.getInstance().getEnvUrl(context, ServerSetting.DEFAULT_LOCAL_STORAGE_URI);
        webView.loadDataWithBaseURL(str, accessToken, RequestPackage.CONTENT_TYPE_TEXT, TTPodFMHttpClient.AppEncode, str);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        IUiListener iUiListener = null;
        for (ApiTask apiTask : this.mTaskList) {
            if (apiTask.mRequestCode == i) {
                iUiListener = apiTask.mListener;
                this.mTaskList.remove(apiTask);
                break;
            }
        }
        if (iUiListener != null) {
            if (i2 == -1) {
                int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    String stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra != null) {
                        try {
                            JSONObject parseJson = Util.parseJson(stringExtra);
                            if (iUiListener == this.a) {
                                Object string = parseJson.getString("access_token");
                                Object string2 = parseJson.getString(Constants.PARAM_EXPIRES_IN);
                                Object string3 = parseJson.getString("openid");
                                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                                    this.mToken.setAccessToken(string, string2);
                                    this.mToken.setOpenId(string3);
                                }
                            }
                            iUiListener.onComplete(parseJson);
                        } catch (Throwable e) {
                            iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                            d.a("openSDK_LOG", "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        d.b("openSDK_LOG", "OpenUi, onActivityResult, onComplete");
                        iUiListener.onComplete(new JSONObject());
                    }
                } else {
                    d.d("openSDK_LOG", "OpenUi, onActivityResult, onError = " + intExtra + "");
                    iUiListener.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            } else {
                d.b("openSDK_LOG", "OpenUi, onActivityResult, Constants.ACTIVITY_CANCEL");
                iUiListener.onCancel();
            }
            d.f().b();
        }
    }
}
