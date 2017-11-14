package com.sds.android.ttpod.fragment.downloadmanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UncompletedDownloadFragment extends DownloadTaskListFragment {
    private static final int MSG_REFRESH_ALL = 1;
    private static final int MSG_REFRESH_PROGRESS = 0;
    private static final int REFRESH_TIME = 500;
    private Set<String> mDownloadingTaskIds = new HashSet();
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ UncompletedDownloadFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (this.a.mTaskAdapter != null) {
                        this.a.mTaskAdapter.notifyDataSetChanged();
                    }
                    if (this.a.mDownloadingTaskIds.size() > 0) {
                        sendEmptyMessageDelayed(0, 500);
                        return;
                    }
                    return;
                case 1:
                    if (this.a.mTaskAdapter != null) {
                        this.a.mTaskAdapter.notifyDataSetChanged();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private View mLeftActionView;
    private View mRightActionView;

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_DOWNLOAD_TASK_LIST_RELOADED, i.a(getClass(), "reloadDownloadTaskList", new Class[0]));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.mDownloadingTaskIds.size() > 0) {
            sendMessageDelayed(0, 500);
        }
        this.mLeftActionView = this.mTopActionPanel.findViewById(R.id.left_action);
        this.mLeftActionView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UncompletedDownloadFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                l.aE();
                t.a(r.ACTION_MY_DOWNLOAD_PAUSE_ALL, s.PAGE_NONE);
                this.a.pauseAll();
            }
        });
        this.mRightActionView = this.mTopActionPanel.findViewById(R.id.right_action);
        this.mRightActionView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UncompletedDownloadFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                l.aD();
                t.a(r.ACTION_MY_DOWNLOAD_START_ALL, s.PAGE_NONE);
                this.a.startAll();
            }
        });
    }

    protected void updateListVisibility() {
        if (this.mTasks.isEmpty()) {
            this.mTaskListView.setVisibility(4);
            setTopActionPanelVisible(4);
            return;
        }
        this.mTaskListView.setVisibility(0);
        setTopActionPanelVisible(0);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        c.a(this.mTopActionPanel.findViewById(R.id.left_action_name), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.mTopActionPanel.findViewById(R.id.right_action_name), ThemeElement.SONG_LIST_ITEM_TEXT);
        v.a((IconTextView) this.mTopActionPanel.findViewById(R.id.left_action_icon), ThemeElement.SONG_LIST_ITEM_TEXT);
        v.a((IconTextView) this.mTopActionPanel.findViewById(R.id.right_action_icon), ThemeElement.SONG_LIST_ITEM_TEXT);
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            l.aC();
        }
    }

    public void onDestroyView() {
        this.mHandler.removeMessages(0);
        super.onDestroyView();
    }

    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        if (downloadTaskInfo.getType() != DownloadTaskInfo.TYPE_AUDIO && downloadTaskInfo.getType() != DownloadTaskInfo.TYPE_VIDEO) {
            return;
        }
        if (downloadTaskInfo.getState().intValue() != 4) {
            if (!this.mTasks.contains(downloadTaskInfo)) {
                addTask(downloadTaskInfo);
            } else if (downloadTaskInfo.getState().intValue() == 5 || downloadTaskInfo.getState().intValue() == 3) {
                this.mDownloadingTaskIds.remove(downloadTaskInfo.getSavePath());
            }
            if (downloadTaskInfo.getState().intValue() == 2) {
                this.mDownloadingTaskIds.add(downloadTaskInfo.getSavePath());
                sendMessage(0);
            }
            sendMessage(1);
        } else if (this.mTasks.contains(downloadTaskInfo)) {
            this.mDownloadingTaskIds.remove(downloadTaskInfo.getSavePath());
            removeTask(downloadTaskInfo);
        }
    }

    public void reloadDownloadTaskList() {
        this.mTasks = readTaskList();
        updateListVisibility();
        notifyTaskListChanged();
    }

    protected List<DownloadTaskInfo> readTaskList() {
        List<DownloadTaskInfo> arrayList;
        List<DownloadTaskInfo> list = (List) b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_UNCOMPLETED_TASK_LIST_BY_TYPE, DownloadTaskInfo.TYPE_AUDIO), List.class);
        list.addAll((List) b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.GET_UNCOMPLETED_TASK_LIST_BY_TYPE, DownloadTaskInfo.TYPE_VIDEO), List.class));
        Collections.sort(list, new Comparator<DownloadTaskInfo>(this) {
            final /* synthetic */ UncompletedDownloadFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DownloadTaskInfo) obj, (DownloadTaskInfo) obj2);
            }

            public int a(DownloadTaskInfo downloadTaskInfo, DownloadTaskInfo downloadTaskInfo2) {
                return this.a.compareTimeMillis(downloadTaskInfo.getAddTime().longValue(), downloadTaskInfo2.getAddTime().longValue());
            }
        });
        if (list == null) {
            arrayList = new ArrayList(0);
        } else {
            arrayList = list;
        }
        doAliStats(arrayList);
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) arrayList.get(size);
            if (downloadTaskInfo.getState().intValue() == 2) {
                this.mDownloadingTaskIds.add(downloadTaskInfo.getSavePath());
            }
        }
        return arrayList;
    }

    private int compareTimeMillis(long j, long j2) {
        if (j == j2) {
            return 0;
        }
        if (j > j2) {
            return 1;
        }
        return -1;
    }

    private void doAliStats(List<DownloadTaskInfo> list) {
        if (!d.t.a().b().equals("classify")) {
            int i = 0;
            for (DownloadTaskInfo type : list) {
                int i2;
                if (type.getType().equals(DownloadTaskInfo.TYPE_AUDIO)) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                i = i2;
            }
            d.c.a("my_downloading_song", String.valueOf(i));
        }
    }

    public void onDropDownMenuClicked(int i) {
        switch (i) {
            case 9:
                h hVar = new h(getActivity(), (int) R.string.download_remove_all_downloading_confirm_hint, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                    final /* synthetic */ UncompletedDownloadFragment a;

                    {
                        this.a = r1;
                    }

                    public void a(h hVar) {
                        if (this.a.getActivity() != null) {
                            t.a(r.ACTION_MY_DOWNLOAD_DELETE_ALL_SURE, s.PAGE_NONE);
                            hVar.dismiss();
                            this.a.deleteAllUncompleted();
                            l.aF();
                        }
                    }
                }, null);
                hVar.setTitle((int) R.string.download_remove_file_title);
                hVar.show();
                return;
            default:
                return;
        }
    }

    private void startAll() {
        for (DownloadTaskInfo downloadTaskInfo : this.mTasks) {
            if (downloadTaskInfo.getState().intValue() == 3 || downloadTaskInfo.getState().intValue() == 5) {
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.ADD_DOWNLOAD_TASK, downloadTaskInfo));
            }
        }
    }

    private void pauseAll() {
        this.mHandler.removeMessages(0);
        for (DownloadTaskInfo downloadTaskInfo : this.mTasks) {
            if (downloadTaskInfo.getState().intValue() == 0 || downloadTaskInfo.getState().intValue() == 2 || downloadTaskInfo.getState().intValue() == 1) {
                b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.CANCEL_DOWNLOAD_TASK, downloadTaskInfo));
            }
        }
    }

    private void deleteAllUncompleted() {
        for (DownloadTaskInfo downloadTaskInfo : this.mTasks) {
            b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.DELETE_DOWNLOAD_TASK, downloadTaskInfo, Boolean.valueOf(true)));
        }
        this.mDownloadingTaskIds.clear();
        this.mHandler.removeMessages(0);
        clear();
    }

    private void sendMessage(int i) {
        sendMessageDelayed(i, 0);
    }

    private void sendMessageDelayed(int i, long j) {
        if (!this.mHandler.hasMessages(i)) {
            this.mHandler.sendEmptyMessageDelayed(i, j);
        }
    }
}
