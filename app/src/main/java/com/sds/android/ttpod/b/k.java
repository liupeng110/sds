package com.sds.android.ttpod.b;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;

/* ForceGCUtils */
public class k {
    public static void a(ViewGroup viewGroup) {
        if (viewGroup != null) {
            synchronized (viewGroup) {
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt instanceof ViewGroup) {
                        a((ViewGroup) childAt);
                    } else if (childAt instanceof ImageView) {
                        a((ImageView) childAt);
                        a(childAt);
                        b((ImageView) childAt);
                    }
                }
            }
        }
    }

    public static void a(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    g.a("ForceGCUtils", "imageview recycle resource bitmap!");
                    imageView.setImageDrawable(null);
                    bitmap.recycle();
                }
            }
        }
    }

    public static void a(View view) {
        if (view != null) {
            Drawable background = view.getBackground();
            if (background instanceof BitmapDrawable) {
                Bitmap bitmap = ((BitmapDrawable) background).getBitmap();
                if (bitmap != null && !bitmap.isRecycled()) {
                    g.a("ForceGCUtils", "imageview recycle background bitmap!");
                    if (j.g()) {
                        view.setBackground(null);
                    } else {
                        view.setBackgroundDrawable(null);
                    }
                    bitmap.recycle();
                }
            }
        }
    }

    public static void b(ImageView imageView) {
        if (imageView != null) {
            Drawable background = imageView.getBackground();
            if (background instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) background;
                animationDrawable.stop();
                for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
                    Drawable frame = animationDrawable.getFrame(i);
                    if (frame instanceof BitmapDrawable) {
                        g.a("ForceGCUtils", "imageview recycle AnimationDrawable bitmap!");
                        if (j.g()) {
                            imageView.setBackground(null);
                        } else {
                            imageView.setBackgroundDrawable(null);
                        }
                        ((BitmapDrawable) frame).getBitmap().recycle();
                    }
                    frame.setCallback(null);
                }
                animationDrawable.setCallback(null);
            }
        }
    }
}
