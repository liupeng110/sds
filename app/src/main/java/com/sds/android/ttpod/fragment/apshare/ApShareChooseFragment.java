package com.sds.android.ttpod.fragment.apshare;

import android.net.wifi.WifiInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a.b;
import com.sds.android.ttpod.adapter.a.b.a;
import com.sds.android.ttpod.component.apshare.j;
import com.sds.android.ttpod.component.apshare.k;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.List;

public class ApShareChooseFragment extends ApShareBaseFragment implements a {
    private static final String TAG = "ApShareChooseFragment";
    private b mAdapter;
    private com.sds.android.ttpod.component.a.a mChooseAction;
    private boolean mEnableBackPressed = true;
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ ApShareChooseFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            g.d(ApShareChooseFragment.TAG, "what= " + message.what);
            switch (message.what) {
                case 13:
                    this.a.mTvAction.setText(R.string.share_enabling_ap);
                    return;
                case 14:
                    String string;
                    String str = "已经打开热点：";
                    if (this.a.getActivity() != null) {
                        string = this.a.getString(R.string.share_enabled_ap);
                    } else {
                        string = str;
                    }
                    str = (String) message.obj;
                    if (m.a(str)) {
                        this.a.openApError();
                        return;
                    }
                    this.a.mEnableBackPressed = true;
                    this.a.mTvAction.setText(string + str);
                    this.a.launch();
                    return;
                case 15:
                    this.a.mTvAction.setText(R.string.share_enable_ap_failed);
                    return;
                default:
                    return;
            }
        }
    };
    private ListView mListView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ ApShareChooseFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.mTvAction) {
                l.g();
                WifiInfo k = this.a.mWifiApManager.k();
                if (k != null) {
                    com.sds.android.ttpod.component.apshare.a.c(k.getSSID());
                }
                this.a.startWifiAp();
            }
        }
    };
    private TextView mTvAction;
    private j mWifiApManager;

    private void startWifiAp() {
        this.mTvAction.setText(R.string.share_creating);
        this.mTvAction.setEnabled(false);
        this.mListView.setEnabled(false);
        this.mAdapter.f();
        this.mChooseAction.a(false);
        this.mEnableBackPressed = false;
        if (this.mWifiApManager != null) {
            this.mWifiApManager.e();
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        actionBarController.b((int) R.string.share_choose);
        View inflate = layoutInflater.inflate(R.layout.apshare_choose, null);
        this.mTvAction = (TextView) inflate.findViewById(R.id.tv_action);
        this.mListView = (ListView) inflate.findViewById(R.id.apshare_choose_listview);
        this.mAdapter = new b(layoutInflater.getContext(), this);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ ApShareChooseFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int headerViewsCount = i - this.a.mListView.getHeaderViewsCount();
                if (this.a.mEnableBackPressed && headerViewsCount < this.a.mAdapter.getCount()) {
                    this.a.mAdapter.d(this.a.mAdapter.a(headerViewsCount));
                }
            }
        });
        this.mTvAction.setOnClickListener(this.mOnClickListener);
        this.mChooseAction = actionBarController.d((int) R.drawable.img_checkbox_multiselect_checked);
        this.mChooseAction.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ ApShareChooseFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                boolean z = !this.a.mAdapter.c();
                this.a.mChooseAction.d(z ? R.drawable.img_checkbox_multiselect_checked : R.drawable.img_checkbox_multiselect_unchecked);
                this.a.mAdapter.a(z);
            }
        });
        this.mWifiApManager = j.a(getActivity());
        if (this.mWifiApManager.c()) {
            this.mWifiApManager.f();
        }
        g.d(TAG, "setWifiStateListener");
        this.mWifiApManager.a(new k(this.mHandler));
        com.sds.android.ttpod.component.apshare.a.b(this.mWifiApManager.a());
        com.sds.android.ttpod.component.apshare.a.a(this.mWifiApManager.d());
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        MediaItem mediaItem = null;
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("key_media_id");
            mediaItem = MediaStorage.queryMediaItem(getActivity(), MediaStorage.GROUP_ID_ALL_LOCAL, string);
            if (mediaItem == null) {
                mediaItem = MediaStorage.queryMediaItem(getActivity(), MediaStorage.GROUP_ID_FAV, string);
            }
        }
        List arrayList = new ArrayList();
        if (mediaItem != null) {
            arrayList.add(new a(mediaItem));
            this.mTvAction.postDelayed(new Runnable(this) {
                final /* synthetic */ ApShareChooseFragment a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.mTvAction != null) {
                        this.a.startWifiAp();
                    }
                }
            }, 1000);
        } else {
            for (MediaItem mediaItem2 : MediaStorage.queryMediaItemList(getActivity(), MediaStorage.GROUP_ID_ALL_LOCAL, MediaStorage.MEDIA_ORDER_BY_FILE_NAME)) {
                arrayList.add(new a(mediaItem2));
            }
        }
        this.mAdapter.a(arrayList);
        flushChosenCountView();
    }

    private void flushChosenCountView() {
        boolean z = true;
        int d = this.mAdapter.d();
        this.mTvAction.setText(getString(R.string.share_start, Integer.valueOf(d)));
        TextView textView = this.mTvAction;
        if (d <= 0) {
            z = false;
        }
        textView.setEnabled(z);
    }

    public void onChooseAmountChanged() {
        this.mChooseAction.d(this.mAdapter.c() ? R.drawable.img_checkbox_multiselect_checked : R.drawable.img_checkbox_multiselect_unchecked);
        flushChosenCountView();
    }

    private void launch() {
        boolean z = true;
        String str = TAG;
        String str2 = "launch getActivity() != null %b";
        Object[] objArr = new Object[1];
        if (getActivity() == null) {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        g.a(str, str2, objArr);
        if (getActivity() != null) {
            this.mWifiApManager.a(null);
            finish();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("key_data", this.mAdapter.e());
            ApSharingFragment apSharingFragment = (ApSharingFragment) Fragment.instantiate(getActivity(), ApSharingFragment.class.getName(), bundle);
            if (apSharingFragment != null) {
                ((BaseActivity) getActivity()).launchFragment(apSharingFragment);
            }
        }
    }

    private void openApError() {
        this.mWifiApManager.f();
        f.a((int) R.string.share_enable_ap_failed);
        finish();
    }

    protected void onTitleClicked() {
        if (this.mEnableBackPressed) {
            this.mWifiApManager.a(null);
            super.onTitleClicked();
        }
    }

    protected void onBackPressed() {
        if (this.mEnableBackPressed) {
            this.mWifiApManager.a(null);
            super.onBackPressed();
        }
    }

    public void onPause() {
        g.d(TAG, "onPause");
        this.mWifiApManager.a(null);
        finish();
        super.onPause();
    }
}
