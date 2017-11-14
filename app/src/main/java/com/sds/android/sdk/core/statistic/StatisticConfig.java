package com.sds.android.sdk.core.statistic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class StatisticConfig {
    private HashMap<Integer, Integer> mControlCodeMap = new HashMap();

    public void fromJsonObject(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            this.mControlCodeMap.put(Integer.valueOf(str), Integer.valueOf(jSONObject.optInt(str)));
        }
    }

    public void toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : this.mControlCodeMap.entrySet()) {
            jSONObject.put(((Integer) entry.getKey()).toString(), entry.getValue());
        }
    }

    public HashMap<Integer, Integer> getControlCodeMap() {
        return this.mControlCodeMap;
    }
}
