package com.sds.android.ttpod.activities.ktv;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.f;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.fragment.main.list.DraggableMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;

public class KtvMediaListFragment extends DraggableMediaListFragment {
    protected void setViewTagHolder(View view) {
        d dVar = new d(view);
        dVar.h().setTag(new f(dVar.h()));
        view.setTag(dVar);
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.ktv_list_media_empty, null);
    }

    protected void configFailedView(View view) {
    }

    protected boolean needFailedState() {
        return true;
    }

    protected void updateMediaList(List<MediaItem> list) {
        super.updateMediaList(list);
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof SubMediaListFragment) {
            ((SubMediaListFragment) parentFragment).getActionBarController().a(list.isEmpty());
        }
    }

    protected void bindView(g gVar, MediaItem mediaItem, boolean z) {
        super.bindView(gVar, mediaItem, z);
        if (gVar instanceof d) {
            d dVar = (d) gVar;
            dVar.a(!z);
            dVar.d().setVisibility(8);
            dVar.e().setVisibility(8);
            dVar.f().setVisibility(8);
            dVar.g().setVisibility(8);
        }
    }
}
