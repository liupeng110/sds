package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;

/* ListPopupWindow */
public class b extends PopupWindow {
    private static ArrayList<b> b = new ArrayList();
    private ListView a;

    public void showAtLocation(View view, int i, int i2, int i3) {
        if (view != null) {
            b.add(this);
            super.showAtLocation(view, i, i2, i3);
        }
    }

    public void showAsDropDown(View view, int i, int i2) {
        b.add(this);
        super.showAsDropDown(view, i, i2);
    }

    public void dismiss() {
        b.remove(this);
        super.dismiss();
    }

    public b(Context context, int i, int i2) {
        super(View.inflate(context, i, null), -2, -2, true);
        setWindowLayoutMode(0, -2);
        setAnimationStyle(16973826);
        setBackgroundDrawable(new ColorDrawable(0));
        View contentView = getContentView();
        if (contentView != null) {
            this.a = (ListView) contentView.findViewById(i2);
            contentView.setFocusableInTouchMode(true);
            contentView.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if (i != 4 && i != 82) {
                        return false;
                    }
                    if (keyEvent.getAction() != 1) {
                        return true;
                    }
                    this.a.dismiss();
                    return true;
                }
            });
        }
    }

    public void a(ListAdapter listAdapter) {
        this.a.setAdapter(listAdapter);
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.a.setOnItemClickListener(onItemClickListener);
    }
}
