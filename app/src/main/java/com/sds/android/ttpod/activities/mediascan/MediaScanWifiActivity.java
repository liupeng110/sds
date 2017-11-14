package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.base.e;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.Map;

public class MediaScanWifiActivity extends SlidingClosableActivity {
    public static final String KEY_WIFI_UPLOAD = "key_wifi_upload";
    private static final String TAG = "MediaScanWifiActivity";
    private boolean mHasMediaFileUploaded;
    private TextView mInputIpTextView;
    private TextView mIpAddressTextView;
    private TextView mTipsTextView;
    private TextView mTitleTextView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan_wifi);
        setStatisticPage(s.PAGE_SCAN_MUSIC_UPLOAD_WIFI);
        setTitle((int) R.string.mediascan_wifi);
        getActionBarController().d();
        this.mTitleTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_title);
        this.mTipsTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_tips);
        this.mInputIpTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_input_ip);
        this.mIpAddressTextView = (TextView) findViewById(R.id.textview_mediascan_wifi_ipaddress);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_WIFI_TRANSMISSION_STATE, i.a(MediaScanWifiActivity.class, "updateWifiTransmissionState", d.class));
    }

    protected void onStart() {
        super.onStart();
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.START_WIFI_TRANSMISSION, new Object[0]));
    }

    protected void onStop() {
        super.onStop();
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.STOP_WIFI_TRANSMISSION, new Object[0]));
    }

    public void updateWifiTransmissionState(d dVar) {
        g.c(TAG, "updateWifiTransmissionState: " + dVar.toString());
        e a = dVar.a();
        if (e.ErrNone == a) {
            this.mTitleTextView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.img_textview_mediascan_wifi_on), null, null);
            this.mTitleTextView.setText(getText(R.string.mediascan_wifi_title));
            this.mTipsTextView.setText(getText(R.string.mediascan_wifi_tips));
            this.mInputIpTextView.setVisibility(0);
            this.mIpAddressTextView.setVisibility(0);
            this.mIpAddressTextView.setText(dVar.d().toString());
        } else if (e.ErrCompletion == a) {
            this.mHasMediaFileUploaded = true;
        } else {
            this.mTitleTextView.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.img_textview_mediascan_wifi_off), null, null);
            this.mTitleTextView.setText(getText(R.string.mediascan_wifi_no_wifi));
            this.mTipsTextView.setText(getText(R.string.mediascan_wifi_check_network));
            this.mInputIpTextView.setVisibility(8);
            this.mIpAddressTextView.setVisibility(8);
        }
    }

    public void finish() {
        setResult(-1, new Intent().putExtra(KEY_WIFI_UPLOAD, this.mHasMediaFileUploaded));
        super.finish();
    }
}
