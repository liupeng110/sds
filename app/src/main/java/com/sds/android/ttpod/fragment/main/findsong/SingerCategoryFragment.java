package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.data.SingerCategory;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.c;
import com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment;
import com.sds.android.ttpod.fragment.main.findsong.base.SingerListImageHeaderFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.NetworkLoadView.b;

public class SingerCategoryFragment extends GridViewFragment<SingerCategory> {
    private static final int ID_HOT = 110;
    private static final int ID_TOP100 = 54;
    private int mId;
    private b mOnStartLoadingListener = new b(this) {
        final /* synthetic */ SingerCategoryFragment a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.requestDataList();
        }
    };
    private String mTitle;

    private static class a extends c<SingerCategory> {
        private a() {
        }

        protected /* synthetic */ String b(Object obj) {
            return a((SingerCategory) obj);
        }

        protected /* synthetic */ String c(Object obj) {
            return b((SingerCategory) obj);
        }

        protected /* synthetic */ String d(Object obj) {
            return c((SingerCategory) obj);
        }

        protected String a(SingerCategory singerCategory) {
            return singerCategory.getTitle();
        }

        protected String b(SingerCategory singerCategory) {
            return this.b.getString(R.string.count_of_singers, new Object[]{Integer.valueOf(singerCategory.getCount())});
        }

        protected String c(SingerCategory singerCategory) {
            return singerCategory.getPicUrl();
        }

        protected void a(View view, SingerCategory singerCategory, int i) {
            super.a(view, singerCategory, i);
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.c.inflate(R.layout.find_song_singer_category_with_num_grid_list_view_item, viewGroup, false);
            inflate.setTag(new com.sds.android.ttpod.adapter.c.a(inflate));
            return inflate;
        }
    }

    public SingerCategoryFragment(String str, int i) {
        super(new a());
        this.mId = i;
        this.mTitle = str;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadView.setOnStartLoadingListener(this.mOnStartLoadingListener);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(this.mTitle);
        setStatisticPageProperties(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mId));
        setTBSPage("tt_singer");
    }

    protected String onLoadTitleText() {
        return this.mTitle;
    }

    protected void requestDataList() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SINGER_CATEGORY_LIST, Integer.valueOf(this.mId)));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        SingerCategory singerCategory = (SingerCategory) view.getTag(R.id.view_bind_data);
        int id = singerCategory.getId();
        String title = singerCategory.getTitle();
        if (id == ID_TOP100 || id == ID_HOT) {
            launchFragment(new SingerCategoryHotDetailFragment(title, id));
        } else {
            launchFragment(new SingerListImageHeaderFragment(title, id));
        }
        o.b(id, title);
        new SUserEvent("PAGE_CLICK", r.ACTION_SINGERS_CLASSIFICATION.getValue(), this.mTitle, title).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(id)).post();
        new com.sds.android.ttpod.framework.a.b.b().a("location", String.valueOf(i)).a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(id)).a("name", title).a();
    }
}
