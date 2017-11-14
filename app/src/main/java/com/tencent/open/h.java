package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebChromeClient;

/* ProGuard */
public abstract class h extends Dialog {
    protected a jsBridge;
    @SuppressLint({"NewApi"})
    protected final WebChromeClient mChromeClient = new e(this);

    protected abstract void onConsoleMessage(String str);

    public h(Context context) {
        super(context);
    }

    public h(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.jsBridge = new a();
    }
}
