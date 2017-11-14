package com.ttfm.android.sdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import com.sds.android.sdk.core.a.b.a;
import com.sds.android.ttpod.R;
import com.ttfm.android.sdk.cache.ImageCacheHelper;

public class TTFMImageUtils {
    public static final float Large_Scale = 0.6f;
    public static final float Middle_Scale = 0.5f;
    public static final float Mini_Scale = 0.2f;
    public static final float Small_Scale = 0.2f;

    public static String getImageUrl(String str, float f, int i) {
        try {
            CharSequence substring = str.substring(str.lastIndexOf("/") + 1);
            String str2 = "";
            int i2 = (int) (((float) i) * f);
            if (i2 <= 180) {
                str2 = "180";
            } else if (i2 > 180 && i2 <= 240) {
                str2 = "240";
            } else if (i2 > 240 && i2 <= 360) {
                str2 = "360";
            } else if (i2 > 360 && i2 <= 480) {
                str2 = "480";
            } else if (i2 <= 480 || i2 > 720) {
                str2 = "720";
            } else {
                str2 = "720";
            }
            str = str.replace(substring, str2 + "/" + substring);
        } catch (Exception e) {
        }
        Log.i("ImageUrl", str);
        return str;
    }

    public static String getImageUrl(String str, int i) {
        try {
            CharSequence substring = str.substring(str.lastIndexOf("/") + 1);
            String str2 = "";
            if (i <= 180) {
                str2 = "180";
            } else if (i > 180 && i <= 240) {
                str2 = "240";
            } else if (i > 240 && i <= 360) {
                str2 = "360";
            } else if (i > 360 && i <= 480) {
                str2 = "480";
            } else if (i <= 480 || i > 720) {
                str2 = "720";
            } else {
                str2 = "720";
            }
            str = str.replace(substring, str2 + "/" + substring);
        } catch (Exception e) {
        }
        Log.i("ImageUrl", str);
        return str;
    }

    public static void setImageView(final ImageView imageView, String str, float f, Bitmap bitmap) {
        if (imageView != null && str != null) {
            ImageCacheHelper imageCacheHelper = TTFMEnvironmentUtils.getImageCacheHelper();
            if (str.length() > 0 && imageCacheHelper != null) {
                String imageUrl = getImageUrl(str, f, TTFMEnvironmentUtils.getmScreenW());
                Bitmap bitmapFromMemCache = imageCacheHelper.getBitmapFromMemCache(imageUrl, null, 0, 0);
                if (bitmapFromMemCache == null) {
                    bitmapFromMemCache = imageCacheHelper.getBitmapFormDiskCache(imageUrl, null, 0, 0);
                }
                imageView.setTag(R.id.view_bind_url, imageUrl);
                if (bitmapFromMemCache != null) {
                    imageView.setImageBitmap(bitmapFromMemCache);
                    return;
                }
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
                imageCacheHelper.loadImageAsync(imageUrl, 0, 0, new a() {
                    public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                        if (imageView != null && str.equals((String) imageView.getTag(R.id.view_bind_url))) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            } else if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public static void setImageView(final ImageView imageView, String str, int i, Bitmap bitmap) {
        if (imageView != null && str != null) {
            ImageCacheHelper imageCacheHelper = TTFMEnvironmentUtils.getImageCacheHelper();
            if (str.length() > 0 && imageCacheHelper != null) {
                String imageUrl = getImageUrl(str, i);
                Bitmap bitmapFromMemCache = imageCacheHelper.getBitmapFromMemCache(imageUrl, null, 0, 0);
                if (bitmapFromMemCache == null) {
                    bitmapFromMemCache = imageCacheHelper.getBitmapFormDiskCache(imageUrl, null, 0, 0);
                }
                imageView.setTag(R.id.view_bind_url, imageUrl);
                if (bitmapFromMemCache != null) {
                    imageView.setImageBitmap(bitmapFromMemCache);
                    return;
                }
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
                imageCacheHelper.loadImageAsync(imageUrl, 0, 0, new a() {
                    public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                        if (imageView != null && str.equals((String) imageView.getTag(R.id.view_bind_url))) {
                            imageView.setImageBitmap(bitmap);
                        }
                    }
                });
            } else if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public static void setImageView(final ImageView imageView, String str, float f, int i) {
        if (imageView != null && str != null) {
            ImageCacheHelper imageCacheHelper = TTFMEnvironmentUtils.getImageCacheHelper();
            if (str.length() <= 0 || imageCacheHelper == null) {
                imageView.setImageResource(i);
                return;
            }
            String imageUrl = getImageUrl(str, f, TTFMEnvironmentUtils.getmScreenW());
            Bitmap bitmapFromMemCache = imageCacheHelper.getBitmapFromMemCache(imageUrl, null, 0, 0);
            if (bitmapFromMemCache == null) {
                bitmapFromMemCache = imageCacheHelper.getBitmapFormDiskCache(imageUrl, null, 0, 0);
            }
            imageView.setTag(R.id.view_bind_url, imageUrl);
            if (bitmapFromMemCache != null) {
                imageView.setImageBitmap(bitmapFromMemCache);
                return;
            }
            imageView.setImageResource(i);
            imageCacheHelper.loadImageAsync(imageUrl, 0, 0, new a() {
                public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                    if (imageView != null && str.equals((String) imageView.getTag(R.id.view_bind_url))) {
                        imageView.setImageBitmap(bitmap);
                    }
                }
            });
        }
    }

    public static void setLargeImageView(ImageView imageView, String str, Bitmap bitmap) {
        setImageView(imageView, str, (float) Large_Scale, bitmap);
    }

    public static void setMiddleImageView(ImageView imageView, String str, Bitmap bitmap) {
        setImageView(imageView, str, (float) Middle_Scale, bitmap);
    }

    public static void setSmallImageView(ImageView imageView, String str, Bitmap bitmap) {
        setImageView(imageView, str, 0.2f, bitmap);
    }

    public static void setMiniImageView(ImageView imageView, String str, Bitmap bitmap) {
        setImageView(imageView, str, 0.2f, bitmap);
    }

    public static void setImageView(final ImageView imageView, String str) {
        if (imageView != null && str != null) {
            ImageCacheHelper imageCacheHelper = TTFMEnvironmentUtils.getImageCacheHelper();
            if (str.length() > 0 && imageCacheHelper != null) {
                Bitmap bitmapFromMemCache = imageCacheHelper.getBitmapFromMemCache(str, null, 0, 0);
                if (bitmapFromMemCache == null) {
                    bitmapFromMemCache = imageCacheHelper.getBitmapFormDiskCache(str, null, 0, 0);
                }
                imageView.setTag(R.id.view_bind_url, str);
                if (bitmapFromMemCache != null) {
                    imageView.setImageBitmap(bitmapFromMemCache);
                } else {
                    imageCacheHelper.loadImageAsync(str, 0, 0, new a() {
                        public void imageLoaded(String str, int i, int i2, Bitmap bitmap) {
                            if (imageView != null && str.equals((String) imageView.getTag(R.id.view_bind_url))) {
                                imageView.setImageBitmap(bitmap);
                            }
                        }
                    });
                }
            }
        }
    }

    public static int Dp2Px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + Middle_Scale);
    }

    public static int Px2Dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + Middle_Scale);
    }
}
