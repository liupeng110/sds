package com.sds.android.ttpod.b;

import android.content.Context;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.media.mediastore.GroupItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.List;

/* GroupItemUtils */
public class l {
    public static String a(Context context) {
        String string = context.getString(R.string.playlist);
        List<GroupItem> j = a.a().j();
        int i = 1;
        while (true) {
            Object obj;
            StringBuilder append = new StringBuilder().append(string);
            int i2 = i + 1;
            String stringBuilder = append.append(i).toString();
            for (GroupItem name : j) {
                if (name.getName().equals(stringBuilder)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                return stringBuilder;
            }
            i = i2;
        }
    }

    public static boolean a(String str) {
        for (GroupItem name : a.a().j()) {
            if (name.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(String str) {
        return (!str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) || MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(str) || MediaStorage.GROUP_ID_RECENTLY_ADD.equals(str)) ? false : true;
    }
}
