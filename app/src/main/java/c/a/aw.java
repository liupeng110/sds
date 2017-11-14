package c.a;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* FieldMetaData */
public class aw implements Serializable {
    private static Map<Class<? extends an>, Map<? extends as, aw>> d = new HashMap();
    public final String a;
    public final byte b;
    public final ax c;

    public aw(String str, byte b, ax axVar) {
        this.a = str;
        this.b = b;
        this.c = axVar;
    }

    public static void a(Class<? extends an> cls, Map<? extends as, aw> map) {
        d.put(cls, map);
    }
}
