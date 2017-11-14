package com.sds.android.ttpod.activities.setting;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.framework.a.b.s;
import java.util.HashMap;
import java.util.Map;

public class MvPlayAndDownloadQualityActivity extends SlidingClosableActivity {
    private static final int ID_DOWNLOAD_ASK_ME_EVERY_TIME = 4;
    private static final int ID_DOWNLOAD_MV_HIGH_QUALITY = 6;
    private static final int ID_DOWNLOAD_MV_NORMAL_QUALITY = 5;
    private static final int ID_DOWNLOAD_MV_SUPER_QUALITY = 7;
    private static final int ID_MV_PLAY_HIGH_QUALITY = 2;
    private static final int ID_MV_PLAY_NORMAL_QUALITY = 1;
    private static final int ID_MV_PLAY_SUPER_QUALITY = 3;
    private b mAuditionSettingCard;
    private b mDownloadSettingCard;
    private Map<Integer, Integer> mMVDownloadQualityMap = new HashMap();
    private Map<Integer, Integer> mMVPlayQualityMap = new HashMap();
    private b mOnItemClickListener = new b(this) {
        final /* synthetic */ MvPlayAndDownloadQualityActivity a;

        {
            this.a = r1;
        }

        public void a(a aVar, int i) {
            switch (aVar.g()) {
                case 1:
                case 2:
                case 3:
                    this.a.updateMVPlaySettingItemActionIcon(aVar);
                    return;
                case 4:
                case 5:
                case 6:
                case 7:
                    this.a.updateMVDownloadSettingItemActionIcon(aVar);
                    return;
                default:
                    return;
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initMVPlayQualityMap();
        initMVDownloadQualityMap();
        setStatisticPage(s.PAGE_AUDITION_AND_DOWNLOAD_QUALITY);
        setContentView((int) R.layout.activity_setting_play_download_quality);
        d.a(this);
        this.mAuditionSettingCard = buildMVPlaySettingCard(R.string.setting_quality_mv_play_title);
        this.mDownloadSettingCard = buildMVDownloadSettingCard(R.string.setting_quality_mv_download_title);
        addViewsToGroup();
    }

    private void initMVPlayQualityMap() {
        this.mMVPlayQualityMap.clear();
        this.mMVPlayQualityMap.put(Integer.valueOf(1), Integer.valueOf(0));
        this.mMVPlayQualityMap.put(Integer.valueOf(2), Integer.valueOf(1));
        this.mMVPlayQualityMap.put(Integer.valueOf(3), Integer.valueOf(2));
    }

    private void initMVDownloadQualityMap() {
        this.mMVDownloadQualityMap.clear();
        this.mMVDownloadQualityMap.put(Integer.valueOf(4), Integer.valueOf(-1));
        this.mMVDownloadQualityMap.put(Integer.valueOf(5), Integer.valueOf(0));
        this.mMVDownloadQualityMap.put(Integer.valueOf(6), Integer.valueOf(1));
        this.mMVDownloadQualityMap.put(Integer.valueOf(7), Integer.valueOf(2));
    }

    private void addViewsToGroup() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.setting_card_container_play_download_quality);
        viewGroup.addView(this.mAuditionSettingCard.e());
        viewGroup.addView(this.mDownloadSettingCard.e());
        viewGroup.addView(buildDescriptionView(R.string.setting_quality_dowload_desc));
    }

    private b buildMVPlaySettingCard(int i) {
        c[] cVarArr = new c[3];
        cVarArr[0] = new c(1, 0, (int) R.string.setting_mv_quality_normal, 0, getAuditionActionIcon(((Integer) this.mMVPlayQualityMap.get(Integer.valueOf(1))).intValue()));
        cVarArr[1] = new c(2, 0, (int) R.string.setting_mv_quality_high, 0, getAuditionActionIcon(((Integer) this.mMVPlayQualityMap.get(Integer.valueOf(2))).intValue()));
        cVarArr[2] = new c(3, 0, (int) R.string.setting_mv_quality_super, 0, getAuditionActionIcon(((Integer) this.mMVPlayQualityMap.get(Integer.valueOf(3))).intValue()));
        return new b(this, cVarArr, i, this.mOnItemClickListener);
    }

    private b buildMVDownloadSettingCard(int i) {
        r6 = new c[4];
        r6[0] = new c(4, 0, (int) R.string.setting_quality_ask_me_every_time, 0, getDownloadActionIcon(((Integer) this.mMVDownloadQualityMap.get(Integer.valueOf(4))).intValue()));
        r6[1] = new c(5, 0, (int) R.string.setting_mv_quality_normal, 0, getDownloadActionIcon(((Integer) this.mMVDownloadQualityMap.get(Integer.valueOf(5))).intValue()));
        r6[2] = new c(6, 0, (int) R.string.setting_mv_quality_high, 0, getDownloadActionIcon(((Integer) this.mMVDownloadQualityMap.get(Integer.valueOf(6))).intValue()));
        r6[3] = new c(7, 0, (int) R.string.setting_mv_quality_super, 0, getDownloadActionIcon(((Integer) this.mMVDownloadQualityMap.get(Integer.valueOf(7))).intValue()));
        return new b(this, r6, i, this.mOnItemClickListener);
    }

    private View buildDescriptionView(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.setting_container_desc, null, false);
        TextView textView = (TextView) inflate.findViewById(R.id.id_desc_sub_title);
        textView.setText(i);
        textView.setVisibility(0);
        return inflate;
    }

    private int getAuditionActionIcon(int i) {
        if (i == com.sds.android.ttpod.framework.storage.environment.b.N()) {
            return R.drawable.img_setting_single_choice_checked;
        }
        return 0;
    }

    private int getDownloadActionIcon(int i) {
        if (i == com.sds.android.ttpod.framework.storage.environment.b.O()) {
            return R.drawable.img_setting_single_choice_checked;
        }
        return 0;
    }

    private void updateMVPlaySettingItemActionIcon(a aVar) {
        cleanSettingItemActionIcon(this.mAuditionSettingCard);
        ((c) aVar).a(getResources().getDrawable(R.drawable.img_setting_single_choice_checked));
        this.mAuditionSettingCard.c();
        com.sds.android.ttpod.framework.storage.environment.b.b(((Integer) this.mMVPlayQualityMap.get(Integer.valueOf(aVar.g()))).intValue());
        com.sds.android.ttpod.framework.a.b.b.a("mv_play_quality", aVar.g());
    }

    private void updateMVDownloadSettingItemActionIcon(a aVar) {
        cleanSettingItemActionIcon(this.mDownloadSettingCard);
        ((c) aVar).a(getResources().getDrawable(R.drawable.img_setting_single_choice_checked));
        this.mDownloadSettingCard.c();
        com.sds.android.ttpod.framework.storage.environment.b.c(((Integer) this.mMVDownloadQualityMap.get(Integer.valueOf(aVar.g()))).intValue());
        com.sds.android.ttpod.framework.a.b.b.a("mv_download_quality", aVar.g());
    }

    private void cleanSettingItemActionIcon(b bVar) {
        for (c a : bVar.a()) {
            a.a(new ColorDrawable(0));
        }
    }
}
