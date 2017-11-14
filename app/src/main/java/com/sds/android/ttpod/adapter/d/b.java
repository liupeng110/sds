package com.sds.android.ttpod.adapter.d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.LabeledTTPodUser;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.f.d;
import com.sds.android.ttpod.framework.modules.f.f;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.List;

/* FavoriteAdapter */
public class b extends com.sds.android.ttpod.adapter.a<Post> {

    /* FavoriteAdapter */
    class a {
        final /* synthetic */ b a;
        private UserAvatarView b;
        private TextView c;
        private TextView d;

        public a(b bVar, UserAvatarView userAvatarView, TextView textView, TextView textView2) {
            this.a = bVar;
            this.b = userAvatarView;
            this.c = textView;
            this.d = textView2;
        }
    }

    public b(Context context, List<Post> list) {
        super(context, list);
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_favorite_item, null, false);
        inflate.setTag(new a(this, (UserAvatarView) inflate.findViewById(R.id.image_avatar), (TextView) inflate.findViewById(R.id.tv_tweet), (TextView) inflate.findViewById(R.id.tv_user_name)));
        return inflate;
    }

    protected void a(View view, Post post, int i) {
        a aVar = (a) view.getTag();
        LabeledTTPodUser user = f.a(post).getUser();
        aVar.d.setText(user.getNickName());
        int a = com.sds.android.ttpod.common.c.a.a(48);
        g.a(aVar.b, user.getAvatarUrl(), a, a, (int) R.drawable.img_avatar_default);
        CharSequence charSequence = "";
        if (post.getType() == d.SONG_LIST.value() || post.getType() == d.DJ.value()) {
            charSequence = post.getSongListName();
        } else {
            OnlineMediaItem mediaItem = post.getMediaItem();
            if (mediaItem != null) {
                charSequence = mediaItem.getTitle();
            }
        }
        aVar.c.setText(charSequence);
        aVar.b.setVFlagVisible(user.isVerified());
    }
}
