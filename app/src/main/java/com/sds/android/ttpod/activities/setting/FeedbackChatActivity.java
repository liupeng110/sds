package com.sds.android.ttpod.activities.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FeedbackChatActivity extends SlidingClosableActivity {
    public static final String FEEDBACK_ITEM = "feedback_item";
    private b mAdapter;
    private Button mBtnSend;
    private EditText mEtInput;
    private FeedbackItem mFeedbackItem;
    private boolean mIsRequestingMessage;
    private DragUpdateListView mListView;
    private Long mNewMessageTimestamp;
    private View mReloadView;
    private com.sds.android.ttpod.component.c mRequestState = com.sds.android.ttpod.component.c.IDLE;
    private StateView mStateView;

    class a {
        final /* synthetic */ FeedbackChatActivity a;
        private View b;
        private TextView c;
        private TextView d;

        public a(FeedbackChatActivity feedbackChatActivity, View view) {
            this.a = feedbackChatActivity;
            this.b = view;
            this.c = (TextView) view.findViewById(R.id.text_content);
            this.d = (TextView) view.findViewById(R.id.text_time);
        }
    }

    class b extends com.sds.android.ttpod.adapter.a<FeedbackMessage> {
        final /* synthetic */ FeedbackChatActivity a;

        b(FeedbackChatActivity feedbackChatActivity) {
            this.a = feedbackChatActivity;
        }

        protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.feedback_chat_item, null);
            inflate.setTag(new c(this.a, inflate));
            return inflate;
        }

        protected void a(View view, FeedbackMessage feedbackMessage, int i) {
            a aVar;
            c cVar = (c) view.getTag();
            int i2 = 1 == feedbackMessage.getMsgSource() ? 1 : 0;
            if (i == 0 || a(feedbackMessage.getTimestamp(), ((FeedbackMessage) getItem(i - 1)).getTimestamp())) {
                cVar.c.setText(w.a(TimeUnit.MILLISECONDS.toSeconds(feedbackMessage.getTimestamp())));
                cVar.c.setVisibility(0);
            } else {
                cVar.c.setVisibility(8);
            }
            a b;
            if (i2 != 0) {
                b = cVar.d;
                cVar.e.b.setVisibility(8);
                aVar = b;
            } else {
                b = cVar.e;
                cVar.d.b.setVisibility(8);
                aVar = b;
            }
            aVar.b.setVisibility(0);
            aVar.c.setText(feedbackMessage.getContent());
            aVar.d.setText(w.b(TimeUnit.MILLISECONDS.toSeconds(feedbackMessage.getTimestamp())));
        }

        private boolean a(long j, long j2) {
            return new Date(j).getDate() > new Date(j2).getDate();
        }
    }

    class c {
        final /* synthetic */ FeedbackChatActivity a;
        private TextView b;
        private TextView c;
        private a d;
        private a e;

        public c(FeedbackChatActivity feedbackChatActivity, View view) {
            this.a = feedbackChatActivity;
            this.b = (TextView) view.findViewById(R.id.tv_staff_name);
            this.c = (TextView) view.findViewById(R.id.tv_chat_date);
            this.d = new a(feedbackChatActivity, view.findViewById(R.id.chatting_left));
            this.e = new a(feedbackChatActivity, view.findViewById(R.id.chatting_right));
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFeedbackItem = (FeedbackItem) getIntent().getSerializableExtra(FEEDBACK_ITEM);
        setContentView((int) R.layout.activity_feedback_chat);
        getActionBarController().b((int) R.string.add_feedback_description);
        getActionBarController().d();
        initViews();
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_MESSAGES_FINISH, i.a(cls, "onRequestMessagesFinished", com.sds.android.sdk.lib.b.b.class, List.class, Boolean.class));
        map.put(com.sds.android.ttpod.framework.modules.a.SEND_FEEDBACK_MESSAGE_FINISH, i.a(cls, "onSendMessageFinish", com.sds.android.sdk.lib.b.b.class, FeedbackMessage.class));
    }

    protected void onResume() {
        super.onResume();
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
            requestNewestMessage(this.mNewMessageTimestamp, null);
        } else {
            f.a((int) R.string.network_error);
        }
    }

    public void onRequestMessagesFinished(com.sds.android.sdk.lib.b.b bVar, List list, Boolean bool) {
        this.mListView.c();
        if (!bVar.isSuccess()) {
            f.a((int) R.string.request_feedback_message_fail);
        } else if (bool.booleanValue()) {
            List b = this.mAdapter.b();
            b.addAll(list);
            this.mAdapter.a(b);
        } else {
            this.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.SUCCESS);
            this.mAdapter.a(list);
            if (list.size() > 0) {
                this.mNewMessageTimestamp = Long.valueOf(((FeedbackMessage) list.get(list.size() - 1)).getTimestamp() + 1);
            }
        }
        this.mRequestState = com.sds.android.ttpod.component.c.IDLE;
        this.mIsRequestingMessage = false;
    }

    public void onSendMessageFinish(com.sds.android.sdk.lib.b.b bVar, FeedbackMessage feedbackMessage) {
        this.mListView.c();
        if (bVar.getCode() == SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM) {
            synchronized (this.mAdapter) {
                this.mAdapter.b().add(feedbackMessage);
                this.mNewMessageTimestamp = Long.valueOf(feedbackMessage.getTimestamp() + 1);
            }
            this.mAdapter.notifyDataSetChanged();
            this.mEtInput.setText("");
        } else {
            f.a((int) R.string.send_feedback_message_fail);
        }
        this.mBtnSend.setClickable(true);
    }

    private void initViews() {
        this.mEtInput = (EditText) findViewById(R.id.et_input);
        this.mBtnSend = (Button) findViewById(R.id.btn_send);
        this.mBtnSend.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedbackChatActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
                    if (!m.a(this.a.mEtInput.getText().toString())) {
                        this.a.mBtnSend.setClickable(false);
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEND_FEEDBACK_MESSAGE, new FeedbackMessage(this.a.mFeedbackItem.getId(), FeedbackMessage.TYPE_TEXT, r0)));
                        return;
                    }
                    return;
                }
                f.a((int) R.string.network_error);
            }
        });
        this.mStateView = (StateView) findViewById(R.id.chat_loadingview);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mReloadView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedbackChatActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (com.sds.android.sdk.lib.util.EnvironmentUtils.c.e()) {
                    this.a.mStateView.setState(com.sds.android.ttpod.widget.StateView.b.LOADING);
                    this.a.requestNewestMessage();
                    return;
                }
                f.a((int) R.string.network_error);
            }
        });
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mListView.setOnStartRefreshListener(new com.sds.android.ttpod.widget.dragupdatelist.a.c(this) {
            final /* synthetic */ FeedbackChatActivity a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                this.a.requestNewestMessage(this.a.mNewMessageTimestamp, null);
            }
        });
        this.mListView.setTranscriptMode(2);
        this.mAdapter = new b(this);
        this.mListView.setAdapter(this.mAdapter);
    }

    private void requestNewestMessage() {
        requestNewestMessage(null, null);
    }

    private void requestNewestMessage(Long l, Boolean bool) {
        if (!this.mIsRequestingMessage) {
            this.mRequestState = com.sds.android.ttpod.component.c.REQUESTING;
            this.mIsRequestingMessage = true;
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_FEEDBACK_MESSAGES, this.mFeedbackItem.getId(), l, bool));
        }
    }
}
