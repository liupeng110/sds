package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import java.util.HashMap;
import org.json.JSONObject;

public final class KVStatisticEvent extends StatisticEvent {
    static final /* synthetic */ boolean $assertionsDisabled = (!KVStatisticEvent.class.desiredAssertionStatus());

    public enum ValueOperateMode {
        ADD,
        UNIQUE,
        DEFAULT
    }

    public boolean isCompleted() {
        return true;
    }

    public KVStatisticEvent(JSONObject jSONObject) {
        super(jSONObject);
    }

    public KVStatisticEvent(Parcel parcel) {
        super(parcel);
    }

    private KVStatisticEvent(int i, int i2) {
        super(i, i2);
    }

    public static KVStatisticEvent instance(int i, int i2) {
        if ($assertionsDisabled || !StatisticHelper.isKVByKeyCode(i)) {
            return new KVStatisticEvent(i, i2);
        }
        throw new AssertionError("keyCode is not compatible for KVStatisticEvent");
    }

    public boolean equalData(StatisticEvent statisticEvent) {
        if (this == statisticEvent) {
            return false;
        }
        if (getClass() != statisticEvent.getClass()) {
            return false;
        }
        if (!super.equalData(statisticEvent)) {
            return false;
        }
        HashMap dateMap = getDateMap();
        if (dateMap.size() != statisticEvent.getDateMap().size()) {
            return false;
        }
        for (String containsKey : dateMap.keySet()) {
            if (!statisticEvent.getDateMap().containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public boolean combine(StatisticEvent statisticEvent) {
        if ($assertionsDisabled || !equalData(statisticEvent)) {
            HashMap dateMap = getDateMap();
            for (String str : dateMap.keySet()) {
                if (str.contains(ValueOperateMode.UNIQUE.name())) {
                    return false;
                }
                if (!(!str.contains(ValueOperateMode.ADD.name()) || dateMap.get(str) == null || statisticEvent.getDateMap().get(str) == null)) {
                    dateMap.put(str, Long.valueOf(((Long) dateMap.get("long_result_" + ValueOperateMode.ADD.name())).longValue() + ((Long) statisticEvent.getDateMap().get("long_result_" + ValueOperateMode.ADD.name())).longValue()));
                }
            }
            return true;
        }
        throw new AssertionError("data not equal when combine");
    }

    public void setAddLongValue(long j) {
        setLongValue(j, ValueOperateMode.ADD);
    }

    public void setLongValue(long j) {
        setLongValue(j, ValueOperateMode.DEFAULT);
    }

    public void setLongValue(long j, ValueOperateMode valueOperateMode) {
        put("long_result_" + valueOperateMode.name(), Long.valueOf(j));
    }

    public void setStringValue(String str) {
        setStringValue(str, ValueOperateMode.DEFAULT);
    }

    public void setStringValue(String str, ValueOperateMode valueOperateMode) {
        put("str_result_" + valueOperateMode.name(), str);
    }

    public void setEnumValue(Enum enumR) {
        setEnumValue(enumR, ValueOperateMode.DEFAULT);
    }

    public void setEnumValue(Enum enumR, ValueOperateMode valueOperateMode) {
        put("enum_result_" + valueOperateMode.name(), Integer.valueOf(enumR.ordinal()));
    }

    private void put(String str, Object obj) {
        if ($assertionsDisabled || getDateMap().size() > 2) {
            getDateMap().put(str, obj);
            return;
        }
        throw new AssertionError("KVStatisticEvent data can more than two");
    }
}
