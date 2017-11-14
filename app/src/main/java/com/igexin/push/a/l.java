package com.igexin.push.a;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import com.igexin.push.core.g;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class l {
    private static String a = "FileConfig";

    public static void a() {
        FileInputStream fileInputStream;
        Throwable th;
        InputStream inputStream = null;
        if (new File(g.X).exists()) {
            FileInputStream fileInputStream2;
            try {
                fileInputStream2 = new FileInputStream(g.X);
                try {
                    a((InputStream) fileInputStream2);
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                            return;
                        } catch (Exception e) {
                            return;
                        }
                    }
                    return;
                } catch (Exception e2) {
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                            return;
                        } catch (Exception e3) {
                            return;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    fileInputStream = fileInputStream2;
                    th = th3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream2 = null;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                    return;
                }
                return;
            } catch (Throwable th4) {
                th = th4;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        }
        try {
            inputStream = g.i.getResources().getAssets().open(g.g + ".properties");
            a(inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e6) {
                }
            }
        } catch (Exception e7) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e8) {
                }
            }
        } catch (Throwable th5) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e9) {
                }
            }
        }
    }

    public static void a(Context context) {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader2;
        Throwable th;
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            inputStreamReader = new InputStreamReader(context.getResources().getAssets().open("green.cfg"));
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (readLine != null) {
                            String[] split = readLine.split("=");
                            if (split != null && split.length == 2) {
                                g.c().put(split[0].trim(), split[1].trim());
                            }
                        }
                    } catch (IOException e) {
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        inputStreamReader2 = inputStreamReader;
                        bufferedReader2 = bufferedReader;
                        th = th3;
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e2) {
                        return;
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (null != null) {
                    assetFileDescriptor.close();
                }
            } catch (IOException e3) {
                bufferedReader = null;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e4) {
                        return;
                    }
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (null != null) {
                    assetFileDescriptor.close();
                }
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader2 = inputStreamReader;
                bufferedReader2 = null;
                if (inputStreamReader2 != null) {
                    try {
                        inputStreamReader2.close();
                    } catch (IOException e5) {
                        throw th;
                    }
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (null != null) {
                    assetFileDescriptor.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            bufferedReader = null;
            inputStreamReader = null;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (null != null) {
                assetFileDescriptor.close();
            }
        } catch (Throwable th5) {
            th = th5;
            bufferedReader2 = null;
            inputStreamReader2 = null;
            if (inputStreamReader2 != null) {
                inputStreamReader2.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (null != null) {
                assetFileDescriptor.close();
            }
            throw th;
        }
    }

    public static void a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return;
                } else if (!readLine.startsWith("#")) {
                    String[] split = readLine.split("=");
                    if (split.length >= 2) {
                        String trim = split[0].trim();
                        readLine = split[1].trim();
                        if (trim.equals("sdk.cm_address")) {
                            k.a = readLine.split(SelectCountryActivity.SPLITTER);
                            g.a = k.a[0];
                        } else if (trim.equals("sdk.phone_address")) {
                            g.b = readLine;
                        } else if (trim.equals("sdk.cm_address_backup")) {
                            k.c = readLine.split(SelectCountryActivity.SPLITTER);
                        } else if (trim.equals("sdk.phone_address_backup")) {
                            k.d = readLine;
                        } else if (!trim.equals("sdk.debug")) {
                            if (trim.equals("sdk.domainbackup.enable")) {
                                k.j = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.readlocalcell.enable")) {
                                k.k = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.uploadapplist.enable")) {
                                k.l = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.feature.sendmessage.enable")) {
                                k.m = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.feature.settag.enable")) {
                                k.n = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.feature.setsilenttime.enable")) {
                                k.o = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.ca.enable")) {
                                k.r = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.snl.enable")) {
                                k.s = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.snl.maxactiveflow")) {
                                k.t = Long.valueOf(readLine).longValue();
                            } else if (trim.equals("sdk.feature.setheartbeatinterval.enable")) {
                                k.p = Boolean.valueOf(readLine).booleanValue();
                            } else if (trim.equals("sdk.feature.setsockettimeout.enable")) {
                                k.q = Boolean.valueOf(readLine).booleanValue();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
