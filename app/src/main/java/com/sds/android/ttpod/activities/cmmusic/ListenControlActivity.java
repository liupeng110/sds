package com.sds.android.ttpod.activities.cmmusic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.cmmusic.fragment.AdSeatContentFragment;
import com.sds.android.ttpod.cmmusic.fragment.PersonalListenControlFragment;
import com.sds.android.ttpod.cmmusic.fragment.SearchListenFragment;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;

public class ListenControlActivity extends SlidingClosableActivity {
    private FragmentManager mFragmentManager;
    private int mPageCode;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.cmmusic_listen_control_acitvity);
        setTitle((int) R.string.cailing);
        initActionBar();
        viewInit();
    }

    private void initActionBar() {
        a actionBarController = getActionBarController();
        actionBarController.d();
        actionBarController.c(true);
        actionBarController.d((int) R.drawable.cmmusic_img_mine).a(new b(this) {
            final /* synthetic */ ListenControlActivity a;

            {
                this.a = r1;
            }

            public void a(a.a aVar) {
                if (this.a.mPageCode != s.PAGE_CMMUSIC_PERSONAL_CODE.getValue()) {
                    new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_PERSIONAL.getValue(), this.a.mPageCode, s.PAGE_CMMUSIC_PERSONAL_CODE.getValue()).post();
                    this.a.mPageCode = s.PAGE_CMMUSIC_PERSONAL_CODE.getValue();
                }
                this.a.mFragmentManager.beginTransaction().replace(R.id.layout_controlpage, new PersonalListenControlFragment()).commit();
            }
        });
        actionBarController.d((int) R.drawable.cmmusic_search).a(new b(this) {
            final /* synthetic */ ListenControlActivity a;

            {
                this.a = r1;
            }

            public void a(a.a aVar) {
                if (this.a.mPageCode != s.PAGE_CMMUSIC_SEARCH_CODE.getValue()) {
                    new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_SEARCH.getValue(), this.a.mPageCode, s.PAGE_CMMUSIC_SEARCH_CODE.getValue()).post();
                    this.a.mPageCode = s.PAGE_CMMUSIC_SEARCH_CODE.getValue();
                }
                this.a.mFragmentManager.beginTransaction().replace(R.id.layout_controlpage, new SearchListenFragment()).commit();
            }
        });
    }

    private void viewInit() {
        this.mFragmentManager = getSupportFragmentManager();
        try {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("pagename");
            if (string.equals("SearchPage")) {
                this.mPageCode = s.PAGE_CMMUSIC_SEARCH_CODE.getValue();
                runFragment(new SearchListenFragment());
            } else if (string.equals("PersionalListenControl")) {
                this.mPageCode = s.PAGE_CMMUSIC_PERSONAL_CODE.getValue();
                runFragment(new PersonalListenControlFragment());
            } else if (string.equals("AdSeatContent")) {
                this.mPageCode = s.PAGE_CMMUSIC_AD_SEAT_CODE.getValue();
                String string2 = extras.getString("href");
                Fragment adSeatContentFragment = new AdSeatContentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("href", string2);
                adSeatContentFragment.setArguments(bundle);
                runFragment(adSeatContentFragment);
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runFragment(Fragment fragment) {
        this.mFragmentManager.beginTransaction().add((int) R.id.layout_controlpage, fragment).commit();
    }
}
