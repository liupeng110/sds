package com.sds.android.ttpod.common.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.common.R;

/* ShareWaitingDialog */
public class b extends a {
    private View a;
    private ImageView b;
    private TextView c;
    private Animation d;

    protected /* synthetic */ Object a() {
        return b();
    }

    public b(Context context) {
        super(context, R.style.Share_Theme_Dialog_Waiting);
        a(false);
        b(false);
        a(R.drawable.xml_share_background_dialog_waiting);
        setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.c.clearAnimation();
            }
        });
    }

    protected View a(Context context, ViewGroup viewGroup) {
        this.a = View.inflate(context, R.layout.share_dialog_body_waiting, null);
        this.b = (ImageView) this.a.findViewById(R.id.waiting_progress);
        this.c = (TextView) this.a.findViewById(R.id.waiting_message);
        this.d = AnimationUtils.loadAnimation(context, R.anim.rotate);
        return this.a;
    }

    protected b b() {
        return this;
    }

    public void a(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public void show() {
        super.show();
        this.b.startAnimation(this.d);
    }
}
