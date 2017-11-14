package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.Account;
import com.sds.android.sdk.lib.b.b;
import com.sds.android.sdk.lib.b.d;
import com.sds.android.sdk.lib.b.h;
import com.sds.android.sdk.lib.b.i;
import com.sds.android.sdk.lib.b.j;
import com.sds.android.sdk.lib.b.l;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.io.File;

/* AccountSystemAPI */
public class a {
    public static l<NewUser> a(String str) {
        return new d(NewUser.class, "http://api.user.ttpod.com/user/" + str);
    }

    public static l<b> a(String str, String str2) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + "signature").a("signature", (Object) str2);
    }

    public static l<b> a(long j, String str, String str2) {
        return new j(b.class, "http://api.account.ttpod.com/account/" + j + "/" + "password").a("old_password", (Object) str).a("new_password", (Object) str2);
    }

    public static l<b> b(String str, String str2) {
        return new j(b.class, "http://api.account.ttpod.com/guest/password?biz_id=2010").a("mobile", (Object) str).a("password", (Object) str2);
    }

    public static l<b> c(String str, String str2) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + "nickname").a("nickname", (Object) str2);
    }

    public static l<b> d(String str, String str2) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + "avatar").a("avatar_url", (Object) str2);
    }

    public static l<b> e(String str, String str2) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + "cover").a(User.KEY_COVER, (Object) str2);
    }

    public static l<b> a(String str, int i) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + User.KEY_SEX).a(User.KEY_SEX, (Object) Integer.valueOf(i));
    }

    public static l<b> f(String str, String str2) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + User.KEY_BIRTHDAY).a(User.KEY_BIRTHDAY, (Object) str2);
    }

    public static l<b> a(String str, String str2, String str3, String str4) {
        return new j(b.class, "http://api.user.ttpod.com/profile/" + str + "/" + "address").a("country", (Object) str2).a("city", (Object) str3).a("region", (Object) str4);
    }

    public static l<Account> a(String str, String str2, int i) {
        return a(str, str2, i, "http://api.account.ttpod.com/session/qq");
    }

    public static l<Account> b(String str, String str2, int i) {
        return a(str, str2, i, "http://api.account.ttpod.com/session/sina");
    }

    public static l<Account> c(String str, String str2, int i) {
        return a(str, str2, i, "http://api.account.ttpod.com/session/wechat");
    }

    private static l<Account> a(String str, String str2, int i, String str3) {
        return new i(Account.class, str3).a("access_token", (Object) str).a("openid", (Object) str2).a("expire_in", Integer.valueOf(i));
    }

    public static l<Account> g(String str, String str2) {
        return new i(Account.class, "http://api.account.ttpod.com/session/local").a(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, (Object) str).a("password", (Object) str2).a("remember_me", Boolean.valueOf(false));
    }

    public static l<b> h(String str, String str2) {
        return new i(b.class, "http://api.account.ttpod.com/guest").a(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME, (Object) str).a("password", (Object) str2);
    }

    public static l<b> b(String str) {
        return new d(b.class, "http://api.account.ttpod.com/guest/" + str);
    }

    public static l<b> c(String str) {
        return new i(b.class, "http://api.account.ttpod.com/captcha/biz/2030").a("mobile", (Object) str);
    }

    public static l<b> d(String str) {
        return new i(b.class, "http://api.account.ttpod.com/captcha/biz/2020").a("email", (Object) str);
    }

    public static l<b> e(String str) {
        return new i(b.class, "http://api.account.ttpod.com/captcha/biz/2010").a("mobile", (Object) str);
    }

    public static l<b> i(String str, String str2) {
        return new d(b.class, "http://api.account.ttpod.com/captcha/biz/2010").a("mobile", (Object) str).a("verify_code", (Object) str2);
    }

    public static l<b> a(String str, File file) {
        return new h(b.class, "http://api.user.ttpod.com/user/" + str + "/" + "avatar").a("avatar", file);
    }

    public static l<b> b(String str, File file) {
        return new h(b.class, "http://api.user.ttpod.com/user/" + str + "/" + "cover").a("cover", file);
    }
}
