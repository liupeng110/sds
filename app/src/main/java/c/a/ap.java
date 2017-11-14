package c.a;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* TBaseHelper */
public final class ap {
    private static final Comparator a = new a();

    /* TBaseHelper */
    private static class a implements Comparator {
        private a() {
        }

        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof List) {
                return ap.a((List) obj, (List) obj2);
            }
            if (obj instanceof Set) {
                return ap.a((Set) obj, (Set) obj2);
            }
            if (obj instanceof Map) {
                return ap.a((Map) obj, (Map) obj2);
            }
            if (obj instanceof byte[]) {
                return ap.a((byte[]) obj, (byte[]) obj2);
            }
            return ap.a((Comparable) obj, (Comparable) obj2);
        }
    }

    public static int a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        if (b2 < b) {
            return 1;
        }
        return 0;
    }

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        if (i2 < i) {
            return 1;
        }
        return 0;
    }

    public static int a(byte[] bArr, byte[] bArr2) {
        int a = a(bArr.length, bArr2.length);
        if (a != 0) {
            return a;
        }
        for (a = 0; a < bArr.length; a++) {
            int a2 = a(bArr[a], bArr2[a]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    public static int a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static int a(List list, List list2) {
        int a = a(list.size(), list2.size());
        if (a != 0) {
            return a;
        }
        for (a = 0; a < list.size(); a++) {
            int compare = a.compare(list.get(a), list2.get(a));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public static int a(Set set, Set set2) {
        int a = a(set.size(), set2.size());
        if (a != 0) {
            return a;
        }
        SortedSet treeSet = new TreeSet(a);
        treeSet.addAll(set);
        SortedSet treeSet2 = new TreeSet(a);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            a = a.compare(it.next(), it2.next());
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    public static int a(Map map, Map map2) {
        int a = a(map.size(), map2.size());
        if (a != 0) {
            return a;
        }
        SortedMap treeMap = new TreeMap(a);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        treeMap = new TreeMap(a);
        treeMap.putAll(map2);
        Iterator it2 = treeMap.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Entry entry2 = (Entry) it2.next();
            int compare = a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            a = a.compare(entry.getValue(), entry2.getValue());
            if (a != 0) {
                return a;
            }
        }
        return 0;
    }

    public static void a(ByteBuffer byteBuffer, StringBuilder stringBuilder) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int position = arrayOffset + byteBuffer.position();
        int limit = byteBuffer.limit() + arrayOffset;
        arrayOffset = limit - position > 128 ? position + 128 : limit;
        for (int i = position; i < arrayOffset; i++) {
            if (i > position) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(a(array[i]));
        }
        if (limit != arrayOffset) {
            stringBuilder.append("...");
        }
    }

    public static String a(byte b) {
        return Integer.toHexString((b | 256) & 511).toUpperCase().substring(1);
    }
}
