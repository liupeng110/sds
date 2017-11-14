package c.a;

import android.content.Context;

/* MacTracker */
public class by extends a {
    private Context a;

    public by(Context context) {
        super("mac");
        this.a = context;
    }

    public String f() {
        String str = null;
        try {
            str = ah.k(this.a);
        } catch (Exception e) {
        }
        return str;
    }
}
