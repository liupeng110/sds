package com.sds.android.ttpod.adapter.d.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageOverView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.List;

/* MessageEntryAdapter */
public class b extends com.sds.android.ttpod.adapter.a<PrivateMessageOverView> {

    /* MessageEntryAdapter */
    class a {
        final /* synthetic */ b a;
        private UserAvatarView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;

        public a(b bVar, View view) {
            this.a = bVar;
            this.b = (UserAvatarView) view.findViewById(R.id.image_avatar);
            this.c = (TextView) view.findViewById(R.id.text_count);
            this.d = (TextView) view.findViewById(R.id.artist_name);
            this.e = (TextView) view.findViewById(R.id.text_datetime);
            this.f = (TextView) view.findViewById(R.id.text_message);
        }
    }

    public b(Context context, List<PrivateMessageOverView> list) {
        super(context, list);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_private_message_item, null, false);
        inflate.setTag(new a(this, inflate));
        return inflate;
    }

    protected void a(View view, PrivateMessageOverView privateMessageOverView, int i) {
        a aVar = (a) view.getTag();
        TTPodUser user = privateMessageOverView.getUser();
        if (user != null) {
            g.a(aVar.b, user.getAvatarUrl(), aVar.b.getWidth(), aVar.b.getHeight(), (int) R.drawable.img_avatar_default);
            aVar.b.setVFlagVisible(user.isVerified());
            aVar.d.setText(user.getNickName());
            aVar.e.setText(w.a(privateMessageOverView.getLastModified()));
            CharSequence a = com.sds.android.ttpod.component.emoticons.b.b().a(a(), privateMessageOverView.getLastMsg());
            if (a == null) {
                a = "";
            }
            aVar.f.setText(a);
            int unreadCount = privateMessageOverView.getUnreadCount();
            if (unreadCount > 0) {
                aVar.c.setVisibility(0);
                aVar.c.setText(Integer.toString(unreadCount));
            } else {
                aVar.c.setVisibility(8);
            }
            aVar.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                }
            });
        }
    }
}
