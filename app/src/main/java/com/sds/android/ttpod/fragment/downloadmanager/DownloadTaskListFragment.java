package com.sds.android.ttpod.fragment.downloadmanager;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class DownloadTaskListFragment extends BaseFragment {
    protected int mDownloadType;
    private View mFailedView;
    private int mIconResId = R.string.icon_male;
    private View mLeftActionView;
    private int mNoDataMessage = R.string.no_song_go_recommend;
    private OnClickListener mNoDataViewOnClickListener;
    private View mRightActionView;
    private View mRootView;
    private StateView mStateView;
    protected a mTaskAdapter;
    private b mTaskCountChangeListener;
    protected ListView mTaskListView;
    protected List<DownloadTaskInfo> mTasks = new ArrayList();
    protected View mTopActionPanel;

    public interface b {
        void a(int i);
    }

    class a extends BaseAdapter {
        final /* synthetic */ DownloadTaskListFragment a;
        private SparseArray b = new SparseArray();
        private final LayoutInflater c;
        private List<DownloadTaskInfo> d = new ArrayList();

        final class a extends com.sds.android.ttpod.framework.modules.theme.c.a {
            final /* synthetic */ a a;
            private View b;
            private TextView c;
            private ProgressBar d;
            private TextView e;
            private TextView f;
            private IconTextView g;
            private IconTextView h;
            private IconTextView i;

            private a(a aVar, ViewGroup viewGroup) {
                this.a = aVar;
                this.b = viewGroup;
                this.c = (TextView) viewGroup.findViewById(R.id.textview_download_item_filename);
                this.d = (ProgressBar) viewGroup.findViewById(R.id.progressbar_download_item);
                this.e = (TextView) viewGroup.findViewById(R.id.textivew_download_item_progress);
                this.f = (TextView) viewGroup.findViewById(R.id.textview_download_item_hint_info);
                this.g = (IconTextView) viewGroup.findViewById(R.id.flag_quality_view);
                this.h = (IconTextView) viewGroup.findViewById(R.id.flag_mv_view);
                this.i = (IconTextView) viewGroup.findViewById(R.id.download_delete);
            }

            protected void a() {
                c.a(this.b, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
                c.a(this.d, ThemeElement.COMMON_PROGRESS_BAR);
                c.a(this.c, ThemeElement.SONG_LIST_ITEM_TEXT);
                c.a(this.e, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                c.a(this.f, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                v.a(this.i, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            }
        }

        public a(DownloadTaskListFragment downloadTaskListFragment, LayoutInflater layoutInflater) {
            this.a = downloadTaskListFragment;
            this.c = layoutInflater;
            this.b.append(3, downloadTaskListFragment.getString(R.string.download_paused));
            this.b.append(5, downloadTaskListFragment.getString(R.string.download_failed));
            this.b.append(0, downloadTaskListFragment.getString(R.string.download_waiting));
        }

        public void a(List<DownloadTaskInfo> list) {
            this.a.updateStateViews();
            this.d = list;
            this.a.mTaskAdapter.notifyDataSetChanged();
        }

        public int getCount() {
            return this.d.size();
        }

        public Object getItem(int i) {
            return this.d.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate;
            a aVar;
            if (view == null) {
                inflate = this.c.inflate(R.layout.download_list_item, viewGroup, false);
                a aVar2 = new a((ViewGroup) inflate);
                inflate.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
                inflate = view;
            }
            a(i, aVar);
            return inflate;
        }

        private void a(int i, a aVar) {
            final DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) getItem(i);
            a(aVar, downloadTaskInfo);
            if (m.a(downloadTaskInfo.getAudioQuality())) {
                aVar.g.setVisibility(8);
            } else {
                a(aVar.g, downloadTaskInfo);
            }
            if (DownloadTaskInfo.TYPE_VIDEO.equals(downloadTaskInfo.getType())) {
                aVar.h.setVisibility(0);
            } else {
                aVar.h.setVisibility(8);
            }
            aVar.i.setContentDescription("delete_btn" + i);
            aVar.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    t.a(r.ACTION_DOWNING_SINGLE_CLICK_DELETE, s.PAGE_NONE);
                    f.a(this.b.a.getActivity(), this.b.a(downloadTaskInfo), new com.sds.android.ttpod.common.a.a.a<h>(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(h hVar) {
                            t.a(r.ACTION_DOWNING_SINGLE_CLICK_DELETE_SURE, s.PAGE_NONE);
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_DOWNLOAD_TASK, downloadTaskInfo, Boolean.valueOf(true)));
                            this.a.b.a.removeTask(downloadTaskInfo);
                        }
                    });
                }
            });
            a(downloadTaskInfo, aVar);
            aVar.a(v.b());
        }

        private void a(a aVar, DownloadTaskInfo downloadTaskInfo) {
            aVar.c.setText(a(downloadTaskInfo));
        }

        private String a(DownloadTaskInfo downloadTaskInfo) {
            String k = e.k(downloadTaskInfo.getSavePath());
            if (!DownloadTaskInfo.TYPE_AUDIO.equals(downloadTaskInfo.getType())) {
                return k;
            }
            List c = m.c(k, "-");
            return c.size() > 1 ? ((String) c.get(1)).trim() : ((String) c.get(0)).trim();
        }

        private void a(DownloadTaskInfo downloadTaskInfo, a aVar) {
            int i;
            int i2;
            String str = "";
            Integer fileLength = downloadTaskInfo.getFileLength();
            int downloadLength = downloadTaskInfo.getDownloadLength();
            switch (downloadTaskInfo.getState().intValue()) {
                case 1:
                case 2:
                    downloadLength = ((Integer) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_TASK_DOWNLOADED_LENGTH, downloadTaskInfo), Integer.class)).intValue();
                    downloadTaskInfo.setDownloadLength(downloadLength);
                    i = 0;
                    i2 = downloadLength;
                    break;
                default:
                    i = 8;
                    i2 = downloadLength;
                    break;
            }
            if (i == 0) {
                aVar.d.setProgress(downloadTaskInfo.getDownloadProgress().intValue());
            }
            aVar.d.setVisibility(i);
            aVar.f.setText((String) this.b.get(downloadTaskInfo.getState().intValue()));
            TextView f = aVar.f;
            if (8 == i) {
                downloadLength = 0;
            } else {
                downloadLength = 8;
            }
            f.setVisibility(downloadLength);
            if (fileLength == null || fileLength.intValue() <= 0) {
                aVar.e.setVisibility(8);
                return;
            }
            DownloadTaskListFragment downloadTaskListFragment = this.a;
            r3 = new Object[2];
            r3[0] = String.format("%.1f", new Object[]{Float.valueOf(((float) (i2 >> 16)) / 16.0f)});
            r3[1] = String.format("%.1f", new Object[]{Float.valueOf(((float) (fileLength.intValue() >> 16)) / 16.0f)});
            aVar.e.setText(downloadTaskListFragment.getString(R.string.download_size_ratio, r3));
            aVar.e.setVisibility(0);
        }

        private void a(IconTextView iconTextView, DownloadTaskInfo downloadTaskInfo) {
            AudioQuality valueOf = AudioQuality.valueOf(downloadTaskInfo.getAudioQuality());
            if (valueOf.ordinal() >= AudioQuality.SUPER.ordinal()) {
                iconTextView.setVisibility(0);
                iconTextView.setText(valueOf == AudioQuality.LOSSLESS ? R.string.icon_text_wusun : R.string.icon_text_gaozhi);
                iconTextView.setTextColor(valueOf == AudioQuality.LOSSLESS ? -2185667 : -8665764);
                return;
            }
            iconTextView.setVisibility(8);
        }
    }

    public abstract void onDropDownMenuClicked(int i);

    protected abstract List<DownloadTaskInfo> readTaskList();

    public abstract void updateTaskState(DownloadTaskInfo downloadTaskInfo);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDownloadType = getArguments().getInt(DownloadManagerFragment.DOWNLOAD_TYPE);
        if (this.mDownloadType == DownloadTaskInfo.TYPE_APP.intValue()) {
            setNoDataMessage(R.string.icon_no_playing, R.string.no_content);
        } else if (this.mDownloadType == DownloadTaskInfo.TYPE_VIDEO.intValue()) {
            setNoDataMessage(R.string.icon_no_playing, R.string.no_mv_download);
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mTaskListView, ThemeElement.COMMON_SEPARATOR);
        this.mStateView.onThemeLoaded();
        this.mTaskAdapter.notifyDataSetChanged();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DOWNLOAD_TASK_STATE, i.a(cls, "updateTaskState", DownloadTaskInfo.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.download_task_list_view, viewGroup, false);
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mStateView = (StateView) view.findViewById(R.id.media_task_loading_view);
        this.mFailedView = onCreateFailedView(getLayoutInflater(bundle));
        this.mStateView.setFailedView(this.mFailedView);
        this.mTaskListView = (ListView) view.findViewById(R.id.task_list);
        this.mTopActionPanel = getLayoutInflater(bundle).inflate(R.layout.download_list_header, this.mTaskListView, false);
        this.mTaskListView.addHeaderView(this.mTopActionPanel);
        this.mTaskAdapter = new a(this, getLayoutInflater(bundle));
        updateStateViews();
        this.mTasks = readTaskList();
        updateListVisibility();
        notifyTaskListChanged();
        this.mTaskListView.setAdapter(this.mTaskAdapter);
        this.mTaskListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ DownloadTaskListFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int a = com.sds.android.ttpod.b.m.a(this.a.mTaskListView.getHeaderViewsCount(), i, this.a.mTasks.size());
                if (a >= 0) {
                    switch (((DownloadTaskInfo) this.a.mTasks.get(a)).getState().intValue()) {
                        case 0:
                        case 1:
                        case 2:
                            t.a(r.ACTION_DOWNING_SINGLE_PAUSE, s.PAGE_NONE);
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.CANCEL_DOWNLOAD_TASK, r0));
                            return;
                        case 3:
                        case 5:
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, r0));
                            return;
                        default:
                            return;
                    }
                }
            }
        });
    }

    protected void setTopActionPanelVisible(int i) {
        this.mTopActionPanel.setVisibility(i);
    }

    protected void setLeftTopAction(int i, int i2, OnClickListener onClickListener) {
        this.mLeftActionView = this.mTopActionPanel.findViewById(R.id.left_action);
        this.mLeftActionView.setOnClickListener(onClickListener);
        ((TextView) this.mTopActionPanel.findViewById(R.id.left_action_name)).setText(i);
        ((ImageView) this.mTopActionPanel.findViewById(R.id.left_action_icon)).setImageResource(i2);
    }

    protected void setRightTopAction(int i, int i2, OnClickListener onClickListener) {
        this.mRightActionView = this.mTopActionPanel.findViewById(R.id.right_action);
        this.mRightActionView.setOnClickListener(onClickListener);
        ((TextView) this.mTopActionPanel.findViewById(R.id.right_action_name)).setText(i);
        ((ImageView) this.mTopActionPanel.findViewById(R.id.right_action_icon)).setImageResource(i2);
    }

    public void setOnTaskCountChangeListener(b bVar) {
        this.mTaskCountChangeListener = bVar;
    }

    protected View getTopActionPanel() {
        return this.mTopActionPanel;
    }

    protected void addTask(DownloadTaskInfo downloadTaskInfo) {
        addTask(this.mTasks.size(), downloadTaskInfo);
    }

    protected void addTask(int i, DownloadTaskInfo downloadTaskInfo) {
        this.mTasks.add(i, downloadTaskInfo);
        updateListVisibility();
        notifyTaskListChanged();
    }

    protected void removeTask(DownloadTaskInfo downloadTaskInfo) {
        this.mTasks.remove(downloadTaskInfo);
        updateListVisibility();
        notifyTaskListChanged();
    }

    protected void clear() {
        this.mTasks.clear();
        updateListVisibility();
        notifyTaskListChanged();
    }

    protected void updateListVisibility() {
    }

    protected final void notifyTaskListChanged() {
        if (this.mTaskCountChangeListener != null) {
            this.mTaskCountChangeListener.a(this.mTasks.size());
        }
        this.mTaskAdapter.a(this.mTasks);
    }

    private void updateStateViews() {
        if (!needFailedState()) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
        } else if (this.mTasks == null) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        } else if (this.mTasks.size() == 0) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
            configFailedView(this.mFailedView);
        } else if (this.mTasks.size() > 0) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
        }
    }

    protected boolean needFailedState() {
        return true;
    }

    protected void configFailedView(View view) {
        configNoDataView((IconTextView) view.findViewById(R.id.no_media_icon), (TextView) view.findViewById(R.id.textview_load_failed), (TextView) view.findViewById(R.id.no_data_action_view));
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, null);
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        iconTextView.setOnClickListener(this.mNoDataViewOnClickListener);
        textView.setOnClickListener(this.mNoDataViewOnClickListener);
        iconTextView.setText(this.mIconResId);
        textView.setText(com.sds.android.ttpod.common.c.c.a(getString(this.mNoDataMessage), getResources().getColor(R.color.not_data_hint_text)));
        textView2.setVisibility(4);
    }

    public void setNoDataViewOnClickListener(OnClickListener onClickListener) {
        this.mNoDataViewOnClickListener = onClickListener;
    }

    private void setNoDataMessage(int i, int i2) {
        this.mIconResId = i;
        this.mNoDataMessage = i2;
    }
}
