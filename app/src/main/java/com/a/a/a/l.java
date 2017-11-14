package com.a.a.a;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* PullXMLTool */
public class l {
    public static String a(InputStream inputStream) {
        String str;
        Throwable e;
        Throwable th;
        String str2 = "";
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(inputStream, "UTF-8");
            str = str2;
            int eventType = newPullParser.getEventType();
            while (1 != eventType) {
                try {
                    String name = newPullParser.getName();
                    switch (eventType) {
                        case 0:
                            str2 = str;
                            break;
                        case 2:
                            if (name.equalsIgnoreCase("return_code")) {
                                str2 = newPullParser.nextText();
                                break;
                            }
                            break;
                        case 3:
                            str2 = str;
                            break;
                    }
                    str2 = str;
                    str = str2;
                    eventType = newPullParser.next();
                } catch (XmlPullParserException e2) {
                    e = e2;
                } catch (IOException e3) {
                    e = e3;
                }
            }
        } catch (Throwable e4) {
            th = e4;
            str = str2;
            e = th;
        } catch (Throwable e42) {
            th = e42;
            str = str2;
            e = th;
        }
        return str;
        Log.e("SDK_LW_CMM", e.getMessage(), e);
        return str;
        Log.e("SDK_LW_CMM", e.getMessage(), e);
        return str;
    }

    public static String b(InputStream inputStream) {
        String str;
        Throwable e;
        Throwable th;
        String str2 = "";
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(inputStream, "UTF-8");
            str = str2;
            int eventType = newPullParser.getEventType();
            while (1 != eventType) {
                try {
                    String name = newPullParser.getName();
                    switch (eventType) {
                        case 0:
                            str2 = str;
                            break;
                        case 2:
                            if (name.equalsIgnoreCase("return_desc")) {
                                str2 = newPullParser.nextText();
                                break;
                            }
                            break;
                        case 3:
                            str2 = str;
                            break;
                    }
                    str2 = str;
                    str = str2;
                    eventType = newPullParser.next();
                } catch (XmlPullParserException e2) {
                    e = e2;
                } catch (IOException e3) {
                    e = e3;
                }
            }
        } catch (Throwable e4) {
            th = e4;
            str = str2;
            e = th;
        } catch (Throwable e42) {
            th = e42;
            str = str2;
            e = th;
        }
        return str;
        Log.e("SDK_LW_CMM", e.getMessage(), e);
        return str;
        Log.e("SDK_LW_CMM", e.getMessage(), e);
        return str;
    }

    public static InputStream a(byte[] bArr) {
        String str = "";
        try {
            Log.d("responsBody", new String(bArr, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(bArr);
    }
}
