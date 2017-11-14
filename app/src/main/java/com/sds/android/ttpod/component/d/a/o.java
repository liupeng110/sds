package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a;

/* RecommendsEffectHelpDialog */
public class o extends a {
    public o(Context context) {
        super(context);
        b(R.string.effect_know, null);
        d(true);
        setTitle((int) R.string.recommends_effect_help_dialog_title);
    }

    protected View a(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(getContext(), R.layout.dialog_recommends_effect_help, null);
        inflate.findViewById(R.id.textview_contact).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ o a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.b(this.a.getContext(), "http://bbs.dongting.com");
                this.a.dismiss();
            }
        });
        return inflate;
    }

    protected <T> T a() {
        return null;
    }

    private static void b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(a(str))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String a(String str) {
        if (URLUtil.isNetworkUrl(str)) {
            return str;
        }
        return "http://" + str;
    }
}
