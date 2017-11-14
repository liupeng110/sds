package com.sds.android.ttpod.cmmusic.d;

import com.sds.android.ttpod.cmmusic.b.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* MusicContentArrayPut */
public class e {
    public static ArrayList<HashMap<String, String>> a(List<b> list) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList();
        try {
            for (b bVar : list) {
                HashMap hashMap = new HashMap();
                hashMap.put("resource_name", bVar.a());
                hashMap.put("resource_songer", bVar.b());
                hashMap.put("zhenling_id", bVar.e());
                hashMap.put("cailing_id", bVar.d());
                hashMap.put("music_id", bVar.f());
                arrayList.add(hashMap);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
