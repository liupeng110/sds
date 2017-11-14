package com.sds.android.ttpod.activities.version;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.Map;

public class VersionUpgradeProgressActivity extends BaseActivity {
    public static final String KEY_THIRDPARTY_PROGRESS = "key_thirdparty_progress";
    private static final String TAG = "VersionUpgradeProgressActivity";
    private static final double TOTAL_PROGRESS_SIZE = 100.0d;
    private String mDownloadInfo;
    private long mDownloadedSize = 0;
    private TextView mDownloadingProgress;
    private boolean mIsShowThirdpartyProgress = false;
    private ProgressBar mProgressBar;
    private long mTotalFileSize = 0;

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView((int) R.layout.dialog_upgrade_progress);
        initView();
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            this.mIsShowThirdpartyProgress = getIntent().getExtras().getBoolean(KEY_THIRDPARTY_PROGRESS, false);
        }
        this.mDownloadInfo = getString(R.string.downloading_text);
        this.mDownloadingProgress.setText(String.format(this.mDownloadInfo, new Object[]{TTTextUtils.readableByte(this.mDownloadedSize), TTTextUtils.readableByte(this.mTotalFileSize)}));
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UPDATE_ALL_UPGRADE_PROGRESS_INFO, i.a(cls, "updateProgressInfo", DownloadTaskInfo.class));
        map.put(a.THIRDPARTY_DOWNLOAD_PROGRESS, i.a(cls, "updateProgressChange", Long.class, Long.class));
    }

    public void updateProgressInfo(DownloadTaskInfo downloadTaskInfo) {
        if (!this.mIsShowThirdpartyProgress) {
            g.c(TAG, "updateProgressInfo state = " + downloadTaskInfo.getState());
            switch (downloadTaskInfo.getState().intValue()) {
                case 0:
                case 1:
                case 2:
                    this.mTotalFileSize = (long) downloadTaskInfo.getFileLength().intValue();
                    downloadTaskInfo.setDownloadLength(((Integer) b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_TASK_DOWNLOADED_LENGTH, downloadTaskInfo), Integer.class)).intValue());
                    this.mDownloadedSize = (long) downloadTaskInfo.getDownloadLength();
                    this.mDownloadingProgress.setText(String.format(this.mDownloadInfo, new Object[]{TTTextUtils.readableByte(this.mDownloadedSize), TTTextUtils.readableByte(this.mTotalFileSize)}));
                    this.mProgressBar.setProgress(downloadTaskInfo.getDownloadProgress().intValue());
                    return;
                case 4:
                    if (downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_APP) {
                        com.sds.android.ttpod.b.a.a(BaseApplication.e(), downloadTaskInfo.getSavePath());
                        break;
                    }
                    break;
            }
            finish();
        }
    }

    public void updateProgressChange(Long l, Long l2) {
        if (this.mIsShowThirdpartyProgress) {
            g.c(TAG, "updateProgressInfo thiraparty state receiveDataLen:" + l + "  totalDataLen:" + l2);
            this.mTotalFileSize = l2.longValue();
            this.mDownloadedSize = l.longValue();
            this.mDownloadingProgress.setText(String.format(this.mDownloadInfo, new Object[]{TTTextUtils.readableByte(this.mDownloadedSize), TTTextUtils.readableByte(this.mTotalFileSize)}));
            this.mProgressBar.setProgress((int) (((double) l.longValue()) / (((double) l2.longValue()) / TOTAL_PROGRESS_SIZE)));
        }
    }

    private void initView() {
        Button button;
        Button button2;
        if (j.f()) {
            button = (Button) findViewById(R.id.pdialog_standard_left);
            button2 = (Button) findViewById(R.id.pdialog_standard_right);
        } else {
            button = (Button) findViewById(R.id.pdialog_standard_right);
            button2 = (Button) findViewById(R.id.pdialog_standard_left);
        }
        Button button3 = (Button) findViewById(R.id.pdialog_standard_mid);
        this.mDownloadingProgress = (TextView) findViewById(R.id.version_downloading_progress);
        this.mProgressBar = (ProgressBar) findViewById(R.id.version_downloading_progress_bar);
        ((TextView) findViewById(R.id.pdialog_upgrade_progress_title)).setText(R.string.version_updating_head);
        button2.setText(R.string.version_upgrade_hide_dialog);
        button.setText(17039360);
        button2.setVisibility(0);
        button3.setVisibility(8);
        button.setVisibility(0);
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                VersionUpgradeProgressActivity.this.finish();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.CANCEL_UPGRADE, new Object[0]));
                VersionUpgradeProgressActivity.this.finish();
            }
        });
    }
}
