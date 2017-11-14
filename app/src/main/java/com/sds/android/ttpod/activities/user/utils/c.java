package com.sds.android.ttpod.activities.user.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.Fragment;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.a.e;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a;
import java.io.File;
import java.util.Arrays;

/* PickImageHelper */
public class c {
    private static final String a = (a.d() + File.separator + ".temp.jpg");
    private Activity b;
    private int c;
    private int d;
    private String e;
    private int f = 3;

    public c(Activity activity) {
        this.b = activity;
    }

    public void a(int i, CharSequence charSequence, int i2, int i3) {
        final e eVar = new e(this.b, Arrays.asList(new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(0, 0, (int) R.string.userinfo_from_photo_galley), new com.sds.android.ttpod.component.b.a(1, 0, (int) R.string.userinfo_from_photo_camera)}), null, null);
        eVar.b(R.string.cancel, null);
        eVar.setTitle(charSequence);
        final CharSequence charSequence2 = charSequence;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        eVar.a(new b(this) {
            final /* synthetic */ c f;

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                eVar.dismiss();
                switch (aVar.g()) {
                    case 0:
                        this.f.a(charSequence2, i4, i5, i6);
                        return;
                    case 1:
                        this.f.b(charSequence2, i4, i5, i6);
                        return;
                    default:
                        return;
                }
            }
        });
        eVar.show();
    }

    public int a() {
        return this.f;
    }

    public boolean a(CharSequence charSequence, int i, int i2, int i3) {
        try {
            a(new Intent("android.intent.action.PICK").setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*"), charSequence, i, i2, i3);
            this.f = 1;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean a(Fragment fragment, CharSequence charSequence, int i, int i2, int i3) {
        try {
            a(fragment, new Intent("android.intent.action.PICK").setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*"), charSequence, i, i2, i3);
            this.f = 1;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean b(CharSequence charSequence, int i, int i2, int i3) {
        try {
            a(new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", Uri.fromFile(new File(a))), charSequence, i, i2, i3);
            this.f = 2;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean b(Fragment fragment, CharSequence charSequence, int i, int i2, int i3) {
        try {
            a(fragment, new Intent("android.media.action.IMAGE_CAPTURE").putExtra("output", Uri.fromFile(new File(a))), charSequence, i, i2, i3);
            this.f = 2;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void a(Intent intent, CharSequence charSequence, int i, int i2, int i3) {
        this.b.startActivityForResult(Intent.createChooser(intent.putExtra("return-data", false), charSequence), i);
        this.c = i2;
        this.d = i3;
    }

    private void a(Fragment fragment, Intent intent, CharSequence charSequence, int i, int i2, int i3) {
        fragment.startActivityForResult(Intent.createChooser(intent.putExtra("return-data", false), charSequence), i);
        this.c = i2;
        this.d = i3;
    }

    public void a(Uri uri, String str) {
        a(uri, str, this.c, this.d);
    }

    public void a(Fragment fragment, Uri uri, String str) {
        a(fragment, uri, str, this.c, this.d);
    }

    public void a(Uri uri, String str, int i, int i2) {
        this.e = str;
        if (this.f == 2) {
            File file = new File(a);
            Parcelable fromFile = file.exists() ? Uri.fromFile(file) : null;
        } else {
            Object obj = uri;
        }
        if (fromFile == null) {
            f.a((int) R.string.userinfo_can_not_open_image);
            return;
        }
        try {
            this.b.startActivityForResult(new Intent(this.b, CropImageActivity.class).putExtra("input", fromFile).putExtra("output", str).putExtra("width", i).putExtra("height", i2), 3);
        } catch (Throwable e) {
            g.b("PickImageHelper", "无法启动剪切程序。", e);
        }
    }

    public void a(Fragment fragment, Uri uri, String str, int i, int i2) {
        this.e = str;
        if (this.f == 2) {
            File file = new File(a);
            Parcelable fromFile = file.exists() ? Uri.fromFile(file) : null;
        } else {
            Object obj = uri;
        }
        if (fromFile == null) {
            f.a((int) R.string.userinfo_can_not_open_image);
            return;
        }
        try {
            fragment.startActivityForResult(new Intent(fragment.getActivity(), CropImageActivity.class).putExtra("input", fromFile).putExtra("output", str).putExtra("width", i).putExtra("height", i2), 3);
        } catch (Throwable e) {
            g.b("PickImageHelper", "无法启动剪切程序。", e);
        }
    }

    public void a(Bundle bundle) {
        if (bundle != null) {
            bundle.putInt("width", this.c);
            bundle.putInt("height", this.d);
            bundle.putString("path", this.e);
            bundle.putInt("pick_id", this.f);
        }
    }

    public void b(Bundle bundle) {
        if (bundle != null) {
            this.c = bundle.getInt("width", this.c);
            this.d = bundle.getInt("height", this.d);
            this.f = bundle.getInt("pick_id", this.f);
            this.e = bundle.getString("path");
        }
    }
}
