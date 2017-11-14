package com.sds.android.ttpod.fragment.main.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.widget.AZSideBar;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BaseGroupListFragment extends BaseFragment {
    private AZSideBar mAZSideBar;
    private View mFailedView;
    private View mFooterView;
    protected ActionExpandableListView mGroupHeaderListView;
    private List<GroupItem> mGroupItemList;
    private a mGroupListAdapter;
    private String mPlayingGroupID;
    private StateView mStateView;

    private class a extends BaseAdapter {
        final /* synthetic */ BaseGroupListFragment a;

        private a(BaseGroupListFragment baseGroupListFragment) {
            this.a = baseGroupListFragment;
        }

        public int getCount() {
            return this.a.mGroupItemList == null ? 0 : this.a.mGroupItemList.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return this.a.getGroupItemView((GroupItem) this.a.mGroupItemList.get(i), view, viewGroup, i);
        }
    }

    protected static class b {
        private TextView a;
        private TextView b;
        private IconTextView c;
        private View d;
        private IconTextView e;

        public b(View view) {
            this.a = (TextView) view.findViewById(R.id.title_view);
            this.b = (TextView) view.findViewById(R.id.subtitle_view);
            this.c = (IconTextView) view.findViewById(R.id.menu_view);
            this.d = view.findViewById(R.id.view_playstate_group);
            this.e = (IconTextView) view.findViewById(R.id.drag_handle);
        }

        public View a() {
            return this.d;
        }

        public TextView b() {
            return this.a;
        }

        public TextView c() {
            return this.b;
        }

        public IconTextView d() {
            return this.c;
        }

        public IconTextView e() {
            return this.e;
        }
    }

    protected abstract void configFailedView(View view);

    protected abstract void configListFooterView(View view);

    protected abstract boolean isLocalGroup();

    protected abstract View onCreateFailedView(LayoutInflater layoutInflater);

    protected abstract View onCreateListFooterView(LayoutInflater layoutInflater);

    protected abstract void onGroupItemClicked(GroupItem groupItem);

    protected abstract void onReloadData();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mStateView = (StateView) layoutInflater.inflate(R.layout.fragment_draggable_group_list, viewGroup, false);
        this.mGroupHeaderListView = (ActionExpandableListView) this.mStateView.findViewById(R.id.list_group);
        this.mFooterView = onCreateListFooterView(layoutInflater);
        if (this.mFooterView != null) {
            this.mGroupHeaderListView.addFooterView(this.mFooterView);
        }
        this.mAZSideBar = (AZSideBar) this.mStateView.findViewById(R.id.azsidebar);
        this.mFailedView = onCreateFailedView(layoutInflater);
        this.mStateView.setFailedView(this.mFailedView);
        return this.mStateView;
    }

    protected boolean isAZSideBarEnable() {
        return false;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP_CHANGED, i.a(getClass(), "playGroupChanged", new Class[0]));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mGroupListAdapter = new a();
        this.mPlayingGroupID = com.sds.android.ttpod.framework.storage.environment.b.m();
        this.mGroupHeaderListView.a(this.mGroupListAdapter, 0, 0);
        this.mGroupHeaderListView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ BaseGroupListFragment a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int a = m.a(this.a.mGroupHeaderListView.getHeaderViewsCount(), i, this.a.mGroupListAdapter.getCount());
                if (a > -1) {
                    this.a.onGroupItemClicked((GroupItem) this.a.mGroupItemList.get(a));
                }
            }
        });
        this.mGroupHeaderListView.setOnItemLongClickListener(new OnItemLongClickListener(this) {
            final /* synthetic */ BaseGroupListFragment a;

            {
                this.a = r1;
            }

            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                int a = m.a(this.a.mGroupHeaderListView.getHeaderViewsCount(), i, this.a.mGroupListAdapter.getCount());
                if (a > -1) {
                    this.a.onGroupItemLongClicked((GroupItem) this.a.mGroupItemList.get(a));
                }
                return true;
            }
        });
        this.mAZSideBar.setOnMatchedPositionChangeListener(new com.sds.android.ttpod.widget.AZSideBar.a(this) {
            final /* synthetic */ BaseGroupListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, String str) {
                this.a.selectRow(i);
            }
        });
        this.mGroupHeaderListView.setOnScrollListener(this.mAZSideBar);
        updateStateViews();
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        updateAZSideBar();
    }

    public void onThemeLoaded() {
        this.mStateView.onThemeLoaded();
        this.mAZSideBar.onThemeLoaded();
        c.a(this.mGroupHeaderListView, ThemeElement.COMMON_SEPARATOR);
        c.a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
        c.a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        notifyDataSetChanged();
    }

    protected void onGroupItemLongClicked(GroupItem groupItem) {
    }

    public void updateGroupList(List<GroupItem> list) {
        this.mGroupItemList = list;
        statisticGroupCount(list);
        notifyDataSetChanged();
        if (isViewAccessAble()) {
            updateStateViews();
            updateAZSideBar();
        }
    }

    private void statisticGroupCount(List<GroupItem> list) {
        if (String.valueOf(s.PAGE_MY_SONGLIST.getValue()).equals(getStatisitcPage()) || "tt_my_songlist".equals(l.a().b())) {
            int i;
            int size = list != null ? list.size() : 0;
            if (size > 0) {
                Iterator it = list.iterator();
                i = 0;
                while (it.hasNext() && !((GroupItem) it.next()).getGroupID().startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX)) {
                    i++;
                }
            } else {
                i = 0;
            }
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_AMOUNT_SONGLIST.getValue(), 0, 0);
            sUserEvent.setPageParameter(true);
            sUserEvent.append("local_count", Integer.valueOf(i));
            sUserEvent.append("online_count", Integer.valueOf(size - i));
            sUserEvent.post();
            d.c.a(i, size - i);
        }
    }

    protected List<GroupItem> getGroupItemList() {
        return this.mGroupItemList;
    }

    protected void removeGroupItem(GroupItem groupItem) {
        if (this.mGroupItemList != null) {
            this.mGroupItemList.remove(groupItem);
            notifyDataSetChanged();
            configListFooterView(this.mFooterView);
        }
    }

    protected ActionExpandableListView getListView() {
        return this.mGroupHeaderListView;
    }

    private void updateAZSideBar() {
        boolean z = false;
        if (isViewAccessAble()) {
            boolean isAZSideBarEnable = isAZSideBarEnable();
            this.mAZSideBar.setVisibility(isAZSideBarEnable ? 0 : 8);
            ActionExpandableListView actionExpandableListView = this.mGroupHeaderListView;
            if (!isAZSideBarEnable) {
                z = true;
            }
            actionExpandableListView.setVerticalScrollBarEnabled(z);
        }
    }

    protected void updateAZKeys(List<String> list) {
        com.sds.android.sdk.lib.util.d.a((Object) list, "rawAZKeys");
        if (isViewAccessAble()) {
            this.mAZSideBar.a((List) list);
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (this.mGroupItemList == null) {
            onReloadData();
        }
    }

    public void selectRow(int i) {
        this.mGroupHeaderListView.requestFocus();
        this.mGroupHeaderListView.setSelection(i);
    }

    public boolean isEmpty() {
        return this.mGroupItemList == null || this.mGroupItemList.isEmpty();
    }

    private void updateStateViews() {
        if (isLocalGroup()) {
            if (isEmpty()) {
                this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
                configFailedView(this.mFailedView);
                return;
            }
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            configListFooterView(this.mFooterView);
        } else if (this.mGroupItemList == null) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
        } else if (this.mGroupItemList.size() > 0) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            configListFooterView(this.mFooterView);
        } else {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
            configFailedView(this.mFailedView);
        }
    }

    public void playGroupChanged() {
        this.mPlayingGroupID = com.sds.android.ttpod.framework.storage.environment.b.m();
        notifyDataSetChanged();
    }

    protected final void notifyDataSetChanged() {
        if (this.mGroupListAdapter != null) {
            this.mGroupListAdapter.notifyDataSetChanged();
        }
    }

    protected View getGroupItemView(GroupItem groupItem, View view, ViewGroup viewGroup, int i) {
        int i2;
        boolean z = false;
        if (view == null) {
            view = getLayoutInflater(null).inflate(R.layout.media_group_item, null);
            view.setTag(new b(view));
        }
        b bVar = (b) view.getTag();
        Context context = view.getContext();
        boolean startsWith = groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX);
        bVar.d().setVisibility(8);
        bVar.b().setText(startsWith ? e.j(groupItem.getName()) : TTTextUtils.validateString(view.getContext(), groupItem.getName()));
        bVar.c().setText(context.getString(R.string.count_of_media, new Object[]{groupItem.getCount()}) + (startsWith ? "  " + groupItem.getName() : ""));
        View a = bVar.a();
        if (com.sds.android.sdk.lib.util.m.a(groupItem.getGroupID(), this.mPlayingGroupID)) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        a.setVisibility(i2);
        if (!com.sds.android.sdk.lib.util.m.a(groupItem.getGroupID(), this.mPlayingGroupID)) {
            z = true;
        }
        view.setEnabled(z);
        c.a(view, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        c.a(bVar.b(), ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(bVar.c(), ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(bVar.a(), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        v.a(bVar.d(), (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        v.a(bVar.e(), (int) R.string.icon_drag_sort, ThemeElement.SONG_LIST_ITEM_TEXT);
        bVar.e().setContentDescription(getResources().getString(R.string.drag_handle) + i);
        bVar.d().setContentDescription(getResources().getString(R.string.menu_post_item) + i);
        return view;
    }
}
