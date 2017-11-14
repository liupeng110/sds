package c.a;

import android.content.Context;
import android.provider.Settings.Secure;

/* AndroidIdTracker */
public class y extends a {
    private Context a;

    public y(Context context) {
        super("android_id");
        this.a = context;
    }

    public String f() {
        String str = null;
        try {
            str = Secure.getString(this.a.getContentResolver(), "android_id");
        } catch (Exception e) {
        }
        return str;
    }
}
