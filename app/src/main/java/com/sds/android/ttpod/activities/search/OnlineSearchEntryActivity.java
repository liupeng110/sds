package com.sds.android.ttpod.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.search.OnlineSearchFragment;

public class OnlineSearchEntryActivity extends SlidingClosableActivity {
    private static final String LOG_TAG = "OnlineSearchEntryActivity";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("keyword");
        int intExtra = intent.getIntExtra("app", 0);
        if (TextUtils.isEmpty(stringExtra)) {
            g.b(LOG_TAG, "keyword or appName is empty, will exit, keyword=" + stringExtra);
            finish();
            return;
        }
        getActionBarController().c(false);
        setContentView((int) R.layout.activity_online_search_entry);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, OnlineSearchFragment.instantiate(stringExtra, intExtra)).commitAllowingStateLoss();
    }
}
