package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;

public class AppWidget91Layout4x1 extends AppWidget91Base {
    private String a;
    private ImageView b;
    private TextView c;
    private ImageButton d;
    private ImageButton e;
    private ImageButton f;
    private ImageButton g;
    private ProgressBar h;
    private boolean i;

    public AppWidget91Layout4x1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected ImageView getAlbumImageView() {
        return this.b;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (ImageView) findViewById(R.id.image_album_cover);
        this.c = (TextView) findViewById(R.id.text_title);
        this.d = (ImageButton) findViewById(R.id.button_play_prev);
        this.e = (ImageButton) findViewById(R.id.button_play_pause);
        this.f = (ImageButton) findViewById(R.id.button_play_next);
        this.g = (ImageButton) findViewById(R.id.button_minilyric);
        this.h = (ProgressBar) findViewById(R.id.seekbar_progress);
        this.d.setOnLongClickListener(this);
        this.e.setOnLongClickListener(this);
        this.f.setOnLongClickListener(this);
        this.g.setOnLongClickListener(this);
        this.c.setOnLongClickListener(this);
        this.b.setOnLongClickListener(this);
        this.h.setOnLongClickListener(this);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(Action.START_ENTRY);
            }
        });
        this.g.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("switch_desktop_lyric_hide_show_command");
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("previous_command");
            }
        });
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("next_command");
            }
        });
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x1 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("play_pause_command");
                this.a.mClickedPlay = true;
            }
        });
        g.a("AppWidget91Layout4x1", "onFinishInflate");
    }

    protected void onMetaChanged(String str, String str2, String str3) {
        if (TTTextUtils.isValidateMediaString(str)) {
            str3 = TTTextUtils.validateString(BaseApplication.e(), str) + " - " + TTTextUtils.validateString(BaseApplication.e(), str3);
        }
        this.c.setText(str3);
        MediaItem w = this.mSupport.w();
        if (!(w == null || w.isNull())) {
            this.mSongDuration = w.getDuration().intValue();
        }
        a(0, this.mSongDuration, this.mSupport.m());
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

    protected void setPlayStateBackground(PlayStatus playStatus) {
        if (!m.a(this.a)) {
            String str;
            String str2;
            if (PlayStatus.STATUS_PLAYING == playStatus) {
                str = this.a + "pandawidget_ttpod_pausenormal_4x1.png";
                str2 = this.a + "pandawidget_ttpod_pausepressed_4x1.png";
            } else {
                str = this.a + "pandawidget_ttpod_playnormal_4x1.png";
                str2 = this.a + "pandawidget_ttpod_playpressed_4x1.png";
            }
            if (e.b(str) && e.b(str2)) {
                Drawable newSelector = AppWidget91Base.newSelector(getContext(), str, str2);
                if (newSelector != null) {
                    this.e.setImageDrawable(newSelector);
                    return;
                }
            }
        }
        this.e.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.img_appwidget_pause : R.drawable.img_appwidget_play);
    }

    protected void setPlayModeBackground(f fVar) {
    }

    protected void setMiniLyricButton(boolean z) {
        if (!m.a(this.a)) {
            String str = z ? this.a + "pandawidget_ttpod_minilyricon_4x1.png" : this.a + "pandawidget_ttpod_minilyricoff_4x1.png";
            if (e.b(str)) {
                Drawable newSelector = AppWidget91Base.newSelector(getContext(), str, str);
                if (newSelector != null) {
                    this.g.setImageDrawable(newSelector);
                    return;
                }
            }
        }
        this.g.setImageResource(z ? R.drawable.img_appwidget_minilyric_on : R.drawable.img_appwidget_minilyric_off);
    }

    protected void applyTheme(Intent intent) {
        String stringExtra = intent.getStringExtra("widgetSkinPath");
        if (!m.a(stringExtra)) {
            String str = stringExtra + "/widget/ttpod/";
            if (!str.equals(this.a)) {
                this.a = str;
                if (FeedbackItem.STATUS_WAITING.equals(stringExtra.substring(stringExtra.indexOf("Themes") + 7)) && !this.i) {
                    this.i = true;
                    b();
                } else if (new File(this.a).exists()) {
                    this.i = false;
                    a();
                } else if (!this.i) {
                    this.i = true;
                    b();
                }
            }
        }
    }

    protected void updatePlayTime() {
        a(this.mSupport.l().intValue(), this.mSongDuration, this.mSupport.m());
    }

    protected void registerPreferenceListener() {
        b.a(getContext(), c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        b.a(getContext(), c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    protected void unRegisterPreferenceListener() {
        b.b(getContext(), c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.mOnPreferencesChangeListener);
        b.b(getContext(), c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    private void a() {
        try {
            String str = this.a + "pandawidget_ttpod_prevnormal_4x1.png";
            String str2 = this.a + "pandawidget_ttpod_prevpressed_4x1.png";
            if (e.b(str) && e.b(str2)) {
                this.d.setImageDrawable(AppWidget91Base.newSelector(getContext(), str, str2));
            } else {
                this.d.setImageResource(R.drawable.img_appwidget_play_prev);
            }
            str = this.a + "pandawidget_ttpod_nextnormal_4x1.png";
            str2 = this.a + "pandawidget_ttpod_nextpressed_4x1.png";
            if (e.b(str) && e.b(str2)) {
                this.f.setImageDrawable(AppWidget91Base.newSelector(getContext(), str, str2));
            } else {
                this.f.setImageResource(R.drawable.img_appwidget_play_next);
            }
            setMiniLyricButton(b.s());
            setPlayStateBackground(this.mSupport.n());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void b() {
        setMiniLyricButton(b.s());
        setPlayStateBackground(this.mSupport.n());
        this.d.setImageResource(R.drawable.img_appwidget_play_prev);
        this.f.setImageResource(R.drawable.img_appwidget_play_next);
    }
}
