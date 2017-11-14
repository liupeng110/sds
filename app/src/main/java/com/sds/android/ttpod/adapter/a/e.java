package com.sds.android.ttpod.adapter.a;

import android.os.SystemClock;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.CheckImageView;

/* ShareItemViewHolder */
public final class e {
    private final ImageView a;
    private final CheckImageView b;
    private final TextView c;
    private final TextView d;
    private final TextView e;
    private final TextView f;
    private final TextView g;
    private final RelativeLayout h;
    private final ProgressBar i;
    private com.sds.android.ttpod.fragment.apshare.a j;
    private a k;

    /* ShareItemViewHolder */
    public interface a {
        void a(com.sds.android.ttpod.fragment.apshare.a aVar);

        void a(boolean z, com.sds.android.ttpod.fragment.apshare.a aVar);
    }

    public e(View view, int i, a aVar) {
        this.k = aVar;
        this.h = (RelativeLayout) view.findViewById(R.id.media_list_item_layout);
        this.a = (ImageView) view.findViewById(R.id.iv_icon);
        this.b = (CheckImageView) view.findViewById(R.id.ck_choose);
        this.c = (TextView) view.findViewById(R.id.tv_title);
        this.d = (TextView) view.findViewById(R.id.tv_size);
        this.e = (TextView) view.findViewById(R.id.tv_speed);
        this.f = (TextView) view.findViewById(R.id.tv_receiver);
        this.g = (TextView) view.findViewById(R.id.tv_action);
        this.i = (ProgressBar) view.findViewById(R.id.progress_bar);
        this.i.setMax(100);
        if (i == 1) {
            this.i.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            this.b.setOnCheckedChangeListener(new com.sds.android.ttpod.widget.CheckImageView.a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void a(CheckImageView checkImageView, boolean z, boolean z2) {
                    if (this.a.k != null) {
                        this.a.k.a(z, this.a.j);
                    }
                }
            });
        } else if (i == 2) {
            this.b.setVisibility(8);
            this.g.setVisibility(8);
        } else if (i == 3) {
            this.b.setVisibility(8);
            this.f.setVisibility(8);
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.k != null) {
                        this.a.k.a(this.a.j);
                        this.a.e(this.a.j);
                    }
                }
            });
        }
        this.b.a((int) R.drawable.img_checkbox_multiselect_unchecked, (int) R.drawable.img_checkbox_multiselect_checked);
    }

    public void a() {
        this.b.setEnabled(false);
    }

    private void c(com.sds.android.ttpod.fragment.apshare.a aVar) {
        this.j = aVar;
        MediaItem a = aVar.a();
        String localDataSource = a.getLocalDataSource();
        this.c.setText(com.sds.android.sdk.lib.util.e.j(localDataSource));
        long size = a.getSize();
        if (size <= 0) {
            size = com.sds.android.sdk.lib.util.e.g(localDataSource);
            a.setSize(size);
        }
        this.d.setText(Formatter.formatFileSize(this.h.getContext(), size));
    }

    public void a(com.sds.android.ttpod.fragment.apshare.a aVar, boolean z) {
        c(aVar);
        this.b.setChecked(z);
    }

    public void a(com.sds.android.ttpod.fragment.apshare.a aVar) {
        c(aVar);
        if (aVar.f() == null) {
            this.e.setText("");
        } else {
            f f = aVar.f();
            aVar.a(SystemClock.currentThreadTimeMillis());
            this.e.setText(f.a());
        }
        this.i.setProgress(aVar.g());
        if (aVar.g() == 100) {
            this.e.setText(R.string.share_send_complete);
        }
        if (aVar.h() == com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_CANCELLED) {
            this.e.setText(R.string.share_receive_cancelled);
        }
        if (aVar.h() == com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_FAILED) {
            this.e.setText(R.string.share_send_failed);
        }
        this.f.setText("接收者：" + aVar.d());
    }

    public void b(com.sds.android.ttpod.fragment.apshare.a aVar) {
        c(aVar);
        e(aVar);
        this.i.setProgress(aVar.g());
    }

    private void d(com.sds.android.ttpod.fragment.apshare.a aVar) {
        f f = aVar.f();
        if (f != null) {
            aVar.a(SystemClock.currentThreadTimeMillis());
            this.e.setText(f.a());
        }
    }

    private void e(com.sds.android.ttpod.fragment.apshare.a aVar) {
        switch (aVar.h()) {
            case TRANSMIT_IDLE:
                this.e.setText("");
                this.g.setText(R.string.share_receive);
                this.g.setBackgroundResource(R.drawable.xml_background_apshare_receive);
                return;
            case WAITING:
                this.e.setText("");
                this.g.setText(R.string.share_cancel);
                this.g.setBackgroundResource(R.drawable.xml_background_apshare_cancel);
                return;
            case TRANSMITTING:
                d(aVar);
                this.g.setText(R.string.share_cancel);
                this.g.setBackgroundResource(R.drawable.xml_background_apshare_cancel);
                return;
            case TRANSMIT_FINISHED:
                this.e.setText(R.string.share_receive_complete);
                this.i.setProgress(100);
                this.g.setText(R.string.delete);
                this.g.setBackgroundResource(R.drawable.xml_background_apshare_delete);
                return;
            case TRANSMIT_FAILED:
                this.e.setText(R.string.share_receive_failed);
                this.g.setText(R.string.share_failed);
                this.g.setBackgroundResource(R.drawable.xml_background_apshare_cancel);
                return;
            case TRANSMIT_CANCELLED:
                this.e.setText(R.string.share_receive_cancelled);
                this.g.setText(R.string.share_receive);
                this.g.setBackgroundResource(R.drawable.xml_background_apshare_receive);
                aVar.a(com.sds.android.ttpod.fragment.apshare.a.a.TRANSMIT_IDLE);
                return;
            default:
                return;
        }
    }
}
