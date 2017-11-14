package com.mradar.sdk.record;

import android.content.Context;
import com.igexin.sdk.PushConsts;
import com.mradar.sdk.a.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* OfflineRecognizer */
public class i extends Thread {
    private Context a;
    private boolean b;
    private boolean c;
    private long d;
    private long e;
    private File f;
    private a g;
    private b h;
    private String i;

    protected void a() {
        this.b = true;
    }

    protected void b() {
        this.c = true;
        this.g = null;
    }

    public void run() {
        if (c.b(this.a)) {
            try {
                FileInputStream fileInputStream = new FileInputStream(this.f);
                this.h.a(this.a);
                Object obj = new byte[1600];
                while (!this.b && !this.c) {
                    try {
                        int read = fileInputStream.read(obj);
                        if (read == -1) {
                            a();
                        } else if (read > 0) {
                            byte[] bArr = new byte[read];
                            System.arraycopy(obj, 0, bArr, 0, read);
                            if (this.h.a(bArr) != 0) {
                                break;
                            }
                            this.e = ((long) read) + this.e;
                            if (this.d != 0 && this.e >= this.d) {
                                a();
                            }
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                if (!this.c) {
                    this.h.a();
                    String c = this.h.c();
                    switch (a(c)) {
                        case 0:
                            if (this.g != null) {
                                this.g.a(g.a(c));
                                break;
                            }
                            break;
                        case PushConsts.GET_MSG_DATA /*10001*/:
                            if (this.g != null) {
                                this.g.a(4011, this.i);
                                break;
                            }
                            break;
                        case PushConsts.GET_CLIENTID /*10002*/:
                            if (this.g != null) {
                                this.g.a(4012, this.i);
                                break;
                            }
                            break;
                        case 10003:
                        case 10004:
                        case PushConsts.CHECK_CLIENTID /*10005*/:
                        case PushConsts.THIRDPART_FEEDBACK /*10006*/:
                            if (this.g != null) {
                                this.g.a(4013, this.i);
                                break;
                            }
                            break;
                        case PushConsts.GET_SDKONLINESTATE /*10007*/:
                            if (this.g != null) {
                                this.g.a(4017, this.i);
                                break;
                            }
                            break;
                        case PushConsts.GET_SDKSERVICEPID /*10008*/:
                            if (this.g != null) {
                                this.g.a(4018, this.i);
                                break;
                            }
                            break;
                        default:
                            if (this.g != null) {
                                this.g.a(4012, this.i);
                                break;
                            }
                            break;
                    }
                }
                this.h.d();
            } catch (FileNotFoundException e4) {
                e4.printStackTrace();
                if (this.g != null) {
                    this.g.a(4006, "DATA_UNAVAILABLE");
                }
            }
        } else if (this.g != null) {
            this.g.a(4001, "NETWORK_UNAVAILABLE");
        }
    }

    private int a(String str) {
        try {
            try {
                JSONObject jSONObject = new JSONArray(str).getJSONObject(0);
                try {
                    int i = jSONObject.getInt("errorcode");
                    this.i = "";
                    try {
                        this.i = jSONObject.getString("error");
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
