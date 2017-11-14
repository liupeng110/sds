package com.ttfm.android.sdk.embed;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.support.a.g;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.core.TTFMSDK;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.ChannelSongListV2Result;
import com.ttfm.android.sdk.entity.NextSongGetResult;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import com.ttfm.android.sdk.storage.TTFMBaseDB;
import com.ttfm.android.sdk.utils.TTFMUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TTFMPlayAdapter {
    public static final String LAST_PLAY_STATE_FILE = (Environment.getExternalStorageDirectory().toString() + "/TTFM/lastplaystate.rd");
    private static TTFMPlayAdapter mInstance;
    private static g mPlayer;
    private int curAudioQuality = 1;
    private ChannelEntity curPlayChannel;
    private TTFMSongEntity curPlaySong;
    private PlayStatus mCurrentPlayStatus = PlayStatus.STATUS_STOPPED;

    public static class WrapChannelSongListResult {
        List<MediaItem> mMediaItemList;
        NextSongGetResult mNextSongGetResult;
        List<TTFMSongEntity> mTTFMSongEntityList;
    }

    public static class WrapChannelSongResult implements Serializable {
        ChannelEntity mChannelEntity;
        TTFMSongEntity mNextSongGetResult;

        public ChannelEntity getChannelEntity() {
            return this.mChannelEntity;
        }

        public TTFMSongEntity getNextSongGetResult() {
            return this.mNextSongGetResult;
        }
    }

    private TTFMPlayAdapter() {
    }

    public static TTFMPlayAdapter getInstance(g gVar) {
        if (mInstance == null) {
            mInstance = new TTFMPlayAdapter();
        }
        mPlayer = gVar;
        return mInstance;
    }

    public static TTFMPlayAdapter getInstance() {
        if (mInstance == null) {
            mInstance = new TTFMPlayAdapter();
        }
        return mInstance;
    }

    public PlayStatus getCurrentPlayStatus() {
        return this.mCurrentPlayStatus;
    }

    public void setCurrentPlayStatus(PlayStatus playStatus) {
        this.mCurrentPlayStatus = playStatus;
    }

    public void playChannel(Context context, ChannelEntity channelEntity, long j) {
        if (channelEntity == null) {
            return;
        }
        if (this.curPlayChannel != null && this.curPlayChannel.getChannelId() == channelEntity.getChannelId() && (j == -1 || (this.curPlaySong != null && this.curPlaySong.getMusicID() == j))) {
            toggle(context);
        } else if (channelEntity.getCiType() == 1) {
            playSingleSongById(context, channelEntity);
        } else {
            playChannelSongList(context, TTFMUtils.getLoginUserId(), channelEntity, j);
        }
    }

    public void updateHistoryDB(Context context, ChannelEntity channelEntity) {
        if (TTFMBaseDB.getChannelPlayHistoryDB(context).add(channelEntity)) {
            TTFMBaseDB.getChannelPlayHistoryDB(context).fixListSizeTo(25);
            TTFMBroadcast.notifyChannelHistoryChanged(context);
        }
    }

    public void pause(Context context, boolean z) {
        if (z) {
            syncPlayState(context);
        }
    }

    public void toggle(Context context) {
        PlayStatus n = e.a(BaseApplication.e()).n();
        if (n == PlayStatus.STATUS_PAUSED) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
        } else if (n == PlayStatus.STATUS_PLAYING) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
        } else {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
        }
    }

    public boolean playSingleSongNext(Context context, ChannelEntity channelEntity) {
        if (channelEntity == null || channelEntity.getCiType() != 1) {
            return false;
        }
        playSingleSongById(context, channelEntity);
        return true;
    }

    private void syncPlayState(final Context context) {
        if (this.curPlayChannel != null && this.curPlaySong != null) {
            com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<ChannelEntity, Boolean>(this.curPlayChannel) {
                protected Boolean onDoInBackground(ChannelEntity channelEntity) {
                    int duration = TTFMPlayAdapter.this.curPlaySong.getDuration();
                    int lastPlayTime = TTFMPlayAdapter.this.curPlaySong.getLastPlayTime();
                    return Boolean.valueOf(TTFMSDK.getInstance().syncChannelPlayState(context, TTFMUtils.getLoginUserId(), channelEntity.getChannelId(), TTFMPlayAdapter.this.curPlaySong.getMusicID(), TTFMPlayAdapter.this.curPlaySong.getSerialNo(), duration, lastPlayTime));
                }

                protected void onPostExecuteForeground(Boolean bool) {
                }
            });
        }
    }

    public void stopPlayBeforeExit(Context context) {
        syncPlayState(context);
        if (this.curPlaySong == null) {
        }
    }

    public void setChannel(ChannelEntity channelEntity) {
        this.curPlayChannel = channelEntity;
    }

    public ChannelEntity getCurPlayChannel() {
        return this.curPlayChannel;
    }

    public void setCurPlaySong(TTFMSongEntity tTFMSongEntity) {
        this.curPlaySong = tTFMSongEntity;
    }

    public TTFMSongEntity getCurPlaySong() {
        return this.curPlaySong;
    }

    public void updatePlayingMediaInfo(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isNull() || !(mediaItem.isTtfmRadioSingleSong() || mediaItem.isTtfmRadioSongList())) {
            setChannel(null);
            setCurPlaySong(null);
            return;
        }
        WrapChannelSongResult wrapChannelSongResult = (WrapChannelSongResult) f.a(((OnlineMediaItem) f.a(mediaItem.getExtra(), OnlineMediaItem.class)).getTTFMExtract(), WrapChannelSongResult.class);
        setChannel(wrapChannelSongResult.getChannelEntity());
        setCurPlaySong(wrapChannelSongResult.getNextSongGetResult());
    }

    public void downloadOrOpenTTFMApp(Context context) {
        if (TTFMUtils.isAvilible(context, GlobalEnv.TTFM_PACKAGE_NAME)) {
            try {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setComponent(new ComponentName(GlobalEnv.TTFM_PACKAGE_NAME, GlobalEnv.TTFM_MAIN_ACTIVITY));
                context.startActivity(intent);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Toast.makeText(context, "下载暂未处理...", 0).show();
    }

    public void playChannelSongList(Context context, long j, ChannelEntity channelEntity, long j2) {
        if (channelEntity != null && channelEntity.getCiType() != 1) {
            final long j3 = j;
            final long j4 = j2;
            final Context context2 = context;
            final ChannelEntity channelEntity2 = channelEntity;
            com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<ChannelEntity, WrapChannelSongListResult>(channelEntity) {
                protected WrapChannelSongListResult onDoInBackground(ChannelEntity channelEntity) {
                    WrapChannelSongListResult wrapChannelSongListResult = new WrapChannelSongListResult();
                    ChannelSongListV2Result channelSongListWithUrlObject = TTFMSDK.getInstance().getChannelSongListWithUrlObject(j3, channelEntity.getChannelId(), TTFMPlayAdapter.this.curAudioQuality);
                    if (j4 == -1) {
                        wrapChannelSongListResult.mNextSongGetResult = TTFMSDK.getInstance().getNextPlaySong(context2, TTFMUtils.getLoginUserId(), channelEntity.getChannelId(), TTFMPlayAdapter.this.curAudioQuality, TTFMPlayAdapter.this.curPlaySong);
                    } else {
                        wrapChannelSongListResult.mNextSongGetResult = TTFMSDK.getInstance().getSelectPlaySong(context2, TTFMUtils.getLoginUserId(), channelEntity.getChannelId(), j4, TTFMPlayAdapter.this.curAudioQuality, TTFMPlayAdapter.this.curPlaySong);
                    }
                    if (!(channelSongListWithUrlObject == null || channelSongListWithUrlObject.getList() == null)) {
                        List arrayList = new ArrayList();
                        List list = channelSongListWithUrlObject.getList();
                        int size = list.size();
                        Object wrapChannelSongResult = new WrapChannelSongResult();
                        wrapChannelSongResult.mChannelEntity = channelEntity;
                        String a = f.a(wrapChannelSongResult);
                        int i = 0;
                        while (i < size && i <= 30) {
                            MediaItem a2 = k.a((TTFMSongEntity) list.get(i), a);
                            a2.setPlayType(2);
                            arrayList.add(a2);
                            i++;
                        }
                        wrapChannelSongListResult.mTTFMSongEntityList = list;
                        wrapChannelSongListResult.mMediaItemList = arrayList;
                    }
                    return wrapChannelSongListResult;
                }

                protected void onPostExecuteForeground(WrapChannelSongListResult wrapChannelSongListResult) {
                    List arrayList = new ArrayList();
                    if (wrapChannelSongListResult.mMediaItemList != null) {
                        arrayList = wrapChannelSongListResult.mMediaItemList;
                    }
                    if (wrapChannelSongListResult.mNextSongGetResult != null) {
                        TTFMSongEntity next = wrapChannelSongListResult.mNextSongGetResult.getNext();
                        TTFMPlayAdapter.this.curPlaySong = next;
                        int access$200 = TTFMPlayAdapter.this.getPlayIndex(wrapChannelSongListResult.mTTFMSongEntityList, next);
                        Object wrapChannelSongResult = new WrapChannelSongResult();
                        wrapChannelSongResult.mChannelEntity = channelEntity2;
                        MediaItem a = k.a(next, f.a(wrapChannelSongResult));
                        a.setPlayType(2);
                        if (access$200 == -1) {
                            arrayList.add(a);
                            access$200 = 0;
                        } else {
                            arrayList.remove(access$200);
                            arrayList.add(access$200, a);
                        }
                        TTFMPlayAdapter.this.setChannel(channelEntity2);
                        TTFMPlayAdapter.this.updateHistoryDB(context2, channelEntity2);
                        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, arrayList));
                        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.get(access$200)));
                    }
                }
            });
        }
    }

    private void playSingleSongById(final Context context, final ChannelEntity channelEntity) {
        if (channelEntity == null) {
            throw new IllegalArgumentException("ids should not be null");
        }
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<ChannelEntity, NextSongGetResult>(channelEntity) {
            protected NextSongGetResult onDoInBackground(ChannelEntity channelEntity) {
                return TTFMSDK.getInstance().getNextPlaySong(context, TTFMUtils.getLoginUserId(), channelEntity.getChannelId(), TTFMPlayAdapter.this.curAudioQuality, TTFMPlayAdapter.this.curPlaySong);
            }

            protected void onPostExecuteForeground(NextSongGetResult nextSongGetResult) {
                if (nextSongGetResult != null) {
                    TTFMPlayAdapter.this.curPlaySong = nextSongGetResult.getNext();
                    Object wrapChannelSongResult = new WrapChannelSongResult();
                    wrapChannelSongResult.mNextSongGetResult = TTFMPlayAdapter.this.curPlaySong;
                    wrapChannelSongResult.mChannelEntity = channelEntity;
                    MediaItem a = k.a(TTFMPlayAdapter.this.curPlaySong, f.a(wrapChannelSongResult));
                    a.setPlayType(1);
                    new ArrayList().add(a);
                    TTFMPlayAdapter.this.setChannel(channelEntity);
                    TTFMPlayAdapter.this.updateHistoryDB(context, channelEntity);
                    b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, r1));
                    b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, r1.get(0)));
                    if (TTFMPlayAdapter.mPlayer != null) {
                        TTFMPlayAdapter.mPlayer.a(a);
                        TTFMPlayAdapter.mPlayer.s();
                        TTFMPlayAdapter.mPlayer.t();
                    }
                }
            }
        });
    }

    private int getPlayIndex(List<TTFMSongEntity> list, TTFMSongEntity tTFMSongEntity) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (((TTFMSongEntity) list.get(i)).getMusicID() == tTFMSongEntity.getMusicID()) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void onMediaStop(Context context, MediaItem mediaItem, int i, int i2) {
        if (mediaItem == null) {
            return;
        }
        if ((mediaItem.isTtfmRadioSongList() || mediaItem.isTtfmRadioSingleSong()) && this.curPlaySong != null) {
            this.curPlaySong.setDuration(i);
            this.curPlaySong.setLastPlayTime(i2);
            pause(context, true);
        }
    }
}
