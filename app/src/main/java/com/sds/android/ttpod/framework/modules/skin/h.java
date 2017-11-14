package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.d.i;

/* OnlineCategoryListDownloader */
public class h extends i {
    public h(String str, String str2, a aVar) {
        super(null, str, str2, aVar);
    }

    public void run() {
        String str = this.a + ".tmp";
        if (a(this.b, str) && !TextUtils.isEmpty(this.a)) {
            e.h(this.a);
        }
        e.c(str, this.a);
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(this.c, new Object[0]), c.SKIN);
    }
}
