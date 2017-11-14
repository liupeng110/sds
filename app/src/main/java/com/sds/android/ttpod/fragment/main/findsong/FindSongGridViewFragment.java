package com.sds.android.ttpod.fragment.main.findsong;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FindSongHotListData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class FindSongGridViewFragment extends FindSongBaseViewFragment {
    private int mDescTextLines = 1;
    protected a mFindSongGridAdapter;
    private ViewGroup mFindSongGridViewContainer;
    private LayoutInflater mLayoutInflater;

    private class a extends BaseAdapter {
        final /* synthetic */ FindSongGridViewFragment a;
        private int b;
        private final int c;

        public a(FindSongGridViewFragment findSongGridViewFragment, int i) {
            this.a = findSongGridViewFragment;
            this.c = i;
        }

        public void notifyDataSetChanged() {
            this.b = -1;
            super.notifyDataSetChanged();
        }

        public Object getItem(int i) {
            return FindSongGridViewFragment.NULL_RECOMMEND_DATA;
        }

        public long getItemId(int i) {
            return FindSongGridViewFragment.NULL_RECOMMEND_DATA.getId();
        }

        public int getCount() {
            return this.a.getDataListSize();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = this.a.getLayoutInflater().inflate(R.layout.fing_song_grid_view_normal_item, null);
            c(i, inflate);
            b(i, inflate);
            d(i, inflate);
            f(i, inflate);
            e(i, inflate);
            a(i, inflate);
            c.a(inflate.findViewById(R.id.id_grid_view_item_name), ThemeElement.SONG_LIST_ITEM_TEXT);
            return inflate;
        }

        private void a(int i, View view) {
            if (this.a.isNeedQuickPlay(i)) {
                View findViewById = view.findViewById(R.id.icon_grid_quick_play);
                findViewById.setOnClickListener(this.a.createQuickPlayListener(i));
                findViewById.setVisibility(0);
            }
        }

        private void b(int i, View view) {
            if (this.a.isSongListItemInSongListModule(i)) {
                TextView textView = (TextView) view.findViewById(R.id.id_grid_view_item_author_name);
                textView.setText(((FindSongHotListData) this.a.getItemData(i)).getAuthor());
                float a = ((float) com.sds.android.ttpod.common.c.a.a(11)) - (((float) this.c) * 1.0f);
                textView.setTextSize(0, a);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) a, (int) a);
                layoutParams.addRule(15);
                layoutParams.rightMargin = com.sds.android.ttpod.common.c.a.a(3);
                layoutParams.leftMargin = com.sds.android.ttpod.common.c.a.a(5);
                view.findViewById(R.id.id_author_icon).setLayoutParams(layoutParams);
                return;
            }
            view.findViewById(R.id.id_author_layout).setVisibility(8);
        }

        private void c(int i, View view) {
            if (this.a.isSongListItemInSongListModule(i)) {
                TextView textView = (TextView) view.findViewById(R.id.id_grid_view_item_listen_count);
                textView.setText(String.valueOf(((FindSongHotListData) this.a.getItemData(i)).getListenCount()));
                float a = ((float) com.sds.android.ttpod.common.c.a.a(10)) - (((float) this.c) * 1.0f);
                textView.setTextSize(0, a);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) a) + 1, ((int) a) + 1);
                layoutParams.addRule(15);
                layoutParams.rightMargin = com.sds.android.ttpod.common.c.a.a(3);
                view.findViewById(R.id.id_listen_count_icon).setLayoutParams(layoutParams);
                return;
            }
            view.findViewById(R.id.id_listen_count_layout).setVisibility(8);
        }

        private void d(int i, View view) {
            TextView textView = (TextView) view.findViewById(R.id.id_grid_view_item_name);
            if (this.a.isInSongListModule()) {
                int i2;
                textView.setVisibility(0);
                if (this.a.getItemData(i).getName() != null) {
                    textView.setText(this.a.getItemData(i).getName());
                } else {
                    textView.setText("");
                }
                textView.setTextSize(0, com.sds.android.ttpod.b.i.a.d(this.c));
                if (this.a.getItemData(i).getName() == null) {
                    i2 = 1;
                } else if (this.a.getItemData(i).getName().length() > com.sds.android.ttpod.b.i.a.a(this.c)) {
                    i2 = 2;
                } else {
                    i2 = 1;
                }
                if ((i + 1) % this.c == 0) {
                    this.a.setDescTextLines(Math.max(this.a.getDescTextLines(), i2));
                    i2 = this.a.getDescTextLines();
                    this.a.setDescTextLines(1);
                } else {
                    this.a.setDescTextLines(Math.max(this.a.getDescTextLines(), i2));
                }
                textView.getLayoutParams().height = i2 == 2 ? com.sds.android.ttpod.common.c.a.a(48) : com.sds.android.ttpod.common.c.a.a(30);
                textView.setMaxLines(i2);
                return;
            }
            textView.setVisibility(8);
        }

        private void e(int i, View view) {
            view.findViewById(R.id.id_click_view).setOnClickListener(this.a.createItemOnClickListener(i));
        }

        private void f(int i, View view) {
            int i2;
            ImageView imageView = (ImageView) view.findViewById(R.id.id_grid_view_no_fold_image);
            int b = com.sds.android.ttpod.b.i.a.b(this.c);
            LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = this.c == 1 ? b / 2 : b;
            layoutParams.width = b;
            imageView.setLayoutParams(layoutParams);
            if (this.c == 1) {
                i2 = b / 2;
            } else {
                i2 = b;
            }
            this.a.requestImage(imageView, this.a.getItemData(i).getPicUrl(), b, i2, R.drawable.img_music_default_icon);
        }
    }

    private int getDescTextLines() {
        return this.mDescTextLines;
    }

    private void setDescTextLines(int i) {
        this.mDescTextLines = i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mLayoutInflater = layoutInflater;
        if (this.mFindSongGridViewContainer == null) {
            this.mFindSongGridViewContainer = (ViewGroup) layoutInflater.inflate(R.layout.layout_null_linear_layout, null);
            addViewToContainer(createTitleBarView(layoutInflater), createGridView());
        }
        return this.mFindSongGridViewContainer;
    }

    private LayoutInflater getLayoutInflater() {
        if (this.mLayoutInflater == null) {
            this.mLayoutInflater = LayoutInflater.from(getActivity());
        }
        return this.mLayoutInflater;
    }

    private void addViewToContainer(View view, View view2) {
        int a = com.sds.android.ttpod.common.c.a.a(8);
        if (getModuleDataType().equals("song_list")) {
            this.mFindSongGridViewContainer.addView(view);
            this.mFindSongGridViewContainer.setPadding(a, 0, a, 0);
        } else {
            this.mFindSongGridViewContainer.setPadding(a, a, a, 0);
        }
        this.mFindSongGridViewContainer.addView(view2);
    }

    private GridView createGridView() {
        GridView gridView = new GridView(getActivity());
        int c = com.sds.android.ttpod.b.i.a.c(getModuleData().getStyle());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, com.sds.android.ttpod.b.i.a.a(getDataListSize(), c, getModuleDataType(), getDataList()));
        gridView.setNumColumns(c);
        gridView.setSelector(new ColorDrawable(0));
        gridView.setVerticalSpacing(com.sds.android.ttpod.common.c.a.a(0));
        gridView.setHorizontalSpacing(com.sds.android.ttpod.common.c.a.a(8));
        gridView.setStretchMode(2);
        gridView.setLayoutParams(layoutParams);
        gridView.setGravity(17);
        this.mFindSongGridAdapter = new a(this, c);
        gridView.setAdapter(this.mFindSongGridAdapter);
        return gridView;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (isViewAccessAble() && this.mThemeReload) {
            setTitleBarTheme();
            this.mFindSongGridAdapter.notifyDataSetChanged();
            this.mThemeReload = false;
        }
    }
}
