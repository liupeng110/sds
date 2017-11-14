package com.sds.android.ttpod.activities.mediascan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.SlidingPagerFragment;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MediaScanAnimationActivity extends BaseActivity {
    public static final String KEY_GROUP_ID = "key_groupid";
    public static final String KEY_SCAN_FILES = "key_scan_files";
    private static final int MSG_REFRESH = 0;
    private static final int REFRESH_TIME = 50;
    private static final String TAG = "MediaScanAnimationActivity";
    private Animation mAnimationImageView;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ MediaScanAnimationActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.refreshProgress();
                    sendEmptyMessageDelayed(0, 50);
                    return;
                default:
                    return;
            }
        }
    };
    private int[] mImageIds = new int[]{R.drawable.img_scan_navigation001, R.drawable.img_scan_navigation002, R.drawable.img_scan_navigation003, R.drawable.img_scan_navigation004, R.drawable.img_scan_navigation005, R.drawable.img_scan_navigation006};
    private boolean mIsScanning;
    private TextView mMediaScanCountTextView;
    private Button mMediaScanFinishedButton;
    private TextView mMediaScanPathTextView;
    private ProgressBar mProgressBar;

    protected void onCreate(Bundle bundle) {
        Object[] stringArrayExtra;
        List list = null;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan_animation);
        if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            Fragment slidingPagerFragment = new SlidingPagerFragment(this.mImageIds);
            slidingPagerFragment.setAutoScroll(true);
            beginTransaction.replace(R.id.fragment_navigate, slidingPagerFragment);
            beginTransaction.commit();
        }
        this.mAnimationImageView = (Animation) findViewById(R.id.imageview_mediascan_anim);
        this.mAnimationImageView.setIgnoreFocusChanged(true);
        this.mAnimationImageView.setAnimationResource(R.drawable.xml_imageview_mediascan_animation);
        this.mMediaScanCountTextView = (TextView) findViewById(R.id.textview_mediascan_count);
        this.mMediaScanPathTextView = (TextView) findViewById(R.id.textview_mediascan_scan_path);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progressbar_mediascan_progress);
        this.mProgressBar.setProgress(0);
        this.mMediaScanFinishedButton = (Button) findViewById(R.id.button_mediascan_scan_finished);
        this.mMediaScanFinishedButton.setVisibility(8);
        this.mMediaScanFinishedButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MediaScanAnimationActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        Intent intent = getIntent();
        String str = MediaStorage.GROUP_ID_ALL_LOCAL;
        if (intent != null) {
            stringArrayExtra = intent.getStringArrayExtra(KEY_SCAN_FILES);
            str = intent.getStringExtra(KEY_GROUP_ID);
        } else {
            stringArrayExtra = null;
        }
        this.mIsScanning = true;
        if (stringArrayExtra == null) {
            setStatisticPage(s.PAGE_SCAN_MUSIC_ONE_KEY);
        }
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.STOP_SCAN, new Object[0]));
        b a = b.a();
        com.sds.android.ttpod.framework.modules.a aVar = com.sds.android.ttpod.framework.modules.a.START_SCAN;
        Object[] objArr = new Object[2];
        if (stringArrayExtra != null) {
            list = Arrays.asList(stringArrayExtra);
        }
        objArr[0] = list;
        if (m.a(str)) {
            str = MediaStorage.GROUP_ID_ALL_LOCAL;
        }
        objArr[1] = str;
        a.a(new a(aVar, objArr));
        com.sds.android.ttpod.framework.storage.environment.b.f(true);
        this.mAnimationImageView.a();
        this.mHandler.sendEmptyMessage(0);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, i.a(getClass(), "onScanFinished", Integer.class));
    }

    private void refreshProgress() {
        this.mMediaScanCountTextView.setText(String.valueOf((Integer) b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SCANNED_FILE_COUNT, new Object[0]), Integer.class)));
        this.mMediaScanPathTextView.setText((CharSequence) b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SCANNING_FILE_PATH, new Object[0]), String.class));
        this.mProgressBar.setProgress(((Integer) b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SCAN_PROGRESS, new Object[0]), Integer.class)).intValue());
    }

    public void onScanFinished(Integer num) {
        this.mHandler.removeMessages(0);
        this.mIsScanning = false;
        this.mMediaScanCountTextView.setText(String.valueOf(num));
        this.mMediaScanFinishedButton.setVisibility(0);
        this.mMediaScanPathTextView.setVisibility(8);
        this.mProgressBar.setVisibility(8);
        this.mAnimationImageView.b();
    }

    public void onBackPressed() {
        if (!this.mIsScanning) {
            super.onBackPressed();
        }
    }

    protected void onDestroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
