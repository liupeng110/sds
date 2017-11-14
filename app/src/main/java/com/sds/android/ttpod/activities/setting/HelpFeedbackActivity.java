package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.settings.feedback.FeedbackComposeFragment;
import com.sds.android.ttpod.fragment.settings.feedback.FeedbackListFragment;
import com.sds.android.ttpod.framework.a.b.a;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import java.util.ArrayList;
import java.util.List;

public class HelpFeedbackActivity extends SlidingPagerActivity {
    private static final String HELP_CONTENT_EN_URL = "http://m.dongting.com/help/feedback-en.html";
    private static final String HELP_CONTENT_URL = "http://m.dongting.com/help/feedback.html";
    private static final ArrayList<a> SLIST = new ArrayList<a>(4) {
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPagerContent.setOffscreenPageLimit(3);
        initView();
    }

    private void initView() {
        d.a(this);
        getRootView().setBackgroundResource(17170443);
        this.mPagerTitle.setBackgroundDrawable(getResources().getDrawable(R.drawable.xml_background_tab_feedback));
        this.mPagerTitle.setTextColor(getResources().getColorStateList(R.drawable.xml_tab_text_feedback));
        this.mPagerTitle.setIndicatorColorResource(R.color.tab_indicator_feedback_selected);
        this.mPagerTitle.setUnderlineColorResource(R.color.tab_indicator_feedback_normal);
        this.mPagerTitle.setUnderlineHeight(1);
        this.mPagerTitle.setIndicatorHeight(com.sds.android.ttpod.common.c.a.a(2));
    }

    protected void onBuildActionBar(com.sds.android.ttpod.component.a aVar) {
    }

    protected void onBuildFragmentBinderList(List<e.a> list) {
        Bundle bundle = new Bundle();
        if (getResources().getConfiguration().locale.getCountry().equals("CN") || getResources().getConfiguration().locale.getCountry().equals("TW") || getResources().getConfiguration().locale.getCountry().equals("HK")) {
            bundle.putString(WebFragment.EXTRA_URL, HELP_CONTENT_URL);
        } else {
            bundle.putString(WebFragment.EXTRA_URL, HELP_CONTENT_EN_URL);
        }
        Fragment webFragment = new WebFragment();
        webFragment.setArguments(bundle);
        webFragment.setStatisticPage(s.PAGE_SETTING_QUESTION);
        webFragment.initBundle(String.valueOf(s.PAGE_SETTING_QUESTION.getValue()), null);
        list.add(new e.a(0, (int) R.string.ttpod_helpbook, 0, webFragment));
        webFragment = new FeedbackComposeFragment();
        webFragment.initBundle(String.valueOf(s.PAGE_SETTING_FEEDBACK.getValue()), null);
        list.add(new e.a(1, (int) R.string.feedback_suggestion, 0, webFragment));
        webFragment = new FeedbackListFragment();
        webFragment.initBundle(String.valueOf(s.PAGE_SETTING_MY_FEEDBACK.getValue()), null);
        list.add(new e.a(2, (int) R.string.my_feedback, 0, webFragment));
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        t.a(((a) SLIST.get(i)).a(), ((a) SLIST.get(i)).b());
    }
}
