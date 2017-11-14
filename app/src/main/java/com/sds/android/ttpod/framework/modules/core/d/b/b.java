package com.sds.android.ttpod.framework.modules.core.d.b;

import com.sds.android.sdk.lib.util.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

/* HttpServer */
class b {
    private static Hashtable<String, String> h = new Hashtable();
    private String a;
    private int b;
    private String c;
    private final ServerSocket d;
    private Thread e;
    private File f;
    private final a g;

    /* HttpServer */
    interface a {
        void a();
    }

    static {
        StringTokenizer stringTokenizer = new StringTokenizer("css\t\ttext/css htm\t\ttext/html html\t\ttext/html gif\t\timage/gif jpg\t\timage/jpeg jpeg\t\timage/jpeg png\t\timage/png ico\t\timage/x-icon swf\t\tapplication/x-shockwave-flash js\t\t\tapplication/javascript ");
        while (stringTokenizer.hasMoreTokens()) {
            h.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
        }
    }

    public b(String str, int i, String str2, String str3, a aVar) throws IOException {
        if (aVar == null) {
            throw new IllegalArgumentException("callback must not be null!!");
        }
        this.g = aVar;
        this.a = str;
        this.b = i;
        this.f = new File(str2);
        this.c = str3;
        this.d = new ServerSocket(this.b, 0, InetAddress.getByName(this.a));
        this.e = new Thread(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                while (true) {
                    try {
                        c cVar = new c(this.a.d.accept(), this.a);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        });
    }

    public void a() {
        if (this.e.isAlive()) {
            throw new IllegalStateException("already started!");
        }
        this.e.setDaemon(true);
        this.e.start();
    }

    public void b() {
        try {
            this.d.close();
            this.e.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    String c() {
        return this.c;
    }

    public a a(String str, String str2, Properties properties, Properties properties2) {
        g.a("HttpServer", str2 + " '" + str + "' ");
        Enumeration propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str3 = (String) propertyNames.nextElement();
            g.a("HttpServer", "Header: '" + str3 + "' = '" + properties.getProperty(str3) + "'");
        }
        propertyNames = properties2.propertyNames();
        while (propertyNames.hasMoreElements()) {
            str3 = (String) propertyNames.nextElement();
            g.a("HttpServer", "Parameters: '" + str3 + "' = '" + properties2.getProperty(str3) + "'");
        }
        a a = a(str, properties, this.f);
        if (a.a() == "200 OK") {
            this.g.a();
        }
        return a;
    }

    a a(String str, Properties properties, File file) {
        a aVar;
        a aVar2;
        a aVar3;
        String replace = str.trim().replace(File.separatorChar, '/');
        if (replace.indexOf(63) >= 0) {
            replace = replace.substring(0, replace.indexOf(63));
        }
        if (replace.startsWith("..") || replace.endsWith("..") || replace.indexOf("../") >= 0) {
            aVar = new a("403 Forbidden", "text/plain", "Forbidden: Can't access for security reasons.");
        } else {
            aVar = null;
        }
        File file2 = new File(file, replace);
        if (aVar != null || file2.exists()) {
            aVar2 = aVar;
        } else {
            aVar2 = new a("404 Not Found", "text/plain", "Error 404, file not found.");
        }
        if (aVar2 != null || !file2.isDirectory()) {
            aVar3 = aVar2;
        } else if (new File(file2, "index.html").exists()) {
            file2 = new File(file + replace + "/index.html");
            aVar3 = aVar2;
        } else if (new File(file2, "index.htm").exists()) {
            file2 = new File(file + replace + "/index.htm");
            aVar3 = aVar2;
        } else {
            aVar3 = new a("403 Forbidden", "text/plain", "Forbidden: Can't access for security reasons.");
        }
        if (aVar3 != null) {
            return aVar3;
        }
        try {
            String str2;
            int lastIndexOf = file2.getCanonicalPath().lastIndexOf(46);
            if (lastIndexOf >= 0) {
                replace = (String) h.get(file2.getCanonicalPath().substring(lastIndexOf + 1).toLowerCase());
            } else {
                replace = null;
            }
            if (replace == null) {
                str2 = "application/octet-stream";
            } else {
                str2 = replace;
            }
            aVar3 = new a("200 OK", str2, new FileInputStream(file2));
            aVar3.a("Content-Length", "" + file2.length());
            return aVar3;
        } catch (IOException e) {
            return new a("403 Forbidden", "text/plain", "Forbidden: Access file failed.");
        }
    }
}
