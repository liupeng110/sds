package com.sds.android.ttpod.component.soundsearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.b.a.a.c;
import com.mradar.sdk.record.DoresoMusicTrack;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.Serializable;

public final class SoundSearchInfo implements Parcelable, Serializable {
    public static final Creator<SoundSearchInfo> CREATOR = new Creator<SoundSearchInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public SoundSearchInfo a(Parcel parcel) {
            return new SoundSearchInfo(parcel);
        }

        public SoundSearchInfo[] a(int i) {
            return new SoundSearchInfo[i];
        }
    };
    public static final SoundSearchInfo a = new SoundSearchInfo();
    @c(a = "md5")
    private String b;
    @c(a = "name")
    private String c;
    @c(a = "artist")
    private String d;
    @c(a = "album")
    private String e;
    @c(a = "rating")
    private double f;
    @c(a = "offset")
    private long g;
    private MediaItem h;

    public String a() {
        return this.b;
    }

    public double b() {
        return this.f;
    }

    public String c() {
        return this.d;
    }

    public long d() {
        return this.g;
    }

    public String e() {
        return this.c;
    }

    public void a(MediaItem mediaItem) {
        this.h = mediaItem;
    }

    public MediaItem f() {
        return this.h;
    }

    public boolean equals(Object obj) {
        return (obj instanceof SoundSearchInfo) && this.b.equals(((SoundSearchInfo) obj).a());
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        return "SoundSearchInfo{mMd5='" + this.b + '\'' + ", mRating=" + this.f + ", mArtist='" + this.d + '\'' + ", mOffset=" + this.g + ", mAlbum='" + this.e + '\'' + ", mName='" + this.c + '\'' + '}';
    }

    private SoundSearchInfo(Parcel parcel) {
        this.c = "";
        this.d = "";
        this.e = "";
        if (parcel != null) {
            this.b = parcel.readString();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readDouble();
            this.g = parcel.readLong();
            this.h = (MediaItem) parcel.readSerializable();
        }
    }

    public SoundSearchInfo() {
        this.c = "";
        this.d = "";
        this.e = "";
    }

    public SoundSearchInfo(DoresoMusicTrack doresoMusicTrack) {
        this.c = "";
        this.d = "";
        this.e = "";
        this.b = doresoMusicTrack.d();
        this.c = doresoMusicTrack.a();
        this.d = doresoMusicTrack.b();
        this.e = doresoMusicTrack.c();
        this.f = doresoMusicTrack.f();
        this.g = doresoMusicTrack.e();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeDouble(this.f);
        parcel.writeLong(this.g);
        parcel.writeSerializable(this.h);
    }
}
