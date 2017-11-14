package com.sds.android.ttpod.activities.local;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.GroupListFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import com.sds.android.ttpod.media.mediastore.PinyinUtils.Pinyin;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class MediaGroupSearchActivity extends BaseSearchActivity {
    private static final String TAG = "MediaGroupSearchActivity";
    private a mAdapter;
    private GroupType mGroupType;

    class a extends com.sds.android.ttpod.adapter.c.a {
        final /* synthetic */ MediaGroupSearchActivity a;

        public class a implements com.sds.android.ttpod.adapter.c.a.a {
            final /* synthetic */ a a;
            private GroupItem b;
            private Pinyin c;
            private Pinyin d;

            public GroupItem a() {
                return this.b;
            }

            public CharSequence b() {
                if (TextUtils.isEmpty(a.d) || this.d == null) {
                    return this.b.getName();
                }
                int[] iArr = new int[]{0, 0, 0};
                if (!(this.a.a(this.c, iArr) || this.a.a(this.d, iArr))) {
                    this.a.a(this.b.getName(), iArr);
                }
                if (iArr[2] <= 0) {
                    return this.b.getName();
                }
                CharSequence spannableString = new SpannableString(this.b.getName());
                spannableString.setSpan(a.e, iArr[1], iArr[2] + iArr[1], 33);
                return spannableString;
            }

            public a(a aVar, GroupItem groupItem) {
                this.a = aVar;
                this.b = groupItem;
                Pinyin[] twoKindOfPinyin = PinyinUtils.getTwoKindOfPinyin(this.b.getName());
                if (twoKindOfPinyin != null) {
                    this.c = twoKindOfPinyin[0];
                    this.d = twoKindOfPinyin[1];
                }
            }

            public int[] c() {
                int[] iArr = new int[]{0, 0, 0};
                boolean c = this.a.a(this.c, iArr);
                if (!c) {
                    c = this.a.a(this.d, iArr);
                    if (!c) {
                        c = this.a.a(this.b.getName(), iArr);
                    }
                }
                return c ? iArr : null;
            }
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(MediaGroupSearchActivity mediaGroupSearchActivity, Activity activity) {
            this.a = mediaGroupSearchActivity;
            super(activity);
        }

        public void a(List<GroupItem> list) {
            if (list == null) {
                throw new IllegalArgumentException("datas must not be null");
            }
            for (GroupItem aVar : list) {
                this.f.add(new a(this, aVar));
            }
            c = "";
            d = "";
            this.h = this.f;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.h.size();
        }

        public a a(int i) {
            return (a) this.h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(this.b, R.layout.media_group_item, null);
                view.setTag(new com.sds.android.ttpod.adapter.c.b.a(view));
            }
            a((com.sds.android.ttpod.adapter.c.b.a) view.getTag(), a(i));
            return view;
        }

        private void a(com.sds.android.ttpod.adapter.c.b.a aVar, a aVar2) {
            aVar.e().setVisibility(8);
            aVar.c().setText(aVar2.b());
            aVar.d().setText(this.b.getString(R.string.count_of_media, new Object[]{aVar2.a().getCount()}));
            aVar.a(v.b());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_local_search");
        trackModule("local_search");
        onNewIntent(getIntent());
        if (GroupType.CUSTOM_LOCAL == this.mGroupType) {
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_LOCAL_AND_ONLINE_GROUP_LIST, new Object[0]));
            return;
        }
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_GROUP_ITEM_LIST, this.mGroupType));
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            Object stringExtra = intent.getStringExtra(GroupListFragment.KEY_GROUP_TYPE);
            if (TextUtils.isEmpty(stringExtra)) {
                throw new IllegalArgumentException("group type is invalid");
            }
            this.mGroupType = GroupType.valueOf(stringExtra);
        }
    }

    protected com.sds.android.ttpod.adapter.c.a onCreateAdapter() {
        this.mAdapter = new a(this, this);
        return this.mAdapter;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_GROUP_LIST, i.a(getClass(), "updateGroupList", GroupType.class, List.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_LOCAL_AND_ONLINE_GROUP_LIST, i.a(getClass(), "updateGroupList", List.class));
    }

    public void updateGroupList(GroupType groupType, List<GroupItem> list) {
        String str = TAG;
        String str2 = "updateGroupList groupType=%s, size=%d";
        Object[] objArr = new Object[2];
        objArr[0] = groupType.name();
        objArr[1] = Integer.valueOf(list != null ? list.size() : -1);
        g.a(str, str2, objArr);
        if (this.mGroupType.equals(groupType)) {
            this.mAdapter.a((List) list);
            onLoadDataFinished();
        }
    }

    public void updateGroupList(List<GroupItem> list) {
        updateGroupList(this.mGroupType, list);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
        GroupItem a = this.mAdapter.a(i).a();
        setResult(-1, new Intent().putExtra(AbsMediaListFragment.KEY_GROUP_ID, a.getGroupID()).putExtra("title", a.getName()));
        finish();
    }
}
