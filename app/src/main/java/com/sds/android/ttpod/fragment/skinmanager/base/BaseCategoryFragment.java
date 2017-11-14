package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.ArrayList;

public abstract class BaseCategoryFragment extends BaseFragment implements OnItemClickListener {
    protected static final long TIME_DELTA = 43200000;
    private a mCategoryAdapter;
    protected GridView mGridView;
    private boolean mIsUpdated = false;
    private StateView mStateView;

    private class a extends BaseAdapter {
        final /* synthetic */ BaseCategoryFragment a;
        private LayoutInflater b;
        private ArrayList<com.sds.android.ttpod.framework.modules.skin.a.a> c;
        private final float d = TTFMImageUtils.Large_Scale;
        private final float e = TTFMImageUtils.Middle_Scale;
        private final int f = (com.sds.android.ttpod.common.c.a.d() / 2);
        private final int g = ((int) ((((float) this.f) * TTFMImageUtils.Large_Scale) + TTFMImageUtils.Middle_Scale));

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(BaseCategoryFragment baseCategoryFragment) {
            this.a = baseCategoryFragment;
            this.b = LayoutInflater.from(baseCategoryFragment.getActivity());
        }

        public void a(ArrayList<com.sds.android.ttpod.framework.modules.skin.a.a> arrayList) {
            if (arrayList != null) {
                this.c = arrayList;
                notifyDataSetChanged();
            }
        }

        public void a() {
            if (this.c != null) {
                this.c.clear();
            }
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public com.sds.android.ttpod.framework.modules.skin.a.a a(int i) {
            return (this.c == null || i >= this.c.size()) ? null : (com.sds.android.ttpod.framework.modules.skin.a.a) this.c.get(i);
        }

        public int getCount() {
            return this.c != null ? this.c.size() : 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = a(viewGroup);
            }
            return a(view, i);
        }

        private View a(ViewGroup viewGroup) {
            View inflate = this.b.inflate(this.a.getItemLayoutId(), viewGroup, false);
            inflate.setTag(new com.sds.android.ttpod.fragment.skinmanager.a(inflate));
            return inflate;
        }

        private View a(View view, int i) {
            com.sds.android.ttpod.fragment.skinmanager.a aVar = (com.sds.android.ttpod.fragment.skinmanager.a) view.getTag();
            com.sds.android.ttpod.framework.modules.skin.a.a a = a(i);
            aVar.b().setText(a.b());
            a(a, aVar.a());
            return view;
        }

        private void a(com.sds.android.ttpod.framework.modules.skin.a.a aVar, ImageView imageView) {
            String e = aVar.e();
            Bitmap a = g.a(e, this.f, this.g);
            if (a != null) {
                imageView.setImageBitmap(a);
            } else {
                g.a(imageView, e, this.f, this.g, (int) R.drawable.img_skin_default_thumbnail);
            }
        }
    }

    protected abstract int getItemLayoutId();

    protected abstract com.sds.android.ttpod.framework.base.a.a getLoadDataCommand();

    protected abstract com.sds.android.ttpod.framework.modules.a getRequestDataCommandID();

    protected abstract void startActivity(com.sds.android.ttpod.framework.modules.skin.a.a aVar);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCategoryAdapter = new a(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_base_category_layout, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.theme_loadingview);
        this.mStateView.setState(b.LOADING);
        this.mGridView = (GridView) view.findViewById(R.id.gridview_category);
        this.mGridView.setAdapter(this.mCategoryAdapter);
        this.mGridView.setOnItemClickListener(this);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        loadData();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        this.mCategoryAdapter.notifyDataSetChanged();
    }

    private void loadData() {
        com.sds.android.ttpod.framework.base.a.b.a().a(getLoadDataCommand());
    }

    public void onDataListDownloaded() {
        loadData();
    }

    public void onDataListParsed(ArrayList<com.sds.android.ttpod.framework.modules.skin.a.a> arrayList, Long l) {
        this.mCategoryAdapter.a();
        setAdapterDataSource(arrayList);
        if (!this.mIsUpdated) {
            checkUpdateDataList(l);
            this.mIsUpdated = true;
        }
    }

    protected void setAdapterDataSource(ArrayList<com.sds.android.ttpod.framework.modules.skin.a.a> arrayList) {
        this.mCategoryAdapter.a((ArrayList) arrayList);
        this.mStateView.setState(b.SUCCESS);
    }

    protected a getAdapter() {
        return this.mCategoryAdapter;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (c.e()) {
            com.sds.android.ttpod.framework.modules.skin.a.a a = this.mCategoryAdapter.a(i);
            if (a != null) {
                startActivity(a);
                return;
            }
            return;
        }
        f.a((int) R.string.shake_error_hint);
    }

    private void checkUpdateDataList(Long l) {
        if (l.longValue() + TIME_DELTA < System.currentTimeMillis()) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(getRequestDataCommandID(), new Object[0]));
        }
    }
}
