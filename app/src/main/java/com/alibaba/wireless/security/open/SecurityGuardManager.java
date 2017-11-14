package com.alibaba.wireless.security.open;

import android.content.Context;
import com.alibaba.wireless.security.open.datacollection.IDataCollectionComponent;
import com.alibaba.wireless.security.open.dynamicdataencrypt.IDynamicDataEncryptComponent;
import com.alibaba.wireless.security.open.dynamicdatastore.IDynamicDataStoreComponent;
import com.alibaba.wireless.security.open.initialize.IInitializeComponent;
import com.alibaba.wireless.security.open.initialize.a;
import com.alibaba.wireless.security.open.opensdk.IOpenSDKComponent;
import com.alibaba.wireless.security.open.pkgvaliditycheck.IPkgValidityCheckComponent;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import com.alibaba.wireless.security.open.staticdatastore.IStaticDataStoreComponent;
import com.alibaba.wireless.security.open.statickeyencrypt.IStaticKeyEncryptComponent;
import com.alibaba.wireless.security.open.umid.IUMIDComponent;

public class SecurityGuardManager {
    private static volatile SecurityGuardManager b;
    private static volatile IInitializeComponent c;
    private static final Object d = new Object();
    private a a;

    private SecurityGuardManager(Context context) {
        this.a = a.a(context);
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

    public static SecurityGuardManager getInstance(Context context) throws SecException {
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
        return (IDataCollectionComponent) a(5);
    }

    public IDynamicDataEncryptComponent getDynamicDataEncryptComp() {
        return (IDynamicDataEncryptComponent) a(7);
    }

    public IDynamicDataStoreComponent getDynamicDataStoreComp() {
        return (IDynamicDataStoreComponent) a(2);
    }

    public IOpenSDKComponent getOpenSDKComp() {
        return (IOpenSDKComponent) a(10);
    }

    public IPkgValidityCheckComponent getPackageValidityCheckComp() {
        return (IPkgValidityCheckComponent) a(12);
    }

    public String getSDKVerison() {
        return "2.3.39";
    }

    public ISecureSignatureComponent getSecureSignatureComp() {
        return (ISecureSignatureComponent) a(1);
    }

    public IStaticDataEncryptComponent getStaticDataEncryptComp() {
        return (IStaticDataEncryptComponent) a(6);
    }

    public IStaticDataStoreComponent getStaticDataStoreComp() {
        return (IStaticDataStoreComponent) a(3);
    }

    public IStaticKeyEncryptComponent getStaticKeyEncryptComp() {
        return (IStaticKeyEncryptComponent) a(9);
    }

    public IUMIDComponent getUMIDComp() {
        return (IUMIDComponent) a(11);
    }

    public Boolean isOpen() {
        return Boolean.valueOf(false);
    }
}
