package com.sds.android.ttpod.activities.mediascan.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MediaScanExcludeActivity extends SlidingClosableActivity {
    private a mAdapter;
    private List<String> mAllFolderList = new ArrayList();
    private Set<String> mExcludeFolderSet = new HashSet();

    private final class a extends BaseAdapter {
        final /* synthetic */ MediaScanExcludeActivity a;

        private a(MediaScanExcludeActivity mediaScanExcludeActivity) {
            this.a = mediaScanExcludeActivity;
        }

        public int getCount() {
            return this.a.mAllFolderList.size();
        }

        public Object getItem(int i) {
            return this.a.mAllFolderList.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            b bVar;
            if (view == null) {
                view = LayoutInflater.from(this.a).inflate(R.layout.mediascan_exclude_item, viewGroup, false);
                b bVar2 = new b((TextView) view.findViewById(R.id.textview_mediascan_exclude_item_title), (TextView) view.findViewById(R.id.textview_mediascan_exclude_item_sub_title), (CheckBox) view.findViewById(R.id.checkbox_mediascan_exclude_item));
                view.setTag(bVar2);
                bVar = bVar2;
            } else {
                bVar = (b) view.getTag();
            }
            String str = (String) this.a.mAllFolderList.get(i);
            bVar.a.setText(e.j(str));
            bVar.b.setText(e.l(str));
            bVar.c.setOnCheckedChangeListener(null);
            bVar.c.setChecked(this.a.mExcludeFolderSet.contains(str));
            bVar.c.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
                final /* synthetic */ a b;

                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        this.b.a.mExcludeFolderSet.add(this.b.a.mAllFolderList.get(i));
                    } else {
                        this.b.a.mExcludeFolderSet.remove(this.b.a.mAllFolderList.get(i));
                    }
                }
            });
            return view;
        }
    }

    private static final class b {
        private TextView a;
        private TextView b;
        private CheckBox c;

        private b(TextView textView, TextView textView2, CheckBox checkBox) {
            this.a = textView;
            this.b = textView2;
            this.c = checkBox;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_mediascan_exclude);
        setTitle((int) R.string.mediascan_setting_choose_exclude_folders);
        this.mExcludeFolderSet = com.sds.android.ttpod.framework.storage.environment.b.g() != null ? com.sds.android.ttpod.framework.storage.environment.b.g() : this.mExcludeFolderSet;
        for (String str : this.mExcludeFolderSet) {
            if (e.a(str)) {
                this.mAllFolderList.add(str);
            }
        }
        this.mAdapter = new a();
        ListView listView = (ListView) findViewById(R.id.listview_mediascan_exclude);
        listView.setAdapter(this.mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ MediaScanExcludeActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((b) view.getTag()).c.toggle();
            }
        });
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, GroupType.DEFAULT_FOLDER));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_LIST, i.a(getClass(), "updateGroupList", GroupType.class, List.class));
    }

    protected void onPause() {
        super.onPause();
        com.sds.android.ttpod.framework.storage.environment.b.a(new HashSet(this.mExcludeFolderSet));
    }

    public void updateGroupList(GroupType groupType, List<GroupItem> list) {
        if (groupType.equals(GroupType.DEFAULT_FOLDER)) {
            for (GroupItem name : list) {
                String name2 = name.getName();
                if (!this.mExcludeFolderSet.contains(name2)) {
                    this.mAllFolderList.add(name2);
                }
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
