package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    public static final int FEMALE = 0;
    private static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_AVATAR = "pic";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_COVER = "cover_url";
    private static final String KEY_CREATE_AT = "create_at";
    private static final String KEY_EXPIRE_AT = "expires_at";
    private static final String KEY_INFO = "info";
    private static final String KEY_IS_LOCAL_BIND = "is_local_bind";
    public static final String KEY_NICK_NAME = "nick_name";
    private static final String KEY_OLD_NAME = "old_name";
    public static final String KEY_SEX = "sex";
    public static final String KEY_USER_EMAIL = "user_name";
    public static final String KEY_USER_ID = "tuid";
    private static final String KEY_VIA = "via";
    private static final String KEY_VIP = "vip";
    private static final String KEY_VIP_EXPIRES_AT = "vip_expires_at";
    private static final String KEY_WEIBO_ENABLED = "weibo_enabled";
    public static final int MALE = 1;
    private static final long serialVersionUID = 1;
    @c(a = "access_token")
    private String mAccessToken;
    @c(a = "pic")
    private String mAvatarUrl = "";
    @c(a = "birthday")
    private long mBirthdayInSecond = 0;
    @c(a = "cover_url")
    private String mCoverUrl = "";
    @c(a = "create_at")
    private long mCreateAtInSecond;
    @c(a = "expires_at")
    private long mExpiresAtSecond;
    @c(a = "info")
    private Map<String, Object> mInfo;
    @c(a = "is_local_bind")
    private Object mIsLocalBind;
    @c(a = "nick_name")
    private String mNickName = "";
    @c(a = "old_name")
    private String mOldName;
    @c(a = "sex")
    private int mSex = 1;
    @c(a = "tuid")
    private long mUserId;
    @c(a = "user_name")
    private String mUserName = "";
    @c(a = "via")
    private String mVia;
    @c(a = "vip_expires_at")
    private long mVipExpiresAt;
    @c(a = "vip")
    private int mVipLevel;
    @c(a = "weibo_enabled")
    private boolean mWeiboEnabled;

    public String getUserName() {
        return this.mUserName;
    }

    public String getProfileCoverUrl() {
        return this.mCoverUrl;
    }

    public int getSex() {
        return this.mSex;
    }

    public long getBirthdayInSecond() {
        return this.mBirthdayInSecond;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public long getUserId() {
        return this.mUserId;
    }

    public void setUserName(String str) {
        this.mUserName = str;
    }

    public void setProfileCoverUrl(String str) {
        this.mCoverUrl = str;
    }

    public void setSex(int i) {
        if (!(i == 1 || i == 0)) {
            i = this.mSex;
        }
        this.mSex = i;
    }

    public void setBirthday(long j) {
        this.mBirthdayInSecond = j;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public void setAvatarUrl(String str) {
        this.mAvatarUrl = str;
    }

    public void setUserId(long j) {
        this.mUserId = j;
    }

    public String getOldName() {
        return this.mOldName == null ? "" : this.mOldName;
    }

    public void setOldName(String str) {
        this.mOldName = str;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public long getCreateAtInSecond() {
        return this.mCreateAtInSecond;
    }

    public void setCreateAtInSecond(long j) {
        this.mCreateAtInSecond = j;
    }

    public long getExpiresAtSecond() {
        return this.mExpiresAtSecond;
    }

    public void setExpiresAtSecond(long j) {
        this.mExpiresAtSecond = j;
    }

    public Object getIsLocalBind() {
        try {
            if (this.mIsLocalBind != null) {
                if (this.mIsLocalBind instanceof Number) {
                    return Integer.valueOf(((Number) this.mIsLocalBind).intValue());
                }
                if (this.mIsLocalBind instanceof Boolean) {
                    return Integer.valueOf(((Boolean) this.mIsLocalBind).booleanValue() ? 0 : 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.mIsLocalBind;
    }

    public void setIsLocalBind(Object obj) {
        this.mIsLocalBind = obj;
    }

    public String getVia() {
        return this.mVia;
    }

    public void setVia(String str) {
        this.mVia = str;
    }

    public int getVipLevel() {
        return this.mVipLevel;
    }

    public long getVipExpiresAt() {
        return this.mVipExpiresAt;
    }

    public boolean getWeiboEnabled() {
        return this.mWeiboEnabled;
    }

    public void setWeiboEnabled(boolean z) {
        this.mWeiboEnabled = z;
    }

    public Map<String, Object> getInfo() {
        return this.mInfo == null ? new HashMap() : this.mInfo;
    }

    public void setInfo(Map<String, Object> map) {
        this.mInfo = map;
    }
}
