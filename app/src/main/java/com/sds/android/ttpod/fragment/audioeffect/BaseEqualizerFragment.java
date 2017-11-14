package com.sds.android.ttpod.fragment.audioeffect;

import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.activities.audioeffect.CustomEqualizerActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.modules.core.audioeffect.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class BaseEqualizerFragment extends BaseFragment {
    public static final Settings DEFAULT_SETTINGS = new Settings(e.b(), (short) 10, e.b(e.b()));
    private static final String TAG = "BaseEqualizerFragment";
    protected Settings mEqualizerSettings = null;

    protected abstract void updateListView(String str);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadEqData();
    }

    private void loadEqData() {
        AudioEffectParam t = com.sds.android.ttpod.framework.support.e.a(getActivity()).t();
        if (t != null) {
            this.mEqualizerSettings = new Settings(t.g());
        } else if (this.mEqualizerSettings == null) {
            this.mEqualizerSettings = DEFAULT_SETTINGS;
        }
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_AUDIO_EFFECT_INFO, i.a(getClass(), "updateAudioEffectInfo", new Class[0]));
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            updateAudioEffectInfo();
        }
    }

    public void updateAudioEffectInfo() {
        if (isAdded()) {
            Settings settings = DEFAULT_SETTINGS;
            AudioEffectParam t = com.sds.android.ttpod.framework.support.e.a(getActivity()).t();
            if (t != null) {
                settings = new Settings(t.g());
            }
            updateListView(settings.getName());
        }
    }

    protected void setEqualizer(Settings settings) {
        g.a(TAG, "setEqualizer " + settings);
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SET_EQUALIZER, settings));
    }

    protected Settings getEqualizerSettingsByName(String str) {
        short[] b = e.b(str);
        if (b == null) {
            b = DEFAULT_SETTINGS.getBandLevels();
        }
        return new Settings(str, (short) 10, b);
    }

    protected void startCustomEqualizerActivity(Settings settings) {
        com.sds.android.ttpod.framework.storage.environment.b.k(settings.toString());
        Intent intent = new Intent(getActivity(), CustomEqualizerActivity.class);
        intent.putExtra(CustomEqualizerActivity.KEY_CUSTOM_EQUALIZER, settings.toString());
        startActivity(intent);
    }
}
