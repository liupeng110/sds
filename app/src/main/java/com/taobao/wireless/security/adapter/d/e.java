package com.taobao.wireless.security.adapter.d;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.taobao.dp.DeviceSecuritySDK;
import com.taobao.dp.http.DefaultUrlRequestService;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.datacollection.IDataCollectionComponent;
import com.taobao.wireless.security.sdk.staticdatastore.IStaticDataStoreComponent;
import com.tencent.open.SocialConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public final class e {
    private static String a = "((((ftp:|https:|http:)([\\Q/\\\\E])*)|())(((%[0-9a-fA-F][0-9a-fA-F])|([a-zA-Z0-9])|([\\Q$-_.+!*'(),;?&=\\E]))+(:((%[0-9a-fA-F][0-9a-fA-F])|([a-zA-Z0-9])|([\\Q$-_.+!*'(),;?&=\\E]))*)?@)?(((((([a-zA-Z0-9]){1}([a-zA-Z0-9\\-])*([a-zA-Z0-9]{1}))|([a-zA-Z0-9]))\\.)+(biz|com|edu|gov|info|int|mil|name|net|org|pro|aero|cat|coop|jobs|museum|travel|arpa|root|mobi|post|tel|asia|geo|kid|mail|sco|web|xxx|nato|example|invalid|test|bitnet|csnet|onion|uucp|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|um|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw))|([0-9]{1,3}(\\.[0-9]{1,3}){3}))(\\:([0-9]+))?(([\\Q/\\\\E])+((((%[0-9a-fA-F][0-9a-fA-F])|([a-zA-Z0-9])|([\\Q$-_.+\\!*'(),;:@&=\\E]))*)(([\\Q/\\\\E])*((%[0-9a-fA-F]{2})|([a-zA-Z0-9])|([\\Q$-_.+\\!*'(),;:@&=\\E]))*)*)(\\?((%[0-9a-fA-F]{2})|([a-zA-Z0-9])|([\\Q$-_.+!*'(),;:@&=<>#\"{}[] ^`~|\\/\\E]))*)*)*)";
    private static String b = "";
    private static String c = "http://wlc.alibaba-inc.com/kgbreport";

    public static int a(String str, Context context) {
        if (context == null || str == null) {
            return 3;
        }
        DeviceSecuritySDK.getInstance(context).init(new DefaultUrlRequestService());
        String securityToken = DeviceSecuritySDK.getInstance(context).getSecurityToken();
        return securityToken == null ? 1 : a(c(str), securityToken, context);
    }

    private static int a(HashSet hashSet, String str, Context context) {
        if (hashSet == null || hashSet.size() <= 0) {
            return 4;
        }
        try {
            String str2;
            String str3;
            Object obj;
            Object staticSafeEncrypt;
            long currentTimeMillis;
            HttpResponse a;
            int statusCode;
            int i;
            JSONArray jSONArray = new JSONArray(hashSet);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("urls", jSONArray);
            jSONObject.put("token", str);
            jSONObject.put("appname", "mobileclient");
            jSONObject.put(SocialConstants.PARAM_TYPE, FeedbackItem.STATUS_RECORDED);
            SecurityGuardManager instance = SecurityGuardManager.getInstance(context);
            String str4 = "";
            String str5 = "";
            String str6 = "";
            if (instance != null) {
                IDataCollectionComponent dataCollectionComp = instance.getDataCollectionComp();
                if (dataCollectionComp != null) {
                    str4 = dataCollectionComp.getNick();
                }
                IStaticDataStoreComponent staticDataStoreComp = instance.getStaticDataStoreComp();
                str5 = staticDataStoreComp.getAppKeyByIndex(0);
                if (b != null && b.length() > 0) {
                    str6 = staticDataStoreComp.getExtraData(b);
                    str2 = str5;
                    str3 = str6;
                    obj = str4;
                    str4 = str3;
                    str5 = "sendertbaccount";
                    if (obj == null) {
                        obj = "";
                    }
                    jSONObject.put(str5, obj);
                    str6 = "";
                    if (instance == null) {
                        staticSafeEncrypt = instance.getStaticDataEncryptComp().staticSafeEncrypt(16, str2, jSONObject.toString());
                    } else {
                        str5 = str6;
                    }
                    if (staticSafeEncrypt == null) {
                        return 5;
                    }
                    jSONObject = new JSONObject();
                    obj = "";
                    if (str4 != null && str4.length() > 0) {
                        obj = b(str4);
                    }
                    currentTimeMillis = System.currentTimeMillis() / 1000;
                    str4 = b(currentTimeMillis + "alibaba_antitrojan_client" + currentTimeMillis);
                    jSONObject.put("t", currentTimeMillis);
                    jSONObject.put("sign", str4);
                    jSONObject.put("appkey", str2);
                    jSONObject.put("keymd5", obj);
                    jSONObject.put("content", staticSafeEncrypt);
                    a = a.a(c, jSONObject);
                    if (a != null) {
                        statusCode = a.getStatusLine().getStatusCode();
                        new StringBuilder().append(statusCode);
                        if (statusCode == 200) {
                            i = new JSONObject(a.a(a)).getInt("code");
                            return i == 0 ? i + 100 : 0;
                        }
                    }
                    return 2;
                }
            }
            str2 = str5;
            str3 = str6;
            str6 = str4;
            str4 = str3;
            str5 = "sendertbaccount";
            if (obj == null) {
                obj = "";
            }
            jSONObject.put(str5, obj);
            str6 = "";
            if (instance == null) {
                str5 = str6;
            } else {
                staticSafeEncrypt = instance.getStaticDataEncryptComp().staticSafeEncrypt(16, str2, jSONObject.toString());
            }
            if (staticSafeEncrypt == null) {
                return 5;
            }
            jSONObject = new JSONObject();
            obj = "";
            obj = b(str4);
            currentTimeMillis = System.currentTimeMillis() / 1000;
            str4 = b(currentTimeMillis + "alibaba_antitrojan_client" + currentTimeMillis);
            jSONObject.put("t", currentTimeMillis);
            jSONObject.put("sign", str4);
            jSONObject.put("appkey", str2);
            jSONObject.put("keymd5", obj);
            jSONObject.put("content", staticSafeEncrypt);
            a = a.a(c, jSONObject);
            if (a != null) {
                statusCode = a.getStatusLine().getStatusCode();
                new StringBuilder().append(statusCode);
                if (statusCode == 200) {
                    i = new JSONObject(a.a(a)).getInt("code");
                    if (i == 0) {
                    }
                }
            }
            return 2;
        } catch (Throwable th) {
        }
    }

    public static void a(String str) {
        b = str;
    }

    private static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (!(str == null || "".equals(str))) {
                byte[] digest = instance.digest(str.getBytes());
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    stringBuffer.append(Integer.toHexString((b & MotionEventCompat.ACTION_MASK) | 256).substring(1, 3));
                }
                return stringBuffer.toString();
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return "";
    }

    private static HashSet c(String str) {
        Matcher matcher = Pattern.compile(a).matcher(str);
        HashSet hashSet = new HashSet();
        while (matcher.find()) {
            hashSet.add(matcher.group());
        }
        return hashSet;
    }
}
