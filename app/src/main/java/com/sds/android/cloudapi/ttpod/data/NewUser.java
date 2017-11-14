package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.sds.android.sdk.lib.b.b;
import java.io.Serializable;

public class NewUser extends b {
    public static final int ACTIVE = 1;
    public static final int CONFIDENTIAL = 2;
    public static final int DELETED = -1;
    public static final int EMAIL_BOUND = 1;
    public static final int EMAIL_UNBOUND = 0;
    public static final int FAMOUS_PERSON = 2;
    public static final int FEMALE = 0;
    public static final int FROZEN = 2;
    public static final int INACTIVE = 0;
    public static final String LOCAL_LOGIN = "local";
    public static final int MALE = 1;
    public static final int MOBILE_BOUND = 1;
    public static final int MOBILE_UNBOUND = 0;
    public static final int NOT_MEMBER = 0;
    public static final int OPERATION_STAFF = 1;
    public static final int ORDINARY_MEMBER = 1;
    public static final int ORDINARY_USER = 3;
    public static final int PASSWORD_RESET = 3;
    public static final int PREMIUM_MEMBER = 2;
    public static final String QQ_LOGIN = "qq";
    public static final String SINA_LOGIN = "sina";
    public static final String WECHAT_LOGIN = "wchat";
    @c(a = "pic")
    private String mAvatarUrl = "";
    @c(a = "birthday")
    private long mBirthday = 0;
    @c(a = "coverPic")
    private String mCoverPic = "";
    @c(a = "createAt")
    private long mCreatedTime;
    @c(a = "email")
    private String mEmail = "";
    @c(a = "emailBind")
    private int mEmailBind = 0;
    private int mFollowersCount;
    private int mFollowersRank;
    private int mFollowingsCount;
    @c(a = "vip")
    private int mMemberLevel = 0;
    @c(a = "mobile")
    private String mMobile = "";
    @c(a = "mobileBind")
    private int mMobileBind = 0;
    @c(a = "nickName")
    private String mNickName = "";
    @c(a = "priv")
    private int mPriv = 3;
    @c(a = "profile")
    private Profile mProfile = new Profile();
    @c(a = "sex")
    private int mSex = 2;
    @c(a = "status")
    private int mStatus = 1;
    @c(a = "id")
    private long mUserId;
    @c(a = "userName")
    private String mUserName = "";
    @c(a = "via")
    private String mVia = "";
    @c(a = "vipExpire")
    private long mVipExpireTime;

    private class Profile implements Serializable {
        @c(a = "city")
        private String mCity;
        @c(a = "country")
        private String mCountry;
        @c(a = "region")
        private String mRegion;
        @c(a = "signature")
        private String mSignature;

        private Profile() {
            this.mSignature = "";
            this.mCountry = "";
            this.mCity = "";
            this.mRegion = "";
        }

        public String getSignature() {
            return this.mSignature;
        }

        public void setSignature(String str) {
            this.mSignature = str;
        }

        public String getCountry() {
            return this.mCountry;
        }

        public void setCountry(String str) {
            this.mCountry = str;
        }

        public String getCity() {
            return this.mCity;
        }

        public void setCity(String str) {
            this.mCity = str;
        }

        public String getRegion() {
            return this.mRegion;
        }

        public void setRegion(String str) {
            this.mRegion = str;
        }
    }

    public Profile getUserProfile() {
        return this.mProfile;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public void setUserName(String str) {
        this.mUserName = str;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public long getCreatedTime() {
        return this.mCreatedTime;
    }

    public void setCreatedTime(long j) {
        this.mCreatedTime = j;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.mAvatarUrl = str;
    }

    public int getSex() {
        return this.mSex;
    }

    public void setSex(int i) {
        this.mSex = i;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String str) {
        this.mEmail = str;
    }

    public int getEmailBind() {
        return this.mEmailBind;
    }

    public void setEmailBind(int i) {
        this.mEmailBind = i;
    }

    public String getMobile() {
        return this.mMobile;
    }

    public void setMobile(String str) {
        this.mMobile = str;
    }

    public int getMobileBind() {
        return this.mMobileBind;
    }

    public void setMobileBind(int i) {
        this.mMobileBind = i;
    }

    public String getVia() {
        return this.mVia;
    }

    public void setVia(String str) {
        this.mVia = str;
    }

    public int getPriv() {
        return this.mPriv;
    }

    public void setPriv(int i) {
        this.mPriv = i;
    }

    public int getMemberLevel() {
        return this.mMemberLevel;
    }

    public void setMemberLevel(int i) {
        this.mMemberLevel = i;
    }

    public long getVipExpireTime() {
        return this.mVipExpireTime;
    }

    public void setVipExpireTime(long j) {
        this.mVipExpireTime = j;
    }

    public String getCoverPic() {
        return this.mCoverPic;
    }

    public void setCoverPic(String str) {
        this.mCoverPic = str;
    }

    public long getBirthday() {
        return this.mBirthday;
    }

    public void setBirthday(long j) {
        this.mBirthday = j;
    }

    public String getSignature() {
        return this.mProfile.getSignature();
    }

    public void setSignature(String str) {
        this.mProfile.setSignature(str);
    }

    public String getCountry() {
        return this.mProfile.getCountry();
    }

    public void setCountry(String str) {
        this.mProfile.setCountry(str);
    }

    public String getCity() {
        return this.mProfile.getCity();
    }

    public void setCity(String str) {
        this.mProfile.setCity(str);
    }

    public String getRegion() {
        return this.mProfile.getRegion();
    }

    public void setRegion(String str) {
        this.mProfile.setRegion(str);
    }

    public int getFollowersRank() {
        return this.mFollowersRank;
    }

    public void setFollowersRank(int i) {
        this.mFollowersRank = i;
    }

    public int getFollowersCount() {
        return this.mFollowersCount;
    }

    public int getFollowingsCount() {
        return this.mFollowingsCount;
    }

    public void setFollowersCount(int i) {
        this.mFollowersCount = i;
    }

    public void setFollowingsCount(int i) {
        this.mFollowingsCount = i;
    }
}
