package com.sds.android.ttpod.framework.modules.skin.d.a;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/* PackHandle */
public abstract class a implements Closeable, Iterable<String> {
    public abstract void a(InputStream inputStream, boolean z) throws IOException;

    public abstract void a(String str) throws IOException;

    public abstract boolean a();

    public abstract byte[] b(String str) throws IOException;

    public abstract void close() throws IOException;

    public abstract Iterator<String> iterator();
}
