package com.igexin.push.core.a.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import com.igexin.push.a.j;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.g;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements a {
    public static HashMap a = new HashMap();
    private static final String b = j.a;

    private PendingIntent a(String str, String str2, String str3, int i) {
        Intent intent = new Intent("com.igexin.sdk.action.doaction");
        intent.putExtra("taskid", str);
        intent.putExtra("messageid", str2);
        intent.putExtra("appid", g.c);
        intent.putExtra("actionid", str3);
        intent.putExtra("accesstoken", g.ax);
        intent.putExtra("notifID", i);
        return PendingIntent.getBroadcast(g.i, new Random().nextInt(1000), intent, 134217728);
    }

    public b a(PushTaskBean pushTaskBean, BaseAction baseAction) {
        return b.success;
    }

    public BaseAction a(JSONObject jSONObject) {
        try {
            BaseAction hVar = new com.igexin.push.core.bean.h();
            hVar.setType("notification");
            hVar.setActionId(jSONObject.getString("actionid"));
            hVar.setDoActionId(jSONObject.getString("do"));
            String string = jSONObject.getString("title");
            String string2 = jSONObject.getString(FeedbackMessage.TYPE_TEXT);
            hVar.a(string);
            hVar.b(string2);
            if (jSONObject.has("logo") && !"".equals(jSONObject.getString("logo"))) {
                string = jSONObject.getString("logo");
                if (string.lastIndexOf(".png") == -1 && string.lastIndexOf(".jpeg") == -1) {
                    string = "null";
                } else {
                    int indexOf = string.indexOf(".png");
                    if (indexOf == -1) {
                        indexOf = string.indexOf(".jpeg");
                    }
                    if (indexOf != -1) {
                        string = string.substring(0, indexOf);
                    }
                }
                hVar.c(string);
            }
            if (jSONObject.has("is_noclear")) {
                hVar.a(jSONObject.getBoolean("is_noclear"));
            }
            if (jSONObject.has("is_novibrate")) {
                hVar.b(jSONObject.getBoolean("is_novibrate"));
            }
            if (jSONObject.has("is_noring")) {
                hVar.c(jSONObject.getBoolean("is_noring"));
            }
            if (jSONObject.has("is_chklayout")) {
                hVar.d(jSONObject.getBoolean("is_chklayout"));
            }
            if (jSONObject.has("logo_url")) {
                hVar.d(jSONObject.getString("logo_url"));
            }
            if (jSONObject.has("banner_url")) {
                hVar.e(jSONObject.getString("banner_url"));
            }
            return hVar;
        } catch (JSONException e) {
            return null;
        }
    }

    public void a(String str, String str2, com.igexin.push.core.bean.h hVar) {
        int currentTimeMillis = (int) System.currentTimeMillis();
        g.am.put(str, Integer.valueOf(currentTimeMillis));
        PendingIntent a = a(str, str2, hVar.getDoActionId(), currentTimeMillis);
        NotificationManager notificationManager = (NotificationManager) g.i.getSystemService("notification");
        Notification notification = new Notification();
        notification.tickerText = hVar.b();
        notification.defaults = 4;
        notification.ledARGB = -16711936;
        notification.ledOnMS = 1000;
        notification.ledOffMS = 3000;
        notification.flags = 1;
        if (hVar.c()) {
            notification.flags |= 32;
        } else {
            notification.flags |= 16;
        }
        if (!hVar.e()) {
            notification.defaults |= 1;
        }
        if (!hVar.d()) {
            notification.defaults |= 2;
        }
        int identifier = g.i.getResources().getIdentifier("push", "drawable", g.g);
        if (hVar.f() == null) {
            if (identifier != 0) {
                notification.icon = identifier;
            } else {
                notification.icon = 17301651;
            }
        } else if ("null".equals(hVar.f())) {
            notification.icon = 17301651;
        } else if (hVar.f().startsWith("@")) {
            String f = hVar.f();
            if (f.substring(1, f.length()).endsWith("email")) {
                notification.icon = 17301647;
            } else {
                notification.icon = 17301651;
            }
        } else {
            identifier = g.i.getResources().getIdentifier(hVar.f(), "drawable", g.g);
            if (identifier != 0) {
                notification.icon = identifier;
            } else {
                notification.icon = 17301651;
            }
        }
        if ((hVar.h() == null && hVar.g() == null) || !hVar.i()) {
            notification.setLatestEventInfo(g.i, hVar.a(), hVar.b(), a);
            notificationManager.notify(currentTimeMillis, notification);
        }
    }

    public boolean b(PushTaskBean pushTaskBean, BaseAction baseAction) {
        if (!(pushTaskBean == null || baseAction == null || !(baseAction instanceof com.igexin.push.core.bean.h))) {
            a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), (com.igexin.push.core.bean.h) baseAction);
        }
        return true;
    }
}
