package com.sds.android.ttpod.b;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;

/* ListViewUtils */
public class m {
    private static boolean a = false;

    /* ListViewUtils */
    public static class a implements OnScrollListener {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            Adapter adapter = absListView.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            if (adapter != null) {
                m.a(i != 0, (BaseAdapter) adapter);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }
    }

    public static int a(int i, int i2, int i3) {
        if (i < 0 || i2 < 0 || i3 < 0) {
            throw new IllegalArgumentException("headCount,srcPosition,total must be >= 0");
        }
        int i4 = i2 - i;
        return (i4 < 0 || i4 >= i3) ? -1 : i4;
    }

    public static boolean b(int i, int i2, int i3) {
        return i3 >= i2 && i3 - i <= i2;
    }

    public static void a(boolean z, BaseAdapter baseAdapter) {
        a = z;
        if (!a) {
            baseAdapter.notifyDataSetChanged();
        }
    }

    public static boolean a(ImageView imageView, int i) {
        if (!a) {
            return false;
        }
        if (i > 0) {
            imageView.setImageResource(i);
        }
        imageView.setTag(" ");
        return true;
    }

    public static boolean a(final ActionExpandableListView actionExpandableListView) {
        return actionExpandableListView != null && actionExpandableListView.postDelayed(new Runnable() {
            public void run() {
                actionExpandableListView.f();
            }
        }, 200);
    }
}
