package com.sds.android.cloudapi.ttpod.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.request.e;

public class UserResult extends e<User> implements Parcelable {
    public static final Creator<UserResult> CREATOR = new Creator<UserResult>() {
        public UserResult createFromParcel(Parcel parcel) {
            return new UserResult(parcel);
        }

        public UserResult[] newArray(int i) {
            return new UserResult[i];
        }
    };

    private UserResult(Parcel parcel) {
        setCode(parcel.readInt());
        setMessage(parcel.readString());
        setData(new User());
        ((User) getData()).setAccessToken(parcel.readString());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getCode());
        parcel.writeString(getMessage());
        parcel.writeString(((User) getData()).getAccessToken());
    }
}
