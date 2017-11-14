package com.baidu.location;

import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import org.json.JSONObject;

public final class BDLocation {
    public static final int TypeCacheLocation = 65;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerError = 167;
    private String a = null;
    private boolean b = false;
    private String byte = null;
    private boolean c = false;
    private boolean case = false;
    private float char = -1.0f;
    private String d = null;
    private int do = -1;
    private boolean e = false;
    private double else = Double.MIN_VALUE;
    private double f = Double.MIN_VALUE;
    private double for = Double.MIN_VALUE;
    private String goto = null;
    private int if = 0;
    private boolean int = false;
    private float long = 0.0f;
    public a mAddr = new a(this);
    public String mServerString = null;
    private float new = 0.0f;
    private boolean try = false;
    private boolean void = false;

    public class a {
        final /* synthetic */ BDLocation a;
        public String byte = null;
        public String do = null;
        public String for = null;
        public String if = null;
        public String int = null;
        public String new = null;
        public String try = null;

        public a(BDLocation bDLocation) {
            this.a = bDLocation;
        }
    }

    public BDLocation(double d, double d2, float f) {
        this.else = d2;
        this.for = d;
        this.long = f;
        this.d = j.for();
    }

    public BDLocation(String str) {
        if (str != null && !str.equals("")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                int parseInt = Integer.parseInt(jSONObject2.getString("error"));
                setLocType(parseInt);
                setTime(jSONObject2.getString("time"));
                if (parseInt == 61) {
                    jSONObject = jSONObject.getJSONObject("content");
                    jSONObject2 = jSONObject.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject2.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject2.getString("x")));
                    setRadius(Float.parseFloat(jSONObject.getString("radius")));
                    setSpeed(Float.parseFloat(jSONObject.getString("s")));
                    setDerect(Float.parseFloat(jSONObject.getString("d")));
                    setSatelliteNumber(Integer.parseInt(jSONObject.getString("n")));
                } else if (parseInt == TypeNetWorkLocation) {
                    jSONObject2 = jSONObject.getJSONObject("content");
                    jSONObject = jSONObject2.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject.getString("x")));
                    setRadius(Float.parseFloat(jSONObject2.getString("radius")));
                    if (jSONObject2.has("addr")) {
                        String string = jSONObject2.getString("addr");
                        this.mAddr.try = string;
                        j.if(f.v, string);
                        String[] split = string.split(SelectCountryActivity.SPLITTER);
                        this.mAddr.if = split[0];
                        this.mAddr.new = split[1];
                        this.mAddr.int = split[2];
                        this.mAddr.byte = split[3];
                        this.mAddr.do = split[4];
                        this.mAddr.for = split[5];
                        if ((this.mAddr.if.contains("北京") && this.mAddr.new.contains("北京")) || ((this.mAddr.if.contains("上海") && this.mAddr.new.contains("上海")) || ((this.mAddr.if.contains("天津") && this.mAddr.new.contains("天津")) || (this.mAddr.if.contains("重庆") && this.mAddr.new.contains("重庆"))))) {
                            j.if(f.v, "true,beijing");
                            string = this.mAddr.if;
                        } else {
                            string = this.mAddr.if + this.mAddr.new;
                        }
                        this.mAddr.try = string + this.mAddr.int + this.mAddr.byte + this.mAddr.do;
                        this.void = true;
                    } else {
                        this.void = false;
                        setAddrStr(null);
                    }
                    if (jSONObject2.has("poi")) {
                        this.case = true;
                        this.byte = jSONObject2.getJSONObject("poi").toString();
                    }
                } else if (parseInt == 66 || parseInt == 68) {
                    jSONObject = jSONObject.getJSONObject("content");
                    jSONObject2 = jSONObject.getJSONObject("point");
                    setLatitude(Double.parseDouble(jSONObject2.getString("y")));
                    setLongitude(Double.parseDouble(jSONObject2.getString("x")));
                    setRadius(Float.parseFloat(jSONObject.getString("radius")));
                    a(Boolean.valueOf(Boolean.parseBoolean(jSONObject.getString("isCellChanged"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.if = 0;
                this.void = false;
            }
        }
    }

    public BDLocation(String str, double d, double d2, float f, String str2, String str3) {
        this.d = str;
        this.else = d;
        this.for = d2;
        this.long = f;
        this.goto = str2;
        this.a = str3;
        this.d = j.for();
    }

    private void a(Boolean bool) {
        this.e = bool.booleanValue();
    }

    public String getAddrStr() {
        return this.mAddr.try;
    }

    public double getAltitude() {
        return this.f;
    }

    public String getCity() {
        return this.mAddr.new;
    }

    public String getCityCode() {
        return this.mAddr.for;
    }

    public String getCoorType() {
        return this.goto;
    }

    public float getDerect() {
        return this.char;
    }

    public String getDistrict() {
        return this.mAddr.int;
    }

    public double getLatitude() {
        return this.else;
    }

    public int getLocType() {
        return this.if;
    }

    public double getLongitude() {
        return this.for;
    }

    public String getPoi() {
        return this.byte;
    }

    public String getProvince() {
        return this.mAddr.if;
    }

    public float getRadius() {
        return this.long;
    }

    public int getSatelliteNumber() {
        this.b = true;
        return this.do;
    }

    public float getSpeed() {
        return this.new;
    }

    public String getStreet() {
        return this.mAddr.byte;
    }

    public String getStreetNumber() {
        return this.mAddr.do;
    }

    public String getTime() {
        return this.d;
    }

    public boolean hasAddr() {
        return this.void;
    }

    public boolean hasAltitude() {
        return this.c;
    }

    public boolean hasPoi() {
        return this.case;
    }

    public boolean hasRadius() {
        return this.try;
    }

    public boolean hasSateNumber() {
        return this.b;
    }

    public boolean hasSpeed() {
        return this.int;
    }

    public boolean isCellChangeFlag() {
        return this.e;
    }

    public void setAddrStr(String str) {
        this.a = str;
        this.void = true;
    }

    public void setAltitude(double d) {
        this.f = d;
        this.c = true;
    }

    public void setCoorType(String str) {
        this.goto = str;
    }

    public void setDerect(float f) {
        this.char = f;
    }

    public void setLatitude(double d) {
        this.else = d;
    }

    public void setLocType(int i) {
        this.if = i;
    }

    public void setLongitude(double d) {
        this.for = d;
    }

    public void setRadius(float f) {
        this.long = f;
        this.try = true;
    }

    public void setSatelliteNumber(int i) {
        this.do = i;
    }

    public void setSpeed(float f) {
        this.new = f;
        this.int = true;
    }

    public void setTime(String str) {
        this.d = str;
    }

    public String toJsonString() {
        return null;
    }

    public BDLocation toNewLocation(String str) {
        return null;
    }
}
