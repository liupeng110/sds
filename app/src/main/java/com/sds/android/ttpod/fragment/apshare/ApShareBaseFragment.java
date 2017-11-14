package com.sds.android.ttpod.fragment.apshare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.audioeffect.a;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;

public class ApShareBaseFragment extends ActionBarFragment {
    public void onThemeLoaded() {
        getRootView().setBackgroundResource(R.drawable.apshare_whole_bkg);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        a.b(getActionBarController());
        return null;
    }

    protected boolean needSearchAction() {
        return false;
    }
}
