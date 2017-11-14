package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.b.b;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.support.e;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReverbFragment extends BaseFragment {
    private static final int[] REVERB_RES = new int[]{R.drawable.img_background_imageview_effect_reverb_none, R.drawable.img_background_imageview_effect_reverb_concert, R.drawable.img_background_imageview_effect_reverb_bedroom, R.drawable.img_background_imageview_effect_reverb_stone_house, R.drawable.img_background_imageview_effect_reverb_theater, R.drawable.img_background_imageview_effect_reverb_cave, R.drawable.img_background_imageview_effect_reverb_roadway, R.drawable.img_background_imageview_effect_reverb_city, R.drawable.img_background_imageview_effect_reverb_parking_lot, R.drawable.img_background_imageview_effect_reverb_parlor, R.drawable.img_background_imageview_effect_reverb_auditorium, R.drawable.img_background_imageview_effect_reverb_hall};
    private b mAdapter;
    private List<com.sds.android.ttpod.component.b.b> mAudioEffectItems = new ArrayList(REVERB_RES.length);
    private boolean mIsSelectd = false;
    private View mRootView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int length = REVERB_RES.length;
        String[] stringArray = getActivity().getResources().getStringArray(R.array.environment_title);
        if (stringArray.length != length) {
            throw new RuntimeException("音效资源不正确");
        }
        for (int i = 0; i < length; i++) {
            this.mAudioEffectItems.add(new com.sds.android.ttpod.component.b.b(REVERB_RES[i], stringArray[i], Integer.toString(i)));
        }
        this.mAdapter = new b(getActivity(), this.mAudioEffectItems, "");
        updateAudioEffectInfo();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_AUDIO_EFFECT_INFO, i.a(getClass(), "updateAudioEffectInfo", new Class[0]));
    }

    public void updateAudioEffectInfo() {
        AudioEffectParam t = e.a(BaseApplication.e()).t();
        if (this.mAdapter != null && t != null) {
            this.mAdapter.a(String.valueOf(t.d()));
            d.b.a("audioeffect_environment", ((com.sds.android.ttpod.component.b.b) this.mAudioEffectItems.get(t.d())).b());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_audio_effect_reverb, viewGroup, false);
            GridView gridView = (GridView) this.mRootView.findViewById(R.id.gridview_reverb);
            gridView.setAdapter(this.mAdapter);
            gridView.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ ReverbFragment a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    this.a.processItemClick(i);
                }
            });
        }
        return this.mRootView;
    }

    private void processItemClick(int i) {
        String c = ((com.sds.android.ttpod.component.b.b) this.mAudioEffectItems.get(i)).c();
        this.mAdapter.a(c);
        int parseInt = Integer.parseInt(c);
        com.sds.android.ttpod.framework.storage.environment.b.E(true);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.SET_REVERB, Integer.valueOf(parseInt)));
        if (!this.mIsSelectd) {
            this.mIsSelectd = true;
            f.n();
            t.a("PAGE_CLICK", r.ACTION_EFFECT_REVERB_SELECTED, s.PAGE_AUDIO_REVERB, s.PAGE_NONE);
        }
    }
}
