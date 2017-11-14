package com.sds.android.ttpod.framework.support.search.task;

import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* ReportTask */
public class d implements Runnable {
    private b a;
    private a b;
    private MediaItem c;

    /* ReportTask */
    public enum a {
        REPORT_NO_CONTENT_STATE,
        REPORT_NO_MATCH_STATE,
        REPORT_LOW_QUALITY_STATE
    }

    /* ReportTask */
    public enum b {
        REPORT_TYPE_LYRIC,
        REPORT_TYPE_PICTURE
    }

    public d(b bVar, a aVar, MediaItem mediaItem) {
        this.a = bVar;
        this.b = aVar;
        this.c = mediaItem;
    }

    public void run() {
        Boolean valueOf;
        Boolean.valueOf(false);
        if (b.REPORT_TYPE_LYRIC == this.a) {
            w.a("lyric_pic", "report", "lyric", (long) this.b.ordinal(), this.c.getSongID().longValue(), this.c.getArtist(), this.c.getTitle());
            valueOf = Boolean.valueOf(a("http://lrc.ttpod.com/report?", a()));
        } else {
            w.a("lyric_pic", "report", SocialConstants.PARAM_AVATAR_URI, (long) this.b.ordinal(), this.c.getSongID().longValue(), this.c.getArtist(), this.c.getTitle());
            valueOf = Boolean.valueOf(a("http://picdown.ttpod.cn/picreport?", b()));
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_REPORT, this.a, this.c, valueOf), c.SEARCH);
    }

    protected boolean a(String str, List<? extends NameValuePair> list) {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, (int) TimeUnit.SECONDS.toMillis(5));
        HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
        HttpUriRequest httpPost = new HttpPost(str);
        if (list != null) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        int statusCode = defaultHttpClient.execute(httpPost).getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == SecExceptionCode.SEC_ERROR_STA_STORE_DATA_FILE_MISMATCH) {
            return true;
        }
        return false;
    }

    ArrayList<BasicNameValuePair> a() {
        String str = "";
        ArrayList<BasicNameValuePair> arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("lrcid", ""));
        arrayList.add(new BasicNameValuePair("level", String.valueOf(this.b)));
        arrayList.add(new BasicNameValuePair("ti", ""));
        arrayList.add(new BasicNameValuePair("ar", ""));
        a(arrayList);
        HashMap e = com.sds.android.sdk.lib.util.EnvironmentUtils.b.e();
        for (String str2 : e.keySet()) {
            arrayList.add(new BasicNameValuePair(str2, e.get(str2).toString()));
        }
        return arrayList;
    }

    ArrayList<BasicNameValuePair> b() {
        String str = "";
        ArrayList<BasicNameValuePair> arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("arpic", ""));
        arrayList.add(new BasicNameValuePair("level", String.valueOf(this.b)));
        arrayList.add(new BasicNameValuePair("ar", ""));
        a(arrayList);
        HashMap e = com.sds.android.sdk.lib.util.EnvironmentUtils.b.e();
        for (String str2 : e.keySet()) {
            arrayList.add(new BasicNameValuePair(str2, e.get(str2).toString()));
        }
        return arrayList;
    }

    private void a(ArrayList<BasicNameValuePair> arrayList) {
        arrayList.add(new BasicNameValuePair("title", this.c.getTitle()));
        arrayList.add(new BasicNameValuePair("artist", this.c.getArtist()));
        arrayList.add(new BasicNameValuePair("album", this.c.getAlbum()));
        String j = e.j(this.c.getLocalDataSource());
        arrayList.add(new BasicNameValuePair("filename", j));
        arrayList.add(new BasicNameValuePair("mediatype", j.substring(j.lastIndexOf(46) + 1)));
        arrayList.add(new BasicNameValuePair("duration", String.valueOf(this.c.getDuration())));
        arrayList.add(new BasicNameValuePair("bitrate", String.valueOf(this.c.getBitRate())));
        arrayList.add(new BasicNameValuePair("srate", String.valueOf(this.c.getSampleRate())));
    }
}
