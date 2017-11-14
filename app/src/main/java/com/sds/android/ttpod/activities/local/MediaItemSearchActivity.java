package com.sds.android.ttpod.activities.local;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.component.d.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.media.mediastore.PinyinUtils.Pinyin;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaItemSearchActivity extends BaseSearchActivity implements com.sds.android.ttpod.fragment.main.list.d.a {
    private String mGroupID;
    private a mMediaItemSearchAdapter;

    class a extends com.sds.android.ttpod.adapter.c.a {
        final /* synthetic */ MediaItemSearchActivity a;

        public final class a implements com.sds.android.ttpod.adapter.c.a.a {
            final /* synthetic */ a a;
            private MediaItem b;
            private Pinyin c;
            private Pinyin d;
            private Pinyin e;
            private Pinyin f;
            private String g;
            private String h;

            private a(a aVar, MediaItem mediaItem, Map<String, Pinyin[]> map) {
                this.a = aVar;
                this.b = mediaItem;
                String str = (String) TTTextUtils.validateString(aVar.b, this.b.getArtist());
                String title = this.b.getTitle();
                if (!TextUtils.isEmpty(str)) {
                    this.h = str.toUpperCase();
                }
                if (!TextUtils.isEmpty(title)) {
                    this.g = title.toUpperCase();
                }
                Pinyin[] twoKindOfPinyin = PinyinUtils.getTwoKindOfPinyin(title);
                if (twoKindOfPinyin != null) {
                    this.c = twoKindOfPinyin[0];
                    this.e = twoKindOfPinyin[1];
                }
                if (str == null || !map.containsKey(str)) {
                    Object twoKindOfPinyin2 = PinyinUtils.getTwoKindOfPinyin(str);
                    if (twoKindOfPinyin2 != null) {
                        map.put(str, twoKindOfPinyin2);
                    }
                    Object obj = twoKindOfPinyin2;
                } else {
                    Pinyin[] pinyinArr = (Pinyin[]) map.get(str);
                }
                if (pinyinArr != null) {
                    this.d = pinyinArr[0];
                    this.f = pinyinArr[1];
                }
            }

            public MediaItem a() {
                return this.b;
            }

            public CharSequence b() {
                if (TextUtils.isEmpty(a.d) || this.c == null) {
                    return this.b.getTitle();
                }
                int[] iArr = new int[]{0, 0, 0};
                if (!(this.a.a(this.c, iArr) || this.a.a(this.e, iArr))) {
                    this.a.a(this.g, iArr);
                }
                if (iArr[2] <= 0) {
                    return this.b.getTitle();
                }
                CharSequence spannableString = new SpannableString(this.b.getTitle());
                spannableString.setSpan(a.e, iArr[1], iArr[2] + iArr[1], 33);
                return spannableString;
            }

            public CharSequence d() {
                String str = (String) TTTextUtils.validateString(this.a.b, this.b.getArtist());
                if (TextUtils.isEmpty(a.d) || this.d == null) {
                    return str;
                }
                int[] iArr = new int[]{0, 0, 0};
                if (!(this.a.a(this.d, iArr) || this.a.a(this.f, iArr))) {
                    this.a.a(this.h, iArr);
                }
                if (iArr[2] <= 0) {
                    return str;
                }
                CharSequence spannableString = new SpannableString(str);
                spannableString.setSpan(a.e, iArr[1], iArr[2] + iArr[1], 33);
                return spannableString;
            }

            public int[] c() {
                int[] iArr = new int[]{0, 0, 0};
                boolean e = this.a.a(this.c, iArr);
                iArr[0] = 0;
                if (!e) {
                    e = this.a.a(this.d, iArr);
                    if (e) {
                        iArr[0] = 1;
                    } else {
                        e = this.a.a(this.e, iArr);
                        if (e) {
                            iArr[0] = 2;
                        } else {
                            e = this.a.a(this.f, iArr);
                            if (e) {
                                iArr[0] = 3;
                            } else {
                                e = this.a.a(this.g, iArr);
                                if (e) {
                                    iArr[0] = 4;
                                } else {
                                    e = this.a.a(this.h, iArr);
                                    if (e) {
                                        iArr[0] = 5;
                                    }
                                }
                            }
                        }
                    }
                }
                if (e) {
                    return iArr;
                }
                return null;
            }
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(MediaItemSearchActivity mediaItemSearchActivity, Activity activity) {
            this.a = mediaItemSearchActivity;
            super(activity);
        }

        private a b(MediaItem mediaItem) {
            for (com.sds.android.ttpod.adapter.c.a.a aVar : this.f) {
                if (((a) aVar).a().equals(mediaItem)) {
                    return (a) aVar;
                }
            }
            return null;
        }

        public void a(MediaItem mediaItem) {
            a b = b(mediaItem);
            this.f.remove(b);
            this.h.remove(b);
            notifyDataSetChanged();
        }

        public void a(List<MediaItem> list) {
            if (list == null) {
                throw new IllegalArgumentException("mediaItems must not be null");
            }
            Map hashMap = new HashMap();
            for (MediaItem aVar : list) {
                this.f.add(new a(aVar, hashMap));
            }
            c = "";
            d = "";
            this.h = this.f;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.h.size();
        }

        public a a(int i) {
            return (a) this.h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        protected void a(View view) {
            view.setTag(new g(view));
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            MediaItem a = this.a.mMediaItemSearchAdapter.a(i).a();
            if (view == null) {
                view = View.inflate(this.b, R.layout.media_list_item, null);
                a(view);
            }
            g gVar = (g) view.getTag();
            gVar.a(this.a.mSearchResultListView, a, i, true, this.a);
            gVar.j().setText(a(i).b());
            gVar.a(a(i).d(), 0, false);
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_local_search");
        trackModule("local_search");
        setLaunchFragmentAttr(R.id.action_bar_activity_container, R.anim.slide_in_right, R.anim.slide_out_right);
        onNewIntent(getIntent());
        updateAlibabaProperty("songlist_id", "local_media_search_list");
        updateAlibabaProperty("songlist_name", "local_media_search_list");
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mGroupID = intent.getStringExtra(AbsMediaListFragment.KEY_GROUP_ID);
        d.a(this.mGroupID, "mGroupID");
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_MEDIA_ITEM_LIST, this.mGroupID, com.sds.android.ttpod.framework.storage.environment.b.l(this.mGroupID)));
    }

    protected com.sds.android.ttpod.adapter.c.a onCreateAdapter() {
        this.mMediaItemSearchAdapter = new a(this, this);
        return this.mMediaItemSearchAdapter;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIST, i.a(getClass(), "updateMediaList", String.class, List.class));
    }

    public void updateMediaList(String str, List<MediaItem> list) {
        if (this.mGroupID.equals(str)) {
            this.mMediaItemSearchAdapter.a((List) list);
            onLoadDataFinished();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
        int headerViewsCount = i - getListView().getHeaderViewsCount();
        if (headerViewsCount >= 0 && headerViewsCount < this.mMediaItemSearchAdapter.getCount()) {
            a a = this.mMediaItemSearchAdapter.a(headerViewsCount);
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, this.mGroupID, a.a()));
        }
        finish();
    }

    public void onMediaItemLongClicked(AdapterView<?> adapterView, View view, int i, long j) {
        new c(this, this.mMediaItemSearchAdapter.a(i).a(), i).a();
    }

    public void onDeleteMediaItem(final MediaItem mediaItem) {
        f.a((Context) this, mediaItem, this.mGroupID, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.i>(this) {
            final /* synthetic */ MediaItemSearchActivity b;

            public void a(com.sds.android.ttpod.component.d.a.i iVar) {
                b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, this.b.mGroupID, mediaItem, Boolean.valueOf(iVar.b())));
                this.b.mMediaItemSearchAdapter.a(mediaItem);
            }
        });
    }

    private void initMediaItemMenuClickEvent(com.sds.android.ttpod.component.b.f fVar, int i) {
        MediaItem a = this.mMediaItemSearchAdapter.a(i).a();
        OnClickListener dVar = new com.sds.android.ttpod.fragment.main.list.d(this, this.mSearchResultListView, a, i);
        dVar.a((com.sds.android.ttpod.fragment.main.list.d.a) this);
        fVar.a(a);
        fVar.a(dVar);
    }

    public void onExpand(View view, int i) {
        super.onExpand(view, i);
        if (this.mMediaItemSearchAdapter != null && i < this.mMediaItemSearchAdapter.getCount() && i >= 0) {
            initMediaItemMenuClickEvent((com.sds.android.ttpod.component.b.f) view.getTag(), i);
        }
    }
}
