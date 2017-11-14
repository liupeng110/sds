package com.sds.android.ttpod.activities.ktv;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.List;

/* KtvMediaItemViewHolder */
public class d extends g implements OnClickListener {
    private Button a;
    private Context b;
    private MediaItem c;
    private View d;

    public d(View view) {
        super(view);
        this.b = view.getContext();
        this.d = view.findViewById(R.id.menu_view);
        this.a = (Button) view.findViewById(R.id.button_ktv);
        this.a.setOnClickListener(this);
    }

    protected void a(boolean z) {
        if (z) {
            this.a.setVisibility(0);
            this.d.setVisibility(8);
            return;
        }
        this.a.setVisibility(8);
        this.d.setVisibility(0);
    }

    public void a(MediaItem mediaItem) {
        super.a(mediaItem);
        this.c = mediaItem;
    }

    public void onClick(View view) {
        if (this.c != null && this.b != null) {
            List arrayList = new ArrayList();
            arrayList.add(new g(this.c.getTitle(), this.c.getArtist()));
            f.a().a(this.b, arrayList);
        }
    }
}
