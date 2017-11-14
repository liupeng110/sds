package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;

/* NoCopyrightDialog */
public class l extends e<a> {
    private TextView a;
    private TextView b;

    public l(Context context, List<a> list, MediaItem mediaItem) {
        super(context, (List) list, (int) R.string.cancel, null);
        setTitle(mediaItem.getTitle());
        if (j.a(list)) {
            this.a.setText(R.string.search_notification_no_resource);
        } else {
            this.a.setText(R.string.search_notification_no_copyright);
            a(context);
        }
        b(R.string.cancel, new com.sds.android.ttpod.common.a.a.a(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void a(Object obj) {
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_CANCEL_COPYRIGHT_DIALOG.getValue(), s.PAGE_DIALOG_COPYRIGHT.getValue()).post();
            }
        });
    }

    protected View e() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_copyright_notification_title, null);
        this.a = (TextView) inflate.findViewById(R.id.tv_dialog_notification);
        this.b = (TextView) inflate.findViewById(R.id.unicom_flow_message);
        return inflate;
    }

    private void a(Context context) {
        if (com.sds.android.sdk.lib.a.a.c()) {
            CharSequence string = context.getString(R.string.unicom_flow_search_prefix);
            String string2 = context.getString(R.string.unicom_flow_search_suffix);
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(string);
            spannableStringBuilder.append(Html.fromHtml("<font color=#f20d0d>" + string2 + "</font>"));
            spannableStringBuilder.append("。");
            this.b.setText(spannableStringBuilder);
            this.b.setVisibility(0);
        }
    }
}
