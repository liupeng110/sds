package com.igexin.push.core.d;

import android.content.ContentValues;
import com.igexin.a.b.a;
import com.igexin.push.core.f;
import com.igexin.push.e.a.b;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class g extends b {
    private boolean a = false;

    public g(String str, byte[] bArr, int i, boolean z) {
        super(str);
        this.a = z;
        a(bArr, i);
    }

    private void a(byte[] bArr, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushConsts.CMD_ACTION, "upload_BI");
            jSONObject.put("BIType", String.valueOf(i));
            jSONObject.put("cid", com.igexin.push.core.g.u);
            jSONObject.put("BIData", new String(com.igexin.a.a.b.g.e(bArr, 0), "UTF-8"));
            b(a.b(jSONObject.toString().getBytes()));
        } catch (Exception e) {
        }
    }

    public void a(byte[] bArr) {
        JSONObject jSONObject = new JSONObject(new String(bArr));
        if (this.a && jSONObject.has("result") && jSONObject.getString("result").equals("ok")) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(SocialConstants.PARAM_TYPE, FeedbackItem.STATUS_WAITING);
            f.a().i().a("bi", contentValues, new String[]{SocialConstants.PARAM_TYPE}, new String[]{FeedbackItem.STATUS_SOLVED});
            com.igexin.push.core.c.f.a().c(System.currentTimeMillis());
        }
    }

    public int b() {
        return 0;
    }
}
