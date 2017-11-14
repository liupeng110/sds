package com.sds.android.ttpod.framework.modules.core.f;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;

/* UserInfoUtil */
public class b {
    public static NewUser a(TTPodUser tTPodUser) {
        int i = 1;
        NewUser newUser = new NewUser();
        newUser.setUserId(tTPodUser.getUserId());
        newUser.setCoverPic(tTPodUser.getProfileCoverUrl());
        newUser.setAvatarUrl(tTPodUser.getAvatarUrl());
        newUser.setNickName(tTPodUser.getNickName());
        newUser.setUserName(tTPodUser.getUserName());
        newUser.setSex(tTPodUser.getSex());
        newUser.setBirthday(tTPodUser.getBirthdayInSecond());
        newUser.setCreatedTime(tTPodUser.getCreateAtInSecond());
        newUser.setVipExpireTime(tTPodUser.getVipExpiresAt());
        newUser.setEmail(tTPodUser.getUserName());
        newUser.setVia(tTPodUser.getVia());
        int i2 = (tTPodUser.getIsLocalBind() == null || FeedbackItem.STATUS_WAITING.equals(tTPodUser.getIsLocalBind().toString())) ? 1 : 0;
        if (i2 == 0) {
            i = 0;
        }
        newUser.setEmailBind(i);
        newUser.setMemberLevel(tTPodUser.getVipLevel());
        newUser.setMobileBind(0);
        newUser.setPriv(tTPodUser.isVerified() ? 2 : 3);
        newUser.setFollowersCount(tTPodUser.getFollowersCount());
        newUser.setFollowersRank(tTPodUser.getFollowersRank());
        newUser.setFollowingsCount(tTPodUser.getFollowingsCount());
        return newUser;
    }
}
