package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.SupportService;

public class MediaButtonReceiver extends BroadcastReceiver {
    private static long a = 0;
    private static int b = 0;
    private static Handler c = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    MediaButtonReceiver.c.removeMessages(2);
                    MediaButtonReceiver.j();
                    return;
                case 2:
                    int i = message.arg1;
                    if (i == 1 && !MediaButtonReceiver.c.hasMessages(1)) {
                        MediaButtonReceiver.h();
                        return;
                    } else if (i == 2) {
                        MediaButtonReceiver.i();
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };

    /* JADX err: Inconsistent code. */
    public void onReceive(android.content.Context r14, android.content.Intent r15) {
        /*
        r13 = this;
        r12 = 0;
        r10 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r9 = 1;
        r8 = 2;
        if (r15 == 0) goto L_0x0023;
    L_0x0007:
        r0 = "MediaButtonReceiver";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "onReceive: ";
        r1 = r1.append(r2);
        r2 = r15.toString();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.sds.android.sdk.lib.util.g.a(r0, r1);
    L_0x0023:
        r0 = com.sds.android.ttpod.framework.storage.environment.b.v();
        if (r0 == 0) goto L_0x00b6;
    L_0x0029:
        r0 = "android.intent.action.MEDIA_BUTTON";
        r1 = r15.getAction();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x00b6;
    L_0x0035:
        r0 = "android.intent.extra.KEY_EVENT";
        r0 = r15.getParcelableExtra(r0);
        r0 = (android.view.KeyEvent) r0;
        if (r0 == 0) goto L_0x00b6;
    L_0x003f:
        r3 = r0.getKeyCode();
        r4 = r0.getAction();
        r6 = r0.getEventTime();
        r1 = "Receiver";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r5 = "keyCode: ";
        r2 = r2.append(r5);
        r2 = r2.append(r3);
        r5 = " action: ";
        r2 = r2.append(r5);
        r2 = r2.append(r4);
        r5 = " eventTime: ";
        r2 = r2.append(r5);
        r2 = r2.append(r6);
        r2 = r2.toString();
        com.sds.android.sdk.lib.util.g.a(r1, r2);
        r2 = 0;
        switch(r3) {
            case 79: goto L_0x00ba;
            case 85: goto L_0x00d9;
            case 86: goto L_0x00b7;
            case 87: goto L_0x00cd;
            case 88: goto L_0x00d0;
            case 126: goto L_0x00d3;
            case 127: goto L_0x00d6;
            default: goto L_0x007b;
        };
    L_0x007b:
        r1 = r2;
    L_0x007c:
        r13.a(r3);
        if (r1 == 0) goto L_0x00b6;
    L_0x0081:
        if (r4 != 0) goto L_0x011f;
    L_0x0083:
        r0 = r0.getRepeatCount();
        if (r0 != 0) goto L_0x00ad;
    L_0x0089:
        r0 = 79;
        if (r3 != r0) goto L_0x0106;
    L_0x008d:
        r0 = a;
        r0 = r6 - r0;
        r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
        if (r0 >= 0) goto L_0x00eb;
    L_0x0095:
        r0 = b;
        if (r0 != r9) goto L_0x00dc;
    L_0x0099:
        b = r8;
        r0 = c;
        r0.removeMessages(r8);
        r0 = c;
        r1 = c;
        r1 = r1.obtainMessage(r8, r8, r12);
        r0.sendMessageDelayed(r1, r10);
        a = r6;
    L_0x00ad:
        r0 = r13.isOrderedBroadcast();
        if (r0 == 0) goto L_0x00b6;
    L_0x00b3:
        r13.abortBroadcast();
    L_0x00b6:
        return;
    L_0x00b7:
        r1 = "stop_command";
        goto L_0x007c;
    L_0x00ba:
        r1 = "phone";
        r1 = r14.getSystemService(r1);
        r1 = (android.telephony.TelephonyManager) r1;
        r1 = r1.getCallState();
        if (r1 == r9) goto L_0x007b;
    L_0x00c8:
        if (r1 == r8) goto L_0x007b;
    L_0x00ca:
        r1 = "play_pause_command";
        goto L_0x007c;
    L_0x00cd:
        r1 = "next_command";
        goto L_0x007c;
    L_0x00d0:
        r1 = "previous_command";
        goto L_0x007c;
    L_0x00d3:
        r1 = "play_pause_command";
        goto L_0x007c;
    L_0x00d6:
        r1 = "play_pause_command";
        goto L_0x007c;
    L_0x00d9:
        r1 = "play_pause_command";
        goto L_0x007c;
    L_0x00dc:
        r0 = c;
        r0.removeMessages(r8);
        r0 = 0;
        a = r0;
        b = r12;
        k();
        goto L_0x00ad;
    L_0x00eb:
        a = r6;
        b = r9;
        r0 = c;
        r1 = c;
        r1 = r1.obtainMessage(r8, r9, r12);
        r0.sendMessageDelayed(r1, r10);
        r0 = c;
        r1 = c;
        r1 = r1.obtainMessage(r9);
        r0.sendMessageDelayed(r1, r10);
        goto L_0x00ad;
    L_0x0106:
        r0 = com.sds.android.ttpod.framework.base.BaseApplication.e();
        r2 = new android.content.Intent;
        r3 = com.sds.android.ttpod.framework.base.BaseApplication.e();
        r4 = com.sds.android.ttpod.framework.support.SupportService.class;
        r2.<init>(r3, r4);
        r3 = "command";
        r1 = r2.putExtra(r3, r1);
        r0.startService(r1);
        goto L_0x00ad;
    L_0x011f:
        r0 = c;
        r0.removeMessages(r9);
        goto L_0x00ad;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.support.monitor.MediaButtonReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    private void a(int i) {
        if (i == 86 || i == 79 || i == 87 || i == 88 || i == TransportMediator.KEYCODE_MEDIA_PLAY || i == TransportMediator.KEYCODE_MEDIA_PAUSE || i == 85) {
            new SSystemEvent("SYS_HEADSET", String.valueOf(i)).post();
        }
    }

    private static void h() {
        g.c("MediaButtonReceiver", "performSingleClick");
        BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", "play_pause_command"));
    }

    private static void i() {
        g.c("MediaButtonReceiver", "performDoubleClick");
        BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", b.w() ? "previous_command" : "next_command"));
    }

    private static void j() {
        g.c("MediaButtonReceiver", "performLongPress");
        BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", b.w() ? "next_command" : "previous_command"));
    }

    private static void k() {
        g.c("MediaButtonReceiver", "performThreeClick");
        BaseApplication.e().startService(new Intent(BaseApplication.e(), SupportService.class).putExtra("command", b.w() ? "next_command" : "previous_command"));
    }

    public static void a() {
        g.a("MediaButtonReceiver", "reloadMediaButtonMonitorDelay");
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                MediaButtonReceiver.c();
            }
        }, 500);
    }

    public static void b() {
        a.a(new Runnable() {
            public void run() {
                MediaButtonReceiver.c();
            }
        });
    }

    public static void c() {
        g.a("MediaButtonReceiver", "registerMediaButtonEvent");
        com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor");
        try {
            Context e = BaseApplication.e();
            AudioManager audioManager = (AudioManager) e.getSystemService("audio");
            com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor  after getSystemService");
            ComponentName componentName = new ComponentName(e, MediaButtonReceiver.class);
            audioManager.unregisterMediaButtonEventReceiver(new ComponentName(e, "com.sds.android.ttpod.core.playback.MediaButtonIntentReceiver"));
            audioManager.unregisterMediaButtonEventReceiver(new ComponentName(e, "com.sds.android.ttpod.app.support.monitor.MediaButtonReceiver"));
            com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor  after unregisterMediaButtonEventReceiver");
            boolean v = b.v();
            com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor  after isHeadsetControlEnabled");
            if (v) {
                audioManager.registerMediaButtonEventReceiver(componentName);
                g.a("MediaButtonReceiver", "registerMediaButtonEvent...");
            } else {
                audioManager.unregisterMediaButtonEventReceiver(componentName);
                g.a("MediaButtonReceiver", "unregisterMediaButtonEvent...");
            }
            com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor  after if (enabled)  enable=" + v);
            BaseApplication.e().getPackageManager().setComponentEnabledSetting(componentName, v ? 1 : 2, 1);
            com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor  after setComponentEnabledSetting");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        com.sds.android.ttpod.framework.a.a.b.a("MediaButtonReceiver", "reloadMediaButtonMonitor END");
    }
}
