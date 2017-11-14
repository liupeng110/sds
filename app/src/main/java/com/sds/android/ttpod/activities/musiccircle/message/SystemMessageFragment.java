package com.sds.android.ttpod.activities.musiccircle.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.SystemNotice;
import com.sds.android.cloudapi.ttpod.result.SystemNoticeListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.d.a.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemMessageFragment extends SlidingClosableFragment {
    private static final int MESSAGE_SIZE = 20;
    private f mAdapter;
    private DragUpdateListView mListView;
    private SlidingClosableFragment mOriginFragment;
    private StateView mStateView;
    private List<SystemNotice> mSystemNotices = new ArrayList();

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().b((int) R.string.musiccircle_system_notice);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_layout, viewGroup, false);
        this.mStateView = (StateView) inflate.findViewById(R.id.musiccircle_message_loading_view);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mAdapter = new f(getActivity(), this.mSystemNotices);
        this.mListView.setAdapter(this.mAdapter);
        requestSystemMessage();
        return inflate;
    }

    private void loadErrorView() {
        View inflate = View.inflate(getActivity(), R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(b.FAILED);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SystemMessageFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (c.e()) {
                    this.a.requestSystemMessage();
                } else {
                    com.sds.android.ttpod.component.d.f.a((int) R.string.network_error);
                }
            }
        });
    }

    private void loadNoDataView() {
        View inflate = View.inflate(getActivity(), R.layout.musiccircle_message_empty, null);
        this.mStateView.setFailedView(inflate);
        this.mStateView.setState(b.FAILED);
        ((TextView) inflate.findViewById(R.id.note2)).setVisibility(0);
    }

    private void toggleFailedView() {
        if (c.e()) {
            loadNoDataView();
        } else {
            loadErrorView();
        }
    }

    protected boolean needSearchAction() {
        return false;
    }

    private void requestSystemMessage() {
        this.mStateView.setState(b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.REQUEST_SYSTEM_NOTICES, Long.valueOf(0), Integer.valueOf(20), "system_message"));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SYSTEM_NOTICE_LIST, i.a(getClass(), "updateSystemMessage", SystemNoticeListResult.class, String.class));
    }

    public void updateSystemMessage(SystemNoticeListResult systemNoticeListResult, String str) {
        if ("system_message".equals(str)) {
            List dataList = systemNoticeListResult.getDataList();
            if (dataList.isEmpty()) {
                toggleFailedView();
                return;
            }
            this.mStateView.setState(b.SUCCESS);
            this.mSystemNotices.clear();
            this.mSystemNotices = dataList;
            this.mAdapter.a(this.mSystemNotices);
        }
    }
}
