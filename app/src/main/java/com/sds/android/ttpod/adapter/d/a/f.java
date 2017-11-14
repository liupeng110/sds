package com.sds.android.ttpod.adapter.d.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.SystemNotice;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.a.g;
import java.util.List;

/* SystemMessageAdapter */
public class f extends com.sds.android.ttpod.adapter.a<SystemNotice> {

    /* SystemMessageAdapter */
    class a {
        final /* synthetic */ f a;
        private TextView b;
        private TextView c;
        private TextView d;
        private ImageView e;

        public a(f fVar, View view) {
            this.a = fVar;
            this.b = (TextView) view.findViewById(R.id.message_time);
            this.c = (TextView) view.findViewById(R.id.message_title);
            this.d = (TextView) view.findViewById(R.id.message_content);
            this.e = (ImageView) view.findViewById(R.id.message_pic);
        }
    }

    public f(Context context, List<SystemNotice> list) {
        super(context, list);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_system_message_item, null, false);
        inflate.setTag(new a(this, inflate));
        return inflate;
    }

    protected void a(View view, SystemNotice systemNotice, int i) {
        a aVar = (a) view.getTag();
        aVar.b.setText(w.a(systemNotice.getTimeStamp()));
        aVar.c.setText(systemNotice.getTitle());
        aVar.d.setText(systemNotice.getMessage());
        g.a(aVar.e, systemNotice.getPicture(), aVar.e.getWidth(), aVar.e.getHeight(), (int) R.drawable.img_avatar_default);
    }
}
