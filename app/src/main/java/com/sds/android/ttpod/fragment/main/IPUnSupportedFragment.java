package com.sds.android.ttpod.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseFragment;

public class IPUnSupportedFragment extends BaseFragment {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_ip_support, viewGroup, false);
    }
}
