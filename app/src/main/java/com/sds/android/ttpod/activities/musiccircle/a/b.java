package com.sds.android.ttpod.activities.musiccircle.a;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;

/* Checker */
public abstract class b implements p {
    private a a;
    private boolean b = false;
    private BaseResult c;

    /* Checker */
    protected interface a {
        void a(b bVar, boolean z, BaseResult baseResult);
    }

    protected abstract boolean a(BaseResult baseResult);

    protected abstract void e();

    protected b(a aVar) {
        this.a = aVar;
    }

    protected boolean a() {
        return this.b;
    }

    protected BaseResult b() {
        return this.c;
    }

    protected void c() {
        this.b = false;
        this.c = null;
    }

    public void onRequestSuccess(BaseResult baseResult) {
        this.c = baseResult;
        this.b = a(baseResult);
        this.a.a(this, this.b, baseResult);
    }

    public void onRequestFailure(BaseResult baseResult) {
        this.c = baseResult;
        this.b = false;
        this.a.a(this, this.b, baseResult);
    }

    protected void d() {
        if (this.b) {
            this.a.a(this, this.b, this.c);
        } else {
            e();
        }
    }
}
