package com.sds.android.ttpod.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.l;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.DraggableGroupListFragment;
import com.sds.android.ttpod.fragment.main.list.FavoriteSubMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.SubGroupListFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.a.b.d.k;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SubCustomGroupListFragment extends SubGroupListFragment {
    private static final String KTV_NAME = "我的KTV";
    private boolean mSynchronizing = false;

    private class a extends DraggableGroupListFragment {
        private List<GroupItem> mMusicCircleGroups;

        private a() {
            this.mMusicCircleGroups = new ArrayList();
        }

        protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
            super.onLoadCommandMap(map);
            Class cls = getClass();
            map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_POST_INFO_LIST_BY_ID, i.a(cls, "updateMusicCircleLists", PostResult.class, String.class));
            map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_ADD_FAVORITE_POSTS, i.a(cls, "onMusicCirclePostsChanged", BaseResult.class, String.class));
            map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_REMOVE_FAVORITE_POSTS, i.a(cls, "onMusicCirclePostsChanged", BaseResult.class, String.class));
            map.put(com.sds.android.ttpod.framework.modules.a.LOGIN_FINISHED, i.a(cls, "onLoginFinished", d.class, String.class));
            map.put(com.sds.android.ttpod.framework.modules.a.ADD_POSTS_TO_MEDIA_GROUP_FINISHED, i.a(cls, "addPostToMediaGroupFinished", new Class[0]));
            map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_LOCAL_AND_ONLINE_GROUP_LIST, i.a(cls, "updateGroupList", List.class));
        }

        protected void onGroupItemClicked(GroupItem groupItem) {
            String groupID = groupItem.getGroupID();
            if (groupID.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX)) {
                t.a(r.ACTION_SONG_LIST_ONLINE, s.PAGE_MY_SONGLIST_ONLINE_DETAIL);
                Bundle bundle = new Bundle();
                bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, groupItem.getName());
                bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, groupID);
                bundle.putBoolean(AbsMediaListFragment.KEY_FROM_GROUP, true);
                launchFragment((BaseFragment) Fragment.instantiate(getActivity(), FavoriteSubMediaListFragment.class.getName(), bundle));
                return;
            }
            t.a(r.ACTION_SONG_LIST_LOCAL, s.PAGE_MY_SONGLIST_LOCAL_DETAIL);
            super.onGroupItemClicked(groupItem);
        }

        protected void configNoDataView(IconTextView iconTextView, TextView textView, TextView textView2) {
            iconTextView.setText((int) R.string.icon_no_songlist);
            textView.setText(R.string.no_song_songlist);
            textView2.setVisibility(8);
        }

        protected void onGroupItemLongClicked(GroupItem groupItem) {
            if (!groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_KTV) && (groupItem instanceof GroupItem)) {
                showContextDialog(groupItem);
            }
        }

        public void onReloadData() {
            if (b.av()) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_FAVORITE_SONG_LIST_POSTS, new Object[0]));
            } else {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, new Object[0]));
            }
        }

        public void onMusicCirclePostsChanged(BaseResult baseResult, String str) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, GroupType.CUSTOM_ONLINE));
        }

        public void updateMusicCircleLists(PostResult postResult, String str) {
            if ("favorite".equals(str)) {
                updateFavoritePosts(postResult.getDataList());
            }
        }

        public void updateMediaLibraryChanged(String str) {
            if (str.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX)) {
                super.updateMediaLibraryChanged(str);
            }
        }

        public void updateGroupList(GroupType groupType, List<GroupItem> list) {
            if (groupType == GroupType.CUSTOM_LOCAL && getGroupItemList() != null && list != null) {
                Collection groupItemList = getGroupItemList();
                groupItemList.removeAll(list);
                list.addAll(groupItemList);
                super.updateGroupList(groupType, list);
            }
        }

        public void onLoginFinished(d dVar, String str) {
            onReloadData();
        }

        public void addPostToMediaGroupFinished() {
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, new Object[0]));
        }

        protected View getGroupItemView(GroupItem groupItem, View view, ViewGroup viewGroup, int i) {
            View groupItemView = super.getGroupItemView(groupItem, view, viewGroup, i);
            b bVar = (b) groupItemView.getTag();
            bVar.e().setVisibility(0);
            if (groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_KTV)) {
                bVar.d().setVisibility(8);
            } else {
                bVar.d().setVisibility(0);
            }
            return groupItemView;
        }

        protected void showContextDialog(final GroupItem groupItem) {
            if (getActivity() != null) {
                final String groupID = groupItem.getGroupID();
                final String str = groupItem.getName().toString();
                f.a(getActivity(), groupID.startsWith(MediaStorage.GROUP_ID_MUSICCIRCLE_PREFIX) ? new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(30, 0, (int) R.string.cancel_favorite)} : new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(1, 0, (int) R.string.menu_rename_songlist), new com.sds.android.ttpod.component.b.a(0, 0, (int) R.string.menu_delete_songlist)}, str, new com.sds.android.ttpod.component.b.a.b(this) {
                    final /* synthetic */ a d;

                    public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                        if (aVar.g() == 0) {
                            this.d.showSongListDeleteDialog(groupItem);
                        } else if (aVar.g() == 1) {
                            this.d.showSongListRenameDialog(groupItem);
                        } else if (aVar.g() == 30) {
                            new ArrayList().add(MediaStorage.getPostIdByGroupId(groupID));
                            this.d.removeGroupItem(groupItem);
                            k a = k.a(str, "", "");
                            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REMOVE_FAVORITE_POSTS, r0, a.a()));
                        }
                    }
                });
            }
        }

        private void updateFavoritePosts(List<Post> list) {
            String buildMusicCircleFavGroupIDPrefix = MediaStorage.buildMusicCircleFavGroupIDPrefix();
            List arrayList = new ArrayList();
            for (Post post : list) {
                if (!MediaStorage.isGroupExisted(BaseApplication.e(), buildMusicCircleFavGroupIDPrefix + post.getPostId())) {
                    arrayList.add(post);
                }
            }
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_POSTS_TO_MEDIA_GROUP, arrayList));
        }

        private void showSongListRenameDialog(final GroupItem groupItem) {
            if (getActivity() != null) {
                t.a(r.ACTION_MENU_RENAME_SONGLIST, s.PAGE_NONE);
                com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(getActivity(), new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, "", groupItem.getName(), getActivity().getString(R.string.rename_hint))}, R.string.save, new com.sds.android.ttpod.common.a.a.a<com.sds.android.ttpod.component.d.a.b>(this) {
                    final /* synthetic */ a b;

                    public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                        com.sds.android.ttpod.component.d.a.b.a c = bVar.c(1);
                        if (c == null) {
                            return;
                        }
                        if (l.a(c.e().toString())) {
                            bVar.e(false);
                            f.a((int) R.string.playlist_name_existed);
                            return;
                        }
                        bVar.e(true);
                        GroupItem groupItem = new GroupItem(c.e().toString(), groupItem.getGroupID(), groupItem.getCount());
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_ITEM, groupItem));
                    }
                }, null);
                bVar.setTitle((int) R.string.rename);
                bVar.show();
            }
        }

        private void showSongListDeleteDialog(final GroupItem groupItem) {
            Context activity = getActivity();
            if (activity != null) {
                t.a(r.ACTION_MENU_DELETE_SONGLIST, s.PAGE_NONE);
                h hVar = new h(activity, activity.getString(R.string.delete_message), new com.sds.android.ttpod.common.a.a.a<h>(this) {
                    final /* synthetic */ a b;

                    public void a(h hVar) {
                        this.b.removeGroupItem(groupItem);
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_GROUP, groupItem.getGroupID()));
                    }
                }, null);
                hVar.setTitle((int) R.string.menu_delete_songlist);
                hVar.show();
            }
        }
    }

    public DraggableGroupListFragment getFragmentInstance() {
        DraggableGroupListFragment aVar = new a();
        aVar.setArguments(getArguments());
        return aVar;
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        t.a(r.ACTION_OPEN_LOCAL_DROP_MENU, s.PAGE_NONE);
        com.sds.android.ttpod.framework.a.b.b.a("my_menu");
        Collection arrayList = new ArrayList();
        arrayList.add(new com.sds.android.ttpod.component.b.a(16, 0, (int) R.string.menu_sync_playlists));
        return arrayList;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        switch (i) {
            case 16:
                com.sds.android.ttpod.framework.a.b.b.a("my_menu_sync_data");
                t.a(r.ACTION_MENU_SONGLIST_SYNC, s.PAGE_NONE);
                if (!b.av()) {
                    com.sds.android.ttpod.b.f.a(true);
                } else if (c.e()) {
                    f.a((int) R.string.sync_favorite_playlists_start);
                    ((a) super.getFragmentInstance()).onReloadData();
                    this.mSynchronizing = true;
                } else {
                    f.a((int) R.string.network_unavailable);
                    return;
                }
                com.sds.android.ttpod.framework.a.b.l.al();
                return;
            default:
                return;
        }
    }
}
