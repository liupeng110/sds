package com.sds.android.ttpod.fragment.settings.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.b.b;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.a;
import java.lang.reflect.Method;
import java.util.Map;

public class FeedbackComposeFragment extends BaseFragment implements OnClickListener {
    private static final int FEEDBACK_CONTACTWAY_LENGTH_LIMIT = 40;
    private static final int FEEDBACK_CONTENT_LENGTH_LIMIT = 5;
    private static final int REQUEST_FILE = 2;
    private static final int REQUEST_PICTURE = 1;
    private Button mBtnSend;
    private EditText mEtContactWay;
    private EditText mEtContent;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_feedback_compose, null);
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.PROPOSAL_FEEDBACK_FINISH, i.a(getClass(), "onProposalFinished", b.class, FeedbackItem.class));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mEtContent = (EditText) view.findViewById(R.id.et_feedback);
        this.mEtContactWay = (EditText) view.findViewById(R.id.et_contactway);
        this.mBtnSend = (Button) view.findViewById(R.id.btn_send_feedback);
        this.mBtnSend.setOnClickListener(this);
    }

    public void onProposalFinished(b bVar, FeedbackItem feedbackItem) {
        if (bVar.getCode() == SecExceptionCode.SEC_ERROR_STA_STORE_INVALID_PARAM) {
            f.a((int) R.string.feedback_send_complete);
            this.mEtContent.setText("");
        } else {
            f.a((int) R.string.feedback_send_fail);
        }
        this.mBtnSend.setText(R.string.submit_feedback);
        this.mBtnSend.setClickable(true);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_feedback:
                if (c.e()) {
                    String trim = this.mEtContent.getText().toString().trim();
                    String trim2 = this.mEtContactWay.getText().toString().trim();
                    if (trim.length() <= 5) {
                        f.a((int) R.string.send_feedback_content_too_short);
                        return;
                    } else if (trim2.length() > FEEDBACK_CONTACTWAY_LENGTH_LIMIT) {
                        f.a((int) R.string.send_feedback_contactway_too_long);
                        return;
                    } else {
                        this.mBtnSend.setClickable(false);
                        this.mBtnSend.setText(R.string.feedback_sending);
                        FeedbackItem feedbackItem = new FeedbackItem(trim, c.f().toString(), trim2);
                        com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(a.PROPOSAL_FEEDBACK, feedbackItem));
                        return;
                    }
                }
                f.a((int) R.string.network_error);
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
