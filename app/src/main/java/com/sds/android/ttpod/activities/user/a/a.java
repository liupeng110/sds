package com.sds.android.ttpod.activities.user.a;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* CityContentDomParser */
public class a {
    public static List<b> a(Context context) {
        InputStream b = b(context);
        List<b> arrayList = new ArrayList();
        try {
            NodeList elementsByTagName = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(b).getDocumentElement().getElementsByTagName("dict");
            for (int i = 0; i < elementsByTagName.getLength(); i++) {
                b bVar = new b();
                NodeList childNodes = ((Element) elementsByTagName.item(i)).getChildNodes();
                for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                    Node item = childNodes.item(i2);
                    if (item.getNodeType() == (short) 1) {
                        Element element = (Element) item;
                        String nodeName = element.getNodeName();
                        if ("string".equals(nodeName)) {
                            bVar.a(element.getFirstChild().getNodeValue());
                        } else if ("array".equals(nodeName)) {
                            List arrayList2 = new ArrayList();
                            NodeList childNodes2 = element.getChildNodes();
                            for (int i3 = 0; i3 < childNodes2.getLength(); i3++) {
                                item = childNodes2.item(i3);
                                if (item.getNodeType() == (short) 1) {
                                    element = (Element) item;
                                    if ("string".equals(element.getNodeName())) {
                                        arrayList2.add(element.getFirstChild().getNodeValue());
                                    }
                                }
                            }
                            bVar.a(arrayList2);
                        }
                    }
                }
                arrayList.add(bVar);
            }
            b.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static InputStream b(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("city_list/city.plist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
