package com.sds.android.ttpod.fragment.main.findsong;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.sds.android.cloudapi.ttpod.data.RadioCategory;
import com.sds.android.cloudapi.ttpod.data.RadioCategoryChannel;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.findsong.base.DoubleItemGridSectionListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.skin.view.Animation;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.online.MovementImage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RadioCategoryFragment extends DoubleItemGridSectionListFragment<RadioCategoryChannel> {
    private static final int ALPHA = 180;
    private static final int CHANNEL_CHANGE = 4;
    private static final int CHANNEL_IDLE = 1;
    private static final int CHANNEL_PAUSE = 3;
    private static final int CHANNEL_PLAY = 2;
    private static final int ITEM_NUM_IN_LINE = 3;
    private static final int NONE = -1;
    private static final int SPACING = 32;
    private static final int SPEED = 1;
    private static final String TAG = "RadioCategoryFragment";
    private int mActiveChannelId = -1;
    private String mActiveChannelTitle = "";
    private Bitmap mArtistBitmap;
    private int mCurrentChannelState = 1;
    private boolean mNetMusicListNeedSynced = true;
    private MediaItem mPlayingListLastMediaItem;
    private RadioCategoryChannel mRadioCategoryChannel;
    private String mTitle;

    private class a extends b<RadioCategoryChannel> {
        final /* synthetic */ RadioCategoryFragment b;

        private a(RadioCategoryFragment radioCategoryFragment) {
            this.b = radioCategoryFragment;
        }

        protected /* synthetic */ void b(com.sds.android.ttpod.adapter.c.a aVar, Object obj) {
            a(aVar, (RadioCategoryChannel) obj);
        }

        protected View b(ViewGroup viewGroup) {
            View inflate = this.a.inflate(R.layout.radio_category_section_subitem_view, viewGroup, false);
            com.sds.android.ttpod.adapter.c.a aVar = new com.sds.android.ttpod.adapter.c.a(inflate.findViewById(R.id.song_item1));
            LayoutParams layoutParams = aVar.g().getLayoutParams();
            layoutParams.height = (com.sds.android.ttpod.common.c.a.d() - 32) / 3;
            aVar.g().setLayoutParams(layoutParams);
            com.sds.android.ttpod.adapter.c.a aVar2 = new com.sds.android.ttpod.adapter.c.a(inflate.findViewById(R.id.song_item2));
            inflate.setTag(new com.sds.android.ttpod.adapter.c.a[]{aVar, aVar2});
            LayoutParams layoutParams2 = aVar2.g().getLayoutParams();
            layoutParams2.height = (com.sds.android.ttpod.common.c.a.d() - 32) / 3;
            aVar2.g().setLayoutParams(layoutParams2);
            return inflate;
        }

        protected String a(RadioCategoryChannel radioCategoryChannel) {
            return this.b.getString(R.string.radio_mhz, radioCategoryChannel.getCategoryChannelName());
        }

        protected String b(RadioCategoryChannel radioCategoryChannel) {
            return this.b.getString(R.string.count_of_media, Integer.valueOf(radioCategoryChannel.getCount()));
        }

        protected void c(RadioCategoryChannel radioCategoryChannel) {
            this.b.performItemClick(radioCategoryChannel);
        }

        protected void a(com.sds.android.ttpod.adapter.c.a aVar, RadioCategoryChannel radioCategoryChannel) {
            Animation e = aVar.e();
            MovementImage movementImage = (MovementImage) aVar.d();
            ImageView f = aVar.f();
            int i = ((this.b.mCurrentChannelState == 2 || this.b.mCurrentChannelState == 4) && this.b.isPlayingItem(radioCategoryChannel)) ? 1 : 0;
            if (i != 0) {
                f.setBackgroundResource(R.drawable.img_online_radio_play);
                f.setVisibility(0);
                e.setVisibility(0);
                e.setAnimationResource(R.drawable.xml_imageview_radio_play_animation);
                e.a();
                if (!c.a(aVar.g(), ThemeElement.TILE_MASK)) {
                    aVar.g().setBackgroundResource(R.drawable.color_background_radio_playing);
                }
                aVar.g().setVisibility(0);
                movementImage.setVisibility(0);
                if (this.b.mArtistBitmap != null) {
                    movementImage.setMoveMentBitmap(this.b.mArtistBitmap);
                    movementImage.setMoveSpeed(com.sds.android.ttpod.common.c.a.a(1));
                    movementImage.setAlpha(RadioCategoryFragment.ALPHA);
                    movementImage.a(movementImage.getWidth(), movementImage.getHeight());
                    movementImage.a();
                    return;
                }
                return;
            }
            f.setVisibility(4);
            e.setVisibility(4);
            e.b();
            c.a(aVar.g(), ThemeElement.TILE_BACKGROUND);
            movementImage.setMoveMentBitmap(null);
            movementImage.b();
            movementImage.setVisibility(4);
        }
    }

    public RadioCategoryFragment(String str) {
        super(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CATEGORY_LIST, null, null);
        setAdapter(new a());
        this.mTitle = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(this.mTitle);
        setTBSPage("tt_radio");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mRadioCategoryChannel = (RadioCategoryChannel) arguments.getSerializable("data");
        }
        this.mActiveChannelTitle = com.sds.android.ttpod.framework.storage.environment.b.bl();
        if (!m.a(this.mActiveChannelTitle) && com.sds.android.ttpod.component.c.c.b(this.mActiveChannelTitle) && e.a(getActivity()).n() == PlayStatus.STATUS_PLAYING) {
            this.mCurrentChannelState = 2;
        }
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (M != null && !M.isNull()) {
            String a = com.sds.android.ttpod.framework.storage.environment.b.a(M);
            g.a(TAG, "getCurrentArtistBitmapPath = " + a);
            if (!m.a(a)) {
                this.mArtistBitmap = new com.sds.android.sdk.lib.util.b().a(a);
            }
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RADIO_CHANNEL_MUSIC_LIST, i.a(cls, "updateRadioChannelMusicList", ArrayList.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAYING_MEDIA_INFO, i.a(cls, "updatePlayMediaItemInfo", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PICTURE_STATE, i.a(cls, "updateSearchPictureState", com.sds.android.ttpod.framework.support.search.b.class, List.class, String.class, Bitmap.class));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mRadioCategoryChannel != null) {
            performItemClick(this.mRadioCategoryChannel);
        }
    }

    public void updateSearchPictureState(com.sds.android.ttpod.framework.support.search.b bVar, List<ResultData> list, String str, Bitmap bitmap) {
        if (m.a(com.sds.android.ttpod.framework.storage.a.a.a().M().getID(), str)) {
            switch (bVar) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    setArtistBitmap(bitmap);
                    return;
                default:
                    setArtistBitmap(null);
                    return;
            }
        }
    }

    private void setArtistBitmap(Bitmap bitmap) {
        if (isViewAccessAble() && this.mAdapter != null) {
            this.mArtistBitmap = bitmap;
            this.mAdapter.notifyDataSetChanged();
        }
    }

    protected String onLoadTitleText() {
        return this.mTitle;
    }

    protected ArrayList<com.sds.android.ttpod.adapter.b.a<RadioCategoryChannel>> convertDataList(ArrayList arrayList) {
        int size = arrayList.size();
        ArrayList<com.sds.android.ttpod.adapter.b.a<RadioCategoryChannel>> arrayList2 = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            RadioCategory radioCategory = (RadioCategory) arrayList.get(i);
            arrayList2.add(new com.sds.android.ttpod.adapter.b.a(radioCategory.getRadioCategoryName(), 0, 0, radioCategory.getRadioCategoryChannels()));
        }
        return arrayList2;
    }

    protected void requestRadioMusicList(int i) {
        g.a(TAG, "RadioCategoryFragment.requestRadioMusicList---id: " + i);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_RADIO_CHANNEL_MUSIC_LIST, "111", Integer.valueOf(i)));
    }

    public void updateRadioChannelMusicList(ArrayList<MediaItem> arrayList) {
        if (!isViewAccessAble()) {
            return;
        }
        if (arrayList == null || arrayList.size() == 0) {
            f.a((int) R.string.network_unavailable);
            return;
        }
        g.a(TAG, "RadioCategoryFragment.updateRadioChannelMusicList---musicList.size: " + arrayList.size() + " mNetMusicListNeedSynced: " + this.mNetMusicListNeedSynced);
        this.mPlayingListLastMediaItem = (MediaItem) arrayList.get(arrayList.size() - 1);
        if (this.mNetMusicListNeedSynced) {
            trackPlaySong("channel", String.valueOf(this.mActiveChannelId), true);
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP_WITH_NAME, arrayList, this.mActiveChannelTitle));
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.get(0)));
            this.mNetMusicListNeedSynced = false;
            return;
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.APPEND_NET_TEMPORARY_MEDIA_ITEMS, arrayList));
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        g.a(TAG, "RadioCategoryFragment.updatePlayStatus---status: " + playStatus);
        if (isViewAccessAble()) {
            switch (playStatus) {
                case STATUS_PLAYING:
                    this.mCurrentChannelState = 2;
                    this.mActiveChannelTitle = com.sds.android.ttpod.framework.storage.environment.b.bl();
                    this.mAdapter.notifyDataSetChanged();
                    return;
                case STATUS_PAUSED:
                    this.mCurrentChannelState = 3;
                    this.mActiveChannelTitle = "";
                    this.mAdapter.notifyDataSetChanged();
                    return;
                case STATUS_STOPPED:
                case STATUS_ERROR:
                    this.mActiveChannelTitle = "";
                    this.mAdapter.notifyDataSetChanged();
                    this.mCurrentChannelState = 1;
                    return;
                default:
                    return;
            }
        }
    }

    public void updatePlayMediaItemInfo() {
        if (isViewAccessAble() && com.sds.android.ttpod.framework.storage.a.a.a().M().equals(this.mPlayingListLastMediaItem)) {
            requestRadioMusicList(this.mActiveChannelId);
        }
    }

    private void performItemClick(RadioCategoryChannel radioCategoryChannel) {
        if (radioCategoryChannel != null) {
            int categoryChannelId = radioCategoryChannel.getCategoryChannelId();
            String categoryChannelName = radioCategoryChannel.getCategoryChannelName();
            String groupName = getGroupName(radioCategoryChannel);
            if (!m.a(this.mActiveChannelTitle, groupName)) {
                this.mActiveChannelId = categoryChannelId;
                this.mActiveChannelTitle = groupName;
                this.mCurrentChannelState = 4;
                this.mArtistBitmap = null;
                this.mAdapter.notifyDataSetChanged();
            }
            transferToState(this.mCurrentChannelState);
            o.c(categoryChannelId, categoryChannelName);
            new com.sds.android.ttpod.framework.a.b.b().c(categoryChannelName).c("radio_" + categoryChannelName).a("channel_id", String.valueOf(categoryChannelId)).a("channel_name", categoryChannelName).a();
            String str = "library-music-radio_" + categoryChannelName + "_" + o.a();
            new SUserEvent("PAGE_CLICK", r.ACTION_FM_PLAY.getValue(), this.mTitle, String.valueOf(s.PAGE_NONE.getValue())).append("channel_id", Integer.valueOf(categoryChannelId)).append("channel_name", categoryChannelName).append(SubMediaListFragment.KEY_GROUP_NAME, groupName).post();
            p.a(str);
            p.a();
        }
    }

    private String getGroupName(RadioCategoryChannel radioCategoryChannel) {
        return getString(R.string.category_radio) + "_" + radioCategoryChannel.getCategoryChannelName();
    }

    public void updateDataList(ArrayList arrayList) {
        g.a(TAG, "RadioCategoryFragment.updateDataList---> ");
        if (arrayList != null) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.addAll(((RadioCategory) it.next()).getRadioCategoryChannels());
            }
            super.updateData(arrayList2);
            return;
        }
        super.updateData(null);
    }

    private void transferToState(int i) {
        g.a(TAG, "RadioCategoryFragment.transferToState--->state: " + i);
        switch (i) {
            case 1:
            case 4:
                requestRadioMusicList(this.mActiveChannelId);
                this.mNetMusicListNeedSynced = true;
                return;
            case 2:
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                return;
            case 3:
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(e.a(getActivity()).n() == PlayStatus.STATUS_PAUSED ? com.sds.android.ttpod.framework.modules.a.RESUME : com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                return;
            default:
                return;
        }
    }

    private boolean isPlayingItem(RadioCategoryChannel radioCategoryChannel) {
        return m.a(this.mActiveChannelTitle, com.sds.android.ttpod.component.c.c.a(radioCategoryChannel));
    }
}
