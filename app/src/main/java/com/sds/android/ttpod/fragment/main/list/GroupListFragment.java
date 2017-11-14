package com.sds.android.ttpod.fragment.main.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.local.MediaGroupSearchActivity;
import com.sds.android.ttpod.activities.mediascan.MediaScanAnimationActivity;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupListFragment extends BaseGroupListFragment implements b, c {
    private static final SparseArray<a> CLICK_ACTION_MAP = new SparseArray();
    private static final long DELAY_LAUNCH = 200;
    public static final String KEY_GROUP_TYPE = "group_type";
    private static final SparseArray<r> ORDERID_ACTION_MAP = new SparseArray();
    private static final String TAG = "GroupListFragment";
    protected GroupType mGroupType;

    private static final class a {
        private r a;
        private s b;
        private s c;

        private a(r rVar, s sVar, s sVar2) {
            this.a = rVar;
            this.b = sVar;
            this.c = sVar2;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Object arguments = getArguments();
        d.a(arguments, "bundle");
        String string = arguments.getString(KEY_GROUP_TYPE);
        if (m.a(string)) {
            throw new IllegalArgumentException("groupType must not be empty");
        }
        this.mGroupType = GroupType.valueOf(string);
        g.a(TAG, "onCreate lookStatisticId grouptype=%s", this.mGroupType.name());
        if (this.mGroupType == GroupType.CUSTOM_LOCAL || this.mGroupType == GroupType.CUSTOM_ALL || this.mGroupType == GroupType.DEFAULT_ALBUM || this.mGroupType == GroupType.DEFAULT_ARTIST || this.mGroupType == GroupType.DEFAULT_FOLDER || this.mGroupType == GroupType.DEFAULT_GENRE) {
            a aVar = (a) CLICK_ACTION_MAP.get(this.mGroupType.ordinal());
            if (aVar != null) {
                setStatisticPage(aVar.b);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("GroupType must be GroupType.CUSTOM_LOCAL, GroupType.DEFAULT_ARTIST, GroupType.DEFAULT_ALBUM, GroupType.DEFAULT_FOLDER or GroupType.DEFAULT_GENRE");
    }

    static {
        CLICK_ACTION_MAP.append(GroupType.DEFAULT_ARTIST.ordinal(), new a(r.ACTION_LOCAL_ARTIST_CLICK, s.PAGE_LOCAL_ARTIST, s.PAGE_LOCAL_ARTIST_DETAIL));
        CLICK_ACTION_MAP.append(GroupType.DEFAULT_ALBUM.ordinal(), new a(r.ACTION_LOCAL_ALBUM_CLICK, s.PAGE_LOCAL_ALBUM, s.PAGE_LOCAL_ALBUM_DETAIL));
        CLICK_ACTION_MAP.append(GroupType.DEFAULT_FOLDER.ordinal(), new a(r.ACTION_LOCAL_FOLDER_CLICK, s.PAGE_LOCAL_FOLDER, s.PAGE_LOCAL_FOLDER_DETAIL));
        CLICK_ACTION_MAP.append(GroupType.CUSTOM_LOCAL.ordinal(), new a(r.ACTION_NONE, s.PAGE_MY_SONGLIST, s.PAGE_NONE));
        CLICK_ACTION_MAP.append(GroupType.CUSTOM_ALL.ordinal(), new a(r.ACTION_NONE, s.PAGE_MY_SONGLIST, s.PAGE_NONE));
        ORDERID_ACTION_MAP.append(7, r.ACTION_MENU_ORDER_BY_TITLE);
        ORDERID_ACTION_MAP.append(10, r.ACTION_MENU_ORDER_BY_ADDED_TIME);
        ORDERID_ACTION_MAP.append(12, r.ACTION_MENU_ORDER_BY_AMOUNT);
        ORDERID_ACTION_MAP.append(9, r.ACTION_MENU_ORDER_BY_ALBUM);
        ORDERID_ACTION_MAP.append(11, r.ACTION_MENU_ORDER_BY_FILENAME);
        ORDERID_ACTION_MAP.append(8, r.ACTION_MENU_ORDER_BY_ARTIST);
    }

    public static r orderAction(int i) {
        return (r) ORDERID_ACTION_MAP.get(i);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_LIST, i.a(cls, "updateGroupList", GroupType.class, List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SCAN_FINISHED, i.a(cls, "onScanFinished", Integer.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_LIBRARY_CHANGED, i.a(cls, "updateMediaLibraryChanged", String.class));
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.stateview_fail_local_media, null);
    }

    protected void configListFooterView(View view) {
        TextView textView = (TextView) view;
        List groupItemList = getGroupItemList();
        textView.setText((groupItemList != null ? groupItemList.size() : 0) + " " + groupTypeToName());
    }

    private String groupTypeToName() {
        switch (this.mGroupType) {
            case DEFAULT_ARTIST:
                return getString(R.string.local_music_artist);
            case DEFAULT_GENRE:
                return getString(R.string.local_music_genre);
            case DEFAULT_FOLDER:
                return getString(R.string.folder);
            case DEFAULT_ALBUM:
                return getString(R.string.local_music_album);
            case CUSTOM_LOCAL:
            case CUSTOM_ALL:
                return getString(R.string.playlist);
            default:
                throw new IllegalArgumentException("invalid GroupType");
        }
    }

    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.listview_footer_local_media, null);
    }

    protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
        textView2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GroupListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.startActivity(new Intent(this.a.getActivity(), MediaScanAnimationActivity.class));
            }
        });
    }

    protected void configFailedView(View view) {
        configNoDataView((IconTextView) view.findViewById(R.id.no_media_icon), (TextView) view.findViewById(R.id.textview_load_failed), (TextView) view.findViewById(R.id.no_data_action_view));
    }

    protected boolean canLoadDataWhenResume() {
        return !isLocalGroup();
    }

    protected void onReloadData() {
        if (MediaStorage.MEDIA_ORDER_BY_AMOUNT.equals(b.a(this.mGroupType))) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST_BY_AMOUNT_ORDER, this.mGroupType));
            return;
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, this.mGroupType));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getGroupItemList() != null) {
            updateAZKeys(buildRawAZKeys(getGroupItemList()));
        }
    }

    protected boolean isLocalGroup() {
        return this.mGroupType.equals(GroupType.DEFAULT_ARTIST) || this.mGroupType.equals(GroupType.DEFAULT_ALBUM) || this.mGroupType.equals(GroupType.DEFAULT_GENRE) || this.mGroupType.equals(GroupType.DEFAULT_FOLDER);
    }

    protected boolean isAZSideBarEnable() {
        if (getGroupItemList() == null || getGroupItemList().size() <= 20 || !isLocalGroup()) {
            return false;
        }
        if (MediaStorage.MEDIA_ORDER_BY_AMOUNT.equals(b.a(this.mGroupType))) {
            return false;
        }
        return true;
    }

    protected void onGroupItemClicked(GroupItem groupItem) {
        if (isLocalGroup()) {
            a aVar = (a) CLICK_ACTION_MAP.get(this.mGroupType.ordinal());
            if (aVar != null) {
                t.a(aVar.a, aVar.c);
            }
        }
        com.sds.android.ttpod.framework.a.b.b.a("local_songlist_item_entry");
        launchSubMediaListFragment((String) TTTextUtils.validateString(getActivity(), groupItem.getName()), groupItem.getGroupID());
    }

    public void onScanFinished(Integer num) {
        onReloadData();
    }

    public void updateMediaLibraryChanged(String str) {
        if (getGroupItemList() != null) {
            if (MediaStorage.GROUP_ID_ALL_LOCAL.equals(str)) {
                onReloadData();
                return;
            }
            for (GroupItem groupID : getGroupItemList()) {
                if (str.equals(groupID.getGroupID())) {
                    onReloadData();
                    return;
                }
            }
        }
    }

    public void updateGroupList(GroupType groupType, List<GroupItem> list) {
        if (groupType == this.mGroupType) {
            String str = TAG;
            String str2 = "updateGroupList groupType=%s, size=%d";
            Object[] objArr = new Object[2];
            objArr[0] = groupType.name();
            objArr[1] = Integer.valueOf(list != null ? list.size() : -1);
            g.a(str, str2, objArr);
            updateGroupList(list);
            updateAZKeys(buildRawAZKeys(list));
        }
    }

    public void search() {
        com.sds.android.ttpod.framework.a.b.b.a("my_search_button");
        if (((a) CLICK_ACTION_MAP.get(this.mGroupType.ordinal())) != null) {
            t.a(r.ACTION_LOCAL_SEARCH, s.PAGE_NONE);
        }
        startActivityForResult(new Intent(getActivity(), MediaGroupSearchActivity.class).putExtra(KEY_GROUP_TYPE, this.mGroupType.name()).putExtra("origin", groupTypeToName()), 1);
    }

    public void onActivityResult(int i, int i2, final Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1 && intent != null) {
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ GroupListFragment b;

                public void run() {
                    if (intent != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, intent.getStringExtra("title"));
                        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, intent.getStringExtra(AbsMediaListFragment.KEY_GROUP_ID));
                        this.b.launchFragment((BaseFragment) Fragment.instantiate(this.b.getActivity(), SubMediaListFragment.class.getName(), bundle));
                    }
                }
            }, DELAY_LAUNCH);
        }
    }

    protected GroupType getGroupType() {
        return this.mGroupType;
    }

    private List<String> buildRawAZKeys(List<GroupItem> list) {
        List arrayList = new ArrayList(list.size());
        for (GroupItem groupItem : list) {
            if (this.mGroupType == GroupType.DEFAULT_FOLDER) {
                arrayList.add(e.j(groupItem.getName()));
            } else {
                arrayList.add(groupItem.getName());
            }
        }
        return arrayList;
    }

    private void launchSubMediaListFragment(String str, String str2) {
        Bundle bundle = new Bundle();
        if (this.mGroupType == GroupType.DEFAULT_FOLDER) {
            str = e.j(str);
        }
        bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, str);
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, str2);
        bundle.putBoolean(AbsMediaListFragment.KEY_FROM_GROUP, true);
        launchFragment((BaseFragment) Fragment.instantiate(getActivity(), SubMediaListFragment.selectSubMediaListFragmentClassName(str2), bundle));
    }

    public void onKeyPressed(int i, KeyEvent keyEvent) {
        super.onKeyPressed(i, keyEvent);
        if (getUserVisibleHint() && i == 84) {
            search();
        }
    }

    public void order() {
    }

    public void order(int i) {
        String str = "";
        if (i == 12) {
            str = MediaStorage.MEDIA_ORDER_BY_AMOUNT;
        }
        b.a(this.mGroupType, str);
        r orderAction = orderAction(i);
        if (orderAction != null) {
            t.a(orderAction, s.PAGE_NONE);
        }
        onReloadData();
    }
}
