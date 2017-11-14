package com.taobao.dp.util;

import java.util.Map;
import org.json.JSONObject;

public final class d {
    public static String a(Map map) {
        return new JSONObject(map).toString();
    }
}
