package com.sds.android.ttpod.framework.support.search.task;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.r;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.search.SearchMediaLinkInfo;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.search.a.a;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.tencent.open.SocialConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* PictureSearchTask */
public class c extends a {
    private static final Set<String> c = new HashSet();
    private com.sds.android.ttpod.framework.support.search.a.c a;
    private String b;

    protected /* synthetic */ a b() {
        return e();
    }

    protected void a(List<ResultData> list) {
        if (list == null || list.isEmpty()) {
            a(com.sds.android.ttpod.framework.support.search.a.b);
            return;
        }
        Item[] c = ((ResultData) list.get(0)).c();
        int length = (!r.a() || c == null) ? 1 : c.length;
        if (c == null) {
            length = 0;
        }
        int d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
        int aI = b.aI();
        int aJ = b.aJ();
        if (d != 2) {
            aI = aJ;
        }
        ArrayList b = b(com.sds.android.ttpod.framework.a.t() + File.separator + e.o(e().i().getArtist()) + File.separator);
        d = 0;
        int i = 0;
        boolean z = false;
        while (i < length && d < aI) {
            Item item = c[i];
            i++;
            d++;
            if ((b == null || !b.contains(Integer.valueOf(item.a()))) && !e.b(item.d())) {
                z = i >= length || d >= aI;
                a(item, z);
            }
        }
        if (!z) {
            a(com.sds.android.ttpod.framework.support.search.a.a);
        }
    }

    protected boolean a(ArrayList<String> arrayList) {
        Exception e;
        Item[] c;
        int i;
        int i2;
        int d;
        Throwable th;
        List list = null;
        MediaItem i3 = e().i();
        String a = a(i3);
        if (a != null) {
            a = e.i(a);
            if (!m.a(a)) {
                ByteArrayInputStream byteArrayInputStream;
                try {
                    com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
                    byteArrayInputStream = new ByteArrayInputStream(a.getBytes("UTF-8"));
                    try {
                        aVar.setInput(byteArrayInputStream, "UTF-8");
                        list = a(aVar, i3, e().l(), this.b);
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (Exception e3) {
                        e2 = e3;
                        try {
                            e2.printStackTrace();
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                            c = ((ResultData) list.get(0)).c();
                            if (c != null) {
                                i = 0;
                            } else {
                                i = c.length;
                            }
                            i2 = i;
                            d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
                            if (i2 != 0) {
                            }
                            return true;
                        } catch (Throwable th2) {
                            th = th2;
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Exception e5) {
                    e22 = e5;
                    byteArrayInputStream = list;
                    e22.printStackTrace();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    c = ((ResultData) list.get(0)).c();
                    if (c != null) {
                        i = c.length;
                    } else {
                        i = 0;
                    }
                    i2 = i;
                    d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
                    if (i2 != 0) {
                    }
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayInputStream = list;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw th;
                }
                if (!(list == null || list.isEmpty())) {
                    c = ((ResultData) list.get(0)).c();
                    if (c != null) {
                        i = c.length;
                    } else {
                        i = 0;
                    }
                    i2 = i;
                    d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
                    if (i2 != 0 || d == -1) {
                        return true;
                    }
                    boolean z;
                    d = d == 2 ? b.aI() : b.aJ();
                    if (arrayList != null) {
                        i = arrayList.size();
                    } else {
                        i = 0;
                    }
                    if (i >= d || i >= i2) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z) {
                        String str = com.sds.android.ttpod.framework.a.t() + File.separator + e.o(i3.getArtist()) + File.separator;
                        ArrayList b = b(str);
                        if (b != null) {
                            Iterator it = b.iterator();
                            int i4 = i;
                            while (it.hasNext()) {
                                if (e.b(str + ((long) ((Integer) it.next()).intValue()))) {
                                    i = i4;
                                } else {
                                    i = i4 + 1;
                                }
                                i4 = i;
                            }
                            if (i4 >= d || i4 >= i2) {
                                return true;
                            }
                            return false;
                        }
                    }
                    return z;
                }
            }
        }
        boolean z2 = false;
        d = com.sds.android.sdk.lib.util.EnvironmentUtils.c.d();
        if (i2 != 0) {
        }
        return true;
    }

    public static ArrayList<Integer> b(String str) {
        Throwable th;
        ObjectInputStream objectInputStream = null;
        ObjectInputStream objectInputStream2;
        try {
            objectInputStream2 = new ObjectInputStream(new FileInputStream(str + "uopicid"));
            try {
                ArrayList<Integer> arrayList = (ArrayList) objectInputStream2.readObject();
                if (objectInputStream2 == null) {
                    return arrayList;
                }
                try {
                    objectInputStream2.close();
                    return arrayList;
                } catch (IOException e) {
                    e.printStackTrace();
                    return arrayList;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    if (objectInputStream2 != null) {
                        return null;
                    }
                    try {
                        objectInputStream2.close();
                        return null;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectInputStream = objectInputStream2;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            throw th;
        }
    }

    public static void a(String str, ArrayList<Integer> arrayList) {
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        ObjectOutputStream objectOutputStream2;
        try {
            objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(str + "uopicid"));
            try {
                objectOutputStream2.writeObject(arrayList);
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw th;
        }
    }

    protected String a(MediaItem mediaItem) {
        String d = d(mediaItem.getID());
        if (m.a(d)) {
            d = e.o(mediaItem.getArtist());
        }
        if (!TTTextUtils.isValidateMediaString(d)) {
            d = null;
        }
        if (m.a(d)) {
            return null;
        }
        return com.sds.android.ttpod.framework.a.t() + File.separator + d + File.separator + "result.xml";
    }

    protected ArrayList<ResultData> a(com.sds.android.ttpod.framework.modules.search.a.a aVar) throws Exception {
        MediaItem i = e().i();
        ArrayList<ResultData> a = a(aVar, i, e().l(), this.b);
        if (!(!m.a(this.b) || a == null || a.isEmpty() || TTTextUtils.isValidateMediaString(i.getArtist()))) {
            this.b = ((ResultData) a.get(0)).b();
            b(i.getID(), this.b);
        }
        return a;
    }

    public static void b(String str, String str2) {
        SearchMediaLinkInfo a = com.sds.android.ttpod.framework.storage.database.a.a(BaseApplication.e().getContentResolver(), str);
        if (a != null) {
            a.setArtist(str2);
            com.sds.android.ttpod.framework.storage.database.a.a(BaseApplication.e().getContentResolver(), a, str);
            return;
        }
        a = new SearchMediaLinkInfo();
        a.setMediaId(str);
        a.setArtist(str2);
        com.sds.android.ttpod.framework.storage.database.a.a(BaseApplication.e().getContentResolver(), a);
    }

    public static ArrayList<ResultData> a(com.sds.android.ttpod.framework.modules.search.a.a aVar, MediaItem mediaItem, int i, String str) {
        try {
            String str2;
            Object obj;
            ResultData resultData;
            ArrayList arrayList = new ArrayList();
            ArrayList<ResultData> arrayList2 = new ArrayList();
            aVar.nextTag();
            aVar.require(2, null, "tt_song_list");
            String attributeValue = aVar.getAttributeValue(null, "ip");
            Object o = str != null ? str : mediaItem != null ? e.o(mediaItem.getArtist()) : null;
            if (TTTextUtils.isValidateMediaString(o)) {
                str2 = com.sds.android.ttpod.framework.a.t() + File.separator + e.o(o) + File.separator;
                obj = o;
                resultData = null;
            } else {
                str2 = null;
                obj = null;
                resultData = null;
            }
            while (true) {
                String str3;
                ResultData resultData2;
                int next = aVar.next();
                switch (next) {
                    case 2:
                        String str4;
                        if (!"tt_songinfo".equals(aVar.getName())) {
                            if (!User.KEY_AVATAR.equals(aVar.getName())) {
                                aVar.a();
                                str3 = str2;
                                o = obj;
                                resultData2 = resultData;
                                break;
                            }
                            String attributeValue2 = aVar.getAttributeValue(null, SocialConstants.PARAM_TYPE);
                            if (a(attributeValue2, i)) {
                                String str5;
                                int parseInt;
                                str4 = null;
                                try {
                                    str4 = attributeValue + '/' + TTTextUtils.decryptPictureKey((int) Long.parseLong(aVar.getAttributeValue(null, "uid0"), 16), (int) Long.parseLong(aVar.getAttributeValue(null, "uid1"), 16), (int) Long.parseLong(aVar.getAttributeValue(null, "uid2"), 16), aVar.getAttributeValue(null, "uid3")) + ".jpg";
                                    str5 = str4;
                                    parseInt = Integer.parseInt(aVar.getAttributeValue(null, StarCategory.KEY_STAR_CATEGORY_ID));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    String str6 = str4;
                                    parseInt = 0;
                                    str5 = str6;
                                }
                                if ("artist".equals(attributeValue2)) {
                                    str3 = str2 + parseInt;
                                } else if ("album".equals(attributeValue2)) {
                                    str3 = str2 + parseInt;
                                    parseInt = 0;
                                } else {
                                    str3 = str2 + parseInt;
                                }
                                if (!(parseInt == 0 || str5 == null)) {
                                    arrayList.add(new Item(attributeValue2, str5, str3, parseInt));
                                }
                            }
                            str3 = str2;
                            o = obj;
                            resultData2 = resultData;
                            break;
                        }
                        resultData = new ResultData();
                        resultData.a = aVar.getAttributeValue(null, "title");
                        resultData.b = e.o(aVar.getAttributeValue(null, "artist"));
                        resultData.c = aVar.getAttributeValue(null, "album");
                        resultData.d = aVar.getAttributeValue(null, "allname");
                        if (obj == null) {
                            String str7 = resultData.b;
                            str3 = com.sds.android.ttpod.framework.a.t() + File.separator + str7 + File.separator;
                            str4 = str7;
                            resultData2 = resultData;
                            break;
                        }
                        break;
                    case 3:
                        if ("tt_songinfo".equals(aVar.getName()) && resultData != null) {
                            int size = arrayList.size();
                            if (size > 0) {
                                Collections.sort(arrayList);
                                resultData.e = new Item[size];
                                arrayList.toArray(resultData.e);
                                arrayList.clear();
                            }
                            arrayList2.add(resultData);
                            return arrayList2;
                        }
                }
                str3 = str2;
                o = obj;
                resultData2 = resultData;
                if (1 == next) {
                    return arrayList2;
                }
                str2 = str3;
                obj = o;
                resultData = resultData2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static boolean a(String str, int i) {
        return 6 == (i & 6) || a(i) || ((b(i) && "artist".equals(str)) || (c(i) && "album".equals(str)));
    }

    private static boolean a(int i) {
        return 1 == (i & 1);
    }

    private static boolean b(int i) {
        return 2 == (i & 2);
    }

    private static boolean c(int i) {
        return 4 == (i & 4);
    }

    protected String a() {
        return a(e());
    }

    public static String a(com.sds.android.ttpod.framework.support.search.a.c cVar) {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        str = "";
        try {
            MediaItem i = cVar.i();
            boolean e = cVar.e();
            stringBuilder.append("http://picdown.ttpod.cn/picsearch?");
            str = i.getArtist();
            if (e && !TextUtils.isEmpty(cVar.g())) {
                str = cVar.g();
            }
            if (!TTTextUtils.isValidateMediaString(str)) {
                str = "";
            }
            stringBuilder.append("artist=");
            stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
            cVar.d(str);
            str = i.getTitle();
            stringBuilder.append("&title=");
            stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
            cVar.c(str);
            if (!i.isOnline()) {
                stringBuilder.append("&filename=");
                stringBuilder.append(URLEncoder.encode(cVar.h()[1], "UTF-8"));
            }
            if (!i.isOnline()) {
                stringBuilder.append("&mediatype=");
                stringBuilder.append(URLEncoder.encode(cVar.h()[2], "UTF-8"));
            }
            stringBuilder.append("&x=");
            stringBuilder.append(com.sds.android.ttpod.common.c.a.d());
            stringBuilder.append("&y=");
            stringBuilder.append(com.sds.android.ttpod.common.c.a.e());
            if (i.isOnline()) {
                stringBuilder.append("&song_id=");
                stringBuilder.append(i.getSongID());
                long artistID = i.getArtistID();
                if (0 != artistID) {
                    stringBuilder.append("&singer_id=");
                    stringBuilder.append(artistID);
                }
            }
            stringBuilder.append("&auto=");
            stringBuilder.append(1);
            stringBuilder.append("&s=");
            stringBuilder.append(EnvironmentUtils.b.b());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuilder.toString();
    }

    static {
        c.add("result.xml");
        c.add("result.json");
        c.add("info.json");
        c.add("userpic.json");
        c.add("uopicid");
    }

    private String d(String str) {
        if (m.a(this.b)) {
            SearchMediaLinkInfo a = com.sds.android.ttpod.framework.storage.database.a.a(BaseApplication.e().getContentResolver(), str);
            if (!(a == null || m.a(a.getArtist()))) {
                this.b = a.getArtist();
            }
        }
        return this.b;
    }

    protected ArrayList<String> a(String str, String str2) {
        ArrayList<String> arrayList;
        MediaItem i = e().i();
        String d = d(i.getID());
        if (m.a(d)) {
            d = e.o(i.getArtist());
            if (!TTTextUtils.isValidateMediaString(d)) {
                return null;
            }
        }
        File[] listFiles = new File(com.sds.android.ttpod.framework.a.t() + File.separator + d).listFiles(new FilenameFilter(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public boolean accept(File file, String str) {
                String toLowerCase = str.toLowerCase();
                if (c.c.contains(toLowerCase)) {
                    return false;
                }
                toLowerCase = e.m(toLowerCase);
                if ("thumb".equals(toLowerCase) || "tmp".equals(toLowerCase)) {
                    return false;
                }
                return true;
            }
        });
        if (listFiles != null) {
            arrayList = new ArrayList(listFiles.length);
            for (File absolutePath : listFiles) {
                arrayList.add(absolutePath.getAbsolutePath());
            }
        } else {
            arrayList = null;
        }
        return arrayList;
    }

    public c(com.sds.android.ttpod.framework.support.search.a.c cVar) {
        this.a = cVar;
    }

    protected com.sds.android.ttpod.framework.support.search.a.c e() {
        return this.a;
    }

    protected String b(MediaItem mediaItem) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        String localDataSource = mediaItem.getLocalDataSource();
        if (m.a(localDataSource)) {
            return null;
        }
        String str = com.sds.android.ttpod.framework.a.u() + File.separator + com.sds.android.ttpod.framework.modules.search.a.b.a(localDataSource);
        if (!c(str)) {
            File file = new File(str);
            if (!file.exists() || file.delete()) {
                MediaTag createMediaTag = MediaTag.createMediaTag(localDataSource, true);
                if (createMediaTag != null) {
                    byte[] cover = createMediaTag.cover();
                    createMediaTag.close();
                    if (cover != null && cover.length > 0) {
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                fileOutputStream.write(cover);
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            } catch (Exception e3) {
                                e2 = e3;
                                try {
                                    e2.printStackTrace();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (Exception e22) {
                                            e22.printStackTrace();
                                        }
                                    }
                                    createMediaTag.close();
                                    if (c(str)) {
                                        return null;
                                    }
                                    return str;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (Exception e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            }
                        } catch (Exception e5) {
                            e22 = e5;
                            fileOutputStream = null;
                            e22.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            createMediaTag.close();
                            if (c(str)) {
                                return null;
                            }
                            return str;
                        } catch (Throwable th3) {
                            fileOutputStream = null;
                            th = th3;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                    createMediaTag.close();
                }
            }
        }
        if (c(str)) {
            return null;
        }
        return str;
    }

    protected boolean c(String str) {
        boolean z = !m.a(str);
        if (!z) {
            return z;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return options.outMimeType != null;
    }
}
