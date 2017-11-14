package com.sds.android.ttpod.fragment.main;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.NewUser;

public class MySongListFragment extends SubCustomGroupListFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_my_songlist");
        trackPlaySong(NewUser.LOCAL_LOGIN, "tt_my_songlist", false);
        trackModule("my_songlist");
    }
}
