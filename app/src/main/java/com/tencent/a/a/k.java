package com.tencent.a.a;

import java.io.File;
import java.util.Comparator;

/* ProGuard */
class k implements Comparator<File> {
    final /* synthetic */ h a;

    k(h hVar) {
        this.a = hVar;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((File) obj, (File) obj2);
    }

    public int a(File file, File file2) {
        return h.f(file) - h.f(file2);
    }
}
