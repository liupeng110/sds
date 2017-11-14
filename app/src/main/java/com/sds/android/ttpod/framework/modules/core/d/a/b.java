package com.sds.android.ttpod.framework.modules.core.d.a;

import android.text.TextUtils.SimpleStringSplitter;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.a.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

/* CDRWinSheetParser */
public class b implements Iterator<a> {
    private BufferedReader a;
    private a b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public /* synthetic */ Object next() {
        return d();
    }

    public b(String str) throws IOException {
        this.a = new BufferedReader(new c(new FileInputStream(str)));
        try {
            a(str);
            this.b = f();
        } catch (IOException e) {
            e();
            throw e;
        }
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f;
    }

    private void a(String str) throws IOException {
        while (true) {
            String readLine = this.a.readLine();
            this.c = readLine;
            if (readLine != null) {
                this.c = this.c.trim();
                readLine = this.c.toUpperCase(Locale.US);
                if (!readLine.startsWith("TRACK ")) {
                    if (readLine.startsWith("FILE ")) {
                        String str2 = e.l(str) + File.separatorChar;
                        String b = b(this.c);
                        this.d = str2 + b;
                        File file = new File(this.d);
                        if (!(file.isFile() && file.exists())) {
                            this.d = str2 + e.k(str) + '.' + e.m(b);
                            file = new File(this.d);
                        }
                        if (file.isFile() && file.exists()) {
                        }
                    } else if (readLine.startsWith("TITLE ")) {
                        this.e = b(this.c);
                    } else if (readLine.startsWith("PERFORMER ")) {
                        this.f = b(this.c);
                    } else if (readLine.startsWith("SONGWRITER ")) {
                        this.g = b(this.c);
                    }
                } else {
                    return;
                }
            }
            return;
        }
        throw new FileNotFoundException("File " + this.d + " not found!");
    }

    private String b(String str) {
        int indexOf = str.indexOf(34);
        int lastIndexOf = str.lastIndexOf(34);
        if (indexOf < 0) {
            return str;
        }
        indexOf++;
        if (lastIndexOf > indexOf) {
            return str.substring(indexOf, lastIndexOf);
        }
        return str;
    }

    private a f() throws IOException {
        if (this.c == null) {
            return null;
        }
        String toUpperCase = this.c.toUpperCase(Locale.US);
        a aVar = new a();
        SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(':');
        do {
            int indexOf;
            if (toUpperCase.startsWith("TRACK ")) {
                toUpperCase = this.c.substring("TRACK ".length());
                indexOf = toUpperCase.indexOf(32);
                if (indexOf > 0) {
                    try {
                        aVar.a(Integer.parseInt(toUpperCase.substring(0, indexOf)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (toUpperCase.startsWith("INDEX ")) {
                this.c = this.c.substring("INDEX ".length());
                int indexOf2 = this.c.indexOf(32);
                if (indexOf2 > 0) {
                    try {
                        int i = indexOf2 + 1;
                        indexOf2 = Integer.parseInt(this.c.substring(0, indexOf2));
                        simpleStringSplitter.setString(this.c.substring(i).trim());
                        if (simpleStringSplitter.hasNext()) {
                            indexOf = Integer.parseInt(simpleStringSplitter.next()) * 60;
                            if (simpleStringSplitter.hasNext()) {
                                indexOf += Integer.parseInt(simpleStringSplitter.next());
                                if (simpleStringSplitter.hasNext()) {
                                    int parseInt = Integer.parseInt(simpleStringSplitter.next());
                                    if (indexOf2 == 0) {
                                        aVar.d(indexOf);
                                        aVar.e(parseInt);
                                    } else if (indexOf2 == 1) {
                                        aVar.b(indexOf);
                                        aVar.c(parseInt);
                                    } else {
                                        aVar.a(new int[]{indexOf, parseInt});
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (toUpperCase.startsWith("TITLE ")) {
                aVar.a(b(this.c));
            } else if (toUpperCase.startsWith("PERFORMER ")) {
                aVar.b(b(this.c));
            }
            this.c = this.a.readLine();
            if (this.c == null) {
                break;
            }
            this.c = this.c.trim();
            toUpperCase = this.c.toUpperCase(Locale.US);
        } while (!toUpperCase.startsWith("TRACK "));
        if (aVar.f() == aVar.g() && aVar.f() < 0) {
            aVar.d(aVar.a());
            aVar.e(aVar.b());
        }
        return aVar;
    }

    public boolean hasNext() {
        return this.b != null;
    }

    public a d() {
        if (hasNext()) {
            a aVar = this.b;
            try {
                this.b = f();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return aVar;
        }
        throw new IndexOutOfBoundsException("has no more entry.");
    }

    public void remove() {
        throw new UnsupportedOperationException("cannot support remove operation.");
    }

    public void e() {
        try {
            this.a.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.a = null;
    }
}
