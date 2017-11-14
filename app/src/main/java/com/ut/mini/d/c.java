package com.ut.mini.d;

import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

/* UTMCHttpUtils */
public final class c {
    static {
        System.setProperty("http.keepAlive", "true");
    }

    public static byte[] a(int i, String str, Map<String, Object> map, boolean z) {
        Exception e;
        DataOutputStream dataOutputStream;
        Throwable th;
        if (m.a(str)) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (httpURLConnection == null) {
                return null;
            }
            int read;
            if (i == 2 || i == 3) {
                httpURLConnection.setDoOutput(true);
            }
            httpURLConnection.setDoInput(true);
            if (i == 2 || i == 3) {
                try {
                    httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                } catch (ProtocolException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            httpURLConnection.setRequestMethod(Constants.HTTP_GET);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(35000);
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            if (z) {
                httpURLConnection.setRequestProperty(HttpClientProxy.HEADER_ACCEPT_ENCODING, "gzip,deflate");
            }
            httpURLConnection.setInstanceFollowRedirects(true);
            byte[] bArr = null;
            if (i == 2 || i == 3) {
                byte[] bArr2;
                int length;
                if (i == 2) {
                    httpURLConnection.setRequestProperty(HttpClientProxy.HEADER_CONTENT_TYPE, "multipart/form-data; boundary=GJircTeP");
                } else if (i == 3) {
                    httpURLConnection.setRequestProperty(HttpClientProxy.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded");
                }
                if (map == null || map.size() <= 0) {
                    bArr2 = null;
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    Set keySet = map.keySet();
                    String[] strArr = new String[keySet.size()];
                    keySet.toArray(strArr);
                    for (Object obj : e.a().a(strArr, true)) {
                        if (i == 2) {
                            bArr = (byte[]) map.get(obj);
                            if (bArr != null) {
                                try {
                                    byteArrayOutputStream.write(String.format("--GJircTeP\r\nContent-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: application/octet-stream \r\n\r\n", new Object[]{obj, obj}).getBytes());
                                    byteArrayOutputStream.write(bArr);
                                    byteArrayOutputStream.write("\r\n".getBytes());
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        } else if (i == 3) {
                            String str2 = (String) map.get(obj);
                            if (byteArrayOutputStream.size() > 0) {
                                try {
                                    byteArrayOutputStream.write(String.format("&%s=%s", new Object[]{obj, str2}).getBytes());
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            } else {
                                try {
                                    byteArrayOutputStream.write(String.format("%s=%s", new Object[]{obj, str2}).getBytes());
                                } catch (IOException e322) {
                                    e322.printStackTrace();
                                }
                            }
                        }
                    }
                    if (i == 2) {
                        try {
                            byteArrayOutputStream.write("--GJircTeP--\r\n".getBytes());
                        } catch (IOException e3222) {
                            e3222.printStackTrace();
                        }
                    }
                    bArr2 = byteArrayOutputStream.toByteArray();
                }
                if (bArr2 != null) {
                    length = bArr2.length;
                } else {
                    length = 0;
                }
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(length));
                bArr = bArr2;
            }
            DataOutputStream dataOutputStream2 = null;
            try {
                InputStream gZIPInputStream;
                ByteArrayOutputStream byteArrayOutputStream2;
                long currentTimeMillis;
                httpURLConnection.connect();
                if ((i == 2 || i == 3) && bArr != null && bArr.length > 0) {
                    DataOutputStream dataOutputStream3 = new DataOutputStream(httpURLConnection.getOutputStream());
                    try {
                        dataOutputStream3.write(bArr);
                        dataOutputStream3.flush();
                        dataOutputStream3.close();
                        dataOutputStream2 = dataOutputStream3;
                    } catch (Exception e4) {
                        e = e4;
                        dataOutputStream = dataOutputStream3;
                        try {
                            e.printStackTrace();
                            if (dataOutputStream != null) {
                                return null;
                            }
                            try {
                                dataOutputStream.close();
                                return null;
                            } catch (IOException e32222) {
                                e32222.printStackTrace();
                                return null;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            dataOutputStream2 = dataOutputStream;
                            if (dataOutputStream2 != null) {
                                try {
                                    dataOutputStream2.close();
                                } catch (IOException e322222) {
                                    e322222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        dataOutputStream2 = dataOutputStream3;
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        throw th;
                    }
                }
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (IOException e3222222) {
                        e3222222.printStackTrace();
                    }
                }
                if (z) {
                    try {
                        if (HttpClientProxy.CONTENT_ENCODING_GZIP.equals(httpURLConnection.getContentEncoding())) {
                            gZIPInputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                            byteArrayOutputStream2 = new ByteArrayOutputStream();
                            currentTimeMillis = System.currentTimeMillis();
                            bArr = new byte[2048];
                            do {
                                try {
                                    read = gZIPInputStream.read(bArr, 0, 2048);
                                    if (read != -1) {
                                        break;
                                    }
                                    byteArrayOutputStream2.write(bArr, 0, read);
                                } catch (IOException e32222222) {
                                    e32222222.printStackTrace();
                                }
                            } while (System.currentTimeMillis() - currentTimeMillis <= 10000);
                            byteArrayOutputStream2.reset();
                            if (gZIPInputStream != null) {
                                try {
                                    gZIPInputStream.close();
                                } catch (Exception e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (byteArrayOutputStream2.size() <= 0) {
                                return byteArrayOutputStream2.toByteArray();
                            }
                            return null;
                        }
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        return null;
                    }
                }
                gZIPInputStream = new DataInputStream(httpURLConnection.getInputStream());
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                currentTimeMillis = System.currentTimeMillis();
                bArr = new byte[2048];
                do {
                    read = gZIPInputStream.read(bArr, 0, 2048);
                    if (read != -1) {
                        break;
                    }
                    byteArrayOutputStream2.write(bArr, 0, read);
                } while (System.currentTimeMillis() - currentTimeMillis <= 10000);
                byteArrayOutputStream2.reset();
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                if (byteArrayOutputStream2.size() <= 0) {
                    return null;
                }
                return byteArrayOutputStream2.toByteArray();
            } catch (Exception e7) {
                e5 = e7;
                dataOutputStream = null;
                e5.printStackTrace();
                if (dataOutputStream != null) {
                    return null;
                }
                dataOutputStream.close();
                return null;
            } catch (Throwable th4) {
                th = th4;
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                throw th;
            }
        } catch (MalformedURLException e8) {
            e8.printStackTrace();
            return null;
        } catch (IOException e62) {
            e62.printStackTrace();
            return null;
        }
    }
}
