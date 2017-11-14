package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OnlineMediaItem implements Serializable {
    public static final int FMRADIO_SINGLE_SONG = 1;
    public static final int FMRADIO_SONG_LIST = 2;
    public static final int NORMAL = 0;
    @c(a = "album_name")
    private String mAlbum;
    @c(a = "album_id")
    private long mAlbumId;
    @c(a = "singer_name")
    private String mArtist = "";
    @c(a = "singer_id")
    private long mArtistId;
    @c(a = "ae")
    private AudioEffect mAudioEffect = new AudioEffect();
    @c(a = "audition_list")
    private ArrayList<Url> mAuditionUrls = new ArrayList();
    @c(a = "flag")
    private int mCensorLevel = 0;
    @c(a = "url_list")
    private ArrayList<Url> mDownloadUrls = new ArrayList();
    @c(a = "ll_list")
    private ArrayList<Url> mLLUrls = new ArrayList();
    @c(a = "mv_list")
    private ArrayList<MvListItem> mMVUrls = new ArrayList();
    @c(a = "out_list")
    private ArrayList<OutListItem> mOutList = new ArrayList();
    @c(a = "pick_count")
    private int mPickCount;
    private int mPlayType = 0;
    @c(a = "scm")
    private String mScm = "";
    @c(a = "artist_flag")
    private int mSingerSFlag;
    @c(a = "song_id")
    private long mSongId;
    @c(a = "song_url")
    private String mSongUrl;
    private String mTTFMExtract = "";
    @c(a = "song_name")
    private String mTitle = "";
    @c(a = "vip")
    private int mVip;

    public static class AudioEffect implements Serializable {
        @c(a = "device")
        private String mDevice = "";
        @c(a = "audio_effect")
        private AudioEffectItemData mEffectData = new AudioEffectItemData();
        @c(a = "exp")
        private AudioEffectUserExp mExp = new AudioEffectUserExp();
        @c(a = "_id")
        private String mID = "";
        @c(a = "nick_name")
        private String mNickName = "";
        @c(a = "output")
        private int mOutput = 0;
        @c(a = "pic")
        private String mPic = "";
        @c(a = "pick_count")
        private int mPickCount = 0;
        @c(a = "style")
        private int mStyle = 0;
        @c(a = "user_id")
        private int mUserId = 0;

        public short[] getDataEqualizer() {
            return this.mEffectData.getEqualizer();
        }

        public int getDataBass() {
            return this.mEffectData.getBass();
        }

        public int getDataTreble() {
            return this.mEffectData.getTreble();
        }

        public int getDataVirtualizer() {
            return this.mEffectData.getVirtualizer();
        }

        public int getDataReverb() {
            return this.mEffectData.getReverb();
        }

        public float getDataBalance() {
            return this.mEffectData.getBalance();
        }

        public boolean getDataIsLimit() {
            return this.mEffectData.getIsLimit();
        }

        public int getStyle() {
            return this.mStyle;
        }

        public String getDevice() {
            return this.mDevice;
        }

        public String getId() {
            return this.mID;
        }

        public int getOutput() {
            return this.mOutput;
        }

        public String getNickName() {
            return this.mNickName;
        }

        public String getPic() {
            return this.mPic;
        }

        public int getTotal() {
            return this.mExp.getTotal();
        }

        public int getPickCount() {
            return this.mPickCount;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public String getGenre() {
            return "";
        }

        public short[] getEqualizer() {
            return new short[]{(short) 0};
        }
    }

    public static class OutListItem implements Serializable {
        @c(a = "logo")
        private String mLogo;
        @c(a = "name")
        private String mName;
        @c(a = "url")
        private String mUrl;

        public String getLogoUrl() {
            return this.mLogo;
        }

        public String getName() {
            return this.mName;
        }

        public String getUrl() {
            return this.mUrl;
        }
    }

    public static class Url implements Serializable {
        @c(a = "bitrate")
        private int mBitrate;
        @c(a = "duration")
        private String mDuration = "";
        @c(a = "format")
        private String mFormat = "";
        @c(a = "size")
        private String mSize = "";
        @c(a = "type")
        private int mType;
        @c(a = "type_description")
        private String mTypeDescription = "";
        @c(a = "url")
        private String mUrl = "";

        public Url(String str, String str2, String str3, String str4, int i, String str5) {
            this.mDuration = str;
            this.mSize = str2;
            this.mUrl = str3;
            this.mFormat = str4;
            this.mBitrate = i;
            this.mTypeDescription = str5;
        }

        public String getDuration() {
            return this.mDuration;
        }

        public String getSize() {
            return this.mSize;
        }

        public double getSizeInByte() {
            return parseFileSize(this.mSize);
        }

        private double parseFileSize(String str) {
            if (str == null || str.length() == 0) {
                return 0.0d;
            }
            String replaceAll = str.replaceAll("\\d+.\\d+", "");
            if (replaceAll.equals(str)) {
                replaceAll = str.replaceAll("\\d+", "");
            }
            String replaceAll2 = str.replaceAll(replaceAll, "");
            if (replaceAll2.length() <= 0) {
                return 0.0d;
            }
            if (replaceAll == null || !replaceAll.substring(0, 1).equals("M")) {
                return (replaceAll == null || !replaceAll.substring(0, 1).equals("K")) ? 0.0d : Double.valueOf(replaceAll2).doubleValue() * 1024.0d;
            } else {
                return Double.valueOf(replaceAll2).doubleValue() * 1048576.0d;
            }
        }

        public String getUrl() {
            return this.mUrl;
        }

        public String getFormat() {
            return this.mFormat;
        }

        public int getBitrate() {
            return this.mBitrate;
        }

        public int getType() {
            return this.mType;
        }

        public String getTypeDescription() {
            return this.mTypeDescription;
        }
    }

    public int getPlayType() {
        return this.mPlayType;
    }

    public void setPlayType(int i) {
        this.mPlayType = i;
    }

    public boolean isTtfmRadioSingleSong() {
        return this.mPlayType == 1;
    }

    public boolean isTtfmRadioSongList() {
        return this.mPlayType == 2;
    }

    public String getTTFMExtract() {
        return this.mTTFMExtract;
    }

    public void setTTFMExtract(String str) {
        this.mTTFMExtract = str;
    }

    public ArrayList<OutListItem> getOutList() {
        return this.mOutList;
    }

    public int getCensorLevel() {
        return this.mCensorLevel;
    }

    public String getSongUrl() {
        return this.mSongUrl == null ? "" : this.mSongUrl;
    }

    public long getSongId() {
        return this.mSongId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public long getArtistId() {
        return this.mArtistId;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public long getAlbumId() {
        return this.mAlbumId;
    }

    public void setAlbumId(long j) {
        this.mAlbumId = j;
    }

    private void offsetPickCount(int i) {
        this.mPickCount = Math.max(0, this.mPickCount + i);
    }

    public void increasePickCount() {
        offsetPickCount(1);
    }

    public int getSingerSFlag() {
        return this.mSingerSFlag;
    }

    public void setSingerSFlag(int i) {
        this.mSingerSFlag = i;
    }

    public void decreasePickCount() {
        offsetPickCount(-1);
    }

    public int getPickCount() {
        return this.mPickCount;
    }

    public int getVip() {
        return this.mVip;
    }

    public List<Url> getAuditionUrls() {
        return this.mAuditionUrls;
    }

    public List<Url> getDownloadUrls() {
        return this.mDownloadUrls;
    }

    public List<MvListItem> getMVUrls() {
        return this.mMVUrls;
    }

    public List<Url> getLLUrls() {
        return this.mLLUrls;
    }

    public AudioEffect getAudioEffect() {
        return this.mAudioEffect;
    }

    public void setScm(String str) {
        this.mScm = str;
    }

    public String getScm() {
        return this.mScm;
    }

    public OnlineMediaItem build(long j, String str, long j2, String str2, int i, String str3, int i2, ArrayList<Url> arrayList, ArrayList<Url> arrayList2, ArrayList<MvListItem> arrayList3, ArrayList<Url> arrayList4, AudioEffect audioEffect, int i3, String str4, ArrayList<OutListItem> arrayList5) {
        this.mSongId = j;
        this.mTitle = str;
        this.mArtistId = j2;
        this.mArtist = str2;
        this.mPickCount = i;
        this.mAlbum = str3;
        this.mVip = i2;
        this.mAuditionUrls = arrayList;
        this.mDownloadUrls = arrayList2;
        this.mMVUrls = arrayList3;
        this.mLLUrls = arrayList4;
        this.mAudioEffect = audioEffect;
        this.mCensorLevel = i3;
        this.mSongUrl = str4;
        this.mOutList = arrayList5;
        return this;
    }
}
