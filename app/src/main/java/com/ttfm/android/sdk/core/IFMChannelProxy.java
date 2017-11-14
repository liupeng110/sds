package com.ttfm.android.sdk.core;

public interface IFMChannelProxy {
    String getChannelClassify(long j);

    String getChannelClassifyList(long j, int i, String str, int i2);

    String getChannelClassifyListV2(long j, int i, int i2, String str);

    String getChannelDetailURL(int i, int i2);

    String getChannelHomePage(long j, int i);

    String getChannelInfo(long j, int i);

    String getChannelList(long j, int i, int i2, int i3, String str);

    String getChannelSongList(long j, int i, int i2, int i3);

    String getChannelSongListWithUrl(long j, int i, int i2);

    String getDynamicTagList(long j);
}
