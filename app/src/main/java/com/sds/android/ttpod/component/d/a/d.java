package com.sds.android.ttpod.component.d.a;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.o;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.b.e;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import com.sds.android.ttpod.widget.GlobalMenuThumbImageView;
import com.sds.android.ttpod.widget.SimpleGridView;
import java.util.ArrayList;
import java.util.List;

/* GlobalMenuDialog */
public class d extends Dialog {
    private static boolean k = false;
    private static boolean l = false;
    private static boolean p = false;
    private ViewPager a;
    private a b;
    private b c;
    private View d;
    private View e;
    private Context f;
    private GlobalMenuThumbImageView g;
    private boolean h;
    private c i;
    private c j;
    private List<e> m;
    private boolean n = true;
    private e o;
    private Handler q = new Handler(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.f();
                    this.a.q.sendEmptyMessageDelayed(1, 1000);
                    return;
                default:
                    return;
            }
        }
    };
    private OnClickListener r = new OnClickListener(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
        }
    };
    private final OnPageChangeListener s = new OnPageChangeListener(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (i2 > 0) {
                this.a.g.setThumbOffset(f);
            }
        }

        public void onPageSelected(int i) {
        }

        public void onPageScrollStateChanged(int i) {
        }
    };

    /* GlobalMenuDialog */
    public interface b {
        void onMenuItemClicked(int i);
    }

    /* GlobalMenuDialog */
    private final class a extends PagerAdapter {
        final /* synthetic */ d a;
        private Context b;
        private ArrayList<List<e>> c;
        private OnClickListener d;

        private void a(List<e> list, List<e> list2) {
            this.c = new ArrayList(2);
            this.c.add(new ArrayList(list));
            this.c.add(new ArrayList(list2));
        }

        private a(d dVar, Context context) {
            this.a = dVar;
            this.d = new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!y.a()) {
                        c cVar = (c) view.getTag();
                        int b = cVar.f.b();
                        if (b == 3) {
                            new SUserEvent("PAGE_CLICK", r.ACTION_GLOBAL_MENU_PLAY_MODE.getValue(), s.PAGE_GLOBAL_MENU.getValue(), s.PAGE_NONE.getValue()).append("play_mode", Integer.valueOf(com.sds.android.ttpod.framework.storage.environment.b.l().ordinal())).post();
                            f.a();
                            com.sds.android.ttpod.framework.a.b.b.a("play_mode");
                            return;
                        }
                        switch (b) {
                            case 0:
                            case 6:
                                cVar.a(false);
                                break;
                            case 4:
                                cVar.a(false);
                                d.b(false);
                                break;
                            case 5:
                                cVar.a(false);
                                d.c(false);
                                break;
                            case 11:
                                cVar.a(false);
                                break;
                        }
                        if (this.a.a.c != null) {
                            this.a.a.c.onMenuItemClicked(b);
                        }
                    }
                }
            };
            this.b = context;
        }

        public int getCount() {
            int size = this.c.size();
            return 1;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View simpleGridView = new SimpleGridView(viewGroup.getContext());
            viewGroup.addView(simpleGridView);
            simpleGridView.setNumColumns(4);
            List list = (List) this.c.get(i);
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                e eVar = (e) list.get(i2);
                View inflate = View.inflate(this.b, R.layout.menu_global_item, null);
                c cVar = new c(inflate);
                inflate.setTag(cVar);
                inflate.setOnClickListener(this.d);
                a(cVar, eVar);
                cVar.a(eVar);
                simpleGridView.addView(inflate);
            }
            this.a.b();
            this.a.a();
            return simpleGridView;
        }

        private void a(c cVar, e eVar) {
            switch (eVar.b()) {
                case 0:
                    boolean z = VersionUpdateModule.hasNewVersion() || com.sds.android.ttpod.framework.storage.environment.b.bx();
                    eVar.a(z);
                    return;
                case 1:
                    this.a.i = cVar;
                    return;
                case 3:
                    this.a.j = cVar;
                    return;
                case 4:
                    eVar.a(d.k);
                    return;
                case 5:
                    eVar.a(d.l);
                    return;
                case 6:
                    eVar.a(com.sds.android.ttpod.framework.storage.environment.b.aX());
                    return;
                case 11:
                    eVar.a(com.sds.android.ttpod.framework.storage.environment.b.bq());
                    return;
                default:
                    return;
            }
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* GlobalMenuDialog */
    private final class c {
        final /* synthetic */ d a;
        private final IconTextView b;
        private final TextView c;
        private final IconTextView d;
        private final View e;
        private e f;

        private c(d dVar, View view) {
            this.a = dVar;
            this.b = (IconTextView) view.findViewById(R.id.itv_icon);
            this.c = (TextView) view.findViewById(R.id.tv_title);
            this.d = (IconTextView) view.findViewById(R.id.itv_indicator);
            this.e = view;
        }

        private void a(e eVar) {
            this.f = eVar;
            this.c.setText(eVar.c());
            this.d.setVisibility(eVar.d() ? 0 : 8);
            v.a(this.b, eVar.e(), eVar.a(), ThemeElement.SETTING_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.c, ThemeElement.SETTING_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.e, ThemeElement.SETTING_ITEM_BACKGROUND);
        }

        private void a(boolean z) {
            this.f.a(z);
            this.d.setVisibility(z ? 0 : 8);
        }

        private void a(CharSequence charSequence) {
            this.c.setText(charSequence);
        }

        private void a(int i) {
            String[] strArr = new String[]{ThemeElement.SETTING_PLAY_LOOP_IMAGE, ThemeElement.SETTING_PLAY_SINGLE_IMAGE, ThemeElement.SETTING_PLAY_SEQUENCE_IMAGE, ThemeElement.SETTING_PLAY_SHUFFLE_IMAGE};
            int[] iArr = new int[]{R.string.icon_repeat_play, R.string.icon_repeat_one_play, R.string.icon_sequence_play, R.string.icon_shuffle_play};
            this.f.a(new int[]{R.string.repeat_play, R.string.repeat_one_play, R.string.sequence_play, R.string.shuffle_play}[i]);
            this.f.a(strArr[i]);
            this.f.b(iArr[i]);
            a(this.f);
        }
    }

    private void f() {
        if (this.i != null) {
            long longValue = ((Long) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SLEEP_MODE_REMAIN_TIME, new Object[0]), Long.class)).longValue() / 60;
            this.i.a(getContext().getString(R.string.menu_remain_time, new Object[]{Integer.valueOf((int) longValue), Integer.valueOf((int) (r0 - (longValue * 60)))}));
        }
    }

    public void a() {
        if (this.j != null) {
            this.j.a(com.sds.android.ttpod.framework.storage.environment.b.l().ordinal());
        }
    }

    public void a(Boolean bool) {
        if (!this.m.contains(this.o) && bool.booleanValue()) {
            g();
            this.m.add(8, this.o);
        } else if (!bool.booleanValue()) {
            this.m.remove(this.o);
        }
        this.b.a(this.m.subList(0, 8), this.m.subList(8, this.m.size()));
        this.b.notifyDataSetChanged();
    }

    public void b() {
        if (this.i == null) {
            return;
        }
        if (((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
            if (!this.q.hasMessages(1)) {
                this.q.sendEmptyMessageDelayed(1, 1000);
            }
            f();
            return;
        }
        this.q.removeCallbacksAndMessages(null);
        this.i.a(getContext().getString(R.string.menu_sleep));
    }

    public void a(boolean z) {
        this.h = z;
    }

    public d(Context context, b bVar) {
        super(context, R.style.Dialog_Transparent);
        setCanceledOnTouchOutside(true);
        this.f = context;
        this.c = bVar;
        a(context);
    }

    private void a(Context context) {
        setContentView(R.layout.popups_global_menu_panel);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = com.sds.android.ttpod.common.c.a.d();
        window.setWindowAnimations(R.style.Dialog_Window_Anim);
        window.setAttributes(attributes);
        this.e = findViewById(R.id.layout_root_view);
        this.d = findViewById(R.id.content_view);
        this.g = (GlobalMenuThumbImageView) findViewById(R.id.iv_indicator);
        this.a = (ViewPager) findViewById(R.id.vp_menu);
        this.b = new a(context);
        this.m = o.a();
        a(this.m);
        this.b.a(this.m.subList(0, 8), this.m.subList(8, this.m.size()));
        this.a.setAdapter(this.b);
        this.a.setOnPageChangeListener(this.s);
        this.e.setOnClickListener(this.r);
    }

    private void a(List<e> list) {
        this.o = new e(11, 0, R.string.unicom_flow_menu_name, ThemeElement.SETTING_TRAFFIC_IMAGE, R.string.icon_unicom_flow);
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.b().startsWith("46001") && com.sds.android.ttpod.framework.storage.a.a.a().G() && com.sds.android.ttpod.framework.storage.a.a.a().H()) {
            g();
            list.add(8, this.o);
        }
    }

    private void g() {
        if (!p) {
            com.sds.android.ttpod.framework.a.b.b.a("menu_show_unicom");
            p = true;
        }
    }

    public void show() {
        super.show();
        if (((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
            this.q.sendEmptyMessage(1);
        }
        this.a.setCurrentItem(0, false);
        this.g.setThumbOffset(0.0f);
        t.a("PAGE_CLICK", r.ACTION_GLOBAL_MENU, s.PAGE_NONE, s.PAGE_GLOBAL_MENU);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
            case 82:
                dismiss();
                break;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        this.q.removeCallbacksAndMessages(null);
        super.dismiss();
    }

    private void h() {
        int f;
        int dimensionPixelSize = this.f.getResources().getDimensionPixelSize(R.dimen.global_menu_dialog_height);
        com.sds.android.ttpod.framework.modules.theme.b.e b = com.sds.android.ttpod.framework.modules.theme.c.b(ThemeElement.SETTING_BACKGROUND);
        if (b != null) {
            f = b.f();
            if (f <= dimensionPixelSize) {
                f = dimensionPixelSize;
            }
        } else {
            f = dimensionPixelSize;
        }
        this.d.getLayoutParams().height = f;
        if (isShowing()) {
            hide();
            show();
        }
    }

    private void i() {
        Drawable a = com.sds.android.ttpod.framework.modules.theme.c.a(ThemeElement.SETTING_HORIZONTAL_BACKGROUND_IMAGE);
        if (a == null) {
            com.sds.android.ttpod.framework.modules.theme.b.e b = com.sds.android.ttpod.framework.modules.theme.c.b(ThemeElement.SETTING_BACKGROUND);
            a = b != null ? b.c() : null;
            if (a == null) {
                a = new ColorDrawable(this.f.getResources().getColor(R.color.slide_menu_background));
            }
        }
        this.d.setBackgroundDrawable(a);
    }

    public void c() {
        h();
        i();
        this.g.a();
        this.b.notifyDataSetChanged();
    }

    public static void b(boolean z) {
        k = z;
    }

    public static void c(boolean z) {
        l = z;
    }
}
