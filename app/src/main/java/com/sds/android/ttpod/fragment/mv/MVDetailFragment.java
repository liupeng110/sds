package com.sds.android.ttpod.fragment.mv;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.StringResult;
import com.sds.android.cloudapi.ttpod.result.MVResult;
import com.sds.android.cloudapi.ttpod.result.SingerMvResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mv.b;
import com.sds.android.ttpod.fragment.main.findsong.singer.c;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MVDetailFragment extends BaseFragment {
    public static final String KEY_PLAY_MV_DATA = "play_mv_data";
    private a mDetailViewHolder;
    private String mMVId;
    private c mMVListAdapter;
    private b mMVShareListener;
    private List<MvData> mMvDataList = new ArrayList();
    private ListView mMvListView;
    private com.sds.android.ttpod.activities.mv.c mPlayData;
    private int mRecommendType = 0;
    private SingerMvResult mResult;
    private StateView mStateView;

    public static MVDetailFragment instate(com.sds.android.ttpod.activities.mv.c cVar) {
        Bundle bundle = new Bundle();
        if (cVar != null) {
            bundle.putSerializable(KEY_PLAY_MV_DATA, cVar);
        }
        MVDetailFragment mVDetailFragment = new MVDetailFragment();
        mVDetailFragment.setArguments(bundle);
        return mVDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mPlayData = (com.sds.android.ttpod.activities.mv.c) arguments.getSerializable(KEY_PLAY_MV_DATA);
            if (this.mPlayData != null) {
                this.mMVId = String.valueOf(this.mPlayData.e());
                this.mRecommendType = this.mPlayData.b().getRecommendType();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("getArguments() KEY_PLAY_MV_DATA must not be null");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_mv_detail, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mMvListView = (ListView) view.findViewById(R.id.list_view);
        ViewCompat.setOverScrollMode(this.mMvListView, 2);
        this.mStateView = (StateView) view.findViewById(R.id.state_view);
        this.mStateView.setNodataView(onCreateNodataView(LayoutInflater.from(getActivity())));
        this.mStateView.setOnRetryRequestListener(new a(this) {
            final /* synthetic */ MVDetailFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestSingerMvResult();
            }
        });
        ((MarginLayoutParams) view.getLayoutParams()).bottomMargin = 0;
        this.mDetailViewHolder = new a(getActivity(), this.mPlayData, view, this.mMvListView);
        this.mMvListView.addHeaderView(this.mDetailViewHolder.a());
        this.mDetailViewHolder.a(this.mMVShareListener);
        this.mMVListAdapter = new c(getActivity());
        this.mMVListAdapter.a(false);
        this.mMvListView.setAdapter(this.mMVListAdapter);
        this.mMvListView.setOnItemClickListener(new b(getActivity(), "mv_recommend"));
        requestSingerMvResult();
    }

    protected View onCreateNodataView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.loadingview_nodata, null);
        TextView textView = (TextView) inflate.findViewById(R.id.textview_load_failed);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MVDetailFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestSingerMvResult();
            }
        });
        if (textView != null) {
            textView.setText(R.string.not_mv);
        }
        return inflate;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SIMILAR_MV_LIST, i.a(getClass(), "updateSimilarMVResult", SingerMvResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MV_INFO, i.a(getClass(), "updateMVInfo", MVResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PRAISE_OR_STEP_MV, i.a(getClass(), "praiseOrStepMV", StringResult.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mResult != null) {
            updateSingerMvList(this.mResult);
        }
    }

    private void requestSingerMvResult() {
        this.mStateView.setState(StateView.b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_MV_INFO, this.mMVId));
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SIMILAR_MV_LIST, this.mMVId));
    }

    public void updateSimilarMVResult(SingerMvResult singerMvResult) {
        this.mResult = singerMvResult;
        updateSingerMvList(singerMvResult);
    }

    public void updateMVInfo(MVResult mVResult) {
        if (mVResult != null && mVResult.isSuccess()) {
            MvData mVData = mVResult.getMVData();
            mVData.setRecommendType(this.mRecommendType);
            this.mDetailViewHolder.a(mVData);
        }
    }

    public void praiseOrStepMV(StringResult stringResult) {
        this.mDetailViewHolder.a(stringResult);
    }

    public void updateSingerMvList(SingerMvResult singerMvResult) {
        boolean z = true;
        if (singerMvResult != null && isViewAccessAble()) {
            boolean z2;
            a aVar = this.mDetailViewHolder;
            if (j.a(singerMvResult.getMvDataList())) {
                z2 = false;
            } else {
                z2 = true;
            }
            aVar.a(z2);
            a aVar2 = this.mDetailViewHolder;
            if (j.a(singerMvResult.getMvDataList())) {
                z = false;
            }
            aVar2.b(z);
            this.mStateView.setVisibility(8);
            this.mMvDataList.addAll(singerMvResult.getMvDataList());
            this.mMVListAdapter.a(this.mMvDataList);
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mMvListView.setBackgroundColor(-1);
    }

    public void setMVShareListener(b bVar) {
        this.mMVShareListener = bVar;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDetailViewHolder.b();
    }
}
