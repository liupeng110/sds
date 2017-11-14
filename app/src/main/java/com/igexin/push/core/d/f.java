package com.igexin.push.core.d;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.igexin.a.b.a;
import com.igexin.push.core.bean.e;
import com.igexin.push.core.g;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushService;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class f implements Runnable {
    private Context a;
    private e b;
    private int c = 0;

    public f(Context context, e eVar) {
        this.a = context;
        this.b = eVar;
    }

    protected boolean a(String str, String str2, String str3) {
        if (str2 == null || str2.length() == 0) {
            return false;
        }
        if (str == null) {
            this.c = 3;
            com.igexin.push.core.a.f.a().f("url is null");
            return false;
        } else if (str.startsWith("http:/") || str.startsWith("https:/")) {
            Process.setThreadPriority(10);
            try {
                HttpResponse execute = new DefaultHttpClient().execute(new HttpGet(str));
                if (execute.getStatusLine().getStatusCode() == 200) {
                    InputStream content = execute.getEntity().getContent();
                    String str4 = g.ad + "/" + str2;
                    File file = new File(str4);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                    content.close();
                    if (a.a(this.a, str4).equals(str3)) {
                        File file2 = new File(g.ad + "/" + this.b.c());
                        file.renameTo(file2);
                        if (!this.b.g() && this.b.h() == 0) {
                            File file3 = new File(g.ac + "/" + this.b.c());
                            if (!file3.exists()) {
                                com.igexin.push.core.a.f.a().a(file2, file3, this.b.f());
                            } else if (!a.a(g.i, file3.getAbsolutePath()).equals(this.b.f())) {
                                file3.delete();
                                com.igexin.push.core.a.f.a().a(file2, file3, this.b.f());
                            }
                        }
                        return true;
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    this.c = 4;
                    return false;
                }
                this.c++;
                return false;
            } catch (IllegalArgumentException e) {
                this.c = 3;
                com.igexin.push.core.a.f.a().f(e.toString());
                return false;
            } catch (Exception e2) {
                this.c++;
                return false;
            }
        } else {
            this.c = 3;
            com.igexin.push.core.a.f.a().f("httpUrl:" + str + " is not a valid url...");
            return false;
        }
    }

    public void run() {
        Intent intent;
        while (!a(this.b.e(), this.b.c() + ".tmp", this.b.f())) {
            if (this.c >= 3) {
                intent = new Intent(this.a, PushService.class);
                intent.putExtra(PushConsts.CMD_ACTION, "com.igexin.sdk.action.extdownloadsuccess");
                intent.putExtra(StarCategory.KEY_STAR_CATEGORY_ID, this.b.a());
                intent.putExtra("result", false);
                this.a.startService(intent);
                return;
            }
        }
        intent = new Intent(this.a, PushService.class);
        intent.putExtra(PushConsts.CMD_ACTION, "com.igexin.sdk.action.extdownloadsuccess");
        intent.putExtra(StarCategory.KEY_STAR_CATEGORY_ID, this.b.a());
        intent.putExtra("result", true);
        this.a.startService(intent);
    }
}
