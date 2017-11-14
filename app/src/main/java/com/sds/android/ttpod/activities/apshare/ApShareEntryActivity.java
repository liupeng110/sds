package com.sds.android.ttpod.activities.apshare;

import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanWifiActivity;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.apshare.j;
import com.sds.android.ttpod.fragment.apshare.ApShareEntryFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class ApShareEntryActivity extends SlidingClosableActivity {
    public static final int REQUEST_CODE_WIFI = 2;
    private static final String TAG = "ApShareEntryActivity";
    private String mMediaId;
    private j mWifiApManager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_DELIVER_SONG);
        setTBSPage("tt_deliver_song");
        trackModule("deliver_song");
        setContentView((int) R.layout.activity_lightweight_entry);
        a actionBarController = getActionBarController();
        com.sds.android.ttpod.activities.audioeffect.a.b(actionBarController);
        actionBarController.b((int) R.string.share_fast_send);
        this.mWifiApManager = j.a(getApplicationContext());
        Intent intent = getIntent();
        if (intent != null) {
            this.mMediaId = intent.getStringExtra("key_media_id");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_entry, new ApShareEntryFragment()).commit();
        if (!m.a(this.mMediaId)) {
            intent = new Intent(this, ApShareChooseActivity.class);
            intent.putExtra("key_media_id", this.mMediaId);
            startActivity(intent);
        }
    }

    protected void onResume() {
        g.d(TAG, "onResume");
        this.mWifiApManager.f();
        super.onResume();
    }

    protected void onDestroy() {
        g.d(TAG, "onDestroy");
        this.mWifiApManager.b("TTPODShare-");
        this.mWifiApManager.f();
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            switch (i) {
                case 2:
                    if (intent.getBooleanExtra(MediaScanWifiActivity.KEY_WIFI_UPLOAD, false)) {
                        String[] strArr = new String[]{com.sds.android.ttpod.framework.a.m()};
                        Intent intent2 = new Intent(this, MediaScanAnimationActivity.class);
                        intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, strArr);
                        startActivity(intent2);
                        finish();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
