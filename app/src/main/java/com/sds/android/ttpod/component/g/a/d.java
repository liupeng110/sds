package com.sds.android.ttpod.component.g.a;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.ttpod.component.g.a.a.c;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView.a;

/* PanelViewController */
public class d extends c {
    private e a;

    public d(Context context, String str) {
        super(context, str);
    }

    public void a(e eVar) {
        this.a = eVar;
    }

    public e c() {
        return this.a;
    }

    public View a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.lastIndexOf(46) <= 0) {
            return super.a(str);
        }
        if (this.a == null) {
            return null;
        }
        return this.a.a(str);
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.lastIndexOf(46) <= 0) {
                super.b(str);
            } else if (this.a != null) {
                this.a.b(str);
            }
        }
    }

    protected void d() {
        if (this.K != null && !this.K.b()) {
            this.K.setDisplayMode(a.Single);
            this.K.setSlowScroll(false);
        }
    }

    public int e() {
        return this.a == null ? 0 : this.a.e();
    }

    public int f() {
        return this.a == null ? 0 : this.a.f();
    }
}
