package com.sds.android.ttpod.a.b;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* BaseIUiListener */
public abstract class c implements IUiListener {
    public abstract void a(JSONObject jSONObject);

    public void onComplete(Object obj) {
        if (obj != null) {
            try {
                a(new JSONObject(obj.toString()));
                return;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        onError(new UiError(111111, "接口调用失败", ""));
    }

    public void onError(UiError uiError) {
    }

    public void onCancel() {
    }
}
