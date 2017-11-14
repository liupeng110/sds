package com.tencent.open;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* ProGuard */
public class a {
    HashMap<String, a> a = new HashMap();

    /* ProGuard */
    public static class a {
        public void call(String str, List<String> list, b bVar) {
            Method method = null;
            for (Method method2 : getClass().getDeclaredMethods()) {
                if (method2.getName().equals(str) && method2.getParameterTypes().length == list.size()) {
                    method = method2;
                    break;
                }
            }
            if (method != null) {
                try {
                    Object invoke;
                    switch (list.size()) {
                        case 0:
                            invoke = method.invoke(this, new Object[0]);
                            break;
                        case 1:
                            invoke = method.invoke(this, new Object[]{list.get(0)});
                            break;
                        case 2:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1)});
                            break;
                        case 3:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2)});
                            break;
                        case 4:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3)});
                            break;
                        case 5:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4)});
                            break;
                        default:
                            invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)});
                            break;
                    }
                    if (method.getReturnType() == Void.class) {
                        if (bVar != null) {
                            bVar.a(null);
                        }
                    } else if (bVar != null && customCallback()) {
                        bVar.a(invoke.toString());
                        return;
                    } else {
                        return;
                    }
                } catch (IllegalAccessException e) {
                    if (bVar != null) {
                        bVar.a();
                    }
                } catch (InvocationTargetException e2) {
                    if (bVar != null) {
                        bVar.a();
                    }
                } catch (Exception e3) {
                    if (bVar != null) {
                        bVar.a();
                    }
                }
            }
            if (bVar != null) {
                bVar.a();
            }
        }

        public boolean customCallback() {
            return false;
        }
    }

    /* ProGuard */
    public static class b {
        WeakReference<WebView> a;
        long b;
        String c;

        public b(WebView webView, long j, String str) {
            this.a = new WeakReference(webView);
            this.b = j;
            this.c = str;
        }

        public void a(Object obj) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                String str = "'undefined'";
                if (obj instanceof String) {
                    str = "'" + ((String) obj).replace("\\", "\\\\").replace("'", "\\'") + "'";
                } else if ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float)) {
                    str = obj.toString();
                } else if (obj instanceof Boolean) {
                    str = obj.toString();
                }
                webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':0,'result':" + str + "});");
            }
        }

        public void a() {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:window.JsBridge&&JsBridge.callback(" + this.b + ",{'r':1,'result':'no such method'})");
            }
        }

        public void a(String str) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl("javascript:" + str);
            }
        }
    }

    public void a(a aVar, String str) {
        this.a.put(str, aVar);
    }

    public void a(String str, String str2, List<String> list, b bVar) {
        Log.d("TDialog", "getResult---objName = " + str + " methodName = " + str2);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode((String) list.get(i), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        a aVar = (a) this.a.get(str);
        if (aVar != null) {
            Log.d("TDialog", "call----");
            aVar.call(str2, list, bVar);
            return;
        }
        Log.d("TDialog", "not call----objName NOT FIND");
        if (bVar != null) {
            bVar.a();
        }
    }

    public boolean a(WebView webView, String str) {
        Log.d("Dialog", "canHandleUrl---url = " + str);
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(2);
        String str3 = (String) arrayList.get(3);
        List subList = arrayList.subList(4, arrayList.size() - 1);
        b bVar = new b(webView, 4, str);
        webView.getUrl();
        a(str2, str3, subList, bVar);
        return true;
    }
}
