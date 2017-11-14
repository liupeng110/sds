package com.tencent.open.yyb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.igexin.sdk.PushConsts;
import com.tencent.a.a.d;
import com.tencent.connect.common.Constants;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.Util;
import java.io.IOException;
import java.io.InputStream;

/* ProGuard */
public class b {

    /* ProGuard */
    public static class a {
        public String a;
        public String b;
        public long c;
    }

    /* ProGuard */
    private static class b extends AsyncTask<Bundle, Void, Void> {
        private b() {
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Bundle[]) objArr);
        }

        protected Void a(Bundle... bundleArr) {
            if (bundleArr != null) {
                try {
                    d.b("openSDK_LOG", "-->(ViaAsyncTask)doInBackground : ret = " + Util.parseJson(HttpUtils.openUrl2(null, "http://analy.qq.com/cgi-bin/mapp_apptrace", Constants.HTTP_GET, bundleArr[0]).response).getInt("ret"));
                } catch (Exception e) {
                    d.b("openSDK_LOG", "-->(ViaAsyncTask)doInBackground : Exception = " + e.toString());
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            String str5 = null;
            if (Uri.parse(str).getHost().toLowerCase().endsWith(".qq.com")) {
                str5 = ".qq.com";
            }
            instance.setCookie(str, b("logintype", "MOBILEQ", str5));
            instance.setCookie(str, b("qopenid", str2, str5));
            instance.setCookie(str, b("qaccesstoken", str3, str5));
            instance.setCookie(str, b("openappid", str4, str5));
            CookieSyncManager.getInstance().sync();
        }
    }

    private static String b(String str, String str2, String str3) {
        String str4 = str + "=" + str2;
        if (str3 == null) {
            return str4;
        }
        return (str4 + "; path=/") + "; domain=" + str3;
    }

    public static Drawable a(String str, Context context) {
        return a(str, context, new Rect(0, 0, 0, 0));
    }

    public static Drawable a(String str, Context context, Rect rect) {
        InputStream open;
        Drawable ninePatchDrawable;
        IOException e;
        InputStream inputStream;
        Throwable th;
        Context applicationContext = context.getApplicationContext();
        try {
            open = applicationContext.getAssets().open(str);
            if (open != null) {
                try {
                    if (str.endsWith(".9.png")) {
                        Bitmap decodeStream = BitmapFactory.decodeStream(open);
                        if (decodeStream != null) {
                            ninePatchDrawable = new NinePatchDrawable(applicationContext.getResources(), decodeStream, decodeStream.getNinePatchChunk(), rect, null);
                        } else if (open == null) {
                            return null;
                        } else {
                            try {
                                open.close();
                                return null;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                return null;
                            }
                        }
                    }
                    ninePatchDrawable = Drawable.createFromStream(open, str);
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    inputStream = open;
                    try {
                        e2.printStackTrace();
                        d.b("openSDK_LOG", "-->(AppbarUtil)getDrawable : IOException");
                        if (inputStream == null) {
                            ninePatchDrawable = null;
                        } else {
                            try {
                                inputStream.close();
                                ninePatchDrawable = null;
                            } catch (IOException e22) {
                                e22.printStackTrace();
                                ninePatchDrawable = null;
                            }
                        }
                        return ninePatchDrawable;
                    } catch (Throwable th2) {
                        th = th2;
                        open = inputStream;
                        if (open != null) {
                            try {
                                open.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (open != null) {
                        open.close();
                    }
                    throw th;
                }
                return ninePatchDrawable;
            } else if (open == null) {
                return null;
            } else {
                try {
                    open.close();
                    return null;
                } catch (IOException e222) {
                    e222.printStackTrace();
                    return null;
                }
            }
        } catch (IOException e5) {
            e222 = e5;
            inputStream = null;
            e222.printStackTrace();
            d.b("openSDK_LOG", "-->(AppbarUtil)getDrawable : IOException");
            if (inputStream == null) {
                inputStream.close();
                ninePatchDrawable = null;
            } else {
                ninePatchDrawable = null;
            }
            return ninePatchDrawable;
        } catch (Throwable th4) {
            th = th4;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    public static void a(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("uin", "1000");
        bundle.putString(PushConsts.CMD_ACTION, str2);
        bundle.putString("appid", str);
        bundle.putString("via", str3);
        new b().execute(new Bundle[]{bundle});
    }
}
