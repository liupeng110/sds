package b.a.a.a.a;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* Header */
public class b implements Iterable<f> {
    private final List<f> a = new LinkedList();
    private final Map<String, List<f>> b = new HashMap();

    public void a(f fVar) {
        if (fVar != null) {
            String toLowerCase = fVar.a().toLowerCase(Locale.US);
            List list = (List) this.b.get(toLowerCase);
            if (list == null) {
                list = new LinkedList();
                this.b.put(toLowerCase, list);
            }
            list.add(fVar);
            this.a.add(fVar);
        }
    }

    public f a(String str) {
        if (str == null) {
            return null;
        }
        List list = (List) this.b.get(str.toLowerCase(Locale.US));
        return (list == null || list.isEmpty()) ? null : (f) list.get(0);
    }

    public Iterator<f> iterator() {
        return Collections.unmodifiableList(this.a).iterator();
    }

    public String toString() {
        return this.a.toString();
    }
}
