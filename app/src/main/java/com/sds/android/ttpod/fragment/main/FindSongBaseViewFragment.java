package com.sds.android.ttpod.fragment.main;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FindSongHotListData;
import com.sds.android.cloudapi.ttpod.data.ForwardAction;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.StyleDataListResult;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.PostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.SlidingAlbumDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.i.d;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.WebSlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.BestAlbumFromNewSongFragment;
import com.sds.android.ttpod.fragment.main.findsong.DiscoveryFragment;
import com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment;
import com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment;
import com.sds.android.ttpod.fragment.main.findsong.PrivateCustomFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubChannelDetailFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubDailyRecommendFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubPopularSongFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubSceneRecommendFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubSelectChannelFragment;
import com.sds.android.ttpod.fragment.main.findsong.SubSongCategoryDetailFragment;
import com.sds.android.ttpod.fragment.musiccircle.MusicCircleEntryFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.fragment.mv.MVFragment;
import com.sds.android.ttpod.framework.a.b.d.j;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.tencent.open.SocialConstants;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;

public class FindSongBaseViewFragment extends BaseFragment implements d {
    private static final int FORWARD_ALBUM_DETAIL = 19;
    private static final int FORWARD_BEST_ALBUM = 10;
    private static final int FORWARD_CATEGORY_DETAIL = 4;
    private static final int FORWARD_CHANNEL_DETAIL = 16;
    private static final int FORWARD_DAILY_RECOMMEND = 13;
    private static final int FORWARD_DISCOVERY = 8;
    private static final int FORWARD_MUSIC_CIRCLE = 7;
    private static final int FORWARD_MV_DETAIL = 18;
    private static final int FORWARD_MV_MORE = 200;
    private static final int FORWARD_NEW_ALBUM = 11;
    @Deprecated
    private static final int FORWARD_NEW_SONG_PUBLISH = 9;
    private static final int FORWARD_NONE = 0;
    private static final int FORWARD_PERSONALIZED = 12;
    private static final int FORWARD_POPULAR_SONG = 6;
    private static final int FORWARD_POST_DETAIL = 5;
    private static final int FORWARD_SCENE_RECOMMEND = 15;
    private static final int FORWARD_SELECT_CHANNEL = 17;
    @Deprecated
    private static final int FORWARD_SONG_LIST_SQUARE = 2;
    private static final int FORWARD_UNICOM_OPEN = 14;
    private static final int FORWARD_USER_HOME = 3;
    private static final int FORWARD_WEB = 1;
    private static final SparseArray<Method> METHODS = new SparseArray();
    protected static final RecommendData NULL_RECOMMEND_DATA = new RecommendData() {
        public long getId() {
            return 0;
        }

        public String getTag() {
            return "";
        }

        public Integer getTagIntValue() {
            return Integer.valueOf(-1);
        }

        public String getName() {
            return "";
        }

        public String getPicUrl() {
            return "";
        }

        public String getDesc() {
            return "";
        }

        public ForwardAction getForwardAction() {
            return new ForwardAction();
        }
    };
    private static final int POSITION_FORWARD_MORE = -1;
    private static final String TAG = FindSongBaseViewFragment.class.getSimpleName();
    private static final HashSet<Integer> TYPES_NEED_VALUE = new HashSet();
    private static final SparseIntArray TYPE_STO = new SparseIntArray();
    private b mDelayRequestImage = new b();
    private boolean mShow;
    protected boolean mThemeReload = true;
    protected View mTitleBarView;
    private int mUserType;

    static {
        TYPES_NEED_VALUE.add(Integer.valueOf(1));
        TYPES_NEED_VALUE.add(Integer.valueOf(3));
        TYPES_NEED_VALUE.add(Integer.valueOf(4));
        TYPES_NEED_VALUE.add(Integer.valueOf(5));
        TYPE_STO.put(1, s.PAGE_ONLINE_POST_DETAIL_WEB.getValue());
        TYPE_STO.put(3, s.PAGE_ONLINE_MUSIC_CIRCLE_USER_HOME.getValue());
        TYPE_STO.put(4, s.PAGE_ONLINE_SONG_CATEGORY.getValue());
        TYPE_STO.put(5, s.PAGE_ONLINE_POST_DETAIL.getValue());
        TYPE_STO.put(6, s.PAGE_ONLINE_POPULAR_SONG.getValue());
        TYPE_STO.put(7, s.PAGE_ONLINE_MUSIC_CIRCLE_MY_HOME.getValue());
        TYPE_STO.put(8, s.PAGE_ONLINE_DISCOVERY.getValue());
        TYPE_STO.put(10, s.PAGE_ONLINE_BEST_ALBUM_FROM_NEW_SONG.getValue());
        TYPE_STO.put(11, s.PAGE_ONLINE_NEW_ALBUM.getValue());
        TYPE_STO.put(12, s.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue());
        TYPE_STO.put(13, s.PAGE_ONLINE_DAILY_RECOMMEND.getValue());
        TYPE_STO.put(14, s.PAGE_UNICOM_ORDER.getValue());
        TYPE_STO.put(15, s.PAGE_ONLINE_SCENE_RECOMMEND.getValue());
        TYPE_STO.put(16, s.PAGE_ONLINE_CHANNEL_DETAIL.getValue());
        TYPE_STO.put(17, s.PAGE_ONLINE_SELECT_CHANNEL.getValue());
        try {
            Class cls = FindSongBaseViewFragment.class;
            METHODS.put(0, i.a(cls, "forwardAbnormal", Integer.class));
            METHODS.put(1, i.a(cls, "forwardWeb", Integer.class));
            METHODS.put(3, i.a(cls, "forwardMusicCircleUserHome", Integer.class));
            METHODS.put(4, i.a(cls, "forwardCategoryDetail", Integer.class));
            METHODS.put(5, i.a(cls, "forwardPostDetail", Integer.class));
            METHODS.put(6, i.a(cls, "forwardPopularSong", Integer.class));
            METHODS.put(7, i.a(cls, "forwardMusicCircle", Integer.class));
            METHODS.put(8, i.a(cls, "forwardDiscovery", Integer.class));
            METHODS.put(10, i.a(cls, "forwardBestAlbum", Integer.class));
            METHODS.put(11, i.a(cls, "forwardNewAlbum", Integer.class));
            METHODS.put(12, i.a(cls, "forwardPersonalized", Integer.class));
            METHODS.put(13, i.a(cls, "forwardDailyRecommend", Integer.class));
            METHODS.put(14, i.a(cls, "forwardUnicomOpen", Integer.class));
            METHODS.put(15, i.a(cls, "forwardSceneRecommend", Integer.class));
            METHODS.put(16, i.a(cls, "forwardChannelDetail", Integer.class));
            METHODS.put(17, i.a(cls, "forwardSelectChannel", Integer.class));
            METHODS.put(18, i.a(cls, "forwardMVDetail", Integer.class));
            METHODS.put(19, i.a(cls, "forwardAlbumDetail", Integer.class));
            METHODS.put(200, i.a(cls, "forwardMVMore", Integer.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRequestIdForCommandParallel() {
        return String.valueOf(getModuleId());
    }

    protected void forward(Integer num) {
        com.sds.android.ttpod.framework.a.b.i.a(348, getModuleId());
        if (TYPES_NEED_VALUE.contains(Integer.valueOf(getForwardType(num.intValue()))) && m.a(getForwardValue(num.intValue()))) {
            showSorry();
            return;
        }
        try {
            Method method = (Method) METHODS.get(getForwardType(num.intValue()));
            if (method != null) {
                method.invoke(this, new Object[]{num});
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        forwardAbnormal(num);
    }

    private void forwardAbnormal(Integer num) {
        g.c(TAG, "forwardAbnormal");
        showSorry();
    }

    private void forwardMusicCircleUserHome(Integer num) {
        try {
            Long valueOf = Long.valueOf(Long.parseLong(getForwardValue(num.intValue())));
            if (b.av()) {
                launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUserId(valueOf.longValue(), ""));
            } else {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        } catch (NumberFormatException e) {
            showSorry();
            e.printStackTrace();
        }
    }

    private void forwardCategoryDetail(Integer num) {
        launchFragment(new SubSongCategoryDetailFragment(getForwardValue(num.intValue())));
    }

    private void forwardDailyRecommend(Integer num) {
        RecommendData itemData = getItemData(num.intValue());
        launchFragment(new SubDailyRecommendFragment(itemData.getId(), itemData.getDesc(), itemData.getName()));
    }

    private void forwardPostDetail(Integer num) {
        try {
            PostDetailFragment.b personalizedRecommendationInfo = getPersonalizedRecommendationInfo(getItemData(num.intValue()));
            BaseFragment createById = SubPostDetailFragment.createById(Long.valueOf(getForwardValue(num.intValue())).longValue(), getModuleName());
            createById.setPersonalizedRecommendInfo(personalizedRecommendationInfo);
            launchFragment(createById);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            showSorry();
        }
    }

    private PostDetailFragment.b getPersonalizedRecommendationInfo(RecommendData recommendData) {
        int recommentType;
        int i = 0;
        if (recommendData instanceof FindSongHotListData) {
            recommentType = ((FindSongHotListData) recommendData).getRecommentType();
            i = ((FindSongHotListData) recommendData).getRecommentAlgorithm();
        } else {
            recommentType = 0;
        }
        return new PostDetailFragment.b(this.mUserType, recommentType, i);
    }

    private void forwardMusicCircle(Integer num) {
        if (!b.av()) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else if (b.aP()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("new_flag", false);
            bundle.putSerializable("user", b.at());
            BaseFragment musicCircleEntryFragment = new MusicCircleEntryFragment();
            musicCircleEntryFragment.setArguments(bundle);
            launchFragment(musicCircleEntryFragment);
        } else {
            launchFragment(new IPUnSupportedFragment());
        }
    }

    private void forwardWeb(Integer num) {
        launchFragment(WebSlidingClosableFragment.instantiate(getForwardValue(num.intValue()), getItemName(num.intValue()), getItemPicUrl(num.intValue()), true, false));
    }

    private void forwardNewSongPublish(Integer num) {
        launchFragment(new NewSongPublishFragment());
    }

    private void forwardUnicomOpen(Integer num) {
        f.c(getActivity());
    }

    private void forwardPopularSong(Integer num) {
        launchFragment(new SubPopularSongFragment(getItemData(num.intValue()).getId()));
    }

    private void forwardSceneRecommend(Integer num) {
        launchFragment(new SubSceneRecommendFragment(getItemData(num.intValue()).getId()));
    }

    private void forwardDiscovery(Integer num) {
        BaseFragment discoveryFragment = new DiscoveryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", getItemName(num.intValue()));
        discoveryFragment.setArguments(bundle);
        launchFragment(discoveryFragment);
    }

    private void forwardBestAlbum(Integer num) {
        launchFragment(new BestAlbumFromNewSongFragment());
    }

    private void forwardNewAlbum(Integer num) {
        launchFragment(new NewAlbumFragment());
    }

    private void forwardPersonalized(Integer num) {
        String moduleName = m.a(getItemName(num.intValue())) ? getModuleName() : getItemName(num.intValue());
        BaseFragment privateCustomFragment = new PrivateCustomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", moduleName);
        privateCustomFragment.setArguments(bundle);
        launchFragment(privateCustomFragment);
    }

    private void forwardChannelDetail(Integer num) {
        BaseFragment subChannelDetailFragment = new SubChannelDetailFragment();
        subChannelDetailFragment.setArguments(buildArguments(num));
        launchFragment(subChannelDetailFragment);
    }

    private void forwardSelectChannel(Integer num) {
        BaseFragment subSelectChannelFragment = new SubSelectChannelFragment();
        subSelectChannelFragment.setArguments(buildArguments(num));
        launchFragment(subSelectChannelFragment);
    }

    private void forwardMVDetail(final Integer num) {
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ FindSongBaseViewFragment b;

            public void run() {
                try {
                    String valueOf = String.valueOf(this.b.getModuleId());
                    t.a().a(valueOf);
                    j.a("mv_origin", valueOf);
                    VideoPlayManager.a(this.b.getActivity(), Integer.valueOf(Integer.parseInt(this.b.getForwardValue(num.intValue()))));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    this.b.showSorry();
                }
            }
        }, 30);
    }

    private void forwardAlbumDetail(Integer num) {
        try {
            launchFragment(SlidingAlbumDetailFragment.instantiate(Long.valueOf(getForwardValue(num.intValue())).longValue(), getItemName(num.intValue())));
        } catch (Exception e) {
            e.printStackTrace();
            forwardAbnormal(num);
        }
    }

    private void forwardMVMore(Integer num) {
        launchFragment(new MVFragment(getString(R.string.category_mv_zone)));
    }

    private Bundle buildArguments(Integer num) {
        Bundle bundle = new Bundle();
        bundle.putLong(StarCategory.KEY_STAR_CATEGORY_ID, getItemId(num));
        bundle.putString(User.KEY_AVATAR, getItemPicUrl(num.intValue()));
        bundle.putString("name", getItemName(num.intValue()));
        bundle.putString(SocialConstants.PARAM_APP_DESC, getItemData(num.intValue()).getDesc());
        bundle.putString("message", getForwardAction(num.intValue()).getValue());
        return bundle;
    }

    private String getModuleName() {
        return getModuleData().getName();
    }

    private void showSorry() {
        com.sds.android.ttpod.component.d.f.a((int) R.string.sorry);
    }

    public void setUserType(int i) {
        this.mUserType = i;
    }

    public void onThemeChanged() {
        this.mThemeReload = true;
        super.onThemeChanged();
    }

    protected void setTitleBarTheme() {
        c.a(this.mTitleBarView.findViewById(R.id.id_text_title), ThemeElement.TILE_TEXT);
        c.a(this.mTitleBarView.findViewById(R.id.id_text_more), ThemeElement.TILE_SUB_TEXT);
        c.a(this.mTitleBarView.findViewById(R.id.id_title_bar_layout), ThemeElement.TILE_BACKGROUND);
        c.a(this.mTitleBarView.findViewById(R.id.id_title_bar_left_line), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        v.a((IconTextView) this.mTitleBarView.findViewById(R.id.img_arrow_more), ThemeElement.TILE_SUB_TEXT);
    }

    protected View createTitleBarView(LayoutInflater layoutInflater) {
        this.mTitleBarView = layoutInflater.inflate(R.layout.find_song_title_bar, null);
        ((TextView) this.mTitleBarView.findViewById(R.id.id_text_title)).setText(getModuleName());
        setTitleBarTheme();
        if (getForwardType(-1) == 0) {
            this.mTitleBarView.findViewById(R.id.layout_for_more).setVisibility(8);
        } else {
            bindTitleBarForForwardMore();
        }
        return this.mTitleBarView;
    }

    private void bindTitleBarForForwardMore() {
        View findViewById = this.mTitleBarView.findViewById(R.id.layout_for_more);
        if (getForwardType(-1) == 17) {
            ((TextView) this.mTitleBarView.findViewById(R.id.id_text_more)).setText(R.string.find_song_change);
        }
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FindSongBaseViewFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.doFindSongStatistic(-1, r.ACTION_CLICK_ONLINE_FIND_SONG_MODULE_MORE);
                com.sds.android.ttpod.framework.a.b.i.a(322, this.a.getModuleData().getId());
                this.a.aliStats(-1, "find_song_more");
                this.a.forward(Integer.valueOf(-1));
            }
        });
    }

    protected OnClickListener createItemOnClickListener(final int i) {
        return new OnClickListener(this) {
            final /* synthetic */ FindSongBaseViewFragment b;

            public void onClick(View view) {
                this.b.clickItem(i);
            }
        };
    }

    protected void clickItem(int i) {
        doFindSongStatistic(i, r.ACTION_CLICK_ONLINE_FIND_SONG_ITEM);
        com.sds.android.ttpod.framework.a.b.i.a(323, getItemData(i).getId());
        aliStats(i, "find_song_item");
        forward(Integer.valueOf(i));
    }

    protected void aliStats(int i, String str) {
        View view = getView();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        String valueOf = String.valueOf(getModuleId());
        t.a().a(valueOf);
        RecommendData itemData = getItemData(i);
        com.sds.android.ttpod.framework.a.b.b a = new com.sds.android.ttpod.framework.a.b.b().c(valueOf).d("recommend_" + getModuleName()).b(str).a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(itemData.getId())).a("name", String.valueOf(itemData.getName())).a("location", String.valueOf(viewGroup.indexOfChild(view) + 1)).a("scm", itemData.getScm());
        if (itemData.getForwardAction().getType() == 5) {
            a.a("songlist_id", itemData.getForwardAction().getValue());
        }
        a.a();
    }

    protected int getDataListSize() {
        return getDataList().size();
    }

    protected List<RecommendData> getDataList() {
        return getModuleData().getDataList();
    }

    protected String getModuleDataType() {
        return d.a(getModuleData());
    }

    protected StyleDataListResult getModuleData() {
        return (StyleDataListResult) getArguments().getParcelableArrayList("result").get(0);
    }

    protected RecommendData getItemData(int i) {
        if (i == -1) {
            return NULL_RECOMMEND_DATA;
        }
        if (i >= getDataListSize()) {
            return NULL_RECOMMEND_DATA;
        }
        RecommendData recommendData = (RecommendData) getDataList().get(i);
        return recommendData == null ? NULL_RECOMMEND_DATA : recommendData;
    }

    private long getModuleId() {
        return getModuleData().getId();
    }

    private long getItemId(Integer num) {
        return num.intValue() == -1 ? getModuleId() : getItemData(num.intValue()).getId();
    }

    private String getItemName(int i) {
        String moduleName = i == -1 ? getModuleName() : getItemData(i).getName();
        return moduleName == null ? "" : moduleName;
    }

    private String getItemPicUrl(int i) {
        return getItemData(i).getPicUrl();
    }

    private ForwardAction getForwardAction(int i) {
        return i == -1 ? getModuleData().getForwardAction() : getItemData(i).getForwardAction();
    }

    private int getForwardType(int i) {
        return getForwardAction(i).getType();
    }

    private String getForwardValue(int i) {
        return getForwardAction(i).getValue();
    }

    protected boolean isSongListItemInSongListModule(int i) {
        return isInSongListModule() && getItemData(i).getForwardAction().getType() == 5;
    }

    protected boolean isInSongListModule() {
        return getModuleDataType().equals("song_list");
    }

    protected boolean isNeedQuickPlay(int i) {
        int type = getItemData(i).getForwardAction().getType();
        return type == 15 || type == 16;
    }

    protected OnClickListener createQuickPlayListener(final int i) {
        return new OnClickListener(this) {
            final /* synthetic */ FindSongBaseViewFragment b;

            public void onClick(View view) {
                this.b.doFindSongStatistic(i, r.ACTION_CLICK_FIND_SONG_QUICK_PLAY);
                t.a().a(String.valueOf(this.b.getModuleId()));
                this.b.aliStats(-1, "quick_play");
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.FIND_SONG_QUICK_PLAY, Long.valueOf(this.b.getItemId(Integer.valueOf(i)))));
                this.b.afterQuickPlay(i);
            }
        };
    }

    protected void afterQuickPlay(int i) {
    }

    protected void doFindSongStatistic(int i, r rVar) {
        String str = "PAGE_CLICK";
        SEvent append = new SUserEvent("PAGE_CLICK", rVar.getValue(), s.PAGE_ONLINE_FIND_SONG.getValue(), TYPE_STO.get(getForwardType(i))).append("forward_type", Integer.valueOf(getForwardType(i))).append("forward_value", getForwardValue(i)).append("module_id", Long.valueOf(getModuleId())).append("module_name", getModuleName()).append("item_id", Long.valueOf(getItemData(i).getId())).append("item_name", getItemName(i)).append("position", Integer.valueOf(i + 1));
        append.setPageParameter(true);
        append.post();
    }

    public void onScroll() {
        View view = getView();
        if (!this.mShow && view != null && view.getGlobalVisibleRect(new Rect())) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            this.mShow = true;
            onFirstShow(view, viewGroup);
        }
    }

    private void onFirstShow(View view, ViewGroup viewGroup) {
        g.a(TAG, "onFirstShow  " + getModuleName());
        new com.sds.android.ttpod.framework.a.b.c("show").a("location", String.valueOf(viewGroup.indexOfChild(view) + 1)).a("module_id", String.valueOf(getModuleId())).a("module_name", String.valueOf(getModuleName())).a();
        this.mDelayRequestImage.a();
    }

    protected void requestImage(ImageView imageView, String str, int i, int i2, int i3) {
        if (this.mShow) {
            this.mDelayRequestImage.b(imageView, str, i, i2, i3);
        } else {
            this.mDelayRequestImage.a(imageView, str, i, i2, i3);
        }
    }
}
