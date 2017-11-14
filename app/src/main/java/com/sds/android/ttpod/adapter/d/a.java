package com.sds.android.ttpod.adapter.d;

import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.UserAvatarView;

/* CommentViewHolder */
public class a {
    public static final int a = "回复 %1$s: %2$s".indexOf("%1$s");
    private UserAvatarView b;
    private TextView c;
    private TextView d;
    private TextView e;

    public a(View view) {
        this.b = (UserAvatarView) view.findViewById(R.id.image_avatar);
        this.c = (TextView) view.findViewById(R.id.tv_user_name);
        this.d = (TextView) view.findViewById(R.id.tv_tweet);
        this.e = (TextView) view.findViewById(R.id.tv_time);
        this.d.setMovementMethod(com.sds.android.ttpod.widget.TextViewFixTouchConsume.a.a());
    }

    public UserAvatarView a() {
        return this.b;
    }

    public TextView b() {
        return this.c;
    }

    public TextView c() {
        return this.d;
    }

    public TextView d() {
        return this.e;
    }
}
