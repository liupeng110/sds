package com.sds.android.ttpod.framework.a;

import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.Serializable;

/* AuditionMediaCache */
public class a implements Serializable {
    private static int c = 0;
    private MediaItem a;
    private a b = a.IDLE;

    /* AuditionMediaCache */
    enum a {
        IDLE,
        SAVED,
        FAILURE_FULL_STORAGE
    }

    public a(MediaItem mediaItem) {
        a(mediaItem);
        this.a.setSongID(mediaItem.getSongID());
    }

    private void a(MediaItem mediaItem) {
        Url a = n.a((OnlineMediaItem) f.a(mediaItem.getExtra(), OnlineMediaItem.class), c.d());
        String Q = b.Q();
        this.a = new MediaItem(null, null, Q + "/" + mediaItem.getArtist() + " - " + mediaItem.getTitle() + "." + a.getFormat(), Q, mediaItem.getTitle(), mediaItem.getArtist(), mediaItem.getAlbum(), mediaItem.getGenre(), mediaItem.getComposer(), a.getFormat(), mediaItem.getStartTime(), mediaItem.getDuration(), mediaItem.getTrack(), mediaItem.getYear(), mediaItem.getGrade(), Integer.valueOf(a.getBitrate()), mediaItem.getSampleRate(), mediaItem.getChannels(), mediaItem.getComment(), mediaItem.getErrorStatus(), mediaItem.getUseCount(), mediaItem.getDateAddedInMills(), mediaItem.getDateModifiedInMills(), mediaItem.getDateLastAccessInMills(), mediaItem.getFav(), null, null);
    }

    public boolean a() {
        if (e.b(this.a.getLocalDataSource(), com.sds.android.ttpod.framework.a.D())) {
            if (!MediaStorage.isGroupExisted(BaseApplication.e(), MediaStorage.GROUP_ID_DOWNLOAD)) {
                MediaStorage.insertGroup(BaseApplication.e(), MediaStorage.GROUP_NAME_DOWNLOADED_SONG, MediaStorage.GROUP_ID_DOWNLOAD, GroupType.CUSTOM_LOCAL);
            }
            if (b.av()) {
                MediaItem queryMediaItemBySongID = MediaStorage.queryMediaItemBySongID(BaseApplication.e(), MediaStorage.buildOnlineFavGroupID(), this.a.getSongID());
                if (queryMediaItemBySongID != null) {
                    k.a(queryMediaItemBySongID, this.a.getLocalDataSource());
                    MediaStorage.updateMediaItem(BaseApplication.e(), queryMediaItemBySongID);
                }
            }
            MediaStorage.insertMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_DOWNLOAD, this.a);
            MediaStorage.insertMediaItem(BaseApplication.e(), MediaStorage.GROUP_ID_ALL_LOCAL, this.a);
            this.b = a.SAVED;
            return true;
        }
        this.b = a.FAILURE_FULL_STORAGE;
        c++;
        return false;
    }

    public MediaItem b() {
        return this.a;
    }

    public boolean c() {
        return this.b == a.SAVED;
    }

    public void d() {
        if (c() || c <= 1) {
            Intent intent = new Intent(Action.PLAY_CACHE_SAVED);
            intent.putExtra("cached_media", this);
            BaseApplication.e().sendBroadcast(intent);
        }
    }
}
