package com.taobao.wireless.security.sdk;

import android.content.Context;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.sdk.datacollection.IDataCollectionComponent;
import com.taobao.wireless.security.sdk.dynamicdataencrypt.IDynamicDataEncryptComponent;
import com.taobao.wireless.security.sdk.dynamicdatastore.IDynamicDataStoreComponent;
import com.taobao.wireless.security.sdk.indiekit.IIndieKitComponent;
import com.taobao.wireless.security.sdk.initialize.IInitializeComponent;
import com.taobao.wireless.security.sdk.initialize.a;
import com.taobao.wireless.security.sdk.pkgvaliditycheck.IPkgValidityCheckComponent;
import com.taobao.wireless.security.sdk.rootdetect.IRootDetectComponent;
import com.taobao.wireless.security.sdk.securesignature.ISecureSignatureComponent;
import com.taobao.wireless.security.sdk.securityDNS.ISecurityDNSComponent;
import com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent;
import com.taobao.wireless.security.sdk.securitydetect.ISecurityDetect;
import com.taobao.wireless.security.sdk.simulatordetect.ISimulatorDetectComponent;
import com.taobao.wireless.security.sdk.staticdataencrypt.IStaticDataEncryptComponent;
import com.taobao.wireless.security.sdk.staticdatastore.IStaticDataStoreComponent;

public class SecurityGuardManager {
    private static volatile SecurityGuardManager b;
    private static volatile IInitializeComponent c;
    private static final Object d = new Object();
    private a a;
    private Context e;

    private SecurityGuardManager(Context context) {
        this.a = a.a(context);
        this.e = context;
    }

    private IComponent a(int i) {
        return this.a.a(i);
    }

    public static IInitializeComponent getInitializer() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new a();
                }
            }
        }
        return c;
    }

    public static SecurityGuardManager getInstance(Context context) {
        if (b == null) {
            synchronized (SecurityGuardManager.class) {
                if (context == null) {
                    return null;
                } else if (b == null && getInitializer().initialize(context) == 0) {
                    b = new SecurityGuardManager(context);
                }
            }
        }
        return b;
    }

    public IDataCollectionComponent getDataCollectionComp() {
        return (IDataCollectionComponent) a(6);
    }

    public IDynamicDataEncryptComponent getDynamicDataEncryptComp() {
        return (IDynamicDataEncryptComponent) a(9);
    }

    public IDynamicDataStoreComponent getDynamicDataStoreComp() {
        return (IDynamicDataStoreComponent) a(2);
    }

    public IIndieKitComponent getIndieKitComp() {
        return (IIndieKitComponent) a(3);
    }

    public IPkgValidityCheckComponent getPackageValidityCheckComp() {
        return (IPkgValidityCheckComponent) a(12);
    }

    public IRootDetectComponent getRootDetectComp() {
        return (IRootDetectComponent) a(5);
    }

    public String getSDKVerison() {
        try {
            return com.alibaba.wireless.security.open.SecurityGuardManager.getInstance(this.e).getSDKVerison();
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ISecureSignatureComponent getSecureSignatureComp() {
        return (ISecureSignatureComponent) a(1);
    }

    public ISecurityBodyComponent getSecurityBodyComp() {
        return (ISecurityBodyComponent) a(8);
    }

    public ISecurityDNSComponent getSecurityDNSComp() {
        return (ISecurityDNSComponent) a(11);
    }

    public ISecurityDetect getSecurityDetectComp() {
        return (ISecurityDetect) a(13);
    }

    public ISimulatorDetectComponent getSimulatorDetectComp() {
        return (ISimulatorDetectComponent) a(10);
    }

    public IStaticDataEncryptComponent getStaticDataEncryptComp() {
        return (IStaticDataEncryptComponent) a(7);
    }

    public IStaticDataStoreComponent getStaticDataStoreComp() {
        return (IStaticDataStoreComponent) a(4);
    }

    public Boolean isOpen() {
        return Boolean.valueOf(false);
    }
}
