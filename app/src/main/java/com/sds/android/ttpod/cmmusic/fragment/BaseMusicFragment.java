package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.Toast;
import com.a.a.a.h;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.cmmusic.a.a;
import com.sds.android.ttpod.cmmusic.d.b;
import com.sds.android.ttpod.cmmusic.d.e;
import com.sds.android.ttpod.cmmusic.widget.LoadMoreListView;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public abstract class BaseMusicFragment extends BaseFragment implements OnClickListener, OnScrollListener {
    private a mAdapter;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ BaseMusicFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.onDataChange();
                    this.a.mIsNull = false;
                    return;
                case 14:
                    String str = (String) ((Hashtable) message.obj).get("code");
                    if (FeedbackItem.STATUS_RECORDED.equals(str)) {
                        Toast.makeText(this.a.getActivity(), this.a.getString(R.string.cardisnotcmcc), 0).show();
                        return;
                    } else if (FeedbackItem.STATUS_SOLVED.equals(str)) {
                        Toast.makeText(this.a.getActivity(), this.a.getString(R.string.pleasechecknetwork), 0).show();
                        return;
                    } else if (b.a() && !FeedbackItem.STATUS_WAITING.equals(str)) {
                        com.sds.android.ttpod.cmmusic.b.a.a(this.a.getActivity());
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private boolean mIsNull = false;
    private ArrayList<HashMap<String, String>> mItemListTemp = new ArrayList();
    private View mLayoutFailed;
    private ListView mListView;
    private View mRootView;
    private ArrayList<HashMap<String, String>> mViewListContent = new ArrayList();

    protected abstract int getPageCode();

    protected abstract String getPageName();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.cmmusic_list_fragment, viewGroup, false);
        initView();
        if (c.e()) {
            this.mListView.setVisibility(0);
            this.mListView.removeFooterView(LoadMoreListView.getFooterView());
            updateViewContent();
        } else {
            this.mLayoutFailed.setVisibility(0);
        }
        return this.mRootView;
    }

    private void initView() {
        this.mLayoutFailed = this.mRootView.findViewById(R.id.layout_failed);
        this.mRootView.findViewById(R.id.btn_tryagain).setOnClickListener(this);
        this.mListView = (LoadMoreListView) this.mRootView.findViewById(R.id.listview_media);
        this.mAdapter = new a(getActivity(), this, this.mViewListContent, getPageName(), getPageCode());
        initHeaderView(this.mRootView);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnScrollListener(this);
    }

    protected ListView getListView() {
        return this.mListView;
    }

    protected void initHeaderView(View view) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (!this.mIsNull && i == 0 && absListView.getLastVisiblePosition() == absListView.getCount() - 1) {
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ BaseMusicFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mListView.getCount() > 0) {
                        this.a.mIsNull = true;
                        this.a.onScrollAddData();
                    }
                    this.a.refresh();
                }
            });
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    private void refresh() {
        if (this.mHandler != null) {
            Message message = new Message();
            message.what = 1;
            this.mHandler.sendMessage(message);
        }
    }

    private void sdkInitCheck() {
        if (!h.b(getActivity())) {
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ BaseMusicFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    Hashtable a = h.a(this.a.getActivity());
                    Message message = new Message();
                    message.what = 14;
                    message.obj = a;
                    this.a.mHandler.sendMessage(message);
                }
            });
        }
    }

    private void toastNotNetwork() {
        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.notNetworkPrompt), 0).show();
    }

    protected View getRootView() {
        return this.mRootView;
    }

    private void onScrollAddData() {
        try {
            this.mItemListTemp.clear();
            this.mItemListTemp.addAll(e.a(com.sds.android.ttpod.cmmusic.c.e.a(getPageName(), Integer.valueOf(this.mAdapter.getCount()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        if (R.id.btn_tryagain != view.getId()) {
            return;
        }
        if (c.e()) {
            sdkInitCheck();
            this.mListView.setVisibility(0);
            this.mLayoutFailed.setVisibility(8);
            updateViewContent();
            return;
        }
        toastNotNetwork();
    }

    protected void updateViewContent() {
        try {
            com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
                final /* synthetic */ BaseMusicFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.mItemListTemp.clear();
                    this.a.mItemListTemp.addAll(e.a(com.sds.android.ttpod.cmmusic.c.e.a(this.a.getPageName())));
                    this.a.refresh();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onDataChange() {
        if (this.mItemListTemp.size() > 0) {
            if (this.mListView.getFooterViewsCount() == 0) {
                this.mListView.addFooterView(LoadMoreListView.getFooterView());
            }
            this.mViewListContent.addAll(this.mItemListTemp);
            this.mAdapter.notifyDataSetChanged();
            this.mItemListTemp.clear();
        } else if (this.mItemListTemp.size() == 0) {
            this.mListView.removeFooterView(LoadMoreListView.getFooterView());
        }
    }
}
