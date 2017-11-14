package com.sds.android.ttpod.fragment.apshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.apshare.ApShareChooseActivity;
import com.sds.android.ttpod.activities.apshare.ApShareReceiveActivity;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.base.BaseFragment;

public class ApShareEntryFragment extends BaseFragment {
    private OnClickListener mOnClickListener = new OnClickListener(this) {
        final /* synthetic */ ApShareEntryFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_share:
                    this.a.startActivity(new Intent(this.a.getActivity(), ApShareChooseActivity.class));
                    l.d();
                    t.b("PAGE_CLICK", r.ACTION_UPLOAD_SONG_SHARE, s.PAGE_DELIVER_SONG, s.PAGE_UPLOAD_SONG_SHARE);
                    new b().b("send_song").a();
                    return;
                case R.id.tv_receive:
                    this.a.startActivity(new Intent(this.a.getActivity(), ApShareReceiveActivity.class));
                    l.e();
                    t.b("PAGE_CLICK", r.ACTION_UPLOAD_SONG_RECEIVE, s.PAGE_DELIVER_SONG, s.PAGE_UPLOAD_SONG_RECEIVE);
                    new b().b("receive_song").a();
                    return;
                case R.id.tv_pc_send:
                    f.a(this.a.getActivity(), 2);
                    l.f();
                    t.b("PAGE_CLICK", r.ACTION_UPLOAD_SONG_COMPUTE, s.PAGE_DELIVER_SONG, s.PAGE_UPLOAD_SONG_PC);
                    new b().b("connect_pc").a();
                    return;
                default:
                    return;
            }
        }
    };
    private TextView mReceive;
    private TextView mSend;
    private TextView mShare;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(R.layout.activity_apshare_entry, null);
        this.mShare = (TextView) inflate.findViewById(R.id.tv_share);
        this.mShare.setOnClickListener(this.mOnClickListener);
        this.mReceive = (TextView) inflate.findViewById(R.id.tv_receive);
        this.mReceive.setOnClickListener(this.mOnClickListener);
        this.mSend = (TextView) inflate.findViewById(R.id.tv_pc_send);
        this.mSend.setOnClickListener(this.mOnClickListener);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageview_header);
        try {
            imageView.setImageResource(R.drawable.img_apshare_entry_top);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            imageView.setBackgroundColor(-1);
        }
        return inflate;
    }

    public void onThemeLoaded() {
    }
}
