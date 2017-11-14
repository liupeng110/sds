package com.taobao.wireless.security.adapter.m;

import com.alibaba.wireless.security.open.SecException;

public interface b {
    int a(String str, byte[] bArr) throws SecException;

    boolean a(String str);

    byte[] a(int i, String str, String str2) throws SecException;

    byte[] a(int i, String str, byte[] bArr) throws SecException;

    int b(String str);

    byte[] b(int i, String str, byte[] bArr) throws SecException;
}
