package com.tencent.open;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import java.io.IOException;
import java.io.InputStream;

/* ProGuard */
public class b extends Dialog {
    private TextView a;
    private TextView b;
    private Button c;
    private Button d;

    public b(Context context) {
        super(context);
        Drawable colorDrawable = new ColorDrawable();
        colorDrawable.setAlpha(0);
        getWindow().setBackgroundDrawable(colorDrawable);
        setContentView(a(context));
    }

    public b a(String str) {
        this.a.setText(str);
        return this;
    }

    public b b(String str) {
        this.b.setText(str);
        return this;
    }

    public b c(String str) {
        this.c.setText(str);
        return this;
    }

    public b d(String str) {
        this.d.setText(str);
        return this;
    }

    public b a(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
        return this;
    }

    public b b(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
        return this;
    }

    private View a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        int i = (int) (60.0f * f);
        i = (int) (60.0f * f);
        i = (int) (14.0f * f);
        i = (int) (18.0f * f);
        i = (int) (6.0f * f);
        i = (int) (18.0f * f);
        View relativeLayout = new RelativeLayout(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.a = new TextView(context);
        this.a.setTextSize(17.0f);
        this.a.setId(10);
        this.a.getPaint().setFakeBoldText(true);
        layoutParams.addRule(14);
        layoutParams.setMargins(0, 20, 0, 0);
        relativeLayout.addView(this.a, layoutParams);
        this.b = new TextView(context);
        this.b.setTextSize(16.0f);
        this.b.setIncludeFontPadding(false);
        layoutParams.setMargins(0, 20, 0, 0);
        this.b.setLines(2);
        this.b.setId(11);
        this.b.setMinWidth((int) (185.0f * f));
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(3, 10);
        relativeLayout.addView(this.b, layoutParams);
        View view = new View(context);
        view.setBackgroundColor(Color.rgb(214, 214, 214));
        view.setId(12);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, 2);
        layoutParams2.addRule(3, 11);
        layoutParams2.setMargins(0, 10, 0, (int) (12.0f * f));
        relativeLayout.addView(view, layoutParams2);
        view = new LinearLayout(context);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(5, 12);
        layoutParams2.addRule(7, 12);
        layoutParams2.addRule(3, 12);
        this.c = new Button(context);
        this.c.setBackgroundDrawable(a("buttonNegt.png", context));
        this.c.setTextColor(Color.rgb(36, 97, 131));
        this.c.setTextSize(18.0f);
        this.c.setId(14);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.weight = 1.0f;
        layoutParams3.rightMargin = (int) (14.0f * f);
        layoutParams3.leftMargin = (int) (4.0f * f);
        view.addView(this.c, layoutParams3);
        this.d = new Button(context);
        this.d.setTextSize(18.0f);
        this.d.setTextColor(Color.rgb(MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK, MotionEventCompat.ACTION_MASK));
        this.d.setBackgroundDrawable(a("buttonPost.png", context));
        layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.weight = 1.0f;
        layoutParams3.rightMargin = (int) (4.0f * f);
        view.addView(this.d, layoutParams3);
        relativeLayout.addView(view, layoutParams2);
        layoutParams3 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (163.0f * f));
        relativeLayout.setPadding((int) (TitleBar.SHAREBTN_RIGHT_MARGIN * f), 0, (int) (TitleBar.SHAREBTN_RIGHT_MARGIN * f), (int) (12.0f * f));
        relativeLayout.setLayoutParams(layoutParams3);
        relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
        Drawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
        paintDrawable.setCornerRadius(f * 5.0f);
        relativeLayout.setBackgroundDrawable(paintDrawable);
        return relativeLayout;
    }

    private Drawable a(String str, Context context) {
        IOException e;
        Drawable createFromStream;
        try {
            InputStream open = context.getApplicationContext().getAssets().open(str);
            if (open == null) {
                return null;
            }
            if (str.endsWith(".9.png")) {
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                if (decodeStream != null) {
                    return new NinePatchDrawable(decodeStream, decodeStream.getNinePatchChunk(), new Rect(), null);
                }
                return null;
            }
            createFromStream = Drawable.createFromStream(open, str);
            try {
                open.close();
                return createFromStream;
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return createFromStream;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            createFromStream = null;
            e = iOException;
            e.printStackTrace();
            return createFromStream;
        }
    }
}
