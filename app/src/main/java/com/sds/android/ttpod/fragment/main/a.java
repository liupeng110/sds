package com.sds.android.ttpod.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.AudioEffectFragmentActivity;
import com.sds.android.ttpod.activities.ThemeManagementActivity;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.activities.user.login.LoginActivity;
import com.sds.android.ttpod.b.c;
import com.sds.android.ttpod.component.d.a.m;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.g.a.a.b;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.skin.d.j;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.io.File;

/* DefaultSkinEventHandler */
public class a implements j {
    private Integer a;
    private b b;
    private Activity c;
    private AudioManager d;

    public a(Activity activity, b bVar) {
        this.c = activity;
        this.b = bVar;
    }

    public boolean a(int i, Object obj) {
        g.a("DefaultSkinEventHandler", "actionId:" + i);
        final MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        switch (i) {
            case 0:
                if (this.c instanceof ThemeActivity) {
                    ((ThemeActivity) this.c).toggleMenu();
                    com.sds.android.ttpod.framework.a.b.b.a("global_menu");
                    break;
                }
                break;
            case 4:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_POSITION, this.a));
                this.a = null;
                break;
            case 5:
                if (!((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue()) {
                    f.b(this.c, null);
                    break;
                }
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.STOP_SLEEP_MODE, new Object[0]));
                f.a(this.c.getString(R.string.cancel_sleep_mode));
                break;
            case 6:
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SWITCH_PLAY_MODE, new Object[0]));
                e();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_play_mode");
                break;
            case 10:
                if (obj instanceof Number) {
                    a(((Number) obj).intValue());
                    break;
                }
                break;
            case 11:
                if (!M.isNull()) {
                    if (!M.isThirdParty()) {
                        if (M.isOnline() && !com.sds.android.ttpod.framework.storage.environment.b.av()) {
                            this.c.startActivity(new Intent(this.c, LoginActivity.class));
                            break;
                        }
                        int i2;
                        boolean z = !M.getFav();
                        if (z) {
                            com.sds.android.ttpod.b.g.a(M, true);
                        } else {
                            com.sds.android.ttpod.b.g.b(M, false);
                        }
                        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_ADD_FAVORITE.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue());
                        String str = Downloads.COLUMN_STATUS;
                        if (z) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        sUserEvent.append(str, Integer.valueOf(i2)).append(MediasColumns.SONG_ID, M.getSongID()).post();
                        com.sds.android.ttpod.framework.a.b.b.a("portrait_favorite");
                        break;
                    }
                    f.a("第三方无法歌曲不能收藏！");
                    break;
                }
                break;
            case 12:
                if (!M.isNull() && M.containMV()) {
                    com.sds.android.ttpod.framework.a.b.b.a("portrait_mv");
                    d.j.a("mv_origin", "portrait_mv");
                    VideoPlayManager.a(this.c, M);
                    break;
                }
            case 15:
            case 16:
                if (!com.sds.android.sdk.lib.util.EnvironmentUtils.a.j() || (obj != null && (obj instanceof Number))) {
                    if (i == 16) {
                        this.a = Integer.valueOf((this.a == null ? e.a(BaseApplication.e()).l() : this.a).intValue() + ((Number) obj).intValue());
                        this.a = Integer.valueOf(Math.min(Math.max(0, this.a.intValue()), com.sds.android.ttpod.framework.storage.a.a.a().M().getDuration().intValue()));
                    } else {
                        this.a = Integer.valueOf(((Number) obj).intValue());
                    }
                    this.b.a((long) this.a.intValue(), e.a(BaseApplication.e()).m());
                    break;
                }
                throw new IllegalArgumentException("actionData must be Integer");
            case 19:
                if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_PAUSED) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RESUME, new Object[0]));
                } else if (e.a(BaseApplication.e()).n() == PlayStatus.STATUS_STOPPED) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START, new Object[0]));
                }
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_PLAY.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, M.getSongID()).post();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_play");
                break;
            case 20:
                ((BaseActivity) this.c).acquireFastClickSupport();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PAUSE, new Object[0]));
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_PAUSE.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, M.getSongID()).post();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_pause");
                break;
            case 21:
                ((BaseActivity) this.c).acquireFastClickSupport();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.NEXT, new Object[0]));
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_NEXT.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, M.getSongID()).post();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_next");
                break;
            case 22:
                ((BaseActivity) this.c).acquireFastClickSupport();
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PREVIOUS, new Object[0]));
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_PREV.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, M.getSongID()).post();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_prev");
                break;
            case 23:
                this.c.startActivity(new Intent(this.c, AudioEffectFragmentActivity.class));
                break;
            case 24:
                a(null);
                break;
            case 25:
                if (!com.sds.android.ttpod.framework.storage.a.a.a().M().isOnline()) {
                    f.a(this.c, com.sds.android.ttpod.framework.storage.a.a.a().j(), com.sds.android.ttpod.framework.storage.a.a.a().M(), null, null);
                    break;
                }
                f.a("网络歌曲不能添加到自定义列表");
                break;
            case 26:
                a();
                l.X();
                com.sds.android.ttpod.framework.a.b.b.a("portrait_share");
                break;
            case 27:
                c();
                break;
            case 28:
                if (!M.isNull()) {
                    f.a(this.c, (int) R.string.remove_option, this.c.getString(R.string.media_delete_title), this.c.getString(R.string.media_delete_single, new Object[]{M.getTitle()}), new com.sds.android.ttpod.common.a.a.a<m>(this) {
                        final /* synthetic */ a b;

                        public void a(m mVar) {
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, M.getGroupID(), M, Boolean.valueOf(mVar.b())));
                        }
                    });
                    break;
                }
                break;
            case 29:
                if (!M.isNull()) {
                    f.a(this.c, M);
                    break;
                }
                break;
            case 31:
                this.c.startActivity(new Intent(this.c, ThemeManagementActivity.class));
                x.g("play");
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_SKIN.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue(), s.PAGE_THEME_BACKGROUND.getValue()).post();
                x.t();
                break;
            default:
                return false;
        }
        return true;
    }

    private void e() {
        int[] iArr = new int[]{R.string.repeat_play, R.string.repeat_one_play, R.string.sequence_play, R.string.shuffle_play};
        int ordinal = com.sds.android.ttpod.framework.storage.environment.b.l().ordinal();
        f.a(iArr[ordinal]);
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_PORTRAIT_PLAY_MODE.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue(), s.PAGE_NONE.getValue()).append(Downloads.COLUMN_STATUS, Integer.valueOf(ordinal + 1)).post();
    }

    public void a(int i) {
        d().setStreamVolume(3, i, 0);
    }

    public void a(b bVar) {
        this.b = bVar;
    }

    public void a() {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            f.a(this.c, M);
        }
    }

    public void a(com.sds.android.ttpod.component.b.a.b bVar) {
        final MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull()) {
            if (com.sds.android.sdk.lib.util.e.a(M.getLocalDataSource())) {
                f.a(this.c, M, null, new com.sds.android.ttpod.component.b.a.b(this) {
                    final /* synthetic */ a b;

                    public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                        f.a(this.b.c, M, com.sds.android.ttpod.framework.storage.environment.b.m(), null);
                    }
                }, new com.sds.android.ttpod.component.b.a.b(this) {
                    final /* synthetic */ a b;

                    public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                        f.a(this.b.c, com.sds.android.ttpod.framework.storage.a.a.a().j(), M, null, null);
                    }
                });
            } else if (M.isOnline()) {
                f.a(this.c, M, bVar);
            }
        }
    }

    public void b() {
        if (this.b != null && this.a == null) {
            this.b.a((long) e.a(BaseApplication.e()).l().intValue(), com.sds.android.ttpod.framework.storage.a.a.a().M().isOnline() ? e.a(BaseApplication.e()).m() : 0.0f);
        }
    }

    public void c() {
        MediaItem M = com.sds.android.ttpod.framework.storage.a.a.a().M();
        if (!M.isNull() && com.sds.android.sdk.lib.util.e.b(M.getLocalDataSource())) {
            try {
                c.a(this.c, new File[]{new File(M.getLocalDataSource())});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public AudioManager d() {
        if (this.d == null) {
            this.d = (AudioManager) this.c.getSystemService("audio");
        }
        return this.d;
    }
}
