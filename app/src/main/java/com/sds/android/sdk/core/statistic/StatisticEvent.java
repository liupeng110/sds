package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class StatisticEvent implements Parcelable {
    public static final Creator<StatisticEvent> CREATOR = new Creator<StatisticEvent>() {
        public StatisticEvent createFromParcel(Parcel parcel) {
            try {
                for (Constructor constructor : Class.forName(parcel.readString()).getDeclaredConstructors()) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (1 == parameterTypes.length && parameterTypes[0].equals(Parcel.class)) {
                        return (StatisticEvent) constructor.newInstance(new Object[]{parcel});
                    }
                }
            } catch (Exception e) {
                g.c(StatisticEvent.TAG, "createFromParcel e = " + e.toString());
            }
            return null;
        }

        public StatisticEvent[] newArray(int i) {
            return new StatisticEvent[i];
        }
    };
    static final String KEY_CODE = "key";
    static final String KEY_CONTROL_CODE = "control_code";
    static final String KEY_ENUM_VALUE = "enum_result";
    static final String KEY_LONG_VALUE = "long_result";
    static final String KEY_MODULE = "module";
    static final String KEY_ORIGIN = "origin";
    static final String KEY_STR_VALUE = "str_result";
    static final String KEY_TYPE = "type";
    static final String KEY_VERSION = "v";
    private static final String TAG = "StatisticEvent";
    private int mControlCode = 0;
    private HashMap<String, Object> mDataMap = new HashMap();

    public abstract boolean isCompleted();

    protected StatisticEvent(int i, int i2) {
        this.mControlCode = i2;
        this.mDataMap.put(KEY_CODE, Integer.valueOf(i));
    }

    public int getControlCode() {
        return this.mControlCode;
    }

    public void setControlCode(int i) {
        this.mControlCode = i;
    }

    public boolean isOldVersion() {
        return ((Integer) this.mDataMap.get(KEY_CODE)).intValue() == 0;
    }

    protected StatisticEvent(Parcel parcel) {
        fromParcel(parcel);
    }

    protected StatisticEvent(JSONObject jSONObject) {
        fromJsonObject(jSONObject);
    }

    protected void fromJsonObject(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            if (KEY_CONTROL_CODE.equals(str)) {
                this.mControlCode = jSONObject.optInt(KEY_CONTROL_CODE);
            } else if (KEY_CODE.equals(str)) {
                this.mDataMap.put(str, Integer.valueOf(jSONObject.optInt(KEY_CODE)));
            } else {
                Object opt = jSONObject.opt(str);
                if (opt instanceof Integer) {
                    opt = Long.valueOf(((Integer) opt).longValue());
                }
                this.mDataMap.put(str, opt);
            }
        }
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(KEY_CONTROL_CODE, this.mControlCode);
        for (Entry entry : this.mDataMap.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    public HashMap<String, Object> getDateMap() {
        return this.mDataMap;
    }

    boolean combine(StatisticEvent statisticEvent) {
        return true;
    }

    public boolean equalData(StatisticEvent statisticEvent) {
        if (isOldVersion()) {
            return equalDataCompat(statisticEvent);
        }
        return this.mControlCode == statisticEvent.getControlCode() && this.mDataMap.get(KEY_CODE).equals(statisticEvent.getDateMap().get(KEY_CODE));
    }

    protected boolean equalDataCompat(StatisticEvent statisticEvent) {
        HashMap dateMap = statisticEvent.getDateMap();
        Object obj = this.mDataMap.get(KEY_MODULE);
        if (obj == null || !obj.equals(dateMap.get(KEY_MODULE))) {
            return false;
        }
        obj = this.mDataMap.get("type");
        if (obj == null || !obj.equals(dateMap.get("type"))) {
            return false;
        }
        obj = this.mDataMap.get(KEY_ORIGIN);
        if (obj == null || !obj.equals(dateMap.get(KEY_ORIGIN))) {
            return false;
        }
        obj = this.mDataMap.get(KEY_VERSION);
        if (obj == null || !obj.equals(dateMap.get(KEY_VERSION))) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(KEY_CODE).append(" = ").append(this.mDataMap.get(KEY_CODE)).append(SelectCountryActivity.SPLITTER);
        for (Entry entry : this.mDataMap.entrySet()) {
            stringBuilder.append((String) entry.getKey()).append(" = ").append(entry.getValue()).append(", ");
        }
        return stringBuilder.toString();
    }

    protected void fromParcel(Parcel parcel) {
        this.mControlCode = parcel.readInt();
        this.mDataMap = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getClass().getName());
        parcel.writeInt(this.mControlCode);
        parcel.writeMap(this.mDataMap);
    }

    public int describeContents() {
        return 0;
    }
}
