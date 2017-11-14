package com.tencent.open;

import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
public class g implements IRequestListener {
    private IUiListener a;

    public g(IUiListener iUiListener) {
        this.a = iUiListener;
    }

    public void onComplete(JSONObject jSONObject) {
        if (this.a != null) {
            this.a.onComplete(jSONObject);
        }
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

    private void a(Exception exception) {
        if (this.a != null) {
            this.a.onError(new UiError(100, exception.getMessage(), null));
        }
    }
}
