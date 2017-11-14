package com.ut.mini.core.f;

import com.ut.mini.d.m;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* UTMCTransferDataBuilder */
public class a {
    public static Map<String, Object> a(com.ut.mini.core.c.a.a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        OutputStream gZIPOutputStream;
        Throwable th;
        UnsupportedEncodingException e;
        ByteArrayOutputStream byteArrayOutputStream2;
        OutputStream outputStream;
        Exception e2;
        if (aVar != null) {
            String str;
            Map hashMap = new HashMap();
            for (com.ut.mini.core.c.a.a.a aVar2 : aVar.c()) {
                for (String str2 : aVar2.b()) {
                    if (hashMap.containsKey(str2)) {
                        hashMap.put(str2, ((String) hashMap.get(str2)) + "\n" + aVar2.a());
                    } else {
                        hashMap.put(str2, aVar2.a());
                    }
                }
            }
            if (hashMap != null && hashMap.size() > 0) {
                Map<String, Object> hashMap2 = new HashMap();
                if (hashMap != null && hashMap.size() > 0) {
                    for (String str3 : hashMap.keySet()) {
                        str2 = (String) hashMap.get(str3);
                        if (!(m.a(str3) || m.a(str2))) {
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                                    try {
                                        gZIPOutputStream.write(str2.getBytes("UTF-8"));
                                        gZIPOutputStream.flush();
                                        gZIPOutputStream.close();
                                        hashMap2.put(str3, com.ut.mini.a.a.a(byteArrayOutputStream.toByteArray(), com.ut.mini.base.a.b()));
                                        if (com.ut.mini.b.a.a()) {
                                            com.ut.mini.b.a.b(2, "req[stm=" + str3 + "]", String.format("%s", new Object[]{str2}));
                                        }
                                        try {
                                            gZIPOutputStream.close();
                                        } catch (Throwable th2) {
                                            th2.printStackTrace();
                                        }
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Throwable th22) {
                                            th22.printStackTrace();
                                        }
                                    } catch (UnsupportedEncodingException e3) {
                                        e = e3;
                                        byteArrayOutputStream2 = byteArrayOutputStream;
                                        outputStream = gZIPOutputStream;
                                        try {
                                            e.printStackTrace();
                                            try {
                                                outputStream.close();
                                            } catch (Throwable th222) {
                                                th222.printStackTrace();
                                            }
                                            try {
                                                byteArrayOutputStream2.close();
                                            } catch (Throwable th2222) {
                                                th2222.printStackTrace();
                                            }
                                        } catch (Throwable th3) {
                                            th2222 = th3;
                                            gZIPOutputStream = outputStream;
                                            byteArrayOutputStream = byteArrayOutputStream2;
                                        }
                                    } catch (Exception e4) {
                                        e2 = e4;
                                        try {
                                            e2.printStackTrace();
                                            try {
                                                gZIPOutputStream.close();
                                            } catch (Throwable th22222) {
                                                th22222.printStackTrace();
                                            }
                                            try {
                                                byteArrayOutputStream.close();
                                            } catch (Throwable th222222) {
                                                th222222.printStackTrace();
                                            }
                                        } catch (Throwable th4) {
                                            th222222 = th4;
                                        }
                                    }
                                } catch (UnsupportedEncodingException e5) {
                                    e = e5;
                                    byteArrayOutputStream2 = byteArrayOutputStream;
                                    outputStream = null;
                                    e.printStackTrace();
                                    outputStream.close();
                                    byteArrayOutputStream2.close();
                                } catch (Exception e6) {
                                    e2 = e6;
                                    gZIPOutputStream = null;
                                    e2.printStackTrace();
                                    gZIPOutputStream.close();
                                    byteArrayOutputStream.close();
                                } catch (Throwable th5) {
                                    th222222 = th5;
                                    gZIPOutputStream = null;
                                }
                            } catch (UnsupportedEncodingException e7) {
                                e = e7;
                                byteArrayOutputStream2 = null;
                                outputStream = null;
                                e.printStackTrace();
                                outputStream.close();
                                byteArrayOutputStream2.close();
                            } catch (Exception e8) {
                                e2 = e8;
                                byteArrayOutputStream = null;
                                gZIPOutputStream = null;
                                e2.printStackTrace();
                                gZIPOutputStream.close();
                                byteArrayOutputStream.close();
                            } catch (Throwable th6) {
                                th222222 = th6;
                                byteArrayOutputStream = null;
                                gZIPOutputStream = null;
                            }
                        }
                    }
                }
                return hashMap2;
            }
        }
        return null;
        throw th222222;
        try {
            gZIPOutputStream.close();
        } catch (Throwable th7) {
            th7.printStackTrace();
        }
        try {
            byteArrayOutputStream.close();
        } catch (Throwable th72) {
            th72.printStackTrace();
        }
        throw th222222;
        byteArrayOutputStream.close();
        throw th222222;
    }
}
