package com.sds.android.ttpod.fragment.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.core.audioeffect.f;
import java.util.List;

public abstract class BaseEffectListFragment extends BaseFragment implements OnItemClickListener {
    protected BaseAdapter mAdapter;
    protected ListView mListView;
    protected List<f> mMyEffectItemList;

    private class a extends BaseAdapter {
        final /* synthetic */ BaseEffectListFragment a;

        private a(BaseEffectListFragment baseEffectListFragment) {
            this.a = baseEffectListFragment;
        }

        public int getCount() {
            return this.a.mMyEffectItemList == null ? 0 : this.a.mMyEffectItemList.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return this.a.getEffectItem(i, view, viewGroup);
        }
    }

    protected abstract View getEffectItem(int i, View view, ViewGroup viewGroup);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.effect_listview, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mListView = (ListView) view.findViewById(R.id.listview_effect);
        this.mAdapter = new a();
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void updateData(List<f> list) {
        this.mMyEffectItemList = list;
        notifyDataSetChanged();
    }

    public List<f> getData() {
        return this.mMyEffectItemList;
    }

    public final void notifyDataSetChanged() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
