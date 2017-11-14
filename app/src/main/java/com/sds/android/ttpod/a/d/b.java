package com.sds.android.ttpod.a.d;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.common.b.a.a;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* ShareContentUtil */
public class b {
    private static boolean a;
    private static String b;
    private static String c;

    static {
        a = false;
        try {
            a = new Date().before(new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-15"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        b = null;
        c = null;
    }

    public static void a(String str, String str2) {
        b = str;
        c = str2;
    }

    private static boolean c(String str) {
        return m.a(str, b) && !m.a(c);
    }

    public static boolean a(a aVar, e eVar) {
        return c(b(aVar, eVar));
    }

    public static String b(a aVar, e eVar) {
        if (aVar.q()) {
            return aVar.p();
        }
        if (aVar.k() == a.a.POST) {
            return aVar.m();
        }
        return c(aVar, eVar);
    }

    public static String a(a aVar) {
        String str = "分享";
        if (aVar.k() == a.a.MV) {
            str = str + aVar.h() + "的MV";
        } else if (b(aVar.h())) {
            str = str + aVar.h() + "的单曲";
        }
        str = str + "《";
        if (!TextUtils.isEmpty(aVar.g())) {
            str = str + aVar.g();
        }
        return str + "》";
    }

    public static String b(a aVar) {
        if (!m.a(aVar.m())) {
            return aVar.m();
        }
        if (aVar.i().longValue() > 0) {
            return "http://m.dongting.com/?song_id=" + aVar.i() + "&from=uc";
        }
        return "http://www.dongting.com/#a=searchlist&q=" + URLEncoder.encode(aVar.g());
    }

    public static String a(long j) {
        return "http://pic.ttpod.cn/singerpic" + "/" + (j / 255) + "/" + (j / 7) + "/" + j + "_320.jpg";
    }

    public static String a(a aVar, e eVar, boolean z) {
        String b = b(aVar, eVar);
        if (z && c(b)) {
            b = c;
        }
        if (aVar.q()) {
            b = "分享:" + aVar.f() + " " + b;
        } else if (aVar.k() == a.a.POST) {
            b = "分享歌单《" + aVar.g() + "》:" + b;
        } else {
            b = a(aVar) + a(b);
        }
        if (eVar != e.SINA_WEIBO) {
            return b;
        }
        g.a("ShareContentUtil", "lookFrom %s", aVar.k().name());
        if (aVar.k() != a.a.THIRDPARTY) {
            b = b + "&style=card";
        }
        return b + " (分享自@天天动听，好音质 #天天动听#)";
    }

    public static String a(String str) {
        return m.a(str) ? "" : ":" + str;
    }

    private static String c(a aVar, e eVar) {
        return b(aVar);
    }

    public static String a(String str, String str2, a aVar, e eVar) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!m.a(str)) {
            stringBuilder.append(str);
            stringBuilder.append(" //");
        }
        stringBuilder.append(a(aVar, eVar, false));
        stringBuilder.append(" ");
        if (!a) {
            stringBuilder.append(str2);
        }
        return stringBuilder.toString();
    }

    public static String a(OnlineMediaItem onlineMediaItem) {
        if (onlineMediaItem == null) {
            return "";
        }
        String str = "";
        List downloadUrls = onlineMediaItem.getDownloadUrls();
        if (downloadUrls == null || downloadUrls.size() <= 0) {
            return str;
        }
        return ((Url) downloadUrls.get(0)).getUrl();
    }

    public static boolean b(String str) {
        return (TextUtils.isEmpty(str) || "<unknown>".equalsIgnoreCase(str) || "<unknown>".equalsIgnoreCase(str)) ? false : true;
    }
}
