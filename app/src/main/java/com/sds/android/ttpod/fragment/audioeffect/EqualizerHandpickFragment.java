package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.b.b;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;

public class EqualizerHandpickFragment extends BaseEqualizerFragment {
    private static final int[] HANDPICK_RES = new int[]{R.drawable.img_background_imageview_effect_equalizer_handpick_common, R.drawable.img_background_imageview_effect_equalizer_handpick_rock, R.drawable.img_background_imageview_effect_equalizer_handpick_pop, R.drawable.img_background_imageview_effect_equalizer_handpick_dance, R.drawable.img_background_imageview_effect_equalizer_handpick_hip_hop, R.drawable.img_background_imageview_effect_equalizer_handpick_classical, R.drawable.img_background_imageview_effect_equalizer_handpick_bass, R.drawable.img_background_imageview_effect_equalizer_handpick_voice};
    private static final String[] HANDPICK_TITLES_VALUES = new String[]{"普通/Normal", "摇滚/Rock", "流行/Pop", "舞曲/Dance", "嘻哈/Hip-Hop", "古典/Classic", "超重低音/Bass", "人声/Vocal"};
    public static final String KEY_CUSTOM = "自定义/custom";
    private static final String TAG = "EqualizerHandpickFragment";
    private b mHandpickAdapter;
    private List<com.sds.android.ttpod.component.b.b> mHandpickList = new ArrayList(HANDPICK_RES.length);
    private boolean mIsSelected = false;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener(this) {
        final /* synthetic */ EqualizerHandpickFragment a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.a.performItemClick((String) view.getTag(R.id.view_bind_data));
        }
    };
    private View mRootView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_audio_effect_reverb, viewGroup, false);
            AudioEffectParam t = e.a(getActivity()).t();
            initHandpickData(t != null ? t.g() : "");
            initGridView();
        }
        return this.mRootView;
    }

    private void initGridView() {
        GridView gridView = (GridView) this.mRootView.findViewById(R.id.gridview_reverb);
        if (this.mEqualizerSettings != null) {
            this.mHandpickAdapter.a(this.mEqualizerSettings.getName());
        }
        gridView.setAdapter(this.mHandpickAdapter);
        gridView.setOnItemClickListener(this.mOnItemClickListener);
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mHandpickAdapter = null;
            this.mHandpickList.clear();
            this.mRootView = null;
        }
    }

    protected void updateListView(String str) {
        this.mHandpickAdapter.a(str);
    }

    private void initHandpickData(String str) {
        int length = HANDPICK_RES.length;
        for (int i = 0; i < length; i++) {
            this.mHandpickList.add(new com.sds.android.ttpod.component.b.b(HANDPICK_RES[i], HANDPICK_TITLES_VALUES[i], HANDPICK_TITLES_VALUES[i]));
        }
        this.mHandpickAdapter = new b(getActivity(), this.mHandpickList, str);
    }

    private void performItemClick(String str) {
        if (str.equals(KEY_CUSTOM)) {
            String ak = com.sds.android.ttpod.framework.storage.environment.b.ak();
            Settings equalizerSettingsByName = m.a(ak) ? getEqualizerSettingsByName(str) : new Settings(ak);
            setEqualizer(equalizerSettingsByName);
            startCustomEqualizerActivity(equalizerSettingsByName);
        } else {
            setEqualizer(getEqualizerSettingsByName(str));
            com.sds.android.ttpod.framework.storage.environment.b.E(true);
        }
        this.mHandpickAdapter.a(str);
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_EFFECT_EQULIZER_DEFAULT_HANDPICK_SELECTED.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        sUserEvent.append(SocialConstants.PARAM_TYPE, str);
        sUserEvent.post();
        if (!this.mIsSelected) {
            this.mIsSelected = true;
            f.g();
        }
    }
}
