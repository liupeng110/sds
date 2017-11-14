package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.settings.SettingEntryFragment;

public class SettingEntryActivity extends SlidingClosableActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.setting);
        setContentView((int) R.layout.activity_lightweight_entry);
        getActionBarController().d();
        setLaunchFragmentAttr(R.id.layout_entry, R.anim.slide_in_right, R.anim.slide_out_right);
        setPrimaryFragment(new SettingEntryFragment());
    }

    protected void onPause() {
        super.onPause();
        f.a();
    }
}
