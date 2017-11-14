package com.sds.android.ttpod.framework.modules.f;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* PostUtils */
public class f {
    public static Post a(Post post) {
        Post repostedMsg = post != null ? post.getRepostedMsg() : null;
        while (repostedMsg != null) {
            post = repostedMsg;
            repostedMsg = repostedMsg.getRepostedMsg();
        }
        return post;
    }

    public static List<MediaItem> b(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        Post a = a(post);
        int type = a.getType();
        List<MediaItem> arrayList = new ArrayList();
        if (d.SINGLE_SONG.value() == type) {
            a(arrayList, a);
        } else if (d.DJ.value() == type) {
            a(arrayList, a);
            b(arrayList, a);
        } else if (d.SONG_LIST.value() == type) {
            b(arrayList, a);
        }
        return arrayList;
    }

    private static void a(List<MediaItem> list, Post post) {
        OnlineMediaItem mediaItem = post.getMediaItem();
        if (mediaItem != null) {
            list.add(k.a(mediaItem));
        }
    }

    private static void b(List<MediaItem> list, Post post) {
        ArrayList songList = post.getSongList();
        if (songList != null) {
            Iterator it = songList.iterator();
            while (it.hasNext()) {
                OnlineMediaItem onlineMediaItem = (OnlineMediaItem) it.next();
                if (onlineMediaItem != null) {
                    list.add(k.a(onlineMediaItem));
                }
            }
        }
    }

    public static List<Long> c(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        Post a = a(post);
        int type = a.getType();
        List<Long> arrayList = new ArrayList();
        if (d.SINGLE_SONG.value() == type) {
            c(arrayList, a);
        } else if (d.DJ.value() == type) {
            c(arrayList, a);
            d(arrayList, a);
        } else if (d.SONG_LIST.value() == type) {
            d(arrayList, a);
        }
        return arrayList;
    }

    private static void c(List<Long> list, Post post) {
        OnlineMediaItem mediaItem = post.getMediaItem();
        if (mediaItem != null) {
            list.add(Long.valueOf(mediaItem.getSongId()));
        }
    }

    private static void d(List<Long> list, Post post) {
        List<OnlineMediaItem> songList = post.getSongList();
        if (songList != null) {
            for (OnlineMediaItem onlineMediaItem : songList) {
                if (onlineMediaItem != null) {
                    list.add(Long.valueOf(onlineMediaItem.getSongId()));
                }
            }
        }
    }

    public static String d(Post post) {
        ArrayList picList = post.getPicList();
        if (picList != null && !picList.isEmpty()) {
            return (String) picList.get(0);
        }
        if (post.getType() == d.SINGLE_SONG.value()) {
            return a(post.getMediaItem().getArtistId());
        }
        return "";
    }

    private static String a(long j) {
        return "http://pic.ttpod.cn/singerpic" + "/" + (j / 255) + "/" + (j / 7) + "/" + j + "_320.jpg";
    }

    public static String e(Post post) {
        String title = post.getMediaItem().getTitle();
        return (post.getType() != d.SINGLE_SONG.value() || "".equals(title)) ? post.getSongListName() : title;
    }
}
