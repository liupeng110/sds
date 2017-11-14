package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult.CategoryData;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult.SubCategoryData;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.c;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.NetworkLoadView.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SongCategoryChannelFragment extends GridViewFragment<CategoryData> {
    private static final int REQUEST_SUB_CATEGORY_PAGE = 1;
    private static final int REQUEST_SUB_CATEGORY_PAGE_SIZE = 15;
    private a mAdapter = new a();
    private long mId;
    private b mOnStartLoadingListener = new b(this) {
        final /* synthetic */ SongCategoryChannelFragment a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.requestSubCategory();
        }
    };
    private OnlineMusicSubCategoryResult mSubCategoryResult;
    private String mTitle;

    private class a extends c<SubCategoryData> {
        final /* synthetic */ SongCategoryChannelFragment a;

        private a(SongCategoryChannelFragment songCategoryChannelFragment) {
            this.a = songCategoryChannelFragment;
        }

        protected /* synthetic */ String b(Object obj) {
            return a((SubCategoryData) obj);
        }

        protected /* synthetic */ String c(Object obj) {
            return b((SubCategoryData) obj);
        }

        protected /* synthetic */ String d(Object obj) {
            return c((SubCategoryData) obj);
        }

        protected String a(SubCategoryData subCategoryData) {
            return subCategoryData.getName();
        }

        protected String b(SubCategoryData subCategoryData) {
            if (subCategoryData.getCount() == 0) {
                return "";
            }
            return this.b.getString(R.string.count_of_media, new Object[]{Integer.valueOf(subCategoryData.getCount())});
        }

        protected String c(SubCategoryData subCategoryData) {
            return null;
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.song_category_grid_list_view_item, viewGroup, false);
            inflate.setTag(new com.sds.android.ttpod.adapter.c.a(inflate));
            return inflate;
        }

        protected void a(View view, SubCategoryData subCategoryData, int i) {
            super.a(view, subCategoryData, i);
            a((com.sds.android.ttpod.adapter.c.a) view.getTag());
        }
    }

    private void gotoBrowserPage(String str, String str2) {
    }

    public SongCategoryChannelFragment(com.sds.android.ttpod.adapter.b.a<CategoryData> aVar) {
        super(null);
        this.mTitle = aVar.c();
        this.mId = aVar.b();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MUSIC_SUB_CATEGORY, i.a(getClass(), "updateSubCategory", OnlineMusicSubCategoryResult.class));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadView.setOnStartLoadingListener(this.mOnStartLoadingListener);
    }

    private void requestSubCategory() {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_MUSIC_SUB_CATEGORY, Long.valueOf(this.mId), Integer.valueOf(1), Integer.valueOf(15)));
    }

    public void updateSubCategory(OnlineMusicSubCategoryResult onlineMusicSubCategoryResult) {
        this.mSubCategoryResult = onlineMusicSubCategoryResult;
        e.a(this, this.mSubCategoryResult, new com.sds.android.ttpod.fragment.main.e.a<OnlineMusicSubCategoryResult>(this) {
            final /* synthetic */ SongCategoryChannelFragment a;

            {
                this.a = r1;
            }

            public void a(OnlineMusicSubCategoryResult onlineMusicSubCategoryResult) {
                this.a.updateSubCategoryView(onlineMusicSubCategoryResult);
            }
        });
    }

    private void updateSubCategoryView(OnlineMusicSubCategoryResult onlineMusicSubCategoryResult) {
        if (onlineMusicSubCategoryResult != null) {
            if (onlineMusicSubCategoryResult.isSuccess()) {
                List subCategoryList = onlineMusicSubCategoryResult.getSubCategoryList();
                if (subCategoryList != null) {
                    this.mAdapter = new a();
                    this.mAdapter.a(subCategoryList);
                    setGridListAdapter(this.mAdapter);
                    this.mLoadView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
                    return;
                }
                return;
            }
            this.mLoadView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateSubCategoryView(this.mSubCategoryResult);
    }

    protected String onLoadTitleText() {
        return this.mTitle;
    }

    public void updateDataList(ArrayList arrayList) {
        super.updateDataList(arrayList);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mSubCategoryResult != null && !j.a(this.mSubCategoryResult.getSubCategoryList())) {
            SubCategoryData subCategoryData = (SubCategoryData) this.mSubCategoryResult.getSubCategoryList().get(i);
            if (subCategoryData != null) {
                String name = subCategoryData.getName();
                long id = subCategoryData.getId();
                if (m.a(subCategoryData.getUrl())) {
                    launchFragment(new SubSongCategoryDetailFragment(subCategoryData));
                } else {
                    gotoBrowserPage(subCategoryData.getUrl(), name);
                }
                new SUserEvent("PAGE_CLICK", r.ACTION_LIBRARY_THREE.getValue(), this.mTitle, name).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(subCategoryData.getId())).post();
                new com.sds.android.ttpod.framework.a.b.b().a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(id)).a("name", name).a();
                o.d((int) id, name);
            }
        }
    }
}
