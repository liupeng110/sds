package com.sds.android.ttpod.activities.user;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.user.utils.e;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;

public class PhoneCaptchaActivity extends SlidingClosableActivity {
    public static final int CAPTCHA_VALID_TIME = 60;
    private static final int DELAY_TIME = 1000;
    private static final int RESEND_CAPTCHA_TEXT_ACTIVE_COLOR = -10839041;
    private static final int RESEND_CAPTCHA_TEXT_INACTIVE_COLOR = -5984842;
    private String mCaptchaName;
    private EditText mCaptchaView;
    private Handler mCountDownHandler = new Handler();
    private Runnable mCountRunnable = new Runnable(this) {
        final /* synthetic */ PhoneCaptchaActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.mReSendView.setText(this.a.getString(R.string.obtain_captcha_after_seconds, new Object[]{this.a.mCaptchaName, Integer.valueOf(60 - this.a.mWaitTime)}));
            if (this.a.mWaitTime < 60) {
                this.a.mWaitTime = this.a.mWaitTime + 1;
                this.a.mCountDownHandler.postDelayed(this, 1000);
                return;
            }
            this.a.enableResendView();
        }
    };
    private TextView mFinishView;
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ PhoneCaptchaActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.finish:
                    this.a.validateCaptcha();
                    return;
                case R.id.send_again:
                    this.a.reSendCaptcha();
                    this.a.triggerCountDown();
                    return;
                default:
                    return;
            }
        }
    };
    private String mPhoneNumber;
    private TextView mReSendView;
    private TextView mTipView;
    private int mWaitTime = 0;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
    }

    private void initView() {
        setContentView((int) R.layout.activity_user_phone_captcha);
        this.mPhoneNumber = getIntent().getExtras().getString(IndieKitDefine.SG_KEY_INDIE_KIT_USERNAME);
        this.mTipView = (TextView) findViewById(R.id.tip);
        this.mCaptchaView = (EditText) findViewById(R.id.captcha);
        this.mReSendView = (TextView) findViewById(R.id.send_again);
        this.mReSendView.setOnClickListener(this.mOnClickListener);
        this.mFinishView = (TextView) findViewById(R.id.finish);
        this.mFinishView.setOnClickListener(this.mOnClickListener);
        triggerCountDown();
    }

    protected void setFinishViewText(int i) {
        this.mFinishView.setText(i);
    }

    protected void setCaptchaName(String str) {
        this.mCaptchaName = str;
        this.mCaptchaView.setHint(getString(R.string.input_received, new Object[]{this.mCaptchaName}));
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(getString(R.string.sent_to_phone, new Object[]{this.mCaptchaName}));
        spannableStringBuilder.append(Html.fromHtml("<font color=#4C596D>" + e.a(this.mPhoneNumber) + "</font>"));
        this.mTipView.setText(spannableStringBuilder);
    }

    protected String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    protected String getCaptcha() {
        return this.mCaptchaView.getText().toString().trim();
    }

    protected void reSendCaptcha() {
    }

    protected void validateCaptcha() {
    }

    private void enableResendView() {
        this.mReSendView.setTextColor(RESEND_CAPTCHA_TEXT_ACTIVE_COLOR);
        this.mReSendView.setText(getString(R.string.resend, new Object[]{this.mCaptchaName}));
        this.mReSendView.setClickable(true);
    }

    private void disableResendView() {
        this.mReSendView.setTextColor(RESEND_CAPTCHA_TEXT_INACTIVE_COLOR);
        this.mReSendView.setClickable(false);
    }

    private void triggerCountDown() {
        this.mWaitTime = 0;
        disableResendView();
        this.mCountDownHandler.post(this.mCountRunnable);
    }

    private void stopCountDown() {
        try {
            this.mWaitTime = 0;
            enableResendView();
            this.mCountDownHandler.removeCallbacks(this.mCountRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mCountDownHandler.removeCallbacksAndMessages(null);
    }

    protected void onStop() {
        super.onStop();
        stopCountDown();
    }
}
