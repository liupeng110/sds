package com.sds.android.cloudapi.ttpod.result;

import com.b.a.a.c;
import com.sds.android.cloudapi.ttpod.data.TagData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SingerBriefResult extends SingerBaseResult {
    @c(a = "data")
    private SingerBriefData mData;

    public static class SingerBriefData implements Serializable {
        @c(a = "achievements")
        private String mAchievements;
        @c(a = "activeYear")
        private int mActiveYear;
        @c(a = "alias")
        private List<String> mAlias;
        @c(a = "area")
        private String mArea;
        @c(a = "audit")
        private int mAudit;
        @c(a = "avatarId")
        private long mAvatarId;
        @c(a = "birthDate")
        private String mBirthDate;
        @c(a = "birthPlace")
        private String mBirthPlace;
        @c(a = "birthYear")
        private int mBirthYear;
        @c(a = "bloodType")
        private String mBloodType;
        @c(a = "brief")
        private String mBrief;
        @c(a = "career")
        private String mCareer;
        @c(a = "chineseName")
        private String mChineseName;
        @c(a = "companyId")
        private long mCompanyId;
        @c(a = "companyName")
        private String mCompanyName;
        @c(a = "country")
        private String mCountry;
        @c(a = "deathYear")
        private int mDeathYear;
        @c(a = "englishName")
        private String mEnglishName;
        @c(a = "enter")
        private long mEnter;
        @c(a = "gender")
        private int mGender;
        @c(a = "genres")
        private List<TagData> mGenres;
        @c(a = "group")
        private String mGroup;
        @c(a = "height")
        private String mHeight;
        @c(a = "horoscope")
        private int mHoroScope;
        @c(a = "id")
        private long mId;
        @c(a = "identifier")
        private String mIdentifier;
        @c(a = "lang")
        private String mLang;
        @c(a = "name")
        private String mName;
        @c(a = "nation")
        private String mNation;
        @c(a = "picUrl")
        private String mPicUrl;
        @c(a = "school")
        private String mSchool;
        @c(a = "status")
        private int mStatus;
        @c(a = "styles")
        private List<TagData> mStyles;
        @c(a = "tags")
        private List<TagData> mTags;
        @c(a = "type")
        private int mType;
        @c(a = "weight")
        private String mWeight;

        public long getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getType() {
            return this.mType;
        }

        public String getIdentifier() {
            return this.mIdentifier;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public int getAudit() {
            return this.mAudit;
        }

        public int getActiveYear() {
            return this.mActiveYear;
        }

        public String getBrief() {
            return this.mBrief;
        }

        public String getGroup() {
            return this.mGroup;
        }

        public String getArea() {
            return this.mArea;
        }

        public int getGender() {
            return this.mGender;
        }

        public String getLang() {
            return this.mLang;
        }

        public long getAvatarId() {
            return this.mAvatarId;
        }

        public String getCountry() {
            return this.mCountry;
        }

        public List<TagData> getTags() {
            if (this.mTags == null) {
                this.mTags = new ArrayList();
            }
            return this.mTags;
        }

        public String getChineseName() {
            return this.mChineseName;
        }

        public String getEnglishName() {
            return this.mEnglishName;
        }

        public List<String> getAlias() {
            if (this.mAlias == null) {
                this.mAlias = new ArrayList();
            }
            return this.mAlias;
        }

        public String getNation() {
            return this.mNation;
        }

        public String getBirthPlace() {
            return this.mBirthPlace;
        }

        public String getCareer() {
            return this.mCareer;
        }

        public String getSchool() {
            return this.mSchool;
        }

        public long getCompanyId() {
            return this.mCompanyId;
        }

        public String getAchievements() {
            return this.mAchievements;
        }

        public String getBloodType() {
            return this.mBloodType;
        }

        public String getHeight() {
            return this.mHeight;
        }

        public String getWeight() {
            return this.mWeight;
        }

        public long getEnter() {
            return this.mEnter;
        }

        public int getBirthYear() {
            return this.mBirthYear;
        }

        public int getDeathYear() {
            return this.mDeathYear;
        }

        public String getBirthDate() {
            return this.mBirthDate;
        }

        public int getHoroScope() {
            return this.mHoroScope;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }

        public void setPicUrl(String str) {
            this.mPicUrl = str;
        }

        public String getCompanyName() {
            return this.mCompanyName;
        }

        public void setCompanyName(String str) {
            this.mCompanyName = str;
        }

        public List<TagData> getStyles() {
            if (this.mStyles == null) {
                this.mStyles = new ArrayList();
            }
            return this.mStyles;
        }

        public List<TagData> getGenres() {
            if (this.mGenres == null) {
                this.mGenres = new ArrayList();
            }
            return this.mGenres;
        }
    }

    public SingerBriefData getData() {
        if (this.mData == null) {
            this.mData = new SingerBriefData();
        }
        return this.mData;
    }
}
