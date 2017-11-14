package com.sds.android.ttpod.framework.modules;

import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;

/* MediaItemListResult */
public class b extends BaseResult {
    private f a;
    private ArrayList<MediaItem> b;

    public void a(f fVar) {
        this.a = fVar;
    }

    public void a(ArrayList<MediaItem> arrayList) {
        this.b = arrayList;
    }

    public ArrayList<MediaItem> a() {
        return this.b;
    }

    public f b() {
        return this.a;
    }
}
