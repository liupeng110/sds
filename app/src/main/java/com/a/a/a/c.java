package com.a.a.a;

import android.app.PendingIntent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;

/* DualSimUtils */
public class c {
    public static String a(Context context, int i) throws NoSuchMethodException {
        String str;
        Object obj = null;
        String str2 = null;
        Object obj2 = 1;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            obj = 1;
            str2 = (String) Class.forName("android.telephony.TelephonyManager").getMethod("getSubscriberIdGemini", new Class[]{Integer.TYPE}).invoke(telephonyManager, new Object[]{Integer.valueOf(i)});
        } catch (Exception e) {
        } catch (Error e2) {
        }
        if (str2 == null) {
            try {
                Class cls = Class.forName("android.telephony.MSimTelephonyManager");
                Object invoke = cls.getMethod("getDefault", null).invoke(null, null);
                obj = 1;
                str2 = (String) cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(invoke, new Object[]{Integer.valueOf(i)});
            } catch (Exception e3) {
            } catch (Error e4) {
            }
        }
        if (str2 == null) {
            try {
                cls = Class.forName("com.mediatek.telephony.TelephonyManagerEx");
                invoke = cls.getMethod("getDefault", null).invoke(null, null);
                obj = 1;
                str2 = (String) cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE}).invoke(invoke, new Object[]{Integer.valueOf(i)});
            } catch (Exception e5) {
            } catch (Error e6) {
            }
        }
        if (str2 == null) {
            try {
                cls = Class.forName("android.telephony.TelephonyManager");
                str = (String) cls.getMethod("getSubscriberId", null).invoke(cls.getMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(i)}), null);
            } catch (Exception e7) {
                obj2 = obj;
                str = str2;
            } catch (Error e8) {
                obj2 = obj;
                str = str2;
            }
        } else {
            obj2 = obj;
            str = str2;
        }
        if (str == null) {
            str = "";
        }
        if (obj2 != null) {
            return str;
        }
        throw new NoSuchMethodException();
    }

    public static int a(Context context) throws NoSuchMethodException {
        Class cls;
        Object invoke;
        int i;
        try {
            return ((Integer) Class.forName("android.net.NetworkInfo").getMethod("getSimId", null).invoke(((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0), null)).intValue();
        } catch (Exception e) {
            try {
                cls = Class.forName("android.telephony.MSimTelephonyManager");
                return ((Integer) cls.getMethod("getPreferredDataSubscription", null).invoke(cls.getMethod("getDefault", null).invoke(null, null), null)).intValue();
            } catch (Exception e2) {
                try {
                    cls = Class.forName("android.telephony.MSimTelephonyManager");
                    return ((Integer) cls.getMethod("getDefaultSubscription", null).invoke(cls.getMethod("getDefault", null).invoke(null, null), null)).intValue();
                } catch (Exception e3) {
                    try {
                        cls = Class.forName("android.telephony.TelephonyManager");
                        invoke = cls.getMethod("getDefault", new Class[0]).invoke(null, null);
                        return ((Integer) cls.getMethod("getDefaultSim", new Class[]{Context.class, Integer.TYPE}).invoke(invoke, new Object[]{context, Integer.valueOf(1)})).intValue();
                    } catch (Exception e4) {
                        try {
                            cls = Class.forName("android.telephony.TelephonyManager");
                            return ((Integer) cls.getMethod("getSmsDefaultSim", null).invoke(cls.getMethod("getDefault", new Class[0]).invoke(null, null), null)).intValue();
                        } catch (Exception e5) {
                            try {
                                i = System.getInt(context.getContentResolver(), "gprs_connection_setting", -4);
                                if (i >= 0 || i > 2) {
                                    return 0;
                                }
                                return i;
                            } catch (Exception e6) {
                                throw new NoSuchMethodException();
                            } catch (Error e7) {
                                throw new NoSuchMethodException();
                            }
                        } catch (Error e8) {
                            i = System.getInt(context.getContentResolver(), "gprs_connection_setting", -4);
                            if (i >= 0) {
                            }
                            return 0;
                        }
                    } catch (Error e9) {
                        cls = Class.forName("android.telephony.TelephonyManager");
                        return ((Integer) cls.getMethod("getSmsDefaultSim", null).invoke(cls.getMethod("getDefault", new Class[0]).invoke(null, null), null)).intValue();
                    }
                } catch (Error e10) {
                    cls = Class.forName("android.telephony.TelephonyManager");
                    invoke = cls.getMethod("getDefault", new Class[0]).invoke(null, null);
                    return ((Integer) cls.getMethod("getDefaultSim", new Class[]{Context.class, Integer.TYPE}).invoke(invoke, new Object[]{context, Integer.valueOf(1)})).intValue();
                }
            } catch (Error e11) {
                cls = Class.forName("android.telephony.MSimTelephonyManager");
                return ((Integer) cls.getMethod("getDefaultSubscription", null).invoke(cls.getMethod("getDefault", null).invoke(null, null), null)).intValue();
            }
        } catch (Error e12) {
            cls = Class.forName("android.telephony.MSimTelephonyManager");
            return ((Integer) cls.getMethod("getPreferredDataSubscription", null).invoke(cls.getMethod("getDefault", null).invoke(null, null), null)).intValue();
        }
    }

    public static void a(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i) throws NoSuchMethodException {
        Object invoke;
        try {
            Class cls = Class.forName("android.telephony.gemini.GeminiSmsManager");
            cls.getMethod("sendTextMessageGemini", new Class[]{String.class, String.class, String.class, Integer.TYPE, PendingIntent.class, PendingIntent.class}).invoke(cls, new Object[]{str, str2, str3, Integer.valueOf(i), pendingIntent, pendingIntent2});
            return;
        } catch (Exception e) {
        } catch (Error e2) {
        }
        try {
            cls = Class.forName("android.telephony.MSimSmsManager");
            invoke = cls.getMethod("getDefault", null).invoke(null, null);
            cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class, Integer.TYPE}).invoke(invoke, new Object[]{str, str2, str3, pendingIntent, pendingIntent2, Integer.valueOf(i)});
        } catch (Exception e3) {
            try {
                cls = Class.forName("android.telephony.MSimSmsManager");
                cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class, Integer.TYPE}).invoke(cls.newInstance(), new Object[]{str, str2, str3, pendingIntent, pendingIntent2, Integer.valueOf(i)});
            } catch (Exception e4) {
                try {
                    cls = Class.forName("android.telephony.SmsManager");
                    invoke = cls.getDeclaredMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(i)});
                    cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class}).invoke(invoke, new Object[]{str, str2, str3, pendingIntent, pendingIntent2});
                } catch (Exception e5) {
                    try {
                        cls = Class.forName("com.mediatek.telephony.SmsManager");
                        cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, Integer.TYPE, PendingIntent.class, PendingIntent.class}).invoke(cls.newInstance(), new Object[]{str, str2, str3, Integer.valueOf(i), pendingIntent, pendingIntent2});
                    } catch (Exception e6) {
                        try {
                            cls = Class.forName("com.mediatek.telephony.SmsManagerEx");
                            invoke = cls.getMethod("getDefault", null).invoke(null, null);
                            cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class, Integer.TYPE}).invoke(invoke, new Object[]{str, str2, str3, pendingIntent, pendingIntent2, Integer.valueOf(i)});
                        } catch (Exception e7) {
                            throw new NoSuchMethodException();
                        } catch (Error e8) {
                            throw new NoSuchMethodException();
                        }
                    } catch (Error e9) {
                        cls = Class.forName("com.mediatek.telephony.SmsManagerEx");
                        invoke = cls.getMethod("getDefault", null).invoke(null, null);
                        cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class, Integer.TYPE}).invoke(invoke, new Object[]{str, str2, str3, pendingIntent, pendingIntent2, Integer.valueOf(i)});
                    }
                } catch (Error e10) {
                    cls = Class.forName("com.mediatek.telephony.SmsManager");
                    cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, Integer.TYPE, PendingIntent.class, PendingIntent.class}).invoke(cls.newInstance(), new Object[]{str, str2, str3, Integer.valueOf(i), pendingIntent, pendingIntent2});
                }
            } catch (Error e11) {
                cls = Class.forName("android.telephony.SmsManager");
                invoke = cls.getDeclaredMethod("getDefault", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(i)});
                cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class}).invoke(invoke, new Object[]{str, str2, str3, pendingIntent, pendingIntent2});
            }
        } catch (Error e12) {
            cls = Class.forName("android.telephony.MSimSmsManager");
            cls.getMethod("sendTextMessage", new Class[]{String.class, String.class, String.class, PendingIntent.class, PendingIntent.class, Integer.TYPE}).invoke(cls.newInstance(), new Object[]{str, str2, str3, pendingIntent, pendingIntent2, Integer.valueOf(i)});
        }
    }
}
