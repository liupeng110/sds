package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.DraggableListView.b;
import com.sds.android.ttpod.widget.DraggableListView.f;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.List;

public class DraggableGroupListFragment extends GroupListFragment {
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ActionExpandableListView listView = getListView();
        listView.setDragListener(new b(this) {
            final /* synthetic */ DraggableGroupListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                List groupItemList = this.a.getGroupItemList();
                if (i >= 0 && i2 >= 0 && i != i2 && i2 < this.a.bottomLimit() && i < this.a.bottomLimit()) {
                    GroupItem groupItem = (GroupItem) groupItemList.get(i);
                    GroupItem groupItem2 = (GroupItem) groupItemList.get(i2);
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.ADD_GROUP_ITEM_EXCHANGE_ORDER_EVENT, this.a.getGroupType(), groupItem.getGroupID(), groupItem2.getGroupID()));
                    groupItemList.set(i, groupItem2);
                    groupItemList.set(i2, groupItem);
                }
            }
        });
        listView.setDropListener(new f(this) {
            final /* synthetic */ DraggableGroupListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                t.a(r.ACTION_MY_SONGLIST_DRAG_SORT, s.PAGE_NONE);
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.COMMIT_GROUP_ITEM_EXCHANGE_ORDER, this.a.getGroupType()));
                this.a.notifyDataSetChanged();
            }
        });
        listView.setDragStartViewId(R.id.drag_handle);
    }

    protected View getGroupItemView(final GroupItem groupItem, View view, ViewGroup viewGroup, int i) {
        View groupItemView = super.getGroupItemView(groupItem, view, viewGroup, i);
        b bVar = (b) groupItemView.getTag();
        bVar.e().setVisibility(0);
        String groupID = groupItem.getGroupID();
        if (!groupID.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) || groupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_ADD) || groupID.startsWith(MediaStorage.GROUP_ID_RECENTLY_PLAY)) {
            bVar.d().setVisibility(8);
        } else {
            bVar.d().setVisibility(0);
            bVar.d().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DraggableGroupListFragment b;

                public void onClick(View view) {
                    this.b.showContextDialog(groupItem);
                }
            });
        }
        return groupItemView;
    }

    protected void showContextDialog(GroupItem groupItem) {
    }

    private int bottomLimit() {
        return getGroupItemList() != null ? getGroupItemList().size() : 0;
    }

    public void onKeyPressed(int i, KeyEvent keyEvent) {
    }
}
