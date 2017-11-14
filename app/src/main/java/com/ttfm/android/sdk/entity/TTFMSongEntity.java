package com.ttfm.android.sdk.entity;

import java.io.Serializable;

public class TTFMSongEntity implements Serializable {
    public static final int BITRATE_DEFAULT = 320;
    private static final long serialVersionUID = 1;
    private int Bitrate = BITRATE_DEFAULT;
    private int Duration;
    private String FileName = "";
    private long FileSize;
    private String Format = "";
    private long MusicID;
    private String PlugInName;
    private int PluginID;
    private int Progress;
    private long SerialNo;
    private String Singer = "";
    private String Song = "";
    private long TTSingerID;
    private long TTSongID;
    private String URL = "";
    private int channelID;
    private String channelName = "";
    private long downloadSize;
    private boolean isCollected = false;
    private boolean isHate;
    private boolean isPlugIn;
    private boolean isSkipped;
    private boolean isUsed;
    private boolean isZan;
    private long lastModifyDate;
    private long lastPlayDate;
    private int lastPlayTime;
    private int playAtChannelId;
    private int tryTimes;
    private long urlGetTime;

    public String getURL() {
        return this.URL;
    }

    public void setURL(String str) {
        this.URL = str;
    }

    public String getSinger() {
        return this.Singer;
    }

    public void setSinger(String str) {
        this.Singer = str;
    }

    public int getDuration() {
        return this.Duration;
    }

    public void setDuration(int i) {
        this.Duration = i;
    }

    public long getMusicID() {
        return this.MusicID;
    }

    public void setMusicID(long j) {
        this.MusicID = j;
    }

    public long getSingerID() {
        return this.TTSingerID;
    }

    public void setSingerID(long j) {
        this.TTSingerID = j;
    }

    public String getSong() {
        return this.Song;
    }

    public void setSong(String str) {
        this.Song = str;
    }

    public String getFormat() {
        return this.Format;
    }

    public void setFormat(String str) {
        this.Format = str;
    }

    public long getFileSize() {
        return this.FileSize;
    }

    public void setFileSize(long j) {
        this.FileSize = j;
    }

    public String getFileName() {
        return this.FileName;
    }

    public void setFileName(String str) {
        this.FileName = str;
    }

    public String toString() {
        return "musicID:" + this.TTSongID + ",song:" + this.Song + ",format:" + this.Format + ",duration:" + this.Duration + ",url:" + this.URL + ",singerID:" + this.TTSingerID + ",singer:" + this.Singer;
    }

    public int getLastPlayTime() {
        return this.lastPlayTime;
    }

    public void setLastPlayTime(int i) {
        this.lastPlayTime = i;
    }

    public long getDownloadSize() {
        return this.downloadSize;
    }

    public void setDownloadSize(long j) {
        this.downloadSize = j;
    }

    public boolean isDownloadFinish() {
        return this.FileSize > 0 && this.downloadSize >= this.FileSize;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof TTFMSongEntity) && ((TTFMSongEntity) obj).getMusicID() == this.MusicID) {
            return true;
        }
        return false;
    }

    public boolean isSkipped() {
        return this.isSkipped;
    }

    public void setSkipped(boolean z) {
        this.isSkipped = z;
    }

    public boolean isCollected() {
        return this.isCollected;
    }

    public void setCollected(boolean z) {
        this.isCollected = z;
    }

    public long getTTSongID() {
        return this.TTSongID;
    }

    public void setTTSongID(long j) {
        this.TTSongID = j;
    }

    public long getTTSingerID() {
        return this.TTSingerID;
    }

    public void setTTSingerID(long j) {
        this.TTSingerID = j;
    }

    public int getChannelID() {
        return this.channelID;
    }

    public void setChannelID(int i) {
        this.channelID = i;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String str) {
        this.channelName = str;
    }

    public boolean isPlugIn() {
        return this.isPlugIn;
    }

    public void setPlugIn(boolean z) {
        this.isPlugIn = z;
    }

    public int getPluginID() {
        return this.PluginID;
    }

    public void setPluginID(int i) {
        this.PluginID = i;
    }

    public String getPlugInName() {
        return this.PlugInName;
    }

    public void setPlugInName(String str) {
        this.PlugInName = str;
    }

    public long getSerialNo() {
        return this.SerialNo;
    }

    public void setSerialNo(long j) {
        this.SerialNo = j;
    }

    public int getProgress() {
        return this.Progress;
    }

    public void setProgress(int i) {
        this.Progress = i;
    }

    public int getTryTimes() {
        return this.tryTimes;
    }

    public void setTryTimes(int i) {
        this.tryTimes = i;
    }

    public int getBitrate() {
        return this.Bitrate;
    }

    public void setBitrate(int i) {
        if (i <= 0) {
            i = BITRATE_DEFAULT;
        }
        this.Bitrate = i;
    }

    public long getLastModifyDate() {
        return this.lastModifyDate;
    }

    public void setLastModifyDate(long j) {
        this.lastModifyDate = j;
    }

    public boolean isUsed() {
        return this.isUsed;
    }

    public void setUsed(boolean z) {
        this.isUsed = z;
    }

    public long getLastPlayDate() {
        return this.lastPlayDate;
    }

    public void setLastPlayDate(long j) {
        this.lastPlayDate = j;
    }

    public long getUrlGetTime() {
        return this.urlGetTime;
    }

    public void setUrlGetTime(long j) {
        this.urlGetTime = j;
    }

    public boolean isZan() {
        return this.isZan;
    }

    public void setZan(boolean z) {
        this.isZan = z;
    }

    public boolean isHate() {
        return this.isHate;
    }

    public void setHate(boolean z) {
        this.isHate = z;
    }

    public int getPlayAtChannelId() {
        return this.playAtChannelId;
    }

    public void setPlayAtChannelId(int i) {
        this.playAtChannelId = i;
    }
}
