package com.sds.android.ttpod.framework.modules.skin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.c;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.d.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.a.b;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* SkinReader */
public class p {
    protected a b = null;

    protected boolean a(InputStream inputStream) {
        boolean z = this.b == null;
        if (this.b == null) {
            this.b = new b();
        }
        try {
            this.b.a(inputStream, z);
        } catch (Exception e) {
            b(inputStream);
        }
        if (this.b == null || !this.b.a()) {
            return false;
        }
        return true;
    }

    protected boolean e(String str) {
        if (this.b == null) {
            this.b = new b();
        }
        try {
            this.b.a(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.b != null && this.b.a();
    }

    private void b(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                g.c(o.TAG, "BaseCreator.openSkinPackInputStream crate unPacker failed");
            }
        }
    }

    public void j() {
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected BufferedReader k() {
        byte[] l = l();
        if (l != null) {
            return new BufferedReader(new c(new ByteArrayInputStream(l)));
        }
        return null;
    }

    protected byte[] l() {
        byte[] bArr;
        IOException e;
        String[] a = a(File.separatorChar + "skin");
        try {
            int length = a.length;
            bArr = null;
            int i = 0;
            while (i < length) {
                try {
                    bArr = this.b.b(a[i]);
                    if (bArr != null) {
                        break;
                    }
                    i++;
                } catch (IOException e2) {
                    e = e2;
                }
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            bArr = null;
            e = iOException;
            e.printStackTrace();
            return bArr;
        }
        return bArr;
    }

    private String[] a(String str) {
        String h = com.sds.android.ttpod.common.c.a.h();
        return new String[]{str + com.sds.android.ttpod.common.c.a.d() + "x" + com.sds.android.ttpod.common.c.a.e() + ".xml", str + com.sds.android.ttpod.common.c.a.g() + ".xml", str + h + ".xml", File.separatorChar + h + File.separatorChar + "skin" + ".xml", File.separatorChar + "skin" + ".xml"};
    }

    protected InputStream a(int i, String str) {
        Context e = BaseApplication.e();
        switch (i) {
            case 1:
                return a(e.getAssets(), str);
            case 2:
                return a(e, str);
            default:
                return b(str);
        }
    }

    private InputStream a(Context context, String str) {
        Resources resourcesForApplication;
        InputStream inputStream = null;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                resourcesForApplication = packageManager.getResourcesForApplication(str);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            resourcesForApplication = null;
        }
        if (resourcesForApplication != null) {
            inputStream = a(resourcesForApplication.getAssets(), "skin/skin.tsk");
        }
        return inputStream;
    }

    private InputStream a(AssetManager assetManager, String str) {
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(str, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private InputStream b(String str) {
        try {
            return new com.sds.android.ttpod.framework.modules.skin.d.a.c(str, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
