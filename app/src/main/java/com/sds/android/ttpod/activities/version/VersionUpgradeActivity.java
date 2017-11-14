package com.sds.android.ttpod.activities.version;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateData.UpdateState;
import com.sds.android.ttpod.component.d.a.s;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.ab;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import java.util.Map;

public class VersionUpgradeActivity extends BaseActivity {
    public static final String KEY_DATA = "key_data";
    public static final String KEY_IS_SMART = "key_force_update";
    private static final String TYPE_ANZHI_UPDATE = "anzhi";
    private Button mCancelButton;
    private TextView mDescriptionView;
    private s mDialog;
    private boolean mIsSmart;
    private TextView mNameView;
    private Button mNormalButton;
    private Button mSmartButton;
    private TextView mSmartMessageView;
    private VersionUpdateData mVersionUpdateData;

    class AnonymousClass1 implements OnClickListener {
        final /* synthetic */ VersionUpdateData val$appVersion;

        AnonymousClass1(VersionUpdateData versionUpdateData) {
            this.val$appVersion = versionUpdateData;
        }

        public void onClick(View view) {
            w.a("update", VersionUpgradeActivity.this.getStatisticType(this.val$appVersion.getUpgradeType()), "smart");
            if (UpdateState.NEED == this.val$appVersion.getUpdateState()) {
                b.a().a(new a(com.sds.android.ttpod.framework.modules.a.START_SMART_UPGRADE, this.val$appVersion.getAppstoreInstalled()));
            } else if (UpdateState.NO_NEED == this.val$appVersion.getUpdateState()) {
                f.a("没有发现新的版本");
            } else {
                f.a("检测更新失败");
            }
            VersionUpgradeActivity.this.finish();
        }
    }

    class AnonymousClass2 implements OnClickListener {
        final /* synthetic */ VersionUpdateData val$appVersion;

        AnonymousClass2(VersionUpdateData versionUpdateData) {
            this.val$appVersion = versionUpdateData;
        }

        public void onClick(View view) {
            ab.b();
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.START_COMMON_UPGRADE, this.val$appVersion.getUpdateUrl()));
            VersionUpgradeActivity.this.startActivity(new Intent(VersionUpgradeActivity.this, VersionUpgradeProgressActivity.class));
            VersionUpgradeActivity.this.finish();
        }
    }

    private void dismiss() {
    }

    private void downloadApp(String str, String str2) {
    }

    private void initView() {
    }

    private void setNormalButton(VersionUpdateData versionUpdateData) {
    }

    private void setSmartButton(VersionUpdateData versionUpdateData) {
    }

    private void showUpdateProgressDialog() {
    }

    private void updateContent(VersionUpdateData versionUpdateData) {
    }

    private void updateSmartContent(VersionUpdateData versionUpdateData) {
    }

    public void installApp(String str) {
    }

    protected void onLoadCommandMap(Map map) {
    }

    protected void onResume() {
    }

    public void showDownloadProgress(Boolean bool) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    private String getStatisticType(int i) {
        switch (i) {
            case 1000:
                return "360";
            case 1001:
                return VersionUpdateConst.TYPE_WANDOUJIA_UPDATE;
            case 1002:
                return VersionUpdateConst.TYPE_HIAPK_UPDATE;
            case 1003:
                return VersionUpdateConst.TYPE_BAIDU_UPDATE;
            case VersionUpdateConst.UPDATE_TENCENT_TYPE /*1004*/:
                return VersionUpdateConst.TYPE_TENCENT_UPDATE;
            case VersionUpdateConst.UPDATE_ANZHI_TYPE /*1005*/:
                return TYPE_ANZHI_UPDATE;
            default:
                return "update";
        }
    }
}
