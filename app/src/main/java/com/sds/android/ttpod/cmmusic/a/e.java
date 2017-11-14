package com.sds.android.ttpod.cmmusic.a;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/* TabPagerAdapter */
public class e extends FragmentStatePagerAdapter {
    private List<Fragment> a;

    public e(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.a = list;
    }

    public Fragment getItem(int i) {
        return (Fragment) this.a.get(i);
    }

    public int getCount() {
        return this.a.size();
    }
}
