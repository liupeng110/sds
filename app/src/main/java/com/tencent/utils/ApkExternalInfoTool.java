package com.tencent.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

/* ProGuard */
public final class ApkExternalInfoTool {
    public static final String CHANNELID = "channelNo";
    private static final ZipLong a = new ZipLong(101010256);
    private static final ZipShort b = new ZipShort(38651);

    /* ProGuard */
    private static class ApkExternalInfo {
        Properties a;
        byte[] b;

        private ApkExternalInfo() {
            this.a = new Properties();
        }

        void a(byte[] bArr) throws IOException {
            if (bArr != null) {
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                int length = ApkExternalInfoTool.b.getBytes().length;
                byte[] bArr2 = new byte[length];
                wrap.get(bArr2);
                if (!ApkExternalInfoTool.b.equals(new ZipShort(bArr2))) {
                    throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
                } else if (bArr.length - length > 2) {
                    bArr2 = new byte[2];
                    wrap.get(bArr2);
                    int value = new ZipShort(bArr2).getValue();
                    if ((bArr.length - length) - 2 >= value) {
                        byte[] bArr3 = new byte[value];
                        wrap.get(bArr3);
                        this.a.load(new ByteArrayInputStream(bArr3));
                        length = ((bArr.length - length) - value) - 2;
                        if (length > 0) {
                            this.b = new byte[length];
                            wrap.get(this.b);
                        }
                    }
                }
            }
        }

        byte[] a() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(ApkExternalInfoTool.b.getBytes());
            String str = "";
            for (Object next : this.a.keySet()) {
                str = str + next + "=" + this.a.getProperty((String) next) + "\r\n";
            }
            byte[] bytes = str.getBytes();
            byteArrayOutputStream.write(new ZipShort(bytes.length).getBytes());
            byteArrayOutputStream.write(bytes);
            if (this.b != null) {
                byteArrayOutputStream.write(this.b);
            }
            return byteArrayOutputStream.toByteArray();
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.a + ", otherData=" + Arrays.toString(this.b) + "]";
        }
    }

    public static String read(File file, String str) throws IOException {
        Throwable th;
        String str2 = null;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                byte[] a = a(randomAccessFile);
                if (a != null) {
                    ApkExternalInfo apkExternalInfo = new ApkExternalInfo();
                    apkExternalInfo.a(a);
                    str2 = apkExternalInfo.a.getProperty(str);
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } else if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            randomAccessFile = null;
            th = th4;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public static String readChannelId(File file) throws IOException {
        return read(file, CHANNELID);
    }

    private static byte[] a(RandomAccessFile randomAccessFile) throws IOException {
        int i = 1;
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] bytes = a.getBytes();
        byte read = randomAccessFile.read();
        while (read != (byte) -1) {
            if (read == bytes[0] && randomAccessFile.read() == bytes[1] && randomAccessFile.read() == bytes[2] && randomAccessFile.read() == bytes[3]) {
                break;
            }
            length--;
            randomAccessFile.seek(length);
            read = randomAccessFile.read();
        }
        i = 0;
        if (i == 0) {
            throw new ZipException("archive is not a ZIP archive");
        }
        randomAccessFile.seek((16 + length) + 4);
        byte[] bArr = new byte[2];
        randomAccessFile.readFully(bArr);
        i = new ZipShort(bArr).getValue();
        if (i == 0) {
            return null;
        }
        bArr = new byte[i];
        randomAccessFile.read(bArr);
        return bArr;
    }

    public static void updateExternalInfo(File file, String str, String str2) throws IOException {
        RandomAccessFile randomAccessFile;
        Throwable th;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                byte[] a = a(randomAccessFile);
                ApkExternalInfo apkExternalInfo = new ApkExternalInfo();
                apkExternalInfo.a(a);
                apkExternalInfo.a.setProperty(str, str2);
                randomAccessFile.seek((randomAccessFile.length() - ((long) (a == null ? 0 : a.length))) - 2);
                a = apkExternalInfo.a();
                randomAccessFile.write(new ZipShort(a.length).getBytes());
                randomAccessFile.write(a);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th2) {
                th = th2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }
}
