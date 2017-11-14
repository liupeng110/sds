package com.sds.android.ttpod.framework.modules.f;

import com.sds.android.cloudapi.ttpod.a.ae;
import com.sds.android.cloudapi.ttpod.a.d;
import com.sds.android.cloudapi.ttpod.a.k;
import com.sds.android.cloudapi.ttpod.a.n;
import com.sds.android.cloudapi.ttpod.a.q;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.a.u;
import com.sds.android.cloudapi.ttpod.a.v;
import com.sds.android.cloudapi.ttpod.a.y;
import com.sds.android.cloudapi.ttpod.data.Comment;
import com.sds.android.cloudapi.ttpod.data.MessageCollectItem;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.result.CircleFirstPublishListResult;
import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.a.t;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.e;
import com.sds.android.ttpod.framework.modules.f;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* MusicCircleModule */
public final class c extends b {
    public static final long TUID_TTPOD = 204713344;

    protected com.sds.android.ttpod.framework.modules.c id() {
        return com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(a.REQUEST_MUSIC_POSTER_LIST, i.a(cls, "requestMusicPosterList", new Class[0]));
        map.put(a.REQUEST_NEW_SONG_PUBLISH_LIST, i.a(cls, "requestNewSongPublishList", new Class[0]));
        map.put(a.REQUEST_MORE_NEW_SONG_PUBLISH_LIST, i.a(getClass(), "requestMoreNewSongPublishList", Integer.class, Integer.class));
        map.put(a.REQUEST_NEW_SONG_CATEGORY_PUBLISH_LIST, i.a(getClass(), "requestNewSongCategoryPublishList", new Class[0]));
        map.put(a.REQUEST_NEW_ALBUM_PUBLISH_LIST, i.a(getClass(), "requestNewAlbumPublishList", Integer.class, Integer.class));
        map.put(a.REQUEST_SHACK_USERS, i.a(cls, "requestShakeUsers", Float.class, Float.class, String.class));
        map.put(a.REQUEST_ALIKE_USERS, i.a(cls, "requestAlikeUsers", String.class));
        map.put(a.REQUEST_STAR_USERS_BY_RANK, i.a(cls, "requestStarUsersByRank", Integer.class, Integer.class, Integer.class, String.class));
        map.put(a.REQUEST_STAR_USERS_BY_CATEGORY, i.a(cls, "requestStarUsersByCategory", Integer.class, Integer.class, Integer.class, String.class));
        map.put(a.REQUEST_STAR_CATEGORIES, i.a(cls, "requestStarCategories", String.class));
        map.put(a.MUSICCIRCLE_SEARCH, i.a(cls, "search", String.class, String.class));
        map.put(a.FOLLOW_FRIEND, i.a(cls, "followFriend", Long.class, String.class));
        map.put(a.UNFOLLOW_FRIEND, i.a(cls, "unFollowFriend", Long.class, String.class));
        map.put(a.REQUEST_FOLLOWING_FRIEND_IDS, i.a(cls, "requestFollowingFriendIds", Long.class, String.class));
        map.put(a.REQUEST_FOLLOWING_FRIENDS, i.a(cls, "requestFollowingFriends", Long.class, Integer.class, Integer.class, Long.class, String.class));
        map.put(a.REQUEST_FOLLOWER_FRIENDS, i.a(cls, "requestFollowerFriends", Long.class, Integer.class, Integer.class, Long.class, String.class));
        map.put(a.REQUEST_USER_INFO_BY_IDS, i.a(cls, "requestFollowerFriends", Collection.class, String.class));
        map.put(a.REQUEST_SYSTEM_NOTICES, i.a(cls, "requestSystemNotices", Long.class, Integer.class, String.class));
        map.put(a.POST_COMMENT, i.a(cls, "postComment", Long.class, String.class, Long.class, Long.class, String.class));
        map.put(a.DELETE_COMMENT, i.a(cls, "deleteComment", Long.class, Comment.class, String.class));
        map.put(a.RE_POST, i.a(cls, "rePost", Long.class, Long.class, String.class, String.class));
        map.put(a.REQUEST_PRIVATE_MESSAGES, i.a(cls, "requestPrivateMessages", Long.class, Integer.class, String.class));
        map.put(a.QUERY_PRIVATE_MESSAGES_CONTENT, i.a(cls, "requestPrivateMessagesContent", Long.class, Long.class, Integer.class, String.class));
        map.put(a.DELETE_PRIVATE_MESSAGE, i.a(cls, "deletePrivateMessage", String.class, String.class));
        map.put(a.SEND_PRIVATE_MESSAGE, i.a(cls, "sendPrivateMessage", Long.class, String.class, String.class));
        map.put(a.DELETE_PRIVATE_MESSAGES, i.a(cls, "deletePrivateMessages", Long.class, String.class));
        map.put(a.REQUEST_USER_POST_IDS, i.a(cls, "requestUserPostIds", Long.class, String.class));
        map.put(a.REQUEST_TIMELINE_USER_POST_IDS, i.a(cls, "requestTimelinePostIds", String.class));
        map.put(a.REQUEST_COMMENT_IDS_BY_POST_ID, i.a(cls, "requestCommentsByPostId", Long.class, String.class));
        map.put(a.REQUEST_COMMENT_INFOS_BY_IDS, i.a(cls, "requestCommentInfosByIds", Collection.class, String.class));
        map.put(a.REQUEST_POST_SONG_BY_IDS, i.a(cls, "requestPostSongByIds", List.class, String.class));
        map.put(a.REQUEST_POST_DETAIL_BY_ID, i.a(cls, "requestPostDetailById", Collection.class, String.class));
        map.put(a.REQUEST_ALBUM_DETAIL_BY_ID, i.a(cls, "requestAlbumDetailById", Long.class, String.class));
        map.put(a.REQUEST_POST_INFOS_BY_ID, i.a(cls, "requestPostInfosById", Collection.class, String.class));
        map.put(a.REQUEST_CELEBRITY_POSTS, i.a(cls, "requestCelebrityPosts", Long.class));
        map.put(a.REQUEST_RECOMMEND_CELEBRATE_POST_IDS, i.a(cls, "requestRecommendCelebratePostIds", String.class));
        map.put(a.REQUEST_FIRST_PUBLISH_CELEBRATE_POST_IDS, i.a(cls, "requestFirstPublishCelebratePostIds", String.class));
        map.put(a.REQUEST_POSTS_BY_CATEGORY_ID, i.a(cls, "requestPostsByCategoryId", Long.class, Integer.class, String.class));
        map.put(a.REQUEST_REPOST_NOTICES, i.a(cls, "requestRePostNotices", Integer.class, Integer.class, String.class));
        map.put(a.REQUEST_COMMENT_NOTICES, i.a(cls, "requestCommentNotices", Integer.class, Integer.class, String.class));
        map.put(a.REQUEST_NEW_FOLLOWER_NOTICES, i.a(cls, "requestNewFollowerNotices", String.class));
        map.put(a.DELETE_NOTICE, i.a(cls, "deleteNotice", Notice.class, String.class));
        map.put(a.ADD_FAVORITE_POSTS, i.a(cls, "addFavoritePosts", List.class, String.class));
        map.put(a.REMOVE_FAVORITE_POSTS, i.a(cls, "removeFavoritePosts", List.class, String.class));
        map.put(a.IS_FAVORITE_POST, i.a(cls, "isFavoritePost", Long.class));
        map.put(a.REQUEST_FAVORITE_POSTS, i.a(cls, "requestFavoritePosts", String.class));
        map.put(a.IS_FOLLOWED, i.a(cls, "isFollowed", Long.class));
        map.put(a.SET_LOGIN_UID, i.a(cls, "setLoginUid", Long.class));
        map.put(a.CHANGE_POST_REPLY_COUNT, i.a(cls, "changePostReplyCount", Post.class));
        map.put(a.CHANGE_POST_REPOST_COUNT, i.a(cls, "changePostRepostCount", Post.class));
        map.put(a.REQUEST_FAVORITE_SONG_LIST_POSTS, i.a(cls, "requestFavoriteSongListPosts", new Class[0]));
        map.put(a.ADD_POST_TO_MEDIA_GROUP, i.a(cls, "addPostToMediaGroup", Post.class));
        map.put(a.ADD_POSTS_TO_MEDIA_GROUP, i.a(cls, "addPostsToMediaGroup", List.class));
        map.put(a.ADD_LISTENER_COUNT, i.a(cls, "addListenerCount", Long.class));
        map.put(a.REQUEST_PRIVATE_CUSTOM_POSTS, i.a(cls, "requestRecommendPostPosts", Integer.class, Integer.class));
    }

    static <Result extends BaseResult> Result a(o<Result> oVar) {
        int i = 0;
        g.d("MusicCircleModule", "request begin lookNetProblem %s", oVar.b());
        Result g = oVar.g();
        String str = "MusicCircleModule";
        String str2 = "request end lookNetProblem result not null=%b code=%d";
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(g != null);
        if (g != null) {
            i = g.getCode();
        }
        objArr[1] = Integer.valueOf(i);
        g.d(str, str2, objArr);
        if (!g.isSuccess()) {
            com.sds.android.ttpod.framework.a.b.g.g(oVar.b());
        }
        return g;
    }

    public void requestRecommendPostPosts(Integer num, Integer num2) {
        e.a(com.sds.android.cloudapi.ttpod.a.o.a(num.intValue(), num2.intValue()), a.UPDATE_PRIVATE_CUSTOM_POSTS, id(), null);
    }

    public void requestMusicPosterList() {
        e.a(com.sds.android.cloudapi.ttpod.a.o.a(), a.UPDATE_MUSIC_POSTER_LIST, com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE, null);
    }

    public void requestNewSongPublishList() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_NEW_SONG_PUBLISH_LIST, ((CircleFirstPublishListResult) c.a(com.sds.android.cloudapi.ttpod.a.o.b())).getDataList()), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestMoreNewSongPublishList(Integer num, Integer num2) {
        e.a(com.sds.android.cloudapi.ttpod.a.o.b(num.intValue(), num2.intValue()), a.UPDATE_MORE_NEW_SONG_PUBLISH_LIST, id(), null);
    }

    public void requestNewSongCategoryPublishList() {
        e.a(com.sds.android.cloudapi.ttpod.a.o.c(), a.UPDATE_NEW_SONG_CATEGORY_PUBLISH_LIST, id(), null);
    }

    public void requestNewAlbumPublishList(Integer num, Integer num2) {
        e.a(com.sds.android.cloudapi.ttpod.a.o.c(num.intValue(), num2.intValue()), a.UPDATE_NEW_ALBUM_PUBLISH_LIST, id(), null);
    }

    private String f() {
        return com.sds.android.ttpod.framework.storage.environment.b.aw().getToken();
    }

    public void requestShakeUsers(final Float f, final Float f2, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SHAKE_RESULT, c.a(y.a(this.d.f(), f.floatValue(), f2.floatValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestAlikeUsers(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_ALIKE_USER_LIST, c.a(ae.a(this.b.f())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestStarUsersByRank(Integer num, Integer num2, Integer num3, String str) {
        final Integer num4 = num;
        final Integer num5 = num2;
        final Integer num6 = num3;
        final String str2 = str;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c e;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_STAR_USER_LIST_BY_RANK, c.a(d.b(num4.intValue(), num5.intValue(), num6.intValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestStarCategories(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_STAR_CATEGORIES_LIST, c.a(d.a()), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestStarUsersByCategory(Integer num, Integer num2, Integer num3, String str) {
        final Integer num4 = num;
        final Integer num5 = num2;
        final Integer num6 = num3;
        final String str2 = str;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c e;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_STAR_USER_lIST_BY_CATEGORY, c.a(d.a(num4.intValue(), num5.intValue(), num6.intValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void search(final String str, final String str2) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SEARCH_RESULT, c.a(ae.a(this.c.f(), str)), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void followFriend(final Long l, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FOLLOW_FRIEND, b.a().a(l.longValue()), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void unFollowFriend(final Long l, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_UNFOLLOW_FRIEND, b.a().b(l.longValue()), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public Boolean isFollowed(Long l) {
        if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
            return Boolean.valueOf(false);
        }
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                b.a().a(new b.a(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SYNC_FOLLOWING_FINISHED, new Object[0]), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                    }
                });
            }
        });
        return Boolean.valueOf(b.a().c(l.longValue()));
    }

    public void requestFollowingFriendIds(final Long l, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FOLLOWING_FRIEND_ID_LIST, c.a(k.a(l.longValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFollowingFriends(Long l, Integer num, Integer num2, Long l2, String str) {
        final Long l3 = l;
        final Integer num3 = num;
        final Integer num4 = num2;
        final Long l4 = l2;
        final String str2 = str;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c f;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FOLLOWING_FRIEND_LIST, c.a(k.a(l3.longValue(), num3.intValue(), num4.intValue(), l4.longValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFollowerFriends(Long l, Integer num, Integer num2, Long l2, String str) {
        final Long l3 = l;
        final Integer num3 = num;
        final Integer num4 = num2;
        final Long l4 = l2;
        final String str2 = str;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c f;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FOLLOWER_FRIENDS, c.a(k.b(l3.longValue(), num3.intValue(), num4.intValue(), l4.longValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFollowerFriends(final Collection<Long> collection, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FOLLOWER_FRIEND_LIST_BY_IDS, c.a(k.a(collection)), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestSystemNotices(final Long l, final Integer num, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SYSTEM_NOTICE_LIST, c.a(q.a(this.d.f(), l.longValue(), num.intValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestRePostNotices(Integer num, Integer num2, String str) {
        a(e.REPOST, num, num2, str);
    }

    public void requestCommentNotices(Integer num, Integer num2, String str) {
        a(e.COMMENT, num, num2, str);
    }

    private void a(e eVar, Integer num, Integer num2, String str) {
        final e eVar2 = eVar;
        final Integer num3 = num;
        final Integer num4 = num2;
        final String str2 = str;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c e;

            public void run() {
                switch (eVar2) {
                    case COMMENT:
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_COMMENT_NOTICE_LIST, c.a(q.a(this.e.f(), e.COMMENT.value(), num3.intValue(), num4.intValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                        return;
                    case REPOST:
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_REPOST_NOTICE_LIST, c.a(q.a(this.e.f(), e.REPOST.value(), num3.intValue(), num4.intValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void requestNewFollowerNotices(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_NEW_FOLLOWER_NOTICE_LIST, c.a(q.b(this.b.f())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPrivateMessages(final Long l, final Integer num, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_PRIVATE_MESSAGE_LIST, c.a(v.a(this.d.f(), l.longValue(), num.intValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPrivateMessagesContent(Long l, Long l2, Integer num, String str) {
        final Long l3 = l;
        final Long l4 = l2;
        final Integer num2 = num;
        final String str2 = str;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c e;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_PRIVATE_MESSAGE_CONTEXT_LIST, c.a(v.a(this.e.f(), l3.longValue(), l4.longValue(), num2.intValue())), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void deletePrivateMessage(final String str, final String str2) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_DELETE_PRIVATE_MESSAGE, c.a(v.a(this.c.f(), str)), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void sendPrivateMessage(final Long l, final String str, final String str2) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SEND_PRIVATE_MESSAGE, c.a(v.a(this.d.f(), l.longValue(), str)), str2), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void deletePrivateMessages(final Long l, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_DELETE_PRIVATE_MESSAGE_LIST, c.a(v.a(this.c.f(), l.longValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public com.sds.android.ttpod.framework.base.d postComment(Long l, String str, Long l2, Long l3, String str2) {
        String trim = str.trim();
        if (trim.length() == 0) {
            return new com.sds.android.ttpod.framework.base.d(com.sds.android.ttpod.framework.base.e.ErrArgument, "请输入有效评论");
        }
        if (t.a(sContext).a(trim)) {
            return new com.sds.android.ttpod.framework.base.d(com.sds.android.ttpod.framework.base.e.ErrArgument, "内容含有敏感词，提交失败");
        }
        final Long l4 = l;
        final String str3 = str;
        final Long l5 = l2;
        final Long l6 = l3;
        final String str4 = str2;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c f;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_COMMENT_POSTED, c.a(u.a(this.f.f(), l4.longValue(), str3, l5.longValue(), l6.longValue())), str4), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
        return new com.sds.android.ttpod.framework.base.d(com.sds.android.ttpod.framework.base.e.ErrNone, "Success");
    }

    public void deleteComment(final Long l, final Comment comment, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_COMMENT_DELETED, c.a(u.a(this.d.f(), l.longValue(), comment.getId())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void rePost(Long l, Long l2, String str, String str2) {
        final Long l3 = l;
        final Long l4 = l2;
        final String str3 = str;
        final String str4 = str2;
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c e;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_RE_POSTED, c.a(u.a(this.e.f(), l3.longValue(), l4.longValue(), str3)), str4), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestUserPostIds(final Long l, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_USER_POST_ID_LIST, c.a(u.b(this.c.f(), l.longValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestTimelinePostIds(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_TIMELINE_USER_POST_IDS, c.a(u.b(this.b.f())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestCommentsByPostId(final Long l, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_COMMENT_ID_LIST_BY_POST_ID, c.a(u.a("", l.longValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestCommentInfosByIds(final Collection collection, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_COMMENT_INFO_LIST_BY_ID_LIST, c.a(u.a(this.c.f(), collection)), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPostSongByIds(List<Long> list, String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.aQ()) {
            e.a(r.c(list), a.UPDATE_POST_SONG_BY_IDS, id(), new f<OnlineMediaItemsResult, com.sds.android.ttpod.framework.modules.b>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public com.sds.android.ttpod.framework.modules.b a(OnlineMediaItemsResult onlineMediaItemsResult) {
                    return this.a.a(onlineMediaItemsResult);
                }
            }, str);
        } else {
            e.a(r.a((Collection) list), a.UPDATE_POST_SONG_BY_IDS, id(), new f<OnlineMediaItemsResult, com.sds.android.ttpod.framework.modules.b>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public com.sds.android.ttpod.framework.modules.b a(OnlineMediaItemsResult onlineMediaItemsResult) {
                    return this.a.a(onlineMediaItemsResult);
                }
            }, str);
        }
    }

    private com.sds.android.ttpod.framework.modules.b a(OnlineMediaItemsResult onlineMediaItemsResult) {
        com.sds.android.ttpod.framework.modules.b bVar = new com.sds.android.ttpod.framework.modules.b();
        bVar.a(a(onlineMediaItemsResult.getDataList()));
        bVar.a(onlineMediaItemsResult.getExtra());
        bVar.setCode(onlineMediaItemsResult.getCode());
        bVar.setMessage(onlineMediaItemsResult.getMessage());
        return bVar;
    }

    private ArrayList a(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof OnlineMediaItem) {
                arrayList2.add(com.sds.android.ttpod.framework.a.k.a((OnlineMediaItem) next));
            }
        }
        return arrayList2;
    }

    public void requestPostDetailById(Collection collection, String str) {
        e.a(u.a(collection), a.UPDATE_POST_DETAIL_BY_ID, id(), null, str);
    }

    public void requestAlbumDetailById(Long l, String str) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.b(l.longValue()), a.UPDATE_ALBUM_DETAIL_BY_ID, id(), null, str);
    }

    public void requestPostInfosById(final Collection collection, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                PostResult postResult = (PostResult) c.a(u.a(collection));
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_POST_INFO_LIST_BY_ID, postResult, str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestCelebrityPosts(Long l) {
        e.a(u.a(l.longValue()), a.UPDATE_DISCOVERY_POST_INFO_LIST_BY_ID, id(), null);
    }

    public void requestRecommendCelebratePostIds(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_RECOMMEND_CELEBRATE_POST_ID_LIST, c.a(u.a()), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestFirstPublishCelebratePostIds(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FIRST_PUBLISH_CELEBRATE_POST_ID_LIST, c.a(u.b()), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void deleteNotice(final Notice notice, final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c c;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_NOTICE_DELETED, c.a(q.a(this.c.f(), notice.getNoticeId())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void addFavoritePosts(final List<Long> list, final String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ c c;

                public void run() {
                    BaseResult a = a.a().a(list, this.c.f());
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_ADD_FAVORITE_POSTS, a, str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                    if (list.size() > 0) {
                        com.sds.android.ttpod.framework.a.b.d.f.a(str, true, String.valueOf(list.get(0)), "songlist", String.valueOf(a.getCode()));
                    }
                    PostResult postResult = (PostResult) c.a(u.a(list));
                    if (postResult.getDataList().size() > 0) {
                        Iterator it = postResult.getDataList().iterator();
                        while (it.hasNext()) {
                            this.c.addPostToMediaGroup((Post) it.next());
                        }
                    }
                }
            });
        }
    }

    public void removeFavoritePosts(final List<Long> list, final String str) {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ c c;

                public void run() {
                    BaseResult b = a.a().b(list, this.c.f());
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_REMOVE_FAVORITE_POSTS, b, str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                    if (list.size() > 0) {
                        com.sds.android.ttpod.framework.a.b.d.f.a(str, false, String.valueOf(list.get(0)), "songlist", String.valueOf(b.getCode()));
                    }
                    String buildMusicCircleFavGroupIDPrefix = MediaStorage.buildMusicCircleFavGroupIDPrefix();
                    for (Long longValue : list) {
                        if (MediaStorage.isGroupExisted(c.sContext, buildMusicCircleFavGroupIDPrefix + longValue.longValue())) {
                            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.DELETE_GROUP, r0));
                        }
                    }
                }
            });
        }
    }

    public Boolean isFavoritePost(Long l) {
        if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
            return Boolean.valueOf(false);
        }
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                a.a().a(new a.a(this) {
                    final /* synthetic */ AnonymousClass32 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_SYNC_FAVORITE_POST_FINISHED, new Object[0]), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                    }
                }, this.a.f());
            }
        });
        return Boolean.valueOf(a.a().b(l.longValue()));
    }

    public void requestFavoriteSongListPosts() {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                List dataList = ((MessageCollectListResult) c.a(n.a(this.a.f()))).getDataList();
                Collection arrayList = new ArrayList();
                if (!dataList.isEmpty()) {
                    dataList = com.sds.android.ttpod.framework.a.q.a(dataList, 20);
                    com.sds.android.ttpod.framework.a.q qVar = new com.sds.android.ttpod.framework.a.q();
                    qVar.b(dataList.size());
                    qVar.a(0);
                    qVar.c(0);
                    for (MessageCollectItem id : (List) dataList.get(qVar.a())) {
                        arrayList.add(Long.valueOf(id.getId()));
                    }
                }
                this.a.requestPostInfosById(arrayList, "favorite");
            }
        });
    }

    public void requestFavoritePosts(final String str) {
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_FAVORITE_COLLECT_LIST, c.a(n.a(this.b.f())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void requestPostsByCategoryId(final Long l, final Integer num, final String str) {
        com.sds.android.sdk.lib.util.d.a((Object) l, "timestamp not null");
        com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
            final /* synthetic */ c d;

            public void run() {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_POSTS_BY_CATEGORY_ID, c.a(u.a(l.longValue(), num.intValue())), str), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }
        });
    }

    public void setLoginUid(Long l) {
        b.a().d(l.longValue());
        a.a().a(l.longValue());
    }

    public void changePostReplyCount(Post post) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_POST_REPLY_COUNT, post), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
    }

    public void changePostRepostCount(Post post) {
        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.UPDATE_POST_REPOST_COUNT, post), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
    }

    public void addPostToMediaGroup(final Post post) {
        final String buildMusicCircleFavGroupID = MediaStorage.buildMusicCircleFavGroupID(post.getPostId());
        com.sds.android.ttpod.framework.a.o.b(com.sds.android.ttpod.framework.a.o.b(f.b(post)), new com.sds.android.ttpod.framework.a.o.a<List<MediaItem>>(this) {
            final /* synthetic */ c c;

            public void a(List<MediaItem> list) {
                if (!list.isEmpty()) {
                    MediaStorage.insertGroup(c.sContext, post.getSongListName(), buildMusicCircleFavGroupID, GroupType.CUSTOM_ONLINE);
                    MediaStorage.insertMediaItems(c.sContext, buildMusicCircleFavGroupID, list);
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.ADD_POST_TO_MEDIA_GROUP_FINISHED, buildMusicCircleFavGroupID), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
                }
            }
        });
    }

    public void addPostsToMediaGroup(List<Post> list) {
        if (list == null) {
            throw new IllegalArgumentException("postList should not be null");
        }
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<List<Post>, Boolean>(this, list) {
            final /* synthetic */ c a;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((List) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((Boolean) obj);
            }

            protected Boolean a(List<Post> list) {
                if (com.sds.android.ttpod.framework.storage.environment.b.aQ()) {
                    c(list);
                } else {
                    b(list);
                }
                return Boolean.valueOf(true);
            }

            protected void a(Boolean bool) {
                com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.ADD_POSTS_TO_MEDIA_GROUP_FINISHED, new Object[0]), com.sds.android.ttpod.framework.modules.c.MUSIC_CIRCLE);
            }

            private void b(List<Post> list) {
                for (Post post : list) {
                    a(post.getSongListName(), MediaStorage.buildMusicCircleFavGroupID(post.getPostId()), a((OnlineMediaItemsResult) r.a(a(post)).g()));
                }
            }

            private void c(List<Post> list) {
                for (Post post : list) {
                    a(post.getSongListName(), MediaStorage.buildMusicCircleFavGroupID(post.getPostId()), a((OnlineMediaItemsResult) r.c(a(post)).g()));
                }
            }

            private List<Long> a(Post post) {
                return com.sds.android.ttpod.framework.a.o.b(f.b(post));
            }

            private List<MediaItem> a(OnlineMediaItemsResult onlineMediaItemsResult) {
                List<OnlineMediaItem> dataList = onlineMediaItemsResult.getDataList();
                List<MediaItem> arrayList = new ArrayList();
                for (OnlineMediaItem onlineMediaItem : dataList) {
                    if (onlineMediaItem != null) {
                        arrayList.add(com.sds.android.ttpod.framework.a.k.a(onlineMediaItem));
                    }
                }
                return arrayList;
            }

            private void a(String str, String str2, List<MediaItem> list) {
                if (!list.isEmpty()) {
                    MediaStorage.insertGroup(c.sContext, str, str2, GroupType.CUSTOM_ONLINE);
                    MediaStorage.insertMediaItems(c.sContext, str2, list);
                }
            }
        });
    }

    public void addListenerCount(final Long l) {
        if (l.longValue() != 0) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    u.b(l.longValue()).g();
                }
            });
        }
    }
}
