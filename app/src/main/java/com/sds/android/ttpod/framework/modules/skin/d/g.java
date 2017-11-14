package com.sds.android.ttpod.framework.modules.skin.d;

import android.graphics.Rect;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* NinePatchChunk */
public class g {
    private final Rect a = new Rect();
    private int[] b;
    private int[] c;
    private int[] d;

    private static void a(int[] iArr, ByteBuffer byteBuffer) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = byteBuffer.getInt();
        }
    }

    private static void a(int i) {
        if (i == 0 || (i & 1) != 0) {
            throw new RuntimeException("invalid nine-patch: " + i);
        }
    }

    public Rect a() {
        return this.a;
    }

    public static g a(byte[] bArr) {
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder());
        if (order.get() == (byte) 0) {
            return null;
        }
        g gVar = new g();
        gVar.b = new int[order.get()];
        gVar.c = new int[order.get()];
        gVar.d = new int[order.get()];
        a(gVar.b.length);
        a(gVar.c.length);
        order.getInt();
        order.getInt();
        gVar.a.left = order.getInt();
        gVar.a.right = order.getInt();
        gVar.a.top = order.getInt();
        gVar.a.bottom = order.getInt();
        order.getInt();
        a(gVar.b, order);
        a(gVar.c, order);
        a(gVar.d, order);
        return gVar;
    }
}
