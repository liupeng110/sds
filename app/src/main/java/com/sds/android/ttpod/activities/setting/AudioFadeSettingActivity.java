package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.a.q;
import com.sds.android.ttpod.framework.a.b.w;

public class AudioFadeSettingActivity extends SlidingClosableActivity {
    private static final int ID_FADE_PLAY_PAUSE = 2;
    private static final int ID_FADE_SEEK = 3;
    private static final int ID_FADE_WHILE_CHANGING_MEDIA = 0;
    private static final int MAXPROGRESS = 5000;
    private static final int MINPROGRESS = 0;
    private static final float ONE_MILLIONSECOND = 1000.0f;
    private static final int STEP = 100;
    private b mFadeSettingCard;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ AudioFadeSettingActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            switch (aVar.g()) {
                case 0:
                    this.a.setFadeLength(aVar, i, 0);
                    return;
                case 2:
                    this.a.setFadeLength(aVar, i, 2);
                    return;
                case 3:
                    this.a.setFadeLength(aVar, i, 3);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_setting_audio_fade);
        d.a(this);
        this.mFadeSettingCard = new b(this, new c[]{new c(0, 0, (int) R.string.setting_audio_fade_while_changing_media, (((float) com.sds.android.ttpod.framework.storage.environment.b.be()) / ONE_MILLIONSECOND) + getString(R.string.setting_audio_fade_length_unit_second), (int) R.drawable.img_setting_right_arrow), new c(2, 0, (int) R.string.setting_audio_fade_play_pause, (((float) com.sds.android.ttpod.framework.storage.environment.b.bf()) / ONE_MILLIONSECOND) + getString(R.string.setting_audio_fade_length_unit_second), (int) R.drawable.img_setting_right_arrow), new c(3, 0, (int) R.string.setting_audio_fade_seek, (((float) com.sds.android.ttpod.framework.storage.environment.b.bh()) / ONE_MILLIONSECOND) + getString(R.string.setting_audio_fade_length_unit_second), (int) R.drawable.img_setting_right_arrow)}, R.string.setting_audio_fade_in_out_time, this.mOnItemClickListener);
        ((ViewGroup) findViewById(R.id.setting_card_container_audio_fade)).addView(this.mFadeSettingCard.e());
    }

    private void setFadeLength(final a aVar, final int i, final int i2) {
        int be;
        switch (i2) {
            case 0:
                be = com.sds.android.ttpod.framework.storage.environment.b.be();
                break;
            case 2:
                be = com.sds.android.ttpod.framework.storage.environment.b.bf();
                break;
            case 3:
                be = com.sds.android.ttpod.framework.storage.environment.b.bh();
                break;
            default:
                be = 0;
                break;
        }
        q qVar = new q(this, 0, be, MAXPROGRESS, 100, getString(R.string.setting_audio_fade_length_unit_second), new com.sds.android.ttpod.common.a.a.a<q>(this) {
            final /* synthetic */ AudioFadeSettingActivity d;

            public void a(q qVar) {
                int b = qVar.b();
                aVar.a((((float) b) / AudioFadeSettingActivity.ONE_MILLIONSECOND) + this.d.getString(R.string.setting_audio_fade_length_unit_second));
                this.d.mFadeSettingCard.a((c) aVar, i);
                switch (i2) {
                    case 0:
                        com.sds.android.ttpod.framework.storage.environment.b.s(b);
                        w.a(NewUser.LOCAL_LOGIN, "click", "fade-over-cut", (long) b);
                        return;
                    case 2:
                        com.sds.android.ttpod.framework.storage.environment.b.t(b);
                        w.a(NewUser.LOCAL_LOGIN, "click", "fade-over-play", (long) b);
                        return;
                    case 3:
                        com.sds.android.ttpod.framework.storage.environment.b.u(b);
                        w.a(NewUser.LOCAL_LOGIN, "click", "fade-over-adjust", (long) b);
                        return;
                    default:
                        return;
                }
            }
        });
        qVar.setTitle(aVar.d());
        qVar.a(1000, 1);
        qVar.show();
    }
}
