package com.sds.android.ttpod.fragment.apshare;

import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a.c;
import com.sds.android.ttpod.component.apshare.ClientModel;
import com.sds.android.ttpod.component.apshare.a;
import com.sds.android.ttpod.component.apshare.e;
import com.sds.android.ttpod.component.apshare.i;
import com.sds.android.ttpod.component.apshare.i.b;
import com.sds.android.ttpod.component.apshare.j;
import com.sds.android.ttpod.component.apshare.k;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ApShareReceiveFragment extends ApShareBaseFragment {
    private static final int CONNECT_WIFI_TIMEOUT_IN_MILLIS = 20000;
    private static final int SCAN_WIFI_INTERVAL_IN_MILLIS = 4000;
    private static final int SCAN_WIFI_MAXIMUM_TIMES = 100;
    private static final int STATE_CONNECTED = 5;
    private static final int STATE_CONNECTING = 4;
    private static final int STATE_SCANNING = 1;
    private static final int STATE_SCAN_STOP = 0;
    private static final int STATE_SEARCH_MULTI = 3;
    private static final int STATE_SEARCH_SINGLE = 2;
    private static final String TAG = "ApShareReceiveFragment";
    public static final int WHAT_DOWNLOAD = 100;
    public static final int WHAT_DOWNLOAD_CANCELED = 102;
    public static final int WHAT_GET_SHARED_LIST_COMPLETE = 101;
    private c mAdapter;
    private int mChooseIndex;
    private e mClient;
    private Runnable mConnectWifiTimeoutTask = new Runnable(this) {
        final /* synthetic */ ApShareReceiveFragment a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.mWifiApManager != null) {
                this.a.mWifiApManager.i();
                this.a.switchState(1);
            }
        }
    };
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ ApShareReceiveFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.getActivity() != null) {
                switch (message.what) {
                    case 4:
                        if (this.a.mShareDeviceList.size() == 0 && this.a.mState == 1) {
                            this.a.switchState(1);
                            return;
                        }
                        return;
                    case 5:
                        if (this.a.mState != 4) {
                            this.a.wifiScanFinished(message);
                            return;
                        }
                        return;
                    case 7:
                        this.a.wifiConnected(message);
                        return;
                    case 8:
                        this.a.wifiDisconnected();
                        return;
                    case 100:
                        this.a.startDownload(message);
                        return;
                    case 101:
                        this.a.mTvReceiveAll.setText(this.a.getString(R.string.share_receive_all, (Integer) message.obj));
                        return;
                    case 102:
                        if (this.a.mClient != null) {
                            this.a.mClient.b();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private boolean mIsConnected = false;
    private boolean mIsDialogShow = false;
    private boolean mIsExist = false;
    private ViewGroup mLayoutBottom;
    private ViewGroup mLayoutSearch;
    private ListView mListView;
    private String mMyIp;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ ApShareReceiveFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.mTvCancelAll) {
                this.a.cancelAll();
            } else if (view != this.a.mTvReceiveAll) {
                Object tag = view.getTag();
                if (tag instanceof Number) {
                    this.a.mChooseIndex = ((Number) tag).intValue();
                    this.a.switchState(2);
                }
            } else if (this.a.mAdapter != null) {
                this.a.mAdapter.d();
            }
        }
    };
    private TextView mRescanButton;
    private String mSSID;
    private int mScanCount = 0;
    private Runnable mScanWifiTask = new Runnable(this) {
        final /* synthetic */ ApShareReceiveFragment a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.mWifiApManager != null) {
                this.a.mWifiApManager.i();
            }
        }
    };
    private Runnable mScanWifiTimeoutTask = new Runnable(this) {
        final /* synthetic */ ApShareReceiveFragment a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.switchState(0);
            this.a.mHandler.removeCallbacks(this.a.mScanWifiTask);
        }
    };
    private i mServer;
    private List<ScanResult> mShareDeviceList = new ArrayList();
    private int mState;
    private TextView mTvCancelAll;
    private TextView mTvDeviceTop;
    private TextView[] mTvDevices;
    private TextView mTvReceiveAll;
    private TextView mTvSubTitle;
    private TextView mTvTitle;
    private j mWifiApManager;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        getActionBarController().b((int) R.string.share_receive_media);
        View inflate = layoutInflater.inflate(R.layout.apshare_receiver_main, null);
        bindView(inflate);
        this.mWifiApManager = j.a(getActivity());
        this.mWifiApManager.a(new k(this.mHandler));
        this.mWifiApManager.b("TTPODShare-");
        this.mWifiApManager.a(false);
        a.b(this.mWifiApManager.a());
        a.a(this.mWifiApManager.d());
        WifiInfo k = this.mWifiApManager.k();
        if (k != null) {
            a.c(k.getSSID());
        }
        inflate.setKeepScreenOn(true);
        return inflate;
    }

    private void bindView(View view) {
        this.mLayoutSearch = (ViewGroup) view.findViewById(R.id.layout_search);
        this.mListView = (ListView) view.findViewById(R.id.apshare_receiver_listview);
        this.mLayoutBottom = (ViewGroup) view.findViewById(R.id.layout_bottom);
        this.mTvCancelAll = (TextView) this.mLayoutBottom.findViewById(R.id.tv_cancel_all);
        this.mTvReceiveAll = (TextView) this.mLayoutBottom.findViewById(R.id.tv_receive_all);
        this.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        this.mTvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
        this.mTvDevices = new TextView[3];
        this.mTvDeviceTop = (TextView) view.findViewById(R.id.tv_device_top);
        this.mTvDevices[0] = this.mTvDeviceTop;
        this.mTvDevices[1] = (TextView) view.findViewById(R.id.tv_device_center);
        this.mTvDevices[2] = (TextView) view.findViewById(R.id.tv_device_bottom);
        this.mRescanButton = (TextView) view.findViewById(R.id.tv_rescan_wifi);
        for (TextView onClickListener : this.mTvDevices) {
            onClickListener.setOnClickListener(this.mOnClickListener);
        }
        this.mTvCancelAll.setOnClickListener(this.mOnClickListener);
        this.mTvReceiveAll.setOnClickListener(this.mOnClickListener);
        this.mTvReceiveAll.setText(getString(R.string.share_receiving));
        this.mRescanButton.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ApShareReceiveFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.mWifiApManager.i();
                this.a.switchState(1);
                this.a.mScanCount = 0;
            }
        });
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mWifiApManager.i();
        switchState(1);
        this.mScanCount = 0;
    }

    private void cancelAll() {
        if (this.mAdapter != null) {
            this.mAdapter.c();
        }
    }

    private void shutdownServer() {
        if (this.mServer != null) {
            this.mServer.a();
            this.mServer = null;
        }
    }

    private void switchState(int i) {
        if (this.mAdapter != null) {
            this.mAdapter = null;
            this.mListView.setAdapter(this.mAdapter);
        }
        this.mState = i;
        switch (this.mState) {
            case 0:
                this.mTvTitle.setText(R.string.share_searching_no_sharer);
                showScanWifiTimeoutView();
                return;
            case 1:
                showSearchView(true);
                this.mTvTitle.setText(R.string.share_searching);
                this.mTvSubTitle.setText(R.string.share_searching_subtitle);
                invisibleAllDeviceView();
                return;
            case 2:
                showSearchView(true);
                this.mTvTitle.setText(R.string.share_searched_connecting);
                this.mTvSubTitle.setText(R.string.share_receive_prompt_title);
                showSingleDevice();
                return;
            case 3:
                showSearchView(true);
                this.mTvTitle.setText(R.string.share_received_multi_device);
                this.mTvSubTitle.setText(R.string.share_receive_prompt_title);
                this.mTvDeviceTop.setBackgroundResource(R.drawable.apshare_bkg_device_enabled);
                showMultiDevice();
                return;
            case 5:
                showSearchView(false);
                return;
            default:
                return;
        }
    }

    protected void onBackPressed() {
        exitDialog();
    }

    private void exitDialog() {
        h hVar = new h(getActivity(), (int) R.string.share_receive_cancel, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ ApShareReceiveFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                if (this.a.mClient != null) {
                    this.a.mClient.a("disconnect", new ClientModel(this.a.mMyIp, Build.MODEL), new e.a(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            if (this.a.a.mWifiApManager != null) {
                                this.a.a.mWifiApManager.b("TTPODShare-");
                            }
                        }
                    });
                } else if (this.a.mWifiApManager != null) {
                    this.a.mWifiApManager.b("TTPODShare-");
                }
                this.a.mClient = null;
                a.a(this.a.mWifiApManager);
                this.a.mHandler.removeCallbacks(this.a.mScanWifiTask);
                this.a.cancelAll();
                if (this.a.mWifiApManager != null) {
                    this.a.mWifiApManager.a(a.a());
                }
                this.a.shutdownServer();
                this.a.mIsExist = true;
                this.a.finish();
            }
        }, null);
        hVar.setTitle((int) R.string.prompt_title);
        hVar.show();
    }

    private void disconnectedDialog(String str) {
        this.mIsDialogShow = true;
        if (getActivity() == null || this.mHandler == null) {
            reset();
            return;
        }
        h hVar = new h(getActivity(), str, (int) R.string.iknown, new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ ApShareReceiveFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.reset();
            }
        });
        hVar.setTitle((int) R.string.prompt_title);
        hVar.show();
    }

    private void reset() {
        this.mShareDeviceList.clear();
        this.mWifiApManager.i();
        this.mHandler.removeCallbacks(this.mConnectWifiTimeoutTask);
        this.mClient = null;
        switchState(1);
        this.mIsDialogShow = false;
        this.mIsConnected = false;
        shutdownServer();
        this.mScanCount = 0;
    }

    private void showSingleDevice() {
        invisibleAllDeviceView();
        if (this.mChooseIndex < this.mShareDeviceList.size()) {
            this.mTvDeviceTop.setVisibility(0);
            this.mTvDeviceTop.setBackgroundResource(R.drawable.apshare_bkg_device_disable);
            this.mTvDeviceTop.setEnabled(false);
            this.mTvDeviceTop.setText(((ScanResult) this.mShareDeviceList.get(this.mChooseIndex)).SSID.replace("TTPODShare-", ""));
            this.mWifiApManager.a((ScanResult) this.mShareDeviceList.get(this.mChooseIndex));
            g.d(TAG, "connect to ap: " + ((ScanResult) this.mShareDeviceList.get(this.mChooseIndex)).SSID);
            this.mState = 4;
            g.d(TAG, "add a mConnectWifiTimeoutTask");
            this.mHandler.postDelayed(this.mConnectWifiTimeoutTask, 20000);
        }
    }

    private void showScanWifiTimeoutView() {
        invisibleAllDeviceView();
        this.mRescanButton.setVisibility(0);
    }

    private void invisibleAllDeviceView() {
        for (TextView visibility : this.mTvDevices) {
            visibility.setVisibility(4);
        }
    }

    private void showMultiDevice() {
        invisibleAllDeviceView();
        int i = 0;
        while (i < this.mTvDevices.length && i < this.mShareDeviceList.size()) {
            TextView textView = this.mTvDevices[i];
            textView.setVisibility(0);
            textView.setTag(Integer.valueOf(i));
            textView.setText(((ScanResult) this.mShareDeviceList.get(i)).SSID.replace("TTPODShare-", ""));
            i++;
        }
    }

    private void showSearchView(boolean z) {
        int i;
        int i2 = 0;
        ViewGroup viewGroup = this.mLayoutSearch;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        viewGroup.setVisibility(i);
        viewGroup = this.mLayoutBottom;
        if (z) {
            i = 8;
        } else {
            i = 0;
        }
        viewGroup.setVisibility(i);
        ListView listView = this.mListView;
        if (z) {
            i2 = 8;
        }
        listView.setVisibility(i2);
        this.mRescanButton.setVisibility(8);
    }

    protected boolean needSearchAction() {
        return false;
    }

    private void getMediaItemList() {
        this.mAdapter = new c(getActivity(), this.mHandler);
        this.mListView.setAdapter(this.mAdapter);
        DhcpInfo dhcpInfo = this.mWifiApManager.j().getDhcpInfo();
        this.mMyIp = a.a(dhcpInfo.ipAddress);
        this.mClient = new e(a.a(dhcpInfo.gateway), this.mHandler, this.mAdapter);
        try {
            if (((Boolean) this.mClient.a("who_am_i", new ClientModel(this.mMyIp, Build.MODEL), null).get()).booleanValue()) {
                f.a(getString(R.string.share_receive_force_disconnect, this.mSSID));
                switchState(1);
                this.mWifiApManager.i();
                return;
            }
            this.mClient.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startDownload(Message message) {
        if (this.mClient != null) {
            a aVar = (a) message.obj;
            if (aVar == null) {
                f.a(getString(R.string.share_data_transfer_error));
                return;
            }
            aVar.c(Build.MODEL);
            aVar.d(this.mMyIp);
            this.mClient.a(aVar);
        }
    }

    private void wifiScanFinished(Message message) {
        this.mShareDeviceList = (List) message.obj;
        this.mScanCount++;
        g.d(TAG, "scan finished, size: %d, scan = %d", Integer.valueOf(this.mShareDeviceList.size()), Integer.valueOf(this.mScanCount));
        if (this.mShareDeviceList.size() == 0) {
            switchState(1);
        } else if (this.mShareDeviceList.size() == 1) {
            switchState(2);
        } else if (this.mShareDeviceList.size() > 1) {
            switchState(3);
        }
        if (this.mScanCount >= 100) {
            g.d(TAG, "add mScanWifiTimeoutTask");
            this.mHandler.post(this.mScanWifiTimeoutTask);
            this.mState = 0;
        }
        if ((this.mShareDeviceList.size() == 0 || this.mShareDeviceList.size() > 1) && this.mState != 0) {
            g.d(TAG, "rescan, state = %s, add mScanWifiTask", Integer.valueOf(this.mState));
            this.mHandler.postDelayed(this.mScanWifiTask, 4000);
        }
    }

    private void wifiConnected(Message message) {
        this.mIsConnected = true;
        switchState(5);
        this.mHandler.removeCallbacks(this.mConnectWifiTimeoutTask);
        this.mSSID = ((String) message.obj).replace("TTPODShare-", "");
        this.mTvTitle.setText(getString(R.string.share_connect_ap_success, this.mSSID));
        this.mHandler.removeCallbacks(this.mScanWifiTask);
        getMediaItemList();
        startClientSideServer();
        this.mScanCount = 0;
    }

    private void wifiDisconnected() {
        WifiInfo k = this.mWifiApManager.k();
        int i = k == null ? 0 : k.getSupplicantState() == SupplicantState.DISCONNECTED ? 1 : 0;
        if (i != 0 && this.mState == 5 && !this.mIsDialogShow && this.mIsConnected && !this.mIsExist) {
            disconnectedDialog(getString(R.string.share_receive_sharer_finish_sharing, this.mSSID));
        }
    }

    private void startClientSideServer() {
        shutdownServer();
        this.mServer = new i(new b(this) {
            final /* synthetic */ ApShareReceiveFragment a;

            {
                this.a = r1;
            }

            public void a(Socket socket) {
                try {
                    String readLine = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                    g.d(ApShareReceiveFragment.TAG, "receive message: " + readLine);
                    if (readLine.equals("finished")) {
                        this.a.mHandler.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.disconnectedDialog(this.a.a.getString(R.string.share_receive_force_disconnect, this.a.a.mSSID));
                            }
                        });
                    }
                    socket.close();
                    this.a.shutdownServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5002);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandler.removeCallbacksAndMessages(null);
    }
}
