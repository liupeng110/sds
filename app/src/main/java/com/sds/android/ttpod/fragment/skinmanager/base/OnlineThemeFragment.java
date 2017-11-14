package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.skinmanager.SkinCategoryDetailFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeRankFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeRecommendFragment;
import com.sds.android.ttpod.fragment.skinmanager.b;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.skin.m;

public abstract class OnlineThemeFragment extends BaseThemeFragment {

    protected class a extends com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment.a {
        final /* synthetic */ OnlineThemeFragment a;

        protected a(OnlineThemeFragment onlineThemeFragment) {
            this.a = onlineThemeFragment;
            super(onlineThemeFragment);
        }

        protected void a(m mVar, ImageView imageView) {
            String pictureUrl = mVar.f().getPictureUrl();
            Bitmap bitmap = null;
            if (!this.a.checkUpdateForSkin(mVar)) {
                bitmap = g.a(mVar.b(), this.b, this.c);
            }
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                g.a(imageView, pictureUrl, this.b, this.c, (int) R.drawable.img_skin_default_thumbnail);
            }
        }

        protected void a(m mVar, b bVar) {
            d(mVar);
            super.a(mVar, bVar);
        }

        private void d(m mVar) {
            switch (mVar.a()) {
                case 0:
                    if (!e.b(mVar.b())) {
                        mVar.a(4);
                        return;
                    }
                    return;
                case 4:
                    if (e.b(mVar.b())) {
                        mVar.a(0);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    protected void initThemeAdapter() {
        this.mThemeAdapter = new a(this);
    }

    protected void onThemeItemSelected(m mVar) {
        if (mVar.a() == 0) {
            checkSkinItem(mVar);
        } else if (4 == mVar.a() && !sDownloadingSkinMap.containsKey(getSkinInfoMapKey(mVar))) {
            doStatistic(mVar.g());
            sLastDownloadThemeName = mVar.g();
            tryDownloadSkin(mVar, false);
        }
    }

    private void doStatistic(String str) {
        SUserEvent sUserEvent;
        if (this instanceof ThemeRecommendFragment) {
            x.a(str);
            sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_RECOMMEND_DOWNLOAD.getValue(), s.PAGE_THEME_BACKGROUND.getValue(), s.PAGE_NONE.getValue());
            sUserEvent.setPageParameter(true);
            sUserEvent.append("skin_name", str);
            sUserEvent.post();
        } else if (this instanceof ThemeRankFragment) {
            x.b(str);
            sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_RANK_DOWNLOAD.getValue(), s.PAGE_THEME_RANK.getValue(), s.PAGE_NONE.getValue());
            sUserEvent.append("skin_name", str);
            sUserEvent.post();
        } else if (this instanceof SkinCategoryDetailFragment) {
            x.c(str);
            sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_CATEGORY_DOWNLOAD.getValue(), s.PAGE_THEME_CATEGORY_DETAIL.getValue());
            sUserEvent.setPageParameter(true);
            sUserEvent.append("skin_name", str);
            sUserEvent.post();
        }
    }

    protected String getStatisticOrigin() {
        return "recommend";
    }

    protected void updateSkinInfoForThemeName(String str, int i) {
        m skinItemForThemeName = getSkinItemForThemeName(str);
        if (skinItemForThemeName != null) {
            skinItemForThemeName.a(i);
        }
    }
}
