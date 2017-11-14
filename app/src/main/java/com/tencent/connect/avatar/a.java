package com.tencent.connect.avatar;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
class a implements IUiListener {
    final /* synthetic */ ImageActivity a;

    a(ImageActivity imageActivity) {
        this.a = imageActivity;
    }

    public void onError(UiError uiError) {
        a(0);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        int i = -1;
        try {
            i = jSONObject.getInt("ret");
            if (i == 0) {
                final String string = jSONObject.getString("nickname");
                this.a.d.post(new Runnable(this) {
                    final /* synthetic */ a b;

                    public void run() {
                        this.b.a.c(string);
                    }
                });
                this.a.a("10659", 0);
            } else {
                this.a.a("10661", 0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (i != 0) {
            a(i);
        }
    }

    public void onCancel() {
    }

    private void a(int i) {
        if (this.a.k < 2) {
            this.a.e();
        }
    }
}
