package com.sds.android.ttpod.fragment.audioeffect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUser;
import com.sds.android.cloudapi.ttpod.data.AudioEffectUserExp;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectUserResult;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.activities.audioeffect.CloudAudioEffectActivity;
import com.sds.android.ttpod.activities.audioeffect.EffectLevelActivity;
import com.sds.android.ttpod.activities.audioeffect.SelectDeleteEffectActivity;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.component.a.c;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.core.audioeffect.f;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.audioeffect.CircularProgress;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class MyAudioEffectFragment extends BaseFragment {
    private static final int DELAY_TIME = 2100;
    private static final int EFFECT_STATUS_USE_CLOUD = 3;
    private static final int EFFECT_STATUS_USE_PRIVATE = 1;
    private static final int HUNRED = 100;
    private static final int LIST_DELIVER_HEIGHT = 12;
    private static final String TAG = "MyEffectActivity";
    private ImageView mAvatar;
    private MediaItem mCurrentMediaItem;
    private View mDetailView;
    private ProgressBar mEffectLevelProgressbar;
    private Handler mHandler = new Handler();
    private ImageView mImgLevel;
    private boolean mIsCloudAudioOpen = false;
    private boolean mIsItemClicked = false;
    private boolean mIsStatisticed = false;
    private long mLastTime = 0;
    private View mLayoutEffectDetail;
    private TextView mLevel;
    private TextView mListTitle;
    private TextView mListTitleTip;
    private StateView mLoadingLayer;
    private List<MediaItem> mMediaItemList;
    private View mMyAudioEffectRootView;
    private MyEffectListFragment mMyEffectListFragment;
    private boolean mNeedUpdate = true;
    private boolean mNetTemporaryGroupSynced = false;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ MyAudioEffectFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view.getId() == R.id.layout_effect_detail) {
                this.a.performEffectLevelClick();
            }
        }
    };
    private PlayStatusMonitor mPlayStatusMonitor;
    private RelativeLayout mScoreLayer;
    private MediaItem mSelectedMediaItem;
    private TextView mTotalScore;
    private TextView mUpgradeLeftScore;
    private int mUsedEffect;
    private TextView mUserName;
    private TextView mWifiTip;

    public class MyEffectListFragment extends BaseEffectListFragment {
        private int mSelectItem = -1;
        private a mUpdateRunnable = new a(this);

        public class a implements Runnable {
            final /* synthetic */ MyEffectListFragment a;

            public a(MyEffectListFragment myEffectListFragment) {
                this.a = myEffectListFragment;
            }

            public void run() {
                BaseActivity baseActivity = (BaseActivity) this.a.getActivity();
                if (baseActivity != null) {
                    baseActivity.trackPlaySong("audio_effect", "audio_effect", MyAudioEffectFragment.this.mSelectedMediaItem.isOnline());
                }
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, MyAudioEffectFragment.this.mSelectedMediaItem));
            }
        }

        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            this.mListView.setBackgroundDrawable(getResources().getDrawable(R.color.effect_section_background));
            this.mListView.setDivider(getResources().getDrawable(R.color.effect_section_background));
            this.mListView.setDividerHeight(com.sds.android.ttpod.common.c.a.a(12));
        }

        protected View getEffectItem(int i, View view, ViewGroup viewGroup) {
            boolean z;
            boolean z2 = true;
            if (view == null) {
                view = getLayoutInflater(null).inflate(R.layout.myeffect_list_item_new, null);
                view.setTag(new a(MyAudioEffectFragment.this, view));
            }
            f fVar = (f) this.mMyEffectItemList.get(i);
            a aVar = (a) view.getTag();
            String a = fVar.a();
            int lastIndexOf = a.lastIndexOf("-");
            Object obj = a.substring(0, lastIndexOf) + "   " + getString(R.string.effect_provide_by, fVar.b());
            CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#80ffffff")), lastIndexOf, obj.length(), 33);
            aVar.b.setText(spannableStringBuilder);
            aVar.c.setText(a.substring(lastIndexOf + 1));
            c.a(aVar.d, fVar.h());
            if (!MyAudioEffectFragment.this.mIsItemClicked) {
                if (MyAudioEffectFragment.this.mIsCloudAudioOpen && MyAudioEffectFragment.this.mCurrentMediaItem != null && m.a(fVar.f().getID(), MyAudioEffectFragment.this.mCurrentMediaItem.getID())) {
                    this.mSelectItem = i;
                } else {
                    this.mSelectItem = -1;
                }
            }
            if (i != this.mSelectItem || e.a(getActivity()).n() == PlayStatus.STATUS_PLAYING) {
                aVar.i.setBackgroundResource(R.drawable.recommand_list_hit_xml);
            } else {
                aVar.i.setBackgroundResource(R.drawable.myeffect_pause);
            }
            RelativeLayout e = aVar.e;
            if (i == this.mSelectItem) {
                z = true;
            } else {
                z = false;
            }
            e.setSelected(z);
            ImageView d = aVar.i;
            if (i != this.mSelectItem) {
                z2 = false;
            }
            d.setSelected(z2);
            aVar.f.setValue(RadialProgressWidget.b(fVar.i()));
            aVar.g.setValue(RadialProgressWidget.b(fVar.j()));
            aVar.h.setValue(RadialProgressWidget.b(fVar.k()));
            return view;
        }

        private void setSelectItem(int i) {
            this.mSelectItem = i;
            this.mAdapter.notifyDataSetChanged();
        }

        private void play(int i, boolean z) {
            if (!MyAudioEffectFragment.this.mIsStatisticed) {
                MyAudioEffectFragment.this.mIsStatisticed = true;
                com.sds.android.ttpod.framework.a.b.f.x();
                t.a("PAGE_CLICK", r.ACTION_EFFECT_MY_EFFECT_PLAY, s.PAGE_NONE, s.PAGE_AUDIO_MY_CLOUD_EFFECT);
            }
            if (!MyAudioEffectFragment.this.mNetTemporaryGroupSynced) {
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, MyAudioEffectFragment.this.mMediaItemList));
                MyAudioEffectFragment.this.mNetTemporaryGroupSynced = true;
            }
            MediaItem f = ((f) this.mMyEffectItemList.get(i)).f();
            MyAudioEffectFragment.this.mSelectedMediaItem = f;
            if (m.a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY, com.sds.android.ttpod.framework.storage.environment.b.m()) && m.a(f.getID(), com.sds.android.ttpod.framework.storage.environment.b.n())) {
                PlayStatus n = e.a(getActivity()).n();
                if (n == PlayStatus.STATUS_PAUSED) {
                    b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                    return;
                } else if (n == PlayStatus.STATUS_PLAYING) {
                    b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                    return;
                } else {
                    b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                    return;
                }
            }
            MyAudioEffectFragment.this.mHandler.postDelayed(this.mUpdateRunnable, z ? 2100 : 0);
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            boolean z = true;
            super.onItemClick(adapterView, view, i, j);
            MyAudioEffectFragment.this.mIsItemClicked = true;
            MyAudioEffectFragment.this.mHandler.removeCallbacks(this.mUpdateRunnable);
            long currentTimeMillis = System.currentTimeMillis();
            setSelectItem(i);
            if (currentTimeMillis - MyAudioEffectFragment.this.mLastTime >= 2100) {
                z = false;
            }
            play(i, z);
            MyAudioEffectFragment.this.mLastTime = currentTimeMillis;
        }
    }

    public class PlayStatusMonitor extends BroadcastReceiver {
        final /* synthetic */ MyAudioEffectFragment a;

        public PlayStatusMonitor(MyAudioEffectFragment myAudioEffectFragment) {
            this.a = myAudioEffectFragment;
        }

        public void onReceive(Context context, Intent intent) {
            if (Action.PLAY_STATUS_CHANGED.equals(intent.getAction())) {
                this.a.mIsItemClicked = false;
                this.a.getCurrentMediaItemAndEffect();
                this.a.mMyEffectListFragment.notifyDataSetChanged();
            }
        }

        public IntentFilter a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            return intentFilter;
        }
    }

    private class a {
        final /* synthetic */ MyAudioEffectFragment a;
        private TextView b;
        private TextView c;
        private EqualizerAnimationWaveView d;
        private RelativeLayout e;
        private CircularProgress f;
        private CircularProgress g;
        private CircularProgress h;
        private ImageView i;

        public a(MyAudioEffectFragment myAudioEffectFragment, View view) {
            this.a = myAudioEffectFragment;
            this.b = (TextView) view.findViewById(R.id.textview_title);
            this.c = (TextView) view.findViewById(R.id.textview_title_2);
            this.d = (EqualizerAnimationWaveView) view.findViewById(R.id.waveview_effect_equalizer_equ);
            this.e = (RelativeLayout) view.findViewById(R.id.layout_root);
            this.f = (CircularProgress) view.findViewById(R.id.rotatebutton_bass_boost);
            this.f.setPaintText(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY);
            this.g = (CircularProgress) view.findViewById(R.id.rotatebutton_treble_boost);
            this.g.setPaintText("T");
            this.h = (CircularProgress) view.findViewById(R.id.rotatebutton_surround);
            this.h.setPaintText("S");
            this.i = (ImageView) view.findViewById(R.id.image_play);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mMyAudioEffectRootView == null) {
            this.mMyAudioEffectRootView = layoutInflater.inflate(R.layout.activity_myeffect, viewGroup, false);
            this.mLayoutEffectDetail = this.mMyAudioEffectRootView.findViewById(R.id.layout_effect_detail);
            initMyEffectFragment();
            initMyAudioEffectViews();
            getCurrentMediaItemAndEffect();
        }
        return this.mMyAudioEffectRootView;
    }

    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        setUserInfoView();
        loadMonitor();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT_USERINFO, new Object[0]));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT_USERINFO, i.a(cls, "updateQueryEffectUserInfo", AudioEffectUserResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_PRIVATE_EFFECT, i.a(cls, "updateQueryPrivateEffect", List.class, List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DELETE_PRIVATE_EFFECT_LIST, i.a(cls, "updateDeletePrivateEffectList", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_LOCAL, i.a(cls, "updateSaveEffectToLocal", Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, i.a(cls, "updateSaveEffectToNetwork", AudioEffectAddResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MANUAL_SETTING_EFFECT, i.a(cls, "updateManualSettingEffect", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, i.a(cls, "updateAudioEffectList", new Class[0]));
    }

    public void updateManualSettingEffect() {
        this.mMyEffectListFragment.setSelectItem(-1);
        getCurrentMediaItemAndEffect();
    }

    public void updateAudioEffectList() {
        updateMyEffectList();
    }

    private void getCurrentMediaItemAndEffect() {
        this.mCurrentMediaItem = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (e.a(getActivity()).t() != null) {
            this.mUsedEffect = e.a(getActivity()).t().h();
        }
    }

    private void unLoadMonitor() {
        getActivity().unregisterReceiver(this.mPlayStatusMonitor);
        this.mPlayStatusMonitor = null;
    }

    public void onDestroy() {
        super.onDestroy();
        unLoadMonitor();
    }

    private void loadMonitor() {
        g.a(TAG, "loadMonitor");
        this.mPlayStatusMonitor = new PlayStatusMonitor(this);
        getActivity().registerReceiver(this.mPlayStatusMonitor, this.mPlayStatusMonitor.a());
    }

    public void updateSaveEffectToNetwork(AudioEffectAddResult audioEffectAddResult) {
        setUserInfoView();
    }

    public void updateSaveEffectToLocal(Boolean bool) {
        this.mNeedUpdate = bool.booleanValue();
    }

    public void onResume() {
        super.onResume();
        updateCloudAudioEffectState();
        updateMyEffectList();
    }

    private void updateCloudAudioEffectState() {
        if (com.sds.android.ttpod.framework.storage.environment.b.ag()) {
            this.mIsCloudAudioOpen = true;
        } else {
            this.mIsCloudAudioOpen = false;
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mNeedUpdate) {
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_PRIVATE_EFFECT, new Object[0]));
            this.mNeedUpdate = false;
        }
    }

    public void updateMyEffectList() {
        this.mIsItemClicked = false;
        updateCloudAudioEffectState();
        getCurrentMediaItemAndEffect();
        this.mMyEffectListFragment.notifyDataSetChanged();
    }

    public void updateQueryEffectUserInfo(AudioEffectUserResult audioEffectUserResult) {
        if (audioEffectUserResult.getCode() == 1) {
            AudioEffectUserExp exp = ((AudioEffectUser) audioEffectUserResult.getData()).getExp();
            if (exp != null) {
                int total = exp.getTotal();
                com.sds.android.ttpod.component.a.a a = com.sds.android.ttpod.component.a.b.a(total);
                this.mImgLevel.setImageResource(a.b());
                this.mLevel.setText(a.a());
                int c = a.c() + 1;
                if (total < 10000) {
                    this.mUpgradeLeftScore.setText(getString(R.string.effect_user_detail_update_left_score, Integer.valueOf(c - total)));
                    this.mTotalScore.setText(getString(R.string.effect_user_detail_total_score, Integer.valueOf(c)));
                    this.mEffectLevelProgressbar.setProgress((total * 100) / c);
                    return;
                }
                this.mUpgradeLeftScore.setText(R.string.effect_user_detail_score_max);
                this.mTotalScore.setText("");
                this.mEffectLevelProgressbar.setProgress(100);
            }
        } else if (getUserVisibleHint()) {
            com.sds.android.ttpod.component.d.f.a((int) R.string.network_error);
        }
    }

    public void updateQueryPrivateEffect(List<f> list, List<MediaItem> list2) {
        boolean z;
        this.mLoadingLayer.setState(StateView.b.SUCCESS);
        this.mMyEffectListFragment.updateData(list);
        this.mMediaItemList = list2;
        boolean z2 = list.size() > 0;
        CloudAudioEffectActivity cloudAudioEffectActivity = (CloudAudioEffectActivity) getActivity();
        cloudAudioEffectActivity.setHasPrivateAudioEffect(z2);
        if (cloudAudioEffectActivity.isMyAudioEffectFragmentPage()) {
            z = z2;
        } else {
            z = false;
        }
        cloudAudioEffectActivity.getDeleteAction().c(z);
        this.mNetTemporaryGroupSynced = false;
        if (z2) {
            this.mListTitle.setVisibility(0);
            this.mListTitleTip.setVisibility(4);
            return;
        }
        this.mListTitle.setVisibility(4);
        this.mListTitleTip.setVisibility(0);
    }

    public void updateDeletePrivateEffectList() {
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_PRIVATE_EFFECT, new Object[0]));
    }

    private void initMyAudioEffectViews() {
        this.mDetailView = this.mMyAudioEffectRootView.findViewById(R.id.layout_effect_detail);
        this.mDetailView.setOnClickListener(this.mOnClickListener);
        this.mAvatar = (ImageView) this.mMyAudioEffectRootView.findViewById(R.id.image_avatar);
        this.mUserName = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_username);
        this.mLevel = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_level);
        this.mImgLevel = (ImageView) this.mMyAudioEffectRootView.findViewById(R.id.imageview_level);
        this.mUpgradeLeftScore = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_upgrade_left_score);
        this.mTotalScore = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.textview_score);
        this.mEffectLevelProgressbar = (ProgressBar) this.mMyAudioEffectRootView.findViewById(R.id.progressbar_effect_level);
        this.mWifiTip = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.wifi_tip);
        this.mScoreLayer = (RelativeLayout) this.mMyAudioEffectRootView.findViewById(R.id.textview_upgrade_left_score_layer);
        this.mListTitle = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.myeffect_title);
        this.mListTitleTip = (TextView) this.mMyAudioEffectRootView.findViewById(R.id.myeffect_title_tip);
        this.mLoadingLayer = (StateView) this.mMyAudioEffectRootView.findViewById(R.id.loading_my_effect);
        this.mLoadingLayer.setState(StateView.b.LOADING);
    }

    public void onDeleteActionItemClick() {
        Intent intent = new Intent(getActivity(), SelectDeleteEffectActivity.class);
        SelectDeleteEffectActivity.setEffectList(this.mMyEffectListFragment.getData());
        startActivity(intent);
    }

    private boolean visitNetWifi() {
        return !p.a();
    }

    private void setUserinfo(NewUser newUser, String str) {
        com.sds.android.ttpod.framework.a.g.a(this.mAvatar, str, (int) getResources().getDimension(R.dimen.avatar_width), (int) getResources().getDimension(R.dimen.avatar_height));
        this.mUserName.setText(newUser.getNickName());
    }

    private void setUserInfoView() {
        if (com.sds.android.ttpod.framework.storage.environment.b.av() && visitNetWifi()) {
            this.mLayoutEffectDetail.setVisibility(0);
            if (getUserVisibleHint()) {
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT_USERINFO, new Object[0]));
            }
            NewUser at = com.sds.android.ttpod.framework.storage.environment.b.at();
            setUserinfo(at, at.getAvatarUrl());
            setControlsVisible(0, 4);
        } else if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            this.mLayoutEffectDetail.setVisibility(0);
            setUserinfo(com.sds.android.ttpod.framework.storage.environment.b.at(), null);
            setControlsVisible(4, 0);
        } else {
            this.mLayoutEffectDetail.setVisibility(8);
        }
    }

    private void setControlsVisible(int i, int i2) {
        this.mScoreLayer.setVisibility(i);
        this.mEffectLevelProgressbar.setVisibility(i);
        this.mLevel.setVisibility(i);
        this.mImgLevel.setVisibility(i);
        this.mWifiTip.setVisibility(i2);
    }

    private void initMyEffectFragment() {
        if (this.mMyEffectListFragment == null) {
            this.mMyEffectListFragment = new MyEffectListFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_myeffect, this.mMyEffectListFragment).commit();
        }
    }

    private void performEffectLevelClick() {
        startActivity(new Intent(getActivity(), EffectLevelActivity.class));
    }
}
