package com.sds.android.ttpod.activities.soundsearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;

public class SoundSearchMultiResultActivity extends SoundSearchMediaListActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_sound_recognize_result");
        setTitle((int) R.string.search_sound_search);
        a actionBarController = getActionBarController();
        actionBarController.d();
        actionBarController.d((int) R.drawable.img_actionitem_history).a(new b(this) {
            final /* synthetic */ SoundSearchMultiResultActivity a;

            {
                this.a = r1;
            }

            public void a(a.a aVar) {
                this.a.startActivity(new Intent(this.a, SoundSearchHistoryActivity.class));
            }
        });
    }

    protected void onBindData(a aVar) {
        Parcelable[] parcelableArrayExtra = getIntent().getParcelableArrayExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT);
        int length = parcelableArrayExtra.length;
        SoundSearchInfo[] soundSearchInfoArr = new SoundSearchInfo[length];
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < length; i++) {
            SoundSearchInfo soundSearchInfo = (SoundSearchInfo) parcelableArrayExtra[i];
            soundSearchInfoArr[i] = soundSearchInfo;
            stringBuilder.append(String.valueOf(soundSearchInfo.f().getID()));
            stringBuilder2.append(String.valueOf(soundSearchInfo.b()));
            if (i < length - 1) {
                stringBuilder.append(";");
                stringBuilder2.append(";");
            }
        }
        aVar.a(soundSearchInfoArr);
        doAlibabaStatic(stringBuilder.toString(), stringBuilder2.toString(), String.valueOf(getIntent().getLongExtra(SoundSearchActivity.EXTRA_RECOGNIZE_TIME, 0)));
    }

    private void doAlibabaStatic(String str, String str2, String str3) {
        updateAlibabaProperty(MediasColumns.SONG_ID, str);
        updateAlibabaProperty("recognize_rating", str2);
        updateAlibabaProperty("recognize_time", str3);
    }

    protected void onAddHeaderView(ListView listView) {
        listView.addHeaderView(View.inflate(this, R.layout.soundsearch_multiresult_head, null));
    }
}
