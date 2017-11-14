package com.sds.android.ttpod.component.landscape.c;

import com.sds.android.ttpod.component.landscape.d.c;

/* Texture2D */
public class b extends a {
    public void b(android.graphics.Bitmap r10, boolean r11) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
        /*
        r9 = this;
        if (r10 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        r1 = r10.getWidth();
        r2 = r10.getHeight();
        r3 = r9.a(r1);
        r4 = r9.a(r2);
        if (r1 != r3) goto L_0x0017;
    L_0x0015:
        if (r2 == r4) goto L_0x003b;
    L_0x0017:
        r0 = android.graphics.Bitmap.Config.ARGB_8888;	 Catch:{ OutOfMemoryError -> 0x005f, Exception -> 0x006e, all -> 0x0075 }
        r0 = android.graphics.Bitmap.createBitmap(r3, r4, r0);	 Catch:{ OutOfMemoryError -> 0x005f, Exception -> 0x006e, all -> 0x0075 }
    L_0x001d:
        r5 = 0;
        r0.eraseColor(r5);	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r5 = 0;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r0.setDensity(r5);	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r5 = new android.graphics.Canvas;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r5.<init>(r0);	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r6 = 0;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r5.setDensity(r6);	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r6 = 0;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r7 = 0;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r8 = 0;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r5.drawBitmap(r10, r6, r7, r8);	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        if (r11 == 0) goto L_0x0039;
    L_0x0036:
        r10.recycle();
    L_0x0039:
        r11 = 1;
        r10 = r0;
    L_0x003b:
        r9.a(r10, r11);
        r0 = r9.c;
        r5 = (float) r1;
        r0.a(r5);
        r0 = r9.c;
        r5 = (float) r2;
        r0.b(r5);
        r0 = r9.d;
        r0.a(r3);
        r0 = r9.d;
        r0.b(r4);
        r0 = (float) r1;
        r1 = (float) r3;
        r0 = r0 / r1;
        r9.e = r0;
        r0 = (float) r2;
        r1 = (float) r4;
        r0 = r0 / r1;
        r9.f = r0;
        goto L_0x0002;
    L_0x005f:
        r0 = move-exception;
        r0 = android.graphics.Bitmap.Config.RGB_565;	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        r0 = android.graphics.Bitmap.createBitmap(r3, r4, r0);	 Catch:{ OutOfMemoryError -> 0x0067, Exception -> 0x006e, all -> 0x0075 }
        goto L_0x001d;
    L_0x0067:
        r0 = move-exception;
        if (r11 == 0) goto L_0x0002;
    L_0x006a:
        r10.recycle();
        goto L_0x0002;
    L_0x006e:
        r0 = move-exception;
        if (r11 == 0) goto L_0x0002;
    L_0x0071:
        r10.recycle();
        goto L_0x0002;
    L_0x0075:
        r0 = move-exception;
        if (r11 == 0) goto L_0x007b;
    L_0x0078:
        r10.recycle();
    L_0x007b:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.landscape.c.b.b(android.graphics.Bitmap, boolean):void");
    }

    public b(String str) {
        super(str);
        this.b = 0;
        this.c = new c();
        this.d = new com.sds.android.ttpod.component.landscape.d.b();
        this.e = 0.0f;
        this.f = 0.0f;
    }
}
