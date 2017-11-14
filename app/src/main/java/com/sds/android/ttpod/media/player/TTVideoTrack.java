package com.sds.android.ttpod.media.player;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.Surface;
import android.view.Surface.OutOfResourcesException;
import com.sds.android.sdk.lib.util.g;

public class TTVideoTrack {
    private static final String TAG = "TTVideoTrack";
    private Bitmap mBitmap;
    private Rect mDst;
    private int mHeightBitmap;
    private int mScreenHeight;
    private int mScreenWidth;
    private Surface mSurface;
    private int mWidthBitmap;

    public TTVideoTrack() {
        this.mWidthBitmap = 0;
        this.mHeightBitmap = 0;
        this.mScreenWidth = 0;
        this.mScreenHeight = 0;
        this.mDst = null;
        this.mSurface = null;
        this.mBitmap = null;
        this.mWidthBitmap = 0;
        this.mHeightBitmap = 0;
    }

    private void setViewSize(int i, int i2) {
        if (i > i2) {
            this.mScreenWidth = i;
            this.mScreenHeight = i2;
        } else {
            this.mScreenWidth = i2;
            this.mScreenHeight = i;
        }
        g.e(TAG, "Screen Size " + this.mScreenWidth + "X" + this.mScreenHeight);
    }

    private void setSurface(Surface surface) {
        this.mSurface = surface;
    }

    private void updateRect() {
        if (this.mWidthBitmap != 0 && this.mHeightBitmap != 0) {
            int i = this.mScreenWidth;
            int i2 = this.mScreenHeight;
            int i3;
            if (this.mHeightBitmap * i > this.mWidthBitmap * i2) {
                i3 = ((this.mWidthBitmap * i2) / this.mHeightBitmap) & -4;
                i = ((i - i3) / 2) & -4;
                this.mDst = new Rect(i, 0, i3 + i, i2 & -4);
                return;
            }
            i3 = ((this.mHeightBitmap * i) / this.mWidthBitmap) & -4;
            i2 = ((i2 - i3) / 2) & -4;
            this.mDst = new Rect(0, i2, i & -4, i3 + i2);
        }
    }

    private int init(int i, int i2) {
        g.e(TAG, "Init " + i + "X" + i2);
        if (i == 0 || i2 == 0) {
            return -1;
        }
        if (this.mWidthBitmap == i && this.mHeightBitmap == i2) {
            return 0;
        }
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
        try {
            this.mBitmap = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
            if (this.mBitmap == null) {
                g.c(TAG, "Failed to Create Bitmap buffer");
                return -1;
            }
            this.mWidthBitmap = i;
            this.mHeightBitmap = i2;
            updateRect();
            g.e(TAG, "Init OK ");
            return 0;
        } catch (Exception e) {
            g.c(TAG, "Failed to Create Bitmap buffer on catch! " + e.getMessage());
            return -1;
        }
    }

    private int render() {
        if (this.mSurface == null || this.mBitmap == null) {
            return -1;
        }
        try {
            Canvas lockCanvas = this.mSurface.lockCanvas(null);
            if (lockCanvas == null) {
                this.mSurface.unlockCanvasAndPost(lockCanvas);
                return -1;
            }
            lockCanvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, null);
            this.mSurface.unlockCanvasAndPost(lockCanvas);
            return 0;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (OutOfResourcesException e2) {
            e2.printStackTrace();
        }
    }
}
