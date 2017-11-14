package com.sds.android.ttpod.activities.soundsearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.g;
import com.sds.android.ttpod.component.c.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Medias;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SoundSearchResultActivity extends SlidingClosableActivity {
    private static final int ARG_REFRESH_CANCEL = -1;
    private static final int ARG_REFRESH_REPEAT = 1;
    private static final int HIGH_LIGHT_COLOR = -14256489;
    private static final int HIGH_LIGHT_SIZE = 18;
    private static final int NORMAL_COLOR = -4600363;
    private static final int NORMAL_SIZE = 14;
    private static final long REFRESH_DELAY = 50;
    private static final int WHAT_REFRESH_LYRIC = 1;
    private static final int WHAT_REFRESH_LYRIC_WHILE_NO_PLAY = 2;
    private TextView mAlbum;
    private ImageView mCover;
    private TextView mFavoriteCount;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ SoundSearchResultActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (this.a.mLyricView != null) {
                    this.a.mLyricView.setPlayingTime((long) e.a(BaseApplication.e()).l().intValue());
                    if (message.arg1 == 1) {
                        this.a.refreshLyric(1);
                    }
                }
            } else if (message.what == 2) {
                this.a.tryToRefreshLyricWhileNoPlay();
            }
        }
    };
    private boolean mHasBeenPlayed = false;
    private ImageView mImageViewAddFavor;
    private ImageView mImageViewDownload;
    private ImageView mImageViewPlay;
    private ImageView mImageViewShare;
    private long mLyricOffset;
    private LyricView mLyricView;
    private MediaItem mMediaItem;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ SoundSearchResultActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_soundsearch_download:
                    new b(this.a).a(this.a.mMediaItem, "search");
                    return;
                case R.id.imageview_soundsearch_play:
                    if (!a.a().M().getID().equals(this.a.mMediaItem.getID())) {
                        new ArrayList().add(this.a.mMediaItem);
                        com.sds.android.ttpod.framework.storage.environment.b.K(false);
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, r0));
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, this.a.mMediaItem));
                    } else if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PAUSED) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                    } else if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PLAYING) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                    }
                    this.a.mHasBeenPlayed = true;
                    return;
                case R.id.imageview_soundsearch_share:
                    f.a(this.a, this.a.mMediaItem);
                    return;
                case R.id.imageview_soundsearch_favorite:
                    boolean z;
                    if (((Boolean) this.a.mImageViewAddFavor.getTag()).booleanValue()) {
                        z = false;
                    } else {
                        z = true;
                    }
                    Boolean valueOf = Boolean.valueOf(z);
                    if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                        this.a.mMediaItem.setFav(valueOf.booleanValue());
                        this.a.mImageViewAddFavor.setTag(valueOf);
                        this.a.mImageViewAddFavor.setImageResource(valueOf.booleanValue() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
                        if (valueOf.booleanValue()) {
                            g.a(this.a.mMediaItem, true);
                            return;
                        } else {
                            g.b(this.a.mMediaItem, false);
                            return;
                        }
                    }
                    this.a.mImageViewAddFavor.setTag(Boolean.valueOf(false));
                    com.sds.android.ttpod.b.f.a(true);
                    return;
                default:
                    return;
            }
        }
    };
    private SoundSearchInfo mSoundSearchInfo;
    private long mStartTime;
    private TextView mTitle;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_sound_recognize_result");
        setTitle((int) R.string.search_sound_search);
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        actionBarController.d();
        actionBarController.d((int) R.drawable.img_actionitem_history).a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ SoundSearchResultActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                this.a.startActivity(new Intent(this.a, SoundSearchHistoryActivity.class));
            }
        });
        setContentView((int) R.layout.activity_soundsearch_result);
        initView();
        initData();
    }

    protected void onResume() {
        super.onResume();
        initFav();
    }

    protected void onDestroy() {
        super.onDestroy();
        a.a().n();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PLAY_STATUS, i.a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_LYRIC_STATE, i.a(cls, "updateSearchLyricState", com.sds.android.ttpod.framework.support.search.b.class, List.class, String.class, com.sds.android.ttpod.framework.modules.skin.e.g.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_PICTURE_STATE, i.a(cls, "updateSearchPictureState", com.sds.android.ttpod.framework.support.search.b.class, List.class, String.class, Bitmap.class));
    }

    public void updateSearchPictureState(com.sds.android.ttpod.framework.support.search.b bVar, List<ResultData> list, String str, Bitmap bitmap) {
        switch (bVar) {
            case SEARCH_LOCAL_FINISHED:
            case SEARCH_DOWNLOAD_FINISHED:
                this.mCover.setImageBitmap(bitmap);
                return;
            case SEARCH_ONLINE_FINISHED:
                if (list != null && list.size() > 0) {
                    Item item = ((ResultData) list.get(0)).c()[0];
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_DOWNLOAD_SEARCH_PICTURE, item.c(), item.d(), this.mMediaItem));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void updateSearchLyricState(com.sds.android.ttpod.framework.support.search.b bVar, List<ResultData> list, String str, com.sds.android.ttpod.framework.modules.skin.e.g gVar) {
        if (m.a(str, this.mMediaItem.getID())) {
            switch (bVar) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    this.mLyricView.setLyric(gVar);
                    this.mLyricOffset = this.mSoundSearchInfo != null ? this.mSoundSearchInfo.d() : 0;
                    this.mLyricView.setPlayingTime(this.mLyricOffset);
                    tryToRefreshLyricWhileNoPlay();
                    return;
                case SEARCH_ONLINE_FINISHED:
                    if (list != null && list.size() > 0) {
                        Item item = ((ResultData) list.get(0)).c()[0];
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_DOWNLOAD_SEARCH_LYRIC, item, this.mMediaItem));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mImageViewPlay.setImageResource(playStatus == PlayStatus.STATUS_PLAYING ? R.drawable.xml_imageview_soundsearch_result_pause : R.drawable.xml_imageview_soundsearch_result_play);
        refreshLyric(playStatus == PlayStatus.STATUS_PLAYING ? 1 : -1);
    }

    private void initFav() {
        this.mMediaItem.setFav(k.a(this.mMediaItem));
        this.mImageViewAddFavor.setTag(Boolean.valueOf(this.mMediaItem.getFav()));
        this.mImageViewAddFavor.setImageResource(this.mMediaItem.getFav() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
    }

    private void initView() {
        this.mCover = (ImageView) findViewById(R.id.imageview_soundsearch_cover);
        this.mImageViewDownload = (ImageView) findViewById(R.id.imageview_soundsearch_download);
        this.mImageViewDownload.setOnClickListener(this.mOnClickListener);
        this.mImageViewPlay = (ImageView) findViewById(R.id.imageview_soundsearch_play);
        this.mImageViewPlay.setOnClickListener(this.mOnClickListener);
        this.mImageViewShare = (ImageView) findViewById(R.id.imageview_soundsearch_share);
        this.mImageViewShare.setOnClickListener(this.mOnClickListener);
        this.mImageViewAddFavor = (ImageView) findViewById(R.id.imageview_soundsearch_favorite);
        this.mImageViewAddFavor.setOnClickListener(this.mOnClickListener);
        this.mTitle = (TextView) findViewById(R.id.textview_soundsearch_title);
        this.mAlbum = (TextView) findViewById(R.id.textview_soundsearch_album);
        this.mLyricView = (LyricView) findViewById(R.id.lyricview_soundsearch);
        this.mFavoriteCount = (TextView) findViewById(R.id.textview_soundsearch_favorite_count);
    }

    private void initData() {
        this.mSoundSearchInfo = (SoundSearchInfo) getIntent().getParcelableExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT);
        long longExtra = getIntent().getLongExtra(SoundSearchActivity.EXTRA_RECOGNIZE_TIME, 0);
        this.mMediaItem = this.mSoundSearchInfo.f();
        if (this.mMediaItem != null) {
            this.mTitle.setText(this.mMediaItem.getTitle());
            String str = "";
            CharSequence artist = this.mMediaItem.getArtist();
            if (!TTTextUtils.isValidateMediaString(artist)) {
                Object obj = str;
            }
            str = this.mMediaItem.getAlbum();
            if (TTTextUtils.isValidateMediaString(str)) {
                artist = artist + (artist.length() > 0 ? "-" + str : str);
            }
            this.mAlbum.setText(artist);
            this.mFavoriteCount.setVisibility(8);
        }
        updateAlibabaProperty(MediasColumns.SONG_ID, this.mMediaItem.getID());
        updateAlibabaProperty("recognize_rating", String.valueOf(this.mSoundSearchInfo.b()));
        updateAlibabaProperty("recognize_time", String.valueOf(longExtra));
        initCover();
        initLyric();
        startSearchLyricPic();
    }

    private void startSearchLyricPic() {
        startService(new Intent(this, SupportService.class).putExtra("command", "search_lyric_pic_command").putExtra(Medias.URI_PATH, this.mMediaItem));
    }

    private void initCover() {
    }

    private void initLyric() {
        this.mLyricView.setDefaultColorHighlight(HIGH_LIGHT_COLOR);
        this.mLyricView.setColorHighlight(HIGH_LIGHT_COLOR);
        this.mLyricView.setColorNormal(NORMAL_COLOR);
        this.mLyricView.setAlign(Align.CENTER);
        this.mLyricView.setDefaultFontSizeHighlight(18.0f);
        this.mLyricView.setDefaultFontSizeNormal(14.0f);
        this.mLyricView.setDisplayMode(LyricView.a.Normal);
        this.mLyricView.setTextSize(14.0f);
        this.mLyricView.setTextSizeHighlight(18.0f);
        this.mLyricView.setKalaOK(false);
        this.mLyricView.setSlowScroll(true);
        this.mStartTime = System.currentTimeMillis();
    }

    private void tryToRefreshLyricWhileNoPlay() {
        if (!this.mHasBeenPlayed) {
            this.mLyricView.setPlayingTime(this.mLyricOffset + (System.currentTimeMillis() - this.mStartTime));
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessage(2);
        }
    }

    private void refreshLyric(int i) {
        this.mHandler.removeMessages(1);
        Message obtainMessage = this.mHandler.obtainMessage(1);
        obtainMessage.arg1 = i;
        this.mHandler.sendMessageDelayed(obtainMessage, REFRESH_DELAY);
    }
}
