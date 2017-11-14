package com.tencent.wxop.stat;

import android.app.Activity;

public class EasyActivity extends Activity {
    protected void onPause() {
        super.onPause();
        f.m(this);
    }

    protected void onResume() {
        super.onResume();
        f.l(this);
    }
}
