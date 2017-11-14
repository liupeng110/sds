package com.a.a.a;

import android.content.Context;
import android.util.Log;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* XZip */
public class n {
    /* JADX err: Inconsistent code. */
    static void a(android.content.Context r7, java.lang.String r8, int r9) {
        /*
        r2 = 0;
        r0 = "UTF-8";
        r0 = r8.getBytes(r0);	 Catch:{ UnsupportedEncodingException -> 0x0195, FileNotFoundException -> 0x0107, IOException -> 0x0128, all -> 0x0149 }
        r0 = a(r0);	 Catch:{ UnsupportedEncodingException -> 0x0195, FileNotFoundException -> 0x0107, IOException -> 0x0128, all -> 0x0149 }
        r3 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x0195, FileNotFoundException -> 0x0107, IOException -> 0x0128, all -> 0x0149 }
        r1 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x0195, FileNotFoundException -> 0x0107, IOException -> 0x0128, all -> 0x0149 }
        r4 = "UTF-8";
        r1.<init>(r0, r4);	 Catch:{ UnsupportedEncodingException -> 0x0195, FileNotFoundException -> 0x0107, IOException -> 0x0128, all -> 0x0149 }
        r3.<init>(r1);	 Catch:{ UnsupportedEncodingException -> 0x0195, FileNotFoundException -> 0x0107, IOException -> 0x0128, all -> 0x0149 }
        if (r9 != 0) goto L_0x006e;
    L_0x0019:
        r1 = new java.io.BufferedOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r0 = new java.util.zip.GZIPOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r4 = new java.io.FileOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r5 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = "/data/data/";
        r5.<init>(r6);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = r7.getPackageName();	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r5 = r5.append(r6);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = "/";
        r5 = r5.append(r6);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = "cmsc.si";
        r5 = r5.append(r6);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r5 = r5.toString();	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r0.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r1.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x0199, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r2 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r4 = "chmod 770 /data/data/";
        r2.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r4 = r7.getPackageName();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r2 = r2.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r4 = "/";
        r2 = r2.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r4 = "cmsc.si";
        r2 = r2.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r2 = r2.toString();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r0.exec(r2);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0189, IOException -> 0x017e, all -> 0x016f }
        r2 = r1;
    L_0x006e:
        r0 = 1;
        if (r9 != r0) goto L_0x01a3;
    L_0x0071:
        r1 = new java.io.BufferedOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r0 = new java.util.zip.GZIPOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r4 = new java.io.FileOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r5 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = "/data/data/";
        r5.<init>(r6);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = r7.getPackageName();	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r5 = r5.append(r6);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = "/";
        r5 = r5.append(r6);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r6 = "cmsc.si";
        r5 = r5.append(r6);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r5 = r5.toString();	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r0.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r1.<init>(r0);	 Catch:{ UnsupportedEncodingException -> 0x019e, FileNotFoundException -> 0x0187, IOException -> 0x017c }
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r2 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r4 = "chmod 770 /data/data/";
        r2.<init>(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r4 = r7.getPackageName();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r2 = r2.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r4 = "/";
        r2 = r2.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r4 = "cmsc.si";
        r2 = r2.append(r4);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r2 = r2.toString();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
        r0.exec(r2);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x018d, IOException -> 0x0181, all -> 0x0172 }
    L_0x00c5:
        r0 = r3.read();	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0191, IOException -> 0x0184, all -> 0x0175 }
        r2 = -1;
        if (r0 != r2) goto L_0x00d7;
    L_0x00cc:
        if (r3 == 0) goto L_0x00d1;
    L_0x00ce:
        r3.close();	 Catch:{ IOException -> 0x0161 }
    L_0x00d1:
        if (r3 == 0) goto L_0x00d6;
    L_0x00d3:
        r1.close();	 Catch:{ IOException -> 0x0161 }
    L_0x00d6:
        return;
    L_0x00d7:
        r0 = (char) r0;
        r0 = java.lang.String.valueOf(r0);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0191, IOException -> 0x0184, all -> 0x0175 }
        r2 = "UTF-8";
        r0 = r0.getBytes(r2);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0191, IOException -> 0x0184, all -> 0x0175 }
        r1.write(r0);	 Catch:{ UnsupportedEncodingException -> 0x00e6, FileNotFoundException -> 0x0191, IOException -> 0x0184, all -> 0x0175 }
        goto L_0x00c5;
    L_0x00e6:
        r0 = move-exception;
        r2 = r3;
    L_0x00e8:
        r3 = "SDK_LW_CMM";
        r4 = r0.getMessage();	 Catch:{ all -> 0x0178 }
        android.util.Log.e(r3, r4, r0);	 Catch:{ all -> 0x0178 }
        if (r2 == 0) goto L_0x00f6;
    L_0x00f3:
        r2.close();	 Catch:{ IOException -> 0x00fc }
    L_0x00f6:
        if (r2 == 0) goto L_0x00d6;
    L_0x00f8:
        r1.close();	 Catch:{ IOException -> 0x00fc }
        goto L_0x00d6;
    L_0x00fc:
        r0 = move-exception;
        r1 = "SDK_LW_CMM";
        r2 = r0.getMessage();
        android.util.Log.e(r1, r2, r0);
        goto L_0x00d6;
    L_0x0107:
        r0 = move-exception;
        r3 = r2;
    L_0x0109:
        r1 = "SDK_LW_CMM";
        r4 = r0.getMessage();	 Catch:{ all -> 0x016d }
        android.util.Log.e(r1, r4, r0);	 Catch:{ all -> 0x016d }
        if (r3 == 0) goto L_0x0117;
    L_0x0114:
        r3.close();	 Catch:{ IOException -> 0x011d }
    L_0x0117:
        if (r3 == 0) goto L_0x00d6;
    L_0x0119:
        r2.close();	 Catch:{ IOException -> 0x011d }
        goto L_0x00d6;
    L_0x011d:
        r0 = move-exception;
        r1 = "SDK_LW_CMM";
        r2 = r0.getMessage();
        android.util.Log.e(r1, r2, r0);
        goto L_0x00d6;
    L_0x0128:
        r0 = move-exception;
        r3 = r2;
    L_0x012a:
        r1 = "SDK_LW_CMM";
        r4 = r0.getMessage();	 Catch:{ all -> 0x016d }
        android.util.Log.e(r1, r4, r0);	 Catch:{ all -> 0x016d }
        if (r3 == 0) goto L_0x0138;
    L_0x0135:
        r3.close();	 Catch:{ IOException -> 0x013e }
    L_0x0138:
        if (r3 == 0) goto L_0x00d6;
    L_0x013a:
        r2.close();	 Catch:{ IOException -> 0x013e }
        goto L_0x00d6;
    L_0x013e:
        r0 = move-exception;
        r1 = "SDK_LW_CMM";
        r2 = r0.getMessage();
        android.util.Log.e(r1, r2, r0);
        goto L_0x00d6;
    L_0x0149:
        r0 = move-exception;
        r3 = r2;
    L_0x014b:
        if (r3 == 0) goto L_0x0150;
    L_0x014d:
        r3.close();	 Catch:{ IOException -> 0x0156 }
    L_0x0150:
        if (r3 == 0) goto L_0x0155;
    L_0x0152:
        r2.close();	 Catch:{ IOException -> 0x0156 }
    L_0x0155:
        throw r0;
    L_0x0156:
        r1 = move-exception;
        r2 = "SDK_LW_CMM";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x0155;
    L_0x0161:
        r0 = move-exception;
        r1 = "SDK_LW_CMM";
        r2 = r0.getMessage();
        android.util.Log.e(r1, r2, r0);
        goto L_0x00d6;
    L_0x016d:
        r0 = move-exception;
        goto L_0x014b;
    L_0x016f:
        r0 = move-exception;
        r2 = r1;
        goto L_0x014b;
    L_0x0172:
        r0 = move-exception;
        r2 = r1;
        goto L_0x014b;
    L_0x0175:
        r0 = move-exception;
        r2 = r1;
        goto L_0x014b;
    L_0x0178:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x014b;
    L_0x017c:
        r0 = move-exception;
        goto L_0x012a;
    L_0x017e:
        r0 = move-exception;
        r2 = r1;
        goto L_0x012a;
    L_0x0181:
        r0 = move-exception;
        r2 = r1;
        goto L_0x012a;
    L_0x0184:
        r0 = move-exception;
        r2 = r1;
        goto L_0x012a;
    L_0x0187:
        r0 = move-exception;
        goto L_0x0109;
    L_0x0189:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0109;
    L_0x018d:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0109;
    L_0x0191:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0109;
    L_0x0195:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00e8;
    L_0x0199:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00e8;
    L_0x019e:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00e8;
    L_0x01a3:
        r1 = r2;
        goto L_0x00c5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.a.n.a(android.content.Context, java.lang.String, int):void");
    }

    /* JADX err: Inconsistent code. */
    static java.lang.String a(android.content.Context r10, int r11) {
        /*
        r1 = 0;
        r8 = 1;
        r0 = "";
        r2 = "";
        if (r11 != 0) goto L_0x0176;
    L_0x0008:
        r0 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r4 = "/data/data/";
        r3.<init>(r4);
        r4 = r10.getPackageName();
        r3 = r3.append(r4);
        r4 = "/";
        r3 = r3.append(r4);
        r4 = "cmsc.si";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r0.<init>(r3);
    L_0x002c:
        if (r11 != r8) goto L_0x0052;
    L_0x002e:
        r0 = new java.io.File;
        r3 = new java.lang.StringBuilder;
        r4 = "/data/data/";
        r3.<init>(r4);
        r4 = r10.getPackageName();
        r3 = r3.append(r4);
        r4 = "/";
        r3 = r3.append(r4);
        r4 = "cmsc.si";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r0.<init>(r3);
    L_0x0052:
        r0 = r0.exists();
        if (r0 == 0) goto L_0x0152;
    L_0x0058:
        if (r11 != 0) goto L_0x0090;
    L_0x005a:
        r0 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r3 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r4 = new java.util.zip.GZIPInputStream;	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r5 = new java.io.FileInputStream;	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r6 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r7 = "/data/data/";
        r6.<init>(r7);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r7 = r10.getPackageName();	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r6 = r6.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r7 = "/";
        r6 = r6.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r7 = "cmsc.si";
        r6 = r6.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r6 = r6.toString();	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r5.<init>(r6);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r5 = "UTF-8";
        r3.<init>(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r0.<init>(r3);	 Catch:{ UnsupportedEncodingException -> 0x00d8, FileNotFoundException -> 0x00f7, IOException -> 0x0116, all -> 0x0135 }
        r1 = r0;
    L_0x0090:
        if (r11 != r8) goto L_0x0172;
    L_0x0092:
        r0 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r3 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r4 = new java.util.zip.GZIPInputStream;	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r5 = new java.io.FileInputStream;	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r6 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r7 = "/data/data/";
        r6.<init>(r7);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r7 = r10.getPackageName();	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r6 = r6.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r7 = "/";
        r6 = r6.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r7 = "cmsc.si";
        r6 = r6.append(r7);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r6 = r6.toString();	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r5.<init>(r6);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r5 = "UTF-8";
        r3.<init>(r4, r5);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r0.<init>(r3);	 Catch:{ UnsupportedEncodingException -> 0x0168, FileNotFoundException -> 0x0160, IOException -> 0x0158, all -> 0x0135 }
        r9 = r0;
        r0 = r2;
        r2 = r9;
    L_0x00ca:
        r1 = r2.readLine();	 Catch:{ UnsupportedEncodingException -> 0x016f, FileNotFoundException -> 0x0166, IOException -> 0x015e }
        if (r1 != 0) goto L_0x00d6;
    L_0x00d0:
        if (r2 == 0) goto L_0x00d5;
    L_0x00d2:
        r2.close();	 Catch:{ IOException -> 0x0147 }
    L_0x00d5:
        return r0;
    L_0x00d6:
        r0 = r1;
        goto L_0x00ca;
    L_0x00d8:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
    L_0x00dd:
        r3 = "SDK_LW_CMM";
        r4 = r1.getMessage();	 Catch:{ all -> 0x0155 }
        android.util.Log.e(r3, r4, r1);	 Catch:{ all -> 0x0155 }
        if (r2 == 0) goto L_0x00d5;
    L_0x00e8:
        r2.close();	 Catch:{ IOException -> 0x00ec }
        goto L_0x00d5;
    L_0x00ec:
        r1 = move-exception;
        r2 = "SDK_LW_CMM";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x00d5;
    L_0x00f7:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
    L_0x00fc:
        r3 = "SDK_LW_CMM";
        r4 = r1.getMessage();	 Catch:{ all -> 0x0155 }
        android.util.Log.e(r3, r4, r1);	 Catch:{ all -> 0x0155 }
        if (r2 == 0) goto L_0x00d5;
    L_0x0107:
        r2.close();	 Catch:{ IOException -> 0x010b }
        goto L_0x00d5;
    L_0x010b:
        r1 = move-exception;
        r2 = "SDK_LW_CMM";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x00d5;
    L_0x0116:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
    L_0x011b:
        r3 = "SDK_LW_CMM";
        r4 = r1.getMessage();	 Catch:{ all -> 0x0155 }
        android.util.Log.e(r3, r4, r1);	 Catch:{ all -> 0x0155 }
        if (r2 == 0) goto L_0x00d5;
    L_0x0126:
        r2.close();	 Catch:{ IOException -> 0x012a }
        goto L_0x00d5;
    L_0x012a:
        r1 = move-exception;
        r2 = "SDK_LW_CMM";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x00d5;
    L_0x0135:
        r0 = move-exception;
    L_0x0136:
        if (r1 == 0) goto L_0x013b;
    L_0x0138:
        r1.close();	 Catch:{ IOException -> 0x013c }
    L_0x013b:
        throw r0;
    L_0x013c:
        r1 = move-exception;
        r2 = "SDK_LW_CMM";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x013b;
    L_0x0147:
        r1 = move-exception;
        r2 = "SDK_LW_CMM";
        r3 = r1.getMessage();
        android.util.Log.e(r2, r3, r1);
        goto L_0x00d5;
    L_0x0152:
        r0 = "-1";
        goto L_0x00d5;
    L_0x0155:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0136;
    L_0x0158:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x011b;
    L_0x015e:
        r1 = move-exception;
        goto L_0x011b;
    L_0x0160:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00fc;
    L_0x0166:
        r1 = move-exception;
        goto L_0x00fc;
    L_0x0168:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00dd;
    L_0x016f:
        r1 = move-exception;
        goto L_0x00dd;
    L_0x0172:
        r0 = r2;
        r2 = r1;
        goto L_0x00ca;
    L_0x0176:
        r0 = r1;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.a.n.a(android.content.Context, int):java.lang.String");
    }

    static InputStream a(byte[] bArr) {
        return new ByteArrayInputStream(bArr);
    }

    static void a(Context context, String str) {
        BufferedReader bufferedReader;
        BufferedOutputStream bufferedOutputStream;
        Throwable e;
        BufferedReader bufferedReader2;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(a(str.getBytes("UTF-8")), "UTF-8"));
            try {
                bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("/data/data/" + context.getPackageName() + "/" + "cmsc.si")));
                try {
                    Runtime.getRuntime().exec("chmod 770 /data/data/" + context.getPackageName() + "/" + "cmsc.si");
                    while (true) {
                        int read = bufferedReader.read();
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(String.valueOf((char) read).getBytes("UTF-8"));
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e2) {
                            Log.e("SDK_LW_CMM", e2.getMessage(), e2);
                            return;
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedOutputStream.close();
                    }
                } catch (UnsupportedEncodingException e3) {
                    e2 = e3;
                    bufferedReader2 = bufferedReader;
                } catch (FileNotFoundException e4) {
                    e2 = e4;
                    bufferedOutputStream2 = bufferedOutputStream;
                } catch (IOException e5) {
                    e2 = e5;
                    bufferedOutputStream2 = bufferedOutputStream;
                } catch (Throwable th) {
                    e2 = th;
                    bufferedOutputStream2 = bufferedOutputStream;
                }
            } catch (UnsupportedEncodingException e6) {
                e2 = e6;
                bufferedOutputStream = null;
                bufferedReader2 = bufferedReader;
                try {
                    Log.e("SDK_LW_CMM", e2.getMessage(), e2);
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e22) {
                            Log.e("SDK_LW_CMM", e22.getMessage(), e22);
                            return;
                        }
                    }
                    if (bufferedReader2 != null) {
                        bufferedOutputStream.close();
                    }
                } catch (Throwable th2) {
                    e22 = th2;
                    bufferedReader = bufferedReader2;
                    bufferedOutputStream2 = bufferedOutputStream;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e7) {
                            Log.e("SDK_LW_CMM", e7.getMessage(), e7);
                            throw e22;
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedOutputStream2.close();
                    }
                    throw e22;
                }
            } catch (FileNotFoundException e8) {
                e22 = e8;
                try {
                    Log.e("SDK_LW_CMM", e22.getMessage(), e22);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e222) {
                            Log.e("SDK_LW_CMM", e222.getMessage(), e222);
                            return;
                        }
                    }
                    if (bufferedReader != null) {
                        bufferedOutputStream2.close();
                    }
                } catch (Throwable th3) {
                    e222 = th3;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (bufferedReader != null) {
                        bufferedOutputStream2.close();
                    }
                    throw e222;
                }
            } catch (IOException e9) {
                e222 = e9;
                Log.e("SDK_LW_CMM", e222.getMessage(), e222);
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e2222) {
                        Log.e("SDK_LW_CMM", e2222.getMessage(), e2222);
                        return;
                    }
                }
                if (bufferedReader != null) {
                    bufferedOutputStream2.close();
                }
            }
        } catch (UnsupportedEncodingException e10) {
            e2222 = e10;
            bufferedOutputStream = null;
            Log.e("SDK_LW_CMM", e2222.getMessage(), e2222);
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (bufferedReader2 != null) {
                bufferedOutputStream.close();
            }
        } catch (FileNotFoundException e11) {
            e2222 = e11;
            bufferedReader = null;
            Log.e("SDK_LW_CMM", e2222.getMessage(), e2222);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedReader != null) {
                bufferedOutputStream2.close();
            }
        } catch (IOException e12) {
            e2222 = e12;
            bufferedReader = null;
            Log.e("SDK_LW_CMM", e2222.getMessage(), e2222);
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedReader != null) {
                bufferedOutputStream2.close();
            }
        } catch (Throwable th4) {
            e2222 = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedReader != null) {
                bufferedOutputStream2.close();
            }
            throw e2222;
        }
    }

    static String a(Context context) {
        BufferedReader bufferedReader;
        Throwable e;
        Throwable e2;
        String str = "";
        String str2 = "";
        BufferedReader bufferedReader2 = null;
        if (!new File("/data/data/" + context.getPackageName() + "/" + "cmsc.si").exists()) {
            return WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        }
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("/data/data/" + context.getPackageName() + "/" + "cmsc.si")), "UTF-8"));
            str = str2;
            while (true) {
                try {
                    str2 = bufferedReader.readLine();
                    if (str2 == null) {
                        break;
                    }
                    str = str2;
                } catch (UnsupportedEncodingException e3) {
                    e = e3;
                } catch (FileNotFoundException e4) {
                    e = e4;
                } catch (IOException e5) {
                    e = e5;
                }
            }
            if (bufferedReader == null) {
                return str;
            }
            try {
                bufferedReader.close();
                return str;
            } catch (Throwable e6) {
                Log.e("SDK_LW_CMM", e6.getMessage(), e6);
                return str;
            }
        } catch (Throwable e22) {
            bufferedReader = null;
            e6 = e22;
            str = str2;
            try {
                Log.e("SDK_LW_CMM", e6.getMessage(), e6);
                if (bufferedReader == null) {
                    return str;
                }
                try {
                    bufferedReader.close();
                    return str;
                } catch (Throwable e62) {
                    Log.e("SDK_LW_CMM", e62.getMessage(), e62);
                    return str;
                }
            } catch (Throwable th) {
                e22 = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e622) {
                        Log.e("SDK_LW_CMM", e622.getMessage(), e622);
                    }
                }
                throw e22;
            }
        } catch (Throwable e222) {
            bufferedReader = null;
            e622 = e222;
            str = str2;
            Log.e("SDK_LW_CMM", e622.getMessage(), e622);
            if (bufferedReader == null) {
                return str;
            }
            try {
                bufferedReader.close();
                return str;
            } catch (Throwable e6222) {
                Log.e("SDK_LW_CMM", e6222.getMessage(), e6222);
                return str;
            }
        } catch (Throwable e2222) {
            bufferedReader = null;
            e6222 = e2222;
            str = str2;
            Log.e("SDK_LW_CMM", e6222.getMessage(), e6222);
            if (bufferedReader == null) {
                return str;
            }
            try {
                bufferedReader.close();
                return str;
            } catch (Throwable e62222) {
                Log.e("SDK_LW_CMM", e62222.getMessage(), e62222);
                return str;
            }
        } catch (Throwable th2) {
            e2222 = th2;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw e2222;
        }
    }
}
