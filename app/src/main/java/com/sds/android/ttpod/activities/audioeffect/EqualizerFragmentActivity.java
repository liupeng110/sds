package com.sds.android.ttpod.activities.audioeffect;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.fragment.audioeffect.EqualizerAllFragment;
import com.sds.android.ttpod.fragment.audioeffect.EqualizerHandpickFragment;
import com.sds.android.ttpod.framework.a.b.a;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.List;

public class EqualizerFragmentActivity extends SlidingPagerActivity {
    private static final int ID_FRAGMENT_EQUALIZER_ALL = 1;
    private static final int ID_FRAGMENT_EQUALIZER_HANDPICK = 0;
    private static final ArrayList<a> SLIST = new ArrayList<a>(2) {
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPagerTitle.setShouldExpand(true);
        setTitle((int) R.string.effect_equalizer_default);
        a.a(getActionBarController());
        this.mPagerTitle.setBackgroundColor(Color.parseColor("#1f2223"));
        this.mPagerTitle.setTextColorResource(R.color.xml_local_media_tab_text_black);
        this.mPagerTitle.setIndicatorColorResource(R.color.effect_blue);
    }

    protected void onBuildActionBar(com.sds.android.ttpod.component.a aVar) {
    }

    protected void onBuildFragmentBinderList(List<e.a> list) {
        Fragment fragment = (BaseFragment) Fragment.instantiate(this, EqualizerHandpickFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_DEFAULT);
        list.add(new e.a(0, (int) R.string.effect_equalizer_handpick, 0, fragment));
        fragment = (BaseFragment) Fragment.instantiate(this, EqualizerAllFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_DEFAULT_ALL);
        list.add(new e.a(1, (int) R.string.effect_equalizer_all, 0, fragment));
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        t.a(((a) SLIST.get(i)).a(), ((a) SLIST.get(i)).b());
        if (i == 0) {
            f.f();
        } else if (i == 1) {
            f.h();
        }
    }
}
