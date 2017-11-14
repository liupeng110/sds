package com.sds.android.ttpod.fragment.main.findsong;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.RelatedPost;
import com.sds.android.cloudapi.ttpod.data.RelatedPost.RelatedPostItem;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.component.c.c;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* RelatedRecommendHeader */
public class f {
    private BaseActivity a;
    private View b;
    private View c;
    private View d;
    private ImageView e;
    private ViewGroup f;
    private LayoutInflater g = LayoutInflater.from(this.a);

    public f(BaseActivity baseActivity, ViewGroup viewGroup) {
        this.a = baseActivity;
        this.d = this.g.inflate(R.layout.layout_related_recommend_header, viewGroup, false);
        this.f = (ViewGroup) this.d.findViewById(R.id.posts_container);
        this.b = this.d.findViewById(R.id.layout_title_related_post);
        this.c = this.d.findViewById(R.id.layout_title_recommend_song);
        this.b.setVisibility(8);
    }

    public void a(List<RelatedPost> list) {
        c();
        this.f.removeAllViews();
        this.f.setVisibility(0);
        this.b.setVisibility(0);
        for (int i = 0; i < list.size(); i++) {
            View a;
            if (list.size() == 1) {
                a = a((RelatedPost) list.get(0));
            } else {
                a = a((RelatedPost) list.get(i), i);
            }
            e eVar = new e(a);
            a.setTag(eVar);
            eVar.a((RelatedPost) list.get(i), i);
            eVar.a();
            this.f.addView(a);
        }
        if (list.size() == 2) {
            View a2 = a((RelatedPost) list.get(0), 0);
            a2.setVisibility(4);
            this.f.addView(a2);
        }
    }

    public void a() {
        c();
        this.c.setVisibility(0);
        this.b.setVisibility(8);
        this.f.setVisibility(8);
    }

    private View a(final RelatedPost relatedPost) {
        View inflate = this.g.inflate(R.layout.related_post_item, this.f, false);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f b;

            public void onClick(View view) {
                this.b.b(relatedPost, 0);
            }
        });
        this.e = (ImageView) inflate.findViewById(R.id.iv_play_pause);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f b;

            public void onClick(View view) {
                this.b.b(relatedPost.getId(), 0);
                this.b.b(relatedPost);
            }
        });
        return inflate;
    }

    private View a(final RelatedPost relatedPost, final int i) {
        View inflate = this.g.inflate(R.layout.related_post_item_sqaure, this.f, false);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f c;

            public void onClick(View view) {
                this.c.b(relatedPost, i);
            }
        });
        return inflate;
    }

    private void b(final RelatedPost relatedPost) {
        a.a((Object) this, new a.a<List<Long>, List<MediaItem>>(this, c(relatedPost)) {
            final /* synthetic */ f b;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((List) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                b((List) obj);
            }

            protected List<MediaItem> a(List<Long> list) {
                return this.b.b(((OnlineMediaItemsResult) r.a((Collection) list).g()).getDataList());
            }

            protected void b(List<MediaItem> list) {
                this.b.a((List) list, relatedPost.getId());
            }
        });
    }

    private List<Long> c(RelatedPost relatedPost) {
        if (relatedPost == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        List arrayList = new ArrayList();
        a(arrayList, relatedPost.getRelatedSongList());
        return arrayList;
    }

    private void a(List<Long> list, ArrayList<RelatedPostItem> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Long valueOf = Long.valueOf(((RelatedPostItem) it.next()).getSongId());
                if (valueOf != null && valueOf.longValue() > 0) {
                    list.add(valueOf);
                }
            }
        }
    }

    public void a(List<MediaItem> list, long j) {
        if (!list.isEmpty()) {
            o.a((List) list, c.c(String.valueOf(j)), j);
            b.a(com.sds.android.ttpod.framework.support.a.f.REPEAT);
        }
    }

    private List<MediaItem> b(List<OnlineMediaItem> list) {
        List<MediaItem> arrayList = new ArrayList();
        for (OnlineMediaItem a : list) {
            arrayList.add(k.a(a));
        }
        return arrayList;
    }

    public View b() {
        return this.d;
    }

    private void b(RelatedPost relatedPost, int i) {
        d(relatedPost);
        this.a.launchFragment(SubPostDetailFragment.createByIdAndScm(relatedPost.getId(), relatedPost.getScm(), getClass().getSimpleName()));
        a(relatedPost.getId(), i);
    }

    private void a(long j, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_RECOMMEND_POST.getValue(), 0, s.PAGE_ONLINE_POST_DETAIL.getValue());
        sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j));
        sUserEvent.append("position", Integer.valueOf(i + 1));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void b(long j, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_RECOMMEND_POST_PLAY.getValue(), 0, 0);
        sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private void d(RelatedPost relatedPost) {
        com.sds.android.ttpod.framework.a.b.b a = new com.sds.android.ttpod.framework.a.b.b().c("similar_songlist").a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(relatedPost.getId())).a("name", relatedPost.getSongListName()).a("scm", relatedPost.getScm());
        BaseFragment topFragment = this.a.getTopFragment();
        if (topFragment != null) {
            a.a("trigger_id", topFragment.getAlibabaProperty("trigger_id"));
        }
        a.a();
    }

    public void c() {
        com.sds.android.ttpod.framework.modules.theme.c.a(this.b, ThemeElement.TILE_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.b.findViewById(R.id.v_indicator_related_post), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.b.findViewById(R.id.tv_title_related_song), ThemeElement.TILE_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.c, ThemeElement.TILE_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.c.findViewById(R.id.tv_title_recommend_song), ThemeElement.TILE_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.c.findViewById(R.id.v_indicator_recommend_song), ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }
}
