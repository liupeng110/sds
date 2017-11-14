package com.alibaba.wireless.security.open.umid;

import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;

public interface IUMIDComponent extends IComponent {
    public static final int ENVIRONMENT_DAILY = 1;
    public static final int ENVIRONMENT_ONLINE = 0;
    public static final int ENVIRONMENT_PRE = 2;

    String getSecurityToken() throws SecException;

    void initUMID(String str, int i, String str2, IUMIDInitListenerEx iUMIDInitListenerEx) throws SecException;

    void registerInitListener(IUMIDInitListener iUMIDInitListener) throws SecException;
}
