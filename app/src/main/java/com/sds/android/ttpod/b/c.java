package com.sds.android.ttpod.b;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/* BlueToothUtils */
public class c {
    public static void a(Context context, File[] fileArr) {
        if (fileArr != null && fileArr.length > 0) {
            if (a(context)) {
                b(context, fileArr);
            } else {
                f.a((int) R.string.bluetooth_unsupport);
            }
        }
    }

    public static boolean a(Context context) {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public static void b(Context context, File[] fileArr) {
        String str;
        Intent intent = new Intent();
        intent.setType("*/*");
        if (fileArr.length > 1) {
            Serializable arrayList = new ArrayList(fileArr.length);
            for (File fromFile : fileArr) {
                arrayList.add(Uri.fromFile(fromFile));
            }
            intent.putExtra("android.intent.extra.STREAM", arrayList);
            intent.setAction("android.intent.action.SEND_MULTIPLE");
        } else {
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(fileArr[0]));
            intent.setAction("android.intent.action.SEND");
        }
        String str2 = "";
        String str3 = "";
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            if (resolveInfo.activityInfo.packageName.contains("bluetooth")) {
                str3 = resolveInfo.activityInfo.packageName;
                str = resolveInfo.activityInfo.name;
                break;
            }
        }
        str = str3;
        str3 = str2;
        intent.setClassName(str3, str);
        if (context instanceof Activity) {
            try {
                ((Activity) context).startActivityForResult(intent, 1);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
