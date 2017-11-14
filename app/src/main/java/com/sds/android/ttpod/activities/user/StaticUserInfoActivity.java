package com.sds.android.ttpod.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.a.g;
import java.io.Serializable;

public class StaticUserInfoActivity extends SlidingClosableActivity {
    private NewUser mUser;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.musiccircle_static_user_info_layout);
        onNewIntent(getIntent());
        ImageView imageView = (ImageView) findViewById(R.id.user_avatar);
        TextView textView = (TextView) findViewById(R.id.user_name);
        TextView textView2 = (TextView) findViewById(R.id.user_extra_info_1);
        TextView textView3 = (TextView) findViewById(R.id.user_extra_info_2);
        TextView textView4 = (TextView) findViewById(R.id.text_sex);
        TextView textView5 = (TextView) findViewById(R.id.text_birthday);
        int a = a.a(120);
        g.a(imageView, this.mUser.getAvatarUrl(), a, a, (int) R.drawable.img_avatar_default);
        textView.setText(this.mUser.getNickName());
        textView2.setText("粉丝：" + this.mUser.getFollowersCount());
        textView3.setText("关注：" + this.mUser.getFollowingsCount());
        textView4.setText(this.mUser.getSex() == 0 ? "美女" : "帅哥");
        textView5.setText(new com.sds.android.ttpod.activities.user.utils.a(this.mUser.getBirthday()).toString());
        setTitle(this.mUser.getNickName());
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            Serializable serializableExtra = intent.getSerializableExtra("user");
            if (serializableExtra instanceof NewUser) {
                this.mUser = (NewUser) serializableExtra;
                return;
            }
            throw new IllegalArgumentException("illegal value for BundleKey.USER");
        }
    }
}
