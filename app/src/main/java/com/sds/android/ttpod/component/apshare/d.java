package com.sds.android.ttpod.component.apshare;

import com.b.a.a.c;
import com.sds.android.ttpod.fragment.apshare.a;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.tencent.open.SocialConstants;
import java.io.Serializable;

/* ExchangedItem */
public class d implements Serializable {
    @c(a = "_id")
    private String a;
    @c(a = "_data")
    private String b;
    @c(a = "title")
    private String c;
    @c(a = "mime_type")
    private String d;
    @c(a = "artist")
    private String e;
    @c(a = "album")
    private String f;
    @c(a = "_size")
    private String g;
    @c(a = "duration")
    private String h;
    @c(a = "sender")
    private String i;
    @c(a = "receiver")
    private String j;
    @c(a = "receiver_ip")
    private String k;

    public d(MediaItem mediaItem, String str, String str2, String str3) {
        this.a = mediaItem.getID();
        this.b = mediaItem.getLocalDataSource();
        this.c = mediaItem.getTitle();
        this.d = mediaItem.getMimeType();
        this.e = mediaItem.getArtist();
        this.f = mediaItem.getAlbum();
        this.g = String.valueOf(mediaItem.getSize());
        this.h = String.valueOf(mediaItem.getDuration());
        this.i = str;
        this.j = str2;
        this.k = str3;
    }

    public d(a aVar) {
        this(aVar.a(), aVar.c(), aVar.d(), aVar.e());
    }

    public d() {
        this(MediaItem.MEDIA_ITEM_NULL, "", "", "");
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public String toString() {
        return "_id: " + this.a + ", " + "title" + ": " + this.c + ", " + MediasColumns.MIME_TYPE + ": " + this.d + ", " + "_data" + ": " + this.b + ", " + "album" + ": " + this.f + ", " + "artist" + ": " + this.e + ", " + MediasColumns.SIZE + ": " + this.g + ", " + "duration" + ": " + this.h + ", " + "sender" + ": " + this.i + ", " + SocialConstants.PARAM_RECEIVER + ": " + this.j + ", " + "receiver_ip" + ": " + this.k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (this.c.equals(dVar.a()) && this.d.equals(dVar.b()) && this.f.equals(dVar.d()) && this.e.equals(dVar.c()) && this.g.equals(dVar.e()) && this.h.equals(dVar.f())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.c.hashCode() & this.d.hashCode()) & this.f.hashCode()) & this.e.hashCode()) & this.g.hashCode()) & this.h.hashCode();
    }

    public a g() {
        int intValue;
        long longValue;
        int i;
        NumberFormatException e;
        MediaItem mediaItem;
        a aVar;
        long j = 0;
        try {
            intValue = Integer.valueOf(this.h).intValue();
            try {
                longValue = Long.valueOf(this.g).longValue();
                i = intValue;
            } catch (NumberFormatException e2) {
                e = e2;
                e.printStackTrace();
                longValue = j;
                i = intValue;
                mediaItem = new MediaItem(this.a, Long.valueOf(0), this.b, "", this.c, this.e, this.f, "", "", this.d, Integer.valueOf(0), Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), false, "", "");
                mediaItem.setSize(longValue);
                aVar = new a(mediaItem);
                aVar.b(this.i);
                aVar.c(this.j);
                aVar.d(this.k);
                return aVar;
            }
        } catch (NumberFormatException e3) {
            NumberFormatException numberFormatException = e3;
            intValue = 0;
            e = numberFormatException;
            e.printStackTrace();
            longValue = j;
            i = intValue;
            mediaItem = new MediaItem(this.a, Long.valueOf(0), this.b, "", this.c, this.e, this.f, "", "", this.d, Integer.valueOf(0), Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), false, "", "");
            mediaItem.setSize(longValue);
            aVar = new a(mediaItem);
            aVar.b(this.i);
            aVar.c(this.j);
            aVar.d(this.k);
            return aVar;
        }
        mediaItem = new MediaItem(this.a, Long.valueOf(0), this.b, "", this.c, this.e, this.f, "", "", this.d, Integer.valueOf(0), Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), false, "", "");
        mediaItem.setSize(longValue);
        aVar = new a(mediaItem);
        aVar.b(this.i);
        aVar.c(this.j);
        aVar.d(this.k);
        return aVar;
    }
}
