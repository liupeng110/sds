package com.sds.android.ttpod.adapter.d;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.widget.UserAvatarView;

/* UserInfoViewHolder */
public class h {
    private UserAvatarView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private Button e;

    public h(View view) {
        this.a = (UserAvatarView) view.findViewById(R.id.user_avatar);
        this.b = (TextView) view.findViewById(R.id.user_name);
        this.c = (TextView) view.findViewById(R.id.user_extra_info_1);
        this.d = (TextView) view.findViewById(R.id.user_extra_info_2);
        this.e = (Button) view.findViewById(R.id.follow_button);
    }

    public UserAvatarView a() {
        return this.a;
    }

    public TextView b() {
        return this.b;
    }

    public TextView c() {
        return this.c;
    }

    public TextView d() {
        return this.d;
    }

    public Button e() {
        return this.e;
    }
}
