package com.sds.android.ttpod.activities.musiccircle.radar;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.sds.android.cloudapi.ttpod.data.AlikeTTPodUser;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.tencent.open.yyb.TitleBar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* RadarAnimationManager */
public class c implements OnClickListener {
    private int A = 0;
    private int B = 0;
    private a C;
    private com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a D;
    private Activity a;
    private View b;
    private int c = 0;
    private c d = c.NONE;
    private FrameLayout e;
    private ImageView f;
    private b g;
    private Button h;
    private ImageView[] i = new ImageView[4];
    private RelativeLayout[] j = new RelativeLayout[4];
    private Point[] k = new Point[4];
    private double[] l = new double[4];
    private boolean[] m = new boolean[4];
    private Map<Object, Object> n = new HashMap();
    private int o;
    private int p = 4;
    private int q = 0;
    private List<Number> r = new ArrayList();
    private List<Number> s = new ArrayList();
    private int t = 0;
    private int u = 0;
    private List<AlikeTTPodUser> v;
    private List<a> w;
    private TextSwitcher x;
    private int y = 0;
    private String[] z;

    /* RadarAnimationManager */
    public interface a {
    }

    /* RadarAnimationManager */
    private class b {
        final /* synthetic */ c a;
        private ImageView b;
        private int c;

        public b(c cVar, ImageView imageView, int i) {
            this.a = cVar;
            this.b = imageView;
            this.c = i;
        }

        private ImageView a() {
            return this.b;
        }

        private int b() {
            return this.c;
        }
    }

    /* RadarAnimationManager */
    private enum c {
        ERROR,
        NONE,
        HAS_RESULT,
        FINISH
    }

    public void a(com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a aVar) {
        this.D = aVar;
    }

    public void a() {
        this.f.clearAnimation();
        this.n.clear();
        this.r.clear();
        this.s.clear();
        this.v.clear();
        t();
    }

    public void b() {
        this.g = new b();
        this.g.setRepeatMode(1);
        this.g.setRepeatCount(-1);
        this.g.setInterpolator(new LinearInterpolator());
        this.g.a(new com.sds.android.ttpod.activities.musiccircle.radar.b.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(float f) {
                this.a.a(f);
            }
        });
        this.g.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                if (this.a.d != c.HAS_RESULT) {
                    this.a.w();
                }
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                this.a.g();
            }
        });
        this.f.startAnimation(this.g);
        k();
    }

    private void f() {
        Collections.shuffle(this.r);
    }

    private void a(float f) {
        if (this.q >= this.p && this.p != 0) {
            this.d = c.FINISH;
        }
        ImageView b = b(f);
        if (b != null) {
            TextView textView = (TextView) this.j[this.y].findViewById(R.id.info1);
            g.a("TEST", "get point view to display.... : " + b + " status: " + this.d);
            b.setVisibility(0);
            if (this.d != c.HAS_RESULT || !a(this.y)) {
                g.a("TEST", "hideFlashAnimation start...");
                b.startAnimation(o());
                this.m[this.y] = true;
            } else if (!m.a(textView.getText().toString())) {
                g.a("TEST", "is allow to display... : " + this.y);
                Animation n = n();
                b.startAnimation(n);
                this.n.put(n, new b(this, b, this.y));
                this.q++;
                this.m[this.y] = true;
            } else if (this.y < this.v.size()) {
                g.c("TEST", "error in radar animation, wrong result:" + this.y + " name: " + ((AlikeTTPodUser) this.v.get(this.y)).getNickName() + " fans: " + ((AlikeTTPodUser) this.v.get(this.y)).getFollowersCount() + " same song: " + ((AlikeTTPodUser) this.v.get(this.y)).getSameSongCount());
            } else {
                g.c("TEST", "error in radar animation, wrong scope: " + this.y + " data size: " + this.v.size());
            }
            h();
        }
    }

    private void g() {
        g.a("TEST", "onAnimationRepeat, count: " + this.c + " status: " + this.d + " display count: " + this.q);
        this.c++;
        if (this.c >= 4) {
            if (this.q > 0) {
                this.d = c.FINISH;
                g.a("TEST", "set Status to FINISH, repeat count: " + this.c + " display count: " + this.q);
            } else {
                this.d = c.ERROR;
            }
        }
        if (this.d == c.FINISH) {
            if (this.p == 0) {
                g.a("TEST", "handleErrorStatus: " + this.d + " resultCount: " + this.p);
                j();
                return;
            }
            i();
        } else if (this.d == c.ERROR) {
            g.a("TEST", "handleErrorStatus: " + this.d);
            j();
        } else if (this.q == 0) {
            x();
            if (this.d != c.HAS_RESULT) {
                w();
            }
        }
    }

    private void h() {
        int i = this.B;
        this.B = i + 1;
        if (i % 2 == 0) {
            this.A %= this.z.length;
            TextSwitcher textSwitcher = this.x;
            String[] strArr = this.z;
            int i2 = this.A;
            this.A = i2 + 1;
            textSwitcher.setText(strArr[i2]);
        }
    }

    private void i() {
        this.f.clearAnimation();
        this.x.setVisibility(8);
        this.h.setVisibility(0);
    }

    private void j() {
        this.f.clearAnimation();
        this.f.setVisibility(4);
        f.a("知音少，弦断有谁听？");
        this.e.setBackgroundResource(R.drawable.img_musiccircle_radar_error);
        this.x.setVisibility(8);
        this.h.setVisibility(0);
    }

    public void c() {
        r();
        this.f.setVisibility(0);
        this.h.setVisibility(8);
        this.x.setText("");
        this.x.setVisibility(0);
        this.e.setBackgroundResource(R.drawable.img_musiccircle_radar);
        s();
        t();
        b();
        com.sds.android.ttpod.activities.musiccircle.c.i();
    }

    public void onClick(View view) {
        if (this.h == view) {
            c();
        }
    }

    private void k() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_ALIKE_USERS, "alike"));
    }

    public void a(AlikeUserListResult alikeUserListResult) {
        if (b(alikeUserListResult)) {
            a(alikeUserListResult.getDataList());
        }
    }

    private boolean b(AlikeUserListResult alikeUserListResult) {
        return alikeUserListResult.getCode() == 1 && alikeUserListResult.getDataList() != null && alikeUserListResult.getDataList().size() >= 1 && alikeUserListResult.getDataList().size() <= 4;
    }

    public c(Activity activity, View view) {
        this.a = activity;
        this.b = view;
    }

    public void d() {
        this.h = (Button) this.b.findViewById(R.id.rescan);
        this.z = this.a.getResources().getStringArray(R.array.poetry);
        this.h.setOnClickListener(this);
        this.h.setVisibility(8);
        this.e = (FrameLayout) this.b.findViewById(R.id.layout_radar);
        this.f = (ImageView) this.b.findViewById(R.id.radar_line_view);
        this.i[0] = (ImageView) this.b.findViewById(R.id.point1);
        this.i[1] = (ImageView) this.b.findViewById(R.id.point2);
        this.i[2] = (ImageView) this.b.findViewById(R.id.point3);
        this.i[3] = (ImageView) this.b.findViewById(R.id.point4);
        this.j[0] = (RelativeLayout) this.b.findViewById(R.id.layout_user_info1);
        this.j[1] = (RelativeLayout) this.b.findViewById(R.id.layout_user_info2);
        this.j[2] = (RelativeLayout) this.b.findViewById(R.id.layout_user_info3);
        this.j[3] = (RelativeLayout) this.b.findViewById(R.id.layout_user_info4);
        this.x = (TextSwitcher) this.b.findViewById(R.id.info);
        this.x.setFactory(new ViewFactory(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public View makeView() {
                View textView = new TextView(this.a.a);
                textView.setGravity(49);
                textView.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
                return textView;
            }
        });
        Animation loadAnimation = AnimationUtils.loadAnimation(this.a, 17432576);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.a, 17432577);
        this.x.setInAnimation(loadAnimation);
        this.x.setOutAnimation(loadAnimation2);
        l();
        this.v = new ArrayList();
        this.w = new ArrayList();
        s();
        t();
        x();
        w();
        m();
    }

    private void l() {
        this.r.add(Integer.valueOf(0));
        this.r.add(Integer.valueOf(1));
        this.r.add(Integer.valueOf(2));
        this.r.add(Integer.valueOf(3));
    }

    private void m() {
        this.A = a(this.z.length - 1, 0);
        if (this.A % 2 == 1) {
            this.A++;
        }
    }

    private Animation n() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.a, R.anim.flash_show_animation);
        loadAnimation.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
                g.a("TEST", " showFlashAnimation start...");
                b bVar = (b) this.a.n.get(animation);
                if (bVar != null) {
                    this.a.a(bVar.a(), bVar.b());
                }
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        return loadAnimation;
    }

    private Animation o() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.a, R.anim.flash_hide_animation);
        loadAnimation.setFillAfter(true);
        return loadAnimation;
    }

    public void a(List<AlikeTTPodUser> list) {
        int i = 0;
        if (!this.r.isEmpty()) {
            this.v.addAll(list);
            this.p = this.v.size();
            this.q = 0;
            if (this.p > 4) {
                this.p = 4;
            }
            this.s.clear();
            while (i < this.p) {
                this.s.add(this.r.get(i));
                i++;
            }
            g.a("TEST", "return " + this.p + " result!" + "list size:" + list.size() + " index: " + this.s.toString());
            this.d = c.HAS_RESULT;
            x();
            p();
            com.sds.android.ttpod.activities.musiccircle.c.a(this.p);
        }
    }

    public void e() {
        this.d = c.ERROR;
    }

    public void a(a aVar) {
        this.C = aVar;
    }

    private void p() {
        int i = 0;
        int i2 = 0;
        while (i < this.p) {
            Number number = (Number) this.r.get(i);
            g.a("TEST", "show number: " + number.intValue());
            RelativeLayout relativeLayout = this.j[number.intValue()];
            TextView textView = (TextView) relativeLayout.findViewById(R.id.user_name);
            TextView textView2 = (TextView) relativeLayout.findViewById(R.id.info1);
            TextView textView3 = (TextView) relativeLayout.findViewById(R.id.info2);
            relativeLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.D.a(com.sds.android.ttpod.framework.modules.core.f.b.a((AlikeTTPodUser) view.getTag()));
                }
            });
            int i3 = i2 + 1;
            AlikeTTPodUser alikeTTPodUser = (AlikeTTPodUser) this.v.get(i2);
            relativeLayout.setTag(alikeTTPodUser);
            textView.setText(alikeTTPodUser.getNickName());
            textView2.setText("粉丝：" + alikeTTPodUser.getFollowersCount());
            textView3.setText(String.format("共同歌曲: %d", new Object[]{Integer.valueOf(alikeTTPodUser.getSameSongCount())}));
            g.a("TEST", "name: " + alikeTTPodUser.getNickName() + "fans: " + alikeTTPodUser.getFollowersCount() + " avatar: " + alikeTTPodUser.getAvatarUrl());
            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.avatar);
            String avatarUrl = alikeTTPodUser.getAvatarUrl();
            int a = com.sds.android.ttpod.common.c.a.a(70);
            com.sds.android.ttpod.framework.a.g.a(imageView, avatarUrl, a, a, (int) R.drawable.img_avatar_default);
            i++;
            i2 = i3;
        }
    }

    private ImageView b(float f) {
        int i = 0;
        while (i < 4) {
            double d = this.l[i];
            if (((double) f) < d || this.m[i]) {
                i++;
            } else {
                i = c((float) d);
                this.y = i;
                return this.i[i];
            }
        }
        return null;
    }

    private boolean a(int i) {
        for (Number intValue : this.s) {
            if (i == intValue.intValue()) {
                return true;
            }
        }
        return false;
    }

    private int c(float f) {
        return (int) (f / 90.0f);
    }

    private void q() {
        for (int i = 0; i < 4; i++) {
            a(this.i[i], i);
        }
    }

    private void r() {
        this.d = c.NONE;
        this.p = 0;
        this.q = 0;
        this.c = 0;
        this.B = 0;
        this.n.clear();
        this.v.clear();
        x();
        m();
    }

    private void s() {
        int i = 0;
        for (RelativeLayout visibility : this.j) {
            visibility.setVisibility(4);
        }
        ImageView[] imageViewArr = this.i;
        int length = imageViewArr.length;
        while (i < length) {
            imageViewArr[i].setVisibility(4);
            i++;
        }
    }

    private void t() {
        RelativeLayout relativeLayout = (RelativeLayout) this.b.findViewById(R.id.root);
        for (a aVar : this.w) {
            aVar.setVisibility(8);
            aVar.a();
            relativeLayout.removeView(aVar);
        }
        this.w.clear();
    }

    private void u() {
        if (this.t == 0 || this.t == 128) {
            Rect rect = new Rect();
            this.f.getGlobalVisibleRect(rect);
            this.t = (rect.width() / 2) - 10;
        }
        if (this.t <= 0) {
            this.t = 128;
        }
        g.a("TEST", "radar radius: " + this.t);
    }

    private void v() {
        if (this.u == 0) {
            Rect rect = new Rect();
            this.i[0].getGlobalVisibleRect(rect);
            this.u = rect.width() / 2;
        }
        if (this.u < 0) {
            this.u = 10;
        }
        g.a("TEST", "point radius: " + this.u);
    }

    private void w() {
        f();
        u();
        v();
        y();
        q();
    }

    private void x() {
        g.a("TEST", "resetPointAngleFlag...");
        for (int i = 0; i < 4; i++) {
            this.m[i] = false;
        }
    }

    private void y() {
        int i = this.t - (this.u * 2);
        int i2 = (this.t / 3) + 1;
        x();
        for (int i3 = 0; i3 < 4; i3++) {
            int a = a(i, i2);
            double a2 = (double) a(70, 20);
            int sin = (int) (((double) a) * Math.sin(Math.toRadians(a2)));
            int cos = (int) (((double) a) * Math.cos(Math.toRadians(a2)));
            this.k[i3] = new Point(sin, cos);
            this.l[i3] = a(this.k[i3]) + ((double) (i3 * 90));
            g.a("TEST", "r: " + a + " x: " + sin + " y:" + cos + " a: " + a2 + " an: " + this.l[i3]);
        }
    }

    private double a(Point point) {
        return Math.atan2((double) point.x, (double) point.y) * 57.29577951308232d;
    }

    private void a(ImageView imageView, int i) {
        imageView.setVisibility(4);
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        switch (i) {
            case 0:
                layoutParams.setMargins(this.k[0].x, 0, 0, this.k[0].y);
                break;
            case 1:
                layoutParams.setMargins(this.k[1].x, this.k[1].y, 0, 0);
                break;
            case 2:
                layoutParams.setMargins(0, this.k[2].y, this.k[2].x, 0);
                break;
            default:
                layoutParams.setMargins(0, 0, this.k[3].x, this.k[3].y);
                break;
        }
        imageView.setLayoutParams(layoutParams);
    }

    private int a(int i, int i2) {
        return new Random().nextInt(i - i2) + i2;
    }

    private void a(View view, int i) {
        float width;
        float height;
        int dimension = ((int) this.a.getResources().getDimension(R.dimen.dialog_header_height)) + z();
        int[] a = a(view);
        View view2 = (ImageView) this.j[i].findViewById(R.id.avatar);
        int[] a2 = a(view2);
        a2[1] = a2[1] - dimension;
        float width2 = (float) (a[0] + (view.getWidth() / 2));
        float height2 = (float) ((a[1] + (view.getHeight() / 2)) - dimension);
        switch (i) {
            case 0:
                width = (float) (a2[0] + view2.getWidth());
                height = (float) (view2.getHeight() + a2[1]);
                break;
            case 1:
            case 2:
                width = (float) (view2.getWidth() + a2[0]);
                height = (float) a2[1];
                break;
            default:
                width = (float) (a2[0] + view2.getWidth());
                height = (float) (view2.getHeight() + a2[1]);
                break;
        }
        a(i, width2, height2, width, height);
    }

    private int[] a(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr;
    }

    private int z() {
        if (this.o == 0) {
            Rect rect = new Rect();
            this.a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            this.o = rect.top;
        }
        return this.o;
    }

    private void a(int i, float f, float f2, float f3, float f4) {
        View aVar = new a(this.a, i, new com.sds.android.ttpod.activities.musiccircle.radar.a.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                this.a.a(i, i2, i3);
            }
        });
        ((RelativeLayout) this.b.findViewById(R.id.root)).addView(aVar);
        this.w.add(aVar);
        aVar.a(f, f3, f2, f4);
    }

    private void a(int i, int i2, int i3) {
        this.j[i].setVisibility(0);
    }
}
