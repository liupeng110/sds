package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.UserAvatarView;

/* PostDetailHeader */
public class d {
    private Context a;
    private View b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private IconTextView f;
    private IconTextView g;
    private IconTextView h;
    private IconTextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private IconTextView n;
    private UserAvatarView o;
    private View p;
    private View q;
    private RelativeLayout r;
    private RelativeLayout s;
    private ImageView t;
    private boolean u;

    /* PostDetailHeader */
    public static class a extends d {
        public void a() {
        }
    }

    public d(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (context == null) {
            throw new IllegalArgumentException("context and post must not be null");
        }
        this.a = context;
        this.b = layoutInflater.inflate(R.layout.musiccircle_post_detail_header, viewGroup, false);
        this.b.setEnabled(false);
        this.b.setOnClickListener(null);
        this.t = (ImageView) this.b.findViewById(R.id.post_header_picture);
        this.c = (ImageView) this.b.findViewById(R.id.post_header_play);
        this.d = (TextView) this.b.findViewById(R.id.post_header_download);
        this.e = (TextView) this.b.findViewById(R.id.post_header_share);
        this.f = (IconTextView) this.b.findViewById(R.id.post_head_favorite_icon_text);
        this.g = (IconTextView) this.b.findViewById(R.id.post_head_comment_icon_text);
        this.h = (IconTextView) this.b.findViewById(R.id.post_head_share_icon_text);
        this.i = (IconTextView) this.b.findViewById(R.id.post_head_download_icon_text);
        this.j = (TextView) this.b.findViewById(R.id.post_header_favorite);
        this.k = (TextView) this.b.findViewById(R.id.post_header_comment);
        this.l = (TextView) this.b.findViewById(R.id.post_header_user_name);
        this.m = (TextView) this.b.findViewById(R.id.post_header_tweet);
        this.n = (IconTextView) this.b.findViewById(R.id.post_header_right_arrow);
        this.o = (UserAvatarView) this.b.findViewById(R.id.post_header_user_avatar);
        this.p = this.b.findViewById(R.id.post_detail_header_operations);
        this.q = this.b.findViewById(R.id.post_header_info_container);
        this.r = (RelativeLayout) this.b.findViewById(R.id.post_header_user_click_bounds);
        this.s = (RelativeLayout) this.b.findViewById(R.id.post_header_tweet_bounds);
        a();
    }

    public void a(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
        this.j.setOnClickListener(onClickListener);
        this.e.setOnClickListener(onClickListener);
        this.k.setOnClickListener(onClickListener);
        this.c.setOnClickListener(onClickListener);
        this.s.setOnClickListener(onClickListener);
        this.r.setOnClickListener(onClickListener);
    }

    public void a(Post post) {
        a(f.d(post));
        d(post.getTweet());
        c(post.getUser().getNickName());
        if (post.getCommentCount() > 0) {
            this.k.setText(post.getCommentCount() + "");
        }
        b(post.getUser().getAvatarUrl());
        a(((Boolean) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_FAVORITE_POST, Long.valueOf(post.getId())), Boolean.class)).booleanValue(), post.getFavoriteCount());
    }

    public void a(boolean z, int i) {
        this.u = z;
        this.f.setText(z ? R.string.icon_post_header_favorite_yes : R.string.icon_post_header_favorite_no);
        a(this.u);
        this.j.setText(i > 0 ? String.valueOf(i) : this.a.getResources().getString(R.string.favorite));
    }

    public View b() {
        return this.b;
    }

    public void a() {
        c.a(this.l, ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.m, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(this.b.findViewById(R.id.post_header_info_divider), ThemeElement.COMMON_SEPARATOR);
        c.a(this.b.findViewById(R.id.post_header_bottom_divider), ThemeElement.COMMON_SEPARATOR);
        c.a(this.p, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        c.a(this.q, ThemeElement.TILE_MASK);
        v.a(this.n, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, (int) R.string.icon_arrow_right, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        d();
    }

    private void d() {
        String str = ThemeElement.SONG_LIST_ITEM_TEXT;
        String str2 = ThemeElement.SONG_LIST_ITEM_SUB_TEXT;
        a(this.u);
        v.a(this.g, str, (int) R.string.icon_post_header_comments, str);
        v.a(this.h, str, (int) R.string.icon_post_header_share, str);
        v.a(this.i, str, (int) R.string.icon_post_header_download, str);
        c.a(this.j, str2);
        c.a(this.k, str2);
        c.a(this.e, str2);
        c.a(this.d, str2);
    }

    private void a(boolean z) {
        if (z) {
            this.f.setTextColor(Color.parseColor("#FF617C"));
        } else {
            v.a(this.f, ThemeElement.SONG_LIST_ITEM_TEXT, (int) R.string.icon_post_header_favorite_no, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
    }

    protected void a(String str) {
        g.a(this.t, str, this.t.getWidth(), this.t.getHeight(), (int) R.drawable.img_background_song_publish);
    }

    protected void b(String str) {
        g.a(this.o, str, this.o.getWidth(), this.o.getHeight(), (int) R.drawable.img_avatar_default);
    }

    protected void c(String str) {
        this.l.setText(str);
    }

    protected void a(Drawable drawable) {
        this.l.setCompoundDrawables(null, null, drawable, null);
    }

    protected void d(String str) {
        if (!m.a(str)) {
            this.m.setText(str);
        }
    }

    protected View c() {
        return this.b;
    }
}
