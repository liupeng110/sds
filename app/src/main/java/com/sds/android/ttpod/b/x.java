package com.sds.android.ttpod.b;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* VideoUtils */
public class x {

    /* VideoUtils */
    public enum a {
        TYPE_NORMAL(-1),
        TYPE_HIGH(R.string.mv_quality_hd),
        TYPE_SUPER(R.string.mv_quality_uhd);
        
        private String mMvQuality;

        private a(int i) {
            if (i != -1) {
                this.mMvQuality = BaseApplication.e().getResources().getString(i);
            } else {
                this.mMvQuality = "";
            }
        }

        public String getMvQuality() {
            return this.mMvQuality;
        }
    }

    public static a a(int i) {
        a aVar = a.TYPE_NORMAL;
        switch (i) {
            case 1:
                return a.TYPE_HIGH;
            case 2:
                return a.TYPE_SUPER;
            default:
                return aVar;
        }
    }
}
