package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.MediaController.c;

/* MediaTitleBanner */
public class d {
    private PopupWindow a;
    private Context b;
    private int c;
    private View d;
    private View e;
    private ImageButton f;
    private TextView g;
    private c h;
    private com.sds.android.ttpod.widget.MediaController.d i;
    private Handler j = new Handler(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.a();
                    return;
                default:
                    return;
            }
        }
    };

    public d(Context context) {
        this.b = context;
        this.e = View.inflate(this.b, R.layout.media_title_banner, null);
        this.f = (ImageButton) this.e.findViewById(R.id.title_back);
        this.g = (TextView) this.e.findViewById(R.id.title_content);
        c();
    }

    public void a(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    private void c() {
        this.a = new PopupWindow(this.b);
        this.a.setFocusable(false);
        this.a.setBackgroundDrawable(null);
        this.a.setOutsideTouchable(true);
        this.c = 16973824;
    }

    public void a(View view) {
        this.d = view;
        this.a.setContentView(this.e);
        this.a.setWidth(-1);
        this.a.setHeight(-2);
    }

    public void a(CharSequence charSequence) {
        this.g.setText(charSequence);
    }

    public void a() {
        if (this.a.isShowing()) {
            try {
                this.a.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            if (this.h != null) {
                this.h.a();
            }
        }
    }

    public void b() {
        a(3000);
    }

    public void a(long j) {
        if (!this.a.isShowing()) {
            int[] iArr = new int[2];
            this.d.getLocationOnScreen(iArr);
            Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.d.getWidth(), iArr[1] + this.d.getHeight());
            this.a.setAnimationStyle(this.c);
            this.a.showAtLocation(this.d, 0, rect.left, rect.top);
            if (this.i != null) {
                this.i.a();
            }
            if (j != 0) {
                this.j.removeMessages(1);
                this.j.sendMessageDelayed(this.j.obtainMessage(1), j);
            }
        }
    }
}
