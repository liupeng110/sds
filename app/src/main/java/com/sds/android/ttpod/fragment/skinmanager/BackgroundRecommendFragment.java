package com.sds.android.ttpod.fragment.skinmanager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.activities.BackgroundManagementActivity;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment;
import com.sds.android.ttpod.fragment.skinmanager.base.a;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BackgroundRecommendFragment extends BackgroundBaseFragment implements a {
    protected void initListViewHeader() {
    }

    protected void initListViewFooter() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        sBkgEditListenerList.add(this);
        setStatisticPage(s.PAGE_NICE_BACKGROUND);
    }

    public void onDestroy() {
        super.onDestroy();
        sBkgEditListenerList.remove(this);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND_LIST, i.a(cls, "updateBackgroundList", ArrayList.class));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        v.a(view);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        loadBackgroundList();
    }

    private void loadBackgroundList() {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.LOAD_BACKGROUND_LIST, Boolean.valueOf(false)));
    }

    public void updateBackgroundList(ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList) {
        if (this.mBackgroundAdapter != null && arrayList != null) {
            this.mBackgroundAdapter.a((ArrayList) arrayList);
            this.mStateView.setState(StateView.b.SUCCESS);
        } else if (this.mStateView != null) {
            this.mStateView.setState(StateView.b.FAILED);
        }
    }

    public void updateBackground(Drawable drawable) {
        if (!(getActivity() instanceof BackgroundManagementActivity)) {
            super.updateBackground(drawable);
        }
    }

    public boolean isSupportOfflineMode() {
        return true;
    }

    public void onBkgDeleted(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        com.sds.android.ttpod.framework.modules.theme.a itemForName = getItemForName(aVar.b());
        if (itemForName != null) {
            itemForName.a(com.sds.android.ttpod.framework.modules.theme.a.a.ONLINE_BACKGROUND);
            notifyDataSetChanged();
        }
    }

    private com.sds.android.ttpod.framework.modules.theme.a getItemForName(String str) {
        Iterator it = this.mBackgroundAdapter.b().iterator();
        while (it.hasNext()) {
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) it.next();
            if (aVar.b().equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}
