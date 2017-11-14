package com.ut.mini.core;

import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.core.d.a;
import com.ut.mini.core.e.a.b;
import com.ut.mini.d.m;
import java.util.HashMap;
import java.util.Map;

/* UTMCLogTransferMain */
public class c implements a {
    private static c a = new c();
    private volatile boolean b = false;
    private d c = null;

    private c() {
    }

    public static c a() {
        return a;
    }

    private boolean b() {
        if (com.ut.mini.base.c.a().k() == null) {
            return false;
        }
        b bVar = new b();
        bVar.a(com.ut.mini.core.b.a.a());
        bVar.a(com.ut.mini.core.e.a.a());
        bVar.a();
        this.c = new d();
        this.c.a((a) this);
        this.c.a();
        com.ut.mini.plugin.a.a().a(new com.ut.mini.plugin.a.a(), false);
        return true;
    }

    public void a(Map<String, String> map) {
        if (!this.b && b()) {
            this.b = true;
        }
        if (map == null) {
            b.a().a(null, null);
        } else if (com.ut.mini.base.c.a().k() == null || m.a(com.ut.mini.base.c.a().l())) {
            com.ut.mini.b.a.c(1, "setRequestAuthentication", "Fatal Error,no appkey was setted in RequestAuthentication");
        } else if (this.c != null) {
            this.c.a((Map) map);
        }
    }

    private void c(Map<String, String> map) {
        if (map != null) {
            String a;
            String assemble;
            com.ut.mini.plugin.a.a().a(3, (Object) map);
            if (map.containsKey("_priority")) {
                a = m.a(map.get("_priority"));
            } else {
                a = null;
            }
            Object obj = null;
            if (map.containsKey(com.ut.mini.base.b.AGGREGATION_LOG.toString())) {
                obj = 1;
                map.remove(com.ut.mini.base.b.AGGREGATION_LOG.toString());
            }
            Object obj2 = obj;
            if (obj2 != null) {
                try {
                    obj = new HashMap();
                    obj.putAll(map);
                    Map hashMap = new HashMap();
                    hashMap.putAll(map);
                    obj.putAll(com.ut.mini.core.d.b.disassemble(com.ut.mini.core.d.b.assemble(hashMap)));
                    obj.remove(UTLogFieldsScheme.ARGS.toString());
                    com.ut.mini.plugin.a.a().a(5, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                assemble = com.ut.mini.core.d.b.assemble(map);
                if (!m.a(assemble)) {
                    b.a().a(assemble, a);
                }
            }
            if (obj2 != null && e.a().c()) {
                Map hashMap2 = new HashMap();
                hashMap2.putAll(map);
                assemble = (String) hashMap2.get(UTLogFieldsScheme.EVENTID.toString());
                if (!m.a(assemble)) {
                    hashMap2.remove(UTLogFieldsScheme.EVENTID.toString());
                    hashMap2.put(UTLogFieldsScheme.EVENTID.toString(), "6699");
                    hashMap2.put("_event_id", assemble);
                    assemble = com.ut.mini.core.d.b.assemble(hashMap2);
                    if (!m.a(assemble)) {
                        b.a().a(assemble, a);
                    }
                }
            }
        }
    }

    public void b(Map<String, String> map) {
        c(map);
    }
}
