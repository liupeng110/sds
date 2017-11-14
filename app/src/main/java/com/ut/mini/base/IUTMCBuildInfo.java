package com.ut.mini.base;

public interface IUTMCBuildInfo {
    String getBuildID();

    String getFullSDKVersion();

    String getGitCommitID();

    String getShortSDKVersion();
}
