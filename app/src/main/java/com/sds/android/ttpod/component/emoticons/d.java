package com.sds.android.ttpod.component.emoticons;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import java.util.List;

/* EmoticonsPagerAdapter */
public class d extends PagerAdapter {
    private List<View> a;

    public d(List<View> list) {
        this.a = list;
    }

    public int getCount() {
        return this.a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public void destroyItem(View view, int i, Object obj) {
        ((ViewPager) view).removeView((View) this.a.get(i));
    }

    public Object instantiateItem(View view, int i) {
        ((ViewPager) view).addView((View) this.a.get(i));
        return this.a.get(i);
    }
}
