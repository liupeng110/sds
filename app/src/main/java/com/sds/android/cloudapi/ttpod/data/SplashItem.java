package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import java.io.Serializable;

public class SplashItem implements Serializable {
    @c(a = "end_time")
    private int mEndTime;
    @c(a = "files")
    private SplashFile mSplashFile;
    @c(a = "start_time")
    private int mStartTime;

    private class SplashFile implements Serializable {
        @c(a = "hdpi")
        private String mHdpiFile;
        @c(a = "mdpi")
        private String mMdpiFile;
        @c(a = "xhdpi")
        private String mXhdpiFile;
        @c(a = "xxhdpi")
        private String mXxhdpiFile;

        private SplashFile() {
        }

        private String getSuitFile(int i) {
            String str = this.mMdpiFile;
            switch (i) {
                case 240:
                    return this.mHdpiFile;
                case TTFMSongEntity.BITRATE_DEFAULT /*320*/:
                    return this.mXhdpiFile;
                case 480:
                    return this.mXxhdpiFile;
                default:
                    return str;
            }
        }
    }

    public boolean isContain(int i) {
        return this.mStartTime <= i && i <= this.mEndTime;
    }

    public boolean isValid(int i) {
        return i <= this.mEndTime;
    }

    public String getSuitFile(int i) {
        return this.mSplashFile != null ? this.mSplashFile.getSuitFile(i) : null;
    }
}
