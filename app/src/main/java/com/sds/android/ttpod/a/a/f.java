package com.sds.android.ttpod.a.a;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.a.d.c;
import com.sds.android.ttpod.common.b.a.a;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/* QQWeiboApi */
public class f extends b {
    private String a;
    private int b;

    public f(String str) {
        super(str);
    }

    public i a(a aVar, a aVar2) {
        String a;
        List a2 = a(aVar);
        if (m.a(aVar.d())) {
            a = c.a("https://open.t.qq.com/api/t/add_pic", a2);
        } else {
            a = c.a("https://open.t.qq.com/api/t/add_pic", a2, (NameValuePair[]) b(aVar).toArray(new NameValuePair[0]));
        }
        i iVar = new i();
        if (m.a(a)) {
            iVar.a("result is empty");
        } else {
            try {
                this.b = new JSONObject(a).optInt("ret");
                if (this.b == 0) {
                    iVar.a(1);
                    iVar.a(a);
                } else {
                    iVar.a(a);
                }
            } catch (JSONException e) {
                iVar.a(e.getMessage());
            }
        }
        return iVar;
    }

    private List<NameValuePair> a(a aVar) {
        List<NameValuePair> arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("oauth_version", "2.a"));
        arrayList.add(new BasicNameValuePair("scope", "all"));
        arrayList.add(new BasicNameValuePair("access_token", b()));
        arrayList.add(new BasicNameValuePair("oauth_consumer_key", a()));
        arrayList.add(new BasicNameValuePair("openid", f()));
        arrayList.add(new BasicNameValuePair("format", "json"));
        arrayList.add(new BasicNameValuePair("syncflag", FeedbackItem.STATUS_WAITING));
        arrayList.add(new BasicNameValuePair("content", aVar.f()));
        arrayList.add(new BasicNameValuePair("compatibleflag", FeedbackItem.STATUS_WAITING));
        return arrayList;
    }

    private List<NameValuePair> b(a aVar) {
        List<NameValuePair> arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair(User.KEY_AVATAR, aVar.d()));
        return arrayList;
    }

    public void b(String str) {
        this.a = str;
    }

    public String f() {
        return this.a;
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        b(bundle.getString("openid"));
    }

    public boolean e() {
        if (this.b < 1 || this.b > 4) {
            return super.e();
        }
        return true;
    }

    public String d() {
        return "TENTCANT_WEIBO_TTPOD_TOKEN";
    }
}
