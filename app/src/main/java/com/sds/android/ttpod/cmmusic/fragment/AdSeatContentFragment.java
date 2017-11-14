package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.cmmusic.a.a;
import com.sds.android.ttpod.cmmusic.d.e;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;

public class AdSeatContentFragment extends BaseFragment {
    private a mAdapter;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ AdSeatContentFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.mViewContentList.addAll(this.a.mItemInfoListTemp);
                    this.a.mItemInfoListTemp.clear();
                    this.a.mAdapter.notifyDataSetChanged();
                    this.a.mIsNull = false;
                    return;
                default:
                    return;
            }
        }
    };
    private String mHref;
    private boolean mIsNull = false;
    private ArrayList<HashMap<String, String>> mItemInfoListTemp = new ArrayList();
    private ListView mListContent;
    private Thread mLoadWebDataThread;
    private ArrayList<HashMap<String, String>> mViewContentList = new ArrayList();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cmmusic_ad_seat_content_activity, viewGroup, false);
        getIntentData();
        listViewInit(inflate);
        getDBViewContent();
        return inflate;
    }

    private void getIntentData() {
        try {
            this.mHref = getArguments().getString("href");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDBViewContent() {
        com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
            final /* synthetic */ AdSeatContentFragment a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.mItemInfoListTemp = new ArrayList();
                this.a.mItemInfoListTemp.addAll(e.a(com.sds.android.ttpod.cmmusic.c.e.a(this.a.mHref)));
                Message message = new Message();
                message.what = 1;
                this.a.mHandler.sendMessage(message);
            }
        });
    }

    private void onScrollAddData(int i) {
        this.mItemInfoListTemp = new ArrayList();
        try {
            this.mItemInfoListTemp.addAll(e.a(com.sds.android.ttpod.cmmusic.c.e.a(this.mHref, Integer.valueOf(i))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void listViewInit(View view) {
        this.mListContent = (ListView) view.findViewById(R.id.ad_list_seatpage);
        this.mAdapter = new a(getActivity(), this, this.mViewContentList, "AdSeatPage_" + this.mHref, s.PAGE_CMMUSIC_AD_SEAT_CODE.getValue());
        this.mListContent.setAdapter(this.mAdapter);
        this.mListContent.setCacheColorHint(0);
        this.mListContent.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ AdSeatContentFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (this.a.mIsNull || i != 0 || absListView.getLastVisiblePosition() != absListView.getCount() - 1) {
                    return;
                }
                if (this.a.mLoadWebDataThread == null || this.a.mLoadWebDataThread.isAlive()) {
                    this.a.mLoadWebDataThread = new Thread(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.a.mListContent.getCount() > 0) {
                                this.a.a.onScrollAddData(this.a.a.mListContent.getCount());
                                this.a.a.mIsNull = true;
                            }
                            Message message = new Message();
                            message.what = 1;
                            this.a.a.mHandler.sendMessage(message);
                        }
                    };
                    this.a.mLoadWebDataThread.start();
                    this.a.mLoadWebDataThread = null;
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        });
    }
}
