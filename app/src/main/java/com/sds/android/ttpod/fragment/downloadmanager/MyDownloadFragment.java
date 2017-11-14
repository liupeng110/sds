package com.sds.android.ttpod.fragment.downloadmanager;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.ttpod.widget.h;
import com.sds.android.ttpod.widget.h.a;
import java.util.ArrayList;

public class MyDownloadFragment extends DownloadManagerFragment {
    private static final ArrayList<a> LIST_DOWNLOAD_PAGE = new ArrayList<a>() {
        {
            add(new a("my_download", "tt_my_download_song"));
            add(new a("my_download", "tt_my_download_mv"));
            add(new a("my_download", "tt_my_download_doing"));
        }
    };
    private int mCurrentItem = 0;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage(((a) LIST_DOWNLOAD_PAGE.get(this.mCurrentItem)).c());
        trackModule(((a) LIST_DOWNLOAD_PAGE.get(this.mCurrentItem)).b());
        trackPlaySong(NewUser.LOCAL_LOGIN, ((a) LIST_DOWNLOAD_PAGE.get(this.mCurrentItem)).b(), false);
        setPageChangeListener(new h(this, this.mCurrentItem, LIST_DOWNLOAD_PAGE));
    }
}
