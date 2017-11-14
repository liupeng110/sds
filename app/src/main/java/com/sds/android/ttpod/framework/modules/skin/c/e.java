package com.sds.android.ttpod.framework.modules.skin.c;

import android.text.TextUtils.SimpleStringSplitter;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.ttpod.framework.modules.skin.d.m;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.Iterator;

/* EventCompiler */
public class e {
    public static int[] a(String str) {
        if (str == null) {
            return null;
        }
        if (!str.endsWith(";")) {
            str = str + ';';
        }
        int[] iArr = new int[b(str)];
        SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(';');
        simpleStringSplitter.setString(str);
        Iterator it = simpleStringSplitter.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            SimpleStringSplitter simpleStringSplitter2 = new SimpleStringSplitter('|');
            simpleStringSplitter2.setString(str2);
            Iterator it2 = simpleStringSplitter2.iterator();
            while (it2.hasNext()) {
                i = a((String) it2.next(), iArr, i);
            }
            int i2 = i + 1;
            iArr[i] = 0;
            i = i2;
        }
        iArr[i] = -1;
        return iArr;
    }

    private static int b(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < length; i3++) {
            switch (str.charAt(i3)) {
                case '(':
                    i2 += 2;
                    i = i3;
                    break;
                case ')':
                    if (i3 - i <= 1) {
                        break;
                    }
                    i2++;
                    i = 0;
                    break;
                case ',':
                    i2++;
                    break;
                case ';':
                    i2++;
                    break;
                default:
                    break;
            }
        }
        return i2;
    }

    private static int a(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i, int i2) {
        int i3 = i + 1;
        iArr[i] = i2;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        while (simpleStringSplitter.hasNext()) {
            int i5 = i4 + 1;
            iArr[i4] = m.b(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
            i4 = i5;
        }
        return i4 - i;
    }

    private static int a(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36864;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = m.a(simpleStringSplitter.next(), 0);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        return i2 - i;
    }

    private static int b(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65541;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = m.a(simpleStringSplitter.next(), 1);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        return i2 - i;
    }

    private static int c(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 65542;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = m.a(simpleStringSplitter.next(), 1);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        return i2 - i;
    }

    private static int d(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36865;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (m.a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), 1.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        return i2 - i;
    }

    private static int e(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36866;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (m.a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), 1.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), 1.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        return i2 - i;
    }

    private static int f(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36867;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (m.a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), 180.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        return i2 - i;
    }

    private static int g(SimpleStringSplitter simpleStringSplitter, int[] iArr, int i) {
        int i2;
        int i3 = i + 1;
        iArr[i] = 36868;
        int i4 = i3 + 1;
        iArr[i3] = 0;
        if (simpleStringSplitter.hasNext()) {
            i2 = i4 + 1;
            iArr[i4] = (int) (m.a(simpleStringSplitter.next(), 0.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
        } else {
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), 180.0f) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = (int) (m.a(simpleStringSplitter.next(), (float) TTFMImageUtils.Middle_Scale) * 100.0f);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = i2 + 1;
            iArr[i2] = m.a(simpleStringSplitter.next(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
            iArr[i3] = iArr[i3] + 1;
            i2 = i4;
        }
        return i2 - i;
    }

    private static int a(String str, int[] iArr, int i) {
        SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(',');
        String str2 = "";
        int indexOf = str.indexOf(40);
        if (indexOf <= 0) {
            return i;
        }
        int lastIndexOf = str.lastIndexOf(41);
        if (lastIndexOf <= indexOf) {
            return i;
        }
        String substring = str.substring(0, indexOf);
        indexOf++;
        if (indexOf < lastIndexOf) {
            str2 = str.substring(indexOf, lastIndexOf);
        }
        simpleStringSplitter.setString(str2);
        if ("show".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, StatisticHelper.DELAY_SEND);
        }
        if ("hide".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65538);
        }
        if ("move".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65539);
        }
        if ("animation".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i);
        }
        if ("sleep".equals(substring)) {
            return i + b(simpleStringSplitter, iArr, i);
        }
        if ("wait".equals(substring)) {
            return i + c(simpleStringSplitter, iArr, i);
        }
        if ("alphaAnimation".equals(substring)) {
            return i + d(simpleStringSplitter, iArr, i);
        }
        if ("scaleAnimation".equals(substring)) {
            return i + e(simpleStringSplitter, iArr, i);
        }
        if ("rotateAnimation".equals(substring)) {
            return i + f(simpleStringSplitter, iArr, i);
        }
        if ("rotate3dAnimation".equals(substring)) {
            return i + g(simpleStringSplitter, iArr, i);
        }
        if (ParamKey.OFFSET.equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65543);
        }
        if ("enable".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65544);
        }
        if ("disable".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65545);
        }
        if ("startRotate".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65552);
        }
        if ("stopRotate".equals(substring)) {
            return i + a(simpleStringSplitter, iArr, i, 65553);
        }
        return i;
    }
}
