package c.a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* UEKV */
public class b extends m implements ch {
    public b(String str, Map<String, Object> map, long j, int i) {
        a(str);
        b(System.currentTimeMillis());
        if (map.size() > 0) {
            a(b(map));
        }
        if (i <= 0) {
            i = 1;
        }
        a(i);
        if (j > 0) {
            a(j);
        }
    }

    private HashMap<String, x> b(Map<String, Object> map) {
        Iterator it = map.entrySet().iterator();
        HashMap<String, x> hashMap = new HashMap();
        int i = 0;
        while (i < 10 && it.hasNext()) {
            Entry entry = (Entry) it.next();
            x xVar = new x();
            Object value = entry.getValue();
            if (value instanceof String) {
                xVar.a((String) value);
            } else if (value instanceof Long) {
                xVar.a(((Long) value).longValue());
            } else if (value instanceof Integer) {
                xVar.a(((Integer) value).longValue());
            } else if (value instanceof Float) {
                xVar.a(((Float) value).longValue());
            } else if (value instanceof Double) {
                xVar.a(((Double) value).longValue());
            }
            if (xVar.e()) {
                hashMap.put((String) entry.getKey(), xVar);
                i++;
            }
        }
        return hashMap;
    }

    public void a(ae aeVar, String str) {
        if (aeVar.b() > 0) {
            for (t tVar : aeVar.c()) {
                if (str.equals(tVar.a())) {
                    break;
                }
            }
        }
        t tVar2 = null;
        if (tVar2 == null) {
            tVar2 = new t();
            tVar2.a(str);
            aeVar.a(tVar2);
        }
        tVar2.a((m) this);
    }
}
