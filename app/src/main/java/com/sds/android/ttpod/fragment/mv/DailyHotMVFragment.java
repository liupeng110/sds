package com.sds.android.ttpod.fragment.mv;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.DailyHotMVData;
import com.sds.android.cloudapi.ttpod.data.MvTag;
import com.sds.android.cloudapi.ttpod.result.DailyHotMVDataResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.f;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.c.c;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DailyHotMVFragment extends BaseFragment implements c {
    private static final int BLACK_THEME_DEFAULT_TEXT_COLOR = -6381922;
    private static final int[] DEFAULT_MV_TAG_ICON_RES_IDS = new int[]{R.drawable.img_daily_hot_mv_tag1, R.drawable.img_daily_hot_mv_tag2, R.drawable.img_daily_hot_mv_tag3};
    private static final int MAX_TAG_SIZE = 3;
    private static final String MV_TAG_COLOR1 = "rgb(247,205,136)";
    private static final String MV_TAG_COLOR2 = "rgb(198,174,234)";
    private static final String MV_TAG_COLOR3 = "rgb(162,218,248)";
    private static final String MV_TAG_COLOR4 = "rgb(124,215,171)";
    private static final String MV_TAG_COLOR5 = "rgb(211,234,174)";
    private static final String MV_TAG_COLOR6 = "rgb(248,162,201)";
    private static final String MV_TAG_COLOR7 = "rgb(226,186,191)";
    private static final String MV_TAG_COLOR8 = "rgb(216,189,144)";
    private static final String MV_TAG_COLOR9 = "rgb(166,195,134)";
    private static final HashMap<String, Integer> MV_TAG_ICON_RES_IDS = new HashMap<String, Integer>() {
        {
            put(DailyHotMVFragment.MV_TAG_COLOR1, Integer.valueOf(R.drawable.img_daily_hot_mv_tag1));
            put(DailyHotMVFragment.MV_TAG_COLOR2, Integer.valueOf(R.drawable.img_daily_hot_mv_tag2));
            put(DailyHotMVFragment.MV_TAG_COLOR3, Integer.valueOf(R.drawable.img_daily_hot_mv_tag3));
            put(DailyHotMVFragment.MV_TAG_COLOR4, Integer.valueOf(R.drawable.img_daily_hot_mv_tag4));
            put(DailyHotMVFragment.MV_TAG_COLOR5, Integer.valueOf(R.drawable.img_daily_hot_mv_tag5));
            put(DailyHotMVFragment.MV_TAG_COLOR6, Integer.valueOf(R.drawable.img_daily_hot_mv_tag6));
            put(DailyHotMVFragment.MV_TAG_COLOR7, Integer.valueOf(R.drawable.img_daily_hot_mv_tag7));
            put(DailyHotMVFragment.MV_TAG_COLOR8, Integer.valueOf(R.drawable.img_daily_hot_mv_tag8));
            put(DailyHotMVFragment.MV_TAG_COLOR9, Integer.valueOf(R.drawable.img_daily_hot_mv_tag9));
        }
    };
    private static final int PAGE_SIZE = 6;
    private String mCurrentTheme = "";
    private a mDailyHotMVAdapter;
    private int mFirstItemDayColor = R.color.mv_daily_hot_first_day_text_color;
    private com.sds.android.ttpod.widget.c mPager;
    private View mRootView;
    private int mThemeItemBackgroundColor;
    private int mThemeItemSeparatorColor;
    private int mThemeItemSubTextColor;
    private int mThemeItemTextColor;

    class a extends BaseAdapter {
        final /* synthetic */ DailyHotMVFragment a;
        private Context b;
        private List<DailyHotMVData> c = new ArrayList();

        public a(DailyHotMVFragment dailyHotMVFragment, Context context) {
            this.a = dailyHotMVFragment;
            this.b = context;
        }

        public void a(List<DailyHotMVData> list) {
            this.c = list;
        }

        public void b(List<DailyHotMVData> list) {
            this.c.addAll(list);
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.fragment_daily_hot_mv_item, viewGroup, false);
                view.setTag(new b(this.a, view));
            }
            a((b) view.getTag(), i);
            b((b) view.getTag(), i);
            return view;
        }

        private void a(b bVar, int i) {
            DailyHotMVData dailyHotMVData = (DailyHotMVData) this.c.get(i);
            bVar.e.setText(dailyHotMVData.getDesc());
            bVar.f.setText(f.a(dailyHotMVData.getDanmakuCount()));
            a(bVar, dailyHotMVData);
            a(bVar.g, dailyHotMVData);
            a(bVar.b, dailyHotMVData);
            a(bVar.k, dailyHotMVData);
        }

        private void a(List<TextView> list, DailyHotMVData dailyHotMVData) {
            for (TextView visibility : list) {
                TextView visibility2;
                visibility2.setVisibility(8);
            }
            if (!j.a(dailyHotMVData.getTags())) {
                int min = Math.min(dailyHotMVData.getTags().size(), 3);
                for (int i = 0; i < min; i++) {
                    visibility2 = (TextView) list.get(i);
                    MvTag mvTag = (MvTag) dailyHotMVData.getTags().get(i);
                    visibility2.setText(mvTag.getTagName());
                    visibility2.setBackgroundResource(a(mvTag.getTagColor(), i));
                    visibility2.setVisibility(0);
                }
            }
        }

        private int a(String str, int i) {
            Integer num = (Integer) DailyHotMVFragment.MV_TAG_ICON_RES_IDS.get(str);
            if (num != null) {
                return num.intValue();
            }
            return DailyHotMVFragment.DEFAULT_MV_TAG_ICON_RES_IDS[i];
        }

        private void a(b bVar, DailyHotMVData dailyHotMVData) {
            if (a(dailyHotMVData.getTitle())) {
                String[] split = dailyHotMVData.getTitle().replace(" ", "").split("/");
                bVar.d.setText(String.format("%02d", new Object[]{Integer.valueOf(Integer.parseInt(split[0]))}));
                bVar.c.setText(String.format("%02d", new Object[]{Integer.valueOf(Integer.parseInt(split[1]))}));
                return;
            }
            a(bVar);
        }

        private boolean a(String str) {
            return !m.a(str) && Pattern.compile("^\\d{1,2}/\\d{1,2}$").matcher(str.replace(" ", "")).matches();
        }

        private void a(b bVar) {
            Calendar instance = Calendar.getInstance();
            bVar.d.setText(String.format("%02d", new Object[]{Integer.valueOf(instance.get(5))}));
            bVar.c.setText(String.format("%02d", new Object[]{Integer.valueOf(instance.get(2) + 1)}));
        }

        private void a(ImageView imageView, DailyHotMVData dailyHotMVData) {
            if (m.a(dailyHotMVData.getBigPicUrl())) {
                imageView.setImageResource(R.drawable.img_daily_hot_mv_default_screenshot);
            } else {
                g.a(imageView, dailyHotMVData.getBigPicUrl(), 0, 0, (int) R.drawable.img_daily_hot_mv_default_screenshot);
            }
        }

        private void a(View view, final DailyHotMVData dailyHotMVData) {
            if (!j.a(dailyHotMVData.getMvList())) {
                view.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        d.j.a("mv_origin", "mv_daily");
                        new com.sds.android.ttpod.framework.a.b.b().b("daily_mv_play").a("mv_id", String.valueOf(dailyHotMVData.getId())).a("mv_name", dailyHotMVData.getName()).a();
                        VideoPlayManager.a(this.b.a.getActivity(), dailyHotMVData);
                    }
                });
            }
        }

        private void b(b bVar, int i) {
            bVar.b.setBackgroundColor(this.a.mThemeItemBackgroundColor);
            if (this.a.mCurrentTheme.contains("3_yejian6")) {
                if (i == 0) {
                    bVar.d.setTextColor(this.a.getResources().getColor(this.a.mFirstItemDayColor));
                    bVar.e.setTextColor(this.a.getResources().getColor(this.a.mFirstItemDayColor));
                } else {
                    bVar.d.setTextColor(DailyHotMVFragment.BLACK_THEME_DEFAULT_TEXT_COLOR);
                    bVar.e.setTextColor(DailyHotMVFragment.BLACK_THEME_DEFAULT_TEXT_COLOR);
                }
                bVar.c.setTextColor(DailyHotMVFragment.BLACK_THEME_DEFAULT_TEXT_COLOR);
                bVar.i.setBackgroundColor(DailyHotMVFragment.BLACK_THEME_DEFAULT_TEXT_COLOR);
                bVar.j.setBackgroundColor(DailyHotMVFragment.BLACK_THEME_DEFAULT_TEXT_COLOR);
            } else if (this.a.mCurrentTheme.contains("2_menghuan5") || this.a.mCurrentTheme.contains("1_baise5")) {
                if (i == 0) {
                    bVar.d.setTextColor(this.a.getResources().getColor(this.a.mFirstItemDayColor));
                    bVar.e.setTextColor(this.a.mThemeItemTextColor);
                } else {
                    bVar.d.setTextColor(this.a.mThemeItemSeparatorColor);
                    bVar.e.setTextColor(this.a.mThemeItemSubTextColor);
                }
                bVar.c.setTextColor(this.a.mThemeItemSeparatorColor);
                bVar.i.setBackgroundColor(this.a.mThemeItemSeparatorColor);
                bVar.j.setBackgroundColor(this.a.mThemeItemSeparatorColor);
            } else {
                if (i == 0) {
                    bVar.d.setTextColor(this.a.getResources().getColor(this.a.mFirstItemDayColor));
                    bVar.e.setTextColor(this.a.mThemeItemTextColor);
                } else {
                    bVar.d.setTextColor(this.a.mThemeItemSubTextColor);
                    bVar.e.setTextColor(this.a.mThemeItemSubTextColor);
                }
                bVar.c.setTextColor(this.a.mThemeItemSubTextColor);
                bVar.i.setBackgroundColor(this.a.mThemeItemSubTextColor);
                bVar.j.setBackgroundColor(this.a.mThemeItemSubTextColor);
            }
            bVar.h.setTextColor(this.a.mThemeItemSubTextColor);
            bVar.f.setTextColor(this.a.mThemeItemSubTextColor);
        }
    }

    class b {
        final /* synthetic */ DailyHotMVFragment a;
        private View b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;
        private ImageView g;
        private IconTextView h;
        private View i;
        private View j;
        private List<TextView> k = new ArrayList();

        public b(DailyHotMVFragment dailyHotMVFragment, View view) {
            this.a = dailyHotMVFragment;
            this.b = view;
            this.c = (TextView) this.b.findViewById(R.id.mv_date_month);
            this.d = (TextView) this.b.findViewById(R.id.mv_date_day);
            this.e = (TextView) this.b.findViewById(R.id.mv_desc);
            this.h = (IconTextView) this.b.findViewById(R.id.mv_danmaku_icon);
            this.f = (TextView) this.b.findViewById(R.id.mv_danmaku_count);
            this.g = (ImageView) this.b.findViewById(R.id.mv_screenshot);
            this.i = this.b.findViewById(R.id.divider_line);
            this.j = this.b.findViewById(R.id.item_divider_line);
            this.k.add((TextView) this.b.findViewById(R.id.mv_tag1));
            this.k.add((TextView) this.b.findViewById(R.id.mv_tag2));
            this.k.add((TextView) this.b.findViewById(R.id.mv_tag3));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_daily_hot_mv, viewGroup, false);
        this.mDailyHotMVAdapter = new a(this, getActivity());
        this.mPager = new com.sds.android.ttpod.widget.c((ListView) this.mRootView.findViewById(R.id.daily_hot_mv_list_view), (NetworkLoadView) this.mRootView.findViewById(R.id.daily_hot_mv_load_view), this);
        this.mPager.e(R.string.count_of_mv);
        this.mPager.a(this.mDailyHotMVAdapter);
        return this.mRootView;
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        this.mPager.j();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mPager.l();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        this.mFirstItemDayColor = getFirstItemDayColorByThemePath();
        this.mThemeItemBackgroundColor = com.sds.android.ttpod.framework.modules.theme.c.d(ThemeElement.TILE_BACKGROUND);
        this.mThemeItemSeparatorColor = com.sds.android.ttpod.framework.modules.theme.c.d(ThemeElement.COMMON_SEPARATOR);
        this.mThemeItemTextColor = com.sds.android.ttpod.framework.modules.theme.c.d(ThemeElement.SONG_LIST_ITEM_TEXT);
        this.mThemeItemSubTextColor = com.sds.android.ttpod.framework.modules.theme.c.d(ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        this.mDailyHotMVAdapter.notifyDataSetChanged();
    }

    private int getFirstItemDayColorByThemePath() {
        String b = v.b();
        if (b != null) {
            this.mCurrentTheme = b;
            if (b.contains("2_menghuan5")) {
                return R.color.rank_category_number_blue_theme;
            }
            if (b.contains("3_yejian6")) {
                return R.color.rank_category_number_black_theme;
            }
        }
        return R.color.mv_daily_hot_first_day_text_color;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_DAILY_HOT_MV_LIST, i.a(getClass(), "updateDailyHotMVList", DailyHotMVDataResult.class));
    }

    public void updateDailyHotMVList(DailyHotMVDataResult dailyHotMVDataResult) {
        if (isViewAccessAble()) {
            this.mPager.a(dailyHotMVDataResult.isSuccess(), j.a(dailyHotMVDataResult.getDailyHotMVList()));
            this.mPager.a(getString(R.string.not_mv));
            if (dailyHotMVDataResult.isSuccess() && !j.a(dailyHotMVDataResult.getDailyHotMVList())) {
                this.mPager.b(dailyHotMVDataResult.getPageCount());
                if (this.mPager.i()) {
                    this.mDailyHotMVAdapter.a(dailyHotMVDataResult.getDailyHotMVList());
                } else {
                    this.mDailyHotMVAdapter.b(dailyHotMVDataResult.getDailyHotMVList());
                }
                this.mDailyHotMVAdapter.notifyDataSetChanged();
            }
        }
    }

    public void requestPageData(int i) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_DAILY_HOT_MV_LIST, Integer.valueOf(this.mPager.a()), Integer.valueOf(6)));
    }
}
