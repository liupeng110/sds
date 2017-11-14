package com.tencent.open.yyb;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* ProGuard */
public class ShareModel implements Parcelable {
    public static final Creator<ShareModel> CREATOR = new a();
    public String a;
    public String b;
    public String c;
    public String d;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
