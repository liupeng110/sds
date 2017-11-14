package com.sds.android.ttpod.cmmusic.a;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.e.a;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.cmmusic.c.i;
import com.sds.android.ttpod.cmmusic.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import java.util.ArrayList;
import java.util.HashMap;

/* PersonalListenControlAdapter */
public class c extends BaseAdapter implements OnClickListener {
    private ArrayList<HashMap<String, String>> a;
    private Context b;
    private LayoutInflater c = null;
    private int d = -1;
    private String e;
    private Dialog f;
    private String g;
    private Handler h = new Handler(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    String str = (String) message.obj;
                    if (str != null) {
                        Toast.makeText(this.a.b, str, 0).show();
                        return;
                    } else {
                        Toast.makeText(this.a.b, this.a.b.getString(R.string.networkinstable), 0).show();
                        return;
                    }
                default:
                    return;
            }
        }
    };

    public c(Context context, ArrayList<HashMap<String, String>> arrayList) {
        this.b = context;
        this.a = arrayList;
        this.c = (LayoutInflater) this.b.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public Object getItem(int i) {
        return this.a == null ? null : (HashMap) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.c.inflate(R.layout.cmmusic_personal_list_row, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.text_persionlistsonger);
        TextView textView2 = (TextView) view.findViewById(R.id.text_timeout);
        Button button = (Button) view.findViewById(R.id.btn_set_default);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_persionlistdefault);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_persionpage);
        final HashMap hashMap = (HashMap) this.a.get(i);
        ((TextView) view.findViewById(R.id.text_persionlistsongname)).setText((CharSequence) hashMap.get("resource_name"));
        textView.setText((CharSequence) hashMap.get("resource_songer"));
        String str = (String) hashMap.get("time_out");
        textView2.setText(this.b.getString(R.string.timeout) + str.subSequence(0, 10));
        linearLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                if (hashMap.get("resource_path") != null) {
                    f.a((String) hashMap.get("resource_path"), (String) hashMap.get("resource_name"), (String) hashMap.get("resource_songer"));
                }
            }
        });
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                this.b.f = new Dialog(this.b.b, R.style.DialogStyle);
                this.b.f.setContentView(this.b.a(hashMap));
                this.b.f.show();
            }
        });
        if (this.d == i) {
            view.setBackgroundColor(this.b.getResources().getColor(R.color.listitem_touch_down));
            imageView.setImageResource(R.drawable.cmmusic_default_true);
        } else {
            view.setBackgroundColor(0);
            imageView.setImageResource(R.drawable.cmmusic_default_false);
        }
        return view;
    }

    private View a(HashMap<String, String> hashMap) {
        View inflate = View.inflate(this.b, R.layout.cmmusic_set_default_or_delete_listen_activity, null);
        inflate.findViewById(R.id.btn_setdefault_cancel).setOnClickListener(this);
        inflate.findViewById(R.id.btn_setdefault_submit).setOnClickListener(this);
        this.g = (String) hashMap.get("resource_name");
        this.e = (String) hashMap.get("cailing_id");
        TextView textView = (TextView) inflate.findViewById(R.id.text_prompt_setdefaultpage);
        String string = this.b.getResources().getString(R.string.setdefaultconfirm);
        textView.setText(string + this.g + this.b.getResources().getString(R.string.setdefaultorder));
        return inflate;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btn_setdefault_cancel == id) {
            this.f.dismiss();
        } else if (R.id.btn_setdefault_submit == id) {
            new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_SET_DEFAULT_LISTEN.getValue(), s.PAGE_CMMUSIC_PERSONAL_CODE.getValue(), s.PAGE_NONE.getValue()).append("song_name", this.g).post();
            a.a(this.b, new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    String a = i.a(this.a.e);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = a;
                    this.a.h.sendMessage(message);
                }
            });
            this.f.dismiss();
        }
    }
}
