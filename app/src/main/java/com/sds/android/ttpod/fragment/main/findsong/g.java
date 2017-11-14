package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult.SubCategoryData;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.RoundedImageView;

/* SceneRecommendHeader */
public class g {
    private Context a;
    private View b;
    private RoundedImageView c;
    private ImageView d;
    private TextView e;
    private IconTextView f;
    private IconTextView g;
    private IconTextView h;
    private TextView i;
    private TextView j;
    private View k;
    private View l;

    public g(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null, SongCategoryDetailHeader create fail");
        }
        this.a = context;
        this.b = layoutInflater.inflate(R.layout.song_category_detail_header, viewGroup, false);
        this.c = (RoundedImageView) this.b.findViewById(R.id.imageview_header);
        this.d = (ImageView) this.b.findViewById(R.id.imageview_header_play);
        this.e = (TextView) this.b.findViewById(R.id.textview_header_detail);
        this.i = (TextView) this.b.findViewById(R.id.text_next_page);
        this.j = (TextView) this.b.findViewById(R.id.text_download_all);
        this.f = (IconTextView) this.b.findViewById(R.id.imageview_next_page);
        this.g = (IconTextView) this.b.findViewById(R.id.imageview_download_all);
        this.h = (IconTextView) this.b.findViewById(R.id.icontext_arrow_right);
        this.k = this.b.findViewById(R.id.layout_detail_content);
        this.l = this.b.findViewById(R.id.layout_song_category_operation);
        d();
    }

    public View a() {
        return this.b;
    }

    public void a(boolean z) {
        if (this.b != null) {
            this.b.findViewById(R.id.layout_next_page).setVisibility(z ? 0 : 8);
        }
    }

    public void b() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.a, R.anim.rotate);
        loadAnimation.setFillEnabled(true);
        loadAnimation.setFillAfter(true);
        this.f.startAnimation(loadAnimation);
    }

    public void a(SubCategoryData subCategoryData) {
        if (subCategoryData != null) {
            a(subCategoryData.getDetail(), subCategoryData.getLargePicUrl());
        }
    }

    public void a(String str, String str2) {
        CharSequence string;
        if (m.a(str)) {
            string = this.a.getString(R.string.post_detail_header_tweet_default);
        }
        a(string);
        int dimension = (int) this.a.getResources().getDimension(R.dimen.song_category_detail_image_size);
        com.sds.android.ttpod.framework.a.g.a(this.c, str2, dimension, dimension, (int) R.drawable.img_background_song_publish);
    }

    public void c() {
        this.f.clearAnimation();
    }

    public void a(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
        this.i.setOnClickListener(onClickListener);
        this.j.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
    }

    public void a(CharSequence charSequence) {
        this.e.setText(charSequence);
    }

    public void d() {
        c.a(this.k, ThemeElement.TILE_MASK);
        c.a(this.l, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        c.a(this.e, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(this.b.findViewById(R.id.scene_recommend_divider), ThemeElement.COMMON_SEPARATOR);
        String str = ThemeElement.SONG_LIST_ITEM_TEXT;
        String str2 = ThemeElement.SONG_LIST_ITEM_SUB_TEXT;
        v.a(this.f, str, (int) R.string.icon_next_page, str);
        v.a(this.g, str, (int) R.string.icon_post_header_download, str);
        v.a(this.h, str2, (int) R.string.icon_arrow_right, str2);
        c.a(this.i, str2);
        c.a(this.j, str2);
    }
}
