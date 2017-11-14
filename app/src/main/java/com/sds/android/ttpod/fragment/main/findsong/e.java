package com.sds.android.ttpod.fragment.main.findsong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.RelatedPost;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.RoundedImageView;
import java.util.List;

/* RelatedPostViewHolder */
public class e {
    private View a;
    private View b;
    private View c;
    private RoundedImageView d;
    private TextView e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private IconTextView i;
    private View j;
    private TextView k;

    public e(View view) {
        this.a = view.findViewById(R.id.id_post_item_container);
        this.b = view.findViewById(R.id.id_post_item_card);
        this.c = view.findViewById(R.id.id_listen_count_layout);
        this.d = (RoundedImageView) view.findViewById(R.id.id_post_image);
        this.e = (TextView) view.findViewById(R.id.id_post_listen_count);
        this.f = (ImageView) view.findViewById(R.id.iv_play_pause);
        this.g = (TextView) view.findViewById(R.id.id_post_name);
        this.h = (TextView) view.findViewById(R.id.id_post_desc);
        this.i = (IconTextView) view.findViewById(R.id.itv_arrow);
        this.j = view.findViewById(R.id.id_separator_view);
        this.k = (TextView) view.findViewById(R.id.id_post_author_name);
    }

    public void a(RelatedPost relatedPost, int i) {
        if (relatedPost != null) {
            long listenerCount = relatedPost.getListenerCount();
            this.c.setVisibility(listenerCount >= 0 ? 0 : 8);
            if (listenerCount >= 0) {
                this.e.setText("" + listenerCount);
            }
            CharSequence tweet = relatedPost.getTweet();
            if (this.h != null) {
                TextView textView = this.h;
                if (m.a((String) tweet)) {
                    tweet = "暂无介绍";
                }
                textView.setText(tweet);
            }
            List picsUrl = relatedPost.getPicsUrl();
            if (j.a(picsUrl) || m.a((String) picsUrl.get(0))) {
                this.d.setImageResource(R.drawable.img_musiccircle_post_pic_default);
            } else {
                g.a(this.d, (String) picsUrl.get(0), this.a.getWidth(), this.a.getWidth(), (int) R.drawable.img_musiccircle_post_pic_default);
            }
            tweet = "";
            if (relatedPost.getRelatedPostUser() != null) {
                tweet = relatedPost.getSongListName() != null ? relatedPost.getSongListName() : "";
            }
            this.g.setText(tweet);
            String nickName = relatedPost.getRelatedPostUser().getNickName();
            if (this.k != null && !m.a(nickName)) {
                this.k.setText(nickName);
            }
        }
    }

    public void a() {
        c.a(this.a, ThemeElement.BACKGROUND_MASK);
        c.a(this.g, ThemeElement.CARD_TEXT);
        if (this.b != null) {
            c.a(this.b, ThemeElement.CARD_CONTROL_BACKGROUND);
        }
        if (this.h != null) {
            c.a(this.h, ThemeElement.CARD_SUB_TEXT);
        }
        if (this.i != null) {
            v.a(this.i, ThemeElement.TILE_SUB_TEXT);
        }
        if (this.j != null) {
            c.a(this.j, ThemeElement.COMMON_SEPARATOR);
        }
    }
}
