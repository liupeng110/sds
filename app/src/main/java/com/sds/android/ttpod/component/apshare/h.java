package com.sds.android.ttpod.component.apshare;

import com.sds.android.sdk.lib.util.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* SocketClient */
public class h {
    private Socket a;

    /* SocketClient */
    public interface b {
        void a(OutputStream outputStream);
    }

    /* SocketClient */
    public interface a {
        void a(InputStream inputStream);
    }

    public h(String str, int i) {
        try {
            this.a = new Socket(str, i);
            this.a.setSoTimeout(360000);
            this.a.setSendBufferSize(32768);
            this.a.setReceiveBufferSize(32768);
            g.a("SocketClient", "connected to server: " + str);
        } catch (Throwable e) {
            g.b("SocketClient", e.getMessage(), e);
        }
    }

    public boolean a() {
        if (this.a != null) {
            return this.a.isClosed();
        }
        return true;
    }

    public void b() {
        try {
            if (this.a != null && !this.a.isClosed()) {
                this.a.close();
                g.a("SocketClient", "client socket is closed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(b bVar) {
        if (this.a != null && this.a.isConnected() && bVar != null) {
            try {
                bVar.a(this.a.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void a(a aVar) {
        if (this.a != null && this.a.isConnected() && aVar != null) {
            try {
                aVar.a(this.a.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
