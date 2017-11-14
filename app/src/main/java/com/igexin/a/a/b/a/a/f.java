package com.igexin.a.a.b.a.a;

import java.util.Comparator;

class f implements Comparator {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public int a(g gVar, g gVar2) {
        return gVar == null ? 1 : gVar2 == null ? -1 : ((long) gVar.K) + gVar.I <= ((long) gVar2.K) + gVar2.I ? ((long) gVar.K) + gVar.I < ((long) gVar2.K) + gVar2.I ? -1 : 0 : 1;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((g) obj, (g) obj2);
    }
}
