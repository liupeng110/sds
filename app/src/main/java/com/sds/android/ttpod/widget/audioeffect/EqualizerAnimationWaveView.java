package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import java.lang.reflect.Array;

public class EqualizerAnimationWaveView extends EqualizerIndicatorWaveView {
    private float[][] c;
    private Handler d;

    public EqualizerAnimationWaveView(Context context) {
        this(context, null);
        setWillNotDraw(false);
    }

    public EqualizerAnimationWaveView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        setWillNotDraw(false);
    }

    public EqualizerAnimationWaveView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Handler(this) {
            final /* synthetic */ EqualizerAnimationWaveView a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                this.a.setWaveValue(this.a.c[message.arg1]);
                if (message.arg1 + 1 < 6) {
                    this.a.d.sendMessageDelayed(this.a.d.obtainMessage(100, message.arg1 + 1, 0), 50);
                }
            }
        };
        this.c = (float[][]) Array.newInstance(Float.TYPE, new int[]{6, 10});
        setWillNotDraw(false);
    }

    public void setAnimationWaveValue(short[] sArr) {
        this.d.removeMessages(100);
        for (int i = 0; i < 10; i++) {
            a(this.a[i], (float) sArr[i], this.c, i);
        }
        setWaveValue(this.c[0]);
        this.d.sendMessageDelayed(this.d.obtainMessage(100, 1, 0), 50);
    }

    private void a(float f, float f2, float[][] fArr, int i) {
        float f3 = (f2 - f) / 6.0f;
        for (int i2 = 0; i2 < 6; i2++) {
            fArr[i2][i] = (((float) (i2 + 1)) * f3) + f;
        }
    }

    public void a(int i, float f) {
        this.d.removeMessages(100);
        super.a(i, f);
    }
}
