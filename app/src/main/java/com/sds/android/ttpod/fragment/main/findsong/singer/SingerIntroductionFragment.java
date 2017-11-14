package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.b.e;
import com.sds.android.ttpod.framework.modules.b.f;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.util.ArrayList;
import java.util.List;

public class SingerIntroductionFragment extends BaseFragment {
    private LayoutInflater mInflater;
    private View mIntroductionView;
    private LinearLayout mItemsContainer;
    private ImageView mLandScapeView;
    private View mRootView;
    private IconTextView mSingerDefaultIcon;
    private SingerDetailResult mSingerDetailResult;
    private List<a> mSingerInfoItemViewHolders = new ArrayList();
    private TextView mTweetTextView;

    class a {
        final /* synthetic */ SingerIntroductionFragment a;
        private View b;
        private TextView c;
        private TextView d;

        public a(SingerIntroductionFragment singerIntroductionFragment, View view, TextView textView, TextView textView2) {
            this.a = singerIntroductionFragment;
            this.b = view;
            this.c = textView;
            this.d = textView2;
            a();
        }

        public void a(f fVar) {
            if (this.c != null) {
                this.c.setText(fVar.a());
            }
            if (this.d != null) {
                this.d.setText(fVar.b());
            }
        }

        public void a() {
            c.a(this.b, ThemeElement.SONG_LIST_ITEM_INDICATOR);
            c.a(this.c, ThemeElement.TILE_TEXT);
            c.a(this.d, ThemeElement.TILE_SUB_TEXT);
        }
    }

    public SingerIntroductionFragment(SingerDetailResult singerDetailResult) {
        this.mSingerDetailResult = singerDetailResult;
    }

    public void onGotSingerDetail() {
        e convertToSingerInfoListResult = convertToSingerInfoListResult(this.mSingerDetailResult);
        requestHeaderImage(convertToSingerInfoListResult.b());
        initIntroductionView(convertToSingerInfoListResult.c());
        initSingerInfoItemsView(convertToSingerInfoListResult.a());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mInflater = layoutInflater;
        initView(layoutInflater, viewGroup);
        return this.mIntroductionView;
    }

    private void initView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.mIntroductionView = layoutInflater.inflate(R.layout.singer_introduction_layout, viewGroup, false);
        this.mRootView = this.mIntroductionView.findViewById(R.id.singer_info_scroller);
        this.mItemsContainer = (LinearLayout) this.mIntroductionView.findViewById(R.id.singer_info_container);
        this.mLandScapeView = (ImageView) this.mIntroductionView.findViewById(R.id.singer_introduction_pic);
        this.mSingerDefaultIcon = (IconTextView) this.mIntroductionView.findViewById(R.id.singer_icon_text);
        onGotSingerDetail();
    }

    private void requestHeaderImage(String str) {
        int d = com.sds.android.ttpod.common.c.a.d() - (getResources().getDimensionPixelSize(R.dimen.singer_avatar_padding) * 2);
        LayoutParams layoutParams = this.mLandScapeView.getLayoutParams();
        LayoutParams layoutParams2 = this.mSingerDefaultIcon.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = d;
            layoutParams.height = d;
        }
        if (layoutParams2 != null) {
            layoutParams2.width = d;
            layoutParams2.height = d;
        }
        if (m.a(str)) {
            this.mSingerDefaultIcon.setVisibility(8);
            this.mLandScapeView.setImageResource(R.drawable.img_singer_default);
            return;
        }
        g.a(this.mLandScapeView, str, d, d, new com.sds.android.ttpod.framework.a.g.a(this) {
            final /* synthetic */ SingerIntroductionFragment a;

            {
                this.a = r1;
            }

            public Bitmap a(Bitmap bitmap) {
                this.a.mSingerDefaultIcon.setVisibility(8);
                return b.a(bitmap, false);
            }
        });
    }

    private void initIntroductionView(String str) {
        this.mTweetTextView = (TextView) this.mIntroductionView.findViewById(R.id.singer_introduction);
        c.a(this.mTweetTextView, ThemeElement.TILE_SUB_TEXT);
        if (m.a(str)) {
            this.mTweetTextView.setText(getString(R.string.post_no_description));
        } else {
            this.mTweetTextView.setText(str);
        }
    }

    private View buildSingInfoItemView() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getActivity());
        }
        View inflate = this.mInflater.inflate(R.layout.singer_info_item_layout, null);
        a aVar = new a(this, inflate.findViewById(R.id.singer_info_indicator), (TextView) inflate.findViewById(R.id.singer_info_title), (TextView) inflate.findViewById(R.id.singer_info_content));
        inflate.setTag(aVar);
        this.mSingerInfoItemViewHolders.add(aVar);
        return inflate;
    }

    private e convertToSingerInfoListResult(SingerDetailResult singerDetailResult) {
        e eVar = new e();
        eVar.a(e.a(singerDetailResult.getData()));
        eVar.setCode(singerDetailResult.getCode());
        eVar.setMessage(singerDetailResult.getMessage());
        eVar.a(singerDetailResult.getData().getPicUrl());
        eVar.b(singerDetailResult.getData().getBrief());
        return eVar;
    }

    private void initSingerInfoItemsView(List<f> list) {
        if (list != null || !list.isEmpty()) {
            for (f fVar : list) {
                String a = fVar.a();
                String b = fVar.b();
                if (!(m.a(a) || m.a(b))) {
                    View buildSingInfoItemView = buildSingInfoItemView();
                    a aVar = (a) buildSingInfoItemView.getTag();
                    aVar.a(fVar);
                    aVar.a();
                    this.mItemsContainer.addView(buildSingInfoItemView);
                }
            }
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mTweetTextView, ThemeElement.TILE_SUB_TEXT);
        v.a(this.mSingerDefaultIcon, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, (int) R.string.icon_singer_default, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(this.mSingerDefaultIcon, ThemeElement.TILE_MASK);
        for (a a : this.mSingerInfoItemViewHolders) {
            a.a();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        y.a(this.mIntroductionView);
        y.a(this.mLandScapeView);
        this.mInflater = null;
        this.mSingerInfoItemViewHolders = null;
    }
}
