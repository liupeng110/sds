package com.sds.android.ttpod.fragment.settings.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.setting.FeedbackChatActivity;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.c;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackListFragment extends BaseFragment implements OnItemClickListener {
    private static final int LIST_DIVIDER_HEIGHT = 2;
    private a mAdapter;
    private FeedbackItem mClickedFeedbackItem;
    private DragUpdateListView mListView;
    private View mReloadView;
    private c mRequestState = c.IDLE;
    private StateView mStateView;
    private TextView mTvHint;

    class a extends com.sds.android.ttpod.adapter.a<FeedbackItem> {
        final /* synthetic */ FeedbackListFragment a;
        private String[] e = this.a.getResources().getStringArray(R.array.feedback_status);
        private int[] f = new int[]{R.color.text_background_feedback_status_waiting, R.color.text_background_feedback_status_solving, R.color.text_background_feedback_status_solved, R.color.text_background_feedback_status_recorded, R.color.text_background_feedback_status_not_now};

        a(FeedbackListFragment feedbackListFragment) {
            this.a = feedbackListFragment;
        }

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public int getCount() {
            return this.d.size();
        }

        public FeedbackItem a(int i) {
            return (FeedbackItem) this.d.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.feedback_item, null);
            inflate.setTag(new b(this.a, (ViewGroup) inflate));
            return inflate;
        }

        protected void a(View view, FeedbackItem feedbackItem, int i) {
            b bVar = (b) view.getTag();
            bVar.c.setText(feedbackItem.getProposalContent());
            bVar.d.setText(w.a(feedbackItem.getLastUpdated(), "yyyy-MM-dd hh:mm:ss a"));
            bVar.b.setVisibility(feedbackItem.isUnread() ? 0 : 4);
            int status = feedbackItem.getStatus();
            bVar.e.setText(status < this.e.length ? this.e[status] : this.a.getResources().getString(R.string.unknown_status));
            bVar.e.setBackgroundResource(status < this.f.length ? this.f[status] : this.f[0]);
        }
    }

    class b {
        final /* synthetic */ FeedbackListFragment a;
        private IconTextView b;
        private TextView c;
        private TextView d;
        private TextView e;

        b(FeedbackListFragment feedbackListFragment, ViewGroup viewGroup) {
            this.a = feedbackListFragment;
            this.b = (IconTextView) viewGroup.findViewById(R.id.new_flag);
            this.b.setVisibility(4);
            this.c = (TextView) viewGroup.findViewById(R.id.tv_feedback_topic);
            this.d = (TextView) viewGroup.findViewById(R.id.tv_feedback_update_time);
            this.e = (TextView) viewGroup.findViewById(R.id.tv_feedback_status);
        }
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_LIST_FINISH, i.a(cls, "updateFeedbackList", com.sds.android.sdk.lib.b.b.class, List.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.PROPOSAL_FEEDBACK_FINISH, i.a(cls, "onProposalFeedbackFinish", com.sds.android.sdk.lib.b.b.class, FeedbackItem.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_FEEDBACK_MESSAGE_FINISH, i.a(cls, "onSendMessageFinish", com.sds.android.sdk.lib.b.b.class, FeedbackMessage.class));
        map.put(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_MESSAGES_FINISH, i.a(cls, "onRequestMessagesFinished", com.sds.android.sdk.lib.b.b.class, List.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.NEW_REPLYIED_FEEDBACKS_RECEIVED, i.a(cls, "onRequestNewRepliedMessagesFinished", List.class));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_feedback_list, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTvHint = (TextView) view.findViewById(R.id.tv_feedback_history_hint);
        this.mStateView = (StateView) view.findViewById(R.id.feedback_list_loadingview);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedbackListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (EnvironmentUtils.c.e()) {
                    this.a.requestFeedbackList();
                } else {
                    f.a((int) R.string.network_error);
                }
            }
        });
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setHeaderDividersEnabled(true);
        this.mListView.setDivider(getResources().getDrawable(R.color.listDivider_feedback_list));
        this.mListView.setDividerHeight(2);
        this.mListView.setOnStartRefreshListener(new com.sds.android.ttpod.widget.dragupdatelist.a.c(this) {
            final /* synthetic */ FeedbackListFragment a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                if (!EnvironmentUtils.c.e()) {
                    f.a((int) R.string.network_error);
                } else if (this.a.mRequestState != c.REQUESTING) {
                    this.a.requestFeedbackList();
                }
            }
        });
        this.mListView.setOnItemClickListener(this);
        this.mAdapter = new a(this);
        this.mListView.setAdapter(this.mAdapter);
        loadFeedbackCache();
        if (EnvironmentUtils.c.e()) {
            requestFeedbackList();
        }
    }

    public void onDestroyView() {
        Map hashMap = new HashMap();
        if (this.mAdapter != null) {
            for (FeedbackItem feedbackItem : this.mAdapter.b()) {
                hashMap.put(feedbackItem.getId(), feedbackItem);
            }
        }
        com.sds.android.ttpod.framework.storage.a.a.a().a(hashMap);
        super.onDestroyView();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (EnvironmentUtils.c.e()) {
            int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
            if (a > -1) {
                this.mClickedFeedbackItem = this.mAdapter.a(a);
                startActivity(new Intent(getActivity(), FeedbackChatActivity.class).putExtra(FeedbackChatActivity.FEEDBACK_ITEM, this.mClickedFeedbackItem));
                this.mAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        f.a((int) R.string.network_error);
    }

    public void updateFeedbackList(com.sds.android.sdk.lib.b.b bVar, List list, Boolean bool) {
        this.mListView.c();
        if (bVar.isSuccess()) {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            if (bool.booleanValue()) {
                if (list == null || list.size() <= 0) {
                    this.mTvHint.setText(R.string.feedback_history_empty);
                } else {
                    this.mTvHint.setText(R.string.feedback_history_hint);
                }
                sortFeedbackItems(list);
                this.mAdapter.a(list);
            } else {
                this.mRequestState = c.IDLE;
                return;
            }
        }
        this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.FAILED);
        this.mRequestState = c.IDLE;
    }

    public void onProposalFeedbackFinish(com.sds.android.sdk.lib.b.b bVar, FeedbackItem feedbackItem) {
        this.mListView.c();
        if (bVar.isSuccess()) {
            this.mTvHint.setText(R.string.feedback_history_hint);
            synchronized (this.mAdapter) {
                this.mAdapter.b().add(0, feedbackItem);
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onRequestMessagesFinished(com.sds.android.sdk.lib.b.b bVar, List list, Boolean bool) {
        if (bVar.isSuccess() && this.mClickedFeedbackItem.isUnread()) {
            this.mClickedFeedbackItem.setUnread(false);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onSendMessageFinish(com.sds.android.sdk.lib.b.b bVar, FeedbackMessage feedbackMessage) {
        if (bVar.isSuccess()) {
            this.mClickedFeedbackItem.setLastUpdated(feedbackMessage.getTimestamp());
            this.mAdapter.b().remove(this.mClickedFeedbackItem);
            this.mAdapter.b().add(0, this.mClickedFeedbackItem);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onRequestNewRepliedMessagesFinished(List<FeedbackItem> list) {
        loadFeedbackCache();
    }

    private void requestFeedbackList() {
        this.mRequestState = c.REQUESTING;
        com.sds.android.ttpod.framework.base.a.b a = com.sds.android.ttpod.framework.base.a.b.a();
        com.sds.android.ttpod.framework.modules.a aVar = com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_LIST;
        Object[] objArr = new Object[2];
        objArr[0] = Long.valueOf(com.sds.android.ttpod.framework.storage.a.a.a().p() == null ? 0 : com.sds.android.ttpod.framework.storage.environment.b.bw());
        objArr[1] = null;
        a.b(new com.sds.android.ttpod.framework.base.a.a(aVar, objArr));
    }

    private void loadFeedbackCache() {
        Map p = com.sds.android.ttpod.framework.storage.a.a.a().p();
        if (p != null && p.size() != 0) {
            this.mTvHint.setText(R.string.feedback_history_hint);
            List arrayList = new ArrayList(p.values());
            sortFeedbackItems(arrayList);
            this.mAdapter.a(arrayList);
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
        }
    }

    private void sortFeedbackItems(List<FeedbackItem> list) {
        Collections.sort(list, new Comparator<FeedbackItem>(this) {
            final /* synthetic */ FeedbackListFragment a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((FeedbackItem) obj, (FeedbackItem) obj2);
            }

            public int a(FeedbackItem feedbackItem, FeedbackItem feedbackItem2) {
                return -a(feedbackItem.getLastUpdated(), feedbackItem2.getLastUpdated());
            }

            private int a(long j, long j2) {
                if (j > j2) {
                    return 1;
                }
                if (j == j2) {
                    return 0;
                }
                return -1;
            }
        });
    }
}
