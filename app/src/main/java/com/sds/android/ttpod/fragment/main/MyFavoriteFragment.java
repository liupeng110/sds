package com.sds.android.ttpod.fragment.main;

import android.os.Bundle;
import com.sds.android.ttpod.fragment.main.list.FavoriteSubMediaListFragment;

public class MyFavoriteFragment extends FavoriteSubMediaListFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_my_favorite");
        trackModule("my_favorite");
    }
}
