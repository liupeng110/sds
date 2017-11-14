package com.sds.android.ttpod.component.h;

import android.util.Xml;
import com.sds.android.sdk.lib.util.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* RegisterData */
public final class b {
    private static b c;
    private static String d = "user_data";
    private static String e;
    private HashMap<String, String> a = new HashMap();
    private Vector<String> b = new Vector();

    private b(String str) {
        b(str);
    }

    public static b a(String str) {
        e = str;
        if (c == null) {
            c = new b(str);
        }
        return c;
    }

    private void b(String str) {
        e = str;
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            InputStream fileInputStream = new FileInputStream(new File(str));
            try {
                newPullParser.setInput(fileInputStream, "UTF-8");
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    switch (eventType) {
                        case 2:
                            String name = newPullParser.getName();
                            if (!(name.equals(d) || name.equals(""))) {
                                b(name, newPullParser.nextText());
                                break;
                            }
                    }
                }
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public void a() {
        OutputStream fileOutputStream;
        e.f(e.l(e));
        XmlSerializer newSerializer = Xml.newSerializer();
        try {
            fileOutputStream = new FileOutputStream(new File(e));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        try {
            newSerializer.setOutput(fileOutputStream, "UTF-8");
            newSerializer.startDocument(null, Boolean.valueOf(true));
            newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            newSerializer.startTag(null, d);
            a(newSerializer);
            newSerializer.endTag(null, d);
            newSerializer.endDocument();
            newSerializer.flush();
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(XmlSerializer xmlSerializer, String str, String str2) {
        try {
            xmlSerializer.startTag(null, str);
            xmlSerializer.text(str2);
            xmlSerializer.endTag(null, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(XmlSerializer xmlSerializer) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            String str = (String) this.b.get(size);
            a(xmlSerializer, str, (String) this.a.get(str));
        }
    }

    public String a(String str, String str2) {
        return this.a.get(str) == null ? str2 : (String) this.a.get(str);
    }

    public void b(String str, String str2) {
        this.a.put(str, str2);
        if (!this.b.contains(str)) {
            this.b.add(str);
        }
    }
}
