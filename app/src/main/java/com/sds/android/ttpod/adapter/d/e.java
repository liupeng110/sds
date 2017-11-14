package com.sds.android.ttpod.adapter.d;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.CommentsFragment.b;
import com.sds.android.ttpod.adapter.a;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.a.b.d.k;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.f.d;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.util.ArrayList;
import java.util.List;

/* PostListAdapter */
public abstract class e extends a<Post> {
    private List<MediaItem> a = new ArrayList();
    private c e;
    private long f;
    private String g;
    private String h;
    private b i;

    protected abstract void a(Post post);

    protected abstract void a(Post post, boolean z);

    protected abstract void a(TTPodUser tTPodUser);

    public /* synthetic */ void a(Object obj) {
        d((Post) obj);
    }

    public e(Context context, List<Post> list, String str) {
        super(context, list);
        this.h = str;
        this.e = c.from(com.sds.android.ttpod.framework.support.e.a(BaseApplication.e()).n());
        Long songID = com.sds.android.ttpod.framework.storage.a.a.a().M().getSongID();
        this.f = songID == null ? 0 : songID.longValue();
        this.g = com.sds.android.ttpod.framework.storage.environment.b.bl();
        com.sds.android.ttpod.framework.storage.environment.b.a(context, c.ONLINE_MEDIA_LIST_GROUP_NAME, new com.sds.android.ttpod.framework.storage.environment.b.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(c cVar) {
                this.a.g = com.sds.android.ttpod.framework.storage.environment.b.bl();
                this.a.f = com.sds.android.ttpod.framework.storage.a.a.a().M().getSongID().longValue();
            }
        });
    }

    public void b(List<Post> list) {
        this.d.addAll(list);
        notifyDataSetChanged();
    }

    public void a(b bVar) {
        this.i = bVar;
    }

    public void a(Long l) {
        this.f = l == null ? 0 : l.longValue();
        this.e = c.LOADING;
        notifyDataSetChanged();
    }

    public void a(PlayStatus playStatus) {
        this.e = c.from(playStatus);
        notifyDataSetChanged();
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_post_item, null, false);
        inflate.setTag(new f(inflate));
        return inflate;
    }

    protected final void a(View view, Post post, int i) {
        f fVar = (f) view.getTag();
        a(fVar);
        a(fVar, post, f.a(post), i);
    }

    protected void a(f fVar, Post post, Post post2, int i) {
        a(fVar, post.getUser());
        long createTimeInSecond = post.getCreateTimeInSecond();
        fVar.d().setText(createTimeInSecond > 0 ? w.a(a(), createTimeInSecond) : "");
        CharSequence tweet = post.getTweet();
        if (!TextUtils.isEmpty(tweet)) {
            fVar.i().setVisibility(0);
            fVar.i().setText(tweet);
        } else if (fVar.i().getVisibility() != 8) {
            fVar.i().setVisibility(8);
        }
        if (post != post2) {
            fVar.e().setVisibility(0);
            LabeledTTPodUser user = post2.getUser();
            fVar.e().setText("来自 " + (user != null ? user.getNickName() : ""));
        } else {
            fVar.e().setVisibility(8);
        }
        a(fVar, post2, i);
        a(post2, fVar.m());
        a(fVar, post, post2);
        a(fVar, post2);
        b(fVar, post2);
    }

    private void a(Post post, ImageView imageView) {
        ArrayList picList = post.getPicList();
        if (picList != null && !picList.isEmpty()) {
            String str = (String) picList.get(0);
            if (str != null) {
                g.a(imageView, str, imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
            }
        } else if (post.getType() == d.SINGLE_SONG.value()) {
            g.a(imageView, com.sds.android.ttpod.b.b.a(post.getMediaItem().getArtistId()), imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_musiccircle_post_pic_default);
        } else {
            imageView.setImageResource(R.drawable.img_musiccircle_post_pic_default);
        }
    }

    private void a(f fVar, final Post post, final Post post2) {
        fVar.g().setText(post2.getCommentCount() > 0 ? String.valueOf(post2.getCommentCount()) : "评论");
        fVar.n().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e c;

            public void onClick(View view) {
                this.c.a(post, post2);
                this.c.b(post2);
            }
        });
    }

    protected void a(Post post, Post post2) {
        this.h + "_" + String.valueOf(post2.getTitleName());
        this.i.a(post2.getId(), post2.getUser().getUserId());
    }

    private void a(f fVar) {
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.f(), ThemeElement.CARD_CONTROL_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.g(), ThemeElement.CARD_CONTROL_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.h(), ThemeElement.CARD_CONTROL_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.d(), ThemeElement.CARD_SUB_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.e(), ThemeElement.CARD_SUB_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.b(), ThemeElement.CARD_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.i(), ThemeElement.CARD_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.p(), ThemeElement.CARD_CONTROL_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.n(), ThemeElement.CARD_CONTROL_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.o(), ThemeElement.CARD_CONTROL_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.q(), ThemeElement.CARD_CONTROL_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.a(), ThemeElement.COMMON_SUB_BAR);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.r(), ThemeElement.COMMON_SEPARATOR);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.s(), ThemeElement.COMMON_SEPARATOR);
        com.sds.android.ttpod.framework.modules.theme.c.a(fVar.t(), ThemeElement.CARD_BACKGROUND);
    }

    private void a(f fVar, final Post post) {
        fVar.h().setText(post.getRepostCount() > 0 ? String.valueOf(post.getRepostCount()) : "转发");
        fVar.o().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e b;

            public void onClick(View view) {
                if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                    com.sds.android.ttpod.component.d.f.a((Activity) this.b.a(), post);
                    this.b.c(post);
                    return;
                }
                com.sds.android.ttpod.b.f.a(true);
            }
        });
    }

    private void a(f fVar, final LabeledTTPodUser labeledTTPodUser) {
        TextView b = fVar.b();
        b.setText(labeledTTPodUser.getNickName());
        ImageView j = fVar.j();
        j.setVFlagVisible(labeledTTPodUser.isVerified());
        g.a(j, labeledTTPodUser.getAvatarUrl(), j.getWidth(), j.getHeight(), (int) R.drawable.img_avatar_default);
        OnClickListener anonymousClass4 = new OnClickListener(this) {
            final /* synthetic */ e b;

            public void onClick(View view) {
                if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                    this.b.a(labeledTTPodUser);
                } else {
                    com.sds.android.ttpod.b.f.a(false);
                }
            }
        };
        fVar.a().setOnClickListener(anonymousClass4);
        b.setOnClickListener(anonymousClass4);
        j.setOnClickListener(anonymousClass4);
    }

    private void g(Post post) {
        if (!this.a.isEmpty()) {
            this.g = com.sds.android.ttpod.component.c.c.a(post);
            o.a(this.f, this.a, this.g);
        }
    }

    private boolean h(Post post) {
        return com.sds.android.ttpod.component.c.c.a(this.g, post);
    }

    private void a(final f fVar, final Post post, int i) {
        Object obj = post.getType() < d.DJ.value() ? 1 : null;
        OnlineMediaItem mediaItem = post.getMediaItem();
        a(fVar.k(), post, this.e);
        fVar.k().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e c;

            public void onClick(View view) {
                if (this.c.h(post)) {
                    this.c.a = f.b(post);
                    this.c.g(post);
                } else {
                    this.c.f = 0;
                    this.c.e = c.LOADING;
                    this.c.d(post);
                    new d(this.c.a(), fVar.k(), this.c, this.c.a, this.c.e).a(post);
                }
                this.c.a(post);
            }
        });
        TextView c = fVar.c();
        r1 = obj != null ? mediaItem != null ? mediaItem.getTitle() + " - " + mediaItem.getArtist() : "没有获取到信息" : post.getSongListName();
        c.setText(r1);
        ImageView l = fVar.l();
        int i2 = obj != null ? R.drawable.img_musiccircle_song : post.getType() == d.DJ.value() ? R.drawable.img_musiccircle_radio : R.drawable.img_musiccircle_songlist;
        l.setImageResource(i2);
    }

    private void a(View view, Post post, c cVar) {
        boolean z = false;
        if (!h(post)) {
            view.setEnabled(true);
            view.clearAnimation();
            view.setSelected(false);
        } else if (cVar == c.LOADING) {
            view.setEnabled(false);
            view.setSelected(true);
            view.startAnimation(AnimationUtils.loadAnimation(a(), R.anim.unlimited_rotate));
        } else {
            view.setEnabled(true);
            view.clearAnimation();
            if (cVar == c.PLAYING || (c() != null && cVar == c.STOP)) {
                z = true;
            }
            view.setSelected(z);
        }
    }

    private void b(f fVar, final Post post) {
        fVar.f().setText(post.getFavoriteCount() > 0 ? String.valueOf(post.getFavoriteCount()) : "收藏");
        final boolean booleanValue = ((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_FAVORITE_POST, Long.valueOf(post.getId())), Boolean.class)).booleanValue();
        fVar.f().setCompoundDrawablesWithIntrinsicBounds(booleanValue ? R.drawable.img_musiccircle_favorite_mark_yes : R.drawable.img_musiccircle_favorite_mark_no, 0, 0, 0);
        fVar.p().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e c;

            public void onClick(View view) {
                if (EnvironmentUtils.c.e() && com.sds.android.ttpod.framework.storage.environment.b.av()) {
                    new ArrayList().add(Long.valueOf(post.getId()));
                    k a = k.a(post.getSongListName(), "", "");
                    if (booleanValue) {
                        post.decreaseFavoriteCount();
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REMOVE_FAVORITE_POSTS, r0, a.a()));
                    } else {
                        post.increaseFavoriteCount();
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_FAVORITE_POSTS, r0, a.a()));
                    }
                    this.c.a(post, booleanValue);
                    return;
                }
                com.sds.android.ttpod.b.f.a(true);
            }
        });
    }

    protected void b(Post post) {
    }

    protected void c(Post post) {
    }

    public void d(Post post) {
        super.a((Object) post);
    }

    public void e(Post post) {
        for (Post a : b()) {
            Post a2 = f.a(a2);
            if (a2.getId() == post.getId()) {
                a2.setCommentCount(post.getCommentCount());
                return;
            }
        }
    }

    public void f(Post post) {
        for (Post a : b()) {
            Post a2 = f.a(a2);
            if (a2.getId() == post.getId()) {
                a2.setRepostCount(post.getRepostCount());
                return;
            }
        }
    }
}
