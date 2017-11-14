package com.mradar.sdk.record;

import java.util.LinkedList;
import java.util.Queue;

/* RecordByteQueue */
public class l {
    private Queue<byte[]> a = new LinkedList();
    private int b = 0;
    private final int c = 49152;

    public void a(byte[] bArr) {
        if (this.b >= 49152) {
            this.a.poll();
        }
        this.a.offer(bArr);
        this.b += bArr.length;
    }

    public byte[] a() {
        return (byte[]) this.a.poll();
    }

    public int b() {
        return this.a.size();
    }

    public void c() {
        this.b = 0;
        this.a.clear();
    }
}
