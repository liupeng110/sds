package com.sds.android.ttpod.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.fragment.RecommendAppFragment;
import com.sds.android.ttpod.framework.base.BaseActivity;

public class RecommendAppActivity extends SlidingClosableActivity {
    private RecommendAppFragment mFragment;
    private a mReceiver;

    private static class a extends BroadcastReceiver {
        private RecommendAppFragment a;

        public a(RecommendAppFragment recommendAppFragment) {
            this.a = recommendAppFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                this.a.onAppInstalled();
            }
        }

        public static IntentFilter a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            return intentFilter;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.framelayout_container);
        setTBSPage("tt_app_recommend");
        getActionBarController().b((int) R.string.recommend_app);
        this.mFragment = new RecommendAppFragment();
        if (p.a()) {
            p.a(findViewById(R.id.container), new com.sds.android.ttpod.b.p.a(this) {
                final /* synthetic */ RecommendAppActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.launchFragment();
                }
            });
        } else {
            launchFragment();
        }
    }

    private void launchFragment() {
        this.mReceiver = new a(this.mFragment);
        registerReceiver(this.mReceiver, a.a());
        getSupportFragmentManager().beginTransaction().add((int) R.id.container, this.mFragment).commit();
    }

    protected void onDestroy() {
        if (this.mReceiver != null) {
            unregisterReceiver(this.mReceiver);
        }
        super.onDestroy();
    }

    public static void start(BaseActivity baseActivity) {
        baseActivity.startActivity(new Intent(baseActivity, RecommendAppActivity.class));
    }
}
