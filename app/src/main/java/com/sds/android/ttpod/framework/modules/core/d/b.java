package com.sds.android.ttpod.framework.modules.core.d;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore.Audio.Media;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.media.FileMatcher;
import com.sds.android.ttpod.media.FileMatcher.CallBack;
import com.sds.android.ttpod.media.MediaTag;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;
import com.tencent.stat.DeviceInfo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* MediaScanner */
final class b {
    private int a = 0;
    private volatile b b;
    private a c;
    private boolean d;
    private String e;
    private String f;

    /* MediaScanner */
    interface a {
        void onScanFinished();
    }

    /* MediaScanner */
    private class b extends AsyncTask<Object, Object, Void> {
        final /* synthetic */ b a;
        private MediaTag b = new MediaTag();
        private final Collection<String> c = new HashSet();
        private final String d;
        private boolean e;
        private List<MediaItem> f = new ArrayList();
        private List<MediaItem> g = new ArrayList();
        private volatile String h;
        private AtomicInteger i = new AtomicInteger(0);
        private AtomicInteger j = new AtomicInteger(0);
        private List<String> k = new LinkedList();
        private List<String> l = new LinkedList();
        private List<String> m;
        private boolean n;
        private FileMatcher o = new FileMatcher(new CallBack(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onFileMatched(String str) {
                this.a.a(str);
            }

            public void onFolderMatched(String str) {
                this.a.h = str;
            }
        });

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Void) obj);
        }

        b(b bVar, Collection<String> collection, String str) {
            this.a = bVar;
            for (String str2 : collection) {
                try {
                    this.c.add(new File(str2).getCanonicalPath());
                } catch (IOException e) {
                    this.c.add(str2);
                }
            }
            this.d = str;
            this.n = str.equals(MediaStorage.GROUP_ID_ALL_LOCAL);
        }

        String a() {
            return this.h;
        }

        Integer b() {
            int intValue = ((c().intValue() + this.j.get()) * 100) / this.a.a;
            if (intValue >= 95) {
                intValue = 100;
            }
            return Integer.valueOf(intValue);
        }

        Integer c() {
            return Integer.valueOf(this.i.get());
        }

        void d() {
            cancel(true);
            this.o.stop();
        }

        protected Void a(Object... objArr) {
            String str;
            String str2;
            g.c("mediaScan", "doInBackground");
            for (String str3 : this.c) {
                g.c("scan_path", str3);
            }
            Collection<String> a = a(this.c);
            for (String str32 : a) {
                g.c("merged_scan_path", str32);
            }
            for (String str322 : a) {
                if (e.d(str322)) {
                    this.k.addAll(MediaStorage.queryMediaIDsUnderFolder(BaseApplication.e(), str322, com.sds.android.ttpod.framework.storage.environment.b.l(this.d)));
                }
            }
            this.m = MediaStorage.queryMediaIDs(BaseApplication.e(), this.d, com.sds.android.ttpod.framework.storage.environment.b.l(this.d));
            StringBuilder stringBuilder = new StringBuilder("|");
            Iterator it = com.sds.android.ttpod.framework.storage.environment.b.g().iterator();
            while (it.hasNext()) {
                str322 = (String) it.next();
                if (e.d(str322)) {
                    stringBuilder.append(str322 + "|");
                } else {
                    it.remove();
                }
            }
            this.e = com.sds.android.ttpod.framework.storage.environment.b.h();
            str322 = "|mp3|wma|aac|m4a|ape|flac|ogg|wma|cue|wav|";
            if (com.sds.android.ttpod.framework.storage.environment.b.i()) {
                str2 = str322;
            } else {
                str2 = str322 + "amr|mid|midi|";
            }
            for (String str3222 : this.c) {
                g.c("scaning", str3222);
                a(str3222, stringBuilder, str2);
            }
            f();
            if (this.a.d && this.n && !this.c.contains(d.b()) && this.i.get() == 0) {
                a(d.b(), stringBuilder, str2);
                f();
            }
            if (this.k.size() > 0) {
                MediaStorage.deleteMediaItemList(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, this.k);
                this.k.clear();
            }
            if (this.l.size() > 0) {
                MediaStorage.deleteMediaItemList(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, this.l);
                this.l.clear();
            }
            this.m = null;
            this.a.b = null;
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, c()), c.MEDIA_SCAN);
            return null;
        }

        private Collection<String> a(Collection<String> collection) {
            Collection arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Object obj = null;
            List list = arrayList2;
            for (String str : collection) {
                Object obj2 = obj;
                for (String str2 : r5) {
                    if (!str2.contains(str) && !str.contains(str2)) {
                        list.add(str2);
                        obj2 = null;
                    } else if (str.contains(str2)) {
                        list.add(str2);
                        obj2 = 1;
                    } else if (str2.contains(str)) {
                        list.add(str);
                        obj2 = 1;
                    }
                }
                if (obj2 == null) {
                    list.add(str);
                }
                r5.clear();
                obj = obj2;
                Collection collection2 = r5;
                Object obj3 = list;
                Collection collection3 = collection2;
            }
            return r5;
        }

        protected void a(Void voidR) {
            super.onPostExecute(voidR);
            this.o.release();
            if (!(isCancelled() || this.a.c == null || (this.a.b != null && this.a.b != this))) {
                this.a.c.onScanFinished();
            }
            if (m.a(com.sds.android.ttpod.framework.storage.environment.b.m(), this.d)) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_PLAYING_GROUP, new Object[0]));
            }
        }

        private void a(String str, StringBuilder stringBuilder, String str2) {
            if (!isCancelled()) {
                if (e.d(str)) {
                    if (str.endsWith("/")) {
                        str = str.substring(0, str.length() - 1);
                    }
                    this.o.start(stringBuilder.toString(), str2, com.sds.android.ttpod.framework.storage.environment.b.j(), str);
                } else if (e.b(str)) {
                    a(str);
                }
            }
        }

        private void a(String str) {
            this.h = str;
            String m = e.m(str);
            if (!m.a(m)) {
                if (m.equalsIgnoreCase("cue")) {
                    c(str);
                } else if (m.equalsIgnoreCase(DeviceInfo.TAG_MID) || m.equalsIgnoreCase("midi") || m.equalsIgnoreCase("amr")) {
                    d(str);
                } else {
                    b(str);
                }
            }
        }

        private void b(String str) {
            this.h = str;
            long currentTimeMillis;
            if (!this.b.openFile(str, true)) {
                currentTimeMillis = System.currentTimeMillis();
                a(new MediaItem(null, null, str, e.l(str), e.k(str), "", "", "", null, e.m(str), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), Long.valueOf(0), false, null, null));
                e();
            } else if (a((long) this.b.duration())) {
                currentTimeMillis = System.currentTimeMillis();
                a(new MediaItem(null, null, str, e.l(str), this.b.getTitle(), this.b.getArtist(), this.b.getAlbum(), this.b.getGenre(), null, e.m(str), Integer.valueOf(0), Integer.valueOf(this.b.duration()), Integer.valueOf(this.b.track()), Integer.valueOf(this.b.year()), null, Integer.valueOf(this.b.bitRate()), Integer.valueOf(this.b.sampleRate()), Integer.valueOf(this.b.channels()), this.b.getComment(), Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(currentTimeMillis), Long.valueOf(currentTimeMillis), Long.valueOf(0), false, null, null));
                e();
            } else {
                this.j.set(this.j.get() + 1);
            }
            this.b.close();
        }

        private void c(String str) {
            com.sds.android.ttpod.framework.modules.core.d.a.b bVar;
            try {
                bVar = new com.sds.android.ttpod.framework.modules.core.d.a.b(str);
            } catch (IOException e) {
                e.printStackTrace();
                bVar = null;
            }
            if (bVar != null) {
                if (bVar.hasNext()) {
                    String b = bVar.b();
                    MediaItem a = k.a(b);
                    if (a != null) {
                        this.l.add(a.getID());
                        int intValue = a.getBitRate().intValue();
                        int intValue2 = a.getSampleRate().intValue();
                        int intValue3 = a.getChannels().intValue();
                        String artist = TextUtils.isEmpty(bVar.c()) ? a.getArtist() : bVar.c();
                        String album = TextUtils.isEmpty(bVar.a()) ? a.getAlbum() : bVar.a();
                        String comment = a.getComment();
                        String genre = a.getGenre();
                        int intValue4 = a.getYear().intValue();
                        String title = a.getTitle();
                        int toSeconds = (int) TimeUnit.MILLISECONDS.toSeconds((long) a.getDuration().intValue());
                        com.sds.android.ttpod.framework.modules.core.d.a.a d = bVar.d();
                        while (true) {
                            com.sds.android.ttpod.framework.modules.core.d.a.a d2;
                            int f;
                            int h = d.h();
                            if (bVar.hasNext()) {
                                d2 = bVar.d();
                                f = d2.f() - h;
                            } else {
                                d2 = null;
                                f = toSeconds - h;
                            }
                            int i = f * 1000;
                            if (a((long) i)) {
                                String str2;
                                String str3;
                                String p = e.p(b);
                                String l = e.l(b);
                                if (TextUtils.isEmpty(d.c())) {
                                    str2 = title;
                                } else {
                                    str2 = d.c();
                                }
                                if (TextUtils.isEmpty(d.d())) {
                                    str3 = artist;
                                } else {
                                    str3 = d.d();
                                }
                                String m = e.m(b);
                                if (h == 0) {
                                    h = 1;
                                } else {
                                    h *= 1000;
                                }
                                a(new MediaItem(null, null, p, l, str2, str3, album, genre, null, m, Integer.valueOf(h), Integer.valueOf(i), Integer.valueOf(d.e()), Integer.valueOf(intValue4), Integer.valueOf(0), Integer.valueOf(intValue), Integer.valueOf(intValue2), Integer.valueOf(intValue3), comment, Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.currentTimeMillis()), Long.valueOf(0), false, null, null));
                                if (d2 != null) {
                                    e();
                                }
                            }
                            if (d2 == null) {
                                break;
                            }
                            d = d2;
                        }
                    }
                }
                bVar.e();
            }
        }

        private void d(String str) {
            String p = e.p(str);
            a(new MediaItem(null, null, p, e.l(p), e.k(p), "", "", "", null, e.m(p), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(System.currentTimeMillis()), Long.valueOf(System.currentTimeMillis()), Long.valueOf(0), false, null, null));
            e();
        }

        private void e() {
            this.i.set(this.i.get() + 1);
        }

        private boolean a(long j) {
            return !this.e || j > 60000;
        }

        private void a(MediaItem mediaItem) {
            com.sds.android.sdk.lib.util.k.b.a("xxx_yyy");
            com.sds.android.sdk.lib.util.k.b.a("xxx/yyy");
            if (this.k.contains(mediaItem.getID())) {
                this.k.remove(mediaItem.getID());
            } else {
                this.f.add(mediaItem);
            }
            if (!(this.n || this.m.contains(mediaItem.getID()))) {
                this.g.add(mediaItem);
            }
            if (this.f.size() > 40 || this.g.size() > 40) {
                f();
            }
        }

        private void f() {
            if (!this.f.isEmpty()) {
                MediaStorage.insertMediaItems(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, this.f);
                MediaStorage.insertMediaItems(BaseApplication.e(), MediaStorage.GROUP_ID_RECENTLY_ADD, this.f);
                this.f.clear();
            }
            if (!this.g.isEmpty()) {
                MediaStorage.insertMediaItems(BaseApplication.e(), this.d, this.g);
                this.g.clear();
            }
        }
    }

    b() {
        if (this.a == 0) {
            int e = e();
            if (e == 0) {
                e = 1;
            }
            this.a = e;
            g.d("MediaScanner", "MediaScanner mSystemMediaFileCount=%d", Integer.valueOf(this.a));
        }
        g();
    }

    void a(a aVar) {
        this.c = aVar;
    }

    void a(Collection<String> collection, String str) {
        if (collection == null || collection.size() == 0) {
            collection = Arrays.asList(f());
            this.d = true;
        }
        if (this.b != null) {
            a();
            g.b("MediaScanner", "is scanning, canceled!");
        }
        this.b = new b(this, collection, str);
        this.b.execute(new Object[0]);
    }

    void a() {
        if (this.b != null) {
            this.b.d();
            this.b = null;
        }
    }

    Integer b() {
        return Integer.valueOf(this.b != null ? this.b.b().intValue() : 100);
    }

    String c() {
        return this.b != null ? this.b.a() : null;
    }

    Integer d() {
        return Integer.valueOf(this.b != null ? this.b.c().intValue() : 0);
    }

    private int e() {
        int i;
        Exception exception;
        int i2 = 0;
        try {
            Cursor query = BaseApplication.e().getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"count(*)"}, null, null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    i = query.getInt(0);
                } else {
                    i = 0;
                }
                try {
                    query.close();
                } catch (Exception e) {
                    i2 = i;
                    exception = e;
                    exception.printStackTrace();
                    i = i2;
                    return i != 0 ? i : 1;
                }
                if (i != 0) {
                }
            }
        } catch (Exception e2) {
            exception = e2;
            exception.printStackTrace();
            i = i2;
            if (i != 0) {
            }
        }
        i = i2;
        if (i != 0) {
        }
    }

    private String[] f() {
        String b = d.b();
        String d = d.d(BaseApplication.e());
        ArrayList arrayList = new ArrayList();
        if (m.a(d) || m.a(this.f) || m.a(this.f, this.e)) {
            return new String[]{b};
        }
        arrayList.add(b);
        arrayList.add(d);
        if (j.i()) {
            arrayList.add(b(this.f));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void g() {
        String b = d.b();
        String d = d.d(BaseApplication.e());
        this.e = b;
        this.f = d;
        try {
            if (m.a(d) || b.equals(d) || !a(b) || !a(d)) {
                this.f = "";
            } else if (j.b() && Environment.isExternalStorageRemovable() && e.d(d.b(), Environment.getExternalStorageDirectory().getCanonicalPath())) {
                this.e = d.d(BaseApplication.e());
                this.f = d.b();
            }
        } catch (Exception e) {
            this.e = d.b();
            this.f = "";
            e.printStackTrace();
        }
    }

    private boolean a(String str) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String str2 = b(str) + File.separator + valueOf.toString();
        e.e(str2);
        if (!e.a(str2)) {
            return false;
        }
        e.h(str2);
        return true;
    }

    private String b(String str) {
        String str2 = str + File.separator + MediaStore.AUTHORITY;
        if (j.i() && this.f.equals(str) && !this.f.equals(d.b())) {
            str2 = d.a(BaseApplication.e(), com.sds.android.sdk.lib.util.EnvironmentUtils.d.a.SECOND_SD_CARD);
        }
        if (!e.d(str2)) {
            e.f(str2);
        }
        return str2;
    }
}
