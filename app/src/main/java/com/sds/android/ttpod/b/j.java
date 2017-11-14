package com.sds.android.ttpod.b;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import com.sds.android.cloudapi.ttpod.result.StyleDataListResult;
import com.sds.android.ttpod.fragment.main.findsong.FindSongBannerFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongCarouselFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongGridViewFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongListViewFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongMvFragment;
import com.sds.android.ttpod.fragment.main.findsong.FindSongPostFragment;
import java.util.ArrayList;

/* FindSongFragmentFactory */
public class j {
    private static final SparseArray<String> a = new SparseArray();

    static {
        a.put(0, FindSongPostFragment.class.getName());
        a.put(1, FindSongBannerFragment.class.getName());
        a.put(2, FindSongListViewFragment.class.getName());
        a.put(3, FindSongGridViewFragment.class.getName());
        a.put(4, FindSongGridViewFragment.class.getName());
        a.put(5, FindSongGridViewFragment.class.getName());
        a.put(6, FindSongGridViewFragment.class.getName());
        a.put(7, FindSongCarouselFragment.class.getName());
        a.put(8, FindSongPostFragment.class.getName());
        a.put(9, FindSongMvFragment.class.getName());
    }

    public static Fragment a(Context context, StyleDataListResult styleDataListResult) {
        int style = styleDataListResult.getStyle();
        ArrayList dataList = styleDataListResult.getDataList();
        if (context == null || dataList == null || dataList.isEmpty() || style < 0 || style >= a.size()) {
            return null;
        }
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        arrayList.add(styleDataListResult);
        bundle.putParcelableArrayList("result", arrayList);
        return Fragment.instantiate(context, (String) a.get(style), bundle);
    }
}
