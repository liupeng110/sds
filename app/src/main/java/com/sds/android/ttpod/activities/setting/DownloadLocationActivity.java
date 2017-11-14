package com.sds.android.ttpod.activities.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.mediascan.FilePickerActivity;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;
import com.sds.android.ttpod.widget.CheckImageView;
import java.io.File;

public class DownloadLocationActivity extends SlidingClosableActivity {
    private static final int REQUEST_CODE_AUTO_DOWNLOAD_DIR = 0;
    private TextView mDownloadPathInfoTextView;
    private String mExtensionCardPath;
    private CheckImageView mExtensionSDCardCheckView;
    private String mStandardCardPath;
    private CheckImageView mStandardSDCardCheckView;
    private View mStoreOtherDirView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_DOWNLOAD_LOCATION);
        setContentView((int) R.layout.activity_download_location_layout);
        this.mStoreOtherDirView = findViewById(R.id.store_other_dir);
        this.mStoreOtherDirView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadLocationActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String Q = b.Q();
                if (j.i()) {
                    Q = a.r();
                }
                this.a.startActivityForResult(new Intent(this.a, FilePickerActivity.class).putExtra(FilePickerActivity.KEY_EXTRA_PATH, Q).putExtra(FilePickerActivity.KEY_EXTRA_CHOICE_MODE, 1).putExtra(FilePickerActivity.KEY_EXTRA_NEW_FOLDER, true).putExtra(FilePickerActivity.KEY_EXTRA_SHOW_FILE_TYPE, 2), 0);
            }
        });
        d.a(this);
        initSDCardPath();
        if (m.a(this.mExtensionCardPath)) {
            initStandardSDCard(this.mStandardCardPath);
        } else {
            findViewById(R.id.extension_sdcard).setVisibility(0);
            initStandardSDCard(this.mStandardCardPath);
            initExtensionSDCard(this.mExtensionCardPath);
        }
        this.mDownloadPathInfoTextView = (TextView) findViewById(R.id.download_path_info);
        this.mDownloadPathInfoTextView.setText(String.format(getResources().getString(R.string.download_path_info), new Object[]{b.Q()}));
    }

    private void initSDCardPath() {
        String b = d.b();
        String d = d.d(this);
        this.mStandardCardPath = b;
        this.mExtensionCardPath = d;
        try {
            if (m.a(d) || b.equals(d) || !checkSDCardPath(b) || !checkSDCardPath(d)) {
                this.mExtensionCardPath = "";
            } else if (j.b() && Environment.isExternalStorageRemovable() && e.d(d.b(), Environment.getExternalStorageDirectory().getCanonicalPath())) {
                this.mStandardCardPath = d.d(this);
                this.mExtensionCardPath = d.b();
            }
        } catch (Exception e) {
            this.mStandardCardPath = d.b();
            this.mExtensionCardPath = "";
            e.printStackTrace();
        }
    }

    private void initStandardSDCard(String str) {
        TextView textView = (TextView) findViewById(R.id.standard_sdcard_storage_status);
        float totalSizeInGB = getTotalSizeInGB(str);
        float availableSizeInGB = getAvailableSizeInGB(str);
        textView.setText(String.format(textView.getText().toString(), new Object[]{Float.valueOf(totalSizeInGB), Float.valueOf(availableSizeInGB)}));
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.standard_sdcard_progressbar);
        progressBar.setMax((int) totalSizeInGB);
        progressBar.setProgress((int) (totalSizeInGB - availableSizeInGB));
        final String str2 = getWritableBasePath(str) + File.separator + "song";
        if (!e.d(str2)) {
            e.f(str2);
        }
        this.mStandardSDCardCheckView = (CheckImageView) findViewById(R.id.standard_sdcard_checkView);
        this.mStandardSDCardCheckView.a((int) R.drawable.img_checkbox_multiselect_unchecked, (int) R.drawable.img_checkbox_multiselect_checked);
        this.mStandardSDCardCheckView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadLocationActivity b;

            public void onClick(View view) {
                if (!this.b.mStandardSDCardCheckView.a()) {
                    this.b.setCheckViewById(R.id.standard_sdcard_checkView);
                    b.g(str2);
                    this.b.mDownloadPathInfoTextView.setText(String.format(this.b.getResources().getString(R.string.download_path_info), new Object[]{str2}));
                    t.a(r.ACTION_SETTING_MUSIC_SAVE_STANDARD_SD, s.PAGE_NONE);
                }
            }
        });
        if (b.Q().equals(str2)) {
            setCheckViewById(R.id.standard_sdcard_checkView);
        }
    }

    private void initExtensionSDCard(String str) {
        TextView textView = (TextView) findViewById(R.id.extension_sdcard_storage_status);
        float totalSizeInGB = getTotalSizeInGB(str);
        float availableSizeInGB = getAvailableSizeInGB(str);
        textView.setText(String.format(textView.getText().toString(), new Object[]{Float.valueOf(totalSizeInGB), Float.valueOf(availableSizeInGB)}));
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.extension_sdcard_progressbar);
        progressBar.setMax((int) totalSizeInGB);
        progressBar.setProgress((int) (totalSizeInGB - availableSizeInGB));
        this.mExtensionSDCardCheckView = (CheckImageView) findViewById(R.id.extension_sdcard_checkView);
        this.mExtensionSDCardCheckView.a((int) R.drawable.img_checkbox_multiselect_unchecked, (int) R.drawable.img_checkbox_multiselect_checked);
        final String str2 = getWritableBasePath(str) + File.separator + "song";
        if (!e.d(str2)) {
            e.f(str2);
        }
        this.mExtensionSDCardCheckView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DownloadLocationActivity b;

            public void onClick(View view) {
                if (!this.b.mExtensionSDCardCheckView.a()) {
                    this.b.setCheckViewById(R.id.extension_sdcard_checkView);
                    try {
                        b.g(str2);
                        this.b.mDownloadPathInfoTextView.setText(String.format(this.b.getResources().getString(R.string.download_path_info), new Object[]{str2}));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (j.i() && b.bv()) {
                        f.a(this.b, (int) R.string.never_show_again, (CharSequence) "通知", this.b.getString(R.string.download_chose_tip_info), null).b(R.string.i_known, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.m>(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void a(com.sds.android.ttpod.component.d.a.m mVar) {
                                b.ah(!mVar.b());
                            }
                        });
                    }
                    t.a(r.ACTION_SETTING_MUSIC_SAVE_EXTERN_SD, s.PAGE_NONE);
                }
            }
        });
        if (b.Q().equals(str2)) {
            setCheckViewById(R.id.extension_sdcard_checkView);
        }
    }

    private String getWritableBasePath(String str) {
        String str2 = str + File.separator + MediaStore.AUTHORITY;
        if (j.i() && this.mExtensionCardPath.equals(str) && !this.mExtensionCardPath.equals(d.b())) {
            str2 = d.a(this, d.a.SECOND_SD_CARD);
        }
        if (!e.d(str2)) {
            e.f(str2);
        }
        return str2;
    }

    private void setCheckViewById(int i) {
        if (i == R.id.standard_sdcard_checkView) {
            trySetCheckView(this.mStandardSDCardCheckView, true);
            trySetCheckView(this.mExtensionSDCardCheckView, false);
        } else if (i == R.id.extension_sdcard_checkView) {
            trySetCheckView(this.mStandardSDCardCheckView, false);
            trySetCheckView(this.mExtensionSDCardCheckView, true);
        } else {
            trySetCheckView(this.mStandardSDCardCheckView, false);
            trySetCheckView(this.mExtensionSDCardCheckView, false);
        }
    }

    void trySetCheckView(CheckImageView checkImageView, boolean z) {
        if (checkImageView != null) {
            checkImageView.setChecked(z);
        }
    }

    private float getTotalSizeInGB(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        return (((float) statFs.getBlockCount()) * ((float) statFs.getBlockSize())) / 1.07374182E9f;
    }

    private float getAvailableSizeInGB(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        return (((float) statFs.getAvailableBlocks()) * ((float) statFs.getBlockSize())) / 1.07374182E9f;
    }

    private boolean checkSDCardPath(String str) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String str2 = getWritableBasePath(str) + File.separator + valueOf.toString();
        e.e(str2);
        if (!e.a(str2)) {
            return false;
        }
        e.h(str2);
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == -1) {
            String stringExtra = intent.getStringExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES);
            if (d.a(stringExtra)) {
                updateDownloadPath(stringExtra);
                return;
            }
            final File file = new File(d.c((Context) this), "song");
            h hVar = new h((Context) this, getString(R.string.change_to_valid_path, new Object[]{file.getAbsolutePath()}), new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ DownloadLocationActivity b;

                public void a(h hVar) {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    this.b.updateDownloadPath(file.getAbsolutePath());
                }
            }, null);
            hVar.setTitle((int) R.string.invalid_download_path);
            hVar.show();
        }
    }

    private void updateDownloadPath(String str) {
        String str2 = getWritableBasePath(this.mStandardCardPath) + File.separator + "song";
        String str3 = getWritableBasePath(this.mExtensionCardPath) + File.separator + "song";
        e.f(str);
        if (!m.a(str) && str.equals(str2)) {
            setCheckViewById(R.id.standard_sdcard_checkView);
        } else if (m.a(str) || !str.equals(str3)) {
            setCheckViewById(-1);
        } else {
            setCheckViewById(R.id.extension_sdcard_checkView);
        }
        b.g(str);
        this.mDownloadPathInfoTextView.setText(String.format(getResources().getString(R.string.download_path_info), new Object[]{str}));
    }
}
