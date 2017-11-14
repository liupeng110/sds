package com.sds.android.ttpod.adapter.d.a;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.core.f.b;
import com.sds.android.ttpod.framework.modules.f.d;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.widget.TextViewFixTouchConsume;
import java.util.List;

/* NoticeAdapter */
public class c extends com.sds.android.ttpod.adapter.a<Notice> {
    private com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment.a a;
    private com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a e;

    /* NoticeAdapter */
    class a {
        final /* synthetic */ c a;
        private ImageView b;
        private View c;
        private TextView d;
        private TextView e;
        private TextViewFixTouchConsume f;

        public a(c cVar, View view) {
            this.a = cVar;
            this.b = (ImageView) view.findViewById(R.id.image_avatar);
            this.c = view.findViewById(R.id.iv_flag);
            this.d = (TextView) view.findViewById(R.id.tv_time);
            this.e = (TextView) view.findViewById(R.id.tv_user_name);
            this.f = (TextViewFixTouchConsume) view.findViewById(R.id.tv_tweet);
        }
    }

    public void a(com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment.a aVar) {
        this.a = aVar;
    }

    public void a(com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a aVar) {
        this.e = aVar;
    }

    public c(Context context, List<Notice> list) {
        super(context, list);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_notice_repost_item, null, false);
        inflate.setTag(new a(this, inflate));
        return inflate;
    }

    protected void a(View view, Notice notice, int i) {
        a aVar = (a) view.getTag();
        aVar.c.setVisibility(notice.getReadStatus() ? 8 : 0);
        aVar.d.setText(w.a(a(), notice.getTimeStamp()));
        Post post = notice.getPost();
        if (post != null) {
            int i2;
            final TTPodUser user = post.getUser();
            g.a(aVar.b, user.getAvatarUrl(), aVar.b.getWidth(), aVar.b.getHeight(), (int) R.drawable.img_avatar_default);
            TextView d = aVar.e;
            if (user.isVerified()) {
                i2 = R.drawable.img_user_v;
            } else {
                i2 = 0;
            }
            d.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
            aVar.e.setText(user.getNickName());
            OnClickListener anonymousClass1 = new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    this.b.e.a(b.a(user));
                }
            };
            aVar.e.setOnClickListener(anonymousClass1);
            aVar.b.setOnClickListener(anonymousClass1);
        }
        CharSequence charSequence = "";
        Post a = f.a(notice.getOriginPost());
        if (a != null) {
            String title;
            if (a.getType() < d.DJ.value()) {
                title = a.getMediaItem().getTitle();
            } else {
                title = a.getSongListName();
            }
            String str = "分享了你的 ";
            int length = str.length();
            CharSequence spannableString = new SpannableString(str + title);
            spannableString.setSpan(new com.sds.android.ttpod.activities.musiccircle.message.b(notice, a, new com.sds.android.ttpod.activities.musiccircle.message.a(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void a(Notice notice, Post post) {
                    this.a.a.a(post);
                }
            }), length, title.length() + length, 33);
            charSequence = spannableString;
        }
        aVar.f.setText(charSequence);
        aVar.f.setMovementMethod(com.sds.android.ttpod.widget.TextViewFixTouchConsume.a.a());
    }
}
