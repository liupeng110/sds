package com.sds.android.ttpod.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.unicomflow.c;
import com.sds.android.ttpod.b.u;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.TTWebView;
import com.sds.android.ttpod.widget.i;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Map;

public class WebFragment extends BaseFragment implements com.sds.android.ttpod.widget.i.a {
    public static final String EXTRA_ENABLE_SLIDING_CLOSABLE = "enable_sliding_closable";
    public static final String EXTRA_HINT_BANNER_SHOW = "extra_hint_banner_show";
    public static final String EXTRA_IS_SHOW_PLAY_CONTROL_BAR = "extra_is_show_play_control_bar";
    public static final String EXTRA_PAGE = "extra_page";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_URL = "extra_url";
    private static final int MAX_PROGRESS = 100;
    private static final String TAG = "WebFragment";
    private DownloadListener mDownloadListener = new DownloadListener(this) {
        final /* synthetic */ WebFragment a;

        {
            this.a = r1;
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            if (this.a.generateDownloadTaskInfo(str) != null) {
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, r0));
            }
        }
    };
    private FrameLayout mFrameLayoutWebView;
    private NetworkLoadView mLoadingView;
    private a mOnReceiveTitleListener;
    private ProgressBar mProgressBar;
    private u mStartAction;
    private i mTTWebViewClient;
    private c mWebCallbackManager;
    private WebChromeClient mWebChromeClient = new WebChromeClient(this) {
        final /* synthetic */ WebFragment a;

        {
            this.a = r1;
        }

        public void onProgressChanged(WebView webView, int i) {
            if (this.a.mProgressBar != null) {
                this.a.mProgressBar.setProgress(i);
                if (i == 100) {
                    this.a.mProgressBar.setVisibility(8);
                }
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (this.a.mOnReceiveTitleListener != null) {
                this.a.mOnReceiveTitleListener.a(str);
            }
        }
    };
    private TTWebView mWebView;

    public interface a {
        void a(String str);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.layout_web_page, viewGroup, false);
        initView(layoutInflater, viewGroup, viewGroup2);
        return viewGroup2;
    }

    private void initView(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewGroup viewGroup2) {
        this.mFrameLayoutWebView = (FrameLayout) viewGroup2.findViewById(R.id.web_view);
        this.mWebView = new TTWebView(getActivity());
        this.mStartAction = new u((BaseActivity) getActivity());
        this.mWebCallbackManager = new c(this.mWebView, this.mStartAction);
        this.mFrameLayoutWebView.addView(this.mWebView, new LayoutParams(-1, -1));
        this.mProgressBar = (ProgressBar) viewGroup2.findViewById(R.id.web_view_load_progress);
        this.mProgressBar.setProgress(0);
        this.mProgressBar.setMax(100);
        this.mLoadingView = (NetworkLoadView) layoutInflater.inflate(R.layout.network_loadview, viewGroup, false);
        this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
        viewGroup2.addView(this.mLoadingView, new ViewGroup.LayoutParams(-1, -1));
        this.mTTWebViewClient = new i(this, getActivity(), this, viewGroup2.findViewById(R.id.error_Page_layout)) {
            final /* synthetic */ WebFragment a;

            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
                if (c.a() && com.sds.android.sdk.lib.a.a.c()) {
                    httpAuthHandler.proceed(com.sds.android.ttpod.framework.modules.h.c.USERNAME, com.sds.android.ttpod.framework.modules.h.c.PASSWORD);
                } else {
                    super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
                }
            }
        };
        this.mWebView.setWebChromeClient(this.mWebChromeClient);
        this.mWebView.setWebViewClient(this.mTTWebViewClient);
        this.mWebView.setDownloadListener(this.mDownloadListener);
        Bundle arguments = getArguments();
        String string = arguments.getString(EXTRA_URL);
        boolean z = arguments.getBoolean(EXTRA_IS_SHOW_PLAY_CONTROL_BAR, false);
        if (m.a(string)) {
            throw new IllegalStateException("Url CAN NOT be null");
        }
        this.mWebView.loadUrl(string);
        if (z) {
            ((LayoutParams) this.mWebView.getLayoutParams()).bottomMargin = (int) getResources().getDimension(R.dimen.playcontrol_bar_height);
        }
        ((TextView) viewGroup2.findViewById(R.id.hint_2)).setText(getString(R.string.online_search_hint_source, string));
        viewGroup2.findViewById(R.id.hint_banner).setVisibility(arguments.getBoolean(EXTRA_HINT_BANNER_SHOW, false) ? 0 : 8);
        com.sds.android.ttpod.activities.unicomflow.b.a(getActivity());
        e.b(getActivity());
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_MEDIA_CHANGED, com.sds.android.sdk.lib.util.i.a(cls, "playMediaChanged", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, com.sds.android.sdk.lib.util.i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_UNICOM_FLOW_STATUS, com.sds.android.sdk.lib.util.i.a(cls, "updateFlowStatus", Boolean.class));
    }

    public void updateFlowStatus(Boolean bool) {
        com.sds.android.ttpod.activities.unicomflow.b.a(getActivity());
    }

    public void playMediaChanged() {
        this.mWebCallbackManager.a(com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).n().toString());
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mWebCallbackManager.a(playStatus.toString());
    }

    public void onBackPressed() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private DownloadTaskInfo generateDownloadTaskInfo(String str) {
        if (m.a(str)) {
            g.a(TAG, "url非法 url=" + str);
            return null;
        }
        String j = com.sds.android.sdk.lib.util.e.j(str);
        String m = com.sds.android.sdk.lib.util.e.m(j);
        if (m.equals("tsk")) {
            m = "tsk/";
        } else {
            m = MimeTypeMap.getSingleton().getMimeTypeFromExtension(m);
        }
        if (m == null) {
            g.a(TAG, "不支持的类型 url=" + str);
            return null;
        }
        Integer valueOf = Integer.valueOf(-1);
        if (m.startsWith("tsk/")) {
            m = com.sds.android.ttpod.framework.a.o() + File.separator + j;
            valueOf = DownloadTaskInfo.TYPE_SKIN;
        } else if (m.startsWith("audio/")) {
            m = com.sds.android.ttpod.framework.storage.environment.b.Q() + File.separator + j;
            valueOf = DownloadTaskInfo.TYPE_AUDIO;
        } else if (m.startsWith("application/")) {
            m = com.sds.android.ttpod.framework.a.x() + File.separator + j;
            valueOf = DownloadTaskInfo.TYPE_APP;
        } else if (m.startsWith("video/")) {
            m = com.sds.android.ttpod.framework.a.z() + File.separator + j;
            valueOf = DownloadTaskInfo.TYPE_VIDEO;
        } else {
            m = null;
        }
        g.a(TAG, "download url=" + str + ",savePath=" + m + ",downloadType=" + valueOf);
        if (m.a(m) || valueOf.intValue() < 0) {
            return null;
        }
        return com.sds.android.ttpod.framework.a.e.a(str, m, Long.valueOf(0), j, valueOf, Boolean.valueOf(true), "skin");
    }

    public void onDestroyView() {
        this.mWebCallbackManager.b();
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mFrameLayoutWebView != null) {
            this.mFrameLayoutWebView.removeAllViews();
        }
        if (this.mTTWebViewClient != null) {
            this.mTTWebViewClient.a();
            this.mTTWebViewClient.a(null);
        }
        if (this.mWebView != null) {
            this.mWebView.stopLoading();
            this.mWebView.removeAllViews();
            this.mWebView.setWebChromeClient(null);
            this.mWebView.setWebViewClient(null);
            this.mWebView.setDownloadListener(null);
            this.mWebCallbackManager.c();
        }
        if (this.mWebView != null) {
            this.mWebView.clearCache(true);
            this.mWebView.destroy();
        }
    }

    private boolean handleIntent(String str) {
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            parseUri.addCategory("android.intent.category.BROWSABLE");
            parseUri.setComponent(null);
            parseUri.putExtra("com.android.browser.application_id", getActivity().getPackageName());
            try {
                startActivity(parseUri);
                return true;
            } catch (ActivityNotFoundException e) {
                return false;
            }
        } catch (URISyntaxException e2) {
            return false;
        }
    }

    private boolean filterUrlScheme(String str) {
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return false;
        }
        return true;
    }

    public boolean onOverrideUrlLoadingEvent(WebView webView, String str) {
        if (!filterUrlScheme(str)) {
            return false;
        }
        handleIntent(str);
        return true;
    }

    public void onPageStartedEvent(WebView webView, String str, Bitmap bitmap) {
    }

    public void onPageFinishedEvent(WebView webView, String str) {
        if (this.mLoadingView != null) {
            this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
        }
    }

    public boolean onReceivedErrorEvent(WebView webView, int i, String str, String str2) {
        return false;
    }

    public void onReloadEvent() {
        this.mWebView.reload();
    }

    public void onLoadResource(WebView webView, String str) {
    }

    public void onHttpRequestedErrorEvent(int i, String str, String str2) {
    }

    public boolean canBackward() {
        return this.mWebView.canGoBack();
    }

    public void goBack() {
        this.mWebView.goBack();
    }

    public void setOnReceiveTitleListener(a aVar) {
        this.mOnReceiveTitleListener = aVar;
    }
}
