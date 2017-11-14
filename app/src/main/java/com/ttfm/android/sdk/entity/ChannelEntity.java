package com.ttfm.android.sdk.entity;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;

public class ChannelEntity implements Parcelable, Serializable {
    public static final int CIType_AB = 3;
    public static final int CIType_Music = 1;
    public static final int CIType_NJ = 2;
    private static final long serialVersionUID = 1;
    private String ciAuditionFile = "";
    private String ciBackgroundImg = "";
    private String ciCode = "";
    private String ciCreateTime = "";
    private long ciCreatorId;
    private int ciId;
    private String ciIntro = "";
    private String ciName = "";
    private int ciPlayScore = 0;
    private int ciScore = 0;
    private String ciSongUri = "";
    private int ciTopicScore = 0;
    private int ciType;
    private String collectCount = "";
    private ArrayList<Contributes> contributor = new ArrayList();
    private int isCollected;
    private boolean isPlayingCache;
    private ArrayList<LabelInfo> labelInfos = new ArrayList();
    private long lastModifyTime;
    private int liType = 0;
    private ArrayList<ChannelUser> manager = new ArrayList();
    private String newSongCount = "";
    private String newTopicCount = "";
    private ChannelSettings settings;
    private String songCount = "";
    private String topicCount = "";
    private int ucccId;

    public class ChannelSettings implements Serializable {
        private static final long serialVersionUID = 1;
        private int cisAllowAddSong;
        private int cisAllowPosted;
        private int cisAllowReply;
        private int cisSelectPlay;
    }

    public class ChannelUser implements Serializable {
        public static final long serialVersionUID = 1;
        public String nickName = "";
        public int role = 2;
        public long uId;
        public String userPic = "";

        public String getuserPic() {
            return this.userPic;
        }

        public String getnickName() {
            return this.nickName;
        }

        public long getuId() {
            return this.uId;
        }
    }

    public class Contributes implements Serializable {
        private static final long serialVersionUID = 1;
        public int songCounts;
        public ChannelUser user;

        public Contributes() {
            this.user = new ChannelUser();
        }

        public long getUserUID() {
            return this.user != null ? this.user.uId : -1;
        }

        public String getUserPic() {
            return this.user != null ? this.user.userPic : "";
        }

        public String getUserNickName() {
            return this.user != null ? this.user.nickName : "";
        }

        public int getSongCounts() {
            return this.songCounts;
        }
    }

    public static class LabelInfo implements Serializable {
        private static final long serialVersionUID = 1;
        private long liId = 0;
        private String liName = "";
        private String liValue = "";

        public long getLiId() {
            return this.liId;
        }

        public void setLiId(long j) {
            this.liId = j;
        }

        public String getLiValue() {
            return this.liValue;
        }

        public void setLiValue(String str) {
            this.liValue = str;
        }

        public String getLiName() {
            return this.liName;
        }

        public void setLiName(String str) {
            this.liName = str;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ciId);
    }

    private void readFromParcel(Parcel parcel) {
    }

    public String getCiSongUri() {
        return this.ciSongUri;
    }

    public void setCiSongUri(String str) {
        this.ciSongUri = str;
    }

    public String getChannelName() {
        return this.ciName;
    }

    public void setChannelName(String str) {
        this.ciName = str;
    }

    public String getChannelCreateTime() {
        return this.ciCreateTime;
    }

    public void setChannelCreateTime(String str) {
        this.ciCreateTime = str;
    }

    public int getChannelId() {
        return this.ciId;
    }

    public void setChannelId(int i) {
        this.ciId = i;
    }

    public String getChannelBackgroundImg() {
        return this.ciBackgroundImg;
    }

    public void setChannelBackgroundImg(String str) {
        this.ciBackgroundImg = str;
    }

    public String getCiCode() {
        return this.ciCode;
    }

    public void setCiCode(String str) {
        this.ciCode = str;
    }

    public String getChannelIntro() {
        return this.ciIntro;
    }

    public void setChannelIntro(String str) {
        this.ciIntro = str;
    }

    public boolean isCollected() {
        return this.isCollected == 1;
    }

    public void setCollected(boolean z) {
        if (z) {
            this.isCollected = 1;
        } else {
            this.isCollected = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ChannelEntity) && this.ciId == ((ChannelEntity) obj).getChannelId()) {
            return true;
        }
        return false;
    }

    public int getCollectId() {
        return this.ucccId;
    }

    public void setCollectId(int i) {
        this.ucccId = i;
    }

    public long getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(long j) {
        this.lastModifyTime = j;
    }

    public int getCiType() {
        return this.ciType;
    }

    public void setCiType(int i) {
        this.ciType = i;
    }

    public String getCiAuditionFile() {
        return this.ciAuditionFile;
    }

    public void setCiAuditionFile(String str) {
        this.ciAuditionFile = str;
    }

    public long getCiCreatorId() {
        return this.ciCreatorId;
    }

    public void setCiCreatorId(long j) {
        this.ciCreatorId = j;
    }

    public int getciScore() {
        return this.ciScore;
    }

    public int getCiScore() {
        return this.ciScore;
    }

    public void setCiScore(int i) {
        this.ciScore = i;
    }

    public int getCiPlayScore() {
        return this.ciPlayScore;
    }

    public void setCiPlayScore(int i) {
        this.ciPlayScore = i;
    }

    public int getCiTopicScore() {
        return this.ciTopicScore;
    }

    public void setCiTopicScore(int i) {
        this.ciTopicScore = i;
    }

    public String getTopicCount() {
        return this.topicCount;
    }

    public void setTopicCount(String str) {
        this.topicCount = str;
    }

    public String getSongCount() {
        return this.songCount;
    }

    public void setSongCount(String str) {
        this.songCount = str;
    }

    public String getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(String str) {
        this.collectCount = str;
    }

    public ArrayList<Contributes> getContributor() {
        return this.contributor;
    }

    public void setContributor(ArrayList<Contributes> arrayList) {
        this.contributor = arrayList;
    }

    public ArrayList<ChannelUser> getManager() {
        return this.manager;
    }

    public void setManager(ArrayList<ChannelUser> arrayList) {
        this.manager = arrayList;
    }

    public ChannelUser getCreator() {
        if (this.manager != null) {
            for (int i = 0; i < this.manager.size(); i++) {
                ChannelUser channelUser = (ChannelUser) this.manager.get(i);
                if (channelUser != null && channelUser.role == 1) {
                    return channelUser;
                }
            }
        }
        return null;
    }

    public boolean getcisAllowReply() {
        if (this.settings != null) {
            return this.settings.cisAllowReply == 1;
        } else {
            return false;
        }
    }

    public boolean getcisAllowAddSong() {
        if (this.settings != null) {
            return this.settings.cisAllowAddSong == 1;
        } else {
            return false;
        }
    }

    public boolean getcisSelectPlay() {
        if (this.settings != null) {
            return this.settings.cisSelectPlay == 1;
        } else {
            return false;
        }
    }

    public boolean getcisAllowPosted() {
        if (this.settings != null) {
            return this.settings.cisAllowPosted == 1;
        } else {
            return false;
        }
    }

    public String getNewSongCount() {
        return this.newSongCount;
    }

    public void setNewSongCount(String str) {
        this.newSongCount = str;
    }

    public String getNewTopicCount() {
        return this.newTopicCount;
    }

    public void setNewTopicCount(String str) {
        this.newTopicCount = str;
    }

    public boolean isPlayingCache() {
        return this.isPlayingCache;
    }

    public void setPlayingCache(boolean z) {
        this.isPlayingCache = z;
    }

    public ArrayList<LabelInfo> getLabelInfos() {
        return this.labelInfos;
    }

    public void setLabelInfos(ArrayList<LabelInfo> arrayList) {
        this.labelInfos = arrayList;
    }

    public int getLiType() {
        return this.liType;
    }

    public void setLiType(int i) {
        this.liType = i;
    }

    public boolean isHead() {
        return this.liType != 0;
    }
}
