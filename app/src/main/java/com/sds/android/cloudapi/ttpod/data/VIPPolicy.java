package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.util.ArrayList;

public class VIPPolicy {
    private static final String KEY_POLICY = "policy";
    private static final String KEY_PRIZE = "prize";
    private static final String KEY_PRODUCTS = "products";
    private static final String KEY_VALID = "valid";
    @c(a = "policy")
    private ArrayList<Entry> mPolicyEntries;
    @c(a = "prize")
    private String mPrize;
    @c(a = "products")
    private ArrayList<Product> mProducts;
    @c(a = "valid")
    private boolean mValid = false;

    public static class Entry {
        private static final int DEFAULT_MAX_BITRATE = 64;
        private static final String KEY_LEVEL = "level";
        private static final String KEY_MAX_AUDITION_BITRATE = "max_audition_bitrate";
        private static final String KEY_MAX_CACHE_COUNT = "max_cache_count";
        private static final String KEY_MAX_DOWNLOAD_BITRATE = "max_download_bitrate";
        private static final String KEY_MAX_DOWNLOAD_COUNT = "max_download_count";
        public static final int MAX_LIMIT = Integer.MAX_VALUE;
        @c(a = "level")
        private int mLevel = 0;
        @c(a = "max_audition_bitrate")
        private int mMaxAuditionBitRate = 64;
        @c(a = "max_cache_count")
        private int mMaxCacheCount = 1;
        @c(a = "max_download_bitrate")
        private int mMaxDownloadBitRate = 0;
        @c(a = "max_download_count")
        private int mMaxDownloadCount = 0;

        public int getLevel() {
            return this.mLevel;
        }

        public int getMaxAuditionBitRate() {
            return this.mMaxAuditionBitRate >= 0 ? this.mMaxAuditionBitRate : MAX_LIMIT;
        }

        public int getMaxCacheCount() {
            return this.mMaxCacheCount >= 0 ? this.mMaxCacheCount : MAX_LIMIT;
        }

        public int getMaxDownloadBitRate() {
            return this.mMaxDownloadBitRate >= 0 ? this.mMaxDownloadBitRate : MAX_LIMIT;
        }

        public int getMaxDownloadCount() {
            return this.mMaxDownloadCount >= 0 ? this.mMaxDownloadCount : MAX_LIMIT;
        }
    }

    public static class Product {
        private static final String KEY_DESC = "desc";
        private static final String KEY_DISCOUNT = "discount";
        private static final String KEY_PRICE = "price";
        private static final String KEY_PRODUCT_ID = "product_id";
        @c(a = "desc")
        private String mDesc = "";
        @c(a = "discount")
        private String mDiscount = "";
        @c(a = "price")
        private int mPrice = 0;
        @c(a = "product_id")
        private int mProductId = 0;

        public int getProductId() {
            return this.mProductId;
        }

        public int getPrice() {
            return this.mPrice;
        }

        public String getDesc() {
            return this.mDesc;
        }

        public String getDiscount() {
            return this.mDiscount;
        }
    }

    public boolean isValid() {
        return this.mValid;
    }

    public String getPrize() {
        return this.mPrize;
    }

    public ArrayList<Entry> getPolicyEntries() {
        return this.mPolicyEntries;
    }

    public ArrayList<Product> getProducts() {
        return this.mProducts;
    }
}
