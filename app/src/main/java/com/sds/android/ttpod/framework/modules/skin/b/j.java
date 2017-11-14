package com.sds.android.ttpod.framework.modules.skin.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sds.android.ttpod.framework.modules.skin.n;
import java.util.HashMap;

/* SComponent */
public abstract class j<T extends View> extends f {
    private n c;
    protected String f;
    protected int g;
    protected boolean h;
    protected h i;

    abstract T b(Context context, com.sds.android.ttpod.framework.modules.skin.j jVar);

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public j(a aVar, HashMap<String, h> hashMap, int i) {
        super(aVar);
        this.c = a(aVar, i);
        if (this.c == null) {
            throw new NullPointerException("SkinLayoutParams cannot be null.");
        }
        this.g = m.a(aVar.getAttributeValue(null, "Visable"), true) ? 0 : 4;
        this.h = m.a(aVar.getAttributeValue(null, "Enable"), true);
        this.f = aVar.getAttributeValue(null, "OnClick");
        String attributeValue = aVar.getAttributeValue(null, "BackgroundColor");
        if (attributeValue == null) {
            this.i = a((HashMap) hashMap, aVar, "Background");
        } else {
            this.i = new h(attributeValue);
        }
    }

    protected n a(a aVar, int i) {
        return new n(aVar, i);
    }

    public static h a(HashMap<String, h> hashMap, a aVar, String str) {
        String attributeValue = aVar.getAttributeValue(null, str);
        if (TextUtils.isEmpty(attributeValue)) {
            attributeValue = a((HashMap) hashMap, aVar.getAttributeValue(null, "Normal" + str));
        }
        Object a = a((HashMap) hashMap, aVar.getAttributeValue(null, "Pressed" + str));
        Object a2 = a((HashMap) hashMap, aVar.getAttributeValue(null, "Disable" + str));
        Object a3 = a((HashMap) hashMap, aVar.getAttributeValue(null, "Selected" + str));
        Object a4 = a((HashMap) hashMap, aVar.getAttributeValue(null, "Focused" + str));
        if (TextUtils.isEmpty(attributeValue) && TextUtils.isEmpty(a) && TextUtils.isEmpty(a2) && TextUtils.isEmpty(a3) && TextUtils.isEmpty(a4)) {
            return null;
        }
        if (TextUtils.isEmpty(attributeValue)) {
            attributeValue = "#FF000000";
        }
        h hVar = new h(attributeValue);
        if (!TextUtils.isEmpty(a)) {
            hVar.b(a);
        }
        if (!TextUtils.isEmpty(a3)) {
            hVar.e(a3);
        }
        if (!TextUtils.isEmpty(a4)) {
            hVar.d(a4);
        }
        if (!TextUtils.isEmpty(a2)) {
            hVar.c(attributeValue);
            hVar.a(a2);
        }
        hVar.b();
        if (!hVar.i() && hashMap != null && hashMap.containsKey(attributeValue)) {
            return (h) hashMap.get(attributeValue);
        }
        if (hVar.i() && TextUtils.isEmpty(hVar.d())) {
            hVar.a(attributeValue);
        }
        return hVar;
    }

    private static String a(HashMap<String, h> hashMap, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        h hVar = (h) hashMap.get(str);
        if (hVar != null) {
            return hVar.c();
        }
        return str;
    }

    public T c(Context context, com.sds.android.ttpod.framework.modules.skin.j jVar) {
        View b = b(context, jVar);
        b.setLayoutParams(this.c);
        a(context, b, jVar);
        return b;
    }

    void a(Context context, T t, com.sds.android.ttpod.framework.modules.skin.j jVar) {
        t.setEnabled(this.h);
        t.setVisibility(this.g);
        t.setTag(this.a);
        t.setTag(R.id.tag_event_on_click, this.f);
        t.setBackgroundDrawable(d(context, jVar));
    }

    public Drawable d(Context context, com.sds.android.ttpod.framework.modules.skin.j jVar) {
        return jVar.a(context.getResources(), this.i);
    }
}
