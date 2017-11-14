package c.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.c.a.a;
import com.sds.android.sdk.core.statistic.SEvent;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import java.util.Arrays;
import java.util.List;

/* SessionTracker */
public class cm {
    private final String a = "a_start_time";
    private final String b = "a_end_time";

    public e a(Context context) {
        SharedPreferences a = cl.a(context);
        String string = a.getString(SEvent.FIELD_SESSION_ID, null);
        if (string == null) {
            return null;
        }
        long j = a.getLong("session_start_time", 0);
        long j2 = a.getLong("session_end_time", 0);
        long j3 = 0;
        if (j2 != 0) {
            j3 = j2 - j;
            if (Math.abs(j3) > HttpChannelSongListGetV2.CACHE_TIME) {
                j3 = 0;
            }
        }
        e eVar = new e();
        eVar.a(string);
        eVar.a(j);
        eVar.b(j2);
        eVar.c(j3);
        double[] a2 = a.a();
        if (a2 != null) {
            u uVar = new u(a2[0], a2[1], System.currentTimeMillis());
            if (eVar.f()) {
                eVar.a(uVar);
            } else {
                eVar.b(Arrays.asList(new u[]{uVar}));
            }
        }
        ad a3 = cp.a(context);
        if (a3 != null) {
            eVar.a(a3);
        }
        List a4 = cq.a(a);
        if (a4 != null && a4.size() > 0) {
            eVar.a(a4);
        }
        a(a);
        return eVar;
    }

    private void a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.remove("session_start_time");
        edit.remove("session_end_time");
        edit.remove(SEvent.FIELD_SESSION_ID);
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.putString("activities", "");
        edit.commit();
    }

    public String b(Context context) {
        String c = ah.c(context);
        String a = a.a(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (a == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(a).append(c);
        return ak.a(stringBuilder.toString());
    }

    public void c(Context context) {
        SharedPreferences a = cl.a(context);
        if (a != null) {
            if (b(a)) {
                ai.a("MobclickAgent", "Start new session: " + a(context, a));
            } else {
                ai.a("MobclickAgent", "Extend current session: " + a.getString(SEvent.FIELD_SESSION_ID, null));
            }
            Editor edit = a.edit();
            edit.putLong("a_start_time", System.currentTimeMillis());
            edit.putLong("a_end_time", 0);
            edit.commit();
        }
    }

    public void d(Context context) {
        SharedPreferences a = cl.a(context);
        if (a != null) {
            if (a.getLong("a_start_time", 0) == 0 && a.j) {
                ai.b("MobclickAgent", "onPause called before onResume");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Editor edit = a.edit();
            edit.putLong("a_start_time", 0);
            edit.putLong("a_end_time", currentTimeMillis);
            edit.putLong("session_end_time", currentTimeMillis);
            edit.commit();
        }
    }

    private boolean b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j != 0 && currentTimeMillis - j < a.n) {
            ai.b("MobclickAgent", "onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j2 > a.n) {
            return true;
        } else {
            return false;
        }
    }

    private String a(Context context, SharedPreferences sharedPreferences) {
        cc a = cc.a(context);
        String b = b(context);
        ch a2 = a(context);
        Editor edit = sharedPreferences.edit();
        edit.putString(SEvent.FIELD_SESSION_ID, b);
        edit.putLong("session_start_time", System.currentTimeMillis());
        edit.putLong("session_end_time", 0);
        edit.commit();
        if (a2 != null) {
            a.a(a2);
        } else {
            a.a(null);
        }
        return b;
    }
}
