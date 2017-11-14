package com.sds.android.ttpod.fragment.main.findsong;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongMoreResult;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongMoreResult.AlbumData;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.adapter.d;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.NewSongPublishHeaderView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class NewSongPublishFragment extends SlidingClosableFragment implements OnItemClickListener {
    private static final int DEFAULT_PAGE_TOTAL = 1;
    private static final int MAX_HEADER_VIEW_NUMBER = 3;
    private static final int PAGE_1 = 1;
    private static final int PAGE_SIZE = 30;
    private a mAdapter;
    private ArrayList<AlbumData> mAlbumList;
    private com.sds.android.ttpod.activities.musiccircle.b mFooter;
    private ArrayList<MusicCircleFirstPublish> mHeaderDataList;
    private NewSongPublishHeaderView mListHeader;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private com.sds.android.ttpod.widget.NetworkLoadView.b mOnLoadingViewStartLoadingListener = new com.sds.android.ttpod.widget.NetworkLoadView.b(this) {
        final /* synthetic */ NewSongPublishFragment a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.mRequestState != c.REQUESTING) {
                this.a.requestDataList(1);
            }
        }
    };
    private com.sds.android.ttpod.widget.SimpleSongView.b mOnSectionViewItemClickListener = new com.sds.android.ttpod.widget.SimpleSongView.b(this) {
        final /* synthetic */ NewSongPublishFragment a;

        {
            this.a = r1;
        }

        public void a(Object obj) {
            this.a.launchFragment(SubPostDetailFragment.createById(((MusicCircleFirstPublish) obj).getMsgId(), "first-publish"));
        }
    };
    private q mPager = new q();
    private c mRequestState = c.IDLE;
    private FirstPublishNewSongMoreResult mResult;

    private class a extends d {
        final /* synthetic */ NewSongPublishFragment b;
        private Activity c;
        private LayoutInflater d = LayoutInflater.from(BaseApplication.e());
        private ArrayList<AlbumData> e;
        private boolean f = false;
        private OnClickListener g = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b.launchFragment(SubPostDetailFragment.createById(((AlbumData) view.getTag(R.id.view_bind_data)).getMsgId(), "first-publish"));
            }
        };

        protected /* synthetic */ Object a(int i, int i2) {
            return c(i, i2);
        }

        public a(NewSongPublishFragment newSongPublishFragment) {
            this.b = newSongPublishFragment;
        }

        public void a(Activity activity) {
            this.c = activity;
        }

        public void a(ArrayList arrayList) {
            this.e = arrayList;
            b();
            notifyDataSetChanged();
        }

        protected int a() {
            return 1;
        }

        protected int a(int i) {
            if (this.e != null) {
                return (int) Math.ceil(((double) this.e.size()) / 3.0d);
            }
            return 0;
        }

        protected AlbumData c(int i, int i2) {
            if (this.e == null || i2 >= this.e.size()) {
                return null;
            }
            return (AlbumData) this.e.get(i2);
        }

        protected Object c(int i) {
            if (this.e != null) {
                return this.e;
            }
            return null;
        }

        protected View a(ViewGroup viewGroup) {
            return this.d.inflate(R.layout.new_song_publish_section_view, viewGroup, false);
        }

        protected View b(ViewGroup viewGroup) {
            View inflate = this.d.inflate(R.layout.new_song_publish_list_item, viewGroup, false);
            b bVar = new b(inflate.findViewById(R.id.song_item1));
            b bVar2 = new b(inflate.findViewById(R.id.song_item2));
            b bVar3 = new b(inflate.findViewById(R.id.song_item3));
            inflate.setTag(new b[]{bVar, bVar2, bVar3});
            return inflate;
        }

        protected void a(int i, View view) {
            if (this.e != null) {
                View findViewById = view.findViewById(R.id.new_song_publish_divider_line);
                View view2 = (TextView) view.findViewById(R.id.new_song_publish_section_year);
                ((TextView) view.findViewById(R.id.new_song_publish_section_week)).setVisibility(8);
                view2.setVisibility(8);
                com.sds.android.ttpod.framework.modules.theme.c.a(findViewById, ThemeElement.COMMON_SEPARATOR);
                com.sds.android.ttpod.framework.modules.theme.c.a(view2, ThemeElement.TILE_TEXT);
            }
        }

        protected void a(int i, int i2, View view) {
            AlbumData c = c(i, i2 * 3);
            AlbumData c2 = c(i, (i2 * 3) + 1);
            AlbumData c3 = c(i, (i2 * 3) + 2);
            Object tag = view.getTag();
            if (tag != null) {
                b[] bVarArr = (b[]) tag;
                a(bVarArr[0], c);
                a(bVarArr[1], c2);
                a(bVarArr[2], c3);
            }
        }

        private void a(b bVar, AlbumData albumData) {
            if (bVar == null || albumData == null) {
                a(bVar, false);
                return;
            }
            a(bVar, true);
            bVar.d.setVisibility(4);
            bVar.b.setText(albumData.getTitle());
            bVar.a.setTag(R.id.view_bind_data, albumData);
            bVar.a.setOnClickListener(this.g);
            com.sds.android.ttpod.framework.modules.theme.c.a(bVar.b, ThemeElement.COMMON_CONTENT_TEXT);
            int d = com.sds.android.ttpod.common.c.a.d() / 4;
            g.a(bVar.c, albumData.getPicUrl(), d, d, (int) R.drawable.img_background_song_publish);
        }

        private void a(b bVar, boolean z) {
            if (bVar != null) {
                int i = z ? 0 : 4;
                bVar.d.setVisibility(i);
                bVar.b.setVisibility(i);
                bVar.a.setVisibility(i);
                bVar.c.setVisibility(i);
            }
        }
    }

    public static class b {
        private View a;
        private TextView b;
        private ImageView c;
        private ImageView d;

        public b(View view) {
            this.a = view.findViewById(R.id.item_click_view);
            this.b = (TextView) view.findViewById(R.id.item_name);
            this.c = (ImageView) view.findViewById(R.id.item_picture);
            this.d = (ImageView) view.findViewById(R.id.first_publish_flag);
        }

        public View a() {
            return this.a;
        }

        public TextView b() {
            return this.b;
        }

        public ImageView c() {
            return this.c;
        }

        public ImageView d() {
            return this.d;
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MORE_NEW_SONG_PUBLISH_LIST, i.a(getClass(), "updatePublishResult", FirstPublishNewSongMoreResult.class));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mFooter = new com.sds.android.ttpod.activities.musiccircle.b(layoutInflater, new OnClickListener(this) {
            final /* synthetic */ NewSongPublishFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestDataList(this.a.mPager.a());
            }
        });
        this.mFooter.onThemeLoaded();
        return layoutInflater.inflate(R.layout.fragment_new_song_publish, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().b((int) R.string.new_song_publish);
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.market_load_view);
        this.mListView = (ListView) view.findViewById(R.id.market_app_list);
        this.mListHeader = new NewSongPublishHeaderView(getActivity());
        this.mAdapter = new a(this);
        this.mAdapter.a(getActivity());
        this.mListView.setOnScrollListener(new com.sds.android.ttpod.b.m.a());
        this.mLoadingView.setOnStartLoadingListener(this.mOnLoadingViewStartLoadingListener);
        this.mListHeader.setOnSectionViewItemClickListener(this.mOnSectionViewItemClickListener);
        this.mListView.addHeaderView(this.mListHeader);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setDivider(null);
        this.mListView.setOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ NewSongPublishFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (m.b(i, i2, i3) && this.a.mRequestState != c.REQUESTING) {
                    this.a.requestDataList(this.a.mPager.a());
                }
            }
        });
        this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updatePublishData(this.mResult);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mListHeader.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
        this.mLoadingView.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
    }

    protected void requestDataList(int i) {
        this.mRequestState = c.REQUESTING;
        if (i != 1) {
            this.mFooter.a(false, 0, getString(R.string.loading));
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_MORE_NEW_SONG_PUBLISH_LIST, Integer.valueOf(i), Integer.valueOf(30)));
    }

    public void updatePublishResult(FirstPublishNewSongMoreResult firstPublishNewSongMoreResult) {
        this.mResult = firstPublishNewSongMoreResult;
        e.a(this, firstPublishNewSongMoreResult, new com.sds.android.ttpod.fragment.main.e.a<FirstPublishNewSongMoreResult>(this) {
            final /* synthetic */ NewSongPublishFragment a;

            {
                this.a = r1;
            }

            public void a(FirstPublishNewSongMoreResult firstPublishNewSongMoreResult) {
                this.a.updatePublishData(firstPublishNewSongMoreResult);
            }
        });
    }

    public void updatePublishData(FirstPublishNewSongMoreResult firstPublishNewSongMoreResult) {
        if (firstPublishNewSongMoreResult != null) {
            if (firstPublishNewSongMoreResult.isSuccess()) {
                if (this.mPager.c() == 1) {
                    this.mListView.addFooterView(this.mFooter.a());
                    this.mPager.a(1);
                    this.mPager.b(firstPublishNewSongMoreResult.getPageCount());
                    this.mPager.c(1);
                    this.mHeaderDataList = firstPublishNewSongMoreResult.getSingleList();
                    this.mListHeader.a(this.mHeaderDataList, 3);
                }
                this.mLoadingView.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
                if (this.mAlbumList == null) {
                    this.mAlbumList = new ArrayList();
                }
                this.mAlbumList.addAll(firstPublishNewSongMoreResult.getDataList());
                if (this.mPager.d(this.mPager.d())) {
                    this.mListView.removeFooterView(this.mFooter.a());
                    this.mListView.setOnScrollListener(null);
                } else {
                    this.mPager.c(this.mPager.d());
                }
                this.mAdapter.a(this.mAlbumList);
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

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }
}
