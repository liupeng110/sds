package com.sds.android.ttpod.fragment.skinmanager.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.skinmanager.MyThemeFragment;
import com.sds.android.ttpod.framework.a.b.c;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.skin.m;
import com.sds.android.ttpod.framework.modules.skin.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.widget.StateView;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class BaseThemeFragment extends BaseFragment implements com.sds.android.ttpod.component.soundsearch.a, b {
    private static final int DOWNLOAD_MSG = 1;
    private static final int REFRESH_INTERVAL = 500;
    private static final int REFRESH_MSG = 0;
    private static final String UPDATE_TEMP_SUFFIX = "updatetmp";
    protected static HashMap<String, DownloadTaskInfo> sDownloadingSkinMap = new HashMap();
    private static Queue<DownloadTaskInfo> sDownloadingSkinQueue = new LinkedList();
    private static DownloadTaskInfo sDownloadingTask;
    protected static String sLastDownloadThemeName;
    protected static HashMap<String, m> sLocalSkinInfoMap = new HashMap();
    protected static HashMap<String, m> sOnlineSkinInfoMap = new HashMap();
    private static Handler sSkinDownloadHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (BaseThemeFragment.sDownloadingTask == null || BaseThemeFragment.sDownloadingTask.getState() == null || BaseThemeFragment.sDownloadingTask.getState().intValue() == 4) {
                        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) BaseThemeFragment.sDownloadingSkinQueue.poll();
                        if (downloadTaskInfo != null) {
                            BaseThemeFragment.sDownloadingTask = downloadTaskInfo;
                            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, downloadTaskInfo, Boolean.FALSE));
                            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, downloadTaskInfo));
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    protected boolean mCacheMode;
    protected ArrayList<m> mCachedSkinItems;
    private int mDownloadingCount = 0;
    protected boolean mEnableRefreshProgressbar = true;
    protected boolean mInEditMode = false;
    protected int mInternalThemeCount = -1;
    private boolean mIsForLocal = false;
    protected com.sds.android.ttpod.framework.modules.a mLoadDataCommandID;
    private boolean mLoadingTheme = false;
    private View mOfflineModeView;
    private OnClickListener mOnItemClickListener = new OnClickListener(this) {
        final /* synthetic */ BaseThemeFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            m mVar = (m) view.getTag();
            if (mVar != null && this.a.mThemeAdapter.d != mVar) {
                this.a.onThemeItemSelected(mVar);
            }
        }
    };
    private OnLongClickListener mOnItemLongClickListener = new OnLongClickListener(this) {
        final /* synthetic */ BaseThemeFragment a;

        {
            this.a = r1;
        }

        public boolean onLongClick(View view) {
            if (!(this.a.mInEditMode || this.a.mParentEditToggle == null || !this.a.hasEditableContent())) {
                this.a.mParentEditToggle.toggleEditMode();
            }
            return true;
        }
    };
    private com.sds.android.ttpod.widget.StateView.a mOnRetryRequestListener = new com.sds.android.ttpod.widget.StateView.a(this) {
        final /* synthetic */ BaseThemeFragment a;

        {
            this.a = r1;
        }

        public void onRetryRequested() {
            this.a.loadData();
        }
    };
    protected com.sds.android.ttpod.component.soundsearch.a mParentEditToggle = null;
    private Handler mRefreshHandler = new Handler(this) {
        final /* synthetic */ BaseThemeFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.mThemeAdapter.notifyDataSetChanged();
                    if (BaseThemeFragment.sDownloadingTask != null) {
                        this.a.mRefreshHandler.sendEmptyMessageDelayed(0, 500);
                        BaseThemeFragment.sSkinDownloadHandler.sendEmptyMessage(1);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    protected StateView mStateView;
    protected String mSubClassTag;
    protected a mThemeAdapter;
    protected ArrayList<m> mThemeData;
    protected ListView mThemeListView;
    private OnClickListener mUpdateClickListener = new OnClickListener(this) {
        final /* synthetic */ BaseThemeFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            m mVar = (m) view.getTag();
            String g = mVar.g();
            String c = mVar.c();
            if (g != null && c != null) {
                c = this.a.getSkinInfoMapKey(g);
                if (!BaseThemeFragment.sDownloadingSkinMap.containsKey(g)) {
                    mVar = (m) BaseThemeFragment.sOnlineSkinInfoMap.get(c);
                    x.f(g);
                    new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_UPDATE_SKIN.getValue(), s.PAGE_NONE.getValue()).append("skin_name", g).post();
                    this.a.tryDownloadSkin(mVar, true);
                }
            }
        }
    };

    public class a extends BaseAdapter {
        private LayoutInflater a;
        protected final int b;
        protected final int c;
        protected m d;
        protected String e = null;
        final /* synthetic */ BaseThemeFragment f;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(BaseThemeFragment baseThemeFragment) {
            this.f = baseThemeFragment;
            a();
            this.a = LayoutInflater.from(baseThemeFragment.getActivity());
            this.b = q.a;
            this.c = q.c;
        }

        public boolean a() {
            String str = this.e;
            this.e = BaseThemeFragment.getRawSkinPath();
            return !this.e.equals(str);
        }

        public String b() {
            return this.e;
        }

        public boolean a(m mVar) {
            return this.e.equals(mVar.b());
        }

        public void a(ArrayList<m> arrayList) {
            if (arrayList != null) {
                c((ArrayList) arrayList);
                this.f.mThemeData = arrayList;
                notifyDataSetChanged();
            }
        }

        public void b(ArrayList<m> arrayList) {
            if (arrayList != null) {
                c((ArrayList) arrayList);
                this.f.mThemeData.addAll(arrayList);
                if (this.f.mDownloadingCount > 0) {
                    this.f.onSkinDownloading(null);
                }
                notifyDataSetChanged();
            }
        }

        public ArrayList<m> c() {
            return this.f.mThemeData;
        }

        private void c(ArrayList<m> arrayList) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m mVar = (m) it.next();
                String h = mVar.h();
                d(mVar);
                if (BaseThemeFragment.sDownloadingSkinMap.containsKey(h)) {
                    mVar.a(3);
                    this.f.mDownloadingCount = this.f.mDownloadingCount + 1;
                }
            }
        }

        private void d(m mVar) {
            String h = mVar.h();
            if (BaseThemeFragment.sDownloadingSkinMap.containsKey(h)) {
                String savePath = ((DownloadTaskInfo) BaseThemeFragment.sDownloadingSkinMap.get(h)).getSavePath();
                if (e.a(savePath) && !savePath.endsWith(BaseThemeFragment.UPDATE_TEMP_SUFFIX)) {
                    mVar.a(0);
                    BaseThemeFragment.sDownloadingSkinMap.remove(h);
                }
            }
        }

        public boolean b(m mVar) {
            String b = mVar.b();
            if ((3 == mVar.a()) || b == null || b.equals(this.e)) {
                return false;
            }
            this.d = mVar;
            this.e = mVar.b();
            notifyDataSetChanged();
            x.g();
            new c("click").a("theme_name", mVar.g()).a(Downloads.COLUMN_STATUS, String.valueOf(2)).a();
            t.a(r.ACTION_CLICK_CHANGE_SKIN, s.PAGE_NONE);
            return true;
        }

        public m d() {
            return this.d;
        }

        public void c(m mVar) {
            this.f.mThemeData.remove(mVar);
            g.b(mVar.b(), this.b, this.c);
            notifyDataSetChanged();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public m a(int i) {
            return (this.f.mThemeData == null || i >= this.f.mThemeData.size()) ? null : (m) this.f.mThemeData.get(i);
        }

        public int getCount() {
            return (int) Math.ceil(((double) (this.f.mThemeData != null ? this.f.mThemeData.size() : 0)) / 3.0d);
        }

        public int e() {
            return this.f.mThemeData != null ? this.f.mThemeData.size() : 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = a(viewGroup);
            }
            return a(view, i);
        }

        private View a(View view, int i) {
            com.sds.android.ttpod.fragment.skinmanager.b[] bVarArr = (com.sds.android.ttpod.fragment.skinmanager.b[]) view.getTag();
            a(a(i, 0), bVarArr[0]);
            a(a(i, 1), bVarArr[1]);
            a(a(i, 2), bVarArr[2]);
            return view;
        }

        private int a(int i, int i2) {
            return (i * 3) + i2;
        }

        protected View a(ViewGroup viewGroup) {
            View inflate = this.a.inflate(R.layout.theme_list_item, viewGroup, false);
            com.sds.android.ttpod.fragment.skinmanager.b bVar = new com.sds.android.ttpod.fragment.skinmanager.b(inflate.findViewById(R.id.theme_item1));
            com.sds.android.ttpod.fragment.skinmanager.b bVar2 = new com.sds.android.ttpod.fragment.skinmanager.b(inflate.findViewById(R.id.theme_item2));
            com.sds.android.ttpod.fragment.skinmanager.b bVar3 = new com.sds.android.ttpod.fragment.skinmanager.b(inflate.findViewById(R.id.theme_item3));
            inflate.setTag(new com.sds.android.ttpod.fragment.skinmanager.b[]{bVar, bVar2, bVar3});
            return inflate;
        }

        protected void a(int i, com.sds.android.ttpod.fragment.skinmanager.b bVar) {
            m a = a(i);
            com.sds.android.ttpod.framework.modules.theme.c.a(bVar.d(), ThemeElement.COMMON_CONTENT_TEXT);
            View h = bVar.h();
            if (a != null) {
                h.setVisibility(0);
                h.setTag(a);
                h.setOnClickListener(this.f.mOnItemClickListener);
                h.setOnLongClickListener(this.f.mOnItemLongClickListener);
                a(a, bVar.b());
                b(a, bVar.a());
                a(a, bVar);
                a(a, bVar.d());
                c(a, bVar.i());
                return;
            }
            h.setVisibility(4);
        }

        private void c(m mVar, ImageView imageView) {
            if (4 != mVar.a()) {
                imageView.setVisibility(8);
            } else {
                y.a(mVar.e(), (View) imageView);
            }
        }

        protected void a(m mVar, ImageView imageView) {
        }

        protected void b(m mVar, ImageView imageView) {
            if (imageView != null) {
                int i = 4;
                if (this.e.equals(mVar.b())) {
                    this.d = mVar;
                    i = 0;
                }
                imageView.setVisibility(i);
            }
        }

        protected void a(m mVar, com.sds.android.ttpod.fragment.skinmanager.b bVar) {
            int i;
            Object obj = null;
            int i2 = 4;
            int i3 = 0;
            ProgressBar c = bVar.c();
            TextView e = bVar.e();
            View g = bVar.g();
            View j = bVar.j();
            j.setTag(mVar);
            j.setVisibility(8);
            j.setOnClickListener(null);
            View f = bVar.f();
            if (3 == mVar.a()) {
                if (BaseThemeFragment.sDownloadingTask != null) {
                    obj = BaseThemeFragment.sDownloadingTask.getFileName();
                }
                if (mVar.h().equals(obj)) {
                    BaseThemeFragment.sDownloadingTask.setDownloadLength(((Integer) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_TASK_DOWNLOADED_LENGTH, BaseThemeFragment.sDownloadingTask), Integer.class)).intValue());
                    if (this.f.mEnableRefreshProgressbar) {
                        c.setProgress(BaseThemeFragment.sDownloadingTask.getDownloadProgress().intValue());
                    }
                } else {
                    c.setProgress(0);
                }
                f.setVisibility(8);
                i = 0;
            } else if (4 == mVar.a()) {
                c.setProgress(0);
                e.setText(mVar.f().getFileSizeStr());
                f.setBackgroundResource(R.drawable.img_background_skin_start_download);
                f.setVisibility(0);
                i = 4;
                i2 = 0;
            } else {
                m mVar2 = (m) BaseThemeFragment.sOnlineSkinInfoMap.get(mVar.g());
                m mVar3 = (m) BaseThemeFragment.sLocalSkinInfoMap.get(mVar.i());
                if (this.f.mInEditMode || mVar2 == null || mVar3 == null) {
                    f.setVisibility(8);
                    i = 4;
                    i2 = 0;
                    i3 = 8;
                } else if (this.f.checkUpdateForSkin(mVar)) {
                    c.setProgress(0);
                    e.setText(this.f.getResources().getString(R.string.update));
                    f.setBackgroundResource(R.drawable.img_background_skin_update);
                    f.setVisibility(0);
                    j.setVisibility(0);
                    j.setOnClickListener(this.f.mUpdateClickListener);
                    i = 4;
                    i2 = 0;
                } else {
                    f.setVisibility(8);
                    i = 4;
                    i3 = 8;
                }
            }
            e.setVisibility(i2);
            c.setVisibility(i);
            g.setVisibility(i3);
        }

        private void a(m mVar, TextView textView) {
            if (3 != mVar.a() || mVar.f() == null) {
                textView.setText(mVar.g());
            } else {
                textView.setText(mVar.f().getName());
            }
            textView.setVisibility(0);
        }

        public void a(DownloadTaskInfo downloadTaskInfo) {
            int intValue = downloadTaskInfo.getState().intValue();
            String fileName = downloadTaskInfo.getFileName();
            m mVar = new m((m) BaseThemeFragment.sOnlineSkinInfoMap.get(fileName));
            if (intValue == 4) {
                this.f.updateSkinInfoForThemeName(fileName, 0);
                b(fileName);
                if (a(fileName)) {
                    b(downloadTaskInfo);
                    f.a(fileName + " " + this.f.getResources().getString(R.string.download_finished));
                }
                if (fileName.equals(BaseThemeFragment.sLastDownloadThemeName)) {
                    if (this.f.isVisible()) {
                        this.f.checkSkinItem(mVar);
                    }
                    BaseThemeFragment.sLastDownloadThemeName = null;
                }
                this.f.performSkinDownloaded(mVar);
            } else if (intValue == 3 || intValue == 5) {
                if (e.a(downloadTaskInfo.getSavePath())) {
                    this.f.performSkinItemStateChange(fileName, 0);
                } else {
                    this.f.performSkinItemStateChange(fileName, 4);
                }
                BaseThemeFragment.sLastDownloadThemeName = null;
                a(fileName);
                this.f.performSkinDownloadError(mVar);
            }
        }

        private void b(DownloadTaskInfo downloadTaskInfo) {
            String savePath = downloadTaskInfo.getSavePath();
            if (savePath.endsWith(BaseThemeFragment.UPDATE_TEMP_SUFFIX)) {
                String substring = savePath.substring(0, savePath.length() - BaseThemeFragment.UPDATE_TEMP_SUFFIX.length());
                e.h(substring);
                e.c(savePath, substring);
                g.b(substring, q.a, q.c);
            }
        }

        private void b(String str) {
            m mVar = new m((m) BaseThemeFragment.sOnlineSkinInfoMap.get(str));
            BaseThemeFragment.sLocalSkinInfoMap.put(mVar.i(), mVar);
        }

        public void a(m mVar, DownloadTaskInfo downloadTaskInfo) {
            BaseThemeFragment.sDownloadingSkinMap.put(mVar.h(), downloadTaskInfo);
        }

        public boolean a(String str) {
            return BaseThemeFragment.sDownloadingSkinMap.remove(str) != null;
        }
    }

    protected abstract com.sds.android.ttpod.framework.modules.a getLoadDataCommandID();

    protected abstract String getStatisticOrigin();

    protected abstract ArrayList<m> loadDataFromCache();

    protected abstract void onThemeItemSelected(m mVar);

    protected String getSkinInfoMapKey(m mVar) {
        if (mVar != null) {
            return mVar.g();
        }
        return null;
    }

    protected String getSkinInfoMapKey(String str) {
        return str;
    }

    protected static String getRawSkinPath() {
        return v.a(com.sds.android.ttpod.b.v.b());
    }

    protected static void saveSkinToSystem(String str, int i) {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_SKIN, str, Integer.valueOf(i)));
    }

    private void performSkinDownloaded(m mVar) {
        c.a().a(mVar);
    }

    private void performSkinDownloadError(m mVar) {
        c.a().b(mVar);
    }

    private void performSkinDownloading(m mVar) {
        c.a().c(mVar);
    }

    protected void performSkinDeleted(m mVar) {
        c.a().d(mVar);
    }

    private void performCurrentSkinChanged(String str) {
        c.a().a(str);
    }

    protected void performSkinInfoLoaded() {
        c.a().b();
    }

    protected void performSkinItemStateChange(String str, int i) {
        c.a().a(str, i);
    }

    protected void setAdapterDataSource(ArrayList<m> arrayList) {
        this.mThemeAdapter.a((ArrayList) arrayList);
        if (!this.mIsForLocal) {
            setOnlineSkinInfoMap(arrayList);
        }
        this.mStateView.setState(StateView.b.SUCCESS);
    }

    protected void setOnlineSkinInfoMap(ArrayList<m> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            m mVar = (m) it.next();
            sOnlineSkinInfoMap.put(mVar.g(), new m(mVar.f()));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initThemeAdapter();
        c.a().a((b) this);
        this.mLoadDataCommandID = getLoadDataCommandID();
    }

    public void onDestroy() {
        super.onDestroy();
        c.a().b((b) this);
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_base_theme_layout, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.theme_loadingview);
        this.mStateView.setOnRetryRequestListener(this.mOnRetryRequestListener);
        this.mThemeListView = (ListView) view.findViewById(R.id.theme_listview);
        initListViewHeader();
        initListViewFooter();
        this.mThemeListView.setAdapter(this.mThemeAdapter);
        this.mThemeListView.setOnScrollListener(getOnScrollListener());
        if (isShowOfflineModeView()) {
            this.mOfflineModeView = p.a(this.mStateView);
            return;
        }
        if (this.mOfflineModeView != null) {
            this.mOfflineModeView.setVisibility(8);
        }
        showLoadingView();
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        loadData();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_STATE, i.a(cls, "updateThemeDownloadingState", DownloadTaskInfo.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND, i.a(cls, "updateBackground", Drawable.class));
    }

    public void updateThemeDownloadingState(DownloadTaskInfo downloadTaskInfo) {
        if (DownloadTaskInfo.TYPE_SKIN.equals(downloadTaskInfo.getType())) {
            getAdapter().a(downloadTaskInfo);
        }
    }

    public void updateBackground(Drawable drawable) {
        this.mLoadingTheme = false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof com.sds.android.ttpod.component.soundsearch.a) {
            this.mParentEditToggle = (com.sds.android.ttpod.component.soundsearch.a) activity;
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.mThemeAdapter != null && this.mThemeAdapter.getCount() > 0 && this.mThemeAdapter.a()) {
            this.mThemeAdapter.notifyDataSetChanged();
        }
        if (this.mIsForLocal && !z && isInEditMode()) {
            toggleEditMode();
        }
    }

    protected void showLoadingView() {
        if (this.mThemeAdapter.getCount() == 0) {
            this.mStateView.setState(StateView.b.LOADING);
        }
    }

    protected void loadData() {
        this.mCachedSkinItems = loadDataFromCache();
        if (this.mCachedSkinItems == null || this.mCachedSkinItems.size() == 0) {
            this.mStateView.setState(StateView.b.LOADING);
        } else {
            this.mCacheMode = true;
            setAdapterDataSource(this.mCachedSkinItems);
            refreshEditButton();
        }
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(this.mLoadDataCommandID, new Object[0]));
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        this.mThemeAdapter.notifyDataSetChanged();
    }

    public boolean isInEditMode() {
        return this.mInEditMode;
    }

    public void toggleEditMode() {
        if (this.mThemeAdapter != null) {
            this.mThemeAdapter.a();
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    public boolean hasEditableContent() {
        return false;
    }

    protected void refreshEditButton() {
    }

    protected void checkSkinItem(m mVar) {
        if (!this.mLoadingTheme && mVar != null && this.mThemeAdapter.b(mVar)) {
            if (this instanceof MyThemeFragment) {
                x.a();
                t.b("PAGE_CLICK", r.ACTION_CLICK_THEME_MY_SET, s.PAGE_MY_THEME, s.PAGE_NONE);
            }
            this.mLoadingTheme = true;
            refreshEditButton();
            saveSkinToSystem(mVar.b(), mVar.a());
            com.sds.android.ttpod.framework.storage.environment.b.i("follow_skin");
            performCurrentSkinChanged(mVar.b());
        }
    }

    protected void initThemeAdapter() {
    }

    protected OnScrollListener getOnScrollListener() {
        return new OnScrollListener(this) {
            final /* synthetic */ BaseThemeFragment a;

            {
                this.a = r1;
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i) {
                    case 0:
                        this.a.mEnableRefreshProgressbar = true;
                        return;
                    case 1:
                        this.a.mEnableRefreshProgressbar = false;
                        return;
                    default:
                        return;
                }
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        };
    }

    protected a getAdapter() {
        return this.mThemeAdapter;
    }

    public void onCurrentSkinChanged(String str) {
        if (!str.equals(this.mThemeAdapter.b())) {
            this.mThemeAdapter.a();
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }

    public void onSkinDeleted(m mVar) {
        this.mThemeAdapter.notifyDataSetChanged();
    }

    public void onSkinDownloaded(m mVar) {
        this.mThemeAdapter.notifyDataSetChanged();
        sDownloadingTask = null;
        sSkinDownloadHandler.sendEmptyMessage(1);
    }

    public void onSkinDownloading(m mVar) {
        this.mRefreshHandler.removeMessages(0);
        this.mRefreshHandler.sendEmptyMessage(0);
    }

    public void onSkinDownloadError(m mVar) {
        this.mThemeAdapter.notifyDataSetChanged();
        sDownloadingTask = null;
        sSkinDownloadHandler.sendEmptyMessage(1);
    }

    public void onSkinInfoLoaded() {
        this.mThemeAdapter.notifyDataSetChanged();
    }

    public void onSkinItemStateChange(String str, int i) {
        updateSkinInfoForThemeName(str, i);
    }

    private boolean isShowOfflineModeView() {
        return isSupportOfflineMode() && p.a();
    }

    protected void tryDownloadSkin(m mVar, boolean z) {
        if (!EnvironmentUtils.c.e()) {
            f.a((int) R.string.shake_error_hint);
        } else if (!e.b(mVar.b()) || z) {
            String g = mVar.g();
            if (sDownloadingSkinMap.containsKey(g)) {
                f.a((int) R.string.downloading_already);
                return;
            }
            m mVar2 = (m) sOnlineSkinInfoMap.get(g);
            OnlineSkinItem f = mVar2 != null ? mVar2.f() : null;
            if (f != null) {
                String skinUrl = f.getSkinUrl();
                String str = com.sds.android.ttpod.framework.a.o() + File.separator + g + ".tsk";
                if (z) {
                    str = str + UPDATE_TEMP_SUFFIX;
                }
                if (e.b(str)) {
                    e.h(str);
                }
                DownloadTaskInfo a = com.sds.android.ttpod.framework.a.e.a(skinUrl, str, Long.valueOf(0), g, DownloadTaskInfo.TYPE_SKIN, Boolean.valueOf(false), getStatisticOrigin());
                sDownloadingSkinQueue.offer(a);
                performSkinItemStateChange(g, 3);
                getAdapter().a(mVar, a);
                sSkinDownloadHandler.sendEmptyMessage(1);
                performSkinDownloading(mVar2);
                new c("click").a("theme_name", g).a(Downloads.COLUMN_STATUS, String.valueOf(1)).a();
            }
        } else {
            f.a((int) R.string.skin_file_already_existed);
        }
    }

    protected void setLocalSkinInfoMap(ArrayList<m> arrayList) {
        sLocalSkinInfoMap.clear();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m mVar = (m) it.next();
                sLocalSkinInfoMap.put(mVar.i(), mVar);
            }
        }
    }

    protected m getSkinItemForThemeName(String str) {
        ArrayList themeDataList = getThemeDataList();
        if (themeDataList == null || str == null) {
            return null;
        }
        for (int i = 0; i < themeDataList.size(); i++) {
            m mVar = (m) themeDataList.get(i);
            if (mVar.g().equals(str)) {
                return mVar;
            }
        }
        return null;
    }

    protected void updateSkinInfoForThemeName(String str, int i) {
    }

    public boolean isSupportOfflineMode() {
        return !this.mIsForLocal;
    }

    protected boolean checkIfReloadData(ArrayList<m> arrayList) {
        return !com.sds.android.ttpod.b.v.a((ArrayList) arrayList, this.mThemeAdapter != null ? this.mThemeAdapter.c() : null);
    }

    protected ArrayList<m> getThemeDataList() {
        return this.mThemeAdapter != null ? this.mThemeAdapter.c() : null;
    }

    protected void setForLocal() {
        this.mIsForLocal = true;
    }

    protected void initListViewHeader() {
    }

    protected void initListViewFooter() {
    }

    protected boolean checkUpdateForSkin(m mVar) {
        m mVar2 = (m) sOnlineSkinInfoMap.get(mVar.g());
        m mVar3 = (m) sLocalSkinInfoMap.get(mVar.i());
        if (mVar2 == null || mVar3 == null) {
            return false;
        }
        return skinVersionCompare(mVar2.d(), mVar3.d());
    }

    private boolean skinVersionCompare(String str, String str2) {
        boolean z = true;
        if (str2 == null && str == null) {
            return false;
        }
        if (str2 != null && str == null) {
            return false;
        }
        if (str2 == null && str != null) {
            return true;
        }
        if (str.compareTo(str2) <= 0) {
            z = false;
        }
        return z;
    }

    protected boolean isLocalUnSelectedSkinItem(m mVar) {
        if (mVar == null || mVar.a() != 0 || this.mThemeAdapter.a(mVar)) {
            return false;
        }
        return true;
    }

    protected void notifyDataSetChanged() {
        if (this.mThemeAdapter != null) {
            this.mThemeAdapter.notifyDataSetChanged();
        }
    }
}
