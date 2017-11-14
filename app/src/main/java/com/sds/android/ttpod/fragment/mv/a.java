package com.sds.android.ttpod.fragment.mv;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.StringResult;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mv.b;
import com.sds.android.ttpod.activities.mv.c;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.video.d;
import com.sds.android.ttpod.framework.a.b.d.e;
import com.sds.android.ttpod.framework.a.b.d.i;
import com.sds.android.ttpod.framework.a.f;

/* MVDetailViewHolder */
public class a {
    private Activity a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private IconTextView j;
    private IconTextView k;
    private IconTextView l;
    private View m;
    private View n;
    private View o;
    private View p;
    private View q;
    private View r;
    private View s;
    private boolean t;
    private int u = 0;
    private int v;
    private int w;
    private MvData x;
    private c y;
    private b z;

    static /* synthetic */ int c(a aVar) {
        int i = aVar.v + 1;
        aVar.v = i;
        return i;
    }

    static /* synthetic */ int d(a aVar) {
        int i = aVar.v - 1;
        aVar.v = i;
        return i;
    }

    static /* synthetic */ int f(a aVar) {
        int i = aVar.w + 1;
        aVar.w = i;
        return i;
    }

    static /* synthetic */ int g(a aVar) {
        int i = aVar.w - 1;
        aVar.w = i;
        return i;
    }

    public a(Activity activity, c cVar, View view, ViewGroup viewGroup) {
        this.a = activity;
        this.y = cVar;
        this.b = LayoutInflater.from(activity).inflate(R.layout.mv_detail_information, viewGroup, false);
        this.c = (TextView) view.findViewById(R.id.tv_praise_count);
        this.d = (TextView) view.findViewById(R.id.tv_step_count);
        this.j = (IconTextView) view.findViewById(R.id.itv_mv_praise);
        this.k = (IconTextView) view.findViewById(R.id.itv_mv_step);
        this.m = view.findViewById(R.id.mv_menu_praise);
        this.n = view.findViewById(R.id.mv_menu_step);
        this.o = view.findViewById(R.id.mv_menu_download);
        this.p = view.findViewById(R.id.mv_menu_share);
        this.s = this.b.findViewById(R.id.v_mv_similar_divider);
        this.s.setVisibility(8);
        this.e = (TextView) this.b.findViewById(R.id.tv_mv_title);
        this.f = (TextView) this.b.findViewById(R.id.tv_mv_play_count);
        this.i = (TextView) this.b.findViewById(R.id.tv_mv_danmaku_count);
        this.g = (TextView) this.b.findViewById(R.id.tv_mv_description);
        this.h = (TextView) this.b.findViewById(R.id.tv_expand);
        this.q = this.b.findViewById(R.id.layout_indicator);
        this.r = this.b.findViewById(R.id.v_expand);
        this.l = (IconTextView) this.b.findViewById(R.id.itv_expand);
        ((TextView) this.q.findViewById(R.id.tv_title_intro)).setText(R.string.recommend_mv);
        this.q.findViewById(R.id.tv_post_introduction_tweet).setVisibility(8);
        this.q.setVisibility(8);
        this.b.setVisibility(8);
        c();
    }

    private void c() {
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                if (this.a.x != null) {
                    i.b("up");
                    boolean z = this.a.u == 0;
                    a aVar = this.a;
                    if (z) {
                        i = 1;
                    }
                    aVar.u = i;
                    this.a.c.setText(f.a(z ? a.c(this.a) : a.d(this.a)));
                    this.a.c(true);
                }
            }
        });
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.x != null) {
                    int i;
                    i.b("down");
                    boolean z = this.a.u == 0;
                    a aVar = this.a;
                    if (z) {
                        i = 2;
                    } else {
                        i = 0;
                    }
                    aVar.u = i;
                    this.a.d.setText(f.a(z ? a.f(this.a) : a.g(this.a)));
                    this.a.c(false);
                }
            }
        });
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.y != null) {
                    e.a("mv_origin", "mv_portrait_player");
                    i.b("download_button");
                    d.a(this.a.a, this.a.y);
                }
            }
        });
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.x != null && this.a.x.getId() > 0 && this.a.z != null) {
                    i.b("share_button");
                    this.a.z.a(this.a.x);
                }
            }
        });
    }

    public void a(MvData mvData) {
        if (mvData != null) {
            this.b.setVisibility(0);
            this.x = mvData;
            this.c.setText(f.a(this.x.getPraiseCount()));
            this.d.setText(f.a(this.x.getBlameCount()));
            this.v = this.x.getPraiseCount();
            this.w = this.x.getBlameCount();
            String singerName = mvData.getSingerName();
            this.e.setText(m.a(singerName) ? mvData.getName() : mvData.getName() + " - " + singerName);
            this.f.setText(f.a(mvData.getPlayCount()));
            this.i.setText(f.a(mvData.getDanmakuCount()));
            if (m.a(mvData.getDesc())) {
                this.g.setVisibility(8);
            } else {
                this.g.setText(mvData.getDesc());
            }
            this.u = mvData.getOperatorType();
            if (mvData.getOperatorType() == 1 || mvData.getOperatorType() == 2) {
                boolean z;
                if (mvData.getOperatorType() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                c(z);
            }
            this.g.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    Layout layout = this.a.g.getLayout();
                    if (layout != null) {
                        int lineCount = layout.getLineCount();
                        if (lineCount >= 0 && layout.getEllipsisCount(lineCount - 1) > 0) {
                            this.a.r.setVisibility(0);
                            this.a.t = false;
                        }
                    }
                }
            });
            this.r.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int integer = this.a.a.getResources().getInteger(R.integer.mv_description_lines);
                    int integer2 = this.a.a.getResources().getInteger(R.integer.mv_description_max_lines);
                    TextView l = this.a.g;
                    if (!this.a.t) {
                        integer = integer2;
                    }
                    l.setMaxLines(integer);
                    this.a.h.setText(this.a.t ? R.string.expand : R.string.collapse);
                    this.a.l.setText(this.a.t ? R.string.icon_expand : R.string.icon_collapse);
                    this.a.t = !this.a.t;
                }
            });
        }
    }

    private void c(boolean z) {
        int i = R.color.mv_praise_step_pressed;
        boolean z2 = true;
        if (z) {
            boolean z3;
            this.j.setText(this.u == 1 ? R.string.icon_finger_up_pressed : R.string.icon_finger_up);
            this.j.setTextColor(this.a.getResources().getColor(this.u == 1 ? R.color.mv_praise_step_pressed : R.color.mv_portrait_menu_icon_color));
            View view = this.n;
            if (this.u != 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            view.setEnabled(z3);
            return;
        }
        this.k.setText(this.u == 2 ? R.string.icon_finger_down_pressed : R.string.icon_finger_down);
        if (this.u != 2) {
            i = R.color.mv_portrait_menu_icon_color;
        }
        this.k.setTextColor(this.a.getResources().getColor(i));
        View view2 = this.m;
        if (this.u == 2) {
            z2 = false;
        }
        view2.setEnabled(z2);
    }

    public void a(StringResult stringResult) {
        if (stringResult.getCode() == 0) {
            com.sds.android.ttpod.component.d.f.a("Error");
        }
    }

    public void a(boolean z) {
        this.s.setVisibility(z ? 0 : 8);
    }

    public View a() {
        return this.b;
    }

    public void b(boolean z) {
        if (z) {
            this.q.setVisibility(0);
        }
    }

    public void a(b bVar) {
        this.z = bVar;
    }

    public void b() {
        if (this.x != null && this.u != this.x.getOperatorType()) {
            int i = this.x.getOperatorType() == 0 ? this.u : this.x.getOperatorType() == 1 ? this.u == 0 ? 1 : 2 : this.u == 0 ? 2 : 1;
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PRAISE_OR_STEP_MV, String.valueOf(this.x.getId()), Integer.valueOf(i)));
        }
    }
}
