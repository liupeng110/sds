package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.SearchData.KeywordData;
import com.sds.android.cloudapi.ttpod.data.SearchData.SingerData;
import com.sds.android.cloudapi.ttpod.result.OnlineSearchResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.adapter.f.a;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.c.b;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.fragment.search.AssociateFragment.c;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.d.r;
import com.sds.android.ttpod.framework.a.b.j;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.HotwordView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OnlineSearchFragment extends SlidingClosableFragment implements c, b {
    private static final int BILLBOARD_MAX_SIZE = 20;
    private static final int SHOW_INPUT_METHOD_DELAY = 500;
    private static final String TAG = "SearchResultFragment";
    private static boolean sGotBillboard;
    private AssociateFragment mAssociateFragment;
    private Button mBtnClearHistory;
    private IconTextView mClearImageView;
    private OnlineSearchDetailFragment mDetailFragment;
    private View mHeaderView;
    private a mHistoryAdapter;
    private HotwordView mHotwordView;
    private EditText mInputEditText;
    private InputMethodManager mInputMethodManager;
    private boolean mLastActionAssociate;
    private ListView mListView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ OnlineSearchFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_search_clear:
                    this.a.mInputEditText.setText("");
                    this.a.showSoftInputFromWindow();
                    return;
                case R.id.menu_view:
                    new b(this.a.getActivity()).a((MediaItem) view.getTag(), p.b());
                    return;
                case R.id.button_search_clear_history:
                    com.sds.android.ttpod.framework.a.b.b.a("clear_history");
                    this.a.mSearchHistory.a();
                    this.a.flushHistoryView(null);
                    return;
                default:
                    return;
            }
        }
    };
    private OnEditorActionListener mOnEditorActionListener = new OnEditorActionListener(this) {
        final /* synthetic */ OnlineSearchFragment a;

        {
            this.a = r1;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 3 && i != 2 && i != 0 && i != 4) {
                return false;
            }
            this.a.searchKeyword(this.a.mInputEditText.getText().toString());
            return true;
        }
    };
    private HotwordView.a mOnHotwordClickListener = new HotwordView.a(this) {
        final /* synthetic */ OnlineSearchFragment a;

        {
            this.a = r1;
        }

        public void a(String str) {
            this.a.searchHotword(str);
        }
    };
    private String mOrigin = "search-button";
    private View mRootView;
    private com.sds.android.ttpod.component.a.a mSearchAction;
    private com.sds.android.ttpod.component.e.a mSearchHistory;
    private View mSearchInputLayout;
    private TextWatcher mTextWatcher = new TextWatcher(this) {
        final /* synthetic */ OnlineSearchFragment a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.a.mLastActionAssociate = true;
            if (m.a(charSequence.toString())) {
                this.a.mClearImageView.setVisibility(8);
                this.a.jumpToHomepage();
                return;
            }
            this.a.mClearImageView.setVisibility(0);
            this.a.startOnlineAssociateSearch(charSequence.toString());
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    private TextView mTvHistoryHint;
    private TextView mTvHotwordHint;
    private String mWord;

    public static OnlineSearchFragment instantiate(String str, int i) {
        OnlineSearchFragment onlineSearchFragment = new OnlineSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("search_word", str);
        bundle.putInt("app", i);
        onlineSearchFragment.setArguments(bundle);
        return onlineSearchFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mWord = getArguments().getString("search_word");
            u.d(this.mWord);
        }
        String uuid = UUID.randomUUID().toString();
        u.b(uuid);
        j.b(uuid);
        j.a(this.mWord);
        j.a(0);
        setStatisticPage(s.PAGE_SEARCH_HOME);
        setTBSPage("tt_online_search");
        trackModule("online_search");
    }

    public void onKeyPressed(int i, KeyEvent keyEvent) {
        super.onKeyPressed(i, keyEvent);
        if (i == 82) {
            hideSoftInputFromWindow();
        }
    }

    private void jumpToHomepage() {
        if (!isChildFragmentBackStackEmpty()) {
            this.mDetailFragment = null;
            this.mAssociateFragment = null;
            super.onBackPressed();
            setTopFragment(true);
            onStatsResume();
            this.mListView.setVisibility(0);
            updateStatisticPageProperties();
        }
    }

    protected void onBackPressed() {
        onPauseChildFragment();
        clearChildFragmentBackStackManager();
        super.onBackPressed();
        hideSoftInputFromWindow();
    }

    private void onPauseChildFragment() {
        if (!isChildFragmentBackStackEmpty()) {
            getChildFragmentBackStackManager().c().onStatsPause();
        }
    }

    public void onStatsResume() {
        if (isChildFragmentBackStackEmpty()) {
            super.onStatsResume();
            return;
        }
        getChildFragmentBackStackManager().c().onStatsResume();
        updateAlibabaProperty("keyword", this.mWord);
        updateAlibabaProperty("search_type", r.a());
    }

    public void onStatsPause() {
        if (isChildFragmentBackStackEmpty()) {
            super.onStatsPause();
            return;
        }
        updateAlibabaProperty("keyword", this.mWord);
        updateAlibabaProperty("search_type", r.a());
        getChildFragmentBackStackManager().c().onStatsPause();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mInputMethodManager = null;
        j.b(null);
        j.a(null);
        j.a(-1);
        AssociateFragment.clearCache();
        u.d("");
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        this.mSearchAction = actionBarController.a(null);
        this.mSearchAction.a(new com.sds.android.ttpod.component.a.b(this) {
            final /* synthetic */ OnlineSearchFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                this.a.searchKeyword(this.a.mInputEditText.getText().toString());
            }
        });
        this.mRootView = layoutInflater.inflate(R.layout.fragment_online_search, viewGroup, false);
        this.mSearchInputLayout = actionBarController.a();
        this.mInputEditText = (EditText) this.mSearchInputLayout.findViewById(R.id.edittext_search_input);
        this.mClearImageView = (IconTextView) this.mSearchInputLayout.findViewById(R.id.imageview_search_clear);
        this.mClearImageView.setVisibility(8);
        this.mClearImageView.setOnClickListener(this.mOnClickListener);
        this.mListView = (ListView) this.mRootView.findViewById(R.id.online_search_listview);
        this.mHeaderView = layoutInflater.inflate(R.layout.online_search_header, null);
        this.mTvHotwordHint = (TextView) this.mHeaderView.findViewById(R.id.tv_hotword_hint);
        this.mTvHistoryHint = (TextView) this.mHeaderView.findViewById(R.id.tv_history_hint);
        this.mHotwordView = (HotwordView) this.mHeaderView.findViewById(R.id.hotwordView);
        flushHotwordView();
        this.mHotwordView.setListener(this.mOnHotwordClickListener);
        this.mListView.addHeaderView(this.mHeaderView, null, false);
        this.mBtnClearHistory = (Button) layoutInflater.inflate(R.layout.online_search_clear_history, null);
        this.mListView.addFooterView(this.mBtnClearHistory);
        setLaunchChildFragmentAttr(R.id.container, 0, 0);
        this.mListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ OnlineSearchFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent != null && motionEvent.getAction() == 0) {
                    this.a.hideSoftInputFromWindow();
                }
                return false;
            }
        });
        return this.mRootView;
    }

    private void searchKeyword(String str) {
        if (!m.a(str)) {
            r.a("search_input");
            updateOrigin("search-button", "search-button");
            requestDetailInfo(str, com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_SEARCH_BUTTON);
        }
    }

    private void searchHotword(String str) {
        r.a("search_hotwords");
        updateOrigin("search-hotword", "search-hotword");
        requestDetailInfo(str, com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_HOT_WORD);
    }

    private void searchHistory(String str) {
        r.a("search_history");
        updateOrigin("search-historyWord", "search-historyWord");
        requestDetailInfo(str, com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_HISTORY_WORD);
    }

    private void searchFromOut() {
        r.a("search_input");
        updateOrigin("search-button", "search-button");
        requestDetailInfo(this.mWord, com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_SEARCH_BUTTON);
    }

    private void flushHotwordView() {
        List w = com.sds.android.ttpod.framework.storage.a.a.a().w();
        TextView textView = this.mTvHotwordHint;
        int i = (w == null || w.isEmpty()) ? 8 : 0;
        textView.setVisibility(i);
        this.mHotwordView.setRows(2);
        this.mHotwordView.setContent(w);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSearchHistory = new com.sds.android.ttpod.component.e.a();
        this.mInputEditText.addTextChangedListener(this.mTextWatcher);
        this.mInputEditText.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mHistoryAdapter = new a(this.mSearchHistory);
        this.mHistoryAdapter.a(new a.a(this) {
            final /* synthetic */ OnlineSearchFragment a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.mSearchHistory.b((Object) str);
                this.a.flushHistoryView(null);
            }
        });
        this.mListView.setAdapter(this.mHistoryAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ OnlineSearchFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int a = com.sds.android.ttpod.b.m.a(this.a.mListView.getHeaderViewsCount(), i, this.a.mHistoryAdapter.getCount());
                if (a >= 0) {
                    this.a.searchHistory(this.a.mHistoryAdapter.a(a));
                }
            }
        });
        this.mBtnClearHistory.setOnClickListener(this.mOnClickListener);
        flushHistoryView(null);
        if (!sGotBillboard) {
            sGotBillboard = true;
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_BILLBOARD, Integer.valueOf(20)));
        }
        new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_ACCESS_SEARCH_PAGE.getValue(), 0, s.PAGE_SEARCH_HOME.getValue()).post();
        this.mInputEditText.requestFocus();
        this.mInputEditText.postDelayed(new Runnable(this) {
            final /* synthetic */ OnlineSearchFragment a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.showSoftInputFromWindow();
            }
        }, 500);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mHeaderView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mBtnClearHistory, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mBtnClearHistory, ThemeElement.SONG_LIST_ITEM_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mTvHotwordHint, ThemeElement.TILE_SUB_TEXT);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mTvHistoryHint, ThemeElement.TILE_SUB_TEXT);
        v.a(this.mSearchAction, ThemeElement.TOP_BAR_SEARCH_IMAGE, (int) R.string.icon_search, ThemeElement.TOP_BAR_TEXT);
        if (this.mHistoryAdapter != null) {
            this.mHistoryAdapter.notifyDataSetChanged();
        }
        this.mHotwordView.a();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ONLINE_ASSOCIATE_SEARCH_FINISHED, i.a(cls, "updateOnlineSearchAssociateFinished", OnlineSearchResult.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_BILLBOARD_FINISHED, i.a(cls, "updateBillboardSearchFinished", List.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (m.a(this.mWord)) {
            this.mInputEditText.setText(this.mWord);
        } else {
            searchFromOut();
        }
        List b = this.mSearchHistory.b();
        if (!com.sds.android.ttpod.framework.a.j.a(b)) {
            d.c.a("search_history", String.valueOf(b.size()));
        }
    }

    public void onPause() {
        super.onPause();
        hideSoftInputFromWindow();
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void updateOnlineSearchAssociateFinished(OnlineSearchResult onlineSearchResult) {
        String obj = this.mInputEditText.getText().toString();
        if (this.mLastActionAssociate && !m.a(obj)) {
            if (this.mDetailFragment != null) {
                super.onBackPressed();
                setSlidingCloseMode(3);
                this.mDetailFragment = null;
            }
            this.mListView.setVisibility(4);
            if (this.mAssociateFragment != null) {
                this.mAssociateFragment.updateAssociate(onlineSearchResult, obj);
                return;
            }
            this.mAssociateFragment = new AssociateFragment();
            this.mAssociateFragment.updateAssociate(onlineSearchResult, obj);
            setTopFragment(false);
            onStatsPause();
            launchChildFragment(this.mAssociateFragment);
        }
    }

    public void updateBillboardSearchFinished(List list) {
        if (list == null || list.isEmpty()) {
            sGotBillboard = false;
            return;
        }
        com.sds.android.ttpod.framework.storage.a.a.a().b(list);
        flushHotwordView();
    }

    public void pageStatisticBack() {
        if (this.mAssociateFragment != null) {
            this.mAssociateFragment.updateStatisticPageProperties();
        } else {
            updateStatisticPageProperties();
        }
    }

    private void flushHistoryView(String str) {
        if (!m.a(str)) {
            this.mSearchHistory.a((Object) str);
        }
        this.mHistoryAdapter.notifyDataSetChanged();
        this.mListView.setSelection(0);
        if (this.mSearchHistory.c() > 0) {
            this.mTvHistoryHint.setVisibility(0);
            this.mBtnClearHistory.setVisibility(0);
            this.mListView.setFooterDividersEnabled(true);
        } else {
            this.mTvHistoryHint.setVisibility(8);
            this.mBtnClearHistory.setVisibility(8);
            this.mListView.setFooterDividersEnabled(false);
        }
        this.mHistoryAdapter.notifyDataSetChanged();
    }

    private void startOnlineAssociateSearch(String str) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_ONLINE_ASSOCIATE_SEARCH, str));
    }

    private void requestDetailInfo(String str, com.sds.android.ttpod.framework.a.b.r rVar) {
        String trim = str.trim();
        if (!m.a(trim)) {
            this.mLastActionAssociate = false;
            this.mWord = trim;
            u.d(this.mWord);
            trim = UUID.randomUUID().toString();
            u.b(trim);
            j.b(trim);
            String obj = this.mInputEditText.getText().toString();
            flushInputEditText();
            flushHistoryView(this.mWord);
            hideSoftInputFromWindow();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), s.PAGE_NONE.getValue(), s.PAGE_SEARCH_SINGLE_SONG.getValue());
            sUserEvent.append("keyword", this.mWord);
            c.a(sUserEvent, obj, this.mAssociateFragment != null ? this.mAssociateFragment.popShowCount() : 0);
            if (rVar != com.sds.android.ttpod.framework.a.b.r.ACTOIN_CLICK_SEARCH_ASSOCIATE_KEYWORD) {
                r.b(this.mWord);
            }
            if (this.mAssociateFragment != null) {
                super.onBackPressed();
                this.mAssociateFragment = null;
            }
            this.mListView.setVisibility(4);
            if (this.mDetailFragment != null) {
                this.mDetailFragment.requestDetailInfo(this.mWord, rVar.getValue(), obj);
            } else {
                this.mDetailFragment = OnlineSearchDetailFragment.instantiate(this.mWord, rVar, obj);
                setTopFragment(false);
                onStatsPause();
                launchChildFragment(this.mDetailFragment);
            }
            trackPlaySong("search", "search_" + this.mWord, true);
            trackSearch(r.a(), this.mWord);
        }
    }

    private void flushInputEditText() {
        this.mInputEditText.removeTextChangedListener(this.mTextWatcher);
        this.mInputEditText.setText(this.mWord);
        if (m.a(this.mWord)) {
            this.mClearImageView.setVisibility(8);
        } else {
            this.mClearImageView.setVisibility(0);
        }
        this.mInputEditText.setSelection(this.mWord.length());
        this.mInputEditText.addTextChangedListener(this.mTextWatcher);
    }

    private InputMethodManager getInputMethodManager() {
        FragmentActivity activity = getActivity();
        if (this.mInputMethodManager == null && activity != null) {
            this.mInputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        }
        return this.mInputMethodManager;
    }

    void hideSoftInputFromWindow() {
        if (this.mInputEditText != null && getInputMethodManager() != null) {
            getInputMethodManager().hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
            this.mInputEditText.clearFocus();
            this.mClearImageView.requestFocus();
        }
    }

    private void showSoftInputFromWindow() {
        if (this.mInputEditText != null && getInputMethodManager() != null) {
            getInputMethodManager().showSoftInput(this.mInputEditText, 2);
        }
    }

    public void onFragmentSelected(int i, String str, String str2) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
        hideSoftInputFromWindow();
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return this.mDetailFragment != null && this.mDetailFragment.isTheRightFragment(i);
    }

    public void onResume() {
        super.onResume();
        getView().setKeepScreenOn(com.sds.android.ttpod.framework.storage.environment.b.z());
        p.a(this.mOrigin);
        p.a();
    }

    private void updateOrigin(String str, String str2) {
        this.mOrigin = str;
        u.a(this.mOrigin);
        u.c(str2);
    }

    public void onKeywordClicked(KeywordData keywordData) {
        hideSoftInputFromWindow();
        String keyword = keywordData.getKeyword();
        if (!m.a(keyword)) {
            updateOrigin("search-associativeWord", "search-associativeWord");
            requestDetailInfo(keyword, com.sds.android.ttpod.framework.a.b.r.ACTOIN_CLICK_SEARCH_ASSOCIATE_KEYWORD);
        }
    }

    public void onSingerClicked(SingerData singerData) {
        hideSoftInputFromWindow();
        flushHistoryView(singerData.getSingerName());
        SingerDetailFragment.launch((BaseActivity) getActivity(), singerData.getSingerName(), (int) singerData.getSingerId());
    }

    public void onSongClicked(MediaItem mediaItem, int i) {
        hideSoftInputFromWindow();
        flushHistoryView(mediaItem.getTitle() + " - " + mediaItem.getArtist());
        if (m.a(MediaStorage.GROUP_ID_ONLINE_TEMPORARY, com.sds.android.ttpod.framework.storage.environment.b.m()) && m.a(mediaItem.getID(), com.sds.android.ttpod.framework.storage.environment.b.n())) {
            OnlineMediaListFragment.performPlayCurrentMedia(false);
            openPlayerPanel();
        } else if (mediaItem.hasCopyright() || mediaItem.getCensorLevel() == 4) {
            new ArrayList().add(mediaItem);
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, r0));
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, mediaItem));
            openPlayerPanel();
        } else {
            OnlineMediaListFragment.checkMediaCensor(getActivity(), mediaItem, i);
        }
    }

    public void onMvClicked(MvData mvData, int i) {
        hideSoftInputFromWindow();
        d.j.a("mv_origin", "mv_associate_search");
        VideoPlayManager.a(getActivity(), mvData);
    }

    private void openPlayerPanel() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.openPlayerPanel();
        }
    }
}
