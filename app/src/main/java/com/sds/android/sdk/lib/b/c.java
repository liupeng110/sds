package com.sds.android.sdk.lib.b;

import com.b.a.c.a;
import com.sds.android.sdk.lib.util.f;
import java.util.ArrayList;

/* DataListResultRest */
public class c<T> extends b {
    private ArrayList<T> a = new ArrayList();

    public ArrayList<T> a() {
        this.a = (ArrayList) f.a(getContent(), new a<ArrayList<T>>(this) {
            final /* synthetic */ c d;

            {
                this.d = r1;
            }
        }.b());
        return this.a;
    }
}
