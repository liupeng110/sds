package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ScrollView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a;

/* MoreOptionalDialog */
public abstract class i extends a {
    private ScrollView a;
    private CheckedTextView b;
    private CheckedTextView c;
    private CheckedTextView d;
    private CheckedTextView e;
    private OnClickListener f;

    protected abstract View a(Context context);

    protected /* synthetic */ Object a() {
        return h();
    }

    public i(Context context, int i, a.a<i> aVar, int i2, a.a<i> aVar2) {
        super(context);
        a(i, (a.a) aVar, i2, (a.a) aVar2);
        this.f = new OnClickListener(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                ((CheckedTextView) view).toggle();
                boolean isChecked = ((CheckedTextView) view).isChecked();
                if (this.a.b == view && isChecked) {
                    this.a.d.setChecked(true);
                    this.a.e.setChecked(true);
                }
            }
        };
        this.b.setOnClickListener(this.f);
        this.c.setOnClickListener(this.f);
        this.d.setOnClickListener(this.f);
        this.e.setOnClickListener(this.f);
        this.d.setChecked(true);
        this.e.setChecked(true);
    }

    public i(Context context, a.a<i> aVar, a.a<i> aVar2) {
        this(context, R.string.ok, aVar, R.string.cancel, aVar2);
    }

    public boolean b() {
        return this.b.isChecked();
    }

    protected final View a(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.dialog_body_more_option, null);
        this.b = (CheckedTextView) inflate.findViewById(R.id.option_original_file);
        this.c = (CheckedTextView) inflate.findViewById(R.id.option_artist_pic);
        this.d = (CheckedTextView) inflate.findViewById(R.id.option_audio_profile);
        this.e = (CheckedTextView) inflate.findViewById(R.id.option_lyric);
        this.a = (ScrollView) inflate.findViewById(R.id.scroll_content);
        View a = a(context);
        if (a != null) {
            this.a.addView(a);
        }
        return inflate;
    }

    public boolean c() {
        return this.c.isChecked();
    }

    public boolean d() {
        return this.d.isChecked();
    }

    public boolean g() {
        return this.e.isChecked();
    }

    protected i h() {
        return this;
    }
}
