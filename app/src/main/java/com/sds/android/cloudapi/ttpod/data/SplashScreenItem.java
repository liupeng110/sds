package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplashScreenItem implements Serializable {
    @c(a = "audio")
    private String mAudioUrl = "";
    @c(a = "date")
    private String mDate = "";
    @c(a = "label")
    private String mLabel = "";
    @c(a = "large_pic_url")
    private String mLargePicUrl = "";
    @c(a = "middle_pic_url")
    private String mMiddlePicUrl = "";
    @c(a = "small_pic_url")
    private String mSmallPicUrl = "";

    public static class Period implements Serializable {
        private List<DateRange> mDateRanges = new ArrayList();

        private class DateRange implements Serializable {
            private String mBegin = "";
            private String mEnd = "";

            public DateRange(String str) {
                Matcher matcher = Pattern.compile("^\\s*(\\d{8})\\s*(-\\s*(\\d{8})){0,1}\\s*$").matcher(str);
                while (matcher.find()) {
                    this.mBegin = matcher.group(1) != null ? matcher.group(1) : "";
                    this.mEnd = matcher.group(3) != null ? matcher.group(3) : this.mBegin;
                }
            }

            public boolean contain(String str) {
                return str.compareTo(this.mBegin) >= 0 && str.compareTo(this.mEnd) <= 0;
            }

            public boolean valid(String str) {
                return str.compareTo(this.mEnd) <= 0;
            }
        }

        public Period(String str) {
            for (String dateRange : str.split(";")) {
                this.mDateRanges.add(new DateRange(dateRange));
            }
        }

        public boolean contain(String str) {
            for (DateRange contain : this.mDateRanges) {
                if (contain.contain(str)) {
                    return true;
                }
            }
            return false;
        }

        public boolean valid(String str) {
            for (DateRange valid : this.mDateRanges) {
                if (valid.valid(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isContain(String str) {
        return new Period(this.mDate).contain(str);
    }

    public boolean isValid(String str) {
        return new Period(this.mDate).valid(str);
    }

    public String getDate() {
        return this.mDate;
    }

    public String getLargePicUrl() {
        return this.mLargePicUrl;
    }

    public String getMiddlePicUrl() {
        return this.mMiddlePicUrl;
    }

    public String getSmallPicUrl() {
        return this.mSmallPicUrl;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getAudioUrl() {
        return this.mAudioUrl;
    }
}
