package com.sds.android.ttpod.fragment.mv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MVCategoryDimension;
import com.sds.android.cloudapi.ttpod.result.MVCategoryDimensionResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MVSelectCategoryFragment extends SlidingClosableFragment {
    private static final int SELECT_ALL_CATEGORY_DIMENSION = 0;
    private static long mSelectionId = 0;
    private a mCategorySelectedListener;
    private List<com.sds.android.ttpod.fragment.main.findsong.base.a> mDimenLayoutHolders = new ArrayList();
    private View mPreSelectedCategoryView;
    private ViewGroup mRootView;
    private View mSelectAllView;
    private MVCategoryDimension mSelectedDimension;
    private StateView mStateView;

    public interface a {
        void a(MVCategoryDimension mVCategoryDimension);
    }

    private final class b extends BaseAdapter {
        final /* synthetic */ MVSelectCategoryFragment a;
        private final MVCategoryDimension b;
        private final List<MVCategoryDimension> c = this.b.getCategories();

        class a {
            final /* synthetic */ b a;
            private TextView b;
            private View c;

            a(b bVar, View view) {
                this.a = bVar;
                this.b = (TextView) view.findViewById(R.id.mv_category_item);
                this.c = view.findViewById(R.id.mv_category_item_background);
            }
        }

        public b(MVSelectCategoryFragment mVSelectCategoryFragment, MVCategoryDimension mVCategoryDimension) {
            this.a = mVSelectCategoryFragment;
            this.b = mVCategoryDimension;
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.a.getLayoutInflater(null).inflate(R.layout.layout_mv_category_grid_item, viewGroup, false);
                view.setTag(new a(this, view));
            }
            a((MVCategoryDimension) this.c.get(i), (a) view.getTag());
            return view;
        }

        private void a(final MVCategoryDimension mVCategoryDimension, final a aVar) {
            aVar.b.setText(mVCategoryDimension.getName());
            if (mVCategoryDimension.getId() == MVSelectCategoryFragment.mSelectionId) {
                aVar.c.setSelected(true);
                this.a.mPreSelectedCategoryView = aVar.c;
            }
            aVar.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ b c;

                public void onClick(View view) {
                    MVSelectCategoryFragment.mSelectionId = mVCategoryDimension.getId();
                    if (this.c.a.mPreSelectedCategoryView != null) {
                        this.c.a.mPreSelectedCategoryView.setSelected(false);
                    }
                    aVar.c.setSelected(true);
                    this.c.a.mPreSelectedCategoryView = aVar.c;
                    this.c.a.mSelectedDimension = mVCategoryDimension;
                    this.c.a.doCategorySelectedStats();
                    this.c.a.finish();
                }
            });
        }
    }

    public MVSelectCategoryFragment(a aVar) {
        this.mCategorySelectedListener = aVar;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MV_CATEGORY_DIMENSIONS, i.a(getClass(), "updateCategoryDimensions", MVCategoryDimensionResult.class));
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().a(getString(R.string.select_mv_category));
        this.mRootView = (ViewGroup) layoutInflater.inflate(R.layout.fragment_select_mv_category, viewGroup, false);
        this.mStateView = new StateView(getActivity());
        this.mStateView.setLayoutParams(new LayoutParams(-1, -1));
        this.mStateView.setSuccessView(this.mRootView);
        this.mStateView.setFailedView(layoutInflater.inflate(R.layout.loadingview_failed, this.mStateView, false));
        this.mStateView.setOnRetryRequestListener(new com.sds.android.ttpod.widget.StateView.a(this) {
            final /* synthetic */ MVSelectCategoryFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.acquireMVCategoryDimensions();
            }
        });
        this.mSelectAllView = this.mRootView.findViewById(R.id.select_all_container);
        this.mPreSelectedCategoryView = this.mSelectAllView;
        this.mPreSelectedCategoryView.setSelected(mSelectionId == 0);
        this.mSelectAllView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MVSelectCategoryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                MVSelectCategoryFragment.mSelectionId = 0;
                this.a.mPreSelectedCategoryView.setSelected(false);
                MVCategoryDimension mVCategoryDimension = new MVCategoryDimension();
                mVCategoryDimension.setName(this.a.getString(R.string.mv_select_all_category));
                mVCategoryDimension.setId(0);
                this.a.mSelectedDimension = mVCategoryDimension;
                this.a.mSelectAllView.setSelected(true);
                this.a.doCategorySelectedStats();
                this.a.finish();
            }
        });
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        this.mStateView.setBackgroundResource(17170443);
        setTBSPage("tt_mv_category_choose");
        return this.mStateView;
    }

    public void onLoadFinished() {
        acquireMVCategoryDimensions();
    }

    private void acquireMVCategoryDimensions() {
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ACQUIRE_MV_CATEGORY_DIMENSIONS, new Object[0]));
    }

    public void updateCategoryDimensions(MVCategoryDimensionResult mVCategoryDimensionResult) {
        if (mVCategoryDimensionResult.isSuccess() && j.b(mVCategoryDimensionResult.getDimensions())) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            ViewGroup viewGroup = (ViewGroup) this.mRootView.findViewById(R.id.mv_category_container);
            for (MVCategoryDimension createDimensionView : mVCategoryDimensionResult.getDimensions()) {
                com.sds.android.ttpod.fragment.main.findsong.base.a createDimensionView2 = createDimensionView(createDimensionView);
                this.mDimenLayoutHolders.add(createDimensionView2);
                viewGroup.addView(createDimensionView2.b());
            }
            onThemeLoaded();
            return;
        }
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
        f.a((int) R.string.network_unavailable);
    }

    private com.sds.android.ttpod.fragment.main.findsong.base.a createDimensionView(MVCategoryDimension mVCategoryDimension) {
        View inflate = getLayoutInflater(null).inflate(R.layout.layout_mv_select_category, this.mRootView, false);
        ((TextView) inflate.findViewById(R.id.select_dimension_name)).setText(mVCategoryDimension.getName());
        ((GridView) inflate.findViewById(R.id.select_dimension_grid)).setAdapter(new b(this, mVCategoryDimension));
        return new com.sds.android.ttpod.fragment.main.findsong.base.a(inflate);
    }

    private void doCategorySelectedStats() {
        new com.sds.android.ttpod.framework.a.b.b().b("mv_type").a("mv_type_id", String.valueOf(this.mSelectedDimension.getId())).a("mv_type_name", this.mSelectedDimension.getName()).a();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mDimenLayoutHolders.clear();
    }

    public void onDetach() {
        super.onDetach();
        notifyCategorySelected();
    }

    private void notifyCategorySelected() {
        if (this.mCategorySelectedListener != null && this.mSelectedDimension != null) {
            this.mCategorySelectedListener.a(this.mSelectedDimension);
        }
    }
}
