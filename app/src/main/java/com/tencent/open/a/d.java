package com.tencent.open.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* ProGuard */
public class d {
    private Context a;
    private SQLiteDatabase b;
    private a c;
    private int d = c().size();

    /* ProGuard */
    private class a extends SQLiteOpenHelper {
        final /* synthetic */ d a;

        public a(d dVar, Context context, String str, CursorFactory cursorFactory, int i) {
            this.a = dVar;
            super(context, str, cursorFactory, i);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                Log.i("cgi_report_debug", "ReportDataModal onCreate sql1 = create table if not exists newdata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
                sQLiteDatabase.execSQL("create table if not exists newdata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
                Log.i("cgi_report_debug", "ReportDataModal onCreate sql2 = create table if not exists olddata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
                sQLiteDatabase.execSQL("create table if not exists olddata(id integer primary key,apn text,frequency text,commandid text,resultcode text,tmcost text,reqsize text,rspsize text,deviceinfo text,detail text)");
            } catch (Exception e) {
                Log.e("cgi_report_debug", "ReportDataModal onCreate failed");
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.i("cgi_report_debug", "ReportDataModal onUpgrade oldVersion=" + i + "  newVersion=" + i2 + "");
            if (i != i2) {
                try {
                    sQLiteDatabase.execSQL("drop table if exists newdata");
                    sQLiteDatabase.execSQL("drop table if exists olddata");
                    onCreate(sQLiteDatabase);
                    Log.i("cgi_report_debug", "ReportDataModal onUpgrade success");
                } catch (Exception e) {
                    Log.e("cgi_report_debug", "ReportDataModal onUpgrade failed");
                }
            }
        }
    }

    public d(Context context) {
        this.a = context;
        this.c = new a(this, context, "sdk_cgi_report.db", null, 2);
    }

    public synchronized boolean a(String str, String str2, String str3, int i, long j, long j2, long j3, String str4) {
        boolean z;
        if (str3.contains("?")) {
            str3 = str3.split("\\?")[0];
        }
        Log.i("cgi_report_debug", "ReportDataModal addNewItem apn=" + str + ",frequency=" + str2 + ",commandid=" + str3 + ",resultCode=" + i + ",costTime=" + j + ",reqSzie=" + j2 + ",rspSize=" + j3);
        ContentValues contentValues = new ContentValues();
        contentValues.put("apn", str + "");
        contentValues.put("frequency", str2 + "");
        contentValues.put("commandid", str3 + "");
        contentValues.put("resultcode", i + "");
        contentValues.put("tmcost", j + "");
        contentValues.put("reqsize", j2 + "");
        contentValues.put("rspsize", j3 + "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("network=").append(str).append('&');
        stringBuilder.append("sdcard=").append(Environment.getExternalStorageState().equals("mounted") ? 1 : 0).append('&');
        stringBuilder.append("wifi=").append(c.c(this.a));
        contentValues.put("deviceinfo", stringBuilder.toString());
        contentValues.put("detail", str4);
        try {
            this.b = this.c.getWritableDatabase();
            this.b.insertOrThrow("newdata", null, contentValues);
            this.b.close();
            Log.i("cgi_report_debug", "ReportDataModal addNewItem success");
            this.d++;
            z = true;
        } catch (Exception e) {
            Log.e("cgi_report_debug", "ReportDataModal addNewItem failed");
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    public synchronized int a(ArrayList<a> arrayList) {
        int i;
        Log.i("cgi_report_debug", "ReportDataModal backupOldItems count = " + arrayList.size());
        Iterator it = arrayList.iterator();
        i = 0;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            ContentValues contentValues = new ContentValues();
            contentValues.put("apn", aVar.a());
            contentValues.put("frequency", aVar.b());
            contentValues.put("commandid", aVar.c());
            contentValues.put("resultcode", aVar.d());
            contentValues.put("tmcost", aVar.e());
            contentValues.put("reqsize", aVar.f());
            contentValues.put("rspsize", aVar.g());
            contentValues.put("deviceinfo", aVar.i());
            contentValues.put("detail", aVar.h());
            try {
                this.b = this.c.getWritableDatabase();
                this.b.insertOrThrow("olddata", null, contentValues);
                this.b.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        Log.i("cgi_report_debug", "ReportDataModal backupOldItems succ_count = " + i);
        return i;
    }

    public synchronized boolean a() {
        boolean z = false;
        synchronized (this) {
            Log.i("cgi_report_debug", "ReportDataModal deleteOldItems start");
            try {
                this.b = this.c.getWritableDatabase();
                try {
                    this.b.execSQL("delete from olddata;");
                    this.b.close();
                    Log.i("cgi_report_debug", "ReportDataModal deleteOldItems success");
                    z = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    this.b.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    public synchronized boolean b() {
        boolean z = false;
        synchronized (this) {
            Log.i("cgi_report_debug", "ReportDataModal deleteNewItems start");
            try {
                this.b = this.c.getWritableDatabase();
                try {
                    this.b.execSQL("delete from newdata;");
                    this.d = 0;
                    this.b.close();
                    Log.i("cgi_report_debug", "ReportDataModal deleteNewItems start");
                    z = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    this.b.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    public synchronized ArrayList<a> c() {
        ArrayList<a> arrayList;
        Log.i("cgi_report_debug", "ReportDataModal getNewItems start");
        ArrayList<a> arrayList2 = new ArrayList();
        try {
            this.b = this.c.getReadableDatabase();
            Cursor rawQuery = this.b.rawQuery("select * from newdata", new String[0]);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                arrayList2.add(new a(rawQuery.getString(rawQuery.getColumnIndex("apn")), rawQuery.getString(rawQuery.getColumnIndex("frequency")), rawQuery.getString(rawQuery.getColumnIndex("commandid")), rawQuery.getString(rawQuery.getColumnIndex("resultcode")), rawQuery.getString(rawQuery.getColumnIndex("tmcost")), rawQuery.getString(rawQuery.getColumnIndex("reqsize")), rawQuery.getString(rawQuery.getColumnIndex("rspsize")), rawQuery.getString(rawQuery.getColumnIndex("deviceinfo")), rawQuery.getString(rawQuery.getColumnIndex("detail"))));
                rawQuery.moveToNext();
            }
            rawQuery.close();
            this.b.close();
            Log.i("cgi_report_debug", "ReportDataModal getNewItems success, count = " + arrayList2.size());
            arrayList = arrayList2;
        } catch (Exception e) {
            e.printStackTrace();
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public synchronized ArrayList<a> d() {
        ArrayList<a> arrayList;
        Log.i("cgi_report_debug", "ReportDataModal getOldItems start");
        ArrayList<a> arrayList2 = new ArrayList();
        try {
            this.b = this.c.getReadableDatabase();
            Cursor rawQuery = this.b.rawQuery("select * from olddata", new String[0]);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                arrayList2.add(new a(rawQuery.getString(rawQuery.getColumnIndex("apn")), rawQuery.getString(rawQuery.getColumnIndex("frequency")), rawQuery.getString(rawQuery.getColumnIndex("commandid")), rawQuery.getString(rawQuery.getColumnIndex("resultcode")), rawQuery.getString(rawQuery.getColumnIndex("tmcost")), rawQuery.getString(rawQuery.getColumnIndex("reqsize")), rawQuery.getString(rawQuery.getColumnIndex("rspsize")), rawQuery.getString(rawQuery.getColumnIndex("deviceinfo")), rawQuery.getString(rawQuery.getColumnIndex("detail"))));
                rawQuery.moveToNext();
            }
            rawQuery.close();
            this.b.close();
            Log.i("cgi_report_debug", "ReportDataModal getOldItems success, count = " + arrayList2.size());
            arrayList = arrayList2;
        } catch (Exception e) {
            e.printStackTrace();
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public int e() {
        Log.i("cgi_report_debug", "ReportDataModal getTotalCount count = " + this.d);
        return this.d;
    }
}
