package com.sds.android.ttpod.component.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.fragment.main.findsong.RankCategoryFragment;
import com.sds.android.ttpod.framework.base.Action;
import com.tencent.open.SocialConstants;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class GexinSdkMessageReceiver extends BroadcastReceiver {
    private static String b;
    private static final String[] c = new String[]{"postId", "rankId", "categoryId", "link", "search"};
    private static final List<String> d = Arrays.asList(new String[]{"postId", "rankId", "categoryId"});
    private Context a;

    private String a(byte[] bArr) {
        if (bArr != null) {
            return new String(bArr);
        }
        return "";
    }

    public void onReceive(Context context, Intent intent) {
        this.a = context;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            switch (extras.getInt(PushConsts.CMD_ACTION)) {
                case PushConsts.GET_MSG_DATA /*10001*/:
                    PushManager.getInstance().sendFeedbackMessage(context, extras.getString("taskid"), extras.getString("messageid"), PushConsts.MIN_FEEDBACK_ACTION);
                    extras = b(a(extras.getByteArray("payload")));
                    if (m.a(extras.getString(PushConsts.CMD_ACTION))) {
                        b(context, extras);
                        return;
                    } else {
                        a(context, extras);
                        return;
                    }
                case PushConsts.GET_CLIENTID /*10002*/:
                    b = extras.getString("clientid");
                    a(this.a, b);
                    return;
                case PushConsts.THIRDPART_FEEDBACK /*10006*/:
                    return;
                default:
                    return;
            }
        }
    }

    private void a(final Context context, final Bundle bundle) {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ GexinSdkMessageReceiver c;

            public void run() {
                try {
                    Intent intent = new Intent(Action.START_ENTRY);
                    intent.addFlags(268435456);
                    intent.addFlags(67108864);
                    intent.putExtra(SocialConstants.PARAM_TYPE, "gexin");
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

    private void b(final Context context, final Bundle bundle) {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ GexinSdkMessageReceiver c;

            public void run() {
                boolean z;
                Intent intent = new Intent(Action.START_ENTRY);
                intent.addFlags(268435456);
                intent.addFlags(67108864);
                intent.putExtra(SocialConstants.PARAM_TYPE, "gexin");
                String str = "recommendPage";
                String[] a = GexinSdkMessageReceiver.c;
                int length = a.length;
                int i = 0;
                while (i < length) {
                    String str2 = a[i];
                    str = bundle.getString(str2);
                    if (str2.equals("rankId") && String.valueOf(RankCategoryFragment.ID_RANK_CATEGORY).equals(str)) {
                        str = "";
                    }
                    if (m.a(str) || (GexinSdkMessageReceiver.d.contains(str2) && !GexinSdkMessageReceiver.c(str))) {
                        i++;
                    } else {
                        intent.putExtra("push_type", str2);
                        if (GexinSdkMessageReceiver.c(str)) {
                            intent.putExtra(str2, Long.parseLong(str));
                        } else {
                            intent.putExtra(str2, str);
                        }
                        str2 + "=" + str;
                        z = false;
                        if (z) {
                            intent.putExtra("push_type", "push_redirect_id");
                            intent.putExtra("push_redirect_id", true);
                        }
                        context.startActivity(intent);
                    }
                }
                z = true;
                if (z) {
                    intent.putExtra("push_type", "push_redirect_id");
                    intent.putExtra("push_redirect_id", true);
                }
                context.startActivity(intent);
            }
        }, 1000);
    }

    private Bundle b(String str) {
        Bundle bundle = new Bundle();
        for (String str2 : str.split(";")) {
            int indexOf = str2.indexOf("=");
            if (indexOf > 0) {
                bundle.putString(str2.substring(0, indexOf), str2.substring(indexOf + 1));
            }
        }
        return bundle;
    }

    private static boolean c(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    private void a(final Context context, final String str) {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ GexinSdkMessageReceiver c;

            public void run() {
                Intent intent = new Intent(Action.PUSH_CLIENT_ID_BROADCAST);
                intent.putExtra("client_id", str);
                context.sendBroadcast(intent);
            }
        }, 1000);
    }
}
