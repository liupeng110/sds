package com.ttfm.android.sdk.core;

public interface IFMPlayProxy {
    String getMusicInfo(long j, int i, long j2, int i2);

    String playChannelDemand(long j, int i, long j2, int i2);

    String playChannelManual(long j, int i, int i2, long j2, long j3, int i3, int i4);

    String playChannelNext(long j, int i, int i2, long j2, long j3, long j4, int i3, int i4);

    String setChannelPause(long j, int i, long j2, long j3, long j4, int i2, int i3);
}
