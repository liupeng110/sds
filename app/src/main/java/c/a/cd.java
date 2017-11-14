package c.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/* Caretaker */
public class cd {
    private final String a = "umeng_event_snapshot";
    private boolean b = false;
    private SharedPreferences c;
    private Map d = new HashMap();

    public cd(Context context) {
        this.c = cl.a(context, "umeng_event_snapshot");
    }

    public void a(boolean z) {
        this.b = z;
    }
}
