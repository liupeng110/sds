package com.sds.android.ttpod.component.c;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem.Url;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.e;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.n;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import junit.framework.Assert;

/* DownloadMenuHandler */
public final class b {
    private static final String a = b.class.getSimpleName();
    private static SparseArray<AudioQuality> b = new SparseArray();
    private static final String[] c = BaseApplication.e().getResources().getStringArray(R.array.song_quality_list);
    private Activity d;
    private List<MediaItem> e;
    private String f;

    static {
        b.put(0, AudioQuality.LOSSLESS);
        b.put(1, AudioQuality.SUPER);
        b.put(2, AudioQuality.STANDARD);
        b.put(3, AudioQuality.COMPRESSED);
    }

    public b(Activity activity) {
        Assert.assertNotNull(activity);
        this.d = activity;
    }

    public void a(List<MediaItem> list) {
        if (!j.a(list)) {
            List a = a((List) list, 4);
            if (a.size() == 0) {
                f.a((int) R.string.no_copyright_notification);
                return;
            }
            this.e = a(a, 8);
            if (this.e.size() <= 0) {
                f.a("已经下载了");
            } else if (c.e()) {
                f.a(this.d, b(), this.d.getString(R.string.title_choose_download_playlist, new Object[]{Integer.valueOf(this.e.size())}), a());
            } else {
                f.a((int) R.string.network_unavailable);
            }
        }
    }

    private List<MediaItem> a(List<MediaItem> list, int i) {
        List<MediaItem> arrayList = new ArrayList();
        for (MediaItem mediaItem : list) {
            if (i == 8 && m.a(mediaItem.getLocalDataSource())) {
                arrayList.add(mediaItem);
            } else if (i == 4 && mediaItem.hasCopyright()) {
                arrayList.add(mediaItem);
            }
        }
        return arrayList;
    }

    public void a(MediaItem mediaItem, String str) {
        if (mediaItem != null) {
            this.e = new ArrayList(1);
            this.e.add(mediaItem);
            this.f = str;
            String extra = mediaItem.getExtra();
            if (extra != null && !this.d.isFinishing() && !new a(this.d, mediaItem).a(11)) {
                OnlineMediaItem onlineMediaItem = (OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(extra, OnlineMediaItem.class);
                if (onlineMediaItem == null) {
                    g.c(a, "cast to onlineMediaItem is null");
                    return;
                }
                AudioQuality P = com.sds.android.ttpod.framework.storage.environment.b.P();
                if (P != AudioQuality.UNDEFINED) {
                    a(onlineMediaItem, mediaItem, a(onlineMediaItem, P));
                } else {
                    a(mediaItem, onlineMediaItem);
                }
                a(mediaItem, P);
            }
        }
    }

    private void a(MediaItem mediaItem, AudioQuality audioQuality) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_DOWNLOAD.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
        sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID());
        if (audioQuality != AudioQuality.UNDEFINED) {
            sUserEvent.append("quality_type", Integer.valueOf(audioQuality.ordinal()));
        }
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }

    private com.sds.android.ttpod.component.b.a.b a() {
        return new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(a aVar, int i) {
                AudioQuality a = this.a.a(i);
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_DOWNLOAD_ALL_SELECT_QUALITY.getValue(), 0, 0);
                sUserEvent.setPageParameter(true);
                sUserEvent.append("quality_type", Integer.valueOf(a.ordinal())).post();
                List a2 = this.a.a(this.a.e, a);
                com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ASYN_ADD_DOWNLOAD_TASK_LIST, a2, Boolean.valueOf(false)));
                f.a(this.a.d.getString(R.string.toast_download_songs, new Object[]{Integer.valueOf(a2.size())}));
            }
        };
    }

    private AudioQuality a(int i) {
        return (AudioQuality) b.get(i);
    }

    private List<a> b() {
        List<a> arrayList = new ArrayList();
        for (int i = 0; i < c.length; i++) {
            if (!c(i)) {
                a aVar = new a(i, 0, b(i));
                a(aVar);
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private void a(a aVar) {
        switch (aVar.g()) {
            case 0:
                aVar.b((int) R.string.icon_text_wusun);
                aVar.a(a.a.TITLE_ICON);
                return;
            case 1:
                aVar.b((int) R.string.icon_text_gaozhi);
                aVar.a(a.a.TITLE_ICON);
                return;
            default:
                aVar.a(a.a.NO_ICON);
                return;
        }
    }

    private String b(int i) {
        long longValue = ((Long) com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_TOTAL_DOWNLOAD_FILE_SIZE, this.e, a(i)), Long.class)).longValue();
        return String.format(c[i], new Object[]{Float.valueOf(((float) longValue) / 1048576.0f)});
    }

    private boolean c(int i) {
        if (this.e.size() > 1) {
            return false;
        }
        OnlineMediaItem onlineMediaItem = (OnlineMediaItem) com.sds.android.sdk.lib.util.f.a(((MediaItem) this.e.get(0)).getExtra(), OnlineMediaItem.class);
        if (onlineMediaItem == null) {
            return true;
        }
        if (i == 0) {
            boolean z = onlineMediaItem.getLLUrls() == null || onlineMediaItem.getLLUrls().isEmpty();
            return z;
        } else if (i != 1) {
            return false;
        } else {
            Url b = n.b(onlineMediaItem, AudioQuality.SUPER);
            if (b == null || b.getBitrate() < AudioQuality.bitrateRange(AudioQuality.SUPER)[0]) {
                return true;
            }
            return false;
        }
    }

    private List<DownloadTaskInfo> a(List<MediaItem> list, AudioQuality audioQuality) {
        ArrayList arrayList = new ArrayList(list);
        List<DownloadTaskInfo> arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (m.a(((MediaItem) arrayList.get(size)).getLocalDataSource())) {
                DownloadTaskInfo a = e.a((MediaItem) arrayList.get(size), audioQuality);
                if (a != null) {
                    if (com.sds.android.sdk.lib.util.e.a(a.getSavePath())) {
                        k.a((MediaItem) arrayList.get(size), a.getSavePath());
                        MediaStorage.updateMediaItem(this.d, (MediaItem) arrayList.get(size));
                        arrayList.remove(size);
                    } else {
                        a.setAlibabaOrigin(d.k.a(true, a.getFileFormat(), String.valueOf(a.getSingerID()), a.getScm()).a());
                        arrayList2.add(a);
                    }
                }
            } else {
                arrayList.remove(size);
            }
        }
        Collections.reverse(arrayList2);
        return arrayList2;
    }

    public void a(final MediaItem mediaItem, final OnlineMediaItem onlineMediaItem) {
        List a = a(onlineMediaItem);
        final View inflate = this.d.getLayoutInflater().inflate(R.layout.dialog_list_footer_remember, null, false);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b b;

            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.dialog_check_box);
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        f.a(this.d, a, this.d.getString(R.string.choose_music_download_quality), new com.sds.android.ttpod.component.b.a.b(this) {
            final /* synthetic */ b d;

            public void a(a aVar, int i) {
                if (inflate != null && ((CheckBox) inflate.findViewById(R.id.dialog_check_box)).isChecked()) {
                    com.sds.android.ttpod.framework.storage.environment.b.d(AudioQuality.quality(((com.sds.android.ttpod.component.b) aVar.h()).b().getBitrate()));
                }
                com.sds.android.ttpod.component.b bVar = (com.sds.android.ttpod.component.b) aVar.h();
                Url b = bVar.b();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_DOWNLOAD_SELECT.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
                sUserEvent.setPageParameter(true);
                sUserEvent.append("quality_type", Integer.valueOf(AudioQuality.quality(b.getBitrate()).ordinal())).append(MediasColumns.SONG_ID, mediaItem.getSongID()).append(Downloads.COLUMN_URI, b.getUrl()).post();
                if (com.sds.android.ttpod.component.b.a.MEDIA == bVar.a()) {
                    this.d.a(onlineMediaItem, mediaItem, b);
                }
            }
        }, inflate);
    }

    private void a(OnlineMediaItem onlineMediaItem, MediaItem mediaItem, Url url) {
        if (url == null) {
            g.c(a, "downloadSingleSong url is null");
            return;
        }
        DownloadTaskInfo a = e.a(mediaItem.getGroupID(), url.getUrl(), n.a(onlineMediaItem, url), Long.valueOf(onlineMediaItem.getSongId()), onlineMediaItem.getTitle(), DownloadTaskInfo.TYPE_AUDIO, Boolean.valueOf(true), this.f);
        a.setAudioQuality(AudioQuality.quality(url.getBitrate()).toString());
        a.setTag(mediaItem);
        a.setPosition(p.f());
        a.setListId(com.sds.android.ttpod.framework.a.b.j.b());
        a.setListType(com.sds.android.ttpod.framework.a.b.j.a());
        a.setSongType(e.a(url));
        a.setAlibabaOrigin(d.k.a(false, url.getFormat(), String.valueOf(onlineMediaItem.getArtistId()), onlineMediaItem.getScm()).a());
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, a));
    }

    private Url a(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        Collection downloadUrls = onlineMediaItem.getDownloadUrls();
        Collection lLUrls = onlineMediaItem.getLLUrls();
        List<Url> arrayList = new ArrayList();
        if (downloadUrls != null) {
            arrayList.addAll(downloadUrls);
        }
        if (lLUrls != null) {
            arrayList.addAll(lLUrls);
        }
        if (arrayList.isEmpty()) {
            g.c(a, "mediaDownloadUrls is empty, the song may offline");
            return null;
        }
        int[] bitrateRange = AudioQuality.bitrateRange(audioQuality);
        for (Url url : arrayList) {
            if (url.getBitrate() > bitrateRange[0] && url.getBitrate() <= bitrateRange[1]) {
                return url;
            }
        }
        return (Url) arrayList.get(arrayList.size() - 1);
    }

    private List<a> a(OnlineMediaItem onlineMediaItem) {
        Collection downloadUrls = onlineMediaItem.getDownloadUrls();
        Collection lLUrls = onlineMediaItem.getLLUrls();
        List<Url> arrayList = new ArrayList();
        if (downloadUrls != null) {
            arrayList.addAll(downloadUrls);
        }
        if (lLUrls != null) {
            arrayList.addAll(lLUrls);
        }
        List<a> arrayList2 = new ArrayList();
        for (Url url : arrayList) {
            a aVar = new a(url.getUrl().hashCode(), 0, url.getTypeDescription() + " " + url.getSize().replaceAll(VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY, ""));
            if (AudioQuality.quality(url.getBitrate()) == AudioQuality.LOSSLESS) {
                aVar.b((int) R.string.icon_text_wusun);
                aVar.a(a.a.TITLE_ICON);
            } else if (AudioQuality.quality(url.getBitrate()) == AudioQuality.SUPER) {
                aVar.a(a.a.TITLE_ICON);
                aVar.b((int) R.string.icon_text_gaozhi);
            } else {
                aVar.a(a.a.NO_ICON);
            }
            aVar.a(new com.sds.android.ttpod.component.b(com.sds.android.ttpod.component.b.a.MEDIA, url));
            arrayList2.add(aVar);
        }
        Collections.reverse(arrayList2);
        return arrayList2;
    }
}
