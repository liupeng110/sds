package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.b.a.a.c;
import java.io.Serializable;

/* AudioEffectCache */
public class a implements Serializable {
    @c(a = "_ID")
    private String a = "";
    @c(a = "song_id")
    private long b = 0;
    @c(a = "artist")
    private String c = "";
    @c(a = "title")
    private String d = "";
    @c(a = "style")
    private int e = 0;
    @c(a = "output")
    private int f = 0;
    @c(a = "device")
    private String g = "";
    @c(a = "total")
    private int h = 0;
    @c(a = "pick_cout")
    private int i = 0;
    @c(a = "nickname")
    private String j = "";
    @c(a = "pic")
    private String k = "";
    @c(a = "user_id")
    private long l = 0;
    @c(a = "bass")
    private int m = 0;
    @c(a = "treble")
    private int n = 0;
    @c(a = "virtualizer")
    private int o = 0;
    @c(a = "reverb")
    private int p = 0;
    @c(a = "balance")
    private float q = 0.0f;
    @c(a = "limit")
    private boolean r = false;
    @c(a = "equalizer")
    private short[] s = new short[]{(short) 0};
    @c(a = "time")
    private long t = 0;
    @c(a = "media_path")
    private String u = "";

    public void a(String str) {
        this.a = str;
    }

    public String a() {
        return this.a;
    }

    public void a(Long l) {
        this.b = l.longValue();
    }

    public Long b() {
        return Long.valueOf(this.b);
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public void a(int i) {
        this.e = i;
    }

    public int e() {
        return this.e;
    }

    public void b(int i) {
        this.f = i;
    }

    public int f() {
        return this.f;
    }

    public void d(String str) {
        this.g = str;
    }

    public void c(int i) {
        this.h = i;
    }

    public void d(int i) {
        this.i = i;
    }

    public void e(String str) {
        this.k = str;
    }

    public void f(String str) {
        this.j = str;
    }

    public String g() {
        return this.j;
    }

    public void a(long j) {
        this.l = j;
    }

    public void e(int i) {
        this.m = i;
    }

    public int h() {
        return this.m;
    }

    public void f(int i) {
        this.n = i;
    }

    public int i() {
        return this.n;
    }

    public void g(int i) {
        this.o = i;
    }

    public int j() {
        return this.o;
    }

    public void h(int i) {
        this.p = i;
    }

    public int k() {
        return this.p;
    }

    public void a(float f) {
        this.q = f;
    }

    public float l() {
        return this.q;
    }

    public void a(boolean z) {
        this.r = z;
    }

    public boolean m() {
        return this.r;
    }

    public void a(short[] sArr) {
        this.s = sArr;
    }

    public short[] n() {
        return this.s;
    }

    public void b(long j) {
        this.t = j;
    }

    public long o() {
        return this.t;
    }

    public void g(String str) {
        this.u = str;
    }

    public String p() {
        return this.u;
    }
}
