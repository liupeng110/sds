package com.sds.android.ttpod.component.a;

import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;

/* EqualizerWaveViewHelper */
public class c {
    public static void a(EqualizerAnimationWaveView equalizerAnimationWaveView, short[] sArr) {
        if (equalizerAnimationWaveView != null && sArr != null && sArr.length == 10) {
            equalizerAnimationWaveView.setWaveShadowColor(-66);
            equalizerAnimationWaveView.setWaveShadowRadius(1.0f);
            equalizerAnimationWaveView.setWaveColor(-1);
            equalizerAnimationWaveView.setWaveStrokeWidth(2);
            equalizerAnimationWaveView.setWaveValue(sArr);
        }
    }
}
