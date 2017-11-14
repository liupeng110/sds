package com.ut.mini.internal;

import com.ut.mini.core.e.a;
import com.ut.mini.plugin.UTMCPlugin;
import java.util.Map;

public class UTTeamWork {
    public static final String DEBUG_API_URL = "debug_api_url";
    public static final String DEBUG_KEY = "debug_key";
    private static UTTeamWork s_instance = null;

    public static synchronized UTTeamWork getInstance() {
        UTTeamWork uTTeamWork;
        synchronized (UTTeamWork.class) {
            if (s_instance == null) {
                s_instance = new UTTeamWork();
            }
            uTTeamWork = s_instance;
        }
        return uTTeamWork;
    }

    public void initialized() {
    }

    public void turnOnRealTimeDebug(Map<String, String> map) {
        a.a().a(map);
    }

    public void turnOffRealTimeDebug() {
        a.a().b();
    }

    public void registerPlugin(UTPlugin uTPlugin) {
        com.ut.mini.plugin.a.a().a((UTMCPlugin) uTPlugin, true);
    }

    public void unregisterPlugin(UTPlugin uTPlugin) {
        com.ut.mini.plugin.a.a().a(uTPlugin);
    }
}
