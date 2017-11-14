package com.sds.android.ttpod.framework.modules.core.d.b;

import com.igexin.download.Downloads;
import com.sds.android.sdk.lib.util.g;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;

/* HttpSession */
class c implements Runnable {
    private static SimpleDateFormat c = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.CHINESE);
    private Socket a;
    private b b;
    private FileOutputStream d = null;
    private boolean e = true;

    static {
        c.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public c(Socket socket, b bVar) {
        this.a = socket;
        this.b = bVar;
        Thread thread = new Thread(this);
        thread.setPriority(10);
        thread.setDaemon(true);
        thread.start();
    }

    public void run() {
        try {
            InputStream inputStream = this.a.getInputStream();
            if (inputStream != null) {
                byte[] bArr = new byte[32768];
                int read = inputStream.read(bArr, 0, 32768);
                if (read > 0) {
                    int i;
                    long j;
                    g.a("HttpSession", "header read:" + read + "bytes");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, read)));
                    Properties properties = new Properties();
                    Properties properties2 = new Properties();
                    Properties properties3 = new Properties();
                    a(bufferedReader, properties, properties2, properties3);
                    String property = properties.getProperty("method");
                    String property2 = properties.getProperty(Downloads.COLUMN_URI);
                    long j2 = Long.MAX_VALUE;
                    String property3 = properties3.getProperty("content-length");
                    if (property3 != null) {
                        try {
                            j2 = (long) Integer.parseInt(property3);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    int a = a(bArr, 0);
                    if (a < read) {
                        i = read - a;
                    } else {
                        i = 0;
                    }
                    if (-1 == a || j2 == Long.MAX_VALUE) {
                        j = 0;
                    } else {
                        j = j2;
                    }
                    if (property.equalsIgnoreCase(Constants.HTTP_POST)) {
                        String b = b(properties3.getProperty("content-type"));
                        long j3 = 0;
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
                        if (i > 0) {
                            byteArrayOutputStream.write(bArr, a, i);
                            j3 = 0 + ((long) i);
                        }
                        Object obj = (((long) i) != j || j <= 0) ? null : 1;
                        while (true) {
                            if (obj == null && byteArrayOutputStream.size() < 32768) {
                                i = inputStream.read(bArr, 0, 32768 - byteArrayOutputStream.size());
                                if (i > 0) {
                                    byteArrayOutputStream.write(bArr, 0, i);
                                    j3 += (long) i;
                                }
                                if (j3 == j || -1 == i) {
                                    obj = 1;
                                }
                            }
                            a(byteArrayOutputStream.toByteArray(), b);
                            byteArrayOutputStream.reset();
                            if (obj != null) {
                                break;
                            }
                        }
                        if (this.d != null) {
                            this.d.close();
                            this.d = null;
                        }
                    }
                    a a2 = this.b.a(property2, property, properties3, properties2);
                    if (a2 == null) {
                        a("500 Internal Server Error", "SERVER INTERNAL ERROR: Serve() returned a null response.");
                    } else {
                        a(a2.a(), a2.b(), a2.d(), a2.c());
                    }
                    inputStream.close();
                    if (this.d != null) {
                        try {
                            this.d.close();
                            this.d = null;
                            return;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    return;
                } else if (this.d != null) {
                    try {
                        this.d.close();
                        this.d = null;
                        return;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return;
                    }
                } else {
                    return;
                }
            } else if (this.d != null) {
                try {
                    this.d.close();
                    this.d = null;
                    return;
                } catch (IOException e222) {
                    e222.printStackTrace();
                    return;
                }
            } else {
                return;
            }
        } catch (ArrayIndexOutOfBoundsException e3) {
            try {
                a();
            } catch (IOException e2222) {
                e2222.printStackTrace();
            } catch (Throwable th) {
                if (this.d != null) {
                    try {
                        this.d.close();
                        this.d = null;
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
            if (this.d != null) {
                try {
                    this.d.close();
                    this.d = null;
                    return;
                } catch (IOException e22222) {
                    e22222.printStackTrace();
                    return;
                }
            }
            return;
        } catch (IOException e222222) {
            try {
                a();
            } catch (IOException e42) {
                e42.printStackTrace();
            }
            a("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + e222222.getMessage());
        } catch (InterruptedException e5) {
            try {
                a();
            } catch (IOException e2222222) {
                e2222222.printStackTrace();
            }
            if (this.d != null) {
                try {
                    this.d.close();
                    this.d = null;
                    return;
                } catch (IOException e22222222) {
                    e22222222.printStackTrace();
                    return;
                }
            }
            return;
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        if (this.d != null) {
            try {
                this.d.close();
                this.d = null;
            } catch (IOException e222222222) {
                e222222222.printStackTrace();
            }
        }
    }

    private void a() throws IOException {
        this.a.close();
        if (this.d != null) {
            File file = new File(this.d.getFD().toString());
            if (file != null) {
                file.delete();
            }
        }
    }

    private void a(byte[] bArr, String str) throws IOException {
        byte[] bytes = ("--" + str + "\r\n").getBytes();
        long a = d.a(bArr, 0, bytes);
        if (-1 == a) {
            a(bArr, 0, bArr.length, bytes);
            return;
        }
        int i = 0;
        while (-1 != a) {
            if (!this.e) {
                a(bArr, i, (int) ((a - ((long) i)) - 2), bytes);
                this.e = true;
            }
            long a2 = d.a(bArr, ((int) a) + bytes.length, bytes);
            if (a2 != -1) {
                this.e = true;
                a(bArr, (int) a, (int) ((a2 - a) - 2), bytes);
                long j = a2;
                i = (int) a2;
                a = j;
            } else {
                a(bArr, (int) a, (int) (((long) bArr.length) - (a - ((long) null))), bytes);
                this.e = false;
                return;
            }
        }
    }

    private void a(byte[] bArr, int i, int i2, byte[] bArr2) throws IOException {
        if (this.e) {
            if (this.d != null) {
                try {
                    this.d.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.d = null;
                this.e = true;
            }
            if (b(bArr, i) != -1) {
                String a = a(c(bArr, i));
                if (a != null) {
                    String c = this.b.c();
                    if (c == null) {
                        c = System.getProperty("java.io.tmpdir");
                    }
                    try {
                        this.d = new FileOutputStream(new File(c, a));
                        if (this.d != null) {
                            int a2 = a(bArr, i);
                            if (-1 != a2) {
                                int length;
                                long a3 = d.a(bArr, a2, bArr2);
                                if (a3 == -1) {
                                    length = bArr.length - a2;
                                } else {
                                    length = ((int) a3) - a2;
                                }
                                this.d.write(bArr, a2, length);
                                this.d.flush();
                            }
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } else if (this.d != null) {
            this.d.write(bArr, i, i2);
            this.d.flush();
        }
    }

    private int a(byte[] bArr, int i) {
        Object obj;
        while (i < bArr.length - 4) {
            if (bArr[i] == (byte) 13) {
                i++;
                if (bArr[i] == (byte) 10) {
                    i++;
                    if (bArr[i] == (byte) 13) {
                        i++;
                        if (bArr[i] == (byte) 10) {
                            obj = 1;
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            i++;
        }
        obj = null;
        return obj != null ? i + 1 : -1;
    }

    private long b(byte[] bArr, int i) {
        return d.a(bArr, i, "Content-Disposition:".getBytes());
    }

    private String c(byte[] bArr, int i) {
        int b = (int) b(bArr, i);
        if (-1 == b) {
            return null;
        }
        int i2 = b;
        while (i2 < bArr.length && bArr[i2] != (byte) 13) {
            i2++;
        }
        return bArr[i2] == (byte) 13 ? new String(bArr, b, i2 - b) : null;
    }

    private String a(String str) {
        if (str.length() == 0 || str.indexOf("Content-Disposition") != 0) {
            return null;
        }
        int indexOf = str.indexOf("filename=");
        if (-1 != indexOf) {
            return str.substring("filename=".length() + indexOf).replace("\"", "");
        }
        return null;
    }

    private String b(String str) {
        if (str != null) {
            int indexOf = str.indexOf("boundary=");
            if (indexOf >= 0) {
                return str.substring(indexOf + "boundary=".length());
            }
        }
        return null;
    }

    private void a(BufferedReader bufferedReader, Properties properties, Properties properties2, Properties properties3) throws InterruptedException {
        try {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                Object c;
                StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                if (!stringTokenizer.hasMoreTokens()) {
                    a("400 Bad Request", "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                }
                properties.put("method", stringTokenizer.nextToken());
                if (!stringTokenizer.hasMoreTokens()) {
                    a("400 Bad Request", "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                }
                readLine = stringTokenizer.nextToken();
                int indexOf = readLine.indexOf(63);
                if (indexOf >= 0) {
                    a(readLine.substring(indexOf + 1), properties2);
                    c = c(readLine.substring(0, indexOf));
                } else {
                    String c2 = c(readLine);
                }
                if (stringTokenizer.hasMoreTokens()) {
                    readLine = bufferedReader.readLine();
                    while (readLine != null && readLine.trim().length() > 0) {
                        int indexOf2 = readLine.indexOf(58);
                        if (indexOf2 >= 0) {
                            properties3.put(readLine.substring(0, indexOf2).trim().toLowerCase(), readLine.substring(indexOf2 + 1).trim());
                        }
                        readLine = bufferedReader.readLine();
                    }
                }
                properties.put(Downloads.COLUMN_URI, c);
            }
        } catch (IOException e) {
            a("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
        }
    }

    private String c(String str) throws InterruptedException {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                switch (charAt) {
                    case '%':
                        stringBuffer.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                        i += 2;
                        break;
                    case '+':
                        stringBuffer.append(' ');
                        break;
                    default:
                        stringBuffer.append(charAt);
                        break;
                }
                i++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            a("400 Bad Request", "BAD REQUEST: Bad percent-encoding.");
            return null;
        }
    }

    private void a(String str, Properties properties) throws InterruptedException {
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                if (indexOf >= 0) {
                    properties.put(c(nextToken.substring(0, indexOf)).trim(), c(nextToken.substring(indexOf + 1)));
                }
            }
        }
    }

    private void a(String str, String str2) throws InterruptedException {
        a(str, "text/plain", null, new ByteArrayInputStream(str2.getBytes()));
        throw new InterruptedException();
    }

    private void a(String str, String str2, Properties properties, InputStream inputStream) {
        if (str == null) {
            throw new Error("sendResponse: Status can't be null.");
        }
        OutputStream outputStream = this.a.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.print("HTTP/1.1 " + str + " \r\n");
        if (str2 != null) {
            printWriter.print("Content-Type: " + str2 + "\r\n");
        }
        if (properties == null || properties.getProperty("Date") == null) {
            printWriter.print("Date: " + c.format(new Date()) + "\r\n");
        }
        if (properties != null) {
            printWriter.print("Connection: close\r\n");
            Enumeration keys = properties.keys();
            while (keys.hasMoreElements()) {
                String str3 = (String) keys.nextElement();
                printWriter.print(str3 + ": " + properties.getProperty(str3) + "\r\n");
            }
        }
        try {
            printWriter.print("\r\n");
            printWriter.flush();
            if (inputStream != null) {
                int available = inputStream.available();
                byte[] bArr = new byte[32768];
                while (available > 0) {
                    int read = inputStream.read(bArr, 0, available > 32768 ? 32768 : available);
                    if (read <= 0) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    available -= read;
                }
            }
            outputStream.flush();
            outputStream.close();
            if (inputStream != null) {
                inputStream.close();
            }
            try {
                this.a.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (IOException e) {
            g.a("HttpSession", "Message:" + e.getMessage());
            this.a.close();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
