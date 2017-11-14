package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class AvatarFrameView extends RelativeLayout {
    private ImageView a = ((ImageView) findViewById(R.id.image_avatar));
    private ImageView b = ((ImageView) findViewById(R.id.image_avatar_frame));
    private ImageView c = ((ImageView) findViewById(R.id.image_avatar_mask));

    public AvatarFrameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_avatar_frame, this);
    }

    public ImageView getAvatarView() {
        return this.a;
    }

    public int getMaxChildHeight() {
        return Math.max(Math.max(v.a(c.b(ThemeElement.SETTING_AVATAR_FRAME), 0), v.a(c.b(ThemeElement.SETTING_AVATAR_MASK), 0)), (int) getResources().getDimension(R.dimen.avatar_frame_height));
    }
}
