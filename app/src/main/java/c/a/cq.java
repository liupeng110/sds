package c.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* ViewPageTracker */
public class cq {
    private final Map<String, Long> a = new HashMap();
    private final ArrayList<co> b = new ArrayList();

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                this.a.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Long l;
            synchronized (this.a) {
                l = (Long) this.a.remove(str);
            }
            if (l == null) {
                ai.d("MobclickAgent", String.format("please call 'onPageStart(%s)' before onPageEnd", new Object[]{str}));
                return;
            }
            long currentTimeMillis = System.currentTimeMillis() - l.longValue();
            synchronized (this.b) {
                this.b.add(new co(str, currentTimeMillis));
            }
        }
    }

    public void a() {
        synchronized (this.a) {
            String str = null;
            long j = 0;
            for (Entry entry : this.a.entrySet()) {
                if (((Long) entry.getValue()).longValue() > j) {
                    j = ((Long) entry.getValue()).longValue();
                    str = (String) entry.getKey();
                }
            }
        }
        if (str != null) {
            b(str);
        }
    }

    public void a(Context context) {
        SharedPreferences a = cl.a(context);
        Editor edit = a.edit();
        if (this.b.size() > 0) {
            Object string = a.getString("activities", "");
            StringBuilder stringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(string)) {
                stringBuilder.append(string);
                stringBuilder.append(";");
            }
            synchronized (this.b) {
                Iterator it = this.b.iterator();
                while (it.hasNext()) {
                    co coVar = (co) it.next();
                    stringBuilder.append(String.format("[\"%s\",%d]", new Object[]{coVar.a, Long.valueOf(coVar.b)}));
                    stringBuilder.append(";");
                }
                this.b.clear();
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            edit.remove("activities");
            edit.putString("activities", stringBuilder.toString());
        }
        edit.commit();
    }

    public static List<w> a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("activities", "");
        if ("".equals(string)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            String[] split = string.split(";");
            for (Object obj : split) {
                if (!TextUtils.isEmpty(obj)) {
                    arrayList.add(new d(obj));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }
}
