package com.ttfm.android.sdk.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class TTFMFragmentPagerAdapter extends FragmentPagerAdapter {
    private int mCurrentPage;
    private final FragmentActivity mFragmentActivity;
    private final SparseArray<SoftReference<Fragment>> mFragmentArray = new SparseArray();
    private final List<Holder> mHolderList = new ArrayList();

    private static final class Holder {
        String mClassName;
        Bundle mParams;

        private Holder() {
        }
    }

    public TTFMFragmentPagerAdapter(FragmentActivity fragmentActivity, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentActivity = fragmentActivity;
    }

    public void add(Class<? extends Fragment> cls, Bundle bundle) {
        Holder holder = new Holder();
        holder.mClassName = cls.getName();
        holder.mParams = bundle;
        this.mHolderList.add(this.mHolderList.size(), holder);
        notifyDataSetChanged();
    }

    public void delete(int i) {
        try {
            this.mHolderList.remove(i);
            if (this.mFragmentArray.size() > i) {
                SoftReference softReference = (SoftReference) this.mFragmentArray.get(i);
                if (softReference != null) {
                    softReference.clear();
                }
                this.mFragmentArray.remove(i);
            }
            notifyDataSetChanged();
        } catch (Exception e) {
        }
    }

    public Fragment getFragment(int i) {
        SoftReference softReference = (SoftReference) this.mFragmentArray.get(i);
        if (softReference == null || softReference.get() == null) {
            return getItem(i);
        }
        return (Fragment) softReference.get();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        SoftReference softReference = (SoftReference) this.mFragmentArray.get(i);
        if (softReference != null) {
            softReference.clear();
        }
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.mFragmentArray.put(i, new SoftReference(fragment));
        return fragment;
    }

    public Fragment getItem(int i) {
        Holder holder = (Holder) this.mHolderList.get(i);
        return Fragment.instantiate(this.mFragmentActivity, holder.mClassName, holder.mParams);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
    }

    public int getCount() {
        return this.mHolderList.size();
    }

    public int getCurrentPage() {
        return this.mCurrentPage;
    }

    protected void setCurrentPage(int i) {
        this.mCurrentPage = i;
    }
}
