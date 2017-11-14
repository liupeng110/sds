package com.sds.android.ttpod.framework.modules.core.d;

import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.core.d.b.e;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/* MediaScanModule */
public final class a extends b implements a {
    private b a = new b();
    private e b;

    protected c id() {
        return c.MEDIA_SCAN;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.START_SCAN, i.a(cls, "startScan", Collection.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.STOP_SCAN, i.a(cls, "stopScan", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SCAN_PROGRESS, i.a(cls, "progress", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SCANNED_FILE_COUNT, i.a(cls, "scannedFileCount", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.SCANNING_FILE_PATH, i.a(cls, "scanningFilePath", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.START_WIFI_TRANSMISSION, i.a(cls, "startWifiTransmission", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.STOP_WIFI_TRANSMISSION, i.a(cls, "stopWifiTransmission", new Class[0]));
    }

    public a() {
        this.a.a((a) this);
    }

    public void startScan(Collection<String> collection, String str) {
        d.a((Object) str, "groupID");
        if (str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX)) {
            this.a.a((Collection) collection, str);
            return;
        }
        throw new IllegalArgumentException("groupID " + str + " can not be scan into!");
    }

    public void stopScan() {
        this.a.a();
    }

    public Integer progress() {
        return this.a.b();
    }

    public Integer scannedFileCount() {
        return this.a.d();
    }

    public String scanningFilePath() {
        return this.a.c();
    }

    public void startWifiTransmission() {
        stopWifiTransmission();
        this.b = new e();
        this.b.b();
    }

    public void stopWifiTransmission() {
        try {
            if (this.b != null) {
                this.b.c();
                this.b = null;
                a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onScanFinished() {
        this.a = null;
        a();
    }

    private void a() {
        if (this.b == null && this.a == null) {
            com.sds.android.ttpod.framework.modules.d.a().a(id());
        }
    }
}
