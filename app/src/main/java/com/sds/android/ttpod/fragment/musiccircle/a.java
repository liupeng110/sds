package com.sds.android.ttpod.fragment.musiccircle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import com.sds.android.ttpod.widget.dragupdatelist.a.c;

/* NoHeaderPostListProxy */
public class a {
    private DragUpdateListView a;
    private StateView b;
    private View c;
    private OnClickListener d;
    private c e;

    public a(OnClickListener onClickListener, c cVar) {
        this.d = onClickListener;
        this.e = cVar;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_no_header_post_list_layout, viewGroup, false);
        this.b = (StateView) inflate.findViewById(R.id.discovery_loadingview);
        this.a = (DragUpdateListView) this.b.findViewById(R.id.musiccircle_dragupdate_listview);
        this.a.setOnStartRefreshListener(this.e);
        this.c = this.b.findViewById(R.id.loadingview_failed);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b.setState(b.LOADING);
                if (!EnvironmentUtils.c.e()) {
                    this.a.b.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a(b.FAILED);
                            f.a((int) R.string.network_error);
                        }
                    }, 200);
                } else if (this.a.d != null) {
                    this.a.d.onClick(view);
                }
            }
        });
        return inflate;
    }

    public ListView a() {
        return this.a;
    }

    public void a(b bVar) {
        if (this.b != null) {
            this.b.setState(bVar);
        }
    }

    public StateView b() {
        return this.b;
    }

    public void c() {
        if (this.a != null) {
            this.a.c();
        }
    }
}
