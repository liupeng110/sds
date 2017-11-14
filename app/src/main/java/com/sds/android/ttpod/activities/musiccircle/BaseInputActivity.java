package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.b.r;
import com.sds.android.ttpod.component.a.a;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.component.emoticons.EmoticonsLayout;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import java.util.ArrayList;

public abstract class BaseInputActivity extends SlidingClosableActivity {
    private static final int MAX_INPUT_SIZE = 140;
    private static final int TIME_DELAY_HIED = 100;
    private ImageView mAvatar;
    private EditText mContent;
    private ImageButton mEmoctionEntry;
    private OnClickListener mEmoctionEntryClickListener = new OnClickListener(this) {
        final /* synthetic */ BaseInputActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (this.a.mEmoticonsLayout.getVisibility() == 8) {
                this.a.mInputMgr.hideSoftInputFromWindow(this.a.mContent.getWindowToken(), 0);
                this.a.mContent.clearFocus();
                this.a.mEmoctionEntry.setImageResource(R.drawable.img_musiccircle_post_detail_softinput);
                this.a.mEmoctionEntry.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.a.mEmoticonsLayout != null) {
                            this.a.a.mEmoticonsLayout.setVisibility(0);
                            this.a.a.mSlidingClosableRelativeLayout.a(this.a.a.mEmoticonsLayout.getRectEmoticons());
                        }
                    }
                }, 100);
                return;
            }
            this.a.mEmoctionEntry.setImageResource(R.drawable.img_musiccircle_post_detail_expression);
            this.a.mEmoticonsLayout.setVisibility(8);
            this.a.mSlidingClosableRelativeLayout.b(this.a.mEmoticonsLayout.getRectEmoticons());
            this.a.mContent.requestFocus();
            this.a.mInputMgr.showSoftInput(this.a.mContent, 2);
        }
    };
    private EmoticonsLayout mEmoticonsLayout;
    private InputMethodManager mInputMgr;
    private TextView mInputSize;
    private boolean mIsSending;
    private TextView mNickName;
    private a mSendMessageAction;
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;
    private TextWatcher mTextWatcher = new TextWatcher(this) {
        final /* synthetic */ BaseInputActivity a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            int a = EmoticonsLayout.a(editable.toString());
            a = (a / 2) + (a % 2);
            if (a <= BaseInputActivity.MAX_INPUT_SIZE) {
                this.a.mInputSize.setText(String.valueOf(140 - a));
            } else if (a <= BaseInputActivity.MAX_INPUT_SIZE || a > 142) {
                editable.delete(BaseInputActivity.MAX_INPUT_SIZE, a);
            } else {
                editable.delete(this.a.mContent.getSelectionStart() - 1, this.a.mContent.getSelectionEnd());
            }
        }
    };
    private TextView mTweet;

    protected abstract String onLoadPicUrl();

    protected abstract String onLoadTweet();

    protected abstract TTPodUser onLoadUser();

    protected abstract void onSend(String str);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.musiccircle_repost_input);
        this.mInputMgr = (InputMethodManager) getSystemService("input_method");
        onNewIntent(getIntent());
        this.mContent = (EditText) findViewById(R.id.et_repost_content);
        this.mAvatar = (ImageView) findViewById(R.id.user_image);
        this.mNickName = (TextView) findViewById(R.id.user_name);
        this.mTweet = (TextView) findViewById(R.id.user_info);
        this.mEmoctionEntry = (ImageButton) findViewById(R.id.btn_emoctions);
        this.mInputSize = (TextView) findViewById(R.id.text_input_size);
        this.mEmoticonsLayout = (EmoticonsLayout) findViewById(R.id.layout_emoticons);
        this.mSlidingClosableRelativeLayout = getSlidingContainer();
        initView();
        this.mSendMessageAction = getActionBarController().d((int) R.drawable.img_musiccircle_send_message);
        this.mSendMessageAction.a(new b(this) {
            final /* synthetic */ BaseInputActivity a;

            {
                this.a = r1;
            }

            public void a(a aVar) {
                if (!this.a.mIsSending) {
                    String trim = this.a.mContent.getText().toString().trim();
                    if (r.a(this.a).a(trim)) {
                        f.a("内容含有敏感词，提交失败");
                        return;
                    }
                    this.a.mIsSending = true;
                    this.a.startSendingAnimation();
                    this.a.onSend(trim);
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if (this.mEmoticonsLayout.getVisibility() == 8) {
            this.mContent.requestFocus();
            this.mInputMgr.showSoftInput(this.mContent, 2);
        }
    }

    private void startSendingAnimation() {
        this.mSendMessageAction.f();
    }

    private void stopSendingAnimation() {
        this.mSendMessageAction.h();
    }

    protected void onSendFinished() {
        stopSendingAnimation();
        this.mIsSending = false;
        this.mInputMgr.hideSoftInputFromWindow(this.mContent.getWindowToken(), 0);
        f.a("发送成功");
        this.mContent.setText("");
        setResult(-1);
        finish();
    }

    protected void onSendFail() {
        this.mIsSending = false;
        stopSendingAnimation();
    }

    public static String getFirstPicInPost(Post post) {
        ArrayList picList = post.getPicList();
        if (picList == null || picList.isEmpty()) {
            return null;
        }
        return (String) picList.get(0);
    }

    private void initView() {
        String onLoadPicUrl = onLoadPicUrl();
        if (!TextUtils.isEmpty(onLoadPicUrl)) {
            int a = com.sds.android.ttpod.common.c.a.a(84);
            g.a(this.mAvatar, onLoadPicUrl, a, a, (int) R.drawable.img_avatar_default);
        }
        TTPodUser onLoadUser = onLoadUser();
        if (onLoadUser != null) {
            this.mNickName.setText(onLoadUser.getNickName());
            this.mNickName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, onLoadUser.isVerified() ? R.drawable.img_user_v : 0);
        }
        this.mTweet.setText(onLoadTweet());
        this.mContent.addTextChangedListener(this.mTextWatcher);
        this.mEmoctionEntry.setOnClickListener(this.mEmoctionEntryClickListener);
        this.mEmoticonsLayout.setInputEditText(this.mContent);
        this.mEmoticonsLayout.setVisibility(8);
        this.mEmoticonsLayout.setmMaxLength(MAX_INPUT_SIZE);
        this.mEmoticonsLayout.a(this.mSlidingClosableRelativeLayout);
        this.mContent.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ BaseInputActivity a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                this.a.mEmoctionEntry.setImageResource(R.drawable.img_musiccircle_post_detail_expression);
                this.a.mEmoticonsLayout.setVisibility(8);
                this.a.mSlidingClosableRelativeLayout.b(this.a.mEmoticonsLayout.getRectEmoticons());
                return false;
            }
        });
    }

    protected void onPause() {
        super.onPause();
        this.mInputMgr.hideSoftInputFromWindow(this.mContent.getWindowToken(), 0);
    }
}
