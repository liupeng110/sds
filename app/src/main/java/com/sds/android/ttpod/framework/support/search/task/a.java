package com.sds.android.ttpod.framework.support.search.task;

import android.content.Intent;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.m;
import com.sds.android.ttpod.framework.a.h;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.search.b;
import com.sds.android.ttpod.framework.support.search.c;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* LyrPicBaseSearchTask */
public abstract class a implements Runnable {
    private int a = 0;
    private ArrayList<String> b = new ArrayList();

    protected abstract String a();

    protected abstract String a(MediaItem mediaItem);

    protected abstract ArrayList<ResultData> a(com.sds.android.ttpod.framework.modules.search.a.a aVar) throws Exception;

    protected abstract ArrayList<String> a(String str, String str2);

    protected abstract void a(List<ResultData> list);

    protected abstract boolean a(ArrayList<String> arrayList);

    protected abstract com.sds.android.ttpod.framework.support.search.a.a b();

    protected abstract String b(MediaItem mediaItem);

    public void run() {
        this.b.clear();
        try {
            com.sds.android.ttpod.framework.support.search.a.a b = b();
            g.d("LyrPicBaseSearchTask", "in run lookLyricPic begin task search title=%s", b.j());
            if (!a(b)) {
                throw new IllegalArgumentException("search no invalid args");
            } else if (b.e()) {
                a(true, b);
            } else {
                b(b, b.SEARCH_LOCAL_STARTED);
                MediaItem i = b.i();
                if (b.d() == c.PICTURE_SEARCH_TASK_TYPE && com.sds.android.ttpod.framework.storage.environment.b.F()) {
                    String b2 = b(i);
                    if (b2 != null) {
                        this.b.add(b2);
                        b(b, b.SEARCH_LOCAL_FINISHED, null, this.b, 0);
                        a(com.sds.android.ttpod.framework.support.search.a.c);
                        return;
                    }
                }
                ArrayList d = d();
                if (d == null || d.isEmpty()) {
                    b(b, b.SEARCH_LOCAL_FAILURE);
                    if (!b.c()) {
                        a(false, b);
                        return;
                    }
                    return;
                }
                this.b.addAll(d);
                b(b, b.SEARCH_LOCAL_FINISHED, null, this.b, 0);
                if (b.a()) {
                    a(d, b.d() == c.PICTURE_SEARCH_TASK_TYPE);
                } else if (b.c() || a(d)) {
                    a(com.sds.android.ttpod.framework.support.search.a.c);
                } else {
                    a(false, b);
                }
            }
        } catch (Exception e) {
            Exception exception = e;
            com.sds.android.ttpod.framework.support.search.a.a b3 = b();
            MediaItem i2 = b3.i();
            String str = "LyrPicBaseSearchTask";
            String str2 = "in run searchTask lookLyricPic type=%s title=%s exception=%s";
            Object[] objArr = new Object[3];
            objArr[0] = b3 instanceof com.sds.android.ttpod.framework.support.search.a.b ? "lyric" : User.KEY_AVATAR;
            objArr[1] = i2 != null ? i2.getTitle() : null;
            objArr[2] = exception.toString();
            g.c(str, str2, objArr);
            b(b3, b.SEARCH_DOWNLOAD_FAILURE);
            a(com.sds.android.ttpod.framework.support.search.a.b);
        }
    }

    private void a(ArrayList<String> arrayList, boolean z) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g.a("LyrPicBaseSearchTask", "removeResult %s", (String) it.next());
            e.h(r0);
        }
        if (z) {
            e.c(new File((String) arrayList.get(0)).getParentFile());
        }
    }

    protected void a(b bVar) {
        com.sds.android.ttpod.framework.support.search.a.a b = b();
        com.sds.android.ttpod.framework.support.search.a.a().a(b.i().getID(), b.d() == c.PICTURE_SEARCH_TASK_TYPE ? "picture_type" : "lyric_type", bVar);
    }

    private static void b(com.sds.android.ttpod.framework.support.search.a.a aVar, b bVar) {
        b(aVar, bVar, null, null, 0);
    }

    private static void b(com.sds.android.ttpod.framework.support.search.a.a aVar, b bVar, ArrayList<ResultData> arrayList, ArrayList<String> arrayList2, int i) {
        if (!aVar.b() && !aVar.a()) {
            String str;
            String str2 = "LyrPicBaseSearchTask";
            String str3 = "notifySearchTaskStatusChanged lookLyricPic looklyric type=%s SearchStatus=%s title=%s dAmount=%d";
            Object[] objArr = new Object[4];
            objArr[0] = aVar instanceof com.sds.android.ttpod.framework.support.search.a.b ? "lyric" : SocialConstants.PARAM_AVATAR_URI;
            objArr[1] = bVar.name();
            objArr[2] = aVar.i().getTitle();
            objArr[3] = Integer.valueOf(arrayList2 != null ? arrayList2.size() : 0);
            g.a(str2, str3, objArr);
            MediaItem i2 = aVar.i();
            if (aVar instanceof com.sds.android.ttpod.framework.support.search.a.b) {
                a(aVar, bVar, "lyric", i);
            } else if (aVar instanceof com.sds.android.ttpod.framework.support.search.a.c) {
                if (bVar == b.SEARCH_LOCAL_FINISHED || bVar == b.SEARCH_DOWNLOAD_FINISHED || bVar == b.SEARCH_LOCAL_FAILURE) {
                    h.d().a(i2.getID(), arrayList2);
                }
                a(aVar, bVar, SocialConstants.PARAM_AVATAR_URI, i);
            }
            String id = i2.getID();
            if (aVar instanceof com.sds.android.ttpod.framework.support.search.a.b) {
                str = "lyric_type";
            } else {
                str = "picture_type";
            }
            a(id, str, bVar, (ArrayList) arrayList, (ArrayList) arrayList2);
        }
    }

    private static void a(String str, String str2, b bVar, ArrayList<ResultData> arrayList, ArrayList<String> arrayList2) {
        Intent intent = new Intent(Action.LYRIC_PIC_OPERATE_RESULT);
        intent.putExtra(Members.MEDIA_ID, str);
        intent.putExtra(SocialConstants.PARAM_TYPE, str2);
        intent.putExtra("state", bVar);
        if (arrayList != null) {
            intent.putParcelableArrayListExtra("search_result_list", arrayList);
        }
        if (arrayList2 != null) {
            intent.putStringArrayListExtra("download_result_list", arrayList2);
        }
        BaseApplication.e().sendBroadcast(intent);
    }

    private static void a(com.sds.android.ttpod.framework.support.search.a.a aVar, b bVar, String str, int i) {
        MediaItem i2 = aVar.i();
        switch (bVar) {
            case SEARCH_ONLINE_NET_EXCEPTION:
            case SEARCH_ONLINE_FAILURE:
                m.a(str, i2.getSongID(), aVar.k(), aVar.j(), Integer.valueOf(2));
                return;
            case SEARCH_ONLINE_FINISHED:
                m.a(str, i2.getSongID(), aVar.k(), aVar.j(), Integer.valueOf(1));
                return;
            case SEARCH_DOWNLOAD_FAILURE:
                m.a(str, i, aVar.k(), aVar.j(), Integer.valueOf(2));
                return;
            case SEARCH_DOWNLOAD_FINISHED:
                m.a(str, i, aVar.k(), aVar.j(), Integer.valueOf(1));
                return;
            default:
                return;
        }
    }

    protected boolean a(com.sds.android.ttpod.framework.support.search.a.a aVar) {
        return aVar.i() != null;
    }

    protected void a(boolean z, com.sds.android.ttpod.framework.support.search.a.a aVar) throws Exception {
        b(aVar, b.SEARCH_ONLINE_STARTED);
        String a = a();
        boolean z2 = aVar instanceof com.sds.android.ttpod.framework.support.search.a.b;
        if (!z2 || c()) {
            g.a("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic list url = " + a);
            List a2 = a(a);
            String str = aVar.b() ? "batch" : z ? "manual" : "auto";
            long longValue = aVar.i() != null ? aVar.i().getSongID().longValue() : 0;
            if (a2 == null) {
                g.a("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, net exception", a);
                d.h.a(z2, str, false, -1, longValue);
                b(aVar, b.SEARCH_ONLINE_NET_EXCEPTION);
                a(com.sds.android.ttpod.framework.support.search.a.b);
                return;
            } else if (a2.isEmpty()) {
                g.a("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, server no result", a);
                d.h.a(z2, str, false, 0, longValue);
                b(aVar, b.SEARCH_ONLINE_FAILURE);
                a(com.sds.android.ttpod.framework.support.search.a.c);
                return;
            } else if (z) {
                g.a("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, manual result_group=%d", a, Integer.valueOf(a2.size()));
                d.h.a(z2, str, true, 0, longValue);
                b(aVar, b.SEARCH_ONLINE_FINISHED, a2, null, 0);
                a(com.sds.android.ttpod.framework.support.search.a.c);
                return;
            } else {
                g.a("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic getResultDataList url = %s, auto result_group=%d", a, Integer.valueOf(a2.size()));
                d.h.a(z2, str, true, 0, longValue);
                b(aVar, b.SEARCH_ONLINE_FINISHED);
                a(a2);
                return;
            }
        }
        g.a("LyrPicBaseSearchTask", "searchTaskFromNetwork lookLyricPic no permission url = " + a);
        b(aVar, b.SEARCH_ONLINE_SETTING_EXCEPTION);
        a(com.sds.android.ttpod.framework.support.search.a.c);
    }

    protected boolean c() {
        com.sds.android.ttpod.framework.modules.core.b.a R = com.sds.android.ttpod.framework.storage.environment.b.R();
        int d = EnvironmentUtils.c.d();
        com.sds.android.ttpod.framework.support.search.a.a b = b();
        if (!b.e() && !b.b() && ((com.sds.android.ttpod.framework.modules.core.b.a.WIFI != R || 2 != d) && com.sds.android.ttpod.framework.modules.core.b.a.ALL != R)) {
            return false;
        }
        if (d == 2 || !com.sds.android.ttpod.framework.storage.environment.b.I() || (b.e() && com.sds.android.ttpod.framework.storage.environment.b.I())) {
            return true;
        }
        return false;
    }

    /* JADX err: Inconsistent code. */
    protected java.util.ArrayList<com.sds.android.ttpod.framework.support.search.task.ResultData> a(java.lang.String r15) {
        /*
        r14 = this;
        r12 = 0;
        r11 = 1;
        r2 = 0;
        r0 = r14.b();
        r5 = r0.i();
        r0 = r14.a(r5);
        r1 = r14.b();
        r1 = r1.e();
        if (r1 != 0) goto L_0x0071;
    L_0x0019:
        if (r0 == 0) goto L_0x0071;
    L_0x001b:
        r0 = com.sds.android.sdk.lib.util.e.i(r0);
        r1 = com.sds.android.sdk.lib.util.m.a(r0);
        if (r1 != 0) goto L_0x0071;
    L_0x0025:
        r3 = new com.sds.android.ttpod.framework.modules.search.a.a;	 Catch:{ Exception -> 0x0053, all -> 0x0065 }
        r3.<init>();	 Catch:{ Exception -> 0x0053, all -> 0x0065 }
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0053, all -> 0x0065 }
        r4 = "UTF-8";
        r0 = r0.getBytes(r4);	 Catch:{ Exception -> 0x0053, all -> 0x0065 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0065 }
        r0 = "UTF-8";
        r3.setInput(r1, r0);	 Catch:{ Exception -> 0x018d }
        r0 = r14.a(r3);	 Catch:{ Exception -> 0x018d }
        if (r1 == 0) goto L_0x0193;
    L_0x0040:
        r1.close();	 Catch:{ Exception -> 0x004d }
        r1 = r0;
    L_0x0044:
        if (r1 == 0) goto L_0x0071;
    L_0x0046:
        r0 = r1.isEmpty();
        if (r0 != 0) goto L_0x0071;
    L_0x004c:
        return r1;
    L_0x004d:
        r1 = move-exception;
        r1.printStackTrace();
        r1 = r0;
        goto L_0x0044;
    L_0x0053:
        r0 = move-exception;
        r1 = r2;
    L_0x0055:
        r0.printStackTrace();	 Catch:{ all -> 0x0189 }
        if (r1 == 0) goto L_0x0190;
    L_0x005a:
        r1.close();	 Catch:{ Exception -> 0x005f }
        r1 = r2;
        goto L_0x0044;
    L_0x005f:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = r2;
        goto L_0x0044;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        if (r2 == 0) goto L_0x006b;
    L_0x0068:
        r2.close();	 Catch:{ Exception -> 0x006c }
    L_0x006b:
        throw r0;
    L_0x006c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x006b;
    L_0x0071:
        r0 = r2;
        r1 = r2;
    L_0x0073:
        r3 = 0;
        r4 = "LyrPicBaseSearchTask";
        r6 = "lookLyricPic will get search content form url %s";
        r7 = new java.lang.Object[r11];
        r7[r12] = r15;
        com.sds.android.sdk.lib.util.g.d(r4, r6, r7);
        r4 = 0;
        r6 = 0;
        r0 = com.sds.android.sdk.lib.a.a.a(r15, r4, r6);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r6 = r0.c();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        if (r4 != r6) goto L_0x014d;
    L_0x008d:
        r3 = r0.e();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r3 = com.sds.android.sdk.lib.util.m.a(r3);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r6 = new com.sds.android.ttpod.framework.modules.search.a.a;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r6.<init>();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r4 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r7 = "UTF-8";
        r7 = r3.getBytes(r7);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r4.<init>(r7);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r7 = "UTF-8";
        r6.setInput(r4, r7);	 Catch:{ Exception -> 0x0104 }
        r1 = r14.a(r6);	 Catch:{ Exception -> 0x0104 }
        if (r3 == 0) goto L_0x0130;
    L_0x00b0:
        r3 = r3.trim();	 Catch:{ Exception -> 0x0104 }
        r6 = "<?xml";
        r6 = r3.startsWith(r6);	 Catch:{ Exception -> 0x0104 }
        if (r6 == 0) goto L_0x00df;
    L_0x00bc:
        r6 = r14.a(r5);	 Catch:{ Exception -> 0x0104 }
        if (r6 == 0) goto L_0x00cd;
    L_0x00c2:
        if (r1 == 0) goto L_0x00cd;
    L_0x00c4:
        r7 = r1.isEmpty();	 Catch:{ Exception -> 0x0104 }
        if (r7 != 0) goto L_0x00cd;
    L_0x00ca:
        com.sds.android.sdk.lib.util.e.a(r3, r6);	 Catch:{ Exception -> 0x0104 }
    L_0x00cd:
        if (r0 == 0) goto L_0x00d2;
    L_0x00cf:
        r0.g();
    L_0x00d2:
        if (r4 == 0) goto L_0x004c;
    L_0x00d4:
        r4.close();	 Catch:{ Exception -> 0x00d9 }
        goto L_0x004c;
    L_0x00d9:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004c;
    L_0x00df:
        r6 = "LyrPicBaseSearchTask";
        r7 = "searchResult lookLyricPic not xml: is lyric %b, %s - %s, result=%s";
        r8 = 4;
        r8 = new java.lang.Object[r8];	 Catch:{ Exception -> 0x0104 }
        r9 = 0;
        r10 = r14 instanceof com.sds.android.ttpod.framework.support.search.task.b;	 Catch:{ Exception -> 0x0104 }
        r10 = java.lang.Boolean.valueOf(r10);	 Catch:{ Exception -> 0x0104 }
        r8[r9] = r10;	 Catch:{ Exception -> 0x0104 }
        r9 = 1;
        r10 = r5.getArtist();	 Catch:{ Exception -> 0x0104 }
        r8[r9] = r10;	 Catch:{ Exception -> 0x0104 }
        r9 = 2;
        r10 = r5.getTitle();	 Catch:{ Exception -> 0x0104 }
        r8[r9] = r10;	 Catch:{ Exception -> 0x0104 }
        r9 = 3;
        r8[r9] = r3;	 Catch:{ Exception -> 0x0104 }
        com.sds.android.sdk.lib.util.g.c(r6, r7, r8);	 Catch:{ Exception -> 0x0104 }
        goto L_0x00cd;
    L_0x0104:
        r3 = move-exception;
    L_0x0105:
        r6 = "LyrPicBaseSearchTask";
        r7 = "lookLyricPic get search content form url %s, has exception=%s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ all -> 0x013e }
        r9 = 0;
        r8[r9] = r15;	 Catch:{ all -> 0x013e }
        r9 = 1;
        r10 = r3.toString();	 Catch:{ all -> 0x013e }
        r8[r9] = r10;	 Catch:{ all -> 0x013e }
        com.sds.android.sdk.lib.util.g.d(r6, r7, r8);	 Catch:{ all -> 0x013e }
        r3.printStackTrace();	 Catch:{ all -> 0x013e }
        if (r0 == 0) goto L_0x0121;
    L_0x011e:
        r0.g();
    L_0x0121:
        if (r4 == 0) goto L_0x0126;
    L_0x0123:
        r4.close();	 Catch:{ Exception -> 0x0175 }
    L_0x0126:
        r3 = r14.a;
        r4 = r3 + 1;
        r14.a = r4;
        if (r3 < r11) goto L_0x0073;
    L_0x012e:
        goto L_0x004c;
    L_0x0130:
        r3 = "LyrPicBaseSearchTask";
        r6 = "lookLyricPic doGetRequest content is null, url=%s";
        r7 = 1;
        r7 = new java.lang.Object[r7];	 Catch:{ Exception -> 0x0104 }
        r8 = 0;
        r7[r8] = r15;	 Catch:{ Exception -> 0x0104 }
        com.sds.android.sdk.lib.util.g.d(r3, r6, r7);	 Catch:{ Exception -> 0x0104 }
        goto L_0x00cd;
    L_0x013e:
        r1 = move-exception;
        r13 = r1;
        r1 = r0;
        r0 = r13;
    L_0x0142:
        if (r1 == 0) goto L_0x0147;
    L_0x0144:
        r1.g();
    L_0x0147:
        if (r4 == 0) goto L_0x014c;
    L_0x0149:
        r4.close();	 Catch:{ Exception -> 0x017a }
    L_0x014c:
        throw r0;
    L_0x014d:
        r4 = "LyrPicBaseSearchTask";
        r6 = "lookLyricPic doGetRequest resultCode=%d url=%s";
        r7 = 2;
        r7 = new java.lang.Object[r7];	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r8 = 0;
        r9 = r0.c();	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r7[r8] = r9;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        r8 = 1;
        r7[r8] = r15;	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        com.sds.android.sdk.lib.util.g.d(r4, r6, r7);	 Catch:{ Exception -> 0x0185, all -> 0x017f }
        if (r0 == 0) goto L_0x016a;
    L_0x0167:
        r0.g();
    L_0x016a:
        if (r2 == 0) goto L_0x0126;
    L_0x016c:
        r3.close();	 Catch:{ Exception -> 0x0170 }
        goto L_0x0126;
    L_0x0170:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0126;
    L_0x0175:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0126;
    L_0x017a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x014c;
    L_0x017f:
        r1 = move-exception;
        r4 = r2;
        r13 = r1;
        r1 = r0;
        r0 = r13;
        goto L_0x0142;
    L_0x0185:
        r3 = move-exception;
        r4 = r2;
        goto L_0x0105;
    L_0x0189:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0066;
    L_0x018d:
        r0 = move-exception;
        goto L_0x0055;
    L_0x0190:
        r1 = r2;
        goto L_0x0044;
    L_0x0193:
        r1 = r0;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.framework.support.search.task.a.a(java.lang.String):java.util.ArrayList<com.sds.android.ttpod.framework.support.search.task.ResultData>");
    }

    protected void a(final Item item, final boolean z) {
        com.sds.android.sdk.lib.util.d.a((Object) item, "resultDataItem");
        g.a("LyrPicBaseSearchTask", "doDownload add for execute lookLyricPic url = " + item.c());
        com.sds.android.sdk.core.download.a.a().a("lyrics_picture_file_download", new TaskInfo(item.c(), item.d(), Boolean.valueOf(false)), new com.sds.android.sdk.core.download.b.a(this) {
            final /* synthetic */ a c;

            public void b(TaskInfo taskInfo) {
                super.b(taskInfo);
                g.a("LyrPicBaseSearchTask", "down lookLyricPic start %s", taskInfo.getSavePath());
                a.b(this.c.b(), b.SEARCH_DOWNLOAD_STARTED);
            }

            public void c(TaskInfo taskInfo) {
                super.c(taskInfo);
                g.a("LyrPicBaseSearchTask", "down finish lookLyricPic %s", taskInfo.getSavePath());
                com.sds.android.ttpod.framework.support.search.a.a b = this.c.b();
                synchronized (this.c.b) {
                    this.c.b.add(taskInfo.getSavePath());
                    a.b(b, b.SEARCH_DOWNLOAD_FINISHED, null, this.c.b, item.a());
                }
                if (z) {
                    d.h.a(b instanceof com.sds.android.ttpod.framework.support.search.a.b, b.b() ? "batch" : "auto", true, 0, b.i().getSongID().longValue(), item.a());
                    this.c.a(com.sds.android.ttpod.framework.support.search.a.a);
                }
            }

            public void a(TaskInfo taskInfo, com.sds.android.sdk.core.download.b.b bVar) {
                g.a("LyrPicBaseSearchTask", "down error lookLyricPic %s msg=%s", taskInfo.getSavePath(), bVar.name());
                a.b(this.c.b(), b.SEARCH_DOWNLOAD_FAILURE, null, null, item.a());
                com.sds.android.ttpod.framework.support.search.a.a b = this.c.b();
                String sourceUrl = taskInfo.getSourceUrl();
                if (b instanceof com.sds.android.ttpod.framework.support.search.a.b) {
                    a.b(taskInfo, "error", bVar, "lyric_type");
                    com.sds.android.ttpod.framework.a.b.g.a(sourceUrl);
                } else if (b instanceof com.sds.android.ttpod.framework.support.search.a.c) {
                    a.b(taskInfo, "error", bVar, "picture_type");
                    com.sds.android.ttpod.framework.a.b.g.b(sourceUrl);
                }
                if (z) {
                    d.h.a(b instanceof com.sds.android.ttpod.framework.support.search.a.b, b.b() ? "batch" : "auto", false, -1, b.i().getSongID().longValue(), item.a());
                    this.c.a(com.sds.android.ttpod.framework.support.search.a.b);
                }
            }
        });
    }

    protected ArrayList<String> d() {
        String title = b().i().getTitle();
        String artist = b().i().getArtist();
        ArrayList arrayList = new ArrayList();
        if (title == null) {
            title = b().h()[1];
        }
        if (artist == null) {
            artist = "";
        }
        ArrayList<String> a = a(title, artist);
        artist = (a == null || a.isEmpty()) ? null : (String) a.get(0);
        g.d("LyrPicBaseSearchTask", "searchTaskFromLocal lookLyricPic title=%s firstPath=%s", title, artist);
        return a;
    }

    public static void a(final String str, final Item item, final MediaItem mediaItem) {
        com.sds.android.ttpod.framework.support.search.a.a a;
        if ("lyric_type".equals(str)) {
            a = com.sds.android.ttpod.framework.modules.search.a.c.a(mediaItem);
            String d = item.d();
            if (d != null && d.endsWith(".lrc")) {
                e.h(d.replace(".lrc", ".trc"));
            }
        } else {
            a = com.sds.android.ttpod.framework.modules.search.a.c.b(mediaItem);
        }
        a.c(mediaItem.getTitle());
        a.d(mediaItem.getArtist());
        TaskInfo taskInfo = new TaskInfo(item.c(), item.d(), Boolean.valueOf(false));
        com.sds.android.sdk.core.download.b.a anonymousClass2 = new com.sds.android.sdk.core.download.b.a() {
            public void b(TaskInfo taskInfo) {
                super.b(taskInfo);
                g.d("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic onStarted down type=%s url=%s", str, item.c());
            }

            public void c(TaskInfo taskInfo) {
                super.c(taskInfo);
                g.d("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic onFinished down type=%s url=%s", str, item.c());
                d.h.a("lyric_type".equals(str), "manual", true, 0, mediaItem.getSongID().longValue(), item.a());
                ArrayList arrayList = new ArrayList();
                arrayList.add(item.d());
                a.b(a, b.SEARCH_DOWNLOAD_FINISHED, null, arrayList, item.a());
            }

            public void a(TaskInfo taskInfo, com.sds.android.sdk.core.download.b.b bVar) {
                String sourceUrl = taskInfo.getSourceUrl();
                g.d("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic onError down type=%s url=%s", str, sourceUrl);
                d.h.a("lyric_type".equals(str), "manual", false, -1, mediaItem.getSongID().longValue(), item.a());
                a.b(a, b.SEARCH_DOWNLOAD_FAILURE, null, null, item.a());
                if ("lyric_type".equals(str)) {
                    com.sds.android.ttpod.framework.a.b.g.a(sourceUrl);
                    a.b(taskInfo, "error", bVar, str);
                    return;
                }
                com.sds.android.ttpod.framework.a.b.g.b(sourceUrl);
                a.b(taskInfo, "error", bVar, str);
            }
        };
        g.d("LyrPicBaseSearchTask", "doDownloadResultItem lookLyricPic will execute down type=%s url=%s", str, item.c());
        com.sds.android.sdk.core.download.a.a().a("lyrics_picture_file_download", taskInfo, anonymousClass2);
        b(a, b.SEARCH_DOWNLOAD_STARTED);
    }

    private static void b(TaskInfo taskInfo, String str, com.sds.android.sdk.core.download.b.b bVar, String str2) {
        SSystemEvent sSystemEvent = new SSystemEvent("SYS_DOWNLOAD", str);
        sSystemEvent.append(Downloads.COLUMN_URI, taskInfo.getSourceUrl()).append(DownloadManagerFragment.DOWNLOAD_TYPE, str2).append("path", taskInfo.getSavePath());
        if (bVar != null) {
            sSystemEvent.append("error_code", bVar);
        }
        sSystemEvent.post();
    }
}
