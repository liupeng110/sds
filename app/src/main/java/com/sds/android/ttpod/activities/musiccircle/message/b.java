package com.sds.android.ttpod.activities.musiccircle.message;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a;

/* MessageClickableSpan */
public class b extends ClickableSpan {
    private static int f = -11365447;
    private a a;
    private Post b;
    private Notice c;
    private a d;
    private NewUser e;

    public static void a(int i) {
        f = i;
    }

    public b(Notice notice, Post post, a aVar) {
        this.c = notice;
        this.b = post;
        this.d = aVar;
    }

    public b(NewUser newUser, a aVar) {
        this.e = newUser;
        this.a = aVar;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(f);
        textPaint.setUnderlineText(false);
    }

    public void onClick(View view) {
        if (this.b != null) {
            this.d.a(this.c, this.b);
        }
        if (this.e != null && this.a != null) {
            this.a.a(this.e);
        }
    }
}
