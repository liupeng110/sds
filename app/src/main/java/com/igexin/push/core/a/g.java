package com.igexin.push.core.a;

import com.igexin.push.core.bean.l;
import java.util.Comparator;

class g implements Comparator {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public int a(l lVar, l lVar2) {
        return lVar.c() != lVar2.c() ? lVar.c().compareTo(lVar2.c()) : 0;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((l) obj, (l) obj2);
    }
}
