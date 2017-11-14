package com.sds.android.ttpod.activities.apshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.apshare.ApShareReceiveFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;

public class ApShareReceiveActivity extends SlidingClosableActivity {
    private static final String TAG = "ApShareReceiveActivity";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideActionBar();
        setContentView((int) R.layout.activity_lightweight_entry);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_entry, (BaseFragment) Fragment.instantiate(this, ApShareReceiveFragment.class.getName(), null)).commit();
    }
}
