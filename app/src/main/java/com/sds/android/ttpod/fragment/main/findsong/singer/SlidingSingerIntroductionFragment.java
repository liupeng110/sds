package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult;
import com.sds.android.cloudapi.ttpod.result.SingerDetailResult.SingerDetailData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;

public class SlidingSingerIntroductionFragment extends SlidingClosableFragment {
    private SingerDetailResult mResult;
    private SingerIntroductionFragment mSingerIntroductionFragment;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_singer_info");
        initIntroductionData();
    }

    private void initIntroductionData() {
        Bundle arguments = getArguments();
        this.mResult = (SingerDetailResult) arguments.getSerializable(SingerDetailFragment.KEY_SINGER_INFO);
        this.mSingerIntroductionFragment = new SingerIntroductionFragment(this.mResult);
        updateAlibabaProperty("singer_id", arguments.getString(SingerDetailFragment.KEY_SINGER_ID));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mSingerIntroductionFragment, null).commitAllowingStateLoss();
        SingerDetailData data = this.mResult.getData();
        if (data != null) {
            getActionBarController().a(data.getName());
        }
        return inflate;
    }
}
