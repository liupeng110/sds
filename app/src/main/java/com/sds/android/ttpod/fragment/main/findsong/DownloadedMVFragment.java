package com.sds.android.ttpod.fragment.main.findsong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.x;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.video.d;
import com.sds.android.ttpod.fragment.mv.MVFragment;
import com.sds.android.ttpod.framework.a.b.n;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadedMVFragment extends BaseFragment implements com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.a {
    private static final String SINGER_NAME_SEPARATOR = " - ";
    private static final String TAG = "DownloadedMVFragment";
    private View mFailedView;
    protected a mListAdapter;
    protected ActionExpandableListView mListView;
    private com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.b mListener;
    private MvData mMVData;
    private SparseArrayCompat<String> mMvFilePath = new SparseArrayCompat();
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ DownloadedMVFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            BaseFragment mVFragment = new MVFragment();
            mVFragment.setCurrentPosition(0);
            this.a.launchFragment(mVFragment);
        }
    };
    private StateView mStateView;

    private class a extends com.sds.android.ttpod.adapter.a<MvData> {
        final /* synthetic */ DownloadedMVFragment a;
        private Handler e = new Handler();
        private HashMap<String, MvData> f = new HashMap();
        private int g;
        private int h;

        public a(DownloadedMVFragment downloadedMVFragment, Context context) {
            this.a = downloadedMVFragment;
            this.g = (int) context.getResources().getDimension(R.dimen.mv_thumbnail_width);
            this.h = (int) context.getResources().getDimension(R.dimen.mv_thumbnail_height);
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.c.inflate(R.layout.mv_list_item_downloaded, viewGroup, false);
            inflate.setTag(new h(inflate));
            return inflate;
        }

        protected void a(final View view, MvData mvData, int i) {
            if (this.d != null) {
                MvData mvData2 = (MvData) this.d.get(i);
                view.setTag(R.id.view_bind_data, mvData2);
                h hVar = (h) view.getTag();
                a(hVar.c(), mvData2.getName());
                hVar.d().setText(mvData2.getSingerName());
                hVar.b().setTag(R.id.view_bind_data, mvData2);
                ImageView e = hVar.e();
                String str = (String) this.a.mMvFilePath.get(mvData2.hashCode());
                if (str != null) {
                    a(str, e);
                    a(str, hVar.g(), hVar.f());
                }
                hVar.a();
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.mMVData = (MvData) view.getTag(R.id.view_bind_data);
                    if (this.b.a.mMVData != null) {
                        this.b.a.playMV(this.b.a.mMVData);
                    }
                }
            });
            c.a(view, ThemeElement.TILE_BACKGROUND);
        }

        private void a(TextView textView, String str) {
            if (!m.a(str)) {
                int lastIndexOf = str.lastIndexOf(DownloadedMVFragment.SINGER_NAME_SEPARATOR);
                if (lastIndexOf != -1) {
                    str = str.substring(0, lastIndexOf);
                }
            }
            textView.setText(str);
        }

        private void a(final String str, final ImageView imageView) {
            final String str2 = com.sds.android.ttpod.framework.a.A() + File.separator + (e.k(str) + ".jpg");
            Bitmap a = g.a(str2, this.g, this.h, true);
            if (a != null) {
                imageView.setImageBitmap(a);
                return;
            }
            imageView.setImageResource(R.drawable.img_mv_default_image);
            com.sds.android.sdk.lib.e.a.a(this.a, new Runnable(this) {
                final /* synthetic */ a d;

                public void run() {
                    Bitmap a = g.a(str2, this.d.g, this.d.h, false);
                    if (a == null && j.a()) {
                        a = ThumbnailUtils.createVideoThumbnail(str, 3);
                        g.a(str2, this.d.g, this.d.h, a);
                    }
                    this.d.e.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            if (imageView != null && a != null) {
                                imageView.setImageBitmap(a);
                            }
                        }
                    });
                }
            });
        }

        private String a(int i, List<MvListItem> list) {
            if (list == null) {
                return "";
            }
            for (MvListItem mvListItem : list) {
                if (i == mvListItem.getType()) {
                    return String.valueOf(com.sds.android.sdk.lib.util.c.a(mvListItem.getDuration()));
                }
            }
            return "";
        }

        private void a(final String str, final TextView textView, final TextView textView2) {
            if (this.f.containsKey(str)) {
                MvData mvData = (MvData) this.f.get(str);
                String a = a(mvData.getDownloadQuality(), mvData.getMvList());
                a(textView, mvData.getDownloadQuality());
                if (m.a(a)) {
                    textView2.setVisibility(4);
                    return;
                }
                textView2.setText(a);
                textView2.setVisibility(0);
                return;
            }
            textView2.setVisibility(4);
            textView.setVisibility(4);
            com.sds.android.sdk.lib.e.a.a(this.a, new Runnable(this) {
                final /* synthetic */ a d;

                public void run() {
                    MvData a = d.a(str);
                    if (a != null) {
                        this.d.f.put(str, a);
                        this.d.a(this.d.a(a.getDownloadQuality(), a.getMvList()), a.getDownloadQuality(), textView2, textView);
                    }
                }
            });
        }

        private void a(TextView textView, int i) {
            if (i > 0) {
                textView.setVisibility(0);
                textView.setText(x.a(i).getMvQuality());
                return;
            }
            textView.setVisibility(8);
        }

        private void a(String str, int i, TextView textView, TextView textView2) {
            final TextView textView3 = textView2;
            final TextView textView4 = textView;
            final int i2 = i;
            final String str2 = str;
            this.e.post(new Runnable(this) {
                final /* synthetic */ a e;

                public void run() {
                    if (textView3 != null && textView4 != null) {
                        this.e.a(textView3, i2);
                        if (!m.a(str2)) {
                            textView4.setText(str2);
                            textView4.setVisibility(0);
                        }
                    }
                }
            });
        }
    }

    private class b implements OnClickListener {
        final /* synthetic */ DownloadedMVFragment a;
        private Activity b;
        private MvData c;
        private ActionExpandableListView d;

        public b(DownloadedMVFragment downloadedMVFragment, Activity activity, MvData mvData, ActionExpandableListView actionExpandableListView) {
            this.a = downloadedMVFragment;
            this.b = activity;
            this.c = mvData;
            this.d = actionExpandableListView;
        }

        private void a(Context context, final MvData mvData) {
            h hVar = new h(context, context.getString(R.string.remove_confirm, new Object[]{mvData.getName()}), null, null);
            hVar.setTitle((int) R.string.delete_mv);
            hVar.a((int) R.string.ok, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ b b;

                public void a(h hVar) {
                    File access$100 = this.b.a.getDownloadFile(mvData);
                    if (access$100 != null && this.b.a.mListAdapter.b() != null) {
                        access$100.delete();
                        this.b.a(mvData);
                        this.b.a.mListAdapter.b().remove(mvData);
                        this.b.a.mListAdapter.notifyDataSetChanged();
                        this.b.a.updateTaskCount();
                    }
                }
            }, (int) R.string.cancel, null);
            hVar.show();
        }

        private void a(MvData mvData) {
            String str = com.sds.android.ttpod.framework.a.A() + File.separator + e.k((String) this.a.mMvFilePath.get(mvData.hashCode()));
            e.h(str);
            e.h(str + ".dm");
            e.h(str + ".jpg");
        }

        public void onClick(View view) {
            com.sds.android.ttpod.b.m.a(this.d);
            if (this.c != null && this.b != null) {
                n.b();
                a(this.b, this.c);
                int id = this.c.getId();
                com.sds.android.ttpod.framework.a.b.b a = new com.sds.android.ttpod.framework.a.b.b().b("mv_drop-down_menu_delete").a("mv_id", String.valueOf(id));
                if (id == 0) {
                    a.a("mv_name", String.valueOf(this.c.getName()));
                }
                a.a();
            }
        }
    }

    public void setOnTaskCountChangeListener(com.sds.android.ttpod.fragment.downloadmanager.DownloadTaskListFragment.b bVar) {
        this.mListener = bVar;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_STATE, i.a(getClass(), "updateTaskState", DownloadTaskInfo.class));
    }

    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_VIDEO && downloadTaskInfo.getState().intValue() == 4) {
            updateDownloadMvList();
        }
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setText((int) R.string.icon_media_menu_mv);
        iconTextView.setTextColor(getResources().getColor(R.color.black));
        textView.setText(com.sds.android.ttpod.common.c.c.a(getString(R.string.no_mv_download), getResources().getColor(R.color.not_data_hint_text)));
        textView2.setVisibility(4);
        iconTextView.setOnClickListener(this.mOnClickListener);
        textView.setOnClickListener(this.mOnClickListener);
    }

    public void updateDownloadMvList() {
        ArrayList readDownloadMvList = readDownloadMvList();
        this.mListAdapter.a((List) readDownloadMvList);
        if (this.mListener != null) {
            this.mListener.a(readDownloadMvList.size());
        }
        if (this.mListAdapter.isEmpty()) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
        } else {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
        }
        com.sds.android.ttpod.framework.a.b.d.c.a("my_mv", String.valueOf(readDownloadMvList.size()));
    }

    private ArrayList<MvData> readDownloadMvList() {
        ArrayList<MvData> arrayList = new ArrayList();
        File file = new File(com.sds.android.ttpod.framework.a.z());
        final String[] stringArray = getResources().getStringArray(R.array.mv_suffix);
        File[] listFiles = file.listFiles(new FileFilter(this) {
            final /* synthetic */ DownloadedMVFragment b;

            public boolean accept(File file) {
                String toLowerCase = file.getPath().toLowerCase();
                for (String str : stringArray) {
                    if (toLowerCase.endsWith("." + str)) {
                        return true;
                    }
                }
                return false;
            }
        });
        if (listFiles != null) {
            for (File file2 : listFiles) {
                MvData parseMvDataFromFile = parseMvDataFromFile(e.k(file2.getName()));
                if (parseMvDataFromFile != null) {
                    arrayList.add(parseMvDataFromFile);
                    this.mMvFilePath.put(parseMvDataFromFile.hashCode(), file2.getPath());
                }
            }
        }
        return arrayList;
    }

    protected MvData parseMvDataFromFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2;
        int indexOf;
        String str3 = SINGER_NAME_SEPARATOR;
        int indexOf2 = str.indexOf(str3);
        if (indexOf2 < 0) {
            str3 = str3.trim();
            str2 = str3;
            indexOf = str.indexOf(str3);
        } else {
            str2 = str3;
            indexOf = indexOf2;
        }
        if (indexOf < 0) {
            return null;
        }
        MvData mvData = new MvData();
        String substring = str.substring(str2.length() + indexOf);
        int indexOf3 = substring.indexOf(str2);
        String str4 = "";
        if (indexOf3 > 0) {
            str4 = substring.substring(0, indexOf3);
            str4.trim();
        }
        mvData.setName(str4);
        if (indexOf > 0) {
            mvData.setSingerName(str.substring(0, indexOf));
        }
        return mvData;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mListAdapter = new a(this, getActivity());
    }

    public void onResume() {
        super.onResume();
        n.a("mv_channel");
    }

    public void onPause() {
        super.onPause();
        n.a("other_channel");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_local_mv_list, viewGroup, false);
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.local_mv_state_view);
        this.mFailedView = onCreateFailedView(getLayoutInflater(null));
        configNoDataView((IconTextView) this.mFailedView.findViewById(R.id.no_media_icon), (TextView) this.mFailedView.findViewById(R.id.textview_load_failed), (TextView) this.mFailedView.findViewById(R.id.no_data_action_view));
        this.mStateView.setFailedView(this.mFailedView);
        this.mListView = (ActionExpandableListView) view.findViewById(R.id.lv_downloaded_mv);
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.a(this.mListAdapter, R.id.menu_view, R.id.expandable);
        this.mListView.setItemExpandCollapseListener(this);
        updateDownloadMvList();
    }

    protected void updateTaskCount() {
        int count = this.mListAdapter.getCount();
        if (count == 0) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
        }
        if (this.mListener != null) {
            this.mListener.a(count);
        }
    }

    public void onExpand(View view, int i) {
        View view2 = (View) view.getParent();
        updateMenuArrowView(view2, true);
        View findViewById = view2.findViewById(R.id.media_menu_delete);
        if (findViewById != null) {
            MvData mvData = (MvData) view2.getTag(R.id.view_bind_data);
            findViewById.setOnClickListener(new b(this, getActivity(), mvData, this.mListView));
            int id = mvData.getId();
            com.sds.android.ttpod.framework.a.b.b a = new com.sds.android.ttpod.framework.a.b.b().b("mv_drop-down_menu").a("mv_id", String.valueOf(id));
            if (id == 0) {
                a.a("mv_name", String.valueOf(mvData.getName()));
            }
            a.a();
        }
    }

    public void onCollapse(View view, int i) {
        updateMenuArrowView((View) view.getParent(), false);
    }

    private void updateMenuArrowView(View view, boolean z) {
        ((IconTextView) view.findViewById(R.id.menu_icon_image)).setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        this.mStateView.onThemeLoaded();
        this.mListAdapter.notifyDataSetChanged();
    }

    private void playMV(MvData mvData) {
        File file;
        String str = (String) this.mMvFilePath.get(mvData.hashCode());
        try {
            file = new File(str);
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        }
        if (file != null && file.exists()) {
            com.sds.android.ttpod.framework.a.b.d.j.a("mv_origin", "my_download");
            f.a(getActivity(), mvData.getName(), mvData.getSingerName(), str);
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_PLAY_LOCAL_MV.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
            sUserEvent.setPageParameter(true);
            sUserEvent.post();
        }
    }

    public void deleteAllCompleted() {
        com.sds.android.ttpod.component.d.f.a(getActivity(), new com.sds.android.ttpod.common.a.a.a<h>(this) {
            final /* synthetic */ DownloadedMVFragment a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.clearList();
                this.a.updateTaskCount();
            }
        });
    }

    private void clearList() {
        if (this.mListAdapter != null) {
            List<MvData> b = this.mListAdapter.b();
            if (b != null) {
                for (MvData downloadFile : b) {
                    File downloadFile2 = getDownloadFile(downloadFile);
                    if (downloadFile2 != null) {
                        downloadFile2.delete();
                    }
                }
                b.clear();
            }
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    private File getDownloadFile(MvData mvData) {
        String str = (String) this.mMvFilePath.get(mvData.hashCode());
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        return null;
    }
}
