package c.a;

import android.content.Context;
import com.c.a.a;
import java.util.HashMap;
import java.util.Map;

/* EventTracker */
public class cf {
    private final int a = 128;
    private final int b = 256;
    private cd c;
    private Context d;
    private cc e;

    public cf(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null, can't track event");
        }
        this.d = context.getApplicationContext();
        this.c = new cd(this.d);
        this.c.a(!a.l);
        this.e = cc.a(this.d);
    }

    public void a(String str, String str2, long j, int i) {
        if (a(str) && b(str2)) {
            Map hashMap = new HashMap();
            if (str2 == null) {
                str2 = "";
            }
            hashMap.put(str, str2);
            this.e.a(new b(str, hashMap, j, i));
        }
    }

    private boolean a(String str) {
        if (str != null) {
            int length = str.trim().getBytes().length;
            if (length > 0 && length <= 128) {
                return true;
            }
        }
        ai.b("MobclickAgent", "Event id is empty or too long in tracking Event");
        return false;
    }

    private boolean b(String str) {
        if (str == null || str.trim().getBytes().length <= 256) {
            return true;
        }
        ai.b("MobclickAgent", "Event label or value is empty or too long in tracking Event");
        return false;
    }
}
