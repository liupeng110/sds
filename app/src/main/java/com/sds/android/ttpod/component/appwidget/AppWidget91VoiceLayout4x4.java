package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.a.af;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.VoiceOfChina;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.VoiceOfChinaListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppWidget91VoiceLayout4x4 extends AppWidget91Base implements OnClickListener {
    private static int[] a = new int[]{R.drawable.img_appwidget91_voice_playmode_repeat_all, R.drawable.img_appwidget91_voice_playmode_repeat_current, R.drawable.img_appwidget91_voice_playmode_normal, R.drawable.img_appwidget91_voice_playmode_shuffle};
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private ProgressBar h;
    private TextView i;
    private TextView j;
    private TextView[] k = new TextView[3];
    private LinearLayout l;
    private ImageButton m;
    private ImageButton n;
    private ImageButton o;
    private ImageButton p;
    private ImageButton q;
    private Button r;
    private ImageButton s;
    private ImageView t;
    private View u;
    private RelativeLayout v;
    private List<MediaItem> w = new ArrayList();
    private Timer x;

    public AppWidget91VoiceLayout4x4(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected ImageView getAlbumImageView() {
        return this.b;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        b.setContext(getContext());
        this.t = (ImageView) findViewById(R.id.image_bg_default);
        this.b = (ImageView) findViewById(R.id.image_album_cover);
        this.c = (TextView) findViewById(R.id.text_artist);
        this.e = (TextView) findViewById(R.id.text_album);
        this.d = (TextView) findViewById(R.id.text_title);
        this.f = (TextView) findViewById(R.id.text_time_current);
        this.g = (TextView) findViewById(R.id.text_time_duration);
        this.h = (ProgressBar) findViewById(R.id.seekbar_progress);
        this.l = (LinearLayout) findViewById(R.id.bg_buttons);
        this.v = (RelativeLayout) findViewById(R.id.widget_bg);
        this.m = (ImageButton) findViewById(R.id.button_playmode);
        this.n = (ImageButton) findViewById(R.id.button_play_prev);
        this.o = (ImageButton) findViewById(R.id.button_play_pause);
        this.p = (ImageButton) findViewById(R.id.button_play_next);
        this.q = (ImageButton) findViewById(R.id.button_minilyric);
        this.i = (TextView) findViewById(R.id.text_voice_title);
        this.k[0] = (TextView) findViewById(R.id.text_song1);
        this.k[1] = (TextView) findViewById(R.id.text_song2);
        this.k[2] = (TextView) findViewById(R.id.text_song3);
        this.r = (Button) findViewById(R.id.button_more);
        this.s = (ImageButton) findViewById(R.id.button_refresh);
        this.j = (TextView) findViewById(R.id.textview_load_failed);
        this.u = findViewById(R.id.layout_list_item);
        this.t.setOnLongClickListener(this);
        this.c.setOnLongClickListener(this);
        this.d.setOnLongClickListener(this);
        this.f.setOnLongClickListener(this);
        this.g.setOnLongClickListener(this);
        this.i.setOnLongClickListener(this);
        this.k[0].setOnLongClickListener(this);
        this.k[1].setOnLongClickListener(this);
        this.k[2].setOnLongClickListener(this);
        this.l.setOnLongClickListener(this);
        this.v.setOnLongClickListener(this);
        this.m.setOnLongClickListener(this);
        this.n.setOnLongClickListener(this);
        this.o.setOnLongClickListener(this);
        this.p.setOnLongClickListener(this);
        this.q.setOnLongClickListener(this);
        this.b.setOnLongClickListener(this);
        this.o.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.k[0].setOnClickListener(this);
        this.k[1].setOnClickListener(this);
        this.k[2].setOnClickListener(this);
        if (a()) {
            b();
        } else {
            setVoiceOfChinaListVisible(!this.w.isEmpty());
        }
    }

    public void onDestory(int i) {
        super.onDestory(i);
        this.x.cancel();
    }

    public void onLoad(int i) {
        super.onLoad(i);
        this.x = new Timer();
        try {
            this.x.schedule(new TimerTask(this) {
                final /* synthetic */ AppWidget91VoiceLayout4x4 a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.a()) {
                        this.a.b();
                    }
                }
            }, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-07-25 23:00:00"), 604800000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean a() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) ? false : true;
    }

    private void b() {
        af.a(1213, 1, 1).a(new p<VoiceOfChinaListResult>(this) {
            final /* synthetic */ AppWidget91VoiceLayout4x4 a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((VoiceOfChinaListResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((VoiceOfChinaListResult) baseResult);
            }

            public void a(VoiceOfChinaListResult voiceOfChinaListResult) {
                List dataList = voiceOfChinaListResult.getDataList();
                if (dataList != null && dataList.size() > 0) {
                    VoiceOfChina voiceOfChina = (VoiceOfChina) dataList.get(0);
                    if (voiceOfChina != null) {
                        this.a.i.setText(voiceOfChina.getTitle());
                        this.a.b(voiceOfChina.getId());
                        this.a.setVoiceOfChinaListVisible(true);
                    }
                }
            }

            public void b(VoiceOfChinaListResult voiceOfChinaListResult) {
                this.a.setVoiceOfChinaListVisible(!this.a.w.isEmpty());
            }
        });
    }

    private void setVoiceOfChinaListVisible(boolean z) {
        if (z) {
            this.u.setVisibility(0);
            this.j.setVisibility(4);
            return;
        }
        this.u.setVisibility(4);
        this.j.setVisibility(0);
    }

    private void a(int i) {
        if (i >= 0 && i < this.w.size()) {
            MediaItem mediaItem = (MediaItem) this.w.get(i);
            if (mediaItem != null) {
                try {
                    Context context = getContext();
                    MediaStorage.clearGroup(context, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
                    MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, this.w);
                    context.startService(new Intent(context, SupportService.class).putExtra("command", "sync_command").putExtra("group", MediaStorage.GROUP_ID_ONLINE_TEMPORARY).putExtra("media_source", mediaItem.getID()));
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("mediaItem", mediaItem);
                    context.startService(new Intent(context, SupportService.class).putExtra("command", "play_command").putExtra("group", MediaStorage.GROUP_ID_ONLINE_TEMPORARY).putExtra("media_source", mediaItem.getID()).putExtras(bundle));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void b(int i) {
        af.b(i, 1, 100).a(new p<OnlineMediaItemsResult>(this) {
            final /* synthetic */ AppWidget91VoiceLayout4x4 a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                b((OnlineMediaItemsResult) baseResult);
            }

            public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                a((OnlineMediaItemsResult) baseResult);
            }

            public void a(OnlineMediaItemsResult onlineMediaItemsResult) {
                ArrayList dataList = onlineMediaItemsResult.getDataList();
                if (dataList != null) {
                    this.a.w.clear();
                    Iterator it = dataList.iterator();
                    while (it.hasNext()) {
                        this.a.w.add(k.a((OnlineMediaItem) it.next()));
                    }
                    this.a.a(0, dataList);
                    this.a.a(1, dataList);
                    this.a.a(2, dataList);
                }
            }

            public void b(OnlineMediaItemsResult onlineMediaItemsResult) {
            }
        });
    }

    private void a(int i, ArrayList<OnlineMediaItem> arrayList) {
        if (i < 0 || i >= arrayList.size() || arrayList.get(i) == null) {
            this.k[i].setText("");
            return;
        }
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) arrayList.get(i);
        this.k[i].setText((i + 1) + "." + onlineMediaItem.getTitle() + "--" + onlineMediaItem.getArtist());
    }

    protected void onMetaChanged(String str, String str2, String str3) {
        this.d.setText(str3);
        this.c.setText(TTTextUtils.validateString(getContext(), str));
        this.e.setText(TTTextUtils.validateString(getContext(), str2));
        MediaItem w = this.mSupport.w();
        if (!(w == null || w.isNull())) {
            this.mSongDuration = w.getDuration().intValue();
        }
        a(0, this.mSongDuration, this.mSupport.m());
        this.f.setText(getFormatTime(0));
        this.g.setText(getFormatTime(this.mSongDuration));
    }

    private void a(int i, int i2, float f) {
        int i3 = (int) (((float) i2) * f);
        if (i3 < 0) {
            i3 = 0;
        }
        this.h.setProgress(i);
        this.h.setMax(i2);
        this.h.setSecondaryProgress(i3);
    }

    protected void setPlayModeBackground(f fVar) {
        if (fVar != null) {
            try {
                this.m.setImageResource(a[fVar.ordinal()]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void setMiniLyricButton(boolean z) {
        this.q.setImageResource(z ? R.drawable.img_appwidget91_voice_minilyric_on : R.drawable.img_appwidget91_voice_minilyric_off);
    }

    protected void applyTheme(Intent intent) {
    }

    protected void setPlayStateBackground(PlayStatus playStatus) {
        this.o.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.xml_appwidget91_voice_pause : R.drawable.xml_appwidget91_voice_play);
    }

    protected void registerPreferenceListener() {
        com.sds.android.ttpod.framework.storage.environment.b.a(getContext(), c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        com.sds.android.ttpod.framework.storage.environment.b.a(getContext(), c.PLAY_MODE, this.mOnPreferencesChangeListener);
        com.sds.android.ttpod.framework.storage.environment.b.a(getContext(), c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    protected void unRegisterPreferenceListener() {
        com.sds.android.ttpod.framework.storage.environment.b.b(getContext(), c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        com.sds.android.ttpod.framework.storage.environment.b.b(getContext(), c.PLAY_MODE, this.mOnPreferencesChangeListener);
        com.sds.android.ttpod.framework.storage.environment.b.b(getContext(), c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    protected void updatePlayTime() {
        this.f.setText(getFormatTime(this.mSupport.l().intValue()));
        this.g.setText(getFormatTime(this.mSongDuration));
        a(this.mSupport.l().intValue(), this.mSongDuration, this.mSupport.m());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_album_cover:
                startActivity(Action.START_ENTRY);
                return;
            case R.id.button_play_prev:
                startService("previous_command");
                return;
            case R.id.button_play_pause:
                this.mClickedPlay = true;
                startService("play_pause_command");
                return;
            case R.id.button_play_next:
                startService("next_command");
                return;
            case R.id.button_minilyric:
                startService("switch_desktop_lyric_hide_show_command");
                return;
            case R.id.button_playmode:
                startService("switch_play_mode_command");
                return;
            case R.id.button_refresh:
                b();
                return;
            case R.id.button_more:
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.setData(Uri.parse("http://m.voice.dongting.com/?from=ttpod&v=%s"));
                intent.putExtra(WebFragment.EXTRA_TITLE, getContext().getString(R.string.voice_of_china));
                intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
                intent.addFlags(268435456);
                getContext().startActivity(intent);
                return;
            case R.id.text_song1:
                a(0);
                return;
            case R.id.text_song2:
                a(1);
                return;
            case R.id.text_song3:
                a(2);
                return;
            default:
                return;
        }
    }
}
