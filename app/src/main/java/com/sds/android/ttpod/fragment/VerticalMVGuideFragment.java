package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.k;

public class VerticalMVGuideFragment extends BaseGuideFragment {
    private ImageView mGuideImage;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_mv_vertival_guide, null, false);
        this.mGuideImage = (ImageView) inflate.findViewById(R.id.image_vertical_guide);
        inflate.findViewById(R.id.mv_guide_vertical_ok).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VerticalMVGuideFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        k.a(this.mGuideImage);
    }
}
