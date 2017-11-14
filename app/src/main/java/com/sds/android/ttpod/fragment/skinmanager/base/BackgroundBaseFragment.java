package com.sds.android.ttpod.fragment.skinmanager.base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment;
import com.sds.android.ttpod.fragment.skinmanager.b;
import com.sds.android.ttpod.framework.a.b.c;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.modules.skin.q;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.widget.StateView;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BackgroundBaseFragment extends SlidingClosableFragment {
    private static final String BACKGROUND_DOWNLOAD_ISSUE = "background_download_issue";
    private static final int MAX_DOWNLOAD_COUNT = 4;
    private static final int REFRESH_INTERVAL = 500;
    private static final int REFRESH_MSG = 0;
    private static com.sds.android.sdk.core.download.a mDownloadManager;
    protected static ArrayList sBkgDownloadListenerList = new ArrayList();
    protected static ArrayList<a> sBkgEditListenerList = new ArrayList();
    private static com.sds.android.ttpod.framework.modules.theme.a sLastRequestItem = null;
    protected a mBackgroundAdapter = null;
    protected ListView mBackgroundListView;
    protected boolean mInEditMode = false;
    protected boolean mIsLoading = false;
    private View mOfflineModeView;
    private OnClickListener mOnBackgroundClickListener = new OnClickListener(this) {
        final /* synthetic */ BackgroundBaseFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) view.getTag();
            if (aVar != null && this.a.mBackgroundAdapter != null && !aVar.toString().equals(this.a.mBackgroundAdapter.a)) {
                this.a.checkNormalStateItem(aVar);
            }
        }
    };
    private Handler mRefreshHandler = new Handler(this) {
        final /* synthetic */ BackgroundBaseFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.mRefreshHandler.removeMessages(0);
                    this.a.mBackgroundAdapter.notifyDataSetChanged();
                    if (this.a.isNeedRefresh()) {
                        this.a.mRefreshHandler.sendEmptyMessageDelayed(0, 500);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    protected StateView mStateView;

    public class a extends BaseAdapter {
        private String a;
        protected ArrayList<com.sds.android.ttpod.framework.modules.theme.a> b;
        protected LayoutInflater c = null;
        final /* synthetic */ BackgroundBaseFragment d;
        private com.sds.android.ttpod.framework.modules.theme.a e;

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(BackgroundBaseFragment backgroundBaseFragment, LayoutInflater layoutInflater, String str) {
            this.d = backgroundBaseFragment;
            this.c = layoutInflater;
            this.a = str;
            this.b = new ArrayList();
        }

        public int getCount() {
            return (int) Math.ceil(((double) (this.b == null ? 0 : this.b.size())) / 3.0d);
        }

        public com.sds.android.ttpod.framework.modules.theme.a a(int i) {
            return (this.b == null || i >= this.b.size()) ? null : (com.sds.android.ttpod.framework.modules.theme.a) this.b.get(i);
        }

        protected com.sds.android.ttpod.framework.modules.theme.a a(int i, int i2) {
            int i3 = (i * 3) + i2;
            return i3 < this.b.size() ? (com.sds.android.ttpod.framework.modules.theme.a) this.b.get(i3) : null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return a(a(view, viewGroup), i);
        }

        protected View a(View view, ViewGroup viewGroup) {
            if (view != null) {
                return view;
            }
            return a(viewGroup);
        }

        private View a(ViewGroup viewGroup) {
            View inflate = this.c.inflate(R.layout.background_list_item, viewGroup, false);
            b bVar = new b(inflate.findViewById(R.id.background_item1));
            b bVar2 = new b(inflate.findViewById(R.id.background_item2));
            b bVar3 = new b(inflate.findViewById(R.id.background_item3));
            bVar.e().setVisibility(0);
            bVar2.e().setVisibility(0);
            bVar3.e().setVisibility(0);
            bVar.e().setText(R.string.setting_download);
            bVar2.e().setText(R.string.setting_download);
            bVar3.e().setText(R.string.setting_download);
            inflate.setTag(new b[]{bVar, bVar2, bVar3});
            return inflate;
        }

        protected View a(View view, int i) {
            b[] bVarArr = (b[]) view.getTag();
            a(a(i, 0), bVarArr[0], true);
            a(a(i, 1), bVarArr[1], true);
            a(a(i, 2), bVarArr[2], true);
            bVarArr[0].b().setContentDescription("thumbnailImage" + i + "-0");
            bVarArr[1].b().setContentDescription("thumbnailImage" + i + WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
            bVarArr[2].b().setContentDescription("thumbnailImage" + i + "-2");
            return view;
        }

        protected void a(com.sds.android.ttpod.framework.modules.theme.a aVar, b bVar, boolean z) {
            if (bVar != null) {
                View h = bVar.h();
                if (!z || aVar == null) {
                    h.setVisibility(4);
                    return;
                }
                h.setVisibility(0);
                h.setTag(aVar);
                h.setOnClickListener(this.d.mOnBackgroundClickListener);
                c(aVar, bVar.b());
                a(aVar, bVar.a());
                a(aVar, bVar);
                b(aVar, bVar.i());
            }
        }

        private void b(com.sds.android.ttpod.framework.modules.theme.a aVar, ImageView imageView) {
            if (com.sds.android.ttpod.framework.modules.theme.a.a.ONLINE_BACKGROUND != aVar.a()) {
                imageView.setVisibility(8);
            } else {
                y.a(aVar.i(), (View) imageView);
            }
        }

        private void c(com.sds.android.ttpod.framework.modules.theme.a aVar, ImageView imageView) {
            if (aVar.g() != null && !aVar.g().isRecycled()) {
                imageView.setTag("localBackground");
                imageView.setImageBitmap(aVar.g());
            } else if (com.sds.android.ttpod.framework.modules.theme.a.a.ONLINE_BACKGROUND == aVar.a() || aVar.c() != null) {
                g.a(imageView, aVar.c().getPictureUrl(), q.a, q.c, (int) R.drawable.img_skin_default_thumbnail);
            } else {
                imageView.setImageResource(R.drawable.img_skin_default_thumbnail);
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DECODE_BACKGROUND_THUMBNAIL, aVar));
            }
        }

        protected void a(com.sds.android.ttpod.framework.modules.theme.a aVar, ImageView imageView) {
            if (imageView != null) {
                int i = 4;
                if (this.a.equals(aVar.toString())) {
                    i = 0;
                    c(aVar);
                }
                imageView.setVisibility(i);
            }
        }

        private void a(com.sds.android.ttpod.framework.modules.theme.a aVar, b bVar) {
            View g = bVar.g();
            ProgressBar c = bVar.c();
            TextView e = bVar.e();
            View f = bVar.f();
            if (com.sds.android.ttpod.framework.modules.theme.a.a.ONLINE_BACKGROUND == aVar.a()) {
                g.setVisibility(0);
                TaskInfo d = aVar.d();
                if (d != null) {
                    c.setVisibility(0);
                    e.setVisibility(4);
                    f.setVisibility(4);
                    c.setProgress(d.getDownloadProgress().intValue());
                    return;
                }
                e.setVisibility(0);
                f.setVisibility(0);
                c.setVisibility(4);
                return;
            }
            g.setVisibility(4);
        }

        public void a(ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList) {
            this.b.addAll(arrayList);
            notifyDataSetChanged();
        }

        public boolean a(com.sds.android.ttpod.framework.modules.theme.a aVar) {
            return this.a.equals(aVar.toString());
        }

        public void b(com.sds.android.ttpod.framework.modules.theme.a aVar) {
            this.a = aVar.toString();
            c(aVar);
            notifyDataSetChanged();
        }

        public com.sds.android.ttpod.framework.modules.theme.a a() {
            return this.e;
        }

        protected void c(com.sds.android.ttpod.framework.modules.theme.a aVar) {
            this.e = aVar;
        }

        public void d(com.sds.android.ttpod.framework.modules.theme.a aVar) {
            Bitmap g = aVar.g();
            if (!(g == null || g.isRecycled())) {
                g.recycle();
                aVar.a(null);
            }
            this.b.remove(aVar);
            notifyDataSetChanged();
        }

        public ArrayList<com.sds.android.ttpod.framework.modules.theme.a> b() {
            return this.b;
        }

        public String c() {
            return this.a;
        }

        public boolean d() {
            String str = this.a;
            this.a = com.sds.android.ttpod.framework.storage.environment.b.aa();
            return !this.a.equals(str);
        }

        public void e(com.sds.android.ttpod.framework.modules.theme.a aVar) {
            this.b.add(aVar);
        }

        public void a(DownloadTaskInfo downloadTaskInfo) {
            int intValue = downloadTaskInfo.getState().intValue();
            String fileName = downloadTaskInfo.getFileName();
            com.sds.android.ttpod.framework.modules.theme.a aVar = (com.sds.android.ttpod.framework.modules.theme.a) downloadTaskInfo.getTag();
            if (intValue == 4 && aVar.d() != null) {
                aVar.a(com.sds.android.ttpod.framework.modules.theme.a.a.ADD_BY_USER);
                aVar.a(null);
                this.d.doStatistic(aVar.c());
                if (aVar.equals(BackgroundBaseFragment.sLastRequestItem)) {
                    this.d.setSelectedBackground(aVar);
                    BackgroundBaseFragment.sLastRequestItem = null;
                }
                f.a(fileName + " " + this.d.getResources().getString(R.string.download_finished));
            } else if (intValue == 3 || intValue == 5) {
                f.a(this.d.getString(R.string.unknown_error));
                aVar.a(null);
                BackgroundBaseFragment.sLastRequestItem = null;
            }
        }
    }

    protected abstract void initListViewFooter();

    protected abstract void initListViewHeader();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBackgroundAdapter = initAdapter();
        mDownloadManager = com.sds.android.sdk.core.download.a.a();
        if (!mDownloadManager.a(BACKGROUND_DOWNLOAD_ISSUE)) {
            mDownloadManager.a(BACKGROUND_DOWNLOAD_ISSUE, 4);
        }
    }

    protected a initAdapter() {
        return new a(this, getActivity().getLayoutInflater(), com.sds.android.ttpod.framework.storage.environment.b.aa());
    }

    public void onDestroy() {
        super.onDestroy();
        mDownloadManager.b(BACKGROUND_DOWNLOAD_ISSUE);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_background_screen_layout, viewGroup, false);
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().b((int) R.string.change_background);
        this.mStateView = (StateView) view.findViewById(R.id.background_loadingview);
        this.mBackgroundListView = (ListView) view.findViewById(R.id.background_list);
        this.mBackgroundListView.setOnScrollListener(new com.sds.android.ttpod.b.m.a());
        initListViewHeader();
        initListViewFooter();
        this.mBackgroundListView.setAdapter(this.mBackgroundAdapter);
        if (isShowOfflineModeView()) {
            this.mOfflineModeView = p.a(this.mStateView, new com.sds.android.ttpod.b.p.a(this) {
                final /* synthetic */ BackgroundBaseFragment a;

                {
                    this.a = r1;
                }

                public void a() {
                    com.sds.android.ttpod.framework.storage.environment.b.y(false);
                    this.a.mOfflineModeView.setVisibility(8);
                }
            });
        } else if (this.mOfflineModeView != null) {
            this.mOfflineModeView.setVisibility(8);
        }
        showLoadingView();
    }

    public boolean needApplyNavigationBarStyle() {
        return false;
    }

    public boolean needApplyStatusBarStyle() {
        return false;
    }

    private void showLoadingView() {
        if (this.mBackgroundAdapter.getCount() == 0) {
            this.mStateView.setState(StateView.b.LOADING);
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateView.onThemeLoaded();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.BACKGROUND_THUMBNAIL_CREATED, i.a(cls, "backgroundThumbnailCreated", com.sds.android.ttpod.framework.modules.theme.a.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_STATE, i.a(cls, "updateBkgDownloadingState", DownloadTaskInfo.class));
    }

    public void updateBkgDownloadingState(DownloadTaskInfo downloadTaskInfo) {
        if (DownloadTaskInfo.TYPE_BACKGROUND.equals(downloadTaskInfo.getType())) {
            this.mBackgroundAdapter.a(downloadTaskInfo);
        }
    }

    public void backgroundThumbnailCreated(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        if (this.mBackgroundAdapter != null && aVar.g() != null) {
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
    }

    private void setSelectedBackground(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        String b = aVar.b();
        if (aVar.a() == com.sds.android.ttpod.framework.modules.theme.a.a.FOLLOW_SKIN || b != null) {
            this.mBackgroundAdapter.b(aVar);
            saveBackgroundSettingToSystem(aVar);
            x.i();
            if (this instanceof MyBackgroundFragment) {
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_BKG_SET.getValue(), s.PAGE_MY_BACKGROUND.getValue()).post();
                new c("click").a("background_name", aVar.b()).a();
            } else {
                new c("click").a("background_name", aVar.b()).a(Downloads.COLUMN_STATUS, String.valueOf(2)).a();
            }
            refreshEditButton();
        }
    }

    private void saveBackgroundSettingToSystem(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SET_BACKGROUND, aVar.toString()));
    }

    protected void performBkgDeleted(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        Iterator it = sBkgEditListenerList.iterator();
        while (it.hasNext()) {
            a aVar2 = (a) it.next();
            if (aVar2 != null) {
                aVar2.onBkgDeleted(aVar);
            }
        }
    }

    protected List<DownloadTaskInfo> getBackgroundTaskList() {
        return (List) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_TASK_LIST_WITH_TYPE, DownloadTaskInfo.TYPE_BACKGROUND), List.class);
    }

    private String getStatisticOrigin() {
        return "recommend";
    }

    private void tryDownloadBackground(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        OnlineSkinItem c = aVar.c();
        if (c != null) {
            if (!EnvironmentUtils.c.e()) {
                f.a((int) R.string.shake_error_hint);
            } else if (e.b(aVar.h())) {
                f.a((int) R.string.skin_file_already_existed);
            } else {
                TaskInfo a = com.sds.android.ttpod.framework.a.e.a(c.getSkinUrl(), aVar.h(), Long.valueOf(0), aVar.b(), DownloadTaskInfo.TYPE_BACKGROUND, Boolean.valueOf(false), getStatisticOrigin());
                List backgroundTaskList = getBackgroundTaskList();
                if (backgroundTaskList == null || !backgroundTaskList.contains(a)) {
                    a.setTag(aVar);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, a, Boolean.FALSE));
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, a));
                    aVar.a(a);
                    sLastRequestItem = aVar;
                    this.mRefreshHandler.sendEmptyMessage(0);
                    new c("click").a("background_name", aVar.b()).a(Downloads.COLUMN_STATUS, String.valueOf(1)).a();
                    return;
                }
                f.a((int) R.string.downloading_already);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    private void doStatistic(OnlineSkinItem onlineSkinItem) {
        String pictureUrl = onlineSkinItem.getPictureUrl();
        if (pictureUrl == null || !pictureUrl.startsWith("http://api.skin.ttpod.com/skin")) {
            x.m();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_BKG_DOWNLOAD_FROM_CATEGORY.getValue(), s.PAGE_BACKGROUND_CATEGORY_DETAIL.getValue());
            sUserEvent.append("background_name", onlineSkinItem.getName());
            sUserEvent.post();
            return;
        }
        x.h(onlineSkinItem.getName());
        sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_BKG_DOWNLOAD.getValue(), s.PAGE_NICE_BACKGROUND.getValue(), s.PAGE_NONE.getValue());
        sUserEvent.append("background_name", onlineSkinItem.getName());
        sUserEvent.post();
    }

    protected void refreshEditButton() {
    }

    protected boolean isLocalBackground(com.sds.android.ttpod.framework.modules.theme.a.a aVar) {
        return com.sds.android.ttpod.framework.modules.theme.a.a.ADD_BY_USER == aVar;
    }

    protected void checkNormalStateItem(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        if (com.sds.android.ttpod.framework.modules.theme.a.a.ONLINE_BACKGROUND == aVar.a()) {
            if (aVar.d() == null) {
                tryDownloadBackground(aVar);
            }
            sLastRequestItem = aVar;
            return;
        }
        setSelectedBackground(aVar);
        sLastRequestItem = null;
    }

    protected void notifyDataSetChanged() {
        if (this.mBackgroundAdapter != null) {
            this.mBackgroundAdapter.notifyDataSetChanged();
        }
    }

    protected boolean isLocalUnSelectedBackgroundItem(com.sds.android.ttpod.framework.modules.theme.a aVar) {
        if (aVar == null || aVar.a() != com.sds.android.ttpod.framework.modules.theme.a.a.ADD_BY_USER || this.mBackgroundAdapter.a(aVar)) {
            return false;
        }
        return true;
    }

    private boolean isShowOfflineModeView() {
        return isSupportOfflineMode() && p.a();
    }

    public boolean isSupportOfflineMode() {
        return true;
    }

    public void updateBackgroundList(ArrayList<com.sds.android.ttpod.framework.modules.theme.a> arrayList) {
    }

    public boolean isNeedRefresh() {
        List backgroundTaskList = getBackgroundTaskList();
        return backgroundTaskList != null && backgroundTaskList.size() > 0;
    }
}
