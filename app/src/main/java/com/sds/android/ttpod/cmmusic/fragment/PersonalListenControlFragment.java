package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.cmmusic.a.c;
import com.sds.android.ttpod.cmmusic.b.b;
import com.sds.android.ttpod.cmmusic.c.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonalListenControlFragment extends BaseFragment {
    private c mAdapter;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ PersonalListenControlFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.a.mItemInfoListTemp == null || this.a.mItemInfoListTemp.size() <= 0) {
                        this.a.mLoadingLayout.setVisibility(8);
                        this.a.mLayout.setVisibility(0);
                        return;
                    }
                    this.a.mLoadingLayout.setVisibility(8);
                    this.a.mViewContentList.addAll(this.a.mItemInfoListTemp);
                    this.a.mItemInfoListTemp.clear();
                    this.a.mAdapter.notifyDataSetChanged();
                    return;
                default:
                    return;
            }
        }
    };
    private ArrayList<HashMap<String, String>> mItemInfoListTemp = new ArrayList();
    private LinearLayout mLayout;
    private ListView mListView;
    private LinearLayout mLoadingLayout;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList();

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cmmusic_personal_listen_control_activity, viewGroup, false);
        linstwInit(inflate);
        initDBViewContent();
        return inflate;
    }

    private void initDBViewContent() {
        try {
            a.a((Object) this, new Runnable(this) {
                final /* synthetic */ PersonalListenControlFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    for (b bVar : j.a()) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("resource_name", bVar.a());
                        hashMap.put("resource_songer", bVar.b());
                        hashMap.put("cailing_id", bVar.d());
                        hashMap.put("time_out", bVar.g());
                        hashMap.put("resource_path", bVar.c());
                        this.a.mItemInfoListTemp.add(hashMap);
                    }
                    Message message = new Message();
                    message.what = 1;
                    this.a.mHandler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void linstwInit(View view) {
        this.mLayout = (LinearLayout) view.findViewById(R.id.persional_listen_not_data);
        this.mLoadingLayout = (LinearLayout) view.findViewById(R.id.persional_listen_loading);
        this.mListView = (ListView) view.findViewById(R.id.list_persionpage);
        this.mAdapter = new c(getActivity(), this.mViewContentList);
        this.mListView.setAdapter(this.mAdapter);
    }
}
