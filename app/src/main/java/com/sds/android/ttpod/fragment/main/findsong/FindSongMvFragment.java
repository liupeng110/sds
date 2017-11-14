package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.a.f;

public class FindSongMvFragment extends FindSongBaseViewFragment {
    private static final String CHAR_TAG_SPLIT = ",";
    private static final int MY_POSITION = 0;
    private static final String TAG = "FindSongMvFragment";
    private static final int[] TAGS_VIEW_IDS = new int[]{R.id.find_song_mv_item_tag1, R.id.find_song_mv_item_tag2, R.id.find_song_mv_item_tag3};
    private TextView mCount;
    private TextView mDay;
    private TextView mDesc;
    private TextView mMonth;
    private View mMvItem;
    private ViewGroup mRootView;

    private static class a {
        private static final String[] a = new String[]{"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
        private String b = "";
        private String c = "";

        public a(String str) {
            Time time = new Time("GMT+8");
            this.b = String.valueOf(time.month);
            this.c = String.valueOf(time.monthDay);
            String[] split = str.split("\\.");
            switch (split.length) {
                case 1:
                    this.b = split[0];
                    return;
                case 2:
                    int intValue = Integer.valueOf(split[0]).intValue();
                    if (intValue >= 1 && intValue <= 12) {
                        this.b = a[intValue];
                    }
                    this.c = split[1];
                    return;
                default:
                    g.a(FindSongMvFragment.TAG, "DataStringConvert ");
                    return;
            }
        }

        public String a() {
            return this.b;
        }

        public String b() {
            return this.c;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            int a = com.sds.android.ttpod.common.c.a.a(8);
            this.mRootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_null_linear_layout, viewGroup, false);
            this.mRootView.addView(createTitleBarView(layoutInflater));
            this.mRootView.setPadding(a, 0, a, a);
            this.mMvItem = layoutInflater.inflate(R.layout.find_song_mv_item, this.mRootView, false);
            autoSetMvViewHeight();
            this.mMonth = (TextView) this.mMvItem.findViewById(R.id.find_song_mv_item_month);
            this.mDay = (TextView) this.mMvItem.findViewById(R.id.find_song_mv_item_day);
            this.mDesc = (TextView) this.mMvItem.findViewById(R.id.find_song_mv_item_desc);
            this.mCount = (TextView) this.mMvItem.findViewById(R.id.find_song_mv_item_count);
            ((IconTextView) this.mMvItem.findViewById(R.id.find_song_mv_item_count_pic)).setTextColor(-1);
            this.mRootView.addView(this.mMvItem);
            bindView();
        }
        return this.mRootView;
    }

    private void autoSetMvViewHeight() {
        int d = (int) (((double) (com.sds.android.ttpod.common.c.a.d() - (com.sds.android.ttpod.common.c.a.a(4) * 2))) * 0.535d);
        LayoutParams layoutParams = this.mMvItem.getLayoutParams();
        layoutParams.height = d;
        this.mMvItem.setLayoutParams(layoutParams);
    }

    private void bindView() {
        RecommendData itemData = getItemData(0);
        ImageView imageView = (ImageView) this.mMvItem.findViewById(R.id.find_song_mv_item_pic);
        requestImage(imageView, itemData.getPicUrl(), imageView.getWidth(), imageView.getHeight(), R.drawable.img_mv_default_image);
        a aVar = new a(itemData.getName());
        this.mMonth.setText(aVar.a());
        this.mDay.setText(aVar.b());
        this.mDesc.setText(itemData.getDesc());
        this.mCount.setText(f.a(Long.valueOf(itemData.getBulletCount())));
        this.mMvItem.setOnClickListener(createItemOnClickListener(0));
        bindTags(itemData.getTag());
    }

    private void bindTags(String str) {
        if (!m.a(str)) {
            String[] split = str.split(",");
            for (int i = 0; i < split.length; i++) {
                TextView textView = (TextView) this.mMvItem.findViewById(TAGS_VIEW_IDS[i]);
                CharSequence charSequence = split[i];
                if (charSequence.length() > 4) {
                    charSequence = charSequence.substring(0, 4);
                }
                textView.setText(charSequence);
                textView.setVisibility(0);
            }
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        setTitleBarTheme();
    }
}
