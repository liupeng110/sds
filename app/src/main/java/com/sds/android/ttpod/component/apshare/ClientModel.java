package com.sds.android.ttpod.component.apshare;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.b.a.a.c;

public class ClientModel implements Parcelable {
    public static final Creator<ClientModel> CREATOR = new Creator<ClientModel>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ClientModel a(Parcel parcel) {
            return new ClientModel(parcel);
        }

        public ClientModel[] a(int i) {
            return new ClientModel[i];
        }
    };
    @c(a = "ip")
    private String a;
    @c(a = "name")
    private String b;

    public ClientModel(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public ClientModel(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    public String toString() {
        return "name: " + this.b + ", " + "ip" + ": " + this.a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ClientModel) {
            return ((ClientModel) obj).a().equals(this.a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
