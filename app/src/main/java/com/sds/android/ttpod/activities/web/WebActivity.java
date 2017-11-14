package com.sds.android.ttpod.activities.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;

@Deprecated
public class WebActivity extends SlidingClosableActivity {
    private static final String TAG = "WebActivity";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.framelayout_container);
        getActionBarController().d();
        String str = "tt_web_html";
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(WebFragment.EXTRA_URL, intent.getData().toString());
            bundle2.putAll(intent.getExtras());
            CharSequence string = bundle2.getString(WebFragment.EXTRA_TITLE);
            a actionBarController = getActionBarController();
            if (m.a((String) string)) {
                string = "";
            }
            actionBarController.a(string);
            String string2 = bundle2.getString(WebFragment.EXTRA_PAGE);
            if (!m.a(string2)) {
                str = string2;
            }
            if (!bundle2.getBoolean(WebFragment.EXTRA_ENABLE_SLIDING_CLOSABLE, true)) {
                setSlidingCloseMode(0);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, (BaseFragment) Fragment.instantiate(this, WebFragment.class.getName(), bundle2)).commitAllowingStateLoss();
        }
        setTBSPage(str);
        trackModule("web_html");
        trackPlaySong("web_html", "web_html", true);
    }
}
