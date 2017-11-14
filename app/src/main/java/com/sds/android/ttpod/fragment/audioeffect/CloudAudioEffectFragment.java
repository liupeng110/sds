package com.sds.android.ttpod.fragment.audioeffect;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.audiofx.TTEqualizer.Settings;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.audioeffect.CircularProgress;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.tencent.open.yyb.TitleBar;
import java.lang.reflect.Method;
import java.util.Map;

public class CloudAudioEffectFragment extends BaseFragment {
    private static final int DELAYTIME = 200;
    private static final int DELAY_LOAD_TIME = 1200;
    private static final int EFFECT_STATUS_USE_AUTO = 2;
    private static final int EFFECT_STATUS_USE_CLOUD = 3;
    private static final int EFFECT_STATUS_USE_LOCAL = 4;
    private static final int EFFECT_STATUS_USE_PRIVATE = 1;
    private static final int EFFECT_STATUS_USE_UNINIT = 0;
    private TextView mAauthor;
    private boolean mAttach;
    private AudioEffectUser mAudioEffectUser;
    private RelativeLayout mAudioStopLayout;
    private LinearLayout mAudioWifiTipLayout;
    private TextView mAutorTextView;
    private boolean mChangedMedia = false;
    private MediaItem mCurrentMediaItem;
    private View mDefaultEffectLayout;
    private View mDefaultRootHit;
    private FrameLayout mFrameLayout;
    private Handler mHandler = new Handler();
    private ImageView mHeadPlaystatusView;
    private boolean mInit;
    private RelativeLayout mLayoutRoot;
    private TextView mMediaTextView;
    private Button mOfflineContinueBt;
    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(this) {
        final /* synthetic */ CloudAudioEffectFragment a;

        {
            this.a = r1;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.a.performCloudMatchChanged(z);
            if (z) {
                this.a.startRecommandAudioEffectFragment();
            } else {
                this.a.mAutorTextView.setText(this.a.getResources().getString(R.string.effect_match_no));
            }
            this.a.mDefaultEffectLayout.setVisibility(z ? 0 : 8);
            this.a.updatePlayView();
            new SUserEvent("PAGE_CLICK", r.ACTION_EFFECT_MATCH_CLOUD_AUDIO.getValue(), s.PAGE_AUDIO_CLOUD_EFFECT.getValue(), s.PAGE_NONE.getValue()).append(Downloads.COLUMN_STATUS, Boolean.valueOf(z)).post();
        }
    };
    private ToggleButton mOpenCloudEffectToggleButton;
    private RecommandAudioEffectFragment mRecommandAudioEffectFragment;
    private RelativeLayout mRecommandAudioFragment;
    private Runnable mRequestRunnable = new Runnable(this) {
        final /* synthetic */ CloudAudioEffectFragment a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.mRecommandAudioEffectFragment != null) {
                this.a.mRecommandAudioEffectFragment.updateRecommandList();
            }
        }
    };
    private View mRootView;
    private a mRunnable = new a();
    private RelativeLayout mTitleLayer;

    private class a implements Runnable {
        final /* synthetic */ CloudAudioEffectFragment a;

        private a(CloudAudioEffectFragment cloudAudioEffectFragment) {
            this.a = cloudAudioEffectFragment;
        }

        public void run() {
            this.a.updateAudioEffectInfo();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_cloud_audio_effect, viewGroup, false);
            initViews();
        }
        return this.mRootView;
    }

    private void initViews() {
        boolean ag = b.ag();
        this.mAutorTextView = (TextView) this.mRootView.findViewById(R.id.textview_effect_equalizer_content);
        this.mAutorTextView.setText(getResources().getString(R.string.effect_match_successex));
        this.mAutorTextView.setSelected(true);
        if (!ag) {
            this.mAutorTextView.setText(getResources().getString(R.string.effect_match_no));
        }
        this.mOpenCloudEffectToggleButton = (ToggleButton) this.mRootView.findViewById(R.id.togglebutton_effect_equalizer_cloud);
        this.mOpenCloudEffectToggleButton.setChecked(ag);
        this.mOpenCloudEffectToggleButton.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        initDefaultAudioEffectViews(ag);
        this.mMediaTextView = (TextView) this.mRootView.findViewById(R.id.textview_effect_equalizer_media);
        this.mAudioStopLayout = (RelativeLayout) this.mRootView.findViewById(R.id.audio_stop);
        this.mAudioWifiTipLayout = (LinearLayout) this.mRootView.findViewById(R.id.audio_wifi_tip);
        this.mOfflineContinueBt = (Button) this.mRootView.findViewById(R.id.button_offline_continue);
        this.mOfflineContinueBt.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CloudAudioEffectFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                b.y(false);
                b.D(true);
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_LOCAL_AUDIO_EFFECT, Boolean.valueOf(false)));
                this.a.requestAudioEffect();
            }
        });
        this.mFrameLayout = (FrameLayout) this.mRootView.findViewById(R.id.recomand_audio_effect);
        this.mAauthor = (TextView) this.mRootView.findViewById(R.id.music_author);
        this.mLayoutRoot = (RelativeLayout) this.mRootView.findViewById(R.id.layout_root);
        this.mLayoutRoot.setSelected(true);
        this.mHeadPlaystatusView = (ImageView) this.mRootView.findViewById(R.id.head_playstatus);
        updatePlayView();
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT_USERINFO, new Object[0]));
        this.mTitleLayer = (RelativeLayout) this.mRootView.findViewById(R.id.new_title);
        this.mTitleLayer.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CloudAudioEffectFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                PlayStatus n = e.a(this.a.getActivity()).n();
                if (n == PlayStatus.STATUS_PAUSED) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                } else if (n == PlayStatus.STATUS_PLAYING) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                } else {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                }
                this.a.updateAudioEffectInfoByRunnable();
            }
        });
        this.mInit = true;
    }

    private void initDefaultAudioEffectViews(boolean z) {
        this.mCurrentMediaItem = com.sds.android.ttpod.framework.storage.a.a.a().M();
        this.mDefaultEffectLayout = this.mRootView.findViewById(R.id.recommends_effect_default);
        ((CircularProgress) this.mDefaultEffectLayout.findViewById(R.id.rotatebutton_bass_boost)).setPaintText(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY);
        ((CircularProgress) this.mDefaultEffectLayout.findViewById(R.id.rotatebutton_treble_boost)).setPaintText("T");
        ((CircularProgress) this.mDefaultEffectLayout.findViewById(R.id.rotatebutton_surround)).setPaintText("S");
        updateWaveView((EqualizerAnimationWaveView) this.mDefaultEffectLayout.findViewById(R.id.waveview_effect_equalizer_equ));
        if (!z) {
            this.mDefaultEffectLayout.setVisibility(8);
        }
        this.mDefaultRootHit = this.mDefaultEffectLayout.findViewById(R.id.root_hit);
        this.mDefaultRootHit.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CloudAudioEffectFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.handleClickDefaultAudioEffect(view);
            }
        });
    }

    private void updateWaveView(EqualizerAnimationWaveView equalizerAnimationWaveView) {
        equalizerAnimationWaveView.setWaveShadowColor(-66);
        equalizerAnimationWaveView.setWaveShadowRadius(TitleBar.SHAREBTN_RIGHT_MARGIN);
        equalizerAnimationWaveView.setWaveColor(-1);
        equalizerAnimationWaveView.setWaveStrokeWidth(2);
    }

    private void handleClickDefaultAudioEffect(View view) {
        f.t();
        t.a("PAGE_CLICK", r.ACTION_EFFECT_AJUST_RESET, s.PAGE_NONE, s.PAGE_NONE);
        if (!this.mCurrentMediaItem.isNull() && !view.isSelected()) {
            view.setSelected(true);
            restoreCurrentEffect();
        }
    }

    private void restoreCurrentEffect() {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_AUDIO_EFFECT_RESET, new Object[0]));
        String title = this.mCurrentMediaItem.getTitle();
        String artist = this.mCurrentMediaItem.getArtist();
        Boolean valueOf = Boolean.valueOf(false);
        short[] sArr = new short[]{(short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0};
        String a = new com.sds.android.ttpod.framework.modules.core.audioeffect.b(b.at().getUserId(), title, artist, sArr, 0, 0, 0, 0, 0.0f, true).a();
        com.sds.android.ttpod.framework.modules.core.audioeffect.a aVar = new com.sds.android.ttpod.framework.modules.core.audioeffect.a();
        aVar.a(a);
        aVar.a(this.mCurrentMediaItem.getSongID());
        aVar.b(artist);
        aVar.c(title);
        aVar.a(0);
        aVar.b(0);
        aVar.d(Build.MODEL);
        aVar.f(getResources().getString(R.string.me));
        aVar.e(0);
        aVar.f(0);
        aVar.g(0);
        aVar.h(0);
        aVar.a(0.0f);
        aVar.a(true);
        aVar.a(sArr);
        aVar.b(System.currentTimeMillis());
        aVar.g(this.mCurrentMediaItem.getLocalDataSource());
        g.a("CloudAudioEffectFragment", "saveToLocal " + aVar);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT, this.mCurrentMediaItem, aVar, valueOf));
    }

    public RecommandAudioEffectFragment getRecommandEffectFragment() {
        return this.mRecommandAudioEffectFragment;
    }

    private void updatePlayView() {
        CharSequence string;
        CharSequence charSequence;
        if (this.mCurrentMediaItem.isNull()) {
            string = getString(R.string.effect_no_play);
            charSequence = "";
        } else {
            string = this.mCurrentMediaItem.getTitle();
            charSequence = this.mCurrentMediaItem.getArtist();
        }
        this.mMediaTextView.setText(string);
        this.mAauthor.setText(charSequence);
        if (b.ag()) {
            this.mAudioStopLayout.setVisibility(8);
            this.mFrameLayout.setVisibility(0);
            startRecommandAudioEffectFragment();
            return;
        }
        this.mAudioStopLayout.setVisibility(0);
        this.mFrameLayout.setVisibility(8);
    }

    private String getMediaText(MediaItem mediaItem, AudioEffectParam audioEffectParam) {
        String string;
        String str;
        if (mediaItem.isNull()) {
            string = getString(R.string.effect_no_play);
            str = "";
        } else {
            string = mediaItem.getTitle();
            str = "-" + mediaItem.getArtist();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string).append(str);
        return stringBuilder.toString();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(cls, "updatePlayMediaInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT_USERINFO, i.a(cls, "updateQueryEffectUserInfo", AudioEffectUserResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, i.a(cls, "updateAudioEffectInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MANUAL_SETTING_EFFECT, i.a(cls, "updateManualSettingEffect", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (!this.mInit) {
            return;
        }
        if (playStatus == PlayStatus.STATUS_PLAYING) {
            this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_item_hit);
        } else {
            this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_pause);
        }
    }

    public void updatePlayMediaInfo() {
        this.mChangedMedia = true;
        this.mCurrentMediaItem = com.sds.android.ttpod.framework.storage.a.a.a().M();
        updatePlayView();
        updateAudioEffectInfoByRunnable();
    }

    private void updateAudioEffectInfoByRunnable() {
        if (isAdded()) {
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, 200);
        }
    }

    public void onResume() {
        super.onResume();
        updateAudioEffectInfo();
        this.mOpenCloudEffectToggleButton.setChecked(b.ag());
        d.b.a("audioeffect_cloud", !this.mAutorTextView.getText().equals(getResources().getString(R.string.effect_match_successex)));
    }

    private void updateAuthorText() {
        this.mAutorTextView.setText(R.string.effect_match_local);
        this.mAutorTextView.setSelected(false);
    }

    public void updateQueryEffectUserInfo(AudioEffectUserResult audioEffectUserResult) {
        this.mAudioEffectUser = (AudioEffectUser) audioEffectUserResult.getData();
    }

    public void updateManualSettingEffect() {
        updateAudioEffectInfo();
    }

    private void startRecommandAudioEffectFragment() {
        if (p.a()) {
            this.mAudioWifiTipLayout.setVisibility(0);
        } else {
            requestAudioEffect();
        }
    }

    private void requestAudioEffect() {
        this.mAudioWifiTipLayout.setVisibility(8);
        if (this.mRecommandAudioEffectFragment == null) {
            this.mRecommandAudioEffectFragment = new RecommandAudioEffectFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.recomand_audio_effect, this.mRecommandAudioEffectFragment).commit();
        } else if (this.mChangedMedia) {
            this.mHandler.removeCallbacks(this.mRequestRunnable);
            this.mHandler.postDelayed(this.mRequestRunnable, 1200);
        } else {
            this.mRecommandAudioEffectFragment.notifyUpdateRecommandList();
        }
        this.mChangedMedia = false;
    }

    private void performCloudMatchChanged(boolean z) {
        boolean z2;
        com.sds.android.ttpod.component.d.f.a(getString(z ? R.string.effect_equalizer_cloud_match_enabled : R.string.effect_equalizer_cloud_match_disabled));
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_CLOUD_AUDIO_EFFECT_ENABLED, Boolean.valueOf(z)));
        com.sds.android.ttpod.framework.base.a.b a = com.sds.android.ttpod.framework.base.a.b.a();
        com.sds.android.ttpod.framework.modules.a aVar = com.sds.android.ttpod.framework.modules.a.SET_LOCAL_AUDIO_EFFECT;
        Object[] objArr = new Object[1];
        if (z) {
            z2 = false;
        } else {
            z2 = true;
        }
        objArr[0] = Boolean.valueOf(z2);
        a.a(new com.sds.android.ttpod.framework.base.a.a(aVar, objArr));
        updateAudioEffectInfo();
    }

    private boolean isDefaultAudioEffect(AudioEffectParam audioEffectParam) {
        short[] bandLevels = new Settings(audioEffectParam.g()).getBandLevels();
        float e = audioEffectParam.e();
        int a = audioEffectParam.a();
        int b = audioEffectParam.b();
        int c = audioEffectParam.c();
        int d = audioEffectParam.d();
        for (int length = bandLevels.length - 1; length >= 0; length--) {
            if (bandLevels[length] != (short) 0) {
                return false;
            }
        }
        if (a == 0 && b == 0 && e == 0.0f && c == 0 && d == 0) {
            return true;
        }
        return false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mAttach = true;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacks(this.mRunnable);
    }

    public void onDetach() {
        super.onDetach();
        this.mAttach = false;
    }

    public void updateAudioEffectInfo() {
        if (this.mAttach && getActivity() != null) {
            if (e.a(getActivity()).n() == PlayStatus.STATUS_PLAYING) {
                this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_item_hit);
            } else {
                this.mHeadPlaystatusView.setBackgroundResource(R.drawable.myeffect_pause);
            }
            boolean ag = b.ag();
            if (this.mCurrentMediaItem.isNull()) {
                showNoMatch(false);
                return;
            }
            AudioEffectParam t = e.a(getActivity()).t();
            if (t != null) {
                Boolean valueOf;
                Boolean.valueOf(false);
                switch (t.h()) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        valueOf = Boolean.valueOf(true);
                        break;
                    default:
                        valueOf = Boolean.valueOf(false);
                        break;
                }
                if (valueOf.booleanValue() && ag && !(t.h() == 3 && m.a(t.i()))) {
                    if (isDefaultAudioEffect(t)) {
                        showNoMatch(ag);
                        return;
                    } else {
                        showMatch();
                        return;
                    }
                }
            }
            showNoMatch(ag);
        }
    }

    private void showMatch() {
        this.mAutorTextView.setSelected(true);
        this.mLayoutRoot.setSelected(true);
        this.mAutorTextView.setText(getResources().getText(R.string.effect_match_successex));
        this.mDefaultRootHit.setSelected(false);
    }

    private void showNoMatch(boolean z) {
        if (z) {
            showNoMatch();
            return;
        }
        this.mAutorTextView.setSelected(false);
        this.mLayoutRoot.setSelected(false);
        this.mAutorTextView.setText(getResources().getText(R.string.effect_match_no));
        this.mDefaultRootHit.setSelected(false);
    }

    private void showNoMatch() {
        this.mAutorTextView.setSelected(true);
        this.mLayoutRoot.setSelected(true);
        this.mAutorTextView.setText(getResources().getText(R.string.recommand_effect_default));
        this.mDefaultRootHit.setSelected(true);
    }
}
