package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;

public class WebSlidingClosableFragment extends SlidingClosableFragment {
    public static final String EXTRA_PIC_URL = "extra_pic_url";
    private WebFragment mWebFragment;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_ONLINE_POST_DETAIL_WEB);
        setTBSPage("tt_web_html");
        trackModule("web_html");
        trackPlaySong("web_html", "web_html", true);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence string;
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.framelayout_container, viewGroup, false);
        Bundle arguments = getArguments();
        final String string2 = arguments.getString(WebFragment.EXTRA_TITLE);
        final String string3 = arguments.getString(WebFragment.EXTRA_URL);
        final String string4 = arguments.getString(EXTRA_PIC_URL);
        a actionBarController = getActionBarController();
        if (m.a(string2)) {
            string = getString(R.string.app_name);
        } else {
            Object obj = string2;
        }
        actionBarController.a(string);
        this.mWebFragment = (WebFragment) Fragment.instantiate(getActivity(), WebFragment.class.getName(), arguments);
        a.a a = getActionBarController().a(null);
        v.a(a, (int) R.string.icon_share_action_bar, ThemeElement.TOP_BAR_TEXT);
        a.a(new b(this) {
            final /* synthetic */ WebSlidingClosableFragment d;

            public void a(a.a aVar) {
                String str = string4 == null ? "http://lib.ttdtweb.com/favicon.png" : string4;
                f.a(this.d.getActivity(), this.d.buildShareInfo(string2, string3, str));
                this.d.doStatisticShare(str);
            }
        });
        this.mWebFragment.setOnReceiveTitleListener(new WebFragment.a(this) {
            final /* synthetic */ WebSlidingClosableFragment a;

            {
                this.a = r1;
            }

            public void a(String str) {
                if (!m.a(str)) {
                    this.a.getActionBarController().a((CharSequence) str);
                }
            }
        });
        getChildFragmentManager().beginTransaction().replace(R.id.container, this.mWebFragment).commitAllowingStateLoss();
        updateAlibabaProperty("name", string2);
        return inflate;
    }

    private void doStatisticShare(String str) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_ACTION_BAR_SHARE.getValue(), 0, s.PAGE_SHARE_DIALOG.getValue());
        sUserEvent.append("url", str);
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    protected void onBackPressed() {
        if (this.mWebFragment == null || !this.mWebFragment.canBackward()) {
            super.onBackPressed();
        } else {
            this.mWebFragment.goBack();
        }
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void onTitleClicked() {
        super.onBackPressed();
    }

    private com.sds.android.ttpod.common.b.a.a buildShareInfo(String str, String str2, String str3) {
        com.sds.android.ttpod.common.b.a.a aVar = new com.sds.android.ttpod.common.b.a.a();
        aVar.a(com.sds.android.ttpod.common.b.a.a.a.THIRDPARTY);
        aVar.f(getString(R.string.share));
        aVar.d(str3);
        aVar.i(str2);
        aVar.e(str);
        return aVar;
    }

    public static WebSlidingClosableFragment instantiate(String str, String str2, String str3, boolean z, boolean z2) {
        WebSlidingClosableFragment webSlidingClosableFragment = new WebSlidingClosableFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WebFragment.EXTRA_URL, str);
        bundle.putString(WebFragment.EXTRA_TITLE, str2);
        bundle.putBoolean(WebFragment.EXTRA_HINT_BANNER_SHOW, z2);
        bundle.putBoolean(WebFragment.EXTRA_IS_SHOW_PLAY_CONTROL_BAR, z);
        bundle.putString(EXTRA_PIC_URL, str3);
        webSlidingClosableFragment.setArguments(bundle);
        return webSlidingClosableFragment;
    }

    public static WebSlidingClosableFragment instantiate(String str, String str2, String str3, boolean z) {
        return instantiate(str, str2, str3, z, false);
    }

    public static WebSlidingClosableFragment instantiate(Bundle bundle) {
        WebSlidingClosableFragment webSlidingClosableFragment = new WebSlidingClosableFragment();
        webSlidingClosableFragment.setArguments(bundle);
        return webSlidingClosableFragment;
    }
}
