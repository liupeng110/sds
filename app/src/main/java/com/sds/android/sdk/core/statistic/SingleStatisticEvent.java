package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import com.sds.android.sdk.lib.util.c;
import com.sds.android.sdk.lib.util.m;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

public class SingleStatisticEvent extends StatisticEvent {
    static final /* synthetic */ boolean $assertionsDisabled = (!SingleStatisticEvent.class.desiredAssertionStatus());
    private static final String KEY_OPT_MESSAGE = "optmessage";
    private static final String KEY_OPT_MESSAGE2 = "optmessage2";
    private static final String KEY_OPT_VALUE = "optvalue";
    private static final String KEY_OPT_VALUE2 = "optvalue2";
    private static final String KEY_TIME = "time";
    private static final String KEY_VALUE = "value";
    private static final String TAG = "SingleStatisticEvent";

    private SingleStatisticEvent(String str, String str2, String str3, int i, int i2, int i3) {
        this(0, StatisticHelper.DELAY_SEND);
        HashMap dateMap = getDateMap();
        dateMap.put("module", str);
        dateMap.put(SocialConstants.PARAM_TYPE, str2);
        dateMap.put("origin", str3);
        dateMap.put("v", Long.valueOf(Integer.valueOf(i2).longValue()));
        dateMap.put(KEY_VALUE, Long.valueOf(Integer.valueOf(i).longValue()));
        dateMap.put(KEY_TIME, c.a(i3));
    }

    public static SingleStatisticEvent instance(String str, String str2, String str3, int i, int i2, int i3) {
        if ($assertionsDisabled || (str != null && str2 != null && str3 != null && i > 0)) {
            return new SingleStatisticEvent(str, str2, str3, i, i2, i3);
        }
        throw new AssertionError();
    }

    public void setOptValue(long j) {
        getDateMap().put(KEY_OPT_VALUE, Long.valueOf(j));
    }

    public void setOptValue2(long j) {
        getDateMap().put(KEY_OPT_VALUE2, Long.valueOf(j));
    }

    public void setOptMessage(String str) {
        getDateMap().put(KEY_OPT_MESSAGE, str);
    }

    public void setOptMessage2(String str) {
        getDateMap().put(KEY_OPT_MESSAGE2, str);
    }

    public SingleStatisticEvent(int i, int i2) {
        super(i, i2);
    }

    public SingleStatisticEvent(Parcel parcel) {
        super(parcel);
    }

    public SingleStatisticEvent(JSONObject jSONObject) {
        super(jSONObject);
    }

    public boolean equalData(StatisticEvent statisticEvent) {
        if (this == statisticEvent || !super.equalData(statisticEvent) || getClass() != statisticEvent.getClass()) {
            return false;
        }
        if (isOldVersion()) {
            return equalDataVersionCompat(statisticEvent);
        }
        HashMap dateMap = getDateMap();
        if (dateMap.size() != statisticEvent.getDateMap().size()) {
            return false;
        }
        boolean z = false;
        for (Entry entry : dateMap.entrySet()) {
            boolean a;
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                a = m.a((String) value, (String) statisticEvent.getDateMap().get(str));
            } else if ((value instanceof Integer) || (value instanceof Long)) {
                a = value.equals(statisticEvent.getDateMap().get(str));
            } else {
                a = z;
            }
            if (!a) {
                return false;
            }
            z = a;
        }
        return z;
    }

    private boolean equalDataVersionCompat(StatisticEvent statisticEvent) {
        HashMap dateMap = getDateMap();
        if (!dateMap.get(KEY_TIME).equals(statisticEvent.getDateMap().get(KEY_TIME))) {
            return false;
        }
        if (!dateMap.get(KEY_OPT_VALUE).equals(statisticEvent.getDateMap().get(KEY_OPT_VALUE))) {
            return false;
        }
        if (!dateMap.get(KEY_OPT_VALUE2).equals(statisticEvent.getDateMap().get(KEY_OPT_VALUE2))) {
            return false;
        }
        if (m.a((String) dateMap.get(KEY_OPT_MESSAGE), (String) statisticEvent.getDateMap().get(KEY_OPT_MESSAGE))) {
            return m.a((String) dateMap.get(KEY_OPT_MESSAGE2), (String) statisticEvent.getDateMap().get(KEY_OPT_MESSAGE2));
        }
        return false;
    }

    public boolean combine(StatisticEvent statisticEvent) {
        if (isOldVersion() || $assertionsDisabled || !equalData(statisticEvent)) {
            super.combine(statisticEvent);
            if (isOldVersion()) {
                combineVersionCompat(statisticEvent);
            }
            return true;
        }
        throw new AssertionError("data not equal when combine");
    }

    private void combineVersionCompat(StatisticEvent statisticEvent) {
        getDateMap().put(KEY_VALUE, Long.valueOf(((Long) getDateMap().get(KEY_VALUE)).longValue() + ((Long) statisticEvent.getDateMap().get(KEY_VALUE)).longValue()));
    }

    public static SingleStatisticEvent instance(int i, int i2) {
        if ($assertionsDisabled || StatisticHelper.isKVByKeyCode(i)) {
            return new SingleStatisticEvent(i, i2);
        }
        throw new AssertionError("keyCode is not compatible for SingleStatisticEvent");
    }

    public boolean isCompleted() {
        return true;
    }

    public void put(String str, String str2) {
        put(str, (Object) str2);
    }

    public void put(String str, long j) {
        put(str, Long.valueOf(j));
    }

    private void put(String str, Object obj) {
        getDateMap().put(str, obj);
    }
}
