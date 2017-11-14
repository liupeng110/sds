package com.tencent.wxop.stat;

import android.app.ListActivity;

public class EasyListActivity extends ListActivity {
    protected void onPause() {
        super.onPause();
        f.m(this);
    }

    protected void onResume() {
        super.onResume();
        f.l(this);
    }
}
