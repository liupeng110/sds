package com.sds.android.ttpod.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.mradar.sdk.record.DoresoMusicTrack;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchHistoryActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchMultiResultActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchResultActivity;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.component.soundsearch.c;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.SoundSearchAnimView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SoundSearchActivity extends SlidingClosableActivity {
    public static final String EXTRA_RECOGNIZE_RESULT = "extra_recognize_result";
    public static final String EXTRA_RECOGNIZE_TIME = "extra_recognize_time";
    private static final int MSG_REFRESH = 0;
    private static final int PAGE_SIZE = 50;
    private static final int REFRESH_TIME = 20;
    private static final String TAG = "SoundSearchActivity";
    private static final int TIME_OUT = 15000;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ SoundSearchActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 0) {
                this.a.mRecognizeAnimView.setVolume(((Double) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SEARCH_RECOGNIZE_VOLUME, new Object[0]), Double.class)).doubleValue());
                this.a.mRecognizeAnimView.invalidate();
                sendEmptyMessageDelayed(0, 20);
            }
        }
    };
    private ImageView mImageViewState;
    private SoundSearchAnimView mRecognizeAnimView;
    private c mRecognizerHistory;
    private TextView mRecognizerSubTitle;
    private TextView mRecognizerTitle;
    private View mSoundSearchView;
    private long mStartTime;
    private Runnable mTimeOutRunnable = new Runnable(this) {
        final /* synthetic */ SoundSearchActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.stopSoundSearch();
        }
    };

    enum a {
        RECOGNIZE_IDLE,
        RECOGNIZING,
        RECOGNIZE_SUCCESSFUL,
        RECOGNIZE_FAIL,
        RECOGNIZE_NOT_CONNECT
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_RECOGNIZE);
        setTBSPage("tt_sound_recognize");
        trackModule("sound_recognize");
        setContentView((int) R.layout.activity_soundsearch);
        setTitle((int) R.string.search_sound_search);
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        actionBarController.d();
        actionBarController.d((int) R.drawable.img_actionitem_history).a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ SoundSearchActivity a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                this.a.startActivity(new Intent(this.a, SoundSearchHistoryActivity.class));
                t.b("PAGE_CLICK", r.ACTION_SOUND_RECOGNIZE_HISTORY, s.PAGE_RECOGNIZE, s.PAGE_SOUND_RECOGNIZE_HISTORY);
            }
        });
        this.mRecognizeAnimView = (SoundSearchAnimView) findViewById(R.id.soundSearchAnimView);
        this.mImageViewState = (ImageView) findViewById(R.id.imageview_soundsearch_state);
        this.mRecognizerTitle = (TextView) findViewById(R.id.textview_soundsearch_title);
        this.mRecognizerSubTitle = (TextView) findViewById(R.id.textview_soundsearch_sub_title);
        this.mSoundSearchView = findViewById(R.id.imagview_soundsearch_center);
        this.mSoundSearchView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SoundSearchActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.mRecognizeAnimView.getVisibility() == 0) {
                    this.a.cancelSoundSearch();
                    new com.sds.android.ttpod.framework.a.b.b().b("cancel_recognize").a();
                    return;
                }
                u.e();
                this.a.startSoundSearch();
                new com.sds.android.ttpod.framework.a.b.b().b("start_recognize").a();
            }
        });
        this.mRecognizerHistory = new c(null);
        if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PLAYING) {
            com.sds.android.ttpod.framework.storage.environment.b.K(true);
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
        } else {
            com.sds.android.ttpod.framework.storage.environment.b.K(false);
        }
        startSoundSearch();
    }

    protected void onPause() {
        super.onPause();
        cancelSoundSearch();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PAUSED && com.sds.android.ttpod.framework.storage.environment.b.aE()) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
        }
        this.mHandler.removeCallbacksAndMessages(null);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.SEARCH_RECOGNIZE_ERROR, i.a(cls, "searchRecognizeError", com.sds.android.ttpod.framework.modules.search.b.a.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEARCH_RECOGNIZE_SUCCESS, i.a(cls, "searchRecognizeSuccess", List.class));
    }

    public void searchRecognizeError(com.sds.android.ttpod.framework.modules.search.b.a aVar) {
        g.a(TAG, "searchRecognizeError: " + aVar);
        if (aVar == com.sds.android.ttpod.framework.modules.search.b.a.NOT_CONNECT) {
            updateUI(a.RECOGNIZE_NOT_CONNECT);
        } else {
            updateUI(a.RECOGNIZE_FAIL);
        }
    }

    public void searchRecognizeSuccess(List<DoresoMusicTrack> list) {
        if (list == null || list.isEmpty()) {
            updateUI(a.RECOGNIZE_FAIL);
            return;
        }
        u.d();
        SoundSearchInfo[] soundSearchInfoArr = new SoundSearchInfo[list.size()];
        int i = 0;
        for (DoresoMusicTrack soundSearchInfo : list) {
            int i2 = i + 1;
            soundSearchInfoArr[i] = new SoundSearchInfo(soundSearchInfo);
            i = i2;
        }
        processResults(soundSearchInfoArr);
    }

    private void processResults(SoundSearchInfo[] soundSearchInfoArr) {
        g.a(TAG, "search start ");
        this.mStartTime = System.currentTimeMillis();
        com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<SoundSearchInfo[], SoundSearchInfo[]>(this, soundSearchInfoArr) {
            final /* synthetic */ SoundSearchActivity a;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((SoundSearchInfo[]) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                b((SoundSearchInfo[]) obj);
            }

            protected SoundSearchInfo[] a(SoundSearchInfo[] soundSearchInfoArr) {
                if (this.a.isFinishing()) {
                    return null;
                }
                List arrayList = new ArrayList();
                for (SoundSearchInfo soundSearchInfo : soundSearchInfoArr) {
                    OnlineMediaItemsResult onlineMediaItemsResult = (OnlineMediaItemsResult) com.sds.android.cloudapi.ttpod.a.s.a(soundSearchInfo.e() + " " + soundSearchInfo.c(), 1, 50).g();
                    if (onlineMediaItemsResult != null) {
                        List dataList = onlineMediaItemsResult.getDataList();
                        if (dataList == null || dataList.isEmpty()) {
                            com.sds.android.cloudapi.ttpod.a.s.a(soundSearchInfo.e() + " " + soundSearchInfo.c()).a(null);
                        } else {
                            this.a.chooseSuitableMediaItem(soundSearchInfo, dataList);
                            arrayList.add(soundSearchInfo);
                        }
                    } else {
                        com.sds.android.cloudapi.ttpod.a.s.a(soundSearchInfo.e() + " " + soundSearchInfo.c()).a(null);
                    }
                }
                if (arrayList.isEmpty()) {
                    return null;
                }
                SoundSearchInfo[] soundSearchInfoArr2 = new SoundSearchInfo[arrayList.size()];
                arrayList.toArray(soundSearchInfoArr2);
                return soundSearchInfoArr2;
            }

            protected void b(SoundSearchInfo[] soundSearchInfoArr) {
                long currentTimeMillis = System.currentTimeMillis() - this.a.mStartTime;
                g.a(SoundSearchActivity.TAG, "search end, cost time: " + (System.currentTimeMillis() - this.a.mStartTime) + "ms");
                g.a(SoundSearchActivity.TAG, "search end, result: " + Arrays.toString(soundSearchInfoArr));
                if (soundSearchInfoArr != null) {
                    if (soundSearchInfoArr.length == 1) {
                        this.a.startActivity(new Intent(this.a, SoundSearchResultActivity.class).putExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT, soundSearchInfoArr[0]).putExtra(SoundSearchActivity.EXTRA_RECOGNIZE_TIME, currentTimeMillis));
                    } else {
                        this.a.startActivity(new Intent(this.a, SoundSearchMultiResultActivity.class).putExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT, soundSearchInfoArr).putExtra(SoundSearchActivity.EXTRA_RECOGNIZE_TIME, currentTimeMillis));
                    }
                    this.a.updateUI(a.RECOGNIZE_IDLE);
                    return;
                }
                this.a.updateUI(a.RECOGNIZE_FAIL);
            }
        });
    }

    private void chooseSuitableMediaItem(SoundSearchInfo soundSearchInfo, List<OnlineMediaItem> list) {
        for (OnlineMediaItem onlineMediaItem : list) {
            if (onlineMediaItem.getTitle().equals(soundSearchInfo.e()) && onlineMediaItem.getArtist().equals(soundSearchInfo.c())) {
                break;
            }
        }
        OnlineMediaItem onlineMediaItem2 = null;
        if (onlineMediaItem2 == null) {
            onlineMediaItem2 = (OnlineMediaItem) list.get(0);
        }
        soundSearchInfo.a(k.a(onlineMediaItem2));
        this.mRecognizerHistory.a((Object) soundSearchInfo);
    }

    private void startSoundSearch() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_RECOGNIZE, new Object[0]));
        updateUI(a.RECOGNIZING);
        this.mHandler.postDelayed(this.mTimeOutRunnable, 15000);
        this.mHandler.sendEmptyMessage(0);
    }

    private void stopSoundSearch() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.STOP_SEARCH_RECOGNIZE, new Object[0]));
    }

    private void cancelSoundSearch() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CANCEL_SEARCH_RECOGNIZE, new Object[0]));
        updateUI(a.RECOGNIZE_IDLE);
    }

    private void updateUI(a aVar) {
        int i = R.drawable.img_imageview_soundsearch_state_fail;
        if (!isFinishing()) {
            CharSequence charSequence;
            CharSequence charSequence2;
            g.a(TAG, "updateUI mState: " + aVar);
            int i2 = aVar == a.RECOGNIZING ? 1 : 0;
            switch (aVar) {
                case RECOGNIZING:
                    charSequence = getText(R.string.soundsearch_state_regoznizing_title).toString();
                    charSequence2 = getText(R.string.soundsearch_state_regoznizing).toString();
                    i = R.drawable.img_imageview_soundsearch_state_regoznizing;
                    break;
                case RECOGNIZE_FAIL:
                    charSequence = getText(R.string.soundsearch_state_fail_title).toString();
                    charSequence2 = getText(R.string.soundsearch_state_fail).toString();
                    break;
                case RECOGNIZE_NOT_CONNECT:
                    charSequence = getText(R.string.soundsearch_state_not_connect_title).toString();
                    charSequence2 = getText(R.string.soundsearch_state_not_connect).toString();
                    break;
                default:
                    charSequence = getText(R.string.soundsearch_state_idle_title).toString();
                    charSequence2 = getText(R.string.soundsearch_state_idle).toString();
                    i = R.drawable.img_imageview_soundsearch_state_idle;
                    break;
            }
            this.mRecognizeAnimView.setVisibility(i2 != 0 ? 0 : 8);
            this.mImageViewState.setImageDrawable(getResources().getDrawable(i));
            this.mRecognizerTitle.setText(charSequence);
            this.mRecognizerSubTitle.setText(charSequence2);
            if (i2 == 0) {
                this.mHandler.removeMessages(0);
            }
        }
    }
}
