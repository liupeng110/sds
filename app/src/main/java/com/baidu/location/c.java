package com.baidu.location;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

class c {
    private static String byte = null;
    private static int c = 3;
    private static Method case = null;
    private static boolean char = false;
    private static Class d = null;
    private static Method for = null;
    private static String goto = null;
    private static Method long = null;
    private static long void = 3000;
    private a a = new a(this);
    private boolean b = false;
    private Handler do = null;
    private final String else = f.v;
    private Context if = null;
    private b int = null;
    private List new = null;
    private TelephonyManager try = null;

    public class a {
        final /* synthetic */ c a;
        public long byte;
        public int do;
        public int for;
        public int if;
        public int int;
        public char new;
        public int try;

        public a(c cVar) {
            this.a = cVar;
            this.for = -1;
            this.try = -1;
            this.do = -1;
            this.if = -1;
            this.byte = 0;
            this.int = -1;
            this.new = '\u0000';
            this.byte = System.currentTimeMillis();
        }

        public a(c cVar, int i, int i2, int i3, int i4, char c) {
            this.a = cVar;
            this.for = -1;
            this.try = -1;
            this.do = -1;
            this.if = -1;
            this.byte = 0;
            this.int = -1;
            this.new = '\u0000';
            this.for = i;
            this.try = i2;
            this.do = i3;
            this.if = i4;
            this.new = c;
            this.byte = System.currentTimeMillis() / 1000;
        }

        public String a() {
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append(this.try + 23);
            stringBuffer.append(VersionUpdateConst.KEY_HIAPK_UPDATE_CATEGORY);
            stringBuffer.append(this.for + 45);
            stringBuffer.append("K");
            stringBuffer.append(this.if + 54);
            stringBuffer.append("Q");
            stringBuffer.append(this.do + SecExceptionCode.SEC_ERROR_STA_STORE_NO_DATA_FILE);
            return stringBuffer.toString();
        }

        public boolean a(a aVar) {
            return this.for == aVar.for && this.try == aVar.try && this.if == aVar.if;
        }

        public boolean do() {
            return System.currentTimeMillis() - this.byte < c.void;
        }

        public boolean for() {
            return this.for > -1 && this.try > 0;
        }

        public String if() {
            StringBuffer stringBuffer = new StringBuffer(64);
            stringBuffer.append(String.format("cell=%d|%d|%d|%d:%d", new Object[]{Integer.valueOf(this.do), Integer.valueOf(this.if), Integer.valueOf(this.for), Integer.valueOf(this.try), Integer.valueOf(this.int)}));
            return stringBuffer.toString();
        }

        public String int() {
            String str;
            try {
                List<NeighboringCellInfo> neighboringCellInfo = this.a.try.getNeighboringCellInfo();
                if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
                    str = null;
                    j.if(f.v, "Neighbour:" + str);
                    return str;
                }
                String str2 = "&nc=";
                int i = 0;
                for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                    if (i != 0) {
                        if (i >= 8) {
                            break;
                        }
                        str = neighboringCellInfo2.getLac() != this.for ? str2 + ";" + neighboringCellInfo2.getLac() + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() : str2 + ";" + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi();
                    } else {
                        str = neighboringCellInfo2.getLac() != this.for ? str2 + neighboringCellInfo2.getLac() + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi() : str2 + "|" + neighboringCellInfo2.getCid() + "|" + neighboringCellInfo2.getRssi();
                    }
                    i++;
                    str2 = str;
                }
                str = str2;
                j.if(f.v, "Neighbour:" + str);
                return str;
            } catch (Exception e) {
                str = null;
            }
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append("&nw=");
            stringBuffer.append(this.a.a.new);
            stringBuffer.append(String.format("&cl=%d|%d|%d|%d&cl_s=%d", new Object[]{Integer.valueOf(this.do), Integer.valueOf(this.if), Integer.valueOf(this.for), Integer.valueOf(this.try), Integer.valueOf(this.int)}));
            stringBuffer.append("&cl_t=");
            stringBuffer.append(this.byte);
            if (this.a.new != null && this.a.new.size() > 0) {
                int size = this.a.new.size();
                stringBuffer.append("&clt=");
                for (int i = 0; i < size; i++) {
                    a aVar = (a) this.a.new.get(i);
                    if (aVar.do != this.do) {
                        stringBuffer.append(aVar.do);
                    }
                    stringBuffer.append("|");
                    if (aVar.if != this.if) {
                        stringBuffer.append(aVar.if);
                    }
                    stringBuffer.append("|");
                    if (aVar.for != this.for) {
                        stringBuffer.append(aVar.for);
                    }
                    stringBuffer.append("|");
                    if (aVar.try != this.try) {
                        stringBuffer.append(aVar.try);
                    }
                    stringBuffer.append("|");
                    if (i != size - 1) {
                        stringBuffer.append(aVar.byte / 1000);
                    } else {
                        stringBuffer.append((System.currentTimeMillis() - aVar.byte) / 1000);
                    }
                    stringBuffer.append(";");
                }
            }
            return stringBuffer.toString();
        }
    }

    private class b extends PhoneStateListener {
        final /* synthetic */ c a;

        public b(c cVar) {
            this.a = cVar;
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            if (cellLocation != null) {
                try {
                    this.a.a(this.a.try.getCellLocation());
                } catch (Exception e) {
                }
            }
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (this.a.a != null) {
                if (this.a.a.new == 'g') {
                    this.a.a.int = signalStrength.getGsmSignalStrength();
                } else if (this.a.a.new == 'c') {
                    this.a.a.int = signalStrength.getCdmaDbm();
                }
                j.if("cell strength", "===== cell singal strength changed : " + this.a.a.int);
                if (this.a.do != null) {
                    this.a.do.obtainMessage(31).sendToTarget();
                }
            }
        }
    }

    public c(Context context, Handler handler) {
        this.if = context;
        this.do = handler;
    }

    public static String a(boolean z) {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("&sdk=");
        stringBuffer.append(3.3f);
        if (z && j.A.equals("all")) {
            stringBuffer.append("&addr=all");
        }
        if (z) {
            stringBuffer.append("&coor=gcj02");
        }
        if (goto == null) {
            stringBuffer.append("&im=");
            stringBuffer.append(byte);
        } else {
            stringBuffer.append("&cu=");
            stringBuffer.append(goto);
        }
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        stringBuffer.append("&os=A");
        stringBuffer.append(VERSION.SDK);
        if (z) {
            stringBuffer.append("&sv=");
            String str = VERSION.RELEASE;
            if (str != null && str.length() > 5) {
                str = str.substring(0, 5);
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    private void a(CellLocation cellLocation) {
        if (cellLocation != null && this.try != null) {
            int intValue;
            if (!char) {
                byte = this.try.getDeviceId();
                char = if();
            }
            j.if(f.v, "set cell info..");
            a aVar = new a(this);
            aVar.byte = System.currentTimeMillis();
            String networkOperator = this.try.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                        if (intValue < 0) {
                            intValue = this.a.do;
                        }
                        aVar.do = intValue;
                    }
                    networkOperator = networkOperator.substring(3);
                    if (networkOperator != null) {
                        char[] toCharArray = networkOperator.toCharArray();
                        intValue = 0;
                        while (intValue < toCharArray.length && Character.isDigit(toCharArray[intValue])) {
                            intValue++;
                        }
                    } else {
                        intValue = 0;
                    }
                    intValue = Integer.valueOf(networkOperator.substring(0, intValue)).intValue();
                    if (intValue < 0) {
                        intValue = this.a.if;
                    }
                    aVar.if = intValue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                aVar.for = ((GsmCellLocation) cellLocation).getLac();
                aVar.try = ((GsmCellLocation) cellLocation).getCid();
                aVar.new = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                aVar.new = 'c';
                if (Integer.parseInt(VERSION.SDK) >= 5) {
                    if (d == null) {
                        try {
                            d = Class.forName("android.telephony.cdma.CdmaCellLocation");
                            long = d.getMethod("getBaseStationId", new Class[0]);
                            case = d.getMethod("getNetworkId", new Class[0]);
                            for = d.getMethod("getSystemId", new Class[0]);
                        } catch (Exception e2) {
                            d = null;
                            e2.printStackTrace();
                            return;
                        }
                    }
                    if (d != null && d.isInstance(cellLocation)) {
                        try {
                            intValue = ((Integer) for.invoke(cellLocation, new Object[0])).intValue();
                            if (intValue < 0) {
                                intValue = this.a.if;
                            }
                            aVar.if = intValue;
                            aVar.try = ((Integer) long.invoke(cellLocation, new Object[0])).intValue();
                            aVar.for = ((Integer) case.invoke(cellLocation, new Object[0])).intValue();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                            return;
                        }
                    }
                }
                return;
            }
            if (!aVar.for()) {
                return;
            }
            if (this.a == null || !this.a.a(aVar)) {
                this.a = aVar;
                this.do.obtainMessage(31).sendToTarget();
                if (aVar.for()) {
                    if (this.new == null) {
                        this.new = new LinkedList();
                    }
                    intValue = this.new.size();
                    a aVar2 = intValue == 0 ? null : (a) this.new.get(intValue - 1);
                    if (aVar2 == null || aVar2.try != this.a.try || aVar2.for != this.a.for) {
                        if (aVar2 != null) {
                            aVar2.byte = this.a.byte - aVar2.byte;
                        }
                        this.new.add(this.a);
                        if (this.new.size() > c) {
                            this.new.remove(0);
                        }
                    }
                } else if (this.new != null) {
                    this.new.clear();
                }
            }
        }
    }

    private boolean if() {
        if (byte == null || byte.length() < 10) {
            return false;
        }
        try {
            char[] toCharArray = byte.toCharArray();
            int i = 0;
            while (i < 10) {
                if (toCharArray[i] > '9' || toCharArray[i] < '0') {
                    return false;
                }
                i++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public a a() {
        if (!((this.a != null && this.a.do() && this.a.for()) || this.try == null)) {
            try {
                a(this.try.getCellLocation());
            } catch (Exception e) {
            }
        }
        return this.a;
    }

    public void byte() {
        if (this.b) {
            if (!(this.int == null || this.try == null)) {
                this.try.listen(this.int, 0);
            }
            this.int = null;
            this.try = null;
            this.new.clear();
            this.new = null;
            j.if(f.v, "cell manager stop ...");
            this.b = false;
        }
    }

    public void do() {
        if (!this.b) {
            this.try = (TelephonyManager) this.if.getSystemService("phone");
            this.new = new LinkedList();
            this.int = new b(this);
            if (this.try != null && this.int != null) {
                try {
                    this.try.listen(this.int, 272);
                    byte = this.try.getDeviceId();
                    j.do = "v3.3" + byte + "|" + Build.MODEL;
                } catch (Exception e) {
                }
                try {
                    goto = com.baidu.location.j.a.if(this.if);
                    j.if(f.v, "CUID:" + goto);
                } catch (Exception e2) {
                    goto = null;
                }
                try {
                    if (goto != null) {
                        j.do = "v3.3|" + goto + "|" + Build.MODEL;
                    }
                    j.if(f.v, "CUID:" + j.do);
                } catch (Exception e3) {
                }
                char = if();
                j.a(f.v, "i:" + byte);
                j.if(f.v, "cell manager start...");
                this.b = true;
            }
        }
    }

    public String for() {
        if (this.try == null) {
            this.try = (TelephonyManager) this.if.getSystemService("phone");
        }
        try {
            a(this.try.getCellLocation());
        } catch (Exception e) {
        }
        return this.a.toString();
    }

    public String int() {
        return byte;
    }

    public int new() {
        return this.try.getNetworkType();
    }
}
