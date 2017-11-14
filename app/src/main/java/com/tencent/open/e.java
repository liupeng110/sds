package com.tencent.open;

import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

/* ProGuard */
class e extends WebChromeClient {
    final /* synthetic */ h a;

    e(h hVar) {
        this.a = hVar;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.i("WebConsole", consoleMessage.message() + " -- From  111 line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
        if (VERSION.SDK_INT > 7) {
            this.a.onConsoleMessage(consoleMessage == null ? "" : consoleMessage.message());
        }
        return true;
    }

    public void onConsoleMessage(String str, int i, String str2) {
        Log.i("WebConsole", str + " -- From 222 line " + i + " of " + str2);
        if (VERSION.SDK_INT == 7) {
            this.a.onConsoleMessage(str);
        }
    }
}
