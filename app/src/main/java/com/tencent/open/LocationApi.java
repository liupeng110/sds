package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import com.tencent.a.a.d;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import com.tencent.utils.Util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class LocationApi extends BaseApi implements com.tencent.open.d.a {
    private HandlerThread a;
    private Handler b;
    private Handler c;
    private d d;
    private Bundle e;
    private IUiListener f;

    /* ProGuard */
    private abstract class b implements IRequestListener {
        final /* synthetic */ LocationApi b;

        protected abstract void a(Exception exception);

        private b(LocationApi locationApi) {
            this.b = locationApi;
        }

        public void onIOException(IOException iOException) {
            a(iOException);
        }

        public void onMalformedURLException(MalformedURLException malformedURLException) {
            a(malformedURLException);
        }

        public void onJSONException(JSONException jSONException) {
            a(jSONException);
        }

        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            a(connectTimeoutException);
        }

        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            a(socketTimeoutException);
        }

        public void onNetworkUnavailableException(NetworkUnavailableException networkUnavailableException) {
            a(networkUnavailableException);
        }

        public void onHttpStatusException(HttpStatusException httpStatusException) {
            a(httpStatusException);
        }

        public void onUnknowException(Exception exception) {
            a(exception);
        }
    }

    /* ProGuard */
    private class a extends b {
        final /* synthetic */ LocationApi a;
        private IUiListener c;

        public a(LocationApi locationApi, IUiListener iUiListener) {
            this.a = locationApi;
            super();
            this.c = iUiListener;
        }

        public void onComplete(JSONObject jSONObject) {
            if (this.c != null) {
                this.c.onComplete(jSONObject);
            }
        }

        protected void a(Exception exception) {
            if (this.c != null) {
                this.c.onError(new UiError(100, exception.getMessage(), null));
            }
        }
    }

    public LocationApi(Context context, QQToken qQToken) {
        super(context, qQToken);
        a();
    }

    public LocationApi(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(context, qQAuth, qQToken);
        a();
    }

    private void a() {
        this.d = new d();
        this.a = new HandlerThread("get_location");
        this.a.start();
        this.b = new Handler(this.a.getLooper());
        this.c = new Handler(this, this.mContext.getMainLooper()) {
            final /* synthetic */ LocationApi a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 101:
                        d.b("openSDK_LOG", "location: get location timeout.");
                        this.a.a(-13, Constants.MSG_LOCATION_TIMEOUT_ERROR);
                        break;
                    case SecExceptionCode.SEC_ERROR_INIT_LOADSO_FAIL /*103*/:
                        d.b("openSDK_LOG", "location: verify sosocode success.");
                        this.a.d.a(this.a.mContext, this.a);
                        this.a.c.sendEmptyMessageDelayed(101, 10000);
                        break;
                    case SecExceptionCode.SEC_ERROR_INIT_NO_RSA_FILE_ERROR /*104*/:
                        d.b("openSDK_LOG", "location: verify sosocode failed.");
                        this.a.a(-14, Constants.MSG_LOCATION_VERIFY_ERROR);
                        break;
                }
                super.handleMessage(message);
            }
        };
    }

    public void searchNearby(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (c()) {
            this.e = bundle;
            this.f = iUiListener;
            this.b.post(new Runnable(this) {
                final /* synthetic */ LocationApi a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.d.a()) {
                        Message.obtain(this.a.c, SecExceptionCode.SEC_ERROR_INIT_LOADSO_FAIL).sendToTarget();
                    } else {
                        Message.obtain(this.a.c, SecExceptionCode.SEC_ERROR_INIT_NO_RSA_FILE_ERROR).sendToTarget();
                    }
                }
            });
        } else if (iUiListener != null) {
            iUiListener.onComplete(d());
        }
    }

    public void deleteLocation(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (c()) {
            Bundle bundle2;
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
                bundle2.putAll(composeCGIParams());
            } else {
                bundle2 = composeCGIParams();
            }
            bundle2.putString("appid", this.mToken.getAppId());
            bundle2.putString(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            String str = "encrytoken";
            bundle2.putString(str, Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
            d.b("openSDK_LOG", "location: delete params: " + bundle2);
            HttpUtils.requestAsync(this.mToken, this.mContext, "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi", bundle2, Constants.HTTP_GET, new a(this, iUiListener));
            a("delete_location", "success");
        } else if (iUiListener != null) {
            iUiListener.onComplete(d());
        }
    }

    private void a(Location location) {
        Bundle bundle;
        d.b("openSDK_LOG", "location: search mParams: " + this.e);
        if (this.e != null) {
            bundle = new Bundle(this.e);
            bundle.putAll(composeCGIParams());
        } else {
            bundle = composeCGIParams();
        }
        String valueOf = String.valueOf(location.getLatitude());
        String valueOf2 = String.valueOf(location.getLongitude());
        bundle.putString("appid", this.mToken.getAppId());
        if (!bundle.containsKey(ParamKey.LATITUDE)) {
            bundle.putString(ParamKey.LATITUDE, valueOf);
        }
        if (!bundle.containsKey(ParamKey.LONGITUDE)) {
            bundle.putString(ParamKey.LONGITUDE, valueOf2);
        }
        if (!bundle.containsKey("page")) {
            bundle.putString("page", String.valueOf(1));
        }
        valueOf2 = "encrytoken";
        bundle.putString(valueOf2, Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
        d.b("openSDK_LOG", "location: search params: " + bundle);
        HttpUtils.requestAsync(this.mToken, this.mContext, "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi", bundle, Constants.HTTP_GET, new a(this, this.f));
    }

    private void a(int i, String str) {
        this.d.b();
        if (this.f != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ret", i);
                jSONObject.put("errMsg", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f.onComplete(jSONObject);
        }
    }

    private void b() {
        this.d.b();
    }

    private boolean c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    private JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ret", -9);
            jSONObject.put("errMsg", Constants.MSG_IO_ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void a(final String str, final String... strArr) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ LocationApi c;

            public void run() {
                if (strArr != null && strArr.length != 0) {
                    com.tencent.connect.a.a.a(this.c.mContext, this.c.mToken, "search_nearby".equals(str) ? "id_search_nearby" : "id_delete_location", strArr);
                }
            }
        });
    }

    public void onLocationUpdate(Location location) {
        a(location);
        b();
        this.c.removeMessages(101);
    }
}
