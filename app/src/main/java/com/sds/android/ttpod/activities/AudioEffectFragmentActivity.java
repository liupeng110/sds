package com.sds.android.ttpod.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity;
import com.sds.android.ttpod.activities.base.SlidingPagerActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.component.d.a.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.audioeffect.BoostFragment;
import com.sds.android.ttpod.fragment.audioeffect.CustomEqualizerFragment;
import com.sds.android.ttpod.fragment.audioeffect.MyAudioEffectFragment;
import com.sds.android.ttpod.fragment.audioeffect.ReverbFragment;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.h;
import com.sds.android.ttpod.widget.h.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AudioEffectFragmentActivity extends SlidingPagerActivity {
    private static final int EFFECT_REPEAT = 20000;
    private static final int ID_FRAGMENT_BOOST = 0;
    private static final int ID_FRAGMENT_CUSTOM_EQUALIZER = 1;
    private static final int ID_FRAGMENT_REVERB = 2;
    private static final int INDICATOR_HEIGHT = 4;
    private static final ArrayList<a> LIST_AUDIO_EFFECT_PAGE = new ArrayList<a>() {
        {
            add(new a("audio_effect", "tt_audio_effect_boost"));
            add(new a("audio_effect", "tt_audio_effect_equalizer"));
            add(new a("audio_effect", "tt_audio_effect_reverb"));
        }
    };
    private static final int MENU_CLOUD_EFFECT_RELATE = 100;
    private static final int MENU_RESET_ALL_AUDIO_EFFECT = 101;
    private static final int MENU_SAVE_TO_CLOUD = 102;
    private static final ArrayList<com.sds.android.ttpod.framework.a.b.a> SLIST = new ArrayList<com.sds.android.ttpod.framework.a.b.a>(3) {
    };
    private AudioEffectUser mAudioEffectUser;
    private int mCurrentPage = 0;
    private com.sds.android.ttpod.component.a.a mDeleteAction;
    private boolean mHasPrivateAudioEffect = false;

    public boolean getHasPrivateAudioEffect() {
        return this.mHasPrivateAudioEffect;
    }

    public void setHasPrivateAudioEffect(boolean z) {
        this.mHasPrivateAudioEffect = z;
    }

    public int getCurrentPage() {
        return this.mCurrentPage;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage(((a) LIST_AUDIO_EFFECT_PAGE.get(0)).c());
        trackModule("audio_effect");
        this.mPagerContent.setOffscreenPageLimit(this.mPagerAdapter.getCount() - 1);
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT_USERINFO, new Object[0]));
        this.mPagerTitle.setShouldExpand(true);
        setTitle(getString(R.string.audio_effect));
        this.mPagerTitle.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#222222")));
        this.mPagerTitle.setTextColor(Color.parseColor("#ffffff"));
        this.mPagerTitle.setIndicatorColorResource(R.color.effect_blue);
        this.mPagerTitle.setIndicatorHeight(com.sds.android.ttpod.common.c.a.a(4));
        initActionBar();
        setTTViewPagerListener(new h(this, 0, LIST_AUDIO_EFFECT_PAGE));
        findViewById(R.id.action_bar_activity_container).setBackgroundColor(getResources().getColor(R.color.effect_dialog_background));
    }

    protected void onStop() {
        super.onStop();
        f.a(this.mDropDownMenu);
    }

    public void onToggleMenuView(boolean z) {
        if (this.mDropDownMenu != null && this.mDropDownMenu.isShowing()) {
            this.mDropDownMenu.dismiss();
            this.mDropDownMenu = null;
        } else if (needMenuAction() && this.mMenuAction.c()) {
            Collection onCreateDropDownMenu = onCreateDropDownMenu();
            if (onCreateDropDownMenu != null && !onCreateDropDownMenu.isEmpty()) {
                this.mDropDownMenu = ActionBarFragment.popupMenu(this, onCreateDropDownMenu, z, this, R.layout.audio_effect_popup_menu_layout, R.id.lst_content, R.layout.audio_effect_popup_menu_choice_item, R.id.txt_title, 0);
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT_USERINFO, i.a(cls, "updateQueryEffectUserInfo", AudioEffectUserResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, i.a(cls, "updateSaveEffectToNetwork", AudioEffectAddResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.EFFECT_SAVE_RESULT, i.a(cls, "effectSaveResult", Boolean.class));
    }

    public void effectSaveResult(Boolean bool) {
        if (bool.booleanValue()) {
            f.a(getString(R.string.cloud_effect_save_sucess));
        } else {
            f.a(getString(R.string.cloud_effect_save_failed));
        }
    }

    public void updateSaveEffectToNetwork(AudioEffectAddResult audioEffectAddResult) {
        if (audioEffectAddResult.getCode() == 1) {
            f.a(getString(R.string.cloud_effect_share_sucess));
        } else if (audioEffectAddResult.getCode() == EFFECT_REPEAT) {
            f.a(getString(R.string.cloud_effect_share_repeat));
        } else {
            f.a(getString(R.string.cloud_effect_share_failed));
        }
    }

    public void updateQueryEffectUserInfo(AudioEffectUserResult audioEffectUserResult) {
        this.mAudioEffectUser = (AudioEffectUser) audioEffectUserResult.getData();
    }

    public void onPageSelected(int i) {
        super.onPageSelected(i);
        if (i >= 0) {
            this.mCurrentPage = i;
            if (i == 0) {
                com.sds.android.ttpod.framework.a.b.f.o();
            } else if (i == 1) {
                com.sds.android.ttpod.framework.a.b.f.d();
            } else if (i == 2) {
                com.sds.android.ttpod.framework.a.b.f.m();
            }
            t.a(((com.sds.android.ttpod.framework.a.b.a) SLIST.get(i)).a(), ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(i)).b());
        }
    }

    private void initActionBar() {
        this.mDeleteAction = getActionBarController().d((int) R.drawable.img_edit_effect_delete);
        this.mDeleteAction.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ AudioEffectFragmentActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                this.a.onDeleteActionItemClick();
            }
        });
        this.mDeleteAction.c(false);
        com.sds.android.ttpod.activities.audioeffect.a.a(getActionBarController());
    }

    protected boolean needMenuAction() {
        return true;
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        return new ArrayList<com.sds.android.ttpod.component.b.a>(this, 1) {
            final /* synthetic */ AudioEffectFragmentActivity a;
        };
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        if (100 == i) {
            launcherCloudAudioEffect();
        } else if (101 == i) {
            handleRestAllAudioEffect();
        } else if (102 == i) {
            handleSaveToCloud();
        }
    }

    private void launcherCloudAudioEffect() {
        startActivity(new Intent(this, CloudAudioEffectActivity.class));
        t.a(r.ACTION_EFFECT_CLOUD_AUDIO_RELATIVE, s.PAGE_AUDIO_CLOUD_EFFECT);
    }

    private void handleRestAllAudioEffect() {
        com.sds.android.ttpod.framework.a.b.f.t();
        t.a(r.ACTION_EFFECT_AJUST_RESET, s.PAGE_NONE);
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_AUDIO_EFFECT_RESET, new Object[0]));
    }

    private void handleSaveToCloud() {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (M.isNull()) {
            f.a((int) R.string.can_not_save_effect_no_play);
            return;
        }
        com.sds.android.ttpod.framework.a.b.f.u();
        t.a(r.ACTION_EFFECT_AJUST_SAVE, s.PAGE_NONE);
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (audioManager != null) {
            new c(this, M, this.mAudioEffectUser, audioManager.isWiredHeadsetOn()).show();
        }
    }

    private void onDeleteActionItemClick() {
        ((MyAudioEffectFragment) getSupportFragmentManager().findFragmentById(R.id.sliding_pager_content)).onDeleteActionItemClick();
    }

    public com.sds.android.ttpod.component.a.a getDeleteAction() {
        return this.mDeleteAction;
    }

    protected void onBuildActionBar(com.sds.android.ttpod.component.a aVar) {
    }

    protected void onBuildFragmentBinderList(List<e.a> list) {
        Fragment fragment = (BaseFragment) Fragment.instantiate(this, BoostFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_BOOST);
        list.add(new e.a(0, (int) R.string.boost_effect, 0, fragment));
        fragment = (BaseFragment) Fragment.instantiate(this, CustomEqualizerFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_EQUALIZER);
        list.add(new e.a(1, (int) R.string.effect_equalizer, 0, fragment));
        fragment = (BaseFragment) Fragment.instantiate(this, ReverbFragment.class.getName());
        fragment.setStatisticPage(s.PAGE_AUDIO_REVERB);
        list.add(new e.a(2, (int) R.string.effect_help_reverb_title, 0, fragment));
    }
}
