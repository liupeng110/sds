package com.sds.android.ttpod.component.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.ttpod.activities.DefaultVideoActivity;

/* SystemVideoPlayer */
public class c implements e {
    public void a(Context context, String str, String str2) {
        ((Activity) context).startActivity(new Intent(context, DefaultVideoActivity.class).putExtra("param_video_url", str).putExtra("param_video_title", str2));
    }

    public void a() {
    }

    public String b() {
        return "";
    }
}
