package com.sds.android.cloudapi.ttpod.b;

import com.b.a.c.a;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.b.c;
import com.sds.android.sdk.lib.util.f;
import java.util.ArrayList;

/* GetFeedbackResult */
public class b extends c<FeedbackItem> {
    public b(com.sds.android.sdk.lib.b.b bVar) {
        setCode(bVar.getCode());
        setContent(bVar.getContent());
        setLocation(bVar.getLocation());
    }

    public ArrayList<FeedbackItem> a() {
        ArrayList<FeedbackItem> arrayList = (ArrayList) f.a(getContent(), new a<ArrayList<FeedbackItem>>(this) {
            final /* synthetic */ b d;

            {
                this.d = r1;
            }
        }.b());
        return arrayList == null ? new ArrayList(0) : arrayList;
    }
}
