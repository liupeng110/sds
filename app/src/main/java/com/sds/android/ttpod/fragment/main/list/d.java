package com.sds.android.ttpod.fragment.main.list;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.activities.musiccircle.SlidingAlbumDetailFragment;
import com.sds.android.ttpod.b.c;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.f;
import com.sds.android.ttpod.component.d.a.e;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.main.findsong.SubRelatedRecommendFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.framework.a.b.d.j;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.io.File;

/* MediaItemMenuClickStub */
public class d implements OnClickListener, b {
    private ActionExpandableListView a;
    private BaseActivity b;
    private MediaItem c;
    private e d;
    private int e;
    private int f = -1;
    private a g;

    /* MediaItemMenuClickStub */
    public interface a {
        void onDeleteMediaItem(MediaItem mediaItem);
    }

    public d(Activity activity, ActionExpandableListView actionExpandableListView, MediaItem mediaItem, int i) {
        this.b = (BaseActivity) activity;
        this.a = actionExpandableListView;
        this.c = mediaItem;
        this.e = i;
    }

    public d(Activity activity, MediaItem mediaItem, e eVar, a aVar, int i) {
        this.b = (BaseActivity) activity;
        this.c = mediaItem;
        this.d = eVar;
        this.g = aVar;
        this.e = i;
        this.f = eVar != null ? 1 : 0;
    }

    public void onClick(View view) {
        m.a(this.a);
        t.a(this.e);
        a(view.getId());
    }

    public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
        if (this.d != null) {
            this.d.dismiss();
            a(aVar.g());
        }
    }

    private void a(int i) {
        switch (i) {
            case R.id.media_menu_add:
                i();
                return;
            case R.id.media_menu_album:
                l();
                return;
            case R.id.media_menu_delete:
                h();
                return;
            case R.id.media_menu_download:
                g();
                return;
            case R.id.media_menu_favor:
                b();
                return;
            case R.id.media_menu_info:
                d();
                return;
            case R.id.media_menu_more:
                f();
                return;
            case R.id.media_menu_mv:
                k();
                return;
            case R.id.media_menu_related:
                m();
                return;
            case R.id.media_menu_ring:
                c();
                return;
            case R.id.media_menu_send:
                e();
                return;
            case R.id.media_menu_share:
                j();
                return;
            case R.id.media_menu_singer:
                a();
                return;
            default:
                return;
        }
    }

    private void b() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_heart");
        if (!new com.sds.android.ttpod.component.c.a(this.b, this.c).a(10)) {
            new f(null).a(this.c, this.e);
        }
    }

    private void c() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_ring");
        com.sds.android.ttpod.component.d.f.a(this.b, this.c);
        a(r.ACTION_RIGHT_MENU_RING, s.PAGE_NONE);
    }

    private void d() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_info");
        com.sds.android.ttpod.component.d.f.a(this.b, this.c, null);
        a(r.ACTION_RIGHT_MENU_MUSIC_INFO, s.PAGE_NONE);
    }

    private void e() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_send");
        c.a(this.b, new File[]{new File(this.c.getLocalDataSource())});
        a(r.ACTION_RIGHT_MENU_SEND, s.PAGE_NONE);
    }

    private void f() {
        new com.sds.android.ttpod.component.d.c(this.b, this.c, this.e).a();
        a(r.ACTION_RIGHT_MENU_MORE, s.PAGE_NONE);
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_more");
    }

    private void g() {
        new com.sds.android.ttpod.component.c.b(this.b).a(this.c, p.b());
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_download");
    }

    private void h() {
        if (this.g != null) {
            com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_delete");
            a(r.ACTION_RIGHT_MENU_DELETE, s.PAGE_NONE);
            this.g.onDeleteMediaItem(this.c);
        }
    }

    private void i() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_add");
        com.sds.android.ttpod.component.d.f.a(this.b, com.sds.android.ttpod.framework.storage.a.a.a().j(), this.c, null, null);
        a(r.ACTION_RIGHT_MENU_ADD_TO, s.PAGE_NONE);
    }

    private void j() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_share");
        if (!new com.sds.android.ttpod.component.c.a(this.b, this.c).a(12)) {
            com.sds.android.ttpod.component.d.f.a(this.b, this.c);
            a(r.ACTION_RIGHT_MENU_SHARE, s.PAGE_SHARE_DIALOG);
        }
    }

    private void k() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_mv");
        com.sds.android.ttpod.fragment.main.findsong.a.b(this.b, new com.sds.android.ttpod.fragment.main.findsong.b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a() {
                j.a("mv_origin", "menu_mv");
                VideoPlayManager.a(this.a.b, this.a.c);
            }
        });
    }

    private void l() {
        BaseFragment instantiate = SlidingAlbumDetailFragment.instantiate(this.c.getAlbumId(), "", this.b instanceof MainActivity);
        if (instantiate != null) {
            a(r.ACTION_RIGHT_ALBUM, s.PAGE_ALBUM_DETAIL);
            com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_album");
            this.b.launchFragment(instantiate);
        }
    }

    protected void a() {
        a(r.ACTION_RIGHT_SINGER, s.PAGE_SINGER_MESSAGE);
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_singer");
        boolean z = this.b != null && (this.b instanceof MainActivity);
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(this.c.getExtra(), OnlineMediaItem.class);
        int singerSFlag = onlineMediaItem == null ? 1 : onlineMediaItem.getSingerSFlag();
        if (this.b != null && singerSFlag == 0) {
            BaseFragment topFragment = this.b.getTopFragment();
            if (topFragment != null && topFragment.getClass().equals(FavoriteSubMediaListFragment.class)) {
                singerSFlag = 1;
            }
        }
        SingerDetailFragment.launch(this.b, this.c.getArtist(), (int) this.c.getArtistID(), z, singerSFlag);
    }

    private void m() {
        com.sds.android.ttpod.framework.a.b.b.a(this.b, this.c, "menu_similar");
        a(r.ACTION_RIGHT_RELATED_SONG_LIST, s.PAGE_RELATED_POST);
        SubRelatedRecommendFragment.launch(this.b, this.c, this.b instanceof MainActivity);
    }

    private void a(r rVar, s sVar) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), s.PAGE_NONE.getValue(), sVar.getValue());
        if (this.c.getSongID().longValue() == 0) {
            sUserEvent.append(MediasColumns.SONG_ID, this.c.getLocalDataSource());
        } else {
            sUserEvent.append(MediasColumns.SONG_ID, this.c.getSongID());
        }
        if (this.f != -1) {
            sUserEvent.append("press_type", Integer.valueOf(this.f));
        }
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    public void a(a aVar) {
        this.g = aVar;
    }
}
