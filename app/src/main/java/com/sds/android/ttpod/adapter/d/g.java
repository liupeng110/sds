package com.sds.android.ttpod.adapter.d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a;
import java.util.List;

/* StarCategoryAdapter */
public class g extends a<StarCategory> {
    public g(Context context, List<StarCategory> list) {
        super(context, list);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_star_category_item, null, false);
        inflate.setTag(inflate.findViewById(R.id.category_title));
        return inflate;
    }

    protected void a(View view, StarCategory starCategory, int i) {
        ((TextView) view.getTag()).setText(starCategory.getName());
    }
}
