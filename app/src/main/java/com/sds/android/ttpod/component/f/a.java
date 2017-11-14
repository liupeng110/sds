package com.sds.android.ttpod.component.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;
import com.sds.android.ttpod.framework.a.c;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.j;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParserException;

/* SceneFactory */
public class a {
    public static Renderer a(Context context, j jVar) {
        Renderer renderer = null;
        if (jVar != null) {
            jVar.g();
            try {
                Reader cVar = new c(new ByteArrayInputStream(jVar.d("/scene.xml")));
                com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
                aVar.setInput(cVar);
                aVar.nextTag();
                aVar.require(2, null, "Scene");
                if ("Snow".equals(aVar.getAttributeValue(null, "Name"))) {
                    renderer = a(context, jVar, aVar);
                } else {
                    jVar.j();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jVar.j();
            }
        }
        return renderer;
    }

    private static b a(Context context, j jVar, com.sds.android.ttpod.framework.modules.search.a.a aVar) throws IOException, XmlPullParserException {
        b bVar = new b(context);
        String attributeValue = aVar.getAttributeValue(null, "SnowTexture");
        if (attributeValue != null) {
            Bitmap c = jVar.c(attributeValue);
            if (c != null) {
                bVar.a(c);
            }
        }
        bVar.a(m.a(aVar.getAttributeValue(null, "WindSpeed"), bVar.h()));
        bVar.b(m.a(aVar.getAttributeValue(null, "Gravity"), bVar.i()));
        bVar.c(m.a(aVar.getAttributeValue(null, "MinStartXSpeed"), bVar.b()), m.a(aVar.getAttributeValue(null, "MaxStartXSpeed"), bVar.c()));
        bVar.b(m.a(aVar.getAttributeValue(null, "MinStartYSpeed"), bVar.d()), m.a(aVar.getAttributeValue(null, "MaxStartYSpeed"), bVar.e()));
        bVar.a(m.a(aVar.getAttributeValue(null, "MinStartZ"), bVar.f()), m.a(aVar.getAttributeValue(null, "MaxStartZ"), bVar.g()));
        bVar.a(m.a(aVar.getAttributeValue(null, "SnowNumber"), bVar.a()));
        return bVar;
    }
}
