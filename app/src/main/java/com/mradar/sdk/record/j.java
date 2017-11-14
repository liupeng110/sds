package com.mradar.sdk.record;

import android.content.Context;
import android.media.AudioRecord;
import com.igexin.sdk.PushConsts;
import com.mradar.sdk.a.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* OnlineRecognizer */
public class j extends Thread {
    private Context a;
    private boolean b;
    private boolean c;
    private boolean d = false;
    private boolean e = false;
    private String f;
    private long g;
    private long h;
    private File i;
    private a j;
    private b k = null;
    private l l;
    private AudioRecord m;
    private String n = "";

    protected j(Context context, String str, long j, File file, a aVar) {
        this.a = context;
        this.f = str;
        this.g = 16 * j;
        this.i = file;
        this.j = aVar;
        e.a("OnlineRecognizer", "OnlineRecognizer");
        this.l = new l();
        this.k = new b(str);
    }

    protected void a() {
        this.b = true;
    }

    protected void b() {
        this.k.b();
        this.c = true;
        this.d = false;
        this.j = null;
    }

    public void run() {
        int i = 1280;
        if (c.b(this.a)) {
            int minBufferSize = AudioRecord.getMinBufferSize(8000, 16, 2);
            if (minBufferSize > 1280) {
                i = minBufferSize;
            }
            this.m = new AudioRecord(6, 8000, 16, 2, i);
            if (this.m.getState() != 1) {
                if (!(this.j == null || this.d)) {
                    this.j.a(4004, "RECORD_INIT_FAIL");
                }
                this.d = false;
                if (this.m != null) {
                    this.m.stop();
                    this.m.release();
                    this.m = null;
                    return;
                }
                return;
            }
            FileOutputStream fileOutputStream;
            byte[] bArr;
            int read;
            byte[] bArr2;
            ByteArrayOutputStream byteArrayOutputStream;
            int i2;
            double a;
            String c;
            try {
                if (this.i != null) {
                    File parentFile = this.i.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(this.i);
                    bArr = new byte[1280];
                    this.m.startRecording();
                    while (!this.b && !this.c) {
                        try {
                            read = this.m.read(bArr, 0, bArr.length);
                            if (read > 0) {
                                bArr2 = new byte[read];
                                System.arraycopy(bArr, 0, bArr2, 0, read);
                                if (this.d) {
                                    if (!(this.e || this.c)) {
                                        this.k.a(this.a);
                                        this.e = true;
                                    }
                                    i = this.l.b();
                                    if (i <= 0) {
                                        byteArrayOutputStream = new ByteArrayOutputStream();
                                        for (i2 = 0; i2 < i; i2++) {
                                            byteArrayOutputStream.write(this.l.a());
                                        }
                                        this.k.a(byteArrayOutputStream.toByteArray());
                                        i2 = this.k.a(bArr2);
                                        byteArrayOutputStream.reset();
                                    } else {
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.write(bArr2);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        i2 = this.k.a(bArr2);
                                    }
                                    a = c.a(bArr, read);
                                    if (this.j != null) {
                                        this.j.a(a);
                                    }
                                    if (i2 == 0) {
                                        break;
                                    }
                                    this.h += (long) read;
                                    if (this.g != 0 && this.h >= this.g) {
                                        a();
                                    }
                                } else {
                                    this.l.a(bArr2);
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            if (this.d) {
                                this.d = false;
                            } else {
                                this.j.a(4004, "RECORD_INIT_FAIL");
                            }
                            if (this.m != null && this.m.getState() == 1) {
                                this.m.stop();
                                this.m.release();
                                this.m = null;
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } catch (Throwable th) {
                            if (this.m != null && this.m.getState() == 1) {
                                this.m.stop();
                                this.m.release();
                                this.m = null;
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                        }
                    }
                    this.l.c();
                    if (this.j != null) {
                        this.j.a();
                    }
                    if (this.m != null && this.m.getState() == 1) {
                        this.m.stop();
                        this.m.release();
                        this.m = null;
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    if (!this.c) {
                        this.k.a();
                        c = this.k.c();
                        if (!this.c) {
                            e.a("OnlineRecognizer", "result:" + c);
                            switch (a(c)) {
                                case 0:
                                    if (this.j != null) {
                                        this.j.a(g.a(c));
                                        break;
                                    }
                                    break;
                                case PushConsts.GET_MSG_DATA /*10001*/:
                                    if (this.j != null) {
                                        this.j.a(4011, this.n);
                                        break;
                                    }
                                    break;
                                case PushConsts.GET_CLIENTID /*10002*/:
                                    if (this.j != null) {
                                        this.j.a(4012, this.n);
                                        break;
                                    }
                                    break;
                                case 10003:
                                    if (this.j != null) {
                                        this.j.a(4019, this.n);
                                        break;
                                    }
                                    break;
                                case 10004:
                                case PushConsts.CHECK_CLIENTID /*10005*/:
                                case PushConsts.THIRDPART_FEEDBACK /*10006*/:
                                    if (this.j != null) {
                                        this.j.a(4013, this.n);
                                        break;
                                    }
                                    break;
                                case PushConsts.GET_SDKONLINESTATE /*10007*/:
                                    if (this.j != null) {
                                        this.j.a(4017, this.n);
                                        break;
                                    }
                                    break;
                                case PushConsts.GET_SDKSERVICEPID /*10008*/:
                                    if (this.j != null) {
                                        this.j.a(4018, this.n);
                                        break;
                                    }
                                    break;
                                case PushConsts.SETTAG_ERROR_COUNT /*20001*/:
                                    if (this.j != null) {
                                        this.j.a(4002, this.n);
                                        break;
                                    }
                                    break;
                                case 30001:
                                    if (this.j != null) {
                                        this.j.a(4012, this.n);
                                        break;
                                    }
                                    break;
                                default:
                                    if (this.j != null) {
                                        this.j.a(4012, this.n);
                                        break;
                                    }
                                    break;
                            }
                        }
                    }
                    this.k.b();
                    this.k.d();
                }
            } catch (FileNotFoundException e4) {
                e4.printStackTrace();
            }
            fileOutputStream = null;
            bArr = new byte[1280];
            this.m.startRecording();
            while (!this.b) {
                read = this.m.read(bArr, 0, bArr.length);
                if (read > 0) {
                    bArr2 = new byte[read];
                    System.arraycopy(bArr, 0, bArr2, 0, read);
                    if (this.d) {
                        this.k.a(this.a);
                        this.e = true;
                        i = this.l.b();
                        if (i <= 0) {
                            if (fileOutputStream != null) {
                                fileOutputStream.write(bArr2);
                            }
                            i2 = this.k.a(bArr2);
                        } else {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            for (i2 = 0; i2 < i; i2++) {
                                byteArrayOutputStream.write(this.l.a());
                            }
                            this.k.a(byteArrayOutputStream.toByteArray());
                            i2 = this.k.a(bArr2);
                            byteArrayOutputStream.reset();
                        }
                        a = c.a(bArr, read);
                        if (this.j != null) {
                            this.j.a(a);
                        }
                        if (i2 == 0) {
                            break;
                        }
                        this.h += (long) read;
                        a();
                    } else {
                        this.l.a(bArr2);
                    }
                }
            }
            this.l.c();
            if (this.j != null) {
                this.j.a();
            }
            this.m.stop();
            this.m.release();
            this.m = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (!this.c) {
                this.k.a();
                c = this.k.c();
                if (this.c) {
                    e.a("OnlineRecognizer", "result:" + c);
                    switch (a(c)) {
                        case 0:
                            if (this.j != null) {
                                this.j.a(g.a(c));
                                break;
                            }
                            break;
                        case PushConsts.GET_MSG_DATA /*10001*/:
                            if (this.j != null) {
                                this.j.a(4011, this.n);
                                break;
                            }
                            break;
                        case PushConsts.GET_CLIENTID /*10002*/:
                            if (this.j != null) {
                                this.j.a(4012, this.n);
                                break;
                            }
                            break;
                        case 10003:
                            if (this.j != null) {
                                this.j.a(4019, this.n);
                                break;
                            }
                            break;
                        case 10004:
                        case PushConsts.CHECK_CLIENTID /*10005*/:
                        case PushConsts.THIRDPART_FEEDBACK /*10006*/:
                            if (this.j != null) {
                                this.j.a(4013, this.n);
                                break;
                            }
                            break;
                        case PushConsts.GET_SDKONLINESTATE /*10007*/:
                            if (this.j != null) {
                                this.j.a(4017, this.n);
                                break;
                            }
                            break;
                        case PushConsts.GET_SDKSERVICEPID /*10008*/:
                            if (this.j != null) {
                                this.j.a(4018, this.n);
                                break;
                            }
                            break;
                        case PushConsts.SETTAG_ERROR_COUNT /*20001*/:
                            if (this.j != null) {
                                this.j.a(4002, this.n);
                                break;
                            }
                            break;
                        case 30001:
                            if (this.j != null) {
                                this.j.a(4012, this.n);
                                break;
                            }
                            break;
                        default:
                            if (this.j != null) {
                                this.j.a(4012, this.n);
                                break;
                            }
                            break;
                    }
                }
            }
            this.k.b();
            this.k.d();
        } else if (this.j != null && !f.g) {
            this.j.a(4001, "NETWORK_UNAVAILABLE");
        }
    }

    private int a(String str) {
        try {
            try {
                JSONObject jSONObject = new JSONArray(str).getJSONObject(0);
                try {
                    int i = jSONObject.getInt("errorcode");
                    this.n = "";
                    try {
                        this.n = jSONObject.getString("error");
                        return i;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return i;
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return 0;
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
                return -1;
            }
        } catch (JSONException e32) {
            e32.printStackTrace();
            return -1;
        }
    }
}
