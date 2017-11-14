package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.ttpod.adapter.d.h;
import com.sds.android.ttpod.adapter.d.i;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public abstract class FriendsFragment extends UserListFragment<TTPodUser> {
    private static final long YEAR_2035 = 2075240751;
    private long mCurrentTimeStamp = YEAR_2035;
    private long mUserId;

    protected abstract a onLoadRequestDataCommandID();

    protected abstract a onLoadUpdateRequestDataCommandID();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mUserId = arguments.getLong("user_id");
        }
    }

    protected i<TTPodUser> onCreateAdapter(List<TTPodUser> list) {
        return new i<TTPodUser>(this, getActivity(), list) {
            final /* synthetic */ FriendsFragment a;

            protected void a(h hVar, TTPodUser tTPodUser) {
                super.a(hVar, tTPodUser);
                hVar.d().setVisibility(8);
            }
        };
    }

    protected void onRequestData(int i, int i2) {
        if (this.mUserId > 0) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(onLoadRequestDataCommandID(), Long.valueOf(this.mUserId), Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(this.mCurrentTimeStamp), ""));
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        reload();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(onLoadUpdateRequestDataCommandID(), com.sds.android.sdk.lib.util.i.a(getClass(), "onUpdateData", UserListResult.class, String.class));
    }

    public void onUpdateData(UserListResult userListResult, String str) {
        updateRequestTime();
        addData(userListResult.getDataList(), true);
        setPageTotal(userListResult.getExtra().b());
    }

    private void updateRequestTime() {
        if (this.mCurrentTimeStamp == YEAR_2035) {
            this.mCurrentTimeStamp = com.sds.android.sdk.lib.a.a.a.b();
        }
    }
}
