package com.sds.android.ttpod.activities.ktv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.v;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FragmentLoaderActivity extends SlidingClosableActivity {
    private static final String FOLDER_APK = "apk";
    private static final String FOLDER_DEX = "dexout";
    private AssetManager mAssetManager;
    private ClassLoader mClassLoader;
    private Resources mResources;
    private Theme mTheme;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle((CharSequence) "连接KTV");
        init();
    }

    private void init() {
        try {
            View frameLayout = new FrameLayout(this);
            frameLayout.setLayoutParams(new LayoutParams(-1, -1));
            frameLayout.setId(R.id.layout_primary);
            Drawable a = v.a();
            if (a != null) {
                frameLayout.setBackgroundDrawable(a);
            }
            setContentView(frameLayout);
            File file = new File(getFilesDir(), FOLDER_APK + File.separator + "Ktv.apk");
            File file2 = new File(getFilesDir(), FOLDER_DEX);
            file2.mkdir();
            this.mClassLoader = new DexClassLoader(file.getAbsolutePath(), file2.getAbsolutePath(), null, super.getClassLoader());
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            assetManager.getClass().getMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{file.getAbsolutePath()});
            this.mAssetManager = assetManager;
            Resources resources = super.getResources();
            this.mResources = new Resources(this.mAssetManager, resources.getDisplayMetrics(), resources.getConfiguration());
            this.mTheme = this.mResources.newTheme();
            this.mTheme.setTo(super.getTheme());
            Fragment fragment = (Fragment) this.mClassLoader.loadClass(getIntent().getStringExtra("class")).newInstance();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add((int) R.id.layout_primary, fragment);
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AssetManager getAssets() {
        return this.mAssetManager == null ? super.getAssets() : this.mAssetManager;
    }

    public Resources getResources() {
        return this.mResources == null ? super.getResources() : this.mResources;
    }

    public Theme getTheme() {
        return this.mTheme == null ? super.getTheme() : this.mTheme;
    }

    public ClassLoader getClassLoader() {
        return this.mClassLoader == null ? super.getClassLoader() : this.mClassLoader;
    }

    public static void installPlugin(Context context, String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            File file = new File(context.getFilesDir(), FOLDER_APK);
            file.mkdir();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "Ktv.apk"));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            new File(context.getFilesDir(), FOLDER_DEX).mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startFragmentLoaderActivity(Context context, int i) {
        Intent intent = new Intent();
        intent.setClass(context, FragmentLoaderActivity.class);
        intent.putExtra("path", FOLDER_APK + File.separator + "Ktv.apk");
        intent.putExtra("class", "com.sds.android.ttpod.plugin.ktv.MyCaptureFragment");
        ((Activity) context).startActivityForResult(intent, i);
    }
}
