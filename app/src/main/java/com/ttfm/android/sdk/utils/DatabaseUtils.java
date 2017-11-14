package com.ttfm.android.sdk.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseUtils {
    public static String getString(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndexOrThrow(str));
    }

    public static int getInt(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(str));
    }

    public static long getLong(Cursor cursor, String str) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(str));
    }

    public static float getFloat(Cursor cursor, String str) {
        return cursor.getFloat(cursor.getColumnIndexOrThrow(str));
    }

    public static double getDouble(Cursor cursor, String str) {
        return cursor.getDouble(cursor.getColumnIndexOrThrow(str));
    }

    public static Cursor query(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5) {
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return null;
        }
        return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5);
    }

    public static int delete(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return 0;
        }
        int delete;
        synchronized (sQLiteDatabase) {
            delete = sQLiteDatabase.delete(str, str2, strArr);
        }
        return delete;
    }

    public static Cursor rawQuery(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return null;
        }
        return sQLiteDatabase.rawQuery(str, strArr);
    }

    public static long insert(SQLiteDatabase sQLiteDatabase, String str, String str2, ContentValues contentValues) {
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return 0;
        }
        long insert;
        synchronized (sQLiteDatabase) {
            insert = sQLiteDatabase.insert(str, str2, contentValues);
        }
        return insert;
    }

    public static int update(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues, String str2, String[] strArr) {
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return 0;
        }
        int update;
        synchronized (sQLiteDatabase) {
            update = sQLiteDatabase.update(str, contentValues, str2, strArr);
        }
        return update;
    }

    /* JADX err: Inconsistent code. */
    public static boolean insertOrReplace(android.database.sqlite.SQLiteDatabase r10, android.content.ContentValues r11, java.lang.String r12, java.lang.String[] r13, java.lang.String r14, java.lang.String[] r15) {
        /*
        r8 = 1;
        r9 = 0;
        if (r10 == 0) goto L_0x000a;
    L_0x0004:
        r0 = r10.isOpen();
        if (r0 != 0) goto L_0x000b;
    L_0x000a:
        return r9;
    L_0x000b:
        monitor-enter(r10);
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r10;
        r1 = r12;
        r2 = r13;
        r3 = r14;
        r4 = r15;
        r1 = query(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x001c }
        if (r1 != 0) goto L_0x001f;
    L_0x001a:
        monitor-exit(r10);	 Catch:{ all -> 0x001c }
        goto L_0x000a;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x001c }
        throw r0;
    L_0x001f:
        r0 = r1.moveToFirst();	 Catch:{ SQLiteException -> 0x004c }
        if (r0 == 0) goto L_0x0039;
    L_0x0025:
        r1.close();	 Catch:{ SQLiteException -> 0x004c }
        r0 = update(r10, r12, r11, r14, r15);	 Catch:{ SQLiteException -> 0x004c }
        if (r0 <= 0) goto L_0x0037;
    L_0x002e:
        r0 = r8;
    L_0x002f:
        if (r1 == 0) goto L_0x0034;
    L_0x0031:
        r1.close();	 Catch:{ all -> 0x001c }
    L_0x0034:
        monitor-exit(r10);	 Catch:{ all -> 0x001c }
        r9 = r0;
        goto L_0x000a;
    L_0x0037:
        r0 = r9;
        goto L_0x002f;
    L_0x0039:
        if (r1 == 0) goto L_0x003e;
    L_0x003b:
        r1.close();	 Catch:{ all -> 0x001c }
    L_0x003e:
        r0 = 0;
        r0 = insert(r10, r12, r0, r11);	 Catch:{ all -> 0x001c }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 < 0) goto L_0x004a;
    L_0x0049:
        r9 = r8;
    L_0x004a:
        monitor-exit(r10);	 Catch:{ all -> 0x001c }
        goto L_0x000a;
    L_0x004c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0057 }
        if (r1 == 0) goto L_0x0055;
    L_0x0052:
        r1.close();	 Catch:{ all -> 0x001c }
    L_0x0055:
        monitor-exit(r10);	 Catch:{ all -> 0x001c }
        goto L_0x000a;
    L_0x0057:
        r0 = move-exception;
        if (r1 == 0) goto L_0x005d;
    L_0x005a:
        r1.close();	 Catch:{ all -> 0x001c }
    L_0x005d:
        throw r0;	 Catch:{ all -> 0x001c }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ttfm.android.sdk.utils.DatabaseUtils.insertOrReplace(android.database.sqlite.SQLiteDatabase, android.content.ContentValues, java.lang.String, java.lang.String[], java.lang.String, java.lang.String[]):boolean");
    }
}
