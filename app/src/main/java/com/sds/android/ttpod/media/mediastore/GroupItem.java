package com.sds.android.ttpod.media.mediastore;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import java.io.Serializable;

public class GroupItem implements Parcelable, Serializable {
    public static final Creator<GroupItem> CREATOR = new Creator<GroupItem>() {
        public GroupItem createFromParcel(Parcel parcel) {
            return new GroupItem(parcel);
        }

        public GroupItem[] newArray(int i) {
            return new GroupItem[i];
        }
    };
    private Integer mCount;
    private String mGroupID;
    private String mName;
    private char mNameIndexKey;

    private GroupItem(Parcel parcel) {
        this.mName = parcel.readString();
        this.mGroupID = parcel.readString();
        this.mCount = Integer.valueOf(parcel.readInt());
        this.mNameIndexKey = buildIndexKey(this.mName, this.mGroupID);
    }

    public GroupItem(String str, String str2, Integer num) {
        this.mName = str;
        this.mGroupID = str2;
        this.mCount = num;
        this.mNameIndexKey = buildIndexKey(this.mName, this.mGroupID);
    }

    public String getName() {
        return this.mName;
    }

    public char getNameIndexKey() {
        return this.mNameIndexKey;
    }

    private char buildIndexKey(String str, String str2) {
        if (str == null || m.a(str, "<unknown>")) {
            return '#';
        }
        if (str2.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            str = e.j(str);
        }
        String buildKey = PinyinUtils.buildKey(str);
        if (!m.a(buildKey)) {
            char charAt = buildKey.charAt(0);
            if (charAt >= 'A' && charAt <= 'Z') {
                return charAt;
            }
        }
        return '#';
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public Integer getCount() {
        return this.mCount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GroupItem groupItem = (GroupItem) obj;
        if (this.mGroupID != null) {
            if (this.mGroupID.equals(groupItem.mGroupID)) {
                return true;
            }
        } else if (groupItem.mGroupID == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.mGroupID != null ? this.mGroupID.hashCode() : 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mGroupID);
        parcel.writeInt(this.mCount.intValue());
    }
}
