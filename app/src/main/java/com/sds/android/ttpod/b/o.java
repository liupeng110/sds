package com.sds.android.ttpod.b;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.e;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.audiofx.EffectDetect;
import java.util.ArrayList;
import java.util.List;

/* MenuUtils */
public final class o {
    private static ArrayList<e> a = new ArrayList();

    public static List<e> a() {
        a.clear();
        a.add(new e(2, 0, R.string.menu_scan_media, ThemeElement.SETTING_SCANNING_IMAGE, R.string.icon_menu_scan_media));
        a.add(new e(1, 0, R.string.sleep_mode, ThemeElement.SETTING_SLEEP_IMAGE, R.string.icon_sleep_mode));
        a.add(new e(4, 0, R.string.theme_background, ThemeElement.SETTING_THEME_IMAGE, R.string.icon_skin));
        a.add(new e(6, 0, EffectDetect.usingAudioPlus() ? R.string.audio_plus : R.string.audio_effect, ThemeElement.SETTING_AUDIO_EFFECT_IMAGE, R.string.icon_audio_effect));
        a.add(new e(3, 0, R.string.repeat_play, ThemeElement.SETTING_PLAY_LOOP_IMAGE, R.string.icon_repeat_play));
        a.add(new e(8, 0, R.string.recognize, ThemeElement.SETTING_RECOGNIZE_IMAGE, R.string.icon_recognize));
        a.add(new e(0, 0, R.string.setting, ThemeElement.SETTING_ICON_IMAGE, R.string.icon_setting));
        a.add(new e(9, 0, R.string.exit, ThemeElement.SETTING_EXIT_IMAGE, R.string.icon_exit));
        a.add(new e(10, 0, R.string.menu_ktv, ThemeElement.SETTING_KTV_IMAGE, R.string.icon_menu_ktv));
        a.add(new e(7, 0, R.string.share_fast_send, ThemeElement.SETTING_SHARE_SONG_IMAGE, R.string.icon_share_fast_send));
        return a;
    }
}
