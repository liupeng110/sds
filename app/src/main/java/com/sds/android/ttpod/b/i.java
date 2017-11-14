package com.sds.android.ttpod.b;

import android.util.SparseArray;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.result.CirclePosterListResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHandpickResult;
import com.sds.android.cloudapi.ttpod.result.FindSongHotListResultNew;
import com.sds.android.cloudapi.ttpod.result.OperationZoneResult;
import com.sds.android.cloudapi.ttpod.result.StyleDataListResult;
import com.sds.android.ttpod.R;
import java.util.List;

/* FindSongConfig */
public class i {

    /* FindSongConfig */
    public static class a {
        public static final SparseArray<String> a = new SparseArray();

        public static int a(int i) {
            return (int) (((float) b(i)) / d(i));
        }

        public static int b(int i) {
            return ((com.sds.android.ttpod.common.c.a.d() - ((i - 1) * com.sds.android.ttpod.common.c.a.a(8))) - (com.sds.android.ttpod.common.c.a.a(8) * 2)) / i;
        }

        public static int a(int i, int i2, String str, List<RecommendData> list) {
            if (list == null) {
                return 0;
            }
            int i3;
            int b = b(i2);
            if (i2 == 1) {
                i3 = b / 2;
            } else {
                i3 = b;
            }
            int ceil = (int) Math.ceil(((double) i) / ((double) i2));
            if (str.equals("song_list")) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < list.size()) {
                    int i6 = i4;
                    while (i6 < i4 + i2 && i6 < list.size()) {
                        RecommendData recommendData = (RecommendData) list.get(i6);
                        if (recommendData != null && recommendData.getName() != null && ((RecommendData) list.get(i6)).getName().length() > a(i2)) {
                            i5++;
                            break;
                        }
                        i6++;
                    }
                    i4 += i2;
                }
                b = ((com.sds.android.ttpod.common.c.a.a(48) * i5) + ((ceil - i5) * com.sds.android.ttpod.common.c.a.a(30))) + ((i3 * ceil) + (com.sds.android.ttpod.common.c.a.a(0) * (ceil - 1)));
            } else {
                b = ((i3 * ceil) + (com.sds.android.ttpod.common.c.a.a(0) * (ceil - 1))) + com.sds.android.ttpod.common.c.a.a(8);
            }
            return b;
        }

        public static int c(int i) {
            if (i == 3) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 5) {
                return 3;
            }
            if (i == 6) {
                return 4;
            }
            return 0;
        }

        public static float d(int i) {
            return ((float) com.sds.android.ttpod.common.c.a.a(15)) - (((float) i) * 1.5f);
        }

        static {
            a.put(2, "星期一");
            a.put(3, "星期二");
            a.put(4, "星期三");
            a.put(5, "星期四");
            a.put(6, "星期五");
            a.put(7, "星期六");
            a.put(1, "星期日");
        }
    }

    /* FindSongConfig */
    public static class b {
        public static int a(int i) {
            return (((com.sds.android.ttpod.common.c.a.a(75) + 1) + 8) * i) + 1;
        }
    }

    /* FindSongConfig */
    public static class c {
        public static int a(int i) {
            switch (i) {
                case 0:
                    return R.drawable.img_tag_exclusive_plan;
                case 1:
                    return R.drawable.img_tag_new_song_publish;
                case 2:
                    return R.drawable.img_tag_exclusive_publish;
                case 3:
                    return R.drawable.img_tag_satellite_program;
                case 4:
                    return R.drawable.img_tag_hot_activity;
                case 5:
                    return R.drawable.img_tag_song_list_recommand;
                case 6:
                    return R.drawable.img_tag_voice_program;
                case 7:
                    return R.drawable.img_tag_mv;
                default:
                    return R.drawable.transparent_drawable;
            }
        }
    }

    /* FindSongConfig */
    public static class d {
        public static String a(StyleDataListResult styleDataListResult) {
            if (styleDataListResult instanceof CirclePosterListResult) {
                return "circle_poster";
            }
            if (styleDataListResult instanceof FindSongHandpickResult) {
                return "hand_pick";
            }
            if (styleDataListResult instanceof OperationZoneResult) {
                return "operation_zone";
            }
            if (styleDataListResult instanceof FindSongHotListResultNew) {
                return "song_list";
            }
            throw new IllegalArgumentException("not support result " + styleDataListResult);
        }
    }
}
