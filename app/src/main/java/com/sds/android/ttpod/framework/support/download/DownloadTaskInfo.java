package com.sds.android.ttpod.framework.support.download;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sds.android.sdk.core.download.TaskInfo;
import com.sds.android.sdk.core.download.b.b;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.io.Serializable;

public class DownloadTaskInfo extends TaskInfo {
    public static final Creator<DownloadTaskInfo> CREATOR = new Creator<DownloadTaskInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public DownloadTaskInfo a(Parcel parcel) {
            return new DownloadTaskInfo(parcel);
        }

        public DownloadTaskInfo[] a(int i) {
            return new DownloadTaskInfo[i];
        }
    };
    public static final Integer TYPE_APP = Integer.valueOf(TYPE_SKIN.intValue() + 1);
    public static final Integer TYPE_AUDIO = Integer.valueOf(0);
    public static final Integer TYPE_BACKGROUND = Integer.valueOf(TYPE_FAVORITE_SONG_LIST.intValue() + 1);
    public static final Integer TYPE_FAVORITE_SONG = Integer.valueOf(TYPE_PLUGIN.intValue() + 1);
    public static final Integer TYPE_FAVORITE_SONG_LIST = Integer.valueOf(TYPE_FAVORITE_SONG.intValue() + 1);
    public static final Integer TYPE_PLUGIN = Integer.valueOf(TYPE_APP.intValue() + 1);
    public static final Integer TYPE_SKIN = Integer.valueOf(TYPE_VIDEO.intValue() + 1);
    public static final Integer TYPE_VIDEO = Integer.valueOf(TYPE_AUDIO.intValue() + 1);
    private Long mAddTime;
    private String mAlibabaOrigin;
    private String mAudioQuality;
    private Long mCompleteTime;
    private Long mConnectTimeStamp;
    private Integer mCutOffTimes;
    private b mDownloadError;
    private Long mDownloadTime;
    private String mFileFormat;
    private Long mFileId;
    private String mFileName;
    private String mGroupId;
    private String mListId;
    private int mListType;
    private String mOrigin;
    private Integer mPosition;
    private Long mRespondTime;
    private String mScm;
    private long mSingerID;
    private int mSongType;
    private Bundle mTagBundle;
    private Integer mType;
    private boolean mUrlUpdated;

    public DownloadTaskInfo() {
        this.mPosition = Integer.valueOf(0);
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mAddTime = Long.valueOf(System.currentTimeMillis());
    }

    public DownloadTaskInfo(Integer num) {
        this.mPosition = Integer.valueOf(0);
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mType = num;
    }

    public DownloadTaskInfo(String str) {
        super(null, str);
        this.mPosition = Integer.valueOf(0);
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
    }

    public DownloadTaskInfo(String str, String str2, String str3, Integer num, Boolean bool) {
        super(str2, str3, bool);
        this.mPosition = Integer.valueOf(0);
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mType = num;
        this.mDownloadTime = Long.valueOf(0);
        this.mAddTime = Long.valueOf(System.currentTimeMillis());
        this.mGroupId = str;
    }

    public int getSongType() {
        return this.mSongType;
    }

    public void setSongType(int i) {
        this.mSongType = i;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public Integer getType() {
        return this.mType;
    }

    public void setType(Integer num) {
        this.mType = num;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public Long getAddTime() {
        return this.mAddTime;
    }

    public void setAddTime(Long l) {
        this.mAddTime = l;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public Long getFileId() {
        return this.mFileId;
    }

    public void setFileId(Long l) {
        this.mFileId = l;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public String getFileName() {
        return this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public Long getDownloadTime() {
        return this.mDownloadTime;
    }

    public void setDownloadTime(Long l) {
        this.mDownloadTime = l;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public Long getRespondTime() {
        return this.mRespondTime;
    }

    public void setRespondTime(Long l) {
        if (!(this.mRespondTime == null || 0 == this.mRespondTime.longValue())) {
            l = this.mRespondTime;
        }
        this.mRespondTime = l;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public Integer getCutOffTimes() {
        return this.mCutOffTimes;
    }

    public void setCutOffTimes(Integer num) {
        this.mCutOffTimes = num;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777218)
    public Long getCompleteTime() {
        return this.mCompleteTime;
    }

    public void setCompleteTime(Long l) {
        this.mCompleteTime = l;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777217)
    public String getOrigin() {
        return this.mOrigin;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public Integer getPosition() {
        return this.mPosition;
    }

    public void setPosition(int i) {
        this.mPosition = Integer.valueOf(i);
    }

    public String getListId() {
        return this.mListId;
    }

    public void setListId(String str) {
        this.mListId = str;
    }

    public int getListType() {
        return this.mListType;
    }

    public void setListType(int i) {
        this.mListType = i;
    }

    public Long getConnectTimeStamp() {
        return Long.valueOf(this.mConnectTimeStamp == null ? 0 : this.mConnectTimeStamp.longValue());
    }

    public void setAudioQuality(String str) {
        this.mAudioQuality = str;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777222)
    public String getAudioQuality() {
        return this.mAudioQuality;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777221)
    public String getGroupId() {
        return this.mGroupId;
    }

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    @com.sds.android.sdk.lib.c.a.a.b(a = 4, b = 16777223)
    public String getAlibabaOrigin() {
        return this.mAlibabaOrigin;
    }

    public void setAlibabaOrigin(String str) {
        this.mAlibabaOrigin = str;
    }

    public void setConnectTimeStamp(Long l) {
        this.mConnectTimeStamp = l;
    }

    public boolean isUrlUpdated() {
        return this.mUrlUpdated;
    }

    public void setUrlUpdated(boolean z) {
        this.mUrlUpdated = z;
    }

    public String getFileFormat() {
        return this.mFileFormat;
    }

    public void setFileFormat(String str) {
        this.mFileFormat = str;
    }

    public long getSingerID() {
        return this.mSingerID;
    }

    public void setSingerID(long j) {
        this.mSingerID = j;
    }

    public b getDownloadError() {
        return this.mDownloadError;
    }

    public void setDownloadError(b bVar) {
        this.mDownloadError = bVar;
    }

    public String getScm() {
        return this.mScm;
    }

    public void setScm(String str) {
        this.mScm = str;
    }

    public Object getTag() {
        if (!(this.mTagBundle == null || this.mTagBundle.isEmpty())) {
            String str = (String) this.mTagBundle.keySet().toArray()[0];
            if ("key_parcel".equals(str)) {
                return this.mTagBundle.getParcelable(str);
            }
            if ("key_string".equals(str)) {
                return this.mTagBundle.getString(str);
            }
            if ("key_serial".equals(str)) {
                return this.mTagBundle.getSerializable(str);
            }
            if ("key_bool".equals(str)) {
                return Boolean.valueOf(this.mTagBundle.getBoolean(str));
            }
            if ("key_int".equals(str)) {
                return Integer.valueOf(this.mTagBundle.getInt(str));
            }
            if ("key_float".equals(str)) {
                return Float.valueOf(this.mTagBundle.getFloat(str));
            }
            if ("key_double".equals(str)) {
                return Double.valueOf(this.mTagBundle.getDouble(str));
            }
        }
        return null;
    }

    public void setTag(Object obj) {
        this.mTagBundle = new Bundle();
        if (obj instanceof Parcelable) {
            this.mTagBundle.putParcelable("key_parcel", (Parcelable) obj);
        } else if (obj instanceof String) {
            this.mTagBundle.putString("key_string", (String) obj);
        } else if (obj instanceof Serializable) {
            this.mTagBundle.putSerializable("key_serial", (Serializable) obj);
        } else if (obj instanceof Boolean) {
            this.mTagBundle.putBoolean("key_bool", ((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            this.mTagBundle.putInt("key_int", ((Integer) obj).intValue());
        } else if (obj instanceof Float) {
            this.mTagBundle.putFloat("key_float", ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            this.mTagBundle.putDouble("key_double", ((Double) obj).doubleValue());
        }
    }

    public int describeContents() {
        return super.describeContents();
    }

    public void writeToParcel(Parcel parcel, int i) {
        long j = 0;
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mAddTime.longValue());
        parcel.writeInt(this.mType.intValue());
        parcel.writeString(this.mOrigin);
        parcel.writeLong(this.mDownloadTime == null ? 0 : this.mDownloadTime.longValue());
        parcel.writeLong(this.mRespondTime == null ? 0 : this.mRespondTime.longValue());
        parcel.writeLong(this.mConnectTimeStamp == null ? 0 : this.mConnectTimeStamp.longValue());
        parcel.writeInt(this.mCutOffTimes == null ? 0 : this.mCutOffTimes.intValue());
        parcel.writeString(this.mFileName);
        if (this.mFileId != null) {
            j = this.mFileId.longValue();
        }
        parcel.writeLong(j);
        parcel.writeString(this.mAudioQuality == null ? "" : this.mAudioQuality);
        parcel.writeString(this.mGroupId == null ? MediaStorage.GROUP_ID_DOWNLOAD : this.mGroupId);
        parcel.writeBooleanArray(new boolean[]{this.mUrlUpdated});
        parcel.writeInt(this.mSongType);
        parcel.writeBundle(this.mTagBundle);
        parcel.writeString(this.mAlibabaOrigin == null ? "" : this.mAlibabaOrigin);
    }

    private DownloadTaskInfo(Parcel parcel) {
        super(parcel);
        this.mPosition = Integer.valueOf(0);
        this.mListId = "";
        this.mUrlUpdated = false;
        this.mSongType = -1;
        this.mAddTime = Long.valueOf(parcel.readLong());
        this.mType = Integer.valueOf(parcel.readInt());
        this.mOrigin = parcel.readString();
        this.mDownloadTime = Long.valueOf(parcel.readLong());
        this.mRespondTime = Long.valueOf(parcel.readLong());
        this.mConnectTimeStamp = Long.valueOf(parcel.readLong());
        this.mCutOffTimes = Integer.valueOf(parcel.readInt());
        this.mFileName = parcel.readString();
        this.mFileId = Long.valueOf(parcel.readLong());
        this.mAudioQuality = parcel.readString();
        this.mGroupId = parcel.readString();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.mUrlUpdated = zArr[0];
        this.mSongType = parcel.readInt();
        this.mTagBundle = parcel.readBundle(DownloadTaskInfo.class.getClassLoader());
        this.mAlibabaOrigin = parcel.readString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) obj;
        if (this.mType != null) {
            if (this.mType.equals(downloadTaskInfo.mType)) {
                return true;
            }
        } else if (downloadTaskInfo.mType == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.mType != null ? this.mType.hashCode() : 0) + (super.hashCode() * 31);
    }
}
