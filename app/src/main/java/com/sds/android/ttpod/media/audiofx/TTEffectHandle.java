package com.sds.android.ttpod.media.audiofx;

import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;

public class TTEffectHandle implements IEffectHandle {
    private TTBassBoost mBassBoost;
    private TTVolume mBoostLimiter;
    private TTEqualizer mEqualizer;
    private TTReverb mReverb;
    private TTTrebleBoost mTrebleBoost;
    private TTVirtualizer mVirtualizer;

    public void setEqualizerEnabled(boolean z) {
        if (z) {
            this.mEqualizer = (TTEqualizer) createAudioEffect(TTEqualizer.class, this.mEqualizer);
            enableAudioEffect(this.mEqualizer);
            return;
        }
        disableAudioEffect(this.mEqualizer);
        this.mEqualizer = null;
    }

    public void setEqualizer(Settings settings) {
        short s = (short) 0;
        this.mEqualizer = (TTEqualizer) createAudioEffect(TTEqualizer.class, this.mEqualizer);
        if (this.mEqualizer != null) {
            short[] bandLevels = settings.getBandLevels();
            int length = bandLevels.length;
            int i = 0;
            while (i < length) {
                short s2 = (short) (s + 1);
                this.mEqualizer.setBandLevel(s, bandLevels[i]);
                i++;
                s = s2;
            }
        }
    }

    public void setBassBoostEnabled(boolean z) {
        if (z) {
            this.mBassBoost = (TTBassBoost) createAudioEffect(TTBassBoost.class, this.mBassBoost);
            enableAudioEffect(this.mBassBoost);
            return;
        }
        disableAudioEffect(this.mBassBoost);
        this.mBassBoost = null;
    }

    public void setBassBoost(int i) {
        this.mBassBoost = (TTBassBoost) createAudioEffect(TTBassBoost.class, this.mBassBoost);
        if (this.mBassBoost != null) {
            this.mBassBoost.setStrength((short) i);
        }
    }

    public void setBoostLimitEnabled(boolean z) {
        this.mBoostLimiter = (TTVolume) createAudioEffect(TTVolume.class, this.mBoostLimiter);
        if (this.mBoostLimiter != null) {
            this.mBoostLimiter.setBoostLimitEnabled(z);
        }
        this.mReverb = (TTReverb) createAudioEffect(TTReverb.class, this.mReverb);
        if (this.mReverb != null) {
            this.mReverb.setBoostLimitEnabled(z);
        }
    }

    public void setTrebleBoostEnabled(boolean z) {
        if (z) {
            this.mTrebleBoost = (TTTrebleBoost) createAudioEffect(TTTrebleBoost.class, this.mTrebleBoost);
            enableAudioEffect(this.mTrebleBoost);
            return;
        }
        disableAudioEffect(this.mTrebleBoost);
        this.mTrebleBoost = null;
    }

    public void setTrebleBoost(int i) {
        this.mTrebleBoost = (TTTrebleBoost) createAudioEffect(TTTrebleBoost.class, this.mTrebleBoost);
        if (this.mTrebleBoost != null) {
            this.mTrebleBoost.setStrength((short) i);
        }
    }

    public void setVirtualizerEnabled(boolean z) {
        if (z) {
            this.mVirtualizer = (TTVirtualizer) createAudioEffect(TTVirtualizer.class, this.mVirtualizer);
            enableAudioEffect(this.mVirtualizer);
            return;
        }
        disableAudioEffect(this.mVirtualizer);
        this.mVirtualizer = null;
    }

    public void setVirtualizer(int i) {
        this.mVirtualizer = (TTVirtualizer) createAudioEffect(TTVirtualizer.class, this.mVirtualizer);
        if (this.mVirtualizer != null) {
            this.mVirtualizer.setStrength((short) i);
        }
    }

    public void setReverbEnabled(boolean z) {
        if (z) {
            this.mReverb = (TTReverb) createAudioEffect(TTReverb.class, this.mReverb);
            enableAudioEffect(this.mReverb);
            return;
        }
        disableAudioEffect(this.mReverb);
        this.mReverb = null;
    }

    public void setReverb(int i) {
        this.mReverb = (TTReverb) createAudioEffect(TTReverb.class, this.mReverb);
        if (this.mReverb != null) {
            this.mReverb.setPreset((short) i);
        }
    }

    private <Effect extends TTAudioEffect> Effect createAudioEffect(Class cls, Effect effect) {
        if (EffectDetect.usingAudioPlus()) {
            return null;
        }
        if (effect != null) {
            return effect;
        }
        try {
            if (TTEqualizer.class.equals(cls)) {
                return new TTEqualizer();
            }
            if (TTReverb.class.equals(cls)) {
                return new TTReverb();
            }
            if (TTBassBoost.class.equals(cls)) {
                return new TTBassBoost();
            }
            if (TTTrebleBoost.class.equals(cls)) {
                return new TTTrebleBoost();
            }
            if (TTVirtualizer.class.equals(cls)) {
                return new TTVirtualizer();
            }
            if (TTVolume.class.equals(cls)) {
                return new TTVolume();
            }
            return effect;
        } catch (Exception e) {
            e.printStackTrace();
            return effect;
        }
    }

    private <Effect extends TTAudioEffect> void enableAudioEffect(Effect effect) {
        if (effect != null) {
            effect.setEnabled(true);
        }
    }

    private <Effect extends TTAudioEffect> void disableAudioEffect(Effect effect) {
        if (effect != null) {
            effect.setEnabled(false);
        }
    }

    public void release() {
        releaseAudioEffect();
    }

    private void releaseAudioEffect() {
        if (this.mEqualizer != null) {
            this.mEqualizer.release();
            this.mEqualizer = null;
        }
        if (this.mBassBoost != null) {
            this.mBassBoost.release();
            this.mBassBoost = null;
        }
        if (this.mTrebleBoost != null) {
            this.mTrebleBoost.release();
            this.mTrebleBoost = null;
        }
        if (this.mBoostLimiter != null) {
            this.mBoostLimiter.release();
            this.mBoostLimiter = null;
        }
        if (this.mReverb != null) {
            this.mReverb.release();
            this.mReverb = null;
        }
        if (this.mVirtualizer != null) {
            this.mVirtualizer.release();
            this.mVirtualizer = null;
        }
    }

    public void reset() {
        if (this.mEqualizer != null) {
            this.mEqualizer.reset();
        }
        if (this.mBassBoost != null) {
            this.mBassBoost.reset();
        }
        if (this.mTrebleBoost != null) {
            this.mTrebleBoost.reset();
        }
        if (this.mReverb != null) {
            this.mReverb.reset();
        }
    }
}
