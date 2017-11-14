package com.igexin.push.core;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.igexin.push.a.j;
import com.igexin.push.a.k;
import com.igexin.push.core.a.f;
import com.igexin.push.core.b.c;
import com.igexin.sdk.PushConsts;

public class e extends Handler {
    private static String a = j.a;

    public void handleMessage(Message message) {
        try {
            if (message.what == a.c) {
                Intent intent = (Intent) message.obj;
                if (intent != null && intent.hasExtra(PushConsts.CMD_ACTION)) {
                    String stringExtra = intent.getStringExtra(PushConsts.CMD_ACTION);
                    if (stringExtra.equals(PushConsts.ACTION_SERVICE_INITIALIZE)) {
                        f.a().a(intent);
                    } else if (stringExtra.equals(PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE)) {
                        f.a().b(intent);
                    } else if (stringExtra.equals(PushConsts.ACTION_BROADCAST_REFRESHLS)) {
                        if (k.r) {
                            c.a().b(intent);
                        }
                    } else if (stringExtra.equals(PushConsts.ACTION_BROADCAST_PUSHMANAGER)) {
                        f.a().a(intent.getBundleExtra("bundle"));
                    } else if (stringExtra.equals(PushConsts.ACTION_BROADCAST_USER_PRESENT)) {
                        g.M = System.currentTimeMillis();
                        Object obj = 1;
                        if (f.a().a(System.currentTimeMillis())) {
                            if ("1".equals(f.a().g("ccs"))) {
                                obj = null;
                            }
                        }
                        if (obj != null) {
                            f.a().z();
                        }
                    } else if (stringExtra.equals("com.igexin.sdk.action.extdownloadsuccess")) {
                        f.a().d(intent);
                    }
                }
            } else if (message.what == a.d) {
                c.a().a((Intent) message.obj);
            } else if (message.what == a.e) {
                f.a().c((Intent) message.obj);
            } else if (message.what == a.f) {
                f.a().c((Intent) message.obj);
            } else if (message.what == a.g) {
            } else {
                if (message.what == a.h) {
                    Bundle bundle = (Bundle) message.obj;
                    f.a().b(bundle.getString("taskid"), bundle.getString("messageid"), bundle.getString("actionid"));
                } else if (message.what == a.i) {
                    f.a().a((com.igexin.push.core.bean.f) message.obj);
                } else if (message.what == a.j) {
                    f.a().u();
                }
            }
        } catch (Exception e) {
        }
    }
}
