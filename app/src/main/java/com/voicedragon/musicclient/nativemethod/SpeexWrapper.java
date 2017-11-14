package com.voicedragon.musicclient.nativemethod;

public class SpeexWrapper {
    private long a = init();

    private native void finalizer(long j);

    private native long init();

    private native byte[] native_encode(long j, byte[] bArr, boolean z);

    private native void native_encodeInit(long j, int i);

    private native void native_setQuality(long j, int i, int i2);

    static {
        System.loadLibrary("Speex");
    }

    public void a(int i) {
        if (this.a != 0) {
            native_encodeInit(this.a, i);
        }
    }

    public byte[] a(byte[] bArr, boolean z) {
        if (this.a != 0) {
            return native_encode(this.a, bArr, z);
        }
        return new byte[0];
    }

    public void a(int i, int i2) {
        if (this.a != 0) {
            native_setQuality(this.a, i, i2);
        }
    }

    public void a() {
        if (this.a != 0) {
            finalizer(this.a);
        }
        this.a = 0;
    }
}
