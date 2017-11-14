package com.igexin.sdk;

import java.io.Serializable;

public class Tag implements Serializable {
    private String a;

    private boolean a(String str) {
        boolean z = false;
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            z = (charAt >= '一' && charAt <= '龥') || ((charAt >= 'A' && charAt <= 'Z') || ((charAt >= 'a' && charAt <= 'z') || ((charAt >= '0' && charAt <= '9') || charAt == '+' || charAt == '-' || charAt == '*' || charAt == '_' || charAt == ' ' || charAt == ':')));
            if (!z) {
                break;
            }
        }
        return z;
    }

    private boolean b(String str) {
        return a(str);
    }

    public String getName() {
        return this.a;
    }

    public void setName(String str) {
        if (b(str)) {
            this.a = str;
        }
    }
}
