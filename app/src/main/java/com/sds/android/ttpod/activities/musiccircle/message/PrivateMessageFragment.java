package com.sds.android.ttpod.activities.musiccircle.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageContent;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageContentListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.d.a.d;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout;
import com.sds.android.ttpod.component.emoticons.EmoticonsWithInputLayout.a;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrivateMessageFragment extends SlidingClosableFragment implements a {
    private static final int PAGE_SIZE = 64;
    private d mAdapter;
    private EmoticonsWithInputLayout mEmoticonsWithInputLayout;
    private boolean mIsRequestNewestMessage;
    private DragUpdateListView mListView;
    private List<PrivateMessageContent> mPrivateMessageContents = new ArrayList();
    private View mReloadView;
    private StateView mStateView;
    private NewUser mUserCurrent;
    private NewUser mUserReplyTo;

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mUserReplyTo = (NewUser) getArguments().getSerializable("user");
        View inflate = layoutInflater.inflate(R.layout.musiccircle_private_message, viewGroup, false);
        this.mUserCurrent = b.at();
        this.mStateView = (StateView) inflate.findViewById(R.id.private_message_loadingview);
        this.mListView = (DragUpdateListView) this.mStateView.findViewById(R.id.dragupdate_listview);
        this.mReloadView = this.mStateView.findViewById(R.id.loadingview_failed);
        this.mEmoticonsWithInputLayout = (EmoticonsWithInputLayout) inflate.findViewById(R.id.layout_private_message_input);
        initView();
        this.mStateView.setState(StateView.b.LOADING);
        requestNewestMessage();
        getActionBarController().a(this.mUserReplyTo.getNickName());
        return inflate;
    }

    private void requestMoreMessage() {
        this.mIsRequestNewestMessage = false;
        long timestamp = this.mPrivateMessageContents.isEmpty() ? 0 : ((PrivateMessageContent) this.mPrivateMessageContents.get(this.mPrivateMessageContents.size() - 1)).getTimestamp();
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_PRIVATE_MESSAGES_CONTENT, Long.valueOf(this.mUserReplyTo.getUserId()), Long.valueOf(timestamp), Integer.valueOf(64), ""));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_PRIVATE_MESSAGE_CONTEXT_LIST, i.a(getClass(), "onUpdatePrivateMessageContents", PrivateMessageContentListResult.class, String.class));
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEND_PRIVATE_MESSAGE, i.a(getClass(), "updateSendPrivateMessage", BaseResult.class, String.class));
    }

    private void requestNewestMessage() {
        this.mIsRequestNewestMessage = true;
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.QUERY_PRIVATE_MESSAGES_CONTENT, Long.valueOf(this.mUserReplyTo.getUserId()), Long.valueOf(0), Integer.valueOf(64), ""));
    }

    public void updateSendPrivateMessage(BaseResult baseResult, String str) {
        if (baseResult.isSuccess()) {
            requestNewestMessage();
            this.mEmoticonsWithInputLayout.a();
            this.mEmoticonsWithInputLayout.b();
        } else {
            f.a("发送失败，请重新发送");
        }
        this.mEmoticonsWithInputLayout.setSendEnable(true);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        getActionBarController().onThemeLoaded();
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        this.mStateView.onThemeLoaded();
        if (this.mEmoticonsWithInputLayout != null) {
            this.mEmoticonsWithInputLayout.e();
        }
    }

    public void onUpdatePrivateMessageContents(PrivateMessageContentListResult privateMessageContentListResult, String str) {
        this.mListView.c();
        if (privateMessageContentListResult.isSuccess()) {
            this.mStateView.setState(StateView.b.SUCCESS);
            if (this.mIsRequestNewestMessage) {
                this.mPrivateMessageContents.clear();
            }
            int size = privateMessageContentListResult.getDataList().size();
            if (size > 0) {
                this.mPrivateMessageContents.addAll(privateMessageContentListResult.getDataList());
                this.mAdapter.b(this.mPrivateMessageContents);
                DragUpdateListView dragUpdateListView = this.mListView;
                size = this.mIsRequestNewestMessage ? this.mAdapter.getCount() - 1 : size < 64 ? size + 1 : 65;
                dragUpdateListView.setSelection(size);
            }
        } else if (this.mIsRequestNewestMessage) {
            this.mStateView.setState(StateView.b.FAILED);
        }
    }

    private void initView() {
        this.mAdapter = new d(getActivity(), this.mPrivateMessageContents, this.mUserCurrent, this.mUserReplyTo);
        this.mAdapter.a(new WrapUserPostListFragment.a(this) {
            final /* synthetic */ PrivateMessageFragment a;

            {
                this.a = r1;
            }

            public void a(NewUser newUser) {
                this.a.launchFragment(WrapUserPostListFragment.createUserPostListFragmentByUser(newUser, ""));
            }
        });
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnStartRefreshListener(new com.sds.android.ttpod.widget.dragupdatelist.a.c(this) {
            final /* synthetic */ PrivateMessageFragment a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                this.a.requestMoreMessage();
            }
        });
        this.mListView.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ PrivateMessageFragment a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.mEmoticonsWithInputLayout.b();
                return false;
            }
        });
        this.mReloadView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PrivateMessageFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (EnvironmentUtils.c.e()) {
                    this.a.mStateView.setState(StateView.b.LOADING);
                    this.a.requestNewestMessage();
                    return;
                }
                f.a((int) R.string.network_error);
            }
        });
        this.mEmoticonsWithInputLayout.a(getSlidingContainer(), null, this);
    }

    public void onSend(String str) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SEND_PRIVATE_MESSAGE, Long.valueOf(this.mUserReplyTo.getUserId()), str, ""));
    }

    public void onResume() {
        super.onResume();
        this.mEmoticonsWithInputLayout.c();
    }

    public void onBackPressed() {
        this.mEmoticonsWithInputLayout.setmEmoticonsLayoutVisibility(8);
        if (!this.mEmoticonsWithInputLayout.d()) {
            super.onBackPressed();
        }
    }
}
