package com.sds.android.ttpod.component.d;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import java.util.List;

/* PopupsListAdapter */
public class e<M extends com.sds.android.ttpod.component.b.a> extends BaseAdapter {
    private Context a;
    private List<M> b;

    /* PopupsListAdapter */
    public static class a {
        private IconTextView a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private IconTextView e;
        private TextView f;
        private TextView g;
        private ImageView h;

        public a(ViewGroup viewGroup) {
            this.a = (IconTextView) viewGroup.findViewById(R.id.icon_left);
            this.b = (ImageView) viewGroup.findViewById(R.id.icon_right);
            this.c = (TextView) viewGroup.findViewById(R.id.title);
            this.d = (TextView) viewGroup.findViewById(R.id.title_description);
            this.f = (TextView) viewGroup.findViewById(R.id.subtitle);
            this.e = (IconTextView) viewGroup.findViewById(R.id.title_icon);
            this.g = (TextView) viewGroup.findViewById(R.id.tv_action);
            this.h = (ImageView) viewGroup.findViewById(R.id.item_click_arrow);
        }

        public TextView a() {
            return this.g;
        }

        public IconTextView b() {
            return this.a;
        }

        public TextView c() {
            return this.c;
        }

        public TextView d() {
            return this.f;
        }

        public ImageView e() {
            return this.b;
        }

        public IconTextView f() {
            return this.e;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public e(Context context, List<M> list) {
        this.a = context;
        this.b = list;
    }

    public void a(List<M> list) {
        this.b = list;
        notifyDataSetChanged();
    }

    public List<M> b() {
        return this.b;
    }

    public int getCount() {
        return this.b == null ? 0 : this.b.size();
    }

    public M a(int i) {
        return this.b == null ? null : (com.sds.android.ttpod.component.b.a) this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    protected int a() {
        return R.layout.popups_list_item;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        if (view == null) {
            inflate = View.inflate(this.a, a(), null);
            a aVar = new a((ViewGroup) inflate);
            inflate.setTag(aVar);
            a(aVar);
        } else {
            inflate = view;
        }
        a(i, inflate);
        return inflate;
    }

    protected void a(a aVar) {
    }

    private void a(int i, View view) {
        a aVar = (a) view.getTag();
        if (this.b != null) {
            com.sds.android.ttpod.component.b.a aVar2 = (com.sds.android.ttpod.component.b.a) this.b.get(i);
            aVar.c().setText(aVar2.d());
            if (TextUtils.isEmpty(aVar2.j())) {
                aVar.d().setVisibility(8);
            } else {
                aVar.d().setText(aVar2.j());
            }
            a(aVar, aVar2);
        }
    }

    protected void a(a aVar, M m) {
        if (aVar.b() != null || aVar.e() != null) {
            int l = m.l();
            if (com.sds.android.ttpod.component.b.a.a.TITLE_ICON == m.m()) {
                aVar.f().setText(l);
                if (R.string.icon_text_wusun == l) {
                    aVar.f().setTextColor(-2185667);
                } else if (R.string.icon_text_gaozhi == l) {
                    aVar.f().setTextColor(-8665764);
                }
                aVar.b().setVisibility(8);
                aVar.e().setVisibility(8);
                aVar.f().setVisibility(0);
                return;
            }
            aVar.f().setVisibility(8);
            l = m.e();
            Drawable i = m.i();
            if (l > 0) {
                aVar.b().setVisibility(0);
                aVar.b().setText(m.e());
                aVar.b().setTextColor(m.f());
            } else {
                aVar.b().setVisibility(8);
            }
            if (i != null) {
                aVar.e().setVisibility(0);
                aVar.e().setImageDrawable(m.i());
                return;
            }
            aVar.e().setVisibility(4);
        }
    }
}
