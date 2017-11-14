package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Checkable;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.d.a.q;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.core.b.a;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;

public class LyricPictureSettingActivity extends SlidingClosableActivity {
    private static final int ID_AUTO_DOWNLOAD_LYRIC_NETWORK = 1;
    private static final int ID_DOWNLOAD_PIC_AMOUNT_2G = 3;
    private static final int ID_DOWNLOAD_PIC_AMOUNT_WIFI = 2;
    private static final int ID_PLAY_ARTIST_PICTURE = 4;
    private static final int ID_SHOW_INNER_PICTURE = 5;
    private static final int MAXPROGRESS = 8;
    private static final int MINPROGRESS = 0;
    private static final int STEP = 1;
    private final HashMap<a, Integer> mAutoDownloadNetworkTypeResIdMap = new HashMap(a.values().length);
    private b mLyricSettingCard;
    private b mOnItemClickListener = new b(this) {
        static final /* synthetic */ boolean a = (!LyricPictureSettingActivity.class.desiredAssertionStatus());
        final /* synthetic */ LyricPictureSettingActivity b;

        {
            this.b = r1;
        }

        public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
            switch (aVar.g()) {
                case 1:
                    this.b.setLyricDownloadNetwork(((Checkable) aVar).isChecked());
                    t.a(r.ACTION_SETTING_AUTO_DOWNLOAD_LYRIC, ((Checkable) aVar).isChecked());
                    com.sds.android.ttpod.framework.a.b.b.a("auto_download_lrc", ((Checkable) aVar).isChecked());
                    return;
                case 2:
                    this.b.setDownloadPicAmount(aVar, i, com.sds.android.ttpod.framework.storage.environment.b.aI(), true);
                    return;
                case 3:
                    this.b.setDownloadPicAmount(aVar, i, com.sds.android.ttpod.framework.storage.environment.b.aJ(), false);
                    return;
                case 4:
                    if (a || (aVar instanceof Checkable)) {
                        com.sds.android.ttpod.framework.storage.environment.b.N(((Checkable) aVar).isChecked());
                        t.a(r.ACTION_SETTING_PIC_AUTO, ((Checkable) aVar).isChecked());
                        com.sds.android.ttpod.framework.a.b.b.a("pic_auto_play", ((Checkable) aVar).isChecked());
                        return;
                    }
                    throw new AssertionError();
                case 5:
                    if (a || (aVar instanceof Checkable)) {
                        com.sds.android.ttpod.framework.storage.environment.b.v(((Checkable) aVar).isChecked());
                        t.a(r.ACTION_SETTING_SHOW_MUSIC_PIC, ((Checkable) aVar).isChecked());
                        com.sds.android.ttpod.framework.a.b.b.a("show_inside_pic", ((Checkable) aVar).isChecked());
                        return;
                    }
                    throw new AssertionError();
                default:
                    return;
            }
        }
    };
    private b mPicSettingCard;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_LYRIC_AND_PICTURE);
        setContentView((int) R.layout.activity_setting_lyric_pic);
        d.a(this);
        this.mAutoDownloadNetworkTypeResIdMap.put(a.ALL, Integer.valueOf(R.string.all_network));
        this.mAutoDownloadNetworkTypeResIdMap.put(a.WIFI, Integer.valueOf(R.string.wifi_only));
        this.mAutoDownloadNetworkTypeResIdMap.put(a.DISABLE, Integer.valueOf(R.string.no_network));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.setting_card_container_lyric_pic);
        this.mLyricSettingCard = buildLyricSettingCard();
        viewGroup.addView(this.mLyricSettingCard.e());
        this.mPicSettingCard = buildPicSettingCard();
        viewGroup.addView(this.mPicSettingCard.e());
    }

    private b buildLyricSettingCard() {
        c[] cVarArr = new c[1];
        cVarArr[0] = new a(1, 0, R.string.setting_auto_download_lyric, 0, 0, isLyricDownloadChecked());
        return new b(this, cVarArr, R.string.setting_lyric, this.mOnItemClickListener);
    }

    private b buildPicSettingCard() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new c(3, 0, (int) R.string.image_down_amount_2g, Integer.toString(com.sds.android.ttpod.framework.storage.environment.b.aJ()), (int) R.string.icon_arrow_right, true));
        arrayList.add(new c(2, 0, (int) R.string.image_down_amount_wifi, Integer.toString(com.sds.android.ttpod.framework.storage.environment.b.aI()), (int) R.string.icon_arrow_right, true));
        if (com.sds.android.ttpod.framework.a.r.a()) {
            arrayList.add(new a(4, 0, R.string.image_artist_play, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.aK()));
        }
        arrayList.add(new a(5, 0, R.string.show_inner_picture, 0, 0, com.sds.android.ttpod.framework.storage.environment.b.F()));
        return new b(this, (c[]) arrayList.toArray(new c[arrayList.size()]), R.string.setting_pic, this.mOnItemClickListener);
    }

    private void setDownloadPicAmount(final com.sds.android.ttpod.component.b.a aVar, final int i, int i2, final boolean z) {
        q qVar = new q(this, 0, i2, 8, 1, "", new com.sds.android.ttpod.common.a.a.a<q>(this) {
            final /* synthetic */ LyricPictureSettingActivity d;

            public void a(q qVar) {
                int b = qVar.b();
                aVar.a(b + "");
                this.d.mPicSettingCard.a((c) aVar, i);
                if (z) {
                    this.d.sAmount(r.ACTION_SETTING_WIFI_NUMBER, b);
                    com.sds.android.ttpod.framework.a.b.b.a("wifi_picture_download", b);
                    com.sds.android.ttpod.framework.storage.environment.b.p(b);
                    return;
                }
                this.d.sAmount(r.ACTION_SETTING_2G_3G_NUMBER, b);
                com.sds.android.ttpod.framework.a.b.b.a("2G_3G_picture_download", b);
                com.sds.android.ttpod.framework.storage.environment.b.q(b);
            }
        });
        qVar.setTitle(aVar.d());
        qVar.show();
    }

    private void sAmount(r rVar, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
        sUserEvent.setPageParameter(true);
        sUserEvent.append(SocialConstants.PARAM_TYPE, Integer.valueOf(i));
        sUserEvent.post();
    }

    private void setLyricDownloadNetwork(boolean z) {
        com.sds.android.ttpod.framework.storage.environment.b.b(z ? a.ALL : a.DISABLE);
    }

    private boolean isLyricDownloadChecked() {
        return com.sds.android.ttpod.framework.storage.environment.b.R() != a.DISABLE;
    }
}
