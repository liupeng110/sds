package com.ttfm.android.sdk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.a;
import com.sds.android.ttpod.widget.StateView.b;
import com.ttfm.android.sdk.utils.FastDoubleClick;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TTFMBaseListFragment extends BaseFragment implements OnScrollListener {
    protected Context mContext;
    private MyHandler mHandler = new MyHandler(this);
    protected ListView mListView = null;
    protected StateView mStateView;

    public static class MyHandler extends Handler {
        public static final int MESSAGE_FAILED = 2;
        public static final int MESSAGE_NODATA = 3;
        public static final int MESSAGE_SUCCESS = 1;
        private WeakReference<TTFMBaseListFragment> mListBaseFragmentWeakReference;

        public MyHandler(TTFMBaseListFragment tTFMBaseListFragment) {
            this.mListBaseFragmentWeakReference = new WeakReference(tTFMBaseListFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            TTFMBaseListFragment tTFMBaseListFragment = (TTFMBaseListFragment) this.mListBaseFragmentWeakReference.get();
            if (tTFMBaseListFragment != null) {
                switch (i) {
                    case 1:
                        tTFMBaseListFragment.onSuccess((List) message.obj);
                        return;
                    case 2:
                        tTFMBaseListFragment.onFailed();
                        return;
                    case 3:
                        tTFMBaseListFragment.onNoData();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    public View initCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_ttfm_base_list_view, viewGroup, false);
        this.mListView = (ListView) inflate.findViewById(R.id.ttfm_listview);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!FastDoubleClick.isFastDoubleClick()) {
                    TTFMBaseListFragment.this.onListItemClick(adapterView, view, i, j);
                }
            }
        });
        this.mListView.setOnScrollListener(this);
        this.mStateView = (StateView) inflate.findViewById(R.id.ttfm_stateview);
        this.mStateView.setState(b.LOADING);
        this.mStateView.setOnRetryRequestListener(new a() {
            public void onRetryRequested() {
                TTFMBaseListFragment.this.onRetryRequested();
            }
        });
        return inflate;
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        sendRequestMessage();
    }

    private void onRetryRequested() {
        this.mStateView.setState(b.LOADING);
        sendRequestMessage();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
    }

    protected void sendRequestMessage() {
        com.sds.android.sdk.lib.e.a.a(new Runnable() {
            public void run() {
                List onRequestData = TTFMBaseListFragment.this.onRequestData();
                if (onRequestData == null || onRequestData.size() == 0) {
                    TTFMBaseListFragment.this.mHandler.sendMessage(TTFMBaseListFragment.this.mHandler.obtainMessage(2));
                    return;
                }
                Message obtainMessage = TTFMBaseListFragment.this.mHandler.obtainMessage(1);
                obtainMessage.obj = onRequestData;
                TTFMBaseListFragment.this.mHandler.sendMessage(obtainMessage);
            }
        });
    }

    public void onDestroyView() {
        this.mStateView.setOnRetryRequestListener(null);
        this.mListView.setOnItemClickListener(null);
        this.mListView.setOnScrollListener(null);
        super.onDestroyView();
    }

    protected List<?> onRequestData() {
        return new ArrayList();
    }

    protected void onSuccess(List list) {
        this.mStateView.setState(b.SUCCESS);
        this.mStateView.setVisibility(8);
    }

    protected void onFailed() {
        this.mStateView.setState(b.FAILED);
    }

    protected void onNoData() {
        this.mStateView.setState(b.NO_DATA);
    }

    public void onListItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
