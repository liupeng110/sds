package com.sds.android.ttpod.fragment.main;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.skin.e.g;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.framework.support.search.b;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BasePlayerFragment extends BaseFragment {
    private static final float BUFFER_COMPLETE_PERCENT = 0.99f;
    private static final String LOG_TAG = "BasePlayerFragment";
    private static final int UPDATE_PLAY_POSITION_INTERVAL = 50;
    private static final int UPDATE_PLAY_POSITION_MSG = 1;
    private Bitmap mArtistBitmap;
    private String mArtistPath;
    private g mLyric;
    private String mMediaID;
    private Handler mPlayPositionRefreshHandler = new Handler(this) {
        final /* synthetic */ BasePlayerFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.mPlayPositionRefreshHandler != null) {
                this.a.updatePlayPosition();
                this.a.mPlayPositionRefreshHandler.sendEmptyMessageDelayed(1, 50);
            }
        }
    };

    public abstract void updatePlayMediaInfo();

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.PLAY_MEDIA_CHANGED, i.a(cls, "playMediaChanged", new Class[0]));
        map.put(a.UPDATE_PLAYING_MEDIA_INFO, i.a(cls, "updatePlayMediaInfo", new Class[0]));
        map.put(a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(a.UPDATE_SEARCH_PICTURE_STATE, i.a(cls, "updateSearchPictureState", b.class, List.class, String.class, Bitmap.class));
        map.put(a.SWITCH_ARTIST_PICTURE, i.a(cls, "switchArtistPicture", String.class, String.class, Bitmap.class));
        map.put(a.UPDATE_SEARCH_LYRIC_STATE, i.a(cls, "updateSearchLyricState", b.class, List.class, String.class, g.class));
    }

    public void onPostViewCreated(View view, Bundle bundle) {
        super.onPostViewCreated(view, bundle);
        String g = com.sds.android.ttpod.framework.storage.a.a.a().g();
        flushArtistBitmap(com.sds.android.ttpod.framework.a.g.a(g, com.sds.android.ttpod.common.c.a.d(), com.sds.android.ttpod.common.c.a.e(), true), g);
    }

    public void playMediaChanged() {
        String id = com.sds.android.ttpod.framework.storage.a.a.a().M().getID();
        com.sds.android.sdk.lib.util.g.a(LOG_TAG, "playMediaChanged lookLyricPic fragment=%s will clear lyric pic info equal=%b %s %s", getClass().getSimpleName(), Boolean.valueOf(m.a(id, this.mMediaID)), this.mMediaID, id);
        if (!m.a(id, this.mMediaID)) {
            this.mMediaID = id;
            this.mLyric = null;
            this.mArtistBitmap = null;
            this.mArtistPath = null;
        }
    }

    protected void setLyric(g gVar) {
        this.mLyric = gVar;
    }

    protected void setArtistBitmap(Bitmap bitmap, String str) {
        this.mArtistBitmap = bitmap;
        this.mArtistPath = str;
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (playStatus == PlayStatus.STATUS_PLAYING) {
            startUpdatePlayPosition();
        } else if (!com.sds.android.ttpod.framework.storage.a.a.a().M().isOnline()) {
            stopUpdatePlayPosition();
        } else if (e.a(getActivity()).m() > BUFFER_COMPLETE_PERCENT) {
            stopUpdatePlayPosition();
        }
    }

    protected void artistBitmapLoadFinished() {
    }

    protected void artistBitmapSearchStarted() {
    }

    protected void artistBitmapSearchFailed() {
    }

    protected void artistBitmapDownloadStarted() {
    }

    protected void artistBitmapDownloadError() {
    }

    protected void artistBitmapNetError() {
    }

    protected void lyricLoadFinished() {
    }

    protected void lyricSearchStarted() {
    }

    protected void lyricSearchFailed() {
    }

    protected void lyricDownloadStarted() {
    }

    protected void lyricDownloadError() {
    }

    protected void lyricNetError() {
    }

    protected void lyricSearchStop() {
    }

    private void flushArtistBitmap(Bitmap bitmap, String str) {
        if (m.a(str, this.mArtistPath)) {
            com.sds.android.sdk.lib.util.g.a(LOG_TAG, "flushArtistBitmap lookLyricPic fragment=%s equal Path=%s", getClass().getSimpleName(), str);
            return;
        }
        setArtistBitmap(bitmap, str);
        artistBitmapLoadFinished();
    }

    public void updateSearchPictureState(b bVar, List<ResultData> list, String str, Bitmap bitmap) {
        String a = (list == null || list.isEmpty()) ? "noResult" : ((ResultData) list.get(0)).a();
        String id = com.sds.android.ttpod.framework.storage.a.a.a().M().getID();
        if (bVar == b.SEARCH_LOCAL_FINISHED || bVar == b.SEARCH_DOWNLOAD_FINISHED) {
            a = com.sds.android.ttpod.framework.storage.a.a.a().g();
        }
        if (m.a(id, str)) {
            this.mMediaID = str;
            switch (bVar) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    flushArtistBitmap(bitmap, a);
                    return;
                case SEARCH_LOCAL_FAILURE:
                    flushArtistBitmap(null, null);
                    return;
                case SEARCH_ONLINE_STARTED:
                    artistBitmapSearchStarted();
                    return;
                case SEARCH_ONLINE_FAILURE:
                    artistBitmapSearchFailed();
                    return;
                case SEARCH_ONLINE_NET_EXCEPTION:
                    artistBitmapNetError();
                    return;
                case SEARCH_DOWNLOAD_FAILURE:
                    artistBitmapDownloadError();
                    return;
                case SEARCH_DOWNLOAD_STARTED:
                    artistBitmapDownloadStarted();
                    return;
                default:
                    return;
            }
        }
    }

    public void switchArtistPicture(String str, String str2, Bitmap bitmap) {
        if (m.a(com.sds.android.ttpod.framework.storage.a.a.a().M().getID(), str)) {
            switchArtistPicture(bitmap, str2);
        }
    }

    public void onResume() {
        super.onResume();
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.RESUME_IMAGE_SWITCHER, new Object[0]));
    }

    public void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.PAUSE_IMAGE_SWITCHER, new Object[0]));
    }

    protected void switchArtistPicture(Bitmap bitmap, String str) {
        setArtistBitmap(bitmap, str);
    }

    public void updateSearchLyricState(b bVar, final List<ResultData> list, String str, g gVar) {
        String a = (list == null || list.isEmpty()) ? "noResult" : ((ResultData) list.get(0)).a();
        com.sds.android.sdk.lib.util.g.a(LOG_TAG, "lookLyricPic fragment updateSearchLyricState state=%s title=%s", bVar.name(), a);
        final MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (m.a(M.getID(), str)) {
            switch (bVar) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    setLyric(gVar);
                    lyricLoadFinished();
                    return;
                case SEARCH_ONLINE_STARTED:
                    lyricSearchStarted();
                    return;
                case SEARCH_ONLINE_FAILURE:
                    lyricSearchFailed();
                    return;
                case SEARCH_ONLINE_NET_EXCEPTION:
                    lyricNetError();
                    return;
                case SEARCH_ONLINE_SETTING_EXCEPTION:
                    lyricSearchStop();
                    return;
                case SEARCH_DOWNLOAD_FAILURE:
                    lyricDownloadError();
                    return;
                case SEARCH_DOWNLOAD_STARTED:
                    lyricDownloadStarted();
                    return;
                case SEARCH_ONLINE_FINISHED:
                    boolean z;
                    String str2 = LOG_TAG;
                    String str3 = "updateSearchLyricState SEARCH_ONLINE_FINISHED lookLyricPic searchList!=null_%b isPortPlayFrag=%b userVisible=%b";
                    Object[] objArr = new Object[3];
                    if (list != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    objArr[0] = Boolean.valueOf(z);
                    objArr[1] = Boolean.valueOf(this instanceof PortraitPlayerFragment);
                    objArr[2] = Boolean.valueOf(getUserVisibleHint());
                    com.sds.android.sdk.lib.util.g.a(str2, str3, objArr);
                    if (list != null && (this instanceof PortraitPlayerFragment) && getUserVisibleHint()) {
                        d.a((Collection) list, "searchList");
                        showLyricPictureDownloadSelectDialog(R.string.search_lyric, list, new com.sds.android.ttpod.component.b.a.b(this) {
                            final /* synthetic */ BasePlayerFragment c;

                            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                                d.a(((ResultData) list.get(i)).c(), "items");
                                Item item = ((ResultData) list.get(i)).c()[0];
                                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.START_DOWNLOAD_SEARCH_LYRIC, item, M));
                            }
                        }, new OnCancelListener(this) {
                            final /* synthetic */ BasePlayerFragment a;

                            {
                                this.a = r1;
                            }

                            public void onCancel(DialogInterface dialogInterface) {
                                this.a.lyricLoadFinished();
                            }
                        });
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public g getCurrentLyric() {
        return this.mLyric;
    }

    public Bitmap getCurrentArtistBitmap() {
        return this.mArtistBitmap;
    }

    protected void updatePlayPosition() {
    }

    protected void startUpdatePlayPosition() {
        if (!this.mPlayPositionRefreshHandler.hasMessages(1)) {
            this.mPlayPositionRefreshHandler.sendEmptyMessage(1);
        }
    }

    protected void stopUpdatePlayPosition() {
        this.mPlayPositionRefreshHandler.removeMessages(1);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mPlayPositionRefreshHandler.removeCallbacksAndMessages(null);
    }

    private void showLyricPictureDownloadSelectDialog(int i, List<ResultData> list, final com.sds.android.ttpod.component.b.a.b bVar, final OnCancelListener onCancelListener) {
        List arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(new com.sds.android.ttpod.component.b.a(i2, 0, ((ResultData) list.get(i2)).toString()));
        }
        final com.sds.android.ttpod.component.d.a.e eVar = new com.sds.android.ttpod.component.d.a.e(getActivity(), arrayList, null, null);
        eVar.b(R.string.cancel, new com.sds.android.ttpod.common.a.a.a(this) {
            final /* synthetic */ BasePlayerFragment c;

            public void a(Object obj) {
                onCancelListener.onCancel(eVar);
            }
        });
        eVar.setTitle(i);
        eVar.a(new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ BasePlayerFragment c;

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                bVar.a(aVar, i);
                eVar.dismiss();
            }
        });
        eVar.show();
        com.sds.android.sdk.lib.util.g.a(LOG_TAG, "showLyricPictureDownloadSelectDialog lookLyricPic choose_size=%d", Integer.valueOf(arrayList.size()));
        eVar.setOnCancelListener(onCancelListener);
    }

    public String getCurrentArtistBitmapPath() {
        return this.mArtistPath;
    }
}
