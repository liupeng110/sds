package com.sds.android.sdk.lib.request;

/* RequestCallback */
public interface p<R extends BaseResult> {
    void onRequestFailure(R r);

    void onRequestSuccess(R r);
}
