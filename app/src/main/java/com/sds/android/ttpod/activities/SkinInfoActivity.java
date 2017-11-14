package com.sds.android.ttpod.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ActionBarActivity;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.skin.b.x;
import com.sds.android.ttpod.framework.modules.skin.j;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

public class SkinInfoActivity extends ActionBarActivity {
    private AlertDialog mAlertDialog;
    private Button mApplyButton;
    private TextView mAuthor;
    private Button mCancelButton;
    private File mDesFile;
    private String mDesSkinPath;
    private TextView mEMail;
    private TextView mName;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ SkinInfoActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.mApplyButton) {
                this.a.applySkinFileAsTTpodSkin();
            } else if (view == this.a.mCancelButton) {
                this.a.finish();
            }
        }
    };
    private x mSkinInfo;
    private String mSrcSkinPath;
    private TextView mVersion;
    private TextView mWebPage;

    public class a extends ClickableSpan {
        final /* synthetic */ SkinInfoActivity a;
        private String b;

        public a(SkinInfoActivity skinInfoActivity, String str) {
            this.a = skinInfoActivity;
            this.b = str;
        }

        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.b));
            try {
                this.a.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                f.a((int) R.string.no_program);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_skin_info_layout);
        this.mName = (TextView) findViewById(R.id.skininfo_name);
        this.mAuthor = (TextView) findViewById(R.id.skininfo_author);
        this.mVersion = (TextView) findViewById(R.id.skininfo_version);
        this.mEMail = (TextView) findViewById(R.id.skininfo_mail);
        this.mWebPage = (TextView) findViewById(R.id.skininfo_page);
        this.mApplyButton = (Button) findViewById(R.id.skininfo_confirm);
        this.mCancelButton = (Button) findViewById(R.id.skininfo_cancel);
        setupDefaultDisplayInfo();
        this.mApplyButton.setClickable(false);
        this.mApplyButton.setOnClickListener(this.mOnClickListener);
        this.mCancelButton.setOnClickListener(this.mOnClickListener);
        loadSkin();
    }

    protected void onStop() {
        super.onStop();
        dismissWaitingDialog();
        finish();
    }

    protected void loadSkin() {
        this.mSrcSkinPath = parseSkinPath();
        String a = v.a(this.mSrcSkinPath, 0);
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN_WITH_PATH, a));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.LOAD_SKIN_WITH_PATH_FINISHED, i.a(getClass(), "onLoadSkinFinished", j.class));
    }

    public void onLoadSkinFinished(j jVar) {
        this.mSkinInfo = jVar.f();
        if (this.mSkinInfo != null) {
            this.mName.setText(this.mSkinInfo.d());
            this.mAuthor.setText(getLabelText(R.string.author, this.mSkinInfo.a()));
            this.mVersion.setText(getLabelText(R.string.version, this.mSkinInfo.b()));
            this.mEMail.setText(getLabelTextSpanable(R.string.email, this.mSkinInfo.e()));
            this.mWebPage.setText(getLabelTextSpanable(R.string.website, this.mSkinInfo.f()));
            this.mApplyButton.setClickable(true);
        }
    }

    private void showWaitingDialog() {
        f.a((Context) this, (int) R.string.loading_theme, true);
    }

    private void dismissWaitingDialog() {
        f.a();
    }

    private void setupDefaultDisplayInfo() {
        this.mAuthor.setText(getLabelText(R.string.author, "TTPod"));
        this.mVersion.setText(getLabelText(R.string.version, "2.0"));
        this.mEMail.setText(getLabelTextSpanable(R.string.email, "support@ttpod.com"));
        this.mWebPage.setText(getLabelTextSpanable(R.string.website, "www.ttpod.com"));
    }

    private String getLabelText(int i, String str) {
        return getText(i) + " : " + str;
    }

    private SpannableStringBuilder getLabelTextSpanable(int i, String str) {
        Object obj = getText(i) + " : " + str;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(obj);
        a aVar = new a(this, str);
        int length = obj.length();
        spannableStringBuilder.setSpan(aVar, length - str.length(), length, 34);
        return spannableStringBuilder;
    }

    private void openPlayerActivity() {
        savePreference();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void copyWithoutOverwriteExistFile(String str) {
        this.mDesSkinPath = getUniqueSkinFilePath(str);
        this.mDesFile = new File(this.mDesSkinPath);
        e.f(e.l(this.mDesSkinPath));
        e.b(this.mDesSkinPath, this.mSrcSkinPath);
    }

    private String getUniqueSkinFilePath(String str) {
        int i = 0;
        String l = e.l(str);
        String k = e.k(str);
        String m = e.m(str);
        File file = new File(str);
        while (file.exists()) {
            i++;
            str = l + File.separator + k + "_" + i + "." + m;
            file = new File(str);
        }
        return str;
    }

    private void copyWithOverwriteExistFile(String str) {
        this.mDesSkinPath = str;
        this.mDesFile = new File(str);
        e.f(e.l(this.mDesSkinPath));
        e.b(this.mDesSkinPath, this.mSrcSkinPath);
    }

    private void savePreference() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_SKIN, this.mDesSkinPath, Integer.valueOf(0)));
    }

    private String parseSkinPath() {
        Intent intent = getIntent();
        String decode = Uri.decode(intent.getDataString());
        if (decode == null || !decode.toLowerCase().endsWith(".tsk")) {
            return null;
        }
        return decode.substring((intent.getScheme() + "://").length());
    }

    private void applySkinFileAsTTpodSkin() {
        String j = e.j(this.mSrcSkinPath);
        String l = e.l(this.mSrcSkinPath);
        String o = com.sds.android.ttpod.framework.a.o();
        final String str = o + File.separator + j;
        this.mDesFile = new File(str);
        if (o.startsWith("/mnt") && !l.startsWith("/mnt")) {
            l = "/mnt" + l;
        }
        if (o.equals(l)) {
            showWaitingDialog();
            this.mDesSkinPath = l + File.separator + j;
            openPlayerActivity();
        } else if (this.mDesFile.exists()) {
            h hVar = new h((Context) this, (int) R.string.theme_overwrite_choose, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ SkinInfoActivity b;

                public void a(h hVar) {
                    this.b.showWaitingDialog();
                    this.b.copyWithOverwriteExistFile(str);
                    this.b.openPlayerActivity();
                }
            }, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ SkinInfoActivity b;

                public void a(h hVar) {
                    this.b.showWaitingDialog();
                    this.b.copyWithoutOverwriteExistFile(str);
                    this.b.openPlayerActivity();
                }
            });
            hVar.setTitle((int) R.string.prompt_title);
            hVar.show();
        } else {
            showWaitingDialog();
            copyWithOverwriteExistFile(str);
            openPlayerActivity();
        }
    }
}
