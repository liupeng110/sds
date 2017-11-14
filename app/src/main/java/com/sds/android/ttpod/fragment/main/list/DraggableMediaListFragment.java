package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.local.MediaPickerActivity;
import com.sds.android.ttpod.activities.mediascan.FilePickerActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.b.l;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.b.g;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.ExchangeOrderEntity;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.DraggableListView.b;
import com.sds.android.ttpod.widget.DraggableListView.f;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.ArrayList;
import java.util.List;

public class DraggableMediaListFragment extends MediaListFragment {
    private List<int[]> mExchangeOrderList = new ArrayList();

    public void addMedia(boolean z) {
        if (z) {
            startActivity(new Intent(getActivity(), MediaPickerActivity.class).putExtra(AbsMediaListFragment.KEY_GROUP_ID, getGroupID()));
            return;
        }
        if (l.b(getArguments().getString(AbsMediaListFragment.KEY_GROUP_ID))) {
            com.sds.android.ttpod.framework.a.b.l.ap();
        }
        startActivityForResult(new Intent(getActivity(), FilePickerActivity.class).putExtra(AbsMediaListFragment.KEY_GROUP_ID, getGroupID()), 1);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            getActivity();
            if (i2 == -1) {
                String[] stringArrayExtra = intent.getStringArrayExtra(FilePickerActivity.KEY_EXTRA_SELECTED_FILES);
                Intent intent2 = new Intent(getActivity(), MediaScanAnimationActivity.class);
                intent2.putExtra(MediaScanAnimationActivity.KEY_SCAN_FILES, stringArrayExtra);
                intent2.putExtra(MediaScanAnimationActivity.KEY_GROUP_ID, getGroupID());
                startActivity(intent2);
            }
        }
    }

    public void startEdit() {
        super.startEdit();
        ActionExpandableListView listView = getListView();
        listView.setDragListener(null);
        listView.setDropListener(null);
    }

    public void stopEdit() {
        super.stopEdit();
        setDragAndDropListener();
    }

    private void setDragAndDropListener() {
        ActionExpandableListView listView = getListView();
        listView.setDragListener(new b(this) {
            final /* synthetic */ DraggableMediaListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                if (i >= 0 && i2 >= 0 && i != i2 && i2 < this.a.bottomLimit() && i < this.a.bottomLimit()) {
                    this.a.mExchangeOrderList.add(new int[]{i, i2});
                }
            }
        });
        listView.setDropListener(new f(this) {
            final /* synthetic */ DraggableMediaListFragment a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                List mediaItemList = this.a.getMediaItemList();
                List arrayList = new ArrayList(this.a.mExchangeOrderList.size());
                String groupID = this.a.getGroupID();
                for (int[] iArr : this.a.mExchangeOrderList) {
                    MediaItem mediaItem = (MediaItem) mediaItemList.get(iArr[0]);
                    MediaItem mediaItem2 = (MediaItem) mediaItemList.get(iArr[1]);
                    arrayList.add(new ExchangeOrderEntity(groupID, mediaItem.getID(), mediaItem2.getID()));
                    mediaItemList.set(iArr[0], mediaItem2);
                    mediaItemList.set(iArr[1], mediaItem);
                }
                com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.COMMIT_EXCHANGE_ORDER, this.a.getGroupID(), arrayList));
                this.a.mExchangeOrderList.clear();
                this.a.notifyDataSetChanged();
            }
        });
        listView.setDragStartViewId(R.id.drag_handle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setDragAndDropListener();
    }

    protected void configFailedView(View view) {
        view.findViewById(R.id.no_data_action_view).setVisibility(8);
        ((TextView) view.findViewById(R.id.textview_load_failed)).setText(R.string.no_song_empty_list);
    }

    protected View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
        View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
        g gVar = (g) mediaItemView.getTag();
        gVar.m().setVisibility(isEditing() ? 4 : 0);
        v.a(gVar.m(), (int) R.string.icon_drag_sort, ThemeElement.SONG_LIST_ITEM_TEXT);
        return mediaItemView;
    }

    private int bottomLimit() {
        return getMediaItemList() != null ? getMediaItemList().size() : 0;
    }
}
