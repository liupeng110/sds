package com.sds.android.ttpod.framework.modules.skin.e;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* TrcParser */
public class q extends e {
    private static final Pattern a = Pattern.compile("<(\\d+?)>");

    protected /* synthetic */ f a(long j, String str) {
        return b(j, str);
    }

    protected g a(String str) {
        return new o(str);
    }

    protected r b(long j, String str) {
        r rVar = new r();
        rVar.a(j);
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = a.matcher(str);
        int i = 0;
        int i2 = 0;
        while (matcher.find()) {
            if (i2 > 0) {
                String substring = str.substring(i, matcher.start());
                stringBuilder.append(substring);
                rVar.a(new s(substring.length(), i2));
            }
            try {
                i2 = Integer.parseInt(matcher.group(1).trim());
            } catch (NumberFormatException e) {
                i2 = 0;
            }
            i = matcher.end();
        }
        if (i2 > 0) {
            substring = str.substring(i);
            stringBuilder.append(substring);
            rVar.a(new s(substring.length(), i2));
        }
        if (rVar.h() == null) {
            rVar.a(str);
        } else {
            rVar.a(stringBuilder.toString());
        }
        return rVar;
    }

    protected long a(f fVar, long j) {
        r rVar = (r) fVar;
        long d = fVar.d();
        long j2 = 0;
        ArrayList h = rVar.h();
        if (h == null) {
            return d > j ? 5000 : j - d;
        } else {
            Iterator it = h.iterator();
            while (it.hasNext()) {
                j2 = ((long) ((s) it.next()).c()) + j2;
            }
            return j2;
        }
    }
}
