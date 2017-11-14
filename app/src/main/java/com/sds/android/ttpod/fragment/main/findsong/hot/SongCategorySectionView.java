package com.sds.android.ttpod.fragment.main.findsong.hot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult.CategoryData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.b;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SimpleSongView;

public class SongCategorySectionView extends SimpleSongView {
    public SongCategorySectionView(Context context) {
        this(context, null);
    }

    public SongCategorySectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SongCategorySectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void a(Context context) {
        super.a(context);
        int a = a.a(8);
        this.b.setPadding(0, 0, 0, 0);
        LayoutParams layoutParams = this.b.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            ((MarginLayoutParams) layoutParams).setMargins(0, a, 0, 0);
        }
        this.b.setNumColumns(3);
        this.b.setChildMargin(a);
    }

    public void onThemeLoaded() {
        c.a(this.a, ThemeElement.TILE_TEXT);
        c.a(this.d, ThemeElement.TILE_TEXT);
        c.a(this.e, ThemeElement.TILE_MASK);
    }

    protected void a(View view, Object obj) {
        view.setOnClickListener(this.i);
    }

    protected int a() {
        return R.string.music_library;
    }

    protected void b(View view, Object obj) {
        if (obj instanceof b.a) {
            b.a aVar = (b.a) obj;
            view.setTag(R.id.view_bind_data, aVar);
            com.sds.android.ttpod.adapter.c.a aVar2 = (com.sds.android.ttpod.adapter.c.a) view.getTag();
            aVar2.b().setText(aVar.c());
            aVar2.c().setText(a(aVar));
            if (aVar.c().equals(getContext().getString(R.string.category_mv))) {
                aVar2.c().setText(R.string.music_library_mv_sub_title);
            }
            if (aVar.c().equals(getContext().getString(R.string.category_fm))) {
                aVar2.c().setText(R.string.music_library_tt_fm_sub_title);
            }
            if (!m.a(aVar2.d(), (int) R.drawable.img_music_default_icon)) {
                LayoutParams layoutParams = aVar2.g().getLayoutParams();
                layoutParams.height = (a.d() - 32) / 3;
                aVar2.g().setLayoutParams(layoutParams);
                c.a(aVar2.g(), ThemeElement.TILE_BACKGROUND);
                c.a(aVar2.b(), ThemeElement.TILE_TEXT);
                c.a(aVar2.c(), ThemeElement.TILE_SUB_TEXT);
            }
        }
    }

    protected View b() {
        View inflate = View.inflate(getContext(), R.layout.find_song_with_num_grid_list_view_item, null);
        inflate.setTag(new com.sds.android.ttpod.adapter.c.a(inflate));
        return inflate;
    }

    protected String a(b.a<CategoryData> aVar) {
        return getContext().getString(R.string.count_of_channel, new Object[]{Integer.valueOf(aVar.a())});
    }
}
