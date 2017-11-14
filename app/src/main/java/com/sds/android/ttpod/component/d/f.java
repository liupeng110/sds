package com.sds.android.ttpod.component.d;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.share.ShareSelectActivity;
import com.sds.android.ttpod.b.c;
import com.sds.android.ttpod.b.q;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.a.e;
import com.sds.android.ttpod.component.d.a.g;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.component.d.a.i;
import com.sds.android.ttpod.component.d.a.r;
import com.sds.android.ttpod.component.d.a.s;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* PopupsUtils */
public class f {
    private static Toast a;
    private static s b;
    private static PopupWindow c;
    private static Context d;
    private static final SparseIntArray e = new SparseIntArray();
    private static SparseArray<AudioQuality> f = new SparseArray();
    private static final int[] g = new int[]{10, 20, 30, 60, 90, 0};

    static {
        e.put("title_key".hashCode(), 7);
        e.put("artist_key".hashCode(), 8);
        e.put("album_key".hashCode(), 9);
        e.put("genre_key".hashCode(), 13);
        e.put(MediaStorage.MEDIA_ORDER_BY_ADD_TIME.hashCode(), 10);
        e.put(MediaStorage.MEDIA_ORDER_BY_FILE_NAME.hashCode(), 11);
        e.put(MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME.hashCode(), 14);
        e.put(MediaStorage.MEDIA_ORDER_BY_TITLE_DESC.hashCode(), 7);
        e.put(MediaStorage.MEDIA_ORDER_BY_ARTIST_DESC.hashCode(), 8);
        e.put(MediaStorage.MEDIA_ORDER_BY_ALBUM_DESC.hashCode(), 9);
        e.put(MediaStorage.MEDIA_ORDER_BY_GENRE_DESC.hashCode(), 13);
        e.put(MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC.hashCode(), 10);
        e.put(MediaStorage.MEDIA_ORDER_BY_FILE_NAME_DESC.hashCode(), 11);
        e.put(MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC.hashCode(), 14);
    }

    public static void a(Context context) {
        a = Toast.makeText(context, "", 1);
        d = context;
    }

    public static void a(String str) {
        if (!m.a(str) || a == null) {
            if (j.c()) {
                a.cancel();
                a = Toast.makeText(d, "", 1);
            }
            a.setText(str);
            a.show();
        }
    }

    public static void a(int i) {
        a(d.getString(i));
    }

    public static synchronized void a(Context context, int i) {
        synchronized (f.class) {
            if (context != null) {
                a(context, context.getResources().getString(i), true);
            }
        }
    }

    public static synchronized void a(Context context, String str) {
        synchronized (f.class) {
            if (context != null) {
                a(context, str, true, true);
            }
        }
    }

    public static synchronized void a(Context context, int i, boolean z) {
        synchronized (f.class) {
            if (context != null) {
                a(context, context.getResources().getString(i), z, true);
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z) {
        synchronized (f.class) {
            if (context != null) {
                a(context, str, z, true);
            }
        }
    }

    public static synchronized void a(Context context, int i, boolean z, boolean z2) {
        synchronized (f.class) {
            if (context != null) {
                a(context, context.getString(i), z, z2);
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z, boolean z2) {
        synchronized (f.class) {
            if (context != null) {
                if (b != null && b.isShowing()) {
                    b.dismiss();
                    b = null;
                }
                b = new s(context);
                b.setCanceledOnTouchOutside(z);
                b.setCancelable(z2);
                b.a((CharSequence) str);
                b.show();
            }
        }
    }

    public static void a() {
        if (b != null && b.isShowing()) {
            b.dismiss();
            b = null;
        }
    }

    public static r a(Context context, CharSequence charSequence, d[] dVarArr, int i, final b bVar, a<r> aVar) {
        if (context == null) {
            return null;
        }
        final r rVar = new r(context, dVarArr, aVar);
        rVar.setTitle(charSequence);
        rVar.c(i);
        rVar.show();
        rVar.a(new b() {
            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                if (bVar != null) {
                    bVar.a(aVar, i);
                }
                rVar.dismiss();
            }
        });
        return rVar;
    }

    public static e a(Context context, List<com.sds.android.ttpod.component.b.a> list, String str, b bVar) {
        return a(context, (List) list, str, bVar, null);
    }

    public static e a(Context context, com.sds.android.ttpod.component.b.a[] aVarArr, String str, b bVar) {
        return a(context, new ArrayList(Arrays.asList(aVarArr)), str, bVar);
    }

    public static e a(Context context, List<com.sds.android.ttpod.component.b.a> list, String str, final b bVar, View view) {
        if (context == null) {
            return null;
        }
        final View view2 = view;
        final e anonymousClass12 = new e(context, list, R.string.cancel, null) {
            protected View b() {
                return view2;
            }
        };
        anonymousClass12.a(new b() {
            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                if (bVar != null) {
                    bVar.a(aVar, i);
                }
                anonymousClass12.dismiss();
            }
        });
        anonymousClass12.setTitle((CharSequence) str);
        anonymousClass12.show();
        return anonymousClass12;
    }

    public static e a(final Context context, final MediaItem mediaItem) {
        if (context == null || mediaItem == null) {
            return null;
        }
        if (mediaItem.isOnline() && mediaItem.getLocalDataSource() == null) {
            a((int) R.string.set_ringtone_online_toast);
            mediaItem.getLocalDataSource();
        }
        final e eVar = new e(context, Arrays.asList(new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a((int) R.id.ring_tone_phone, 0, (int) R.string.ringtone_phone), new com.sds.android.ttpod.component.b.a((int) R.id.ring_tone_notice, 0, (int) R.string.ringtone_notice), new com.sds.android.ttpod.component.b.a((int) R.id.ring_tone_all, 0, (int) R.string.ringtone_all)}), null, null);
        eVar.setTitle(context.getString(R.string.set_ringtone_title));
        eVar.b(R.string.cancel, null);
        eVar.a(new b() {
            public void a(final com.sds.android.ttpod.component.b.a aVar, int i) {
                com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<Object, Integer>(this, null) {
                    final /* synthetic */ AnonymousClass18 b;

                    protected /* synthetic */ Object onDoInBackground(Object obj) {
                        return a(obj);
                    }

                    protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                        a((Integer) obj);
                    }

                    protected Integer a(Object obj) {
                        String localDataSource = mediaItem.getLocalDataSource();
                        if (localDataSource == null) {
                            return Integer.valueOf(2);
                        }
                        int a;
                        switch (aVar.g()) {
                            case R.id.ring_tone_notice:
                                f.b(localDataSource, com.sds.android.ttpod.framework.a.b.r.ACTION_RIGHT_MENU_RING_NOTIFICATION);
                                l.aO();
                                a = q.a(context, localDataSource, 2);
                                break;
                            case R.id.ring_tone_phone:
                                l.aN();
                                f.b(localDataSource, com.sds.android.ttpod.framework.a.b.r.ACTION_RIGHT_MENU_RING_INCOMING);
                                a = q.a(context, localDataSource, 1);
                                break;
                            default:
                                f.b(localDataSource, com.sds.android.ttpod.framework.a.b.r.ACTION_RIGHT_MENU_RING_ALL);
                                l.aP();
                                a = q.a(context, localDataSource, 1);
                                q.a(context, localDataSource, 2);
                                q.a(context, localDataSource, 4);
                                break;
                        }
                        return Integer.valueOf(a);
                    }

                    protected void a(Integer num) {
                        switch (num.intValue()) {
                            case 1:
                                f.a((int) R.string.set_ringtone_successful);
                                return;
                            case 2:
                                f.a((int) R.string.set_ringtone_fail);
                                return;
                            case 3:
                                f.a((int) R.string.set_ringtone_unsupport);
                                return;
                            default:
                                return;
                        }
                    }
                });
                eVar.dismiss();
            }
        });
        eVar.show();
        return eVar;
    }

    private static void b(String str, com.sds.android.ttpod.framework.a.b.r rVar) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", rVar.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue());
        sUserEvent.setPageParameter(true);
        sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, str).post();
    }

    public static void a(Context context, MediaItem mediaItem, OnDismissListener onDismissListener) {
        if (context != null) {
            new com.sds.android.ttpod.component.d.a.f(context, mediaItem, onDismissListener).show();
        }
    }

    public static void b(Context context, MediaItem mediaItem, OnDismissListener onDismissListener) {
        if (context != null) {
            new g(context, mediaItem, onDismissListener).show();
        }
    }

    public static void a(Context context, String str, a<h> aVar) {
        h hVar = new h(context, context.getString(R.string.media_delete_single, new Object[]{str}), (int) R.string.delete, (a) aVar, (int) R.string.cancel, null);
        hVar.setTitle((int) R.string.delete);
        hVar.show();
    }

    public static void a(Context context, a<h> aVar) {
        h hVar = new h(context, context.getString(R.string.download_remove_all_mv_confirm_hint), (int) R.string.delete, (a) aVar, (int) R.string.cancel, null);
        hVar.setTitle((int) R.string.delete_all_download);
        hVar.show();
    }

    public static com.sds.android.ttpod.component.d.a.m a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, a<com.sds.android.ttpod.component.d.a.m> aVar) {
        return a(context, i, charSequence, charSequence2, (a) aVar, null);
    }

    public static com.sds.android.ttpod.component.d.a.m a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, a<com.sds.android.ttpod.component.d.a.m> aVar, a<com.sds.android.ttpod.component.d.a.m> aVar2) {
        if (charSequence2 == null) {
            return null;
        }
        final CharSequence charSequence3 = charSequence2;
        com.sds.android.ttpod.component.d.a.m anonymousClass19 = new com.sds.android.ttpod.component.d.a.m(context, i, aVar, aVar2) {
            protected View a(Context context) {
                return f.b(context, charSequence3);
            }
        };
        anonymousClass19.setTitle(charSequence);
        anonymousClass19.show();
        return anonymousClass19;
    }

    private static View b(Context context, CharSequence charSequence) {
        TextView textView = (TextView) View.inflate(context, R.layout.dialog_body_message, null);
        textView.setText(charSequence);
        return textView;
    }

    public static i a(Context context, CharSequence charSequence, final CharSequence charSequence2, int i, a<i> aVar) {
        if (charSequence2 == null) {
            return null;
        }
        i anonymousClass20 = new i(context, aVar, null) {
            protected View a(Context context) {
                return f.b(context, charSequence2);
            }
        };
        anonymousClass20.setTitle(charSequence);
        anonymousClass20.b(i);
        anonymousClass20.show();
        return anonymousClass20;
    }

    public static void b(Context context, String str, final a<com.sds.android.ttpod.component.d.a.b> aVar) {
        if (context != null) {
            com.sds.android.ttpod.component.d.a.b.a[] aVarArr = new com.sds.android.ttpod.component.d.a.b.a[1];
            CharSequence charSequence = "";
            if (m.a(str)) {
                str = com.sds.android.ttpod.b.l.a(context);
            }
            aVarArr[0] = new com.sds.android.ttpod.component.d.a.b.a(0, charSequence, str, context.getString(R.string.input_playlist_name_hint));
            com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(context, aVarArr, R.string.save, new a<com.sds.android.ttpod.component.d.a.b>() {
                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    try {
                        if (com.sds.android.ttpod.b.l.a(bVar.c(0).e().toString())) {
                            bVar.e(false);
                            f.a((int) R.string.playlist_name_existed);
                            return;
                        }
                        bVar.e(true);
                        aVar.a(bVar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            bVar.setTitle((int) R.string.create_playlist);
            bVar.show();
        }
    }

    public static void a(final Context context, String[] strArr, final boolean z) {
        com.sds.android.ttpod.component.d.a.j jVar = new com.sds.android.ttpod.component.d.a.j(context, new d[]{(d) new d(0, R.string.clean_cache_online, false).a(strArr[0]), (d) new d(1, R.string.clean_cache_music, false).a(strArr[1]), (d) new d(2, R.string.clean_cache_pic, false).a(strArr[2]), (d) new d(3, R.string.clean_cache_lyric, false).a(strArr[3])}, new a<com.sds.android.ttpod.component.d.a.j>() {
            public void a(com.sds.android.ttpod.component.d.a.j jVar) {
                List d = jVar.d();
                if (d != null && !d.isEmpty()) {
                    t.a(com.sds.android.ttpod.framework.a.b.r.ACTION_SETTING_CLEAR_CACHE_CLEAR, com.sds.android.ttpod.framework.a.b.s.PAGE_NONE);
                    l.q();
                    f.a(context, (int) R.string.cleaning_cache, false, false);
                    com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<List<d>, Boolean>(this, d) {
                        final /* synthetic */ AnonymousClass2 a;

                        protected /* synthetic */ Object onDoInBackground(Object obj) {
                            return a((List) obj);
                        }

                        protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                            a((Boolean) obj);
                        }

                        protected Boolean a(List<d> list) {
                            int size = list.size();
                            int i = 0;
                            boolean z = false;
                            while (i < size) {
                                boolean z2;
                                d dVar = (d) list.get(i);
                                if (dVar.g() == 0) {
                                    com.sds.android.sdk.lib.util.e.b(new File(com.sds.android.ttpod.framework.a.j()));
                                    z2 = true;
                                } else if (1 == dVar.g()) {
                                    String[] strArr = null;
                                    if (com.sds.android.ttpod.framework.storage.a.a.a().M() != null) {
                                        strArr = new String[]{com.sds.android.ttpod.framework.a.D(), com.sds.android.ttpod.framework.a.a(com.sds.android.ttpod.framework.storage.a.a.a().M().getSongID())};
                                    }
                                    com.sds.android.sdk.lib.util.e.a(com.sds.android.ttpod.framework.a.g(), 0, strArr);
                                    z2 = true;
                                } else if (2 == dVar.g()) {
                                    com.sds.android.sdk.lib.util.e.b(new File(com.sds.android.ttpod.framework.a.v()));
                                    com.sds.android.sdk.lib.util.e.b(new File(com.sds.android.ttpod.framework.a.t()));
                                    z2 = true;
                                } else if (3 == dVar.g()) {
                                    com.sds.android.sdk.lib.util.e.b(new File(com.sds.android.ttpod.framework.a.s()));
                                    z2 = true;
                                } else {
                                    z2 = z;
                                }
                                i++;
                                z = z2;
                            }
                            return Boolean.valueOf(z);
                        }

                        protected void a(Boolean bool) {
                            if (context != null && z) {
                                f.a();
                            }
                            if (bool.booleanValue()) {
                                f.a((int) R.string.cache_has_been_cleaned);
                            }
                        }
                    });
                }
            }
        }, null);
        jVar.b((int) R.string.setting_dialog_button_clean);
        jVar.setTitle(context.getString(R.string.clean_cache_title));
        jVar.show();
    }

    public static void a(Activity activity, MediaItem mediaItem) {
        if (activity != null) {
            b(activity, com.sds.android.ttpod.b.s.a(mediaItem, ""));
        }
    }

    public static void a(Activity activity, MvData mvData) {
        if (activity != null) {
            b(activity, com.sds.android.ttpod.b.s.a(mvData, ""));
        }
    }

    public static void a(Activity activity, com.sds.android.ttpod.common.b.a.a aVar) {
        if (activity != null && aVar != null) {
            b(activity, aVar);
        }
    }

    private static void b(Activity activity, com.sds.android.ttpod.common.b.a.a aVar) {
        a(activity, (Serializable) aVar, "");
    }

    private static void a(Activity activity, Serializable serializable, String str) {
        if (activity != null && !activity.isFinishing()) {
            a(serializable);
            Intent intent = new Intent(activity, ShareSelectActivity.class);
            intent.putExtra(ShareSelectActivity.KEY_EXTRA_DATA, serializable);
            ShareSelectActivity.setShareAction(new com.sds.android.ttpod.activities.share.a(activity, str));
            activity.startActivity(intent);
        }
    }

    private static void a(Serializable serializable) {
        if (b(serializable)) {
            com.sds.android.ttpod.framework.a.b.d.s.b("songlist_id");
            com.sds.android.ttpod.framework.a.b.d.s.b("songlist_type");
            com.sds.android.ttpod.framework.a.b.d.s.b("scm");
            com.sds.android.ttpod.framework.a.b.d.s.b("trigger_id");
            return;
        }
        com.sds.android.ttpod.framework.a.b.d.s.a("songlist_id", com.sds.android.ttpod.framework.a.b.d.t.a().b("songlist_id"));
        com.sds.android.ttpod.framework.a.b.d.s.a("songlist_type", com.sds.android.ttpod.framework.a.b.d.t.a().b("songlist_type"));
        com.sds.android.ttpod.framework.a.b.d.s.a("scm", com.sds.android.ttpod.framework.a.b.d.t.a().b("scm"));
        com.sds.android.ttpod.framework.a.b.d.s.a("trigger_id", com.sds.android.ttpod.framework.a.b.d.t.a().b("trigger_id"));
    }

    private static boolean b(Serializable serializable) {
        if ((serializable instanceof com.sds.android.ttpod.common.b.a.a) && ((com.sds.android.ttpod.common.b.a.a) serializable).k() == com.sds.android.ttpod.common.b.a.a.a.MV) {
            return true;
        }
        return false;
    }

    public static void a(Activity activity, MediaItem mediaItem, Bitmap bitmap) {
        if (activity != null) {
            b(activity, com.sds.android.ttpod.b.s.a(mediaItem, bitmap));
        }
    }

    public static void a(Activity activity, Post post) {
        a(activity, (Serializable) post, "");
    }

    public static void a(Activity activity, Post post, String str) {
        a(activity, (Serializable) post, str);
    }

    public static void b(final Context context, final a<com.sds.android.ttpod.component.d.a.b> aVar) {
        if (context != null) {
            int q = com.sds.android.ttpod.framework.storage.environment.b.q();
            com.sds.android.ttpod.component.d.a.b bVar = new com.sds.android.ttpod.component.d.a.b(context, new com.sds.android.ttpod.component.d.a.b.a[]{new com.sds.android.ttpod.component.d.a.b.a(1, "", "" + q, context.getString(R.string.input_wait_sleep_time), 2, 17, 4)}, R.string.start, new a<com.sds.android.ttpod.component.d.a.b>() {
                public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                    int i = 0;
                    try {
                        String charSequence = bVar.c(1).e().toString();
                        if (!m.a(charSequence)) {
                            i = Integer.parseInt(charSequence);
                        }
                        Integer valueOf = Integer.valueOf(i);
                        bVar.e(true);
                        if (valueOf.intValue() <= 0) {
                            f.a(context.getString(R.string.input_invalid_args));
                            bVar.e(false);
                            return;
                        }
                        if (com.sds.android.ttpod.framework.base.e.ErrNone != ((com.sds.android.ttpod.framework.base.e) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SLEEP_MODE, valueOf), com.sds.android.ttpod.framework.base.e.class))) {
                            f.a(context.getString(R.string.input_invalid_args));
                            return;
                        }
                        l.a((long) valueOf.intValue());
                        new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_GLOBAL_MENU_SLEEP.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_GLOBAL_MENU.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_GLOBAL_MENU_DIALOG.getValue()).append("sleep_time", valueOf).post();
                        f.b(valueOf);
                        f.a(context.getString(R.string.sleep_after_num_minute, new Object[]{valueOf}));
                        if (aVar != null) {
                            aVar.a(bVar);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, null);
            bVar.setTitle(context.getString(R.string.input_wait_sleep_time));
            bVar.show();
        }
    }

    private static void b(Integer num) {
        new com.sds.android.ttpod.framework.a.b.b().b("clock").a("time", String.valueOf(num)).a();
    }

    public static void a(Activity activity, MediaItem mediaItem, OnDismissListener onDismissListener, b bVar, b bVar2) {
        if (activity != null) {
            if (mediaItem == null) {
                throw new IllegalArgumentException("mediaItem should not be null");
            }
            final e eVar = new e((Context) activity, Arrays.asList(new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_delete, (int) R.string.icon_media_menu_delete, (int) R.string.delete), new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_add, (int) R.string.icon_media_menu_add, (int) R.string.add), new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_share, (int) R.string.icon_media_menu_share, (int) R.string.share), new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_send, (int) R.string.icon_media_menu_send, (int) R.string.send), new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_info, (int) R.string.icon_media_menu_info, (int) R.string.media_info)}), null, null);
            final b bVar3 = bVar;
            final MediaItem mediaItem2 = mediaItem;
            final Activity activity2 = activity;
            final OnDismissListener onDismissListener2 = onDismissListener;
            final b bVar4 = bVar2;
            b anonymousClass4 = new b() {
                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    eVar.dismiss();
                    switch (aVar.g()) {
                        case R.id.media_menu_add:
                            if (bVar4 != null) {
                                l.aJ();
                                new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_ADD.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PORTRAIT_PLAYER.getValue()).post();
                                bVar4.a(aVar, i);
                                com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_add");
                                return;
                            }
                            return;
                        case R.id.media_menu_delete:
                            if (bVar3 != null) {
                                l.aI();
                                new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_DELETE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PORTRAIT_PLAYER.getValue()).post();
                                bVar3.a(aVar, i);
                                com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_delete");
                                return;
                            }
                            return;
                        case R.id.media_menu_info:
                            l.aQ();
                            new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_SONG_INFO.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PORTRAIT_PLAYER.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue()).post();
                            f.a(activity2, mediaItem2, onDismissListener2);
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_info");
                            return;
                        case R.id.media_menu_send:
                            l.aL();
                            new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_SEND.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, mediaItem2.getSongID()).post();
                            c.a(activity2, new File[]{new File(mediaItem2.getLocalDataSource())});
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_send");
                            return;
                        case R.id.media_menu_share:
                            l.aH();
                            new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_SHARE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, mediaItem2.getSongID()).post();
                            f.a(activity2, mediaItem2);
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_share");
                            return;
                        default:
                            return;
                    }
                }
            };
            eVar.setTitle((int) R.string.more);
            eVar.a(anonymousClass4);
            eVar.b(R.string.cancel, new a<e>() {
                public void a(e eVar) {
                    eVar.dismiss();
                }
            });
            l.aG();
            eVar.show();
        }
    }

    public static void a(final Activity activity, final MediaItem mediaItem, final b bVar) {
        if (activity != null && bVar != null) {
            if (mediaItem == null) {
                throw new IllegalArgumentException("mediaItem should not be null");
            }
            com.sds.android.ttpod.component.b.a aVar = new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_share, (int) R.string.icon_media_menu_share, (int) R.string.share);
            com.sds.android.ttpod.component.b.a aVar2 = new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_mv, (int) R.string.icon_media_menu_mv, (int) R.string.media_item_menu_mv);
            com.sds.android.ttpod.component.b.a aVar3 = new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_singer, (int) R.string.icon_media_menu_singer, (int) R.string.media_item_menu_singer);
            com.sds.android.ttpod.component.b.a aVar4 = new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_album, (int) R.string.icon_media_menu_album, (int) R.string.media_item_menu_album);
            com.sds.android.ttpod.component.b.a aVar5 = new com.sds.android.ttpod.component.b.a((int) R.id.media_menu_related, (int) R.string.icon_media_menu_related, (int) R.string.media_item_menu_related);
            List arrayList = new ArrayList();
            arrayList.add(aVar);
            if (mediaItem.containMV()) {
                arrayList.add(aVar2);
            }
            arrayList.add(aVar3);
            if (mediaItem.getAlbumId() != 0) {
                arrayList.add(aVar4);
            }
            arrayList.add(aVar5);
            final e eVar = new e((Context) activity, arrayList, (int) R.string.cancel, new a<e>() {
                public void a(e eVar) {
                    eVar.dismiss();
                }
            });
            b anonymousClass7 = new b() {
                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    eVar.dismiss();
                    SUserEvent sUserEvent;
                    switch (aVar.g()) {
                        case R.id.media_menu_album:
                            bVar.a(aVar, i);
                            sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_ALBUM.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PLAYER_MENU_MORE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_ALBUM_DETAIL.getValue());
                            sUserEvent.setPageParameter(true);
                            sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("singer_id", Long.valueOf(mediaItem.getArtistID())).append("song_album_id", Long.valueOf(mediaItem.getAlbumId())).post();
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_album");
                            return;
                        case R.id.media_menu_mv:
                            com.sds.android.ttpod.fragment.main.findsong.a.b(activity, new com.sds.android.ttpod.fragment.main.findsong.b(this) {
                                final /* synthetic */ AnonymousClass7 a;

                                {
                                    this.a = r1;
                                }

                                public void a() {
                                    com.sds.android.ttpod.framework.a.b.d.j.a("mv_origin", "portrait_menu_mv");
                                    VideoPlayManager.a(activity, mediaItem);
                                }
                            });
                            List mVUrls = ((OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(mediaItem.getExtra(), OnlineMediaItem.class)).getMVUrls();
                            SUserEvent sUserEvent2 = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_MENU_MORE_MV.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PLAYER_MENU_MORE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue());
                            sUserEvent2.append(MediasColumns.SONG_ID, mediaItem.getSongID());
                            sUserEvent2.append("mv_id", Integer.valueOf(((MvListItem) mVUrls.get(0)).getId()));
                            sUserEvent2.post();
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_mv");
                            return;
                        case R.id.media_menu_related:
                            bVar.a(aVar, i);
                            sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_RELATED_POST.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PLAYER_MENU_MORE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_RELATED_POST.getValue());
                            sUserEvent.setPageParameter(true);
                            sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("singer_id", Long.valueOf(mediaItem.getArtistID())).append("song_album_id", Long.valueOf(mediaItem.getAlbumId())).post();
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_similar");
                            return;
                        case R.id.media_menu_share:
                            l.aH();
                            f.a(activity, mediaItem);
                            new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_SHARE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PORTRAIT_PLAYER.getValue()).append(MediasColumns.SONG_ID, mediaItem.getSongID()).post();
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_share");
                            return;
                        case R.id.media_menu_singer:
                            bVar.a(aVar, i);
                            sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_CLICK_PORTRAIT_MENU_MORE_SINGER.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_PLAYER_MENU_MORE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_SINGER_MESSAGE.getValue());
                            sUserEvent.setPageParameter(true);
                            sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID()).append("singer_id", Long.valueOf(mediaItem.getArtistID())).append("song_album_id", Long.valueOf(mediaItem.getAlbumId())).post();
                            com.sds.android.ttpod.framework.a.b.b.a("portrait_menu_singer");
                            return;
                        default:
                            return;
                    }
                }
            };
            eVar.b(true);
            eVar.setTitle((int) R.string.more);
            l.aG();
            eVar.a(anonymousClass7);
            eVar.show();
        }
    }

    public static void b(final Context context) {
        List arrayList = new ArrayList(g.length);
        for (int i : g) {
            CharSequence string;
            if (i > 0) {
                string = context.getString(R.string.timing_some_minute, new Object[]{Integer.valueOf(i)});
            } else {
                string = context.getString(R.string.timing_custom);
            }
            arrayList.add(new com.sds.android.ttpod.component.b.a(0, 0, string).a(Integer.valueOf(i)));
        }
        final e eVar = new e(context, arrayList, null, null);
        eVar.setTitle((int) R.string.set_auto_sleep_time);
        eVar.a(new b() {
            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                eVar.dismiss();
                int intValue = ((Number) aVar.h()).intValue();
                if (intValue <= 0) {
                    f.b(context, null);
                    return;
                }
                if (com.sds.android.ttpod.framework.base.e.ErrNone != ((com.sds.android.ttpod.framework.base.e) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SLEEP_MODE, Integer.valueOf(intValue)), com.sds.android.ttpod.framework.base.e.class))) {
                    f.a(context.getString(R.string.input_invalid_args));
                    return;
                }
                l.a((long) intValue);
                new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_GLOBAL_MENU_SLEEP.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_GLOBAL_MENU.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_GLOBAL_MENU_DIALOG.getValue()).append("sleep_time", Integer.valueOf(intValue)).post();
                f.b(Integer.valueOf(intValue));
                f.a(context.getString(R.string.sleep_after_num_minute, new Object[]{Integer.valueOf(intValue)}));
            }
        });
        eVar.b(R.string.cancel, null);
        eVar.show();
    }

    public static void a(Activity activity, MediaItem mediaItem, com.sds.android.ttpod.fragment.main.list.d.a aVar, int i) {
        if (activity != null && mediaItem != null) {
            if (!mediaItem.hasCopyright()) {
                new com.sds.android.ttpod.component.c.a(activity, mediaItem).a(13);
            } else if (mediaItem.isOnline() || com.sds.android.sdk.lib.util.e.a(mediaItem.getLocalDataSource())) {
                e eVar = new e((Context) activity, new b(mediaItem).a(), (int) R.string.cancel, null);
                eVar.setTitle(mediaItem.getTitle());
                eVar.a(new com.sds.android.ttpod.fragment.main.list.d(activity, mediaItem, eVar, aVar, i));
                l.aG();
                eVar.show();
            }
        }
    }

    public static void a(Context context, List<GroupItem> list, MediaItem mediaItem, b bVar, a<com.sds.android.ttpod.component.d.a.b> aVar) {
        Collection arrayList = new ArrayList(1);
        if (!mediaItem.isOnline()) {
            arrayList.add(mediaItem);
        } else if (mediaItem.getLocalDataSource() != null) {
            MediaItem a = k.a(mediaItem.getLocalDataSource());
            if (a != null) {
                a = MediaStorage.queryMediaItem(d, MediaStorage.GROUP_ID_ALL_LOCAL, a.getID());
                if (a != null) {
                    arrayList.add(a);
                } else {
                    return;
                }
            }
            return;
        }
        a(context, (List) list, arrayList, bVar, (a) aVar);
    }

    public static void a(final Context context, List<GroupItem> list, final Collection<MediaItem> collection, final b bVar, final a<com.sds.android.ttpod.component.d.a.b> aVar) {
        if (context != null) {
            if (collection == null) {
                throw new IllegalArgumentException("mediaItems should not be null");
            }
            int size = (list == null || list.isEmpty()) ? 0 : list.size();
            List arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                GroupItem groupItem = (GroupItem) list.get(i);
                arrayList.add(new com.sds.android.ttpod.component.b.a(groupItem.hashCode(), 0, groupItem.getName()).a(groupItem.getGroupID()));
            }
            final e eVar = new e(context, arrayList, (int) R.string.cancel, null);
            eVar.setTitle((int) R.string.dialog_add_to_group_title);
            eVar.a((int) R.string.create_playlist_add, new a<e>() {
                public void a(e eVar) {
                    eVar.dismiss();
                    f.b(context, null, new a<com.sds.android.ttpod.component.d.a.b>(this) {
                        final /* synthetic */ AnonymousClass9 a;

                        {
                            this.a = r1;
                        }

                        public void a(com.sds.android.ttpod.component.d.a.b bVar) {
                            String charSequence = bVar.c(0).e().toString();
                            String str = (String) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_GROUP, charSequence), String.class);
                            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM_LIST, str, collection));
                            f.a(context.getString(R.string.add_to_group_success, new Object[]{charSequence}));
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        }
                    });
                }
            });
            eVar.show();
            eVar.a(new b() {
                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    String str = (String) aVar.h();
                    l.aK();
                    com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_MEDIA_ITEM_LIST, str, collection));
                    SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_RIGHT_MENU_ADD_TO_OK.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue());
                    sUserEvent.setPageParameter(true);
                    sUserEvent.append(AbsMediaListFragment.KEY_GROUP_ID, str).post();
                    f.a(context.getString(R.string.add_to_group_success, new Object[]{aVar.d()}));
                    if (bVar != null) {
                        bVar.a(aVar, i);
                    }
                    eVar.dismiss();
                }
            });
        }
    }

    public static void a(Context context, final MediaItem mediaItem, final String str, final a<i> aVar) {
        if (context != null) {
            if (mediaItem == null || str == null) {
                throw new IllegalArgumentException("mediaItems or groupID should not be null");
            }
            CharSequence string;
            if (TTTextUtils.isValidateMediaString(mediaItem.getArtist())) {
                string = context.getString(R.string.media_delete_single_with_artist, new Object[]{mediaItem.getArtist(), mediaItem.getTitle()});
            } else {
                string = context.getString(R.string.media_delete_single, new Object[]{mediaItem.getTitle()});
            }
            a(context, context.getString(R.string.media_delete_title), string, (int) R.string.delete, new a<i>() {
                public void a(i iVar) {
                    if (str.equals(MediaStorage.GROUP_ID_FAV)) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_FAVORITE_MEDIA_ITEM, mediaItem, Boolean.valueOf(iVar.b())));
                    } else {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM, str, mediaItem, Boolean.valueOf(iVar.b())));
                    }
                    if (aVar != null) {
                        aVar.a(iVar);
                    }
                    new ArrayList().add(mediaItem);
                    if (iVar.d()) {
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_PRIVATE_EFFECT_LIST, r0));
                    }
                    if (iVar.c()) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_PICTURE, r0));
                    }
                    if (iVar.g()) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_LYRIC, r0));
                    }
                    SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", com.sds.android.ttpod.framework.a.b.r.ACTION_RIGHT_MENU_DIALOG_DELETE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue());
                    sUserEvent.setPageParameter(true);
                    sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getLocalDataSource()).append(AbsMediaListFragment.KEY_GROUP_ID, str).post();
                }
            });
        }
    }

    public static void a(Context context, final Collection<MediaItem> collection, final String str, final a<i> aVar) {
        if (context != null) {
            CharSequence string;
            com.sds.android.sdk.lib.util.d.a((Collection) collection, "mediaItems");
            com.sds.android.sdk.lib.util.d.a((Object) str, "groupID");
            if (TTTextUtils.isValidateMediaString(((MediaItem) collection.iterator().next()).getArtist())) {
                string = context.getString(R.string.media_delete_multi_with_artist, new Object[]{((MediaItem) collection.iterator().next()).getArtist(), ((MediaItem) collection.iterator().next()).getTitle(), Integer.valueOf(collection.size())});
            } else {
                string = context.getString(R.string.media_delete_multi, new Object[]{((MediaItem) collection.iterator().next()).getTitle(), Integer.valueOf(collection.size())});
            }
            a(context, context.getString(R.string.media_delete_title), string, (int) R.string.delete, new a<i>() {
                public void a(i iVar) {
                    if (str.equals(MediaStorage.GROUP_ID_FAV)) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_FAVORITE_MEDIA_ITEM_LIST, collection, Boolean.valueOf(iVar.b())));
                    } else {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_MEDIA_ITEM_LIST, str, collection, Boolean.valueOf(iVar.b())));
                    }
                    if (iVar.d()) {
                        f.b(collection);
                    }
                    if (aVar != null) {
                        aVar.a(iVar);
                    }
                    if (iVar.c()) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_PICTURE, collection));
                    }
                    if (iVar.g()) {
                        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_LYRIC, collection));
                    }
                }
            });
        }
    }

    private static void b(Collection<MediaItem> collection) {
        new ArrayList().addAll(collection);
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_PRIVATE_EFFECT_LIST, r0));
    }

    public static void a(Activity activity, final String str, final b bVar) {
        if (activity != null) {
            int i = (MediaStorage.GROUP_ID_EFFECT_LOCAL.equals(str) || str.startsWith(MediaStorage.GROUP_ID_EFFECT_ONLINE) || MediaStorage.GROUP_ID_FAV.equals(str)) ? 1 : 0;
            i = i != 0 ? 14 : 10;
            a((Context) activity, activity.getString(R.string.media_order_title), new d[]{new d(7, R.string.order_as_title), new d(8, R.string.order_as_artist), new d(9, R.string.order_as_album), new d(13, R.string.order_as_genre), new d(11, R.string.order_as_file_name), new d(i, R.string.order_as_add_time)}, e.get(com.sds.android.ttpod.framework.storage.environment.b.l(str).hashCode()), new b() {
                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    f.a(str, aVar.g());
                    if (bVar != null) {
                        bVar.a(aVar, i);
                    }
                }
            }, null);
        }
    }

    public static void a(String str, int i) {
        com.sds.android.ttpod.framework.storage.environment.b.l(str);
        String str2 = null;
        switch (i) {
            case 7:
                str2 = "title_key";
                break;
            case 8:
                str2 = "artist_key";
                break;
            case 9:
                str2 = "album_key";
                break;
            case 10:
                str2 = MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC;
                break;
            case 11:
                str2 = MediaStorage.MEDIA_ORDER_BY_FILE_NAME;
                break;
            case 13:
                str2 = "genre_key";
                break;
            case 14:
                str2 = MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC;
                break;
        }
        com.sds.android.ttpod.framework.storage.environment.b.a(str, str2);
    }

    public static void a(View view, int i, final com.sds.android.ttpod.fragment.main.list.a.a aVar) {
        if (view != null) {
            com.sds.android.sdk.lib.util.d.b(c, "mEditPanel");
            com.sds.android.sdk.lib.util.d.a((Object) aVar, "editRequestListener");
            View inflate = View.inflate(view.getContext(), R.layout.list_media_edit_footer, null);
            inflate.setClickable(true);
            OnClickListener anonymousClass15 = new OnClickListener() {
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.btn_send:
                            t.a(com.sds.android.ttpod.framework.a.b.r.ACTION_BATCH_OPERATE_SEND, com.sds.android.ttpod.framework.a.b.s.PAGE_NONE);
                            aVar.onSendToRequested();
                            return;
                        case R.id.btn_remove:
                            t.a(com.sds.android.ttpod.framework.a.b.r.ACTION_BATCH_OPERATE_REMOVE, com.sds.android.ttpod.framework.a.b.s.PAGE_NONE);
                            aVar.onRemoveRequested();
                            return;
                        case R.id.btn_add:
                            t.a(com.sds.android.ttpod.framework.a.b.r.ACTION_BATCH_OPERATE_ADD, com.sds.android.ttpod.framework.a.b.s.PAGE_NONE);
                            aVar.onAddToRequested();
                            return;
                        default:
                            return;
                    }
                }
            };
            inflate.findViewById(R.id.btn_remove).setOnClickListener(anonymousClass15);
            inflate.findViewById(R.id.btn_add).setOnClickListener(anonymousClass15);
            inflate.findViewById(R.id.btn_send).setOnClickListener(anonymousClass15);
            c = new PopupWindow(inflate, view.getWidth(), view.getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height), false);
            c.setAnimationStyle(R.style.DialogWindowAnim);
            c.update();
            c.showAtLocation(view, 80, 0, i);
        }
    }

    public static void a(View view, int i, final com.sds.android.ttpod.component.soundsearch.b.a aVar) {
        if (view != null) {
            View inflate = View.inflate(view.getContext(), R.layout.list_theme_edit_footer, null);
            inflate.setClickable(true);
            inflate.findViewById(R.id.btn_remove).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    aVar.onRemoveRequested();
                }
            });
            com.sds.android.ttpod.framework.modules.theme.c.a(inflate, ThemeElement.SUB_BAR_BACKGROUND);
            com.sds.android.ttpod.framework.modules.theme.c.a(inflate.findViewById(R.id.delete_textView), ThemeElement.SUB_BAR_TEXT);
            v.a((IconTextView) inflate.findViewById(R.id.delete_iconTextView), ThemeElement.SUB_BAR_TEXT);
            c = new PopupWindow(inflate, view.getWidth(), view.getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height), false);
            c.update();
            c.showAtLocation(view, 80, 0, i);
        }
    }

    public static boolean b() {
        return c != null && c.isShowing();
    }

    public static void c() {
        if (b()) {
            c.dismiss();
            c = null;
        }
    }

    public static void a(PopupWindow popupWindow) {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
