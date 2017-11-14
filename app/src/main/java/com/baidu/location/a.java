package com.baidu.location;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class a {
    private static String if = f.v;
    private boolean a = false;
    private ArrayList do = null;
    private boolean for = false;
    private Handler int = null;

    private class a {
        final /* synthetic */ a a;
        public LocationClientOption do = new LocationClientOption();
        public Messenger for = null;
        public int if = 0;
        public String int = null;

        public a(a aVar, Message message) {
            this.a = aVar;
            this.for = message.replyTo;
            this.int = message.getData().getString("packName");
            this.do.new = message.getData().getString("prodName");
            j.ak = this.int;
            j.b = this.do.new;
            this.do.try = message.getData().getString("coorType");
            this.do.char = message.getData().getString("addrType");
            j.A = this.do.char;
            this.do.case = message.getData().getBoolean("openGPS");
            this.do.int = message.getData().getInt("scanSpan");
            this.do.long = message.getData().getInt("timeOut");
            this.do.goto = message.getData().getInt("priority");
            this.do.void = message.getData().getBoolean("location_change_notify");
        }

        private void a(int i) {
            Message obtain = Message.obtain(null, i);
            try {
                if (this.for != null) {
                    this.for.send(obtain);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
            }
        }

        private void a(int i, String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putString(str, str2);
            Message obtain = Message.obtain(null, i);
            obtain.setData(bundle);
            try {
                if (this.for != null) {
                    this.for.send(obtain);
                }
                this.if = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.if++;
                }
            }
        }

        public void a() {
            a(23);
        }

        public void a(String str) {
            if (this.do.void) {
                if(str);
            }
        }

        public void a(String str, int i) {
            j.if(a.if, "decode...");
            if (str != null) {
                if (i == 21) {
                    a(27, "locStr", str);
                }
                if (!(this.do.try == null || this.do.try.equals("gcj02"))) {
                    double d = j.do(str, "x\":\"", "\"");
                    double d2 = j.do(str, "y\":\"", "\"");
                    j.if(a.if, "decode..." + d + ":" + d2);
                    if (!(d == Double.MIN_VALUE || d2 == Double.MIN_VALUE)) {
                        double[] dArr = Jni.if(d, d2, this.do.try);
                        str = j.a(j.a(str, "x\":\"", "\"", dArr[0]), "y\":\"", "\"", dArr[1]);
                        j.if(a.if, "decode2 ..." + dArr[0] + ":" + dArr[1]);
                        j.if(a.if, "decode3 ..." + str);
                    }
                    if (this.do.else) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                            JSONObject jSONObject3 = jSONObject.getJSONObject("content");
                            if (Integer.parseInt(jSONObject2.getString("error")) == BDLocation.TypeNetWorkLocation) {
                                JSONObject jSONObject4 = jSONObject3.getJSONObject("poi");
                                JSONArray jSONArray = jSONObject4.getJSONArray("p");
                                int i2 = 0;
                                while (i2 < jSONArray.length()) {
                                    JSONObject jSONObject5 = jSONArray.getJSONObject(i2);
                                    double parseDouble = Double.parseDouble(jSONObject5.getString("x"));
                                    double parseDouble2 = Double.parseDouble(jSONObject5.getString("y"));
                                    if (!(parseDouble == Double.MIN_VALUE || parseDouble2 == Double.MIN_VALUE)) {
                                        double[] dArr2 = Jni.if(parseDouble, parseDouble2, this.do.try);
                                        jSONObject5.put("x", String.valueOf(dArr2[0]));
                                        jSONObject5.put("y", String.valueOf(dArr2[1]));
                                        jSONArray.put(i2, jSONObject5);
                                        i2++;
                                    }
                                }
                                jSONObject4.put("p", jSONArray);
                                jSONObject3.put("poi", jSONObject4);
                                jSONObject.put("content", jSONObject3);
                                str = jSONObject.toString();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                a(i, "locStr", str);
            }
        }

        public void if() {
            if (!this.do.void) {
                return;
            }
            if (j.ab) {
                a(54);
            } else {
                a(55);
            }
        }

        public void if(String str) {
            if (str != null) {
                a(27, "locStr", str);
                if (!(this.do.try == null || this.do.try.equals("gcj02"))) {
                    double d = j.do(str, "x\":\"", "\"");
                    double d2 = j.do(str, "y\":\"", "\"");
                    if (!(d == Double.MIN_VALUE || d2 == Double.MIN_VALUE)) {
                        double[] dArr = Jni.if(d, d2, this.do.try);
                        if (dArr[0] > 0.0d || dArr[1] > 0.0d) {
                            str = j.a(j.a(str, "x\":\"", "\"", dArr[0]), "y\":\"", "\"", dArr[1]);
                        }
                    }
                }
                a(21, "locStr", str);
            }
        }
    }

    public a(Handler handler) {
        this.int = handler;
        this.do = new ArrayList();
    }

    private a a(Messenger messenger) {
        if (this.do == null) {
            return null;
        }
        Iterator it = this.do.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.for.equals(messenger)) {
                return aVar;
            }
        }
        return null;
    }

    private void a() {
        Iterator it = this.do.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = ((a) it.next()).do.new.equals("kuikedefancaiburudashahaochi") ? true : z;
        }
        if (this.a != z) {
            this.a = z;
            this.int.obtainMessage(81).sendToTarget();
        }
    }

    private void a(a aVar) {
        if (aVar != null) {
            if (a(aVar.for) != null) {
                aVar.a(14);
                return;
            }
            this.do.add(aVar);
            j.if(if, aVar.int + " registered ");
            aVar.a(13);
        }
    }

    private void do() {
        int();
        a();
        new();
    }

    private void int() {
        Iterator it = this.do.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.do.case) {
                z2 = true;
            }
            z = aVar.do.void ? true : z;
        }
        j.H = z;
        if (this.for != z2) {
            this.for = z2;
            this.int.obtainMessage(52).sendToTarget();
        }
    }

    public String a(Message message) {
        if (message == null || message.replyTo == null) {
            j.if(if, "invalid Poirequest");
            return null;
        }
        a a = a(message.replyTo);
        if (a == null) {
            return null;
        }
        a.do.a = message.getData().getInt("num", a.do.a);
        a.do.do = message.getData().getFloat("distance", a.do.do);
        a.do.if = message.getData().getBoolean("extraInfo", a.do.if);
        a.do.else = true;
        String format = String.format("&poi=%.1f|%d", new Object[]{Float.valueOf(a.do.do), Integer.valueOf(a.do.a)});
        return a.do.if ? format + "|1" : format;
    }

    public void a(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.do.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            aVar.if(str);
            if (aVar.if > 4) {
                arrayList.add(aVar);
            }
        }
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                aVar = (a) it2.next();
                j.if(if, "remove dead object...");
                this.do.remove(aVar);
            }
        }
    }

    public void a(String str, int i) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.do.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            aVar.a(str, i);
            if (aVar.if > 4) {
                arrayList.add(aVar);
            }
        }
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                aVar = (a) it2.next();
                j.if(if, "remove dead object...");
                this.do.remove(aVar);
            }
        }
    }

    public void a(String str, Message message) {
        if (str != null && message != null) {
            a a = a(message.replyTo);
            if (a != null) {
                a.if(str);
                if (a.if > 4) {
                    this.do.remove(a);
                    return;
                }
                return;
            }
            j.if(if, "not found the client messener...");
        }
    }

    public String byte() {
        StringBuffer stringBuffer = new StringBuffer(256);
        a aVar = (a) this.do.get(0);
        if (aVar.do.new != null) {
            stringBuffer.append(aVar.do.new);
        }
        if (aVar.int != null) {
            stringBuffer.append(":");
            stringBuffer.append(aVar.int);
            stringBuffer.append("|");
        }
        String stringBuffer2 = stringBuffer.toString();
        return (stringBuffer2 == null || stringBuffer2.equals("")) ? null : "&prod=" + stringBuffer2;
    }

    public int do(Message message) {
        if (message == null || message.replyTo == null) {
            return 1;
        }
        a a = a(message.replyTo);
        return (a == null || a.do == null) ? 1 : a.do.goto;
    }

    public boolean for() {
        return this.for;
    }

    public boolean for(Message message) {
        boolean z = true;
        a a = a(message.replyTo);
        if (a == null) {
            return false;
        }
        int i = a.do.int;
        a.do.int = message.getData().getInt("scanSpan", a.do.int);
        if (a.do.int < 1000) {
            j.R = false;
        } else {
            j.R = true;
        }
        if (a.do.int <= 999 || i >= 1000) {
            z = false;
        }
        a.do.case = message.getData().getBoolean("openGPS", a.do.case);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = a.do;
        if (string == null || string.equals("")) {
            string = a.do.try;
        }
        locationClientOption.try = string;
        string = message.getData().getString("addrType");
        locationClientOption = a.do;
        if (string == null || string.equals("")) {
            string = a.do.char;
        }
        locationClientOption.char = string;
        j.A = a.do.char;
        a.do.long = message.getData().getInt("timeOut", a.do.long);
        a.do.void = message.getData().getBoolean("location_change_notify", a.do.void);
        a.do.goto = message.getData().getInt("priority", a.do.goto);
        do();
        return z;
    }

    public void if() {
        Iterator it = this.do.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
    }

    public void if(Message message) {
        a a = a(message.replyTo);
        if (a != null) {
            j.if(if, a.int + " unregistered");
            this.do.remove(a);
        }
        do();
    }

    public void if(String str) {
        Iterator it = this.do.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(str);
        }
    }

    public void int(Message message) {
        if (message == null || message.replyTo == null) {
            j.if(if, "invalid regist client");
            return;
        }
        a(new a(this, message));
        do();
    }

    public void new() {
        Iterator it = this.do.iterator();
        while (it.hasNext()) {
            ((a) it.next()).if();
        }
    }
}
