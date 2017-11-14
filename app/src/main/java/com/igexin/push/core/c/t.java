package com.igexin.push.core.c;

enum t {
    NORMAL,
    BACKUP,
    TRY_NORMAL;

    public static t[] a() {
        return (t[]) d.clone();
    }
}
