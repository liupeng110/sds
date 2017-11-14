package com.sds.android.cloudapi.ttpod.data;

import android.graphics.drawable.Drawable;

public interface ISongListItem {
    Drawable getIcon();

    int getIconResourceId();

    int getSubItemCount();

    CharSequence getSubtitleName();

    CharSequence getTitleName();
}
