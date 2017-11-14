package com.sds.android.ttpod.fragment.main.list;

import com.sds.android.ttpod.b.l;

public class DraggableSubMediaListFragment extends SubMediaListFragment {
    protected String selectMediaListFragmentClassName() {
        if (l.b(getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID))) {
            return DraggableMediaListFragment.class.getName();
        }
        throw new IllegalArgumentException("list must be Draggable");
    }
}
