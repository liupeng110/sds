package com.tencent.open.yyb;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* ProGuard */
class a implements Creator<ShareModel> {
    a() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public ShareModel a(Parcel parcel) {
        ShareModel shareModel = new ShareModel();
        shareModel.a = parcel.readString();
        shareModel.b = parcel.readString();
        shareModel.c = parcel.readString();
        shareModel.d = parcel.readString();
        return shareModel;
    }

    public ShareModel[] a(int i) {
        return null;
    }
}
