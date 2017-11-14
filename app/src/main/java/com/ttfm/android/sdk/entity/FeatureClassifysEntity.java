package com.ttfm.android.sdk.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class FeatureClassifysEntity {
    private boolean ffcBottomLine = false;
    private boolean ffcCloseShow = false;
    private String ffcCode = "";
    private String ffcCodeName = "";
    private String ffcIco = "";
    private long ffcId = 0;
    private int ffcLayoutType = 0;
    private int ffcLineShowNumber = 0;
    private boolean ffcMoreShow = true;
    private String ffcName = "";
    private int ffcShowNumber = 0;
    private ArrayList<FeatureClassifysItem> items = null;

    public static class FeatureClassifysItem {
        private ChannelEntity channel = null;
        private long ffiCiId = 0;
        private String ffiCiName = "";
        private String ffiImg = "";
        private String ffiName = "";
        private long ffiSourceId = 0;
        private int ffiType = 0;
        private String ffiTypeImg = "";
        private String ffiUrl = "";
        private FeatureClassifysItemTopic topic = null;

        public String getFfiName() {
            return this.ffiName;
        }

        public void setFfiName(String str) {
            this.ffiName = str;
        }

        public String getFfiImg() {
            return this.ffiImg;
        }

        public void setFfiImg(String str) {
            this.ffiImg = str;
        }

        public String getFfiTypeImg() {
            return this.ffiTypeImg;
        }

        public void setFfiTypeImg(String str) {
            this.ffiTypeImg = str;
        }

        public long getFfiSourceId() {
            return this.ffiSourceId;
        }

        public void setFfiSourceId(long j) {
            this.ffiSourceId = j;
        }

        public long getFfiCiId() {
            return this.ffiCiId;
        }

        public void setFfiCiId(long j) {
            this.ffiCiId = j;
        }

        public String getFfiCiName() {
            return this.ffiCiName;
        }

        public void setFfiCiName(String str) {
            this.ffiCiName = str;
        }

        public int getFfiType() {
            return this.ffiType;
        }

        public void setFfiType(int i) {
            this.ffiType = i;
        }

        public String getFfiUrl() {
            return this.ffiUrl;
        }

        public void setFfiUrl(String str) {
            this.ffiUrl = str;
        }

        public FeatureClassifysItemTopic getTopic() {
            return this.topic;
        }

        public void setTopic(FeatureClassifysItemTopic featureClassifysItemTopic) {
            this.topic = featureClassifysItemTopic;
        }

        public ChannelEntity getChannel() {
            return this.channel;
        }

        public void setChannel(ChannelEntity channelEntity) {
            this.channel = channelEntity;
        }
    }

    public static class FeatureClassifysItemTopic implements Serializable {
        private static final long serialVersionUID = 1;
        private String ctContent = "";
        private int ctReplyCount = 0;

        public String getCtContent() {
            return this.ctContent;
        }

        public void setCtContent(String str) {
            this.ctContent = str;
        }

        public int getCtReplyCount() {
            return this.ctReplyCount;
        }

        public void setCtReplyCount(int i) {
            this.ctReplyCount = i;
        }
    }

    public long getFfcId() {
        return this.ffcId;
    }

    public void setFfcId(long j) {
        this.ffcId = j;
    }

    public String getFfcCode() {
        return this.ffcCode;
    }

    public void setFfcCode(String str) {
        this.ffcCode = str;
    }

    public String getFfcName() {
        return this.ffcName;
    }

    public void setFfcName(String str) {
        this.ffcName = str;
    }

    public int getFfcShowNumber() {
        return this.ffcShowNumber;
    }

    public void setFfcShowNumber(int i) {
        this.ffcShowNumber = i;
    }

    public boolean isFfcBottomLine() {
        return this.ffcBottomLine;
    }

    public void setFfcBottomLine(boolean z) {
        this.ffcBottomLine = z;
    }

    public int getFfcLayoutType() {
        return this.ffcLayoutType;
    }

    public void setFfcLayoutType(int i) {
        this.ffcLayoutType = i;
    }

    public boolean isFfcCloseShow() {
        return this.ffcCloseShow;
    }

    public void setFfcCloseShow(boolean z) {
        this.ffcCloseShow = z;
    }

    public String getFfcIco() {
        return this.ffcIco;
    }

    public void setFfcIco(String str) {
        this.ffcIco = str;
    }

    public int getFfcLineShowNumber() {
        return this.ffcLineShowNumber;
    }

    public void setFfcLineShowNumber(int i) {
        this.ffcLineShowNumber = i;
    }

    public boolean isFfcMoreShow() {
        return this.ffcMoreShow;
    }

    public void setFfcMoreShow(boolean z) {
        this.ffcMoreShow = z;
    }

    public ArrayList<FeatureClassifysItem> getItems() {
        return this.items;
    }

    public void setItems(ArrayList<FeatureClassifysItem> arrayList) {
        this.items = arrayList;
    }

    public void iniffcShowNumber() {
        if (this.items != null && this.items.size() > 0) {
            int size = this.items.size();
            if (this.ffcShowNumber > 0 && this.ffcShowNumber < size) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.items.subList(0, this.ffcShowNumber));
                this.items.clear();
                this.items = arrayList;
            }
        }
    }

    public String getFfcCodeName() {
        return this.ffcCodeName;
    }

    public void setFfcCodeName(String str) {
        this.ffcCodeName = str;
    }
}
