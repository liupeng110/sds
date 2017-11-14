package com.sds.android.sdk.core.statistic;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashMap;

public class SEvent implements Parcelable {
    public static final Creator<SEvent> CREATOR = new Creator<SEvent>() {
        public SEvent createFromParcel(Parcel parcel) {
            return new SEvent(parcel);
        }

        public SEvent[] newArray(int i) {
            return new SEvent[i];
        }
    };
    protected static final String FIELD_ACTION = "action";
    public static final String FIELD_DEEP = "deep";
    protected static final String FIELD_EVENT = "event";
    public static final String FIELD_INDEX = "index";
    public static final String FIELD_PAGE = "page";
    public static final String FIELD_SESSION_ID = "session_id";
    protected static final String FIELD_TIMESTAMP = "timestamp";
    public static final String FIELD_TO = "to";
    private HashMap<String, Object> mDataMap = new HashMap();
    private boolean mPageParam;
    private SPostStrategy mPostStrategy = SPostStrategy.NORMAL;
    private long mTimestamp;

    public SEvent(String str, Object obj) {
        if (str == null) {
            throw new IllegalArgumentException("eventType can't == null!");
        } else if (obj == null) {
            throw new IllegalArgumentException("action can't == null!");
        } else {
            this.mTimestamp = System.currentTimeMillis();
            append("timestamp", Long.valueOf(this.mTimestamp));
            append("event", str);
            append("action", obj);
        }
    }

    public SEvent(Parcel parcel) {
        boolean z = true;
        this.mTimestamp = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt < SPostStrategy.END.ordinal()) {
            this.mPostStrategy = SPostStrategy.values()[readInt];
        } else {
            this.mPostStrategy = SPostStrategy.NORMAL;
        }
        this.mDataMap = parcel.readHashMap(HashMap.class.getClassLoader());
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.mPageParam = z;
    }

    public String toString() {
        return "SEvent{mTimestamp=" + this.mTimestamp + ", mDataMap=" + this.mDataMap + ", mPostStrategy=" + this.mPostStrategy + '}';
    }

    public void setPostStrategy(SPostStrategy sPostStrategy) {
        this.mPostStrategy = sPostStrategy;
    }

    public SPostStrategy getPostStrategy() {
        return this.mPostStrategy;
    }

    public long timestamp() {
        return this.mTimestamp;
    }

    public SEvent append(String str, Object obj) {
        this.mDataMap.put(str, obj);
        return this;
    }

    public SEvent append(HashMap<String, Object> hashMap) {
        this.mDataMap.putAll(hashMap);
        return this;
    }

    public HashMap<String, Object> getDataMap() {
        return this.mDataMap;
    }

    public void setPageParameter(boolean z) {
        this.mPageParam = z;
    }

    public boolean getPageParameter() {
        return this.mPageParam;
    }

    public void post() {
        SEngine.post(this);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTimestamp);
        parcel.writeInt(this.mPostStrategy.ordinal());
        parcel.writeMap(this.mDataMap);
        parcel.writeInt(this.mPageParam ? 0 : 1);
    }
}
