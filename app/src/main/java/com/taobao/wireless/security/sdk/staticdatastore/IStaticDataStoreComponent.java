package com.taobao.wireless.security.sdk.staticdatastore;

import com.taobao.wireless.security.sdk.IComponent;

public interface IStaticDataStoreComponent extends IComponent {
    String getAppKeyByIndex(int i);

    String getExtraData(String str);

    int getKeyType(String str);
}
