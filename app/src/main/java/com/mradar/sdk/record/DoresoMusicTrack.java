package com.mradar.sdk.record;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DoresoMusicTrack implements Parcelable {
    public static final Creator<DoresoMusicTrack> CREATOR = new Creator<DoresoMusicTrack>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DoresoMusicTrack a(Parcel parcel) {
            DoresoMusicTrack doresoMusicTrack = new DoresoMusicTrack();
            doresoMusicTrack.a(parcel.readString());
            doresoMusicTrack.b(parcel.readString());
            doresoMusicTrack.c(parcel.readString());
            doresoMusicTrack.d(parcel.readString());
            doresoMusicTrack.b(parcel.readLong());
            doresoMusicTrack.a(parcel.readDouble());
            doresoMusicTrack.c(parcel.readLong());
            return doresoMusicTrack;
        }

        public DoresoMusicTrack[] a(int i) {
            return new DoresoMusicTrack[i];
        }
    };
    private long a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long f;
    private double g;
    private long h;

    public void a(long j) {
        this.a = j;
    }

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public long e() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }

    public double f() {
        return this.g;
    }

    public void a(double d) {
        this.g = d;
    }

    public void c(long j) {
        this.h = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeLong(this.f);
        parcel.writeDouble(this.g);
        parcel.writeLong(this.h);
    }
}
