package com.igexin.push.core.a;

import com.igexin.a.a.d.d;
import com.igexin.push.a.j;
import com.igexin.push.c.c.a;
import com.igexin.push.c.c.c;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.g;

public class e extends a {
    private static final String a = j.a;

    private void a(String str, a aVar) {
        String str2 = "";
        str2 = "";
        str2 = "";
        if (str != null) {
            str2 = str.substring("CDN".length(), str.length());
            if (str2.contains("@")) {
                String[] split = str2.split("\\@");
                String str3 = split[0];
                if (split[1].contains("|")) {
                    split = split[1].split("\\|");
                    String str4 = split[0];
                    str2 = split[1];
                    if (str3 != null && str4 != null && str2 != null) {
                        PushTaskBean pushTaskBean = new PushTaskBean();
                        pushTaskBean.setAppid(g.c);
                        pushTaskBean.setMessageId(str3);
                        pushTaskBean.setTaskId(str4);
                        pushTaskBean.setId(str3);
                        pushTaskBean.setAppKey(g.d);
                        pushTaskBean.setCurrentActionid(1);
                        f.a().a(pushTaskBean);
                        f.a().a(str2, aVar, pushTaskBean);
                    }
                }
            }
        }
    }

    public boolean a(d dVar) {
        return super.a(dVar);
    }

    public boolean a(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (aVar.c != null) {
                String str = (String) aVar.c;
                com.igexin.a.a.c.a.a("cdnpushmessage|" + str);
                if (str.startsWith("RCV")) {
                    String substring = str.substring("RCV".length(), str.length());
                    if (g.ao.containsKey(substring)) {
                        c cVar = (c) g.ao.get(substring);
                        g.ao.remove(substring);
                        if (cVar != null) {
                            com.igexin.push.e.b.c e = cVar.e();
                            if (e != null) {
                                e.t();
                            }
                        }
                    }
                } else if (str.contains("CDN")) {
                    a(str, aVar);
                }
            }
        }
        return true;
    }
}
