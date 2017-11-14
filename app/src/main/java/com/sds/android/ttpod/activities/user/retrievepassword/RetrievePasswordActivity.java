package com.sds.android.ttpod.activities.user.retrievepassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;

public class RetrievePasswordActivity extends SlidingClosableActivity {
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ RetrievePasswordActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layout_phone:
                    this.a.startActivity(new Intent(this.a, PhoneRetrievePasswordActivity.class));
                    t.a(r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_PHONE, s.PAGE_PHONE_RETRIEVE_PASSWORD);
                    return;
                case R.id.layout_email:
                    this.a.startActivity(new Intent(this.a, EmailRetrievePasswordActivity.class));
                    t.a(r.ACTION_PHONE_RETRIEVE_PASSWORD_VIA_EMAIL, s.PAGE_MAIL_RETRIEVE_PASSWORD);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.retrieve_password);
        setContentView((int) R.layout.activity_find_password_entry);
        getActionBarController().d();
        findViewById(R.id.layout_phone).setOnClickListener(this.mOnClickListener);
        findViewById(R.id.layout_email).setOnClickListener(this.mOnClickListener);
        setStatisticPage(s.PAGE_RETRIEVE_PASSWORD);
        setTBSPage("tt_forgetcode");
    }
}
