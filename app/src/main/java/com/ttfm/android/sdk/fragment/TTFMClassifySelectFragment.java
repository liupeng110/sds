package com.ttfm.android.sdk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.a;
import com.sds.android.ttpod.widget.StateView.b;
import com.ttfm.android.sdk.core.TTFMBroadcast;
import com.ttfm.android.sdk.core.TTFMSDK;
import com.ttfm.android.sdk.entity.ClassifyEntity;
import com.ttfm.android.sdk.entity.ClassifyEntity.ClassifyInfos;
import com.ttfm.android.sdk.entity.ClassifyEntity.ClassifyLabelInfo;
import com.ttfm.android.sdk.entity.ClassifyResult;
import com.ttfm.android.sdk.storage.ChannelListDB;
import com.ttfm.android.sdk.utils.TTFMUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class TTFMClassifySelectFragment extends SlidingClosableFragment {
    private ClassifyResult mClassifyResult = null;
    protected Context mContext;
    private ClassifyLabelInfo mCurClassify;
    private MyHandler mHandler = new MyHandler(this);
    private View mPreSelectedCategoryView;
    private View mRootView;
    private long mSelectionId;
    private StateView mStateView;

    public static class MyHandler extends Handler {
        public static final int MESSAGE_FAILED = 2;
        public static final int MESSAGE_NODATA = 3;
        public static final int MESSAGE_SUCCESS = 1;
        private WeakReference<TTFMClassifySelectFragment> mBaseFragmentWeakReference;

        public MyHandler(TTFMClassifySelectFragment tTFMClassifySelectFragment) {
            this.mBaseFragmentWeakReference = new WeakReference(tTFMClassifySelectFragment);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            TTFMClassifySelectFragment tTFMClassifySelectFragment = (TTFMClassifySelectFragment) this.mBaseFragmentWeakReference.get();
            if (tTFMClassifySelectFragment != null) {
                switch (i) {
                    case 1:
                        tTFMClassifySelectFragment.onSuccess();
                        return;
                    case 2:
                        tTFMClassifySelectFragment.onFailed();
                        return;
                    case 3:
                        tTFMClassifySelectFragment.onNoData();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private final class SelectCategoryGridAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;
        private ArrayList<ClassifyLabelInfo> mList = new ArrayList();

        class ViewHolder {
            private View mBackgroundView;
            private TextView mNameView;

            ViewHolder(View view) {
                this.mNameView = (TextView) view.findViewById(R.id.mv_category_item);
                this.mBackgroundView = view.findViewById(R.id.mv_category_item_background);
            }
        }

        public SelectCategoryGridAdapter(Context context, ArrayList<ClassifyLabelInfo> arrayList) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
            this.mList.addAll(arrayList);
        }

        public int getCount() {
            return this.mList.size();
        }

        public Object getItem(int i) {
            return this.mList.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(R.layout.layout_mv_category_grid_item, viewGroup, false);
                view.setTag(new ViewHolder(view));
            }
            bindView((ClassifyLabelInfo) getItem(i), (ViewHolder) view.getTag());
            return view;
        }

        private void bindView(final ClassifyLabelInfo classifyLabelInfo, final ViewHolder viewHolder) {
            if (classifyLabelInfo != null) {
                viewHolder.mNameView.setText(classifyLabelInfo.getLiName());
                if (classifyLabelInfo.getLiId() == TTFMClassifySelectFragment.this.mSelectionId) {
                    viewHolder.mBackgroundView.setSelected(true);
                }
            }
            viewHolder.mBackgroundView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TTFMClassifySelectFragment.this.mSelectionId = classifyLabelInfo.getLiId();
                    if (TTFMClassifySelectFragment.this.mPreSelectedCategoryView != null) {
                        TTFMClassifySelectFragment.this.mPreSelectedCategoryView.setSelected(false);
                    }
                    viewHolder.mBackgroundView.setSelected(true);
                    TTFMClassifySelectFragment.this.mPreSelectedCategoryView = viewHolder.mBackgroundView;
                    TTFMBroadcast.notifyClassifyChanged(SelectCategoryGridAdapter.this.mContext, classifyLabelInfo);
                    TTFMClassifySelectFragment.this.finish();
                }
            });
        }
    }

    public TTFMClassifySelectFragment(ClassifyLabelInfo classifyLabelInfo) {
        this.mCurClassify = classifyLabelInfo;
        this.mSelectionId = classifyLabelInfo != null ? classifyLabelInfo.getLiId() : -1;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.ttfm_fragment_select_classify, viewGroup, false);
        View findViewById = this.mRootView.findViewById(R.id.select_all_container);
        if (this.mSelectionId == -1) {
            findViewById.setSelected(true);
        }
        findViewById.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                TTFMBroadcast.notifyClassifyChanged(TTFMClassifySelectFragment.this.mContext, null);
                TTFMClassifySelectFragment.this.finish();
            }
        });
        this.mStateView = (StateView) this.mRootView.findViewById(R.id.ttfm_stateview);
        this.mStateView.setState(b.LOADING);
        this.mStateView.setOnRetryRequestListener(new a() {
            public void onRetryRequested() {
                TTFMClassifySelectFragment.this.onRetryRequested();
            }
        });
        return this.mRootView;
    }

    private void onRetryRequested() {
        this.mStateView.setState(b.LOADING);
        sendRequestMessage();
    }

    protected final void sendRequestMessage() {
        com.sds.android.sdk.lib.e.a.a(new Runnable() {
            public void run() {
                if (TTFMClassifySelectFragment.this.onRequestData() == null) {
                    TTFMClassifySelectFragment.this.mHandler.sendMessage(TTFMClassifySelectFragment.this.mHandler.obtainMessage(2));
                }
            }
        });
    }

    protected ClassifyEntity onRequestData() {
        ClassifyEntity classifyEntity = null;
        String channelClassifysList = ChannelListDB.getChannelClassifysList(this.mContext);
        if (channelClassifysList != null) {
            classifyEntity = parseClassEntity(channelClassifysList);
        }
        if (classifyEntity != null) {
            return classifyEntity;
        }
        channelClassifysList = TTFMSDK.getInstance().getChannelClassify(TTFMUtils.getLoginUserId());
        if (channelClassifysList == null) {
            return classifyEntity;
        }
        ChannelListDB.saveChannelClassifysList(this.mContext, channelClassifysList);
        return parseClassEntity(channelClassifysList);
    }

    private ClassifyEntity parseClassEntity(String str) {
        this.mClassifyResult = (ClassifyResult) f.a(str, ClassifyResult.class);
        ClassifyEntity classifyEntity = null;
        if (this.mClassifyResult != null && this.mClassifyResult.isSuccess()) {
            this.mClassifyResult.setAllLabelArrayList();
            this.mClassifyResult.setAllLabelCount();
            classifyEntity = this.mClassifyResult.getData();
            if (classifyEntity != null) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
            }
        }
        return classifyEntity;
    }

    protected void onSuccess() {
        this.mStateView.setState(b.SUCCESS);
        this.mStateView.setVisibility(8);
        addLabalViews(this.mClassifyResult);
    }

    protected void onFailed() {
        this.mStateView.setState(b.FAILED);
    }

    protected void onNoData() {
        this.mStateView.setState(b.NO_DATA);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        sendRequestMessage();
    }

    public void addLabalViews(ClassifyResult classifyResult) {
        ViewGroup viewGroup = (ViewGroup) this.mRootView.findViewById(R.id.mv_category_container);
        viewGroup.removeAllViews();
        Iterator it = classifyResult.getData().getClassify().iterator();
        while (it.hasNext()) {
            viewGroup.addView(createClassifyView((ClassifyInfos) it.next()));
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
    }

    private View createClassifyView(ClassifyInfos classifyInfos) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_mv_select_category, null);
        ((TextView) inflate.findViewById(R.id.select_dimension_name)).setText(classifyInfos.getSliName());
        ((GridView) inflate.findViewById(R.id.select_dimension_grid)).setAdapter(new SelectCategoryGridAdapter(this.mContext, classifyInfos.getLabelInfos()));
        return inflate;
    }
}
