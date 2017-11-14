package com.sds.android.ttpod.activities.setting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Checkable;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.b.c;
import java.util.ArrayList;
import java.util.List;

/* SettingCard */
public class b extends c {
    private c[] a;
    private List<a> b = new ArrayList();
    private com.sds.android.ttpod.component.b.a.b c;

    /* SettingCard */
    class a {
        final /* synthetic */ b a;
        private View b;
        private IconTextView c = ((IconTextView) this.b.findViewById(R.id.itv_label_icon));
        private View d = this.b.findViewById(R.id.new_flag);
        private IconTextView e = ((IconTextView) this.b.findViewById(R.id.itv_action_view));
        private TextView f = ((TextView) this.b.findViewById(R.id.tv_content));
        private TextView g = ((TextView) this.b.findViewById(R.id.label_title));
        private TextView h = ((TextView) this.b.findViewById(R.id.label_subtitle));

        public a(b bVar, View view) {
            this.a = bVar;
            this.b = view;
        }

        public void a(final c cVar, final int i) {
            a(cVar);
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a c;

                public void onClick(View view) {
                    if (cVar instanceof Checkable) {
                        ((Checkable) cVar).setChecked(!((Checkable) cVar).isChecked());
                        this.c.e(cVar);
                    }
                    if (this.c.a.c != null) {
                        this.c.a.c.a(cVar, i);
                    }
                }
            });
        }

        public void a(c cVar) {
            d(cVar);
            c(cVar);
            e(cVar);
            b(cVar);
            this.b.setEnabled(cVar.c());
        }

        private void b(c cVar) {
            CharSequence j = cVar.j();
            this.h.setText(j);
            this.h.setVisibility(TextUtils.isEmpty(j) ? 8 : 0);
        }

        private void c(c cVar) {
            this.g.setText(cVar.d());
        }

        private void d(c cVar) {
            int l = cVar.l();
            if (l != 0) {
                this.c.setText(l);
                return;
            }
            Drawable i = cVar.i();
            this.c.setVisibility(i == null ? 8 : 0);
            this.c.setImageDrawable(i);
        }

        private void e(c cVar) {
            if (cVar instanceof Checkable) {
                this.f.setVisibility(4);
                this.e.setVisibility(0);
                LayoutParams layoutParams = this.e.getLayoutParams();
                layoutParams.height = com.sds.android.ttpod.common.c.a.a(30);
                layoutParams.width = com.sds.android.ttpod.common.c.a.a(48);
                this.e.setLayoutParams(layoutParams);
                this.e.setImageResource(((Checkable) cVar).isChecked() ? R.drawable.icon_setting_checked : R.drawable.icon_setting_uncheck);
                this.e.setContentDescription("" + cVar.g());
                return;
            }
            int b = cVar.b();
            if (b != 0) {
                this.f.setVisibility(4);
                this.e.setVisibility(0);
                this.e.setText(b);
            } else if (cVar.a() != null) {
                this.f.setVisibility(4);
                this.e.setVisibility(0);
                this.e.setImageDrawable(cVar.a());
            } else {
                this.f.setVisibility(0);
                this.e.setVisibility(8);
                Object h = cVar.h();
                if (h instanceof CharSequence) {
                    this.f.setText((CharSequence) h);
                }
            }
        }
    }

    public b(Context context, c[] cVarArr, int i, com.sds.android.ttpod.component.b.a.b bVar) {
        super(context, i);
        a(cVarArr);
        b();
        this.c = bVar;
    }

    public void a(c[] cVarArr) {
        this.a = cVarArr;
    }

    public c[] a() {
        return this.a;
    }

    private View g() {
        View inflate = View.inflate(d(), R.layout.activity_setting_divider, null);
        inflate.setPadding(com.sds.android.ttpod.common.c.a.a(8), 0, 0, 0);
        return inflate;
    }

    private View h() {
        return View.inflate(d(), R.layout.activity_setting_head_or_footer_divider, null);
    }

    private View i() {
        return View.inflate(d(), R.layout.activity_setting_card_item, null);
    }

    protected void b() {
        int i = 0;
        if (this.a != null && this.a.length > 0) {
            c[] cVarArr = this.a;
            int length = cVarArr.length;
            int i2 = 0;
            while (i < length) {
                c cVar = cVarArr[i];
                if (i2 == 0) {
                    a(h());
                } else {
                    a(g());
                }
                View i3 = i();
                a aVar = new a(this, i3);
                aVar.b.setTag(cVar);
                aVar.a(cVar, i2);
                this.b.add(aVar);
                a(i3);
                i2++;
                i++;
            }
            f().addView(g());
        }
    }

    public void a(c cVar, int i) {
        a aVar = (a) this.b.get(i);
        if (aVar != null) {
            aVar.a(cVar);
        }
    }

    private a b(int i) {
        for (a aVar : this.b) {
            if (((c) aVar.b.getTag()).g() == i) {
                return aVar;
            }
        }
        return null;
    }

    public View a(int i) {
        a b = b(i);
        if (b != null) {
            return b.d;
        }
        return null;
    }

    public void c() {
        if (this.a != null && this.a.length > 0) {
            int i = 0;
            for (c cVar : this.a) {
                a aVar = (a) this.b.get(i);
                if (aVar != null) {
                    aVar.a(cVar);
                }
                i++;
            }
        }
    }
}
