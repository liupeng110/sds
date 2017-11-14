package com.sds.android.ttpod.fragment.main.findsong;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FindSongHotListData;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.i;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.b;

public class FindSongListViewFragment extends FindSongBaseViewFragment implements b {
    private a mFindSongListAdapter;
    private LayoutInflater mLayoutInflater;
    private ListView mListView;
    private ViewGroup mRootView;

    private class a extends BaseAdapter {
        final /* synthetic */ FindSongListViewFragment a;

        private a(FindSongListViewFragment findSongListViewFragment) {
            this.a = findSongListViewFragment;
        }

        public int getCount() {
            return this.a.getDataListSize();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = this.a.getLayoutInflater().inflate(R.layout.find_song_normal_list_view_item, viewGroup, false);
            a(inflate, i);
            c(inflate, i);
            b(inflate, i);
            a(i, inflate);
            inflate.setOnClickListener(this.a.createItemOnClickListener(i));
            a(inflate);
            return inflate;
        }

        public Object getItem(int i) {
            return FindSongListViewFragment.NULL_RECOMMEND_DATA;
        }

        private void a(int i, View view) {
            if (this.a.isNeedQuickPlay(i)) {
                IconTextView iconTextView = (IconTextView) view.findViewById(R.id.icon_quick_play);
                view.findViewById(R.id.itv_arrow).setVisibility(4);
                iconTextView.setOnClickListener(this.a.createQuickPlayListener(i));
                iconTextView.setVisibility(0);
            }
        }

        private void a(View view, int i) {
            ((TextView) view.findViewById(R.id.id_list_view_item_name)).setText(this.a.getItemData(i).getName());
        }

        private void b(View view, int i) {
            CharSequence desc = this.a.getItemData(i).getDesc();
            if (this.a.getItemData(i) instanceof FindSongHotListData) {
                FindSongHotListData findSongHotListData = (FindSongHotListData) this.a.getItemData(i);
                if (!m.a(findSongHotListData.getRecommendReason())) {
                    desc = findSongHotListData.getRecommendReason();
                }
            }
            ((TextView) view.findViewById(R.id.id_list_view_item_desc)).setText(desc);
        }

        private void c(View view, int i) {
            int a = com.sds.android.ttpod.common.c.a.a(75);
            this.a.requestImage((ImageView) view.findViewById(R.id.iv_singer), this.a.getItemData(i).getPicUrl(), a, a, R.drawable.img_music_default_icon);
        }

        public long getItemId(int i) {
            return FindSongListViewFragment.NULL_RECOMMEND_DATA.getId();
        }

        private void a(View view) {
            c.a(view.findViewById(R.id.id_list_view_item_desc), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            c.a(view.findViewById(R.id.id_list_view_item_name), ThemeElement.SONG_LIST_ITEM_TEXT);
            c.a(view.findViewById(R.id.id_click_view), ThemeElement.TILE_BACKGROUND);
            c.a(view, ThemeElement.TILE_BACKGROUND);
            v.a((IconTextView) view.findViewById(R.id.itv_arrow), ThemeElement.TILE_SUB_TEXT);
            v.a((IconTextView) view.findViewById(R.id.icon_quick_play), ThemeElement.TILE_SUB_TEXT);
            a();
        }

        private void a() {
            if (j.c()) {
                int a = com.sds.android.ttpod.common.c.a.a(85);
                Drawable a2 = c.a(ThemeElement.COMMON_SEPARATOR);
                this.a.mListView.setDivider(a2 == null ? null : new InsetDrawable(a2, a, 0, 0, 0));
                this.a.mListView.setDividerHeight(1);
                this.a.mListView.setHeaderDividersEnabled(true);
                this.a.mListView.setFooterDividersEnabled(true);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLayoutInflater = layoutInflater;
        if (this.mRootView == null) {
            int a = com.sds.android.ttpod.common.c.a.a(8);
            this.mRootView = (ViewGroup) this.mLayoutInflater.inflate(R.layout.find_song_title_bar, viewGroup, false);
            this.mRootView.removeAllViews();
            this.mRootView.setPadding(a, 0, a, a);
            this.mRootView.addView(createTitleBarView(layoutInflater));
            this.mRootView.addView(createListView());
        }
        return this.mRootView;
    }

    private LayoutInflater getLayoutInflater() {
        if (this.mLayoutInflater == null) {
            this.mLayoutInflater = LayoutInflater.from(getActivity());
        }
        return this.mLayoutInflater;
    }

    private ListView createListView() {
        this.mListView = new ListView(getActivity());
        this.mListView.addHeaderView(new View(getActivity()));
        this.mListView.setLayoutParams(new LayoutParams(-1, i.b.a(getDataListSize())));
        this.mListView.setDivider(new ColorDrawable(0));
        this.mListView.setDividerHeight(com.sds.android.ttpod.common.c.a.a(1));
        this.mListView.setHeaderDividersEnabled(false);
        this.mListView.setFooterDividersEnabled(false);
        this.mListView.setFadingEdgeLength(0);
        this.mFindSongListAdapter = new a();
        this.mListView.setAdapter(this.mFindSongListAdapter);
        return this.mListView;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (isViewAccessAble() && this.mThemeReload) {
            setTitleBarTheme();
            this.mFindSongListAdapter.notifyDataSetChanged();
            this.mThemeReload = false;
        }
    }
}
