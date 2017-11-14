package com.sds.android.ttpod.framework.storage.database;

import android.content.Context;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.util.ArrayList;
import java.util.List;

/* SqliteDbWrapper */
public class c {
    public void a(DownloadTaskInfo downloadTaskInfo) {
        b.a(downloadTaskInfo);
    }

    public List<DownloadTaskInfo> b(DownloadTaskInfo downloadTaskInfo) {
        List<DownloadTaskInfo> b = b.b(downloadTaskInfo);
        return b == null ? new ArrayList() : b;
    }

    public void a(Context context) {
        b.a("SqliteDbWrapper", "init");
        b.a(context);
        b.a("SqliteDbWrapper", "init end");
    }

    public void a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
        b.a(downloadTaskInfo, downloadTaskInfo2);
    }

    public void c(DownloadTaskInfo downloadTaskInfo) {
        b.c(downloadTaskInfo);
    }
}
