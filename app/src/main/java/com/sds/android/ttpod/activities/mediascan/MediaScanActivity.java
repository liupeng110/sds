package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.setting.MediaScanSettingActivity;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;

public class MediaScanActivity extends SlidingClosableActivity {
    private static final int REQUEST_CODE_FILE_PICKER = 0;
    private static final int REQUEST_CODE_SETTING = 1;
    private static final int REQUEST_CODE_WIFI = 2;
    private static final String TAG = "MediaScanActivity";
    private View mCustomView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ MediaScanActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_mediascan_custom:
                    t.b("PAGE_CLICK", r.ACTION_SCAN_MUSIC_CUSTOM, s.PAGE_NONE, s.PAGE_SCAN_MUSIC_CUSTOM);
                    new b().b("my_scan").a();
                    Intent intent = new Intent(this.a, FilePickerActivity.class);
                    intent.putExtra(FilePickerActivity.KEY_EXTRA_CONFIRMYPE, 2);
                    this.a.startActivityForResult(intent, 0);
                    return;
                case R.id.button_mediascan_wifi:
                    t.b("PAGE_CLICK", r.ACTION_SCAN_MUSCI_WIFI, s.PAGE_NONE, s.PAGE_SCAN_MUSIC_UPLOAD_WIFI);
                    new b().b("wifi_song").a();
                    f.a(this.a, 2);
                    return;
                case R.id.button_mediascan_setting:
                    t.b("PAGE_CLICK", r.ACTION_SCAN_MUSCI_SETTING, s.PAGE_NONE, s.PAGE_SCAN_MUSIC_SETTING);
                    new b().b("scan_setting").a();
                    this.a.startActivityForResult(new Intent(this.a, MediaScanSettingActivity.class), 1);
                    return;
                case R.id.button_mediascan_start:
                    t.b("PAGE_CLICK", r.ACTION_SCAN_MUSCI_ONE_KEY, s.PAGE_NONE, s.PAGE_SCAN_MUSIC_ONE_KEY);
                    this.a.startActivity(new Intent(this.a, MediaScanAnimationActivity.class));
                    this.a.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private View mSettingView;
    private View mStartView;
    private View mWifiView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan);
        setStatisticPage(s.PAGE_SCAN_MUSIC);
        setTBSPage("tt_scan_music");
        trackModule("scan_music");
        setTitle((int) R.string.media_scan);
        getActionBarController().d();
        this.mCustomView = findViewById(R.id.button_mediascan_custom);
        this.mCustomView.setOnClickListener(this.mOnClickListener);
        this.mWifiView = findViewById(R.id.button_mediascan_wifi);
        this.mWifiView.setOnClickListener(this.mOnClickListener);
        this.mSettingView = findViewById(R.id.button_mediascan_setting);
        this.mSettingView.setOnClickListener(this.mOnClickListener);
        this.mStartView = findViewById(R.id.button_mediascan_start);
        this.mStartView.setOnClickListener(this.mOnClickListener);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            Intent intent2;
            switch (i) {
                case 0:
                    Object stringArrayExtra = intent.getStringArrayExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES);
                    if (stringArrayExtra != null && stringArrayExtra.length > 0) {
                        g.a(TAG, "set custom scan folder: " + stringArrayExtra.toString());
                        intent2 = new Intent(this, MediaScanAnimationActivity.class);
                        intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, stringArrayExtra);
                        startActivity(intent2);
                        finish();
                        return;
                    }
                    return;
                case 1:
                    finish();
                    return;
                case 2:
                    if (intent.getBooleanExtra(MediaScanWifiActivity.KEY_WIFI_UPLOAD, false)) {
                        String[] strArr = new String[]{a.m()};
                        intent2 = new Intent(this, MediaScanAnimationActivity.class);
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
