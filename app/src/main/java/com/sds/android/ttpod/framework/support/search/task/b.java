package com.sds.android.ttpod.framework.support.search.task;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.support.search.a.a;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/* LyricSearchTask */
public class b extends a {
    private com.sds.android.ttpod.framework.support.search.a.b a;

    protected /* synthetic */ a b() {
        return e();
    }

    public b(com.sds.android.ttpod.framework.support.search.a.b bVar) {
        this.a = bVar;
    }

    protected com.sds.android.ttpod.framework.support.search.a.b e() {
        return this.a;
    }

    protected boolean a(ArrayList<String> arrayList) {
        return true;
    }

    protected String b(MediaItem mediaItem) {
        return null;
    }

    protected ArrayList<ResultData> a(com.sds.android.ttpod.framework.modules.search.a.a aVar) throws Exception {
        ArrayList<ResultData> arrayList = new ArrayList();
        if (e().i() == null) {
            return arrayList;
        }
        aVar.nextTag();
        aVar.require(2, null, "lrc_list");
        int next;
        do {
            next = aVar.next();
            switch (next) {
                case 2:
                    if ("lrc".equals(aVar.getName())) {
                        String str;
                        ResultData resultData = new ResultData();
                        resultData.a = aVar.getAttributeValue(null, "title");
                        resultData.b = aVar.getAttributeValue(null, "artist");
                        if (e().i().isOnline()) {
                            str = null;
                        } else {
                            str = f();
                        }
                        if (str == null) {
                            str = com.sds.android.ttpod.framework.a.s() + File.separator + b(resultData.b, resultData.a);
                        }
                        String attributeValue = aVar.getAttributeValue(null, "lrcID");
                        int parseInt = Integer.parseInt(aVar.getAttributeValue(null, "trc"));
                        String str2 = str + (parseInt == 0 ? ".lrc" : ".trc");
                        Item[] itemArr = new Item[1];
                        itemArr[0] = new Item(parseInt == 0 ? "lrc" : "trc", a(resultData.a, resultData.b, attributeValue), str2, Integer.parseInt(attributeValue));
                        resultData.e = itemArr;
                        arrayList.add(resultData);
                        if (this.a.e()) {
                            break;
                        }
                        return arrayList;
                    }
                    aVar.a();
                    continue;
                default:
                    break;
            }
        } while (next != 1);
        return arrayList;
    }

    private String f() {
        String b = b(null, null);
        String[] h = e().h();
        if (b == null) {
            b = e.o(h[1]);
            if (h[3] != null) {
                b = b + "_" + h[3];
            }
        }
        return com.sds.android.ttpod.framework.a.s() + File.separator + b;
    }

    private String a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("http://lrc.ttpod.com/down?");
            stringBuilder.append("lrcid=");
            stringBuilder.append(URLEncoder.encode(str3, "UTF-8"));
            stringBuilder.append("&code=");
            stringBuilder.append(TTTextUtils.decryptLyricKey(str, str2, Integer.parseInt(str3)));
            stringBuilder.append("&s=");
            stringBuilder.append(com.sds.android.sdk.lib.util.EnvironmentUtils.b.b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    protected void a(List<ResultData> list) {
        a(((ResultData) list.get(0)).c()[0], true);
    }

    protected String a(MediaItem mediaItem) {
        return null;
    }

    protected String a() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        MediaItem i2 = this.a.i();
        try {
            stringBuilder.append("http://lrc.ttpod.com/search?");
            String title = i2.getTitle();
            if (this.a.e() && !TextUtils.isEmpty(this.a.f())) {
                title = this.a.f();
            }
            if (!TTTextUtils.isValidateMediaString(title)) {
                title = "";
            }
            stringBuilder.append("title=");
            stringBuilder.append(URLEncoder.encode(title, "UTF-8"));
            e().c(title);
            title = i2.getArtist();
            if (this.a.e() && !TextUtils.isEmpty(this.a.g())) {
                title = this.a.g();
            }
            if (!TTTextUtils.isValidateMediaString(title)) {
                title = "";
            }
            stringBuilder.append("&artist=");
            stringBuilder.append(URLEncoder.encode(title, "UTF-8"));
            e().d(title);
            if (!i2.isOnline()) {
                stringBuilder.append("&filename=");
                stringBuilder.append(URLEncoder.encode(this.a.h()[1], "UTF-8"));
            }
            stringBuilder.append("&duration=");
            stringBuilder.append(i2.getDuration());
            stringBuilder.append("&bitrate=");
            stringBuilder.append(i2.getBitRate());
            stringBuilder.append("&srate=");
            stringBuilder.append(i2.getSampleRate());
            if (!this.a.e() && i2.isOnline()) {
                stringBuilder.append("&song_id=");
                stringBuilder.append(i2.getSongID());
                long artistID = i2.getArtistID();
                if (artistID != 0) {
                    stringBuilder.append("&singer_id=");
                    stringBuilder.append(artistID);
                }
            }
            stringBuilder.append("&raw=2");
            stringBuilder.append("&trc=1");
            stringBuilder.append("&auto=");
            if (this.a.e()) {
                i = 0;
            }
            stringBuilder.append(i);
            stringBuilder.append("&s=");
            stringBuilder.append(com.sds.android.sdk.lib.util.EnvironmentUtils.b.b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private String b(String str, String str2) {
        String str3;
        MediaItem i = this.a.i();
        CharSequence artist = i.getArtist();
        String title = i.getTitle();
        boolean isValidateMediaString = TTTextUtils.isValidateMediaString(title);
        boolean isValidateMediaString2 = TTTextUtils.isValidateMediaString(artist);
        boolean isValidateMediaString3 = TTTextUtils.isValidateMediaString(str2);
        boolean isValidateMediaString4 = TTTextUtils.isValidateMediaString(str);
        if (!isValidateMediaString) {
            str3 = isValidateMediaString3 ? str + " - " + str2 : null;
        } else if (isValidateMediaString2 || isValidateMediaString4) {
            StringBuilder stringBuilder = new StringBuilder();
            if (isValidateMediaString2) {
                str = artist;
            }
            str3 = stringBuilder.append(str).append(" - ").append(title).toString();
        } else {
            str3 = title;
        }
        return e.o(str3);
    }

    protected ArrayList<String> a(String str, String str2) {
        String str3;
        Object a;
        boolean z = true;
        String[] h = this.a.h();
        MediaItem i = e().i();
        if (i.isOnline()) {
            str3 = null;
        } else {
            str3 = h[1];
            if (h[3] != null) {
                str3 = str3 + '_' + h[3];
            }
        }
        String o = e.o(str);
        String o2 = TTTextUtils.isValidateMediaString(str2) ? e.o(str2) : null;
        if (i.getStartTime() == null || i.getStartTime().intValue() <= 0) {
            z = false;
        }
        if (i.isOnline()) {
            a = a(com.sds.android.ttpod.framework.a.s() + File.separator, str3, o, o2, ".trc", z);
            if (a == null) {
                a = a(com.sds.android.ttpod.framework.a.s() + File.separator, str3, o, o2, ".lrc", z);
            }
        } else {
            a = a(h[0], str3, o, o2, ".trc", z);
            if (a == null) {
                a = a(com.sds.android.ttpod.framework.a.s() + File.separator, str3, o, o2, ".trc", z);
            }
            if (a == null) {
                a = a(h[0], str3, o, o2, ".lrc", z);
            }
            if (a == null) {
                a = a(com.sds.android.ttpod.framework.a.s() + File.separator, str3, o, o2, ".lrc", z);
            }
        }
        if (a == null) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(a);
        return arrayList;
    }

    private String a(String str, String str2, String str3, String str4, String str5, boolean z) {
        String str6;
        if (!(str2 == null || z)) {
            str6 = str + str2 + str5;
            if (new File(str6).exists()) {
                return str6;
            }
        }
        str6 = str + (TextUtils.isEmpty(str4) ? "" : str4 + " - ") + (TextUtils.isEmpty(str3) ? "" : str3) + str5;
        if (new File(str6).exists()) {
            return str6;
        }
        str6 = str + (TextUtils.isEmpty(str4) ? "" : str4 + "-") + (TextUtils.isEmpty(str3) ? "" : str3) + str5;
        if (new File(str6).exists()) {
            return str6;
        }
        str6 = str + (TextUtils.isEmpty(str3) ? "" : str3 + " - ") + (TextUtils.isEmpty(str4) ? "" : str4) + str5;
        if (new File(str6).exists()) {
            return str6;
        }
        StringBuilder append = new StringBuilder().append(str).append(TextUtils.isEmpty(str3) ? "" : str3 + "-");
        if (TextUtils.isEmpty(str4)) {
            str4 = "";
        }
        str6 = append.append(str4).append(str5).toString();
        return !new File(str6).exists() ? null : str6;
    }
}
