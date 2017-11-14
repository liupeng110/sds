package com.taobao.securityjni;

import android.content.ContextWrapper;
import com.taobao.securityjni.tools.DataContext;
import com.taobao.wireless.security.sdk.SecurityGuardManager;
import com.taobao.wireless.security.sdk.dynamicdataencrypt.IDynamicDataEncryptComponent;
import com.taobao.wireless.security.sdk.staticdataencrypt.IStaticDataEncryptComponent;
import com.taobao.wireless.security.sdk.staticdatastore.IStaticDataStoreComponent;

public class UtilWX {
    private ContextWrapper context;

    public UtilWX(ContextWrapper contextWrapper) {
        this.context = contextWrapper;
    }

    public String DecryptData(String str, String str2) {
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
            if (staticDataEncryptComp != null) {
                byte[] staticBinarySafeDecrypt = staticDataEncryptComp.staticBinarySafeDecrypt(16, str2, str.getBytes());
                if (staticBinarySafeDecrypt != null) {
                    return new String(staticBinarySafeDecrypt);
                }
            }
        }
        return null;
    }

    public byte[] DecryptData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr.length > 0 && bArr2 != null && bArr2.length > 0) {
            IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
            if (staticDataEncryptComp != null) {
                return staticDataEncryptComp.staticBinarySafeDecrypt(16, new String(bArr2), bArr);
            }
        }
        return null;
    }

    public String EncryptData(String str, String str2) {
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
            if (staticDataEncryptComp != null) {
                byte[] staticBinarySafeEncrypt = staticDataEncryptComp.staticBinarySafeEncrypt(16, str2, str.getBytes());
                if (staticBinarySafeEncrypt != null) {
                    return new String(staticBinarySafeEncrypt);
                }
            }
        }
        return null;
    }

    public byte[] EncryptData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr.length > 0 && bArr2 != null && bArr2.length > 0) {
            IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
            if (staticDataEncryptComp != null) {
                return staticDataEncryptComp.staticBinarySafeEncrypt(16, new String(bArr2), bArr);
            }
        }
        return null;
    }

    public String Get(String str) {
        if (str != null && str.length() > 0) {
            IDynamicDataEncryptComponent dynamicDataEncryptComp = SecurityGuardManager.getInstance(this.context).getDynamicDataEncryptComp();
            if (dynamicDataEncryptComp != null) {
                return dynamicDataEncryptComp.dynamicDecrypt(str);
            }
        }
        return null;
    }

    public String Get(String str, DataContext dataContext) {
        if (!(str == null || str.length() <= 0 || dataContext == null)) {
            IStaticDataStoreComponent staticDataStoreComp = SecurityGuardManager.getInstance(this.context).getStaticDataStoreComp();
            if (staticDataStoreComp != null) {
                String str2;
                if (dataContext.extData != null) {
                    str2 = new String(dataContext.extData);
                } else {
                    str2 = staticDataStoreComp.getAppKeyByIndex(dataContext.index < 0 ? 0 : dataContext.index);
                }
                if (str2 != null) {
                    IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
                    if (staticDataEncryptComp != null) {
                        return staticDataEncryptComp.staticSafeDecrypt(16, str2, str);
                    }
                }
            }
        }
        return null;
    }

    public byte[] Get(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            IDynamicDataEncryptComponent dynamicDataEncryptComp = SecurityGuardManager.getInstance(this.context).getDynamicDataEncryptComp();
            if (dynamicDataEncryptComp != null) {
                String dynamicDecrypt = dynamicDataEncryptComp.dynamicDecrypt(new String(bArr));
                if (dynamicDecrypt != null && dynamicDecrypt.length() > 0) {
                    return dynamicDecrypt.getBytes();
                }
            }
        }
        return null;
    }

    public byte[] Get(byte[] bArr, DataContext dataContext) {
        if (!(bArr == null || bArr.length <= 0 || dataContext == null)) {
            IStaticDataStoreComponent staticDataStoreComp = SecurityGuardManager.getInstance(this.context).getStaticDataStoreComp();
            if (staticDataStoreComp != null) {
                String str;
                if (dataContext.extData != null) {
                    str = new String(dataContext.extData);
                } else {
                    str = staticDataStoreComp.getAppKeyByIndex(dataContext.index < 0 ? 0 : dataContext.index);
                }
                if (str != null) {
                    IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
                    if (staticDataEncryptComp != null) {
                        return staticDataEncryptComp.staticBinarySafeDecrypt(16, str, bArr);
                    }
                }
            }
        }
        return null;
    }

    public String Put(String str) {
        if (str != null && str.length() > 0) {
            IDynamicDataEncryptComponent dynamicDataEncryptComp = SecurityGuardManager.getInstance(this.context).getDynamicDataEncryptComp();
            if (dynamicDataEncryptComp != null) {
                return dynamicDataEncryptComp.dynamicEncrypt(str);
            }
        }
        return null;
    }

    public String Put(String str, DataContext dataContext) {
        if (!(str == null || str.length() <= 0 || dataContext == null)) {
            IStaticDataStoreComponent staticDataStoreComp = SecurityGuardManager.getInstance(this.context).getStaticDataStoreComp();
            if (staticDataStoreComp != null) {
                String str2;
                if (dataContext.extData != null) {
                    str2 = new String(dataContext.extData);
                } else {
                    str2 = staticDataStoreComp.getAppKeyByIndex(dataContext.index < 0 ? 0 : dataContext.index);
                }
                if (str2 != null) {
                    IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
                    if (staticDataEncryptComp != null) {
                        return staticDataEncryptComp.staticSafeEncrypt(16, str2, str);
                    }
                }
            }
        }
        return null;
    }

    public byte[] Put(byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            IDynamicDataEncryptComponent dynamicDataEncryptComp = SecurityGuardManager.getInstance(this.context).getDynamicDataEncryptComp();
            if (dynamicDataEncryptComp != null) {
                String dynamicEncrypt = dynamicDataEncryptComp.dynamicEncrypt(new String(bArr));
                if (dynamicEncrypt != null && dynamicEncrypt.length() > 0) {
                    return dynamicEncrypt.getBytes();
                }
            }
        }
        return null;
    }

    public byte[] Put(byte[] bArr, DataContext dataContext) {
        if (!(bArr == null || bArr.length <= 0 || dataContext == null)) {
            IStaticDataStoreComponent staticDataStoreComp = SecurityGuardManager.getInstance(this.context).getStaticDataStoreComp();
            if (staticDataStoreComp != null) {
                String str;
                if (dataContext.extData != null) {
                    str = new String(dataContext.extData);
                } else {
                    str = staticDataStoreComp.getAppKeyByIndex(dataContext.index < 0 ? 0 : dataContext.index);
                }
                if (str != null) {
                    IStaticDataEncryptComponent staticDataEncryptComp = SecurityGuardManager.getInstance(this.context).getStaticDataEncryptComp();
                    if (staticDataEncryptComp != null) {
                        return staticDataEncryptComp.staticBinarySafeEncrypt(16, str, bArr);
                    }
                }
            }
        }
        return null;
    }
}
