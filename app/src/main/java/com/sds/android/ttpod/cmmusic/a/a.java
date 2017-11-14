package com.sds.android.ttpod.cmmusic.a;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.cmmusic.d.c;
import com.sds.android.ttpod.cmmusic.d.d;
import com.sds.android.ttpod.cmmusic.d.f;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/* HotSongAdapter */
public class a extends BaseAdapter implements OnClickListener {
    private static WeakReference<a> h;
    private static LayoutInflater i = null;
    private a a = null;
    private c b = null;
    private Context c;
    private Fragment d;
    private ArrayList<HashMap<String, String>> e;
    private HashMap<String, String> f = new HashMap();
    private com.sds.android.ttpod.cmmusic.d.a g = d.a();
    private int j = -1;
    private String k;
    private Dialog l;
    private String m;
    private int n;
    private Handler o = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            try {
                com.sds.android.ttpod.cmmusic.d.a aVar;
                switch (message.what) {
                    case 1:
                        this.a.g = (com.sds.android.ttpod.cmmusic.d.a) message.obj;
                        this.a.l = new Dialog(this.a.c, R.style.DialogStyle);
                        this.a.l.setContentView(this.a.c());
                        this.a.l.show();
                        return;
                    case 6:
                        HashMap hashMap = (HashMap) message.obj;
                        this.a.a((String) hashMap.get("cailing_id"), (String) hashMap.get("resource_name"), (String) hashMap.get("resource_songer"));
                        return;
                    case 7:
                        Toast.makeText(this.a.c, this.a.c.getString(R.string.notcmcccard), 0).show();
                        return;
                    case 8:
                        Toast.makeText(this.a.c, ((com.sds.android.ttpod.cmmusic.d.a) message.obj).i(), 0).show();
                        return;
                    case 9:
                        aVar = (com.sds.android.ttpod.cmmusic.d.a) message.obj;
                        this.a.l = new Dialog(this.a.c, R.style.DialogStyle);
                        this.a.l.setContentView(this.a.a(aVar));
                        this.a.l.show();
                        return;
                    case 12:
                        Toast.makeText(this.a.c, this.a.c.getString(R.string.networkinstable), 0).show();
                        return;
                    case 13:
                        aVar = (com.sds.android.ttpod.cmmusic.d.a) message.obj;
                        if (aVar.i() != null) {
                            Toast.makeText(this.a.c, aVar.i(), 0).show();
                            return;
                        } else {
                            Toast.makeText(this.a.c, this.a.c.getString(R.string.openfuctionfailure), 0).show();
                            return;
                        }
                    case 15:
                        aVar = (com.sds.android.ttpod.cmmusic.d.a) message.obj;
                        if (aVar.h() == null) {
                            return;
                        }
                        if (m.a(aVar.h(), "302011")) {
                            Toast.makeText(this.a.c, aVar.i(), 0).show();
                            return;
                        } else if (m.a(aVar.h(), "000000")) {
                            this.a.l = new Dialog(this.a.c, R.style.DialogStyle);
                            this.a.l.setContentView(this.a.a());
                            this.a.l.show();
                            return;
                        } else {
                            this.a.l = new Dialog(this.a.c, R.style.DialogStyle);
                            this.a.l.setContentView(this.a.b());
                            this.a.l.show();
                            return;
                        }
                    default:
                        return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    };

    /* HotSongAdapter */
    public class a extends AsyncTask<Integer, String, Integer> {
        final /* synthetic */ a a;

        public a(a aVar) {
            this.a = aVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Integer[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        protected void onCancelled() {
            this.a.f();
            super.onCancelled();
        }

        protected Integer a(Integer... numArr) {
            try {
                com.sds.android.ttpod.cmmusic.d.a b = com.sds.android.ttpod.cmmusic.c.c.b();
                if (b.h() == null) {
                    Message message = new Message();
                    message.what = 12;
                    this.a.o.sendMessage(message);
                } else {
                    Message message2 = new Message();
                    if (b.h().equals("100100") || b.h().equals("999011")) {
                        message2.obj = b;
                        message2.what = 1;
                    } else if (b.h().equals("000000")) {
                        message2.obj = this.a.f;
                        message2.what = 6;
                    } else if (b.h().equals("999011")) {
                        message2.what = 7;
                    } else if (b.h().equals("999003")) {
                        message2.what = 7;
                    } else {
                        message2.obj = b;
                        message2.what = 8;
                    }
                    this.a.o.sendMessage(message2);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.a.c, this.a.c.getResources().getString(R.string.networkinstable), 0).show();
            }
            return null;
        }

        protected void onPreExecute() {
            this.a.e();
        }

        protected void a(Integer num) {
            this.a.f();
        }
    }

    public a(Context context, Fragment fragment, ArrayList<HashMap<String, String>> arrayList, String str, int i) {
        this.c = context;
        this.d = fragment;
        this.e = arrayList;
        this.k = str;
        this.n = i;
        i = (LayoutInflater) this.c.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.e == null ? 0 : this.e.size();
    }

    public Object getItem(int i) {
        return this.e == null ? null : (HashMap) this.e.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = i.inflate(R.layout.cmmusic_list_row, null);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.list_item_play);
        TextView textView = (TextView) view.findViewById(R.id.text_resource_name);
        TextView textView2 = (TextView) view.findViewById(R.id.text_resource_songer);
        Button button = (Button) view.findViewById(R.id.btn_soundplay);
        Button button2 = (Button) view.findViewById(R.id.btn_soundbuy);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_paying);
        HashMap hashMap = (HashMap) this.e.get(i);
        textView.setText((CharSequence) hashMap.get("resource_name"));
        textView2.setText((CharSequence) hashMap.get("resource_songer"));
        linearLayout.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                a.m(this.b);
                this.b.j = i;
                this.b.a((HashMap) this.b.e.get(i), i);
            }
        });
        button2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a b;

            public void onClick(View view) {
                this.b.a(i);
            }
        });
        if (this.j == i) {
            view.setBackgroundColor(Color.parseColor("#EAF4F6"));
            textView.setSelected(true);
            imageView.setVisibility(0);
            button.setBackgroundResource(R.drawable.cmmusic_shiting_ing);
            linearLayout.setTag(this.k);
        } else {
            textView.setSelected(false);
            view.setBackgroundColor(-1);
            imageView.setVisibility(4);
            button.setBackgroundResource(R.drawable.cmmusic_shiting);
        }
        return view;
    }

    public void a(boolean z) {
        if (!z) {
            this.j = -1;
        }
        notifyDataSetChanged();
    }

    private void a(final HashMap<String, String> hashMap, int i) {
        new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_LISTEN_PLAYING.getValue(), this.n, s.PAGE_NONE.getValue()).append("position", Integer.valueOf(i + 1)).append("module_name", this.k).append("song_name", hashMap.get("resource_name")).post();
        com.sds.android.sdk.lib.e.a.a(this.d, new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                com.sds.android.ttpod.cmmusic.d.a a = com.sds.android.ttpod.cmmusic.c.d.a((String) hashMap.get("cailing_id"));
                if (a.k() != null) {
                    f.a(a.k(), (String) hashMap.get("resource_name"), (String) hashMap.get("resource_songer"));
                } else if (m.a(a.h(), "999011")) {
                    Message message = new Message();
                    message.what = 7;
                    this.b.o.sendMessage(message);
                }
            }
        });
    }

    private void a(int i) {
        this.f = (HashMap) this.e.get(i);
        new SUserEvent("PAGE_CLICK", r.ACTION_CMMUSIC_CLICK_LISTEN_BUY.getValue(), this.n, s.PAGE_NONE.getValue()).append("module_name", this.k).append("song_name", this.f.get("resource_name")).post();
        try {
            this.a = new a(this);
            this.a.execute(new Integer[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View a() {
        View inflate = View.inflate(this.c, R.layout.cmmusic_listen_order_check_success_activity, null);
        inflate.findViewById(R.id.btn_iknow_orderseccess).setOnClickListener(this);
        inflate.findViewById(R.id.btn_setdefault_orderseccess).setOnClickListener(this);
        return inflate;
    }

    private View b() {
        View inflate = View.inflate(this.c, R.layout.cmmusic_listen_tryagain_by_activity, null);
        inflate.findViewById(R.id.btn_iknow_tryagainpage).setOnClickListener(this);
        inflate.findViewById(R.id.btn_trybuy_tryagainpage).setOnClickListener(this);
        return inflate;
    }

    private View c() {
        View inflate = View.inflate(this.c, R.layout.cmmusic_open_cailing_fuction_activity, null);
        inflate.findViewById(R.id.btn_cancel_opencailing).setOnClickListener(this);
        inflate.findViewById(R.id.btn_confirm_opencailing).setOnClickListener(this);
        ((TextView) inflate.findViewById(R.id.text_coloropenprice)).setText(this.c.getString(R.string.cailingopenorder) + "5" + this.c.getString(R.string.opencailingprice));
        return inflate;
    }

    private View a(com.sds.android.ttpod.cmmusic.d.a aVar) {
        View inflate = View.inflate(this.c, R.layout.cmmusic_listen_order_check_activity, null);
        TextView textView = (TextView) inflate.findViewById(R.id.text_songname_orderpage);
        TextView textView2 = (TextView) inflate.findViewById(R.id.text_singername_orderpage);
        TextView textView3 = (TextView) inflate.findViewById(R.id.text_price_orderpage);
        TextView textView4 = (TextView) inflate.findViewById(R.id.text_invaliddate_orderpage);
        textView.setText(textView.getText() + aVar.n());
        textView2.setText(textView2.getText() + aVar.o());
        textView3.setText(textView3.getText() + String.valueOf(Integer.valueOf(aVar.m()).intValue() / 100) + this.c.getString(R.string.price));
        textView4.setText(textView4.getText() + aVar.l());
        inflate.findViewById(R.id.btn_cancel_orderpage).setOnClickListener(this);
        inflate.findViewById(R.id.btn_submit_orderpage).setOnClickListener(this);
        this.m = aVar.j();
        return inflate;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.btn_cancel_opencailing == id) {
            this.l.dismiss();
        } else if (R.id.btn_confirm_opencailing == id) {
            com.sds.android.sdk.lib.e.a.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    com.sds.android.ttpod.cmmusic.d.a a = com.sds.android.ttpod.cmmusic.c.c.a();
                    Message message = new Message();
                    message.obj = a;
                    message.what = 13;
                    this.a.o.sendMessage(message);
                }
            });
            this.l.dismiss();
        } else if (R.id.btn_cancel_orderpage == id) {
            this.l.dismiss();
        } else if (R.id.btn_submit_orderpage == id || R.id.btn_trybuy_tryagainpage == id) {
            d();
            this.l.dismiss();
        } else if (R.id.btn_iknow_tryagainpage == id || R.id.btn_iknow_orderseccess == id) {
            this.l.dismiss();
        } else if (R.id.btn_setdefault_orderseccess == id) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("pagename", "PersionalListenControl");
            intent.putExtras(bundle);
            intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
            this.l.dismiss();
            this.c.startActivity(intent);
        }
    }

    private void d() {
        com.sds.android.sdk.lib.e.a.a(this.c, new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                com.sds.android.ttpod.cmmusic.d.a a = d.a();
                a.c(this.a.m);
                a = com.sds.android.ttpod.cmmusic.c.f.a(a);
                Message message = new Message();
                message.what = 15;
                message.obj = a;
                this.a.o.sendMessage(message);
            }
        });
    }

    private void e() {
        if (this.b == null) {
            this.b = c.a(this.c);
            this.b.a(this.c.getResources().getString(R.string.loding));
        }
        this.b.show();
    }

    private void f() {
        if (this.b != null) {
            this.b.dismiss();
            this.b = null;
        }
    }

    private void a(final String str, final String str2, final String str3) {
        com.sds.android.sdk.lib.e.a.a(this.d, new Runnable(this) {
            final /* synthetic */ a d;

            public void run() {
                com.sds.android.ttpod.cmmusic.d.a a = com.sds.android.ttpod.cmmusic.c.f.a(str, str2, str3);
                Message message = new Message();
                message.obj = a;
                message.what = 9;
                this.d.o.sendMessage(message);
            }
        });
    }

    private static void m(a aVar) {
        if (!(h == null || h.get() == null)) {
            ((a) h.get()).a(false);
        }
        aVar.a(true);
        h = new WeakReference(aVar);
    }
}
