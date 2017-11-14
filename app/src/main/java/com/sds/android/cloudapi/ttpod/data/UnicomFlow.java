package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;

public class UnicomFlow implements Serializable {
    @c(a = "attention")
    private String mAttention = "";
    @c(a = "trial")
    private boolean mIsTrial;
    @c(a = "valid")
    private boolean mIsValidOpen;
    @c(a = "status_open")
    private int mOpenStatus;
    @c(a = "time_open")
    private String mOpenTime = "";
    @c(a = "phone")
    private String mPhone = "";
    @c(a = "tariff")
    private String mPrice = "";
    @c(a = "time_server")
    private String mServerTime = "";
    @c(a = "token")
    private String mToken = "";
    @c(a = "overdue")
    private String mTrialDay = "";
    @c(a = "status_trial")
    private int mTrialStatus;
    @c(a = "time_trial")
    private String mTrialTime = "";
    @c(a = "time_unsubscribe")
    private String mUnsubscribeTime = "";

    public int getTrialStatus() {
        return this.mTrialStatus;
    }

    public int getOpenStatus() {
        return this.mOpenStatus;
    }

    public String getTrialTime() {
        return this.mTrialTime;
    }

    public String getServerTime() {
        return this.mServerTime;
    }

    public String getOpenTime() {
        return this.mOpenTime;
    }

    public String getUnsubscribeTime() {
        return this.mUnsubscribeTime;
    }

    public boolean isTrial() {
        return this.mIsTrial;
    }

    public boolean isValidOpen() {
        return this.mIsValidOpen;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getTrialDay() {
        return this.mTrialDay;
    }

    public String getAttention() {
        return this.mAttention;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getToken() {
        return this.mToken;
    }
}
