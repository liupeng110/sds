package com.sds.android.ttpod.activities.setting;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.c;

public class ContactUsActivity extends SlidingClosableActivity {
    private static final int ID_BUSINESS_MAIL = 6;
    private static final int ID_FORUM = 4;
    private static final int ID_MUSIC_CIRCLE = 1;
    private static final int ID_SERVICE_MAIL = 5;
    private static final int ID_WECHAT = 3;
    private static final int ID_WEIBO = 2;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ ContactUsActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            Context context = this.a;
            switch (aVar.g()) {
                case 1:
                    ContactUsActivity.startupExternalBrowser(context, "http://quan.dongting.com");
                    return;
                case 2:
                    ContactUsActivity.startupExternalBrowser(context, "http://weibo.com/ittpod");
                    return;
                case 3:
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("http://weixin.qq.com/r/YHXPwBXE8iuNhwSjnyBE"));
                        intent.setFlags(268435456);
                        this.a.startActivity(intent);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case 4:
                    ContactUsActivity.startupExternalBrowser(context, "http://bbs.dongting.com");
                    return;
                case 5:
                    this.a.serviceMail();
                    return;
                case 6:
                    this.a.bussinessEmail();
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_setting_main);
        d.a(this);
        ((LinearLayout) findViewById(R.id.setting_card_container)).addView(buildContactCard().e());
    }

    private c buildContactCard() {
        return new b(this, new c[]{new c(1, 0, (int) R.string.apply_for_music_circle, (int) R.string.webset_music_circle, 0, true), new c(2, 0, (int) R.string.follow_weibo, (int) R.string.webset_weibo, 0, true), new c(3, 0, (int) R.string.follow_wechat, (int) R.string.webset_wechat, 0, true), new c(4, 0, (int) R.string.access_forum, (int) R.string.webset_forum, 0, true), new c(5, 0, (int) R.string.service_mail_title, (int) R.string.service_mail, 0, true), new c(6, 0, (int) R.string.business_mail_title, (int) R.string.business_mail, 0, true)}, R.string.contact_us, this.mOnItemClickListener);
    }

    private void serviceMail() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"support@ttpod.com"});
        intent.putExtra("android.intent.extra.SUBJECT", "Question about TTPod for Android");
        intent.putExtra("android.intent.extra.TEXT", getEmailMessage());
        intent.setType("plain/text");
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.mail_box)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void bussinessEmail() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"market@ttpod.com"});
        intent.putExtra("android.intent.extra.SUBJECT", "market");
        intent.putExtra("android.intent.extra.TEXT", "");
        intent.setType("plain/text");
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.mail_box)));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void startupExternalBrowser(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(amendNetworkUrl(str))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String amendNetworkUrl(String str) {
        if (URLUtil.isNetworkUrl(str)) {
            return str;
        }
        return "http://" + str;
    }

    private String getEmailMessage() {
        String str = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.version_info));
        stringBuilder.append("\n");
        stringBuilder.append("Version: ");
        stringBuilder.append(EnvironmentUtils.a.f());
        stringBuilder.append("\n");
        stringBuilder.append("Market: ");
        stringBuilder.append(EnvironmentUtils.a.b());
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.device_info));
        stringBuilder.append("\n");
        stringBuilder.append("Device: ");
        stringBuilder.append(Build.MANUFACTURER + " " + Build.MODEL + "(" + Build.DEVICE + ")");
        stringBuilder.append("\n");
        stringBuilder.append("Product: ");
        stringBuilder.append(Build.PRODUCT);
        stringBuilder.append("\n");
        stringBuilder.append("Android: ");
        stringBuilder.append(VERSION.RELEASE);
        stringBuilder.append("(");
        stringBuilder.append(VERSION.SDK_INT);
        stringBuilder.append(")");
        stringBuilder.append("\n");
        stringBuilder.append("FingerPrint: ");
        stringBuilder.append(Build.FINGERPRINT);
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.support_description));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
