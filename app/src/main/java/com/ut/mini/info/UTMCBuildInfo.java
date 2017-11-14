package com.ut.mini.info;

import com.ut.mini.base.IUTMCBuildInfo;

public class UTMCBuildInfo implements IUTMCBuildInfo {
    private static final String BUILD_ID = "321863";
    private static final String FULL_SDK_VERSION = "4.3.1.321863";
    private static final String GIT_COMMIT_ID = "7b4d1ec0b8bffa915e760a9d373c209cd5495617";
    private static final String SHORT_SDK_VERSION = "4.3.1";
    private static UTMCBuildInfo s_instance = new UTMCBuildInfo();

    public static UTMCBuildInfo getInstance() {
        return s_instance;
    }

    public String getBuildID() {
        return BUILD_ID;
    }

    public String getGitCommitID() {
        return GIT_COMMIT_ID;
    }

    public String getShortSDKVersion() {
        return SHORT_SDK_VERSION;
    }

    public String getFullSDKVersion() {
        return FULL_SDK_VERSION;
    }
}
