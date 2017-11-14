package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.request.f;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.findsong.FindSongCarouselFragment;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import java.util.ArrayList;

public abstract class SimpleSongView extends FrameLayout implements com.sds.android.ttpod.framework.modules.theme.c.b {
    private static final int l = ((int) (FindSongCarouselFragment.POST_IMAGE_WIDTH_HEIGHT_RATIO * ((double) com.sds.android.ttpod.common.c.a.e())));
    protected TextView a;
    protected SimpleGridView b;
    protected ColorStateList c;
    protected TextView d;
    protected View e;
    protected View f;
    protected q g;
    protected Integer h;
    protected OnClickListener i;
    protected a j;
    protected c k;
    private b m;
    private ImageView n;
    private Animation o;
    private OnLongClickListener p;

    public interface b {
        void a(Object obj);
    }

    public interface a {
        void a(int i, int i2);
    }

    public interface c {
        void a(Object obj);
    }

    protected abstract int a();

    protected abstract void b(View view, Object obj);

    public SimpleSongView(Context context) {
        this(context, null);
    }

    public SimpleSongView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = null;
        this.i = new OnClickListener(this) {
            final /* synthetic */ SimpleSongView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.m != null) {
                    this.a.m.a(view.getTag(R.id.view_bind_data));
                }
            }
        };
        this.p = new OnLongClickListener(this) {
            final /* synthetic */ SimpleSongView a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                if (this.a.k != null) {
                    this.a.k.a(view.getTag(R.id.view_bind_data));
                }
                return true;
            }
        };
        a(context);
    }

    public SimpleSongView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = null;
        this.i = /* anonymous class already generated */;
        this.p = /* anonymous class already generated */;
        a(context);
    }

    protected void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.find_song_inner_section_layout, this, true);
        this.a = (TextView) findViewById(R.id.find_song_section_title);
        this.e = findViewById(R.id.layout_title);
        this.g = new q();
        this.d = (TextView) findViewById(R.id.text_change_data);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SimpleSongView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.j != null) {
                    this.a.c();
                    this.a.j.a(this.a.g.a(), this.a.h.intValue());
                }
            }
        });
        this.n = (ImageView) findViewById(R.id.image_change_data_anim);
        this.o = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        this.o.setRepeatCount(-1);
        this.f = findViewById(R.id.layout_gridview);
        this.b = (SimpleGridView) findViewById(R.id.find_song_section_grid_view);
        this.a.setText(a());
        onThemeLoaded();
    }

    private void c() {
        this.d.setVisibility(8);
        this.n.setVisibility(0);
        this.n.startAnimation(this.o);
    }

    public void onThemeLoaded() {
        com.sds.android.ttpod.framework.modules.theme.c.a(this.a, ThemeElement.TILE_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.d, ThemeElement.TILE_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.e, ThemeElement.TILE_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.f, ThemeElement.TILE_BACKGROUND);
    }

    public void a(ArrayList arrayList, int i) {
        int i2 = 0;
        if (!j.a(arrayList)) {
            int size = arrayList.size();
            if (size > i) {
                size = i - 1;
            }
            this.b.removeAllViews();
            this.b.setVisibility(0);
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                View b = b();
                b.setTag(R.id.view_tag_index, Integer.valueOf(i2));
                b(b, obj);
                this.b.addView(b);
                a(b, obj);
                i2++;
            }
        }
    }

    public void setOnSectionViewItemClickListener(b bVar) {
        this.m = bVar;
    }

    public int getSectionItemCount() {
        return this.b.getChildCount();
    }

    protected void setSectionItemTextColor(ColorStateList colorStateList) {
        if (this.b != null && this.b.getChildCount() != 0) {
            a(this.b, colorStateList);
        }
    }

    private void a(ViewGroup viewGroup, ColorStateList colorStateList) {
        if (viewGroup != null && viewGroup.getChildCount() != 0) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, colorStateList);
                } else if (childAt instanceof TextView) {
                    ((TextView) childAt).setTextColor(colorStateList);
                }
            }
        }
    }

    protected View b() {
        View inflate = View.inflate(getContext(), R.layout.picture_with_bottom_text, null);
        TextView textView = (TextView) inflate.findViewById(R.id.item_name);
        if (this.c != null) {
            textView.setTextColor(this.c);
        }
        return inflate;
    }

    protected void a(View view, Object obj) {
        View findViewById = view.findViewById(R.id.item_click_view);
        findViewById.setTag(R.id.view_bind_data, obj);
        findViewById.setOnClickListener(this.i);
        findViewById.setOnLongClickListener(this.p);
    }

    public void setOnChangeDataClickListener(a aVar) {
        this.j = aVar;
    }

    public void setOnSectionViewItemLongClickListener(c cVar) {
        this.k = cVar;
    }

    public void setTitle(String str) {
        this.a.setText(str);
    }

    public void a(boolean z) {
        this.e.setVisibility(z ? 0 : 8);
    }

    public TextView getChangeDataTextView() {
        return this.d;
    }

    public String getTitle() {
        return this.a == null ? "" : this.a.getText().toString();
    }

    public void setPager(f fVar) {
        int i;
        int b = fVar.b();
        if (this.g.a() == 1) {
            if (b == 0) {
                b = 1;
            }
            this.g.b(b);
        }
        if (this.g.a() >= this.g.g()) {
            i = 1;
        } else {
            i = this.g.d();
        }
        this.g.c(i);
        this.d.setVisibility(b > 1 ? 0 : 4);
    }

    public void setPageSize(Integer num) {
        this.h = num;
    }
}
