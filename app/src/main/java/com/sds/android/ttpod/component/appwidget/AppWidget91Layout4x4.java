package com.sds.android.ttpod.component.appwidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;

public class AppWidget91Layout4x4 extends AppWidget91Base {
    private static final String PATH_BG = "pandawidget_ttpod_bg.png";
    private static final String PATH_BG_BUTTONS = "pandawidget_ttpod_bgbuttons.png";
    private static final String PATH_BG_DEFAULT = "pandawidget_ttpod_bgdefault.png";
    private static final String PATH_BG_TIME = "pandawidget_ttpod_bgtime.png";
    private static final String PATH_FOLDER = "/widget/ttpod/";
    private static final String PATH_MODE_NORMAL = "pandawidget_ttpod_modenormal.png";
    private static final String PATH_MODE_REPEAT_ALL = "pandawidget_ttpod_moderepeatall.png";
    private static final String PATH_MODE_REPEAT_CURRENT = "pandawidget_ttpod_moderepeatcurrent.png";
    private static final String PATH_MODE_SHUFFLE = "pandawidget_ttpod_modeshuffle.png";
    private static final String PATH_NEXT_NORMAL = "pandawidget_ttpod_nextnormal.png";
    private static final String PATH_NEXT_PRESSED = "pandawidget_ttpod_nextpressed.png";
    private static final String PATH_PAUSE_NORMAL = "pandawidget_ttpod_pausenormal.png";
    private static final String PATH_PAUSE_PRESSED = "pandawidget_ttpod_pausepressed.png";
    private static final String PATH_PLAY_NORMAL = "pandawidget_ttpod_playnormal.png";
    private static final String PATH_PLAY_PRESSED = "pandawidget_ttpod_playpressed.png";
    private static final String PATH_PREV_NORMAL = "pandawidget_ttpod_prevnormal.png";
    private static final String PATH_PREV_PRESSED = "pandawidget_ttpod_prevpressed.png";
    private static final int POSITION_THEME_START = 7;
    private static final String TAG = "AppWidget91Layout4x4";
    private static final String THEME91_DEFAULT = "0";
    private static int[] mPlayModeIcons = new int[]{R.drawable.img_appwidget91_playmode_repeat_all, R.drawable.img_appwidget91_playmode_repeat_current, R.drawable.img_appwidget91_playmode_normal, R.drawable.img_appwidget91_playmode_shuffle};
    private static String[] mStrPlayModeIcons = new String[]{PATH_MODE_REPEAT_ALL, PATH_MODE_REPEAT_CURRENT, PATH_MODE_NORMAL, PATH_MODE_SHUFFLE};
    private boolean mDefaultTheme91;
    private ImageButton mImageButtonPlay;
    private ImageButton mImageButtonPlayMode;
    private ImageButton mImageButtonPlayNext;
    private ImageButton mImageButtonPlayPrev;
    private ImageView mImageViewAlbum;
    private ImageView mImageViewMiddleDefault;
    private RelativeLayout mLayoutBackground;
    private LinearLayout mLayoutButtons;
    private TextView mTextViewArtist;
    private TextView mTextViewTime;
    private TextView mTextViewTitle;
    private String mTheme91Path;

    public AppWidget91Layout4x4(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected ImageView getAlbumImageView() {
        return this.mImageViewAlbum;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImageViewMiddleDefault = (ImageView) findViewById(R.id.image_bg_default);
        this.mImageViewAlbum = (ImageView) findViewById(R.id.image_album_cover);
        this.mTextViewArtist = (TextView) findViewById(R.id.text_artist);
        this.mTextViewTitle = (TextView) findViewById(R.id.text_title);
        this.mTextViewTime = (TextView) findViewById(R.id.text_time);
        this.mLayoutButtons = (LinearLayout) findViewById(R.id.bg_buttons);
        this.mLayoutBackground = (RelativeLayout) findViewById(R.id.widget_bg);
        this.mImageButtonPlayMode = (ImageButton) findViewById(R.id.button_playmode);
        this.mImageButtonPlayPrev = (ImageButton) findViewById(R.id.button_play_prev);
        this.mImageButtonPlay = (ImageButton) findViewById(R.id.button_play_pause);
        this.mImageButtonPlayNext = (ImageButton) findViewById(R.id.button_play_next);
        this.mImageViewMiddleDefault.setOnLongClickListener(this);
        this.mTextViewArtist.setOnLongClickListener(this);
        this.mTextViewTitle.setOnLongClickListener(this);
        this.mTextViewTime.setOnLongClickListener(this);
        this.mLayoutButtons.setOnLongClickListener(this);
        this.mLayoutBackground.setOnLongClickListener(this);
        this.mImageButtonPlayMode.setOnLongClickListener(this);
        this.mImageButtonPlayPrev.setOnLongClickListener(this);
        this.mImageButtonPlay.setOnLongClickListener(this);
        this.mImageButtonPlayNext.setOnLongClickListener(this);
        this.mImageViewAlbum.setOnLongClickListener(this);
        this.mImageButtonPlay.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x4 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.mClickedPlay = true;
                this.a.startService("play_pause_command");
            }
        });
        this.mImageButtonPlayPrev.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x4 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("previous_command");
            }
        });
        this.mImageButtonPlayNext.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x4 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("next_command");
            }
        });
        this.mImageButtonPlayMode.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x4 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startService("switch_play_mode_command");
            }
        });
        this.mImageViewAlbum.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AppWidget91Layout4x4 a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(Action.START_ENTRY);
            }
        });
        g.a(TAG, "onFinishInflate");
    }

    protected void onMetaChanged(String str, String str2, String str3) {
        this.mTextViewTitle.setText(str3);
        this.mTextViewArtist.setText(TTTextUtils.validateString(getContext(), str));
        MediaItem w = this.mSupport.w();
        if (!(w == null || w.isNull())) {
            this.mSongDuration = w.getDuration().intValue();
        }
        this.mTextViewTime.setText("00:00/" + getFormatTime(this.mSongDuration));
    }

    protected void setPlayModeBackground(f fVar) {
        if (fVar != null) {
            try {
                if (!m.a(this.mTheme91Path) && e.b(this.mTheme91Path + mStrPlayModeIcons[fVar.ordinal()])) {
                    Drawable drawableFromPath = getDrawableFromPath(this.mTheme91Path + mStrPlayModeIcons[fVar.ordinal()]);
                    if (drawableFromPath != null) {
                        this.mImageButtonPlayMode.setImageDrawable(drawableFromPath);
                        return;
                    }
                }
                this.mImageButtonPlayMode.setImageResource(mPlayModeIcons[fVar.ordinal()]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void setMiniLyricButton(boolean z) {
    }

    protected void setPlayStateBackground(PlayStatus playStatus) {
        if (!m.a(this.mTheme91Path)) {
            String str;
            String str2;
            if (PlayStatus.STATUS_PLAYING == playStatus) {
                str = this.mTheme91Path + PATH_PAUSE_NORMAL;
                str2 = this.mTheme91Path + PATH_PAUSE_PRESSED;
            } else {
                str = this.mTheme91Path + PATH_PLAY_NORMAL;
                str2 = this.mTheme91Path + PATH_PLAY_PRESSED;
            }
            if (e.b(str) && e.b(str2)) {
                Drawable newSelector = AppWidget91Base.newSelector(getContext(), str, str2);
                if (newSelector != null) {
                    this.mImageButtonPlay.setImageDrawable(newSelector);
                    return;
                }
            }
        }
        this.mImageButtonPlay.setImageResource(PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.xml_appwidget91_pause : R.drawable.xml_appwidget91_play);
    }

    protected void registerPreferenceListener() {
        b.a(getContext(), c.PLAY_MODE, this.mOnPreferencesChangeListener);
        b.a(getContext(), c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    protected void unRegisterPreferenceListener() {
        b.b(getContext(), c.PLAY_MODE, this.mOnPreferencesChangeListener);
        b.b(getContext(), c.CURRENT_ARTIST_BITMAP_PATH, this.mOnPreferencesChangeListener);
    }

    protected void applyTheme(Intent intent) {
        String stringExtra = intent.getStringExtra("widgetSkinPath");
        if (!m.a(stringExtra)) {
            String str = stringExtra + PATH_FOLDER;
            if (!str.equals(this.mTheme91Path)) {
                this.mTheme91Path = str;
                if (!"0".equals(stringExtra.substring(stringExtra.indexOf("Themes") + 7)) || this.mDefaultTheme91) {
                    if (new File(this.mTheme91Path).exists()) {
                        this.mDefaultTheme91 = false;
                        set91THEME();
                    } else if (!this.mDefaultTheme91) {
                        this.mDefaultTheme91 = true;
                        setDefaultTheme();
                    }
                    setPlayModeBackground(b.l());
                    setPlayStateBackground(this.mSupport.n());
                    return;
                }
                this.mDefaultTheme91 = true;
                setDefaultTheme();
                setPlayModeBackground(b.l());
                setPlayStateBackground(this.mSupport.n());
            }
        }
    }

    protected void updatePlayTime() {
        this.mTextViewTime.setText(getFormatTime(this.mSupport.l().intValue()) + "/" + getFormatTime(this.mSongDuration));
    }

    private void set91THEME() {
        try {
            String str = this.mTheme91Path + PATH_PREV_NORMAL;
            String str2 = this.mTheme91Path + PATH_PREV_PRESSED;
            if (e.b(str) && e.b(str2)) {
                this.mImageButtonPlayPrev.setImageDrawable(AppWidget91Base.newSelector(getContext(), str, str2));
            } else {
                this.mImageButtonPlayPrev.setImageResource(R.drawable.xml_appwidget91_play_prev);
            }
            str = this.mTheme91Path + PATH_NEXT_NORMAL;
            str2 = this.mTheme91Path + PATH_NEXT_PRESSED;
            if (e.b(str) && e.b(str2)) {
                this.mImageButtonPlayNext.setImageDrawable(AppWidget91Base.newSelector(getContext(), str, str2));
            } else {
                this.mImageButtonPlayNext.setImageResource(R.drawable.img_appwidget_play_next);
            }
            if (e.b(this.mTheme91Path + PATH_BG_BUTTONS)) {
                this.mLayoutButtons.setBackgroundDrawable(getDrawableFromPath(this.mTheme91Path + PATH_BG_BUTTONS));
            } else {
                this.mLayoutButtons.setBackgroundResource(R.drawable.img_background_appwidget91_buttons);
            }
            if (e.b(this.mTheme91Path + PATH_BG_TIME)) {
                this.mTextViewTime.setBackgroundDrawable(getDrawableFromPath(this.mTheme91Path + PATH_BG_TIME));
            } else {
                this.mTextViewTime.setBackgroundResource(R.drawable.img_background_appwidget91_time);
            }
            if (e.b(this.mTheme91Path + PATH_BG)) {
                this.mLayoutBackground.setBackgroundDrawable(getDrawableFromPath(this.mTheme91Path + PATH_BG));
            } else {
                this.mLayoutBackground.setBackgroundResource(R.drawable.img_background_appwidget91);
            }
            if (e.b(this.mTheme91Path + PATH_BG_DEFAULT)) {
                this.mImageViewMiddleDefault.setImageDrawable(getDrawableFromPath(this.mTheme91Path + PATH_BG_DEFAULT));
            } else {
                this.mImageViewMiddleDefault.setImageResource(R.drawable.img_background_appwidget91_default);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDefaultTheme() {
        this.mLayoutBackground.setBackgroundResource(R.drawable.img_background_appwidget91);
        this.mImageViewMiddleDefault.setImageResource(R.drawable.img_background_appwidget91_default);
        this.mImageButtonPlayPrev.setImageResource(R.drawable.xml_appwidget91_play_prev);
        this.mImageButtonPlayNext.setImageResource(R.drawable.xml_appwidget91_play_next);
        this.mLayoutButtons.setBackgroundResource(R.drawable.img_background_appwidget91_buttons);
        this.mTextViewTime.setBackgroundResource(R.drawable.img_background_appwidget91_time);
    }
}
