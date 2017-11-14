package com.taobao.wireless.security.sdk.datacollection;

import com.taobao.wireless.security.sdk.IComponent;

public interface IDataCollectionComponent extends IComponent {
    String getEncryptedDevAndEnvInfo(int i, String str);

    String getNick();

    boolean setNick(String str);
}
