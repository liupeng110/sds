package com.sds.android.sdk.lib.util;

import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* XmlUtils */
public class o {
    public static Map<String, String> a(InputStream inputStream) throws XmlPullParserException, IOException {
        Map hashMap = new HashMap();
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(inputStream, TTPodFMHttpClient.AppEncode);
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 2:
                    if (!newPullParser.getName().equals("string")) {
                        break;
                    }
                    String attributeValue = newPullParser.getAttributeValue(0);
                    String nextText = newPullParser.nextText();
                    if (attributeValue == null) {
                        break;
                    }
                    hashMap.put(attributeValue, nextText);
                    break;
                default:
                    break;
            }
        }
        return hashMap;
    }
}
