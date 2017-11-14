package com.tencent.connect.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.alibaba.wireless.security.SecExceptionCode;
import com.tencent.a.a.d;
import com.tencent.connect.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.AsynLoadImgBack;
import com.tencent.utils.SystemUtils;
import com.tencent.utils.TemporaryStorage;
import com.tencent.utils.Util;
import java.net.URLEncoder;
import java.util.ArrayList;

/* ProGuard */
public class QzoneShare extends BaseApi {
    public static final String SHARE_TO_QQ_APP_NAME = "appName";
    public static final String SHARE_TO_QQ_AUDIO_URL = "audio_url";
    public static final String SHARE_TO_QQ_EXT_INT = "cflag";
    public static final String SHARE_TO_QQ_EXT_STR = "share_qq_ext_str";
    public static final String SHARE_TO_QQ_IMAGE_LOCAL_URL = "imageLocalUrl";
    public static final String SHARE_TO_QQ_IMAGE_URL = "imageUrl";
    public static final String SHARE_TO_QQ_SITE = "site";
    public static final String SHARE_TO_QQ_SUMMARY = "summary";
    public static final String SHARE_TO_QQ_TARGET_URL = "targetUrl";
    public static final String SHARE_TO_QQ_TITLE = "title";
    public static final String SHARE_TO_QZONE_KEY_TYPE = "req_type";
    public static final int SHARE_TO_QZONE_TYPE_IMAGE = 5;
    public static final int SHARE_TO_QZONE_TYPE_IMAGE_TEXT = 1;
    public static final int SHARE_TO_QZONE_TYPE_NO_TYPE = 0;
    private boolean a = true;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;

    public QzoneShare(Context context, QQToken qQToken) {
        super(context, qQToken);
    }

    public void shareToQzone(final Activity activity, final Bundle bundle, final IUiListener iUiListener) {
        if (bundle == null) {
            iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_NULL_ERROR, null));
            return;
        }
        String string = bundle.getString("title");
        String string2 = bundle.getString("summary");
        Object string3 = bundle.getString("targetUrl");
        ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        Object applicationLable = Util.getApplicationLable(activity);
        if (applicationLable == null) {
            applicationLable = bundle.getString("appName");
        } else if (applicationLable.length() > 20) {
            applicationLable = applicationLable.substring(0, 20) + "...";
        }
        int i = bundle.getInt("req_type");
        switch (i) {
            case 1:
                this.a = true;
                this.b = false;
                this.c = true;
                this.d = false;
                break;
            case 5:
                iUiListener.onError(new UiError(-5, Constants.MSG_SHARE_TYPE_ERROR, null));
                d.a("openSDK_LOG", "shareToQzone() error--end暂不支持纯图片分享到空间，建议使用图文分享");
                return;
            default:
                if (!Util.isEmpty(string) || !Util.isEmpty(string2)) {
                    this.a = true;
                } else if (stringArrayList == null || stringArrayList.size() == 0) {
                    string = "来自" + applicationLable + "的分享";
                    this.a = true;
                } else {
                    this.a = false;
                }
                this.b = false;
                this.c = true;
                this.d = false;
                break;
        }
        if (Util.hasSDCard() || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_5_0) >= 0) {
            String str;
            if (this.a) {
                if (TextUtils.isEmpty(string3)) {
                    iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_NULL_ERROR, null));
                    d.a("openSDK_LOG", "shareToQzone() targetUrl null error--end");
                    return;
                } else if (!Util.isValidUrl(string3)) {
                    iUiListener.onError(new UiError(-5, Constants.MSG_PARAM_TARGETURL_ERROR, null));
                    d.a("openSDK_LOG", "shareToQzone() targetUrl error--end");
                    return;
                }
            }
            if (this.b) {
                bundle.putString("title", "");
                bundle.putString("summary", "");
            } else if (this.c && Util.isEmpty(string)) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_TITLE_NULL_ERROR, null));
                d.a("openSDK_LOG", "shareToQzone() title is null--end");
                return;
            } else {
                if (!Util.isEmpty(string) && string.length() > 200) {
                    bundle.putString("title", Util.subString(string, 200, null, null));
                }
                if (!Util.isEmpty(string2) && string2.length() > SecExceptionCode.SEC_ERROR_SIGNATRUE) {
                    bundle.putString("summary", Util.subString(string2, SecExceptionCode.SEC_ERROR_SIGNATRUE, null, null));
                }
            }
            if (!TextUtils.isEmpty(applicationLable)) {
                bundle.putString("appName", applicationLable);
            }
            if (stringArrayList != null && (stringArrayList == null || stringArrayList.size() != 0)) {
                for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                    str = (String) stringArrayList.get(i2);
                    if (!(Util.isValidUrl(str) || Util.isValidPath(str))) {
                        stringArrayList.remove(i2);
                    }
                }
                if (stringArrayList.size() == 0) {
                    iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_FORMAT_ERROR, null));
                    d.a("openSDK_LOG", "shareToQzone() MSG_PARAM_IMAGE_URL_FORMAT_ERROR--end");
                    return;
                }
                bundle.putStringArrayList("imageUrl", stringArrayList);
            } else if (this.d) {
                iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_ERROR, null));
                d.a("openSDK_LOG", "shareToQzone() imageUrl is null--end");
                return;
            }
            if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
                a.a((Context) activity, stringArrayList, new AsynLoadImgBack(this) {
                    final /* synthetic */ QzoneShare d;

                    public void saved(int i, String str) {
                    }

                    public void batchSaved(int i, ArrayList<String> arrayList) {
                        if (i == 0) {
                            bundle.putStringArrayList("imageUrl", arrayList);
                        }
                        this.d.a(activity, bundle, iUiListener);
                    }
                });
            } else if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_2_0) < 0 || SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
                a((Context) activity, bundle, iUiListener);
            } else {
                QQShare qQShare = new QQShare(activity, this.mToken);
                if (stringArrayList != null && stringArrayList.size() > 0) {
                    str = (String) stringArrayList.get(0);
                    if (i != 5 || Util.fileExists(str)) {
                        bundle.putString("imageLocalUrl", str);
                    } else {
                        iUiListener.onError(new UiError(-6, Constants.MSG_PARAM_IMAGE_URL_MUST_BE_LOCAL, null));
                        d.a("openSDK_LOG", "shareToQzone()手Q版本过低，纯图分享不支持网路图片");
                        return;
                    }
                }
                if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_5_0) >= 0) {
                    bundle.putInt("cflag", 1);
                }
                qQShare.shareToQQ(activity, bundle, iUiListener);
            }
            d.a("openSDK_LOG", "shareToQzone() --end");
            return;
        }
        iUiListener.onError(new UiError(-6, Constants.MSG_SHARE_NOSD_ERROR, null));
        d.a("openSDK_LOG", "shareToQzone() sdcard is null--end");
    }

    private void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        d.a("openSDK_LOG", "doShareToQQ() --start");
        StringBuffer stringBuffer = new StringBuffer("mqqapi://share/to_qzone?src_type=app&version=1&file_type=news");
        ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        Object string = bundle.getString("title");
        Object string2 = bundle.getString("summary");
        Object string3 = bundle.getString("targetUrl");
        String string4 = bundle.getString("audio_url");
        int i = bundle.getInt("req_type", 1);
        Object string5 = bundle.getString("appName");
        int i2 = bundle.getInt("cflag", 0);
        String string6 = bundle.getString("share_qq_ext_str");
        CharSequence appId = this.mToken.getAppId();
        String openId = this.mToken.getOpenId();
        Log.v(SystemUtils.QQ_SHARE_CALLBACK_ACTION, "openId:" + openId);
        if (stringArrayList != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            int size = stringArrayList.size() > 9 ? 9 : stringArrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                stringBuffer2.append(URLEncoder.encode((String) stringArrayList.get(i3)));
                if (i3 != size - 1) {
                    stringBuffer2.append(";");
                }
            }
            stringBuffer.append("&image_url=" + Base64.encodeToString(stringBuffer2.toString().getBytes(), 2));
        }
        if (!TextUtils.isEmpty(string)) {
            stringBuffer.append("&title=" + Base64.encodeToString(string.getBytes(), 2));
        }
        if (!TextUtils.isEmpty(string2)) {
            stringBuffer.append("&description=" + Base64.encodeToString(string2.getBytes(), 2));
        }
        if (!TextUtils.isEmpty(appId)) {
            stringBuffer.append("&share_id=" + appId);
        }
        if (!TextUtils.isEmpty(string3)) {
            stringBuffer.append("&url=" + Base64.encodeToString(string3.getBytes(), 2));
        }
        if (!TextUtils.isEmpty(string5)) {
            stringBuffer.append("&app_name=" + Base64.encodeToString(string5.getBytes(), 2));
        }
        if (!Util.isEmpty(openId)) {
            stringBuffer.append("&open_id=" + Base64.encodeToString(openId.getBytes(), 2));
        }
        if (!Util.isEmpty(string4)) {
            stringBuffer.append("&audioUrl=" + Base64.encodeToString(string4.getBytes(), 2));
        }
        stringBuffer.append("&req_type=" + Base64.encodeToString(String.valueOf(i).getBytes(), 2));
        if (!Util.isEmpty(string6)) {
            stringBuffer.append("&share_qq_ext_str=" + Base64.encodeToString(string6.getBytes(), 2));
        }
        stringBuffer.append("&cflag=" + Base64.encodeToString(String.valueOf(i2).getBytes(), 2));
        Log.v(SystemUtils.QQ_SHARE_CALLBACK_ACTION, stringBuffer.toString());
        a.a(this.mContext, this.mToken, "requireApi", "shareToNativeQQ");
        this.mActivityIntent = new Intent("android.intent.action.VIEW");
        this.mActivityIntent.setData(Uri.parse(stringBuffer.toString()));
        if (SystemUtils.compareQQVersion(activity, SystemUtils.QQ_VERSION_NAME_4_6_0) >= 0) {
            Object obj = TemporaryStorage.set(SystemUtils.QZONE_SHARE_CALLBACK_ACTION, iUiListener);
            if (obj != null) {
                ((IUiListener) obj).onCancel();
            }
            if (hasActivityForIntent()) {
                activity.startActivityForResult(this.mActivityIntent, 0);
            }
        } else if (hasActivityForIntent()) {
            startAssitActivity(activity, iUiListener);
        }
        d.a("openSDK_LOG", "doShareToQQ() --end");
    }

    private void a(Context context, Bundle bundle, IUiListener iUiListener) {
        Object obj = TemporaryStorage.set(SystemUtils.QZONE_SHARE_CALLBACK_ACTION, iUiListener);
        if (obj != null) {
            ((IUiListener) obj).onCancel();
        }
        d.a("openSDK_LOG", "shareToH5Qzone() --start");
        StringBuffer stringBuffer = new StringBuffer("http://openmobile.qq.com/api/check2?page=qzshare.html&loginpage=loginindex.html&logintype=qzone");
        if (bundle == null) {
            bundle = new Bundle();
        }
        stringBuffer = a(stringBuffer, bundle);
        a.a(this.mContext, this.mToken, "requireApi", "shareToH5QQ");
        if (!(Util.openBrowser(context, stringBuffer.toString()) || iUiListener == null)) {
            iUiListener.onError(new UiError(-6, Constants.MSG_OPEN_BROWSER_ERROR, null));
        }
        d.a("openSDK_LOG", "shareToH5QQ() --end");
    }

    private StringBuffer a(StringBuffer stringBuffer, Bundle bundle) {
        d.a("openSDK_LOG", "fillShareToQQParams() --start");
        ArrayList stringArrayList = bundle.getStringArrayList("imageUrl");
        Object string = bundle.getString("appName");
        int i = bundle.getInt("req_type", 1);
        String string2 = bundle.getString("title");
        String string3 = bundle.getString("summary");
        bundle.putString("appId", this.mToken.getAppId());
        bundle.putString("sdkp", "a");
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        String str = "...";
        if (!Util.isEmpty(string2) && string2.length() > 40) {
            bundle.putString("title", string2.substring(0, 40) + "...");
        }
        if (!Util.isEmpty(string3) && string3.length() > 80) {
            bundle.putString("summary", string3.substring(0, 80) + "...");
        }
        if (!TextUtils.isEmpty(string)) {
            bundle.putString("site", string);
        }
        if (stringArrayList != null) {
            int size = stringArrayList.size();
            String[] strArr = new String[size];
            for (int i2 = 0; i2 < size; i2++) {
                strArr[i2] = (String) stringArrayList.get(i2);
            }
            bundle.putStringArray("imageUrl", strArr);
        }
        bundle.putString(SocialConstants.PARAM_TYPE, String.valueOf(i));
        stringBuffer.append("&" + Util.encodeUrl(bundle).replaceAll("\\+", "%20"));
        d.a("openSDK_LOG", "fillShareToQQParams() --end");
        return stringBuffer;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }
}
