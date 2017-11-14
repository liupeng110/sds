package com.sds.android.ttpod.framework.modules.core.f;

import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.a.ae;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.result.Account;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.sdk.lib.b.k;
import com.sds.android.sdk.lib.b.l;
import com.sds.android.sdk.lib.b.m;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.ut.mini.UTAnalytics;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* UserSystemModule */
public final class c extends b {
    private l<Account> a;
    private String b = "";
    private List<Integer> c = new ArrayList();
    private a d;
    private m e = new m(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(int i, String str) {
            this.a.a();
            if (this.a.a == null || !str.equals(this.a.a.b())) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.AUTHORIZED_INVALID, new Integer(i)), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }
        }
    };
    private k<Account> f = new k<Account>(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void a(Account account) {
            String token = account.getToken();
            if (com.sds.android.sdk.lib.util.m.a(token)) {
                this.a.a((int) SecExceptionCode.SEC_ERROR_STA_INVALID_PARAM, this.a.a);
                return;
            }
            com.sds.android.ttpod.framework.storage.environment.b.a(account);
            this.a.a(token);
        }

        public void b(Account account) {
            this.a.a(account.getCode(), this.a.a);
        }
    };

    public void onCreate() {
        super.onCreate();
        this.c.add(Integer.valueOf(SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM));
        this.c.add(Integer.valueOf(410));
        l.a(this.e, this.c);
    }

    public void onDestroy() {
        super.onDestroy();
        l.a(this.c);
    }

    private void a() {
        com.sds.android.ttpod.framework.storage.environment.b.a(null);
        com.sds.android.ttpod.framework.storage.environment.b.a(null);
        com.sds.android.sdk.lib.a.a.a();
        EnvironmentUtils.b.a(0);
    }

    protected com.sds.android.ttpod.framework.modules.c id() {
        return com.sds.android.ttpod.framework.modules.c.USER_SYSTEM;
    }

    public long timeOutInMills() {
        return 15000;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.REGISTER, i.a(cls, "register", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.VALIDATE_USERNAME_EXIST, i.a(cls, "validateUserNameExist", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_INITIAL_PASSWORD_MESSAGE, i.a(cls, "sendInitialPasswordMessage", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_MESSAGE, i.a(cls, "sendCaptchaMessage", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_EMAIL, i.a(cls, "sendCaptchaEmail", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.VALIDATE_PHONE_CAPTCHA, i.a(cls, "validatePhoneCaptcha", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGIN, i.a(cls, "login", String.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.QQ_LOGIN, i.a(cls, "qqLogin", String.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SINA_LOGIN, i.a(cls, "sinaLogin", String.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.WECHAT_LOGIN, i.a(cls, "wechatLogin", String.class, String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.LOGOUT, i.a(cls, "logout", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_NICKNAME, i.a(cls, "modifyNickName", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_SIGNATURE, i.a(cls, "modifySignature", String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_BIRTHDAY, i.a(cls, "modifyBirthday", Long.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_SEX, i.a(cls, "modifySex", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_USER_EMAIL, i.a(cls, "modifyUserMail", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_AREA, i.a(cls, "modifyAddress", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.BIND_USER_EMAIL, i.a(cls, "bindUserMail", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.RESET_PASSWORD, i.a(cls, "resetPassword", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_PASSWORD, i.a(cls, "modifyPassword", String.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER, i.a(cls, "modifyCover", String.class, Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR, i.a(cls, "modifyAvatar", String.class, Integer.class, Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REFRESH_INFORMATION, i.a(cls, "refreshInformation", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.GET_USER_INFO_BY_ID, i.a(cls, "getUserInfoById", String.class, Long.class));
    }

    private void a(l<? extends com.sds.android.sdk.lib.b.b> lVar) {
        g.e(lVar.b());
        g.a("user", lVar.b());
    }

    private void a(String str) {
        final l a = com.sds.android.cloudapi.ttpod.a.a.a(str);
        a.a(new k<NewUser>(this) {
            final /* synthetic */ c b;

            public void a(NewUser newUser) {
                this.b.a(newUser);
            }

            public void b(NewUser newUser) {
                com.sds.android.sdk.lib.a.a.a();
                this.b.a(newUser.getCode(), a);
            }
        });
    }

    private String a(int i) {
        switch (i) {
            case -1000:
                return "网络异常，请稍候重试";
            case SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM /*201*/:
            case 409:
                return "已绑定";
            case SecExceptionCode.SEC_ERROR_STA_STORE_INCORRECT_DATA_FILE /*204*/:
                return "用户不存在";
            case 400:
                return "参数类型不合法";
            case SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM /*401*/:
            case 410:
                return "token失效，请重新登录";
            case SecExceptionCode.SEC_ERROR_DYN_ENC_GET_SYS_PROPERTIES_FAILED /*403*/:
                return "参数值不合法";
            case SecExceptionCode.SEC_ERROR_DYN_ENC_GET_DATA_FILE_KEY_FAILED /*404*/:
                return "账号不存在";
            case Downloads.STATUS_NOT_ACCEPTABLE /*406*/:
                return "1.不可以与旧密码重复\n2.密码不能是顺序数字或同一字母、数字";
            case 417:
                return "手机号错误";
            case SecExceptionCode.SEC_ERROR_DYN_STORE /*500*/:
                return "内部错误";
            default:
                return "未知错误";
        }
    }

    private void a(int i, l<? extends com.sds.android.sdk.lib.b.b> lVar) {
        String str;
        switch (i) {
            case SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM /*401*/:
                str = "用户不存在或者密码错误";
                break;
            case SecExceptionCode.SEC_ERROR_DYN_ENC_GET_SYS_PROPERTIES_FAILED /*403*/:
                str = "连续输错用户名或密码超5次";
                break;
            default:
                str = a(i);
                break;
        }
        com.sds.android.ttpod.framework.storage.environment.b.a(null);
        d dVar = new d(e.ErrGeneral, str);
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, dVar, this.b), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
        a((l) lVar);
    }

    public void login(String str, String str2, String str3) {
        this.d = a.LOCAL;
        this.b = str3;
        this.a = com.sds.android.cloudapi.ttpod.a.a.g(str, str2);
        this.a.a(this.f);
    }

    public void qqLogin(String str, String str2, String str3) {
        this.d = a.QQ;
        this.a = com.sds.android.cloudapi.ttpod.a.a.a(str, str2, Integer.parseInt(str3));
        this.a.a(this.f);
    }

    public void validateUserNameExist(String str) {
        com.sds.android.cloudapi.ttpod.a.a.b(str).a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.VALIDATE_USERNAME_EXIST_FINISHED, new d(e.ErrAlreadyExists, "")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                if (bVar.getCode() == SecExceptionCode.SEC_ERROR_DYN_ENC_GET_DATA_FILE_KEY_FAILED) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.VALIDATE_USERNAME_EXIST_FINISHED, new d(e.ErrNotFound, this.a.a(bVar.getCode()))), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    return;
                }
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.VALIDATE_USERNAME_EXIST_FINISHED, new d(e.ErrGeneral, this.a.a(bVar.getCode()))), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }
        });
    }

    public void sinaLogin(String str, String str2, String str3) {
        this.d = a.SINA;
        this.a = com.sds.android.cloudapi.ttpod.a.a.b(str, str2, Integer.parseInt(str3));
        this.a.a(this.f);
    }

    public void sendInitialPasswordMessage(String str, final String str2) {
        com.sds.android.cloudapi.ttpod.a.a.c(str).a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c b;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_INITIAL_PASSWORD_MESSAGE_FINISHED, new d(e.ErrNone, ""), str2), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                String str;
                switch (bVar.getCode()) {
                    case 409:
                        str = "手机号已经注册或者被绑定";
                        break;
                    default:
                        str = this.b.a(bVar.getCode());
                        break;
                }
                d dVar = new d(e.ErrGeneral, str, bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_INITIAL_PASSWORD_MESSAGE_FINISHED, dVar, str2), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }
        });
    }

    public void sendCaptchaMessage(String str, final String str2) {
        com.sds.android.cloudapi.ttpod.a.a.e(str).a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c b;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_MESSAGE_FINISHED, new d(e.ErrNone, ""), str2), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                d dVar = new d(e.ErrGeneral, this.b.a(bVar.getCode()), bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_MESSAGE_FINISHED, dVar, str2), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }
        });
    }

    public void sendCaptchaEmail(String str, final String str2) {
        com.sds.android.cloudapi.ttpod.a.a.d(str).a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c b;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_EMAIL_FINISHED, new d(e.ErrNone, ""), str2), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                d dVar = new d(e.ErrGeneral, this.b.a(bVar.getCode()), bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SEND_CAPTCHA_EMAIL_FINISHED, dVar, str2), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }
        });
    }

    public void validatePhoneCaptcha(String str, String str2) {
        com.sds.android.cloudapi.ttpod.a.a.i(str, str2).a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.VALIDATE_PHONE_CAPTCHA_FINISHED, new d(e.ErrNone, "")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                String str;
                switch (bVar.getCode()) {
                    case SecExceptionCode.SEC_ERROR_DYN_ENC_GET_SYS_PROPERTIES_FAILED /*403*/:
                        str = "验证码错误";
                        break;
                    default:
                        str = this.a.a(bVar.getCode());
                        break;
                }
                d dVar = new d(e.ErrGeneral, str, bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.VALIDATE_PHONE_CAPTCHA_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }
        });
    }

    public void wechatLogin(String str, String str2, String str3) {
        this.d = a.WECAHT;
        this.a = com.sds.android.cloudapi.ttpod.a.a.c(str, str2, Integer.parseInt(str3));
        this.a.a(this.f);
    }

    public void register(final String str, String str2) {
        final l h = com.sds.android.cloudapi.ttpod.a.a.h(str, str2);
        h.a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c c;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REGISTER_FINISHED, new d(e.ErrNone, "")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                UTAnalytics.getInstance().userRegister(str);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                String str;
                switch (bVar.getCode()) {
                    case SecExceptionCode.SEC_ERROR_DYN_ENC_INVALID_PARAM /*401*/:
                        str = "手机注册初始密码无效";
                        break;
                    case SecExceptionCode.SEC_ERROR_DYN_ENC_GET_SYS_PROPERTIES_FAILED /*403*/:
                        str = "用户名不合法（只能是邮箱或手机）";
                        break;
                    case 409:
                        str = "用户名已存在,或者被其他账户使用";
                        break;
                    case 414:
                        str = "初始密码错误，请重试";
                        break;
                    case SecExceptionCode.SEC_ERROR_DYN_STORE /*500*/:
                        str = "注册服务端异常";
                        break;
                    default:
                        str = this.c.a(bVar.getCode());
                        break;
                }
                d dVar = new d(e.ErrGeneral, str);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REGISTER_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                this.c.a(h);
            }
        });
    }

    public void logout() {
        UTAnalytics.getInstance().updateUserAccount("", "");
        a();
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.LOGOUT_FINISHED, new Object[0]), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
    }

    public void modifyBirthday(final Long l) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final l f = com.sds.android.cloudapi.ttpod.a.a.f(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), com.sds.android.sdk.lib.util.c.a(l.longValue() * 1000, 0, "-"));
            f.a(new k<com.sds.android.sdk.lib.b.b>(this) {
                final /* synthetic */ c c;

                public void a(com.sds.android.sdk.lib.b.b bVar) {
                    NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                    at.setBirthday(l.longValue());
                    com.sds.android.ttpod.framework.storage.environment.b.a(at);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_BIRTHDAY_FINISHED, new d(e.ErrNone, "生日修改成功")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                }

                public void b(com.sds.android.sdk.lib.b.b bVar) {
                    String str;
                    switch (bVar.getCode()) {
                        case SecExceptionCode.SEC_ERROR_DYN_ENC_GET_SYS_PROPERTIES_FAILED /*403*/:
                            str = "日期参数值不合法";
                            break;
                        default:
                            str = this.c.a(bVar.getCode());
                            break;
                    }
                    d dVar = new d(e.ErrGeneral, str);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_BIRTHDAY_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    this.c.a(f);
                }
            });
        }
    }

    public void modifySignature(final String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final l a = com.sds.android.cloudapi.ttpod.a.a.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), str);
            a.a(new k<com.sds.android.sdk.lib.b.b>(this) {
                final /* synthetic */ c c;

                public void a(com.sds.android.sdk.lib.b.b bVar) {
                    NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                    at.setSignature(str);
                    com.sds.android.ttpod.framework.storage.environment.b.a(at);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_SIGNATURE_FINISHED, new d(e.ErrNone, "修改签名成功", bVar)), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                }

                public void b(com.sds.android.sdk.lib.b.b bVar) {
                    String str;
                    switch (bVar.getCode()) {
                        case 409:
                            str = "昵称为敏感词错误";
                            break;
                        default:
                            str = this.c.a(bVar.getCode());
                            break;
                    }
                    d dVar = new d(e.ErrGeneral, str, bVar);
                    this.c.a(a);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_SIGNATURE_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                }
            });
        }
    }

    public void modifyNickName(final String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final l c = com.sds.android.cloudapi.ttpod.a.a.c(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), str);
            c.a(new k<com.sds.android.sdk.lib.b.b>(this) {
                final /* synthetic */ c c;

                public void a(com.sds.android.sdk.lib.b.b bVar) {
                    NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                    at.setNickName(str);
                    com.sds.android.ttpod.framework.storage.environment.b.a(at);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_NICKNAME_FINISHED, new d(e.ErrNone, "修改昵称成功", bVar)), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                }

                public void b(com.sds.android.sdk.lib.b.b bVar) {
                    String str;
                    switch (bVar.getCode()) {
                        case 409:
                            str = "昵称为敏感词错误";
                            break;
                        default:
                            str = this.c.a(bVar.getCode());
                            break;
                    }
                    d dVar = new d(e.ErrGeneral, str, bVar);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_NICKNAME_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    this.c.a(c);
                }
            });
        }
    }

    public void modifyAddress(final String str, final String str2) {
        final l a = com.sds.android.cloudapi.ttpod.a.a.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), "aa", str, str2);
        a.a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c d;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                at.setCity(str);
                at.setRegion(str2);
                com.sds.android.ttpod.framework.storage.environment.b.a(at);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AREA_FINISHED, new d(e.ErrNone, "修改地址成功", bVar)), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                d dVar = new d(e.ErrGeneral, this.d.a(bVar.getCode()), bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AREA_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                this.d.a(a);
            }
        });
    }

    public void bindUserMail(String str, String str2) {
    }

    public void modifyUserMail(String str, String str2) {
    }

    public void modifyPassword(String str, String str2) {
        final l a = com.sds.android.cloudapi.ttpod.a.a.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getId(), str, str2);
        a.a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c b;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_PASSWORD_FINISHED, new d(e.ErrNone, "修改密码成功，请重新登录。", bVar)), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                String str;
                switch (bVar.getCode()) {
                    case Downloads.STATUS_PRECONDITION_FAILED /*412*/:
                        str = "密码输入有误";
                        break;
                    default:
                        str = this.b.a(bVar.getCode());
                        break;
                }
                d dVar = new d(e.ErrGeneral, str);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_PASSWORD_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                this.b.a(a);
            }
        });
    }

    public void resetPassword(String str, String str2) {
        final l b = com.sds.android.cloudapi.ttpod.a.a.b(str, str2);
        b.a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c b;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.RESET_PASSWORD_FINISHED, new d(e.ErrNone, "")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                String str;
                switch (bVar.getCode()) {
                    case Downloads.STATUS_NOT_ACCEPTABLE /*406*/:
                        str = "1.密码应为6-16位数字或字母\n2.密码不能是顺序数字或同一字母、数字";
                        break;
                    default:
                        str = this.b.a(bVar.getCode());
                        break;
                }
                d dVar = new d(e.ErrGeneral, str, bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.RESET_PASSWORD_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                this.b.a(b);
            }
        });
    }

    public void modifyCover(String str, Integer num, Integer num2) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av() && a(new File(str))) {
            final l b = com.sds.android.cloudapi.ttpod.a.a.b(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), new File(str));
            final String str2 = str;
            final Integer num3 = num;
            final Integer num4 = num2;
            b.a(new k<com.sds.android.sdk.lib.b.b>(this) {
                final /* synthetic */ c e;

                public void a(com.sds.android.sdk.lib.b.b bVar) {
                    this.e.a(bVar.getContent(), str2, num3.intValue(), num4.intValue());
                }

                public void b(com.sds.android.sdk.lib.b.b bVar) {
                    d dVar = new d(e.ErrGeneral, this.e.a(bVar.getCode()), bVar);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    this.e.a(b);
                }
            });
        }
    }

    private void a(String str, String str2, int i, int i2) {
        final l e = com.sds.android.cloudapi.ttpod.a.a.e(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), str);
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final int i4 = i2;
        e.a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c f;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                at.setCoverPic(str3);
                com.sds.android.ttpod.framework.storage.environment.b.a(at);
                com.sds.android.ttpod.framework.a.g.a(at.getCoverPic(), str4, i3, i4);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER_FINISHED, new d(e.ErrNone, "修改背景成功")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                d dVar = new d(e.ErrGeneral, this.f.a(bVar.getCode()), bVar);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_COVER_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                this.f.a(e);
            }
        });
    }

    public void modifyAvatar(String str, Integer num, Integer num2) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av() && a(new File(str))) {
            final l a = com.sds.android.cloudapi.ttpod.a.a.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), new File(str));
            final String str2 = str;
            final Integer num3 = num;
            final Integer num4 = num2;
            a.a(new k<com.sds.android.sdk.lib.b.b>(this) {
                final /* synthetic */ c e;

                public void a(com.sds.android.sdk.lib.b.b bVar) {
                    this.e.b(bVar.getContent(), str2, num3.intValue(), num4.intValue());
                }

                public void b(com.sds.android.sdk.lib.b.b bVar) {
                    d dVar = new d(e.ErrGeneral, this.e.a(bVar.getCode()));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    this.e.a(a);
                }
            });
        }
    }

    private void b(String str, String str2, int i, int i2) {
        final l d = com.sds.android.cloudapi.ttpod.a.a.d(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), str);
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final int i4 = i2;
        d.a(new k<com.sds.android.sdk.lib.b.b>(this) {
            final /* synthetic */ c f;

            public void a(com.sds.android.sdk.lib.b.b bVar) {
                NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                at.setAvatarUrl(str3);
                com.sds.android.ttpod.framework.storage.environment.b.a(at);
                com.sds.android.ttpod.framework.a.g.a(at.getAvatarUrl(), str4, i3, i4);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR_FINISHED, new d(e.ErrNone, "修改头像成功")), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
            }

            public void b(com.sds.android.sdk.lib.b.b bVar) {
                d dVar = new d(e.ErrGeneral, this.f.a(bVar.getCode()));
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_AVATAR_FINISHED, dVar), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                this.f.a(d);
            }
        });
    }

    private boolean a(File file) {
        return file != null && file.exists() && file.length() != 0 && file.length() <= 1048576;
    }

    public void modifySex(final Integer num) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final l a = com.sds.android.cloudapi.ttpod.a.a.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), num.intValue());
            a.a(new k<com.sds.android.sdk.lib.b.b>(this) {
                final /* synthetic */ c c;

                public void a(com.sds.android.sdk.lib.b.b bVar) {
                    NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
                    at.setSex(num.intValue());
                    com.sds.android.ttpod.framework.storage.environment.b.a(at);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_SEX_FINISHED, new d(e.ErrNone, "修改性别成功", bVar)), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                }

                public void b(com.sds.android.sdk.lib.b.b bVar) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.MODIFY_SEX_FINISHED, new d(e.ErrGeneral, this.c.a(bVar.getCode()))), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    g.e(a.b());
                    g.a("user", a.b());
                }
            });
        }
    }

    private void a(NewUser newUser) {
        long userId = newUser.getUserId();
        com.sds.android.ttpod.framework.storage.environment.b.a(newUser);
        EnvironmentUtils.b.a(userId);
        if (!MediaStorage.isGroupExisted(sContext, MediaStorage.buildOnlineFavGroupID())) {
            MediaStorage.insertGroup(sContext, MediaStorage.GROUP_NAME_FAV_ONLINE, MediaStorage.buildOnlineFavGroupID(), GroupType.CUSTOM_ONLINE);
        }
        if (a.LOCAL == this.d) {
            com.sds.android.ttpod.framework.storage.environment.b.m(newUser.getUserName());
        }
        UTAnalytics.getInstance().updateUserAccount(newUser.getNickName(), String.valueOf(userId));
        com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, new d(e.ErrNone, "登录成功"), this.b), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
    }

    public void refreshInformation() {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final l a = com.sds.android.cloudapi.ttpod.a.a.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken());
            a.a(new k<NewUser>(this) {
                final /* synthetic */ c b;

                public void a(NewUser newUser) {
                    if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                        com.sds.android.ttpod.framework.storage.environment.b.a(newUser);
                        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REFRESH_INFORMATION_FINISHED, new Object[0]), com.sds.android.ttpod.framework.modules.c.USER_SYSTEM);
                    }
                }

                public void b(NewUser newUser) {
                    g.e(a.b());
                    g.a("user", a.b());
                }
            });
        }
    }

    public void getUserInfoById(String str, Long l) {
        ae.a(str, l.longValue()).a(new p<TTPodUserResult>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((TTPodUserResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((TTPodUserResult) baseResult);
            }

            public void a(TTPodUserResult tTPodUserResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_USER_INFO_BY_ID, tTPodUserResult), this.a.id());
            }

            public void b(TTPodUserResult tTPodUserResult) {
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_USER_INFO_BY_ID, tTPodUserResult), this.a.id());
            }
        });
    }
}
