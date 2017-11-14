package com.sds.android.ttpod.adapter.c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;

/* MediaGroupListAdapter */
public class b extends com.sds.android.ttpod.adapter.a<GroupItem> {

    /* MediaGroupListAdapter */
    public static class a extends com.sds.android.ttpod.framework.modules.theme.c.a {
        private TextView a;
        private TextView b;
        private IconTextView c;
        private View d;
        private View e;

        public a(View view) {
            this.e = view;
            this.a = (TextView) view.findViewById(R.id.title_view);
            this.b = (TextView) view.findViewById(R.id.subtitle_view);
            this.c = (IconTextView) view.findViewById(R.id.menu_view);
            this.d = view.findViewById(R.id.view_playstate_group);
        }

        public void a() {
            c.a(this.a, ThemeElement.SONG_LIST_ITEM_TEXT);
            c.a(this.b, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            c.a(this.e, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        }

        public View b() {
            return this.d;
        }

        public TextView c() {
            return this.a;
        }

        public TextView d() {
            return this.b;
        }

        public IconTextView e() {
            return this.c;
        }
    }

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.media_group_item, null);
        inflate.setTag(new a(inflate));
        return inflate;
    }

    protected final void a(View view, GroupItem groupItem, int i) {
        a((a) view.getTag(), groupItem);
        boolean z = c() == null || !m.a(groupItem.getGroupID(), ((GroupItem) c()).getGroupID());
        view.setEnabled(z);
    }

    protected void a(a aVar, GroupItem groupItem) {
        int i;
        boolean startsWith = groupItem.getGroupID().startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX);
        aVar.e().setVisibility(8);
        aVar.c().setText(startsWith ? e.k(groupItem.getName()) : groupItem.getName());
        aVar.d().setText(a().getString(R.string.count_of_media, new Object[]{groupItem.getCount()}) + (startsWith ? "  " + groupItem.getName() : ""));
        View b = aVar.b();
        if (groupItem.equals(c())) {
            i = 0;
        } else {
            i = 8;
        }
        b.setVisibility(i);
    }
}
