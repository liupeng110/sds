package com.sds.android.ttpod.framework.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/* CodeIdentifyInputStreamReader */
public class c extends Reader {
    private final ByteBuffer a = ByteBuffer.allocate(8192);
    private InputStream b;
    private CharsetDecoder c;
    private boolean d = false;

    public c(InputStream inputStream) {
        int read;
        this.a.limit(this.a.capacity());
        try {
            read = inputStream.read(this.a.array(), this.a.arrayOffset(), this.a.capacity());
        } catch (Exception e) {
            e.printStackTrace();
            read = 0;
        }
        if (read > 0) {
            String a = a(this.a);
            this.b = inputStream;
            try {
                a(inputStream, a);
            } catch (UnsupportedEncodingException e2) {
                this.c = Charset.defaultCharset().newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
            }
            this.a.limit(read);
        }
    }

    private void a(InputStream inputStream, String str) throws UnsupportedEncodingException {
        if (str == null) {
            throw new NullPointerException("charsetName == null");
        }
        this.b = inputStream;
        try {
            this.c = Charset.forName(str).newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
        } catch (Throwable e) {
            throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e));
        }
    }

    private String a(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        if (byteBuffer.get() == (byte) -1 && byteBuffer.get() == (byte) -2) {
            return "UTF-16LE";
        }
        byteBuffer.reset();
        if (byteBuffer.get() == (byte) -2 && byteBuffer.get() == (byte) -1) {
            return "UTF-16BE";
        }
        byteBuffer.reset();
        if (byteBuffer.get() == (byte) -17 && byteBuffer.get() == (byte) -69 && byteBuffer.get() == (byte) -65) {
            return "UTF-8";
        }
        byteBuffer.reset();
        if (d.a(byteBuffer.array())) {
            return "UTF-8";
        }
        return "GBK";
    }

    public boolean ready() throws IOException {
        boolean z = false;
        synchronized (this.lock) {
            if (this.b == null) {
                throw new IOException("InputStreamReader is closed");
            }
            try {
                if (this.a.hasRemaining() || this.b.available() > 0) {
                    z = true;
                }
            } catch (IOException e) {
            }
        }
        return z;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3 = 0;
        synchronized (this.lock) {
            if (a()) {
                a(cArr.length, i, i2);
                if (i2 == 0) {
                } else {
                    CoderResult coderResult;
                    CharBuffer wrap = CharBuffer.wrap(cArr, i, i2);
                    CoderResult coderResult2 = CoderResult.UNDERFLOW;
                    if (!this.a.hasRemaining()) {
                        i3 = 1;
                    }
                    while (wrap.hasRemaining()) {
                        if (i3 != 0) {
                            try {
                                if (this.b.available() == 0 && wrap.position() > i) {
                                    coderResult = coderResult2;
                                    break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            int arrayOffset = this.a.arrayOffset() + this.a.limit();
                            i3 = this.b.read(this.a.array(), arrayOffset, this.a.capacity() - this.a.limit());
                            if (i3 == -1) {
                                this.d = true;
                                coderResult = coderResult2;
                                break;
                            } else if (i3 == 0) {
                                coderResult = coderResult2;
                                break;
                            } else {
                                this.a.limit(i3 + this.a.limit());
                            }
                        }
                        coderResult = this.c.decode(this.a, wrap, false);
                        if (coderResult.isUnderflow()) {
                            if (this.a.limit() == this.a.capacity()) {
                                this.a.compact();
                                this.a.limit(this.a.position());
                                this.a.position(0);
                            }
                            coderResult2 = coderResult;
                            i3 = 1;
                        }
                    }
                    coderResult = coderResult2;
                    if (coderResult == CoderResult.UNDERFLOW && this.d) {
                        coderResult = this.c.decode(this.a, wrap, true);
                        this.c.flush(wrap);
                        this.c.reset();
                    }
                    if (coderResult.isMalformed() || coderResult.isUnmappable()) {
                        coderResult.throwException();
                    }
                    if (wrap.position() - i == 0) {
                        i3 = -1;
                    } else {
                        i3 = wrap.position() - i;
                    }
                }
            } else {
                throw new IOException("CodeIdentifyInputStreamReader is closed");
            }
        }
        return i3;
    }

    private boolean a() {
        return this.b != null;
    }

    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.c != null) {
                this.c.reset();
            }
            this.c = null;
            if (this.b != null) {
                this.b.close();
                this.b = null;
            }
        }
    }

    private void a(int i, int i2, int i3) {
        if ((i2 | i3) < 0 || i2 > i || i - i2 < i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
