package com.sds.android.ttpod.activities.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.tencent.open.SocialConstants;

public class SongAuditionAndDownloadQualityActivity extends SlidingClosableActivity {
    private static final int ID_AUDITION_COMPRESS_QUALITY = 1;
    private static final int ID_AUDITION_HIGH_QUALITY = 3;
    private static final int ID_AUDITION_NORMAL_QUALITY = 2;
    private static final int ID_DOWNLOAD_ASK_ME_EVERY_TIME = 4;
    private static final int ID_DOWNLOAD_COMPRESS_QUALITY = 5;
    private static final int ID_DOWNLOAD_HIGH_QUALITY = 7;
    private static final int ID_DOWNLOAD_LOSSLESS_QUALITY = 8;
    private static final int ID_DOWNLOAD_NORMAL_QUALITY = 6;
    private b mAuditionSettingCard;
    private b mDownloadSettingCard;
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ SongAuditionAndDownloadQualityActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            switch (aVar.g()) {
                case 1:
                case 2:
                case 3:
                    this.a.updateAuditionSettingItemActionIcon(aVar);
                    return;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    this.a.updateDownloadSettingItemActionIcon(aVar);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_AUDITION_AND_DOWNLOAD_QUALITY);
        setContentView((int) R.layout.activity_setting_audition_download_quality);
        d.a(this);
        this.mAuditionSettingCard = buildAuditionSettingCard(R.string.setting_quality_audition_title);
        this.mDownloadSettingCard = buildDownloadSettingCard(R.string.setting_quality_download_title);
        addViewsToGroup();
    }

    private void addViewsToGroup() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.setting_card_container_audition_down_quality);
        viewGroup.addView(this.mAuditionSettingCard.e());
        viewGroup.addView(buildDescriptionView(R.string.setting_quality_audition_desc));
        viewGroup.addView(this.mDownloadSettingCard.e());
        viewGroup.addView(buildDescriptionView(R.string.setting_quality_dowload_desc));
    }

    private b buildAuditionSettingCard(int i) {
        c[] cVarArr = new c[3];
        cVarArr[0] = new c(1, 0, (int) R.string.setting_quality_compress, 0, getAuditionActionIcon(1));
        cVarArr[1] = new c(2, 0, (int) R.string.setting_quality_normal, 0, getAuditionActionIcon(2));
        cVarArr[2] = new c(3, 0, (int) R.string.setting_quality_high, 0, getAuditionActionIcon(3));
        return new b(this, cVarArr, i, this.mOnItemClickListener);
    }

    private b buildDownloadSettingCard(int i) {
        c[] cVarArr = new c[5];
        cVarArr[0] = new c(4, 0, (int) R.string.setting_quality_ask_me_every_time, 0, getDownloadActionIcon(4));
        cVarArr[1] = new c(5, 0, (int) R.string.setting_quality_compress, 0, getDownloadActionIcon(5));
        cVarArr[2] = new c(6, 0, (int) R.string.setting_quality_normal, 0, getDownloadActionIcon(6));
        cVarArr[3] = new c(7, 0, (int) R.string.setting_quality_high, 0, getDownloadActionIcon(7));
        cVarArr[4] = new c(8, 0, (int) R.string.lossless_title, 0, getDownloadActionIcon(8));
        return new b(this, cVarArr, i, this.mOnItemClickListener);
    }

    private View buildDescriptionView(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.id_desc_sub_title);
        textView.setText(i);
        textView.setVisibility(0);
        return inflate;
    }

    private AudioQuality getQualityById(int i) {
        if (i == 8) {
            return AudioQuality.LOSSLESS;
        }
        if (i == 5 || i == 1) {
            return AudioQuality.COMPRESSED;
        }
        if (i == 6 || i == 2) {
            return AudioQuality.STANDARD;
        }
        if (i == 7 || i == 3) {
            return AudioQuality.SUPER;
        }
        return AudioQuality.UNDEFINED;
    }

    private int getAuditionActionIcon(int i) {
        if (getQualityById(i).ordinal() == com.sds.android.ttpod.framework.storage.environment.b.M().ordinal()) {
            return R.drawable.img_setting_single_choice_checked;
        }
        return 0;
    }

    private int getDownloadActionIcon(int i) {
        if (getQualityById(i).ordinal() == com.sds.android.ttpod.framework.storage.environment.b.P().ordinal()) {
            return R.drawable.img_setting_single_choice_checked;
        }
        return 0;
    }

    private void updateAuditionSettingItemActionIcon(a aVar) {
        cleanSettingItemActionIcon(this.mAuditionSettingCard);
        ((c) aVar).a(getResources().getDrawable(R.drawable.img_setting_single_choice_checked));
        this.mAuditionSettingCard.c();
        com.sds.android.ttpod.framework.storage.environment.b.c(getQualityById(aVar.g()));
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SETTING_PLAY_QUILITY_SELECT.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        sUserEvent.append(SocialConstants.PARAM_TYPE, Integer.valueOf(aVar.g()));
        sUserEvent.post();
        com.sds.android.ttpod.framework.a.b.b.a("listen_quality", aVar.g());
    }

    private void updateDownloadSettingItemActionIcon(a aVar) {
        cleanSettingItemActionIcon(this.mDownloadSettingCard);
        ((c) aVar).a(getResources().getDrawable(R.drawable.img_setting_single_choice_checked));
        this.mDownloadSettingCard.c();
        com.sds.android.ttpod.framework.storage.environment.b.d(getQualityById(aVar.g()));
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SETTING_DOWNLOAD_QUILITY_SELECT.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        sUserEvent.append(SocialConstants.PARAM_TYPE, Integer.valueOf(aVar.g()));
        sUserEvent.post();
        com.sds.android.ttpod.framework.a.b.b.a("download_quality", aVar.g());
    }

    private void cleanSettingItemActionIcon(b bVar) {
        for (c a : bVar.a()) {
            a.a(new ColorDrawable(0));
        }
    }
}
