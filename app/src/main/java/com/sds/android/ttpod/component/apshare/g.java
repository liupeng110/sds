package com.sds.android.ttpod.component.apshare;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.component.apshare.i.b;
import com.sds.android.ttpod.framework.a.b.d.a;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* ShareSongServer */
public class g implements b {
    private ExecutorService a = Executors.newFixedThreadPool(3);
    private ExecutorService b = Executors.newFixedThreadPool(3);
    private c c;
    private b d;
    private List<MediaItem> e;
    private i f;
    private Handler g = new Handler(Looper.getMainLooper());
    private List<String> h;

    public g(List<MediaItem> list, c cVar, b bVar, int i) {
        this.e = list;
        this.c = cVar;
        this.d = bVar;
        this.f = new i(this, i);
    }

    public void a(final Socket socket) {
        this.a.execute(new Runnable(this) {
            final /* synthetic */ g b;

            public void run() {
                try {
                    this.b.b(socket);
                    try {
                        if (socket != null && socket.isClosed()) {
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Throwable e2) {
                    com.sds.android.sdk.lib.util.g.b("ShareSongServer", e2.getMessage(), e2);
                    try {
                        if (socket != null && socket.isClosed()) {
                            socket.close();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } catch (Throwable th) {
                    try {
                        if (socket != null && socket.isClosed()) {
                            socket.close();
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
        });
    }

    private void b(Socket socket) throws Exception {
        Exception e;
        String readLine;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String readLine2 = bufferedReader.readLine();
        com.sds.android.sdk.lib.util.g.d("ShareSongServer", "server receive message: " + readLine2 + ", tid = " + Process.myTid());
        if (readLine2.equals("get_share_song_list")) {
            a(socket.getOutputStream());
        } else if (readLine2.equals("download_song")) {
            final f fVar;
            try {
                fVar = new f(socket.getOutputStream(), this.c, a.a(bufferedReader));
                try {
                    this.b.submit(new Callable<Boolean>(this) {
                        final /* synthetic */ g b;

                        public /* synthetic */ Object call() throws Exception {
                            return a();
                        }

                        public Boolean a() throws Exception {
                            fVar.run();
                            return Boolean.valueOf(true);
                        }
                    });
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    readLine = bufferedReader.readLine();
                    com.sds.android.sdk.lib.util.g.d("ShareSongServer", "msg: " + readLine);
                    if (!readLine.equals("download_cancel")) {
                        fVar.a();
                        a.a("delivery", "fail");
                    }
                }
            } catch (Exception e3) {
                Exception exception = e3;
                fVar = null;
                e = exception;
                e.printStackTrace();
                readLine = bufferedReader.readLine();
                com.sds.android.sdk.lib.util.g.d("ShareSongServer", "msg: " + readLine);
                if (!readLine.equals("download_cancel")) {
                    fVar.a();
                    a.a("delivery", "fail");
                }
            }
            readLine = bufferedReader.readLine();
            com.sds.android.sdk.lib.util.g.d("ShareSongServer", "msg: " + readLine);
            if (!readLine.equals("download_cancel")) {
                fVar.a();
                a.a("delivery", "fail");
            }
        } else if (!readLine2.equals("who_am_i") && !readLine2.equals("disconnect")) {
        } else {
            if (this.h == null || !this.h.contains(socket.getInetAddress().toString().replace("/", ""))) {
                a(bufferedReader, readLine2);
                a(socket.getOutputStream(), "end");
                return;
            }
            a(socket.getOutputStream(), "black_list");
        }
    }

    private void a(OutputStream outputStream) {
        try {
            if (this.e != null) {
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.print(this.e.size() + "\r\n");
                printWriter.flush();
                for (MediaItem aVar : this.e) {
                    a.a(outputStream, new com.sds.android.ttpod.fragment.apshare.a(aVar));
                }
            }
        } catch (Throwable e) {
            com.sds.android.sdk.lib.util.g.b("ShareSongServer", e.getMessage(), e);
        }
    }

    private void a(BufferedReader bufferedReader, final String str) {
        try {
            final ClientModel clientModel = (ClientModel) f.a(bufferedReader.readLine(), ClientModel.class);
            if (clientModel != null && this.d != null && this.g != null) {
                this.g.post(new Runnable(this) {
                    final /* synthetic */ g c;

                    public void run() {
                        if (str.equals("who_am_i")) {
                            this.c.d.onConnected(clientModel);
                        } else if (str.equals("disconnect")) {
                            this.c.d.onDisconnected(clientModel);
                        }
                    }
                });
            }
        } catch (Throwable e) {
            com.sds.android.sdk.lib.util.g.b("ShareSongServer", e.getMessage(), e);
        }
    }

    private void a(OutputStream outputStream, String str) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print(str + "\r\n");
        printWriter.flush();
    }

    public void a() {
        this.f.a();
    }

    public void a(List<String> list) {
        this.h = list;
    }
}
