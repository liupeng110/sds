package com.sds.android.ttpod.activities.unicomflow;

import android.os.Bundle;
import android.webkit.WebView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.framework.a.b.aa;

public class FaqActivity extends SlidingClosableActivity {
    private WebView mWebView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((CharSequence) "常见问题");
        setContentView((int) R.layout.activity_unicom_flow_faq_detail);
        this.mWebView = (WebView) findViewById(R.id.webview_detail);
        this.mWebView.loadUrl("http://m.dongting.com/help/unicom.html");
        setTBSPage("tt_unicom_faq");
        aa.K();
    }
}
