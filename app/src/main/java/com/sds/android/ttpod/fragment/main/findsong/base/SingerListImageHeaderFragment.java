package com.sds.android.ttpod.fragment.main.findsong.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.adapter.d;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.RoundedImageView;
import com.sds.android.ttpod.widget.SimpleGridView;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SingerListImageHeaderFragment extends SlidingClosableFragment {
    private static final int HEADER_MAX_SIZE = 8;
    private static final String[] SEGMENT_CHAR = new String[]{"A", VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY, "C", "D", "E", "F", "G", VersionUpdateConst.KEY_HIAPK_UPDATE_CATEGORY, "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", VersionUpdateConst.KEY_WANTOUJIA_UPDATE_CATEGORY, "X", "Y", "Z", "#"};
    protected AZSideBar mAZSideBar;
    protected View mFailedView;
    private ArrayList<SingerData> mHeaderSingerDataList = null;
    private String mHotCharacter;
    private int mId;
    protected ListView mListView;
    private OnClickListener mOnSingerClickListener = new OnClickListener(this) {
        final /* synthetic */ SingerListImageHeaderFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            SingerData singerData;
            SingerData singerData2 = (SingerData) view.getTag(R.id.view_bind_data);
            if (singerData2 == null) {
                View findViewById = view.findViewById(R.id.singer_item);
                if (findViewById != null) {
                    singerData = (SingerData) findViewById.getTag(R.id.view_bind_data);
                    if (singerData != null) {
                        p.a("library-music-singer_" + this.a.mTitle + "_" + o.a());
                        SingerDetailFragment.launch((BaseActivity) this.a.getActivity(), singerData.getName(), singerData.getId());
                        new SUserEvent("PAGE_CLICK", r.ACTION_SINGER_NAME.getValue(), this.a.mTitle, String.valueOf(s.PAGE_SINGER_MESSAGE.getValue())).append("singer_id", Integer.valueOf(singerData.getId())).post();
                        new com.sds.android.ttpod.framework.a.b.b().a("singer_id", String.valueOf(singerData.getId())).a("singer_name", singerData.getName()).a();
                    }
                }
            }
            singerData = singerData2;
            if (singerData != null) {
                p.a("library-music-singer_" + this.a.mTitle + "_" + o.a());
                SingerDetailFragment.launch((BaseActivity) this.a.getActivity(), singerData.getName(), singerData.getId());
                new SUserEvent("PAGE_CLICK", r.ACTION_SINGER_NAME.getValue(), this.a.mTitle, String.valueOf(s.PAGE_SINGER_MESSAGE.getValue())).append("singer_id", Integer.valueOf(singerData.getId())).post();
                new com.sds.android.ttpod.framework.a.b.b().a("singer_id", String.valueOf(singerData.getId())).a("singer_name", singerData.getName()).a();
            }
        }
    };
    private ArrayList<SingerData> mSingerDataList = null;
    private a mSingerListAdapter;
    protected StateView mStateView;
    private String mTitle;
    private b mTopSingerManager;

    private class a extends d {
        final /* synthetic */ SingerListImageHeaderFragment b;
        private List<Entry<String, ArrayList<SingerData>>> c;

        private class a {
            final /* synthetic */ a a;
            private TextView b;

            public a(a aVar, View view) {
                this.a = aVar;
                this.b = (TextView) view.findViewById(R.id.artist_name);
            }
        }

        private a(SingerListImageHeaderFragment singerListImageHeaderFragment) {
            this.b = singerListImageHeaderFragment;
        }

        public void a(ArrayList arrayList) {
            this.c = b(arrayList);
            b();
            notifyDataSetChanged();
        }

        public int a(String str) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                Entry entry = (Entry) this.c.get(i);
                if (entry != null && ((String) entry.getKey()).equalsIgnoreCase(str)) {
                    return f(i);
                }
            }
            return -1;
        }

        private List<Entry<String, ArrayList<SingerData>>> b(ArrayList<SingerData> arrayList) {
            HashMap hashMap = new HashMap();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                SingerData singerData = (SingerData) it.next();
                if (singerData != null) {
                    String buildKey = PinyinUtils.buildKey(singerData.getName());
                    if (!"".equals(buildKey) && buildKey.length() > 1) {
                        Object obj;
                        buildKey = buildKey.substring(0, 1).toUpperCase();
                        char charAt = buildKey.charAt(0);
                        if (charAt < 'A' || charAt > 'Z') {
                            obj = "#";
                        } else {
                            String str = buildKey;
                        }
                        ArrayList arrayList2 = (ArrayList) hashMap.get(obj);
                        if (arrayList2 != null) {
                            arrayList2.add(singerData);
                        } else {
                            arrayList2 = new ArrayList();
                            arrayList2.add(singerData);
                            hashMap.put(obj, arrayList2);
                        }
                    }
                }
            }
            return a(hashMap);
        }

        private List<Entry<String, ArrayList<SingerData>>> a(HashMap<String, ArrayList<SingerData>> hashMap) {
            List<Entry<String, ArrayList<SingerData>>> arrayList = new ArrayList(hashMap.entrySet());
            Collections.sort(arrayList, new Comparator<Entry<String, ArrayList<SingerData>>>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((Entry) obj, (Entry) obj2);
                }

                public int a(Entry<String, ArrayList<SingerData>> entry, Entry<String, ArrayList<SingerData>> entry2) {
                    if ("#".equals(entry.getKey())) {
                        return 1;
                    }
                    if ("#".equals(entry2.getKey())) {
                        return -1;
                    }
                    return ((String) entry.getKey()).compareTo((String) entry2.getKey());
                }
            });
            return arrayList;
        }

        protected int a() {
            return this.c.size();
        }

        protected int a(int i) {
            Entry entry = (Entry) this.c.get(i);
            ArrayList arrayList = entry != null ? (ArrayList) entry.getValue() : null;
            return arrayList != null ? arrayList.size() : 0;
        }

        protected Object a(int i, int i2) {
            ArrayList arrayList;
            Entry entry = (Entry) this.c.get(i);
            if (entry != null) {
                arrayList = (ArrayList) entry.getValue();
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                return (SingerData) arrayList.get(i2);
            }
            return null;
        }

        protected Object c(int i) {
            Entry entry = (Entry) this.c.get(i);
            return entry != null ? (String) entry.getKey() : null;
        }

        protected View a(ViewGroup viewGroup) {
            View inflate = this.a.inflate(R.layout.singer_list_section_header, viewGroup, false);
            inflate.setTag(new a(this, inflate));
            inflate.setClickable(false);
            return inflate;
        }

        protected View b(ViewGroup viewGroup) {
            View inflate = this.a.inflate(R.layout.singer_list_item, viewGroup, false);
            inflate.setTag(new a(this, inflate));
            return inflate;
        }

        protected void a(int i, View view) {
            Entry entry = (Entry) this.c.get(i);
            CharSequence charSequence = entry != null ? (String) entry.getKey() : null;
            if (!TextUtils.isEmpty(charSequence)) {
                a aVar = (a) view.getTag();
                c.a(aVar.b, ThemeElement.SUB_BAR_TEXT);
                c.a(aVar.b, ThemeElement.TILE_MASK);
                aVar.b.setText(charSequence);
            }
        }

        protected void a(int i, int i2, View view) {
            SingerData singerData = null;
            Entry entry = (Entry) this.c.get(i);
            ArrayList arrayList = entry != null ? (ArrayList) entry.getValue() : null;
            if (arrayList != null) {
                singerData = (SingerData) arrayList.get(i2);
            }
            if (singerData != null) {
                a aVar = (a) view.getTag();
                aVar.b.setText(singerData.getName());
                c.a(aVar.b, ThemeElement.SONG_LIST_ITEM_TEXT);
                c.a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
                c.a(view.findViewById(R.id.item_click_view), ThemeElement.HOME_ARROW_IMAGE);
                view.setTag(R.id.view_bind_data, singerData);
            }
        }
    }

    private class b {
        final /* synthetic */ SingerListImageHeaderFragment a;
        private Context b;
        private int c;
        private View d;
        private TextView e;
        private SimpleGridView f;
        private final int g = (com.sds.android.ttpod.common.c.a.d() / 4);

        public b(SingerListImageHeaderFragment singerListImageHeaderFragment, Context context) {
            this.a = singerListImageHeaderFragment;
            this.b = context;
            this.d = LayoutInflater.from(context).inflate(R.layout.fragment_singer_image_list_header, null);
            this.e = (TextView) this.d.findViewById(R.id.singer_header_name);
            this.f = (SimpleGridView) this.d.findViewById(R.id.singer_header_grid_view);
            if (this.f != null) {
                this.f.setNumColumns(4);
            }
            a();
        }

        public void a() {
            c.a(this.e, ThemeElement.SUB_BAR_TEXT);
            c.a(this.e, ThemeElement.TILE_MASK);
            this.c = c.d(ThemeElement.SONG_LIST_ITEM_TEXT);
        }

        View b() {
            return this.d;
        }

        void a(List<SingerData> list) {
            if (list != null && list.size() > 0 && this.f != null) {
                if (this.f.getChildCount() > 0) {
                    this.f.removeAllViews();
                }
                for (SingerData singerData : list) {
                    View c = c();
                    a(c, singerData);
                    this.f.addView(c);
                }
            }
        }

        View c() {
            return LayoutInflater.from(this.b).inflate(R.layout.picture_with_bottom_text, null);
        }

        void a(View view, SingerData singerData) {
            if (view != null && singerData != null) {
                TextView textView = (TextView) view.findViewById(R.id.item_name);
                View findViewById = view.findViewById(R.id.item_click_view);
                ImageView imageView = (RoundedImageView) view.findViewById(R.id.item_picture);
                textView.setGravity(17);
                textView.setTextColor(this.c);
                textView.setText(singerData.getName());
                findViewById.setTag(R.id.view_bind_data, singerData);
                findViewById.setTag(R.id.view_tag_index, singerData.getName());
                findViewById.setOnClickListener(this.a.mOnSingerClickListener);
                g.a(imageView, this.a.getImageUrl(singerData), this.g, this.g, (int) R.drawable.img_background_song_publish);
            }
        }
    }

    public SingerListImageHeaderFragment(String str, int i) {
        this.mId = i;
        this.mTitle = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSingerListAdapter = new a();
        setTBSPage("tt_singer_list_" + this.mTitle);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_CATEGORY_DETAIL, i.a(getClass(), "updateSingerListInfo", SingerListResult.class));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_singer_list, viewGroup, false);
        this.mListView = (ListView) this.mStateView.findViewById(R.id.media_listview);
        this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
        this.mTopSingerManager = new b(this, getActivity());
        this.mFailedView = layoutInflater.inflate(R.layout.loadingview_failed, null);
        this.mStateView.setFailedView(this.mFailedView);
        return this.mStateView;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mStateView = null;
        this.mListView = null;
        this.mFailedView = null;
        this.mAZSideBar = null;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().a(this.mTitle);
        setupListView();
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        this.mStateView.setOnRetryRequestListener(new com.sds.android.ttpod.widget.StateView.a(this) {
            final /* synthetic */ SingerListImageHeaderFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                if (this.a.mStateView != null) {
                    this.a.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
                    this.a.requestDataList(1);
                }
            }
        });
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SingerListImageHeaderFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.mOnSingerClickListener.onClick(view);
            }
        });
        this.mAZSideBar.setIndexWords(SEGMENT_CHAR);
        this.mAZSideBar.setOnMatchedPositionChangeListener(new com.sds.android.ttpod.widget.AZSideBar.a(this) {
            final /* synthetic */ SingerListImageHeaderFragment a;

            {
                this.a = r1;
            }

            public void a(int i, String str) {
                this.a.selectSection(i, str);
            }
        });
        this.mListView.setOnScrollListener(this.mAZSideBar);
        this.mHotCharacter = getString(R.string.hot_singer_character);
        requestDataList(1);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
        this.mAZSideBar.onThemeLoaded();
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mSingerListAdapter.notifyDataSetChanged();
        if (this.mTopSingerManager != null) {
            this.mTopSingerManager.a();
        }
    }

    public void updateSingerListInfo(SingerListResult singerListResult) {
        int i = 8;
        if (isViewAccessAble()) {
            Collection dataList = singerListResult.getDataList();
            if (dataList == null || dataList.size() <= 0) {
                this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
                return;
            }
            if (dataList.size() <= 8) {
                i = 0;
            }
            this.mHeaderSingerDataList = new ArrayList();
            if (i > 0) {
                this.mHeaderSingerDataList.addAll(dataList.subList(0, i));
            }
            this.mSingerDataList = new ArrayList();
            this.mSingerDataList.addAll(dataList);
            com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<Object, List<String>>(this, null) {
                final /* synthetic */ SingerListImageHeaderFragment a;

                protected /* synthetic */ Object onDoInBackground(Object obj) {
                    return a(obj);
                }

                protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                    a((List) obj);
                }

                protected List<String> a(Object obj) {
                    return this.a.buildRawAZKeys(this.a.mSingerDataList);
                }

                protected void a(List<String> list) {
                    if (this.a.isViewAccessAble()) {
                        this.a.updateAZKeys(list);
                        this.a.mTopSingerManager.a(this.a.mHeaderSingerDataList);
                        this.a.mSingerListAdapter.a(this.a.mSingerDataList);
                        this.a.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
                    }
                }
            });
        }
    }

    private void setupListView() {
        this.mListView.addHeaderView(this.mTopSingerManager.b());
        this.mListView.setAdapter(this.mSingerListAdapter);
    }

    private List<String> buildRawAZKeys(ArrayList<SingerData> arrayList) {
        List<String> arrayList2 = new ArrayList();
        Collections.sort(arrayList, new Comparator<SingerData>(this) {
            final /* synthetic */ SingerListImageHeaderFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((SingerData) obj, (SingerData) obj2);
            }

            public int a(SingerData singerData, SingerData singerData2) {
                return PinyinUtils.buildKey(singerData.getName()).compareTo(PinyinUtils.buildKey(singerData2.getName()));
            }
        });
        Iterator it = arrayList.iterator();
        String str = null;
        while (it.hasNext()) {
            SingerData singerData = (SingerData) it.next();
            String buildKey = PinyinUtils.buildKey(singerData.getName());
            if (str == null) {
                arrayList2.add(buildKey.charAt(0) + "");
            } else if (str.charAt(0) != buildKey.charAt(0)) {
                arrayList2.add(buildKey.charAt(0) + "");
            }
            arrayList2.add(singerData.getName());
            str = buildKey;
        }
        return arrayList2;
    }

    protected void updateAZKeys(List<String> list) {
        com.sds.android.sdk.lib.util.d.a((Object) list, "AZKeys");
        if (isViewAccessAble()) {
            list.add(0, this.mHotCharacter);
            this.mAZSideBar.a((List) list);
            this.mAZSideBar.a(this.mHotCharacter, 0);
            this.mAZSideBar.setVisibility(0);
        }
    }

    private void selectSection(int i, String str) {
        int i2 = 0;
        if (!str.equals(this.mHotCharacter)) {
            i2 = this.mSingerListAdapter.a(str);
            i2 = i2 >= 0 ? i2 + this.mListView.getHeaderViewsCount() : -1;
        }
        if (i2 >= 0) {
            this.mListView.requestFocus();
            this.mListView.setSelection(i2);
        }
    }

    protected void requestDataList(int i) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SINGER_CATEGORY_DETAIL, Integer.valueOf(this.mId), Integer.valueOf(i)));
    }

    private String getImageUrl(SingerData singerData) {
        if (singerData == null) {
            return null;
        }
        return com.sds.android.ttpod.b.b.a((long) singerData.getId());
    }
}
