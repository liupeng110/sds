package com.sds.android.cloudapi.ttpod.b;

import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.b.b;
import com.sds.android.sdk.lib.b.c;
import com.sds.android.sdk.lib.util.f;
import java.util.ArrayList;

/* GetFeedbackMessageResult */
public class a extends c<FeedbackMessage> {
    public a(b bVar) {
        setCode(bVar.getCode());
        setContent(bVar.getContent());
        setLocation(bVar.getLocation());
    }

    public ArrayList<FeedbackMessage> a() {
        ArrayList<FeedbackMessage> arrayList = (ArrayList) f.a(getContent(), new com.b.a.c.a<ArrayList<FeedbackMessage>>(this) {
            final /* synthetic */ a d;

            {
                this.d = r1;
            }
        }.b());
        return arrayList == null ? new ArrayList(0) : arrayList;
    }
}
