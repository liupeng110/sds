package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;
import java.util.LinkedHashMap;
import java.util.List;

public class AudioEffectDetailResult extends BaseResult {
    @c(a = "data")
    private LinkedHashMap<String, List<AudioEffect>> mAudioEffects = new LinkedHashMap();

    public static class AudioEffect {
        @c(a = "eq")
        private short[] mEqualizer = new short[]{(short) 0};

        public short[] getEqualizer() {
            return this.mEqualizer;
        }
    }

    public LinkedHashMap<String, List<AudioEffect>> getAudioEffects() {
        return this.mAudioEffects;
    }
}
