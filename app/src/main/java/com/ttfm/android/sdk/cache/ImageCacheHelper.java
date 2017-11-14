package com.ttfm.android.sdk.cache;

import android.graphics.Bitmap;
import com.sds.android.sdk.core.a.b;
import com.sds.android.sdk.core.a.b.a;
import com.sds.android.ttpod.framework.a.g;

public class ImageCacheHelper {
    private static b mImageCache = null;
    private static ImageCacheHelper mImageCacheHelper = null;

    public static ImageCacheHelper getInstance() {
        if (mImageCacheHelper == null) {
            mImageCacheHelper = new ImageCacheHelper();
        }
        return mImageCacheHelper;
    }

    public void loadImageAsync(String str, int i, int i2, a aVar) {
        if (mImageCache == null) {
            mImageCache = g.a();
        }
        mImageCache.a(str, i, i2, aVar);
    }

    public Bitmap getBitmapFromMemCache(String str, String str2, int i, int i2) {
        if (mImageCache == null) {
            mImageCache = g.a();
        }
        return mImageCache.a(str, str2, i, i2);
    }

    public Bitmap getBitmapFormDiskCache(String str, String str2, int i, int i2) {
        if (mImageCache == null) {
            mImageCache = g.a();
        }
        return mImageCache.b(str, str2, i, i2);
    }

    public void close() {
        if (mImageCache != null) {
            mImageCache.a();
            mImageCache = null;
        }
    }
}
