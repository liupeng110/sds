package com.taobao.dp.client;

import com.taobao.dp.http.IUrlRequestService;

public interface IDeviceSecurity {
    String didStrategyChanged(String str);

    String getAppName();

    String getSecurityToken();

    void init(IUrlRequestService iUrlRequestService);

    void notifyDidChanged(String str);

    void onInitFinished(int i);

    void sendLoginResult(String str);
}
