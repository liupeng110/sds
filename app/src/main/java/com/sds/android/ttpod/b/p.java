package com.sds.android.ttpod.b;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* OfflineModeUtils */
public class p {

    /* OfflineModeUtils */
    public interface a {
        void a();
    }

    /* OfflineModeUtils */
    public static class b extends com.sds.android.ttpod.common.a.a {
        public b(Context context) {
            super(context);
        }

        protected View a(Context context, ViewGroup viewGroup) {
            return View.inflate(context, R.layout.dialog_confirm_offline_mode, null);
        }

        protected <T> T a() {
            return null;
        }
    }

    /* OfflineModeUtils */
    public static class c implements OnTouchListener {
        private boolean a = false;
        private View b;

        public c(View view) {
            this.b = view;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                case 5:
                    this.a = false;
                    return this.b.onTouchEvent(motionEvent);
                case 1:
                case 3:
                case 6:
                    if (!this.a) {
                        return false;
                    }
                    this.a = false;
                    return this.b.onTouchEvent(motionEvent);
                case 2:
                    this.a = true;
                    return this.b.onTouchEvent(motionEvent);
                default:
                    return false;
            }
        }
    }

    public static View a(View view) {
        return a(view, null);
    }

    public static View a(View view, final a aVar) {
        LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        View findViewById = viewGroup.findViewById(R.id.offline_mode_frame);
        if (findViewById == null) {
            findViewById = a(viewGroup);
            viewGroup.addView(findViewById, layoutParams);
        }
        findViewById.setVisibility(0);
        findViewById.setOnTouchListener(new c(view));
        ((Button) findViewById.findViewById(R.id.button_offline_continue)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                com.sds.android.ttpod.framework.storage.environment.b.y(false);
                findViewById.setVisibility(8);
                if (aVar != null) {
                    aVar.a();
                }
            }
        });
        return findViewById;
    }

    private static View a(ViewGroup viewGroup) {
        return LayoutInflater.from(BaseApplication.e()).inflate(R.layout.layout_offline_mode, viewGroup, false);
    }

    public static Dialog a(Context context, final DialogInterface.OnClickListener onClickListener) {
        if (a()) {
            final Dialog bVar = new b(context);
            bVar.a((int) R.string.continue_text, new com.sds.android.ttpod.common.a.a.a() {
                public void a(Object obj) {
                    com.sds.android.ttpod.framework.storage.environment.b.y(false);
                    onClickListener.onClick(bVar, -1);
                }
            }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a() {
                public void a(Object obj) {
                    onClickListener.onClick(bVar, -2);
                }
            });
            bVar.a(false);
            bVar.show();
            return bVar;
        }
        onClickListener.onClick(null, -1);
        return null;
    }

    public static boolean a() {
        return com.sds.android.sdk.lib.util.EnvironmentUtils.c.d() != 2 && com.sds.android.ttpod.framework.storage.environment.b.I();
    }
}
