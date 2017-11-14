package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.f.d;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import java.util.ArrayList;

/* RecommendPostListAdapter */
public abstract class c extends a<RecommendPost> {
    protected abstract void a(RecommendPost recommendPost, d dVar, int i);

    public c(Context context) {
        super(context, new ArrayList());
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.recommend_post_list_item, null, false);
        inflate.setTag(new d(inflate));
        return inflate;
    }

    protected final void a(View view, RecommendPost recommendPost, int i) {
        d dVar = (d) view.getTag();
        a(dVar);
        dVar.a().setText(recommendPost.getAuthor());
        a(dVar, recommendPost.getListenCount());
        a(dVar.e(), recommendPost);
        a(dVar.f(), recommendPost.getReason());
        a(dVar, recommendPost, i);
        a(recommendPost, dVar.h());
    }

    private void a(d dVar, String str) {
        if (m.a(str)) {
            dVar.c().setVisibility(8);
            return;
        }
        dVar.c().setVisibility(0);
        dVar.b().setText(str);
    }

    private void a(TextView textView, RecommendPost recommendPost) {
        long time = recommendPost.getTime();
        textView.setText(time > 0 ? w.a(a(), time) : "");
    }

    private void a(TextView textView, String str) {
        if (m.a(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(str);
    }

    private void a(RecommendPost recommendPost, ImageView imageView) {
        if (m.a(recommendPost.getPicUrl())) {
            imageView.setImageResource(R.drawable.img_musiccircle_post_pic_default);
        } else {
            g.a(imageView, recommendPost.getPicUrl(), imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
        }
    }

    public void a(d dVar) {
        com.sds.android.ttpod.framework.modules.theme.c.a(dVar.i(), ThemeElement.CARD_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(dVar.j(), ThemeElement.CARD_CONTROL_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(dVar.d(), ThemeElement.CARD_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(dVar.f(), ThemeElement.CARD_SUB_TEXT);
    }

    private void a(final d dVar, final RecommendPost recommendPost, final int i) {
        int type = recommendPost.getType();
        OnlineMediaItem mediaItem = recommendPost.getMediaItem();
        a(dVar.g(), recommendPost);
        dVar.g().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c d;

            public void onClick(View view) {
                this.d.a(recommendPost, dVar, i);
            }
        });
        CharSequence name = (type >= d.DJ.value() || type <= 0) ? recommendPost.getName() : mediaItem != null ? mediaItem.getTitle() + " - " + mediaItem.getArtist() : "没有获取到信息";
        dVar.d().setText(name);
    }

    protected void a(ImageView imageView, RecommendPost recommendPost) {
    }
}
