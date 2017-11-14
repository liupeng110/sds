package com.ttfm.android.sdk.core;

import android.content.Context;
import com.b.a.f;
import com.ttfm.android.sdk.data.LastPlayInfo;
import com.ttfm.android.sdk.entity.ChannelInfoGetResult;
import com.ttfm.android.sdk.entity.ChannelSongListResult;
import com.ttfm.android.sdk.entity.ChannelSongListV2Result;
import com.ttfm.android.sdk.entity.MusicInfoGetResult;
import com.ttfm.android.sdk.entity.NextSongGetResult;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import com.ttfm.android.sdk.http.HttpChannelInfo;
import com.ttfm.android.sdk.http.HttpChannelListGet;
import com.ttfm.android.sdk.http.HttpChannelListGetV2;
import com.ttfm.android.sdk.http.HttpChannelSongListGet;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;
import com.ttfm.android.sdk.http.HttpClassifyGet;
import com.ttfm.android.sdk.http.HttpCode;
import com.ttfm.android.sdk.http.HttpFeaturedListGet;
import com.ttfm.android.sdk.http.HttpMusicInfo;
import com.ttfm.android.sdk.http.HttpMusicOnDemand;
import com.ttfm.android.sdk.http.HttpMusicPlayAuto;
import com.ttfm.android.sdk.http.HttpMusicPlayManual;
import com.ttfm.android.sdk.http.HttpMusicSetPause;
import com.ttfm.android.sdk.http.HttpNavigationGet;
import com.ttfm.android.sdk.http.HttpSearchChannel;
import com.ttfm.android.sdk.storage.TTFMBaseDB;
import com.ttfm.android.sdk.utils.SerializeUtils;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import java.io.File;

public class TTFMSDK implements IFMChannelProxy, IFMPlayProxy {
    private static TTFMSDK instance;
    private long mInstanceCreateTime = System.currentTimeMillis();
    private long oldUserId;

    public static TTFMSDK getInstance() {
        if (instance == null) {
            instance = new TTFMSDK();
        }
        return instance;
    }

    private TTFMSDK() {
    }

    public void initialize(Context context, long j, String str) {
        this.oldUserId = j;
        TTFMEnvironmentUtils.init(context, str);
        DSDManager.initDSD(context, j);
        NavigationManager.initNavigation(context);
    }

    public void finalization() {
        TTFMEnvironmentUtils.finalization();
    }

    public void updateDSD(Context context, long j) {
        DSDManager.updateDSD(context, j);
    }

    public synchronized NextSongGetResult getNextPlaySong(Context context, long j, int i, int i2, TTFMSongEntity tTFMSongEntity) {
        return getNextPlaySong(context, j, i, i2, tTFMSongEntity != null ? tTFMSongEntity.getChannelID() : 0, tTFMSongEntity != null ? tTFMSongEntity.getMusicID() : 0, tTFMSongEntity != null ? tTFMSongEntity.getSerialNo() : 0, tTFMSongEntity != null ? tTFMSongEntity.getDuration() : 0, tTFMSongEntity != null ? tTFMSongEntity.getLastPlayTime() : 0);
    }

    public synchronized NextSongGetResult getSelectPlaySong(Context context, long j, int i, long j2, int i2, TTFMSongEntity tTFMSongEntity) {
        return getSelectPlaySong(context, j, i, j2, i2, tTFMSongEntity != null ? tTFMSongEntity.getChannelID() : 0, tTFMSongEntity != null ? tTFMSongEntity.getMusicID() : 0, tTFMSongEntity != null ? tTFMSongEntity.getSerialNo() : 0, tTFMSongEntity != null ? tTFMSongEntity.getDuration() : 0, tTFMSongEntity != null ? tTFMSongEntity.getLastPlayTime() : 0);
    }

    public synchronized boolean syncChannelPlayState(Context context, long j, TTFMSongEntity tTFMSongEntity) {
        boolean z;
        int channelID = tTFMSongEntity != null ? tTFMSongEntity.getChannelID() : 0;
        long musicID = tTFMSongEntity != null ? tTFMSongEntity.getMusicID() : 0;
        long serialNo = tTFMSongEntity != null ? tTFMSongEntity.getSerialNo() : 0;
        int duration = tTFMSongEntity != null ? tTFMSongEntity.getDuration() : 0;
        int lastPlayTime = tTFMSongEntity != null ? tTFMSongEntity.getLastPlayTime() : 0;
        recordChannelPlayInfo(context, channelID, musicID, serialNo, duration, lastPlayTime);
        if (tTFMSongEntity == null) {
            z = true;
        } else {
            setChannelPause(j, channelID, this.oldUserId, musicID, serialNo, duration, lastPlayTime);
            this.oldUserId = j;
            z = true;
        }
        return z;
    }

    public synchronized NextSongGetResult getNextPlaySong(Context context, long j, int i, int i2, int i3, long j2, long j3, int i4, int i5) {
        NextSongGetResult nextSongGetResult;
        String playChannelManual;
        Object obj = i != i3 ? 1 : null;
        recordChannelPlayInfo(context, i3, j2, j3, i4, i5);
        if (obj != null) {
            LastPlayInfo channelPlayInfo = getChannelPlayInfo(context, i);
            playChannelManual = playChannelManual(j, i, i2, channelPlayInfo != null ? channelPlayInfo.musicId : 0, channelPlayInfo != null ? channelPlayInfo.serialNo : 0, channelPlayInfo != null ? channelPlayInfo.duration : 0, channelPlayInfo != null ? channelPlayInfo.playedMS : 0);
        } else {
            playChannelManual = playChannelNext(j, i, i2, this.oldUserId, j2, j3, i4, i5);
        }
        nextSongGetResult = null;
        if (playChannelManual != null) {
            nextSongGetResult = (NextSongGetResult) new f().a(playChannelManual, NextSongGetResult.class);
            if (nextSongGetResult != null && HttpCode.isOk(nextSongGetResult.getCode())) {
                this.oldUserId = j;
                nextSongGetResult.getNext().setChannelID(i);
                nextSongGetResult.getNext2().setChannelID(i);
                long currentTimeMillis = System.currentTimeMillis();
                nextSongGetResult.getNext().setUrlGetTime(currentTimeMillis);
                nextSongGetResult.getNext2().setUrlGetTime(currentTimeMillis);
            }
        }
        return nextSongGetResult;
    }

    public synchronized NextSongGetResult getSelectPlaySong(Context context, long j, int i, long j2, int i2, int i3, long j3, long j4, int i4, int i5) {
        NextSongGetResult nextSongGetResult;
        recordChannelPlayInfo(context, i3, j3, j4, i4, i5);
        try {
            nextSongGetResult = (NextSongGetResult) new f().a(playChannelDemand(j, i, j2, i2), NextSongGetResult.class);
            if (nextSongGetResult != null && HttpCode.isOk(nextSongGetResult.getCode())) {
                nextSongGetResult.getNext().setChannelID(i);
                nextSongGetResult.getNext2().setChannelID(i);
                long currentTimeMillis = System.currentTimeMillis();
                nextSongGetResult.getNext().setUrlGetTime(currentTimeMillis);
                nextSongGetResult.getNext2().setUrlGetTime(currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
            nextSongGetResult = null;
        }
        return nextSongGetResult;
    }

    public synchronized boolean syncChannelPlayState(Context context, long j, int i, long j2, long j3, int i2, int i3) {
        boolean z;
        recordChannelPlayInfo(context, i, j2, j3, i2, i3);
        if (i <= 0 || j2 <= 0) {
            z = true;
        } else {
            setChannelPause(j, i, this.oldUserId, j2, j3, i2, i3);
            this.oldUserId = j;
            z = true;
        }
        return z;
    }

    public synchronized MusicInfoGetResult getMusicInfoObject(long j, int i, long j2, int i2) {
        MusicInfoGetResult musicInfoGetResult;
        String musicInfo = getMusicInfo(j, i, j2, i2);
        musicInfoGetResult = null;
        if (musicInfo != null) {
            musicInfoGetResult = (MusicInfoGetResult) new f().a(musicInfo, MusicInfoGetResult.class);
            if (musicInfoGetResult != null && HttpCode.isOk(musicInfoGetResult.getCode())) {
                this.oldUserId = j;
            }
        }
        return musicInfoGetResult;
    }

    private void recordChannelPlayInfo(Context context, int i, long j, long j2, int i2, int i3) {
        LastPlayInfo lastPlayInfo = new LastPlayInfo();
        lastPlayInfo.channelId = i;
        lastPlayInfo.musicId = j;
        lastPlayInfo.serialNo = j2;
        lastPlayInfo.duration = i2;
        lastPlayInfo.playedMS = i3;
        TTFMBaseDB.getChannelLastPlayDB(context).addLastPlay(lastPlayInfo);
    }

    private LastPlayInfo getChannelPlayInfo(Context context, int i) {
        return TTFMBaseDB.getChannelLastPlayDB(context).getLastPlay(i);
    }

    public String playChannelManual(long j, int i, int i2, long j2, long j3, int i3, int i4) {
        try {
            byte[] bArr = HttpMusicPlayManual.getInstance().get(j, i, 0, "", j3, j2, i3, i4, i2);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String playChannelNext(long j, int i, int i2, long j2, long j3, long j4, int i3, int i4) {
        try {
            byte[] bArr = HttpMusicPlayAuto.getInstance().get(j, i, 0, "", j2, j4, j3, i3, i4, i3 - i4 > 100, false, false, false, i2);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String setChannelPause(long j, int i, long j2, long j3, long j4, int i2, int i3) {
        try {
            byte[] bArr = HttpMusicSetPause.getInstance().get(j, i, j2, j3, j4, i2, i3, false, false, false, false);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String playChannelDemand(long j, int i, long j2, int i2) {
        try {
            byte[] bArr = HttpMusicOnDemand.getInstance().get(j, "", i, j2, 0, 0, i2);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMusicInfo(long j, int i, long j2, int i2) {
        try {
            byte[] bArr = HttpMusicInfo.getInstance().get(j, i, j2, i2);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelClassify(long j) {
        try {
            byte[] bArr = HttpClassifyGet.getInstance().get(j);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelHomePage(long j, int i) {
        try {
            byte[] bArr = HttpFeaturedListGet.getInstance().get(j, i);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelClassifyList(long j, int i, String str, int i2) {
        try {
            byte[] bArr = HttpSearchChannel.getInstance().get(j, i, str, i2);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelClassifyListV2(long j, int i, int i2, String str) {
        try {
            byte[] bArr = HttpChannelListGetV2.getInstance().get(j, i, i2, str);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelDetailURL(int i, int i2) {
        return "http://fm.ttpod.com/channel/detail.html?cid=" + i + "&type=" + i2;
    }

    public String getDynamicTagList(long j) {
        try {
            byte[] bArr = HttpNavigationGet.getInstance().get(j);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelList(long j, int i, int i2, int i3, String str) {
        try {
            byte[] bArr = HttpChannelListGet.getInstance().get(j, i, i2, i3, str);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getChannelInfo(long j, int i) {
        try {
            byte[] bArr = HttpChannelInfo.getInstance().get(j, (long) i);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChannelInfoGetResult getChannelInfoObject(long j, int i) {
        String channelInfo = getChannelInfo(j, i);
        if (channelInfo != null) {
            return (ChannelInfoGetResult) new f().a(channelInfo, ChannelInfoGetResult.class);
        }
        return null;
    }

    public String getChannelSongList(long j, int i, int i2, int i3) {
        try {
            byte[] bArr = HttpChannelSongListGet.getInstance().get(j, (long) i, i2, i3);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChannelSongListResult getChannelSongListObject(long j, int i, int i2, int i3) {
        String channelSongList = getChannelSongList(j, i, i2, i3);
        if (channelSongList != null) {
            return (ChannelSongListResult) new f().a(channelSongList, ChannelSongListResult.class);
        }
        return null;
    }

    public String getChannelSongListWithUrl(long j, int i, int i2) {
        try {
            byte[] bArr = HttpChannelSongListGetV2.getInstance().get(j, i, i2);
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChannelSongListV2Result getChannelSongListWithUrlObject(long j, int i, int i2) {
        ChannelSongListV2Result channelSongListV2Result = null;
        File file = new File(TTFMEnvironmentUtils.getmCachepath(), "csl_" + i + "_" + i2 + ".list");
        if (file.exists()) {
            channelSongListV2Result = (ChannelSongListV2Result) SerializeUtils.reserializeObjectBy(file.getAbsolutePath());
        }
        if (channelSongListV2Result == null || channelSongListV2Result.getUpdateTime() < this.mInstanceCreateTime) {
            String channelSongListWithUrl = getChannelSongListWithUrl(j, i, i2);
            if (channelSongListWithUrl != null) {
                channelSongListV2Result = (ChannelSongListV2Result) new f().a(channelSongListWithUrl, ChannelSongListV2Result.class);
                if (channelSongListV2Result != null && channelSongListV2Result.isSuccess()) {
                    channelSongListV2Result.setUpdateTime(System.currentTimeMillis());
                    SerializeUtils.serializObjectTo(file.getAbsolutePath(), channelSongListV2Result);
                }
            }
        }
        return channelSongListV2Result;
    }
}
