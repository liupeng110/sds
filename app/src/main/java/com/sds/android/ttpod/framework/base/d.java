package com.sds.android.ttpod.framework.base;

/* CommonResult */
public class d<ResultObj, ResultType> {
    private e a;
    private String b;
    private ResultType c;
    private ResultObj d;

    public d(e eVar, String str) {
        this.a = eVar;
        this.b = str;
    }

    public d(e eVar, String str, ResultObj resultObj) {
        this.a = eVar;
        this.b = str;
        this.d = resultObj;
    }

    public d(e eVar, String str, ResultObj resultObj, ResultType resultType) {
        this.a = eVar;
        this.b = str;
        this.c = resultType;
        this.d = resultObj;
    }

    public e a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public ResultType c() {
        return this.c == null ? null : this.c;
    }

    public ResultObj d() {
        return this.d == null ? null : this.d;
    }

    public String toString() {
        return "ErrCode:" + this.a + ", ErrMessage:" + this.b + ", ResultObj:" + this.d + (this.d != null ? "(" + this.d.getClass() + ")" : "");
    }
}
