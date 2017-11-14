package com.sds.android.ttpod.activities.setting;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import java.util.HashMap;

/* SettingUtils */
public final class d {
    private static final HashMap<AudioQuality, int[]> a = new HashMap(AudioQuality.values().length);

    static {
        a.put(AudioQuality.UNDEFINED, new int[]{R.string.auto_title, R.string.auto_subtitle});
        a.put(AudioQuality.COMPRESSED, new int[]{R.string.compress_title, R.string.compress_subtitle});
        a.put(AudioQuality.STANDARD, new int[]{R.string.normal_title, R.string.normal_subtitle});
        a.put(AudioQuality.HIGH, new int[]{R.string.high_title, R.string.high_subtitle});
        a.put(AudioQuality.SUPER, new int[]{R.string.super_title, R.string.super_subtitle});
        a.put(AudioQuality.LOSSLESS, new int[]{R.string.lossless_title, R.string.lossless_subtitle});
    }

    public static void a(ActionBarActivity actionBarActivity) {
        Intent intent = actionBarActivity.getIntent();
        actionBarActivity.getActionBarController().d();
        if (intent != null) {
            CharSequence stringExtra = intent.getStringExtra("title");
            if (!TextUtils.isEmpty(stringExtra)) {
                actionBarActivity.setTitle(stringExtra);
            }
        }
    }

    public static void a(Activity activity, Class<? extends Activity> cls, CharSequence charSequence) {
        activity.startActivity(new Intent(activity, cls).putExtra("title", charSequence.toString()));
    }
}
