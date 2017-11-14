package com.sds.android.sdk.lib.c.a;

import android.content.ContentValues;
import android.database.Cursor;
import com.sds.android.sdk.lib.c.a.a.c;
import com.sds.android.sdk.lib.c.a.a.d;
import com.sds.android.sdk.lib.util.m;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/* EntityDescriptor */
public class a {
    private Class a;
    private c b = null;
    private com.sds.android.sdk.lib.c.a.a.a c = null;
    private LinkedHashMap<String, b> d = new LinkedHashMap();

    private boolean a(String str, Method[] methodArr, b bVar) throws Exception {
        for (Method method : methodArr) {
            if (str.equals(method.getName())) {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes != null && parameterTypes.length == 1 && parameterTypes[0] == bVar.a()) {
                    int modifiers = method.getModifiers();
                    if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                        throw new Exception("EntityDescriptor: Invalid Language Modifier " + str);
                    }
                    bVar.b(method);
                    return true;
                }
            }
        }
        return false;
    }

    public a(Class cls) throws Exception {
        int i = 0;
        this.a = cls;
        this.c = (com.sds.android.sdk.lib.c.a.a.a) this.a.getAnnotation(com.sds.android.sdk.lib.c.a.a.a.class);
        this.b = c.a();
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getDeclaringClass().isAssignableFrom(cls)) {
                String name = method.getName();
                if (name.startsWith("get")) {
                    String substring = name.substring("get".length());
                    if (substring.length() > 0) {
                        Class[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes != null && parameterTypes.length <= 0) {
                            Class returnType = method.getReturnType();
                            if (c.a(returnType)) {
                                int modifiers = method.getModifiers();
                                if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
                                    throw new Exception("EntityDescriptor: Invalid Language Modifiers " + name);
                                }
                                b bVar = new b();
                                if (this.b.a(bVar, method)) {
                                    bVar.a(method);
                                    bVar.a(returnType);
                                    if (!bVar.e() || bVar.a() == Long.class || bVar.a() == Integer.class || bVar.a() == Short.class) {
                                        String str = "set" + substring;
                                        if (a(str, methods, bVar)) {
                                            this.d.put(substring, bVar);
                                        } else {
                                            throw new Exception("EntityDescriptor: Invalid Setter " + str);
                                        }
                                    }
                                    throw new Exception("EntityDescriptor: AutoIncrement Field must be Long, Short or Integer !!");
                                }
                                continue;
                            } else {
                                continue;
                            }
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        Object[] toArray = this.d.entrySet().toArray();
        Arrays.sort(toArray, new Comparator<Object>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public int compare(Object obj, Object obj2) {
                Entry entry = (Entry) obj2;
                int b = ((b) ((Entry) obj).getValue()).b();
                int b2 = ((b) entry.getValue()).b();
                if (b == b2) {
                    return 0;
                }
                if (b < b2) {
                    return -1;
                }
                return 1;
            }
        });
        this.d.clear();
        int length = toArray.length;
        while (i < length) {
            Entry entry = (Entry) toArray[i];
            this.d.put(entry.getKey(), entry.getValue());
            i++;
        }
    }

    public String a() {
        if (this.c == null || this.c.a().length() <= 0) {
            return this.a.getSimpleName();
        }
        return this.c.a();
    }

    private Object a(Object obj, String str) throws ClassNotFoundException, NoSuchFieldException, InvocationTargetException {
        a(obj);
        Method a = a(str);
        if (a == null) {
            throw new NoSuchFieldException("No Such Getter " + str);
        }
        try {
            return a.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void a(Object obj, String str, Object obj2) throws ClassNotFoundException, NoSuchFieldException, InvocationTargetException {
        a(obj);
        Method b = b(str);
        if (b == null) {
            throw new NoSuchFieldException("No Such Setter " + str);
        }
        try {
            b.invoke(obj, new Object[]{c.a(obj2, c(str))});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e2) {
            e2.printStackTrace();
            throw new IllegalArgumentException("Illegal Argument Cast " + str);
        }
    }

    public Object a(Cursor cursor) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Object newInstance = this.a.newInstance();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            try {
                a(newInstance, cursor.getColumnName(i), cursor.getString(i));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return newInstance;
    }

    public ContentValues a(Object obj, boolean z) throws ClassNotFoundException {
        ContentValues contentValues = new ContentValues();
        a(contentValues, obj, z);
        return contentValues;
    }

    void a(ContentValues contentValues, Object obj, boolean z) throws ClassNotFoundException {
        a(obj);
        for (Entry key : this.d.entrySet()) {
            try {
                String str = (String) key.getKey();
                Object a = a(obj, str);
                if (a != null || z) {
                    c.a(contentValues, str, a);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
    }

    public String b() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.c == null || this.c.b().a().length <= 0) {
            str = null;
        } else {
            stringBuilder.replace(0, stringBuilder.length(), "PRIMARY KEY (");
            stringBuilder.append(m.a(", ", this.c.b().a()));
            stringBuilder.append(")");
            str = stringBuilder.toString();
        }
        Collection arrayList = new ArrayList();
        if (this.c != null) {
            for (d dVar : this.c.c()) {
                if (dVar.b().length > 0) {
                    stringBuilder.replace(0, stringBuilder.length(), "INDEX ").append(dVar.a()).append(" (");
                    stringBuilder.append(m.a(", ", dVar.b()));
                    stringBuilder.append(")");
                    arrayList.add(stringBuilder.toString());
                }
            }
        }
        Collection arrayList2 = new ArrayList();
        for (Entry entry : this.d.entrySet()) {
            b bVar = (b) entry.getValue();
            stringBuilder.replace(0, stringBuilder.length(), (String) entry.getKey()).append(" ").append(c.b(bVar.a()));
            if (bVar.d() && str == null) {
                stringBuilder.append(" PRIMARY KEY");
            }
            if (bVar.e()) {
                stringBuilder.append(" AUTOINCREMENT");
            }
            if (bVar.f() != null && bVar.f().length() > 0) {
                stringBuilder.append(" DEFAULT ").append(bVar.f());
            }
            if (bVar.g() != null && bVar.g().length() > 0) {
                stringBuilder.append(" COMMENT ").append("'").append(bVar.g()).append("'");
            }
            arrayList2.add(stringBuilder.toString());
        }
        stringBuilder.replace(0, stringBuilder.length(), "CREATE TABLE IF NOT EXISTS ").append(a()).append(" (").append(m.a(", ", arrayList2));
        if (str != null) {
            stringBuilder.append(", ").append(str);
        }
        if (arrayList.size() > 0) {
            stringBuilder.append(", ").append(m.a(", ", arrayList));
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public String c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE IF EXISTS ").append(a());
        return stringBuilder.toString();
    }

    public boolean a(int i) {
        for (Object obj : this.d.keySet()) {
            if (((b) this.d.get(obj)).c() > i) {
                return false;
            }
        }
        return true;
    }

    public List<String> a(int i, int i2) {
        List<String> arrayList = new ArrayList();
        for (String str : this.d.keySet()) {
            b bVar = (b) this.d.get(str);
            if (bVar.c() > i) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ALTER TABLE ").append(a()).append(" ADD COLUMN ").append(str).append(" ").append(c.b(bVar.a()));
                if (bVar.d()) {
                    stringBuilder.append(" PRIMARY KEY");
                }
                if (bVar.e()) {
                    stringBuilder.append(" AUTOINCREMENT");
                }
                if (bVar.f() != null && bVar.f().length() > 0) {
                    stringBuilder.append(" DEFAULT ").append(bVar.f());
                }
                if (bVar.g() != null && bVar.g().length() > 0) {
                    stringBuilder.append(" COMMENT ").append("'").append(bVar.g()).append("'");
                }
                arrayList.add(stringBuilder.toString());
            }
        }
        return arrayList;
    }

    protected boolean a(Object obj) throws ClassNotFoundException {
        if (obj.getClass() == this.a) {
            return true;
        }
        throw new ClassNotFoundException();
    }

    protected Method a(String str) {
        if (this.d.containsKey(str)) {
            return ((b) this.d.get(str)).h();
        }
        return null;
    }

    protected Method b(String str) {
        if (this.d.containsKey(str)) {
            return ((b) this.d.get(str)).i();
        }
        return null;
    }

    protected Class c(String str) {
        if (this.d.containsKey(str)) {
            return ((b) this.d.get(str)).a();
        }
        return null;
    }
}
