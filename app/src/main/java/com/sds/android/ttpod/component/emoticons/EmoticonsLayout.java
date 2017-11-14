package com.sds.android.ttpod.component.emoticons;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.igexin.download.Downloads;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import java.util.ArrayList;
import java.util.List;

public class EmoticonsLayout extends RelativeLayout implements OnItemClickListener {
    private Context a;
    private Rect b;
    private int c = 0;
    private a d;
    private ViewPager e;
    private ArrayList<View> f;
    private LinearLayout g;
    private ArrayList<ImageView> h;
    private List<List<a>> i;
    private View j;
    private EditText k;
    private List<c> l;
    private int m = 0;
    private boolean n = false;

    public interface a {
        void a(a aVar);
    }

    public EmoticonsLayout(Context context) {
        super(context);
        this.a = context;
    }

    public void setmMaxLength(int i) {
        this.c = i;
    }

    public EmoticonsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    public void a(final SlidingClosableRelativeLayout slidingClosableRelativeLayout) {
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ EmoticonsLayout b;

            public void onGlobalLayout() {
                this.b.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int height = slidingClosableRelativeLayout.getHeight();
                int height2 = slidingClosableRelativeLayout.getHeight();
                this.b.b = new Rect(0, height2 - com.sds.android.ttpod.common.c.a.a((int) Downloads.STATUS_PENDING), height, height2);
            }
        });
    }

    public Rect getRectEmoticons() {
        return this.b;
    }

    public EmoticonsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
    }

    public void setOnCorpusSelectedListener(a aVar) {
        this.d = aVar;
    }

    public void setInputEditText(EditText editText) {
        this.k = editText;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b.b().a(((Activity) this.a).getApplication());
        this.i = b.b().a();
        b();
    }

    private void b() {
        c();
        d();
        e();
        f();
    }

    private void c() {
        this.e = (ViewPager) findViewById(R.id.vp_contains);
        this.g = (LinearLayout) findViewById(R.id.image_avatar);
        this.j = findViewById(R.id.layout_emoticons);
    }

    private void d() {
        this.f = new ArrayList();
        View view = new View(this.a);
        view.setBackgroundColor(0);
        this.f.add(view);
        this.l = new ArrayList();
        for (int i = 0; i < this.i.size(); i++) {
            GridView gridView = new GridView(this.a);
            ListAdapter cVar = new c(this.a, (List) this.i.get(i));
            gridView.setAdapter(cVar);
            this.l.add(cVar);
            gridView.setOnItemClickListener(this);
            gridView.setNumColumns(7);
            gridView.setBackgroundColor(0);
            gridView.setHorizontalSpacing(1);
            gridView.setVerticalSpacing(com.sds.android.ttpod.common.c.a.a(15));
            gridView.setStretchMode(2);
            gridView.setCacheColorHint(0);
            gridView.setPadding(5, 0, 5, 0);
            gridView.setSelector(R.drawable.xml_musiccircle_emoticons_item);
            gridView.setLayoutParams(new LayoutParams(-1, -2));
            gridView.setGravity(17);
            this.f.add(gridView);
        }
        view = new View(this.a);
        view.setBackgroundColor(0);
        this.f.add(view);
    }

    private void e() {
        this.h = new ArrayList();
        int i = 0;
        while (i < this.f.size()) {
            View imageView = new ImageView(this.a);
            imageView.setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_normal);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            this.g.addView(imageView, layoutParams);
            if (i == 0 || i == this.f.size() - 1) {
                imageView.setVisibility(8);
            }
            if (i == 1) {
                imageView.setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
            }
            this.h.add(imageView);
            i++;
        }
    }

    private void f() {
        this.e.setAdapter(new d(this.f));
        this.e.setCurrentItem(1);
        this.m = 0;
        this.e.setOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ EmoticonsLayout a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                this.a.m = i - 1;
                this.a.a(i);
                if (i != this.a.h.size() - 1 && i != 0) {
                    return;
                }
                if (i == 0) {
                    this.a.e.setCurrentItem(i + 1);
                    ((ImageView) this.a.h.get(1)).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
                    return;
                }
                this.a.e.setCurrentItem(i - 1);
                ((ImageView) this.a.h.get(i - 1)).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void a(int i) {
        for (int i2 = 1; i2 < this.h.size(); i2++) {
            if (i == i2) {
                ((ImageView) this.h.get(i2)).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_selected);
            } else {
                ((ImageView) this.h.get(i2)).setBackgroundResource(R.drawable.img_musiccircle_emoticons_dot_normal);
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a aVar = (a) ((c) this.l.get(this.m)).getItem(i);
        String obj = this.k.getText().toString();
        if (aVar.b() == R.drawable.xml_musiccircle_emoticons_delete) {
            int selectionStart = this.k.getSelectionStart();
            if (selectionStart > 0) {
                if ("]".equals(obj.substring(selectionStart - 1, selectionStart))) {
                    this.k.getText().delete(obj.lastIndexOf("["), selectionStart);
                    return;
                }
                this.k.getText().delete(selectionStart - 1, selectionStart);
            }
        }
        if (!TextUtils.isEmpty(aVar.a())) {
            if (this.d != null) {
                this.d.a(aVar);
            }
            obj = obj + aVar.a();
            if (this.c <= 0 || ((float) a(obj)) / 2.0f <= ((float) this.c)) {
                this.k.append(b.b().a(getContext(), aVar.b(), aVar.a()));
                this.n = true;
            }
        }
    }

    public boolean a() {
        return this.n;
    }

    public void setUseEmoticon(boolean z) {
        this.n = z;
    }

    public static int a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return 0 + str.replaceAll("[^\\x00-\\xff]", "**").length();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
