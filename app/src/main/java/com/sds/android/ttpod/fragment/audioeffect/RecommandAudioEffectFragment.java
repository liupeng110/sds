package com.sds.android.ttpod.fragment.audioeffect;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AudioEffectItem;
import com.sds.android.cloudapi.ttpod.result.AudioEffectAddResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectCommResult;
import com.sds.android.cloudapi.ttpod.result.AudioEffectItemResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.a.c;
import com.sds.android.ttpod.component.d.a.o;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.core.audioeffect.AudioEffectParam;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.audioeffect.CircularProgress;
import com.sds.android.ttpod.widget.audioeffect.EqualizerAnimationWaveView;
import com.sds.android.ttpod.widget.audioeffect.RadialProgressWidget;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommandAudioEffectFragment extends BaseFragment {
    private static final int DEFAULT_PAGE = 1;
    private static final int DELAY_UPDATE_LIST_TIME = 800;
    private static final int EFFECT_PICK_REPEAT = 20001;
    private static final int EFFECT_STATUS_USE_LOCAL = 4;
    private static final int EFFECT_STATUS_USE_UNINIT = 0;
    private static final int PAGE_SIZE = 20;
    private static final int START_PAGE = 1;
    private BaseAdapter mAdapter;
    private long mContinuesTime = 0;
    private List<AudioEffectItem> mDatas;
    private DataListFooterView mFooterView;
    private boolean mIsItemClicked = false;
    private boolean mIsLoading = true;
    private ListView mListView;
    private OnClickListener mListener = new OnClickListener(this) {
        final /* synthetic */ RecommandAudioEffectFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.hit(((Integer) view.getTag()).intValue());
        }
    };
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ RecommandAudioEffectFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layout_play:
                    this.a.performPickClick(((Integer) view.getTag()).intValue());
                    return;
                default:
                    return;
            }
        }
    };
    private OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ RecommandAudioEffectFragment a;

        {
            this.a = r1;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (m.b(i, i2, i3) && !this.a.mIsLoading) {
                if (this.a.mPager.a() >= this.a.mPager.g()) {
                    this.a.mFooterView.c();
                    return;
                }
                this.a.mIsLoading = true;
                this.a.mFooterView.a();
                this.a.requestRecommendsEffect(Integer.valueOf(this.a.mPager.d()));
                this.a.mPager.c(this.a.mPager.d());
            }
        }
    };
    private q mPager = new q();
    private HashMap<String, Boolean> mPickedHashMap = new HashMap();
    private View mRecommandAudioEffectRootView;
    private StateView mStateView;

    private class a extends BaseAdapter {
        final /* synthetic */ RecommandAudioEffectFragment a;
        private int b;

        private a(RecommandAudioEffectFragment recommandAudioEffectFragment) {
            this.a = recommandAudioEffectFragment;
            this.b = -1;
        }

        public int getCount() {
            return this.a.mDatas == null ? 0 : this.a.mDatas.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.a.getActivity(), R.layout.effect_recommend_item_new, null);
                view.setTag(new b(this.a, view));
            }
            b bVar = (b) view.getTag();
            AudioEffectItem audioEffectItem = (AudioEffectItem) this.a.mDatas.get(i);
            g.a(bVar.b, audioEffectItem.getPic(), com.sds.android.ttpod.common.c.a.a(40), com.sds.android.ttpod.common.c.a.a(40), (int) R.drawable.img_avatar_default);
            bVar.c.setText(audioEffectItem.getNickName());
            bVar.d.setImageResource(com.sds.android.ttpod.component.a.b.a(audioEffectItem.getTotal()).b());
            bVar.e.setText(audioEffectItem.getPickCount() + "");
            if (!this.a.mIsItemClicked) {
                AudioEffectParam t = e.a(BaseApplication.e()).t();
                if (t == null || !com.sds.android.sdk.lib.util.m.a(audioEffectItem.getID(), t.i()) || t.h() == 0 || t.h() == 4) {
                    this.b = -1;
                } else {
                    this.b = i;
                }
            }
            bVar.h.setValue(RadialProgressWidget.b(audioEffectItem.getDataBass()));
            bVar.i.setValue(RadialProgressWidget.b(audioEffectItem.getDataTreble()));
            bVar.j.setValue(RadialProgressWidget.b(audioEffectItem.getDataVirtualizer()));
            bVar.f.setOnClickListener(this.a.mOnClickListener);
            bVar.f.setTag(Integer.valueOf(i));
            c.a(bVar.g, audioEffectItem.getDataEqualizer());
            bVar.k.setSelected(i == this.b);
            bVar.l.setTag(Integer.valueOf(i));
            bVar.m.setTag(Integer.valueOf(i));
            return view;
        }

        public void a(int i) {
            this.b = i;
        }
    }

    private class b {
        final /* synthetic */ RecommandAudioEffectFragment a;
        private ImageView b;
        private TextView c;
        private ImageView d;
        private TextView e;
        private View f;
        private EqualizerAnimationWaveView g;
        private CircularProgress h;
        private CircularProgress i;
        private CircularProgress j;
        private RelativeLayout k;
        private RelativeLayout l;
        private RelativeLayout m;

        public b(RecommandAudioEffectFragment recommandAudioEffectFragment, View view) {
            this.a = recommandAudioEffectFragment;
            this.b = (ImageView) view.findViewById(R.id.imageview_avatar);
            this.c = (TextView) view.findViewById(R.id.textview_nickname);
            this.d = (ImageView) view.findViewById(R.id.imageview_level);
            this.e = (TextView) view.findViewById(R.id.textview_pickcount);
            this.f = view.findViewById(R.id.layout_play);
            this.g = (EqualizerAnimationWaveView) view.findViewById(R.id.waveview_effect_equalizer_equ);
            this.h = (CircularProgress) view.findViewById(R.id.rotatebutton_bass_boost);
            this.h.setPaintText(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY);
            this.i = (CircularProgress) view.findViewById(R.id.rotatebutton_treble_boost);
            this.i.setPaintText("T");
            this.j = (CircularProgress) view.findViewById(R.id.rotatebutton_surround);
            this.j.setPaintText("S");
            this.l = (RelativeLayout) view.findViewById(R.id.recommand_left);
            this.m = (RelativeLayout) view.findViewById(R.id.recommand_middle);
            this.l.setOnClickListener(recommandAudioEffectFragment.mListener);
            this.m.setOnClickListener(recommandAudioEffectFragment.mListener);
            this.k = (RelativeLayout) view.findViewById(R.id.root_hit);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRecommandAudioEffectRootView == null) {
            this.mRecommandAudioEffectRootView = layoutInflater.inflate(R.layout.activity_others_effect, viewGroup, false);
            initRecommandAudioEffectViews();
        }
        return this.mRecommandAudioEffectRootView;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_QUERY_EFFECT, i.a(cls, "updateQueryEffect", AudioEffectItemResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PICK_EFFECT, i.a(cls, "updatePickEffect", AudioEffectCommResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SAVE_EFFECT_TO_NETWORK, i.a(cls, "updateSaveEffectToNetwork", AudioEffectAddResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MANUAL_SETTING_EFFECT, i.a(cls, "updateManualSettingEffect", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_AUDIO_EFFECT_INFO, i.a(cls, "updateAudioEffectList", new Class[0]));
    }

    public void updateAudioEffectList() {
        notifyUpdateRecommandList();
    }

    public void updateManualSettingEffect() {
        ((a) this.mAdapter).a(-1);
        this.mAdapter.notifyDataSetChanged();
    }

    public void notifyUpdateRecommandList() {
        this.mIsItemClicked = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        initData();
    }

    public void updateRecommandList() {
        if (this.mContinuesTime == 0) {
            this.mContinuesTime = System.currentTimeMillis();
            do {
            } while (System.currentTimeMillis() - this.mContinuesTime < 800);
            this.mContinuesTime = 0;
            initData();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void updateSaveEffectToNetwork(AudioEffectAddResult audioEffectAddResult) {
        if (audioEffectAddResult.getCode() == 1) {
            initData();
        }
    }

    public void updateQueryEffect(AudioEffectItemResult audioEffectItemResult) {
        this.mIsItemClicked = false;
        if (audioEffectItemResult.getCode() == 1) {
            int allPage = audioEffectItemResult.getAllPage();
            Object effectList = audioEffectItemResult.getEffectList();
            if (effectList.size() == 0) {
                this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
                this.mListView.setVisibility(4);
            } else {
                this.mListView.setVisibility(0);
                this.mPager.b(allPage);
                if (this.mPager.a() > 1) {
                    this.mDatas.addAll(effectList);
                    this.mFooterView.c();
                } else {
                    this.mPickedHashMap.clear();
                    this.mDatas = effectList;
                }
                this.mAdapter.notifyDataSetChanged();
                this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            }
        } else {
            f.a(getString(R.string.string_effect_net_error));
            if (this.mDatas == null || this.mDatas.size() == 0) {
                this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
            }
        }
        this.mIsLoading = false;
    }

    public void updatePickEffect(AudioEffectCommResult audioEffectCommResult, String str) {
        if (audioEffectCommResult.getCode() == 1) {
            com.sds.android.ttpod.b.e.a();
            f.a(getString(R.string.string_effect_Liked));
            for (int i = 0; i < this.mDatas.size(); i++) {
                AudioEffectItem audioEffectItem = (AudioEffectItem) this.mDatas.get(i);
                if (audioEffectItem.getID() != null && audioEffectItem.getID().equals(str)) {
                    audioEffectItem.setPickCount(audioEffectItem.getPickCount() + 1);
                    break;
                }
            }
            this.mAdapter.notifyDataSetChanged();
        } else if (audioEffectCommResult.getCode() == 20001) {
            f.a("已经点过赞了.");
        } else {
            f.a(getString(R.string.string_effect_unliked));
        }
    }

    private void initData() {
        this.mIsItemClicked = false;
        if (this.mDatas != null) {
            this.mDatas.clear();
            this.mListView.setAdapter(this.mAdapter);
            this.mPager.c(1);
            this.mIsLoading = true;
            ((a) this.mAdapter).a(-1);
            this.mAdapter.notifyDataSetChanged();
        }
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        requestRecommendsEffect(Integer.valueOf(1));
    }

    private void requestRecommendsEffect(Integer num) {
        requestRecommendsEffect(num, Integer.valueOf(20));
    }

    private void requestRecommendsEffect(Integer num, Integer num2) {
        if (com.sds.android.ttpod.framework.storage.a.a.a().M().isNull()) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.NO_DATA);
            return;
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_EFFECT, r0, num, num2));
    }

    private void performActionItemHelpClick() {
        new o(getActivity()).show();
    }

    private void initRecommandAudioEffectViews() {
        this.mStateView = (StateView) this.mRecommandAudioEffectRootView.findViewById(R.id.stateview_recommends_effect);
        this.mListView = (ListView) this.mRecommandAudioEffectRootView.findViewById(R.id.listview_others_effect);
        this.mListView.setFooterDividersEnabled(false);
        this.mListView.setBackgroundColor(Color.parseColor("#1d1f1f"));
        this.mFooterView = new DataListFooterView(getActivity());
        this.mFooterView.setBackgroundResource(R.color.effect_dialog_background);
        this.mFooterView.setTextColor(Color.parseColor("#4f5051"));
        this.mListView.addFooterView(this.mFooterView);
        this.mAdapter = new a();
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
    }

    private void hit(int i) {
        int headerViewsCount = i + this.mListView.getHeaderViewsCount();
        this.mIsItemClicked = true;
        ((a) this.mAdapter).a(i);
        this.mAdapter.notifyDataSetChanged();
        performPlayClick(headerViewsCount, true);
        performSaveClick(i);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_LOCAL_AUDIO_EFFECT, Boolean.valueOf(false)));
        com.sds.android.ttpod.framework.a.b.f.c();
        t.a("PAGE_CLICK", r.ACTION_EFFECT_APPLY, s.PAGE_AUDIO_CLOUD_EFFECT, s.PAGE_NONE);
    }

    public void onThemeLoaded() {
    }

    private void performPlayClick(int i, boolean z) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (a != -1) {
            AudioEffectItem audioEffectItem = (AudioEffectItem) this.mDatas.get(a);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_CLOUD_AUDIO_EFFECT, audioEffectItem, Boolean.valueOf(z)));
        }
    }

    private void performPickClick(int i) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            AudioEffectItem audioEffectItem = (AudioEffectItem) this.mDatas.get(i);
            if (Boolean.TRUE.equals(this.mPickedHashMap.get(audioEffectItem.getID()))) {
                f.a(getString(R.string.string_effect_already_liked));
            } else {
                com.sds.android.ttpod.b.e.a(audioEffectItem.getID());
                this.mPickedHashMap.put(audioEffectItem.getID(), Boolean.valueOf(true));
            }
            com.sds.android.ttpod.framework.a.b.f.b();
            t.a("PAGE_CLICK", r.ACTION_EFFECT_LIKE, s.PAGE_AUDIO_CLOUD_EFFECT, s.PAGE_NONE);
            return;
        }
        f.a(getString(R.string.string_effect_unlogin));
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    private Boolean isPickedEffect(String str) {
        return (Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_PICKED_EFFECT, str), Boolean.class);
    }

    private void performSaveClick(int i) {
        saveCloudAudioEffect((AudioEffectItem) this.mDatas.get(i));
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.BIND_EFFECT, r0.getID()));
        if (!com.sds.android.ttpod.framework.storage.environment.b.ag()) {
            com.sds.android.ttpod.framework.storage.environment.b.C(true);
        }
        f.a(getString(R.string.cloud_effect_use_sucess));
    }

    private void saveCloudAudioEffect(AudioEffectItem audioEffectItem) {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        String title = M.getTitle();
        String artist = M.getArtist();
        Long songID = M.getSongID();
        com.sds.android.ttpod.framework.modules.core.audioeffect.a aVar = new com.sds.android.ttpod.framework.modules.core.audioeffect.a();
        aVar.a(audioEffectItem.getID());
        aVar.a(songID);
        aVar.b(artist);
        aVar.c(title);
        aVar.a(audioEffectItem.getStyle());
        aVar.b(audioEffectItem.getOutput());
        aVar.d(audioEffectItem.getDevice());
        aVar.c(audioEffectItem.getTotal());
        aVar.d(audioEffectItem.getPickCount());
        aVar.f(audioEffectItem.getNickName());
        aVar.e(audioEffectItem.getPic());
        aVar.a((long) audioEffectItem.getUserId());
        aVar.e(audioEffectItem.getDataBass());
        aVar.f(audioEffectItem.getDataTreble());
        aVar.g(audioEffectItem.getDataVirtualizer());
        aVar.h(audioEffectItem.getDataReverb());
        aVar.a(audioEffectItem.getDataBalance());
        aVar.a(audioEffectItem.getDataIsLimit());
        aVar.a(audioEffectItem.getDataEqualizer());
        aVar.b(System.currentTimeMillis());
        if (!com.sds.android.sdk.lib.util.m.a(M.getLocalDataSource())) {
            aVar.g(M.getLocalDataSource());
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT_TO_LOCAL, M, aVar));
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()));
    }
}
