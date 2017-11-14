package com.sds.android.ttpod.component.apshare;

import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.fragment.apshare.a;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.FileInputStream;
import java.io.OutputStream;

/* SendSongHandler */
public class f implements Runnable {
    private a a;
    private OutputStream b;
    private c c;
    private Handler d = new Handler(Looper.getMainLooper());
    private boolean e;

    public f(OutputStream outputStream, c cVar, a aVar) {
        this.a = aVar;
        this.b = outputStream;
        this.c = cVar;
        this.e = false;
    }

    public void run() {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        int i = 0;
        MediaItem a = this.a.a();
        byte[] bArr = new byte[32768];
        try {
            fileInputStream = new FileInputStream(a.getLocalDataSource());
            try {
                if (this.c != null) {
                    this.d.post(new Runnable(this) {
                        final /* synthetic */ f a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.d(this.a.a);
                        }
                    });
                }
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0 || this.e) {
                        this.b.flush();
                        g.d("SendSongHandler", "结束传送歌曲: " + a.getTitle() + ", transfer ratio = " + (((double) i) / ((double) a.getSize())));
                    } else {
                        this.b.write(bArr, 0, read);
                        i += read;
                        if (this.c != null) {
                            final long j = (long) i;
                            this.d.post(new Runnable(this) {
                                final /* synthetic */ f b;

                                public void run() {
                                    this.b.c.a(this.b.a, j);
                                }
                            });
                        }
                    }
                }
                this.b.flush();
                g.d("SendSongHandler", "结束传送歌曲: " + a.getTitle() + ", transfer ratio = " + (((double) i) / ((double) a.getSize())));
                if (this.c != null && ((long) i) == this.a.a().getSize()) {
                    this.d.post(new Runnable(this) {
                        final /* synthetic */ f a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.e(this.a.a);
                            d.a.a("delivery", "success");
                        }
                    });
                } else if (this.c != null) {
                    this.d.post(new Runnable(this) {
                        final /* synthetic */ f a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.f(this.a.a);
                            d.a.a("delivery", "fail");
                        }
                    });
                }
                try {
                    fileInputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Exception e3) {
                e2 = e3;
            }
        } catch (Exception e4) {
            e2 = e4;
            fileInputStream = null;
            try {
                e2.printStackTrace();
                if (this.c != null && ((long) r1) == this.a.a().getSize()) {
                    this.d.post(/* anonymous class already generated */);
                } else if (this.c != null) {
                    this.d.post(/* anonymous class already generated */);
                }
                try {
                    fileInputStream.close();
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                if (this.c == null) {
                }
                if (this.c != null) {
                    this.d.post(/* anonymous class already generated */);
                }
                try {
                    fileInputStream.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (this.c == null && ((long) r1) == this.a.a().getSize()) {
                this.d.post(/* anonymous class already generated */);
            } else if (this.c != null) {
                this.d.post(/* anonymous class already generated */);
            }
            fileInputStream.close();
            throw th;
        }
    }

    public synchronized void a() {
        this.e = true;
    }
}
