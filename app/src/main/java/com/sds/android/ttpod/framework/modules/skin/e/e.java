package com.sds.android.ttpod.framework.modules.skin.e;

import com.sds.android.ttpod.framework.modules.skin.d.d;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* LrcParser */
public class e extends j {
    private static final Pattern a = Pattern.compile("\\[(.+?)\\]");

    protected g a(String str) {
        return new c(str);
    }

    protected void a(g gVar, String str) {
        c cVar = (c) gVar;
        int i = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList c = cVar.c();
        Matcher matcher = a.matcher(str);
        while (matcher.find()) {
            String trim;
            if (!arrayList.isEmpty()) {
                trim = str.substring(i, matcher.start()).trim();
                if (trim.length() > 0) {
                    a(c, arrayList, trim);
                    arrayList.clear();
                }
            }
            trim = matcher.group(1);
            if (!a(cVar.g(), trim)) {
                long a = d.a(trim);
                if (a != Long.MIN_VALUE) {
                    arrayList.add(Long.valueOf(a));
                }
            }
            i = matcher.end();
        }
        if (!arrayList.isEmpty()) {
            a(c, arrayList, str.substring(i).trim());
        }
    }

    private boolean a(i iVar, String str) {
        int indexOf = str.indexOf(":");
        if (indexOf <= 0) {
            return true;
        }
        String trim = str.substring(0, indexOf).trim();
        String trim2 = str.substring(indexOf + 1).trim();
        if (trim.equalsIgnoreCase("ti")) {
            iVar.b(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("ar")) {
            iVar.c(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("al")) {
            iVar.d(trim2);
            return true;
        } else if (trim.equalsIgnoreCase("by")) {
            iVar.e(trim2);
            return true;
        } else if (trim.equalsIgnoreCase(ParamKey.OFFSET)) {
            try {
                long parseLong = Long.parseLong(trim2);
                iVar.a(parseLong);
                iVar.b(parseLong);
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return true;
            }
        } else if (!trim.equalsIgnoreCase("total")) {
            return false;
        } else {
            try {
                iVar.c(Math.max(0, Long.parseLong(trim2)));
                return true;
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                return true;
            }
        }
    }

    private void a(ArrayList<f> arrayList, ArrayList<Long> arrayList2, String str) {
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(a(((Long) it.next()).longValue(), str));
        }
    }

    protected f a(long j, String str) {
        f fVar = new f();
        fVar.a(j);
        fVar.a(str);
        return fVar;
    }

    protected void a(g gVar) {
        c cVar = (c) gVar;
        List c = cVar.c();
        int size = c.size() - 1;
        i g = cVar.g();
        for (int i = 0; i <= size; i++) {
            f fVar = (f) c.get(i);
            long d = fVar.d();
            if (i < size) {
                d = ((f) c.get(i + 1)).d() - d;
            } else {
                d = a(fVar, gVar.f());
            }
            fVar.b(d > 0 ? (int) d : 1);
            fVar.a(g);
        }
    }

    protected long a(f fVar, long j) {
        long d = fVar.d();
        return d > j ? 5000 : j - d;
    }
}
