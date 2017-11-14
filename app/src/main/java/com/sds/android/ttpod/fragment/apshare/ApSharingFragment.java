package com.sds.android.ttpod.fragment.apshare;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a.d;
import com.sds.android.ttpod.adapter.a.d.a;
import com.sds.android.ttpod.component.apshare.ClientModel;
import com.sds.android.ttpod.component.apshare.b;
import com.sds.android.ttpod.component.apshare.g;
import com.sds.android.ttpod.component.apshare.j;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.StateView;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ApSharingFragment extends ApShareBaseFragment implements a, b {
    private static final int BACK_PRESS_DELAY = 200;
    private static final String TAG = "ApSharingFragment";
    private d mAdapter;
    private List<String> mClientBlackList;
    private Map<String, String> mClients;
    private Map<String, Integer> mDownloadCount;
    private ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private ListView mListView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ ApSharingFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.mTvStopShare) {
                this.a.exitDialog();
            } else if (view != this.a.mTvConnected) {
            } else {
                if (this.a.mClients.size() > 0) {
                    this.a.popConnectedDeviceDialog();
                } else {
                    f.a((int) R.string.share_prompt_no_connected_device);
                }
            }
        }
    };
    private g mServer;
    private ArrayList<MediaItem> mSharedList;
    private StateView mStateView;
    private TextView mTvConnected;
    private TextView mTvStopShare;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    private long mViewCreateTime;
    private j mWifiApManager;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        getActionBarController().b((int) R.string.share_sharing);
        View inflate = layoutInflater.inflate(R.layout.apshare_sharing_main, null);
        this.mStateView = (StateView) inflate.findViewById(R.id.stateview);
        this.mStateView.setState(StateView.b.NO_DATA);
        this.mListView = (ListView) this.mStateView.findViewById(R.id.apshare_sharing_listview);
        this.mTvStopShare = (TextView) inflate.findViewById(R.id.tv_stop_share);
        this.mTvConnected = (TextView) inflate.findViewById(R.id.tv_connected);
        this.mAdapter = new d(layoutInflater.getContext());
        this.mListView.setAdapter(this.mAdapter);
        this.mTvStopShare.setOnClickListener(this.mOnClickListener);
        this.mTvConnected.setText(getString(R.string.share_connected_device, Integer.valueOf(0)));
        this.mTvConnected.setOnClickListener(this.mOnClickListener);
        this.mWifiApManager = j.a(getActivity());
        this.mWifiApManager.a(false);
        this.mClients = new HashMap();
        this.mClientBlackList = new ArrayList();
        this.mDownloadCount = new HashMap();
        this.mAdapter.a(this);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSharedList = getArguments().getParcelableArrayList("key_data");
        this.mTvStopShare.setText(getString(R.string.share_stop, Integer.valueOf(this.mSharedList.size())));
        this.mServer = new g(this.mSharedList, this.mAdapter, this, 5001);
        this.mTvStopShare.setText(getString(R.string.share_stop, Integer.valueOf(this.mSharedList.size())));
        this.mViewCreateTime = SystemClock.currentThreadTimeMillis();
    }

    protected boolean needSearchAction() {
        return false;
    }

    protected void onBackPressed() {
        exitDialog();
    }

    private void exitDialog() {
        if (SystemClock.currentThreadTimeMillis() - this.mViewCreateTime >= 200) {
            Context activity = getActivity();
            if (activity != null) {
                h hVar = new h(activity, (int) R.string.share_prompt_cancel, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                    final /* synthetic */ ApSharingFragment a;

                    {
                        this.a = r1;
                    }

                    public void a(h hVar) {
                        com.sds.android.sdk.lib.e.a.a(this.a, new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (this.a.a.mWifiApManager != null && this.a.a.mWifiApManager.c()) {
                                    this.a.a.mWifiApManager.f();
                                }
                                if (this.a.a.mServer != null) {
                                    this.a.a.mServer.a();
                                }
                                com.sds.android.ttpod.component.apshare.a.a(this.a.a.mWifiApManager);
                                if (this.a.a.mWifiApManager != null) {
                                    this.a.a.mWifiApManager.a(com.sds.android.ttpod.component.apshare.a.a());
                                }
                            }
                        });
                        this.a.getActivity().finish();
                    }
                }, null);
                hVar.setTitle((int) R.string.prompt_title);
                hVar.show();
            }
        }
    }

    private void popConnectedDeviceDialog() {
        List arrayList = new ArrayList();
        for (String str : this.mClients.keySet()) {
            com.sds.android.ttpod.component.b.a aVar = new com.sds.android.ttpod.component.b.a(0, 0, (CharSequence) this.mClients.get(str), "下载了" + this.mDownloadCount.get(str) + "首");
            aVar.a(new ClientModel(str, (String) this.mClients.get(str)));
            arrayList.add(aVar);
        }
        com.sds.android.ttpod.component.d.a.a aVar2 = new com.sds.android.ttpod.component.d.a.a(getActivity(), arrayList, new com.sds.android.ttpod.component.d.a.a.a(this) {
            final /* synthetic */ ApSharingFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar) {
                this.a.finishConnections((ClientModel) aVar.h());
            }
        }, null, null);
        aVar2.b(R.string.cancel, null);
        aVar2.setTitle((int) R.string.share_dlg_connected_device);
        aVar2.show();
    }

    private Future<Boolean> finishConnections(final ClientModel clientModel) {
        return this.mExecutor.submit(new Callable<Boolean>(this) {
            final /* synthetic */ ApSharingFragment b;

            public /* synthetic */ Object call() throws Exception {
                return a();
            }

            public Boolean a() throws Exception {
                try {
                    final com.sds.android.ttpod.component.apshare.h hVar = new com.sds.android.ttpod.component.apshare.h(clientModel.a(), 5002);
                    this.b.mServer.a(this.b.mClientBlackList);
                    hVar.a(new com.sds.android.ttpod.component.apshare.h.b(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public void a(OutputStream outputStream) {
                            PrintWriter printWriter = new PrintWriter(outputStream);
                            printWriter.print("finished\r\n");
                            printWriter.flush();
                            hVar.b();
                        }
                    });
                    this.b.mClients.remove(clientModel.a());
                    this.b.mClientBlackList.add(clientModel.a());
                    com.sds.android.sdk.lib.util.g.d(ApSharingFragment.TAG, "size of clients = " + this.b.mClients.size() + ", black list size = " + this.b.mClientBlackList.size());
                    this.b.mUIHandler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (this.a.b.mTvConnected != null) {
                                f.a(this.a.b.getString(R.string.share_add_to_blacklist, clientModel.b()));
                                this.a.b.mTvConnected.setText(this.a.b.getString(R.string.share_connected_device, Integer.valueOf(this.a.b.mClients.size())));
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    f.a((int) R.string.share_finish_connection_error);
                }
                return Boolean.valueOf(true);
            }
        });
    }

    public void onConnected(ClientModel clientModel) {
        if (getActivity() != null && this.mClients != null) {
            this.mClients.put(clientModel.a(), clientModel.b());
            com.sds.android.sdk.lib.util.g.d(TAG, "add a client: " + clientModel.b() + ", " + clientModel.a() + ", size=" + this.mClients.size());
            f.a(getString(R.string.share_add_a_client, clientModel.b()));
            this.mDownloadCount.put(clientModel.a(), Integer.valueOf(0));
            this.mTvConnected.setText(getString(R.string.share_connected_device, Integer.valueOf(this.mClients.size())));
        }
    }

    public void onDisconnected(ClientModel clientModel) {
        if (getActivity() != null && this.mClients != null) {
            this.mClients.remove(clientModel.a());
            this.mTvConnected.setText(getString(R.string.share_connected_device, Integer.valueOf(this.mClients.size())));
            com.sds.android.sdk.lib.util.g.d(TAG, clientModel.b() + " is leave , ip = " + clientModel.a() + ", size =" + this.mClients.size());
        }
    }

    public void onTransmitBegin(a aVar) {
        this.mStateView.setState(StateView.b.SUCCESS);
    }

    public void onTransmitComplete(a aVar) {
        if (!this.mDownloadCount.containsKey(aVar.e())) {
            this.mDownloadCount.put(aVar.e(), Integer.valueOf(0));
        }
        this.mDownloadCount.put(aVar.e(), Integer.valueOf(((Integer) this.mDownloadCount.get(aVar.e())).intValue() + 1));
    }
}
