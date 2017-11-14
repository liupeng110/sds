package com.sds.android.ttpod.component.video;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.sdk.lib.a.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mv.c;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.a.k;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.main.findsong.b;
import com.sds.android.ttpod.framework.a.e;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* VideoDownloadManager */
public class d {
    private static boolean a;
    private static int b;

    public static void a(final Context context, final c cVar) {
        if (!EnvironmentUtils.c.e()) {
            Toast.makeText(context, R.string.network_error, 0).show();
        } else if (2 == EnvironmentUtils.c.d() || k.a() || a.c()) {
            c(context, cVar);
        } else {
            com.sds.android.ttpod.fragment.main.findsong.a.a(context, new b() {
                public void a() {
                    d.c(context, cVar);
                }
            });
        }
    }

    private static String c(MvData mvData, int i) {
        return com.sds.android.ttpod.framework.a.z() + File.separator + e.a(mvData) + ("." + com.sds.android.sdk.lib.util.e.m(mvData.getMvListItemByQuality(i).getUrl()));
    }

    private static List<com.sds.android.ttpod.component.b.a> a(Context context, MvData mvData) {
        List<com.sds.android.ttpod.component.b.a> arrayList = new ArrayList();
        List mvList = mvData.getMvList();
        String str = "";
        for (int i = 0; i < mvList.size(); i++) {
            com.sds.android.ttpod.component.b.a aVar;
            int type = ((MvListItem) mvList.get(i)).getType();
            MvListItem mvListItemByQuality = mvData.getMvListItemByQuality(type);
            if (!(mvListItemByQuality == null || 0 == mvListItemByQuality.getSize())) {
                str = a(mvListItemByQuality.getSize());
            }
            if (type == 0) {
                aVar = new com.sds.android.ttpod.component.b.a(type, 0, context.getString(R.string.mv_standard_definition) + " (" + str + ")");
            } else if (1 == type) {
                aVar = new com.sds.android.ttpod.component.b.a(type, 0, context.getString(R.string.mv_high_definition) + " (" + str + ")");
            } else if (2 == type) {
                aVar = new com.sds.android.ttpod.component.b.a(type, 0, context.getString(R.string.mv_super_definition) + " (" + str + ")");
            } else {
            }
            aVar.a((Object) mvData);
            arrayList.add(aVar);
        }
        return arrayList;
    }

    private static void c(Context context, c cVar) {
        final MvData b = cVar.b();
        if (!m.a(b.getName()) && b.getMvList() != null) {
            int O = com.sds.android.ttpod.framework.storage.environment.b.O();
            if (O == -1) {
                b(context, b);
                return;
            }
            MvListItem d = d(b, O);
            if (d != null) {
                final int type = d.getType();
                b.setDownloadQuality(type);
                if (!f(b, type)) {
                    String string = context.getString(c.a(type));
                    String str = string + a(d.getSize());
                    h hVar = new h(context, context.getString(R.string.download_mv_size_warning, new Object[]{cVar.j(), str}), null, null);
                    hVar.setTitle((int) R.string.prompt_title);
                    hVar.a((int) R.string.ok, new com.sds.android.ttpod.common.a.a.a<h>() {
                        public void a(h hVar) {
                            k.a(true);
                            d.e(b, type);
                        }
                    }, (int) R.string.cancel, null);
                    hVar.show();
                    new com.sds.android.ttpod.framework.a.b.c("show").a("mv_download").a("mv_download_tips", "1").a("mv_download_quality", String.valueOf(type)).a();
                }
            }
        }
    }

    private static MvListItem d(MvData mvData, int i) {
        mvData.setDownloadQuality(i);
        MvListItem mvListItem = null;
        while (i >= 0) {
            mvListItem = mvData.getMvListItemByQuality(i);
            if (mvListItem != null) {
                break;
            }
            i--;
        }
        if (mvListItem != null) {
            return mvListItem;
        }
        mvListItem = (MvListItem) mvData.getMvList().get(0);
        if (mvListItem == null) {
            return null;
        }
        return mvListItem;
    }

    private static void b(Context context, MvData mvData) {
        List a = a(context, mvData);
        final View inflate = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_list_footer_remember, null, false);
        inflate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.dialog_check_box);
                d.a = !checkBox.isChecked();
                checkBox.setChecked(d.a);
            }
        });
        b = -1;
        f.a(context, a, context.getString(R.string.choose_music_download_quality), new com.sds.android.ttpod.component.b.a.b() {
            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                MvData mvData = (MvData) aVar.h();
                d.b = aVar.g();
                if (inflate != null && ((CheckBox) inflate.findViewById(R.id.dialog_check_box)).isChecked()) {
                    com.sds.android.ttpod.framework.storage.environment.b.c(d.b);
                }
                mvData.setDownloadQuality(d.b);
                if (!d.f(mvData, d.b)) {
                    d.e(mvData, d.b);
                }
            }
        }, inflate).setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                new com.sds.android.ttpod.framework.a.b.c("show").a("mv_download").a("mv_download_tips", d.a ? "1" : FeedbackItem.STATUS_WAITING).a("mv_download_quality", String.valueOf(d.b)).a();
            }
        });
    }

    private static void e(MvData mvData, int i) {
        mvData.setDownloadQuality(i);
        MvListItem mvListItemByQuality = mvData.getMvListItemByQuality(i);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_MV_TASK, mvListItemByQuality.getUrl(), mvData));
    }

    private static boolean f(MvData mvData, int i) {
        if (com.sds.android.sdk.lib.util.e.a(c(mvData, i))) {
            f.a((int) R.string.mv_had_download);
            return true;
        }
        if (!((Boolean) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.IS_DOWNLOAD_TASK_EXIST, r0), Boolean.class)).booleanValue()) {
            return false;
        }
        f.a((int) R.string.downloading);
        return true;
    }

    private static String a(long j) {
        return String.valueOf(new DecimalFormat("##0.00").format((double) (((float) j) / 1048576.0f))) + "MB";
    }

    public static MvData a(String str) {
        try {
            g.d("VideoDownloadManager", "lookDanmaku getMvDataFromCache exists=%b jsonPath=%s", Boolean.valueOf(com.sds.android.sdk.lib.util.e.b(com.sds.android.ttpod.framework.a.A() + File.separator + com.sds.android.sdk.lib.util.e.k(str))), com.sds.android.ttpod.framework.a.A() + File.separator + com.sds.android.sdk.lib.util.e.k(str));
            return (MvData) com.sds.android.sdk.lib.util.f.a(com.sds.android.sdk.lib.util.e.i(com.sds.android.ttpod.framework.a.A() + File.separator + com.sds.android.sdk.lib.util.e.k(str)), MvData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
