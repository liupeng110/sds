package com.sds.android.ttpod.fragment.settings;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.setting.AboutActivity;
import com.sds.android.ttpod.activities.setting.DesktopLyricSettingActivity;
import com.sds.android.ttpod.activities.setting.DownloadLocationActivity;
import com.sds.android.ttpod.activities.setting.HeadsetControlActivity;
import com.sds.android.ttpod.activities.setting.LyricPictureSettingActivity;
import com.sds.android.ttpod.activities.setting.MoreSettingActivity;
import com.sds.android.ttpod.activities.setting.MvPlayAndDownloadQualityActivity;
import com.sds.android.ttpod.activities.setting.SongAuditionAndDownloadQualityActivity;
import com.sds.android.ttpod.activities.setting.b;
import com.sds.android.ttpod.activities.setting.d;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.core.b.a.c;
import com.sds.android.ttpod.framework.modules.version.VersionUpdateModule;
import com.tencent.open.SocialConstants;
import java.util.HashMap;

public class SettingEntryFragment extends BaseFragment {
    private static final int ID_ABOUT_TTPOD = 11;
    private static final int ID_AUTO_SAVE_WHEN_LISTEN = 12;
    private static final int ID_CLEAR_CACHE = 10;
    private static final int ID_DESKTOP_LYRIC = 0;
    private static final int ID_DOWNLOAD_ONLY_WIFI = 5;
    private static final int ID_DOWNLOAD_POSITION = 8;
    private static final int ID_HEADSET_CONTROL = 3;
    private static final int ID_LISTEN_AND_DOWNLOAD_QUALITY = 6;
    private static final int ID_LOCK_SCREEN_LYRIC = 1;
    private static final int ID_LYRIC_AND_PICTURE = 9;
    private static final int ID_MORE = 4;
    private static final int ID_MV_PLAY_AND_DOWNLOAD_QUALITY = 7;
    private static final int ID_SHAKE_CHANGE_SONG = 2;
    private static final int LOCK_SCREEN_LYRIC_CLOSE = 0;
    private static final int LOCK_SCREEN_LYRIC_FULL_SCREEN = 2;
    private static final int LOCK_SCREEN_LYRIC_NOT_FULL_SCREEN = 1;
    private static final int SHAKE_CLOSE = 0;
    private static final int STATUS_CLOSE = 2;
    private static final int STATUS_OPEN = 1;
    private static final int TYPE_LOCK_SCREEN_LYRIC_CLOSE = 0;
    private static final int TYPE_LOCK_SCREEN_LYRIC_FULL_SCREEN = 2;
    private static final int TYPE_LOCK_SCREEN_LYRIC_NOT_FULL_SCREEN = 1;
    private b mBaseSettingCard;
    private a.b mOnItemClickListener = new a.b(this) {
        final /* synthetic */ SettingEntryFragment a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            CharSequence d = aVar.d();
            Activity activity = this.a.getActivity();
            if (activity != null) {
                boolean z = false;
                if (aVar instanceof Checkable) {
                    z = ((Checkable) aVar).isChecked();
                }
                switch (aVar.g()) {
                    case 0:
                        d.a(activity, DesktopLyricSettingActivity.class, d);
                        t.a(r.ACTION_SETTING_MENU_DESK_LYRIC, s.PAGE_DESKTOP_LYRIC);
                        return;
                    case 1:
                        this.a.showLockScreenLyricSetting(aVar, i);
                        t.a(r.ACTION_OPEN_MENU_OF_LOCK_LYRIC, s.PAGE_NONE);
                        return;
                    case 2:
                        this.a.showSensitivitySetting(aVar, i);
                        t.a(r.ACTION_OPEN_MENU_OF_SHAKE_CHANGE_SONG, s.PAGE_NONE);
                        return;
                    case 3:
                        d.a(activity, HeadsetControlActivity.class, d);
                        t.a(r.ACTION_SETTING_HEADSET_CONTROL, s.PAGE_HEADSET_CONTROL);
                        return;
                    case 4:
                        d.a(activity, MoreSettingActivity.class, d);
                        l.r();
                        t.a(r.ACTION_SETTING_MORE, s.PAGE_SETTING_MORE);
                        return;
                    case 5:
                        com.sds.android.ttpod.framework.storage.environment.b.y(z);
                        l.a(z);
                        t.a(r.ACTION_SETTING_ONLY_WIFI, z);
                        com.sds.android.ttpod.framework.a.b.b.a("wifi_button_only", z);
                        return;
                    case 6:
                        d.a(activity, SongAuditionAndDownloadQualityActivity.class, d);
                        l.r();
                        t.a(r.ACTION_SETTING_PLAY_DOWNLOAD_QUILITY, s.PAGE_AUDITION_AND_DOWNLOAD_QUALITY);
                        return;
                    case 7:
                        d.a(activity, MvPlayAndDownloadQualityActivity.class, d);
                        l.r();
                        t.a(r.ACTION_SETTING_PLAY_DOWNLOAD_QUILITY, s.PAGE_AUDITION_AND_DOWNLOAD_QUALITY);
                        return;
                    case 8:
                        d.a(activity, DownloadLocationActivity.class, "歌曲存储位置设置");
                        t.a(r.ACTION_SETTING_MUSIC_SAVE_PATH, s.PAGE_DOWNLOAD_LOCATION);
                        return;
                    case 9:
                        d.a(activity, LyricPictureSettingActivity.class, d);
                        l.r();
                        t.a(r.ACTION_SETTING_MUSIC_PIC, s.PAGE_LYRIC_AND_PICTURE);
                        return;
                    case 10:
                        this.a.cleanCache();
                        l.p();
                        t.a(r.ACTION_SETTING_CLEAR_CACHE, s.PAGE_NONE);
                        com.sds.android.ttpod.framework.a.b.b.a("setting_clean_cache");
                        return;
                    case 11:
                        d.a(activity, AboutActivity.class, d);
                        l.r();
                        t.a(r.ACTION_SETTING_ABOUT, s.PAGE_ABOUT_TTPOD);
                        return;
                    case 12:
                        com.sds.android.ttpod.framework.storage.environment.b.z(z);
                        com.sds.android.ttpod.framework.a.b.b.a("listen_download", z);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private HashMap<c, Integer> mSensitivityTextResIdMap = new HashMap(c.values().length);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_SETTING_PAGE);
        setTBSPage("tt_setting");
        trackModule("setting");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_settings, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSensitivityTextResIdMap.put(c.SMOOTH_SHAKE, Integer.valueOf(R.string.shake_play_smooth));
        this.mSensitivityTextResIdMap.put(c.EASY_SHAKE, Integer.valueOf(R.string.shake_play_easy));
        this.mSensitivityTextResIdMap.put(c.NORMAL_SHAKE, Integer.valueOf(R.string.shake_play_normal));
        this.mSensitivityTextResIdMap.put(c.HARD_SHAKE, Integer.valueOf(R.string.shake_play_hard));
        this.mSensitivityTextResIdMap.put(c.EXTREME_SHAKE, Integer.valueOf(R.string.shake_play_extreme));
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.setting_card_container_main);
        this.mBaseSettingCard = (b) buildBaseSettingCard();
        ((LinearLayout) linearLayout.findViewById(R.id.setting_card_container_base_setting)).addView(this.mBaseSettingCard.e());
        ((LinearLayout) linearLayout.findViewById(R.id.setting_card_container_download_setting)).addView(buildDownloadSettingCard().e());
        ((LinearLayout) linearLayout.findViewById(R.id.setting_card_container_about)).addView(buildAboutSettingCard().e());
    }

    private com.sds.android.ttpod.component.b.c buildBaseSettingCard() {
        return new b(getActivity(), new com.sds.android.ttpod.activities.setting.c[]{new com.sds.android.ttpod.activities.setting.c(0, 0, (int) R.string.setting_desktop_lyric, 0, (int) R.string.icon_arrow_right, true), (com.sds.android.ttpod.activities.setting.c) new com.sds.android.ttpod.activities.setting.c(1, 0, (int) R.string.setting_lockscreen_lyric, 0, (int) R.string.icon_arrow_right, true).c(getLockScreenLyricSettingText()), (com.sds.android.ttpod.activities.setting.c) new com.sds.android.ttpod.activities.setting.c(2, 0, (int) R.string.setting_shake_play, 0, (int) R.string.icon_arrow_right, true).c(getSensitivitySettingText()), new com.sds.android.ttpod.activities.setting.c(3, 0, (int) R.string.setting_headset, 0, (int) R.string.icon_arrow_right, true), new com.sds.android.ttpod.activities.setting.c(4, 0, (int) R.string.more, 0, (int) R.string.icon_arrow_right, true)}, R.string.base_setting, this.mOnItemClickListener);
    }

    private com.sds.android.ttpod.component.b.c buildDownloadSettingCard() {
        r10 = new com.sds.android.ttpod.activities.setting.c[7];
        r10[0] = new com.sds.android.ttpod.activities.setting.a(5, 0, R.string.wifi_only, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.I());
        r10[1] = new com.sds.android.ttpod.activities.setting.a(12, 0, R.string.auto_save_when_listen, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.J());
        r10[2] = new com.sds.android.ttpod.activities.setting.c(6, 0, (int) R.string.setting_listen_and_download_quality, 0, R.string.icon_arrow_right, true);
        r10[3] = new com.sds.android.ttpod.activities.setting.c(7, 0, (int) R.string.setting_mv_play_and_download_quality, 0, R.string.icon_arrow_right, true);
        r10[4] = new com.sds.android.ttpod.activities.setting.c(8, 0, (int) R.string.setting_audio_auto_download_dir, 0, R.string.icon_arrow_right, true);
        r10[5] = new com.sds.android.ttpod.activities.setting.c(9, 0, (int) R.string.setting_lyric_and_pic, 0, R.string.icon_arrow_right, true);
        r10[6] = new com.sds.android.ttpod.activities.setting.c(10, 0, (int) R.string.setting_cache_clean, 0, R.string.icon_arrow_right, true);
        return new b(getActivity(), r10, R.string.network_download, this.mOnItemClickListener);
    }

    private com.sds.android.ttpod.component.b.c buildAboutSettingCard() {
        com.sds.android.ttpod.component.b.c bVar = new b(getActivity(), new com.sds.android.ttpod.activities.setting.c[]{new com.sds.android.ttpod.activities.setting.c(11, 0, (int) R.string.setting_about_ttpod, 0, (int) R.string.icon_arrow_right, true)}, R.string.setting_about, this.mOnItemClickListener);
        setLatestUpdateVersionDot(bVar);
        return bVar;
    }

    private void setLatestUpdateVersionDot(b bVar) {
        View a = bVar.a(11);
        if (a != null) {
            a.setVisibility(VersionUpdateModule.hasNewVersion() ? 0 : 8);
        }
    }

    private void showLockScreenLyricSetting(final a aVar, final int i) {
        f.a(getActivity(), aVar.d(), new com.sds.android.ttpod.component.b.d[]{new com.sds.android.ttpod.component.b.d(0, R.string.setting_close), new com.sds.android.ttpod.component.b.d(1, R.string.setting_not_full_screen), new com.sds.android.ttpod.component.b.d(2, R.string.setting_full_screen)}, getLockScreenLyricCheckedId(), new a.b(this) {
            final /* synthetic */ SettingEntryFragment c;

            public void a(a aVar, int i) {
                if (i == 0) {
                    aVar.a(aVar.d());
                    this.c.mBaseSettingCard.a((com.sds.android.ttpod.activities.setting.c) aVar, i);
                    com.sds.android.ttpod.framework.storage.environment.b.i(false);
                } else {
                    com.sds.android.ttpod.framework.storage.environment.b.i(true);
                    aVar.a(aVar.d());
                    this.c.mBaseSettingCard.a((com.sds.android.ttpod.activities.setting.c) aVar, i);
                    if (i == 1) {
                        com.sds.android.ttpod.framework.storage.environment.b.w(false);
                    } else {
                        com.sds.android.ttpod.framework.storage.environment.b.w(true);
                    }
                }
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SETTING_LOCK_LYRIC_SELECT.getValue(), 0, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.append(SocialConstants.PARAM_TYPE, Integer.valueOf(i)).post();
                com.sds.android.ttpod.framework.a.b.b.a("lock_screen_lrc", i);
            }
        }, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.r>(this) {
            final /* synthetic */ SettingEntryFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.r rVar) {
            }
        });
    }

    private int getLockScreenLyricCheckedId() {
        if (!com.sds.android.ttpod.framework.storage.environment.b.j(com.sds.android.ttpod.framework.modules.e.a.isAllowDefaultLockScreen())) {
            return 0;
        }
        if (com.sds.android.ttpod.framework.storage.environment.b.G()) {
            return 2;
        }
        return 1;
    }

    private int getLockScreenLyricSettingText() {
        if (getLockScreenLyricCheckedId() == 0) {
            return R.string.setting_close;
        }
        if (getLockScreenLyricCheckedId() == 1) {
            return R.string.setting_not_full_screen;
        }
        return R.string.setting_full_screen;
    }

    private void showSensitivitySetting(final a aVar, final int i) {
        f.a(getActivity(), aVar.d(), new com.sds.android.ttpod.component.b.d[]{new com.sds.android.ttpod.component.b.d(0, R.string.setting_close), new com.sds.android.ttpod.component.b.d(c.SMOOTH_SHAKE.ordinal() + 1, ((Integer) this.mSensitivityTextResIdMap.get(c.SMOOTH_SHAKE)).intValue()), new com.sds.android.ttpod.component.b.d(c.EASY_SHAKE.ordinal() + 1, ((Integer) this.mSensitivityTextResIdMap.get(c.EASY_SHAKE)).intValue()), new com.sds.android.ttpod.component.b.d(c.NORMAL_SHAKE.ordinal() + 1, ((Integer) this.mSensitivityTextResIdMap.get(c.NORMAL_SHAKE)).intValue()), new com.sds.android.ttpod.component.b.d(c.HARD_SHAKE.ordinal() + 1, ((Integer) this.mSensitivityTextResIdMap.get(c.HARD_SHAKE)).intValue()), new com.sds.android.ttpod.component.b.d(c.EXTREME_SHAKE.ordinal() + 1, ((Integer) this.mSensitivityTextResIdMap.get(c.EXTREME_SHAKE)).intValue())}, getSensitivitySettingCheckedId(), new a.b(this) {
            final /* synthetic */ SettingEntryFragment c;

            public void a(a aVar, int i) {
                if (i == 0) {
                    aVar.a(aVar.d());
                    this.c.mBaseSettingCard.a((com.sds.android.ttpod.activities.setting.c) aVar, i);
                    com.sds.android.ttpod.framework.storage.environment.b.o(false);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_SHAKE_SWITCH_SONG_ENABLED, Boolean.valueOf(false)));
                } else {
                    if (!com.sds.android.ttpod.framework.storage.environment.b.x()) {
                        com.sds.android.ttpod.framework.storage.environment.b.o(true);
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_SHAKE_SWITCH_SONG_ENABLED, Boolean.valueOf(true)));
                    }
                    aVar.c(((Integer) this.c.mSensitivityTextResIdMap.get(c.values()[aVar.g() - 1])).intValue());
                    this.c.mBaseSettingCard.a((com.sds.android.ttpod.activities.setting.c) aVar, i);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_SHAKE_SWITCH_SONG_SENSITIVITY, r1));
                }
                new SUserEvent("PAGE_CLICK", r.ACTION_OPEN_MENU_OF_SHAKE_CHANGE_SONG_SELECT.getValue(), s.PAGE_SETTING_PAGE.getValue(), 0).append(SocialConstants.PARAM_TYPE, Integer.valueOf(i)).post();
                com.sds.android.ttpod.framework.a.b.b.a("shack_cut_song", i);
            }
        }, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.r>(this) {
            final /* synthetic */ SettingEntryFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.d.a.r rVar) {
            }
        });
    }

    private int getSensitivitySettingCheckedId() {
        if (com.sds.android.ttpod.framework.storage.environment.b.x()) {
            return com.sds.android.ttpod.framework.storage.environment.b.y().ordinal() + 1;
        }
        return 0;
    }

    private int getSensitivitySettingText() {
        if (getSensitivitySettingCheckedId() == 0) {
            return R.string.setting_close;
        }
        return ((Integer) this.mSensitivityTextResIdMap.get(com.sds.android.ttpod.framework.storage.environment.b.y())).intValue();
    }

    private void cleanCache() {
        f.a(getActivity(), (int) R.string.cache_size_calculating, false, false);
        com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<Void, String[]>(this, null) {
            final /* synthetic */ SettingEntryFragment a;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((Void) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((String[]) obj);
            }

            protected String[] a(Void voidR) {
                long g = e.g(com.sds.android.ttpod.framework.a.j());
                long g2 = e.g(com.sds.android.ttpod.framework.a.v());
                long g3 = e.g(com.sds.android.ttpod.framework.a.t());
                long g4 = e.g(com.sds.android.ttpod.framework.a.g());
                return new String[]{Formatter.formatFileSize(this.a.getActivity(), g), Formatter.formatFileSize(this.a.getActivity(), g4), Formatter.formatFileSize(this.a.getActivity(), g2 + g3), Formatter.formatFileSize(this.a.getActivity(), e.g(com.sds.android.ttpod.framework.a.s()))};
            }

            protected void a(String[] strArr) {
                if (this.a.getActivity() != null && this.a.isResumeState()) {
                    f.a();
                    f.a(this.a.getActivity(), strArr, this.a.isResumeState());
                }
            }
        });
    }

    private boolean isResumeState() {
        return isViewAccessAble();
    }
}
