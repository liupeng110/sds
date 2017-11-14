package com.a.a.a;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* Constants */
public class a {
    public static a a = a.Original;
    static Map<String, Integer> b = Collections.synchronizedMap(new HashMap());

    /* Constants */
    public enum a {
        Original,
        Send
    }

    static {
        b.put("initCount", Integer.valueOf(0));
    }
}
