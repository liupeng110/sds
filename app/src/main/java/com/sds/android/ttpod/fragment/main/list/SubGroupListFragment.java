package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.c;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import java.util.ArrayList;
import java.util.Collection;

public class SubGroupListFragment extends SlidingClosableFragment {
    private GroupListFragment mGroupListFragment;
    private View mRootView;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_group_list, null);
        return this.mRootView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (this.mGroupListFragment == null) {
            this.mGroupListFragment = getFragmentInstance();
        }
        if (!isDetached()) {
            getChildFragmentManager().beginTransaction().replace(R.id.content_custom_group, this.mGroupListFragment).commitAllowingStateLoss();
        }
        view.findViewById(R.id.tv_create_playlist).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SubGroupListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                b.a("my_songlist_create_songlist");
                t.a(r.ACTION_MY_SONGLIST_CREATE, s.PAGE_NONE);
                l.U();
                f.b(this.a.getActivity(), c.a(0), new a<com.sds.android.ttpod.component.d.a.b>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_GROUP, bVar.c(0).e().toString()), String.class);
                    }
                });
            }
        });
        if (arguments != null) {
            getActionBarController().a(arguments.getString("key_list_title"));
            if (!arguments.getBoolean("key_list_creatable")) {
                this.mRootView.findViewById(R.id.layout_create_playlist).setVisibility(8);
            }
        }
    }

    public GroupListFragment getFragmentInstance() {
        if (this.mGroupListFragment == null) {
            Bundle arguments = getArguments();
            if (arguments == null || arguments.getBoolean("key_list_draggable")) {
                this.mGroupListFragment = (DraggableGroupListFragment) Fragment.instantiate(getActivity(), DraggableGroupListFragment.class.getName(), arguments);
            } else {
                this.mGroupListFragment = (GroupListFragment) Fragment.instantiate(getActivity(), GroupListFragment.class.getName(), arguments);
            }
        }
        return this.mGroupListFragment;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView.findViewById(R.id.content_custom_group), ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView.findViewById(R.id.layout_create_playlist), ThemeElement.SONG_LIST_ITEM_BACKGROUND);
    }

    protected boolean needMenuAction() {
        return true;
    }

    protected Collection<com.sds.android.ttpod.component.b.a> onCreateDropDownMenu() {
        b.a("my_menu");
        t.a(r.ACTION_OPEN_LOCAL_DROP_MENU, s.PAGE_NONE);
        Collection arrayList = new ArrayList();
        arrayList.add(new com.sds.android.ttpod.component.b.a(4, 0, (int) R.string.menu_scan_media));
        return arrayList;
    }

    public void onDropDownMenuClicked(int i, com.sds.android.ttpod.component.b.a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        switch (i) {
            case 4:
                b.a("my_menu_scan");
                t.a(r.ACTION_MENU_SCAN_MUSIC, s.PAGE_SCAN_MUSIC);
                com.sds.android.ttpod.b.f.f(getActivity());
                return;
            default:
                return;
        }
    }

    protected void onSearchActionClicked() {
        if (this.mGroupListFragment != null) {
            this.mGroupListFragment.search();
            l.af();
        }
    }
}
