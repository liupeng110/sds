package com.sds.android.ttpod.adapter.d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.f.c;
import java.util.List;

/* UserListAdapter */
public class i<Data extends TTPodUser> extends com.sds.android.ttpod.adapter.a<Data> {
    private a a;

    /* UserListAdapter */
    public interface a {
        void onFollow(long j);

        void onUnFollow(long j);
    }

    public i(Context context, List<Data> list) {
        super(context, list);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    protected final View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = View.inflate(a(), R.layout.musiccircle_user_list_item, null);
        inflate.setTag(new h(inflate));
        return inflate;
    }

    protected final void a(View view, Data data, int i) {
        a((h) view.getTag(), (TTPodUser) data);
    }

    protected void a(h hVar, Data data) {
        ImageView a = hVar.a();
        g.a(a, data.getAvatarUrl(), a.getWidth(), a.getHeight(), (int) R.drawable.img_avatar_default);
        a.setVFlagVisible(data.isVerified());
        hVar.b().setText(data.getNickName());
        hVar.c().setText(a().getString(R.string.follower_str, new Object[]{Integer.valueOf(data.getFollowersCount())}));
        final long userId = data.getUserId();
        final boolean booleanValue = ((Boolean) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_FOLLOWED, Long.valueOf(userId)), Boolean.class)).booleanValue();
        if (booleanValue) {
            hVar.e().setText(R.string.remove_follow);
            hVar.e().setBackgroundResource(R.drawable.xml_musiccircle_follow_button_bg);
        } else {
            hVar.e().setText(R.string.add_follow);
            hVar.e().setBackgroundResource(R.drawable.xml_musiccircle_unfollow_button_bg);
        }
        if (c.TUID_TTPOD != userId) {
            hVar.e().setVisibility(0);
        } else if (hVar.e().getVisibility() != 4) {
            hVar.e().setVisibility(4);
        }
        final h hVar2 = hVar;
        hVar.e().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ i d;

            public void onClick(View view) {
                if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
                    hVar2.e().setText(R.string.is_processing);
                    if (booleanValue) {
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UNFOLLOW_FRIEND, Long.valueOf(userId), ""));
                    } else {
                        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.FOLLOW_FRIEND, Long.valueOf(userId), ""));
                    }
                    if (this.d.a == null) {
                        return;
                    }
                    if (booleanValue) {
                        this.d.a.onUnFollow(userId);
                        return;
                    } else {
                        this.d.a.onFollow(userId);
                        return;
                    }
                }
                f.a(true);
            }
        });
    }
}
