package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.ttpod.R;

/* DownloadProgressDialog */
public class a extends com.sds.android.ttpod.common.a.a {
    private TextView a;
    private ProgressBar b;

    public a(Context context, com.sds.android.ttpod.common.a.a.a aVar, com.sds.android.ttpod.common.a.a.a aVar2) {
        super(context, R.style.Theme.NoTitle.Dialog);
        a((int) R.string.version_upgrade_hide_dialog, aVar2, (int) R.string.cancel, aVar);
    }

    protected View a(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.ktv_dialog_download_progress, null);
        this.a = (TextView) inflate.findViewById(R.id.text_downloading_progress);
        this.b = (ProgressBar) inflate.findViewById(R.id.downloading_progress_bar);
        setTitle((CharSequence) "正在下载");
        return inflate;
    }

    protected <T> T a() {
        return null;
    }

    public void a(int i, int i2) {
        this.a.setText("已下载" + (i / 1024) + "kb,总共" + (i2 > 0 ? (i2 / 1024) + "kb" : ""));
        this.b.setProgress(i2 > 0 ? (i * 100) / i2 : 0);
    }

    public void show() {
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }
}
