package com.sds.android.ttpod.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.a;

public abstract class BaseGuideFragment extends DialogFragment {
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;

    protected abstract View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    public Dialog onCreateDialog(Bundle bundle) {
        setStyle(2, 16973840);
        return new Dialog(getActivity(), getTheme());
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSlidingClosableRelativeLayout = new SlidingClosableRelativeLayout(getActivity());
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(3);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(new a(this) {
            final /* synthetic */ BaseGuideFragment a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.mSlidingClosableRelativeLayout != null) {
                    this.a.mSlidingClosableRelativeLayout.setVisibility(8);
                }
                FragmentManager fragmentManager = this.a.getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().remove(this.a).commitAllowingStateLoss();
                }
            }
        });
        this.mSlidingClosableRelativeLayout.addView(onCreateContentView(layoutInflater, viewGroup, bundle), new LayoutParams(-1, -1));
        return this.mSlidingClosableRelativeLayout;
    }

    public void setSlidingCloseMode(int i) {
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(i);
    }

    public void setSlidingEnableScrollingMask(boolean z) {
        this.mSlidingClosableRelativeLayout.setEnableScrollingMask(z);
    }

    public void show(FragmentManager fragmentManager, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add((Fragment) this, str);
        beginTransaction.commitAllowingStateLoss();
    }

    public int show(FragmentTransaction fragmentTransaction, String str) {
        fragmentTransaction.add((Fragment) this, str);
        return fragmentTransaction.commitAllowingStateLoss();
    }
}
