package com.tencent.connect.avatar;

import android.content.Context;
import android.content.Intent;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* ProGuard */
class e implements IUiListener {
    final /* synthetic */ ImageActivity a;

    e(ImageActivity imageActivity) {
        this.a = imageActivity;
    }

    public void onError(UiError uiError) {
        this.a.g.setEnabled(true);
        this.a.g.setTextColor(-1);
        this.a.f.setEnabled(true);
        this.a.f.setTextColor(-1);
        this.a.f.setText("重试");
        this.a.j.setVisibility(8);
        this.a.l = true;
        this.a.a(uiError.errorMessage, 1);
        this.a.a("10660", 0);
    }

    public void onComplete(Object obj) {
        int i;
        this.a.g.setEnabled(true);
        this.a.g.setTextColor(-1);
        this.a.f.setEnabled(true);
        this.a.f.setTextColor(-1);
        this.a.j.setVisibility(8);
        JSONObject jSONObject = (JSONObject) obj;
        try {
            i = jSONObject.getInt("ret");
        } catch (JSONException e) {
            e.printStackTrace();
            i = -1;
        }
        if (i == 0) {
            this.a.a("设置成功", 0);
            this.a.a("10658", 0);
            Context context = this.a;
            if (!(this.a.c == null || "".equals(this.a.c))) {
                Intent intent = new Intent();
                intent.setClassName(context, this.a.c);
                if (context.getPackageManager().resolveActivity(intent, 0) != null) {
                    context.startActivity(intent);
                }
            }
            this.a.a(0, jSONObject.toString(), null, null);
            this.a.d();
            return;
        }
        this.a.a("设置出错了，请重新登录再尝试下呢：）", 1);
    }

    public void onCancel() {
    }
}
