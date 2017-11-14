package com.ttfm.android.sdk.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ClassifyEntity {
    private ArrayList<ClassifyLabelInfo> allLabelArraylist;
    private int alllabelcount;
    private ArrayList<ClassifyInfos> classify;
    private int count;

    public static class ClassifyInfos {
        private ArrayList<ClassifyLabelInfo> labelInfos;
        private String sliName;

        public ClassifyInfos() {
            this.sliName = "";
            this.labelInfos = null;
            this.labelInfos = new ArrayList();
        }

        public String getSliName() {
            return this.sliName;
        }

        public void setSliName(String str) {
            this.sliName = str;
        }

        public ArrayList<ClassifyLabelInfo> getLabelInfos() {
            return this.labelInfos;
        }

        public void setLabelInfos(ArrayList<ClassifyLabelInfo> arrayList) {
            this.labelInfos = arrayList;
        }
    }

    public static class ClassifyLabelInfo implements Serializable {
        private static final long serialVersionUID = 1;
        private String liIco = "";
        private long liId = 0;
        private String liName = "";
        private int liType = 0;
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

        public int getLiType() {
            return this.liType;
        }

        public void setLiType(int i) {
            this.liType = i;
        }

        public boolean isHead() {
            return this.liType != 0;
        }

        public String getLiIco() {
            return this.liIco;
        }

        public void setLiIco(String str) {
            this.liIco = str;
        }
    }

    public ClassifyEntity() {
        this.count = 0;
        this.classify = null;
        this.alllabelcount = 0;
        this.allLabelArraylist = new ArrayList();
        this.classify = new ArrayList();
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public ArrayList<ClassifyInfos> getClassify() {
        return this.classify;
    }

    public void setClassify(ArrayList<ClassifyInfos> arrayList) {
        this.classify = arrayList;
    }

    public int getAlllabelcount() {
        return this.alllabelcount;
    }

    public void setAlllabelcount() {
        this.alllabelcount = 0;
        if (this.allLabelArraylist != null) {
            this.alllabelcount = this.allLabelArraylist.size();
        }
    }

    public ArrayList<ClassifyLabelInfo> getAllLaberArrayList() {
        return this.allLabelArraylist;
    }

    public void setAllLaberArrayList() {
        if (this.allLabelArraylist == null) {
            this.allLabelArraylist = new ArrayList();
        }
        this.allLabelArraylist.clear();
        if (this.classify != null) {
            for (int i = 0; i < this.classify.size(); i++) {
                ClassifyInfos classifyInfos = (ClassifyInfos) this.classify.get(i);
                ClassifyLabelInfo classifyLabelInfo = new ClassifyLabelInfo();
                classifyLabelInfo.setLiType(1);
                classifyLabelInfo.setLiName(classifyInfos.getSliName());
                this.allLabelArraylist.add(classifyLabelInfo);
                Collection labelInfos = classifyInfos.getLabelInfos();
                if (labelInfos != null) {
                    this.allLabelArraylist.addAll(labelInfos);
                }
            }
        }
    }
}
