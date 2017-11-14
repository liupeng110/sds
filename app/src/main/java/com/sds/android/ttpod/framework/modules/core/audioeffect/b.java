package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;

/* AudioEffectID */
public class b {
    private long a = 0;
    private String b = "";
    private String c = "";
    private short[] d = new short[]{(short) 0};
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private float i = 0.0f;
    private boolean j = false;

    public b(long j, String str, String str2, short[] sArr, int i, int i2, int i3, int i4, float f, boolean z) {
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = sArr;
        this.e = i;
        this.f = i2;
        this.g = i3;
        this.h = i4;
        this.i = f;
        this.j = z;
    }

    public void a(long j, String str, String str2, a aVar) {
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = aVar.n();
        this.e = aVar.h();
        this.f = aVar.i();
        this.g = aVar.j();
        this.h = aVar.k();
        this.i = aVar.l();
        this.j = aVar.m();
    }

    public String a() {
        String str = "Equalizer:{";
        for (int i = 0; i < this.d.length; i = (short) (i + 1)) {
            str = str + "Level" + i + ":" + this.d[i] + SelectCountryActivity.SPLITTER;
        }
        return com.sds.android.sdk.lib.util.k.b.b("UserId:" + this.a + " " + "SongName:" + this.b + " " + "SingerName:" + this.c + " " + "Bass:" + this.e + " " + "Treble:" + this.f + " " + "Virtualizer:" + this.g + " " + "Reverb:" + this.h + " " + "Balance:" + this.i + " " + "Islimit:" + this.j + " " + (str + "}"));
    }
}
