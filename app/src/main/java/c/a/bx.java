package c.a;

import android.content.Context;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* ImprintHandler */
public class bx {
    private static final byte[] a = "pbl0".getBytes();
    private r b = null;
    private Context c;

    public bx(Context context) {
        this.c = context;
    }

    public String a(r rVar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : new TreeMap(rVar.a()).entrySet()) {
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append(((s) entry.getValue()).a());
            stringBuilder.append(((s) entry.getValue()).c());
            stringBuilder.append(((s) entry.getValue()).e());
        }
        stringBuilder.append(rVar.b);
        return ak.a(stringBuilder.toString()).toLowerCase(Locale.US);
    }

    private boolean c(r rVar) {
        if (!rVar.d().equals(a(rVar))) {
            return false;
        }
        for (s sVar : rVar.a().values()) {
            byte[] b = ao.b(sVar.e());
            byte[] a = a(sVar);
            for (int i = 0; i < 4; i++) {
                if (b[i] != a[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public byte[] a(s sVar) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(null);
        allocate.putLong(sVar.c());
        byte[] array = allocate.array();
        byte[] bArr = a;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (array[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public void b(r rVar) {
        if (rVar != null && c(rVar)) {
            synchronized (this) {
                r rVar2 = this.b;
                if (rVar2 != null) {
                    rVar = a(rVar2, rVar);
                }
                this.b = rVar;
            }
        }
    }

    private r a(r rVar, r rVar2) {
        if (rVar2 != null) {
            Map a = rVar.a();
            for (Entry entry : rVar2.a().entrySet()) {
                if (((s) entry.getValue()).b()) {
                    a.put((String) entry.getKey(), (s) entry.getValue());
                } else {
                    a.remove(entry.getKey());
                }
            }
            rVar.a(rVar2.b());
            rVar.a(a(rVar));
        }
        return rVar;
    }

    public synchronized r a() {
        return this.b;
    }

    public void b() {
        InputStream openFileInput;
        Exception e;
        Object rVar;
        Throwable th;
        InputStream inputStream = null;
        if (new File(this.c.getFilesDir(), ".imprint").exists()) {
            byte[] b;
            try {
                openFileInput = this.c.openFileInput(".imprint");
                try {
                    b = ak.b(openFileInput);
                    ak.c(openFileInput);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        ak.c(openFileInput);
                        if (b == null) {
                            try {
                                rVar = new r();
                                new aq().a(rVar, b);
                                this.b = rVar;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = openFileInput;
                        ak.c(inputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e3 = e4;
                openFileInput = inputStream;
                e3.printStackTrace();
                ak.c(openFileInput);
                if (b == null) {
                    rVar = new r();
                    new aq().a(rVar, b);
                    this.b = rVar;
                }
            } catch (Throwable th3) {
                th = th3;
                ak.c(inputStream);
                throw th;
            }
            if (b == null) {
                rVar = new r();
                new aq().a(rVar, b);
                this.b = rVar;
            }
        }
    }

    public void c() {
        if (this.b != null) {
            try {
                ak.a(new File(this.c.getFilesDir(), ".imprint"), new at().a(this.b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
