package com.sds.android.ttpod.framework.support.a;

import android.content.Context;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d.p;
import com.sds.android.ttpod.framework.a.i;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.storage.environment.b.a;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.ttfm.android.sdk.embed.TTFMPlayAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* MediaSelector */
public class d {
    private List<String> a;
    private String b;
    private MediaItem c;
    private ArrayList<String> d;
    private a e;
    private Context f;

    public d(Context context) {
        this(context, false);
    }

    public d(Context context, boolean z) {
        this.b = b.m();
        this.d = new ArrayList();
        this.e = new a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(c cVar) {
                this.a.d.clear();
            }
        };
        this.f = null;
        this.f = context;
        if (z) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    i.b(false);
                    this.a.g();
                    i.b(true);
                }
            });
        } else {
            g();
        }
    }

    private void g() {
        if (!h()) {
            this.b = MediaStorage.GROUP_ID_ALL_LOCAL;
            b.d(MediaStorage.GROUP_ID_ALL_LOCAL);
            b.e("");
            b.x(MediaStorage.GROUP_ID_ALL_LOCAL);
        }
        p.a().b(b.bz());
        this.a = MediaStorage.queryMediaIDs(this.f, this.b, b.l(this.b));
        String n = b.n();
        if (!m.a(n)) {
            this.c = MediaStorage.queryMediaItem(this.f, this.b, n);
            TTFMPlayAdapter.getInstance().updatePlayingMediaInfo(this.c);
        }
        b.a(c.PLAY_MODE, this.e);
    }

    public void a(String str) {
        p.a().b(str);
    }

    private boolean h() {
        return MediaStorage.isGroupExisted(this.f, this.b) && !MediaStorage.queryMediaIDs(this.f, this.b, b.l(this.b)).isEmpty();
    }

    public void a(String str, String str2) {
        if (!m.a(this.b, str)) {
            this.d.clear();
        }
        this.c = MediaStorage.queryMediaItem(this.f, str, b.n());
        this.a = MediaStorage.queryMediaIDs(this.f, b(str) ? MediaStorage.GROUP_ID_FAV_LOCAL : str, b.l(str));
        if (!(!b(str) || this.a.isEmpty() || this.a.contains(str2))) {
            str2 = (String) this.a.get(0);
        }
        this.b = str;
        b.d(str);
        b.e(str2);
        if (b.l() == f.SHUFFLE) {
            if (this.d.size() > 0) {
                Iterator it = this.d.iterator();
                while (it.hasNext()) {
                    if (!this.a.contains(it.next())) {
                        it.remove();
                    }
                }
            }
            this.d.add(str2);
        }
    }

    private boolean b(String str) {
        return !b.av() && m.a(MediaStorage.GROUP_ID_FAV, str);
    }

    public int a() {
        return this.c != null ? this.a.indexOf(this.c.getID()) : 0;
    }

    public MediaItem b() {
        String n = b.n();
        this.b = b.m();
        if (!(this.c == null || (m.a(this.c.getID(), n) && m.a(this.c.getGroupID(), this.b)))) {
            this.c = MediaStorage.queryMediaItem(this.f, this.b, n);
        }
        return this.c;
    }

    public void a(MediaItem mediaItem) {
        this.c = mediaItem;
    }

    public void c() {
        a(false, true);
    }

    public void d() {
        a(true, true);
    }

    public void e() {
        a(true, false);
    }

    private void a(boolean z, boolean z2) {
        int i = 1;
        if (!f()) {
            String id = this.c == null ? "" : this.c.getID();
            if (this.a == null || this.a.size() <= 0) {
                id = null;
            } else {
                int size = this.a.size();
                int indexOf = this.a.indexOf(id);
                switch (b.l()) {
                    case SEQUENCE:
                        if (!z) {
                            i = -1;
                        }
                        int i2 = indexOf + i;
                        if (z2) {
                            i2 = (i2 + size) % size;
                        }
                        if (i2 >= 0 && i2 < size) {
                            id = (String) this.a.get(i2);
                            break;
                        } else {
                            id = null;
                            break;
                        }
                    case SHUFFLE:
                        if (size != 1) {
                            if (z) {
                                List arrayList = new ArrayList();
                                arrayList.addAll(this.a);
                                i = a(size);
                                int size2 = this.d.size();
                                if (size2 > i) {
                                    arrayList.removeAll(this.d.subList(size2 - i, size2));
                                } else {
                                    arrayList.removeAll(this.d);
                                }
                                if (arrayList.size() > 0) {
                                    id = (String) arrayList.get(new Random().nextInt(arrayList.size()));
                                    this.d.add(id);
                                }
                            } else if (this.d.size() > 0) {
                                this.d.add(0, id);
                                this.d.remove(this.d.size() - 1);
                                id = (String) this.d.get(this.d.size() - 1);
                            } else {
                                id = (String) this.a.get(new Random().nextInt(this.a.size()));
                                this.d.add(id);
                            }
                            if (this.d.size() > this.a.size()) {
                                this.d.remove(0);
                                break;
                            }
                        }
                        break;
                    case REPEAT:
                        id = (String) this.a.get((((z ? 1 : -1) + indexOf) + size) % size);
                        break;
                    case REPEAT_ONE:
                        if (z2) {
                            List list = this.a;
                            if (!z) {
                                i = -1;
                            }
                            id = (String) list.get(((indexOf + i) + size) % size);
                            break;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("illegal play mode!");
                }
            }
            b.e(id);
            this.c = MediaStorage.queryMediaItem(this.f, this.b, id);
        }
    }

    public boolean f() {
        if (this.c == null || !this.c.isTtfmRadioSingleSong()) {
            return false;
        }
        return true;
    }

    private int a(int i) {
        if (i <= 4) {
            return i - 1;
        }
        if (i <= 10) {
            return i - 2;
        }
        if (i <= 20) {
            return i - 4;
        }
        return i - (i / 3);
    }
}
