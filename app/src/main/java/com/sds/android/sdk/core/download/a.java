package com.sds.android.sdk.core.download;

import com.sds.android.sdk.lib.e.b;
import com.sds.android.sdk.lib.util.m;
import java.util.ArrayList;
import java.util.List;

/* Manager */
public final class a {
    private static a a = null;
    private List<b> b = new ArrayList();

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private b c(String str) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (((b) this.b.get(size)).a().equals(str)) {
                return (b) this.b.get(size);
            }
        }
        return null;
    }

    public void a(String str, int i) {
        if (m.a(str)) {
            throw new IllegalStateException(str + " is empty!");
        } else if (c(str) != null) {
            throw new IllegalStateException(str + " already existed!!");
        } else {
            this.b.add(new b(str, i, 15));
        }
    }

    public boolean a(String str) {
        if (!m.a(str)) {
            return c(str) != null;
        } else {
            throw new IllegalStateException(str + " is empty!");
        }
    }

    public void b(String str) {
        this.b.remove(c(str));
    }

    public void a(String str, TaskInfo taskInfo, com.sds.android.sdk.core.download.b.a aVar) {
        b c = c(str);
        if (c == null) {
            throw new IllegalStateException(str + " not exist!");
        }
        Runnable bVar = new b(taskInfo, aVar);
        taskInfo.setAttachTask(bVar);
        c.a(bVar);
    }
}
