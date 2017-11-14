package com.sds.android.ttpod.component.d.a;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.Iterator;

/* PlayerMenuDialog */
public class n extends Dialog {
    private SeekBar a;
    private a b;
    private b c;
    private AudioManager d;
    private IconTextView e;
    private long f;
    private ArrayList<com.sds.android.ttpod.component.b.a> g;
    private OnClickListener h = new OnClickListener(this) {
        final /* synthetic */ n a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            com.sds.android.ttpod.component.b.a aVar = (com.sds.android.ttpod.component.b.a) view.getTag();
            this.a.dismiss();
            SUserEvent sUserEvent;
            switch (aVar.g()) {
                case 0:
                    this.a.b.onPictureOptionSelected();
                    l.u();
                    sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_MENU_SEARCH_PIC.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue());
                    sUserEvent.append(MediasColumns.SONG_ID, Long.valueOf(this.a.f));
                    sUserEvent.post();
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_pic");
                    return;
                case 1:
                    this.a.b.onLyricOptionSelected();
                    l.v();
                    sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_MENU_SEARCH_LYRIC.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue());
                    sUserEvent.append(MediasColumns.SONG_ID, Long.valueOf(this.a.f));
                    sUserEvent.post();
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_lyric");
                    return;
                case 2:
                    this.a.b.onAdjustOptionSelected();
                    l.w();
                    sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_MENU_CONFIG_LYRIC.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue());
                    sUserEvent.append(MediasColumns.SONG_ID, Long.valueOf(this.a.f));
                    sUserEvent.post();
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_setting_lyric");
                    return;
                case 3:
                    this.a.b.onSetRingtoneSelected();
                    sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_MENU_SET_RING.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue());
                    sUserEvent.append(MediasColumns.SONG_ID, Long.valueOf(this.a.f));
                    sUserEvent.post();
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_ring");
                    return;
                case 4:
                    this.a.b.onMoreOptionSelected();
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_MENU_MORE.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue()).post();
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_more");
                    return;
                case 5:
                    this.a.b.onDownloadSelected();
                    l.z();
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_download");
                    return;
                case 6:
                    this.a.b.onShareOptionSelected();
                    l.x();
                    return;
                default:
                    return;
            }
        }
    };

    /* PlayerMenuDialog */
    public interface a {
        void onAdjustOptionSelected();

        void onDownloadSelected();

        void onLyricOptionSelected();

        void onMoreOptionSelected();

        void onPictureOptionSelected();

        void onSetRingtoneSelected();

        void onShareOptionSelected();
    }

    /* PlayerMenuDialog */
    public interface b {
        void onVolumeChanged(int i, int i2);
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public n(Context context) {
        boolean z;
        super(context, R.style.Dialog_Transparent);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.popups_lyric_pics_panel);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = com.sds.android.ttpod.common.c.a.d();
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.Dialog_Window_Anim);
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        this.f = M.getSongID() == null ? 0 : M.getSongID().longValue();
        this.g = new ArrayList();
        if (M.isNull() || e.a(M.getLocalDataSource()) || !M.isOnline()) {
            z = false;
        } else {
            z = true;
        }
        this.g.add(new com.sds.android.ttpod.component.b.a(0, (int) R.string.icon_search_pic, (int) R.string.menu_search_artist_pic));
        this.g.add(new com.sds.android.ttpod.component.b.a(1, (int) R.string.icon_search_lyric, (int) R.string.menu_search_lyric));
        this.g.add(new com.sds.android.ttpod.component.b.a(2, (int) R.string.icon_edit_lyric, (int) R.string.adjust_lyric));
        if (z) {
            this.g.add(new com.sds.android.ttpod.component.b.a(5, (int) R.string.icon_download, (int) R.string.setting_download));
            this.g.add(new com.sds.android.ttpod.component.b.a(4, (int) R.string.icon_more_horizontal, (int) R.string.more));
        } else if (e.a(M.getLocalDataSource())) {
            this.g.add(new com.sds.android.ttpod.component.b.a(3, (int) R.string.icon_set_ringtone, (int) R.string.ringtone));
            this.g.add(new com.sds.android.ttpod.component.b.a(4, (int) R.string.icon_more_horizontal, (int) R.string.more));
        } else if (M.isTtfmRadioSongList()) {
            this.g.add(new com.sds.android.ttpod.component.b.a(5, (int) R.string.icon_download, (int) R.string.setting_download));
        }
        a(context);
    }

    private void a(int i) {
        this.e.setText(i == 0 ? R.string.icon_volume_silence : R.string.icon_volume_voice);
    }

    private void a(Context context) {
        this.d = (AudioManager) getContext().getSystemService("audio");
        this.a = (SeekBar) findViewById(R.id.lrc_pics_volume);
        this.a.setMax(this.d.getStreamMaxVolume(3));
        this.e = (IconTextView) findViewById(R.id.itv_volume);
        this.a.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (this.a.c != null) {
                    this.a.c.onVolumeChanged(i, seekBar.getMax());
                }
                this.a.a(i);
                if (z) {
                    this.a.d.setStreamVolume(3, i, 0);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                l.y();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_volume");
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_function);
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            com.sds.android.ttpod.component.b.a aVar = (com.sds.android.ttpod.component.b.a) it.next();
            View inflate = LayoutInflater.from(context).inflate(R.layout.popups_lyric_pics_panel_item, null);
            ((IconTextView) inflate.findViewById(R.id.itv_icon)).setText(aVar.e());
            ((TextView) inflate.findViewById(R.id.tv_text)).setText(aVar.d());
            inflate.setTag(aVar);
            inflate.setOnClickListener(this.h);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.weight = 1.0f;
            linearLayout.addView(inflate, layoutParams);
        }
    }

    public void show() {
        super.show();
        this.a.post(new Runnable(this) {
            final /* synthetic */ n a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d != null && this.a.a != null) {
                    int streamVolume = this.a.d.getStreamVolume(3);
                    this.a.a.setProgress(streamVolume);
                    this.a.a(streamVolume);
                }
            }
        });
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
            case 82:
                dismiss();
                break;
            case 24:
            case 25:
                this.a.setProgress(this.d.getStreamVolume(3));
                break;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
