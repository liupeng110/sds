package com.igexin.push.core;

/* synthetic */ class j {
    static final /* synthetic */ int[] a = new int[k.a().length];
    static final /* synthetic */ int[] b = new int[l.a().length];

    static {
        try {
            b[l.DETECT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            b[l.STABLE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            b[l.PENDING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[k.HEARTBEAT_OK.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[k.HEARTBEAT_TIMEOUT.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[k.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[k.NETWORK_SWITCH.ordinal()] = 4;
        } catch (NoSuchFieldError e7) {
        }
    }
}
