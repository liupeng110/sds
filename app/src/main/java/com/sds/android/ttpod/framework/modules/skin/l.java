package com.sds.android.ttpod.framework.modules.skin;

import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.skin.b.x;
import java.io.IOException;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParserException;

/* SkinInfoLoader */
public class l extends p implements Runnable {
    private String a;

    public l(String str) {
        this.a = str;
    }

    public void run() {
        b.a().b(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_SKIN_INFO, this.a, a()), c.SKIN);
    }

    public x a() {
        x xVar;
        if (e(this.a)) {
            com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
            Reader k = k();
            if (k != null) {
                try {
                    aVar.setInput(k);
                    aVar.nextTag();
                    aVar.require(2, null, "Theme");
                    xVar = new x(aVar);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    xVar = null;
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                j();
                return xVar;
            }
        }
        xVar = null;
        j();
        return xVar;
    }
}
