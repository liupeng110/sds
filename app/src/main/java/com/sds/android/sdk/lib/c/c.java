package com.sds.android.sdk.lib.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* SqliteStorageImpl */
public class c extends b {
    private Map<String, com.sds.android.sdk.lib.c.a.a> a = new HashMap();
    private Context b;
    private String c;
    private int d;
    private a e;
    private SQLiteDatabase f;

    /* SqliteStorageImpl */
    public interface a {
        void a(int i, int i2);
    }

    /* SqliteStorageImpl */
    private class b extends SQLiteOpenHelper {
        final /* synthetic */ c a;

        public b(c cVar, Context context) {
            this.a = cVar;
            super(context, cVar.c, null, cVar.d);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                for (Entry value : this.a.a.entrySet()) {
                    sQLiteDatabase.execSQL(((com.sds.android.sdk.lib.c.a.a) value.getValue()).b());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            onCreate(sQLiteDatabase);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            try {
                for (Entry value : this.a.a.entrySet()) {
                    List a = ((com.sds.android.sdk.lib.c.a.a) value.getValue()).a(i, i2);
                    for (int i3 = 0; i3 < a.size(); i3++) {
                        sQLiteDatabase.execSQL((String) a.get(i3));
                    }
                }
                onCreate(sQLiteDatabase);
                if (this.a.e != null) {
                    this.a.e.a(i, i2);
                }
            } catch (Exception e) {
                g.c("SqliteStorageImpl", "Upgrade Database error! drop and reCreate!");
                a(sQLiteDatabase);
                e.printStackTrace();
            }
        }

        private void a(SQLiteDatabase sQLiteDatabase) {
            for (Entry value : this.a.a.entrySet()) {
                sQLiteDatabase.execSQL(((com.sds.android.sdk.lib.c.a.a) value.getValue()).c());
            }
            onCreate(sQLiteDatabase);
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            a(sQLiteDatabase);
        }
    }

    public void a(Class cls) {
        if (!this.a.containsKey(cls.getSimpleName())) {
            try {
                com.sds.android.sdk.lib.c.a.a aVar = new com.sds.android.sdk.lib.c.a.a(cls);
                if (aVar.a(this.d)) {
                    this.a.put(cls.getSimpleName(), aVar);
                    return;
                }
                throw new RuntimeException("SqliteStroageImpl Field version must be less than current database version !!");
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private com.sds.android.sdk.lib.c.a.a a(String str) {
        if (this.a.containsKey(str)) {
            return (com.sds.android.sdk.lib.c.a.a) this.a.get(str);
        }
        return null;
    }

    private SQLiteDatabase c() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this.b) {
            if (this.f == null) {
                this.f = new b(this, this.b).getWritableDatabase();
            }
            sQLiteDatabase = this.f;
        }
        return sQLiteDatabase;
    }

    public c(Context context, String str, int i, a aVar) {
        this.b = context;
        this.c = str;
        this.d = i;
        this.e = aVar;
    }

    public void a() {
        Thread thread = new Thread(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        });
        thread.setDaemon(true);
        thread.setPriority(10);
        thread.start();
    }

    public boolean b() {
        return c().isOpen();
    }

    public <Entity> List<Entity> a(Entity entity, boolean z) {
        Cursor query;
        Exception e;
        Cursor cursor;
        Throwable th;
        if (!b()) {
            return null;
        }
        com.sds.android.sdk.lib.c.a.a a = a(entity.getClass().getSimpleName());
        if (a == null) {
            return null;
        }
        String a2 = a.a();
        try {
            Entry a3 = a(a, (Object) entity);
            query = c().query(z, a2, null, (String) a3.getKey(), (String[]) a3.getValue(), null, null, null, null);
            try {
                List<Entity> arrayList = new ArrayList();
                if (query != null && query.moveToFirst()) {
                    do {
                        try {
                            arrayList.add(a.a(query));
                        } catch (InstantiationException e2) {
                            e2.printStackTrace();
                        }
                    } while (query.moveToNext());
                    if (query == null) {
                        return arrayList;
                    }
                    query.close();
                    return arrayList;
                } else if (query == null) {
                    return arrayList;
                } else {
                    query.close();
                    return arrayList;
                }
            } catch (Exception e3) {
                e = e3;
                cursor = query;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
            cursor = null;
            try {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                query = cursor;
                if (query != null) {
                    query.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    public <Entity> long a(Entity entity) {
        long j = -1;
        if (b()) {
            com.sds.android.sdk.lib.c.a.a a = a(entity.getClass().getSimpleName());
            if (a != null) {
                try {
                    j = c().insertOrThrow(a.a(), null, a.a((Object) entity, false));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return j;
    }

    public <Entity> long a(Entity entity, Entity entity2) {
        if (!b()) {
            return -1;
        }
        if (entity.getClass() != entity2.getClass()) {
            return -1;
        }
        com.sds.android.sdk.lib.c.a.a a = a(entity.getClass().getSimpleName());
        if (a == null) {
            return -1;
        }
        String a2 = a.a();
        try {
            Entry a3 = a(a, (Object) entity2);
            return (long) c().update(a2, a.a((Object) entity, false), (String) a3.getKey(), (String[]) a3.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public <Entity> long b(Entity entity) {
        if (!b()) {
            return -1;
        }
        com.sds.android.sdk.lib.c.a.a a = a(entity.getClass().getSimpleName());
        if (a == null) {
            return -1;
        }
        String a2 = a.a();
        try {
            Entry a3 = a(a, (Object) entity);
            return (long) c().delete(a2, (String) a3.getKey(), (String[]) a3.getValue());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static Entry<String, String[]> a(com.sds.android.sdk.lib.c.a.a aVar, Object obj) throws ClassNotFoundException {
        ContentValues a = aVar.a(obj, false);
        if (a.size() <= 0) {
            return new a(null, null);
        }
        Object obj2 = new String[a.size()];
        int i = 0;
        String str = "";
        for (Entry entry : a.valueSet()) {
            int i2;
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                int i3 = i + 1;
                obj2[i] = ((Boolean) value).booleanValue() ? "1" : FeedbackItem.STATUS_WAITING;
                i2 = i3;
            } else {
                i2 = i + 1;
                obj2[i] = entry.getValue().toString();
            }
            String str2 = str + ((String) entry.getKey()) + "=? ";
            if (i2 < a.size()) {
                str2 = str2 + "AND ";
            }
            i = i2;
            str = str2;
        }
        return new a(str, obj2);
    }
}
