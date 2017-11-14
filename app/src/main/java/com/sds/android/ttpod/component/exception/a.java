package com.sds.android.ttpod.component.exception;

import android.os.Environment;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SManager;
import com.sds.android.sdk.core.statistic.SUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* StatisticPageUtils */
public class a {

    /* StatisticPageUtils */
    private static class a implements Comparator<File> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((File) obj, (File) obj2);
        }

        public int a(File file, File file2) {
            if (m.a(file.getName()) || m.a(file2.getName())) {
                return -1;
            }
            return file.getName().compareTo(file2.getName());
        }
    }

    public static String a() {
        File[] listFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + EnvironmentUtils.a() + "/cache/").listFiles();
        if (listFiles == null) {
            return "";
        }
        Arrays.sort(listFiles, new a());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pages:");
        int i = 0;
        for (int length = listFiles.length - 1; length > 0; length--) {
            File file = listFiles[length];
            if (file.getName().startsWith(SManager.PREFIX_STATISTIC_FILE_NAME)) {
                if (i >= 2) {
                    break;
                }
                i++;
                stringBuilder.append(a(file.getAbsolutePath()));
            }
        }
        stringBuilder.append("\r\n");
        g.c("StatisticPageUtils", "getRecentStatisticPages pages=" + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private static String a(String str) {
        if (m.a(str)) {
            return "";
        }
        JSONArray readEventFromFile = SUtils.readEventFromFile(str);
        if (readEventFromFile == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < readEventFromFile.length(); i++) {
            try {
                JSONObject jSONObject = (JSONObject) readEventFromFile.get(i);
                if (m.a(String.valueOf(jSONObject.get(FeedbackMessage.TYPE_EVENT)), "PAGE_CLICK")) {
                    String valueOf = String.valueOf(jSONObject.get(PushConsts.CMD_ACTION));
                    String valueOf2 = String.valueOf(jSONObject.get("page"));
                    stringBuilder.append(valueOf).append(SelectCountryActivity.SPLITTER).append(valueOf2).append(SelectCountryActivity.SPLITTER).append(String.valueOf(jSONObject.get(SEvent.FIELD_TO))).append(";");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
