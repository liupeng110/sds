package com.sds.android.ttpod.framework.modules.core.audioeffect;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.b.a.a.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import java.io.Serializable;

public class AudioEffectParam implements Parcelable, Serializable {
    public static final Creator<AudioEffectParam> CREATOR = new Creator<AudioEffectParam>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AudioEffectParam a(Parcel parcel) {
            g.a("Parcelable.AudioEffectParam.TAG", "createFromParcel");
            AudioEffectParam audioEffectParam = new AudioEffectParam();
            audioEffectParam.b = parcel.readInt();
            audioEffectParam.c = parcel.readInt();
            audioEffectParam.d = parcel.readInt();
            audioEffectParam.e = parcel.readInt();
            audioEffectParam.f = parcel.readFloat();
            audioEffectParam.g = parcel.readInt() != 0;
            audioEffectParam.h = parcel.readString();
            audioEffectParam.l = parcel.readInt();
            audioEffectParam.a = parcel.readString();
            return audioEffectParam;
        }

        public AudioEffectParam[] a(int i) {
            g.a("Parcelable.AudioEffectParam.TAG", "Person[] newArray(int size)");
            return new AudioEffectParam[i];
        }
    };
    @c(a = "_id")
    private String a = "";
    @c(a = "bass")
    private int b = 0;
    @c(a = "treble")
    private int c = 0;
    @c(a = "virtualizer")
    private int d = 0;
    @c(a = "reverb")
    private int e = 0;
    @c(a = "balance")
    private float f = 0.0f;
    @c(a = "limit")
    private boolean g = false;
    @c(a = "equalizer")
    private String h = new Settings(e.b(), (short) 10, e.b(e.b())).toString();
    @c(a = "isedit")
    private boolean i = false;
    @c(a = "nickname")
    private String j = "";
    @c(a = "style")
    private int k = 0;
    @c(a = "usedeffect")
    private int l = 0;

    public void a(String str) {
        this.j = str;
    }

    public void a(int i) {
        this.k = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.c = i;
    }

    public void d(int i) {
        this.d = i;
    }

    public void e(int i) {
        this.e = i;
    }

    public void a(float f) {
        this.f = f;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void b(String str) {
        this.h = str;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public float e() {
        return this.f;
    }

    public boolean f() {
        return this.g;
    }

    public String g() {
        return this.h;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public void f(int i) {
        this.l = i;
    }

    public int h() {
        return this.l;
    }

    public String i() {
        return this.a;
    }

    public void c(String str) {
        this.a = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeFloat(this.f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeString(this.h);
        parcel.writeInt(this.l);
        parcel.writeString(this.a);
    }
}
