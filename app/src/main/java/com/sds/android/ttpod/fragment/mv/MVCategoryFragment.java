package com.sds.android.ttpod.fragment.mv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MVCategoryDimension;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.cloudapi.ttpod.result.MvCategoryResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.x;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.f;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.c.c;
import java.lang.reflect.Method;
import java.util.Map;

public class MVCategoryFragment extends BaseFragment implements OnClickListener, c {
    private static final Object MAX_COUNT = Integer.valueOf(12);
    private static String mSelectName = "全部";
    private static long mTypeId = 0;
    private com.sds.android.ttpod.widget.c.a mFooterViewCallback = new com.sds.android.ttpod.widget.c.a(this) {
        final /* synthetic */ MVCategoryFragment a;

        {
            this.a = r1;
        }

        public void a(int i) {
            this.a.mListViewPager.f(this.a.mMvCategoryAdapter.b().size());
        }
    };
    private LinearLayout mLayoutCategoryAll;
    private com.sds.android.ttpod.widget.c mListViewPager;
    private b mMvCategoryAdapter;
    private int mOrder = 2;
    private View mRootView;
    private TextView mSelectTextView;
    private TextView mTextViewCategoryAll;
    private TextView mTextViewHot;
    private TextView mTextViewNew;

    class a {
        final /* synthetic */ MVCategoryFragment a;
        private View b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private ImageView g;

        public a(MVCategoryFragment mVCategoryFragment, View view) {
            this.a = mVCategoryFragment;
            this.b = view;
            this.c = (TextView) view.findViewById(R.id.textview_mv_quality);
            this.d = (TextView) view.findViewById(R.id.text_mv_play_count);
            this.f = (TextView) view.findViewById(R.id.text_mv_title);
            this.e = (TextView) view.findViewById(R.id.text_mv_singer);
            this.g = (ImageView) view.findViewById(R.id.image_mv_pic);
        }
    }

    class b extends com.sds.android.ttpod.adapter.a<MvData> {
        final /* synthetic */ MVCategoryFragment a;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public b(MVCategoryFragment mVCategoryFragment) {
            this.a = mVCategoryFragment;
        }

        public int getCount() {
            return (int) Math.ceil(((double) b().size()) / 2.0d);
        }

        public MvData a(int i) {
            if (i < b().size()) {
                return (MvData) b().get(i);
            }
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(this.b).inflate(R.layout.fragment_mv_category_item, viewGroup, false);
            View findViewById = inflate.findViewById(R.id.layout_item1);
            View findViewById2 = inflate.findViewById(R.id.layout_item2);
            inflate.setTag(new a[]{new a(this.a, findViewById), new a(this.a, findViewById2)});
            return inflate;
        }

        protected void a(View view, MvData mvData, int i) {
            a[] aVarArr = (a[]) view.getTag();
            a(aVarArr[0], a(i * 2));
            a(aVarArr[1], a((i * 2) + 1));
        }

        private void a(a aVar, MvData mvData) {
            if (mvData == null) {
                aVar.b.setVisibility(4);
                return;
            }
            aVar.b.setVisibility(0);
            aVar.d.setText(f.a(mvData.getPlayCount()));
            a(aVar.e, mvData);
            a(aVar.f, mvData.getName());
            b(aVar.c, mvData);
            a(aVar.g, mvData);
            a(aVar.b, mvData);
        }

        private void a(TextView textView, MvData mvData) {
            textView.setText(mvData.getSingerName());
            com.sds.android.ttpod.framework.modules.theme.c.a((View) textView, ThemeElement.CARD_SUB_TEXT);
        }

        private void a(TextView textView, String str) {
            com.sds.android.ttpod.framework.modules.theme.c.a((View) textView, ThemeElement.CARD_TEXT);
            textView.setText(str);
        }

        private void a(View view, final MvData mvData) {
            if (!j.a(mvData.getMvList())) {
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ b b;

                    public void onClick(View view) {
                        d.j.a("mv_origin", "mv_category");
                        VideoPlayManager.a(this.b.a.getActivity(), mvData);
                        new com.sds.android.ttpod.framework.a.b.b().b("mv_category").a("mv_id", String.valueOf(mvData.getId())).a("mv_name", mvData.getName()).a();
                    }
                });
            }
        }

        private void a(ImageView imageView, MvData mvData) {
            if (!j.a(mvData.getMvList())) {
                g.a(imageView, ((MvListItem) mvData.getMvList().get(0)).getPicUrl(), 0, 0, (int) R.drawable.img_mv_default_image);
            }
        }

        private void b(TextView textView, MvData mvData) {
            if (!j.a(mvData.getMvList())) {
                textView.setVisibility(0);
                MvListItem mvListItem = (MvListItem) mvData.getMvList().get(0);
                if (mvListItem.getType() > 0) {
                    textView.setText(x.a(mvListItem.getType()).getMvQuality());
                    return;
                }
            }
            textView.setVisibility(4);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_mv_category, viewGroup, false);
        NetworkLoadView networkLoadView = (NetworkLoadView) this.mRootView.findViewById(R.id.load_view);
        ListView listView = (ListView) this.mRootView.findViewById(R.id.listview_mv);
        this.mTextViewNew = (TextView) this.mRootView.findViewById(R.id.text_mv_new);
        this.mTextViewHot = (TextView) this.mRootView.findViewById(R.id.text_mv_hot);
        this.mLayoutCategoryAll = (LinearLayout) this.mRootView.findViewById(R.id.layout_category_all);
        this.mTextViewCategoryAll = (TextView) this.mRootView.findViewById(R.id.text_category_all);
        this.mMvCategoryAdapter = new b(this);
        this.mListViewPager = new com.sds.android.ttpod.widget.c(listView, networkLoadView, this);
        this.mListViewPager.a(this.mFooterViewCallback);
        this.mListViewPager.e(R.string.count_of_mv);
        this.mListViewPager.a(this.mMvCategoryAdapter);
        this.mTextViewHot.setOnClickListener(this);
        this.mTextViewNew.setOnClickListener(this);
        this.mLayoutCategoryAll.setOnClickListener(this);
        this.mTextViewCategoryAll.setText(mSelectName);
        checkTextTabView(this.mTextViewHot);
        return this.mRootView;
    }

    public void onClick(View view) {
        if (view == this.mTextViewHot || view == this.mTextViewNew) {
            switchTextTab(view);
        } else if (this.mLayoutCategoryAll == view) {
            selectMvCategory();
        }
    }

    private void selectMvCategory() {
        BaseFragment mVSelectCategoryFragment = new MVSelectCategoryFragment(new com.sds.android.ttpod.fragment.mv.MVSelectCategoryFragment.a(this) {
            final /* synthetic */ MVCategoryFragment a;

            {
                this.a = r1;
            }

            public void a(MVCategoryDimension mVCategoryDimension) {
                MVCategoryFragment.mTypeId = mVCategoryDimension.getId();
                MVCategoryFragment.mSelectName = mVCategoryDimension.getName();
                this.a.mTextViewCategoryAll.setText(mVCategoryDimension.getName());
                this.a.reLoadData();
            }
        });
        com.sds.android.ttpod.framework.a.b.b.a("all_tag");
        launchFragment(mVSelectCategoryFragment);
    }

    private void checkTextTabView(TextView textView) {
        if (textView != this.mSelectTextView) {
            if (this.mSelectTextView != null) {
                this.mSelectTextView.setSelected(false);
            }
            this.mSelectTextView = textView;
            this.mSelectTextView.setSelected(true);
        }
    }

    private void switchTextTab(View view) {
        checkTextTabView((TextView) view);
        if ("hot".equals(String.valueOf(view.getTag()))) {
            this.mOrder = 2;
            com.sds.android.ttpod.framework.a.b.b.a("hot_mv");
        } else {
            this.mOrder = 1;
            com.sds.android.ttpod.framework.a.b.b.a("new_mv");
        }
        reLoadData();
    }

    private void reLoadData() {
        if (this.mMvCategoryAdapter != null && this.mMvCategoryAdapter.getCount() > 0) {
            this.mMvCategoryAdapter.b().clear();
            this.mMvCategoryAdapter.notifyDataSetChanged();
        }
        this.mListViewPager.j();
    }

    public void onLoadFinished() {
        this.mListViewPager.j();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mTextViewCategoryAll, ThemeElement.CARD_TEXT);
        if (this.mMvCategoryAdapter != null) {
            this.mMvCategoryAdapter.notifyDataSetChanged();
        }
        if (this.mListViewPager != null) {
            this.mListViewPager.l();
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MV_LIST, i.a(getClass(), "updateMVListData", MvCategoryResult.class));
    }

    public void updateMVListData(MvCategoryResult mvCategoryResult) {
        if (isViewAccessAble()) {
            this.mListViewPager.a(mvCategoryResult.isSuccess(), j.a(mvCategoryResult.getMvCategoryList()));
            this.mListViewPager.a(getString(R.string.not_mv));
            if (mvCategoryResult.isSuccess() && !j.a(mvCategoryResult.getMvCategoryList())) {
                this.mListViewPager.b(mvCategoryResult.getPageCount());
                if (this.mListViewPager.i()) {
                    this.mMvCategoryAdapter.a(mvCategoryResult.getMvCategoryList());
                } else {
                    this.mMvCategoryAdapter.b().addAll(mvCategoryResult.getMvCategoryList());
                }
                this.mMvCategoryAdapter.notifyDataSetChanged();
            }
        }
    }

    public void requestPageData(int i) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_MV_LIST, Long.valueOf(mTypeId), Integer.valueOf(i), Integer.valueOf(this.mOrder), MAX_COUNT));
    }
}
