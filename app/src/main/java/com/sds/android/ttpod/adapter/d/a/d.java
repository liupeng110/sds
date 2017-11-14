package com.sds.android.ttpod.adapter.d.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.a.v;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.data.PrivateMessageContent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.w;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.widget.UserAvatarView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* PrivateMessageAdapter */
public class d extends BaseAdapter {
    private Context a;
    private NewUser b;
    private NewUser c;
    private int d;
    private String e;
    private List<PrivateMessageContent> f = new ArrayList();
    private List<e> g = new ArrayList();
    private com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a h;

    /* PrivateMessageAdapter */
    class a {
        final /* synthetic */ d a;
        private View b;
        private UserAvatarView c;
        private TextView d;
        private TextView e;

        public a(d dVar, View view) {
            this.a = dVar;
            this.b = view;
            this.c = (UserAvatarView) view.findViewById(R.id.image_userhead);
            this.d = (TextView) view.findViewById(R.id.text_content);
            this.e = (TextView) view.findViewById(R.id.text_time);
        }
    }

    /* PrivateMessageAdapter */
    class b {
        final /* synthetic */ d a;
        private View b;
        private TextView c;

        public b(d dVar, View view) {
            this.a = dVar;
            this.b = view;
            this.c = (TextView) view.findViewById(R.id.text_date);
        }
    }

    /* PrivateMessageAdapter */
    class c {
        final /* synthetic */ d a;
        private a b;
        private a c;
        private b d;

        public c(d dVar, View view) {
            this.a = dVar;
            this.b = new a(dVar, view.findViewById(R.id.chatting_left));
            this.c = new a(dVar, view.findViewById(R.id.chatting_right));
            this.d = new b(dVar, view.findViewById(R.id.chatting_middle));
        }
    }

    public void a(com.sds.android.ttpod.fragment.musiccircle.WrapUserPostListFragment.a aVar) {
        this.h = aVar;
    }

    public d(Context context, List<PrivateMessageContent> list, NewUser newUser, NewUser newUser2) {
        if (context == null || list == null || newUser == null || newUser2 == null) {
            throw new IllegalArgumentException("context,privateMessageContents,userCurrent,userReplyTo should not be null");
        }
        this.a = context;
        this.b = newUser2;
        this.c = newUser;
        b((List) list);
    }

    public List<e> a(List<PrivateMessageContent> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        List<e> arrayList = new ArrayList();
        this.d = -1;
        long j = -1;
        for (PrivateMessageContent privateMessageContent : list) {
            long a = a(privateMessageContent.getTimestamp());
            if (a != j) {
                arrayList.add(new e(null, null, privateMessageContent.getTimestamp(), null, 0));
            } else {
                a = j;
            }
            if (privateMessageContent.getSenderId() == this.c.getUserId()) {
                arrayList.add(new e(this.c, privateMessageContent.getPmId(), privateMessageContent.getTimestamp(), privateMessageContent.getMessage(), 2));
            } else if (privateMessageContent.getSenderId() == this.b.getUserId()) {
                arrayList.add(new e(this.b, privateMessageContent.getPmId(), privateMessageContent.getTimestamp(), privateMessageContent.getMessage(), 1));
            }
            if (this.d < 0 && !m.a(privateMessageContent.getPmId()) && privateMessageContent.getPmId().equals(this.e)) {
                this.d = arrayList.size();
            }
            j = a;
        }
        if (this.d < 0) {
            this.d = arrayList.size();
        }
        return arrayList;
    }

    public void b(List<PrivateMessageContent> list) {
        if (!list.isEmpty()) {
            this.f.clear();
            this.f.addAll(list);
            Collections.reverse(this.f);
            this.e = ((PrivateMessageContent) this.f.get(this.f.size() - 1)).getPmId();
            this.g.clear();
            this.g = a(this.f);
            notifyDataSetChanged();
        }
    }

    private static long a(long j) {
        return (long) Math.ceil(((double) j) / 86400.0d);
    }

    private void a(c cVar, boolean z, boolean z2, boolean z3) {
        int i;
        int i2 = 0;
        View a = cVar.b.b;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        a.setVisibility(i);
        a = cVar.c.b;
        if (z2) {
            i = 0;
        } else {
            i = 8;
        }
        a.setVisibility(i);
        View a2 = cVar.d.b;
        if (!z3) {
            i2 = 8;
        }
        a2.setVisibility(i2);
    }

    private void a(TextView textView, long j) {
        textView.setText(w.a(j));
    }

    private void b(TextView textView, long j) {
        textView.setText(w.b(j));
    }

    private void a(TextView textView, final e eVar) {
        CharSequence a = com.sds.android.ttpod.component.emoticons.b.b().a(this.a, eVar.a());
        if (a == null) {
            a = "";
        }
        textView.setText(a);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d b;

            public void onClick(View view) {
                f.a(this.b.a, new com.sds.android.ttpod.component.b.a[]{new com.sds.android.ttpod.component.b.a(1, 0, (CharSequence) "删除")}, "私信", new com.sds.android.ttpod.component.b.a.b(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                        if (aVar.g() == 1) {
                            final o a = v.a(com.sds.android.ttpod.framework.storage.environment.b.aw().getToken(), eVar.d());
                            a.a(new p<BaseResult>(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public void onRequestSuccess(BaseResult baseResult) {
                                    for (int size = this.b.a.b.g.size() - 1; size >= 0; size--) {
                                        if (((e) this.b.a.b.g.get(size)).d().equals(eVar.d())) {
                                            this.b.a.b.g.remove(size);
                                            break;
                                        }
                                    }
                                    this.b.a.b.notifyDataSetChanged();
                                }

                                public void onRequestFailure(BaseResult baseResult) {
                                    f.a("删除失败");
                                    g.g(a.b());
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void a(UserAvatarView userAvatarView, final NewUser newUser) {
        userAvatarView.setVFlagVisible(newUser.getPriv() == 2);
        com.sds.android.ttpod.framework.a.g.a((ImageView) userAvatarView, newUser.getAvatarUrl(), userAvatarView.getWidth(), userAvatarView.getHeight(), (int) R.drawable.img_avatar_default);
        userAvatarView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d b;

            public void onClick(View view) {
                this.b.h.a(newUser);
            }
        });
    }

    private void a(View view, e eVar, int i) {
        c cVar = (c) view.getTag();
        if (eVar.c() == 0) {
            a(cVar, false, false, true);
            a(cVar.d.c, eVar.b());
        } else if (1 == eVar.c()) {
            a(cVar, true, false, false);
            b(cVar.b.e, eVar.b());
            a(cVar.b.d, eVar);
            a(cVar.b.c, eVar.e());
        } else if (2 == eVar.c()) {
            a(cVar, false, true, false);
            b(cVar.c.e, eVar.b());
            a(cVar.c.d, eVar);
            a(cVar.c.c, eVar.e());
        }
    }

    public int getCount() {
        return this.g.size();
    }

    public Object getItem(int i) {
        return this.g.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.a, R.layout.musiccircle_chatting_item, null);
            view.setTag(new c(this, view));
        }
        a(view, (e) this.g.get(i), i);
        return view;
    }
}
