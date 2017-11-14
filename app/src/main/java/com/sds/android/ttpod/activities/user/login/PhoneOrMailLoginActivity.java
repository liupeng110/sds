package com.sds.android.ttpod.activities.user.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.user.register.RegisterActivity;
import com.sds.android.ttpod.activities.user.retrievepassword.RetrievePasswordActivity;
import com.sds.android.ttpod.activities.user.utils.e;
import com.sds.android.ttpod.common.widget.ClearEditText;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.a;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.lang.reflect.Method;
import java.util.Map;

public class PhoneOrMailLoginActivity extends SlidingClosableActivity {
    private a mAutoCompleteView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ PhoneOrMailLoginActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_login:
                    this.a.login();
                    return;
                case R.id.textview_register:
                    t.a(r.ACTION_REGISTER, s.PAGE_PHONE_REGISTER);
                    this.a.startActivity(new Intent(this.a, RegisterActivity.class));
                    return;
                case R.id.textview_find_password:
                    t.a(r.ACTION_FORGET_PASSWORD, s.PAGE_RETRIEVE_PASSWORD);
                    this.a.startActivity(new Intent(this.a, RetrievePasswordActivity.class));
                    return;
                default:
                    return;
            }
        }
    };
    private ClearEditText mPassWordEditText;
    private ClearEditText mUserNameEditText;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActionBarController().d();
        setTitle((int) R.string.login_text);
        setContentView((int) R.layout.activity_phone_mail_login);
        findViewById(R.id.textview_register).setOnClickListener(this.mOnClickListener);
        findViewById(R.id.textview_find_password).setOnClickListener(this.mOnClickListener);
        findViewById(R.id.textview_login).setOnClickListener(this.mOnClickListener);
        this.mUserNameEditText = (ClearEditText) findViewById(R.id.edittext_username);
        initView();
        setStatisticPage(s.PAGE_LOGIN_MAIL_PHONE);
        setTBSPage("tt_login_email_phone");
        trackModule("user_system");
    }

    private void initView() {
        CharSequence ax = b.ax();
        if (e.c(ax)) {
            ax = e.b(ax);
        }
        this.mUserNameEditText.setText(ax);
        this.mAutoCompleteView = new a(this);
        TextWatcher bVar = new com.sds.android.ttpod.activities.user.utils.b(this, this.mUserNameEditText, this.mAutoCompleteView);
        this.mAutoCompleteView.a(new a.a(this) {
            final /* synthetic */ PhoneOrMailLoginActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.mUserNameEditText.setText(str);
                this.a.mUserNameEditText.setSelection(str.length());
                this.a.mAutoCompleteView.c();
            }
        });
        this.mUserNameEditText.addTextChangedListener(bVar);
        this.mPassWordEditText = (ClearEditText) findViewById(R.id.edittext_password);
        this.mPassWordEditText.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ PhoneOrMailLoginActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.a.login();
                return false;
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.imageview_header);
        try {
            imageView.setImageResource(R.drawable.retrieve_head_background);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            imageView.setBackgroundColor(-1);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getExtras() != null && !m.a(intent.getExtras().getString(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME))) {
            this.mUserNameEditText.setText(intent.getExtras().getString(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME));
        }
    }

    private String getUserName() {
        return this.mUserNameEditText.getText().toString().trim();
    }

    private String getPassword() {
        return this.mPassWordEditText.getText().toString().trim();
    }

    private String getRequestId() {
        return getClass().getName() + getUserName();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mPassWordEditText != null && this.mUserNameEditText != null) {
            this.mPassWordEditText.a();
            this.mUserNameEditText.a();
        }
    }

    protected void onStop() {
        f.a(this.mAutoCompleteView);
        super.onStop();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(getClass(), "loginFinished", d.class, String.class));
    }

    protected void login() {
        String userName = getUserName();
        String password = getPassword();
        if (validateUserName(userName) && validatePassword(password)) {
            if (TextUtils.isDigitsOnly(userName)) {
                if (com.sds.android.ttpod.activities.user.utils.f.d.a(userName)) {
                    userName = "86-" + userName;
                } else {
                    showForeignLoginDialog(getString(R.string.retry_for_invalid_phone_number));
                    return;
                }
            }
            f.a((Context) this, (int) R.string.login_wait_message);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOGIN, userName, password, getRequestId()));
        }
    }

    private boolean validateUserName(String str) {
        return com.sds.android.ttpod.activities.user.utils.f.a(str, R.string.user_name_hint_text, R.string.account_error, this.mUserNameEditText, R.anim.shake, com.sds.android.ttpod.activities.user.utils.f.b);
    }

    protected boolean validatePassword(String str) {
        return com.sds.android.ttpod.activities.user.utils.f.a(str, R.string.password_hint_text, R.string.password_length, this.mPassWordEditText, R.anim.shake, com.sds.android.ttpod.activities.user.utils.f.f);
    }

    private void showForeignLoginDialog(String str) {
        h hVar = new h((Context) this, str, null, null);
        hVar.setTitle((int) R.string.invalid_phone_number);
        hVar.a((int) R.string.retry, null, (int) R.string.oversea_login_title, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ PhoneOrMailLoginActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                t.a(r.ACTION_START_FOREIGN_LOGIN, s.PAGE_FOREIGN_LOGIN);
                this.a.gotoForeignLogin();
            }
        });
        hVar.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ PhoneOrMailLoginActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
        hVar.show();
    }

    private void gotoForeignLogin() {
        Intent intent = new Intent(this, OverseaLoginActivity.class);
        intent.putExtra(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, getUserName());
        startActivity(intent);
    }

    private String getOriginLoginType(String str) {
        if (str.indexOf(64) >= 0) {
            return "邮箱";
        }
        return "手机";
    }

    public void loginFinished(d dVar, String str) {
        if (m.a(getRequestId(), str)) {
            loginStatistics(dVar);
            if (status() == 2) {
                f.a();
            }
            if (dVar.a() == com.sds.android.ttpod.framework.base.e.ErrNone) {
                finish();
            } else {
                f.a(dVar.b());
            }
        }
    }

    protected void loginStatistics(d dVar) {
        int i = 1;
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_LOGIN.getValue(), 0);
        sUserEvent.setPageParameter(true);
        String str = Downloads.COLUMN_STATUS;
        if (dVar.a() != com.sds.android.ttpod.framework.base.e.ErrNone) {
            i = 0;
        }
        sUserEvent.append(str, Integer.valueOf(i));
        sUserEvent.append("way", getOriginLoginType(this.mUserNameEditText.getText().toString().trim()));
        sUserEvent.post();
    }
}
