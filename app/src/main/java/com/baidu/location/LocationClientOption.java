package com.baidu.location;

public final class LocationClientOption {
    public static final int GpsFirst = 1;
    public static final int MIN_SCAN_SPAN = 1000;
    public static final int NetWorkFirst = 2;
    protected int a = 3;
    protected String byte = "com.baidu.location.service_v2.9";
    protected boolean case = false;
    protected String char = "detail";
    protected float do = 500.0f;
    protected boolean else = false;
    protected boolean for = true;
    protected int goto = 1;
    protected boolean if = false;
    protected int int = 0;
    protected int long = 12000;
    protected String new = "SDK2.0";
    protected String try = "gcj02";
    protected boolean void = false;

    public LocationClientOption(LocationClientOption locationClientOption) {
        this.try = locationClientOption.try;
        this.char = locationClientOption.char;
        this.case = locationClientOption.case;
        this.int = locationClientOption.int;
        this.long = locationClientOption.long;
        this.new = locationClientOption.new;
        this.goto = locationClientOption.goto;
        this.void = locationClientOption.void;
        this.if = locationClientOption.if;
        this.do = locationClientOption.do;
        this.a = locationClientOption.a;
        this.byte = locationClientOption.byte;
        this.for = locationClientOption.for;
    }

    public void disableCache(boolean z) {
        this.for = z;
    }

    public boolean equals(LocationClientOption locationClientOption) {
        return this.try.equals(locationClientOption.try) && this.char.equals(locationClientOption.char) && this.case == locationClientOption.case && this.int == locationClientOption.int && this.long == locationClientOption.long && this.new.equals(locationClientOption.new) && this.void == locationClientOption.void && this.goto == locationClientOption.goto && this.a == locationClientOption.a && this.if == locationClientOption.if && this.do == locationClientOption.do && this.for == locationClientOption.for;
    }

    public String getAddrType() {
        return this.char;
    }

    public String getCoorType() {
        return this.try;
    }

    public float getPoiDistance() {
        return this.do;
    }

    public boolean getPoiExtranInfo() {
        return this.if;
    }

    public int getPoiNumber() {
        return this.a;
    }

    public int getPriority() {
        return this.goto;
    }

    public String getProdName() {
        return this.new;
    }

    public int getScanSpan() {
        return this.int;
    }

    public String getServiceName() {
        return this.byte;
    }

    public int getTimeOut() {
        return this.long;
    }

    public boolean isDisableCache() {
        return this.for;
    }

    public boolean isLocationNotify() {
        return this.void;
    }

    public boolean isOpenGps() {
        return this.case;
    }

    public void setAddrType(String str) {
        if (str.length() > 32) {
            str = str.substring(0, 32);
        }
        this.char = str;
    }

    public void setCoorType(String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.equals("gcj02") || toLowerCase.equals("bd09") || toLowerCase.equals("bd09ll")) {
            this.try = toLowerCase;
        }
    }

    public void setLocationNotify(boolean z) {
        this.void = z;
    }

    public void setOpenGps(boolean z) {
        this.case = z;
    }

    public void setPoiDistance(float f) {
        this.do = f;
    }

    public void setPoiExtraInfo(boolean z) {
        this.if = z;
    }

    public void setPoiNumber(int i) {
        if (i > 10) {
            i = 10;
        }
        this.a = i;
    }

    public void setPriority(int i) {
        if (i == 1 || i == 2) {
            this.goto = i;
        }
    }

    public void setProdName(String str) {
        if (str.length() > 64) {
            str = str.substring(0, 64);
        }
        this.new = str;
    }

    public void setScanSpan(int i) {
        this.int = i;
    }

    public void setServiceName(String str) {
        this.byte = str;
    }

    public void setTimeOut(int i) {
        this.long = i;
    }
}
