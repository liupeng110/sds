package com.sds.android.ttpod.activities.musiccircle.message;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.a.q;
import com.sds.android.cloudapi.ttpod.a.v;
import com.sds.android.cloudapi.ttpod.data.NewNoticeCount;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageOverView;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageOverViewListResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.d.a.b;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.f.e;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MessageEntryFragment extends SlidingClosableFragment implements OnScrollListener, OnItemClickListener, OnItemLongClickListener {
    private static final int PAGE_SIZE = 20;
    private static final long REFRESH_INTERVAL = 300000;
    private b mAdapter;
    private a mCommentNoticeHeader;
    private OnClickListener mHeaderItemClickListener = new OnClickListener(this) {
        final /* synthetic */ MessageEntryFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            Bundle bundle = new Bundle();
            BaseFragment noticeFragment;
            switch (view.getId()) {
                case R.id.repost_notice:
                    noticeFragment = new NoticeFragment();
                    bundle.putInt(StarCategory.KEY_STAR_CATEGORY_ID, e.REPOST.value());
                    noticeFragment.setArguments(bundle);
                    noticeFragment.setOriginFragment(this.a);
                    this.a.launchFragment(noticeFragment);
                    new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_MESSAGE_CLICK_NOTIFICATION.getValue(), s.PAGE_CIRCLE_MESSAGE.getValue()).post();
                    return;
                case R.id.comment_notice:
                    noticeFragment = new MyCommentsFragment();
                    bundle.putInt(StarCategory.KEY_STAR_CATEGORY_ID, e.COMMENT.value());
                    noticeFragment.setArguments(bundle);
                    noticeFragment.setOriginFragment(this.a);
                    this.a.launchFragment(noticeFragment);
                    new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_MESSAGE_CLICK_COMMENT.getValue(), s.PAGE_CIRCLE_MESSAGE.getValue()).post();
                    return;
                case R.id.system_notice:
                    this.a.launchFragment(new SystemMessageFragment());
                    new SUserEvent("PAGE_CLICK", r.ACTION_CIRCLE_MESSAGE_CLICK_SYS_INFORM.getValue(), s.PAGE_CIRCLE_MESSAGE.getValue()).post();
                    return;
                default:
                    return;
            }
        }
    };
    private boolean mIsRefreshing;
    private DragUpdateListView mListView;
    private NewNoticeCount mNewNoticeCount;
    private SlidingClosableFragment mOriginFragment;
    private List<PrivateMessageOverView> mPrivateMessages = new ArrayList();
    private Handler mRefreshHandler = new Handler();
    private Runnable mRefreshNewNoticeCountRunnable = new Runnable(this) {
        final /* synthetic */ MessageEntryFragment a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.isAdded()) {
                this.a.mRefreshHandler.postDelayed(this.a.mRefreshNewNoticeCountRunnable, MessageEntryFragment.REFRESH_INTERVAL);
                this.a.refreshNewNoticeCount();
            }
        }
    };
    private a mRepostNoticeHeader;
    private int mRequestCode;
    private c mRequestState = c.IDLE;
    private a mSystemNoticeHeader;

    class a {
        final /* synthetic */ MessageEntryFragment a;
        private TextView b;
        private TextView c;
        private ImageView d;
        private View e;

        public a(MessageEntryFragment messageEntryFragment, View view) {
            this.a = messageEntryFragment;
            this.e = view;
            this.b = (TextView) view.findViewById(R.id.text_count);
            this.c = (TextView) view.findViewById(R.id.tv_text);
            this.d = (ImageView) view.findViewById(R.id.image_avatar);
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getActionBarController().b((int) R.string.musiccircle_entry_title);
        this.mNewNoticeCount = (NewNoticeCount) getArguments().getSerializable("new_notice_count");
        this.mRequestCode = getArguments().getInt(StarCategory.KEY_STAR_CATEGORY_ID);
        View inflate = layoutInflater.inflate(R.layout.musiccircle_message_entry, viewGroup, false);
        View inflate2 = layoutInflater.inflate(R.layout.musiccircle_message_entry_header, null, false);
        this.mRepostNoticeHeader = new a(this, inflate2.findViewById(R.id.repost_notice));
        this.mCommentNoticeHeader = new a(this, inflate2.findViewById(R.id.comment_notice));
        this.mSystemNoticeHeader = new a(this, inflate2.findViewById(R.id.system_notice));
        bindNoticeHeader(this.mRepostNoticeHeader, getString(R.string.musiccircle_notice), 0, R.drawable.img_musiccircle_message_entry_notice, this.mHeaderItemClickListener);
        bindNoticeHeader(this.mCommentNoticeHeader, getString(R.string.musiccircle_comment), 0, R.drawable.img_musiccircle_message_entry_comment, this.mHeaderItemClickListener);
        bindNoticeHeader(this.mSystemNoticeHeader, getString(R.string.musiccircle_system_notice), 0, R.drawable.img_musiccircle_message_entry_system, this.mHeaderItemClickListener);
        this.mListView = (DragUpdateListView) inflate.findViewById(R.id.message_listview);
        this.mListView.addHeaderView(inflate2);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.setOnScrollListener(this);
        this.mListView.setOnStartRefreshListener(new com.sds.android.ttpod.widget.dragupdatelist.a.c(this) {
            final /* synthetic */ MessageEntryFragment a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                if (this.a.mRequestState != c.REQUESTING) {
                    this.a.requestPrivateMessages(0);
                }
            }
        });
        this.mAdapter = new b(getActivity(), this.mPrivateMessages);
        this.mListView.setAdapter(this.mAdapter);
        requestPrivateMessages(0);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        com.sds.android.ttpod.activities.musiccircle.a.c.a().c();
        refreshNewNoticeCount();
        this.mRefreshHandler.postDelayed(this.mRefreshNewNoticeCountRunnable, REFRESH_INTERVAL);
    }

    private void flushCountViewFlag() {
        int i = 0;
        if (this.mNewNoticeCount != null) {
            int newRepostCount = this.mNewNoticeCount.getNewRepostCount() + this.mNewNoticeCount.getNewFollowerCount();
            this.mRepostNoticeHeader.b.setText(String.valueOf(newRepostCount));
            this.mRepostNoticeHeader.b.setVisibility(newRepostCount > 0 ? 0 : 8);
            this.mCommentNoticeHeader.b.setText(String.valueOf(this.mNewNoticeCount.getNewCommentCount()));
            TextView a = this.mCommentNoticeHeader.b;
            if (this.mNewNoticeCount.getNewCommentCount() > 0) {
                newRepostCount = 0;
            } else {
                newRepostCount = 8;
            }
            a.setVisibility(newRepostCount);
            this.mSystemNoticeHeader.b.setText(String.valueOf(this.mNewNoticeCount.getSystemNoticeCount()));
            TextView a2 = this.mSystemNoticeHeader.b;
            if (this.mNewNoticeCount.getSystemNoticeCount() <= 0) {
                i = 8;
            }
            a2.setVisibility(i);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mRefreshHandler.removeCallbacksAndMessages(null);
    }

    private void refreshNewNoticeCount() {
        if (com.sds.android.ttpod.framework.storage.environment.b.av()) {
            final o a = q.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken());
            a.a(new p<NewNoticeCountResult>(this) {
                final /* synthetic */ MessageEntryFragment b;

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((NewNoticeCountResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((NewNoticeCountResult) baseResult);
                }

                public void a(NewNoticeCountResult newNoticeCountResult) {
                    if (this.b.isViewAccessAble()) {
                        this.b.mNewNoticeCount = (NewNoticeCount) newNoticeCountResult.getData();
                        this.b.flushCountViewFlag();
                        this.b.mListView.c();
                    }
                }

                public void b(NewNoticeCountResult newNoticeCountResult) {
                    if (this.b.isViewAccessAble()) {
                        this.b.mNewNoticeCount = null;
                        this.b.mListView.c();
                        g.g(a.b());
                    }
                }
            });
        }
    }

    public void onPause() {
        super.onPause();
        com.sds.android.ttpod.activities.musiccircle.a.c.a().b();
    }

    private void bindNoticeHeader(a aVar, String str, int i, int i2, OnClickListener onClickListener) {
        aVar.d.setImageResource(i2);
        aVar.c.setText(str);
        aVar.b.setText(String.valueOf(i));
        aVar.e.setOnClickListener(onClickListener);
        aVar.b.setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Object obj = 1;
        super.onActivityResult(i, i2, intent);
        if (this.mNewNoticeCount != null) {
            if (e.REPOST.value() == i) {
                if (i2 != this.mNewNoticeCount.getNewRepostCount()) {
                    this.mNewNoticeCount.setNewRepostCount(i2);
                }
                obj = null;
            } else {
                if (e.COMMENT.value() == i && i2 != this.mNewNoticeCount.getNewCommentCount()) {
                    this.mNewNoticeCount.setNewCommentCount(i2);
                }
                obj = null;
            }
            if (obj != null) {
                flushCountViewFlag();
            }
        }
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (a > -1) {
            PrivateMessageOverView privateMessageOverView = (PrivateMessageOverView) this.mAdapter.getItem(a);
            BaseFragment privateMessageFragment = new PrivateMessageFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", com.sds.android.ttpod.framework.modules.core.f.b.a(privateMessageOverView.getUser()));
            privateMessageFragment.setArguments(bundle);
            launchFragment(privateMessageFragment);
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
        if (a > -1) {
            final PrivateMessageOverView privateMessageOverView = (PrivateMessageOverView) this.mAdapter.getItem(a);
            f.a(getActivity(), new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(1, 0, (CharSequence) "删除")}, "删除信息", new com.sds.android.ttpod.component.b.a.b(this) {
                final /* synthetic */ MessageEntryFragment b;

                public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                    if (aVar.g() == 1 && com.sds.android.ttpod.framework.storage.environment.b.av()) {
                        final o a = v.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), privateMessageOverView.getUser().getUserId());
                        a.a(new p<BaseResult>(this) {
                            final /* synthetic */ AnonymousClass5 b;

                            public void onRequestSuccess(BaseResult baseResult) {
                                f.a("删除成功");
                                for (int size = this.b.b.mPrivateMessages.size() - 1; size >= 0; size--) {
                                    if (((PrivateMessageOverView) this.b.b.mPrivateMessages.get(size)).getUser().getUserId() == privateMessageOverView.getUser().getUserId()) {
                                        this.b.b.mPrivateMessages.remove(size);
                                        break;
                                    }
                                }
                                this.b.b.mAdapter.a(this.b.b.mPrivateMessages);
                            }

                            public void onRequestFailure(BaseResult baseResult) {
                                f.a("删除失败");
                                g.g(a.b());
                            }
                        });
                    }
                }
            });
        }
        return true;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (i3 > i2 && (i + i2) + 1 >= i3 && this.mRequestState != c.REQUESTING) {
            requestPrivateMessages(this.mPrivateMessages.isEmpty() ? 0 : ((PrivateMessageOverView) this.mPrivateMessages.get(this.mPrivateMessages.size() - 1)).getLastModified());
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PRIVATE_MESSAGE_LIST, i.a(getClass(), "updatePrivateMessages", PrivateMessageOverViewListResult.class, String.class));
    }

    public void updatePrivateMessages(PrivateMessageOverViewListResult privateMessageOverViewListResult, String str) {
        if ("private_message".equals(str)) {
            Object dataList = privateMessageOverViewListResult.getDataList();
            if (dataList.isEmpty()) {
                this.mRequestState = c.REQUESTED_FAIL;
            } else {
                if (this.mIsRefreshing) {
                    this.mPrivateMessages.clear();
                }
                if (this.mPrivateMessages.size() == 0) {
                    this.mPrivateMessages = dataList;
                } else {
                    addMessages(dataList);
                }
                this.mAdapter.a(this.mPrivateMessages);
                this.mRequestState = c.REQUESTED_SUCCESS;
            }
            this.mIsRefreshing = false;
            this.mListView.c();
        }
    }

    private void addMessages(ArrayList<PrivateMessageOverView> arrayList) {
        Collection arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PrivateMessageOverView privateMessageOverView = (PrivateMessageOverView) it.next();
            Object obj = 1;
            for (PrivateMessageOverView lastModified : this.mPrivateMessages) {
                Object obj2;
                if (lastModified.getLastModified() == privateMessageOverView.getLastModified()) {
                    obj2 = null;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                arrayList2.add(privateMessageOverView);
            }
        }
        this.mPrivateMessages.addAll(arrayList2);
    }

    private void requestPrivateMessages(long j) {
        boolean z;
        if (j <= 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsRefreshing = z;
        this.mRequestState = c.REQUESTING;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_PRIVATE_MESSAGES, Long.valueOf(j), Integer.valueOf(20), "private_message"));
    }

    public void setOriginFragment(SlidingClosableFragment slidingClosableFragment) {
        this.mOriginFragment = slidingClosableFragment;
    }

    protected void onSlidingClosed() {
        super.onSlidingClosed();
        setResults();
    }

    private void setResults() {
        if (this.mOriginFragment != null) {
            SlidingClosableFragment slidingClosableFragment = this.mOriginFragment;
            int i = this.mRequestCode;
            getActivity();
            slidingClosableFragment.onActivityResult(i, -1, new Intent().putExtra("new_notice_count", this.mNewNoticeCount));
        }
    }
}
