package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.f;
import com.sds.android.sdk.lib.request.g;
import com.sds.android.sdk.lib.util.m;
import java.util.List;

public class AppVersionResult extends g<UpdateData> {

    public static class UpdateData {
        @c(a = "update_info")
        private String mDescription = "";
        @c(a = "update_url")
        private String mUrl = "";
    }

    public UpdateData getUpdateData() {
        List dataList = getDataList();
        if (dataList.size() == 1) {
            return (UpdateData) dataList.get(0);
        }
        return null;
    }

    public boolean isUpdateMandatory() {
        return (getExtra() == null || m.a(getExtra().a()) || Integer.parseInt(getExtra().a()) == 0) ? false : true;
    }

    public String getLatestVersion() {
        f extra = getExtra();
        if (extra != null) {
            Object c = extra.c();
            if (c != null && (c instanceof String)) {
                return (String) c;
            }
        }
        return "";
    }

    public String getUpdateUrl() {
        List dataList = getDataList();
        return dataList.size() == 1 ? ((UpdateData) dataList.get(0)).mUrl : "";
    }

    public String getUpdateDescription() {
        List dataList = getDataList();
        return dataList.size() == 1 ? ((UpdateData) dataList.get(0)).mDescription : "";
    }
}
