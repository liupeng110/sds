package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

/* MusicCircleListFooter */
public class b implements com.sds.android.ttpod.framework.modules.theme.c.b {
    private View a;
    private TextView b = ((TextView) this.a.findViewById(R.id.text_rear_content));
    private ImageView c = ((ImageView) this.a.findViewById(R.id.icon_progressing));
    private Animation d;

    public b(LayoutInflater layoutInflater, OnClickListener onClickListener) {
        this.a = layoutInflater.inflate(R.layout.musiccircle_list_footer, null, false);
        this.a.setOnClickListener(onClickListener);
    }

    public void onThemeLoaded() {
        c.a(this.b, ThemeElement.COMMON_CONTENT_TEXT);
        c.a(this.a.findViewById(R.id.layout_root), ThemeElement.BACKGROUND_MASK);
    }

    public View a() {
        return this.a;
    }

    public void a(boolean z, int i, String str) {
        this.a.setEnabled(z);
        this.b.setText(str);
        if (this.a.getVisibility() == 8) {
            this.a.setVisibility(0);
        }
        this.c.setVisibility(i);
        if (i == 0) {
            this.c.setAnimation(a(this.c.getContext()));
        } else {
            this.c.clearAnimation();
        }
    }

    private Animation a(Context context) {
        if (this.d == null) {
            this.d = AnimationUtils.loadAnimation(context, R.anim.unlimited_rotate);
        }
        return this.d;
    }
}
