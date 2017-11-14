package com.sds.android.ttpod.activities.apshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.apshare.ApShareChooseFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;

public class ApShareChooseActivity extends SlidingClosableActivity {
    private static final String TAG = "ApShareChooseActivity";
    private String mMediaId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideActionBar();
        setContentView((int) R.layout.activity_lightweight_entry);
        setLaunchFragmentAttr(R.id.layout_entry, 0, 0);
        onNewIntent(getIntent());
        Bundle bundle2 = new Bundle();
        bundle2.putString("key_media_id", this.mMediaId);
        setPrimaryFragment((BaseFragment) Fragment.instantiate(this, ApShareChooseFragment.class.getName(), bundle2));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            this.mMediaId = intent.getStringExtra("key_media_id");
        }
    }
}
