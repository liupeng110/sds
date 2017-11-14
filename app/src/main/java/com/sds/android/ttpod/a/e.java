package com.sds.android.ttpod.a;

import com.sds.android.ttpod.R;

/* ShareType */
public enum e {
    NONE,
    FRIEND,
    MUSIC_CYCLE,
    SINA_WEIBO,
    QQ_WEIBO,
    QZONE,
    QQ,
    WECHAT,
    WECHAT_FRIENDS,
    COPY,
    OTHER;

    public static int getShareContentDialogTitle(e eVar) {
        switch (eVar) {
            case SINA_WEIBO:
                return R.string.share_to_sina_weibo;
            case QQ_WEIBO:
                return R.string.share_to_qq_weibo;
            case QZONE:
                return R.string.share_to_qqzone;
            default:
                return R.string.share;
        }
    }
}
