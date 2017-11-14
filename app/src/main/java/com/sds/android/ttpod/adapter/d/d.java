package com.sds.android.ttpod.adapter.d;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a;
import com.sds.android.ttpod.component.c.c;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* PlayOnlineMediaTask */
public class d {
    private List<MediaItem> a = new ArrayList();
    private View b;
    private a c;
    private c d;
    private Context e;

    public d(Context context, View view, a aVar, List<MediaItem> list, c cVar) {
        this.e = context;
        this.c = aVar;
        this.b = view;
        this.a = list;
        this.d = cVar;
    }

    public void a(final Post post) {
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<List<Long>, OnlineMediaItemsResult>(this, f.c(post)) {
            final /* synthetic */ d b;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((List) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((OnlineMediaItemsResult) obj);
            }

            protected OnlineMediaItemsResult a(List<Long> list) {
                this.b.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.b.b != null) {
                            this.a.b.a(this.a.b.b, post, this.a.b.c, this.a.b.d);
                        }
                    }
                });
                return (OnlineMediaItemsResult) r.a((Collection) list).g();
            }

            protected void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList dataList = onlineMediaItemsResult.getDataList();
                this.b.a.clear();
                Iterator it = dataList.iterator();
                while (it.hasNext()) {
                    this.b.a.add(k.a((OnlineMediaItem) it.next()));
                }
                this.b.b(post);
            }
        });
    }

    private void b(Post post) {
        if (!this.a.isEmpty()) {
            o.a(com.sds.android.ttpod.framework.storage.a.a.a().M().getSongID().longValue(), this.a, c.a(post));
        }
    }

    private void a(View view, Post post, a aVar, c cVar) {
        boolean z = false;
        if (!c(post)) {
            view.setEnabled(true);
            view.clearAnimation();
            view.setSelected(false);
        } else if (cVar == c.LOADING) {
            view.setEnabled(false);
            view.setSelected(true);
            view.startAnimation(AnimationUtils.loadAnimation(this.e, R.anim.unlimited_rotate));
        } else {
            view.setEnabled(true);
            view.clearAnimation();
            if (cVar == c.PLAYING || (aVar.c() != null && cVar == c.STOP)) {
                z = true;
            }
            view.setSelected(z);
        }
    }

    private boolean c(Post post) {
        return c.a(c.a(post), post);
    }
}
