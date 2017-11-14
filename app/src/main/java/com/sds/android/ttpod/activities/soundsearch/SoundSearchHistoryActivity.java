package com.sds.android.ttpod.activities.soundsearch;

import android.os.Bundle;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.component.soundsearch.c;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.modules.d.b;
import java.util.List;

public class SoundSearchHistoryActivity extends SoundSearchMediaListActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_sound_recognize_history");
        getActionBarController().d();
        setTitle((int) R.string.soundsearch_history);
    }

    protected void onBindData(final a aVar) {
        c cVar = new c(new b<List<SoundSearchInfo>>(this) {
            final /* synthetic */ SoundSearchHistoryActivity b;

            public void a(List<SoundSearchInfo> list) {
                SoundSearchInfo[] soundSearchInfoArr = new SoundSearchInfo[list.size()];
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    SoundSearchInfo soundSearchInfo = (SoundSearchInfo) list.get(i);
                    soundSearchInfo.f().setFav(k.a(soundSearchInfo.f()));
                    soundSearchInfoArr[i] = soundSearchInfo;
                }
                aVar.a(soundSearchInfoArr);
            }
        });
    }

    protected void onAddHeaderView(ListView listView) {
    }
}
