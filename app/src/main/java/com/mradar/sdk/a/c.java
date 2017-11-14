package com.mradar.sdk.a;

import com.mradar.sdk.record.e;
import com.mradar.sdk.record.f;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/* HttpWrapper */
public class c {
    public static String a(Map<String, Object> map, int i, int i2) throws e {
        BufferedOutputStream bufferedOutputStream;
        e eVar;
        IOException e;
        Exception e2;
        Throwable th;
        SocketTimeoutException e3;
        BufferedOutputStream bufferedOutputStream2;
        BufferedReader bufferedReader = null;
        String str = "";
        try {
            URL url = new URL(f.a);
            e.a("HttpWrapper", f.a);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(i2);
            httpURLConnection.setRequestMethod(Constants.HTTP_POST);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Accept-Charset", TTPodFMHttpClient.AppEncode);
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Content-type", "multipart/form-data;boundary=*****2014.5.21.doroso.search.copyright*****");
            try {
                httpURLConnection.connect();
                bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (map != null) {
                        for (String str2 : map.keySet()) {
                            Object obj = map.get(str2);
                            stringBuilder.setLength(0);
                            if ((obj instanceof String) || (obj instanceof Integer)) {
                                stringBuilder.append("--*****2014.5.21.doroso.search.copyright*****\r\n");
                                stringBuilder.append("Content-Disposition:form-data;name=\"");
                                stringBuilder.append(str2);
                                stringBuilder.append("\"\r\n\r\n");
                                stringBuilder.append(obj);
                                stringBuilder.append("\r\n");
                                bufferedOutputStream.write(stringBuilder.toString().getBytes());
                            } else if (obj instanceof byte[]) {
                                stringBuilder.append("--*****2014.5.21.doroso.search.copyright*****\r\n");
                                stringBuilder.append("Content-Disposition:form-data;");
                                stringBuilder.append("name=\"" + str2 + "\";");
                                stringBuilder.append("filename=\"sample.ef\"\r\n");
                                stringBuilder.append("Content-Type:application/octet-stream");
                                stringBuilder.append("\r\n\r\n");
                                bufferedOutputStream.write(stringBuilder.toString().getBytes());
                                bufferedOutputStream.write((byte[]) obj);
                                bufferedOutputStream.write("\r\n".getBytes());
                            }
                        }
                        bufferedOutputStream.write("--*****2014.5.21.doroso.search.copyright*****--\r\n\r\n".getBytes());
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        } catch (IOException e4) {
                            eVar = new e(e4.getMessage());
                            eVar.a = "[{\"error\":\"http client send data error.\", \"errorcode\":\"21002\"}]";
                            throw eVar;
                        }
                    }
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                        try {
                            String str3 = "";
                            str3 = str;
                            while (true) {
                                String readLine = bufferedReader2.readLine();
                                if (readLine == null) {
                                    break;
                                } else if (readLine.length() > 0) {
                                    str3 = new StringBuilder(String.valueOf(str3)).append(readLine).toString();
                                }
                            }
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e42) {
                                    eVar = new e(e42.getMessage());
                                    eVar.a = "[{\"error\":\"http client read data error.\", \"errorcode\":\"21003\"}]";
                                    throw eVar;
                                }
                            }
                            return str3;
                        } catch (Exception e5) {
                            e2 = e5;
                            bufferedReader = bufferedReader2;
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                        }
                    } catch (Exception e6) {
                        e2 = e6;
                        try {
                            e2.printStackTrace();
                            eVar = new e(e2.getMessage());
                            eVar.a = "[{\"error\":\"http client read data error.\", \"errorcode\":\"21003\"}]";
                            throw eVar;
                        } catch (Throwable th3) {
                            th = th3;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e422) {
                                    eVar = new e(e422.getMessage());
                                    eVar.a = "[{\"error\":\"http client read data error.\", \"errorcode\":\"21003\"}]";
                                    throw eVar;
                                }
                            }
                            throw th;
                        }
                    }
                } catch (SocketTimeoutException e7) {
                    e3 = e7;
                    bufferedOutputStream2 = bufferedOutputStream;
                } catch (IOException e8) {
                    e422 = e8;
                }
            } catch (SocketTimeoutException e9) {
                e3 = e9;
                Object obj2 = null;
                try {
                    e eVar2 = new e(e3.getMessage());
                    eVar2.a = "[{\"error\":\"http client connect server timeout.\", \"errorcode\":\"21001\"}]";
                    throw eVar2;
                } catch (Throwable th4) {
                    th = th4;
                    bufferedOutputStream = bufferedOutputStream2;
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                        } catch (IOException e4222) {
                            eVar = new e(e4222.getMessage());
                            eVar.a = "[{\"error\":\"http client send data error.\", \"errorcode\":\"21002\"}]";
                            throw eVar;
                        }
                    }
                    throw th;
                }
            } catch (IOException e10) {
                e4222 = e10;
                bufferedOutputStream = null;
                try {
                    eVar = new e(e4222.getMessage());
                    eVar.a = "[{\"error\":\"http client send data error.\", \"errorcode\":\"21002\"}]";
                    throw eVar;
                } catch (Throwable th5) {
                    th = th5;
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
                bufferedOutputStream = null;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e22) {
            eVar = new e(e22.getMessage());
            eVar.a = "[{\"error\":\"client protocol error, URL is not http.\", \"errorcode\":\"21000\"}]";
            throw eVar;
        }
    }

    public static String a(String str, int i) throws Exception {
        BufferedReader bufferedReader;
        String str2 = "";
        BufferedReader bufferedReader2 = null;
        Exception e;
        Throwable th;
        try {
            e.a("url", "url:" + str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(i);
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            httpURLConnection.connect();
            Map headerFields = httpURLConnection.getHeaderFields();
            for (String str3 : headerFields.keySet()) {
                System.out.println(new StringBuilder(String.valueOf(str3)).append("--->").append(headerFields.get(str3)).toString());
            }
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String str4 = str2;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str4 = new StringBuilder(String.valueOf(str4)).append(readLine).toString();
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            return str4;
            try {
                throw e;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
            }
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e32) {
                    e32.printStackTrace();
                }
            }
            throw th;
            throw th;
        } catch (Exception e4) {
            e = e4;
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
