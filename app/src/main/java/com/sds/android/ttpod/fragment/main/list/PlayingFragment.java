package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.view.View;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.Map;

public class PlayingFragment extends MediaListFragment {
    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.PLAY_GROUP_CHANGED, i.a(getClass(), "playGroupChanged", new Class[0]));
    }

    protected boolean isAZSideBarEnable() {
        String groupID = getGroupID();
        return (groupID.equals(MediaStorage.GROUP_ID_ALL_LOCAL) || groupID.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX) || groupID.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) && (b.l(groupID).equals("title_key") || b.l(groupID).equals("artist_key"));
    }

    protected void configFailedView(View view) {
        super.configFailedView(view);
        view.findViewById(R.id.no_data_action_view).setVisibility(8);
    }

    protected void configListFooterView(View view) {
        super.configListFooterView(view);
        onFlushMediaItemCountView();
    }

    protected void onFlushMediaItemCountView() {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        onFlushMediaItemCountView();
    }

    public void playGroupChanged() {
        this.mGroupID = b.m();
        onReloadData();
    }
}
