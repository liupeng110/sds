package com.sds.android.ttpod.adapter;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.p;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.d.h;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.framework.support.search.task.ResultData.Item;
import com.sds.android.ttpod.framework.support.search.task.c;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.tencent.open.SocialConstants;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PictureManagerAdapter extends BaseAdapter {
    private final Animation a;
    private Activity b;
    private ArrayList<PictureDataItem> c;
    private String d;
    private MediaItem e;
    private boolean f;
    private NetworkLoadView g;
    private boolean h;
    private OnClickListener i = new OnClickListener(this) {
        final /* synthetic */ PictureManagerAdapter a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            boolean z = true;
            this.a.h = true;
            final PictureDataItem pictureDataItem = (PictureDataItem) view.getTag();
            final a aVar = (a) view.getTag(R.id.view_tag_view_holder);
            g.a("PictureManagerAdapter", "onClick item=%s", pictureDataItem.d());
            if (pictureDataItem.g) {
                if (e.h(pictureDataItem.d())) {
                    z = false;
                }
                pictureDataItem.g = z;
                aVar.a(pictureDataItem);
                String str = com.sds.android.ttpod.framework.a.t() + File.separator + this.a.d + File.separator;
                ArrayList b = c.b(str);
                if (b == null) {
                    b = new ArrayList();
                }
                b.add(Integer.valueOf(pictureDataItem.a()));
                c.a(str, b);
            } else if (!pictureDataItem.f) {
                p.a(this.a.b, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ AnonymousClass3 c;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -1) {
                            this.c.a(pictureDataItem, aVar);
                        }
                    }
                });
            }
        }

        private void a(PictureDataItem pictureDataItem, a aVar) {
            pictureDataItem.f = true;
            aVar.a(pictureDataItem);
            com.sds.android.sdk.lib.e.a.a(this.a.b, new com.sds.android.sdk.lib.e.a.a<PictureDataItem, PictureDataItem>(this, pictureDataItem) {
                final /* synthetic */ AnonymousClass3 a;

                protected /* synthetic */ void onCancelled(Object obj) {
                    c((PictureDataItem) obj);
                }

                protected /* synthetic */ Object onDoInBackground(Object obj) {
                    return a((PictureDataItem) obj);
                }

                protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                    b((PictureDataItem) obj);
                }

                protected PictureDataItem a(PictureDataItem pictureDataItem) {
                    InputStream inputStream = null;
                    com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a(pictureDataItem.c(), null, null);
                    if (a != null) {
                        inputStream = a.e();
                    }
                    if (inputStream != null) {
                        String str = pictureDataItem.d() + ".tmp";
                        if (e.a(inputStream, str)) {
                            e.c(str, pictureDataItem.d());
                        } else {
                            e.h(str);
                        }
                    }
                    return pictureDataItem;
                }

                protected void b(PictureDataItem pictureDataItem) {
                    pictureDataItem.f = false;
                    boolean b = e.b(pictureDataItem.d());
                    pictureDataItem.g = b;
                    h.a(false, "manual", b, b ? 0 : -1, this.a.a.e.getSongID().longValue(), pictureDataItem.a());
                    this.a.a.a("download", this.a.a.d, b ? 1 : 2, (long) pictureDataItem.a());
                    this.a.a.notifyDataSetChanged();
                }

                protected void c(PictureDataItem pictureDataItem) {
                    pictureDataItem.f = false;
                    this.a.a.notifyDataSetChanged();
                }
            });
        }
    };

    public static class PictureDataItem extends Item {
        private String e;
        private boolean f;
        private boolean g;

        PictureDataItem(Item item) {
            super(item.b(), item.c(), item.d(), item.a());
            if (this.b != null) {
                int lastIndexOf = this.b.lastIndexOf(47);
                if (lastIndexOf > 0) {
                    this.e = this.b.substring(0, lastIndexOf) + "/144/192" + this.b.substring(lastIndexOf);
                }
            }
            this.g = e.b(item.d());
        }
    }

    public class a {
        final /* synthetic */ PictureManagerAdapter a;
        private View b;
        private a c = new a(this.b.findViewById(R.id.layout_left));
        private a d = new a(this.b.findViewById(R.id.layout_center));
        private a e = new a(this.b.findViewById(R.id.layout_right));

        private final class a {
            final /* synthetic */ a a;
            private View b;
            private ImageView c;
            private ImageView d;

            private a(a aVar, View view) {
                this.a = aVar;
                this.b = view;
                this.c = (ImageView) this.b.findViewById(R.id.imageview);
                this.d = (ImageView) this.b.findViewById(R.id.imageview_bottom);
                this.d.setOnClickListener(aVar.a.i);
                this.d.setTag(R.id.view_tag_view_holder, this);
            }

            protected void a(PictureDataItem pictureDataItem) {
                this.b.setVisibility(pictureDataItem != null ? 0 : 4);
                if (pictureDataItem != null) {
                    try {
                        this.d.setTag(pictureDataItem);
                        com.sds.android.ttpod.framework.a.g.a(this.c, pictureDataItem.e, this.c.getWidth(), this.c.getHeight(), (int) R.drawable.picture_manager_default);
                        if (pictureDataItem.g) {
                            this.d.clearAnimation();
                            this.d.setImageResource(R.drawable.xml_artist_pic_delete);
                            return;
                        } else if (pictureDataItem.f) {
                            this.d.startAnimation(this.a.a.a);
                            this.d.setImageResource(R.drawable.img_button_artist_pic_downing_normal);
                            return;
                        } else {
                            this.d.clearAnimation();
                            this.d.setImageResource(R.drawable.xml_artist_pic_down);
                            return;
                        }
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        return;
                    }
                }
                this.c.setImageDrawable(null);
            }
        }

        protected a(PictureManagerAdapter pictureManagerAdapter, View view) {
            this.a = pictureManagerAdapter;
            this.b = view;
        }

        protected void a(PictureDataItem pictureDataItem, PictureDataItem pictureDataItem2, PictureDataItem pictureDataItem3) {
            this.c.a(pictureDataItem);
            this.d.a(pictureDataItem2);
            this.e.a(pictureDataItem3);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public boolean a() {
        return this.h;
    }

    public MediaItem b() {
        return this.e;
    }

    public PictureManagerAdapter(Activity activity, NetworkLoadView networkLoadView) {
        this.b = activity;
        this.a = AnimationUtils.loadAnimation(activity, R.anim.unlimited_rotate);
        this.g = networkLoadView;
    }

    public int getCount() {
        int size;
        if (this.c != null) {
            size = this.c.size();
        } else {
            size = 0;
        }
        if (size > 0) {
            return ((size - 1) / 3) + 1;
        }
        return 0;
    }

    public PictureDataItem a(int i) {
        return (PictureDataItem) this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) a(i).a();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        PictureDataItem pictureDataItem;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.picture_manager_item, viewGroup, false);
            view.setTag(new a(this, view));
        }
        a aVar = (a) view.getTag();
        int size = this.c.size();
        int i2 = i * 3;
        PictureDataItem pictureDataItem2 = (PictureDataItem) this.c.get(i2);
        int i3 = i2 + 1;
        PictureDataItem pictureDataItem3 = i3 < size ? (PictureDataItem) this.c.get(i3) : null;
        i3++;
        if (i3 < size) {
            pictureDataItem = (PictureDataItem) this.c.get(i3);
        } else {
            pictureDataItem = null;
        }
        aVar.a(pictureDataItem2, pictureDataItem3, pictureDataItem);
        if (!(pictureDataItem2 == null || aVar.c.d == null)) {
            aVar.c.d.setContentDescription("btnView" + i3);
        }
        if (!(pictureDataItem3 == null || aVar.d.d == null)) {
            aVar.d.d.setContentDescription("btnView" + i3);
        }
        if (!(pictureDataItem == null || aVar.e.d == null)) {
            aVar.e.d.setContentDescription("btnView" + i3);
        }
        return view;
    }

    public void a(final MediaItem mediaItem, String str) {
        this.e = mediaItem;
        if (m.a(str)) {
            Object artist = mediaItem.getArtist();
            if (TTTextUtils.isValidateMediaString(artist)) {
                this.d = e.o(artist);
            } else {
                return;
            }
        }
        this.d = str;
        if (e.a(com.sds.android.ttpod.framework.a.t() + File.separator + this.d + File.separator + "result.xml")) {
            com.sds.android.sdk.lib.e.a.a(this.b, new com.sds.android.sdk.lib.e.a.a<Void, ArrayList<PictureDataItem>>(this, null) {
                final /* synthetic */ PictureManagerAdapter b;

                protected /* synthetic */ Object onDoInBackground(Object obj) {
                    return a((Void) obj);
                }

                protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                    a((ArrayList) obj);
                }

                protected ArrayList<PictureDataItem> a(Void voidR) {
                    Exception e;
                    FileInputStream fileInputStream;
                    Throwable th;
                    String str = com.sds.android.ttpod.framework.a.t() + File.separator + this.b.d + File.separator + "result.xml";
                    FileInputStream fileInputStream2;
                    try {
                        com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
                        fileInputStream2 = new FileInputStream(str);
                        try {
                            aVar.setInput(fileInputStream2, "UTF-8");
                            List<ResultData> a = c.a(aVar, mediaItem, 7, this.b.d);
                            if ((a != null ? a.size() : 0) > 0) {
                                ArrayList<PictureDataItem> arrayList = new ArrayList();
                                for (ResultData resultData : a) {
                                    if (!(resultData == null || resultData.c() == null)) {
                                        for (Item item : resultData.c()) {
                                            if (item != null) {
                                                arrayList.add(new PictureDataItem(item));
                                            }
                                        }
                                    }
                                }
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                return arrayList;
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Exception e22) {
                                    e22.printStackTrace();
                                }
                            }
                            return null;
                        } catch (Exception e3) {
                            e22 = e3;
                            fileInputStream = fileInputStream2;
                            try {
                                e22.printStackTrace();
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Exception e222) {
                                        e222.printStackTrace();
                                    }
                                }
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                fileInputStream2 = fileInputStream;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        e222 = e5;
                        fileInputStream = null;
                        e222.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return null;
                    } catch (Throwable th4) {
                        th = th4;
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        throw th;
                    }
                }

                protected void a(ArrayList<PictureDataItem> arrayList) {
                    if (arrayList != null) {
                        this.b.c = new ArrayList(arrayList);
                    } else {
                        this.b.c = null;
                    }
                    if (this.b.c == null || this.b.c.isEmpty()) {
                        this.b.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                    } else {
                        this.b.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
                    }
                    this.b.notifyDataSetChanged();
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.b.c = null;
                    this.b.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                    g.a("PictureManagerAdapter", "onCancelled %s - %s", mediaItem.getArtist(), mediaItem.getTitle());
                    this.b.notifyDataSetChanged();
                }
            });
        } else {
            this.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.IDLE);
        }
    }

    public boolean c() {
        return this.f;
    }

    public void a(final String str) {
        final String b = b(str);
        if (!this.f && !m.a(b)) {
            this.h = true;
            this.f = true;
            this.c = null;
            notifyDataSetChanged();
            this.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.LOADING);
            com.sds.android.sdk.lib.e.a.a(this.b, new com.sds.android.sdk.lib.e.a.a<Void, Integer>(this, null) {
                final /* synthetic */ PictureManagerAdapter c;

                protected /* synthetic */ Object onDoInBackground(Object obj) {
                    return a((Void) obj);
                }

                protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                    a((Integer) obj);
                }

                protected Integer a(Void voidR) {
                    InputStream inputStream = null;
                    com.sds.android.sdk.lib.a.a.a a = com.sds.android.sdk.lib.a.a.a(b, null, null);
                    if (a != null) {
                        inputStream = a.e();
                    }
                    String a2 = m.a(inputStream);
                    g.a("PictureManagerAdapter", "requestList lookpic %s - %s resultContent=%s", this.c.e.getArtist(), this.c.e.getTitle(), a2);
                    if (a2 != null) {
                        String trim = a2.trim();
                        if (trim.startsWith("<?xml")) {
                            try {
                                com.sds.android.ttpod.framework.modules.search.a.a aVar = new com.sds.android.ttpod.framework.modules.search.a.a();
                                aVar.setInput(new ByteArrayInputStream(trim.getBytes("UTF-8")), "UTF-8");
                                ArrayList a3 = c.a(aVar, null, 2, null);
                                int size = a3 != null ? a3.size() : 0;
                                if (size > 0) {
                                    a2 = e.o(str);
                                    if (TTTextUtils.isValidateMediaString(a2)) {
                                        this.c.d = a2;
                                    } else {
                                        this.c.d = e.o(((ResultData) a3.get(0)).b());
                                    }
                                    c.b(this.c.e.getID(), this.c.d);
                                    e.a(trim, com.sds.android.ttpod.framework.a.t() + File.separator + this.c.d + "/result.xml");
                                }
                                return Integer.valueOf(size);
                            } catch (Exception e) {
                                g.c("PictureManagerAdapter", "searchResult found exception: pic, %s - %s, result=%s", this.c.e.getArtist(), this.c.e.getTitle(), trim);
                                e.printStackTrace();
                            }
                        } else {
                            g.c("PictureManagerAdapter", "searchResult not xml: pic, %s - %s, result=%s", this.c.e.getArtist(), this.c.e.getTitle(), trim);
                            return Integer.valueOf(-1);
                        }
                    }
                    return Integer.valueOf(-1);
                }

                protected void a(Integer num) {
                    this.c.f = false;
                    long longValue = this.c.e.getSongID().longValue();
                    if (num.intValue() > 0) {
                        this.c.a("search", str, 1, longValue);
                        h.a(false, "manual", true, 0, longValue);
                        this.c.a(this.c.e, this.c.d);
                        return;
                    }
                    this.c.a("search", str, 2, longValue);
                    h.a(false, "manual", false, num.intValue(), longValue);
                    if (num.intValue() == 0) {
                        f.a("没有搜索到结果，修改歌手名后重试");
                        this.c.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                        return;
                    }
                    f.a("搜索歌手图片失败，请重试");
                    this.c.g.setLoadState(com.sds.android.ttpod.widget.NetworkLoadView.a.FAILED);
                }
            });
        }
    }

    private void a(String str, String str2, int i, long j) {
        w.a("lyric_pic", str, SocialConstants.PARAM_AVATAR_URI, (long) i, j, str2, this.e.getTitle());
    }

    private String b(String str) {
        if (m.a(str)) {
            return null;
        }
        return c.a(com.sds.android.ttpod.framework.modules.search.a.c.b(this.e, null, str));
    }
}
