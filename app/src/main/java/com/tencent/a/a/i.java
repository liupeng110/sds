package com.tencent.a.a;

import java.io.File;
import java.io.FileFilter;

/* ProGuard */
class i implements FileFilter {
    i() {
    }

    public boolean accept(File file) {
        if (file.isDirectory() && h.a(file) > 0) {
            return true;
        }
        return false;
    }
}
