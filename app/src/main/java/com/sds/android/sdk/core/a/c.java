package com.sds.android.sdk.core.a;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView.ScaleType;
import com.sds.android.sdk.lib.util.e;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* ImageLoadTask */
final class c {
    private static final byte[] b = new byte[8192];
    private Handler a = new Handler(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 2) {
                this.a.d.a((Bitmap) message.obj);
                this.a.c.a(this.a.d);
            }
        }
    };
    private a c;
    private e d;

    /* ImageLoadTask */
    public interface a {
        Bitmap a(String str, InputStream inputStream, int i, int i2, ScaleType scaleType);

        void a(e eVar);
    }

    c(e eVar, a aVar) {
        if (eVar == null || aVar == null) {
            throw new IllegalArgumentException("imageRequestInfo and listener must be not null!");
        }
        this.d = eVar;
        this.c = aVar;
    }

    private boolean a(InputStream inputStream) {
        String d = b.d(this.d.b(), this.d.c(), this.d.e(), this.d.f());
        Bitmap a = this.c.a(d, inputStream, this.d.e(), this.d.f(), this.d.h());
        if (a == null) {
            return false;
        }
        Message.obtain(this.a, 2, a).sendToTarget();
        return true;
    }

    void a() {
        String str = this.d.d() + ".tmp";
        try {
            if (a(this.d.b(), str) && e.c(str, this.d.d())) {
                b();
            } else {
                e.h(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean b() {
        InputStream fileInputStream;
        FileNotFoundException e;
        Throwable th;
        InputStream inputStream = null;
        try {
            fileInputStream = new FileInputStream(this.d.d());
            try {
                boolean a = a(fileInputStream);
                if (fileInputStream == null) {
                    return a;
                }
                try {
                    fileInputStream.close();
                    return a;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return a;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = fileInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileInputStream = null;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private boolean a(String str, String str2) {
        OutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream;
        FileNotFoundException e;
        BufferedOutputStream bufferedOutputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2;
        IOException e2;
        BufferedOutputStream bufferedOutputStream3;
        boolean z = false;
        BufferedInputStream bufferedInputStream2 = null;
        com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a(str, null, null);
        if (a != null && a.c() == 200) {
            try {
                fileOutputStream = new FileOutputStream(str2);
                try {
                    bufferedInputStream = new BufferedInputStream(a.e(), 8192);
                } catch (FileNotFoundException e3) {
                    e = e3;
                    bufferedOutputStream = null;
                    try {
                        e.printStackTrace();
                        a(a.e());
                        try {
                            a.e().close();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        try {
                            a.e().close();
                            if (bufferedInputStream2 != null) {
                                bufferedInputStream2.close();
                            }
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e42) {
                            e42.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e2 = e5;
                    bufferedInputStream = null;
                    try {
                        e2.printStackTrace();
                        try {
                            a.e().close();
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (bufferedOutputStream3 != null) {
                                bufferedOutputStream3.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e422) {
                            e422.printStackTrace();
                        }
                        return z;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        bufferedInputStream2 = bufferedInputStream;
                        a.e().close();
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedOutputStream2 = null;
                    a.e().close();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
                try {
                    bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream, 8192);
                    int i = 0;
                    while (true) {
                        try {
                            int read = bufferedInputStream.read(b);
                            if (read < 0) {
                                if (i >= 10) {
                                    break;
                                }
                                i++;
                            } else {
                                bufferedOutputStream2.write(b, 0, read);
                            }
                        } catch (FileNotFoundException e6) {
                            e = e6;
                            bufferedInputStream2 = bufferedInputStream;
                            bufferedOutputStream = bufferedOutputStream2;
                        } catch (IOException e7) {
                            e2 = e7;
                            bufferedOutputStream3 = bufferedOutputStream2;
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedInputStream2 = bufferedInputStream;
                        }
                    }
                    z = true;
                    try {
                        a.e().close();
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Exception e4222) {
                        e4222.printStackTrace();
                    }
                } catch (FileNotFoundException e8) {
                    e = e8;
                    BufferedInputStream bufferedInputStream3 = bufferedInputStream;
                    bufferedOutputStream = null;
                    bufferedInputStream2 = bufferedInputStream3;
                    e.printStackTrace();
                    a(a.e());
                    a.e().close();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return z;
                } catch (IOException e9) {
                    e2 = e9;
                    e2.printStackTrace();
                    a.e().close();
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream3 != null) {
                        bufferedOutputStream3.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    return z;
                } catch (Throwable th6) {
                    th = th6;
                    bufferedOutputStream2 = null;
                    bufferedInputStream2 = bufferedInputStream;
                    a.e().close();
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e10) {
                e = e10;
                fileOutputStream = null;
                bufferedOutputStream = null;
                e.printStackTrace();
                a(a.e());
                a.e().close();
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return z;
            } catch (IOException e11) {
                e2 = e11;
                fileOutputStream = null;
                bufferedInputStream = null;
                e2.printStackTrace();
                a.e().close();
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream3 != null) {
                    bufferedOutputStream3.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return z;
            } catch (Throwable th7) {
                th = th7;
                fileOutputStream = null;
                bufferedOutputStream2 = null;
                a.e().close();
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }
}
