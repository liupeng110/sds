package com.sds.android.sdk.core.download;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.sds.android.sdk.lib.c.a.a.b;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskInfo implements Parcelable {
    public static final Creator<TaskInfo> CREATOR = new Creator<TaskInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public TaskInfo a(Parcel parcel) {
            return new TaskInfo(parcel);
        }

        public TaskInfo[] a(int i) {
            return new TaskInfo[i];
        }
    };
    public static final int DB_VERSION = 16777216;
    private static final String NONE = "none";
    private static final int NUM_100 = 100;
    private String mAllIP;
    private b mAttachTask;
    private String mConnectFailedIPs;
    private String mConnectedIP;
    private AtomicInteger mDownloadLength;
    private AtomicInteger mDownloadSpeed;
    private Integer mFileLength;
    private Boolean mIsResumeBrokenTransferSupported;
    private Integer mResponseCode;
    private String mSavePath;
    private String mSourceUrl;
    private Integer mState;
    private boolean mStatisticRequest;
    private Object mTag;

    public TaskInfo(String str, String str2, Boolean bool) {
        this.mConnectFailedIPs = "none";
        this.mConnectedIP = "none";
        this.mAllIP = "none";
        this.mResponseCode = Integer.valueOf(-1);
        this.mDownloadSpeed = new AtomicInteger(0);
        this.mDownloadLength = new AtomicInteger(0);
        this.mSourceUrl = str;
        this.mSavePath = str2;
        this.mIsResumeBrokenTransferSupported = bool;
    }

    public TaskInfo() {
        this.mConnectFailedIPs = "none";
        this.mConnectedIP = "none";
        this.mAllIP = "none";
        this.mResponseCode = Integer.valueOf(-1);
        this.mDownloadSpeed = new AtomicInteger(0);
        this.mDownloadLength = new AtomicInteger(0);
    }

    public TaskInfo(String str, String str2) {
        this(str, str2, null);
    }

    private void loadDownloadLength() {
        if (e.b(buildTmpPath())) {
            this.mDownloadLength.set((int) e.g(buildTmpPath()));
        } else if (e.b(this.mSavePath)) {
            this.mDownloadLength.set((int) e.c(this.mSavePath));
        }
    }

    public void setStatisticRequest(boolean z) {
        this.mStatisticRequest = z;
    }

    public boolean getStatisticRequest() {
        return this.mStatisticRequest;
    }

    public void statisticConnectFailedIPs(String str) {
        if (str == null) {
            str = "none";
        }
        this.mConnectFailedIPs = str;
    }

    public String getStatisticConnectFailedIP() {
        return this.mConnectFailedIPs;
    }

    public void setConnectedIP(String str) {
        this.mConnectedIP = str;
    }

    public String getConnectedIP() {
        return this.mConnectedIP;
    }

    public void setResponseCode(int i) {
        this.mResponseCode = Integer.valueOf(i);
    }

    public int getResponseCode() {
        return this.mResponseCode.intValue();
    }

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    @b(a = 0, b = 16777216, c = true)
    public final String getSavePath() {
        return this.mSavePath;
    }

    public void setSavePath(String str) {
        this.mSavePath = str;
        loadDownloadLength();
    }

    @b(a = 1, b = 16777216)
    public final String getSourceUrl() {
        return this.mSourceUrl;
    }

    public void setSourceUrl(String str) {
        this.mSourceUrl = str;
    }

    @b(a = 2, b = 16777216)
    public Integer getFileLength() {
        return this.mFileLength;
    }

    public void setFileLength(Integer num) {
        this.mFileLength = num;
    }

    @b(a = 3, b = 16777216)
    public Integer getState() {
        return this.mState;
    }

    public void setState(Integer num) {
        this.mState = num;
    }

    @b(a = 4, b = 16777216)
    public Boolean getIsResumeBrokenTransferSupported() {
        return this.mIsResumeBrokenTransferSupported;
    }

    public void setIsResumeBrokenTransferSupported(Boolean bool) {
        this.mIsResumeBrokenTransferSupported = bool;
    }

    public boolean resumeBrokenTransferSupported() {
        return this.mIsResumeBrokenTransferSupported != null && this.mIsResumeBrokenTransferSupported.booleanValue();
    }

    public int getDownloadLength() {
        return this.mDownloadLength.get();
    }

    public void setDownloadLength(int i) {
        this.mDownloadLength.set(i);
    }

    public int getDownloadSpend() {
        return this.mDownloadSpeed.get();
    }

    void setDownloadSpend(int i) {
        this.mDownloadSpeed.set(i);
    }

    void setAttachTask(b bVar) {
        this.mAttachTask = bVar;
    }

    b getAttachTask() {
        return this.mAttachTask;
    }

    public String getAllIP() {
        return this.mAllIP;
    }

    public void setAllIP(String str) {
        this.mAllIP = str;
    }

    public Integer getDownloadProgress() {
        if (this.mFileLength == null || this.mFileLength.intValue() == 0) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf((int) ((((long) this.mDownloadLength.get()) * 100) / ((long) this.mFileLength.intValue())));
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return m.a(this.mSavePath, ((TaskInfo) obj).getSavePath());
    }

    public int hashCode() {
        return this.mSavePath != null ? this.mSavePath.hashCode() : 0;
    }

    String buildTmpPath() {
        return this.mSavePath + ".tmp";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSavePath);
        parcel.writeString(this.mSourceUrl);
        parcel.writeInt(this.mFileLength == null ? 0 : this.mFileLength.intValue());
        parcel.writeInt(this.mState == null ? 3 : this.mState.intValue());
        parcel.writeBooleanArray(new boolean[]{this.mIsResumeBrokenTransferSupported.booleanValue()});
        parcel.writeInt(this.mDownloadSpeed.get());
        parcel.writeInt(this.mDownloadLength.get());
        parcel.writeString(this.mAllIP);
    }

    protected TaskInfo(Parcel parcel) {
        this.mConnectFailedIPs = "none";
        this.mConnectedIP = "none";
        this.mAllIP = "none";
        this.mResponseCode = Integer.valueOf(-1);
        this.mDownloadSpeed = new AtomicInteger(0);
        this.mDownloadLength = new AtomicInteger(0);
        this.mSavePath = parcel.readString();
        this.mSourceUrl = parcel.readString();
        this.mFileLength = Integer.valueOf(parcel.readInt());
        this.mState = Integer.valueOf(parcel.readInt());
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.mIsResumeBrokenTransferSupported = Boolean.valueOf(zArr[0]);
        this.mDownloadSpeed.set(parcel.readInt());
        this.mDownloadLength.set(parcel.readInt());
        this.mAllIP = parcel.readString();
    }
}
