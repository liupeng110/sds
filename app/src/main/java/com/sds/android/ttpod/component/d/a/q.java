package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a;

/* SeekBarDialog */
public class q extends a {
    private TextView a;
    private SeekBar b;
    private String c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h = 1;
    private int i = 1;

    protected /* synthetic */ Object a() {
        return c();
    }

    public q(Context context, int i, int i2, int i3, int i4, String str, a.a<q> aVar) {
        int i5 = 1000;
        super(context);
        if (i3 <= i) {
            throw new IllegalArgumentException("maxProgress must be > minProgress");
        } else if (i2 < i) {
            throw new IllegalArgumentException("curProgress must be >= minProgress");
        } else if (i2 > i3) {
            throw new IllegalArgumentException("curProgress must be <= maxProgress");
        } else if (i4 <= 0) {
            throw new IllegalArgumentException("step must be > 0");
        } else {
            int i6;
            a((int) R.string.ok, (a.a) aVar, (int) R.string.cancel, null);
            this.c = str;
            this.d = i2;
            this.e = i3 - i;
            if (this.e <= 1000) {
                this.g = 1000 / this.e;
                this.f = this.g * i;
                i6 = this.g * this.d;
            } else {
                this.g = i4;
                this.f = i;
                i5 = i3 - i;
                i6 = this.d - i;
            }
            this.b.setMax(i5);
            this.b.setProgress(i6);
            d();
            this.b.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ q a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    int b;
                    if (this.a.e <= 1000) {
                        b = (this.a.f + i) / this.a.g;
                        if (this.a.d != b) {
                            this.a.d = b;
                            this.a.d();
                            return;
                        }
                        return;
                    }
                    b = ((i / this.a.g) * this.a.g) + this.a.f;
                    if (this.a.d != b) {
                        this.a.d = b;
                        this.a.d();
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
        }
    }

    public void a(int i, int i2) {
        this.h = i;
        this.i = i2;
        d();
    }

    private void d() {
        CharSequence charSequence;
        if (this.h == 1) {
            charSequence = this.d + this.c;
        } else {
            charSequence = ((1.0f * ((float) this.d)) / ((float) this.h)) + this.c;
        }
        this.a.setText(charSequence);
    }

    public int b() {
        return this.d;
    }

    protected View a(Context context, ViewGroup viewGroup) {
        View inflate = View.inflate(context, R.layout.dialog_body_seekbar, null);
        this.a = (TextView) inflate.findViewById(R.id.dialog_display);
        this.b = (SeekBar) inflate.findViewById(R.id.dialog_seekbar);
        return inflate;
    }

    protected q c() {
        return this;
    }
}
