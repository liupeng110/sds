package com.ttfm.android.sdk.entity;

import java.io.Serializable;

public class ChannelSongListEntity implements Serializable {
    private static final long serialVersionUID = 1;
    private long csCiId;
    private String csCreateTime;
    private long csCreatorId;
    private long csId = 0;
    private long csPlayCount;
    private long csPlayCountTotal;
    private long csSiId;
    private ChannelSong song = null;
    private ChannelSongUser user = null;

    public class ChannelSong implements Serializable {
        private static final long serialVersionUID = 1;
        private String siAlbum = "";
        private long siId;
        private String siIntro = "";
        private String siName = "";
        private String siSinger = "";
        private long siSingerTtid;
        private long siTtid;

        public long getSiTtid() {
            return this.siTtid;
        }

        public void setSiTtid(long j) {
            this.siTtid = j;
        }

        public String getSiName() {
            return this.siName;
        }

        public void setSiName(String str) {
            this.siName = str;
        }

        public String getSiAlbum() {
            return this.siAlbum;
        }

        public void setSiAlbum(String str) {
            this.siAlbum = str;
        }

        public String getSiSinger() {
            return this.siSinger;
        }

        public void setSiSinger(String str) {
            this.siSinger = str;
        }

        public long getSiId() {
            return this.siId;
        }

        public void setSiId(long j) {
            this.siId = j;
        }

        public String getSiIntro() {
            return this.siIntro;
        }

        public void setSiIntro(String str) {
            this.siIntro = str;
        }

        public long getSiSingerTtid() {
            return this.siSingerTtid;
        }

        public void setSiSingerTtid(long j) {
            this.siSingerTtid = j;
        }
    }

    public class ChannelSongUser implements Serializable {
        private static final long serialVersionUID = 1;
        private String nickName = "";
        private long uId;
        private String userPic = "";

        public String getUserPic() {
            return this.userPic;
        }

        public void setUserPic(String str) {
            this.userPic = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public long getuId() {
            return this.uId;
        }

        public void setuId(long j) {
            this.uId = j;
        }
    }

    public long getCsId() {
        return this.csId;
    }

    public void setCsId(long j) {
        this.csId = j;
    }

    public long getCsCreatorId() {
        return this.csCreatorId;
    }

    public void setCsCreatorId(long j) {
        this.csCreatorId = j;
    }

    public long getCsSiId() {
        return this.csSiId;
    }

    public void setCsSiId(long j) {
        this.csSiId = j;
    }

    public String getCsCreateTime() {
        return this.csCreateTime;
    }

    public void setCsCreateTime(String str) {
        this.csCreateTime = str;
    }

    public long getCsCiId() {
        return this.csCiId;
    }

    public void setCsCiId(long j) {
        this.csCiId = j;
    }

    public long getCsPlayCount() {
        return this.csPlayCount;
    }

    public void setCsPlayCount(long j) {
        this.csPlayCount = j;
    }

    public long getCsPlayCountTotal() {
        return this.csPlayCountTotal;
    }

    public void setCsPlayCountTotal(long j) {
        this.csPlayCountTotal = j;
    }

    public ChannelSong getSong() {
        return this.song;
    }

    public void setSong(ChannelSong channelSong) {
        this.song = channelSong;
    }

    public String getSongName() {
        return this.song != null ? this.song.siName : "";
    }

    public String getSingerName() {
        return this.song != null ? this.song.siSinger : "";
    }

    public long getSiId() {
        return this.song != null ? this.song.getSiId() : 0;
    }

    public ChannelSongUser getUser() {
        return this.user;
    }

    public void setUser(ChannelSongUser channelSongUser) {
        this.user = channelSongUser;
    }
}
