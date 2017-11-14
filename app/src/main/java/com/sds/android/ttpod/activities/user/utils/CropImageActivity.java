package com.sds.android.ttpod.activities.user.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.widget.CropImageView;
import java.io.File;

public class CropImageActivity extends Activity {
    private CropImageView a;
    private String b;
    private View c;
    private View d;
    private OnClickListener e = new OnClickListener(this) {
        final /* synthetic */ CropImageActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            view.setEnabled(false);
            int id = view.getId();
            if (id == R.id.linearylayout_crop_cancel) {
                this.a.setResult(0);
                this.a.finish();
            } else if (id == R.id.linearylayout_crop_save) {
                if (this.a.a.a(this.a.b)) {
                    this.a.setResult(-1);
                } else {
                    f.a((int) R.string.userinfo_save_croped_image_error);
                    this.a.setResult(0);
                }
                this.a.finish();
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_crop_image);
        this.c = findViewById(R.id.linearylayout_crop_cancel);
        this.c.setOnClickListener(this.e);
        this.d = findViewById(R.id.linearylayout_crop_save);
        this.d.setOnClickListener(this.e);
        Intent intent = getIntent();
        Uri uri = (Uri) intent.getParcelableExtra("input");
        this.b = intent.getStringExtra("output");
        if (TextUtils.isEmpty(this.b)) {
            f.a("没有正确的文件路径，无法启动切图程序");
            return;
        }
        new File(this.b).getParentFile().mkdirs();
        this.a = (CropImageView) findViewById(R.id.cropView);
        this.a.a(intent.getIntExtra("width", 0), intent.getIntExtra("height", 0));
        this.a.setImageURI(uri);
    }
}
