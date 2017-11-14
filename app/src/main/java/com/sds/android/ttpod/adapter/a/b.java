package com.sds.android.ttpod.adapter.a;

import android.content.Context;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* ApShareChooseAdapter */
public class b extends a {
    private HashMap<String, MediaItem> b = new HashMap();
    private a c;
    private boolean d = false;

    /* ApShareChooseAdapter */
    public interface a {
        void onChooseAmountChanged();
    }

    public b(Context context, a aVar) {
        super(context);
        this.c = aVar;
    }

    protected int b() {
        return 1;
    }

    public boolean c() {
        return this.a.size() <= this.b.size();
    }

    public int d() {
        return this.b.size();
    }

    public void a(boolean z) {
        if (z) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                MediaItem a = ((com.sds.android.ttpod.fragment.apshare.a) it.next()).a();
                if (!this.b.containsKey(a.getID())) {
                    this.b.put(a.getID(), a);
                }
            }
        } else {
            this.b.clear();
        }
        notifyDataSetChanged();
    }

    public void d(com.sds.android.ttpod.fragment.apshare.a aVar) {
        MediaItem a = aVar.a();
        String id = a.getID();
        if (this.b.containsKey(id)) {
            this.b.remove(id);
        } else {
            this.b.put(id, a);
        }
        notifyDataSetChanged();
    }

    public ArrayList<MediaItem> e() {
        ArrayList<MediaItem> arrayList = new ArrayList(this.b.size());
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            MediaItem a = ((com.sds.android.ttpod.fragment.apshare.a) it.next()).a();
            if (this.b.containsKey(a.getID())) {
                arrayList.add(a);
                if (a.getSize() <= 0) {
                    a.setSize(e.g(a.getLocalDataSource()));
                }
            }
        }
        return arrayList;
    }

    public void f() {
        this.d = true;
        notifyDataSetChanged();
    }

    public void a(List<com.sds.android.ttpod.fragment.apshare.a> list) {
        super.a((List) list);
        a(true);
    }

    protected void a(e eVar, com.sds.android.ttpod.fragment.apshare.a aVar) {
        if (this.d) {
            eVar.a();
        }
        eVar.a(aVar, this.b.containsKey(aVar.a().getID()));
    }

    public void a(boolean z, com.sds.android.ttpod.fragment.apshare.a aVar) {
        super.a(z, aVar);
        MediaItem a = aVar.a();
        if (z) {
            this.b.put(a.getID(), a);
        } else {
            this.b.remove(a.getID());
        }
        this.c.onChooseAmountChanged();
    }
}
