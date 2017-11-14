package com.sds.android.ttpod.b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.sdk.lib.util.e;
import java.io.File;

/* ApkUtils */
public final class a {
    public static boolean a(Context context, String str) {
        if (!e.b(str)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        context.startActivity(intent);
        return true;
    }
}
