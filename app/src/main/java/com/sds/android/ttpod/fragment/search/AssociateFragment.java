package com.sds.android.ttpod.fragment.search;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.SearchData;
import com.sds.android.cloudapi.ttpod.data.SearchData.KeywordData;
import com.sds.android.cloudapi.ttpod.data.SearchData.SearchMV;
import com.sds.android.cloudapi.ttpod.data.SearchData.SingerData;
import com.sds.android.cloudapi.ttpod.data.SearchData.SongData;
import com.sds.android.cloudapi.ttpod.result.OnlineSearchResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssociateFragment extends BaseFragment {
    private static final int RIGHT_ARRAW_PADDING = com.sds.android.ttpod.common.c.a.a(1);
    private static final String TAG = "AssociateFragment";
    private static SparseArray<MediaItem> sMediaItemArray;
    private static SparseArray<MvData> sMvArray;
    private a mAdapter;
    private int mClickedId;
    private b mClickedViewHolder;
    private boolean mGettingMvInfo;
    private boolean mGettingSongInfo;
    private String mInputWord;
    private ListView mListView;
    private boolean mLongClick;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener(this) {
        final /* synthetic */ AssociateFragment a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (m.a(this.a.mListView.getHeaderViewsCount(), i, this.a.mAdapter.getCount()) >= 0) {
                this.a.mClickedViewHolder = (b) view.getTag();
                Object a = this.a.mClickedViewHolder.h;
                if (a instanceof SingerData) {
                    this.a.onResultClick((SingerData) a);
                } else if (a instanceof KeywordData) {
                    this.a.onResultClick((KeywordData) a);
                } else if (a instanceof SongData) {
                    this.a.onResultClick((SongData) a);
                } else if (a instanceof SearchMV) {
                    this.a.onResultClick((SearchMV) a);
                }
            }
        }
    };
    private OnItemLongClickListener mOnItemLongClickListener = new OnItemLongClickListener(this) {
        final /* synthetic */ AssociateFragment a;

        {
            this.a = r1;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            b bVar = (b) view.getTag();
            if (!(bVar.h instanceof SongData)) {
                return false;
            }
            this.a.mClickedViewHolder = bVar;
            this.a.mLongClick = true;
            SongData songData = (SongData) bVar.h;
            this.a.tryGetSongInfo(songData);
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTOIN_LONG_CLICK_SEARCH_ASSOCIATE_SONG.getValue(), s.PAGE_NONE.getValue());
            sUserEvent.append(MediasColumns.SONG_ID, Integer.valueOf(songData.getSongId()));
            c.a(sUserEvent, this.a.mInputWord, this.a.mShowCount);
            return true;
        }
    };
    private OnlineSearchResult mOnlineSearchResult;
    private View mRootView;
    private int mShowCount;

    private final class a extends BaseAdapter {
        final /* synthetic */ AssociateFragment a;
        private ArrayList<Object> b;
        private ArrayList<SongData> c;
        private ArrayList<SearchMV> d;
        private Context e;
        private SparseArray<ArrayList> f;

        private a(AssociateFragment associateFragment, Context context) {
            this.a = associateFragment;
            this.b = new ArrayList();
            this.f = new SparseArray();
            this.e = context;
        }

        public void a(SearchData searchData) {
            this.b.clear();
            this.c = null;
            this.d = null;
            this.f.clear();
            if (searchData != null) {
                String str;
                if (searchData.getSingerList() != null) {
                    this.f.append(48, searchData.getSingerList());
                }
                if (searchData.getKeywordList() != null) {
                    this.f.append(49, searchData.getKeywordList());
                }
                this.c = searchData.getSongList();
                if (this.c != null) {
                    this.f.append(50, this.c);
                }
                this.d = searchData.getMvList();
                if (this.d != null) {
                    this.f.append(51, this.d);
                }
                String order = searchData.getOrder();
                if (com.sds.android.sdk.lib.util.m.a(order)) {
                    str = "0123";
                } else {
                    str = order;
                }
                for (int i = 0; i < str.length(); i++) {
                    ArrayList arrayList = (ArrayList) this.f.get(str.charAt(i));
                    if (arrayList != null) {
                        this.b.addAll(arrayList);
                    }
                }
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b != null ? this.b.size() : 0;
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.e).inflate(R.layout.fragment_online_search_associate_item, null);
                b bVar2 = new b(view);
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            Object obj = this.b.get(i);
            if (obj instanceof SingerData) {
                bVar.a((SingerData) obj, i);
            } else if (obj instanceof KeywordData) {
                bVar.a((KeywordData) obj, i);
            } else if (obj instanceof SongData) {
                bVar.a(this.c, (SongData) obj, i);
            } else if (obj instanceof SearchMV) {
                bVar.a(this.d, (SearchMV) obj, i);
            }
            bVar.b();
            return view;
        }
    }

    private final class b {
        final /* synthetic */ AssociateFragment a;
        private View b;
        private IconTextView c;
        private IconTextView d;
        private TextView e;
        private TextView f;
        private TextView g;
        private Object h;
        private int i;

        private int a() {
            return this.i;
        }

        private b(AssociateFragment associateFragment, View view) {
            this.a = associateFragment;
            this.b = view;
            this.c = (IconTextView) view.findViewById(R.id.itv_icon);
            this.d = (IconTextView) view.findViewById(R.id.itv_operate);
            this.e = (TextView) view.findViewById(R.id.tv_title);
            this.f = (TextView) view.findViewById(R.id.tv_title_right);
            this.g = (TextView) view.findViewById(R.id.tv_subtitle);
        }

        private void a(SingerData singerData, int i) {
            this.h = singerData;
            CharSequence singerName = singerData.getSingerName();
            String aliasName = singerData.getAliasName();
            if (!com.sds.android.sdk.lib.util.m.a(aliasName)) {
                singerName = singerName + "(" + aliasName + ")";
            }
            this.e.setText(singerName);
            this.f.setText(null);
            this.c.setText((int) R.string.icon_media_menu_singer);
            this.d.setText((int) R.string.icon_arrow_right);
            this.d.setPadding(AssociateFragment.RIGHT_ARRAW_PADDING, 0, AssociateFragment.RIGHT_ARRAW_PADDING, 0);
            this.d.setContentDescription("operate" + i);
            Resources resources = this.e.getContext().getResources();
            this.g.setText(resources.getString(R.string.singer_song_album_num, new Object[]{Integer.valueOf(singerData.getSongCount()), Integer.valueOf(singerData.getAlbumCount())}));
            this.g.setVisibility(0);
        }

        private void a(KeywordData keywordData, int i) {
            this.h = keywordData;
            this.e.setText(keywordData.getKeyword());
            this.f.setText(null);
            this.c.setText((int) R.string.icon_search);
            this.d.setText((int) R.string.icon_singer_search_arraw);
            this.d.setPadding(0, 0, 0, 0);
            this.d.setContentDescription("operate" + i);
            this.g.setVisibility(8);
        }

        private void a(ArrayList<SongData> arrayList, SongData songData, int i) {
            this.h = songData;
            this.e.setText(songData.getSongName() + " - ");
            this.f.setText(songData.getSingerName());
            this.c.setText((int) R.string.icon_search_result_no_song);
            this.d.setText((int) R.string.icon_playbar_play);
            this.d.setPadding(0, 0, 0, 0);
            this.d.setContentDescription("operate" + i);
            this.g.setVisibility(8);
            this.i = arrayList.indexOf(songData);
        }

        private void a(ArrayList<SearchMV> arrayList, SearchMV searchMV, int i) {
            this.h = searchMV;
            this.e.setText(searchMV.getName() + " - ");
            this.f.setText(searchMV.getSingerName());
            this.c.setText((int) R.string.icon_play_mv);
            this.d.setText((int) R.string.icon_playbar_play);
            this.d.setPadding(0, 0, 0, 0);
            this.d.setContentDescription("operate" + i);
            this.g.setVisibility(8);
            this.i = arrayList.indexOf(searchMV);
        }

        private void b() {
            com.sds.android.ttpod.framework.modules.theme.c.a(this.b, ThemeElement.TILE_BACKGROUND);
            v.a(this.c, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            v.a(this.d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.e, ThemeElement.SONG_LIST_ITEM_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.f, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            com.sds.android.ttpod.framework.modules.theme.c.a(this.g, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        }
    }

    public interface c {
        void onKeywordClicked(KeywordData keywordData);

        void onMvClicked(MvData mvData, int i);

        void onSingerClicked(SingerData singerData);

        void onSongClicked(MediaItem mediaItem, int i);
    }

    public static void clearCache() {
        if (sMediaItemArray != null) {
            sMediaItemArray.clear();
            sMediaItemArray = null;
        }
        if (sMvArray != null) {
            sMvArray.clear();
            sMvArray = null;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setStatisticPage(s.PAGE_SEARCH_ASSOCIATE);
        setTBSPage("tt_search_associative");
        this.mRootView = layoutInflater.inflate(R.layout.fragment_online_search_associate, viewGroup, false);
        this.mListView = (ListView) this.mRootView;
        this.mListView.setOnItemClickListener(this.mOnItemClickListener);
        this.mListView.setOnItemLongClickListener(this.mOnItemLongClickListener);
        this.mAdapter = new a(getActivity());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ AssociateFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    ((OnlineSearchFragment) this.a.getParentFragment()).hideSoftInputFromWindow();
                }
                return false;
            }
        });
        return this.mRootView;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mAdapter.notifyDataSetChanged();
    }

    public void updateAssociate(OnlineSearchResult onlineSearchResult, String str) {
        this.mOnlineSearchResult = onlineSearchResult;
        this.mInputWord = str;
        if (isLoadFinished() && this.mAdapter != null) {
            d.r.a("search_associative");
            trackPlaySong("search", "search_" + this.mInputWord, true);
            trackSearch(d.r.a(), this.mInputWord);
            this.mAdapter.a(onlineSearchResult.isSuccess() ? (SearchData) onlineSearchResult.getData() : null);
            this.mShowCount++;
            int code = onlineSearchResult.getCode() == 1 ? this.mAdapter.getCount() > 0 ? 1 : 0 : onlineSearchResult.getCode();
            c.a(r.ACTION_STATE_SEARCH_ASSOCIATE, code);
        }
    }

    int popShowCount() {
        int i = this.mShowCount;
        this.mShowCount = 0;
        return i;
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mOnlineSearchResult != null) {
            updateAssociate(this.mOnlineSearchResult, this.mInputWord);
        }
    }

    private void onResultClick(KeywordData keywordData) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            doAlibabaStats(keywordData.getKeyword(), "word");
            ((c) parentFragment).onKeywordClicked(keywordData);
        }
    }

    private void onResultClick(SingerData singerData) {
        doAlibabaStats(singerData.getSingerName(), "singer");
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            ((c) parentFragment).onSingerClicked(singerData);
        }
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTOIN_CLICK_SEARCH_ASSOCIATE_SINGER.getValue(), s.PAGE_NONE.getValue(), s.PAGE_SINGER_MESSAGE.getValue());
        sUserEvent.append("singer_id", Long.valueOf(singerData.getSingerId()));
        c.a(sUserEvent, this.mInputWord, this.mShowCount);
    }

    private void onResultClick(SongData songData) {
        doAlibabaStats(songData.getSongName(), "song");
        this.mLongClick = false;
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTOIN_CLICK_SEARCH_ASSOCIATE_SONG.getValue(), s.PAGE_NONE.getValue(), s.PAGE_PORTRAIT_PLAYER.getValue());
        sUserEvent.append(MediasColumns.SONG_ID, Integer.valueOf(songData.getSongId()));
        c.a(sUserEvent, this.mInputWord, this.mShowCount);
        tryGetSongInfo(songData);
    }

    private void onResultClick(SearchMV searchMV) {
        doAlibabaStats(searchMV.getName(), "mv");
        this.mLongClick = false;
        tryGetMvInfo(searchMV);
    }

    private void doAlibabaStats(String str, String str2) {
        d.r.a("search_associative");
        d.r.a(str, this.mInputWord, str2);
        BaseFragment baseFragment = (BaseFragment) getParentFragment();
        if (baseFragment != null) {
            baseFragment.trackPlaySong("search", "search_" + this.mInputWord, true);
            baseFragment.trackSearch(d.r.a(), this.mInputWord);
        }
    }

    private void tryGetSongInfo(SongData songData) {
        this.mClickedId = songData.getSongId();
        if (sMediaItemArray == null) {
            sMediaItemArray = new SparseArray();
        }
        if (sMediaItemArray.indexOfKey(this.mClickedId) >= 0) {
            updateSongInfoFinished(null);
        } else if (!this.mGettingSongInfo) {
            requestOnlineMediaInfo();
        }
    }

    private void tryGetMvInfo(SearchMV searchMV) {
        this.mClickedId = searchMV.getId();
        if (sMvArray == null) {
            sMvArray = new SparseArray();
        }
        if (sMvArray.indexOfKey(this.mClickedId) >= 0) {
            updateMvInfoFinished(null);
        } else if (!this.mGettingMvInfo) {
            requestOnlineMvInfo();
        }
    }

    private void updateSongInfoFinished(List<MediaItem> list) {
        if (sMediaItemArray != null && isViewAccessAble()) {
            MediaItem mediaItem;
            if (list != null) {
                for (MediaItem mediaItem2 : list) {
                    sMediaItemArray.put(mediaItem2.getSongID().intValue(), mediaItem2);
                }
            }
            if (this.mClickedViewHolder != null && (this.mClickedViewHolder.h instanceof SongData)) {
                mediaItem2 = (MediaItem) sMediaItemArray.get(this.mClickedId);
                if (mediaItem2 == null) {
                    requestOnlineMediaInfo();
                    return;
                }
                int b = this.mClickedViewHolder.a();
                if (this.mLongClick) {
                    f.a(getActivity(), mediaItem2, null, b);
                    return;
                }
                Fragment parentFragment = getParentFragment();
                if (parentFragment != null) {
                    ((c) parentFragment).onSongClicked(mediaItem2, b);
                }
            }
        }
    }

    private void updateMvInfoFinished(List<MvData> list) {
        if (sMvArray != null && isViewAccessAble()) {
            MvData mvData;
            if (list != null) {
                for (MvData mvData2 : list) {
                    sMvArray.put(mvData2.getId(), mvData2);
                }
            }
            if (this.mClickedViewHolder != null && (this.mClickedViewHolder.h instanceof SearchMV)) {
                mvData2 = (MvData) sMvArray.get(this.mClickedId);
                if (mvData2 == null) {
                    requestOnlineMvInfo();
                    return;
                }
                int b = this.mClickedViewHolder != null ? this.mClickedViewHolder.a() : 0;
                if (this.mLongClick) {
                    com.sds.android.sdk.lib.util.d.a();
                    return;
                }
                Fragment parentFragment = getParentFragment();
                if (parentFragment != null) {
                    ((c) parentFragment).onMvClicked(mvData2, b);
                }
            }
        }
    }

    private void requestOnlineMediaInfo() {
        ArrayList a = this.mAdapter.c;
        if (a != null) {
            if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
                this.mGettingSongInfo = true;
                List arrayList = new ArrayList(a.size());
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    arrayList.add(Long.valueOf((long) ((SongData) it.next()).getSongId()));
                }
                o.b(arrayList, new com.sds.android.ttpod.framework.a.o.a<List<MediaItem>>(this) {
                    final /* synthetic */ AssociateFragment a;

                    {
                        this.a = r1;
                    }

                    public void a(List<MediaItem> list) {
                        this.a.mGettingSongInfo = false;
                        this.a.updateSongInfoFinished(list);
                    }
                });
                return;
            }
            f.a((int) R.string.version_update_network_bad);
        }
    }

    private void requestOnlineMvInfo() {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
            ArrayList b = this.mAdapter.d;
            if (b != null) {
                this.mGettingMvInfo = true;
                List arrayList = new ArrayList(b.size());
                Iterator it = b.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(((SearchMV) it.next()).getId()));
                }
                o.c(arrayList, new com.sds.android.ttpod.framework.a.o.a<List<MvData>>(this) {
                    final /* synthetic */ AssociateFragment a;

                    {
                        this.a = r1;
                    }

                    public void a(List<MvData> list) {
                        this.a.mGettingMvInfo = false;
                        this.a.updateMvInfoFinished(list);
                    }
                });
                return;
            }
            return;
        }
        f.a((int) R.string.version_update_network_bad);
    }
}
