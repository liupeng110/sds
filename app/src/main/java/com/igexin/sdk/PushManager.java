package com.igexin.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import com.igexin.sdk.a.c;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PushManager {
    private static PushManager a;
    private long b = 0;
    private long c = 0;
    private byte[] d = null;

    private String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i : digest) {
                int i2;
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append(FeedbackItem.STATUS_WAITING);
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private byte[] a(Context context) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr = null;
        String str = "/data/data/" + context.getPackageName() + "/files/" + "init.pid";
        if (new File(str).exists()) {
            byte[] bArr2 = new byte[1024];
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        } catch (Exception e) {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e2) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                } catch (Exception e4) {
                    byteArrayOutputStream = bArr;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e6) {
                        }
                    }
                    return bArr;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    byteArrayOutputStream = bArr;
                    th = th4;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e7) {
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e8) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e9) {
                byteArrayOutputStream = bArr;
                fileInputStream = bArr;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return bArr;
            } catch (Throwable th32) {
                fileInputStream = bArr;
                byte[] bArr3 = bArr;
                th = th32;
                byteArrayOutputStream = bArr3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    public static PushManager getInstance() {
        if (a == null) {
            a = new PushManager();
        }
        return a;
    }

    public String getClientid(Context context) {
        if (this.d != null) {
            byte[] a = a(context);
            if (!(this.d == null || a == null || this.d.length != a.length)) {
                byte[] bArr = new byte[a.length];
                for (int i = 0; i < bArr.length; i++) {
                    bArr[i] = (byte) (this.d[i] ^ a[i]);
                }
                return new String(bArr);
            }
        }
        return null;
    }

    public String getVersion(Context context) {
        return PushBuildConfig.sdk_conf_version;
    }

    public void initialize(Context context) {
        try {
            String packageName = context.getApplicationContext().getPackageName();
            Intent intent = new Intent(context.getApplicationContext(), PushService.class);
            intent.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_SERVICE_INITIALIZE);
            intent.putExtra("op_app", packageName);
            context.getApplicationContext().startService(intent);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                String string = applicationInfo.metaData.getString("PUSH_APPID");
                String string2 = applicationInfo.metaData.getString("PUSH_APPSECRET");
                packageName = applicationInfo.metaData.get("PUSH_APPKEY") != null ? applicationInfo.metaData.get("PUSH_APPKEY").toString() : null;
                if (!string.equals("") && !string2.equals("") && !packageName.equals("")) {
                    this.d = a(string + string2 + packageName + context.getPackageName()).getBytes();
                }
            }
        } catch (Exception e) {
        }
    }

    public boolean isPushTurnedOn(Context context) {
        return !new c(context).c();
    }

    public boolean sendFeedbackMessage(Context context, String str, String str2, int i) {
        if (str == null || str2 == null || i < PushConsts.MIN_FEEDBACK_ACTION || i > PushConsts.MAX_FEEDBACK_ACTION) {
            return false;
        }
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "sendFeedbackMessage");
        intent.putExtra("taskid", str);
        intent.putExtra("messageid", str2);
        intent.putExtra("actionid", String.valueOf(i));
        context.sendBroadcast(intent);
        return true;
    }

    public boolean sendMessage(Context context, String str, byte[] bArr) {
        long currentTimeMillis = System.currentTimeMillis();
        if (str == null || bArr == null || ((long) bArr.length) > 4096 || currentTimeMillis - this.c < 1000) {
            return false;
        }
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "sendMessage");
        intent.putExtra("taskid", str);
        intent.putExtra("extraData", bArr);
        context.sendBroadcast(intent);
        return true;
    }

    public boolean setHeartbeatInterval(Context context, int i) {
        if (i < 0) {
            return false;
        }
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "setHeartbeatInterval");
        intent.putExtra("interval", i);
        context.sendBroadcast(intent);
        return true;
    }

    public boolean setSilentTime(Context context, int i, int i2) {
        if (i < 0 || i >= 24 || i2 < 0 || i2 > 23) {
            return false;
        }
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "setSilentTime");
        intent.putExtra("beginHour", i);
        intent.putExtra("duration", i2);
        context.sendBroadcast(intent);
        return true;
    }

    public boolean setSocketTimeout(Context context, int i) {
        if (i < 0) {
            return false;
        }
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "setSocketTimeout");
        intent.putExtra("timeout", i);
        context.sendBroadcast(intent);
        return true;
    }

    public int setTag(Context context, Tag[] tagArr) {
        if (tagArr == null) {
            return PushConsts.SETTAG_ERROR_NULL;
        }
        if (((long) tagArr.length) > 200) {
            return PushConsts.SETTAG_ERROR_COUNT;
        }
        if (this.b > System.currentTimeMillis() - 1000) {
            return PushConsts.SETTAG_ERROR_FREQUENCY;
        }
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "setTag");
        StringBuffer stringBuffer = new StringBuffer();
        for (Tag name : tagArr) {
            stringBuffer.append(name.getName());
            stringBuffer.append(SelectCountryActivity.SPLITTER);
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        intent.putExtra("tags", stringBuffer.toString());
        context.sendBroadcast(intent);
        this.b = System.currentTimeMillis();
        return 0;
    }

    public void stopService(Context context) {
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "stopService");
        context.sendBroadcast(intent);
    }

    public void turnOffPush(Context context) {
        Intent intent = new Intent(PushConsts.ACTION_BROADCAST_PUSHMANAGER);
        intent.putExtra(PushConsts.CMD_ACTION, "turnOffPush");
        context.sendBroadcast(intent);
    }

    public void turnOnPush(Context context) {
        String packageName = context.getApplicationContext().getPackageName();
        try {
            Intent intent = new Intent(context.getApplicationContext(), PushService.class);
            intent.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE);
            intent.putExtra("op_app", packageName);
            intent.putExtra("isSlave", true);
            context.getApplicationContext().startService(intent);
        } catch (Exception e) {
        }
    }
}
