package com.sds.android.ttpod.activities.user.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.activities.user.utils.e;
import com.sds.android.ttpod.activities.user.utils.f;
import com.sds.android.ttpod.common.widget.ClearEditText;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class OverseaLoginActivity extends SlidingClosableActivity {
    private ClearEditText mEditViewPassword;
    private ClearEditText mEditViewPhone;
    private String mRegionCode = "+86";
    private TextView mTextViewLogin;
    private TextView mTextViewRegionCode;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.oversea_login_title);
        setContentView((int) R.layout.activity_oversea_login);
        getActionBarController().d();
        this.mEditViewPassword = (ClearEditText) findViewById(R.id.edittext_password);
        this.mEditViewPhone = (ClearEditText) findViewById(R.id.edittext_phone);
        this.mTextViewRegionCode = (TextView) findViewById(R.id.regionCode);
        this.mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        initView();
        setStatisticPage(s.PAGE_FOREIGN_LOGIN);
        setTBSPage("tt_findcode_abroad");
    }

    protected void initView() {
        this.mEditViewPhone.setText(getIntent().getExtras().getString(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME));
        this.mTextViewRegionCode.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OverseaLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivityForResult(new Intent(this.a, SelectCountryActivity.class), 1);
            }
        });
        this.mTextViewLogin.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ OverseaLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.login();
            }
        });
    }

    private String getRequestId() {
        return getClass().getName() + this.mRegionCode;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.LOGIN_FINISHED, i.a(getClass(), "overseaLoginFinished", d.class, String.class));
    }

    protected boolean validatePassword(String str) {
        return f.a(str, R.string.password_hint_text, R.string.password_length, this.mEditViewPassword, R.anim.shake, f.f);
    }

    protected void login() {
        String trim = this.mEditViewPhone.getText().toString().trim();
        String trim2 = this.mEditViewPassword.getText().toString().trim();
        if (e.a(this.mRegionCode, trim, this.mEditViewPhone) && validatePassword(trim2)) {
            com.sds.android.ttpod.component.d.f.a((Context) this, (int) R.string.login_wait_message);
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.LOGIN, e.a(this.mRegionCode, trim), trim2, getRequestId()));
        }
    }

    public void overseaLoginFinished(d dVar, String str) {
        int i = 1;
        if (m.a(getRequestId(), str)) {
            if (status() == 2) {
                com.sds.android.ttpod.component.d.f.a();
            }
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_FOREIGN_LOGIN.getValue(), 0);
            sUserEvent.setPageParameter(true);
            String str2 = Downloads.COLUMN_STATUS;
            if (dVar.a() != com.sds.android.ttpod.framework.base.e.ErrNone) {
                i = 0;
            }
            sUserEvent.append(str2, Integer.valueOf(i));
            sUserEvent.append("location", this.mTextViewRegionCode.getText().toString().trim());
            sUserEvent.post();
            if (dVar.a() != com.sds.android.ttpod.framework.base.e.ErrNone) {
                com.sds.android.ttpod.component.d.f.a(dVar.b());
            } else {
                finish();
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            String[] split = intent.getStringExtra("region_code_result").split(SelectCountryActivity.SPLITTER);
            if (split.length == 3) {
                this.mRegionCode = split[2];
                this.mTextViewRegionCode.setText(split[1] + " " + this.mRegionCode);
            }
        }
    }
}
