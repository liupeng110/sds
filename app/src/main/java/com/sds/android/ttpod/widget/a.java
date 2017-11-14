package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* AutoCompleteView */
public class a extends PopupWindow {
    private static final int a = com.sds.android.ttpod.common.c.a.a(240);
    private ViewGroup b;
    private Context c;
    private a d;
    private final ViewGroup e;
    private int f = 4;
    private int g = (this.f << 1);
    private int h = 10;
    private Drawable i;
    private int j;
    private Handler k = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                this.a.c();
            }
        }
    };
    private OnClickListener l = new OnClickListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            String charSequence = ((TextView) view).getText().toString();
            if (this.a.d != null) {
                this.a.d.a(charSequence);
            }
        }
    };

    /* AutoCompleteView */
    public interface a {
        void a(String str);
    }

    public a(Context context) {
        super(context);
        this.c = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_auto_complete, null);
        setContentView(inflate);
        this.b = (ViewGroup) inflate.findViewById(R.id.containerView);
        this.e = (ViewGroup) inflate.findViewById(R.id.scroll_container);
        setBackgroundDrawable(new ColorDrawable(0));
        setAnimationStyle(R.style.auto_complete_popup);
        setTouchable(true);
        setOutsideTouchable(true);
    }

    public void a(String str) {
        View view;
        int i = 0;
        boolean z = this.j > 0 && this.i != null;
        if (z) {
            view = new View(this.c);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.j);
            view.setBackgroundDrawable(this.i);
            this.b.addView(view, layoutParams);
        }
        view = new TextView(this.c);
        view.setText(str);
        view.setBackgroundResource(R.drawable.xml_listselector_item_default);
        view.setTextColor(Color.parseColor("#666666"));
        view.setTextSize(15.0f);
        view.setPadding(10, this.h, 0, this.h);
        view.setGravity(16);
        view.setClickable(true);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        view.setOnClickListener(this.l);
        int childCount = this.b.getChildCount();
        if (z) {
            i = 6;
        }
        if (childCount > i + 6 && this.e.getTag() == null) {
            LayoutParams layoutParams3 = this.e.getLayoutParams();
            layoutParams3.height = a;
            if (z) {
                layoutParams3.height += this.j * 6;
            }
            this.e.setTag(Integer.valueOf(layoutParams3.height));
            this.e.setLayoutParams(layoutParams3);
        }
        if (this.b.getTag() != null) {
            layoutParams3 = this.e.getLayoutParams();
            layoutParams3.height = -2;
            this.e.setLayoutParams(layoutParams3);
            this.e.setTag(null);
        }
        this.b.addView(view, layoutParams2);
    }

    public void a() {
        if (this.b.getChildCount() > 0) {
            this.b.removeAllViews();
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void showAsDropDown(View view) {
        setWidth(view.getWidth() - this.f);
        setHeight(-2);
        try {
            super.showAsDropDown(view, this.f, -this.g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismiss() {
        this.k.sendEmptyMessageDelayed(1, 200);
    }

    public void b() {
        this.k.removeMessages(1);
    }

    public void c() {
        if (isShowing()) {
            try {
                super.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(View view) {
        if (this.b.getChildCount() == 0) {
            c();
        } else if (!isShowing()) {
            showAsDropDown(view);
        }
    }
}
