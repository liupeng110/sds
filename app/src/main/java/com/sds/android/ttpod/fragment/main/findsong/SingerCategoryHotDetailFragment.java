package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.b;
import com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragment;
import com.sds.android.ttpod.framework.a.b.o;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

public class SingerCategoryHotDetailFragment extends ListLoadingFragment<SingerData> {
    public static final String KEY_CHANNEL = "key_channel";
    public static final String KEY_DATA = "key_data";
    private int mId;
    private String mTitle;

    private class a extends com.sds.android.ttpod.adapter.a<SingerData> {
        final /* synthetic */ SingerCategoryHotDetailFragment a;
        private OnClickListener e;

        private a(SingerCategoryHotDetailFragment singerCategoryHotDetailFragment) {
            this.a = singerCategoryHotDetailFragment;
            this.e = new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a.performSingerClick((SingerData) view.getTag(R.id.view_bind_data));
                }
            };
        }

        public int getCount() {
            if (this.d == null) {
                return 0;
            }
            int size = this.d.size();
            return size % 4 == 0 ? size / 4 : (size / 4) + 1;
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.c.inflate(R.layout.singer_hot_list_item, viewGroup, false);
            b bVar = new b(inflate.findViewById(R.id.song_item1));
            b bVar2 = new b(inflate.findViewById(R.id.song_item2));
            b bVar3 = new b(inflate.findViewById(R.id.song_item3));
            b bVar4 = new b(inflate.findViewById(R.id.song_item4));
            inflate.setTag(new b[]{bVar, bVar2, bVar3, bVar4});
            return inflate;
        }

        protected void a(View view, SingerData singerData, int i) {
            if (this.d != null) {
                b[] bVarArr = (b[]) view.getTag();
                int i2 = i * 4;
                a(bVarArr[0], i2);
                a(bVarArr[1], i2 + 1);
                a(bVarArr[2], i2 + 2);
                a(bVarArr[3], i2 + 3);
            }
        }

        private void a(b bVar, int i) {
            if (bVar == null || i >= this.d.size()) {
                a(bVar, false);
                return;
            }
            SingerData singerData = (SingerData) this.d.get(i);
            a(bVar, true);
            bVar.d().setVisibility(4);
            bVar.b().setText(singerData.getName());
            bVar.a().setTag(R.id.view_bind_data, singerData);
            bVar.a().setOnClickListener(this.e);
            c.a(bVar.b(), ThemeElement.COMMON_CONTENT_TEXT);
            ImageView c = bVar.c();
            if (!m.a(c, (int) R.drawable.img_background_song_publish)) {
                int d = com.sds.android.ttpod.common.c.a.d() / 4;
                g.a(c, com.sds.android.ttpod.b.b.a((long) singerData.getId()), d, d, R.drawable.img_background_song_publish, 0);
            }
        }

        private void a(b bVar, boolean z) {
            if (bVar != null) {
                int i = z ? 0 : 4;
                bVar.d().setVisibility(i);
                bVar.b().setVisibility(i);
                bVar.a().setVisibility(i);
                bVar.c().setVisibility(i);
            }
        }
    }

    public SingerCategoryHotDetailFragment(String str, int i) {
        super(com.sds.android.ttpod.framework.modules.a.GET_SINGER_CATEGORY_DETAIL, com.sds.android.ttpod.framework.modules.a.UPDATE_SINGER_CATEGORY_DETAIL, null);
        setAdapter(new a());
        this.mId = i;
        this.mTitle = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(this.mTitle);
        setStatisticPageProperties(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mId));
        setTBSPage("tt_singer_list_" + this.mTitle);
    }

    protected void setupListFooter() {
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mListView.setDivider(null);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    protected String onLoadTitleText() {
        return this.mTitle;
    }

    private void performSingerClick(SingerData singerData) {
        if (singerData != null) {
            o.a("singer-rank-detail_" + this.mTitle + "_" + o.a());
            p.a("library-music-singer_" + this.mTitle + "_" + o.a());
            SingerDetailFragment.launch((BaseActivity) getActivity(), singerData.getName(), singerData.getId());
            new SUserEvent("PAGE_CLICK", r.ACTION_SINGER_NAME.getValue(), this.mTitle, String.valueOf(s.PAGE_SINGER_MESSAGE.getValue())).append("singer_id", Integer.valueOf(singerData.getId())).post();
            new com.sds.android.ttpod.framework.a.b.b().a("singer_id", String.valueOf(singerData.getId())).a("singer_name", singerData.getName()).a();
        }
    }

    protected void requestDataList(int i) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(this.mRequestId, Integer.valueOf(this.mId), Integer.valueOf(i)));
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return false;
    }
}
