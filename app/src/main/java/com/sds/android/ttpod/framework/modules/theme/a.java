package com.sds.android.ttpod.framework.modules.theme;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.core.download.TaskInfo;
import java.io.File;
import java.io.Serializable;

/* BackgroundItem */
public class a implements Serializable {
    protected String a;
    private String b;
    private String c;
    private a d;
    private transient Drawable e;
    private Bitmap f;
    private Bitmap g;
    private OnlineSkinItem h;
    private TaskInfo i;
    private long j;

    /* BackgroundItem */
    public enum a {
        ORIGINAL,
        ADD_BY_USER,
        ADD_VIEW,
        FOLLOW_SKIN,
        ONLINE_BACKGROUND
    }

    public a a() {
        return this.d;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public String b() {
        return this.c;
    }

    public OnlineSkinItem c() {
        return this.h;
    }

    public TaskInfo d() {
        return this.i;
    }

    public void a(TaskInfo taskInfo) {
        this.i = taskInfo;
    }

    public a(String str, a aVar) {
        this.c = str;
        this.d = aVar;
    }

    public a(OnlineSkinItem onlineSkinItem) {
        this.c = onlineSkinItem.getName() + ".jpg";
        this.d = a.ONLINE_BACKGROUND;
        this.a = com.sds.android.ttpod.framework.a.n() + File.separator + this.c;
        this.h = onlineSkinItem;
        this.j = onlineSkinItem.getDateCreated();
    }

    public a(String str) {
        this.b = str;
        if (str != null) {
            this.c = str.substring(str.lastIndexOf(File.separator) + 1);
            if (str.startsWith("assets://")) {
                this.d = a.ORIGINAL;
            } else if (str.startsWith("file://")) {
                this.d = a.ADD_BY_USER;
            } else if (str.startsWith("follow_skin")) {
                this.d = a.FOLLOW_SKIN;
            }
        }
    }

    public void a(Drawable drawable) {
        this.e = drawable;
    }

    public Drawable e() {
        return this.e;
    }

    public Bitmap f() {
        return this.f;
    }

    public void a(Bitmap bitmap) {
        this.g = bitmap;
    }

    public Bitmap g() {
        return this.g;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.d == a.ORIGINAL) {
            stringBuffer.append("assets://");
            stringBuffer.append(com.sds.android.ttpod.framework.a.q());
        } else if (this.d == a.ADD_BY_USER) {
            stringBuffer.append("file://");
            stringBuffer.append(com.sds.android.ttpod.framework.a.p());
        } else if (this.d == a.FOLLOW_SKIN) {
            stringBuffer.append("follow_skin");
            return stringBuffer.toString();
        }
        stringBuffer.append(File.separator);
        stringBuffer.append(this.c);
        return stringBuffer.toString();
    }

    public String h() {
        return this.a;
    }

    public void a(String str) {
        this.c = str + ".jpg";
        this.a = com.sds.android.ttpod.framework.a.n() + File.separator + this.c;
    }

    public long i() {
        return this.j;
    }

    public int hashCode() {
        return this.c != null ? this.c.hashCode() : 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof a) && hashCode() == obj.hashCode();
    }
}
