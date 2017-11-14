package com.sds.android.ttpod.activities.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sds.android.ttpod.activities.user.utils.e;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.base.d;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.Map;

public class PhoneValidatorActivity extends SlidingClosableActivity {
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ PhoneValidatorActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.regionCode:
                    this.a.startActivityForResult(new Intent(this.a, SelectCountryActivity.class), 1);
                    return;
                case R.id.next:
                    this.a.validatePhoneExist();
                    return;
                default:
                    return;
            }
        }
    };
    private EditText mPhoneNumberView;
    private String mRegionCode = "+86";
    private TextView mRegionCodeView;
    private TextView mTipView;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
    }

    protected void initView() {
        setContentView((int) R.layout.activity_user_phone_validator);
        this.mRegionCodeView = (TextView) findViewById(R.id.regionCode);
        this.mPhoneNumberView = (EditText) findViewById(R.id.phone_number);
        this.mTipView = (TextView) findViewById(R.id.tip);
        findViewById(R.id.next).setOnClickListener(this.mOnClickListener);
        this.mRegionCodeView.setOnClickListener(this.mOnClickListener);
    }

    protected void setTipText(String str) {
        this.mTipView.setText(str);
    }

    protected void setTipText(SpannableStringBuilder spannableStringBuilder) {
        this.mTipView.setText(spannableStringBuilder);
    }

    protected void setTipClickListener(OnClickListener onClickListener) {
        this.mTipView.setOnClickListener(onClickListener);
    }

    protected String getPhoneNumber() {
        return this.mPhoneNumberView.getText().toString().trim();
    }

    protected String getRegionCode() {
        return this.mRegionCode;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            String[] split = intent.getStringExtra("region_code_result").split(SelectCountryActivity.SPLITTER);
            if (split.length == 3) {
                this.mRegionCode = split[2];
                this.mRegionCodeView.setText(split[1] + " " + this.mRegionCode);
            }
        }
    }

    protected String getRegion() {
        return this.mRegionCodeView.getText().toString();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.VALIDATE_USERNAME_EXIST_FINISHED, i.a(getClass(), "validateUserNameExistFinished", d.class));
    }

    private void validatePhoneExist() {
        if (e.a(this.mRegionCode, getPhoneNumber(), this.mPhoneNumberView)) {
            f.a((Context) this, (int) R.string.login_wait_message);
            b.a().a(new com.sds.android.ttpod.framework.base.a.a(a.VALIDATE_USERNAME_EXIST, e.a(this.mRegionCode, getPhoneNumber())));
        }
    }

    public void validateUserNameExistFinished(d dVar) {
        handleValidateResult(dVar);
    }

    public void handleValidateResult(d dVar) {
    }
}
