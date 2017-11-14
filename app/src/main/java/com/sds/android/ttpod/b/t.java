package com.sds.android.ttpod.b;

import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Parcelable;

/* ShortcutUtil */
public class t {
    public static void a(Context context, Class cls, int i, int i2) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra("duplicate", false);
        intent.putExtra("android.intent.extra.shortcut.NAME", context.getString(i2));
        intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(context.getApplicationContext(), i));
        Parcelable intent2 = new Intent("android.intent.action.MAIN");
        intent2.setClassName(context, cls.getName());
        intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
        context.sendBroadcast(intent);
    }
}
