package com.sds.android.ttpod.activities.mediascan.setting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.c;
import com.sds.android.ttpod.framework.a.b.s;

public class MediaScanSettingActivity extends SlidingClosableActivity {
    private static final int ID_AUTO_SCAN_FOLDER = 4;
    private static final int ID_EXCLUDE_60 = 0;
    private static final int ID_EXCLUDE_FOLDER = 3;
    private static final int ID_EXCLUDE_FORMATS = 1;
    private static final int ID_EXCLUDE_HIDE_FOLDER = 2;
    private static final int REQUEST_CODE_AUTO_SCAN = 0;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ MediaScanSettingActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            switch (aVar.g()) {
                case 0:
                    com.sds.android.ttpod.framework.storage.environment.b.c(((Checkable) aVar).isChecked());
                    return;
                case 1:
                    com.sds.android.ttpod.framework.storage.environment.b.d(((Checkable) aVar).isChecked());
                    return;
                case 2:
                    com.sds.android.ttpod.framework.storage.environment.b.e(((Checkable) aVar).isChecked());
                    return;
                case 3:
                    this.a.startActivity(new Intent(this.a, MediaScanExcludeActivity.class));
                    return;
                case 4:
                    this.a.startActivityForResult(new Intent(this.a, MediaScanAutoScanActivity.class), 0);
                    return;
                default:
                    return;
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan_setting);
        setStatisticPage(s.PAGE_SCAN_MUSIC_SETTING);
        setTitle((int) R.string.mediascan_setting);
        getActionBarController().d();
        ((LinearLayout) findViewById(R.id.setting_card_container_main)).addView(buildCommonCard().e());
    }

    private c buildCommonCard() {
        com.sds.android.ttpod.activities.setting.c[] cVarArr = new com.sds.android.ttpod.activities.setting.c[5];
        cVarArr[0] = new com.sds.android.ttpod.activities.setting.a(0, 0, R.string.mediascan_setting_exclude_60, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.h());
        cVarArr[1] = new com.sds.android.ttpod.activities.setting.a(1, 0, R.string.mediascan_setting_exclude_formats, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.i());
        cVarArr[2] = new com.sds.android.ttpod.activities.setting.a(2, 0, R.string.mediascan_setting_exclude_hidden_folders, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.j());
        cVarArr[3] = new com.sds.android.ttpod.activities.setting.c(3, 0, (int) R.string.mediascan_setting_exclude_folders, 0, (int) R.drawable.img_setting_right_arrow);
        cVarArr[4] = new com.sds.android.ttpod.activities.setting.c(4, 0, (int) R.string.mediascan_setting_auto_scan_foders, 0, (int) R.drawable.img_setting_right_arrow);
        c bVar = new com.sds.android.ttpod.activities.setting.b(this, cVarArr, R.string.mediascan_setting_auto_scan_start, this.mOnItemClickListener);
        bVar.a(false);
        return bVar;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 0:
                if (i2 == -1) {
                    setResult(-1, null);
                    finish();
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void onActionBarBackPressed() {
        finish();
    }
}
