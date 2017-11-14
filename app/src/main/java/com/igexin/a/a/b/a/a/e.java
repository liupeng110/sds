package com.igexin.a.a.b.a.a;

import com.igexin.a.a.b.c;
import com.igexin.a.a.b.d;
import com.igexin.a.a.b.f;
import com.igexin.a.a.c.a;
import java.io.EOFException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class e extends f {
    static volatile e l;
    Selector e;
    Selector f;
    AtomicBoolean g = new AtomicBoolean(false);
    AtomicBoolean h = new AtomicBoolean(true);
    volatile boolean i;
    final long j = 15000;
    int k;
    volatile long m;
    volatile long n;
    volatile long o;
    ConcurrentLinkedQueue p = new ConcurrentLinkedQueue();
    List q = new ArrayList(16);
    d r;
    ByteBuffer s = ByteBuffer.allocate(61440);
    boolean t;
    int u;
    final Comparator v = new f(this);

    public e(int i, String str, c cVar) {
        super(i, str, cVar);
    }

    public static e a(String str, c cVar) {
        if (l == null || l.F || l.x) {
            e eVar = new e(-2047, str, cVar);
            l = eVar;
            return eVar;
        } else if (l.a.equals(str)) {
            return l;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static e h() {
        return l;
    }

    final void a(SocketChannel socketChannel) {
        int read;
        do {
            try {
                read = socketChannel.read(this.s);
                if (read < 0) {
                    a.a("socketread|-1|");
                    this.u++;
                    if (this.u > 20) {
                        this.u = 0;
                        throw new EOFException("NioConnection Read EOF!");
                    }
                    return;
                } else if (read == 0) {
                    a.a("socketread|0|");
                    return;
                } else {
                    this.t = true;
                    this.s.flip();
                    int remaining = this.s.remaining();
                    d c;
                    if (d.f) {
                        c = d.c();
                        c.d += (long) remaining;
                    } else {
                        c = d.c();
                        c.b += (long) remaining;
                    }
                    if (this.b != null) {
                        this.b.c(this, this.d, this.s);
                    }
                    this.s.clear();
                }
            } catch (EOFException e) {
                a.a("exceptionsocketread|" + e.getMessage());
                throw e;
            } catch (Exception e2) {
                a.a("exceptionsocketread|" + e2.getMessage());
                throw new ClosedChannelException();
            }
        } while (read > 0);
    }

    public void a_() {
        boolean z = false;
        super.a_();
        if (this.d == null) {
            try {
                if (l() != null) {
                    l().release();
                }
                g();
            } finally {
                if (l() != null) {
                    l().acquire();
                }
            }
        } else if (this.g.get() || this.r.b) {
            if (this.g.get()) {
                b bVar = new b();
                bVar.a = 1;
                d.c().a((Object) bVar);
            }
            throw new ClosedChannelException();
        } else {
            if (!this.p.isEmpty()) {
                this.r.b(true);
            }
            if (this.m < 0) {
                this.m = 0;
            }
            this.n = System.currentTimeMillis();
            this.h.set(false);
            try {
                int select;
                if (l() != null) {
                    a.a("wakelock|niosockettask|off");
                    l().release();
                }
                if (this.m > 0) {
                    Q.b((this.n + this.m) + com.igexin.a.a.d.e.z);
                    select = this.e.select(this.m);
                    Q.f();
                } else {
                    select = this.e.select();
                }
                if (l() != null) {
                    l().acquire();
                    a.a("wakelock|niosockettask|on");
                }
                this.m = -1;
                this.o = System.currentTimeMillis() - this.n;
                if (this.o >= 30 || select != 0) {
                    this.k = 0;
                } else {
                    this.k++;
                    Thread.yield();
                    if (this.k > 59) {
                        a.a("exceptionrebuildselector");
                        this.f = Selector.open();
                        for (SelectionKey selectionKey : this.e.keys()) {
                            if (!selectionKey.isValid() || selectionKey.interestOps() == 0) {
                                selectionKey.cancel();
                            } else {
                                this.r.e = this.r.a.register(this.f, selectionKey.interestOps(), selectionKey.attachment());
                            }
                        }
                        this.k = 0;
                        this.e.selectNow();
                        this.e.close();
                        this.e = this.f;
                        return;
                    }
                }
                Iterator it;
                if (select > 0) {
                    this.t = false;
                    Set<SelectionKey> selectedKeys = this.e.selectedKeys();
                    for (SelectionKey selectionKey2 : selectedKeys) {
                        selectedKeys.remove(selectionKey2);
                        if (selectionKey2.isValid() && selectionKey2.isWritable()) {
                            this.m = b((SocketChannel) selectionKey2.channel());
                        }
                        if (selectionKey2.isValid() && selectionKey2.isReadable()) {
                            a((SocketChannel) selectionKey2.channel());
                        }
                    }
                } else if (!this.q.isEmpty()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    it = this.q.iterator();
                    while (it.hasNext()) {
                        g gVar = (g) it.next();
                        if (gVar.P != null) {
                            boolean z2;
                            if (!gVar.P.b()) {
                                gVar.o();
                                z2 = z;
                            } else if (this.t || z) {
                                gVar.o();
                                gVar.P.b(gVar);
                                z2 = z;
                            } else if (gVar.P.a(currentTimeMillis, gVar)) {
                                gVar.o();
                                gVar.P.a(gVar);
                                z2 = true;
                            } else {
                                long b = gVar.P.b(currentTimeMillis, gVar);
                                if (this.m < 0 || this.m < b) {
                                    this.m = b;
                                }
                            }
                            it.remove();
                            z = z2;
                        } else {
                            gVar.o();
                            it.remove();
                        }
                    }
                    if (z) {
                        throw new SocketTimeoutException("SocketTask do timeOut!");
                    }
                }
            } catch (Throwable th) {
                if (l() != null) {
                    l().acquire();
                    a.a("wakelock|niosockettask|on");
                }
            }
        }
    }

    public final int b() {
        return -2047;
    }

    /* JADX err: Inconsistent code. */
    final long b(java.nio.channels.SocketChannel r12) {
        /*
        r11 = this;
        r3 = 0;
        r0 = r11.p;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x019e;
    L_0x0009:
        r2 = 0;
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0081 }
    L_0x000e:
        r0 = r11.p;	 Catch:{ all -> 0x0081 }
        r0 = r0.peek();	 Catch:{ all -> 0x0081 }
        r0 = (com.igexin.a.a.b.a.a.g) r0;	 Catch:{ all -> 0x0081 }
        r1 = r11.d;	 Catch:{ all -> 0x0081 }
        r0.d = r1;	 Catch:{ all -> 0x0081 }
        r1 = r0.f;	 Catch:{ all -> 0x0081 }
        if (r1 == 0) goto L_0x003e;
    L_0x001e:
        r0 = r0.f;	 Catch:{ all -> 0x0081 }
        r1 = r2;
        r2 = r0;
    L_0x0022:
        r0 = r2.hasRemaining();	 Catch:{ all -> 0x0081 }
        if (r0 == 0) goto L_0x013a;
    L_0x0028:
        r0 = r12.write(r2);	 Catch:{ IOException -> 0x0056, Exception -> 0x0092 }
    L_0x002c:
        if (r0 <= 0) goto L_0x00cb;
    L_0x002e:
        r1 = com.igexin.a.a.b.d.f;	 Catch:{ all -> 0x0081 }
        if (r1 == 0) goto L_0x00be;
    L_0x0032:
        r1 = com.igexin.a.a.b.d.c();	 Catch:{ all -> 0x0081 }
        r6 = r1.e;	 Catch:{ all -> 0x0081 }
        r8 = (long) r0;	 Catch:{ all -> 0x0081 }
        r6 = r6 + r8;
        r1.e = r6;	 Catch:{ all -> 0x0081 }
        r1 = r0;
        goto L_0x0022;
    L_0x003e:
        r1 = r11.b;	 Catch:{ all -> 0x0081 }
        r6 = r11.d;	 Catch:{ all -> 0x0081 }
        r7 = r0.c;	 Catch:{ all -> 0x0081 }
        r1 = r1.d(r0, r6, r7);	 Catch:{ all -> 0x0081 }
        r1 = (byte[]) r1;	 Catch:{ all -> 0x0081 }
        r1 = (byte[]) r1;	 Catch:{ all -> 0x0081 }
        r1 = java.nio.ByteBuffer.wrap(r1);	 Catch:{ all -> 0x0081 }
        r0.f = r1;	 Catch:{ all -> 0x0081 }
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x0022;
    L_0x0056:
        r1 = move-exception;
        r0 = -1;
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r6.<init>();	 Catch:{ all -> 0x0081 }
        r7 = "exceptionsocketwrite|";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0081 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0081 }
        r1 = r6.append(r1);	 Catch:{ all -> 0x0081 }
        r6 = "|";
        r1 = r1.append(r6);	 Catch:{ all -> 0x0081 }
        r6 = r2.toString();	 Catch:{ all -> 0x0081 }
        r1 = r1.append(r6);	 Catch:{ all -> 0x0081 }
        r1 = r1.toString();	 Catch:{ all -> 0x0081 }
        com.igexin.a.a.c.a.a(r1);	 Catch:{ all -> 0x0081 }
        goto L_0x002c;
    L_0x0081:
        r0 = move-exception;
        r1 = r0;
        r2 = r11.r;
        r0 = r11.p;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x019b;
    L_0x008d:
        r0 = 1;
    L_0x008e:
        r2.b(r0);
        throw r1;
    L_0x0092:
        r0 = move-exception;
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r6.<init>();	 Catch:{ all -> 0x0081 }
        r7 = "exceptionsocketwrite|";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0081 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0081 }
        r0 = r6.append(r0);	 Catch:{ all -> 0x0081 }
        r6 = "|";
        r0 = r0.append(r6);	 Catch:{ all -> 0x0081 }
        r6 = r2.toString();	 Catch:{ all -> 0x0081 }
        r0 = r0.append(r6);	 Catch:{ all -> 0x0081 }
        r0 = r0.toString();	 Catch:{ all -> 0x0081 }
        com.igexin.a.a.c.a.a(r0);	 Catch:{ all -> 0x0081 }
        r0 = r1;
        goto L_0x002c;
    L_0x00be:
        r1 = com.igexin.a.a.b.d.c();	 Catch:{ all -> 0x0081 }
        r6 = r1.c;	 Catch:{ all -> 0x0081 }
        r8 = (long) r0;	 Catch:{ all -> 0x0081 }
        r6 = r6 + r8;
        r1.c = r6;	 Catch:{ all -> 0x0081 }
        r1 = r0;
        goto L_0x0022;
    L_0x00cb:
        if (r0 >= 0) goto L_0x00ed;
    L_0x00cd:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r0.<init>();	 Catch:{ all -> 0x0081 }
        r1 = "socketwrite|-1|";
        r0 = r0.append(r1);	 Catch:{ all -> 0x0081 }
        r1 = r2.toString();	 Catch:{ all -> 0x0081 }
        r0 = r0.append(r1);	 Catch:{ all -> 0x0081 }
        r0 = r0.toString();	 Catch:{ all -> 0x0081 }
        com.igexin.a.a.c.a.a(r0);	 Catch:{ all -> 0x0081 }
        r0 = new java.nio.channels.ClosedChannelException;	 Catch:{ all -> 0x0081 }
        r0.<init>();	 Catch:{ all -> 0x0081 }
        throw r0;	 Catch:{ all -> 0x0081 }
    L_0x00ed:
        if (r0 != 0) goto L_0x0118;
    L_0x00ef:
        r1 = r3 + 1;
        r6 = 140; // 0x8c float:1.96E-43 double:6.9E-322;
        if (r3 >= r6) goto L_0x0118;
    L_0x00f5:
        r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r11.wait(r6);	 Catch:{ all -> 0x0081 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r3.<init>();	 Catch:{ all -> 0x0081 }
        r6 = "socketwrite|0|";
        r3 = r3.append(r6);	 Catch:{ all -> 0x0081 }
        r6 = r2.toString();	 Catch:{ all -> 0x0081 }
        r3 = r3.append(r6);	 Catch:{ all -> 0x0081 }
        r3 = r3.toString();	 Catch:{ all -> 0x0081 }
        com.igexin.a.a.c.a.a(r3);	 Catch:{ all -> 0x0081 }
        r3 = r1;
        r1 = r0;
        goto L_0x0022;
    L_0x0118:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0081 }
        r0.<init>();	 Catch:{ all -> 0x0081 }
        r1 = "socketwrite|-2|";
        r0 = r0.append(r1);	 Catch:{ all -> 0x0081 }
        r1 = r2.toString();	 Catch:{ all -> 0x0081 }
        r0 = r0.append(r1);	 Catch:{ all -> 0x0081 }
        r0 = r0.toString();	 Catch:{ all -> 0x0081 }
        com.igexin.a.a.c.a.a(r0);	 Catch:{ all -> 0x0081 }
        r0 = new java.net.SocketTimeoutException;	 Catch:{ all -> 0x0081 }
        r1 = "write data error!";
        r0.<init>(r1);	 Catch:{ all -> 0x0081 }
        throw r0;	 Catch:{ all -> 0x0081 }
    L_0x013a:
        r0 = r11.p;	 Catch:{ all -> 0x0081 }
        r0 = r0.poll();	 Catch:{ all -> 0x0081 }
        r0 = (com.igexin.a.a.b.a.a.g) r0;	 Catch:{ all -> 0x0081 }
        r2 = r0.K;	 Catch:{ all -> 0x0081 }
        if (r2 <= 0) goto L_0x017b;
    L_0x0146:
        r2 = r0.P;	 Catch:{ all -> 0x0081 }
        if (r2 == 0) goto L_0x017b;
    L_0x014a:
        r0.a(r4);	 Catch:{ all -> 0x0081 }
        r2 = r11.q;	 Catch:{ all -> 0x0081 }
        r2.add(r0);	 Catch:{ all -> 0x0081 }
        r0 = r11.q;	 Catch:{ all -> 0x0081 }
        r2 = r11.v;	 Catch:{ all -> 0x0081 }
        java.util.Collections.sort(r0, r2);	 Catch:{ all -> 0x0081 }
    L_0x0159:
        r3 = 0;
        r0 = r11.p;	 Catch:{ all -> 0x0081 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0081 }
        if (r0 == 0) goto L_0x01a1;
    L_0x0162:
        r0 = r11.q;	 Catch:{ all -> 0x0081 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0081 }
        if (r0 == 0) goto L_0x0186;
    L_0x016a:
        r0 = -1;
    L_0x016c:
        r3 = r11.r;
        r2 = r11.p;
        r2 = r2.isEmpty();
        if (r2 != 0) goto L_0x0199;
    L_0x0176:
        r2 = 1;
    L_0x0177:
        r3.b(r2);
    L_0x017a:
        return r0;
    L_0x017b:
        r0.o();	 Catch:{ all -> 0x0081 }
        r2 = com.igexin.a.a.b.d.c();	 Catch:{ all -> 0x0081 }
        r2.a(r0);	 Catch:{ all -> 0x0081 }
        goto L_0x0159;
    L_0x0186:
        r1 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ all -> 0x0081 }
        r0 = r11.q;	 Catch:{ all -> 0x0081 }
        r2 = 0;
        r0 = r0.get(r2);	 Catch:{ all -> 0x0081 }
        r0 = (com.igexin.a.a.b.a.a.g) r0;	 Catch:{ all -> 0x0081 }
        r0 = r0.K;	 Catch:{ all -> 0x0081 }
        r2 = (long) r0;	 Catch:{ all -> 0x0081 }
        r0 = r1.toMillis(r2);	 Catch:{ all -> 0x0081 }
        goto L_0x016c;
    L_0x0199:
        r2 = 0;
        goto L_0x0177;
    L_0x019b:
        r0 = 0;
        goto L_0x008e;
    L_0x019e:
        r0 = -1;
        goto L_0x017a;
    L_0x01a1:
        r2 = r1;
        goto L_0x000e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.a.a.b.a.a.e.b(java.nio.channels.SocketChannel):long");
    }

    public final void d() {
        super.d();
        this.z = true;
        this.A = true;
        this.U = true;
    }

    protected void e() {
    }

    public void f() {
        Iterator it;
        try {
            this.r.a();
        } catch (Exception e) {
        }
        if (this.e != null) {
            try {
                this.e.selectNow();
                this.e.close();
            } catch (Exception e2) {
            }
        }
        this.e = null;
        this.g = null;
        if (!this.p.isEmpty()) {
            it = this.p.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                gVar.o();
                d.c().a((Object) gVar);
            }
            this.p.clear();
        }
        this.p = null;
        if (!this.q.isEmpty()) {
            for (g gVar2 : this.q) {
                gVar2.o();
                d.c().a((Object) gVar2);
            }
            this.q.clear();
        }
        this.s.clear();
        this.s = null;
        this.h = null;
        this.g = null;
        if (l == this) {
            l = null;
        }
        super.f();
    }

    final void g() {
        if (this.e == null) {
            this.e = Selector.open();
        }
        if (this.r == null) {
            this.r = new d(this.e);
        }
        if (!this.r.b()) {
            this.r.a(this.a);
        }
        if (this.g.get()) {
            b bVar = new b();
            bVar.a = 2;
            d.c().a((Object) bVar);
            throw new ClosedChannelException();
        } else if (this.e.select(15000) > 0) {
            Set<SelectionKey> selectedKeys = this.e.selectedKeys();
            for (SelectionKey selectionKey : selectedKeys) {
                selectedKeys.remove(selectionKey);
                if (selectionKey.isValid() && selectionKey.isConnectable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    if (socketChannel.finishConnect()) {
                        this.i = true;
                        this.d = new com.igexin.a.a.b.e();
                        this.d.a(this.r);
                        Socket socket = socketChannel.socket();
                        a.a("connected|" + (socket.getInetAddress() != null ? socketChannel.socket().getInetAddress().getHostAddress() : "0.0.0.0") + ":" + socketChannel.socket().getPort() + "|" + (socket.getLocalAddress() != null ? socketChannel.socket().getLocalAddress().getHostAddress() : "0.0.0.0") + ":" + socketChannel.socket().getLocalPort());
                    }
                }
            }
            if (this.d != null) {
                this.e.selectNow();
                this.e.close();
                this.e = null;
                this.e = Selector.open();
                this.r.a(this.e);
                this.r.e = this.r.c().register(this.e, 1);
            }
        } else {
            throw new SocketTimeoutException();
        }
    }

    public void i() {
        if (this.e == null) {
            throw new NullPointerException();
        } else if (!this.e.isOpen()) {
            throw new IllegalStateException();
        } else if (this.h.compareAndSet(false, true)) {
            this.e.wakeup();
        }
    }
}
