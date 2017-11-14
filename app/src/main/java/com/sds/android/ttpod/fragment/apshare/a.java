package com.sds.android.ttpod.fragment.apshare;

import android.support.v4.os.EnvironmentCompat;
import com.sds.android.ttpod.adapter.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* TransmittableMediaItem */
public class a {
    private MediaItem a;
    private int b;
    private a c;
    private f d;
    private String e;
    private String f;
    private String g;
    private long h;
    private String i;

    /* TransmittableMediaItem */
    public enum a {
        TRANSMIT_IDLE(0),
        WAITING(1),
        TRANSMITTING(2),
        TRANSMIT_FINISHED(3),
        TRANSMIT_FAILED(4),
        TRANSMIT_CANCELLED(5);
        
        private int mValue;

        private a(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }

        public a valueOf(int i) {
            if (i < 0 || i > 3) {
                return TRANSMIT_IDLE;
            }
            return values()[i];
        }
    }

    public MediaItem a() {
        return this.a;
    }

    public a(MediaItem mediaItem, int i, a aVar) {
        this.c = a.TRANSMIT_IDLE;
        this.a = mediaItem;
        this.b = i;
        this.c = aVar;
        this.d = null;
        this.e = "";
        this.f = "";
    }

    public a(MediaItem mediaItem) {
        this(mediaItem, 0, a.TRANSMIT_IDLE);
    }

    public String b() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        if (str == null && str.equals("")) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.e = str;
    }

    public String d() {
        return this.f;
    }

    public void c(String str) {
        if (str == null && str.equals("")) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.f = str;
    }

    public String e() {
        return this.g;
    }

    public void d(String str) {
        if (str == null && str.equals("")) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.g = str;
    }

    public f f() {
        return this.d;
    }

    public void a(f fVar) {
        this.d = fVar;
    }

    public void a(long j) {
        this.h = j;
    }

    public int g() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public a h() {
        return this.c;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public String toString() {
        return "title: " + this.a.getTitle() + ", sender: " + this.e + ", receiver: " + this.f;
    }
}
