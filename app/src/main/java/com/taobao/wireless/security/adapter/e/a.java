package com.taobao.wireless.security.adapter.e;

import android.content.Context;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.datareport.DataReportJniBridge;

public final class a {
    private JNICLibrary a = JNICLibrary.getInstance();
    private Context b;

    public a(Context context) {
        this.b = context;
    }

    public final int a(boolean z) {
        DataReportJniBridge.initDataReportJniBridge(this.b.getApplicationContext());
        return this.a.initialize(this.b, z);
    }
}
