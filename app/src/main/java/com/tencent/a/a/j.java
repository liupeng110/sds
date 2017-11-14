package com.tencent.a.a;

import java.io.File;
import java.io.FileFilter;

/* ProGuard */
class j implements FileFilter {
    final /* synthetic */ h a;

    j(h hVar) {
        this.a = hVar;
    }

    public boolean accept(File file) {
        if (file.getName().endsWith(this.a.j()) && h.f(file) != -1) {
            return true;
        }
        return false;
    }
}
