package com.sds.android.ttpod.cmmusic.d;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.cmmusic.R;

/* CustomProgressDialog */
public class c extends Dialog {
    private static c b = null;
    private Context a = null;

    public c(Context context, int i) {
        super(context, i);
    }

    public static c a(Context context) {
        b = new c(context, R.style.CustomProgressDialog);
        b.setContentView(R.layout.cmmusic_custom_progressdialog);
        b.getWindow().getAttributes().gravity = 17;
        return b;
    }

    public void onWindowFocusChanged(boolean z) {
        if (b != null) {
            ((AnimationDrawable) ((ImageView) b.findViewById(R.id.loadingImageView)).getBackground()).start();
        }
    }

    public c a(String str) {
        TextView textView = (TextView) b.findViewById(R.id.id_tv_loadingmsg);
        if (textView != null) {
            textView.setText(str);
        }
        return b;
    }
}
