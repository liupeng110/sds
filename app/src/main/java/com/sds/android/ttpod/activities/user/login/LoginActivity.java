package com.sds.android.ttpod.activities.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.b.b;
import com.sds.android.ttpod.a.b.h;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.modules.a;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import java.lang.reflect.Method;
import java.util.Map;

public class LoginActivity extends SlidingClosableActivity implements OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private b mAuthHandler;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.login_title);
        setContentView((int) R.layout.activity_login);
        getActionBarController().d();
        findViewById(R.id.layout_phone).setOnClickListener(this);
        findViewById(R.id.layout_wechat).setOnClickListener(this);
        findViewById(R.id.layout_qq).setOnClickListener(this);
        findViewById(R.id.layout_sina).setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.imageview_header);
        try {
            imageView.setImageResource(R.drawable.retrieve_head_background);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            imageView.setBackgroundColor(-1);
        }
        setStatisticPage(s.PAGE_CIRCLE_LOGIN);
        setTBSPage("tt_singin");
        trackModule("user_system");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_phone:
                t.a(r.ACTION_PHONE_EMAIL_LOGIN, s.PAGE_LOGIN_MAIL_PHONE);
                startActivity(new Intent(this, PhoneOrMailLoginActivity.class));
                return;
            case R.id.layout_wechat:
                t.a(r.ACTION_WECHAT_LOGIN, s.PAGE_WECHAT_LOGIN);
                wechatLogin();
                return;
            case R.id.layout_qq:
                t.a(r.ACTION_QQ_LOGIN, s.PAGE_QQ_LOGIN);
                qqLogin();
                return;
            case R.id.layout_sina:
                t.a(r.ACTION_SINA_LOGIN, s.PAGE_SINA_LOGIN);
                sinaLogin();
                return;
            default:
                return;
        }
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.LOGIN_FINISHED, i.a(getClass(), "loginFinished", d.class, String.class));
    }

    public void loginFinished(d dVar, String str) {
        if (status() == 2) {
            f.a();
        }
        if (dVar.a() != e.ErrNone) {
            f.a(dVar.b());
        } else {
            finish();
        }
    }

    private void wechatLogin() {
        b hVar = new h(this);
        if (hVar.b()) {
            this.mAuthHandler = hVar;
            this.mAuthHandler.c(new com.sds.android.ttpod.a.b.a(this) {
                final /* synthetic */ LoginActivity a;

                {
                    this.a = r1;
                }

                public void a(Bundle bundle) {
                    String string = bundle.getString("access_token");
                    String string2 = bundle.getString(Constants.PARAM_EXPIRES_IN);
                    String string3 = bundle.getString("openid");
                    g.a(LoginActivity.TAG, "mWechatAuthCallback onAuthSuccess token=" + string + ",expiresIn=" + string2 + ",openId=" + string3);
                    if (!m.a(string) && !m.a(string2) && !m.a(string3)) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.WECHAT_LOGIN, string, string3, string2));
                    }
                }

                public void a(String str) {
                    f.a();
                    f.a(str);
                }

                public void a() {
                }
            });
            return;
        }
        f.a((int) R.string.wechat_not_installed);
    }

    private void qqLogin() {
        this.mAuthHandler = new com.sds.android.ttpod.a.b.e(this);
        this.mAuthHandler.a(new com.sds.android.ttpod.a.b.a(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void a(Bundle bundle) {
                if (this.a.status() == 2) {
                    f.a(this.a, (int) R.string.login_wait_message);
                }
                String string = bundle.getString("access_token");
                String string2 = bundle.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = bundle.getString("openid");
                g.a(LoginActivity.TAG, "mQQAuthCallback onAuthSuccess token=" + string + ",expiresIn=" + string2 + ",openId=" + string3);
                if (!m.a(string) && !m.a(string2) && !m.a(string3)) {
                    com.sds.android.ttpod.a.d.a.a(this.a, "TENTCANT_TTPOD_TOKEN", bundle);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.QQ_LOGIN, string, string3, string2));
                }
            }

            public void a(String str) {
                if (this.a.status() == 2) {
                    f.a();
                }
                f.a(str);
                this.a.mAuthHandler.b(this);
            }

            public void a() {
            }
        });
    }

    private void sinaLogin() {
        this.mAuthHandler = new com.sds.android.ttpod.a.b.g(this);
        this.mAuthHandler.a(new com.sds.android.ttpod.a.b.a(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void a(Bundle bundle) {
                if (this.a.status() == 2) {
                    f.a(this.a, (int) R.string.login_wait_message);
                }
                String string = bundle.getString("access_token");
                String string2 = bundle.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = bundle.getString(ParamKey.UID);
                g.a(LoginActivity.TAG, "mQQAuthCallback onAuthSuccess token=" + string + ",expiresIn=" + string2 + ",openId=" + string3);
                if (!m.a(string) && !m.a(string2) && !m.a(string3)) {
                    com.sds.android.ttpod.a.d.a.a(this.a, "SINA_TTPOD_TOKEN", bundle);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SINA_LOGIN, string, string3, string2));
                }
            }

            public void a(String str) {
                if (this.a.status() == 2) {
                    f.a();
                }
                f.a((int) R.string.auth_failed);
                this.a.mAuthHandler.b(this);
            }

            public void a() {
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mAuthHandler != null) {
            this.mAuthHandler.a(i, i2, intent);
        }
    }
}
