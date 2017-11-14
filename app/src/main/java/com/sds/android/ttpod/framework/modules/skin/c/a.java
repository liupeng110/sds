package com.sds.android.ttpod.framework.modules.skin.c;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import com.sds.android.ttpod.framework.modules.skin.view.d;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* AnimationCommand */
public class a {
    private final ArrayList<float[]> a = new ArrayList(10);

    public void a(int i, int i2, int i3) {
        this.a.add(new float[]{(float) i, (float) i2, (float) i3});
    }

    public void a(float f, float f2, int i, int i2) {
        this.a.add(new float[]{-1082130432, (float) i, (float) i2, f, f2});
    }

    public void a(float f, float f2, float f3, float f4, float f5, float f6, int i, int i2) {
        this.a.add(new float[]{-1073741824, (float) i, (float) i2, f, f2, f3, f4, f5, f6});
    }

    public void a(float f, float f2, float f3, float f4, int i, int i2) {
        this.a.add(new float[]{-1069547520, (float) i, (float) i2, f, f2, f3, f4});
    }

    public void a(float f, float f2, float f3, float f4, float f5, int i, int i2) {
        this.a.add(new float[]{-1065353216, (float) i, (float) i2, f, f2, f3, f4, f5});
    }

    public AnimationSet a() {
        if (b() <= 0) {
            return null;
        }
        AnimationSet animationSet = new AnimationSet(false);
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            Animation a = a((float[]) it.next());
            if (a != null) {
                animationSet.addAnimation(a);
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        if (i > 0) {
            return animationSet;
        }
        return null;
    }

    public int b() {
        if (this.a.size() <= 0) {
            return 0;
        }
        Iterator it = this.a.iterator();
        int i = 0;
        while (it.hasNext()) {
            float[] fArr = (float[]) it.next();
            i = (int) Math.max(fArr[2] + fArr[1], (float) i);
        }
        return i;
    }

    public int c() {
        return this.a.size();
    }

    void d() {
        this.a.clear();
    }

    private static Animation a(float[] fArr) {
        if (fArr[1] <= 0.0f) {
            return null;
        }
        Animation dVar;
        switch ((int) fArr[0]) {
            case -4:
                dVar = new d(fArr[3], fArr[4], fArr[5], fArr[6], fArr[7], false);
                break;
            case -3:
                dVar = new RotateAnimation(fArr[3], fArr[4], 1, fArr[5], 1, fArr[6]);
                break;
            case -2:
                dVar = new ScaleAnimation(fArr[3], fArr[4], fArr[5], fArr[6], 1, fArr[7], 1, fArr[8]);
                break;
            case -1:
                dVar = new AlphaAnimation(fArr[3], fArr[4]);
                break;
            case 0:
                dVar = new AlphaAnimation(0.0f, 1.0f);
                break;
            case 1:
                dVar = new AlphaAnimation(1.0f, 0.0f);
                break;
            case 2:
                dVar = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, TTFMImageUtils.Middle_Scale, 1, TTFMImageUtils.Middle_Scale);
                break;
            case 3:
                dVar = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, TTFMImageUtils.Middle_Scale, 1, TTFMImageUtils.Middle_Scale);
                break;
            case 4:
                dVar = new RotateAnimation(0.0f, 360.0f, 1, TTFMImageUtils.Middle_Scale, 1, TTFMImageUtils.Middle_Scale);
                break;
            case 5:
                dVar = new RotateAnimation(360.0f, 0.0f, 1, TTFMImageUtils.Middle_Scale, 1, TTFMImageUtils.Middle_Scale);
                break;
            case 6:
                dVar = new d(0.0f, 90.0f, 0.0f, false);
                break;
            case 7:
                dVar = new d(90.0f, 0.0f, 0.0f, false);
                break;
            default:
                return null;
        }
        dVar.setDuration((long) ((int) fArr[1]));
        dVar.setStartOffset((long) ((int) fArr[2]));
        return dVar;
    }
}
