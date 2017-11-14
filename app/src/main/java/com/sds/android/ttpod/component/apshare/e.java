package com.sds.android.ttpod.component.apshare;

import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* ReceiveSongClient */
public class e {
    private String a = com.sds.android.ttpod.framework.a.r();
    private c b;
    private Handler c;
    private String d;
    private ExecutorService e = Executors.newFixedThreadPool(1);
    private h f;
    private boolean g = false;

    /* ReceiveSongClient */
    public interface a {
        void a();
    }

    public e(String str, Handler handler, c cVar) {
        this.d = str;
        this.b = cVar;
        this.c = handler;
        if (TextUtils.isEmpty(this.a)) {
            throw new IllegalArgumentException("storeDir must be valid");
        }
        File file = new File(this.a);
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IllegalArgumentException("storeDir must be valid");
        }
    }

    private void a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            int parseInt = Integer.parseInt(bufferedReader.readLine());
            g.d("ReceiveSongClient", "count = " + parseInt);
            Message obtainMessage = this.c.obtainMessage();
            obtainMessage.what = 101;
            obtainMessage.obj = Integer.valueOf(parseInt);
            this.c.sendMessage(obtainMessage);
            for (int i = 0; i < parseInt; i++) {
                final com.sds.android.ttpod.fragment.apshare.a a = a.a(bufferedReader);
                if (a != null) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ e b;

                        public void run() {
                            if (this.b.b != null) {
                                this.b.b.d(a);
                            }
                        }
                    });
                }
            }
        } catch (Throwable e) {
            g.b("ReceiveSongClient", e.getMessage(), e);
        }
    }

    private void a(InputStream inputStream, com.sds.android.ttpod.fragment.apshare.a aVar) {
        Throwable e;
        long j;
        FileOutputStream fileOutputStream;
        byte[] bArr = new byte[32768];
        FileOutputStream fileOutputStream2 = null;
        long j2 = 0;
        File file = null;
        MediaItem a = a.a(aVar.a());
        final com.sds.android.ttpod.fragment.apshare.a aVar2 = new com.sds.android.ttpod.fragment.apshare.a(a);
        MediaItem a2;
        try {
            String a3 = a.a(a.getTitle().trim(), "");
            a3 = a3 + "." + a.b(a.getLocalDataSource());
            if (this.a.endsWith(File.separator)) {
                a3 = this.a + a3;
            } else {
                a3 = this.a + File.separatorChar + a3;
            }
            a.setLocalDataSource(a3);
            File file2 = new File(a3);
            try {
                a(file2);
                FileOutputStream fileOutputStream3 = new FileOutputStream(a3);
                int read;
                do {
                    try {
                        read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream3.write(bArr, 0, read);
                        j2 += (long) read;
                        if (this.b != null) {
                            this.c.post(new Runnable(this) {
                                final /* synthetic */ e c;

                                public void run() {
                                    if (this.c.b != null) {
                                        this.c.b.a(aVar2, j2);
                                    }
                                }
                            });
                        }
                        if (j2 >= a.getSize()) {
                            break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        File file3 = file2;
                        j = j2;
                        fileOutputStream = fileOutputStream3;
                        file = file3;
                    } catch (Throwable th) {
                        e = th;
                        fileOutputStream2 = fileOutputStream3;
                        file = file2;
                    }
                } while (read > 0);
                fileOutputStream3.flush();
                g.d("ReceiveSongClient", "=== 下载完成..." + a.getTitle() + ", transfer size = " + j2 + " === ");
                if (this.b != null && j2 == a.getSize()) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ e b;

                        public void run() {
                            if (this.b.b != null) {
                                this.b.b.e(aVar2);
                            }
                        }
                    });
                    a2 = k.a(a.getLocalDataSource());
                    b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, a2));
                    b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, a2));
                    com.sds.android.ttpod.framework.a.b.d.a.a("receive", "success");
                } else if (this.b != null) {
                    g.d("ReceiveSongClient", "[apshare]-transfer canceled");
                    if (this.b != null) {
                        this.c.post(new Runnable(this) {
                            final /* synthetic */ e b;

                            public void run() {
                                if (this.b.b != null) {
                                    this.b.b.g(aVar2);
                                }
                            }
                        });
                    }
                    com.sds.android.ttpod.framework.a.b.d.a.a("receive", "fail");
                    a(file2);
                }
                if (fileOutputStream3 != null) {
                    try {
                        fileOutputStream3.close();
                    } catch (Throwable e3) {
                        g.b("ReceiveSongClient", e3.getMessage(), e3);
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                file = file2;
                fileOutputStream = null;
                j = 0;
                try {
                    g.b("ReceiveSongClient", e3.getMessage(), e3);
                    if (this.b == null) {
                    }
                    if (this.b != null) {
                        g.d("ReceiveSongClient", "[apshare]-transfer canceled");
                        if (this.b != null) {
                            this.c.post(/* anonymous class already generated */);
                        }
                        com.sds.android.ttpod.framework.a.b.d.a.a("receive", "fail");
                        a(file);
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e32) {
                            g.b("ReceiveSongClient", e32.getMessage(), e32);
                        }
                    }
                } catch (Throwable th2) {
                    e32 = th2;
                    fileOutputStream2 = fileOutputStream;
                    j2 = j;
                    if (this.b == null) {
                    }
                    if (this.b != null) {
                        g.d("ReceiveSongClient", "[apshare]-transfer canceled");
                        if (this.b != null) {
                            this.c.post(/* anonymous class already generated */);
                        }
                        com.sds.android.ttpod.framework.a.b.d.a.a("receive", "fail");
                        a(file);
                    }
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable e5) {
                            g.b("ReceiveSongClient", e5.getMessage(), e5);
                        }
                    }
                    throw e32;
                }
            } catch (Throwable th3) {
                e32 = th3;
                file = file2;
                if (this.b == null) {
                }
                if (this.b != null) {
                    g.d("ReceiveSongClient", "[apshare]-transfer canceled");
                    if (this.b != null) {
                        this.c.post(/* anonymous class already generated */);
                    }
                    com.sds.android.ttpod.framework.a.b.d.a.a("receive", "fail");
                    a(file);
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw e32;
            }
        } catch (Exception e6) {
            e32 = e6;
            fileOutputStream = null;
            j = 0;
            g.b("ReceiveSongClient", e32.getMessage(), e32);
            if (this.b == null && j == a.getSize()) {
                this.c.post(/* anonymous class already generated */);
                a2 = k.a(a.getLocalDataSource());
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, a2));
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, a2));
                com.sds.android.ttpod.framework.a.b.d.a.a("receive", "success");
            } else if (this.b != null) {
                g.d("ReceiveSongClient", "[apshare]-transfer canceled");
                if (this.b != null) {
                    this.c.post(/* anonymous class already generated */);
                }
                com.sds.android.ttpod.framework.a.b.d.a.a("receive", "fail");
                a(file);
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th4) {
            e32 = th4;
            if (this.b == null && r4 == a.getSize()) {
                this.c.post(/* anonymous class already generated */);
                MediaItem a4 = k.a(a.getLocalDataSource());
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_RECENTLY_ADD, a4));
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM, MediaStorage.GROUP_ID_ALL_LOCAL, a4));
                com.sds.android.ttpod.framework.a.b.d.a.a("receive", "success");
            } else if (this.b != null) {
                g.d("ReceiveSongClient", "[apshare]-transfer canceled");
                if (this.b != null) {
                    this.c.post(/* anonymous class already generated */);
                }
                com.sds.android.ttpod.framework.a.b.d.a.a("receive", "fail");
                a(file);
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw e32;
        }
    }

    private void a(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public Future<Boolean> a(final String str, final ClientModel clientModel, final a aVar) {
        return this.e.submit(new Callable<Boolean>(this) {
            final /* synthetic */ e d;

            public /* synthetic */ Object call() throws Exception {
                return a();
            }

            public Boolean a() throws Exception {
                this.d.c();
                this.d.f.a(new h.b(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a(OutputStream outputStream) {
                        PrintWriter printWriter = new PrintWriter(outputStream);
                        printWriter.print(str + "\r\n");
                        printWriter.print(f.a(clientModel) + "\r\n");
                        printWriter.flush();
                        if (aVar != null) {
                            this.a.d.c.post(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    aVar.a();
                                }
                            });
                        }
                    }
                });
                this.d.f.a(new com.sds.android.ttpod.component.apshare.h.a(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a(InputStream inputStream) {
                        try {
                            String readLine = new BufferedReader(new InputStreamReader(inputStream)).readLine();
                            g.d("ReceiveSongClient", "msg: " + readLine);
                            if (readLine.equals("black_list")) {
                                this.a.d.d();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                this.d.f.b();
                return Boolean.valueOf(this.d.g);
            }
        });
    }

    public void a() {
        g.d("ReceiveSongClient", "is in black list " + this.g);
        if (!this.g) {
            this.e.execute(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c();
                    this.a.f.a(new h.b(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(OutputStream outputStream) {
                            this.a.a.a(outputStream, "get_share_song_list");
                        }
                    });
                    this.a.f.a(new com.sds.android.ttpod.component.apshare.h.a(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void a(InputStream inputStream) {
                            this.a.a.a(inputStream);
                        }
                    });
                    this.a.f.b();
                }
            });
        }
    }

    public void a(final com.sds.android.ttpod.fragment.apshare.a aVar) {
        if (!this.g) {
            this.e.execute(new Runnable(this) {
                final /* synthetic */ e b;

                public void run() {
                    this.b.c();
                    this.b.f.a(new h.b(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void a(OutputStream outputStream) {
                            this.a.b.a(outputStream, "download_song");
                            a.a(outputStream, aVar);
                        }
                    });
                    this.b.f.a(new com.sds.android.ttpod.component.apshare.h.a(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }

                        public void a(InputStream inputStream) {
                            this.a.b.a(inputStream, aVar);
                        }
                    });
                    if (this.b.f != null && !this.b.f.a()) {
                        this.b.f.a(new h.b(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public void a(OutputStream outputStream) {
                                this.a.b.a(outputStream, "over");
                            }
                        });
                        this.b.f.b();
                    }
                }
            });
        }
    }

    public void b() {
        if (!this.g) {
            new Thread(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.f != null && !this.a.f.a()) {
                        this.a.f.a(new h.b(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void a(OutputStream outputStream) {
                                this.a.a.a(outputStream, "download_cancel");
                            }
                        });
                        this.a.f.b();
                        this.a.f = null;
                        g.d("ReceiveSongClient", "client socket closed");
                    }
                }
            }).start();
        }
    }

    private void a(OutputStream outputStream, String str) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(str + "\r\n");
        printWriter.flush();
        g.d("ReceiveSongClient", "client send message: " + str + ", tid = " + Process.myTid());
    }

    private void c() {
        if (this.f == null || this.f.a()) {
            this.f = new h(this.d, 5001);
        }
    }

    private synchronized void d() {
        this.g = true;
    }
}
