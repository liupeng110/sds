package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FirstPublishAlbumData;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewAlbumResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.adapter.d;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NewAlbumFragment extends SlidingClosableFragment {
    private static final String CURRENT_WEEK = "本周新碟";
    private static final int DEFAULT_PAGE_TOTAL = 1;
    private static final String MANY_SINGERS = "群星";
    private static final int NUMBER_ROW = 2;
    private static final int PAGE_1 = 1;
    private static final int PAGE_SIZE = 10;
    private static final String TITLE_SEPARATOR = " - ";
    private static final String WEEK = "周";
    private a mAdapter;
    private com.sds.android.ttpod.activities.musiccircle.b mFooter;
    private ArrayList<b> mListDataList;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private com.sds.android.ttpod.widget.NetworkLoadView.b mOnLoadingViewStartLoadingListener = new com.sds.android.ttpod.widget.NetworkLoadView.b(this) {
        final /* synthetic */ NewAlbumFragment a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.mRequestState != c.REQUESTING) {
                this.a.requestAlbumList(1);
            }
        }
    };
    private q mPager = new q();
    private c mRequestState = c.IDLE;
    private FirstPublishNewAlbumResult mResult;

    private class a extends d {
        final /* synthetic */ NewAlbumFragment b;
        private LayoutInflater c;
        private ArrayList<b> d;
        private OnClickListener e = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                FirstPublishAlbumData firstPublishAlbumData = (FirstPublishAlbumData) view.getTag(R.id.view_bind_data);
                this.a.b.launchFragment(SubPostDetailFragment.createById(firstPublishAlbumData.getMsgId(), "first-publish"));
                new com.sds.android.ttpod.framework.a.b.b().b("new_album_item").a("songlist_id", String.valueOf(firstPublishAlbumData.getMsgId())).a("songlist_name", firstPublishAlbumData.getTitle()).a();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_ONLINE_NEW_ALBUM_ITEM.getValue(), s.PAGE_ONLINE_NEW_ALBUM.getValue(), s.PAGE_ONLINE_POST_DETAIL.getValue());
                sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(firstPublishAlbumData.getMsgId()));
                sUserEvent.append("song_list_name", firstPublishAlbumData.getTitle());
                sUserEvent.post();
            }
        };

        private class a {
            final /* synthetic */ a a;
            private View b;
            private TextView c;
            private TextView d;
            private ImageView e;

            public a(a aVar, View view) {
                this.a = aVar;
                this.b = view;
                this.c = (TextView) view.findViewById(R.id.item_name);
                this.d = (TextView) view.findViewById(R.id.item_artist);
                this.e = (ImageView) view.findViewById(R.id.item_picture);
            }
        }

        protected /* synthetic */ Object a(int i, int i2) {
            return c(i, i2);
        }

        public a(NewAlbumFragment newAlbumFragment) {
            this.b = newAlbumFragment;
            this.c = LayoutInflater.from(newAlbumFragment.getActivity());
        }

        public void a(ArrayList<b> arrayList) {
            this.d = arrayList;
            b();
            notifyDataSetChanged();
        }

        protected int a() {
            return this.d.size();
        }

        protected int a(int i) {
            if (i < a()) {
                b bVar = (b) this.d.get(i);
                if (bVar != null) {
                    ArrayList c = bVar.c();
                    if (c != null) {
                        return (int) Math.ceil(((double) c.size()) / 2.0d);
                    }
                }
            }
            return 0;
        }

        protected FirstPublishAlbumData c(int i, int i2) {
            if (i < this.d.size()) {
                b bVar = (b) this.d.get(i);
                if (bVar != null) {
                    ArrayList c = bVar.c();
                    if (c != null && i2 < c.size()) {
                        return (FirstPublishAlbumData) c.get(i2);
                    }
                }
            }
            return null;
        }

        protected Object c(int i) {
            return i < this.d.size() ? (b) this.d.get(i) : null;
        }

        protected View a(ViewGroup viewGroup) {
            return this.c.inflate(R.layout.new_disc_section_view, viewGroup, false);
        }

        protected View b(ViewGroup viewGroup) {
            View inflate = this.c.inflate(R.layout.new_disc_list_item, viewGroup, false);
            a aVar = new a(this, inflate.findViewById(R.id.song_item1));
            a aVar2 = new a(this, inflate.findViewById(R.id.song_item2));
            inflate.setTag(new a[]{aVar, aVar2});
            return inflate;
        }

        protected void a(int i, View view) {
            b bVar = (b) this.d.get(i);
            if (bVar != null) {
                CharSequence charSequence;
                View view2 = (TextView) view.findViewById(R.id.new_disc_section_week);
                View view3 = (TextView) view.findViewById(R.id.new_disc_section_year);
                if (i == 0) {
                    charSequence = NewAlbumFragment.CURRENT_WEEK;
                    view3.setVisibility(8);
                } else {
                    String str = String.valueOf(bVar.b()) + NewAlbumFragment.WEEK;
                    view3.setVisibility(0);
                    view3.setText(String.valueOf(bVar.a()));
                    Object obj = str;
                }
                view2.setText(charSequence);
                com.sds.android.ttpod.framework.modules.theme.c.a(view2, ThemeElement.TILE_TEXT);
                com.sds.android.ttpod.framework.modules.theme.c.a(view3, ThemeElement.TILE_SUB_TEXT);
                com.sds.android.ttpod.framework.modules.theme.c.a(view.findViewById(R.id.id_title_bar_left_line), ThemeElement.SONG_LIST_ITEM_INDICATOR);
                com.sds.android.ttpod.framework.modules.theme.c.a(view.findViewById(R.id.id_title_bar_layout), ThemeElement.TILE_BACKGROUND);
            }
        }

        protected void a(int i, int i2, View view) {
            Object tag = view.getTag();
            if (tag != null) {
                FirstPublishAlbumData c = c(i, i2 * 2);
                FirstPublishAlbumData c2 = c(i, (i2 * 2) + 1);
                a[] aVarArr = (a[]) tag;
                a(aVarArr[0], c);
                a(aVarArr[1], c2);
            }
        }

        private void a(a aVar, FirstPublishAlbumData firstPublishAlbumData) {
            if (aVar == null || firstPublishAlbumData == null) {
                a(aVar, false);
                return;
            }
            CharSequence charSequence;
            CharSequence charSequence2;
            a(aVar, true);
            String title = firstPublishAlbumData.getTitle();
            if (title.contains(NewAlbumFragment.TITLE_SEPARATOR)) {
                int indexOf = title.indexOf(NewAlbumFragment.TITLE_SEPARATOR);
                String substring = title.substring(0, indexOf);
                indexOf += NewAlbumFragment.TITLE_SEPARATOR.length();
                if (indexOf < title.length()) {
                    title = title.substring(indexOf);
                }
                String str = substring;
                charSequence = title;
                charSequence2 = str;
            } else {
                Object obj = title;
                Object obj2 = NewAlbumFragment.MANY_SINGERS;
            }
            aVar.c.setText(charSequence);
            aVar.d.setText(charSequence2);
            aVar.b.setTag(R.id.view_bind_data, firstPublishAlbumData);
            aVar.b.setOnClickListener(this.e);
            com.sds.android.ttpod.framework.modules.theme.c.a(aVar.c, ThemeElement.COMMON_TITLE_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(aVar.d, ThemeElement.COMMON_CONTENT_TEXT);
            ImageView d = aVar.e;
            if (d.getTag(R.id.view_bind_data) != firstPublishAlbumData.getPicUrl()) {
                d.setTag(R.id.view_bind_data, firstPublishAlbumData.getPicUrl());
                int d2 = com.sds.android.ttpod.common.c.a.d() / 2;
                g.a(d, firstPublishAlbumData.getPicUrl(), d2, d2, (int) R.drawable.img_background_song_publish);
            }
        }

        private void a(a aVar, boolean z) {
            if (aVar != null) {
                int i = z ? 0 : 4;
                aVar.c.setVisibility(i);
                aVar.d.setVisibility(i);
                aVar.b.setVisibility(i);
                aVar.e.setVisibility(i);
            }
        }
    }

    private class b {
        final /* synthetic */ NewAlbumFragment a;
        private int b;
        private int c;
        private ArrayList<FirstPublishAlbumData> d = new ArrayList();

        public b(NewAlbumFragment newAlbumFragment, int i, int i2, FirstPublishAlbumData firstPublishAlbumData) {
            this.a = newAlbumFragment;
            this.b = i;
            this.c = i2;
            this.d.add(firstPublishAlbumData);
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }

        public ArrayList<FirstPublishAlbumData> c() {
            return this.d;
        }
    }

    public NewAlbumFragment() {
        initBundle(s.PAGE_ONLINE_NEW_ALBUM, null);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mFooter = new com.sds.android.ttpod.activities.musiccircle.b(layoutInflater, null);
        return layoutInflater.inflate(R.layout.fragment_new_album_publish, viewGroup, false);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_new_album");
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().b((int) R.string.new_album_publish);
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.load_view);
        this.mListView = (ListView) view.findViewById(R.id.listview_new_disc);
        this.mAdapter = new a(this);
        this.mLoadingView.setOnStartLoadingListener(this.mOnLoadingViewStartLoadingListener);
        this.mListView.addFooterView(this.mFooter.a());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NewAlbumFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (m.b(i, i2, i3) && this.a.mRequestState != c.REQUESTING) {
                    this.a.requestAlbumList(this.a.mPager.a());
                }
            }
        });
        this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_NEW_ALBUM_PUBLISH_LIST, i.a(getClass(), "updateAlbumResult", FirstPublishNewAlbumResult.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updatePublishData(this.mResult);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
        this.mLoadingView.onThemeLoaded();
        this.mFooter.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
    }

    private void requestAlbumList(int i) {
        this.mRequestState = c.REQUESTING;
        if (i != 1) {
            this.mFooter.a(false, 0, getString(R.string.loading));
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_NEW_ALBUM_PUBLISH_LIST, Integer.valueOf(i), Integer.valueOf(10)));
    }

    public void updateAlbumResult(FirstPublishNewAlbumResult firstPublishNewAlbumResult) {
        this.mResult = firstPublishNewAlbumResult;
        e.a(this, firstPublishNewAlbumResult, new com.sds.android.ttpod.fragment.main.e.a<FirstPublishNewAlbumResult>(this) {
            final /* synthetic */ NewAlbumFragment a;

            {
                this.a = r1;
            }

            public void a(FirstPublishNewAlbumResult firstPublishNewAlbumResult) {
                this.a.updatePublishData(firstPublishNewAlbumResult);
            }
        });
    }

    private void updatePublishData(FirstPublishNewAlbumResult firstPublishNewAlbumResult) {
        if (firstPublishNewAlbumResult != null) {
            if (firstPublishNewAlbumResult.isSuccess()) {
                if (this.mPager.c() == 1) {
                    this.mPager.a(1);
                    this.mPager.b(firstPublishNewAlbumResult.getPageCount());
                    this.mPager.c(1);
                } else {
                    this.mFooter.a(true, 8, getString(R.string.load_comment_fail));
                }
                this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
                if (this.mListDataList == null) {
                    this.mListDataList = new ArrayList();
                }
                for (FirstPublishAlbumData firstPublishAlbumData : firstPublishNewAlbumResult.getDataList()) {
                    boolean z;
                    Iterator it = this.mListDataList.iterator();
                    while (it.hasNext()) {
                        b bVar = (b) it.next();
                        if (bVar.a() == firstPublishAlbumData.getYear() && bVar.b() == firstPublishAlbumData.getWeek()) {
                            if (!isAlubmDataAdded(bVar.c(), firstPublishAlbumData)) {
                                bVar.c().add(firstPublishAlbumData);
                            }
                            z = true;
                            if (!z) {
                                this.mListDataList.add(new b(this, firstPublishAlbumData.getYear(), firstPublishAlbumData.getWeek(), firstPublishAlbumData));
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                        this.mListDataList.add(new b(this, firstPublishAlbumData.getYear(), firstPublishAlbumData.getWeek(), firstPublishAlbumData));
                    }
                }
                if (this.mPager.d(this.mPager.d())) {
                    this.mListView.setOnScrollListener(null);
                } else {
                    this.mPager.c(this.mPager.d());
                }
                this.mAdapter.a(this.mListDataList);
                this.mRequestState = c.REQUESTED_SUCCESS;
                return;
            }
            if (this.mPager.c() == 1) {
                this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
            } else {
                this.mFooter.a(true, 8, getString(R.string.load_comment_fail));
            }
            this.mRequestState = c.REQUESTED_FAIL;
        }
    }

    private boolean isAlubmDataAdded(List<FirstPublishAlbumData> list, FirstPublishAlbumData firstPublishAlbumData) {
        for (FirstPublishAlbumData msgId : list) {
            if (msgId.getMsgId() == firstPublishAlbumData.getMsgId()) {
                return true;
            }
        }
        return false;
    }
}
