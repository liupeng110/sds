package com.sds.android.ttpod.fragment.skinmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.ThemeRankActivity;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.fragment.skinmanager.base.OnlineThemeFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.skin.m;
import com.sds.android.ttpod.widget.StateView.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class ThemeRecommendFragment extends OnlineThemeFragment {
    protected void initListViewHeader() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_theme_header_layout, null);
        this.mThemeListView.addHeaderView(inflate);
        setHeadToggle(inflate.findViewById(R.id.button_toggle_background_manager), R.drawable.img_beautiful_background, new OnClickListener(this) {
            final /* synthetic */ ThemeRecommendFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                f.j(this.a.getActivity());
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_BEAUTIFUL_BACKGROUND.getValue(), s.PAGE_THEME_BACKGROUND.getValue(), s.PAGE_NICE_BACKGROUND.getValue()).post();
            }
        });
        setHeadToggle(inflate.findViewById(R.id.button_toggle_theme_rank), R.drawable.img_theme_rank, new OnClickListener(this) {
            final /* synthetic */ ThemeRecommendFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                x.c();
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_RANK.getValue(), s.PAGE_THEME_BACKGROUND.getValue(), s.PAGE_THEME_RANK.getValue()).post();
                this.a.getActivity().startActivity(new Intent(this.a.getActivity(), ThemeRankActivity.class));
            }
        });
    }

    private void setHeadToggle(View view, int i, OnClickListener onClickListener) {
        try {
            view.setBackgroundResource(i);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        view.setOnClickListener(onClickListener);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(a.UPDATE_RECOMMEND_SKIN_LIST, i.a(cls, "updateDataListForAdapter", ArrayList.class));
        map.put(a.UPDATE_DOWNLOADED_SKIN_LIST, i.a(cls, "updateDownloadedSkinList", ArrayList.class));
    }

    public void updateDataListForAdapter(ArrayList<m> arrayList) {
        this.mCacheMode = false;
        if (arrayList == null || arrayList.size() == 0) {
            this.mStateView.setState(b.FAILED);
            return;
        }
        if (checkIfReloadData(arrayList)) {
            setAdapterDataSource(arrayList);
        }
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_DOWNLOADED_SKIN_LIST, new Object[0]));
    }

    public void updateDownloadedSkinList(ArrayList<m> arrayList) {
        setLocalSkinInfoMap(arrayList);
        performSkinInfoLoaded();
    }

    protected String getStatisticOrigin() {
        return "recommend";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSubClassTag = ThemeRecommendFragment.class.getSimpleName();
        setStatisticPage(s.PAGE_THEME_BACKGROUND);
    }

    public void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.storage.a.a.a().a(getThemeDataList());
    }

    protected ArrayList<m> loadDataFromCache() {
        return com.sds.android.ttpod.framework.storage.a.a.a().r();
    }

    protected a getLoadDataCommandID() {
        return a.REQUEST_RECOMMEND_SKIN_LIST;
    }
}
