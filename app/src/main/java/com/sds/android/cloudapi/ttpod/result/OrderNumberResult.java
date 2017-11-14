package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.sdk.lib.request.BaseResult;

public class OrderNumberResult extends BaseResult {
    @c(a = "data")
    private Data mData;

    public static class Data {
        @c(a = "order_id")
        private String mOrderId;
        @c(a = "order_info")
        private String mOrderInfo;
        @c(a = "user_id")
        private int mUserId;

        public long getUserId() {
            return (long) this.mUserId;
        }

        public String getOrderId() {
            return this.mOrderId;
        }

        public String getOrderInfo() {
            return this.mOrderInfo;
        }
    }

    public Data getData() {
        return this.mData;
    }
}
