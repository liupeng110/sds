package com.sds.android.ttpod.activities.audioeffect;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.fragment.audioeffect.CloudAudioEffectFragment;
import com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment;
import com.sds.android.ttpod.framework.a.b.a;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.storage.environment.b;
import java.util.ArrayList;
import java.util.List;

public class CloudAudioEffectActivity extends SlidingPagerActivity {
    private static final int ID_FRAGMENT_CLOUD_AUDIO = 0;
    private static final int ID_FRAGMENT_MY_AUDIO = 1;
    private static final int INDICATOR_HEIGHT = 4;
    private static final ArrayList<a> SLIST = new ArrayList<a>(2) {
    };
    private int mCurrentPage;
    private com.sds.android.ttpod.component.a.a mDeleteAction;
    private boolean mHasPrivateAudioEffect = false;
    private View mKnownView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_cloud_audio");
        this.mPagerTitle.setShouldExpand(true);
        setTitle(getString(R.string.effect_back));
        this.mPagerTitle.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#222222")));
        this.mPagerTitle.setTextColor(Color.parseColor("#ffffff"));
        this.mPagerTitle.setIndicatorColorResource(R.color.effect_blue);
        this.mPagerTitle.setIndicatorHeight(com.sds.android.ttpod.common.c.a.a(4));
        initActionBar();
        initFirstInto();
        this.mPagerTitle.setOnPageChangeListener(this);
    }

    private void initFirstInto() {
        if (b.as()) {
            b.G(false);
            final View inflate = View.inflate(this, R.layout.audio_effect_guide, null);
            addContentView(inflate, new LayoutParams(-1, -1));
            this.mKnownView = inflate.findViewById(R.id.id_button_known);
            this.mKnownView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ CloudAudioEffectActivity b;

                public void onClick(View view) {
                    inflate.setVisibility(8);
                }
            });
            inflate.setClickable(true);
            Toast.makeText(this, R.string.effect_score_rule_changed, 1).show();
        }
    }

    private void initActionBar() {
        this.mDeleteAction = getActionBarController().d((int) R.drawable.img_edit_effect_delete);
        this.mDeleteAction.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ CloudAudioEffectActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                this.a.onDeleteActionItemClick();
            }
        });
        this.mDeleteAction.c(false);
        a.a(getActionBarController());
    }

    private void onDeleteActionItemClick() {
        ((MyAudioEffectFragment) getSupportFragmentManager().findFragmentById(R.id.sliding_pager_content)).onDeleteActionItemClick();
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        this.mCurrentPage = i;
        t.a(((a) SLIST.get(i)).a(), ((a) SLIST.get(i)).b());
        if (i == 0) {
            this.mDeleteAction.c(false);
        } else if (i == 1) {
            this.mDeleteAction.c(this.mHasPrivateAudioEffect);
            f.w();
        }
    }

    public com.sds.android.ttpod.component.a.a getDeleteAction() {
        return this.mDeleteAction;
    }

    public boolean isMyAudioEffectFragmentPage() {
        return this.mCurrentPage == 1;
    }

    public boolean getHasPrivateAudioEffect() {
        return this.mHasPrivateAudioEffect;
    }

    public void setHasPrivateAudioEffect(boolean z) {
        this.mHasPrivateAudioEffect = z;
    }

    protected void onBuildActionBar(com.sds.android.ttpod.component.a aVar) {
    }

    protected void onBuildFragmentBinderList(List<e.a> list) {
        Fragment fragment = (BaseFragment) Fragment.instantiate(this, CloudAudioEffectFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_CLOUD_EFFECT);
        list.add(new e.a(0, (int) R.string.string_cloud_audio_effect, 0, fragment));
        fragment = (BaseFragment) Fragment.instantiate(this, MyAudioEffectFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_MY_CLOUD_EFFECT);
        list.add(new e.a(1, (int) R.string.my_effect, 0, fragment));
    }
}
