package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/* ISingerPageControl */
public interface a {
    View getSingerListHeaderView();

    ListView getSingerListView();

    void requestNextPage(AbsListView absListView, int i, int i2, int i3);

    void requestRefreshView(Bundle bundle);
}
