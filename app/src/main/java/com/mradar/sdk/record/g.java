package com.mradar.sdk.record;

import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* MRadarSdkJSON */
public class g {
    protected static DoresoMusicTrack[] a(String str) {
        DoresoMusicTrack[] doresoMusicTrackArr = null;
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            doresoMusicTrackArr = new DoresoMusicTrack[length];
            for (int i = 0; i < length; i++) {
                doresoMusicTrackArr[i] = a(jSONArray.getJSONObject(i));
            }
        } catch (JSONException e) {
        }
        return doresoMusicTrackArr;
    }

    protected static DoresoMusicTrack a(JSONObject jSONObject) {
        DoresoMusicTrack doresoMusicTrack = new DoresoMusicTrack();
        try {
            doresoMusicTrack.a(jSONObject.getLong(StarCategory.KEY_STAR_CATEGORY_ID));
        } catch (Exception e) {
            doresoMusicTrack.a(0);
        }
        try {
            doresoMusicTrack.a(jSONObject.getString("name"));
        } catch (Exception e2) {
            doresoMusicTrack.a("");
        }
        try {
            doresoMusicTrack.b(jSONObject.getString("artist_name"));
        } catch (Exception e3) {
            doresoMusicTrack.b("");
        }
        try {
            doresoMusicTrack.c(jSONObject.getString("album"));
        } catch (Exception e4) {
            doresoMusicTrack.c("");
        }
        try {
            doresoMusicTrack.d(jSONObject.getString("image"));
        } catch (Exception e5) {
            doresoMusicTrack.d("");
        }
        try {
            doresoMusicTrack.b(jSONObject.getLong("play_offset"));
        } catch (Exception e6) {
            doresoMusicTrack.b(0);
        }
        try {
            doresoMusicTrack.a(jSONObject.getDouble(MediasColumns.RATING));
        } catch (Exception e7) {
            doresoMusicTrack.a(-1.0d);
        }
        try {
            doresoMusicTrack.c(jSONObject.getLong("duration"));
        } catch (Exception e8) {
            doresoMusicTrack.c(0);
        }
        return doresoMusicTrack;
    }
}
