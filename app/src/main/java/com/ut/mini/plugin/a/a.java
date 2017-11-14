package com.ut.mini.plugin.a;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.ut.mini.core.d.b;
import com.ut.mini.plugin.UTMCPlugin;
import java.util.Map;

/* UTAggregatedLogPlugin */
public class a extends UTMCPlugin {
    public int[] returnRequiredMsgIds() {
        return new int[]{65536};
    }

    public void onPluginMsgArrivedFromSDK(int i, Object obj) {
        if (i == 65536 && (obj instanceof Map)) {
            try {
                String assembleWithFullFields = b.assembleWithFullFields((Map) obj);
                if (assembleWithFullFields != null && assembleWithFullFields.length() > 0) {
                    com.ut.mini.core.b.a().a(assembleWithFullFields, FeedbackItem.STATUS_RECORDED);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
