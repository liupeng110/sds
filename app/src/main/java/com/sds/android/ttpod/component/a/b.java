package com.sds.android.ttpod.component.a;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.ttpod.R;

/* EffectLevelItemHelper */
public class b {
    private static final a a = new a("普通音效达人", R.drawable.img_effect_level_normal, 0, 999);
    private static final a b = new a("初级音效达人", R.drawable.img_effect_level_junior, 1000, 1999);
    private static final a c = new a("高级音效达人", R.drawable.img_effect_level_senior, 2000, 4999);
    private static final a d = new a("资深音效达人", R.drawable.img_effect_level_professional, 5000, 9999);
    private static final a e = new a("音效发烧友", R.drawable.img_effect_level_fancier, 10000, Entry.MAX_LIMIT);

    public static a a(int i) {
        if (i < 1000) {
            return a;
        }
        if (i < 2000) {
            return b;
        }
        if (i < 5000) {
            return c;
        }
        if (i < 10000) {
            return d;
        }
        return e;
    }
}
