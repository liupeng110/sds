package com.sds.android.ttpod.adapter.d.a;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.f.d;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.TextViewFixTouchConsume;
import java.util.List;

/* CommentAdapter */
public class a extends com.sds.android.ttpod.adapter.a<Notice> {
    private com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a a;
    private com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment.a e;

    /* CommentAdapter */
    class a {
        final /* synthetic */ a a;
        private ImageView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextViewFixTouchConsume f;

        public a(a aVar, View view) {
            this.a = aVar;
            View findViewById = view.findViewById(R.id.layout_avatar);
            findViewById.findViewById(R.id.iv_flag).setVisibility(8);
            this.b = (ImageView) findViewById.findViewById(R.id.image_avatar);
            this.c = (TextView) view.findViewById(R.id.tv_time);
            this.d = (TextView) view.findViewById(R.id.tv_user_name);
            this.e = (TextView) view.findViewById(R.id.tv_tweet);
            this.f = (TextViewFixTouchConsume) view.findViewById(R.id.tv_content);
        }
    }

    public void a(com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a aVar) {
        this.a = aVar;
    }

    public void a(com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment.a aVar) {
        this.e = aVar;
    }

    public a(Context context, List<Notice> list) {
        super(context, list);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_comment_item, null, false);
        inflate.setTag(new a(this, inflate));
        return inflate;
    }

    protected final void a(View view, Notice notice, int i) {
        a aVar = (a) view.getTag();
        Comment comment = notice.getComment();
        if (comment != null) {
            if (b.av()) {
                NewUser at = b.at();
                g.a(aVar.b, at.getAvatarUrl(), aVar.b.getWidth(), aVar.b.getHeight(), (int) R.drawable.img_avatar_default);
                aVar.d.setText(at.getNickName());
            }
            aVar.c.setText(w.a(a(), comment.getCreateTimeInSecond()));
            OnClickListener anonymousClass1 = new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (b.av()) {
                        this.a.a.a(b.at());
                    }
                }
            };
            aVar.b.setOnClickListener(anonymousClass1);
            aVar.d.setOnClickListener(anonymousClass1);
        }
        Comment originComment = notice.getOriginComment();
        com.sds.android.ttpod.component.emoticons.b b = com.sds.android.ttpod.component.emoticons.b.b();
        CharSequence tweet = (comment == null || comment.getUser() == null) ? null : comment.getTweet();
        if (m.a((String) tweet)) {
            aVar.e.setText("抱歉，评论已被删除");
            aVar.e.setTextColor(a().getResources().getColor(R.color.post_text_title));
        } else {
            tweet = b.a(a(), tweet);
            if (tweet == null) {
                tweet = "";
            }
            aVar.e.setText(tweet);
            aVar.e.setTextColor(a().getResources().getColor(R.color.post_text_tweet));
        }
        Post originPost = notice.getOriginPost();
        if (originPost == null) {
            aVar.f.setText(null);
            return;
        }
        String str;
        String str2;
        String str3 = "";
        if (originPost.getType() < d.DJ.value()) {
            OnlineMediaItem mediaItem = originPost.getMediaItem();
            if (mediaItem != null) {
                str3 = mediaItem.getTitle();
            }
            str = str3;
        } else {
            str = originPost.getSongListName();
        }
        if (originComment == null) {
            str2 = "评论了 ";
            tweet = str2 + str;
        } else {
            str2 = "在 ";
            tweet = str2 + str + " 回复了我的评论: " + originComment.getTweet();
        }
        CharSequence spannableString = new SpannableString(tweet);
        int length = str2.length();
        spannableString.setSpan(new com.sds.android.ttpod.activities.musiccircle.message.b(notice, originPost, new com.sds.android.ttpod.activities.musiccircle.message.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(Notice notice, Post post) {
                this.a.e.a(post);
            }
        }), length, str.length() + length, 33);
        aVar.f.setText(b.a(a(), spannableString));
        aVar.f.setMovementMethod(com.sds.android.ttpod.widget.TextViewFixTouchConsume.a.a());
    }
}
