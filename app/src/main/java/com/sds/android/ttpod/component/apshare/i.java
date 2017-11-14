package com.sds.android.ttpod.component.apshare;

import com.sds.android.sdk.lib.util.g;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* SocketServer */
public class i {
    private ServerSocket a;
    private b b;
    private a c;

    /* SocketServer */
    public interface b {
        void a(Socket socket);
    }

    /* SocketServer */
    private class a extends Thread {
        final /* synthetic */ i a;
        private boolean b;

        private a(i iVar) {
            this.a = iVar;
            this.b = false;
        }

        public void run() {
            setName(getClass().getSimpleName());
            while (!this.b) {
                Socket socket = null;
                try {
                    if (!(this.a.a == null || this.a.a.isClosed())) {
                        g.d("SocketServer", "wait another client to connect...");
                        socket = this.a.a.accept();
                        g.d("SocketServer", "accept a new connection, addr:" + socket.getInetAddress());
                    }
                    if (!(socket == null || this.a.b == null)) {
                        socket.setKeepAlive(true);
                        this.a.b.a(socket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            g.a("SocketServer", getClass().getSimpleName() + " is shutdown");
        }

        public void a() {
            this.b = true;
            super.interrupt();
        }
    }

    public i(b bVar, int i) {
        this.b = bVar;
        try {
            this.a = new ServerSocket(i, 100);
            this.a.setSoTimeout(360000);
            g.a("SocketServer", "start server at port: " + i);
        } catch (Throwable e) {
            g.b("SocketServer", e.getMessage(), e);
            if (!(this.a == null || this.a.isClosed())) {
                try {
                    this.a.close();
                } catch (Exception e2) {
                    g.b("SocketServer", e2.getMessage(), e);
                }
            }
        }
        this.c = new a();
        this.c.setDaemon(true);
        this.c.setPriority(10);
        this.c.start();
    }

    public void a() {
        try {
            this.c.a();
            if (this.a != null) {
                this.a.close();
                g.a("SocketServer", "server socket is closed");
            }
        } catch (Throwable e) {
            g.b("SocketServer", e.getMessage(), e);
        }
    }
}
