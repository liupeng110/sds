package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.f.d;
import com.sds.android.ttpod.framework.modules.f.f;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DiscoveryFragment extends RecommendPostListFragment {
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        CharSequence string = getArguments().getString("name");
        if (m.a((String) string)) {
            string = getString(R.string.discovery);
        }
        getActionBarController().a(string);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_discovery");
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_DISCOVERY_POST_INFO_LIST_BY_ID, i.a(getClass(), "updateCelebrityPosts", PostResult.class));
    }

    public void updateCelebrityPosts(PostResult postResult) {
        if (isAdded()) {
            handleResult(convertRecommendPost(postResult.getDataList()), null, postResult.getCode());
        }
    }

    protected void onLoadData() {
        Long valueOf;
        switch (getLoadStatus()) {
            case FIRST_LOAD:
                valueOf = Long.valueOf(0);
                break;
            case NEXT_PAGE:
                valueOf = Long.valueOf(((RecommendPost) getDataList().get(getDataCount() - 1)).getTime());
                break;
            case RELOAD:
                valueOf = Long.valueOf(System.currentTimeMillis());
                break;
            default:
                throw new IllegalStateException();
        }
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.REQUEST_CELEBRITY_POSTS, valueOf));
    }

    public List<Long> getPlaySongId(RecommendPost recommendPost) {
        if (recommendPost == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        int type = recommendPost.getType();
        OnlineMediaItem mediaItem = recommendPost.getMediaItem();
        List<Long> arrayList = new ArrayList();
        if (d.SINGLE_SONG.value() == type && mediaItem != null) {
            addSongId(arrayList, Long.valueOf(mediaItem.getSongId()));
        } else if (d.DJ.value() == type) {
            addSongId(arrayList, Long.valueOf(mediaItem.getSongId()));
            addSongIdList(arrayList, recommendPost.getSongList());
        } else if (d.SONG_LIST.value() == type) {
            addSongIdList(arrayList, recommendPost.getSongList());
        }
        return arrayList;
    }

    public void playMediaItemStatistic(long j, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_DISCOVERY_PLAY.getValue(), s.PAGE_ONLINE_DISCOVERY.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    public void startPostDetailStatistic(long j, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_DISCOVERY_POST.getValue(), s.PAGE_ONLINE_DISCOVERY.getValue(), s.PAGE_ONLINE_POST_DETAIL.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    private void addSongId(List<Long> list, Long l) {
        if (l != null && l.longValue() > 0) {
            list.add(l);
        }
    }

    private void addSongIdList(List<Long> list, ArrayList<OnlineMediaItem> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                addSongId(list, Long.valueOf(((OnlineMediaItem) it.next()).getSongId()));
            }
        }
    }

    private List<RecommendPost> convertRecommendPost(List<Post> list) {
        List<RecommendPost> arrayList = new ArrayList();
        for (Post post : list) {
            String nickName = post.getUser().getNickName();
            String tweet = post.getTweet();
            int type = post.getType();
            RecommendPost recommendPost = new RecommendPost(Long.valueOf(post.getId()).longValue(), 0, tweet, "", nickName, post.getSongListName(), tweet, f.d(post), type);
            recommendPost.setTime(post.getCreateTimeInSecond());
            recommendPost.setMediaItem(post.getMediaItem());
            recommendPost.setSongList(post.getSongList());
            arrayList.add(recommendPost);
        }
        return arrayList;
    }

    protected boolean needSearchAction() {
        return false;
    }
}
