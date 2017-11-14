package com.sds.android.ttpod.framework.modules.b;

import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* TimeFormatter */
public class g {
    public int a() {
        return Integer.parseInt(new SimpleDateFormat(VersionUpdateConst.KEY_HIAPK_UPDATE_CATEGORY, Locale.CHINA).format(new Date()));
    }
}
