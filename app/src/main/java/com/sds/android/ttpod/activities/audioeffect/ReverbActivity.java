package com.sds.android.ttpod.activities.audioeffect;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.audioeffect.ReverbFragment;

public class ReverbActivity extends SlidingClosableActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((int) R.string.effect_reverb_label);
        setContentView((int) R.layout.activity_reverb);
        initReverbFragment();
        a.a(getActionBarController());
    }

    private void initReverbFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.reverbfragment_content, new ReverbFragment()).commit();
    }
}
